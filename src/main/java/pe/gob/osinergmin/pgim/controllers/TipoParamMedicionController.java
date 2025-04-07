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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoParametroMed;
import pe.gob.osinergmin.pgim.services.TipoParamMedicionService;

/**
 * Controlador de tipo de parámetro de medición
 * 
 * @descripción: Controlador para la gestión de las funcionalidades relacionadas
 *               con los tipos de parámetro de medición
 *
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 12/09/2022
 */
@RestController
@Slf4j
@RequestMapping("/tipoParamMedicion")
public class TipoParamMedicionController extends BaseController{

    @Autowired
	private TipoParamMedicionService tipoParamMedicionService;

    /**
     * 
     * @param pgimTipoParametroMedDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/crearTipoParamMedicion")
    public ResponseEntity<ResponseDTO> crearTipoParamMedicion( @RequestBody PgimTipoParametroMedDTO pgimTipoParametroMedDTO) throws Exception {

        ResponseDTO responseDTO = null;        
        String mensaje;        
        PgimTipoParametroMedDTO pgimTipoParametroMedDTOCreado = null;
        
        try {
            pgimTipoParametroMedDTOCreado = this.tipoParamMedicionService.crearTipoParamMedicion(pgimTipoParametroMedDTO, this.obtenerAuditoria());
            mensaje = "Estupendo, el parámetro de medición ha sido creado";        

            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimTipoParametroMedDTOCreado, mensaje);

        } catch (final PgimException e) {
            log.error(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), 0));		

        } catch (DataAccessException e) {
            String mensajeAux = "Ocurrió un error al intentar crear un parámetro de medición"; 

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
            
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /**
     * 
     * @param pgimTipoParametroMedDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarTipoParamMedicion")
	public ResponseEntity<?> modificarTipoParamMedicion(
		@Valid @RequestBody PgimTipoParametroMedDTO pgimTipoParametroMedDTO, BindingResult resultadoValidacion)
			throws Exception {

        ResponseDTO responseDTO = null;  
        PgimTipoParametroMed pgimTipoParametroMedActual = null;
		PgimTipoParametroMedDTO pgimTipoParametroMedDTOModificado = null;
        String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el tipo parámetro de medición");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
        
        try {
            pgimTipoParametroMedActual = this.tipoParamMedicionService.getByIdTipoParametroMed(pgimTipoParametroMedDTO.getIdTipoParametroMed());
            if (pgimTipoParametroMedActual == null) {
                mensaje = String.format("El tipo de parámetro de medición %s que intenta actualizar no existe en la base de datos",
                pgimTipoParametroMedDTO.getIdTipoParametroMed());
                respuesta.put("mensaje", mensaje);

                responseDTO = new ResponseDTO(TipoResultado.ERROR, respuesta, mensaje);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
            }
        }  catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		} catch (DataAccessException e) {
            String mensajeAux = "Ocurrió un error intentar recuperar el tipo de parámetro de medición";
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

		try {
			pgimTipoParametroMedDTOModificado = this.tipoParamMedicionService
					.modificarTipoParamMedicion(pgimTipoParametroMedDTO, pgimTipoParametroMedActual, this.obtenerAuditoria());
            String mensajeAux = "El tipo de parámetro de medición ha sido modificado";          
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimTipoParametroMedDTOModificado, mensajeAux);

		}catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		} catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar modificar el tipo de parámetro de medición";
			log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

    /**
     * Permite eliminar un tipo de parámetro de medición
     * @param pgimTipoParametroMedDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/eliminarTipoParametro")
	public ResponseEntity<?> eliminarTipoParamMedicion(
		@Valid @RequestBody PgimTipoParametroMedDTO pgimTipoParametroMedDTO, BindingResult resultadoValidacion)
			throws Exception {

        ResponseDTO responseDTO = null;        
        PgimTipoParametroMed PgimTipoParametroMedActual = null;
		PgimTipoParametroMedDTO PgimTipoParametroMedDTOModificado = null;
        String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias al intentar eliminar el parámetro de medición.");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
        
        try {
            PgimTipoParametroMedActual = this.tipoParamMedicionService.getByIdTipoParametroMed(pgimTipoParametroMedDTO.getIdTipoParametroMed());
            if (PgimTipoParametroMedActual == null) {
                mensaje = String.format("El tipo de parámetro de medición %s que intenta eliminar no existe en la base de datos",
                pgimTipoParametroMedDTO.getIdTipoParametroMed());
                respuesta.put("mensaje", mensaje);

                responseDTO = new ResponseDTO(TipoResultado.ERROR, respuesta, mensaje);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);

            }
        }  catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		}  catch (DataAccessException e) {
            String mensajeAux = "Ocurrió un error intentar eliminar el tipo de parámetro de medición";
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

		try {
			PgimTipoParametroMedDTOModificado = this.tipoParamMedicionService
					.eliminarTipoParamMedicion(pgimTipoParametroMedDTO, PgimTipoParametroMedActual, this.obtenerAuditoria());
			String mensajeAux = "El tipo de parámetro de medición ha sido eliminado";          
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, PgimTipoParametroMedDTOModificado, mensajeAux);

		}catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		} catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar eliminar el tipo de parámetro de medición";
			log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

    
}
