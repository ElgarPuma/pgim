package pe.gob.osinergmin.pgim.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimFaseProceso;
import pe.gob.osinergmin.pgim.services.FaseProcesoService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a las
 * fases del proceso
 * 
 * @descripción: Fases del proceso
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 14/12/2023
 */
@RestController
@Slf4j
@RequestMapping("/faseProcesos")
public class FaseProcesoController extends BaseController {

    @Autowired
    private FaseProcesoService faseProcesoService;

    @PreAuthorize("hasAnyAuthority('cfg011_AC')")
    @GetMapping("/listarFasesProceso/{idProceso}")
    public ResponseEntity<List<PgimFaseProcesoDTO>> listarProcesos(@PathVariable Long idProceso,
            @RequestParam(name = "sort", defaultValue = "idFaseProceso,asc") String sort) throws Exception {

        String[] camposOrdenamiento = sort.split(",");
        String campo = camposOrdenamiento[0];
        Sort.Direction direccion = Sort.Direction.fromString(camposOrdenamiento[1]);

        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = this.faseProcesoService.listarFasesProceso(idProceso, campo,
                direccion);

        return new ResponseEntity<List<PgimFaseProcesoDTO>>(lPgimFaseProcesoDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('cfg011_MO')")
    @PostMapping("/crearFaseProceso")
    public ResponseEntity<?> crearFaseProceso(@Valid @RequestBody PgimFaseProcesoDTO pgimFaseProcesoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimFaseProcesoDTO pgimFaseProcesoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear una fase del proceso");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimFaseProcesoDTOCreado = this.faseProcesoService.crearFaseProceso(pgimFaseProcesoDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear una fase del proceso");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La fase del proceso ha sido creada");
        respuesta.put("pgimFaseProcesoDTOCreado", pgimFaseProcesoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('cfg011_MO')")
    @PutMapping("/modificarFaseProceso")
    public ResponseEntity<?> modificarFaseProceso(@Valid @RequestBody PgimFaseProcesoDTO pgimFaseProcesoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimFaseProceso pgimFaseProcesoActual = null;
        PgimFaseProcesoDTO pgimFaseProcesoDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar una fase del proceso");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimFaseProcesoActual = this.faseProcesoService.getByIdFaseProceso(pgimFaseProcesoDTO.getIdFaseProceso());

            if (pgimFaseProcesoActual == null) {
                mensaje = String.format(
                        "La fase del proceso %s que intenta actualizar no existe en la base de datos",
                        pgimFaseProcesoDTO.getIdFaseProceso());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la fase del proceso a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimFaseProcesoDTOModificada = this.faseProcesoService.modificarFaseProceso(pgimFaseProcesoDTO,
                    pgimFaseProcesoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la fase del proceso");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La fase del proceso ha sido modificada");
        respuesta.put("pgimFaseProcesoDTOModificada", pgimFaseProcesoDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/validarEliminacionFaseProceso/{idFaseProceso}")
    public ResponseEntity<ResponseDTO> validarEliminacionFaseProceso(@PathVariable Long idFaseProceso) throws Exception {
        
		String mensaje;
        PgimFaseProceso pgimFaseProcesoActual = null;

        try {
            pgimFaseProcesoActual = this.faseProcesoService.getByIdFaseProceso(idFaseProceso);

            if (pgimFaseProcesoActual == null) {
                mensaje = String.format("La fase %s que intenta validar no existe en la base de datos", idFaseProceso);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            }

        } catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar la fase a actualizar", e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));

        }

        try {
            this.faseProcesoService.validarEliminacionFaseProceso(pgimFaseProcesoActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar validar la eliminación de la fase del proceso", e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));

        } catch (final PgimException e) {

            log.error(e.getMensaje(), e);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), e.getValor()));
            
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, "La fase del proceso ha sido eliminado"));
    }

    /**
     * Permite eliminar logicamente la fase de flujos de trabajo del proceso
     * 
     * @param idFaseProceso
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('cfg011_EL')")
    @DeleteMapping("/eliminarFaseProceso/{idFaseProceso}")
    public ResponseEntity<ResponseDTO> eliminarFaseProceso(@PathVariable Long idFaseProceso) throws Exception {

        ResponseDTO responseDTO = null;
        PgimFaseProceso pgimFaseProcesoActual = null;
        PgimFaseProcesoDTO pgimFaseProcesoDTOCU = null;
        String mensaje;

        try {
            pgimFaseProcesoActual = this.faseProcesoService.getByIdFaseProceso(idFaseProceso);

            if (pgimFaseProcesoActual == null) {
                mensaje = String.format(
                        "La fase del proceso con id %s que intenta eliminar no existe en la base de datos",
                        idFaseProceso);

                responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error intentar eliminar la fase del proceso: " + e.getMessage();
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR,
                    "Ocurrió un error al intentar eliminar la fase del proceso");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            this.faseProcesoService.eliminarFaseProceso(pgimFaseProcesoActual, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al intentar eliminar la fase del proceso: " + e.getMessage();
            log.error(mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (Exception e) {
            mensaje = "Ocurrió un error al intentar eliminar la fase del proceso: " + e.getMessage();
            log.error(mensaje);
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        mensaje = "La fase del proceso ha sido eliminado";
        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimFaseProcesoDTOCU, mensaje);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
