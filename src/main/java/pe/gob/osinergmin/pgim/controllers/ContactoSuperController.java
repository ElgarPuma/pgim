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
import pe.gob.osinergmin.pgim.dtos.PgimContactoSuperDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContactoSuper;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.services.ContactoSuperService;
import pe.gob.osinergmin.pgim.services.EmpresaSupervisoraService;
import pe.gob.osinergmin.pgim.services.PersonaService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas al contacto de la empresa supervisora
 * 
 * @descripción: Contacto empresa supervisora
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 06/02/2022
 */
@RestController
@Slf4j
@RequestMapping("/contactosupervisora")
public class ContactoSuperController extends BaseController {

    @Autowired
    private ContactoSuperService contactoSuperService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private EmpresaSupervisoraService empresaSupervisoraService;

    @PostMapping("/listarContactosEmpresaSuperv")
    public ResponseEntity<Page<PgimContactoSuperDTO>> listarContactosEmpresaSuperv(
            @RequestBody PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO, Pageable paginador) {

        Page<PgimContactoSuperDTO> lPgimContactoSuperDTO = this.contactoSuperService.listarContactosEmpresaSuperv(
                pgimEmpresaSupervisoraDTO.getIdEmpresaSupervisora(), paginador);
        return new ResponseEntity<Page<PgimContactoSuperDTO>>(lPgimContactoSuperDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerContactoSuperPorId/{idContactoSupervisora}")
    public ResponseEntity<?> obtenerContactoSuperPorId(@PathVariable Long idContactoSupervisora) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimContactoSuperDTO pgimContactoSuperDTO = null;

        try {
            pgimContactoSuperDTO = this.contactoSuperService.obtenerContactoSuperPorId(idContactoSupervisora);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el contacto de la empresa supervisora");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimContactoSuperDTO == null) {
            mensaje = String.format("El contacto de la empresa supervisora con el id: %d no existe en la base de datos",
                    idContactoSupervisora);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El contacto de la empresa supervisora ha sido recuperado");
        respuesta.put("pgimContactoSuperDTO", pgimContactoSuperDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @PostMapping("/crearContactoSuper")
    public ResponseEntity<?> crearContactoSuper(@Valid @RequestBody PgimContactoSuperDTO pgimContactoSuperDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimContactoSuperDTO pgimContactoSuperDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje",
                    "Se han encontrado inconsistencias para crear el contacto de la empresa supervisora");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimContactoSuperDTOCreado = this.contactoSuperService.crearContactoSuper(pgimContactoSuperDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el contacto de la empresa supervisora");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El contacto de la empresa supervisora ha sido creado");
        respuesta.put("pgimContactoSuperDTO", pgimContactoSuperDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarContactoSuper")
    public ResponseEntity<?> modificarContactoSuper(
            @Valid @RequestBody PgimContactoSuperDTO pgimContactoSuperDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimPersona pgimPersonaActual = null;
        PgimEmpresaSupervisora pgimEmpresaSupervisoraActual = null;
        PgimContactoSuperDTO pgimContactoSuperDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje",
                    "Se han encontrado inconsistencias para modificar el contacto de la empresa supervisora");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonaActual = this.personaService.getByIdPersona(pgimContactoSuperDTO.getIdPersona());

            if (pgimPersonaActual == null) {
                mensaje = String.format("La persona %s que intenta actualizar no existe en la base de datos",
                        pgimContactoSuperDTO.getIdPersona());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la persona a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimEmpresaSupervisoraActual = empresaSupervisoraService
                    .getByIdEmpresaSupervisora(pgimContactoSuperDTO.getIdEmpresaSupervisora());

            if (pgimEmpresaSupervisoraActual == null) {
                mensaje = String.format(
                        "La empresa supervisora %s que intenta actualizar no existe en la base de datos",
                        pgimContactoSuperDTO.getIdPersona());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar La empresa supervisora a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimContactoSuperDTOModificado = this.contactoSuperService.modificarContactoSuper(pgimContactoSuperDTO,
                    pgimPersonaActual, pgimEmpresaSupervisoraActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el contacto de la empresa supervisora");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El contacto de la empresa supervisora ha sido modificado");
        respuesta.put("pgimContactoSuperDTO", pgimContactoSuperDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarContactoSuper/{idContactoSupervisora}")
    public ResponseEntity<?> eliminarContactoSuper(@PathVariable Long idContactoSupervisora) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimContactoSuper pgimContactoSuperActual = null;

        try {
            pgimContactoSuperActual = this.contactoSuperService.getByIdContactoSuper(idContactoSupervisora);

            if (pgimContactoSuperActual == null) {
                mensaje = String.format(
                        "El contacto de la empresa supervisora %s que intenta eliminar no existe en la base de datos",
                        idContactoSupervisora);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error intentar recuperar el contacto de la empresa supervisora a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.contactoSuperService.eliminarContactoSuper(pgimContactoSuperActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar el contacto de la empresa supervisora");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
