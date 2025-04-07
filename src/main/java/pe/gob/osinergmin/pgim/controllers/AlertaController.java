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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimAlertaDetalle;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.AlertaService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.IprocesoAlertaService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a las
 * alertas.
 * 
 * @descripción: Alerta
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/alerta")
public class AlertaController extends BaseController {

    @Autowired
    private IprocesoAlertaService iprocesoAlertaService;

    @Autowired
    private AlertaService alertaService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private ValorParametroRepository valorParametroRepository;

    @PreAuthorize("hasAnyAuthority('pg-alerta_AC')")
    @PostMapping("/listarAlerta")
    public ResponseEntity<Page<PgimAlertaDetalleDTO>> listarAlerta(
            @RequestBody PgimAlertaDetalleDTO pgimAlertaDetalleDTO,
            Pageable paginador) throws Exception {

        Page<PgimAlertaDetalleDTO> lPgimAlertaDTO = this.alertaService.listarAlerta(
                pgimAlertaDetalleDTO, this.obtenerAuditoria(), paginador);

        return new ResponseEntity<Page<PgimAlertaDetalleDTO>>(lPgimAlertaDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimProcesoDTO> lPgimProcesoDTO = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoAlerta = null;

        try {
            lPgimProcesoDTO = this.instanciaProcesService
                    .listarProcesos();
            lPgimFaseProcesoDTO = this.instanciaProcesService
                    .obtenerFasesPorProceso();
            lPgimPasoProcesoDTO = this.instanciaProcesService
                    .obtenerPasosPorFase();
            lPgimValorParametroDTOTipoAlerta = this.parametroService
                    .filtrarPorNombreTipoAlerta(ConstantesUtil.PARAM_TIPO_ALERTA);

        } catch (PgimException e) {
            log.error(e.getMensaje(), e);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimProcesoDTO", lPgimProcesoDTO);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);
        respuesta.put("lPgimPasoProcesoDTO", lPgimPasoProcesoDTO);
        respuesta.put("lPgimValorParametroDTOTipoAlerta", lPgimValorParametroDTOTipoAlerta);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('pg-alerta_MO')")
    @PutMapping("/modificarAlertaLeido")
    public ResponseEntity<?> modificarAlertaLeido(
            @Valid @RequestBody PgimAlertaDetalleDTO pgimAlertaDetalleDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimAlertaDetalle pgimAlertaDetalleActual = null;
        PgimAlertaDetalleDTO pgimAlertaDetalleDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el agente fiscalizado");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimAlertaDetalleActual = this.alertaService
                    .getByIdDetalleAlerta(pgimAlertaDetalleDTO.getIdDetalleAlerta());

            if (pgimAlertaDetalleActual == null) {
                mensaje = String.format("La alerta %s que intenta actualizar no existe en la base de datos",
                        pgimAlertaDetalleDTO.getIdDetalleAlerta());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la alerta a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimAlertaDetalleDTOModificado = this.alertaService.modificarAlertaLeido(
                    pgimAlertaDetalleDTO, pgimAlertaDetalleActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la alerta");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La alerta ha sido modificada");
        respuesta.put("pgimAlertaDetalleDTO", pgimAlertaDetalleDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite agregar un documento (y su archivo) en un expediente, si la instancia
     * proceso no tiene un expediente Siged relacionado, el flujo considera la
     * creación del mismo
     * 
     * @param pgimDocumentoDTO       Documento agregado.
     * @param pgimInstanciaProcesDTO instancia del proceso.
     * @param fileDocumento          Archivo adjunto.
     * 
     * @return ID del documento creado en Siged.
     */
    @PostMapping("/obtenerRuta")
    public ResponseEntity<ResponseDTO> obtenerRuta(
            @RequestPart("pgimAlertaDetalleDTO") PgimAlertaDetalleDTO pgimAlertaDetalleDTO)
            throws PgimException, Exception {

        ResponseEntity<ResponseDTO> re = null;
        try {
            re = this.alertaService.obtenerRuta(pgimAlertaDetalleDTO);
        } catch (PgimException e) {
            log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }    

            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0)); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        }
        return re;
    }

    @GetMapping("/contarAlertasPendientes")
    public ResponseEntity<?> contarAlertasPendientes() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        PgimAlertaDetalleDTO pgimAlertaDetalleDTO = null;

        try {
            pgimAlertaDetalleDTO = this.alertaService.contarAlertasPendientes(this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar alertas pendientes sin revisar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron alertas pendientes sin revisar");
        respuesta.put("pgimAlertaDetalleDTO", pgimAlertaDetalleDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerAlertaDetalleByInstanciaPaso/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerAlertaDetalleByInstanciaPaso(@PathVariable Long idInstanciaPaso) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimAlertaDetalleDTO pgimAlertaDetalleDTO = null;

        try {
            pgimAlertaDetalleDTO = this.alertaService.obtenerAlertaDetalleByInstanciaPaso(idInstanciaPaso,
                    this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ALERT_ASGNCION_PSO.toString()));
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el detalle de la alerta");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimAlertaDetalleDTO == null) {
            mensaje = String.format("El detalle de la alerta con el identificador: %d no existe en la base de datos",
                    idInstanciaPaso);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El detalle de la alerta ha sido recuperado");
        respuesta.put("pgimAlertaDetalleDTO", pgimAlertaDetalleDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/listarAlertaCumple")
    public ResponseEntity<Page<PgimIprocesoAlertaDTO>> listarAlertaCumple(@RequestBody final Long id, Pageable paginador) throws Exception {

        Page<PgimIprocesoAlertaDTO> lPgimAlertaDTO = this.iprocesoAlertaService.listarAlertaCumple(
                this.obtenerAuditoria(), paginador);

        return new ResponseEntity<Page<PgimIprocesoAlertaDTO>>(lPgimAlertaDTO, HttpStatus.OK);
    }


}
