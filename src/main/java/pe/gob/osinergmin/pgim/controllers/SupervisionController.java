package pe.gob.osinergmin.pgim.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSeguimientoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervFechaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.LineaProgramaRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.ContratoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.InvolucradoSupervService;
import pe.gob.osinergmin.pgim.services.MotivoSupervisionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PrgrmSupervisionService;
import pe.gob.osinergmin.pgim.services.SubTipoSupervisionService;
import pe.gob.osinergmin.pgim.services.SupervFechaService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a las
 * supervisiones
 * 
 * @descripción: Supervision
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/07/2020
 */
@RestController
@Slf4j
@RequestMapping("/supervisiones")
public class SupervisionController extends BaseController {

    @Autowired
    private SupervisionService supervisionService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private PrgrmSupervisionService prgrmSupervisionService;

    @Autowired
    private SubTipoSupervisionService subTipoSupervisionService;

    @Autowired
    private MotivoSupervisionService motivoSupervisionService;

    @Autowired
    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private SupervFechaService supervFechaService;

    @Autowired
    private InvolucradoSupervService involucradoSupervService;
    
    @Autowired
    private ValorParametroRepository valorParametroRepository;

    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;

    @Autowired
    private LineaProgramaRepository lineaProgramaRepository;
    
    /***
     * Permite obtener un listado de supervisiones en el contexto de la paginación
     * de resultados requerida en la lista den el frontend.
     * 
     * @param filtroSupervision Objeto filtro que porta las propiedades que de tener
     *                          valor, representan criterios filtro específicos esto
     *                          siempre que la propiedad esté configurada para
     *                          aplicarse como criterio al momento de las consultas.
     * @param paginador         Objeto paginador que tiene la información de la
     *                          página actual, tamaño de la página y criterios de
     *                          ordenamiento.
     * @return
     */

    @PreAuthorize("hasAnyAuthority('sp-lista_AC')  or hasAnyAuthority('rep003_AC')")
    @PostMapping("/filtrar")
    public ResponseEntity<Page<PgimSupervisionAuxDTO>> filtrarSupervisiones(
            @RequestBody PgimSupervisionDTO filtroSupervision, Pageable paginador) throws Exception {

        Page<PgimSupervisionAuxDTO> lPgimSupervisionDTO = this.supervisionService.filtrar(filtroSupervision, paginador,
                this.obtenerAuditoria());

        return new ResponseEntity<Page<PgimSupervisionAuxDTO>>(lPgimSupervisionDTO, HttpStatus.OK);
    }

    /**
        * Listar todas las fiscalizaciones para el calendario de vista semanal
    */
    @GetMapping("/listarFiscalizacionesParaCalendario/{inicio}/{fin}")
    public ResponseEntity<List<PgimSupervisionAuxDTO>> listarFiscalizacionesParaCalendario(@PathVariable Date inicio, @PathVariable Date fin) throws Exception {

        List<PgimSupervisionAuxDTO> lPgimSupervisionDTO = this.supervisionService.listarFiscalizacionesParaCalendario(inicio, fin);

        return new ResponseEntity<List<PgimSupervisionAuxDTO>>(lPgimSupervisionDTO, HttpStatus.OK);
    }

    /***
     * Permite obtener las configuraciones necesarias para el listado de las
     * supervisiones. Acá se incluyen configuraciones como: Especialidad, tipos de
     * supervisión, Fase de proceso, Paso de proceso.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
        List<PgimValorParametroDTO> lPgimTipoSupervisionDTO = null;
        List<PgimValorParametroDTO> lPgimDivisionSupervisoraDTO = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = null;
        PgimPersonaDTO pgimPersonaDTO = null;

        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

            lPgimTipoSupervisionDTO = this.parametroService
                    .filtrarPorNombreTipoSupervision(ConstantesUtil.PARAM_TIPO_SUPERVISION);

            lPgimDivisionSupervisoraDTO = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);

            lPgimFaseProcesoDTO = this.instanciaProcesService
                    .obtenerFasesPorIdProceso(ConstantesUtil.PARAM_PROCESO_SUPERVISION);

            lPgimPasoProcesoDTO = this.instanciaProcesService
                    .obtenerPasosPorIdProceso(ConstantesUtil.PARAM_PROCESO_SUPERVISION);

            pgimPersonaDTO = this.flujoTrabajoService
                    .obtenerPersonaPorNombreUsuario(this.obtenerAuditoria().getUsername());

        } catch (PgimException e) {
            // Si es una excepción controlada, entonces el coUsuarioSiged tendrá el valor
            // null
            // No es necesario detener el proceso, debido a que es posible que algunos
            // módulos de la PGIM
            // no requieran integración con el Siged. Para los casos que sí se requiera cada
            // funcionalidad
            // específica tendrá que validar la existencia y necesidad del código de usuario
            // Siged antes citado.
            log.error(e.getMensaje(), e);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
        respuesta.put("lPgimTipoSupervisionDTO", lPgimTipoSupervisionDTO);
        respuesta.put("lPgimDivisionSupervisoraDTO", lPgimDivisionSupervisoraDTO);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);
        respuesta.put("lPgimPasoProcesoDTO", lPgimPasoProcesoDTO);
        respuesta.put("pgimPersonaDTO", pgimPersonaDTO);
        respuesta.put("usuarioAutenticado", this.obtenerAuditoria().getUsername());

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite listar por palabra clave de la lista de Supervisión
     * 
     * @param palabra = coSupervision
     * @return
     */
    @GetMapping("/filtrar/palabraClave/{palabra}")
    public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimSupervisionDTO> lPgimSupervisionDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimSupervisionDTO = new ArrayList<PgimSupervisionDTO>();
            respuesta.put("mensaje", "No se encontraron supervisiones");
            respuesta.put("lPgimSupervisionDTO", lPgimSupervisionDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimSupervisionDTO = this.supervisionService.listarPorPalabraClave(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las fiscalizaciones");
        respuesta.put("lPgimSupervisionDTO", lPgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite listar por cosupervision || noUnidadMinera de la lista de
     * Supervisión
     * 
     * @param palabra = coSupervision || noUnidadMinera
     * @return
     */
    @GetMapping("/listarPorCoSupervision/{coSupervision}")
    public ResponseEntity<?> listarPorCoSupervision(@PathVariable String coSupervision) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimSupervisionDTO> lPgimSupervisionDTO = null;

        if (coSupervision.equals("_vacio_")) {
            lPgimSupervisionDTO = new ArrayList<PgimSupervisionDTO>();
            respuesta.put("mensaje", "No se encontraron fiscalizaciones");
            respuesta.put("lPgimSupervisionDTO", lPgimSupervisionDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimSupervisionDTO = this.supervisionService.listarPorCoSupervision(coSupervision);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las fiscalizaciones");
        respuesta.put("lPgimSupervisionDTO", lPgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite listar por palabra clave Numero de expediente de la lista de
     * Supervisión
     * 
     * @param palabra = nuExpedienteSiged
     * @return
     */
    @GetMapping("/filtrar/palabraClaveNuExpedienteSiged/{palabra}")
    public ResponseEntity<?> listarPorPalabraClaveNuExpedienteSiged(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimSupervisionDTO> lPgimSupervisionDTONuExpedienteSiged = null;

        if (palabra.equals("_vacio_")) {
            lPgimSupervisionDTONuExpedienteSiged = new ArrayList<PgimSupervisionDTO>();
            respuesta.put("mensaje", "No se encontraron los numeros de expedientes");
            respuesta.put("lPgimSupervisionDTONuExpedienteSiged", lPgimSupervisionDTONuExpedienteSiged);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimSupervisionDTONuExpedienteSiged = this.supervisionService
                    .listarPorPalabraClaveNuExpedienteSiged(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de numero de expediente");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los numeros de expediente");
        respuesta.put("lPgimSupervisionDTONuExpedienteSiged", lPgimSupervisionDTONuExpedienteSiged);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener las listas de de los select usados en el dialogo Asignación
     * de supervisión
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoSupervision = null;
        List<PgimSubtipoSupervisionDTO> lPgimSubtipoSupervisionDTO = null;
        List<PgimMotivoSupervisionDTO> lPgimMotivoSupervisionDTO = null;
        List<PgimContratoDTO> lPgimContratoDTO = null;
        PgimRelacionPasoDTO pgimRelacionPasoDTOInicial = null;

        try {
            lPgimPrgrmSupervisionDTO = this.prgrmSupervisionService.obtenerPrgrmSupervision();
            lPgimValorParametroDTOTipoSupervision = this.parametroService.listarValoresParametro(ConstantesUtil.TIPO_SUPERVISION);
            lPgimSubtipoSupervisionDTO = this.subTipoSupervisionService.obtenerSubTipoSupervision();
            lPgimMotivoSupervisionDTO = this.motivoSupervisionService.obtenerMotivoSupervision();
            lPgimContratoDTO = this.contratoService.obtenerContratos();
            pgimRelacionPasoDTOInicial = this.flujoTrabajoService
                    .obtenerRelacionPasoInicial(ConstantesUtil.PARAM_PROCESO_SUPERVISION);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimPrgrmSupervisionDTO", lPgimPrgrmSupervisionDTO);
        respuesta.put("lPgimValorParametroDTOTipoSupervision", lPgimValorParametroDTOTipoSupervision);
        respuesta.put("lPgimSubtipoSupervisionDTO", lPgimSubtipoSupervisionDTO);
        respuesta.put("lPgimMotivoSupervisionDTO", lPgimMotivoSupervisionDTO);
        respuesta.put("lPgimContratoDTO", lPgimContratoDTO);
        respuesta.put("pgimRelacionPasoDTOInicial", pgimRelacionPasoDTOInicial);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite asignar la supervisión, esta puede ser programada o especial.
     * 
     * @param pgimSupervisionDTO  Objetivo portador de datos para la asignación de
     *                            la supervisión.
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('sp-lista_IN')")
    @PostMapping("/asignarSupervision") // REVIEW: Revisar log
    public ResponseEntity<ResponseDTO> asignarSupervision(@Valid @RequestBody PgimSupervisionDTO pgimSupervisionDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimSupervisionDTO pgimSupervisionDTOCreado = null;
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

            pgimSupervisionDTOCreado = this.supervisionService.asignarSupervision(pgimSupervisionDTO, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar la fiscalización");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final PgimException e) {
            log.error(e.getMensaje(), e);

             responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar la fiscalización");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimSupervisionDTOCreado, "Estupendo, la fiscalización ha sido creada y asignada");
        responseDTO.setData(pgimSupervisionDTOCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
    }

    @PreAuthorize("hasAnyAuthority('sp-lista_MO')")
    @PostMapping("/modificarSupervision") // REVIEW: No lo consume desde el Frontend
    public ResponseEntity<?> modificarSupervision(@Valid @RequestBody PgimSupervisionDTO pgimSupervisionDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimSupervisionDTO pgimSupervisionDTOModificado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la fiscalización");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimSupervisionDTOModificado = supervisionService.asignarSupervision(pgimSupervisionDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        respuesta.put("mensaje", "La fiscalización ha sido creada");
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite obtener la supervision usado en el modo consulta y edición del
     * frontend
     * 
     * @param idSupervision
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('sp-lista_CO')")
    @GetMapping("/obtenerSupervisionPorIdInstanciaPaso/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerSupervisionPorIdInstanciaPaso(@PathVariable Long idInstanciaPaso) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimSupervisionDTO pgimSupervisionDTO = null;
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOActual = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;

        Long idSupervision = null;

        Long idInstanciaProces = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();
        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            idSupervision = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getCoTablaInstancia();
            pgimSupervisionDTO = this.supervisionService.obtenerSupervisionPorId(idSupervision);

            if (pgimSupervisionDTO == null) {
                mensaje = String.format("La fiscalización con el id: %d no existe en la base de datos", idSupervision);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
            }

            pgimSupervisionDTO.setDescIdInstanciaPaso(idInstanciaPaso);

            pgimInstanPasoAuxDTOActual = this.flujoTrabajoService
                    .obtenerInstanciaPasoAuxPorId(idInstanciaPaso);

            lPgimFaseProcesoDTO = this.flujoTrabajoService
                    .obtenerFasesInstanciaProceso(pgimInstanPasoAuxDTOActual.getIdInstanciaProceso());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final PgimException e) {
                        
            // Manejo de logs
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la fiscalización");
            respuesta.put("error", e.getMensaje());
            
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (pgimInstanPasoAuxDTOActual == null) {
            mensaje = String.format(
                    "La fiscalización con el id: %d aún no registra un paso actual en el respectivo flujo de trabajo",
                    idSupervision);
            respuesta.put("mensaje", mensaje);
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La fiscalización ha sido recuperada");
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);
        respuesta.put("pgimInstanPasoAuxDTOActual", pgimInstanPasoAuxDTOActual);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener la supervisión de acuerdo con la instancia de paso respectiva
     * parámetro.
     * 
     * @param idInstanciaPaso
     * @return
     * @throws Exception
     */
    @GetMapping("/obtenerAsignacionSupervisionPorIdInstanciaPaso/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerAsignacionSupervisionPorIdInstanciaPaso(@PathVariable Long idInstanciaPaso) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimSupervisionDTO pgimSupervisionDTO = null;
        Long idSupervision = null;

        Long idInstanciaProces = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();
        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            idSupervision = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getCoTablaInstancia();
            pgimSupervisionDTO = this.supervisionService.obtenerAsignacionSupervisionPorId(idSupervision);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la supervision");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimSupervisionDTO == null) {
            mensaje = String.format("La supervision con el id: %d no existe en la base de datos", idSupervision);
            respuesta.put("mensaje", mensaje);
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La fiscalización ha sido recuperada");
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerSeguimientoPorId/{idProgramaSupervision}/{idSupervision}/{idLineaPrograma}")
    public ResponseEntity<?> obtenerSeguimientoPorSupervisionId(@PathVariable Long idProgramaSupervision,
            @PathVariable Long idSupervision, @PathVariable Long idLineaPrograma) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimPrgrmSeguimientoAuxDTO pgimSupervisionDTO = null;
        
        if(idLineaPrograma != null){
            Long idInstanciaProces = this.lineaProgramaRepository.findById(idLineaPrograma).orElse(null).getPgimPrgrmSupervision().getPgimInstanciaProces().getIdInstanciaProceso();
    
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else{
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }


        try {
            pgimSupervisionDTO = this.supervisionService.obtenerSeguimientoPorSupervisionId(idSupervision,
                    idLineaPrograma);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la supervision");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimSupervisionDTO == null) {
            mensaje = String.format("La supervision con el id: %d no existe en la base de datos", idSupervision,
                    idLineaPrograma);
            respuesta.put("mensaje", mensaje);
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La supervision ha sido recuperado");
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerSeguimientoPorItemProgramaId/{idProgramaSupervision}/{idItemProgramaSupe}/{idLineaPrograma}")
    public ResponseEntity<?> obtenerSeguimientoPorItemProgramaId(@PathVariable Long idProgramaSupervision,
            @PathVariable Long idItemProgramaSupe, @PathVariable Long idLineaPrograma) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimPrgrmSeguimientoAuxDTO pgimSupervisionDTOItemSupe = null;

        Long idInstanciaProces = this.lineaProgramaRepository.findById(idLineaPrograma).orElse(null).getPgimPrgrmSupervision().getPgimInstanciaProces().getIdInstanciaProceso();

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {
            pgimSupervisionDTOItemSupe = this.supervisionService.obtenerSeguimientoPorItemProgramaId(idItemProgramaSupe,
                    idLineaPrograma);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la supervision");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (pgimSupervisionDTOItemSupe == null) {
            mensaje = String.format("La supervision con el id: %d no existe en la base de datos", idItemProgramaSupe,
                    idLineaPrograma);
            respuesta.put("mensaje", mensaje);
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La supervision ha sido recuperado");
        respuesta.put("pgimSupervisionDTOItemSupe", pgimSupervisionDTOItemSupe);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener las supervisiones usado en la tarjeta informativa del
     * frontend
     * 
     * @param idSupervision
     * @return
     * @throws Exception
     */
    // @PreAuthorize("hasAnyAuthority('supervision-lista_CO')")
    @GetMapping("/obtenerSupervisionPorIdSupervision/{idSupervision}")
    public ResponseEntity<?> obtenerSupervisionPorIdSupervision(@PathVariable Long idSupervision) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimSupervisionDTO pgimSupervisionDTO = null;
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOActual = null;

        Long idInstanciaProces = this.supervisionService.getByIdSupervision(idSupervision).getPgimInstanciaProces().getIdInstanciaProceso();
        if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {
            pgimSupervisionDTO = this.supervisionService.obtenerSupervisionPorIdSupervision(idSupervision);                         

            if (pgimSupervisionDTO == null) {
                mensaje = String.format("La supervision con el id: %d no existe en la base de datos", idSupervision);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
            }

            if (pgimSupervisionDTO.getDescDeContrato() != null) {
                BigDecimal descMoConsumoContrato = this.supervisionService
                        .obtenerMontoConsumo(pgimSupervisionDTO.getIdSupervision());
                pgimSupervisionDTO.setDescMoConsumoContrato(descMoConsumoContrato);
            }
            
            // Obtenemos el objeto pgimInstanPasoAuxDTOActual de la supervisión. 
            List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTO = this.instanciaPasoRepository
                    .obtenerPasosActualesPorInstanciaProceso(idInstanciaProces);
            
            if(lPgimInstanciaPasoDTO.size() > 0) {
            	// Por lo general será solo un registro en la medida en que aún no se haya implementado flujos paralelos para el módulo Supervisión. Por ello, tomamos el primer objeto.
            	// Asimismo, en este método (obtenerSupervisionPorIdSupervision) es de cierta forma irrelevante la instancia de paso (se suele usar el método obtenerSupervisionPorIdInstanciaPaso en su lugar); 
            	// sin embargo, se requiere este objeto para los casos en que se llame desde otros módulos, ejm. desde Liquidación
            	pgimInstanPasoAuxDTOActual = this.flujoTrabajoService
                        .obtenerInstanciaPasoAuxPorId(lPgimInstanciaPasoDTO.get(0).getIdInstanciaPaso());    
            	pgimSupervisionDTO.setDescIdInstanciaPaso(pgimInstanPasoAuxDTOActual.getIdInstanciaPaso());
            } 
            
            if (pgimInstanPasoAuxDTOActual == null) {
                mensaje = String.format(
                        "La supervision con el id: %d aún no registra un paso actual en el respectivo flujo de trabajo",
                        idSupervision);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); 
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
            }
            
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la supervision");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La supervision ha sido recuperado");
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);
        respuesta.put("pgimInstanPasoAuxDTOActual", pgimInstanPasoAuxDTOActual);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Metodo utilizado para validar que las fechas no se traslapen.
     * 
     * @param feInicioSupervision
     * @param feFinSupervision
     * @return
     */
    @GetMapping("/validarTraslapeFechasSupervision/{idUnidadMinera}/{feInicioSupervision}/{feFinSupervision}")
    public ResponseEntity<ResponseDTO> validarFechasCorrelativo(@PathVariable Long idUnidadMinera,
            @PathVariable Date feInicioSupervision, @PathVariable Date feFinSupervision) {

        String mensaje = "";
        String mensajeDetalle = "";
        int nroTraslapes = 0;

        List<PgimSupervisionDTO> lPgimSupervisionDTO = null;

        try {
            Date dateFechaInicio = feInicioSupervision;
            Date dateFechaFin = feFinSupervision;

            lPgimSupervisionDTO = supervisionService.listarSupervisionesTraslapadas(idUnidadMinera, dateFechaInicio,
                    dateFechaFin);

            if (lPgimSupervisionDTO != null) {
                nroTraslapes = lPgimSupervisionDTO.size();
            }

            if (nroTraslapes > 0) {
                for (PgimSupervisionDTO pgimSupervisionDTO : lPgimSupervisionDTO) {
                    if (mensajeDetalle.equals("")) {
                        mensajeDetalle = String.format("'%s'", pgimSupervisionDTO.getCoSupervision());
                    } else {
                        mensajeDetalle = mensajeDetalle + ", "
                                + String.format("'%s'", pgimSupervisionDTO.getCoSupervision());
                    }
                }
                mensaje = "Caso(s): " + mensajeDetalle;
            } else {
                mensaje = "No se han encontrado traslapes";
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al realizar la validacion de las fechas";
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("error", mensaje, nroTraslapes));
        } catch (Exception e) {
            mensaje = "Ocurrió un error al realizar la validacion de las fechas";
            log.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("error", mensaje, nroTraslapes));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", mensaje, nroTraslapes));
    }

    @PreAuthorize("hasAnyAuthority('sp-m-super_MO')")
    @PutMapping("/registrarFechasReales")
    public ResponseEntity<?> registrarFechasReales(@Valid @RequestBody PgimSupervFechaDTO pgimSupervFechaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimSupervision pgimSupervisionActual = null;
        PgimSupervFechaDTO pgimSupervFechaDTOCreado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        if(pgimSupervFechaDTO.getIdSupervision() != null){
            Long idInstanciaProces = this.supervisionService.getByIdSupervision(pgimSupervFechaDTO.getIdSupervision()).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la fiscalización");
            respuesta.put("error", errores.toString());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimSupervisionActual = this.supervisionService.getByIdSupervision(pgimSupervFechaDTO.getIdSupervision());

            if (pgimSupervisionActual == null) {
                mensaje = String.format("La fiscalización %s que intenta actualizar no existe en la base de datos",
                        pgimSupervFechaDTO.getIdSupervision());
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la fiscalización a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            PgimSupervisionDTO pgimSupervisionDTO = new PgimSupervisionDTO();
            pgimSupervisionDTO.setFeInicioSupervisionReal(pgimSupervFechaDTO.getFeInicioSupervision());
            pgimSupervisionDTO.setFeFinSupervisionReal(pgimSupervFechaDTO.getFeFinSupervision());
            this.supervisionService.registrarFechasRealesSupervision(pgimSupervisionDTO, pgimSupervisionActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (final PgimException e) {
            // Manejo de logs
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el ranking");
            respuesta.put("error", e.getMensaje());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
        	pgimSupervFechaDTO.setIdTipoFecha(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.FECAM_CMBIO_FCHAS_REALES.toString()));
            pgimSupervFechaDTOCreado = this.supervFechaService.crearSupervFecha(pgimSupervFechaDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la fecha de fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La fecha de fiscalización ha sido modificada");
        respuesta.put("pgimSupervFechaDTO", pgimSupervFechaDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/validarFechaInicioRealSupervision/{idSupervision}/{feInicioRealSupervision}")
    public ResponseEntity<ResponseDTO> validarFechaInicioRealSupervision(@PathVariable Long idSupervision,
            @PathVariable Date feInicioRealSupervision) throws Exception {

        String mensaje = "";
        int esIncorrecto = 0;
		Map<String, Object> respuestaLog = new HashMap<>();

        List<PgimSupervisionDTO> lPgimSupervisionDTO = null;

        if(idSupervision != null){
            Long idInstanciaProces = this.supervisionService.getByIdSupervision(idSupervision).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {
            lPgimSupervisionDTO = supervisionService.listarSupervisionesFechaInicioReal(idSupervision,
                    feInicioRealSupervision);

            if (lPgimSupervisionDTO != null) {
                esIncorrecto = lPgimSupervisionDTO.size();
            }

            if (esIncorrecto == 0) {
                mensaje = "No se han encontrado errores";
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al realizar la validacion de la fecha";
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("error", mensaje, esIncorrecto));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", mensaje, esIncorrecto));
    }

    @PreAuthorize("hasAnyAuthority('sp-m-presu_MO')")
    @PutMapping("/redefinirFechaSupervision")
    public ResponseEntity<?> redefinirFechaSupervision(@Valid @RequestBody PgimSupervFechaDTO pgimSupervFechaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimSupervision pgimSupervisionActual = null;
        PgimSupervFechaDTO pgimSupervFechaDTOCreado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        if(pgimSupervFechaDTO.getIdSupervision() != null){
            Long idInstanciaProces = this.supervisionService.getByIdSupervision(pgimSupervFechaDTO.getIdSupervision()).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la fiscalización");
            respuesta.put("error", errores.toString());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimSupervisionActual = this.supervisionService.getByIdSupervision(pgimSupervFechaDTO.getIdSupervision());

            if (pgimSupervisionActual == null) {
                mensaje = String.format("La fiscalización %s que intenta actualizar no existe en la base de datos",
                        pgimSupervFechaDTO.getIdSupervision());
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la fiscalización a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            PgimSupervisionDTO pgimSupervisionDTO = new PgimSupervisionDTO();
            pgimSupervisionDTO.setFeInicioSupervision(pgimSupervFechaDTO.getFeInicioSupervision());
            pgimSupervisionDTO.setFeFinSupervision(pgimSupervFechaDTO.getFeFinSupervision());
            this.supervisionService.redefinirFechaSupervision(pgimSupervisionDTO, pgimSupervisionActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (final PgimException e) {
            // Manejo de logs
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la fiscalización");
            respuesta.put("error", e.getMensaje());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
        	pgimSupervFechaDTO.setIdTipoFecha(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.FECAM_CMBIO_FCHAS_PRGRMDAS.toString()));
            pgimSupervFechaDTOCreado = this.supervFechaService.crearSupervFecha(pgimSupervFechaDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la fecha de fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La fecha de fiscalización ha sido modificada");
        respuesta.put("pgimSupervFechaDTO", pgimSupervFechaDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PostMapping("/modificarSupervisionTDR")
    public ResponseEntity<?> modificarSupervisionTDR(@Valid @RequestBody PgimSupervisionDTO pgimSupervisionDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimSupervisionDTO pgimSupervisionDTOModificado = null;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        if(pgimSupervisionDTO.getIdSupervision() != null){
            Long idInstanciaProces = this.supervisionService.getByIdSupervision(pgimSupervisionDTO.getIdSupervision()).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la fiscalización");
            respuesta.put("error", errores.toString());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimSupervisionDTOModificado = supervisionService.modificarSupervisionTDR(pgimSupervisionDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        respuesta.put("mensaje", "La fiscalización ha sido modificada");
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerActasPresentadasPendienteLiquidar")
    public ResponseEntity<?> obtenerActasPresentadasPendienteLiquidar() {
        Map<String, Object> respuesta = new HashMap<>();

        List<PgimSupervisionDTO> lPgimSupervisionDTO = null;

        try {
            lPgimSupervisionDTO = this.supervisionService.obtenerActasPresentadasPendienteLiquidar();
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar la consulta de las actas presentadas pendientes de liquidar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La supervision ha sido recuperado");
        respuesta.put("lPgimSupervisionDTO", lPgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    /**
     * Me permite listar las supervisiones del contrato
     * 
     * @param idContrato
     * @param filtroSupervision
     * @param paginador
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('co-super_AC')")
    @PostMapping("/listarSeguimientosSupervisiones/{idContrato}")
    public ResponseEntity<Page<PgimSupervisionAuxDTO>> listarSeguimientosSupervisiones(@PathVariable Long idContrato,
            @RequestBody PgimSupervisionDTO filtroSupervision, Pageable paginador) throws Exception {

        Page<PgimSupervisionAuxDTO> lPgimSupervisionDTO = this.supervisionService
                .listarSeguimientosSupervisiones(idContrato, filtroSupervision, paginador, this.obtenerAuditoria());
        return new ResponseEntity<Page<PgimSupervisionAuxDTO>>(lPgimSupervisionDTO, HttpStatus.OK);
    }

    /***
     * Permite obtener un listado de supervisiones pre-comprometidas de un contrato
     * en particular
     * 
     * @return
     */

    // @PreAuthorize("hasAnyAuthority('sp-lista_AC')")
    @PostMapping("/listarSuperPrecomprometidas")
    public ResponseEntity<Page<PgimSupervisionAuxDTO>> listarSuperPrecomprometidas(@RequestBody Long idcontrato,
            Pageable paginador) throws Exception {

        Page<PgimSupervisionAuxDTO> lPgimSupervisionDTO = this.supervisionService
                .listarSupervisionesPrecomprometidasXcontrato(idcontrato, paginador);

        return new ResponseEntity<Page<PgimSupervisionAuxDTO>>(lPgimSupervisionDTO, HttpStatus.OK);
    }

    @GetMapping("/validarActaSupervision/{idSupervision}/{idTipoActaInvolucrado}")
    public ResponseEntity<?> validarActaSupervision(@PathVariable Long idSupervision,
            @PathVariable Long idTipoActaInvolucrado) throws Exception {

        Map<String, Object> respuestaLog = new HashMap<>();
        ResponseDTO responseDTO = null;

        Integer cantidadRepresentantes = 0;

        if(idSupervision != null){
            Long idInstanciaProces = this.supervisionService.getByIdSupervision(idSupervision).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {
            List<PgimInvolucradoSupervDTO> listaTrabajadores = involucradoSupervService
                    .obtenerRepresentantesTrabajadores(idSupervision, idTipoActaInvolucrado);

            List<PgimInvolucradoSupervDTO> listaAgenteSupervisado = involucradoSupervService
                    .obtenerRepresentantesAgenteSupervisado(idSupervision, idTipoActaInvolucrado);

            cantidadRepresentantes = listaTrabajadores.size() + listaAgenteSupervisado.size();

        } catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar validar el acta de inicio de fiscalización");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, cantidadRepresentantes, "El acta de inicio de fiscalización ha sido validado"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Me permite listar las supervisiones del programa
     * 
     * @param idLineaPrograma
     * @param paginador
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('pr-seg-sup_AC')")
    @PostMapping("/listarProgramaSeguimiento/{idLineaPrograma}")
    public ResponseEntity<Page<PgimPrgrmSeguimientoAuxDTO>> listarProgramaSeguimiento(
            @PathVariable Long idLineaPrograma, @RequestBody PgimPrgrmSeguimientoAuxDTO filtroPrgrmSeguimientoAuxDTO,
            Pageable paginador) throws Exception {

        Page<PgimPrgrmSeguimientoAuxDTO> lPgimSupervisionDTO = this.supervisionService
                .listarProgramaSeguimiento(idLineaPrograma, filtroPrgrmSeguimientoAuxDTO, paginador);
        return new ResponseEntity<Page<PgimPrgrmSeguimientoAuxDTO>>(lPgimSupervisionDTO, HttpStatus.OK);
    }

    
    /**
     * Permite obtener la lista de supervisiones-aux usado en reporte
     * correspondiente paginada
     * 
     * @param filtroPgimSupervisionAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteSupervisionPaginado")
    public ResponseEntity<Page<PgimSupervisionAuxDTO>> listarReporteSupervisionPaginado(
            @RequestBody PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO, Pageable paginador) throws Exception {

        Page<PgimSupervisionAuxDTO> lPgimSupervisionAuxDTO = this.supervisionService
                .listarReporteSupervisionPaginado(filtroPgimSupervisionAuxDTO, paginador);

        return new ResponseEntity<Page<PgimSupervisionAuxDTO>>(lPgimSupervisionAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener el listado de las fiscalizaciones pertenecientes a una unidad minera
     * @param filtroSupervision
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/obtenerFiscalizacionPorUnidadMineraPaginado")
    public ResponseEntity<Page<PgimSupervisionDTO>> obtenerFiscalizacionPorUnidadMineraPaginado(
            @RequestBody PgimSupervisionDTO filtroSupervision, Pageable paginador) throws Exception {

        Page<PgimSupervisionDTO> lPgimSupervisionDTO = this.supervisionService.obtenerFiscalizacionPorUnidadMineraPaginado(filtroSupervision, paginador);

        return new ResponseEntity<Page<PgimSupervisionDTO>>(lPgimSupervisionDTO, HttpStatus.OK);
    }
    
    
    /**
     * Permite obtener la lista de supervisiones-aux usado en reporte
     * correspondiente
     * 
     * @param filtroPgimSupervisionAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteSupervision")
    public ResponseEntity<List<PgimSupervisionAuxDTO>> listarReporteSupervision(
            @RequestBody PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO) throws Exception {

        List<PgimSupervisionAuxDTO> lPgimSupervisionAuxDTO = this.supervisionService
                .listarReporteSupervision(filtroPgimSupervisionAuxDTO);

        return new ResponseEntity<List<PgimSupervisionAuxDTO>>(lPgimSupervisionAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista de supervisiones-aux por anio de supervisión usado
     * en reporte correspondiente
     * 
     * @param filtroPgimSupervisionAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarSupervisionxAnioReporte")
    public ResponseEntity<List<PgimSupervisionAuxDTO>> listarSupervisionxAnioReporte(
            @RequestBody PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO) throws Exception {

        List<PgimSupervisionAuxDTO> lPgimSupervisionAuxDTO = this.supervisionService
                .listarSupervisionxAnioReporte(filtroPgimSupervisionAuxDTO);

        return new ResponseEntity<List<PgimSupervisionAuxDTO>>(lPgimSupervisionAuxDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerEntregablesSupervision/{idSupervision}")
    public ResponseEntity<ResponseDTO> obtenerEntregablesSupervision(@PathVariable Long idSupervision) throws Exception {
        String mensaje;
		Map<String, Object> respuestaLog = new HashMap<>();

        List<PgimItemConsumoDTO> lPgimSupervisionDTO = null;
        if(idSupervision != null){
            Long idInstanciaProces = this.supervisionService.getByIdSupervision(idSupervision).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {
            lPgimSupervisionDTO = this.supervisionService.obtenerEntregablesSupervision(idSupervision);
        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al intentar recuperar la supervision";
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + ". " + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error", mensaje));
        }

        if (lPgimSupervisionDTO == null) {
            mensaje = String.format("La supervision con el id: %d no cuenta con entregables", idSupervision);
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
            log.warn(mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error", mensaje));
        }

        mensaje = "Los entregables de la supervision han sido recuperados";

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", lPgimSupervisionDTO, mensaje));
    }

    /**
     * obtener el idSubcatDocumento para el doc. informe de supervision según el
     * tipo de supervision es (propio o no propia)
     * 
     * @param pgimSupervisionDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/obtenerIdSubCatInformeSupervByTipoSuperv")
    public ResponseEntity<?> obtenerIdSubCatInformeSupervByTipoSuperv(
            @RequestBody PgimSupervisionDTO pgimSupervisionDTO) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        Long idSubCatInformeSupervision = CommonsUtil.obtenerIdSubCatInformeSupervByTipoSuperv(pgimSupervisionDTO);

        respuesta.put("mensaje", "Tipo de fiscalización");
        respuesta.put("tipoSupervision", idSubCatInformeSupervision);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    /**
     * validar de que el requerimiento de documentación tenga al menos un
     * responsable
     * 
     * @param idSupervision
     * @param idTipoActaInvolucrado
     * @return
     * @throws Exception
     */
    @GetMapping("/validarDocRequeridoSupervision/{idSupervision}/{idTipoActaInvolucrado}")
    public ResponseEntity<ResponseDTO> validarDocRequeridoSupervision(@PathVariable Long idSupervision,
            @PathVariable Long idTipoActaInvolucrado) throws Exception {

		Map<String, Object> respuestaLog = new HashMap<>();
        ResponseDTO responseDTO = null;

        Integer cantidadRepresentantes = 0;

        if(idSupervision != null){
            Long idInstanciaProces = this.supervisionService.getByIdSupervision(idSupervision).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {
            List<PgimInvolucradoSupervDTO> listaTrabajadores = involucradoSupervService
                    .obtenerRepresentantesTrabajadores(idSupervision, idTipoActaInvolucrado);

            List<PgimInvolucradoSupervDTO> listaAgenteSupervisado = involucradoSupervService
                    .obtenerRepresentantesAgenteSupervisado(idSupervision, idTipoActaInvolucrado);

            cantidadRepresentantes = listaTrabajadores.size() + listaAgenteSupervisado.size();

        } catch (DataAccessException e) {
        
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar validar los documentos requeridos para la fiscalización");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, cantidadRepresentantes, "El acta de documentos requeridos para la fiscalización ha sido validado"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
	 * Permite agregar uno o varios criterios al cuadro de verificacion de una fiscalización
	 * 
	 * @param pgimSupervisionDTO  fiscalizacion.
	 * @param listaCriterios Lista de criterios.
	 * 
	 * @return
	 */
	@PostMapping("/agregarCriteriosFiscalizacion")
	public ResponseEntity<ResponseDTO> agregarCriteriosFiscalizacion(
			@RequestPart("supervisionDTO") PgimSupervisionDTO pgimSupervisionDTO,
			@RequestPart("criterios") PgimCriterioSprvsionDTO[] listaPgimCriterioSprvsionDTO)
			throws PgimException, IOException, Exception {

		Map<String, Object> respuestaLog = new HashMap<>();
		ResponseEntity<ResponseDTO> responseDTO = null;

        if(pgimSupervisionDTO.getIdSupervision() != null){
            Long idInstanciaProces = this.supervisionService.getByIdSupervision(pgimSupervisionDTO.getIdSupervision()).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			responseDTO = this.supervisionService.agregarCriteriosFiscalizacion(listaPgimCriterioSprvsionDTO, pgimSupervisionDTO,this.obtenerAuditoria());
		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error", e.getMensaje(), 0));
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error", e.getMessage(), 0));
		}
		return responseDTO;
	}
    
    /**
     * Permite actualizar la condición de impedimento de realizar la fiscalización de campo
     * @param idSupervision
     * @param supervisionImpedida
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('sp-m-super_MO')")
    @PostMapping("/actualizarImpedimentoAgenteFiscalizado/{idSupervision}/{supervisionImpedida}")
    public ResponseEntity<ResponseDTO> actualizarImpedimentoAgenteFiscalizado(@PathVariable Long idSupervision,
            @PathVariable boolean supervisionImpedida) throws Exception {

        ResponseEntity<ResponseDTO> responseDTO = null;

        try {

            responseDTO = this.supervisionService.actualizarImpedimentoAgenteFiscalizado(idSupervision,
                    supervisionImpedida, this.obtenerAuditoria());

        } catch (PgimException e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage(), 0));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage(), 0));
        }

        return responseDTO;
    }

}