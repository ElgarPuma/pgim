package pe.gob.osinergmin.pgim.controllers;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.dtos.RevisionInforme;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.RevisionInformeService;
import pe.gob.osinergmin.pgim.siged.wssoap.SigedSoapService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.FechaFeriado;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a la revision
 * de informe.
 * 
 * @descripción: Revision de informe
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 22/10/2020
 */
@RestController
@Slf4j
@RequestMapping("/revisioninforme")
public class RevisionInformeController extends BaseController {

    @Autowired
    private RevisionInformeService revisionInformeService;

    @Autowired
    private SigedSoapService sigedSoapService;

    @Autowired
	private FlujoTrabajoService flujoTrabajoService;

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_AC')")
    @GetMapping("/listarDocumentosRevisionInforme/{coTablaInstancia}/{idProceso}/{idFase}/{idSubcatDocumento}/{descFechaPresentacionActa}")
    public ResponseEntity<?> listarDocumentosRevisionInforme(@PathVariable Long coTablaInstancia,
            @PathVariable Long idProceso, @PathVariable Long idFase, @PathVariable Long idSubcatDocumento,
            @PathVariable String descFechaPresentacionActa)
            throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimDocumentoDTO> lPgimDocumentoDTO = new LinkedList<PgimDocumentoDTO>();

        String[] fecha = descFechaPresentacionActa.split("-");
        int iDia = Integer.valueOf(fecha[0]);
        int iMes = Integer.valueOf(fecha[1]);
        int iAnio = Integer.valueOf(fecha[2]);

        Integer caDiasParaPresentacion = ConstantesUtil.PLAZO_DIAS;

        LocalDate fechaParaPresentacion = this.calcularFechas(iDia, iMes, iAnio, 1);

        String feDesdeParaPresentacionDesc = fechaParaPresentacion.getDayOfMonth() + "/"
                + fechaParaPresentacion.getMonthValue() + "/" + fechaParaPresentacion.getYear();

        LocalDate fechaMaxPresentacion = calcularFechas(fechaParaPresentacion.getDayOfMonth(),
                fechaParaPresentacion.getMonthValue(), fechaParaPresentacion.getYear(), ConstantesUtil.PLAZO_DIAS - 1);

        String feMaxPresentacion = fechaMaxPresentacion.getDayOfMonth() + "/" + fechaMaxPresentacion.getMonthValue()
                + "/" + fechaMaxPresentacion.getYear();

        try {

            List<PgimDocumentoDTO> lPgimDocumentoDTOBase = this.revisionInformeService
                    .verDocumentosRevisionInforme(coTablaInstancia, idProceso, idFase, idSubcatDocumento,
                            this.obtenerAuditoria());

            LocalDate fechaActual = LocalDate.now();

            for (PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTOBase) {
                pgimDocumentoDTO
                        .setDescCaDiasDemoraCalculados(obtenerCantidadDiasDemora(fechaActual, fechaMaxPresentacion));
                lPgimDocumentoDTO.add(pgimDocumentoDTO);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
            respuesta.put("error", e.getMessage());

            log.error(e.getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron documentos");
        respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTO);
        respuesta.put("caDiasParaPresentacion", caDiasParaPresentacion);
        respuesta.put("feDesdeParaPresentacionDesc", feDesdeParaPresentacionDesc);
        respuesta.put("feMaxPresentacion", feMaxPresentacion);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_MO')")
    @PutMapping("/crearObservacionInforme")
    public ResponseEntity<ResponseDTO> crearObservacionInforme(
            @Valid @RequestBody RevisionInforme observacionInforme, BindingResult resultadoValidacion)
            throws Exception {

        RevisionInforme observacionInformeCreado = new RevisionInforme();
        String mensaje = "";
        Map<String, Object> respuestaLog = new HashMap<>();
        
        if (observacionInforme.getPgimDocumentoDTO() != null) {
            if (observacionInforme.getPgimDocumentoDTO().getIdInstanciaProceso() != null) {

                Long idInstanciaProceso = observacionInforme.getPgimDocumentoDTO().getIdInstanciaProceso();
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());

            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else{
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());
            
            mensaje = "Se han encontrado inconsistencias para crear la observación del informe";
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errores.toString(), mensaje)); 
        }

        try {
            observacionInformeCreado = this.revisionInformeService.crearObservacionInforme(observacionInforme,
                    this.obtenerAuditoria());
            
        }catch (PgimException e) {

			// Excepcion controlada que deberá ser manejada por el frontend
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }           

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
			
        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al intentar crear la observación del informe";
            String errorMsj = e.getMostSpecificCause().getMessage();
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(errorMsj, e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errorMsj, mensaje));
            
        } catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage())); 
    	
        }

        ResponseDTO respuesta = new ResponseDTO(TipoResultado.SUCCESS, observacionInformeCreado, "Genial, la observación del informe ha sido creada"); 
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * Aprobacion
     * 
     * @param revisionInforme
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('sp-ri-conf_MO')")
    @PutMapping("/crearAprobacionInforme")
    public ResponseEntity<ResponseDTO> crearAprobacionInforme(
            @Valid @RequestBody RevisionInforme revisionInforme, BindingResult resultadoValidacion)
            throws Exception {

        RevisionInforme aprobacionInformeCreado = new RevisionInforme();
        String mensaje = "";
        Map<String, Object> respuestaLog = new HashMap<>();
        
        if (revisionInforme.getPgimDocumentoDTO() != null) {
            if (revisionInforme.getPgimDocumentoDTO().getIdInstanciaProceso() != null) {

                Long idInstanciaProceso = revisionInforme.getPgimDocumentoDTO().getIdInstanciaProceso();
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());

            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else{
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            mensaje = "Se han encontrado inconsistencias para crear la aprobación del informe";
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errores.toString(), mensaje));
        }

        try {
            aprobacionInformeCreado = this.revisionInformeService.crearAprobacionInforme(revisionInforme,
                    this.obtenerAuditoria());
            
        }catch (PgimException e) {
			
			// Excepcion controlada que deberá ser manejada por el frontend
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }           

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
            
        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al intentar crear la aprobación del informe";
            String errorMsj = e.getMostSpecificCause().getMessage();
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(errorMsj, e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errorMsj, mensaje));
            
        } catch (Exception e) {
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);
    		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage())); 
        }

	    ResponseDTO respuesta = new ResponseDTO(TipoResultado.SUCCESS, aprobacionInformeCreado, "Genial, la aprobación del informe ha sido creada. "+
            "Para que esta tarea se considere completada debe firmar este documento y enviar el flujo hacia la tarea “Elaborar ficha de evaluación de hechos verificados v1, MCAF y OCAF"); 
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    private LocalDate calcularFechas(int dia, int mes, int anio, int cantDias) throws Exception {
        LocalDate fechaActual = LocalDate.of(anio, mes, dia);
        for (int i = 0; i < cantDias; i++) {
            fechaActual = fechaActual.plusDays(1);
            fechaActual = validarSabadosDomingosFeriados(fechaActual);
        }
        return fechaActual;
    }

    private LocalDate validarSabadosDomingosFeriados(LocalDate fecha) throws Exception {
        LocalDate fechaModificada = null;
        if (fecha.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            LocalDate fechaPlusDay = fecha.plusDays(1);
            fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
        } else if (fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            LocalDate fechaPlusDay = fecha.plusDays(1);
            fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
        } else {
            String cadenaFecha = completarConCeros(fecha.getDayOfMonth()) + "/"
                    + completarConCeros(fecha.getMonthValue()) + "/" + fecha.getYear();
            // FechaFeriado esFeriado = CommonsUtil.esFeriado(cadenaFecha,
            // propertiesConfig.getUrlSigedRestOld());
            FechaFeriado esFeriado = this.sigedSoapService.esFeriado(cadenaFecha);
            if (esFeriado.getMensajeFeriado().equals("Es Feriado")) {
                LocalDate fechaPlusDay = fecha.plusDays(1);
                fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
            } else {
                fechaModificada = fecha;
            }
        }

        return fechaModificada;
    }

    private String completarConCeros(int diaMes) {
        String cadenaDiaMes = "";
        switch (diaMes) {
            case 1:
                cadenaDiaMes = "0" + diaMes;
                break;
            case 2:
                cadenaDiaMes = "0" + diaMes;
                break;
            case 3:
                cadenaDiaMes = "0" + diaMes;
                break;
            case 4:
                cadenaDiaMes = "0" + diaMes;
                break;
            case 5:
                cadenaDiaMes = "0" + diaMes;
                break;
            case 6:
                cadenaDiaMes = "0" + diaMes;
                break;
            case 7:
                cadenaDiaMes = "0" + diaMes;
                break;
            case 8:
                cadenaDiaMes = "0" + diaMes;
                break;
            case 9:
                cadenaDiaMes = "0" + diaMes;
                break;
            default:
                cadenaDiaMes = String.valueOf(diaMes);
                break;
        }

        return cadenaDiaMes;
    }

    private int obtenerCantidadDiasDemora(LocalDate fechaActual, LocalDate fechaMaxPresentacion) {
        int cantidadDiasDemora = 0;
        if (fechaActual.isEqual(fechaMaxPresentacion)) {
            cantidadDiasDemora = 0;
        } else if (fechaActual.isBefore(fechaMaxPresentacion)) {
            cantidadDiasDemora = 0;
        } else if (fechaActual.isAfter(fechaMaxPresentacion)) {
            Period period = Period.between(fechaMaxPresentacion, fechaActual);
            cantidadDiasDemora = period.getDays();
        }

        return cantidadDiasDemora;
    }

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_EL')")
    @PutMapping("/eliminarObservacionInforme")
    public ResponseEntity<ResponseDTO> eliminarObservacionInforme(@Valid @RequestBody PgimDocumentoDTO pgimDocumentoDTO)
            throws Exception {
                
        ResponseDTO responseDTO = null;
        String mensajeError = "";
        Map<String, Object> respuestaLog = new HashMap<>();
        
        if (pgimDocumentoDTO != null) {
            if (pgimDocumentoDTO.getIdInstanciaProceso() != null) {

                Long idInstanciaProceso = pgimDocumentoDTO.getIdInstanciaProceso();
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());

            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else{
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            this.revisionInformeService.eliminarFichaObservacion(pgimDocumentoDTO, this.obtenerAuditoria());
        } catch (PgimException e) {

			// Excepcion controlada que deberá ser manejada por el frontend
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }           

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
			
        } catch (DataAccessException e) {
            mensajeError = "Ocurrió un error intentar eliminar la ficha de observacion";
            
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(String.format(mensajeError + " %s", e.getMostSpecificCause().getMessage()), e);

            responseDTO = new ResponseDTO("error", mensajeError);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO("error", e.getMessage(), 0));
        }

        responseDTO = new ResponseDTO("success", "El paso del proceso ha sido asignado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_EL')")
    @PutMapping("/eliminarAprobacionInforme")
    public ResponseEntity<?> eliminarAprobacionInforme(@Valid @RequestBody PgimDocumentoDTO pgimDocumentoDTO)
            throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();
        
        if (pgimDocumentoDTO != null) {
            if (pgimDocumentoDTO.getIdInstanciaProceso() != null) {

                Long idInstanciaProceso = pgimDocumentoDTO.getIdInstanciaProceso();
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());

            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else{
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        try {
            this.revisionInformeService.eliminarFichaAprobacion(pgimDocumentoDTO, this.obtenerAuditoria());
        } catch (PgimException e) {

			// Excepcion controlada que deberá ser manejada por el frontend
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }           

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
			
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la ficha de aprobación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la ficha de aprobación");
            respuesta.put("error", e.getMessage());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_CO')")
    @GetMapping("/obtenerObservacionInforme/{idDocumento}/{coTablaInstancia}/{idProceso}/{idFase}/{idSubcatDocumento}/{idContrato}/{descFechaPresentacionActa}")
    public ResponseEntity<?> obtenerObservacionInforme(@PathVariable Long idDocumento,
            @PathVariable Long coTablaInstancia,
            @PathVariable Long idProceso, @PathVariable Long idFase, @PathVariable Long idSubcatDocumento,
            @PathVariable Long idContrato, @PathVariable String descFechaPresentacionActa) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        RevisionInforme revisionInforme = null;

        try {
            revisionInforme = this.revisionInformeService.obtenerObservacionInforme(idDocumento, coTablaInstancia,
                    idProceso,
                    idFase, idSubcatDocumento, idContrato, descFechaPresentacionActa);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el informe de observación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el informe de observación");
            respuesta.put("error", e.getMessage());

            log.error(e.getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (revisionInforme == null) {
            mensaje = String.format("El informe de observación con el id: %d no existe en la base de datos",
                    idDocumento);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El informe de observación ha sido recuperado");
        respuesta.put("revisionInforme", revisionInforme);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_CO')")
    @GetMapping("/obtenerAprobacionInforme/{idDocumentoConformidad}/{idContrato}")
    public ResponseEntity<?> obtenerAprobacionInforme(@PathVariable Long idDocumentoConformidad,
            @PathVariable Long idContrato) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        RevisionInforme revisionInformeAprobacion = null;

        try {
            revisionInformeAprobacion = this.revisionInformeService.obtenerAprobacionInforme(idDocumentoConformidad,
                    idContrato);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el informe de aprobación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (revisionInformeAprobacion == null) {
            mensaje = String.format("El informe de aprobación con el id: %d no existe en la base de datos",
                    idDocumentoConformidad);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El informe de aprobación ha sido recuperado");
        respuesta.put("revisionInformeAprobacion", revisionInformeAprobacion);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_MO')")
    @PutMapping("/modificarObservacionInforme")
    public ResponseEntity<?> modificarObservacionInforme(
            @Valid @RequestBody RevisionInforme revisionInforme, BindingResult resultadoValidacion)
            throws Exception {

        RevisionInforme revisionInformeModificado = new RevisionInforme();
        String mensaje = "";
        Map<String, Object> respuestaLog = new HashMap<>();
        
        if (revisionInforme.getPgimDocumentoDTO() != null) {
            if (revisionInforme.getPgimDocumentoDTO().getIdInstanciaProceso() != null) {

                Long idInstanciaProceso = revisionInforme.getPgimDocumentoDTO().getIdInstanciaProceso();
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());

            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else{
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());
            
            mensaje = "Se han encontrado inconsistencias para modificar el infome de observación";
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errores.toString(), mensaje)); 
        }

        try {
            revisionInformeModificado = this.revisionInformeService.modificarObservacionInforme(revisionInforme,
                    this.obtenerAuditoria());
            
        }catch (PgimException e) {

			// Excepcion controlada que deberá ser manejada por el frontend
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }           

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
			
        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al intentar modificar el informe de observación";
            String errorMsj = e.getMostSpecificCause().getMessage();
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(errorMsj, e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errorMsj, mensaje));
            
        } catch (Exception e) {
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage())); 
    	
        }

        ResponseDTO respuesta = new ResponseDTO(TipoResultado.SUCCESS, revisionInformeModificado, "El informe de observación ha sido modificado"); 
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

    }

    @PutMapping("/subsanarTodasObservaciones")
    public ResponseEntity<ResponseDTO> subsanarTodasObservaciones(@Valid @RequestBody RevisionInforme revisionInforme, BindingResult resultadoValidacion)
            throws Exception {

        List<PgimFichaObservacionDTO> lPgimFichaObservacionDTOSubsanados = null;
        String mensaje = "";

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;
            errores = resultadoValidacion.getFieldErrors().stream().map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage())).collect(Collectors.toList());
            mensaje = "Se han encontrado inconsistencias para subsanar las observaciones";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errores.toString(), mensaje)); 
        }

        try {
            lPgimFichaObservacionDTOSubsanados = this.revisionInformeService.subsanarTodasObservaciones(revisionInforme, this.obtenerAuditoria());
            
        } catch (DataAccessException e) {
            
            mensaje = "Ocurrió un error al intentar subsanar las observaciones";
            String errorMsj = e.getMostSpecificCause().getMessage();
            log.error(errorMsj, e);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errorMsj, mensaje));

        } catch (Exception e) {
			
            log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage())); 
        
        }

        ResponseDTO respuesta = new ResponseDTO(TipoResultado.SUCCESS, lPgimFichaObservacionDTOSubsanados, "Genial, todas las observaciones han sido subsanadas"); 
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

    }

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_MO')")
    @PutMapping("/modificarAprobacionInforme")
    public ResponseEntity<?> modificarAprobacionInforme(
            @Valid @RequestBody RevisionInforme revisionInforme, BindingResult resultadoValidacion)
            throws Exception {

        String mensaje = "";
        RevisionInforme revisionInformeModificado = new RevisionInforme();
        Map<String, Object> respuestaLog = new HashMap<>();
        
        if (revisionInforme.getPgimDocumentoDTO() != null) {
            if (revisionInforme.getPgimDocumentoDTO().getIdInstanciaProceso() != null) {

                Long idInstanciaProceso = revisionInforme.getPgimDocumentoDTO().getIdInstanciaProceso();
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());

            } else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else{
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            mensaje = "Se han encontrado inconsistencias para modificar el infome de aprobación";
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errores.toString(), mensaje));
        }

        try {
            revisionInformeModificado = this.revisionInformeService.modificarAprobacionInforme(revisionInforme,
                    this.obtenerAuditoria());
        }catch (PgimException e) {
			
			// Excepcion controlada que deberá ser manejada por el frontend
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }           

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
            
        }catch (DataAccessException e) {
            mensaje = "Ocurrió un error al intentar modificar el informe de aprobación";
            String errorMsj = e.getMostSpecificCause().getMessage();
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(errorMsj, e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, errorMsj, mensaje));
            
        } catch (Exception e) {
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);
    		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage())); 
        }

        ResponseDTO respuesta = new ResponseDTO(TipoResultado.SUCCESS, revisionInformeModificado, "Genial, la aprobación del informe ha sido modificado." + 
            " Para que esta tarea se considere completada debe firmar este documento y enviar el flujo hacia la tarea “Elaborar ficha de evaluación de hechos verificados v1, MCAF y OCAF"); 
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

    }

    @GetMapping("/obtenerObservacionInformeParaCrear/{coTablaInstancia}/{idProceso}/{idFase}/{idSubcatDocumento}/{idContrato}/{idDocumentoInformeFiscaliza}")
    public ResponseEntity<?> obtenerObservacionInformeParaCrear(@PathVariable Long coTablaInstancia,
            @PathVariable Long idProceso, @PathVariable Long idFase, @PathVariable Long idSubcatDocumento,
            @PathVariable Long idContrato, @PathVariable Long idDocumentoInformeFiscaliza) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        RevisionInforme revisionInforme = new RevisionInforme();

        try {
            revisionInforme = this.revisionInformeService.obtenerObservacionInformeParaCrear(
                    coTablaInstancia, idProceso,
                    idFase, idSubcatDocumento, idContrato, idDocumentoInformeFiscaliza);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la lista ficha de observación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (revisionInforme == null) {
            mensaje = String.format("La lista ficha de observación con el id: %d no existe en la base de datos",
                    coTablaInstancia);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La lista ficha de observación ha sido recuperada");
        respuesta.put("lPgimFichaObservacionDTO", revisionInforme.getlPgimFichaObservacionDTO());
        respuesta.put("revisionInforme", revisionInforme);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @GetMapping("/obtenerFichaAprobacionParaCrear/{idContrato}/{idDocumentoInformeFiscaliza}")
    public ResponseEntity<?> obtenerFichaAprobacionParaCrear(@PathVariable Long idContrato,
            @PathVariable Long idDocumentoInformeFiscaliza) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        RevisionInforme revisionInforme = new RevisionInforme();

        try {
            revisionInforme = this.revisionInformeService.obtenerFichaAprobacionParaCrear(idContrato,
                    idDocumentoInformeFiscaliza);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la lista ficha de observación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la lista ficha de observación");
            respuesta.put("error", e.getMessage());
            log.error(e.getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (revisionInforme == null) {
            mensaje = String.format("La lista ficha de observación con el id: %d no existe en la base de datos",
                    idContrato);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La lista ficha de observación ha sido recuperada");
        respuesta.put("revisionInforme", revisionInforme);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('sp-ri-conf_MO')")
    @PostMapping("/crearJustificacionInforme")
    public ResponseEntity<ResponseDTO> crearJustificacionInforme(
            @RequestPart("documentoPadreDTO") PgimDocumentoDTO pgimDocumentoPadreDTO,
            @RequestPart("documentoDTO") PgimDocumentoDTO pgimDocumentoDTO,
            @RequestPart("instanciaProcesDTO") PgimInstanciaProcesDTO pgimInstanciaProcesDTO,
            @RequestPart("fileDocumento") MultipartFile fileDocumento) throws PgimException, IOException, Exception {

        ResponseEntity<ResponseDTO> resultado = null;

        try {
            resultado = this.revisionInformeService.crearJustificacionInforme(pgimDocumentoPadreDTO, pgimDocumentoDTO,
                    pgimInstanciaProcesDTO, fileDocumento, this.obtenerAuditoria());
        } catch (PgimException e) {
        	// Excepcion controlada que deberá ser manejada por el frontend
        	log.error(e.getMessage(), e);
        	
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		            
        }
        catch (final Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar adjuntar o reemplazar el informe de justificación: "+ e.getMessage()));
        }

        return resultado;
    }

    /**
     * Permite realizar las validaciones previas necesarias, para generar un informe
     * de aprobación.
     * 
     * @param idDocumento
     * @return
     */
    @GetMapping("/validacionPreviaInformeAprobacion/{idDocumento}/{coTablaInstancia}/{idProceso}/{idFase}/{idSubcatDocumento}")
    public ResponseEntity<Map<String, Object>> validacionPreviaInformeAprobacion(
            @PathVariable("idDocumento") Long idDocumento,
            @PathVariable("coTablaInstancia") Long coTablaInstancia, @PathVariable("idProceso") Long idProceso,
            @PathVariable("idFase") Long idFase, @PathVariable("idSubcatDocumento") Long idSubcatDocumento) {

        Map<String, Object> respuesta = new HashMap<>();
        String mensajeValidacion = this.revisionInformeService.validacionPreviaInformeAprobacion(idDocumento,
                coTablaInstancia, idProceso,
                idFase, idSubcatDocumento);
        respuesta.put("mensaje", mensajeValidacion);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerActaSupervisionFinal/{coTablaInstancia}/{idProceso}/{idFase}/{idSubcatDocumento}")
    public ResponseEntity<?> obtenerActaSupervisionFinal(@PathVariable Long coTablaInstancia,
            @PathVariable Long idProceso, @PathVariable Long idFase, @PathVariable Long idSubcatDocumento)
            throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimDocumentoDTO pgimDocumentoDTO = null;

        try {
            pgimDocumentoDTO = this.revisionInformeService.obtenerDocumentoMasReciente(coTablaInstancia, idProceso,
                    idFase, ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION_FINAL);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el acta de fiscalización final");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimDocumentoDTO == null) {
            mensaje = String.format("El acta de fiscalización final no existe en la base de datos");
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El acta de fiscalización ha sido recuperado");
        respuesta.put("pgimDocumentoDTO", pgimDocumentoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

}