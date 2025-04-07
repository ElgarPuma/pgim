package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimHistoriaAsUmDTO;
import pe.gob.osinergmin.pgim.services.HistoriaAsUmService;


/**
 * Controlador del componente historial de agentes supervisados para las unidades mineras, en el portal se encuentra en la ruta: info maestra/unidad minera
 * este componente contiene los metodos ver el historial de agentes supervisados en las unidades mineras.
 * contiene los siguientes metodos:
 * listar
 * crearHistoriaASUM
 * validarFeInicioTitularidad
 *
 * @descripción: Historia AS UM
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 */
@RestController
@Slf4j
@RequestMapping("/historiasasumineras")
public class HistoriaAsUmController extends BaseController {

	@Autowired
	private HistoriaAsUmService historiaAsUmService;

	/**
	 * Permite listar el historial de registro de titulares de la unidad minera.
	 * 
	 * @param idUnidadMinera Identificador interno de la unidad minera.
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('um-titular_AC')")
	@GetMapping("/listar/historia/{idUnidadMinera}")
	public ResponseEntity<List<PgimHistoriaAsUmDTO>> listar(@PathVariable Long idUnidadMinera) {

		List<PgimHistoriaAsUmDTO> history = new ArrayList<>();

		history = historiaAsUmService.listarHistoriasAS(idUnidadMinera);

		if (history == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(history);
	}

	/**
	 * 
	 * @param pgimHistoriaAsUmDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-titular_MO')")
	@PostMapping("/crearHistoriaASUM")
	public ResponseEntity<?> crearHistoriaASUM(@Valid @RequestBody PgimHistoriaAsUmDTO pgimHistoriaAsUmDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimHistoriaAsUmDTO pgimHistoriaAsUmDTOCreada = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear un agente fiscalizado");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimHistoriaAsUmDTOCreada = this.historiaAsUmService.crearHistoriaAsUm(pgimHistoriaAsUmDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar cambiar el agente fiscalizado de la unidad fiscalizada");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El agente fiscalizado de la unidad fiscalizada ha sido cambiado");
		respuesta.put("pgimHistoriaAsUmDTO", pgimHistoriaAsUmDTOCreada);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Esta metodo permite validar la fecha de inicio del titular que sea posterior al 
	 * anterior titular de agente supervisado de la unidad minera
	 * @param idHistoriaAsUm
	 * @return
	 */
	@GetMapping("/validarFeInicioTitularidad/{idHistoriaAsUm}")
	public ResponseEntity<?> validarFeInicioTitularidad(@PathVariable Long idHistoriaAsUm) {
		String mensaje = "";
		PgimHistoriaAsUmDTO historiaAsUmDTO = null;

		Map<String, Object> respuesta = new HashMap<>();

		if (idHistoriaAsUm == 0) {
			idHistoriaAsUm = null;
		}

		try {
			historiaAsUmDTO = this.historiaAsUmService.validarFeInicioTitularidad(idHistoriaAsUm);
			
			mensaje = "Si fue posible validar la fecha de inicio de titularidad";
		} catch (DataAccessException e) {
			mensaje = "Ocurrió un error al verificar la validacion de la fecha de inicio de titularidad";
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", mensaje);
        respuesta.put("lPgimHistoriaAsUmDTO", historiaAsUmDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
}