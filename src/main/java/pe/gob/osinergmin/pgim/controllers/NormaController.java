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
import pe.gob.osinergmin.pgim.dtos.PgimNormaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaEnlaceDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaItemDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemTipificacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimItemTipificacion;
import pe.gob.osinergmin.pgim.models.entity.PgimNorma;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaItem;
import pe.gob.osinergmin.pgim.services.NormaService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para las normas
 *
 * @descripción: Normas
 *
 * @author: PresleyRomero
 * @version: 1.0
 * @fecha_de_creación: 05/05/2021
 */

@RestController
@Slf4j
@RequestMapping("/normas")
public class NormaController extends BaseController {
	@Autowired
	private NormaService normaService;
	
	@Autowired
	private ParametroService parametroService;
	
	/**
	 * Permite obtener un listado de las Normas según una palabra clave ingresada
	 * requerido en campos autocomplete del frontend
	 * @param palabra
	 * @return
	 */
	@GetMapping("/filtrar/palabraClave/{palabra}")
	public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimNormaDTO> lPgimNormaDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimNormaDTO = new ArrayList<PgimNormaDTO>();
			respuesta.put("mensaje", "Se encontraron las normas");
			respuesta.put("lPgimNormaDTO", lPgimNormaDTO);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		
		try {
			lPgimNormaDTO = this.normaService.listarPorPalabraClave(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las normas");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron las normas");
		respuesta.put("lPgimNormaDTO", lPgimNormaDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	

	/***
     * Permite obtener un listado de las Normas en el contexto de la
     * paginación de resultados requerida en la lista en el frontend.
     * 
     * @param filtroNorma 		 Objeto filtro que porta las propiedades que de
     *                           tener valor, representan criterios filtro
     *                           específicos esto siempre que la propiedad esté
     *                           configurada para aplicarse como criterio al momento
     *                           de las consultas.
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @return
     */    
	@PreAuthorize("hasAnyAuthority('no-lista_AC')")
	@PostMapping("/filtrar")
    public ResponseEntity<Page<PgimNormaAuxDTO>> listarNorma(
		@RequestBody PgimNormaDTO filtroNorma, Pageable paginador) {

        Page<PgimNormaAuxDTO> lPgimNormaAuxDTO = this.normaService.filtrar(
			filtroNorma, paginador);
        return new ResponseEntity<Page<PgimNormaAuxDTO>>(lPgimNormaAuxDTO, HttpStatus.OK);
	}
	
	
	/**
     * Permite obtener la norma usado en el modo consulta y edición del frontend
     * @param idNorma
     * @return
     */
    @GetMapping("/obtenerNormaPorId/{idNorma}")
    public ResponseEntity<?> obtenerNormaPorId(@PathVariable Long idNorma) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimNormaDTO pgimNormaDTO = null;

        try {
        	pgimNormaDTO = this.normaService.obtenerNormaPorId(idNorma);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimNormaDTO == null) {
            mensaje = String.format("La norma con el id: %d no existe en la base de datos", idNorma);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La norma ha sido recuperada");
        respuesta.put("pgimNormaDTO", pgimNormaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    /**
     * Permite obtener la NormaAux (vista) 
     * @param idNorma
     * @return
     */
    @GetMapping("/obtenerNormaAuxPorId/{idNorma}")
    public ResponseEntity<?> obtenerNormaAuxPorId(@PathVariable Long idNorma) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimNormaAuxDTO pgimNormaAuxDTO = null;

        try {
        	pgimNormaAuxDTO = this.normaService.obtenerNormaAuxPorId(idNorma);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimNormaAuxDTO == null) {
            mensaje = String.format("La norma con el id: %d no existe en la base de datos", idNorma);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La norma ha sido recuperada");
        respuesta.put("pgimNormaAuxDTO", pgimNormaAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
    
	/***
     * Permite obtener las configuraciones necesarias para el listado de normas.
     * Acá se incluye la configuración como: Tipo de norma (PgimValorParametro).
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTO = null;

        try {
        	lPgimValorParametroDTO = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_NOMBRE_TIPO_NORMA);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de tipos de norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParametroDTO", lPgimValorParametroDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
    
    /**
	 * Permite obtener las listas de tipo de normas y tipo de división de ítems usadas en el frontend
	 * @return
	 */
	@GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoNorma = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTODivisItem = null;
        
        try {
        	lPgimValorParametroDTOTipoNorma = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_NOMBRE_TIPO_NORMA);
        	lPgimValorParametroDTODivisItem = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_NOMBRE_TIPO_DIVISION_ITEM);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOTipoNorma", lPgimValorParametroDTOTipoNorma);   
        respuesta.put("lPgimValorParamDTODivisItem", lPgimValorParametroDTODivisItem);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    /**
     * Permite crear la norma
     * 
     * @param pgimNormaDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
	@PreAuthorize("hasAnyAuthority('no-lista_IN')")
    @PostMapping("/crearNorma")
    public ResponseEntity<?> crearNorma(
            @Valid @RequestBody PgimNormaDTO pgimNormaDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimNormaDTO pgimNormaDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear la norma");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	pgimNormaDTOCreado = normaService.crearNorma(pgimNormaDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La norma ha sido creada");
        respuesta.put("pgimNormaDTO", pgimNormaDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    /**
     * Permite modificar la norma, valida la existencia de la norma
     * 
     * @param pgimNormaDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */    
	@PreAuthorize("hasAnyAuthority('no-lista_MO')")
    @PutMapping("/modificarNorma")
    public ResponseEntity<?> modificarNorma(
            @Valid @RequestBody PgimNormaDTO pgimNormaDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimNorma pgimNormaActual = null;
        PgimNormaDTO pgimNormaDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la norma");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimNormaActual = this.normaService.getByIdNorma(pgimNormaDTO.getIdNorma());

            if (pgimNormaActual == null) {
                mensaje = String.format("La norma %s que intenta actualizar no existe en la base de datos",
                		pgimNormaDTO.getIdNorma());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la norma a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        
        try {
        	pgimNormaDTOModificado = this.normaService.modificarNorma(pgimNormaDTO, pgimNormaActual, this.obtenerAuditoria());
        	
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La norma ha sido modificada");
        respuesta.put("pgimNormaDTO", pgimNormaDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    /**
     * Permite eliminar la norma
     * 
     * @param idNorma
     * @return
     * @throws Exception
     */
	@PreAuthorize("hasAnyAuthority('no-lista_EL')")
    @DeleteMapping("/eliminarNorma/{idNorma}")
    public ResponseEntity<?> eliminarNorma(@PathVariable Long idNorma) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimNorma pgimNormaActual = null;

        try {
        	pgimNormaActual = this.normaService.getByIdNorma(idNorma);

            if (pgimNormaActual == null) {
                mensaje = String.format("La norma %s que intenta eliminar no existe en la base de datos",
                		idNorma);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la norma a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.normaService.eliminarNorma(pgimNormaActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    /**
	 * Permite obtener la lista de enlaces relacionados de la norma por ID de norma
	 * 
	 * @param idNorma
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('no-enlace_AC')")
	@GetMapping("/listarEnlacesNorma/{idNorma}")
	public ResponseEntity<List<PgimNormaEnlaceDTO>> listarEnlacesNorma(
			@PathVariable Long idNorma) throws Exception {

		final List<PgimNormaEnlaceDTO> lPgimNormaEnlaceDTO = this.normaService.listarEnlacesNorma(idNorma);
		
		return new ResponseEntity<List<PgimNormaEnlaceDTO>>(lPgimNormaEnlaceDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Permite crear un enlace relacionado de norma
	 * @param pgimNormaEnlaceDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('no-enlace_IN')")
	@PostMapping("/registrarEnlace")
	public ResponseEntity<?> registrarEnlace(@Valid @RequestBody PgimNormaEnlaceDTO pgimNormaEnlaceDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimNormaEnlaceDTO pgimNormaEnlaceDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el enlace de norma");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimNormaEnlaceDTOCreado = this.normaService.crearEnlace(pgimNormaEnlaceDTO, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear el enlace de norma");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El enlace ha sido creado");
		respuesta.put("pgimNormaEnlaceDTO", pgimNormaEnlaceDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	
	
	/**
	 * Permite eliminar el enlace relacionado de norma
	 * 
	 * @param idEnlace
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('no-enlace_EL')")
	@PostMapping("/eliminarEnlace/idEnlace/{id}")
	public ResponseEntity<ResponseDTO> eliminarEnlace(@PathVariable("id") Long idEnlace) throws Exception {

		try {
			Long rpta = this.normaService.eliminarEnlace(idEnlace, this.obtenerAuditoria());
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",
					"Enlace de la norma eliminado correctamente", rpta));
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDTO("error",
							"Error al intentar eliminar el enlace de norma: ",
							e.getMostSpecificCause().getMessage()));

		}
	}
	
	
	/**
	 * Permite obtener la lista de items de la norma por ID de norma
	 * 
	 * @param idNorma
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('no-item_AC')")
	@PostMapping("/listarItemsNorma/{idNorma}")
	public ResponseEntity<Page<PgimNormaItemAuxDTO>> listarItemsNorma(
			@PathVariable Long idNorma,
			@RequestBody PgimNormaItemDTO filtroPgimNormaItemDTO, Pageable paginador) throws Exception {
		
		Page<PgimNormaItemAuxDTO> lPgimNormaItemAuxDTO = this.normaService.listarItemsNorma(idNorma, filtroPgimNormaItemDTO, paginador);
		
		return new ResponseEntity<Page<PgimNormaItemAuxDTO>>(lPgimNormaItemAuxDTO, HttpStatus.OK);
	}	
	
	/**
	 * Permite crear un ítem de norma 
	 * @param pgimNormaItemDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('no-item_IN')")
	@PostMapping("/crearItemNorma")
	public ResponseEntity<?> crearItemNorma(@Valid @RequestBody PgimNormaItemDTO pgimNormaItemDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimNormaItemDTO pgimNormaItemDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el ítem");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimNormaItemDTOCreado = this.normaService.crearItem(pgimNormaItemDTO, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			respuesta.put("mensaje", "Ocurrió un error al intentar crear el ítem");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El ítem ha sido creado");
		respuesta.put("pgimNormaItemDTO", pgimNormaItemDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	
	/**
     * Permite modificar el ítem de norma, valida la existencia del ítem
     * 
     * @param pgimNormaItemDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
	@PreAuthorize("hasAnyAuthority('no-item_MO')")
    @PutMapping("/modificarItemNorma")
    public ResponseEntity<?> modificarItemNorma(
            @Valid @RequestBody PgimNormaItemDTO pgimNormaItemDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimNormaItem pgimNormaItemActual = null;
        PgimNormaItemDTO pgimNormaItemDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el ítem");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimNormaItemActual = this.normaService.getByIdNormaItem(pgimNormaItemDTO.getIdNormaItem());

            if (pgimNormaItemActual == null) {
                mensaje = String.format("El ítem %s que intenta actualizar no existe en la base de datos",
                		pgimNormaItemDTO.getIdNormaItem());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el ítem a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        
        try {
        	pgimNormaItemDTOModificado = this.normaService.modificarNormaItem(pgimNormaItemDTO, pgimNormaItemActual, this.obtenerAuditoria());
        	
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el ítem");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El ítem ha sido modificado");
        respuesta.put("pgimNormaItemDTO", pgimNormaItemDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    /**
	 * Permite eliminar el ítem de norma.
	 * 
	 * @param idItem
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('no-item_EL')")
	@PostMapping("/eliminarItemNorma/idItem/{id}")
	public ResponseEntity<ResponseDTO> eliminarItemNorma(@PathVariable("id") Long idItem) throws Exception {

		try {
			ArrayList<Long> idItemsEliminados = new ArrayList<Long>();
			ArrayList<Long> rpta = this.normaService.eliminarItemEHijos(idItem, this.obtenerAuditoria(), idItemsEliminados);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",  rpta,
					"Ítem de la norma y/o ítems hijos eliminado correctamente"));
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDTO("error",
							"Error al intentar eliminar el ítem de la norma: ",
							e.getMostSpecificCause().getMessage()));

		}
	}
	
	@PreAuthorize("hasAnyAuthority('ct-lista_AC')")
    @PostMapping("/listarTipificacion")
    public ResponseEntity<Page<PgimNormaDTO>> listarTipificacion(@RequestBody final PgimNormaDTO filtro, final Pageable paginador) throws Exception{

        final Page<PgimNormaDTO> nor = this.normaService.listarTipificacion(filtro, paginador);
		return new ResponseEntity<Page<PgimNormaDTO>>(nor, HttpStatus.OK);
    }

	@PreAuthorize("hasAnyAuthority('ct-lista_EL')")
    @DeleteMapping("/eliminarTipificacion/{idNorma}")
    public ResponseEntity<?> eliminarTipificacion(@PathVariable Long idNorma) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimNorma pgimItemTipificacionActual = null;

        try {
            pgimItemTipificacionActual = this.normaService.getByIdNorma(idNorma);

            if (pgimItemTipificacionActual == null) {
                mensaje = String.format("El item de tipificación %s que intenta eliminar no existe en la base de datos",
                idNorma);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el item de tipificación a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.normaService.eliminarTipificacion(pgimItemTipificacionActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar un item de tipificación");
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
    @GetMapping("/obtenerTipificacionPorId/{idNorma}")
    public ResponseEntity<?> obtenerTipificacionPorId(@PathVariable Long idNorma) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimNormaDTO pgimNormaDTO = null;

        try {
        	pgimNormaDTO = this.normaService.obtenerTipificacionPorId(idNorma);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimNormaDTO == null) {
            mensaje = String.format("La norma con el id: %d no existe en la base de datos", idNorma);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La norma ha sido recuperada");
        respuesta.put("pgimNormaDTO", pgimNormaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerItemTipificacionPorId/{idItemTipificacion}")
    public ResponseEntity<?> obtenerItemTipificacionPorId(@PathVariable Long idItemTipificacion) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimItemTipificacionDTO pgimItemTipificacionDTO = null;

        try {
        	pgimItemTipificacionDTO = this.normaService.obtenerItemTipificacionPorId(idItemTipificacion);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimItemTipificacionDTO == null) {
            mensaje = String.format("La norma con el id: %d no existe en la base de datos", idItemTipificacion);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La norma ha sido recuperada");
        respuesta.put("pgimItemTipificacionDTO", pgimItemTipificacionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ct-item_AC')")
    @PostMapping("/listarItemTipificacion")
    public ResponseEntity<Page<PgimItemTipificacionDTO>> listarItemTipificacion(@RequestBody final Long idNorma, final Pageable paginador) throws Exception{

        Page<PgimItemTipificacionDTO> lPgimItemTipificacionDTO = this.normaService.listarItemTipificacion(idNorma, paginador);
        return new ResponseEntity<Page<PgimItemTipificacionDTO>>(lPgimItemTipificacionDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ct-item_EL')")
    @DeleteMapping("/eliminarItemTipificacion/{idItemTipificacion}")
    public ResponseEntity<?> eliminarItemTipificacion(@PathVariable Long idItemTipificacion) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimItemTipificacion pgimItemTipificacionActual = null;

        try {
            pgimItemTipificacionActual = this.normaService.getByIdItemTipificacion(idItemTipificacion);

            if (pgimItemTipificacionActual == null) {
                mensaje = String.format("El item de tipificación %s que intenta eliminar no existe en la base de datos",
                idItemTipificacion);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el item de tipificación a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.normaService.eliminarItemTipificacion(pgimItemTipificacionActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar un item de tipificación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /***
	 * Permite crear un item de tipificación.
	 * 
	 * @param pgimItemTipificacionDTO Portador de los datos para la creación del
	 *                                item de tipificación.
	 * @param resultadoValidacion     Resultado de la validación aplicada a nivel de
	 *                                la entidad del modelo de datos.
	 * @return
	 * @throws Exception
	 */
    @PreAuthorize("hasAnyAuthority('ct-item_IN')")
	@PostMapping("/crearItemTipificacion")
    public ResponseEntity<?> crearItemTipificacion(@Valid @RequestBody PgimItemTipificacionDTO pgimItemTipificacionDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimItemTipificacionDTO pgimItemTipificacionDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el item de tipificación");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimItemTipificacionDTOCreado = this.normaService.crearItemTipificacion(pgimItemTipificacionDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el item de tipificación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El item de tipificación ha sido creada");
        respuesta.put("pgimItemTipificacionDTO", pgimItemTipificacionDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
	 * Permite modificar un item de tipificación
	 * 
	 * @param pgimItemTipificacionDTO
	 * @return
	 * @throws Exception
	 */
    @PreAuthorize("hasAnyAuthority('ct-item_MO')")
	@PutMapping("/modificarItemTipificacion")
    public ResponseEntity<?> modificarItemTipificacion(
            @Valid @RequestBody PgimItemTipificacionDTO pgimItemTipificacionDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimItemTipificacion pgimItemTipificacionActual = null;
        PgimItemTipificacionDTO pgimItemTipificacionDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el item de tipificación");
            respuesta.put("error", errores.toString());
            log.error("Error al modificar el item de tipificación: " + errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimItemTipificacionActual = this.normaService
                    .getByIdItemTipificacion(pgimItemTipificacionDTO.getIdItemTipificacion());

            if (pgimItemTipificacionActual == null) {
                mensaje = String.format("El item de tipificación %s que intenta actualizar no existe en la base de datos",
                        pgimItemTipificacionDTO.getIdItemTipificacion());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {

			log.error(e.getMostSpecificCause().getMessage(), e);

            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el item de tipificación a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimItemTipificacionDTOModificado = this.normaService.modificarItemTipificacion(pgimItemTipificacionDTO,
                    pgimItemTipificacionActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {

			log.error(e.getMostSpecificCause().getMessage(), e);

            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el item de tipificación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El item de tipificación ha sido modificado");
        respuesta.put("pgimItemTipificacionDTO", pgimItemTipificacionDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
	

	/**
	 * Permite obtener un listado de las Normas según una palabra clave ingresada
	 * requerido en campos autocomplete del frontend 
	 * con metodo post
	 * @param palabra
	 * @return
	 */
	@PostMapping("/filtrar/palabraClavePost")
	public ResponseEntity<?> listarPorPalabraClavePost(@RequestBody String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimNormaDTO> lPgimNormaDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimNormaDTO = new ArrayList<PgimNormaDTO>();
			respuesta.put("mensaje", "Se encontraron las normas");
			respuesta.put("lPgimNormaDTO", lPgimNormaDTO);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		
		try {
			lPgimNormaDTO = this.normaService.listarPorPalabraClave(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las normas");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron las normas");
		respuesta.put("lPgimNormaDTO", lPgimNormaDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
    /**
     * Permite consultar la lista de normas de acuerdo con al titulo corto.
     * @param pgimNormaAuxDTO
     * @return
     */
	@PostMapping("/listarNormaPorNoCortoYTipoNorma")
	public ResponseEntity<ResponseDTO> listarNormaPorNoCortoYTipoNorma(@RequestBody PgimNormaAuxDTO pgimNormaAuxDTO) {

        ResponseDTO responseDTO = null;
        List<PgimNormaDTO> lPgimNormaDTO = null;
        String mensaje;

        try {

        	lPgimNormaDTO = this.normaService.listarNormaPorNoCortoONoNormaYTipoNorma(pgimNormaAuxDTO);

        } catch (DataAccessException e) {
        	log.error("Ocurrió un error al realizar la consulta de las normas");
            log.error(e.getMostSpecificCause().getMessage(), e);

            mensaje = String.format(
                    "Ocurrió un error al realizar la consulta de las normas",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
        	log.error("Ocurrió un error intentar recuperar el listado de normas");
            log.error(e.getMessage(), e);

            mensaje = String.format(
                    "Ocurrió un error intentar recuperar el listado de normas",
                    e.getMessage());

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lPgimNormaDTO, "La lista normas se ha recuperado");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
	
    /**
     * Permite consultar la lista de normas de acuerdo con al titulo.
     * @param pgimNormaAuxDTO
     * @return
     */
	@PostMapping("/listarNormaPorNombreYTipoNorma")
	public ResponseEntity<ResponseDTO> listarNormaPorNombreYTipoNorma(@RequestBody PgimNormaAuxDTO pgimNormaAuxDTO) {

        ResponseDTO responseDTO = null;
        List<PgimNormaDTO> lPgimNormaDTO = null;
        String mensaje;

        try {

        	lPgimNormaDTO = this.normaService.listarNormaPorNoCortoONoNormaYTipoNorma(pgimNormaAuxDTO);

        } catch (DataAccessException e) {
        	log.error("Ocurrió un error al realizar la consulta de las normas");
            log.error(e.getMostSpecificCause().getMessage(), e);

            mensaje = String.format(
                    "Ocurrió un error al realizar la consulta de las normas",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
        	log.error("Ocurrió un error intentar recuperar el listado de normas");
            log.error(e.getMessage(), e);

            mensaje = String.format(
                    "Ocurrió un error intentar recuperar el listado de normas",
                    e.getMessage());

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lPgimNormaDTO, "La lista normas se ha recuperado");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

	
	/**
	 * Permite obtener la lista de items de la norma por ID de norma
	 * para seleccionar y crear obligaciones
	 * 
	 * @param idNorma
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listarItemsNormaParaObligacion/{idNorma}")
	public ResponseEntity<?> listarItemsNormaParaObligacion(
			@PathVariable Long idNorma) throws Exception {
		
		Map<String, Object> respuesta = new HashMap<>();
		List<PgimNormaItemAuxDTO> lPgimNormaItemAuxDTO = null;
		
		try {
			lPgimNormaItemAuxDTO = this.normaService.listarItemsNormaParaObligacion(idNorma);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las normas");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron las normas");
		respuesta.put("lPgimNormaDTO", lPgimNormaItemAuxDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/listarItemsNormaParaObligacionFilter")
    public ResponseEntity<Page<PgimNormaItemAuxDTO>> listarItemsNormaParaObligacionFilter(@RequestBody PgimNormaItemAuxDTO filtroPgimNormaItemAuxDTO, final Pageable paginador) throws Exception{

        Page<PgimNormaItemAuxDTO> pPgimNormaItemAuxDTO = this.normaService.listarItemsParaObligacionFilter(filtroPgimNormaItemAuxDTO, paginador);
        return new ResponseEntity<Page<PgimNormaItemAuxDTO>>(pPgimNormaItemAuxDTO, HttpStatus.OK);
    }
	
	@PostMapping("/obtenerItemNormasHijosPorUbicacion")
    public ResponseEntity<List<PgimNormaItemAuxDTO>> obtenerItemNormasHijosPorUbicacion(@RequestBody PgimNormaItemAuxDTO filtroPgimNormaItemAuxDTO) throws Exception{

        List<PgimNormaItemAuxDTO> pPgimNormaItemAuxDTO = this.normaService.obtenerItemNormasHijosPorUbicacion(filtroPgimNormaItemAuxDTO);
        
        return new ResponseEntity<List<PgimNormaItemAuxDTO>>(pPgimNormaItemAuxDTO, HttpStatus.OK);
    }
	
	/**
	 * Permite obtener el listado de normas item de una obligación fiscalizable
	 * @param filtroPgimOblgcnNrmtvaItemDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/obtenerItemNormasHijosPorUbicacionPorObligacionNormativa")
    public ResponseEntity<List<PgimOblgcnNrmtvaItemDTO>> obtenerItemNormasHijosPorUbicacionPorObligacionNormativa(@RequestBody PgimOblgcnNrmtvaItemDTO filtroPgimOblgcnNrmtvaItemDTO) throws Exception{

        List<PgimOblgcnNrmtvaItemDTO> pgimOblgcnNrmtvaItemDTO = this.normaService.obtenerItemNormasHijosPorUbicacionPorObligacionNormativa(filtroPgimOblgcnNrmtvaItemDTO);
        
        return new ResponseEntity<List<PgimOblgcnNrmtvaItemDTO>>(pgimOblgcnNrmtvaItemDTO, HttpStatus.OK);
    }
	
	
    @PostMapping("/listarItemTipificacionReporte")
    public ResponseEntity<Page<PgimItemTipificacionDTO>> listarItemTipificacionReporte(@RequestBody final PgimItemTipificacionDTO filtro, final Pageable paginador) throws Exception{

        Page<PgimItemTipificacionDTO> lPgimItemTipificacionDTO = this.normaService.listarItemTipificacionReportePaginado(filtro, paginador);
        return new ResponseEntity<Page<PgimItemTipificacionDTO>>(lPgimItemTipificacionDTO, HttpStatus.OK);
    }
    
    @GetMapping("/listarCuadroTipificacion")
    public ResponseEntity<?> listarCuadroTipificacion() throws Exception {
        Map<String, Object> respuesta = new HashMap<>();

        List<PgimNormaDTO> pgimNormaDTO = null;

        try {
        	pgimNormaDTO = this.normaService.listarCuadroTipificacion();
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el listado de norma");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        
        respuesta.put("mensaje", "La norma ha sido recuperada");
        respuesta.put("pgimNormaDTO", pgimNormaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
}
