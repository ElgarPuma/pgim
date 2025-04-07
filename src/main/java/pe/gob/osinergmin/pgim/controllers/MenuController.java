package pe.gob.osinergmin.pgim.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.security.model.SissegModulo;
import pe.gob.osinergmin.pgim.security.service.RestApiSissegService;

/**
 * Controlador del componente del menu,
 * este componente contiene los bloques funcionales por usuario autentificado.
 * contiene los siguientes metodos:
 * listar
 * 
 * @descripción: menu
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 *
 */
@RestController
@Slf4j
@RequestMapping("/menus")
public class MenuController {
	
	@Autowired
	private RestApiSissegService restApiSissegService;	

	@GetMapping(value = "/{idUsuario}")
	public ResponseEntity<List<SissegModulo>> listarItemsMenu(@PathVariable("idUsuario") Long idUsuario) {
		log.info("ingresando a listar items menu por usuario autentificado =="+idUsuario); 		
		List<SissegModulo> lista = restApiSissegService.listarModulos(idUsuario);	
		return ResponseEntity.ok(lista);
	}

	
}
