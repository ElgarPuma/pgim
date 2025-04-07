package pe.gob.osinergmin.pgim.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.GrupoEntregableLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContrLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEntregableLiquidaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimItemLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimLiquidacion;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.SubTipoSupervisionRepository;
import pe.gob.osinergmin.pgim.services.ContratoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.LiquidacionService;
import pe.gob.osinergmin.pgim.services.MotivoSupervisionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a la
 * liquidación
 * 
 * @descripción: Liquidación
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 30/11/2020
 */
@RestController
@Slf4j
@RequestMapping("/liquidacion")
public class LiquidacionController extends BaseController {

    @Autowired
    private ParametroService parametroService;
    
    @Autowired
    private SubTipoSupervisionRepository subTipoSupervisionRepository;

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private LiquidacionService liquidacionService;

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private SupervisionService supervisionService;

    @Autowired
    private MotivoSupervisionService motivoSupervisionService;
    
    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;
    
    @GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOTipoEntregable = null;
        List<PgimValorParametroDTO> lPgimValorParamDTODivSuper = null;
        List<PgimContratoDTO> lPgimContratoDTO = null;
        PgimRelacionPasoDTO pgimRelacionPasoDTOInicial = null;

        try {
            lPgimValorParamDTOTipoEntregable = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ENTREGABLE);

            lPgimValorParamDTODivSuper = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);

            lPgimContratoDTO = this.contratoService.obtenerContratos(this.obtenerAuditoria());

            pgimRelacionPasoDTOInicial = this.flujoTrabajoService
                    .obtenerRelacionPasoInicial(ConstantesUtil.PARAM_PROCESO_LIQUIDACION);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOTipoEntreable", lPgimValorParamDTOTipoEntregable);
        respuesta.put("lPgimValorParamDTODivSuper", lPgimValorParamDTODivSuper);
        respuesta.put("lPgimContratoDTO", lPgimContratoDTO);
        respuesta.put("pgimRelacionPasoDTOInicial", pgimRelacionPasoDTOInicial);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite crear la liquidación.
     * 
     * @param pgimLiquidacionDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('lq-lista_IN')")
    @PostMapping("/crearLiquidacion") // REVIEW: Revisar logs
    public ResponseEntity<ResponseDTO> crearLiquidacion(@Valid @RequestBody PgimLiquidacionDTO pgimLiquidacionDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimLiquidacionDTO pgimLiquidacionDTOCreado = null;
        PgimRelacionPasoDTO pgimRelacionPasoDTOInicial = null;

        ResponseDTO responseDTO = null;

        Map<String, Object> respuesta = new HashMap<>();

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

            pgimLiquidacionDTOCreado = liquidacionService.crearLiquidacion(pgimLiquidacionDTO, this.obtenerAuditoria());
            pgimRelacionPasoDTOInicial = this.flujoTrabajoService.obtenerRelacionPasoInicial(ConstantesUtil.PARAM_PROCESO_LIQUIDACION);

        } catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar crear la liquidación");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);        
        } catch (Exception e) {
			log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, String.format("Ocurrió un error al intentar crear la liquidación. Mensaje de la excepción: %s", e.getMessage()));

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        respuesta.put("pgimLiquidacionDTO", pgimLiquidacionDTOCreado);
        respuesta.put("pgimRelacionPasoDTOInicial", pgimRelacionPasoDTOInicial);

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "La liquidación ha sido creada y asignada");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PreAuthorize("hasAnyAuthority('lq-lista_AC')")
    @PostMapping("/listarLiquidaciones")
    public ResponseEntity<Page<PgimLiquidacionAuxDTO>> listarLiquidaciones(
            @RequestBody PgimLiquidacionAuxDTO filtroLiquidacion, Pageable paginador) throws Exception {

        Page<PgimLiquidacionAuxDTO> lPgimLiquidacionAuxDTO = this.liquidacionService
                .listarLiquidaciones(filtroLiquidacion, paginador, this.obtenerAuditoria());

        return new ResponseEntity<Page<PgimLiquidacionAuxDTO>>(lPgimLiquidacionAuxDTO, HttpStatus.OK);
    }

    @PostMapping("/listarContrLiquidacionAux")
    public ResponseEntity<Page<PgimContrLiquidacionAuxDTO>> listarContrLiquidacionAuxPaginado(
            @RequestBody PgimContrLiquidacionAuxDTO filtroLiquidacion, Pageable paginador) throws Exception {

        Page<PgimContrLiquidacionAuxDTO> lPgimLiquidacionAuxDTO = this.liquidacionService
                .listarContrLiquidacionAux(filtroLiquidacion, paginador);

        return new ResponseEntity<Page<PgimContrLiquidacionAuxDTO>>(lPgimLiquidacionAuxDTO, HttpStatus.OK);
    }

    @PostMapping("/listarLiquidacionesContrato/{idContrato}")
    public ResponseEntity<Page<PgimLiquidacionAuxDTO>> listarLiquidacionesContrato(@PathVariable Long idContrato,
            @RequestBody PgimLiquidacionAuxDTO filtroLiquidacion, Pageable paginador) throws Exception {

        Page<PgimLiquidacionAuxDTO> lPgimLiquidacionAuxDTO = this.liquidacionService
                .listarLiquidacionesContrato(idContrato, filtroLiquidacion, paginador, this.obtenerAuditoria());
                
        return new ResponseEntity<Page<PgimLiquidacionAuxDTO>>(lPgimLiquidacionAuxDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('lq-lista_CO')")
    @GetMapping("/obtenerLiquidacionPorIdInstanciaPaso/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerLiquidacionPorIdInstanciaPaso(@PathVariable Long idInstanciaPaso) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimLiquidacionDTO pgimLiquidacionDTO = null;
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOActual = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
        
        Long idLiquidacion = null;

        Long idInstanciaProces = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();
        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
        	idLiquidacion = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getCoTablaInstancia();
            pgimLiquidacionDTO = this.liquidacionService.obtenerLiquidacionPorId(idLiquidacion);
            pgimLiquidacionDTO.setDescIdInstanciaPaso(idInstanciaPaso);
            
            pgimInstanPasoAuxDTOActual = this.flujoTrabajoService
                    .obtenerInstanciaPasoAuxPorId(idInstanciaPaso);

            lPgimFaseProcesoDTO = this.flujoTrabajoService
                    .obtenerFasesInstanciaProceso(pgimInstanPasoAuxDTOActual.getIdInstanciaProceso());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final PgimException e) {
            // Manejo de logs
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la liquidación");
            respuesta.put("error", e.getMensaje());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (pgimInstanPasoAuxDTOActual == null) {
            mensaje = String.format(
                    "La liquidación con el id: %d aún no registra un paso actual en el respectivo flujo de trabajo",
                    idLiquidacion);
            respuesta.put("mensaje", mensaje);
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La liquidación ha sido recuperada");
        respuesta.put("pgimLiquidacionDTO", pgimLiquidacionDTO);
        respuesta.put("pgimInstanPasoAuxDTOActual", pgimInstanPasoAuxDTOActual);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite validar uno de los campos de Item liquidacion de la Penalidad en
     * el dialogo por especialidad. Validar "moPenalidadReincidencia"
     * 
     * @param idLiquidacion
     * @return
     * @throws Exception
     */
    @GetMapping("/validarPenalidadPorEspecialidad/{idLiquidacion}")
    public ResponseEntity<?> validarPenalidadPorEspecialidad(@PathVariable Long idLiquidacion) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
            Map<String, Object> respuestaLog = new HashMap<>();

        PgimLiquidacionDTO pgimLiquidacionDTO = null;

        if(idLiquidacion != null){
            Long idInstanciaProces = this.liquidacionService.findById(idLiquidacion).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            pgimLiquidacionDTO = this.liquidacionService.validarPenalidadPorEspecialidad(idLiquidacion);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La liquidación ha sido recuperada");
        respuesta.put("pgimLiquidacionDTO", pgimLiquidacionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('lq-lista_CO')")
    @GetMapping("/obtenerLiquidacionAuxPorId/{idLiquidacion}")
    public ResponseEntity<?> obtenerLiquidacionAuxPorId(@PathVariable Long idLiquidacion) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimLiquidacionAuxDTO pgimLiquidacionAuxDTO = null;

        if(idLiquidacion != null){
            Long idInstanciaProces = this.liquidacionService.findById(idLiquidacion).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            pgimLiquidacionAuxDTO = this.liquidacionService.obtenerLiquidacionAuxPorId(idLiquidacion);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimLiquidacionAuxDTO == null) {
            mensaje = String.format(
                    "La liquidación con el id: %d aún no registra un paso actual en el respectivo flujo de trabajo",
                    idLiquidacion);
            respuesta.put("mensaje", mensaje);
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La liquidación ha sido recuperada");
        respuesta.put("pgimLiquidacionAuxDTO", pgimLiquidacionAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('lq-lista_EL')")
    @DeleteMapping("/eliminarLiquidacion/{idLiquidacion}")
    public ResponseEntity<?> eliminarLiquidacion(@PathVariable Long idLiquidacion) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();
        String mensaje;

        PgimLiquidacion pgimLiquidacionActual = null;

        if(idLiquidacion != null){
            Long idInstanciaProces = this.liquidacionService.findById(idLiquidacion).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            pgimLiquidacionActual = liquidacionService.findById(idLiquidacion);

            if (pgimLiquidacionActual == null) {
                mensaje = String.format("La liquidación %s que intenta eliminar no existe en la base de datos",
                        idLiquidacion);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la liquidación a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            liquidacionService.eliminarLiquidacion(pgimLiquidacionActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoEntregable = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoSupervision = null;
        List<PgimSubtipoSupervisionDTO> lPgimSubtipoFiscalizacion = null;
        List<PgimMotivoSupervisionDTO> lPgimMotivoSupervisionDTO = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = null;
        List<PgimContratoDTO> lPgimContratoDTO = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOEstdConsumoPresu = null;


        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

            lPgimValorParamDTOTipoEntregable = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ENTREGABLE);

            lPgimFaseProcesoDTO = this.instanciaProcesService
                    .obtenerFasesPorIdProceso(ConstantesUtil.PARAM_PROCESO_LIQUIDACION);

            lPgimPasoProcesoDTO = this.instanciaProcesService
                    .obtenerPasosPorIdProceso(ConstantesUtil.PARAM_PROCESO_LIQUIDACION);

            lPgimValorParamDTOTipoSupervision = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_SUPERVISION);

            lPgimSubtipoFiscalizacion = this.subTipoSupervisionRepository
            		.obtenerListaSubTipoSupervisionPorTipo();

            lPgimValorParamDTOEstdConsumoPresu = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO);

            lPgimMotivoSupervisionDTO = this.motivoSupervisionService.obtenerMotivoSupervision();

            lPgimContratoDTO = this.contratoService.obtenerContratos(this.obtenerAuditoria());

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al obtener las configuraciones");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOTipoEntregable", lPgimValorParamDTOTipoEntregable);
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);
        respuesta.put("lPgimPasoProcesoDTO", lPgimPasoProcesoDTO);
        respuesta.put("lPgimValorParamDTOTipoSupervision", lPgimValorParamDTOTipoSupervision);
        respuesta.put("lPgimSubtipoFiscalizacion", lPgimSubtipoFiscalizacion);
        respuesta.put("lPgimMotivoSupervisionDTO", lPgimMotivoSupervisionDTO);
        respuesta.put("lPgimContratoDTO", lPgimContratoDTO);
        respuesta.put("lPgimValorParamDTOEstdConsumoPresu", lPgimValorParamDTOEstdConsumoPresu);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerEntregablesAliquidar/{idContrato}/{idTipoEntregable}/{idDivisionSupervisora}/{idSubtipoSupervision}/{idMotivoSupervision}")
    public ResponseEntity<?> obtenerEntregablesAliquidar(@PathVariable Long idContrato,
            @PathVariable Long idTipoEntregable, @PathVariable Long idDivisionSupervisora,
            @PathVariable Long idSubtipoSupervision, @PathVariable Long idMotivoSupervision) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEntregableLiquidaAuxDTO> lPgimEntregableLiquidaAuxDTO = null;

        try {
            lPgimEntregableLiquidaAuxDTO = this.liquidacionService.obtenerEntregablesALiquidar(idContrato,
                    idTipoEntregable, idDivisionSupervisora, idSubtipoSupervision, idMotivoSupervision);
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar la consulta de los entregables pendientes de liquidar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Los entregables presentados y pendientes de liquidar han sido recuperados");
        respuesta.put("lPgimEntregableLiquidaAuxDTO", lPgimEntregableLiquidaAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @GetMapping("/filtrar/numeroLiquidacion/{palabra}")
    public ResponseEntity<?> listarPorNumeroLiquidacion(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimLiquidacionDTO> lPgimLiquidacionDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimLiquidacionDTO = new ArrayList<PgimLiquidacionDTO>();
            respuesta.put("mensaje", "No se encontraron los números de la liquidación");
            respuesta.put("lPgimLiquidacionDTO", lPgimLiquidacionDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimLiquidacionDTO = this.liquidacionService.listarPorNumeroLiquidacion(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los números de la liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los números de la liquidación");
        respuesta.put("lPgimLiquidacionDTO", lPgimLiquidacionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('lq-entreg_AC')")
    @GetMapping("/listarItemLiquidacionActasInformes/{idLiquidacion}")
    public ResponseEntity<?> listarItemLiquidacionActasInformes(@PathVariable Long idLiquidacion) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        List<PgimItemLiquidacionDTO> lPgimItemLiquidacionDTO = null;

        if(idLiquidacion != null){
            Long idInstanciaProces = this.liquidacionService.findById(idLiquidacion).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            lPgimItemLiquidacionDTO = liquidacionService.listarItemLiquidacionActasInformes(idLiquidacion);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los items de liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Los items de liquidación han sido recuperado");
        respuesta.put("lPgimItemLiquidacionDTO", lPgimItemLiquidacionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }


    @PreAuthorize("hasAnyAuthority('lq-entreg_AC')")
    @GetMapping("/listarEntregablesPorLiquidacion/{idLiquidacion}")
    public ResponseEntity<?> listarEntregablesPorLiquidacion(@PathVariable Long idLiquidacion) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        List<PgimEntregableLiquidaAuxDTO> lPgimEntregableLiquidaAuxDTO = null;

        if(idLiquidacion != null){
            Long idInstanciaProces = this.liquidacionService.findById(idLiquidacion).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            lPgimEntregableLiquidaAuxDTO = liquidacionService.listarEntregablesPorLiquidacion(idLiquidacion);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los items de liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Los items de liquidación han sido recuperado");
        respuesta.put("lPgimEntregableLiquidaAuxDTO", lPgimEntregableLiquidaAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('lq-entreg_EL')")
    @DeleteMapping("/eliminarItemLiquidacion/{idItemLiquidacion}")
    public ResponseEntity<?> eliminarItemLiquidacion(@PathVariable Long idItemLiquidacion) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();
        String mensaje;

        PgimItemLiquidacion pgimItemLiquidacionActual = null;

        if(idItemLiquidacion != null){
            Long idInstanciaProces = this.liquidacionService.findByIdIL(idItemLiquidacion).getPgimLiquidacion().getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            pgimItemLiquidacionActual = liquidacionService.findByIdIL(idItemLiquidacion);

            if (pgimItemLiquidacionActual == null) {
                mensaje = String.format("El item liquidación %s que intenta eliminar no existe en la base de datos",
                        idItemLiquidacion);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la liquidación a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            liquidacionService.eliminarItemLiquidacion(pgimItemLiquidacionActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('lq-entreg_IN')")
    @PostMapping("/crearItemLiquidacion")
    public ResponseEntity<?> crearItemLiquidacion(
            @RequestPart("lPgimLiquidacionDTO") List<PgimLiquidacionDTO> lPgimLiquidacionDTO)
            throws PgimException, IOException, Exception {

        List<PgimItemLiquidacionDTO> lPgimItemLiquidacionDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        if(lPgimLiquidacionDTO.get(0).getIdLiquidacion() != null){
            Long idInstanciaProces = this.liquidacionService.findById(lPgimLiquidacionDTO.get(0).getIdLiquidacion()).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            lPgimItemLiquidacionDTOCreado = liquidacionService.crearItemLiquidacion(lPgimLiquidacionDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el item liquidación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "success");
        respuesta.put("lPgimItemLiquidacionDTOCreado", lPgimItemLiquidacionDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('lq-penal_MO')")
    @PostMapping("/modificarItemLiquidacionInfoPenalidad")
    public ResponseEntity<?> modificarItemLiquidacionInfoPenalidad(
            @Valid @RequestBody PgimItemLiquidacionDTO pgimItemLiquidacionDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimItemLiquidacion pgimItemLiquidacionActual = null;
        PgimItemLiquidacionDTO pgimItemLiquidacionDTOCreado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        if(pgimItemLiquidacionDTO.getIdLiquidacion() != null){
            Long idInstanciaProces = this.liquidacionService.findById(pgimItemLiquidacionDTO.getIdLiquidacion()).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
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

            respuesta.put("mensaje", "Se han encontrado inconsistencias para actualizar liquidación informe penalidad");
            respuesta.put("error", errores.toString());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        try {
            pgimItemLiquidacionActual = this.liquidacionService
                    .getByIdItemLiquidacion(pgimItemLiquidacionDTO.getIdItemLiquidacion());

            if (pgimItemLiquidacionActual == null) {
                mensaje = String.format(
                        "El informe penalidad de una liquidacion %s que intenta actualizar no existe en la base de datos",
                        pgimItemLiquidacionDTO.getIdItemLiquidacion());
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error intentar recuperar el informe penalidad de una liquidacion a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            pgimItemLiquidacionDTOCreado = this.liquidacionService
                    .modificarItemLiquidacionInfoPenalidad(pgimItemLiquidacionDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el informe penalidad de una liquidacion");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "el informe penalidad de una liquidacion ha sido modificada");
        respuesta.put("pgimItemLiquidacionDTO", pgimItemLiquidacionDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite obtener las configuraciones necesarias para el formulario de los
     * items de liquidacion de informe de penalidades.
     * 
     * @param idLiquidacion
     * @param idItemLiquidacion
     * @param idTipoEntregable
     * @return
     * @throws Exception
     */
    @GetMapping("/obtenerConfiguracionesGeneralesPenalidades/{idLiquidacion}/{idItemLiquidacion}/{idTipoEntregable}")
    public ResponseEntity<?> obtenerConfiguracionesGeneralesPenalidades(@PathVariable Long idLiquidacion,
            @PathVariable Long idItemLiquidacion, @PathVariable Long idTipoEntregable) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimItemLiquidacionDTO pgimItemLiquidacionDTO = null;
        PgimFichaRevisionDTO pgimFichaRevisionDTO = null;
        PgimItemConsumoDTO pgimItemConsumoDTO = null;
        PgimContratoDTO pgimContratoDTO = null;

        if(idLiquidacion != null){
            Long idInstanciaProces = this.liquidacionService.findById(idLiquidacion).getPgimInstanciaProces().getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {

            pgimItemLiquidacionDTO = this.liquidacionService.obtenerItemLiquidacionPorId(idItemLiquidacion);
            pgimFichaRevisionDTO = this.supervisionService
                    .obtenerFichaRevision(pgimItemLiquidacionDTO.getIdItemLiquidacion(), idTipoEntregable);
            pgimItemConsumoDTO = this.supervisionService
                    .obtenerItemConsumo(pgimItemLiquidacionDTO.getIdItemLiquidacion());
            pgimContratoDTO = this.supervisionService.obtenerContrato(pgimItemLiquidacionDTO.getIdItemLiquidacion());

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final PgimException e) {
            // Manejo de logs
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMensaje());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

        respuesta.put("pgimItemLiquidacionDTO", pgimItemLiquidacionDTO);
        respuesta.put("pgimFichaRevisionDTO", pgimFichaRevisionDTO);
        respuesta.put("pgimItemConsumoDTO", pgimItemConsumoDTO);
        respuesta.put("pgimContratoDTO", pgimContratoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/listarEntregablesPorContrato/{idContrato}/{descPreComprometido}/{descComprometido}/{descPorLiquidar}/{descLiquidado}/{descFacturado}")
    public ResponseEntity<Page<PgimItemConsumoDTO>> listarEntregablesPorContrato(
            @PathVariable Long idContrato, 
            @PathVariable Long descPreComprometido, 
            @PathVariable Long descComprometido, 
            @PathVariable Long descPorLiquidar, 
            @PathVariable Long descLiquidado, 
            @PathVariable Long descFacturado, @RequestBody PgimItemConsumoDTO filtro, Pageable paginador) {
                Page<PgimItemConsumoDTO> lPgimEntregableLiquidaAuxDTO = this.liquidacionService
                .listarEntregablesPorContrato(idContrato, descPreComprometido, descComprometido, 
                descPorLiquidar, descLiquidado, 
                descFacturado, filtro, paginador);
        return new ResponseEntity<Page<PgimItemConsumoDTO>>(lPgimEntregableLiquidaAuxDTO, HttpStatus.OK);
    }

    @PostMapping("/listarEntregablesPorLiquidarPorContrato")
    public ResponseEntity<Page<PgimItemConsumoDTO>> listarEntregablesPorLiquidarPorContrato(
            @RequestBody PgimItemConsumoDTO filtro, Pageable paginador) {

    	Page<PgimItemConsumoDTO> lPgimItemConsumoDTO = null; 
    	
    	try {
    		lPgimItemConsumoDTO = this.liquidacionService
                .listarEntregablesPorLiquidarPorContrato(filtro, paginador);
        
    	}catch(Exception e) {
    		log.error("Error al listar entregables listos para liquidar: " + e.getMessage());
    		log.error(e.getMessage(), e);
    	}

        return new ResponseEntity<Page<PgimItemConsumoDTO>>(lPgimItemConsumoDTO, HttpStatus.OK);
    }
    
    /**
     * Permite listar grupos de entregables listos para liquidar
     * 
     * @param filtro	Objeto que contiene las propiedades para filtrar
     * @param paginador	Objeto Pageable que contiene las propiedades para paginar, 
     * 					su ordenamiento por default corresponde en principio a las propiedades de agrupación
     * 					y en último nivel por código de fiscalización.
     * @return
     */
    @PostMapping("/listarGruposEntregablesPorLiquidar")
    public ResponseEntity<?> listarGruposEntregablesPorLiquidar(
            @RequestBody PgimItemConsumoDTO filtro, 
            @SortDefault(sort = {"idContrato", "idTipoEntregable", "idDivisionSupervisora", "idTipoSupervision", 
            		"idSubtipoSupervision", "idMotivoSupervision", "coSupervision"}, direction = Sort.Direction.ASC) Pageable paginador) {

    	Map<String, Object> respuesta = new HashMap<>();
		
    	Page<PgimItemConsumoDTO> pPgimItemConsumoDTO = null; 
    	List<GrupoEntregableLiquidacionDTO> lGruposEntregables = null;
    	
    	try {
	        pPgimItemConsumoDTO = this.liquidacionService
	                .listarEntregablesPorLiquidarPorContrato(filtro, paginador);
	        
	        List<PgimItemConsumoDTO> lPgimItemConsumoDTOList = pPgimItemConsumoDTO.getContent();
	        
	        lGruposEntregables = this.liquidacionService.agruparEntregablesLiquidar(lPgimItemConsumoDTOList);
        
    	}catch(Exception e) {
    		respuesta.put("mensaje", "Ocurrió un error al listar grupos de entregables listos para liquidar: " + e.getMessage());
			respuesta.put("error", e.getMessage());
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    	}

    	respuesta.put("mensaje", "Se consultó con éxito los grupos de entregables");
		respuesta.put("lGruposEntregables", lGruposEntregables);
		respuesta.put("cantidadEntregablesTotal", pPgimItemConsumoDTO.getTotalElements());

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		
    }
    
    @PutMapping("/aplicarPenalidadReemplazoPersonal")
    public ResponseEntity<ResponseDTO> aplicarPenalidadReemplazoPersonal(@RequestBody PgimLiquidacionDTO pgimLiquidacionDTO) {

        ResponseDTO responseDTO = null;
        PgimLiquidacionDTO pgimLiquidacionDTOModificado = null; 

        try {
            pgimLiquidacionDTOModificado = this.liquidacionService.aplicarPenalidadReemplazoPersonal(pgimLiquidacionDTO, this.obtenerAuditoria());

        } catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar aplicar la penalidad por reemplazo del personal");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);        
        } catch (Exception e) {
			log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, String.format("Ocurrió un error al intentar aplicar la penalidad por reemplazo del personal. Mensaje de la excepción: %s", e.getMessage()));

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, pgimLiquidacionDTOModificado, "Genial, se ha aplicado o no la penalidad al reemplazo del personal con exito"));
		
    }

    // @PreAuthorize("hasAnyAuthority('pr-dto-est_MO')")
	@PutMapping("/modificarPenalidadReemplazoPer")
	public ResponseEntity<ResponseDTO> modificarPenalidadReemplazoPer(@Valid @RequestBody PgimLiquidacionDTO pgimLiquidacionDTO, BindingResult resultadoValidacion) throws Exception {

        ResponseDTO responseDTO = null;
		PgimLiquidacionDTO pgimLiquidacionDTOModificado = null;
		Map<String, Object> respuestaLog = new HashMap<>();

		Long idInstanciaProces = this.liquidacionService.findById(pgimLiquidacionDTO.getIdLiquidacion()).getPgimInstanciaProces().getIdInstanciaProceso();

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());
		
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar modificar el monto de penalidad por reemplazo de personal");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimLiquidacionDTOModificado = this.liquidacionService.modificarPenalidadReemplazoPer(pgimLiquidacionDTO, this.obtenerAuditoria());

		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar modificar el monto de penalidad por reemplazo de personal");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, pgimLiquidacionDTOModificado, "Genial, el monto de penalidad por reemplazo de personal ha sido modificado"));
	}
}
