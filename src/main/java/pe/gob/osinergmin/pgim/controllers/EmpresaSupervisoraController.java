package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
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
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;
import pe.gob.osinergmin.pgim.services.EmpresaSupervisoraService;

/**
 * Controlador del componente empresas supervisoras, en el portal se encuentra
 * en la ruta: info maestra/empresa supervisora, este componente contiene los
 * metodos necesarios para listar y registrar. contiene los siguientes metodos:
 * listar filtrarEmpresas obtener registrar listarEmpresaSupervisora
 * listarPorPalabraClave
 * 
 * @descripción: Empresa supervisora
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 *
 */
@RestController
@Slf4j
@RequestMapping("/empresasupervisoras")
public class EmpresaSupervisoraController extends BaseController {

	@Autowired
	private EmpresaSupervisoraService empresaSupervisoraService;

	/***
	 * Permite obtener un listado de empresas supervisoras en el contexto de la
	 * paginación de resultados requerida en la lista en el frontend.
	 * 
	 * @param filtroEmpresaSupervisora Objeto filtro que porta las propiedades que
	 *                                 de tener valor, representan criterios filtro
	 *                                 específicos esto siempre que la propiedad
	 *                                 esté configurada para aplicarse como criterio
	 *                                 al momento de las consultas.
	 * @param paginador                Objeto paginador que tiene la información de
	 *                                 la página actual, tamaño de la página y
	 *                                 criterios de ordenamiento.
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('es-lista_AC')")
	@PostMapping("/filtrarEmpresasSupervisoras")
	public ResponseEntity<Page<PgimEmpresaSupervisoraDTO>> listarEmpresaSupervisora(
			@RequestBody PgimEmpresaSupervisoraDTO filtroEmpresaSupervisora, Pageable paginador) {

		Page<PgimEmpresaSupervisoraDTO> lPgimEmpresaSupervisoraDTO = this.empresaSupervisoraService
				.filtrar(filtroEmpresaSupervisora, paginador);
		return new ResponseEntity<Page<PgimEmpresaSupervisoraDTO>>(lPgimEmpresaSupervisoraDTO, HttpStatus.OK);
	}

	@GetMapping("/filtrar/palabraClave/{palabra}")
	public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimEmpresaSupervisoraDTO> lPgimEmpresaSupervisoraDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimEmpresaSupervisoraDTO = new ArrayList<PgimEmpresaSupervisoraDTO>();
			respuesta.put("mensaje", "Se encontraron los agentes fiscalizados");
			respuesta.put("lPgimEmpresaSupervisoraDTO", lPgimEmpresaSupervisoraDTO);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			lPgimEmpresaSupervisoraDTO = this.empresaSupervisoraService.listarPorPalabraClave(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del agente fiscalizado");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los agentes fiscalizados");
		respuesta.put("lPgimEmpresaSupervisoraDTO", lPgimEmpresaSupervisoraDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('es-lista_MO')")
	@PostMapping("/crearEmpresaSupervisora")
	public ResponseEntity<?> crearEmpresaSupervisora(
			@Valid @RequestBody PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear una nueva empresa supervisora");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimEmpresaSupervisoraDTOCreado = this.empresaSupervisoraService
					.crearEmpresaSupervisora(pgimEmpresaSupervisoraDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear una empresa supervisora");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La empresa supervisora ha sido creada");
		respuesta.put("pgimEmpresaSupervisoraDTOCreado", pgimEmpresaSupervisoraDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyAuthority('es-lista_MO')")
	@PutMapping("/modificarEmpresaSupervisora")
	public ResponseEntity<?> modificarEmpresaSupervisora(
			@Valid @RequestBody PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimEmpresaSupervisora pgimEmpresaSupervisoraActual = null;
		PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTOModificada = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la empresa supervisora");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimEmpresaSupervisoraActual = this.empresaSupervisoraService
					.getByIdEmpresaSupervisora(pgimEmpresaSupervisoraDTO.getIdEmpresaSupervisora());

			if (pgimEmpresaSupervisoraActual == null) {
				mensaje = String.format(
						"La empresa supervisora %s que intenta actualizar no existe en la base de datos",
						pgimEmpresaSupervisoraDTO.getIdEmpresaSupervisora());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar la empresa supervisora a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimEmpresaSupervisoraDTOModificada = this.empresaSupervisoraService.modificarEmpresaSupervisora(
					pgimEmpresaSupervisoraDTO, pgimEmpresaSupervisoraActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar la empresa supervisora");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La empresa supervisora ha sido modificada");
		respuesta.put("pgimEmpresaSupervisoraDTOModificada", pgimEmpresaSupervisoraDTOModificada);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@GetMapping("/obtenerEmpresaSupervisoraPorId/{idEmpresaSupervisora}")
	public ResponseEntity<?> obtenerEmpresaSupervisoraPorId(@PathVariable Long idEmpresaSupervisora) throws Exception {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO = null;

		try {
			pgimEmpresaSupervisoraDTO = this.empresaSupervisoraService.obtenerPorId(idEmpresaSupervisora);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la empresa supervisora");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (pgimEmpresaSupervisoraDTO == null) {
			mensaje = String.format("La empresa supervisora con el id: %d no existe en la base de datos",
					idEmpresaSupervisora);
			respuesta.put("mensaje", mensaje);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		}

		respuesta.put("mensaje", "La empresa supervisora ha sido recuperado");
		respuesta.put("pgimEmpresaSupervisoraDTO", pgimEmpresaSupervisoraDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('es-lista_EL')")
	@DeleteMapping("/eliminarEmpresaSupervisora/{idEmpresaSupervisora}")
	public ResponseEntity<?> eliminarEmpresaSupervisora(@PathVariable Long idEmpresaSupervisora) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimEmpresaSupervisora pgimEmpresaSupervisoraActual = null;

		try {
			pgimEmpresaSupervisoraActual = this.empresaSupervisoraService
					.getByIdEmpresaSupervisora(idEmpresaSupervisora);

			if (pgimEmpresaSupervisoraActual == null) {
				mensaje = String.format("La empresa supervisora %s que intenta eliminar no existe en la base de datos",
						idEmpresaSupervisora);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar la empresa supervisora a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.empresaSupervisoraService.eliminarEmpresaSupervisora(pgimEmpresaSupervisoraActual,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar la empresa supervisora");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Me permite filtrar las personas de un contrato por DNI, Nombres y Apellidos
	 * completos
	 * 
	 * @param idContrato
	 * @param palabra
	 * @return
	 */
	@GetMapping("/listarPorPersonaJuridica/palabraClave/{palabra}")
	public ResponseEntity<?> listarPorPersonaJuridica(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimEmpresaSupervisoraDTO> lPgimPersonaDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimPersonaDTO = new ArrayList<PgimEmpresaSupervisoraDTO>();
			respuesta.put("mensaje", "Se encontraron las personas jurídicas");
			respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			lPgimPersonaDTO = this.empresaSupervisoraService.listarPorPersonaJuridica(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la persona jurídica");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron las personas jurídicas");
		respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@GetMapping("/obtenerConfiguracionesGenerales/{idEmpresaSupervisora}")
	public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idEmpresaSupervisora) {

		Map<String, Object> respuesta = new HashMap<>();

		try {

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
}
