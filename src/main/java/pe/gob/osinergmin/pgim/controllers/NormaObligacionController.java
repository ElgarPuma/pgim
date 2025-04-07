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
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemSelectAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaObligacion;
import pe.gob.osinergmin.pgim.services.NormaObligacionService;

/**
 * Controlador para la gestión de normas de obligaciones
 * 
 * @descripción: Norma obligacion
 *
 * @author: PresleyRomero
 * @version: 1.0
 * @fecha_de_creación: 18/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/obligacion")
public class NormaObligacionController extends BaseController {
	
	@Autowired
	private NormaObligacionService normaObligacionService;
	
	/**private NormaObligacion normaOb
	
	/***
     * Permite obtener un listado de las Normas en el contexto de la
     * paginación de resultados requerida en la lista en el frontend.
     * 
     * @param idItemTipificacion  		 
     *                           de las consultas.
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @return
     */    
	@PreAuthorize("hasAnyAuthority('ct-ite-obl_AC')")
	@PostMapping("/filtrar")
	public ResponseEntity<Page<PgimNormaObligacionAuxDTO>> listarNormaObligacion(
		@RequestBody Long idItemTipificacion, Pageable paginador) {
		
        Page<PgimNormaObligacionAuxDTO> lPgimNormaObligacion = this.normaObligacionService.filtrar(
        		idItemTipificacion, paginador);
        
        return new ResponseEntity<Page<PgimNormaObligacionAuxDTO>>(lPgimNormaObligacion, HttpStatus.OK);
        
	}
	
	
	@PreAuthorize("hasAnyAuthority('ct-ite-obl_IN')")
    @PostMapping("/crearNormaObligacion")
	public ResponseEntity<?> crearNormaObligacion(@Valid @RequestBody PgimNormaItemSelectAuxDTO pgimNormaItemSelectAuxDTO,
			BindingResult resultadoValidacion) throws Exception {
    	
    	
    	PgimNormaItemSelectAuxDTO pgimNormaItemSelectAuxDTOCreada = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear las obligaciones");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimNormaItemSelectAuxDTOCreada = normaObligacionService.crearNormaObligacionList(pgimNormaItemSelectAuxDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear las obligaciones");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Las obligaciones ha sido creada");
        respuesta.put("pgimNormaItemSelectAuxDTO", pgimNormaItemSelectAuxDTOCreada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    /**
	 * Permite eliminar la normaObligacion. Esta eliminación es lógica.
	 * 
	 * @param idNormaObligacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ct-ite-obl_EL')")
	@DeleteMapping("/eliminarNormaObligacion/{idNormaObligacion}")
	public ResponseEntity<?> eliminarNormaObligacion(@PathVariable Long idNormaObligacion) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimNormaObligacionDTO pgimNormaObligacionDTO = null;

		try {
			pgimNormaObligacionDTO = this.normaObligacionService.obtenerNormaObligacionPorId(idNormaObligacion);

			if (pgimNormaObligacionDTO == null) {
				mensaje = String.format("La obligación que intenta eliminar no existe en la base de datos",
						idNormaObligacion);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar la obligación a eliminar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.normaObligacionService.eliminarNormaObligacion(pgimNormaObligacionDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar a la obligación");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
    
	
	/**
     * Permite obtener la norma usado en el modo consulta y edición del frontend
     * @param idNorma
     * @return
     */
    @GetMapping("/obtenerNormaObligacionAuxPorId/{idNormaObligacion}")
    public ResponseEntity<?> obtenerNormaObligacionAuxPorId(@PathVariable Long idNormaObligacion) {
    	
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimNormaObligacionAuxDTO pgimNormaObligacionAuxDTO = null;

        try {
        	pgimNormaObligacionAuxDTO = this.normaObligacionService.obtenerNormaObligacionAuxPorId(idNormaObligacion);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimNormaObligacionAuxDTO == null) {
            mensaje = String.format("La norma con el id: %d no existe en la base de datos", idNormaObligacion);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La norma ha sido recuperada");
        respuesta.put("pgimNormaObligacionAuxDTO", pgimNormaObligacionAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    /**
     * Permite modificar la norma, valida la existencia de la norma
     * 
     * @param pgimNormaDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */    
    @PreAuthorize("hasAnyAuthority('ct-ite-obl_MO')")
    @PutMapping("/modificarNormaObligacion")
    public ResponseEntity<?> modificarNormaObligacion(
            @Valid @RequestBody PgimNormaObligacionDTO pgimNormaObligacionDTO, BindingResult resultadoValidacion)
            throws Exception {

    	log.info("controller modificacion- "+ pgimNormaObligacionDTO.getIdNormaObligacion());
    	PgimNormaObligacion pgimNormaObligacionActual = null;
        PgimNormaObligacionAuxDTO pgimNormaObligacionAuxDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la obligación");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimNormaObligacionActual = this.normaObligacionService.getByIdNormaObligacion(pgimNormaObligacionDTO.getIdNormaObligacion());

            if (pgimNormaObligacionActual == null) {
                mensaje = String.format("La obligacion %s que intenta actualizar no existe en la base de datos",
                		pgimNormaObligacionDTO.getIdNormaObligacion());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la obligación a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        
        try {
        	pgimNormaObligacionAuxDTOModificado = this.normaObligacionService.modificarNormaObligacion(pgimNormaObligacionDTO, pgimNormaObligacionActual, this.obtenerAuditoria());
        	
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La norma ha sido modificada");
        respuesta.put("pgimNormaObligacionAuxDTO", pgimNormaObligacionAuxDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    
    
}
