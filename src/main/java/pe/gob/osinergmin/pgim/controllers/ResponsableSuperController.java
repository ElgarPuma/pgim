package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
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
import pe.gob.osinergmin.pgim.dtos.PgimResponsableSuperDTO;
import pe.gob.osinergmin.pgim.services.ResponsableSuperService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a Responsable super
 * 
 * @descripción: Responsable super
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 18/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/responsablesuper")
public class ResponsableSuperController {
    
    @Autowired
    private ResponsableSuperService responsableSuperService;

    /**
     * Me permite listar por palabra clave de la lista de Supervisión
     * 
     * @param palabra = coSupervision
     * @return
     */
    @GetMapping("/filtrar/palabraClave/{palabra}")
    public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimResponsableSuperDTO> lPgimResponsableSuperDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimResponsableSuperDTO = new ArrayList<PgimResponsableSuperDTO>();
            respuesta.put("mensaje", "No se encontraron responsables");
            respuesta.put("lPgimResponsableSuperDTO", lPgimResponsableSuperDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimResponsableSuperDTO = this.responsableSuperService.listarPorPalabraClave(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de responsables");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las responsables");
        respuesta.put("lPgimResponsableSuperDTO", lPgimResponsableSuperDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}