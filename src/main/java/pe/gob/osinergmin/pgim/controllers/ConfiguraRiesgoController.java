package pe.gob.osinergmin.pgim.controllers;

import java.util.HashMap;
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
import pe.gob.osinergmin.pgim.dtos.FactorRiesgoTecnicoGestion;
import pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgFactorRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRegla;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgGrupoRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimFactorRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.services.ConfiguraRiesgoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.RankingRiesgoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la configuración de riesgo
 *
 * @descripción: Configuración de riesgo
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 05/05/2021
 */
@RestController
@Slf4j
@RequestMapping("/configurariesgo")
public class ConfiguraRiesgoController extends BaseController {

    @Autowired
    private ConfiguraRiesgoService configuraRiesgoService;

    @Autowired
    private RankingRiesgoService rankingRiesgoService;

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;

   
    @PreAuthorize("hasAnyAuthority('cr-lista_AC')")
    @PostMapping("/filtrar")
    public ResponseEntity<Page<PgimConfiguraRiesgoDTO>> filtrar(
            @RequestBody PgimConfiguraRiesgoDTO filtroConfiguraRiesgo,
            Pageable paginador) throws Exception {

        Page<PgimConfiguraRiesgoDTO> lPgimConfiguraRiesgoDTO = this.configuraRiesgoService.filtrar(
                filtroConfiguraRiesgo,
                paginador, this.obtenerAuditoria());

        return new ResponseEntity<Page<PgimConfiguraRiesgoDTO>>(lPgimConfiguraRiesgoDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener las configuraciones generales para la asignación de una
     * configuración de riesgo.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguracionesGenerales") // REVIEW: Revisar log
    public ResponseEntity<?> obtenerConfiguracionesGenerales() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimConfiguraRiesgoDTO> lPgimConfiguraRiesgoDTO = null;
        PgimRelacionPasoDTO pgimRelacionPasoDTOInicial = null;
        List<PgimEspecialidadDTO> lPgimEspecialidadDTOEspecialidad = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOPeriodo = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOConfigRiesgo = null;

        
        try {
            lPgimConfiguraRiesgoDTO = this.rankingRiesgoService.obtenerConfiguracionParaAsignacion();
            pgimRelacionPasoDTOInicial = this.flujoTrabajoService
                    .obtenerRelacionPasoInicial(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO);
            lPgimEspecialidadDTOEspecialidad = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

            lPgimValorParametroDTOPeriodo = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_PERIODO);
            
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
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTOEspecialidad);
        respuesta.put("lPgimValorParametroDTOPeriodo", lPgimValorParametroDTOPeriodo);
        respuesta.put("lPgimValorParametroDTOConfigRiesgo", lPgimValorParametroDTOConfigRiesgo);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
        List<PgimValorParametroDTO> lPgimTipoEstadoConfigDTO = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoCfgRiesgo = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOPeriodo = null;

        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
            lPgimTipoEstadoConfigDTO = this.parametroService
                    .filtrarPorNombreTipEstadoConfig(ConstantesUtil.PARAM_TIPO_ESTADO_CONFIGURACION);
            lPgimValorParametroDTOTipoCfgRiesgo = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.TIPO_CONFIGURACION_RIESGO);                    
            lPgimFaseProcesoDTO = this.instanciaProcesService
                    .obtenerFasesPorIdProceso(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO);
            lPgimPasoProcesoDTO = this.instanciaProcesService
                    .obtenerPasosPorIdProceso(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO);

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
        respuesta.put("lPgimValorParametroDTOTipoCfgRiesgo", lPgimValorParametroDTOTipoCfgRiesgo);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);
        respuesta.put("lPgimPasoProcesoDTO", lPgimPasoProcesoDTO);
        respuesta.put("usuarioAutenticado", this.obtenerAuditoria().getUsername());
        respuesta.put("lPgimValorParametroDTOPeriodo", lPgimValorParametroDTOPeriodo);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('cr-lista_IN')")
    @PostMapping("/asignarConfiguraRiesgo")
    public ResponseEntity<ResponseDTO> asignarConfiguraRiesgo(@Valid @RequestBody PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTOAsignado = null;
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
            pgimConfiguraRiesgoDTOAsignado = this.configuraRiesgoService.asignarConfiguracionRiesgo(pgimConfiguraRiesgoDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar la configuración de riesgo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (PgimException e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar la configuración de riesgo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimConfiguraRiesgoDTOAsignado, "Estupendo, la configuración riesgo ha sido creado y asignado");
        responseDTO.setData(pgimConfiguraRiesgoDTOAsignado);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
    }

    @SuppressWarnings("unused")
    @GetMapping("/obtenerConfiguraRiesgoPorIdInstanciaPaso/{idIntanciaPaso}")
    public ResponseEntity<?> obtenerConfiguraRiesgoPorIdInstanciaPaso(@PathVariable Long idIntanciaPaso) throws Exception {
        String mensaje;

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOActual = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;

        Long idConfiguraRiesgo = null;

        try {
            PgimInstanciaProces pgimInstanciaProces = this.instanciaPasoRepository.findById(idIntanciaPaso).orElse(null).getPgimInstanciaProces(); 
            
            if(pgimInstanciaProces.getPgimProceso().getIdProceso().equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)){
                idConfiguraRiesgo = this.configuraRiesgoService.obtenerConfiguraRiesgoPorIdSupervision(pgimInstanciaProces.getCoTablaInstancia()).getIdConfiguraRiesgo();
            }else{
                idConfiguraRiesgo = pgimInstanciaProces.getCoTablaInstancia();
            }

            if(pgimInstanciaProces.getIdInstanciaProceso() != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(pgimInstanciaProces.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());          
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
            
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);

            pgimConfiguraRiesgoDTO.setDescIdInstanciaPaso(idIntanciaPaso);

            pgimInstanPasoAuxDTOActual = this.flujoTrabajoService
                    .obtenerInstanciaPasoAuxPorId(idIntanciaPaso);

            lPgimFaseProcesoDTO = this.flujoTrabajoService
                    .obtenerFasesInstanciaProceso(pgimInstanPasoAuxDTOActual.getIdInstanciaProceso());

        } catch (DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[Ocurrió un error al intentar recuperar la configuración de riesgo]" );
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la configuración de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimConfiguraRiesgoDTO == null) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".["+ String.format("La configuración de riesgo con el id: %d no existe en la base de datos", idConfiguraRiesgo) +"]" );
            mensaje = String.format("La configuración de riesgo con el id: %d no existe en la base de datos",
                    idConfiguraRiesgo);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        if (pgimInstanPasoAuxDTOActual == null) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".["+ String.format("La configuración de riesgo con id: %d aún no registra un paso actual en el respectivo flujo de trabajo", idConfiguraRiesgo) +"]" );
            mensaje = String.format(
                    "La configuración de riesgo con id: %d aún no registra un paso actual en el respectivo flujo de trabajo",
                    idConfiguraRiesgo);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La configuración de riesgo ha sido recuperada");
        respuesta.put("pgimConfiguraRiesgoDTO", pgimConfiguraRiesgoDTO);
        respuesta.put("pgimInstanPasoAuxDTOActual", pgimInstanPasoAuxDTOActual);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('cr-lista_MO')")
    @PutMapping("/modificarConfiguraRiesgo")
    public ResponseEntity<?> modificarConfiguraRiesgo(
            @Valid @RequestBody PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimConfiguraRiesgo pgimConfiguraRiesgoActual = null;
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        PgimConfiguraRiesgoDTO configuraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());
        Long idInstanciaProceso = null;
        if(configuraRiesgoDTO != null){
            idInstanciaProceso = configuraRiesgoDTO.getIdInstanciaProceso();
        }

        if(idInstanciaProceso != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );
            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el agente fiscalizado");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimConfiguraRiesgoActual = configuraRiesgoService.findById(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());

            if (pgimConfiguraRiesgoActual == null) {
                mensaje = String.format(
                        "La configuración de riesgo %s que intenta actualizar no existe en la base de datos",
                        pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {

            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la configuración de riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimConfiguraRiesgoDTOModificado = this.configuraRiesgoService.modificarConfiguraRiesgo(
                    pgimConfiguraRiesgoDTO,
                    pgimConfiguraRiesgoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {

            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la configuración de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de riesgo ha sido modificado");
        respuesta.put("pgimConfiguraRiesgoDTO", pgimConfiguraRiesgoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguracionesFactor/{idCfgGrupoRiesgo}/{idGrupoRiesgo}/{idEspecialidad}")
    public ResponseEntity<?> obtenerConfiguracionesFactor(@PathVariable Long idCfgGrupoRiesgo,
            @PathVariable Long idGrupoRiesgo, @PathVariable Long idEspecialidad) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        List<PgimNivelRiesgoDTO> lPgimNivelRiesgoDTO = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTODR = null;
        List<PgimFactorRiesgoDTO> lPgimFactorRiesgoDTONI = null;
        List<PgimFactorRiesgoDTO> lPgimFactorRiesgoDTO = null;
        Long idConfiguraRiesgo = null;

        PgimCfgGrupoRiesgoDTO  pgimCfgGrupoRiesgoDTO  = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);

        if(pgimCfgGrupoRiesgoDTO != null){
            idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();
        }

        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);

        if(pgimConfiguraRiesgoDTO != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(pgimConfiguraRiesgoDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            lPgimNivelRiesgoDTO = this.rankingRiesgoService.listarNivelRiesgo();
            lPgimValorParametroDTOTODR = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ORIGEN_DATO_RIESGO);

            lPgimFactorRiesgoDTONI = configuraRiesgoService.listarFactorRiesgoNotInCfgFactorRiesgo(idCfgGrupoRiesgo,
                    idGrupoRiesgo, idEspecialidad);
            lPgimFactorRiesgoDTO = configuraRiesgoService.listarFactorRiesgo(idGrupoRiesgo, idEspecialidad);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al obtener las configuraciones de factor");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" );
			log.error(e.getMessage(), e);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones de factor");
        respuesta.put("lPgimNivelRiesgoDTO", lPgimNivelRiesgoDTO);
        respuesta.put("lPgimValorParametroDTOTODR", lPgimValorParametroDTOTODR);
        respuesta.put("lPgimFactorRiesgoDTONI", lPgimFactorRiesgoDTONI);
        respuesta.put("lPgimFactorRiesgoDTO", lPgimFactorRiesgoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerFactorRiesgoDetalle/{idCfgFactorRiesgo}/{idGrupoRiesgo}")
    public ResponseEntity<?> obtenerFactorRiesgoDetalle(@PathVariable Long idCfgFactorRiesgo,
            @PathVariable Long idGrupoRiesgo) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgoDTO = null;
        PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO = null;
        Long idCfgGrupoRiesgo = null;
        Long idConfiguraRiesgo = null;
        Long idInstanciaProces = null;

        PgimCfgFactorRiesgoDTO cfgFactorRiesgoDTO = this.configuraRiesgoService.obtenerCfgFactorRiesgoPorId(idCfgFactorRiesgo);
        if(cfgFactorRiesgoDTO != null){
            idCfgGrupoRiesgo = cfgFactorRiesgoDTO.getIdCfgGrupoRiesgo();
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
        if(pgimCfgGrupoRiesgoDTO != null){
            idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
        if(pgimConfiguraRiesgoDTO != null){
            idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            lPgimCfgNivelRiesgoDTO = this.configuraRiesgoService
                    .listarCfgNivelRiesgoPorIdCfgFactorRiesgo(idCfgFactorRiesgo);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los detalles del factor riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimCfgFactorRiesgoDTO = this.configuraRiesgoService.obtenerCfgFactorTecnicoGestion(idCfgFactorRiesgo,
                    idGrupoRiesgo);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los detalles del factor riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron los detalles del factor riesgo");
        respuesta.put("lPgimCfgNivelRiesgoDTO", lPgimCfgNivelRiesgoDTO);
        respuesta.put("pgimCfgFactorRiesgoDTO", pgimCfgFactorRiesgoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('fr001_MO')")
    @PostMapping("/crearFactorRiesgoBase")
    public ResponseEntity<?> crearFactorRiesgoBase(
            @Valid @RequestBody PgimFactorRiesgoDTO pgimFactorRiesgoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimFactorRiesgoDTO pgimFactorRiesgoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el factor riesgo base");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimFactorRiesgoDTOCreado = configuraRiesgoService.crearFactorRiesgo(pgimFactorRiesgoDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el factor riesgo base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El factor riesgo base ha sido creado");
        respuesta.put("pgimFactorRiesgoDTO", pgimFactorRiesgoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarCfgFactorRiesgo/{idCfgFactorRiesgo}")
    public ResponseEntity<?> eliminarCfgFactorRiesgo(@PathVariable Long idCfgFactorRiesgo) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        String mensaje;

        PgimCfgFactorRiesgo pgimCfgFactorRiesgoActual = null;
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
        Long idCfgGrupoRiesgo = null;
        Long idConfiguraRiesgo = null;
        Long idInstanciaProces = null;

        PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO = this.configuraRiesgoService.obtenerCfgFactorRiesgoPorId(idCfgFactorRiesgo);

        if(pgimCfgFactorRiesgoDTO != null){
            idCfgGrupoRiesgo = pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo();
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if(idCfgGrupoRiesgo != null){
            pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
            idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if(idConfiguraRiesgo != null){
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
            idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            pgimCfgFactorRiesgoActual = this.configuraRiesgoService.cfgFactorRiesgoFindById(idCfgFactorRiesgo);

            if (pgimCfgFactorRiesgoActual == null) {
                mensaje = String.format(
                        "La configuración de factor riesgo %s que intenta eliminar no existe en la base de datos",
                        idCfgFactorRiesgo);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error intentar recuperar la configuración de factor riesgo a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.configuraRiesgoService.eliminarCfgFactorRiesgo(pgimCfgFactorRiesgoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la configuración de factor riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/crearCfgFactorRiesgo")
    public ResponseEntity<?> crearCfgFactorRiesgo(@Valid @RequestBody FactorRiesgoTecnicoGestion factorRiesgoTecnicoGestion, BindingResult resultadoValidacion) throws Exception {

        PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
            PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;

    Long idInstanciaProces = null;
            Long idConfiguraRiesgo = null;

            pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(factorRiesgoTecnicoGestion.getPgimCfgFactorRiesgoDTO().getIdCfgGrupoRiesgo());
            idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();

            if (idConfiguraRiesgo != null) {
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if (idInstanciaProces != null) {
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear la configuración de factor riesgo");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimCfgFactorRiesgoDTOCreado = this.configuraRiesgoService.crearCfgFactorRiesgo(
                    factorRiesgoTecnicoGestion.getPgimCfgFactorRiesgoDTO(),
                    factorRiesgoTecnicoGestion.getlPgimCfgNivelRiesgoDTO(), this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la configuración de factor riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]");
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de factor riesgo ha sido creado");
        respuesta.put("pgimCfgFactorRiesgoDTO", pgimCfgFactorRiesgoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarCfgFactorRiesgo")
    public ResponseEntity<?> modificarCfgFactorRiesgo(
            @Valid @RequestBody FactorRiesgoTecnicoGestion factorRiesgoTecnicoGestion,
            BindingResult resultadoValidacion)
            throws Exception {

        PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO = factorRiesgoTecnicoGestion.getPgimCfgFactorRiesgoDTO();
        List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgoDTO = factorRiesgoTecnicoGestion.getlPgimCfgNivelRiesgoDTO();

        PgimCfgFactorRiesgo pgimCfgFactorRiesgoActual = null;
        PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;

        Long idInstanciaProces = null;
        Long idConfiguraRiesgo = null;

 pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(factorRiesgoTecnicoGestion.getPgimCfgFactorRiesgoDTO().getIdCfgGrupoRiesgo());
        idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();

    if (idConfiguraRiesgo != null) {
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
     idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
    } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (idInstanciaProces != null) {
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje",
                    "Se han encontrado inconsistencias para modificar la configuración de factor riesgo");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimCfgFactorRiesgoActual = configuraRiesgoService.cfgFactorRiesgoFindById(factorRiesgoTecnicoGestion.getPgimCfgFactorRiesgoDTO().getIdCfgFactorRiesgo());

            if (pgimCfgFactorRiesgoActual == null) {
                mensaje = String.format(
                        "La configuración de factor riesgo %s que intenta actualizar no existe en la base de datos",
                        pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error intentar recuperar la configuración de factor riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimCfgFactorRiesgoDTOModificado = this.configuraRiesgoService.modificarCfgFactorRiesgo(
                    pgimCfgFactorRiesgoDTO,
                    lPgimCfgNivelRiesgoDTO, pgimCfgFactorRiesgoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la configuración de factor riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de factor riesgo ha sido modificada");
        respuesta.put("pgimCfgFactorRiesgoDTO", pgimCfgFactorRiesgoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarCfgMatrizParesNumeradorDenominador")
    public ResponseEntity<?> modificarCfgMatrizParesNumeradorDenominador(
            @Valid @RequestBody PgimCfgMatrizParesDTO pgimCfgMatrizParesDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOModificado = null;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;

            Long idInstanciaProces = null;
       
            if (pgimCfgMatrizParesDTO.getDescIdConfiguraRiesgo() != null) {
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(pgimCfgMatrizParesDTO.getDescIdConfiguraRiesgo());
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if (idInstanciaProces != null) {
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje",
                    "Se han encontrado inconsistencias para modificar la configuración de matriz de pares");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimCfgMatrizParesDTOModificado = this.configuraRiesgoService.modificarCfgMatrizParesNumeradorDenominador(
                    pgimCfgMatrizParesDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la configuración de matriz de pares");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de matriz de pares ha sido modificada");
        respuesta.put("pgimCfgMatrizParesDTO", pgimCfgMatrizParesDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('fr001_AC')")
    @PostMapping("/listarFactorRiesgoBase")
    public ResponseEntity<Page<PgimFactorRiesgoDTO>> listarFactorRiesgoBase(
            @RequestBody PgimFactorRiesgoDTO filtroFactorRiesgo,
            Pageable paginador) throws Exception {

        Page<PgimFactorRiesgoDTO> lPgimFactorRiesgoDTO = this.configuraRiesgoService.listarFactorRiesgoBase(
                filtroFactorRiesgo, paginador,
                this.obtenerAuditoria());

        return new ResponseEntity<Page<PgimFactorRiesgoDTO>>(lPgimFactorRiesgoDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguracionesFactorRiesgoBase")
    public ResponseEntity<?> obtenerConfiguracionesFactorRiesgoBase() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
        List<PgimGrupoRiesgoDTO> lPgimGrupoRiesgoDTO = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTODR = null;

        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de especialidad");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            lPgimGrupoRiesgoDTO = this.parametroService.filtrarPorGrupoRiesgo();
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del grupo de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            lPgimValorParametroDTOTODR = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ORIGEN_DATO_RIESGO);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del tipo de origen de dato riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
        respuesta.put("lPgimGrupoRiesgoDTO", lPgimGrupoRiesgoDTO);
        respuesta.put("lPgimValorParametroDTOTODR", lPgimValorParametroDTOTODR);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('fr001_AC')")
    @GetMapping("/obtenerFactorRiesgoBase/{idFactorRiesgo}")
    public ResponseEntity<?> obtenerFactorRiesgoBase(@PathVariable Long idFactorRiesgo) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimFactorRiesgoDTO pgimFactorRiesgoDTO = null;

        try {
            pgimFactorRiesgoDTO = this.configuraRiesgoService.obtenerFactorRiesgoBase(idFactorRiesgo);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el factor de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimFactorRiesgoDTO == null) {
            mensaje = String.format("El factor de riesgo con el id: %d no existe en la base de datos", idFactorRiesgo);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El factor de riesgo ha sido recuperado");
        respuesta.put("pgimFactorRiesgoDTO", pgimFactorRiesgoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('fr001_MO')")
    @PutMapping("/modificarFactorRiesgoBase")
    public ResponseEntity<?> modificarFactorRiesgoBase(@Valid @RequestBody PgimFactorRiesgoDTO pgimFactorRiesgoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimFactorRiesgo pgimFactorRiesgoActual = null;
        PgimFactorRiesgoDTO pgimFactorRiesgoDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el factor de riesgo");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimFactorRiesgoActual = this.configuraRiesgoService
                    .factorRiesgoFindById(pgimFactorRiesgoDTO.getIdFactorRiesgo());

            if (pgimFactorRiesgoActual == null) {
                mensaje = String.format("El factor de riesgo %s que intenta actualizar no existe en la base de datos",
                        pgimFactorRiesgoDTO.getIdFactorRiesgo());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el factor de riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimFactorRiesgoDTOModificada = this.configuraRiesgoService.modificarFactorRiesgoBase(pgimFactorRiesgoDTO,
                    pgimFactorRiesgoActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el factor de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El factor de riesgo ha sido modificado");
        respuesta.put("pgimFactorRiesgoDTO", pgimFactorRiesgoDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('fr001_EL')")
    @DeleteMapping("/eliminarFactorRiesgoBase/{idFactorRiesgo}")
    public ResponseEntity<ResponseDTO> eliminarFactorRiesgoBase(@PathVariable Long idFactorRiesgo) throws Exception {
        
    	String mensaje;

        PgimFactorRiesgo pgimFactorRiesgoActual = null;

        try {
            pgimFactorRiesgoActual = this.configuraRiesgoService.factorRiesgoFindById(idFactorRiesgo);

            if (pgimFactorRiesgoActual == null) {
                mensaje = String.format("El factor de riesgo %s que intenta eliminar no existe en la base de datos",
                        idFactorRiesgo);
                log.warn(mensaje);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.WARNING, mensaje));
            }
        } catch (DataAccessException e) {
        	mensaje = "Ocurrió un error intentar recuperar el factor de riesgo a eliminar. Error: " + e.getMessage();
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }

        try {
            this.configuraRiesgoService.eliminarFactorRiesgoBase(pgimFactorRiesgoActual, this.obtenerAuditoria());
            
        } catch (PgimException e) {
			log.warn(e.getMessage(), e);      
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje()));
			
        } catch (DataAccessException e) {
        	mensaje = "Ocurrió un error intentar eliminar el factor riesgo. Error: " + e.getMessage();
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }

        mensaje = "Genial, el factor de riesgo ha sido eliminado";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, mensaje));
    }

    // PGIM 5007 - pjimenez
    @PreAuthorize("hasAnyAuthority('fr001_AC')")
    @GetMapping("/obtenerGrupoRiesgoGestion/{idConfiguraRiesgo}/{idGrupoRiesgo}")
    public ResponseEntity<?> obtenerGrupoFactorTecnicoGestion(@PathVariable Long idConfiguraRiesgo,
            @PathVariable Long idGrupoRiesgo) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTO = null;
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
            Long idInstanciaProces = null;

            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
            idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();

        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            lPgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.listarCfgGrupoRiesgo(idConfiguraRiesgo, idGrupoRiesgo);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los grupos de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);
        } catch (final Exception e) {
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" );
                log.error(e.getMessage(), e);
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        respuesta.put("mensaje", "Se obtuvieron los grupos de riesgo");
        respuesta.put("lPgimCfgGrupoRiesgoDTO", lPgimCfgGrupoRiesgoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/listarReglasRiesgoPorConfiguracion/{idConfiguraRiesgo}")
    public ResponseEntity<?> listarReglasRiesgoPorConfiguracion(@PathVariable Long idConfiguraRiesgo) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        List<PgimConfiguraReglaDTO> lPgimConfiguraReglaDTO = null;
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
        Long idInstanciaProces = null;

            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
            idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();

        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            lPgimConfiguraReglaDTO = this.configuraRiesgoService.listarReglasRiesgoPorConfiguracion(idConfiguraRiesgo);
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar la consulta de las reglas de riesgo por configuración");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las reglas de riesgo por configuración");
        respuesta.put("lPgimConfiguraReglaDTO", lPgimConfiguraReglaDTO);
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    // PGIM 5008 - pjimenez
    @PostMapping("/crearCfgGrupoRiesgo")
    public ResponseEntity<?> crearCfgGrupoRiesgo(
            @Valid @RequestBody PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
           PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
    Long idInstanciaProces = null;

            if(pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo() != null){
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo());
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear la configuración de grupo riesgo");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimCfgGrupoRiesgoDTOCreado = configuraRiesgoService.crearCfgGrupoRiesgo(pgimCfgGrupoRiesgoDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la configuración de grupo riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de grupo riesgo ha sido creado");
        respuesta.put("pgimCfgGrupoRiesgoDTO", pgimCfgGrupoRiesgoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    // PGIM-5009 - pjimenez
    @GetMapping("/obtenerCfgGrupoRiesgoPorId/{idCfgGrupoRiesgo}")
    public ResponseEntity<?> obtenerCfgGrupoRiesgoPorId(@PathVariable Long idCfgGrupoRiesgo) throws Exception {
        System.out.println("idCfgGrupoRiesgo: " + idCfgGrupoRiesgo);
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
            PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
    Long idConfiguraRiesgo = null;
            Long idInstanciaProces = null;

            if(idCfgGrupoRiesgo != null){
                pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
                idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idConfiguraRiesgo != null){
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los detalles del grupo riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron los detalles del grupo riesgo");
        respuesta.put("pgimCfgGrupoRiesgoDTO", pgimCfgGrupoRiesgoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    // PGIM-5009 - pjimenez
    @GetMapping("/obtenerFactorTecnicoGestion/{idCfgGrupoRiesgo}")
    public ResponseEntity<?> obtenerFactorTecnicoGestion(@PathVariable Long idCfgGrupoRiesgo) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTO = null;
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
           Long idConfiguraRiesgo = null;
            Long idInstanciaProces = null;

        if (idCfgGrupoRiesgo != null) {
            pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
        idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();
            } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if (idConfiguraRiesgo != null) {
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        try {
            lPgimCfgFactorRiesgoDTO = this.configuraRiesgoService.listarCfgFactorTecnicoPorCfgGrupo(idCfgGrupoRiesgo);

            pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCalculoRatioConsistenciaPorCfgGrupo(idCfgGrupoRiesgo);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los factores técnicos");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron los factores técnicos");
        respuesta.put("lPgimCfgFactorRiesgoDTO", lPgimCfgFactorRiesgoDTO);
        respuesta.put("pgimCfgGrupoRiesgoDTO", pgimCfgGrupoRiesgoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    // PGIM-5009 - pjimenez
    @GetMapping("/obtenerMatrizPares/{idCfgGrupoRiesgo}")
    public ResponseEntity<?> obtenerMatrizPares(@PathVariable Long idCfgGrupoRiesgo) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTO = null;
        List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTO = null;
    PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
            PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
            Long idConfiguraRiesgo = null;
            Long idInstanciaProces = null;

        if (idCfgGrupoRiesgo != null) {
            pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
        idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();
            } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if (idConfiguraRiesgo != null) {
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        try {
            lPgimCfgFactorRiesgoDTO = this.configuraRiesgoService.listarCfgFactorTecnicoPorCfgGrupo(idCfgGrupoRiesgo);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los factores técnicos o de gestión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            lPgimCfgMatrizParesDTO = this.configuraRiesgoService.obtenerCfgMatrizParesTCPorCfgGrupo(idCfgGrupoRiesgo);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la matriz de pares");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron los factores técnicos o de gestión");
        respuesta.put("lPgimCfgFactorRiesgoDTO", lPgimCfgFactorRiesgoDTO);
        respuesta.put("lPgimCfgMatrizParesDTO", lPgimCfgMatrizParesDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    // PGIM-5009 - pjimenez
    @GetMapping("/obtenerMatrizNormalizada/{idCfgGrupoRiesgo}")
    public ResponseEntity<?> obtenerMatrizNormalizada(@PathVariable Long idCfgGrupoRiesgo) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTO = null;
        List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTO = null;
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
            PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
            Long idConfiguraRiesgo = null;
            Long idInstanciaProces = null;

        if (idCfgGrupoRiesgo != null) {
        pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
                idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();
        } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if (idConfiguraRiesgo != null) {
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        try {
            lPgimCfgFactorRiesgoDTO = this.configuraRiesgoService.listarCfgFactorTecnicoPorCfgGrupo(idCfgGrupoRiesgo);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los factores técnicos o de gestión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            lPgimCfgMatrizParesDTO = this.configuraRiesgoService
                    .obtenerCfgMatrizNormalizadaTCPorCfgGrupo(idCfgGrupoRiesgo);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la matriz de pares");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron los factores técnicos o de gestión");
        respuesta.put("lPgimCfgFactorRiesgoDTO", lPgimCfgFactorRiesgoDTO);
        respuesta.put("lPgimCfgMatrizParesDTO", lPgimCfgMatrizParesDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    // PGIM-5009 - pjimenez
    @GetMapping("/obtenerCalculoRatioConsistencia/{idCfgGrupoRiesgo}")
    public ResponseEntity<?> obtenerCalculoRatioConsistencia(@PathVariable Long idCfgGrupoRiesgo) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///

        List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTO = null;
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO_log = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOICA = null;
        List<PgimValorParametroDTO> lPgimValorParamDTORCA = null;
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
        Long idConfiguraRiesgo = null;
        Long idInstanciaProces = null;

            if (idCfgGrupoRiesgo != null) {
            pgimCfgGrupoRiesgoDTO_log = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
                idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO_log.getIdConfiguraRiesgo();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if (idConfiguraRiesgo != null) {
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        try {
            lPgimCfgFactorRiesgoDTO = this.configuraRiesgoService.listarCfgFactorTecnicoPorCfgGrupo(idCfgGrupoRiesgo);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los factores técnicos o de gestión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService
                    .obtenerCalculoRatioConsistenciaPorCfgGrupo(idCfgGrupoRiesgo);
            lPgimValorParamDTOICA = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_INDICE_CONSISTENCIA_ALEATORIA);
            lPgimValorParamDTORCA = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_INDICE_RATIO_CONSISTENCIA_ACEPTABLE);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del cálculo de ratio de consistencia");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvo el cálculo de ratio de consistencia técnico o de gestión");
        respuesta.put("lPgimCfgFactorRiesgoDTO", lPgimCfgFactorRiesgoDTO);
        respuesta.put("pgimCfgGrupoRiesgoDTO", pgimCfgGrupoRiesgoDTO);
        respuesta.put("lPgimValorParamDTOICA", lPgimValorParamDTOICA);
        respuesta.put("lPgimValorParamDTORCA", lPgimValorParamDTORCA);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    // PGIM-5009 - pjimenez
    @PutMapping("/modificarNombreCfgGrupoRiesgo")
    public ResponseEntity<?> modificarNombreCfgGrupoRiesgo(
            @Valid @RequestBody PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoActual = null;
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
            PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
    Long idInstanciaProces = null;

        if(pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo() != null){
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo());
        idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el grupo de riesgo");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimCfgGrupoRiesgoActual = this.configuraRiesgoService
                    .cfgGrupoRiesgoFindById(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());

            if (pgimCfgGrupoRiesgoActual == null) {
                mensaje = String.format(
                        "La configuración de grupo riesgo %s que intenta actualizar no existe en la base de datos",
                        pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error intentar recuperar la configuración de grupo riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimCfgGrupoRiesgoActual.setNoGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getNoGrupoRiesgo());
            pgimCfgGrupoRiesgoDTOModificado = this.configuraRiesgoService
                    .modificarCfgGrupoRiesgo(pgimCfgGrupoRiesgoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la configuración de grupo riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de grupo riesgo ha sido modificada");
        respuesta.put("pgimCfgGrupoRiesgoDTO", pgimCfgGrupoRiesgoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarDescripcionCfgGrupoRiesgo")
    public ResponseEntity<?> modificarDescripcionCfgGrupoRiesgo(
            @Valid @RequestBody PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoActual = null;
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
            PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
    Long idInstanciaProces = null;
            Long idConfiguraRiesgo = null;

idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();

            if(idConfiguraRiesgo != null){
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el grupo de riesgo");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimCfgGrupoRiesgoActual = this.configuraRiesgoService.cfgGrupoRiesgoFindById(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());

            if (pgimCfgGrupoRiesgoActual == null) {
                mensaje = String.format(
                        "La configuración de grupo riesgo %s que intenta actualizar no existe en la base de datos",
                        pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error intentar recuperar la configuración de grupo riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimCfgGrupoRiesgoActual.setDeGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getDeGrupoRiesgo());
            pgimCfgGrupoRiesgoDTOModificado = this.configuraRiesgoService.modificarCfgGrupoRiesgo(pgimCfgGrupoRiesgoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la configuración de grupo riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de grupo riesgo ha sido modificada");
        respuesta.put("pgimCfgGrupoRiesgoDTO", pgimCfgGrupoRiesgoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    // PGIM-5009 - pjimenez
    @PutMapping("/modificarFactorCorreccionCfgGrupoRiesgo")
    public ResponseEntity<?> modificarFactorCorreccionCfgGrupoRiesgo(
            @Valid @RequestBody PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoActual = null;
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
            Long idInstanciaProces = null;
            Long idConfiguraRiesgo = null;

        idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();

        if(idConfiguraRiesgo != null){
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
            idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
        } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el grupo de riesgo");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimCfgGrupoRiesgoActual = this.configuraRiesgoService
                    .cfgGrupoRiesgoFindById(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());

            if (pgimCfgGrupoRiesgoActual == null) {
                mensaje = String.format(
                        "La configuración de grupo riesgo %s que intenta actualizar no existe en la base de datos",
                        pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error intentar recuperar la configuración de grupo riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimCfgGrupoRiesgoActual.setPcFactorCorreccion(pgimCfgGrupoRiesgoDTO.getPcFactorCorreccion());
            pgimCfgGrupoRiesgoDTOModificado = this.configuraRiesgoService.modificarCfgGrupoRiesgo(pgimCfgGrupoRiesgoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la configuración de grupo riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de grupo riesgo ha sido modificada");
        respuesta.put("pgimCfgGrupoRiesgoDTO", pgimCfgGrupoRiesgoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguracionesReglaRiesgo")
    public ResponseEntity<?> obtenerConfiguracionesReglaRiesgo() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOMetodoMinado = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOSituacion = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTODivisionSupervisora = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoActividad = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOEstado = null;

        try {
            lPgimValorParametroDTODivisionSupervisora = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);

            lPgimValorParametroDTOMetodoMinado = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_METODO_MINADO);

            lPgimValorParametroDTOSituacion = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_SITUACION_UNIDAD_MINERA);

            lPgimValorParametroDTOTipoActividad = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ACTIVIDAD);

            lPgimValorParametroDTOEstado = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_ESTADO_UM);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta a las configuraciones");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

        respuesta.put("lPgimValorParametroDTODivisionSupervisora", lPgimValorParametroDTODivisionSupervisora);
        respuesta.put("lPgimValorParametroDTOMetodoMinado", lPgimValorParametroDTOMetodoMinado);
        respuesta.put("lPgimValorParametroDTOSituacion", lPgimValorParametroDTOSituacion);
        respuesta.put("lPgimValorParametroDTOTipoActividad", lPgimValorParametroDTOTipoActividad);
        respuesta.put("lPgimValorParametroDTOEstado", lPgimValorParametroDTOEstado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/crearReglaRiesgo")
    public ResponseEntity<?> crearReglaRiesgo(
            @Valid @RequestBody PgimConfiguraReglaDTO pgimConfiguraReglaDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimConfiguraReglaDTO pgimConfiguraReglaDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();  // STORY:PGIM-7606 ///
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
        Long idInstanciaProces = null;

        if(pgimConfiguraReglaDTO.getIdConfiguraRiesgo() != null){
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(pgimConfiguraReglaDTO.getIdConfiguraRiesgo());
        idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
        respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear la configuración de regla riesgo");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {

            pgimConfiguraReglaDTOCreado = configuraRiesgoService.crearReglaRiesgo(pgimConfiguraReglaDTO, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la configuración de regla de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración de regla de riesgo ha sido creado");
        respuesta.put("pgimConfiguraReglaDTO", pgimConfiguraReglaDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarReglaRiesgo")
    public ResponseEntity<?> modificarReglaRiesgo(@Valid @RequestBody PgimConfiguraReglaDTO pgimConfiguraReglaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimConfiguraRegla pgimConfiguraReglaActual = null;
        PgimConfiguraReglaDTO pgimConfiguraReglaDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
    Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
            Long idInstanciaProces = null;

        if(pgimConfiguraReglaDTO.getIdConfiguraRiesgo() != null){
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(pgimConfiguraReglaDTO.getIdConfiguraRiesgo());
        idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la regla de riesgo");
            respuesta.put("error", errores.toString());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimConfiguraReglaActual = this.configuraRiesgoService
                    .configuraReglafindById(pgimConfiguraReglaDTO.getIdConfiguraRegla());

            if (pgimConfiguraReglaActual == null) {
                mensaje = String.format("La regla de riesgo %s que intenta actualizar no existe en la base de datos",
                        pgimConfiguraReglaDTO.getIdConfiguraRegla());
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la regla de riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {

            pgimConfiguraReglaDTOModificada = this.configuraRiesgoService.modificarReglaRiesgo(pgimConfiguraReglaDTO,
                    pgimConfiguraReglaActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la regla de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El factor de riesgo ha sido modificado");
        respuesta.put("pgimConfiguraReglaDTO", pgimConfiguraReglaDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarReglaRiesgo/{idReglaRiesgo}")
    public ResponseEntity<?> eliminarReglaRiesgo(@PathVariable Long idReglaRiesgo) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        String mensaje;

        PgimConfiguraRegla PgimConfiguraReglaActual = null;
        PgimConfiguraRegla pgimConfiguraRegla = null;
            PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
            Long idConfiguraRiesgo = null;
            Long idInstanciaProces = null;

        pgimConfiguraRegla = this.configuraRiesgoService.configuraReglafindById(idReglaRiesgo);

            if(pgimConfiguraRegla != null){
                idConfiguraRiesgo = pgimConfiguraRegla.getPgimConfiguraRiesgo().getIdConfiguraRiesgo();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idConfiguraRiesgo != null){
                pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            PgimConfiguraReglaActual = this.configuraRiesgoService.configuraReglafindById(idReglaRiesgo);

            if (PgimConfiguraReglaActual == null) {
                mensaje = String.format("La regla de riesgo %s que intenta eliminar no existe en la base de datos",
                        idReglaRiesgo);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la regla de riesgo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.configuraRiesgoService.eliminarReglaRiesgo(PgimConfiguraReglaActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la regla de riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    // PGIM-5010 - pjimenez
    @DeleteMapping("/eliminarCfgGrupoRiesgo/{idCfgGrupoRiesgo}")
    public ResponseEntity<?> eliminarCfgGrupoRiesgo(@PathVariable Long idCfgGrupoRiesgo) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>(); // STORY:PGIM-7606 ///
        String mensaje;

        PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoActual = null;
        PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO = null;
            PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
            Long idConfiguraRiesgo = null;
            Long idInstanciaProces = null;

        if(idCfgGrupoRiesgo != null){
                pgimCfgGrupoRiesgoDTO = this.configuraRiesgoService.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
        idConfiguraRiesgo = pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if(idConfiguraRiesgo != null){
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
                idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        try {
            pgimCfgGrupoRiesgoActual = this.configuraRiesgoService.cfgGrupoRiesgoFindById(idCfgGrupoRiesgo);

            if (pgimCfgGrupoRiesgoActual == null) {
                mensaje = String.format(
                        "La configuración de grupo riesgo %s que intenta eliminar no existe en la base de datos",
                        idCfgGrupoRiesgo);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la configuración de grupo riesgo a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.configuraRiesgoService.eliminarCfgGrupoRiesgo(pgimCfgGrupoRiesgoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la configuración de grupo riesgo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite validar que las reglas de la configuración no se traslapen entre sí.
     * 
     * @param pgimConfiguraReglaDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/validarTraslapeRegla")
    public ResponseEntity<ResponseDTO> validarTraslapeRegla(@RequestBody PgimConfiguraReglaDTO pgimConfiguraReglaDTO) throws Exception {
        String mensaje = "";
        int nroTraslapes = 0;
        Map<String, Object> respuestaLog = new HashMap<>();
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = null;
        Long idInstanciaProces = null;

        if(pgimConfiguraReglaDTO.getIdConfiguraRiesgo() != null){
            pgimConfiguraRiesgoDTO = this.configuraRiesgoService.obtenerConfiguraRiesgoPorId(pgimConfiguraReglaDTO.getIdConfiguraRiesgo());
        idInstanciaProces = pgimConfiguraRiesgoDTO.getIdInstanciaProceso();
            } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

        if (pgimConfiguraReglaDTO.getIdConfiguraRegla() == 0) {
            pgimConfiguraReglaDTO.setIdConfiguraRegla(null);
        }

        try {
            nroTraslapes = this.configuraRiesgoService.contarCantidadReglasRiesgo(
                    pgimConfiguraReglaDTO.getIdConfiguraRiesgo(), pgimConfiguraReglaDTO.getIdConfiguraRegla(),
                    pgimConfiguraReglaDTO.getIdDivisionSupervisora(), pgimConfiguraReglaDTO.getIdMetodoMinado(),
                    pgimConfiguraReglaDTO.getIdSituacion(), pgimConfiguraReglaDTO.getIdTipoActividad(),
                    pgimConfiguraReglaDTO.getIdEstado());

            if (nroTraslapes >= 1) {
                mensaje = "Ya existe una regla con esta combinación, por favor elija una combinación distinta. Recuerde que la combinación no debe existir tampoco en otras configuraciones de esta especialidad";
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );
                
            } else {
                mensaje = "No se han encontrado traslapes";
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al realizar la validacion de traslapes";
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("error", mensaje, nroTraslapes));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", mensaje, nroTraslapes));
    }

}
