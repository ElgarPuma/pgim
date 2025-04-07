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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoSubcomponenteDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteMinero;
import pe.gob.osinergmin.pgim.services.CmineroSprvsionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PgimComponenteMinService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador del componente componente para las unidades mineras, en el portal
 * se encuentra en la ruta: info maestra/unidad minera/componente este
 * componente contiene los metodos para listar el componente. contiene los
 * siguientes metodos: listar
 *
 * @descripción: Componente minero
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 */
@RestController
@Slf4j
@RequestMapping("/componentesmineros")
public class PgimComponenteMinController extends BaseController {

	@Autowired
	private PgimComponenteMinService componenteMineroService;

	@Autowired
	private ParametroService parametroService;
	
	@Autowired
    private CmineroSprvsionService cMineroSprvsionService;
	
	
	@PreAuthorize("hasAnyAuthority('um-compone_AC')")
	@GetMapping("/listar/idUnidadMinera/{idUnidadMinera}")
	public ResponseEntity<List<PgimComponenteMineroDTO>> listar(@PathVariable Long idUnidadMinera) {

		List<PgimComponenteMineroDTO> componenteM = new ArrayList<>();

		// Ordenamiento
		Sort sort = Sort.by("coComponente");
		sort = sort.ascending();
		
		try {
			
			componenteM = componenteMineroService.listarComponenteMinero(idUnidadMinera, sort);
	
			if (componenteM == null) {
				return ResponseEntity.noContent().build();
			}			
			
			// Añadimos usuario en sesión para uso en registro de servicios GIS
			AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
			
			componenteM = componenteM.stream()
	                .map(cm -> {
	                	cm.setDescUsSesion(auditoriaDTO.getUsername());
	                	cm.setDescIpSesion(auditoriaDTO.getTerminal());
	                	return cm;
	                })
	                .collect(Collectors.toList());  
			
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al consultar la lista de componentes mineros de la UM: " + idUnidadMinera;
			log.error(mensaje + ": " +e.getMessage(), e);

			return new ResponseEntity<List<PgimComponenteMineroDTO>>(componenteM, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(componenteM);

	}

	@GetMapping("/obtenerConfiguracionesGenerales")
	public ResponseEntity<?> obtenerConfiguracionesGenerales() {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> lPgimValorParamDTOTipoComponenteMinero = null;

		try {
			lPgimValorParamDTOTipoComponenteMinero = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_COMPONENTE_MINERO);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimValorParamDTOTipoComponenteMinero", lPgimValorParamDTOTipoComponenteMinero);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite obtener las configuraciones necesarias para el formulario del
	 * involucrado de la supervison o representantes.
	 * 
	 * @param idContrato
	 * @param idInvolucradoSuperv
	 * @return
	 */
	// @PreAuthorize("hasAnyAuthority('sp-sc-repr_CO')")
	@GetMapping("/obtenerConfiguraciones/{idUnidadMinera}/{idComponenteMinero}")
	public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idUnidadMinera,
			@PathVariable Long idComponenteMinero) {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> lPgimValorParamDTOTipoComponenteMinero = null;
		List<PgimComponenteMineroDTO> lPgimComponenteMineroDTONombre = null;
		List<PgimTipoSubcomponenteDTO> lPgimComponenteMineroDTOSubtipo = null;
		PgimComponenteMineroDTO pgimComponenteMineroDTO = null;

		try {
			lPgimValorParamDTOTipoComponenteMinero = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_COMPONENTE_MINERO);
			lPgimComponenteMineroDTONombre = this.componenteMineroService.listarComponentePadrePorUm(idUnidadMinera,
					idComponenteMinero);

			lPgimComponenteMineroDTOSubtipo = this.componenteMineroService.listarSubTipoComponentes();
			if (idComponenteMinero != 0) {
				pgimComponenteMineroDTO = this.componenteMineroService.obtenerComponenteMineroPorId(idComponenteMinero);
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

		respuesta.put("lPgimValorParamDTOTipoComponenteMinero", lPgimValorParamDTOTipoComponenteMinero);
		respuesta.put("lPgimComponenteMineroDTONombre", lPgimComponenteMineroDTONombre);
		respuesta.put("lPgimComponenteMineroDTOSubtipo", lPgimComponenteMineroDTOSubtipo);
		respuesta.put("pgimComponenteMineroDTO", pgimComponenteMineroDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	
	@GetMapping("/obtenerEstadosComponenteMinero")
	public ResponseEntity<?> obtenerEstadosComponenteMinero() {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> lPgimValorParamDTOEstadosComponenteMinero = null;

		try {
			lPgimValorParamDTOEstadosComponenteMinero = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_ESTADO_COMPONENTE_MINERO);
			

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas los estados del componente minero");

		respuesta.put("lPgimValorParamDTOEstadosComponenteMinero", lPgimValorParamDTOEstadosComponenteMinero);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Me permite crear un nuevo componente minero
	 * 
	 * @param pgimComponenteMineroDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-compone_IN')")
	@PostMapping("/crearComponenteMinero")
	public ResponseEntity<?> crearComponenteMinero(@Valid @RequestBody PgimComponenteMineroDTO pgimComponenteMineroDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimComponenteMineroDTO pgimComponenteMineroDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear un nuevo componente minero");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimComponenteMineroDTOCreado = this.componenteMineroService.crearComponenteMinero(pgimComponenteMineroDTO,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear un nuevo componente minero");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El componente minero ha sido creado");
		respuesta.put("pgimComponenteMineroDTOCreado", pgimComponenteMineroDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Me permite modificar el componente minero
	 * 
	 * @param pgimComponenteMineroDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-compone_MO') || hasAnyAuthority('um019_IN')")
	@PostMapping("/modificarComponenteMinero")
	public ResponseEntity<?> modificarComponenteMinero(
			@Valid @RequestBody PgimComponenteMineroDTO pgimComponenteMineroDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimComponenteMinero pgimComponenteMineroActual = null;
		PgimComponenteMineroDTO pgimComponenteMineroDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;
			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());
			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el componente minero");
			respuesta.put("error", errores.toString());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
		try {
			pgimComponenteMineroActual = this.componenteMineroService
					.getByIdComponenteMinero(pgimComponenteMineroDTO.getIdComponenteMinero());

			if (pgimComponenteMineroActual == null) {
				mensaje = String.format("El componente minero %s que intenta actualizar no existe en la base de datos",
						pgimComponenteMineroDTO.getIdComponenteMinero());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el componente minero a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			pgimComponenteMineroDTOModificado = this.componenteMineroService
					.modificarComponenteMinero(pgimComponenteMineroDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar un componente minero");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "El componente minero ha sido modificado");
		respuesta.put("pgimComponenteMineroDTO", pgimComponenteMineroDTOModificado);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyAuthority('um-compone_EL')")
	@DeleteMapping("/eliminarComponenteMinero/{idComponenteMinero}")
	public ResponseEntity<?> eliminarComponenteMinero(@PathVariable Long idComponenteMinero) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimComponenteMinero pgimComponenteMineroActual = null;

		try {
			pgimComponenteMineroActual = this.componenteMineroService.getByIdComponenteMinero(idComponenteMinero);

			if (pgimComponenteMineroActual == null) {
				mensaje = String.format("El componente minero %s que intenta eliminar no existe en la base de datos",
						idComponenteMinero);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar el componente minero a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.componenteMineroService.eliminarComponenteMinero(pgimComponenteMineroActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar un componente minero");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PostMapping("/listarComponenteUMPaginado")
	public ResponseEntity<Page<PgimComponenteMineroDTO>> listarReporteComponenteUMPaginado(
			@RequestBody PgimComponenteMineroDTO filtroPgimComponenteMinero, Pageable paginador) {

		Page<PgimComponenteMineroDTO> lPgimComponenteMineroDTO = this.componenteMineroService
				.listarReporteComponenteUMPaginado(filtroPgimComponenteMinero, paginador);
		return new ResponseEntity<Page<PgimComponenteMineroDTO>>(lPgimComponenteMineroDTO, HttpStatus.OK);
	}
	
	@GetMapping("/listarComponentes")
	public ResponseEntity<List<PgimComponenteMineroDTO>> listarComponentes(){
		
		List<PgimComponenteMineroDTO> lcomponentes = new ArrayList<>();
		
		lcomponentes = componenteMineroService.listarComponentes();

		if (lcomponentes == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(lcomponentes);
		
    }
	
	/**
     * Permite obtener la lista de componentes mineros de acuerdo con la palabra y unidad minera.
     * Esta palabra será buscada en su código o nombre.
     * @param palabra
     * @param idUnidadMinera
     * @return
     */
    @GetMapping("/filtrar/palabraClaveYUm/{palabra}/{idUnidadMinera}")
    public ResponseEntity<?> listarPorPalabraClaveYUm(@PathVariable String palabra,
            @PathVariable Long idUnidadMinera) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimComponenteMineroDTO = new ArrayList<PgimComponenteMineroDTO>();
            respuesta.put("mensaje", "No se encontraron componente mineros");
            respuesta.put("lPgimComponenteMineroDTO", lPgimComponenteMineroDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (palabra.equalsIgnoreCase("todos")) {
            palabra = null;
        }

        try {
            lPgimComponenteMineroDTO = this.componenteMineroService.listarPorPalabraClaveYUm(palabra, idUnidadMinera);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los componentes mineros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los componentes mineros");
        respuesta.put("lPgimComponenteMineroDTO", lPgimComponenteMineroDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/crearComponentesMineros")
    public ResponseEntity<ResponseDTO> crearComponentesMineros(@RequestBody List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO) throws Exception {

        ResponseDTO responseDTO = null;
		String mensaje = "";
        try {
            mensaje = this.componenteMineroService.crearComponentesMineros(lPgimComponenteMineroDTO, this.obtenerAuditoria());
			
        } catch (final DataAccessException e) {
			String mensajeError = "Ocurrió un error al intentar crear componentes: "+ e.getMostSpecificCause().getMessage();
            log.error(mensajeError, e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeError);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);
			TipoResultado tipoResultado = e.getTipoResultado() == null ? TipoResultado.WARNING : e.getTipoResultado();
            responseDTO = new ResponseDTO(tipoResultado, e.getMensaje());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar crear los componentes " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

		
        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);	
	}

	@PostMapping("/obtenerNuevosComponentes")
    public ResponseEntity<?> obtenerNuevosComponentes(@RequestBody List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO) {

		ResponseDTO responseDTO = null;
		List<PgimComponenteMineroDTO> lPgimComponenteMineroDTONuevos = null;

		try {
			lPgimComponenteMineroDTONuevos = this.componenteMineroService.obtenerNuevosComponentes(lPgimComponenteMineroDTO);

		} catch (final DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener los nuevos componentes mineros");
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener los nuevos componentes mineros");
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lPgimComponenteMineroDTONuevos, "Nuevos componentes encontradas");
		responseDTO.setLista(lPgimComponenteMineroDTONuevos);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
	
	@GetMapping("/listarCmPorSupervision/{idSupervision}")
    public ResponseEntity<?> listarCmPorSupervision(@PathVariable Long idSupervision) {
    	
    	Map<String, Object> respuesta = new HashMap<>();
    	List<PgimCmineroSprvsionDTO> lPgimComponenteMineroDTO = null;
    	
    	try {
    		
    		lPgimComponenteMineroDTO = cMineroSprvsionService.listarComponenteMineroSupervision(idSupervision);
           
        } catch (DataAccessException e) {
        	
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite un componente fiscalizable asociada");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    	
    	 respuesta.put("mensaje", "Si fue posible determinar la existencia de un componentes fiscalizable asociada");
         respuesta.put("lPgimComponenteMineroDTO", lPgimComponenteMineroDTO);

         return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
	@PostMapping("/crearComponentesMinerosSupervision")
	public ResponseEntity<?> crearComponentesMinerosSupervision(@RequestBody List<PgimCmineroSprvsionDTO> lPgimCmineroSprvsionDTO) throws Exception {
		
		ResponseDTO responseDTO = null;
		String mensaje = "";
        try {
        	
            mensaje = this.componenteMineroService.crearComponentesMinerosSupervision(lPgimCmineroSprvsionDTO, this.obtenerAuditoria());
			
        } catch (final DataAccessException e) {
			String mensajeError = "Ocurrió un error al intentar crear uno o más componentes mineros por supervision: "+ e.getMostSpecificCause().getMessage();
            log.error(mensajeError, e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeError);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);
			TipoResultado tipoResultado = e.getTipoResultado() == null ? TipoResultado.WARNING : e.getTipoResultado();
            responseDTO = new ResponseDTO(tipoResultado, e.getMensaje());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar crear uno o más componentes mineros por supervision " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
        
        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);	
	}

}
