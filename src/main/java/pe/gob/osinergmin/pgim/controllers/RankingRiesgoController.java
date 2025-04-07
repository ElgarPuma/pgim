package pe.gob.osinergmin.pgim.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.Ranking;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUm;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmRepository;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.RankingRiesgoService;
import pe.gob.osinergmin.pgim.services.RankingUmService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.services.UnidadMineraService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los
 * rankings de riesgo
 * 
 * @descripción: Rankings de riesgo
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/10/2020
 */

@RestController
@Slf4j
@RequestMapping("/rankingriesgos")
public class RankingRiesgoController extends BaseController {

    @Autowired
    private RankingRiesgoService rankingRiesgoService;

    @Autowired
    private RankingUmService rankingUmService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private UnidadMineraService unidadMineraService;

    @Autowired
    private SupervisionService supervisionService;

    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;

    @Autowired
    private RankingUmGrupoRepository rankingUmGrupoRepository;
    
    @Autowired
    private RankingUmRepository rankingUmRepository;
    
    @Autowired
    private RankingRiesgoRepository rankingRiesgoRepository;
    
    @PreAuthorize("hasAnyAuthority('rr-lista_AC')")
    @PostMapping("/filtrar")
    public ResponseEntity<Page<PgimRankingRiesgoDTO>> filtrar(@RequestBody PgimRankingRiesgoDTO filtroRankingRiesgo,
            Pageable paginador) throws Exception {

        Page<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTO = this.rankingRiesgoService.filtrar(filtroRankingRiesgo,
                paginador, this.obtenerAuditoria());

        return new ResponseEntity<Page<PgimRankingRiesgoDTO>>(lPgimRankingRiesgoDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener las configuraciones necesarias para el listado de ranking de
     * riesgo. Acá se incluyen configuraciones como: Tipo de especialidad.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
        List<PgimValorParametroDTO> lPgimTipoEstadoConfigDTO = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = null;
        PgimPersonaDTO pgimPersonaDTO = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoCfgRiesgo = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOPeriodo = null;

        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

            lPgimTipoEstadoConfigDTO = this.parametroService
                    .filtrarPorNombreTipEstadoConfig(ConstantesUtil.PARAM_TIPO_ESTADO_CONFIGURACION);

            lPgimFaseProcesoDTO = this.instanciaProcesService
                    .obtenerFasesPorIdProceso(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS);

            lPgimPasoProcesoDTO = this.instanciaProcesService
                    .obtenerPasosPorIdProceso(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS);

            lPgimValorParametroDTOTipoCfgRiesgo = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.TIPO_CONFIGURACION_RIESGO);

            pgimPersonaDTO = this.flujoTrabajoService
                    .obtenerPersonaPorNombreUsuario(this.obtenerAuditoria().getUsername());

            lPgimValorParametroDTOPeriodo = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_PERIODO);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de especialidad");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
        respuesta.put("lPgimTipoEstadoConfigDTO", lPgimTipoEstadoConfigDTO);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);
        respuesta.put("lPgimPasoProcesoDTO", lPgimPasoProcesoDTO);
        respuesta.put("pgimPersonaDTO", pgimPersonaDTO);
        respuesta.put("usuarioAutenticado", this.obtenerAuditoria().getUsername());
        respuesta.put("lPgimValorParametroDTOTipoCfgRiesgo", lPgimValorParametroDTOTipoCfgRiesgo);
        respuesta.put("lPgimValorParametroDTOPeriodo", lPgimValorParametroDTOPeriodo);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite filtrar por Número de expediente siged
     * 
     * @param palabra = NuExpedienteSiged
     * @return
     */
    @GetMapping("/filtrar/nuExpedienteSiged/{palabra}")
    public ResponseEntity<?> listarPorNuExpedienteSiged(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTONuExpedienteSiged = null;

        if (palabra.equals("_vacio_")) {
            lPgimRankingRiesgoDTONuExpedienteSiged = new ArrayList<PgimRankingRiesgoDTO>();
            respuesta.put("mensaje", "No se encontraron los números de expediente siged");
            respuesta.put("lPgimRankingRiesgoDTONuExpedienteSiged", lPgimRankingRiesgoDTONuExpedienteSiged);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimRankingRiesgoDTONuExpedienteSiged = this.rankingRiesgoService.listarPorNuExpedienteSiged(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los números de expediente siged");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los números de expediente siged");
        respuesta.put("lPgimRankingRiesgoDTONuExpedienteSiged", lPgimRankingRiesgoDTONuExpedienteSiged);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener el Ranking de riesgo usado en el modo consulta y edición del
     * frontend
     * 
     * @param idRankingRiesgo
     * @return
     * @throws Exception
     */
    @GetMapping("/obtenerRankingRiesgoPorIdInstanciaPaso/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerRankingRiesgoPorIdInstanciaPaso(@PathVariable Long idInstanciaPaso) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();
        PgimRankingRiesgoDTO pgimRankingRiesgoDTO = null;
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOActual = null;
        Long idRankingRiesgo = null;

        try {
            idRankingRiesgo = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getCoTablaInstancia();            
            pgimRankingRiesgoDTO = this.rankingRiesgoService.obtenerRankingRiesgoPorId(idRankingRiesgo);

            if (pgimRankingRiesgoDTO == null) {
                mensaje = String.format("El Ranking de riesgo con el id: %d no existe en la base de datos",
                        idRankingRiesgo);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
            }

            respuestaLog = this.flujoTrabajoService.mostrarLog(pgimRankingRiesgoDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());

            pgimRankingRiesgoDTO.setDescIdInstanciaPaso(idInstanciaPaso);

            pgimInstanPasoAuxDTOActual = this.flujoTrabajoService
                    .obtenerInstanciaPasoAuxPorId(idInstanciaPaso);

        } catch (DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el Ranking de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final PgimException e) {
                        
            // Manejo de logs
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el Ranking de riesgo");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (pgimInstanPasoAuxDTOActual == null) {
            mensaje = String.format(
                    "El ranking con id: %d aún no registra un paso actual en el respectivo flujo de trabajo",
                    idRankingRiesgo);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El Ranking de riesgo ha sido recuperado");
        respuesta.put("pgimRankingRiesgoDTO", pgimRankingRiesgoDTO);
        respuesta.put("pgimInstanPasoAuxDTOActual", pgimInstanPasoAuxDTOActual);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerRankingRiesgo/{idRankingRiesgo}")
    public ResponseEntity<?> obtenerRankingRiesgo(@PathVariable Long idRankingRiesgo) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimRankingRiesgoDTO pgimRankingRiesgoDTO = null;

        try {
            pgimRankingRiesgoDTO = this.rankingRiesgoService.obtenerRankingRiesgoPorId(idRankingRiesgo);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el ranking");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimRankingRiesgoDTO == null) {
            mensaje = String.format("El ranking con el id: %d no existe en la base de datos", idRankingRiesgo);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El ranking ha sido recuperado");
        respuesta.put("pgimRankingRiesgoDTO", pgimRankingRiesgoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    /**
     * Permite listar los grupos de rankings de unidades mineras calificadas + las
     * herramientas de las
     * paginaciones
     * 
     * @param idRankingRiesgo
     * @param paginador
     * @return
     */
    @PreAuthorize("hasAnyAuthority('rr-cal-rie_AC')")
    @PostMapping("/listarRankingUmGrupo")
    public ResponseEntity<Page<PgimRankingUmAuxDTO>> listarRankingUmGrupo(@RequestBody final Long idRankingRiesgo,
            final Pageable paginador) {

        final Page<PgimRankingUmAuxDTO> rankingUmGrupo = this.rankingUmService.listarRankingUm(idRankingRiesgo,
                paginador);
        return new ResponseEntity<Page<PgimRankingUmAuxDTO>>(rankingUmGrupo, HttpStatus.OK);
    }
    
    /**
     * Permite listar las unidades mineras calificadas en un ranking (RANKING_UM),
     * de acuerdo con los criterios filtro y de manera paginada.
     * 
     * @param filtroUnidadMinera
     * @param paginador
     * @return
     */
    @PostMapping("/listarRankingUmPorRankingRiesgo/{flActivo}")
    public ResponseEntity<Page<PgimRankingUmAuxDTO>> listarRankingUmPorRankingRiesgo(@PathVariable String flActivo,
    		@RequestBody PgimUnidadMineraAuxDTO filtroUnidadMinera, Pageable paginador) {

    	log.info(flActivo);
        final Page<PgimRankingUmAuxDTO> rankingUmGrupo = this.rankingUmService.listarRankingUmPorFiltroUM(filtroUnidadMinera,
        		flActivo, paginador);
        return new ResponseEntity<Page<PgimRankingUmAuxDTO>>(rankingUmGrupo, HttpStatus.OK);
    }

    /**
     * Permite obtener las listas de los select usados en el dialogo Asignación
     * del ranking
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimConfiguraRiesgoDTO> lPgimConfiguraRiesgoDTO = null;
        PgimRelacionPasoDTO pgimRelacionPasoDTOInicial = null;

        // para la fecha por defecto
        PgimRankingRiesgoDTO pgimRankingRiesgoDTO = new PgimRankingRiesgoDTO();
        Date fecha = new Date();
        pgimRankingRiesgoDTO.setFeGeneracionRanking(fecha);

        List<PgimValorParametroDTO> lPgimValorParametroDTOConfigRiesgo = null;

        try {
            lPgimConfiguraRiesgoDTO = this.rankingRiesgoService.obtenerConfiguracionParaAsignacion();
            pgimRelacionPasoDTOInicial = this.flujoTrabajoService
                    .obtenerRelacionPasoInicial(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS);

            lPgimValorParametroDTOConfigRiesgo = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.TIPO_CONFIGURACION_RIESGO);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimConfiguraRiesgoDTO", lPgimConfiguraRiesgoDTO);
        respuesta.put("pgimRelacionPasoDTOInicial", pgimRelacionPasoDTOInicial);
        respuesta.put("pgimRankingRiesgoDTO", pgimRankingRiesgoDTO);
        respuesta.put("lPgimValorParametroDTOConfigRiesgo", lPgimValorParametroDTOConfigRiesgo);
        
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite asignar el ranking de riesgos
     * 
     * @param pgimRankingRiesgoDTO Objeto portador de datos para la asignación del
     *                             ranking de riesgos
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('rr-lista_IN')")
    @PostMapping("/asignarRanking")
    public ResponseEntity<ResponseDTO> asignarRanking(@Valid @RequestBody PgimRankingRiesgoDTO pgimRankingRiesgoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimRankingRiesgoDTO pgimRankingRiesgoDTOAsignado = null;
        ResponseDTO responseDTO = null;

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());
            
            log.error(errores.toString());

            responseDTO = new ResponseDTO(TipoResultado.ERROR, errores.toString());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            pgimRankingRiesgoDTOAsignado = this.rankingRiesgoService.asignarRanking(pgimRankingRiesgoDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar el ranking");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (PgimException e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar el ranking");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimRankingRiesgoDTOAsignado,
                "Estupendo, el ranking ha sido creado y asignado");
        responseDTO.setData(pgimRankingRiesgoDTOAsignado);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Permite obtener los grupos de riesgo de un determinado ranking y tipo de
     * grupo de riesgo.
     * 
     * @param idRankingUm
     * @param idGrupoRiesgo
     * @return
     */
    @GetMapping("/listarGruposRiesgo/{idRankingUm}/{idRankingSupervision}/{idGrupoRiesgo}")
    public ResponseEntity<ResponseDTO> listarGruposRiesgo(@PathVariable Long idRankingUm,
            @PathVariable Long idRankingSupervision, @PathVariable Long idGrupoRiesgo) {
        ResponseDTO responseDTO = null;

        List<PgimRankingUmGrupoDTO> lPgimRankingUmGrupoDTO = null;

        try {
            if (idRankingUm != 0) {
                lPgimRankingUmGrupoDTO = this.rankingRiesgoService.listarGruposRiesgoPorRanking(idRankingUm,
                        idGrupoRiesgo);
            } else if (idRankingSupervision != 0) {
                lPgimRankingUmGrupoDTO = this.rankingRiesgoService
                        .listarGruposRiesgoPorSupervision(idRankingSupervision, idGrupoRiesgo);
            } else {
                throw new PgimException("error", "No se ha podido encontrar la lista de grupos");
            }

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            responseDTO = new ResponseDTO("error",
                    "Ocurrió un error al intentar recuperar la lista de grupos de riesgo de la configuración");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", lPgimRankingUmGrupoDTO, "Los grupos de riesgo han sido recuperados");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/listarRankingUmFactor/{idRankingUmGrupo}/{idGrupoRiesgo}/{idCfgGrupoRiesgo}")
    public ResponseEntity<?> listarRankingUmFactor(@PathVariable Long idRankingUmGrupo,
            @PathVariable Long idGrupoRiesgo, @PathVariable Long idCfgGrupoRiesgo) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = null;
        List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgoDTO = null;
        List<PgimNivelRiesgoDTO> lPgimNivelRiesgoDTO = null;
        PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO = null;
        PgimSupervisionDTO pgimSupervisionDTO = null;

        try {
            lPgimRankingUmFactorDTO = this.rankingRiesgoService.listarRankingUmFactor(idRankingUmGrupo, idGrupoRiesgo);
            lPgimCfgNivelRiesgoDTO = this.rankingRiesgoService.listarCfgNivelRiesgo(idCfgGrupoRiesgo);
            lPgimNivelRiesgoDTO = this.rankingRiesgoService.listarNivelRiesgo();

            pgimRankingUmGrupoDTO = rankingRiesgoService
                    .obtenerUnidadMineraPorRankingGrupoUmAndGrupoRiesgo(idRankingUmGrupo, idGrupoRiesgo);

            if (pgimRankingUmGrupoDTO == null) {
                // si no hay UM, corresponde a una supervisión
                pgimRankingUmGrupoDTO = rankingRiesgoService.obtenerRankingUMGrupoPorId(idRankingUmGrupo);
            }
            // Obtenemos la supervisión referente (si existe)
            pgimSupervisionDTO = rankingRiesgoService.obtenerSupervisionPorIdRankingUmGrupo(idRankingUmGrupo);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la lista de ranking um factor");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (lPgimRankingUmFactorDTO == null) {
            mensaje = String.format("El ranking UM factor con el id: %d no existe en la base de datos",
                    idRankingUmGrupo);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El ranking um factor ha sido recuperado");
        respuesta.put("lPgimRankingUmFactorDTO", lPgimRankingUmFactorDTO);
        respuesta.put("lPgimCfgNivelRiesgoDTO", lPgimCfgNivelRiesgoDTO);
        respuesta.put("lPgimNivelRiesgoDTO", lPgimNivelRiesgoDTO);
        respuesta.put("pgimRankingUmGrupoDTO", pgimRankingUmGrupoDTO);
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/modificarRanking")
    public ResponseEntity<?> modificarRanking(@Valid @RequestBody Ranking ranking,
            BindingResult resultadoValidacion) throws Exception {

        Ranking rankingModificado = null;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        if (ranking.getlPgimRankingUmFactorDTO().get(0).getIdRankingUmGrupo() != null) {

            PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO = this.rankingRiesgoService.obtenerRankingUMGrupoPorId(ranking.getlPgimRankingUmFactorDTO().get(0).getIdRankingUmGrupo());

            if (pgimRankingUmGrupoDTO != null) {
                respuestaLog = this.mostrarLogRankingSupervision(pgimRankingUmGrupoDTO);
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el ranking");
            respuesta.put("error", errores.toString());

            log.error(this.obtenerAuditoria().getUsername() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            rankingModificado = this.rankingRiesgoService.modificarRankingUmFactor(ranking, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el ranking");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final PgimException e) {
            // Manejo de logs
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el ranking");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        respuesta.put("mensaje", "El ranking ha sido modificado");
        respuesta.put("rankingModificado", rankingModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    public Map<String, Object> mostrarLogRankingSupervision(PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO) throws Exception{

        Map<String, Object> respuestaLog = new HashMap<>();

        PgimRankingSupervisionDTO pgimRankingSupervisionDTO = null;
        PgimSupervisionDTO pgimSupervisionDTO = null;
        PgimRankingUmDTO pgimRankingUmDTO = null;
        PgimRankingRiesgoDTO pgimRankingRiesgoDTO = null;
        Long idRankingSupervision = null;
        Long idRankingUm = null;
        Long idRankingRiesgo = null;
        Long idInstanciaProceso = null;
        
        idRankingSupervision = pgimRankingUmGrupoDTO.getIdRankingSupervision();
        idRankingUm = pgimRankingUmGrupoDTO.getIdRankingUm();
       
        /////////////////////////////////////////////////////////////////////////////////////
        // PASA A LA SUPERVISIÓN
        /////////////////////////////////////////////////////////////////////////////////////
        if (idRankingSupervision != null) {
            pgimRankingSupervisionDTO = this.rankingRiesgoService.obtenerRankingSupervisionPorId(idRankingSupervision);

            if (pgimRankingSupervisionDTO != null) {
                pgimSupervisionDTO = this.supervisionService.obtenerSupervisionPorIdAux(pgimRankingSupervisionDTO.getIdSupervision());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if (pgimSupervisionDTO != null) {
                idInstanciaProceso = pgimSupervisionDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {

            /////////////////////////////////////////////////////////////////////////////////////
            // PASA AL RANKING DE RIESGO
            /////////////////////////////////////////////////////////////////////////////////////
            if(idRankingUm != null){
                pgimRankingUmDTO = this.rankingRiesgoService.obtenerRankingUMPorId(idRankingUm);
                idRankingRiesgo = pgimRankingUmDTO.getIdRankingRiesgo();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idRankingRiesgo != null){
                pgimRankingRiesgoDTO = this.rankingRiesgoService.obtenerRankingRiesgoPorId(idRankingRiesgo);
                idInstanciaProceso = pgimRankingRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }

        if (idInstanciaProceso != null) {
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }
        
        return respuestaLog;
    }

    @PreAuthorize("hasAnyAuthority('rr-lista_MO')")
    @PutMapping("/modificarRankingRiego")
    public ResponseEntity<?> modificarRankingRiego(@Valid @RequestBody PgimRankingRiesgoDTO pgimRankingRiesgoDTO, BindingResult resultadoValidacion) throws Exception {

        PgimRankingRiesgoDTO pgimRankingRiesgoDTOIdRankingRiesgo = this.rankingRiesgoService.obtenerRankingRiesgoPorId(pgimRankingRiesgoDTO.getIdRankingRiesgo());
        Map<String, Object> respuestaLog = this.flujoTrabajoService.mostrarLog(pgimRankingRiesgoDTOIdRankingRiesgo.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());

        PgimRankingRiesgo pgimRankingRiesgoActual = null;
        PgimRankingRiesgoDTO pgimRankingRiesgoDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el ranking de riesgo");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimRankingRiesgoActual = this.rankingRiesgoService.getByIdRankingRiesgo(pgimRankingRiesgoDTO.getIdRankingRiesgo());

            if (pgimRankingRiesgoActual == null) {
                mensaje = String.format("El ranking de riesgo %s que intenta actualizar no existe en la base de datos",
                        pgimRankingRiesgoDTO.getIdRankingRiesgo());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el ranking de riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[Ocurrió un error al intentar modificar el ranking de riesgo]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimRankingRiesgoDTOModificada = this.rankingRiesgoService.modificarRankingRiesgo(pgimRankingRiesgoDTO, pgimRankingRiesgoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el ranking de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[Ocurrió un error al intentar modificar el ranking de riesgo]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El ranking de riesgo ha sido modificada");
        respuesta.put("pgimRankingRiesgoDTOModificada", pgimRankingRiesgoDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite obtener la lista de grupos de factores de riesgo x supervisión
     * 
     * @param idLineaPrograma
     * @return
     * @throws Exception
     */

    @GetMapping("/listarGruposFactorRiesgoSuper/{idSupervision}")
    public ResponseEntity<List<PgimRankingUmGrupoDTO>> listarGruposFactorRiesgoSuper(
            @PathVariable Long idSupervision) throws Exception {

        final List<PgimRankingUmGrupoDTO> lPgimRankingUmGrupoDTO = this.rankingRiesgoService
                .listarRankingUmGrupoPorSupervision(idSupervision);

        return new ResponseEntity<List<PgimRankingUmGrupoDTO>>(lPgimRankingUmGrupoDTO, HttpStatus.OK);
    }
  
    /**
     * Permite obtener la lista de factores por id del RANKING_UM
     * 
     * @param idRankingUm
     * @return
     * @throws Exception
     */
    @GetMapping("/listarRankingUmFactor/{idRankingUm}")
    public ResponseEntity<List<PgimRankingUmFactorDTO>> listarRankingUmFactor(
            @PathVariable Long idRankingUm) throws Exception {

        final List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = this.rankingRiesgoService
                .listarRankingUMFactorPorRankingUm(idRankingUm);

        return new ResponseEntity<List<PgimRankingUmFactorDTO>>(lPgimRankingUmFactorDTO, HttpStatus.OK);
    }

    @GetMapping("/listarResumenRankingUmGrupo/{idRankingRiesgo}/{esAnonimizacion}")
    public ResponseEntity<?> listarResumenRankingUmGrupo(@PathVariable Long idRankingRiesgo,
            @PathVariable String esAnonimizacion) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimRankingUmAuxDTO> listaPgimRankingUmAuxDTO = new LinkedList<PgimRankingUmAuxDTO>();
        List<PgimNivelRiesgoDTO> listaPgimNivelRiesgoDTO = new LinkedList<PgimNivelRiesgoDTO>();

        List<PgimNivelRiesgoDTO> lPgimNivelRiesgoDTO = this.rankingRiesgoService.listarNivelRiesgoDesc();
        for (PgimNivelRiesgoDTO pgimNivelRiesgoDTO : lPgimNivelRiesgoDTO) {
            pgimNivelRiesgoDTO.setCoColorHexadecimal("#" + pgimNivelRiesgoDTO.getCoColorHexadecimal());
            listaPgimNivelRiesgoDTO.add(pgimNivelRiesgoDTO);
        }

        List<PgimRankingUmAuxDTO> lPgimRankingUmAuxDTO = null;
        if (esAnonimizacion.equals("1")) {
            lPgimRankingUmAuxDTO = this.rankingUmService.listarResumenPorAnonimizacion(idRankingRiesgo);
        } else {
            lPgimRankingUmAuxDTO = this.rankingUmService.listarRankingUmByIdRanking(idRankingRiesgo);
        }

        for (PgimRankingUmAuxDTO pgimRankingUmAuxDTO : lPgimRankingUmAuxDTO) {

            // Si el puntaje general es nulo, se completa la informacion con cero
            if (pgimRankingUmAuxDTO.getPuntajeGeneral() == null) {
                pgimRankingUmAuxDTO.setPuntajeGeneral(new BigDecimal(0));
            } else {
                Double puntajeGeneral = pgimRankingUmAuxDTO.getPuntajeGeneral().doubleValue()
                        * new BigDecimal(100).doubleValue();
                pgimRankingUmAuxDTO.setPuntajeGeneral(new BigDecimal(puntajeGeneral));
            }

            for (PgimNivelRiesgoDTO pgimNivelRiesgoDTO : lPgimNivelRiesgoDTO) {
                if (pgimNivelRiesgoDTO.getNuValorInferior() != null
                        && pgimNivelRiesgoDTO.getNuValorSuperior() != null) {
                    if (pgimRankingUmAuxDTO.getPuntajeGeneral().doubleValue() >= pgimNivelRiesgoDTO.getNuValorInferior()
                            .doubleValue() &&
                            pgimRankingUmAuxDTO.getPuntajeGeneral().doubleValue() < pgimNivelRiesgoDTO
                                    .getNuValorSuperior().doubleValue()) {

                        pgimRankingUmAuxDTO.setDescCoNivelRiesgo(pgimNivelRiesgoDTO.getCoNivelRiesgo());
                        pgimRankingUmAuxDTO.setDescNuValorInferior(pgimNivelRiesgoDTO.getNuValorInferior());
                        pgimRankingUmAuxDTO.setDescNuValorSuperior(pgimNivelRiesgoDTO.getNuValorSuperior());
                        pgimRankingUmAuxDTO.setDescCoColorHexadecimal(pgimNivelRiesgoDTO.getCoColorHexadecimal());
                        break;
                    }

                } else if (pgimNivelRiesgoDTO.getNuValorInferior() == null
                        && pgimNivelRiesgoDTO.getNuValorSuperior() != null) {
                    if (pgimRankingUmAuxDTO.getPuntajeGeneral().doubleValue() >= pgimNivelRiesgoDTO.getNuValorSuperior()
                            .doubleValue()) {
                        pgimRankingUmAuxDTO.setDescCoNivelRiesgo(pgimNivelRiesgoDTO.getCoNivelRiesgo());
                        pgimRankingUmAuxDTO.setDescNuValorInferior(pgimNivelRiesgoDTO.getNuValorInferior());
                        pgimRankingUmAuxDTO.setDescNuValorSuperior(pgimNivelRiesgoDTO.getNuValorSuperior());
                        pgimRankingUmAuxDTO.setDescCoColorHexadecimal(pgimNivelRiesgoDTO.getCoColorHexadecimal());
                        break;
                    }
                }
            }

            listaPgimRankingUmAuxDTO.add(pgimRankingUmAuxDTO);
        }

        respuesta.put("lPgimRankingUmAuxDTO", listaPgimRankingUmAuxDTO);
        respuesta.put("lPgimNivelRiesgoDTO", lPgimNivelRiesgoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/asegurarRegistrarEnRankingUmGrupo")
    public ResponseEntity<ResponseDTO> asegurarRegistrarEnRankingUmGrupo(@RequestBody final PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO) throws Exception {

        Map<String, Object> respuestaLog = new HashMap<>();
        
        PgimRankingUmGrupoDTO pgimRankingUmGrupoDTOIdRankingRiesgo = this.rankingUmGrupoRepository.obtenerRankingUmGrupoPorId(pgimRankingUmGrupoDTO.getIdRankingUmGrupo());
        PgimRankingRiesgoDTO pgimRankingRiesgoDTO = this.rankingRiesgoService.obtenerRankingRiesgoPorId(pgimRankingUmGrupoDTOIdRankingRiesgo.getDescIdRankingRiesgo());
        if(pgimRankingRiesgoDTO != null)
            respuestaLog = this.flujoTrabajoService.mostrarLog(pgimRankingRiesgoDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
        else
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());

        final Long idRankingUmGrupo = pgimRankingUmGrupoDTO.getIdRankingUmGrupo();
        final String esRegistrar = pgimRankingUmGrupoDTO.getFlRegistrar();
        final Long idGrupoRiesgo = pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo();

        String mensaje = "";

        try {
            this.rankingUmService.asegurarRegistrarEnRankingUmGrupo(idRankingUmGrupo, esRegistrar, idGrupoRiesgo, this.obtenerAuditoria());
        } catch (final DataAccessException e) {

            mensaje = "Ocurrió un error asegurar la condicón de registro o no del grupo de riesgo";
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error", mensaje));
        } catch (final PgimException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMessage(), e);
            throw e;
        } catch (final Exception e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);
            throw e;
        }

        mensaje = "Se ha logrado asegurar el registro del grupo de riesgo";

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", mensaje));
    }

    @PostMapping("/listarConfiguracionRankings/{idConfiguraRiesgo}")
    public ResponseEntity<Page<PgimRankingRiesgoDTO>> listarConfiguracionRankings(@PathVariable("idConfiguraRiesgo") Long idConfiguraRiesgo, Pageable paginador) throws Exception {
        Page<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTO = this.rankingRiesgoService.listarConfiguracionRankingRiesgo(idConfiguraRiesgo, paginador, this.obtenerAuditoria());
        return new ResponseEntity<Page<PgimRankingRiesgoDTO>>(lPgimRankingRiesgoDTO, HttpStatus.OK);
    }
    
    
    /**
     * Permite obtener las configuraciones necesarias para el diálogo de Selección de unidades mineras del frontend
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguracionesSelectUM")
    public ResponseEntity<?> obtenerConfiguracionesSelectUM() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOSituacion = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoUM = null;
        List<PgimValorParametroDTO> lPgimValorParamDTODivSuper = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOMetodoMinado = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoActividad = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOEstado = null;

        try {
        	lPgimValorParamDTOSituacion = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_SITUACION_UNIDAD_MINERA);
            lPgimValorParamDTOTipoUM = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_UNIDAD_MINERA);
            lPgimValorParamDTODivSuper = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);
            lPgimValorParamDTOMetodoMinado = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_METODO_MINADO);            
            lPgimValorParamDTOTipoActividad = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ACTIVIDAD);
            lPgimValorParamDTOEstado = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_ESTADO_UM);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de especialidad");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOSituacion", lPgimValorParamDTOSituacion);
        respuesta.put("lPgimValorParamDTOTipoUM", lPgimValorParamDTOTipoUM);
        respuesta.put("lPgimValorParamDTODivSuper", lPgimValorParamDTODivSuper);
        respuesta.put("lPgimValorParamDTOMetodoMinado", lPgimValorParamDTOMetodoMinado);
        respuesta.put("lPgimValorParamDTOTipoActividad", lPgimValorParamDTOTipoActividad);
        respuesta.put("lPgimValorParamDTOEstado", lPgimValorParamDTOEstado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Permite crear una lista de entidades de Ranking UM
     * 
     * @param lPgimRankingUmDTO	Lista de objetos portadores de los datos para la creación de las entidades
     * @param resultadoValidacion	Resultado de la validación aplicada a nivel de la entidad del modelo de datos.
     * @return  ResponseEntity
     * @throws Exception	Excepción controlada
     */
    @PostMapping("/crearRankingUm")
    public ResponseEntity<?> crearRankingUm(
           @Valid @RequestBody List<PgimRankingUmDTO> lPgimRankingUmDTO, BindingResult resultadoValidacion)
           throws Exception {

        Map<String, Object> respuestaLog = new HashMap<>();
	      List<PgimRankingUmDTO> lPgimRankingUmDTOCreado = new ArrayList<>();	    
	      List<PgimRankingUmDTO> lPgimRankingUmDTO2 = new ArrayList<>();	
          Long idInstanciaProces = this.rankingRiesgoRepository.findById(lPgimRankingUmDTO.get(0).getIdRankingRiesgo()).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();
	      
          if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
          }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
          }

          Map<String, Object> respuesta = new HashMap<>();
	
	      if (resultadoValidacion.hasErrors()) {
	           List<String> errores = null;
	
	           errores = resultadoValidacion.getFieldErrors().stream()
	                   .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
	                   .collect(Collectors.toList());
	
	           respuesta.put("mensaje", "Se han encontrado inconsistencias para crear los ranking-UM");
	           respuesta.put("error", errores.toString());
	
	           return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
	      }
	       
	      try {     	  
	    	    
	    	  Boolean encontrado = false;
	    	  
	    	  List<PgimRankingUmAuxDTO> lPgimRankingUmAuxDTOExistentes = this.rankingUmService.listarRankingUmByIdRanking(lPgimRankingUmDTO.get(0).getIdRankingRiesgo());
	    	  
	    	  for (PgimRankingUmDTO pgimRankingUmDTO : lPgimRankingUmDTO) {
	    		  // Buscamos si ya existe la UM en el ranking
	    		  encontrado = false;
	    		  for (PgimRankingUmAuxDTO pgimRankingUmAuxDTOE : lPgimRankingUmAuxDTOExistentes) {					
	    			  if(pgimRankingUmDTO.getIdUnidadMinera().equals(pgimRankingUmAuxDTOE.getIdUnidadMinera())) {
	    				  encontrado = true;
	    				  break;
	    			  }
	    		  }
	    		  
	    		  if(!encontrado) {
	    			  lPgimRankingUmDTO2.add(pgimRankingUmDTO);
	    		  }
	    	  }
	         
	           lPgimRankingUmDTOCreado = this.rankingRiesgoService.crearRankingUm(lPgimRankingUmDTO2, this.obtenerAuditoria());
	           
	      } catch (DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[Ocurrió un error al intentar crear los ranking-UM]" ); // STORY:PGIM-7606 // DATA
            respuesta.put("mensaje", "Ocurrió un error al intentar crear los ranking-UM");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
	
	           return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
	      }catch (PgimException e) {		

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM			
            log.error(e.getMensaje(), e);
            respuesta.put("mensaje", e.getMensaje());
            respuesta.put("status", e.getTipoResultadoCadena());
            respuesta.put("resultado", "ERROR");
            respuesta.put("error", e.getMensaje());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	      }
	      
	      String mensaje = "Genial, la(s) unidad(es) minera(as) ha(n) sido agregada(s)";
	      if(lPgimRankingUmDTO2.size() < lPgimRankingUmDTO.size()) {
	    	  mensaje = mensaje + ". Sin embargo, algunas ya estuvieron previamente agregadas, por lo cual se las omitió";
	      }
	
	      log.info(mensaje);
	      respuesta.put("mensaje", mensaje);
	      respuesta.put("lPgimRankingUmDTOCreado", lPgimRankingUmDTOCreado);
	
	      return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    /**
     * Permite eliminar un Ranking UM
     * 
     * @param idRankingUm
     * @return
     * @throws Exception
     */
    @DeleteMapping("/eliminarRankingUm/{idRankingUm}")
    public ResponseEntity<?> eliminarRankingUm(@PathVariable Long idRankingUm) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();
        String mensaje;

        PgimRankingUm pgimRankingUmActual = null;

        try {

            Long idInstanciaProceso = this.rankingUmRepository.findById(idRankingUm).orElse(null).getPgimRankingRiesgo().getPgimInstanciaProces().getIdInstanciaProceso();
            
            if(idInstanciaProceso != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        	pgimRankingUmActual = this.rankingUmService.getByIdRankingUm(idRankingUm);

            if (pgimRankingUmActual == null) {
                mensaje = String.format("El ranking_um %s que intenta eliminar no existe en la base de datos",
                		idRankingUm);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el ranking_um a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.rankingUmService.eliminarRankingUm(pgimRankingUmActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            respuesta.put("mensaje", "Ocurrió un error al intentar eliminar el ranking_um");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Permite desactivar/activar un Ranking UM
     * 
     * @param idRankingUm
     * @param flActivo
     * @return
     * @throws Exception
     */
    @GetMapping("/conmutarFlActivoRankingUm/{idRankingUm}/{flActivo}")
    public ResponseEntity<ResponseDTO> conmutarFlActivoRankingUm(@PathVariable Long idRankingUm, @PathVariable String flActivo) throws Exception {
        Map<String, Object> respuestaLog = new HashMap<>();
        String mensaje;
        String accion = "";
        String accion2 = "";

        PgimRankingUm pgimRankingUmActual = null;

        try {

            Long idInstanciaProceso = this.rankingUmRepository.findById(idRankingUm).orElse(null).getPgimRankingRiesgo().getPgimInstanciaProces().getIdInstanciaProceso();
            
            if(idInstanciaProceso != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        	pgimRankingUmActual = this.rankingUmService.getByIdRankingUm(idRankingUm);
        	
        	accion = (flActivo.equals(ConstantesUtil.FL_IND_SI))? "activar" : "desactivar";
        	accion2 = (flActivo.equals(ConstantesUtil.FL_IND_SI))? "activada" : "desactivada";

            if (pgimRankingUmActual == null) {
                mensaje = String.format("El ranking_um %s que intenta %s no existe en la base de datos",
                		idRankingUm, accion);

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            }
        } catch (Exception e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar recuperar el ranking_um a %s", accion);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }

        try {
            this.rankingUmService.conmutarFlActivoRankingUm(pgimRankingUmActual, flActivo, this.obtenerAuditoria());
            
        } catch (DataAccessException e) {
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar %s la unidad fiscalizada del ranking:  %s", accion, e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar %s la unidad fiscalizada del ranking:  %s", accion, e.getMessage());
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }

        mensaje = String.format("Genial, la unidad fiscalizada del ranking ha sido %s", accion2);
        final ResponseDTO responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    
    @PostMapping("/crearRankingRiesgoSupervision")
    public ResponseEntity<ResponseDTO> crearRankingRiesgoSupervision(@RequestBody PgimSupervisionDTO pgimSupervisionDTO) throws Exception {

        ResponseDTO responseDTO = null;
        Map<String, Object> respuestaLog = new HashMap<>();
        PgimSupervisionDTO pgimSupervisionDTOaModificar = null;
        PgimUnidadMinera pgimUnidadMinera = null;
        PgimRankingSupervisionDTO pgimRankingSupervisionDTO = null;

        try {

            if(pgimSupervisionDTO.getIdInstanciaProceso() != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(pgimSupervisionDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            pgimUnidadMinera = this.unidadMineraService.getByIdUnidadMinera(pgimSupervisionDTO.getIdUnidadMinera());
            
            pgimSupervisionDTOaModificar = this.supervisionService.obtenerSupervisionPorId(pgimSupervisionDTO.getIdSupervision());

            pgimRankingSupervisionDTO = this.rankingRiesgoService.obtenerRankingSupervisionPorIdSupervision(pgimSupervisionDTO.getIdSupervision());

            if (pgimUnidadMinera == null) {

                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + String.format("No se ha encontrado ninguna unidad minera asociada a la supervisión '%s'", pgimSupervisionDTO.getCoSupervision()) + "]" ); // STORY:PGIM-7606 // PGIM

                throw new PgimException(TipoResultado.WARNING, String.format("No se ha encontrado ninguna unidad minera asociada a la supervisión '%s'", pgimSupervisionDTO.getCoSupervision()));
            }

            if (pgimSupervisionDTOaModificar == null) {

                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + String.format("La supervision con el id: %d no existe en la base de datos '%s'", pgimSupervisionDTO.getIdSupervision()) + "]" ); // STORY:PGIM-7606 // PGIM

                throw new PgimException(TipoResultado.WARNING, String.format("La supervision con el id: %d no existe en la base de datos '%s'", pgimSupervisionDTO.getIdSupervision()));
            }

            if (pgimRankingSupervisionDTO != null) {
                this.rankingRiesgoService.eliminarRankingSupervision(pgimRankingSupervisionDTO.getIdRankingSupervision(), this.obtenerAuditoria());
            }

            this.rankingRiesgoService.crearRankingRiesgoSupervision(pgimSupervisionDTOaModificar, pgimUnidadMinera, this.obtenerAuditoria());


        } catch (DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[Ocurrió un error al intentar registrar los los riesgos en la fiscalización]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar registrar los los riesgos en la fiscalización " + pgimSupervisionDTO.getIdSupervision());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final PgimException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);

             responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar registrar los los riesgos en la fiscalización " + pgimSupervisionDTO.getIdSupervision());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "Estupendo, los riesgos para la fiscalización fue registrado.");
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
