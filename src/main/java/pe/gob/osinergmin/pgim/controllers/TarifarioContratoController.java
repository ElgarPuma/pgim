package pe.gob.osinergmin.pgim.controllers;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTO;
import pe.gob.osinergmin.pgim.dtos.Tarifario;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioContrato;
import pe.gob.osinergmin.pgim.services.MotivoSupervisionService;
import pe.gob.osinergmin.pgim.services.SubTipoSupervisionService;
import pe.gob.osinergmin.pgim.services.TarifarioContratoService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los tarifarios
 * 
 * @descripción: Tarifario contrato
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 22/10/2020
 */
@RestController
@Slf4j
@RequestMapping("/tarifariocontrato")
public class TarifarioContratoController  extends BaseController {

	@Autowired
	private TarifarioContratoService tarifarioContratoService;

	@Autowired
	private SubTipoSupervisionService subTipoSupervisionService;
	
	@Autowired
	private MotivoSupervisionService motivoSupervisionService;
	
	@PreAuthorize("hasAnyAuthority('co-tarifa_AC')")
	@GetMapping("/listar/{idContrato}")
	public ResponseEntity<?> listarTarifarioContrato(@PathVariable Long idContrato) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimTarifarioContratoDTO> lPgimTarifarioContratoDTO = null;

		try {
			lPgimTarifarioContratoDTO = this.tarifarioContratoService.listarTarifarioContrato(idContrato);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los tarifarios");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los tarifarios");
		respuesta.put("lPgimTarifarioContratoDTO", lPgimTarifarioContratoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('co-tarifa_IN')")
    @PutMapping("/crearTarifarioContrato")
    public ResponseEntity<?> crearTarifarioContrato(
            @Valid @RequestBody Tarifario tarifario, BindingResult resultadoValidacion)
            throws Exception {

    	Tarifario tarifarioCreado = new Tarifario();

        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el tarifario contrato");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	tarifarioCreado = tarifarioContratoService.crearTarifario(tarifario, 
        			this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el tarifario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El tarifario contrato ha sido creado");
        respuesta.put("tarifarioCreado", tarifarioCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguracionesGenerales/{idEspecialidad}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idEspecialidad) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimSubtipoSupervisionDTO> lPgimSubTipoSupervisionDTO = null;
        List<PgimMotivoSupervisionDTO> lPgimMotivoSupervisionDTO = null;
        
        try {
        	lPgimSubTipoSupervisionDTO = subTipoSupervisionService.obtenerSubTipoSupervisionPorIdEspecialidad(idEspecialidad);
        	lPgimMotivoSupervisionDTO = motivoSupervisionService.obtenerMotivoSupervision();

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimSubTipoSupervisionDTO", lPgimSubTipoSupervisionDTO);
        respuesta.put("lPgimMotivoSupervisionDTO", lPgimMotivoSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyAuthority('co-tarifa_CO')")
    @GetMapping("/obtenerTarifarioContrato/{idTarifarioContrato}")
    public ResponseEntity<?> obtenerTarifarioContrato(@PathVariable Long idTarifarioContrato) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        Tarifario tarifario = null;

        try {
        	tarifario = this.tarifarioContratoService.obtenerTarifario(idTarifarioContrato);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el tarifario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tarifario == null) {
            mensaje = String.format("El tarifario con el id: %d no existe en la base de datos", idTarifarioContrato);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El tarifario ha sido recuperado");
        respuesta.put("tarifario", tarifario);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }
    
    @PreAuthorize("hasAnyAuthority('co-tarifa_MO')")
    @PutMapping("/modificarTarifarioContrato")
    public ResponseEntity<?> modificarTarifarioContrato(
            @Valid @RequestBody Tarifario tarifario, BindingResult resultadoValidacion)
            throws Exception {

    	Tarifario tarifarioModificado = new Tarifario();

        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el tarifario contrato");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	tarifarioModificado = tarifarioContratoService.modificarTarifario(tarifario, 
        			this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el tarifario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El tarifario contrato ha sido modificado");
        respuesta.put("tarifarioModificado", tarifarioModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAnyAuthority('co-tarifa_EL')")
    @DeleteMapping("/eliminarTarifarioContrato/{idTarifarioContrato}")
    public ResponseEntity<?> eliminarTarifarioContrato(@PathVariable Long idTarifarioContrato) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimTarifarioContrato pgimTarifarioContratoActual = null;

        try {
        	pgimTarifarioContratoActual = this.tarifarioContratoService.getByIdTarifarioContrato(idTarifarioContrato);

            if (pgimTarifarioContratoActual == null) {
                mensaje = String.format("El tarifario contrato %s que intenta eliminar no existe en la base de datos",
                		idTarifarioContrato);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el tarifario a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.tarifarioContratoService.eliminarTarifario(pgimTarifarioContratoActual
            		, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar el tarifario contrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @PutMapping("/validarCrearTarifarioContrato")
    public ResponseEntity<?> validarCrearTarifarioContrato(
            @Valid @RequestBody Tarifario tarifario, BindingResult resultadoValidacion)
            throws Exception {

    	List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO = new LinkedList<PgimTarifarioReglaDTO>();

        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para validar la creación del tarifario contrato");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	lPgimTarifarioReglaDTO = tarifarioContratoService.validarCrearTarifario(tarifario);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar validar la creación del tarifario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se valido la creación del tarifario");
        respuesta.put("lPgimTarifarioReglaDTO", lPgimTarifarioReglaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    @PutMapping("/validarModificarTarifarioContrato")
    public ResponseEntity<?> validarModificarTarifarioContrato(
            @Valid @RequestBody Tarifario tarifario, BindingResult resultadoValidacion)
            throws Exception {

    	List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO = new LinkedList<PgimTarifarioReglaDTO>();
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para validar la modificación del tarifario contrato");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	lPgimTarifarioReglaDTO = tarifarioContratoService.validarModificarTarifario(tarifario);
        } catch (DataAccessException e) {
        	respuesta.put("mensaje", "Ocurrió un error al intentar validar la modificación del tarifario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se valido la modificación del tarifario");
        respuesta.put("lPgimTarifarioReglaDTO", lPgimTarifarioReglaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
}
