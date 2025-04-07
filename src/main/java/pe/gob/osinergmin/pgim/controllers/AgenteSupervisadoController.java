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
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.services.AgenteSupervisadoService;
import pe.gob.osinergmin.pgim.services.EstratoService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PersonaService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador del componente agente supervisado, en el portal se encuentra en la ruta info maestra/agente supervisado,
 * este componente contiene los metodos necesarios para el crud y validaciones respectivas.
 * contiene los siguientes metodos:
 * listarPorPalabraClave
 * obtenerAgenteSupervisado
 * obtenerConfiguracionesGenerales
 * crearAgenteSupervisado
 * modificarAgenteSupervisado
 * eliminarAgenteSupervisado
 * obtenerAgenteSupervisadoPorId
 * listarAgenteSupervisado
 * obtenerConfiguraciones
 * existeAgenteSupervisado
 * 
 * @descripción: Agente supervisado
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 *
 */
@RestController
@Slf4j
@RequestMapping("/agentessupervisados")
public class AgenteSupervisadoController extends BaseController{

	@Autowired
	private AgenteSupervisadoService agenteSupervisadoService;
	
	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private EstratoService estratoService;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
    private UnidadMineraRepository unidadMineraRepository;

	@GetMapping("/filtrar/palabraClave/{palabra}")
	public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimAgenteSupervisadoDTO> lPgimAgenteSupervisadoDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimAgenteSupervisadoDTO = new ArrayList<PgimAgenteSupervisadoDTO>();
			respuesta.put("mensaje", "Se encontraron los agentes supervisados");
			respuesta.put("lPgimAgenteSupervisadoDTO", lPgimAgenteSupervisadoDTO);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		
		try {
			lPgimAgenteSupervisadoDTO = this.agenteSupervisadoService.listarPorPalabraClave(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del agente supervisado");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los agentes supervisados");
		respuesta.put("lPgimAgenteSupervisadoDTO", lPgimAgenteSupervisadoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}


	/**
	 * Permite obtener el agente supervisado usado en la tarjetaAS del frontend
	 * @param idAgenteSupervisado
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('as-lista_CO')")
	@GetMapping("/obtenerAgenteSupervisado/{idAgenteSupervisado}")
    public ResponseEntity<?> obtenerAgenteSupervisado(@PathVariable Long idAgenteSupervisado) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = null;

        try {
            pgimAgenteSupervisadoDTO = this.agenteSupervisadoService.obtenerAgenteSupervisado(idAgenteSupervisado);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el agente fiscalizado");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimAgenteSupervisadoDTO == null) {
            mensaje = String.format("El agente fiscalizado con el id: %d no existe en la base de datos", idAgenteSupervisado);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El agente fiscalizado ha sido recuperado");
        respuesta.put("pgimAgenteSupervisadoDTO", pgimAgenteSupervisadoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }
	
	/**
	 * Permite obtener las listas estrato y tamaño empresa usadas en el frontend
	 * @return
	 */
	@GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEstratoDTO> lPgimEstratoDTO = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTamanioEmpresa = null;
        
        try {
        	lPgimEstratoDTO = estratoService.listarEstrato();
        	lPgimValorParamDTOTamanioEmpresa = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TAMANIO_EMPRESA);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEstratoDTO", lPgimEstratoDTO);
        respuesta.put("lPgimValorParamDTOTamanioEmpresa", lPgimValorParamDTOTamanioEmpresa);
        

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
	/**
     * Permite crear el agente supervisado
     * 
     * @param pgimAgenteSupervisadoDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('as-lista_IN')")
    @PostMapping("/crearAgenteSupervisado")
    public ResponseEntity<?> crearAgenteSupervisado(
            @Valid @RequestBody PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el agente fiscalizado");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimAgenteSupervisadoDTOCreado = agenteSupervisadoService.crearAgenteSupervisado(pgimAgenteSupervisadoDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el agente fiscalizado");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El agente fiscalizado ha sido creado");
        respuesta.put("pgimAgenteSupervisadoDTO", pgimAgenteSupervisadoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

	/**
     * Permite modificar el agente supervisado, valida la existencia de la persona y
     * el agente supervisado
     * 
     * @param pgimAgenteSupervisadoDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('as-lista_MO')")
    @PutMapping("/modificarAgenteSupervisado")
    public ResponseEntity<?> modificarAgenteSupervisado(
            @Valid @RequestBody PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimPersona pgimPersonaActual = null;
        PgimAgenteSupervisado pgimAgenteSupervisadoActual = null;
        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el agente fiscalizado");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimPersonaActual = this.personaService.getByIdPersona(pgimAgenteSupervisadoDTO.getIdPersona());

            if (pgimPersonaActual == null) {
                mensaje = String.format("La persona %s que intenta actualizar no existe en la base de datos",
                		pgimAgenteSupervisadoDTO.getIdPersona());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la persona a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        try {
        	pgimAgenteSupervisadoActual = agenteSupervisadoService.getByIdAgenteSupervisado(pgimAgenteSupervisadoDTO.getIdAgenteSupervisado());

            if (pgimAgenteSupervisadoActual == null) {
                mensaje = String.format("El agente fiscalizado %s que intenta actualizar no existe en la base de datos",
                		pgimAgenteSupervisadoDTO.getIdPersona());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el agente fiscalizado a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        try {
        	pgimAgenteSupervisadoDTOModificado = this.agenteSupervisadoService.modificarAgenteSupervisado(pgimAgenteSupervisadoDTO,
            		pgimPersonaActual, pgimAgenteSupervisadoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el agente fiscalizado");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El agente fiscalizado ha sido modificado");
        respuesta.put("pgimAgenteSupervisadoDTO", pgimAgenteSupervisadoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite eliminar el agente supervisado
     * 
     * @param idAgenteSupervisado
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('as-lista_EL')")
    @DeleteMapping("/eliminarAgenteSupervisado/{idAgenteSupervisado}")
    public ResponseEntity<ResponseDTO> eliminarAgenteSupervisado(@PathVariable Long idAgenteSupervisado) throws Exception {
    	ResponseDTO responseDTO = null;
        String mensaje;

        PgimAgenteSupervisado pgimAgenteSupervisadoActual = null;

        try {
        	pgimAgenteSupervisadoActual = this.agenteSupervisadoService.getByIdAgenteSupervisado(idAgenteSupervisado);

            if (pgimAgenteSupervisadoActual == null) {
                mensaje = String.format("El agente fiscalizado %s que intenta eliminar no existe en la base de datos",
                		idAgenteSupervisado);
                responseDTO = new ResponseDTO("mensaje", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar el agente fiscalizado a actualizar",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try { 
        	List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = unidadMineraRepository.listarUnidadMineraPorAgenteSupervisado(idAgenteSupervisado);
            if (lPgimUnidadMineraDTO.size() > 0) {
                mensaje = String.format(
                        "No se puede eliminar el agente fiscalizado, por que se encuentra asignada a " + lPgimUnidadMineraDTO.size() + " unidad(es) minera(s)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar el retiro de la unidad fiscalizable. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
        
        try {
            this.agenteSupervisadoService.eliminarAgenteSupervisado(pgimAgenteSupervisadoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {            
            mensaje = String.format("Ocurrió un error intentar eliminar el agente fiscalizado", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", "El agente fiscalizado fue eliminado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
     * Permite obtener el agente supervisado usado en el modo consulta y edición del frontend
     * @param idAgenteSupervisado
     * @return
     */
    @GetMapping("/obtenerAgenteSupervisadoPorId/{idAgenteSupervisado}")
    public ResponseEntity<?> obtenerAgenteSupervisadoPorId(@PathVariable Long idAgenteSupervisado) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = null;

        try {
        	pgimAgenteSupervisadoDTO = this.agenteSupervisadoService.obtenerAgenteSupervisadoPorId(idAgenteSupervisado);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el agente fiscalizado");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimAgenteSupervisadoDTO == null) {
            mensaje = String.format("El agente fiscalizado con el id: %d no existe en la base de datos", idAgenteSupervisado);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El agente fiscalizado ha sido recuperado");
        respuesta.put("pgimAgenteSupervisadoDTO", pgimAgenteSupervisadoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	

/***
     * Permite obtener un listado de agentes supervisados en el contexto de la
     * paginación de resultados requerida en la lista en el frontend.
     * 
     * @param filtroAgenteSupervisado Objeto filtro que porta las propiedades que de
     *                           tener valor, representan criterios filtro
     *                           específicos esto siempre que la propiedad esté
     *                           configurada para aplicarse como criterio al momento
     *                           de las consultas.
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @return
     */
    @PreAuthorize("hasAnyAuthority('as-lista_AC')")
	@PostMapping("/filtrar")
    public ResponseEntity<Page<PgimAgenteSupervisadoDTO>> listarAgenteSupervisado(
		@RequestBody PgimAgenteSupervisadoDTO filtroAgenteSupervisado, Pageable paginador) {

        Page<PgimAgenteSupervisadoDTO> lPgimAgenteSupervisadoDTO = this.agenteSupervisadoService.filtrar(
			filtroAgenteSupervisado, paginador);
        return new ResponseEntity<Page<PgimAgenteSupervisadoDTO>>(lPgimAgenteSupervisadoDTO, HttpStatus.OK);
	}
	
	/***
     * Permite obtener las configuraciones necesarias para el listado de agentes
     * supervisados. Acá se incluye la configuración como: Estratos.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEstratoDTO> lPgimEstratoDTOEstrato = null;

        try {
            lPgimEstratoDTOEstrato = this.parametroService
                    .filtrarPorNombreEstrato(ConstantesUtil.NOMBRE_ESTRATO_AGENTE_SUPERVISADO);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de estrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEstratoDTOEstrato", lPgimEstratoDTOEstrato);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
    
    /**
     * Permite validar que el RUC no este siendo utilizado por otro agente supervisado 
     * @param idAgenteSupervisado
     * @param coDocumentoIdentidad
     * @return
     */
    @GetMapping("/existeAgenteSupervisado/{idAgenteSupervisado}/{coDocumentoIdentidad}")
    public ResponseEntity<?> existeAgenteSupervisado(@PathVariable Long idAgenteSupervisado,
            @PathVariable String coDocumentoIdentidad) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimAgenteSupervisadoDTO> lPgimAgenteSupervisadoDTO = null;

        if (idAgenteSupervisado == 0) {
        	idAgenteSupervisado = null;
        }

        try {
        	lPgimAgenteSupervisadoDTO = this.agenteSupervisadoService.existeAgenteSupervisado(idAgenteSupervisado, coDocumentoIdentidad);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite el agente fiscalizado");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia del agente fiscalizado");
        respuesta.put("lPgimAgenteSupervisadoDTO", lPgimAgenteSupervisadoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Permite obtener la lista preparada de agentes supervisados usado en reporte correspondiente
     * @param filtroAgenteSupervisado
     * @param paginador
     * @return
     */
    @PostMapping("/listarAgenteSupervisadoPaginado")
    public ResponseEntity<Page<PgimAgenteSupervisadoDTO>> listarReporteASPaginado(
		@RequestBody PgimAgenteSupervisadoDTO filtroAgenteSupervisado, Pageable paginador) {

        Page<PgimAgenteSupervisadoDTO> lPgimAgenteSupervisadoDTO = this.agenteSupervisadoService.filtrar(filtroAgenteSupervisado, paginador);
        
        return new ResponseEntity<Page<PgimAgenteSupervisadoDTO>>(lPgimAgenteSupervisadoDTO, HttpStatus.OK);
	}
}