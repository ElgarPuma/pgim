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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEventoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEventoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.NotFoundException;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.services.EventoService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador del componente evento, en el portal se encuentra en la ruta: info maestra/unidad minera/evento 
 * y en la ruta: info/maestra/agente supervisado/unidad minera/evento,
 * este componente contiene los metodos necesarios para el crud y validaciones respectivas.
 * contiene los siguientes metodos:
 * obtener
 * registrar
 * eliminar
 * listar
 * obtenerConfiguraciones
 * obtenerTipoEvento
 * crearEvento
 * modificarEvento
 * 
 * @descripción: Evento
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 *
 */
@RestController
@Slf4j
@RequestMapping("/evento")
public class EventoController extends BaseController {

	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private ParametroService parametroService;
	
	/**
	 * Permite obtener el evento, usado en el modo edición y consulta del frontend.
	 * @param idEvento
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('um-evento_CO')")
	@GetMapping("/obtener/idEvento/{id}")
	public ResponseEntity<PgimEventoDTO> obtener(@PathVariable("id") Long idEvento) {
		PgimEventoDTO evento = eventoService.obtenerEventoById(idEvento);
		if(evento.getIdEvento() == null) {
			throw new NotFoundException("ID NO ENCONTRADO: " + idEvento);
		}
		return new ResponseEntity<PgimEventoDTO>(evento, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<ResponseDTO> registrar(@RequestBody PgimEventoDTO eventoDTO) 
			throws PgimException {
		Long rpta = 0L;/*eventoService.registrarEvento(eventoDTO);*/	
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",
				"Evento registrado correctamente", rpta));
	}
	
	/**
     * Perminte eliminar el evento.
     * 
     * @param idEvento
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('um-evento_EL')")
    @PostMapping("/eliminar/idEvento/{id}")
    public ResponseEntity<ResponseDTO> eliminar(@PathVariable("id") Long idEvento) throws Exception {
		Long rpta = eventoService.eliminarEvento(idEvento, this.obtenerAuditoria());	
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",
				"Evento eliminado correctamente", rpta));
	}

	/**
	 * Permite obtener la lista de eventos usados en la tabla eventos del frontend.
	 * @param idUnidadMinera
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('um-evento_AC')")
	@GetMapping("/listar/evento/{idUnidadMinera}")
	public ResponseEntity<List<PgimEventoDTO>> listar(@PathVariable Long idUnidadMinera) {

		List<PgimEventoDTO> event = new ArrayList<>();

		event = eventoService.listarEvento(idUnidadMinera);

		if (event==null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(event);
	}
	
	/**
	 * Permite obtener las listas de tipo evento, agente causante, tipo accidente, 
	 * tipo incidente usadas en el frontend 
	 * @return
	 */
	@GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOTipoEvento = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOAgenteCausante = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoAccidente = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoIncidente = null;
        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
        //List<PgimEventoAuxDTO> lPgimEventoAuxDTOAnios = null;        
        
        try {
        	lPgimValorParamDTOTipoEvento = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_EVENTO);
        	lPgimValorParamDTOAgenteCausante = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_AGENTE_CAUSANTE);
        	lPgimValorParamDTOTipoAccidente = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ACCIDENTE);
        	lPgimValorParamDTOTipoIncidente = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_INCIDENTE);
        	lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
        	//lPgimEventoAuxDTOAnios = this.eventoService
            //        .listarAniosEvento();

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOTipoEvento", lPgimValorParamDTOTipoEvento);
        respuesta.put("lPgimValorParamDTOAgenteCausante", lPgimValorParamDTOAgenteCausante);
        respuesta.put("lPgimValorParamDTOTipoAccidente", lPgimValorParamDTOTipoAccidente);
        respuesta.put("lPgimValorParamDTOTipoIncidente", lPgimValorParamDTOTipoIncidente);
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
        //respuesta.put("lPgimEventoAuxDTOAnios", lPgimEventoAuxDTOAnios);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
	/**
	 * Permite obtener el tipo de evento, usado en la creación del evento en el frontend.
	 * @return
	 */
	@GetMapping("/obtenerTipoEvento")
    public ResponseEntity<?> obtenerTipoEvento() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOTipoEvento = null;
        
        try {
        	lPgimValorParamDTOTipoEvento = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_EVENTO);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOTipoEvento", lPgimValorParamDTOTipoEvento);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
	/**
     * Permite crear el evento.
     * 
     * @param pgimEventoDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('um-evento_IN')")
    @PostMapping("/crearEvento")
    public ResponseEntity<?> crearEvento(@Valid @RequestBody PgimEventoDTO pgimEventoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimEventoDTO pgimEventoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el evento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimEventoDTOCreado = eventoService.crearEvento(pgimEventoDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el evento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El evento ha sido creado");
        respuesta.put("pgimEventoDTO", pgimEventoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

	/**
     * Permite modificar el evento.
     * 
     * @param pgimEventoDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('um-evento_MO')")
    @PutMapping("/modificarEvento")
    public ResponseEntity<?> modificarEvento(@Valid @RequestBody PgimEventoDTO pgimEventoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimEventoDTO pgimEventoDTOModificado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el evento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        /*try {
        	pgimEventoDTOModificado = this.unidadMineraService
                    .getByIdUnidadMinera(pgimUnidadMineraDTO.getIdUnidadMinera());

            if (pgimUnidadMinerActual == null) {
                mensaje = String.format("La unidad minera %s que intenta actualizar no existe en la base de datos",
                        pgimUnidadMineraDTO.getIdUnidadMinera());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la unidad minera a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        try {
        	pgimEventoDTOModificado = this.eventoService.modificarEvento(pgimEventoDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el evento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El evento ha sido modificado");
        respuesta.put("pgimEventoDTO", pgimEventoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    
    /**
     * Permite obtener la lista de eventos según filtro y de manera paginada
     * 
     * @param filtroPgimEventoAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
	@PostMapping("/listarEventos")
	public ResponseEntity<Page<PgimEventoAuxDTO>> listarEventos(			
			@RequestBody PgimEventoAuxDTO filtroPgimEventoAuxDTO, Pageable paginador) throws Exception {
		
		Page<PgimEventoAuxDTO> lPgimEventoAuxDTO = this.eventoService.listarEventosReporte(filtroPgimEventoAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimEventoAuxDTO>>(lPgimEventoAuxDTO, HttpStatus.OK);
	}
	
	/**
	 * Permite obtener la lista total de eventos según filtro
	 * 
	 * @param filtroPgimEventoDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarEventosTotal")
	public ResponseEntity<List<PgimEventoAuxDTO>> listarEventosTotal(			
			@RequestBody PgimEventoAuxDTO filtroPgimEventoAuxDTO) throws Exception {
		
		List<PgimEventoAuxDTO> lPgimEventoAuxDTOTotal = this.eventoService.listarEventosTotal(filtroPgimEventoAuxDTO);
		
		return new ResponseEntity<List<PgimEventoAuxDTO>>(lPgimEventoAuxDTOTotal, HttpStatus.OK);
	}
	
}
