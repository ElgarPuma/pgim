package pe.gob.osinergmin.pgim.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.services.ParametroService;

/**
 * Controlador para la gestión de parametros
 * 
 * @descripción: Parametros
 *
 * @author: Pablo
 * @version: 1.0
 * @fecha_de_creación: 18/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/parametros")
public class ParametroController extends BaseController {
	@Autowired
	private ParametroService parametroService;
	
    @GetMapping("/listarParametros")
    public ResponseEntity<?> listarParametros() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimParametroDTO> lPgimParametroDTO = null;

        try {
        	lPgimParametroDTO = this.parametroService.listarParametros();

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
        }

        respuesta.put("mensaje", "Se obtuvieron los parámetros");
        respuesta.put("lPgimParametroDTO", lPgimParametroDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }	
    
    @PostMapping("/listarValoresActivosParametro")
	public ResponseEntity<?> listarValoresActivosParametro(@RequestBody PgimParametroDTO pgimParametroDTO) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimValorParametroDTO> lPgimValorParametroDTO = null;		
		
		try {
			lPgimValorParametroDTO = this.parametroService.listarValoresActivosParametro(pgimParametroDTO.getIdParametro());			
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los valores del parámetro");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los valores del parámetro");
		respuesta.put("lPgimValorParametroDTO", lPgimValorParametroDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
    
    @PostMapping("/listarValoresNoActivosParametro")
	public ResponseEntity<?> listarValoresNoActivosParametro(@RequestBody PgimParametroDTO pgimParametroDTO) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimValorParametroDTO> lPgimValorParametroDTO = null;		
		
		try {
			lPgimValorParametroDTO = this.parametroService.listarValoresNoActivosParametro(pgimParametroDTO.getIdParametro());			
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los valores del parámetro");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los valores del parámetro");
		respuesta.put("lPgimValorParametroDTO", lPgimValorParametroDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
        
    /**
	 * Permite crear un valor de un parámetro 
	 * @param pgimValorParametroDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/crearValorParametro")
	public ResponseEntity<?> crearValorParametro(@Valid @RequestBody PgimValorParametroDTO pgimValorParametroDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimValorParametroDTO pgimValorParametroDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el valor");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimValorParametroDTOCreado = this.parametroService.crearValorParametro(pgimValorParametroDTO, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			respuesta.put("mensaje", "Ocurrió un error al intentar crear el valor");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El valor ha sido creado");
		respuesta.put("pgimValorParametroDTO", pgimValorParametroDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	
	/**
     * Permite modificar el valor de un parámetro, valida la existencia del valor
     * 
     * @param pgimValorParametroDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarValorParametro")
    public ResponseEntity<?> modificarValorParametro(
            @Valid @RequestBody PgimValorParametroDTO pgimValorParametroDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimValorParametro pgimValorParametroActual = null;
        PgimValorParametroDTO pgimValorParametroDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el valor");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimValorParametroActual = this.parametroService.getByIdValorParametro(pgimValorParametroDTO.getIdValorParametro());

            if (pgimValorParametroActual == null) {
                mensaje = String.format("El valor %s que intenta actualizar no existe en la base de datos",
                		pgimValorParametroDTO.getIdValorParametro());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el valor a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        
        try {
        	pgimValorParametroDTOModificado = this.parametroService.modificarValorParametro(pgimValorParametroDTO, pgimValorParametroActual, this.obtenerAuditoria());
        	
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el valor");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El valor ha sido modificado");
        respuesta.put("pgimValorParametroDTO", pgimValorParametroDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    @PutMapping("/desactivarValor/idValorParametro/{idValorParametro}")
    public ResponseEntity<?> desactivarValor(@PathVariable Long idValorParametro) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimValorParametro pgimValorParametroActual = null;

        try {
            pgimValorParametroActual = this.parametroService.getByIdValorParametro(idValorParametro);

            if (pgimValorParametroActual == null) {
                mensaje = String.format("El valor %s que intenta desactivar no existe en la base de datos",
                idValorParametro);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar desactivar el valor");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.parametroService.desactivarValor(pgimValorParametroActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar desactivar el valor");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @PutMapping("/activarValor/idValorParametro/{idValorParametro}")
    public ResponseEntity<?> activarValor(@PathVariable Long idValorParametro) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimValorParametro pgimValorParametroActual = null;

        try {
            pgimValorParametroActual = this.parametroService.getByIdValorParametro(idValorParametro);

            if (pgimValorParametroActual == null) {
                mensaje = String.format("El valor %s que intenta activar no existe en la base de datos",
                idValorParametro);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar activar el valor");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.parametroService.activarValor(pgimValorParametroActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar activar el valor");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/obtenerValorParametroPorCoClaveTexto/{coClaveTexto}")
    public ResponseEntity<?> obtenerValorParametroPorCoClaveTexto(@PathVariable String coClaveTexto) {

        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;
        Long idValorParametro = null;
        PgimValorParametroDTO pgimValorParametroActual = null;
        
        try {
        	idValorParametro = this.parametroService.obtenerIdValorParametroDesdeClaveTexto(coClaveTexto);
        	
        	if (idValorParametro == null) {
                mensaje = String.format("El valor con clave %s que intenta obtener no existe en la base de datos",
                		coClaveTexto);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        	
        	pgimValorParametroActual = this.parametroService.obtenerValorParametroPorId(idValorParametro);

            if (pgimValorParametroActual == null) {
                mensaje = String.format("El valor con clave %s que intenta obtener no existe en la base de datos",
                		coClaveTexto);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el valor a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }                

        respuesta.put("mensaje", "Se obtuvo el valor del parámetro");
        respuesta.put("pgimValorParametroDTO", pgimValorParametroActual);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/obtenerIdValorParametroPorCoClaveTexto/{coClaveTexto}")
    public Long obtenerIdValorParametroPorCoClaveTexto(@PathVariable String coClaveTexto) {
        Long idValorParametro = null;
        
        try {
        	idValorParametro = this.parametroService.obtenerIdValorParametroDesdeClaveTexto(coClaveTexto);
        } catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
            return null;
        }                
        return idValorParametro;
    }
    
    @GetMapping("/obtenerValorParametroPorIdParametro/{idParametro}")
    public ResponseEntity<?> obtenerValorParametroPorIdParametro(@PathVariable Long idParametro) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTO = null;
        try {

            lPgimValorParametroDTO= this.parametroService.listarValoresParametro(idParametro);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los valores de parámetro");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todos los valores de parámetro");

        respuesta.put("lPgimValorParametroDTO", lPgimValorParametroDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
	 * Permite verificar si existe algún valor de parametro con la coClaveTexto proporcionada.
	 * De existir se devolverá una lista con todas las coincidencias, de lo
	 * contrario, se retormará una lista vacía.
	 * 
	 * @param idAccidentado   Identificador interno del accidentado.
	 * @param idTipoDocumento Identifidcador interno del tipo de documento del
	 *                        accidentado.
	 * @param numeroDocumento Número del documento de identidad del accidentado.
	 * @return
	 */
	@GetMapping("/existeCoClaveTexto/{idValorParametro}/{coClaveTexto}")
	public ResponseEntity<?> existeCoClaveTexto(@PathVariable Long idValorParametro, @PathVariable String coClaveTexto) {
		System.out.println("idValorParametro: "+idValorParametro);
		if (idValorParametro == 0) {
			idValorParametro = null;
        }
		
		Map<String, Object> respuesta = new HashMap<>();
		List<PgimValorParametroDTO> lPgimValorParametroDTO = null;

		try {
			lPgimValorParametroDTO = this.parametroService.existeCoClaveTexto(idValorParametro, coClaveTexto);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al verificar si ya exite la coClaveTexto.");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		respuesta.put("mensaje", "Si fue posible determinar la existencia de la coClaveTexto");
		respuesta.put("lPgimValorParametroDTO", lPgimValorParametroDTO);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
}
