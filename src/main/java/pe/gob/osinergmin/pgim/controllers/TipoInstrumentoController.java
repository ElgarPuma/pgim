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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoInstrumento;
import pe.gob.osinergmin.pgim.models.entity.PgimTprmtroXTinstrmnto;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.TipoInstrumentoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador de tipso de instrumentos de medición y sus parámetros
 * 
 * @descripción: Controlador para la gestión de las funcionalidades relacionadas
 *               con los tipos de instrumentos de medición y sus tipos
 *               parámetros
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 07/08/2022
 */
@RestController
@Slf4j
@RequestMapping("/tipoInstrumento")
public class TipoInstrumentoController extends BaseController {

    @Autowired
    private TipoInstrumentoService tipoInstrumentoService;

    @Autowired
    private ParametroService parametroService;


    /**
     * Permite obtener el listado de instrumentos de medición y sus parámetros por
     * especialidad
     * 
     * @param idEspecialidad
     * @param idContrato
     * @return
     */
    @GetMapping("/listarInstrumentosYParametros/{idEspecialidad}/{idContrato}")
    public ResponseEntity<ResponseDTO> listarInstrumentosYParametros(@PathVariable Long idEspecialidad,
            @PathVariable Long idContrato) {

        ResponseDTO responseDTO = null;
        List<PgimTprmtroXTinstrmntoDTO> lPgimTprmtroXTinstrmntoDTO = null;
        String mensaje;

        try {

            lPgimTprmtroXTinstrmntoDTO = this.tipoInstrumentoService
                    .listarInstrumentosYParametros(idEspecialidad, idContrato);

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            mensaje = String.format(
                    "Ocurrió un error intentar recuperar los tipos de parámetros e instrumentos de medición",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            mensaje = String.format(
                    "Ocurrió un error intentar recuperar los tipos de parámetros y tipos de instrumentos de la especialidad",
                    e.getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", lPgimTprmtroXTinstrmntoDTO,
                "La lista tipos de  parámetros y tipos de instrumentos de la especialidad se ha recuperado");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /**
     * Permite obtener la configuracion para el filtro del listado de tipo de instrumentos de medición 
     * 
     * @return
     * @throws Exception
     */
    @GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;

        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de especialidad");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);    

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener el listado de tipos de instrumentos de medición
     * 
     * @param pgimTipoInstrumentoDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('cfg006_AC')")
    @PostMapping("/listarTipoInstrumento")
    public ResponseEntity<Page<PgimTipoInstrumentoDTO>> listarTipoInstrumento(@RequestBody PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, Pageable paginador) throws Exception {

        Page<PgimTipoInstrumentoDTO> lPgimTipoInstrumentoDTO = this.tipoInstrumentoService.listarTipoInstrumento(pgimTipoInstrumentoDTO, paginador);

        return new ResponseEntity<Page<PgimTipoInstrumentoDTO>>(lPgimTipoInstrumentoDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener el listado de parámetros de medición
     * @param pgimTipoParametroMedDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('cfg008_AC')")
    @PostMapping("/listarParamMedicion")
    public ResponseEntity<Page<PgimTipoParametroMedDTO>> listarParamMedicion(@RequestBody PgimTipoParametroMedDTO pgimTipoParametroMedDTO, Pageable paginador) throws Exception {

        Page<PgimTipoParametroMedDTO> lPgimTipoParametroMedDTO = this.tipoInstrumentoService.listarParamMedicion(pgimTipoParametroMedDTO, paginador);

        return new ResponseEntity<Page<PgimTipoParametroMedDTO>>(lPgimTipoParametroMedDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener el listado de parámetros por instrumentos 
     * @param pgimTprmtroXTinstrmntoDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarParamXinstrumento")
    public ResponseEntity<Page<PgimTprmtroXTinstrmntoDTO>> listarParamXinstrumento(@RequestBody Long idTipoInstrumento, Pageable paginador) throws Exception {

        Page<PgimTprmtroXTinstrmntoDTO> lPgimTprmtroXTinstrmntoDTO = this.tipoInstrumentoService.listarParamXinstrumento(idTipoInstrumento, paginador);

        return new ResponseEntity<Page<PgimTprmtroXTinstrmntoDTO>>(lPgimTprmtroXTinstrmntoDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener un tipo de instrumento por el identificador
     * @param idTipoInstrumento
     * @return
     */
    @GetMapping("/obtenerTipoInstrumento/{idTipoInstrumento}")
    public ResponseEntity<?> obtenerTipoInstrumento(@PathVariable Long idTipoInstrumento) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO = null;

        try {
            pgimTipoInstrumentoDTO = this.tipoInstrumentoService.obtenerTipoInstrumento(idTipoInstrumento);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el tipo de instrumento de medición.");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimTipoInstrumentoDTO == null) {
            mensaje = String.format("El tipo de instrumento de medición con el id: %d no existe en la base de datos", idTipoInstrumento);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El tipo de instrumento de medición ha sido recuperado");
        respuesta.put("pgimTipoInstrumentoDTO", pgimTipoInstrumentoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite crear un tipo de instrumento
     * @param pgimTipoInstrumentoDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/crearTipoInstrumento")
    public ResponseEntity<ResponseDTO> crearTipoInstrumento( @RequestBody PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO) throws Exception {

        ResponseDTO responseDTO = null;        
        String mensaje;        
        PgimTipoInstrumentoDTO pgimTipoInstrumentoDTOCreado = null;
        
        try {
            pgimTipoInstrumentoDTOCreado = this.tipoInstrumentoService.crearTipoInstrumento(pgimTipoInstrumentoDTO, this.obtenerAuditoria());
            mensaje = "Estupendo, el instrumento de medición ha sido creado";        
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimTipoInstrumentoDTOCreado, mensaje);

        } catch (PgimException e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), 0));		
		} catch (DataAccessException e) {
            String mensajeAux = "Ocurrió un error al intentar crear un instrumento de medición"; 

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }  

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    /**
     * Permite modificar un tipo de instrumento
     * @param pgimTipoInstrumentoDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarTipoInstrumento")
	public ResponseEntity<?> modificarTipoInstrumento(
		@Valid @RequestBody PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, BindingResult resultadoValidacion)
			throws Exception {

        ResponseDTO responseDTO = null;        
        PgimTipoInstrumento pgimTipoInstrumentoActual = null;
		PgimTipoInstrumentoDTO pgimTipoParametroMedDTOModificado = null;
        String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el tipo de instrumento de medición");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
        
        try {
            pgimTipoInstrumentoActual = this.tipoInstrumentoService.getByIdTipoInstrumento(pgimTipoInstrumentoDTO.getIdTipoInstrumento());
            if (pgimTipoInstrumentoActual == null) {
                mensaje = String.format("El tipo de instrumento de medición %s que intenta actualizar no existe en la base de datos",
                pgimTipoInstrumentoDTO.getIdTipoInstrumento());
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
            String mensajeAux = "Ocurrió un error intentar recuperar el tipo de instrumento de medición";
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

		try {
			pgimTipoParametroMedDTOModificado = this.tipoInstrumentoService
					.modificarTipoInstrumento(pgimTipoInstrumentoDTO, pgimTipoInstrumentoActual, this.obtenerAuditoria());
			String mensajeAux = "El tipo de instrumento de medición ha sido modificado";          
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimTipoParametroMedDTOModificado, mensajeAux);

		} catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		}catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar modificar el tipo de instrumento de medición";
			log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

    /**
     * Permite registrar los parámetros de medición a un instrumento de interés
     * @param lPgimTprmtroXTinstrmntoDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/registrarTiposParamXinstr")
    public ResponseEntity<ResponseDTO> registrarTiposParamXinstr( @RequestBody PgimTprmtroXTinstrmntoDTO[] lPgimTprmtroXTinstrmntoDTO) throws Exception {

        ResponseDTO responseDTO = null;        
        String mensaje;        
        List<Object> pgimTipoInstrumentoDTOCreado = null;
        
        try {
            pgimTipoInstrumentoDTOCreado = this.tipoInstrumentoService.registrarTiposParamXinstr(lPgimTprmtroXTinstrmntoDTO, this.obtenerAuditoria());
            mensaje = "Estupendo, los parámetros de medición seleccionados se registraron correctamente";        
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimTipoInstrumentoDTOCreado, mensaje);

        } catch (PgimException e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), 0));		
		} catch (DataAccessException e) {
            String mensajeAux = "Ocurrió un error al intentar registrar los parámetros de medición"; 

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }  

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    /**
     * Permite quitar los parámetros de medición de un instrumento de interés.
     * @param pgimTprmtroXTinstrmntoDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/quitarTipoParamXinstr")
	public ResponseEntity<?> quitarTipoParamXinstr(
		@Valid @RequestBody PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTO, BindingResult resultadoValidacion)
			throws Exception {

        ResponseDTO responseDTO = null;        
        PgimTprmtroXTinstrmnto pgimTipoParamXinstrActual = null;
		PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTOModificado = null;
        String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias al intentar quitar el parámetro de medición.");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
        
        try {
            pgimTipoParamXinstrActual = this.tipoInstrumentoService.getByIdTipoParamXinstr(pgimTprmtroXTinstrmntoDTO.getIdTprmtroXTinstrmnto());
            if (pgimTipoParamXinstrActual == null) {
                mensaje = String.format("El tipo de parámetro de medición %s que intenta quitar no existe en la base de datos",
                pgimTprmtroXTinstrmntoDTO.getIdTipoInstrumento());
                respuesta.put("mensaje", mensaje);

                responseDTO = new ResponseDTO(TipoResultado.ERROR, respuesta, mensaje);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);

            }
        } catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		}  catch (DataAccessException e) {
            String mensajeAux = "Ocurrió un error intentar quitar el tipo de parámetro de medición";
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

		try {
			pgimTprmtroXTinstrmntoDTOModificado = this.tipoInstrumentoService
					.quitarTipoParamXinstr(pgimTprmtroXTinstrmntoDTO, pgimTipoParamXinstrActual, this.obtenerAuditoria());
			String mensajeAux = "El tipo de parámetro de medición ha sido quitado";          
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimTprmtroXTinstrmntoDTOModificado, mensajeAux);

		} catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		} catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar quitar el tipo de parámetro de medición";
			log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}


    /**
     * Permite la modificación de un parámetro de medición de un instrumento
     * @param pgimTprmtroXTinstrmntoDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarParamXinstr")
	public ResponseEntity<?> modificarParamXinstr(
		@Valid @RequestBody PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTO, BindingResult resultadoValidacion)
			throws Exception {

        ResponseDTO responseDTO = null;        
        PgimTprmtroXTinstrmnto pgimTprmtroXTinstrmntoActual = null;
		PgimTprmtroXTinstrmntoDTO PgimTprmtroXTinstrmntoDTOModificado = null;
        String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el tipo de instrumento de medición");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
        
        try {
            pgimTprmtroXTinstrmntoActual = this.tipoInstrumentoService.getByIdTipoParamXinstr(pgimTprmtroXTinstrmntoDTO.getIdTprmtroXTinstrmnto());
            if (pgimTprmtroXTinstrmntoActual == null) {
                mensaje = String.format("El tipo de parámetro de medición %s para el instrumento %s que intenta actualizar no existe en la base de datos",
                pgimTprmtroXTinstrmntoDTO.getIdTipoParametroMed(), pgimTprmtroXTinstrmntoDTO.getIdTipoInstrumento());
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
            String mensajeAux = "Ocurrió un error intentar recuperar el tipo de parámetro del instrumento de medición";
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

		try {
			PgimTprmtroXTinstrmntoDTOModificado = this.tipoInstrumentoService
					.modificarParamXinstr(pgimTprmtroXTinstrmntoDTO, pgimTprmtroXTinstrmntoActual, this.obtenerAuditoria());
			String mensajeAux = "El tipo de parámetro de medición para el instrumento ha sido modificado";          
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, PgimTprmtroXTinstrmntoDTOModificado, mensajeAux);

		} catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		}catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar modificar el tipo de parámetro de medición para el instrumento";
			log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

    /**
     * Permite eliminar un tipo de instrumento
     * @param pgimTipoInstrumentoDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/eliminarTipoInstrumento")
	public ResponseEntity<?> eliminarTipoInstrumento(
		@Valid @RequestBody PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, BindingResult resultadoValidacion)
			throws Exception {

        ResponseDTO responseDTO = null;        
        PgimTipoInstrumento pgimTipoInstrumentoActual = null;
		PgimTipoInstrumentoDTO PgimTipoInstrumentoDTOModificado = null;
        String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias al intentar eliminar el instrumento de medición.");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
        
        try {
            pgimTipoInstrumentoActual = this.tipoInstrumentoService.getByIdTipoInstrumento(pgimTipoInstrumentoDTO.getIdTipoInstrumento());
            if (pgimTipoInstrumentoActual == null) {
                mensaje = String.format("El tipo de instrumento de medición %s que intenta eliminar no existe en la base de datos",
                pgimTipoInstrumentoDTO.getIdTipoInstrumento());
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
            String mensajeAux = "Ocurrió un error intentar eliminar el tipo de instrumento de medición";
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

		try {
			PgimTipoInstrumentoDTOModificado = this.tipoInstrumentoService
					.eliminarTipoInstrumento(pgimTipoInstrumentoDTO, pgimTipoInstrumentoActual, this.obtenerAuditoria());
			String mensajeAux = "El tipo de instrumento de medición ha sido eliminado";          
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, PgimTipoInstrumentoDTOModificado, mensajeAux);

		} catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		} catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar eliminar el tipo de instrumento de medición";
			log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}


}
