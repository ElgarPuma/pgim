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
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContactoAgenteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimContactoAgente;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.services.AgenteSupervisadoService;
import pe.gob.osinergmin.pgim.services.ContactoAgenteService;
import pe.gob.osinergmin.pgim.services.PersonaService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas al contacto agente supervisado
 * 
 * @descripción: Contacto agente
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 09/04/2021
 */

@RestController
@Slf4j
@RequestMapping("/contactoagente")
public class ContactoAgenteController extends BaseController {

	@Autowired
    private ContactoAgenteService contactoAgenteService;
	
	@Autowired
    private PersonaService personaService;
	
	@Autowired
    private AgenteSupervisadoService agenteSupervisadoService;
	
	@PostMapping("/filtrar")
    public ResponseEntity<Page<PgimContactoAgenteDTO>> listarContactoAgente(
		@RequestBody PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO, Pageable paginador) {

        Page<PgimContactoAgenteDTO> lPgimContactoAgenteDTO = this.contactoAgenteService.filtrar(
        		pgimAgenteSupervisadoDTO.getIdAgenteSupervisado(), paginador);
        return new ResponseEntity<Page<PgimContactoAgenteDTO>>(lPgimContactoAgenteDTO, HttpStatus.OK);
	}
	
	@PostMapping("/crearContactoAgente")
    public ResponseEntity<?> crearContactoAgente(
            @Valid @RequestBody PgimContactoAgenteDTO pgimContactoAgenteDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimContactoAgenteDTO pgimContactoAgenteDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el contacto del agente");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimContactoAgenteDTOCreado = contactoAgenteService.crearContactoAgente(pgimContactoAgenteDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el contacto del agente");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El contacto del agente ha sido creado");
        respuesta.put("pgimContactoAgenteDTO", pgimContactoAgenteDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarContactoAgente")
    public ResponseEntity<?> modificarContactoAgente(
            @Valid @RequestBody PgimContactoAgenteDTO pgimContactoAgenteDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimPersona pgimPersonaActual = null;
        PgimAgenteSupervisado pgimAgenteSupervisadoActual = null;
        PgimContactoAgenteDTO pgimContactoAgenteDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el contacto del agente");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimPersonaActual = this.personaService.getByIdPersona(pgimContactoAgenteDTO.getIdPersona());

            if (pgimPersonaActual == null) {
                mensaje = String.format("La persona %s que intenta actualizar no existe en la base de datos",
                		pgimContactoAgenteDTO.getIdPersona());
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
        	pgimAgenteSupervisadoActual = agenteSupervisadoService.getByIdAgenteSupervisado(pgimContactoAgenteDTO.getIdAgenteSupervisado());

            if (pgimAgenteSupervisadoActual == null) {
                mensaje = String.format("El agente fiscalizado %s que intenta actualizar no existe en la base de datos",
                		pgimContactoAgenteDTO.getIdPersona());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el agente fiscalizado a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        try {
        	pgimContactoAgenteDTOModificado = this.contactoAgenteService.modificarContactoAgente(pgimContactoAgenteDTO,
            		pgimPersonaActual, pgimAgenteSupervisadoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el contacto del agente");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El contacto del agente ha sido modificado");
        respuesta.put("pgimContactoAgenteDTO", pgimContactoAgenteDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarContactoAgente/{idContactoAgente}")
    public ResponseEntity<?> eliminarContactoAgente(@PathVariable Long idContactoAgente) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimContactoAgente pgimContactoAgenteActual = null;

        try {
        	pgimContactoAgenteActual = this.contactoAgenteService.getByIdContactoAgente(idContactoAgente);

            if (pgimContactoAgenteActual == null) {
                mensaje = String.format("El contacto del agente %s que intenta eliminar no existe en la base de datos",
                		idContactoAgente);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el contacto del agente a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.contactoAgenteService.eliminarContactoAgente(pgimContactoAgenteActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar el contacto del agente");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/obtenerContactoAgentePorId/{idContactoAgente}")
    public ResponseEntity<?> obtenerContactoAgentePorId(@PathVariable Long idContactoAgente) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimContactoAgenteDTO pgimContactoAgenteDTO = null;

        try {
        	pgimContactoAgenteDTO = this.contactoAgenteService.obtenerContactoAgentePorId(idContactoAgente);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el contacto del agente");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimContactoAgenteDTO == null) {
            mensaje = String.format("El contacto del agente con el id: %d no existe en la base de datos", idContactoAgente);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El contacto del agente ha sido recuperado");
        respuesta.put("pgimContactoAgenteDTO", pgimContactoAgenteDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }
}
