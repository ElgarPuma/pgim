package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
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
import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionCmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.exception.NotFoundException;
import pe.gob.osinergmin.pgim.services.AutorizacionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PersonaService;
import pe.gob.osinergmin.pgim.services.PgimComponenteMinService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestion de autorizaciones
 *
 * @descripción: Autorizaciones
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 03/09/2020
 */
@RestController
@Slf4j
@RequestMapping("/autorizacion")
public class AutorizacionController extends BaseController {
	
	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private PgimComponenteMinService componenteMineroService;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private AutorizacionService autorizacionService;
	
	/**
	 * Permite obtener la lista de autorizaciones usados en la tabla autorizacion del frontend.
	 * @param idUnidadMinera
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('um-autoriz_AC')")
	@PostMapping("/listarautorizacion")
	public ResponseEntity<Page<PgimAutorizacionDTO>> listar(@RequestBody final Long idUnidadMinera, final Pageable paginador) {

		final Page<PgimAutorizacionDTO> autorzn = this.autorizacionService
                .listarAutorizacion(idUnidadMinera, paginador);
        return new ResponseEntity<Page<PgimAutorizacionDTO>>(autorzn, HttpStatus.OK);
	}
	
	/**
	 * Permite obtener las listas de tipo de autorizacion  
	 * 
	 * @return
	 */
	@GetMapping("/obtenerConfiguraciones/{idUnidadMinera}/{idAutorizacion}")
    public ResponseEntity<?> obtenerConfiguraciones(@PathVariable("idUnidadMinera") Long idUnidadMinera,@PathVariable("idAutorizacion") Long idAutorizacion) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOTipoAutorizacion = null;
        List<PgimComponenteMineroDTO> componenteM = null;
        List<PgimAutorizacionCmDTO> listaACM = null;
        
        try {
        	lPgimValorParamDTOTipoAutorizacion = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_AUTORIZACION);
        	
        	
        	//Ordenamiento
    		Sort sort  = Sort.by("tipoComponenteMinero.noValorParametro");
    		sort = sort.ascending();
    		Sort sort2  = Sort.by("noComponente");
    		sort2 = sort2.ascending();
    		sort = sort.and(sort2);
    		
    		//Componentes de la UM
    		componenteM = componenteMineroService.listarComponenteMinero(idUnidadMinera,sort);
    		
    		//Componentes de la Autorización
    		listaACM = autorizacionService.getListAutorizacionCmByIdAutorizacion(idAutorizacion);
    		
    		for (PgimAutorizacionCmDTO acm : listaACM) {
				
    			for(PgimComponenteMineroDTO cm : componenteM) {
    				if(cm.getIdComponenteMinero().equals(acm.getIdComponenteMinero())) {
    					cm.setDescSeleccionado(true);
    				}
    			}
    			
			}
        	

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOTipoAutorizacion", lPgimValorParamDTOTipoAutorizacion);
        respuesta.put("componenteM", componenteM);
        

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
	@GetMapping("/filtrarPersonaJuridica/palabraClave/{palabra}")
	public ResponseEntity<?> listarPersonaJuridicaPorPalabraClave(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimPersonaDTO> lPgimPersonaDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimPersonaDTO = new ArrayList<PgimPersonaDTO>();
			respuesta.put("mensaje", "Se encontraron las personas jurídicas");
			respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		
		try {
			lPgimPersonaDTO = this.personaService.listarPersonaJuridicaPorPalabraClave(palabra);
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
	
	
	/**
     * Permite crear la autorización.
     * 
     * @param pgimAutorizacionDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('um-autoriz_IN')")
    @PostMapping("/crearAutorizacion")
    public ResponseEntity<?> crearAutorizacion(@Valid @RequestBody PgimAutorizacionDTO pgimAutorizacionDTO,
            BindingResult resultadoValidacion) throws Exception {

    	PgimAutorizacionDTO pgimAutorizacionDTOCreada = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear la autorización");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimAutorizacionDTOCreada = autorizacionService.crearAutorizacion(pgimAutorizacionDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la autorización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La autorización ha sido creada");
        respuesta.put("pgimAutorizacionDTO", pgimAutorizacionDTOCreada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    /**
     * Permite modificar la autorizacion.
     * 
     * @param pgimAutorizacionDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('um-autoriz_MO')")
    @PutMapping("/modificarAutorizacion")
    public ResponseEntity<?> modificarAutorizacion(@Valid @RequestBody PgimAutorizacionDTO pgimAutorizacionDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimAutorizacionDTO pgimAutorizacionDTOModificada = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la autorización");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
     

        try {
        	pgimAutorizacionDTOModificada = this.autorizacionService.modificarAutorizacion(pgimAutorizacionDTO, this.obtenerAuditoria());
        	
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la autorización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El evento ha sido modificado");
        respuesta.put("pgimAutorizacionDTO", pgimAutorizacionDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    /**
	 * Permite eliminar la autorización. Esta eliminación es lógica.
	 * 
	 * @param idAutorizacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-autoriz_EL')")
	@DeleteMapping("/eliminarAutorizacion/{idAutorizacion}")
	public ResponseEntity<?> eliminarAutorizacion(@PathVariable Long idAutorizacion) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimAutorizacionDTO pgimAutorizacionDTO = null;

		try {
			pgimAutorizacionDTO = this.autorizacionService.obtenerAutorizacionById(idAutorizacion);

			if (pgimAutorizacionDTO == null) {
				mensaje = String.format("La autorización que intenta eliminar no existe en la base de datos",
						idAutorizacion);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar la autorización a eliminar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.autorizacionService.eliminarAutorizacion(pgimAutorizacionDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar a la autorización");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
    
    
    /**
	 * Permite obtener la autorización, usado en el modo edición y consulta del frontend.
	 * @param idAutorizacion
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('um-autoriz_CO')")
	@GetMapping("/obtener/idAutorizacion/{id}")
	public ResponseEntity<PgimAutorizacionDTO> obtener(@PathVariable("id") Long idAutorizacion) {
		PgimAutorizacionDTO autorizacion = autorizacionService.obtenerAutorizacionById(idAutorizacion);
		if(autorizacion.getIdAutorizacion() == null) {
			throw new NotFoundException("ID NO ENCONTRADO: " + idAutorizacion);
		}
		return new ResponseEntity<PgimAutorizacionDTO>(autorizacion, HttpStatus.OK);
	}

}
