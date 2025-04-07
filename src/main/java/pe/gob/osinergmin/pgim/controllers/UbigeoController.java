package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.models.entity.Ubigeo;
import pe.gob.osinergmin.pgim.services.UbigeoService;

/**
 * Controlador del componente componente para los ubigeos,
 * este componente contiene los metodos necesarios para el crud y validaciones respectivas.
 * contiene los siguientes metodos:
 * listar
 * listarPageable
 * filtrar
 * obtener
 * registrar
 * modificar
 * eliminar
 * listarPorPalabraClave
 * listarPorPalabraClaveAlternativo
 * 
 * @descripción: Ubigeo
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 *
 */
@RestController
@Slf4j
@RequestMapping("/ubigeos")
public class UbigeoController {

	@Autowired
	private UbigeoService ubigeoService;

	@GetMapping
	public ResponseEntity<Iterable<Ubigeo>> listar() {

		Iterable<Ubigeo> ubigeos = new ArrayList<>();

		ubigeos = ubigeoService.listarUbigeos();

		if (ubigeos == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(ubigeos);
	}

	@GetMapping(value = "/pageable")
	public ResponseEntity<Page<Ubigeo>> listarPageable(Pageable pageable) {
		Page<Ubigeo> listaUbigeos = ubigeoService.listarPageable(pageable);
		return new ResponseEntity<Page<Ubigeo>>(listaUbigeos, HttpStatus.OK);
	}

	@GetMapping("/filtrar/{nombre}")
	public ResponseEntity<List<Ubigeo>> filtrar(@PathVariable String nombre) {
		return ResponseEntity.ok(ubigeoService.filtrarPorNombre(nombre));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Ubigeo> obtener(@PathVariable("id") String id) {
		Ubigeo ubigeo = ubigeoService.obtenerUbigeo(id);
		if (null == ubigeo) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ubigeo);
	}

	@PostMapping
	public ResponseEntity<Ubigeo> registrar(@Valid @RequestBody Ubigeo ubigeo) {

		Ubigeo ubigeoCreate = ubigeoService.registrarUbigeo(ubigeo);

		return new ResponseEntity<Ubigeo>(ubigeoCreate, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Ubigeo> modificar(@Valid @RequestBody Ubigeo ubigeo) {

		Ubigeo ubigeoEdit = ubigeoService.actualizarUbigeo(ubigeo);

		return new ResponseEntity<Ubigeo>(ubigeoEdit, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") String id) {
		ubigeoService.eliminarUbigeo(id);
	}

	@GetMapping("/filtrar/palabraClave/{palabra}")
	public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimUbigeoDTO> lPgimUbigeoDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimUbigeoDTO = new ArrayList<PgimUbigeoDTO>();
			respuesta.put("mensaje", "No se encontraron ubigeos");
			respuesta.put("PgimUbigeoDTO", lPgimUbigeoDTO);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			lPgimUbigeoDTO = this.ubigeoService.listarPorPalabraClave(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las unidades fiscalizadas");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los ubigeos");
		respuesta.put("lPgimUbigeoDTO", lPgimUbigeoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping("/filtrar/palabraClaveAlternativo/{palabra}")
	public ResponseEntity<?> listarPorPalabraClaveAlternativo(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimUbigeoDTO> lPgimUbigeoDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimUbigeoDTO = new ArrayList<PgimUbigeoDTO>();
			respuesta.put("mensaje", "No se encontraron ubigeos");
			respuesta.put("PgimUbigeoDTO", lPgimUbigeoDTO);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			lPgimUbigeoDTO = this.ubigeoService.listarPorPalabraClaveAlternativo(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las unidades fiscalizadas");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los ubigeos");
		respuesta.put("lPgimUbigeoDTO", lPgimUbigeoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

}
