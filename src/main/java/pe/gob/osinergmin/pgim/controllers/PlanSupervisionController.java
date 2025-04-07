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
import pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPlanSupervision;
import pe.gob.osinergmin.pgim.services.PlanSupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los planes
 * de supervisión.
 * 
 * @descripción: Plan supervision
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/planessupervisiones")
public class PlanSupervisionController extends BaseController {

    @Autowired
    private PlanSupervisionService planSupervisionService;

    @PostMapping("/listarPlanSupervision")
    public ResponseEntity<Page<PgimPlanSupervisionDTO>> listarPlanSupervision(
            @RequestBody PgimPlanSupervisionDTO filtroEmpresaSupervisora, Pageable paginador) {

        Page<PgimPlanSupervisionDTO> lPgimPlanSupervisionDTO = this.planSupervisionService
                .listarPlanSupervision(filtroEmpresaSupervisora, paginador);
        return new ResponseEntity<Page<PgimPlanSupervisionDTO>>(lPgimPlanSupervisionDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerPlanSupervisionPorId/{idPlanSupervision}")
    public ResponseEntity<?> obtenerPlanSupervisionPorId(@PathVariable Long idPlanSupervision) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimPlanSupervisionDTO pgimPlanSupervisionDTO = null;

        try {
            pgimPlanSupervisionDTO = this.planSupervisionService.obtenerPlanSupervisionPorId(idPlanSupervision);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el plan de supervisión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimPlanSupervisionDTO == null) {
            mensaje = String.format("El plan de supervisión con el id: %d no existe en la base de datos",
                    idPlanSupervision);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El plan de supervisión ha sido recuperado");
        respuesta.put("pgimPlanSupervisionDTO", pgimPlanSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimPlanSupervisionDTO> lPgimPlanSupervisionDTONuAnio = null;
        // PgimPlanSupervisionDTO maxAnuAnio = null;
        try {
            lPgimPlanSupervisionDTONuAnio = this.planSupervisionService.filtrarPorAnio(ConstantesUtil.PARAM_NU_ANIO);

            // maxAnuAnio = this.planSupervisionService.obtenerMaxNuAnio();
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de filtrar el año");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimPlanSupervisionDTONuAnio", lPgimPlanSupervisionDTONuAnio);
        // respuesta.put("maxAnuAnio", maxAnuAnio);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('pl001_MO')")
    @PostMapping("/crearPlanSupervision")
    public ResponseEntity<?> crearPlanSupervision(@Valid @RequestBody PgimPlanSupervisionDTO pgimPlanSupervisionDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPlanSupervisionDTO pgimPlanSupervisionDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el plan de supervisión");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPlanSupervisionDTOCreado = this.planSupervisionService.crearPlanSupervision(pgimPlanSupervisionDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el plan de supervisión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El plan de supervisión ha sido creada");
        respuesta.put("pgimPlanSupervisionDTOCreado", pgimPlanSupervisionDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('pl001_MO')")
    @PutMapping("/modificarPlanSupervision")
    public ResponseEntity<?> modificarPlanSupervision(@Valid @RequestBody PgimPlanSupervisionDTO pgimPlanSupervisionDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPlanSupervision pgimPlanSupervisionActual = null;
        PgimPlanSupervisionDTO pgimPlanSupervisionDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el plan de supervisión");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPlanSupervisionActual = this.planSupervisionService
                    .getByIdPlanSupervision(pgimPlanSupervisionDTO.getIdPlanSupervision());

            if (pgimPlanSupervisionActual == null) {
                mensaje = String.format(
                        "El plan de supervisión %s que intenta actualizar no existe en la base de datos",
                        pgimPlanSupervisionDTO.getIdPlanSupervision());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el plan de supervisión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimPlanSupervisionDTOModificado = this.planSupervisionService.modificarPlanSupervision(
                    pgimPlanSupervisionDTO, pgimPlanSupervisionActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el plan de supervisión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El plan de supervisión ha sido modificada");
        respuesta.put("pgimPlanSupervisionDTOModificado", pgimPlanSupervisionDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('pl001_EL')")
    @DeleteMapping("/eliminarPlanSupervision/{idPlanSupervision}")
    public ResponseEntity<?> eliminarPlanSupervision(@PathVariable Long idPlanSupervision) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimPlanSupervision pgimPlanSupervisionActual = null;

        try {
            pgimPlanSupervisionActual = this.planSupervisionService.getByIdPlanSupervision(idPlanSupervision);

            if (pgimPlanSupervisionActual == null) {
                mensaje = String.format("El plan de supervisión %s que intenta eliminar no existe en la base de datos",
                        idPlanSupervision);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el plan de supervisión a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.planSupervisionService.eliminarPlanSupervision(pgimPlanSupervisionActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar el plan de supervisión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/existeNuAnio/{idPlanSupervision}/{nuAnio}")
    public ResponseEntity<?> existeNuAnio(@PathVariable Long idPlanSupervision, @PathVariable Long nuAnio) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimPlanSupervisionDTO> lPgimPlanSupervisionDTO = null;

        if (idPlanSupervision == 0) {
            idPlanSupervision = null;
        }

        try {

            lPgimPlanSupervisionDTO = this.planSupervisionService.existeNuAnio(idPlanSupervision, nuAnio);

        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar la verificar si ya existe el año del plan de supervisión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia del año del plan de supervisión");

        respuesta.put("lPgimPlanSupervisionDTO", lPgimPlanSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
