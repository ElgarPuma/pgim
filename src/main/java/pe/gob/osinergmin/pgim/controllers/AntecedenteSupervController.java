package pe.gob.osinergmin.pgim.controllers;

import java.util.HashMap;
import java.util.LinkedList;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimAntecedenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAntecedenteSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSelectAntecedenteAddAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSelectAntecedenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimAntecedenteSuperv;
import pe.gob.osinergmin.pgim.models.repository.AntecedenteSupervRepository;
import pe.gob.osinergmin.pgim.services.AntecedenteSupervService;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.siged.TipoDocumento;
import pe.gob.osinergmin.pgim.siged.Tiposdocumento;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los antecedentes de la supervisión.
 * 
 * @descripción: Alerta
 *
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 22/08/2021
 */
@RestController
@Slf4j
@RequestMapping("/antecedenteSuperv")
public class AntecedenteSupervController extends BaseController {
	
	@Autowired
	private AntecedenteSupervService antecedenteSupervService;
	
	@Autowired
	AntecedenteSupervRepository antecedenteSupervRepository;
	
	@Autowired
	DocumentoService documentoService;
	
	@Autowired
	private ParametroService parametroService;

	@Autowired
	private FlujoTrabajoService flujoTrabajoService;
	
	/**
	 * Permite obtener el listado de documentos que serán usados como antecedentes, las cuales ya se encuentran registradas en sus respectivas tablas maestras
	 * que serán utilizados en el frontend en la  selección de antecedentes
	 * @param pgimSupervisionDTO
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarAntecedentesByUMAux")
    public ResponseEntity<Page<PgimSelectAntecedenteAuxDTO>> listarAntecedentesByUM(@RequestBody PgimSupervisionDTO pgimSupervisionDTO, Pageable paginador) 
    		throws Exception {

		Page<PgimSelectAntecedenteAuxDTO> pSeleccionAntecedente = this.antecedenteSupervService.listarSelecccionAntecedente(pgimSupervisionDTO, paginador);
		
		return new ResponseEntity<Page<PgimSelectAntecedenteAuxDTO>>(pSeleccionAntecedente, HttpStatus.OK);
    }
	
	/**
	 * Permite crear un antecedente a partir de la selección de antecedentes
	 * @param pgimSelectAntecedenteAddAuxDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/crearAntecedente")
	public ResponseEntity<?> crearAntecedenteDesdeSelectAntecedente(@Valid @RequestBody PgimSelectAntecedenteAddAuxDTO pgimSelectAntecedenteAddAuxDTO,
			BindingResult resultadoValidacion) throws Exception {
    	
    	
    	PgimSelectAntecedenteAuxDTO pgimSelectAntecedenteAuxDTOCreada = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear los antecedentes");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimSelectAntecedenteAuxDTOCreada = antecedenteSupervService.crearAntecedenteDesdeSelectAntecedente(pgimSelectAntecedenteAddAuxDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear los antecedentes");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Los antecedentes han sido creados");
        respuesta.put("PgimSelectAntecedenteAuxDTO", pgimSelectAntecedenteAuxDTOCreada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    /**
     * Permite el listado de los antecedentes ya registrados 
     * @param idSupervision
     * @param paginador
     * @return
     * @throws Exception
     */
	@PostMapping("/listarAntecedentes")
    public ResponseEntity<Page<PgimAntecedenteAuxDTO>> listarAntecedentes(@RequestBody Long idSupervision, Pageable paginador) 
    		throws Exception {

		Page<PgimAntecedenteAuxDTO> pPgimAntecedenteAuxDTO = this.antecedenteSupervService.listarAntecedentes(idSupervision, paginador, this.obtenerAuditoria());
		
		return new ResponseEntity<Page<PgimAntecedenteAuxDTO>>(pPgimAntecedenteAuxDTO, HttpStatus.OK);
    }
	
	/**
	 * Permite modificar un antecedente, registro los aspectos revisados del antecedente
	 * @param pgimAntecedenteSupervDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/modificarAntecedente")
	public ResponseEntity<?> modificarAntecedente(@Valid @RequestBody PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimAntecedenteSuperv pgimAntecedenteSupervActual = null;
		PgimAntecedenteSupervDTO pgimAntecedenteSupervDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el antecedente");
			respuesta.put("error", errores.toString());
			log.error("Error al modificar el ANTECEDENTE: " + errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimAntecedenteSupervActual = this.antecedenteSupervService.obtenerAntecedenteSupervPorId(pgimAntecedenteSupervDTO.getIdAntecedenteSuperv());

			if (pgimAntecedenteSupervActual == null) {
				mensaje = String.format("El antecedente %s que intenta actualizar no existe en la base de datos",
						pgimAntecedenteSupervDTO.getIdAntecedenteSuperv());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el antecedente a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimAntecedenteSupervDTOModificado = this.antecedenteSupervService.modificarAntecedente( pgimAntecedenteSupervDTO, pgimAntecedenteSupervActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el antecedente");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El antecedente ha sido modificado");
		respuesta.put("pgimAntecedenteSupervDTO", pgimAntecedenteSupervDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	
	/**
	 * Permite modificar un antecedente, cuando este antecedente es de tipo informe de fiscalización de categoria de documento Antecedente
	 * @param pgimAntecedenteSupervDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/modificarAntecedenteAdjuntado")
	public ResponseEntity<?> modificarAntecedenteAdjuntado(@Valid @RequestBody PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO,
			BindingResult resultadoValidacion) throws Exception {
		
		PgimAntecedenteSuperv pgimAntecedenteSupervActual = null;
		PgimAntecedenteSupervDTO pgimAntecedenteSupervDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();
		
		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;
			
			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());
			
			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el antecedente");
			respuesta.put("error", errores.toString());
			log.error("Error al modificar el ANTECEDENTE: " + errores.toString());
			
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
		try {
			pgimAntecedenteSupervActual = this.antecedenteSupervService.obtenerAntecedenteSupervPorId(pgimAntecedenteSupervDTO.getIdAntecedenteSuperv());
			
			if (pgimAntecedenteSupervActual == null) {
				mensaje = String.format("El antecedente %s que intenta actualizar no existe en la base de datos",
						pgimAntecedenteSupervDTO.getIdAntecedenteSuperv());
				respuesta.put("mensaje", mensaje);
				
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el antecedente a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			pgimAntecedenteSupervDTOModificado = this.antecedenteSupervService.modificarAntecedenteAdjuntado( pgimAntecedenteSupervDTO, pgimAntecedenteSupervActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el antecedente");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("mensaje", "El antecedente ha sido modificado");
		respuesta.put("pgimAntecedenteSupervDTO", pgimAntecedenteSupervDTOModificado);
		
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	
	/**
	 * Permite eliminar el antecedente
	 * 
	 * @param idAntecedenteSuperv
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/eliminarAntecedente/{idAntecedenteSuperv}")
	public ResponseEntity<ResponseDTO> eliminarAntecedente(@PathVariable("idAntecedenteSuperv") Long idAntecedenteSuperv) throws Exception {

		try {
			Long rpta = this.antecedenteSupervService.eliminarAntecedente(idAntecedenteSuperv, this.obtenerAuditoria());
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success", "Antecedente eliminado correctamente", rpta));
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDTO("error", "Error al intentar eliminar el antecedente: ", e.getMostSpecificCause().getMessage()));

		}
	}
	

	/**
	 * validar que todos los antecedentes han sido revisados
	 * @param idSupervision
	 * @return
	 */
    @GetMapping("/validarRevisionAntecedente/{idSupervision}")
    public ResponseEntity<?> validarRevisionAntecedente(@PathVariable Long idSupervision) {
        Map<String, Object> respuesta = new HashMap<>();

        Integer cantidadRevision = 0;
        Integer cantidadAntecedentes = 0;

        try {
        	cantidadRevision = this.antecedenteSupervService.validarRevisionAntecedente(idSupervision);
    		cantidadAntecedentes = this.antecedenteSupervService.cantidadAntecedentes(idSupervision);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar validar los antecedentes requeridos para la supervisión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El formato para revisión de antecedente ha sido validado");
        respuesta.put("cantidadRevision", cantidadRevision);
        respuesta.put("cantidadAntecedentes", cantidadAntecedentes);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
  
	/**
	 * Permite obtener las configuraciones necesarias para el listado de documentos del Siged.
	 * @param idInstanciaProceso
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/obtenerConfiguraciones/{idInstanciaProceso}")
	public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idInstanciaProceso) throws Exception {
			
		log.info("Inicio de solicitud para obtener las configuraciones iniciales entre ellos los tipos documentos del Siged");
		
		Map<String, Object> respuesta = new HashMap<>();
		ResponseDTO responseDTO = new ResponseDTO();
		List<TipoDocumento> lTipoDocumento = new LinkedList<TipoDocumento>();
		
		Map<String, Object> respuestaLog = new HashMap<>();
		String mensajeExcepcionGeneral = "Ocurrió un problema para obtener las configuraciones de antecedentes: ";

		respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
		
		try {
			
			Tiposdocumento tiposdocumento = this.documentoService.listarTipoDocsSiged();
			
			// Si hay un error en el consumo del servicio SIGED, debe enviar el error
			if (!tiposdocumento.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				mensajeExcepcionGeneral += tiposdocumento.getMessage();
				log.error(mensajeExcepcionGeneral);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral));
			}
			
			lTipoDocumento = tiposdocumento.getListTipoDocumento();
			
		} catch (DataAccessException e) {
			mensajeExcepcionGeneral += e.getMostSpecificCause().getMessage();
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" ); 
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral, 0));

		} catch (PgimException e) {
			
			TipoResultado tipoResultado = (e.getTipoResultado() == null) ? TipoResultado.WARNING : e.getTipoResultado();
			
			mensajeExcepcionGeneral += e.getMensaje();
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" );
			log.error(e.getMensaje(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, mensajeExcepcionGeneral));

		} catch (Exception e) {
			mensajeExcepcionGeneral += e.getMessage();
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" );
			log.error(e.getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral));
		}

		respuesta.put("lTipoDocumento", lTipoDocumento);

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Se encontraron las configuraciones.");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
    
    
	/**
	 * Obtener las configuraciones necesarias para editar un antecedente de 
	 * tipo informe de fiscalización de categoria antecedente
	 * @param idSupervision
	 * @return
	 */
    @GetMapping("/obtener/configurarEditAntecedenteSuperv/{idAntecedenteSuperv}")
    public ResponseEntity<?> configurarEditAntecedenteSuperv(@PathVariable Long idAntecedenteSuperv) {
        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOTipoSupervision = null;
        PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO = null;

        try {
    		pgimAntecedenteSupervDTO = this.antecedenteSupervService.obtenerAntecedenteSupervDTOPorId(idAntecedenteSuperv);
    		lPgimValorParamDTOTipoSupervision = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_SUPERVISION);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar obtener las configuraciones para editar un antecedente");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "las configuraciones para editar un antecedente");
        respuesta.put("lPgimValorParamDTOTipoSupervision", lPgimValorParamDTOTipoSupervision);
        respuesta.put("pgimAntecedenteSupervDTO", pgimAntecedenteSupervDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    


}
