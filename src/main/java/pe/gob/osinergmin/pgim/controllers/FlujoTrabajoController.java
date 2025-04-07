package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.MensajeDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionAccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.services.AlertaService;
import pe.gob.osinergmin.pgim.services.ConfiguraRiesgoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.IprocesoAlertaService;
import pe.gob.osinergmin.pgim.services.LiquidacionService;
import pe.gob.osinergmin.pgim.services.PasService;
import pe.gob.osinergmin.pgim.services.PrgrmSupervisionService;
import pe.gob.osinergmin.pgim.services.RankingRiesgoService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.TipoMensaje;

/**
 * Controlador para la gestión de las funcionalidades relacionadas al flujo de
 * trabajo
 * 
 * @descripción: Flujo de trabajo.
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/flujotrabajo")
public class FlujoTrabajoController extends BaseController {

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private InstanciaProcesService instanciaProcesService;
    
    @Autowired
    private SupervisionService supervisionService;
    
    @Autowired
    private PasService pasService;
    
    @Autowired
    private PrgrmSupervisionService prgrmSupervisionService;
    
    @Autowired
    private LiquidacionService liquidacionService;
    
    @Autowired
    private ConfiguraRiesgoService configuraRiesgoService;
    
    @Autowired
    private RankingRiesgoService rankingRiesgoService;      

    @Autowired
    private MensajeController mensajeController;

    @Autowired
    private AlertaService alertaService;
    
    @Autowired
    private IprocesoAlertaService iprocesoAlertaService;

    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;

    /**
     * Permite obtener la lista de personas del Osinergmin
     * 
     * @param palabra Palabra clave de búsqueda.
     * @return
     */
    @GetMapping("/listarPersonalOsi/{palabra}")
    public ResponseEntity<?> listarPersonalOsi(@PathVariable String palabra) {

        final Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaosiAuxDTO> lPgimPersonaosiAuxDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaosiAuxDTO = new ArrayList<PgimPersonaosiAuxDTO>();
            respuesta.put("mensaje", "No se encontraron personas del Osinergmin");
            respuesta.put("lPgimPersonaosiAuxDTO", lPgimPersonaosiAuxDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (palabra.equals("todos")) {
            palabra = null;
        }

        try {
            lPgimPersonaosiAuxDTO = this.flujoTrabajoService.listarPersonalOsi(palabra);
        } catch (final DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la búsqueda de las personas del Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las personas del Osinergmin con la palabra clave");
        respuesta.put("lPgimPersonaosiAuxDTO", lPgimPersonaosiAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener las configuraciones necesarias para realizar la asignación de
     * una instancia de proceso dada.
     * 
     * @param idInstanciaProceso Identificador interno de la instancia del proceso.
     * @return
     * @throws Exception
     */
    @GetMapping("/obtenerConfiguracionesPaso/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerConfiguracionesPaso(@PathVariable final Long idInstanciaPaso) throws Exception {

        final Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();
        PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = null;
        PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = null;
        PgimRelacionPasoDTO pgimRelacionPasoDTOActual = null;
        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes = null;
        List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO = null;

        try {
            
            Long idInstanciaProces = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();

            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(idInstanciaProces, idInstanciaPaso, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }

            pgimInstanciaProcesDTOActual = this.flujoTrabajoService.obtenerInstanciaProceso(idInstanciaProces);
            pgimInstanciaPasoDTOActual = this.flujoTrabajoService.obtenerInstanciaPasoActualPorIdInstanciaPaso(idInstanciaPaso);

            if (pgimInstanciaPasoDTOActual == null) {
                respuesta.put("mensaje", "No se ha encontrado un paso activo para la instancia del proceso");
                respuesta.put("error", "No se ha encontrado un paso activo para la instancia del proceso");

                log.error("No se ha encontrado un paso activo para la instancia del proceso");

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            pgimRelacionPasoDTOActual = this.flujoTrabajoService
                    .obtenerRelacionPaso(pgimInstanciaPasoDTOActual.getIdInstanciaPaso());

            lPgimRelacionPasoDTOSiguientes = this.flujoTrabajoService
                    .obtenerPasosSiguientes(pgimRelacionPasoDTOActual.getIdPasoProcesoDestino(),
                            pgimInstanciaPasoDTOActual);
            
            lPgimRelacionSubcatDTO = this.flujoTrabajoService.obtenerSubcategoriaDocParaFiscPropia(idInstanciaProces, lPgimRelacionPasoDTOSiguientes);

        } catch (final DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[Ocurrió un error al realizar la consulta de las configuraciones para la instancia del paso]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las configuraciones para la instancia del paso");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final PgimException e) {
                        
            // Manejo de logs
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las configuraciones para la instancia del paso");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron las configuraciones para la instancia del paso");

        respuesta.put("pgimInstanciaProcesDTOActual", pgimInstanciaProcesDTOActual);
        respuesta.put("pgimInstanciaPasoDTOActual", pgimInstanciaPasoDTOActual);
        respuesta.put("pgimRelacionPasoDTOActual", pgimRelacionPasoDTOActual);
        respuesta.put("lPgimRelacionPasoDTOSiguientes", lPgimRelacionPasoDTOSiguientes);
        respuesta.put("lPgimRelacionSubcatDTO", lPgimRelacionSubcatDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista de personas del Osinergmin
     * 
     * @param idInstanciaProceso Identificador de la instancia del proceso.
     * @param idContrato         Identificador interno de contrato en caso aplique.
     *                           Si no aplica enviar el valor 0.
     * @param idRelacionPaso     Identificador de la relación del paso. Se debe
     *                           utilizar el paso siguiente.
     * @param palabra            Palabra clave a buscar.
     * @return
     */
    @GetMapping("/listarPersonalAsignable/{idInstanciaProceso}/{idContrato}/{idRelacionPaso}/{palabra}")
    public ResponseEntity<?> listarPersonalAsignable(@PathVariable final Long idInstanciaProceso,
            @PathVariable final Long idContrato, @PathVariable final Long idRelacionPaso,
            @PathVariable String palabra) {

        final Map<String, Object> respuesta = new HashMap<>();
        List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = null;

        if (palabra.equals("_vacio_") || idRelacionPaso == 0L) {
            lPgimEqpInstanciaProDTO = new ArrayList<PgimEqpInstanciaProDTO>();
            respuesta.put("mensaje", "No se encontraron personas para la asignación");
            respuesta.put("lPgimEqpInstanciaProDTO", lPgimEqpInstanciaProDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (palabra.equals("todos")) {
            palabra = null;
        }

        try {
            lPgimEqpInstanciaProDTO = this.flujoTrabajoService.listarPersonalAsignable(idInstanciaProceso, idContrato,
                    idRelacionPaso, palabra);
        } catch (final DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la búsqueda de las personas del Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las personas para la asignación");
        respuesta.put("lPgimEqpInstanciaProDTO", lPgimEqpInstanciaProDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener la persona específica que calce con el rol solicitado por el
     * paso implicado en la relación
     * 
     * @param idInstanciaProceso Identificador interno de la instancia del proceso.
     * @param idRelacionPaso     Identificador interno de la relación del paso.
     * @param idContrato         Identificador interno de contrato en caso aplique.
     *                           Si no aplica enviar el valor 0.
     * @return
     */
    @GetMapping("/obtenerPersonaEqpPorRolDestino/{idInstanciaProceso}/{idRelacionPaso}/{idContrato}")
    public ResponseEntity<?> obtenerPersonaEqpPorRolDestino(@PathVariable final Long idInstanciaProceso,
            @PathVariable final Long idRelacionPaso, @PathVariable final Long idContrato) throws Exception {

        final Map<String, Object> respuesta = new HashMap<>();
        PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = null;

        try {
            pgimEqpInstanciaProDTO = this.flujoTrabajoService.obtenerPersonaEqpPorRolDestino(idInstanciaProceso,
                    idRelacionPaso, idContrato);
            
        } catch (final DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la búsqueda de las personas del Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontró la persona para la asignación");
        respuesta.put("pgimEqpInstanciaProDTO", pgimEqpInstanciaProDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/asignarPasoProceso")
    public ResponseEntity<ResponseDTO> asignarPasoProceso(@RequestBody final PgimInstanciaPasoDTO pgimInstanciaPasoDTO)
            throws Exception {

        Map<String, Object> respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(pgimInstanciaPasoDTO.getIdInstanciaProceso(), 
        		pgimInstanciaPasoDTO.getIdInstanciaPasoPadre(), this.obtenerAuditoria().getUsername());
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOCreada = null;
        ResponseDTO responseDTO = null;

        try {
            final PgimInstanciaPaso pgimInstanciaPaso = this.flujoTrabajoService
                    .asignarPasoProceso(pgimInstanciaPasoDTO, ConstantesUtil.METODO_ASIGNAR_TAREA, this.obtenerAuditoria());

            pgimInstanPasoAuxDTOCreada = this.flujoTrabajoService
                    .obtenerInstanciaPasoAuxPorId(pgimInstanciaPaso.getIdInstanciaPaso());
            

            // Enviando mensaje para los clientes:
            String mensajeParaClientesTitulo = pgimInstanPasoAuxDTOCreada.getNoEtiquetaOtrabajo();

            String mensajeParaClientes = String.format(
                    "%s dice <b>%s</b>",
                    pgimInstanPasoAuxDTOCreada.getDescNoCompletoPersonaOrigen(),
                    pgimInstanPasoAuxDTOCreada.getDeMensaje());

            MensajeDTO mensajeDTO = new MensajeDTO();
            mensajeDTO.setTitulo(mensajeParaClientesTitulo);
            mensajeDTO.setTipo(TipoMensaje.ASIGNAR_TAREA);
            mensajeDTO.setNombreUsuarioOrigen(pgimInstanPasoAuxDTOCreada.getNoUsuarioOrigen());
            mensajeDTO.setNombreUsuarioDestino(pgimInstanPasoAuxDTOCreada.getNoUsuarioDestino());
            mensajeDTO.setFecha(new Date());
            mensajeDTO.setTexto(mensajeParaClientes);            

            this.mensajeController.enviarMensaje(mensajeDTO);
            
            // Enviando mensaje por aprobación de informe
            List<String> lNoUsuarios = this.alertaService.listarDestinatariosNotificacionAlerta(pgimInstanciaPaso);
            
            for (String noUsuario : lNoUsuarios) {
            	MensajeDTO mensajeDTO2 = new MensajeDTO();
                mensajeDTO2.setTitulo(mensajeParaClientesTitulo);
                mensajeDTO2.setTipo(TipoMensaje.INFORME_APROBADO);
                mensajeDTO2.setNombreUsuarioDestino(noUsuario);
                mensajeDTO2.setFecha(new Date());
                mensajeDTO2.setTexto(ConstantesUtil.PARAM_ALERTA_APROBACION_INFORME_FISC_EMP_SUP);            

                this.mensajeController.enviarMensaje(mensajeDTO2);				
			}

        } catch (final DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar el paso del proceso"
            + (e.getMessage()!=null ?":<br> "+e.getMessage():"")
            		);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final PgimException e) {
            
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }

            responseDTO = new ResponseDTO(tipoResultado, e.getMensaje(), e.getDetalleExcepcionDTO());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar el paso del proceso" + (e.getMessage()!=null ?":<br> "+e.getMessage():""));
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimInstanPasoAuxDTOCreada, "El paso del proceso ha sido asignado"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Permite obtener la trazabilidad de acuerdo con la instancia de proceso.
     * @param idInstanciaProceso
     * @param paginador
     * @return
     */
    @PostMapping("/obtenerTrazabilidad")
    public ResponseEntity<Page<PgimInstanPasoAuxDTO>> obtenerTrazabilidad(@RequestBody final Long idInstanciaProceso,
            final Pageable paginador) throws Exception {
                
        final Page<PgimInstanPasoAuxDTO> lPgimInstanPasoAuxDTO = this.flujoTrabajoService
                .obtenerInstanciaPasoAuxPorInstanciaProceso(idInstanciaProceso, paginador);

        return new ResponseEntity<Page<PgimInstanPasoAuxDTO>>(lPgimInstanPasoAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la trazabilidad de acuerdo con el numero de expediente.
     * 
     * @param nuExpedienteSiged
     * @param paginador
     * @return
     */
    @PostMapping("/obtenerTrazabilidadPorExpediente")
    public ResponseEntity<Page<PgimInstanPasoAuxDTO>> obtenerTrazabilidadPorExpediente(
            @RequestBody final String nuExpedienteSiged,
            final Pageable paginador) throws Exception {

        PgimInstanciaProcesDTO pgimInstanciaProcesDTO = this.instanciaProcesService
                .obtenerInstanciaProcesoPorNuExpediente(nuExpedienteSiged);

        Long idInstanciaProceso = null;

        if (pgimInstanciaProcesDTO != null) {
            idInstanciaProceso = pgimInstanciaProcesDTO.getIdInstanciaProceso();
        }

        final Page<PgimInstanPasoAuxDTO> lPgimInstanPasoAuxDTO = this.flujoTrabajoService
                .obtenerInstanciaPasoAuxPorInstanciaProceso(idInstanciaProceso, paginador);
        return new ResponseEntity<Page<PgimInstanPasoAuxDTO>>(lPgimInstanPasoAuxDTO, HttpStatus.OK);
    }

    @PostMapping("/asegurarInteresEnInstanciaProceso")
    public ResponseEntity<ResponseDTO> asegurarInteresEnInstanciaProceso(
            @RequestBody final PgimInstanPasoAuxDTO pgimInstanPasoAuxDTO) throws Exception {

        final Long idInstanciaPaso = pgimInstanPasoAuxDTO.getIdInstanciaPaso();
        final Long idPersonaEqpDestino = pgimInstanPasoAuxDTO.getIdPersonaEqpDestino();
        final String flPersDestinoInteresada = pgimInstanPasoAuxDTO.getFlPersDestinoInteresada();

        String mensaje = "";

        try {
            this.flujoTrabajoService.asegurarInteresEnInstanciaProceso(idInstanciaPaso, idPersonaEqpDestino,
                    flPersDestinoInteresada, this.obtenerAuditoria());
        } catch (final DataAccessException e) {
            mensaje = "Ocurrió un error asegurar el interés del usuario en la instancia del proceso";
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error", mensaje));
        } catch (final PgimException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        mensaje = "Se ha logrado configurar el interés de la persona";

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", mensaje));
    }

    /**
     * Permite obtener la persona específica que calce con el rol solicitado por el
     * paso implicado en la relación
     * 
     * @param idInstanciaProceso Identificador interno de la instancia del proceso.
     * @param idRelacionPaso     Identificador interno de la relación del paso.
     * @param idContrato         Identificador interno de contrato en caso aplique.
     *                           Si no aplica enviar el valor 0.
     * @return
     */
    @GetMapping("/personaEstaInteresada/{idInstanciaProceso}/{noUsuario}")
    public ResponseEntity<ResponseDTO> personaEstaInteresada(@PathVariable final Long idInstanciaProceso,
            @PathVariable final String noUsuario) {

        String mensaje = "";
        int valor;

        try {
            final Boolean estaInteresada = this.flujoTrabajoService.personaEstaInteresada(idInstanciaProceso,
                    noUsuario);
            if (estaInteresada) {
                valor = 1;
            } else {
                valor = 0;
            }
        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            mensaje = "Ocurrió un error: " + e.getMostSpecificCause().getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error", mensaje));
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            mensaje = "Ocurrió un error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error", mensaje));
        }

        mensaje = "Se ha logrado identificar el interés de la persona";
        final ResponseDTO responseDTO = new ResponseDTO("success", mensaje, valor);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/filtrar/listarPorPersonaDestino/{palabra}")
    public ResponseEntity<?> listarPorPersonaDestino(@PathVariable final String palabra) {

        final Map<String, Object> respuesta = new HashMap<>();
        List<PgimInstanPasoAuxDTO> lPgimInstanPasoAuxDTOPersDestino = null;

        if (palabra.equals("_vacio_")) {
            lPgimInstanPasoAuxDTOPersDestino = new ArrayList<PgimInstanPasoAuxDTO>();
            respuesta.put("mensaje", "No se encontraron los nombres de la persona destino");
            respuesta.put("lPgimInstanPasoAuxDTOPersDestino", lPgimInstanPasoAuxDTOPersDestino);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimInstanPasoAuxDTOPersDestino = this.flujoTrabajoService.listarPorPersonaDestino(palabra);
        } catch (final DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los nombres de la persona destino");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los nombres de la persona destino");
        respuesta.put("lPgimInstanPasoAuxDTOPersDestino", lPgimInstanPasoAuxDTOPersDestino);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrar/listarPorPersonaDestinoDelRepDetalladoFisc/{idContrato}/{palabra}")
    public ResponseEntity<?> listarPorPersonaDestinoDelRepDetalladoFisc(@PathVariable final Long idContrato, @PathVariable final String palabra) {

        final Map<String, Object> respuesta = new HashMap<>();
        List<PgimInstanPasoAuxDTO> lPgimInstanPasoAuxDTOPersDestino = null;

        if (palabra.equals("_vacio_")) {
            lPgimInstanPasoAuxDTOPersDestino = new ArrayList<PgimInstanPasoAuxDTO>();
            respuesta.put("mensaje", "No se encontraron los nombres de la persona destino");
            respuesta.put("lPgimInstanPasoAuxDTOPersDestino", lPgimInstanPasoAuxDTOPersDestino);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimInstanPasoAuxDTOPersDestino = this.flujoTrabajoService.listarPorPersonaDestinoDelRepDetalladoFisc(idContrato, palabra);
        } catch (final DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los nombres de la persona destino");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los nombres de la persona destino");
        respuesta.put("lPgimInstanPasoAuxDTOPersDestino", lPgimInstanPasoAuxDTOPersDestino);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrar/listarPorPersonaOsiDestinoDelRepDetalladoFisc/{palabra}")
    public ResponseEntity<?> listarPorPersonaOsiDestinoDelRepDetalladoFisc(@PathVariable final String palabra) {

        final Map<String, Object> respuesta = new HashMap<>();
        List<PgimInstanPasoAuxDTO> lPgimInstanPasoAuxDTOPersDestino = null;

        if (palabra.equals("_vacio_")) {
            lPgimInstanPasoAuxDTOPersDestino = new ArrayList<PgimInstanPasoAuxDTO>();
            respuesta.put("mensaje", "No se encontraron los nombres de la persona destino");
            respuesta.put("lPgimInstanPasoAuxDTOPersDestino", lPgimInstanPasoAuxDTOPersDestino);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimInstanPasoAuxDTOPersDestino = this.flujoTrabajoService.listarPorPersonaOsiDestinoDelRepDetalladoFisc(palabra);
        } catch (final DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los nombres de la persona destino");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los nombres de la persona destino");
        respuesta.put("lPgimInstanPasoAuxDTOPersDestino", lPgimInstanPasoAuxDTOPersDestino);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/reasignarPasoProceso")
    public ResponseEntity<ResponseDTO> reasignarPasoProceso(
            @RequestBody final PgimInstanciaPasoDTO pgimInstanciaPasoDTO)
            throws Exception {

        Map<String, Object> respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(pgimInstanciaPasoDTO.getIdInstanciaProceso(), 
        		pgimInstanciaPasoDTO.getIdInstanciaPaso(), this.obtenerAuditoria().getUsername());
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOCreada = null;
        ResponseDTO responseDTO = null;

        try {
            final PgimInstanciaPaso pgimInstanciaPaso = this.flujoTrabajoService
                    .reasignarPasoProceso(pgimInstanciaPasoDTO, this.obtenerAuditoria());

            pgimInstanPasoAuxDTOCreada = this.flujoTrabajoService
                    .obtenerInstanciaPasoAuxPorId(pgimInstanciaPaso.getIdInstanciaPaso());

            // Enviando mensaje para los clientes:
            String mensajeParaClientesTitulo = pgimInstanPasoAuxDTOCreada.getNoEtiquetaOtrabajo();

            String mensajeParaClientes = String.format(
                    "%s dice <b>%s</b>",
                    pgimInstanPasoAuxDTOCreada.getDescNoCompletoPersonaOrigen(),
                    pgimInstanPasoAuxDTOCreada.getDeMensaje());

            MensajeDTO mensajeDTO = new MensajeDTO();
            mensajeDTO.setTitulo(mensajeParaClientesTitulo);
            mensajeDTO.setTipo(TipoMensaje.REASIGNAR_TAREA);
            mensajeDTO.setNombreUsuarioOrigen(pgimInstanPasoAuxDTOCreada.getNoUsuarioOrigen());
            mensajeDTO.setNombreUsuarioDestino(pgimInstanPasoAuxDTOCreada.getNoUsuarioDestino());
            mensajeDTO.setFecha(new Date());
            mensajeDTO.setTexto(mensajeParaClientes);            

            this.mensajeController.enviarMensaje(mensajeDTO);
            //        
        } catch (final DataAccessException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar reasignar el responsable del proceso"); 

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final PgimException e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {

            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar reasignar el responsable del proceso");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimInstanPasoAuxDTOCreada, "Estupendo, El responsable del proceso ha sido reasignado");
        responseDTO.setData(pgimInstanPasoAuxDTOCreada);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
     * Permite marcar una asignación (instancia de paso) como "Leído" o "No leído"
     * @param idInstanciaPaso
     * @param flLeido
     * @return
     */
    @GetMapping("/marcarFlLecturaInstanciaPaso/{idInstanciaPaso}/{flLeido}")
    public ResponseEntity<ResponseDTO> marcarFlLecturaInstanciaPaso(@PathVariable final Long idInstanciaPaso, 
    		@PathVariable final String flLeido) {

        String mensaje = "";
        String estado = "";

        try {
        	
        	PgimInstanciaPaso pgimInstanciaPasoModificado = this.flujoTrabajoService.marcarFlLecturaInstanciaPaso(
        			idInstanciaPaso, flLeido, this.obtenerAuditoria());
        	
        	if(pgimInstanciaPasoModificado == null && flLeido.equals(ConstantesUtil.FL_IND_SI)) {        		
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.NONE, ""));
        	}
            
            estado = (flLeido.equals(ConstantesUtil.FL_IND_SI))? "Leída" : "No leída";
            
        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = "Ocurrió un error al intentar marcar como \""+ estado +"\": " + e.getMostSpecificCause().getMessage();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            mensaje = "Ocurrió un error al intentar marcar como \""+ estado +"\": " + e.getMessage();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }

        mensaje = "Genial, la tarea se encuentra \""+ estado +"\"";
        final ResponseDTO responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    
    /**
     * Permite finalizar una tarea (instancia de paso) desactivándola,
     * usado para los casos en que llegan a converger los sub-flujos paralelos en determinada tarea, 
     * momento en que no se podrá continuar con el flujo hasta que solo haya una tarea activa en cuestión.
     * 
     * @param idInstanciaPaso
     * @return
     */
    @GetMapping("/finalizarInstanciaPaso/{idInstanciaPaso}")
    public ResponseEntity<ResponseDTO> finalizarInstanciaPaso(@PathVariable final Long idInstanciaPaso) {

        String mensaje = "";

        try {
        	
        	PgimInstanciaPaso pgimInstanciaPasoModificado = this.flujoTrabajoService.finalizarInstanciaPaso(
        			idInstanciaPaso, this.obtenerAuditoria());

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = "Ocurrió un error al intentar finalizar la tarea: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            
        } catch (final PgimException e) {
            log.error(e.getMensaje(), e);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje()));    
            
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            mensaje = "Ocurrió un error al intentar finalizar la tarea: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }

        mensaje = "Genial, la tarea se ha finalizado en su presente sub-flujo";
        final ResponseDTO responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }    
    
    /**
     * Permite verificar la existencia de warnings para mostrarle al usuario antes de
     * que este confirme la asignación de paso. Ejm. documentos sin firmar
     * 
     * @param idInstanciaProceso	Id de la instancia de proceso
     * @param idRelacionPaso		Id de la relación paso que se va a transitar
     * @return
     * @throws Exception
     */
    @GetMapping("/verificarWarningsAsignacion/{idInstanciaProceso}/{idRelacionPaso}")
    public ResponseEntity<?> verificarWarningsAsignacion(@PathVariable final Long idInstanciaProceso,
            @PathVariable final Long idRelacionPaso) throws Exception {

        final Map<String, Object> respuesta = new HashMap<>();
        String sWarnings = "";
        List<PgimInfraccionAuxDTO> lInfracionesSC = new ArrayList<PgimInfraccionAuxDTO>();

        try {      
            
            PgimInstanciaProcesDTO pgimInstanciaProcesoDTO = this.flujoTrabajoService.obtenerInstanciaProceso(idInstanciaProceso);
            
            if (pgimInstanciaProcesoDTO.getNuExpedienteSiged() == null) {

                respuesta.put("mensaje", "No se encontró un expediente para esta instancia");
                respuesta.put("sWarnings", sWarnings); 
        
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
            }
            
            sWarnings = this.flujoTrabajoService
            		.verificarWarningsAsignacion(idRelacionPaso, idInstanciaProceso, this.obtenerAuditoria());
            lInfracionesSC = this.flujoTrabajoService
                    .verificarContratistas(idRelacionPaso, idInstanciaProceso, this.obtenerAuditoria());
            
        } catch (final DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la verificación de warnings para la asignación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
            
        } catch (final Exception e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la verificación de warnings para la asignación");
            respuesta.put("error", e.getMessage());
            log.error(e.getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);        
        }

        respuesta.put("mensaje", "Se realizó la verificación de warnings para la asignación");
        respuesta.put("sWarnings", sWarnings); 
        respuesta.put("lInfracionesSC", lInfracionesSC); 

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerRelacionAccion/{idRelacionPaso}")
    public ResponseEntity<?> obtenerRelacionAccion(@PathVariable final Long idRelacionPaso) throws Exception {

        final Map<String, Object> respuesta = new HashMap<>();
        List<PgimRelacionAccionDTO> lPgimRelacionAccionDTO = null;

        try {                        
            
            lPgimRelacionAccionDTO = this.flujoTrabajoService.obtenerRelacionAccion(idRelacionPaso);
            
        } catch (final DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la obtención de acciones para la asignación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se realizó la obtención de aciones del paso en la asignación");
        respuesta.put("lPgimRelacionAccionDTO", lPgimRelacionAccionDTO); 

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Permite obtener las cantidades de tareas pendientes que tiene el usuario en sesión
     * de los distintos objetos de trabajo
     * 
     * @return
     * @throws Exception
     */
    @GetMapping("/contarTareasPendientes")
    public ResponseEntity<ResponseDTO> contarTareasPendientes() throws Exception {
		
    	ResponseDTO responseDTO = null;
		Map<String, Object> respuesta = new HashMap<>();

		try {  
	        Integer cantidadSupervPendientes = this.supervisionService.contarSupervPendientes(this.obtenerAuditoria());
	        Integer cantidadPasPendientes = this.pasService.contarPasPendientes(this.obtenerAuditoria());
	        Integer cantidadProgramasPendientes = this.prgrmSupervisionService.contarProgramasPendientes(this.obtenerAuditoria());
	        Integer cantidadLiquidacionesPendientes = this.liquidacionService.contarLiquidacionesPendientes(this.obtenerAuditoria());
	        Integer cantidadConfiguraRiesgoPendientes = this.configuraRiesgoService.contarConfiguraRiesgoPendientes(this.obtenerAuditoria());
	        Integer cantidadRankingRiesgoPendientes = this.rankingRiesgoService.contarRankingRiesgoPendientes(this.obtenerAuditoria());
	        
	        respuesta.put("cantidadSupervPendientes", cantidadSupervPendientes);
	        respuesta.put("cantidadPasPendientes", cantidadPasPendientes);
	        respuesta.put("cantidadProgramasPendientes", cantidadProgramasPendientes);
	        respuesta.put("cantidadLiquidacionesPendientes", cantidadLiquidacionesPendientes);
	        respuesta.put("cantidadConfiguraRiesgoPendientes", cantidadConfiguraRiesgoPendientes);
	        respuesta.put("cantidadRankingRiesgoPendientes", cantidadRankingRiesgoPendientes);
	        
		} catch (final Exception e) {
            log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al consultar la cantidad de tareas pendientes"
            			+ (e.getMessage()!=null ?":<br> "+e.getMessage():""));
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);            
        }
        
        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Genial, se recuperó la cantidad de tareas pendientes");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    @GetMapping("/obtenerIProcesoAlertaPorIdInstancia/{idInstanciaProceso}")
	public ResponseEntity<List<PgimIprocesoAlertaDTO>> listar(@PathVariable Long idInstanciaProceso) {
		List<PgimIprocesoAlertaDTO> listaAlertas = new ArrayList<>();

		listaAlertas = iprocesoAlertaService.listarIProcesoAlertaPorIdInstancia(idInstanciaProceso);

		if (listaAlertas == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listaAlertas);
	}
}