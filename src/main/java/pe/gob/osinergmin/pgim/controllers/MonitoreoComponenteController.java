package pe.gob.osinergmin.pgim.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimMonitoreoComponenteDTOResultado;
import pe.gob.osinergmin.pgim.services.MonitoreoComponenteService;

@RestController
@Slf4j
@RequestMapping("/monitoreo")
public class MonitoreoComponenteController {
	
	@Autowired
	private MonitoreoComponenteService monitoreoComponenteService;
	
	@GetMapping("/obtenerMonitoreoDistanciaMinima/{idComponenteMinero}")
	public ResponseEntity<?> obtenerGraficosPorDeposito(@PathVariable Long idComponenteMinero){
		
		final Map<String, Object> respuesta = new HashMap<>();
		
		Long idComponente = idComponenteMinero;
		List<PgimMonitoreoComponenteDTOResultado> listaMonitoreoComponenteDistancia = null;
		List<PgimMonitoreoComponenteDTOResultado> listaMonitoreoComponentePorcentajeAgua= null;
		try {
			listaMonitoreoComponenteDistancia = monitoreoComponenteService.listarMonitoreoComponenteDistancias(idComponente);
			listaMonitoreoComponentePorcentajeAgua = monitoreoComponenteService.listarMonitoreoComponenteDistancias(idComponente);
			 
		} catch (DataAccessException e) {
		
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		respuesta.put("listaMonitoreoComponenteDistancia", listaMonitoreoComponenteDistancia);
		respuesta.put("listaMonitoreoComponentePorcentajeAgua", listaMonitoreoComponentePorcentajeAgua);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	
	
}
