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
import pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;
import pe.gob.osinergmin.pgim.services.RolProcesoService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los
 * roles del proceso
 * 
 * @descripción: Roles del proceso
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 18/12/2023
 */
@RestController
@Slf4j
@RequestMapping("/rolesProceso")
public class RolProcesoController extends BaseController {
    
    @Autowired
    private RolProcesoService rolProcesoService;

    @PreAuthorize("hasAnyAuthority('cfg012_AC')")
    @GetMapping("/listarRolesProceso/{idProceso}")
    public ResponseEntity<List<PgimRolProcesoDTO>> listarRolesProceso(@PathVariable Long idProceso, @RequestParam(name = "sort", defaultValue = "coRolProceso,asc") String sort) throws Exception {

        String[] camposOrdenamiento = sort.split(",");
        String campo = camposOrdenamiento[0];
        Sort.Direction direccion = Sort.Direction.fromString(camposOrdenamiento[1]);

        List<PgimRolProcesoDTO> lPgimFaseProcesoDTO = this.rolProcesoService.obtenerRolesProceso(idProceso, campo, direccion);

        return new ResponseEntity<List<PgimRolProcesoDTO>>(lPgimFaseProcesoDTO, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyAuthority('cfg012_MO')")
    @PostMapping("/crearRolProceso")
    public ResponseEntity<?> crearRolProceso(@Valid @RequestBody PgimRolProcesoDTO pgimRolProcesoDTO, BindingResult resultadoValidacion) throws Exception {

        PgimRolProcesoDTO pgimRolProcesoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el rol del proceso");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimRolProcesoDTOCreado = this.rolProcesoService.crearRolProceso(pgimRolProcesoDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el rol del proceso");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El rol del proceso ha sido creada");
        respuesta.put("pgimRolProcesoDTOCreado", pgimRolProcesoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('cfg012_MO')")
    @PutMapping("/modificarRolProceso")
    public ResponseEntity<?> modificarRolProceso(@Valid @RequestBody PgimRolProcesoDTO pgimRolProcesoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimRolProceso pgimRolProcesoActual = null;
        PgimRolProcesoDTO pgimRolProcesoDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el rol del proceso");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimRolProcesoActual = this.rolProcesoService.getByIdRolProceso(pgimRolProcesoDTO.getIdRolProceso());

            if (pgimRolProcesoActual == null) {
                mensaje = String.format(
                        "El rol del proceso %s que intenta actualizar no existe en la base de datos",
                        pgimRolProcesoDTO.getIdRolProceso());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el rol del proceso a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimRolProcesoDTOModificada = this.rolProcesoService.modificarRolProceso(pgimRolProcesoDTO, pgimRolProcesoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el rol del proceso");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El rol del proceso ha sido modificada");
        respuesta.put("pgimRolProcesoDTOModificada", pgimRolProcesoDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/validarEliminacionRolProceso/{idRolProceso}")
    public ResponseEntity<ResponseDTO> validarEliminacionRolProceso(@PathVariable Long idRolProceso) throws Exception {
        
		String mensaje;
        PgimRolProceso pgimRolProcesoActual = null;

        try {
            pgimRolProcesoActual = this.rolProcesoService.getByIdRolProceso(idRolProceso);

            if (pgimRolProcesoActual == null) {
                mensaje = String.format("El rol %s que intenta validar no existe en la base de datos", idRolProceso);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            }

        } catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar el rol a actualizar", e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));

        }

        try {
            this.rolProcesoService.validarEliminacionRolProceso(pgimRolProcesoActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar validar la eliminación de el rol del proceso", e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));

        } catch (final PgimException e) {

            log.error(e.getMensaje(), e);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), e.getValor()));
            
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, "El rol del proceso ha sido eliminado"));
    }

    /**
     * Permite eliminar logicamente el rol de flujos de trabajo del proceso
     * 
     * @param idRolProceso
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('cfg012_EL')")
    @DeleteMapping("/eliminarRolProceso/{idRolProceso}")
    public ResponseEntity<ResponseDTO> eliminarRolProceso(@PathVariable Long idRolProceso) throws Exception {

        ResponseDTO responseDTO = null;
        PgimRolProceso pgimRolProcesoActual = null;
        PgimRolProcesoDTO pgimRolProcesoDTOCU = null;
        String mensaje;

        try {
            pgimRolProcesoActual = this.rolProcesoService.getByIdRolProceso(idRolProceso);

            if (pgimRolProcesoActual == null) {
                mensaje = String.format(
                        "El rol del proceso con id %s que intenta eliminar no existe en la base de datos",
                        idRolProceso);

                responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error intentar eliminar el rol del proceso: " + e.getMessage();
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR,
                    "Ocurrió un error al intentar eliminar el rol del proceso");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            this.rolProcesoService.eliminarRolProceso(pgimRolProcesoActual, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al intentar eliminar el rol del proceso: " + e.getMessage();
            log.error(mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (Exception e) {
            mensaje = "Ocurrió un error al intentar eliminar el rol del proceso: " + e.getMessage();
            log.error(mensaje);
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        mensaje = "El rol del proceso ha sido eliminado";
        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimRolProcesoDTOCU, mensaje);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
