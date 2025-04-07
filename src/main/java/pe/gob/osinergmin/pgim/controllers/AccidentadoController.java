package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSegAccidentadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimAccidentado;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.pido.PidoBeanOutRO;
import pe.gob.osinergmin.pgim.services.AccidentadoService;
import pe.gob.osinergmin.pgim.services.EmpresaEventoService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PidoService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Controlador del componente accidentado, en el portal se encuentra en la ruta info maestra/unidad minera/evento/accidentados,
 * este componente contiene los metodos necesarios para el crud y validaciones respectivas.
 * contiene los siguientes metodos:
 * listar
 * obtenerConfiguraciones
 * existeAccidentado
 * crearAccidentado
 * modificarAccidentado
 * delete
 * 
 * @descripción: Accidentado
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 *
 */
@RestController
@Slf4j
@RequestMapping("/accidentados")
public class AccidentadoController extends BaseController{

	@Autowired
	private AccidentadoService accidentadoService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private EmpresaEventoService empresaEventoService;

    @Autowired
    private PidoService pidoService;
    
    @Autowired
	private ValorParametroRepository valorParametroRepository;
	
	/**
	 * Permite listar los accidentados de un evento dado.
	 * 
	 * @param idEvento Identificador interno del evento.
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('um-ev-acci_AC')")
	@GetMapping("/listar/accident/{idEvento}")
	public ResponseEntity<List<PgimAccidentadoDTO>> listar(@PathVariable Long idEvento) {
		List<PgimAccidentadoDTO> acc = new ArrayList<>();

		acc = accidentadoService.listarAccidentado(idEvento);

		if (acc == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(acc);
	}

	/**
	 * Permite obtener las configuraciones necesarias para el formulario del
	 * accidentado.
	 * 
	 * @param idEvento      Identifidador del evento asociado al accidentadao.
	 * @param idAccidentado Identificador del accidentado; tiene valor distinto de
	 *                      cero cuando la persona accidentada ya fue registrada.
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('um-ev-acci_CO')")
	@GetMapping("/obtenerConfiguraciones/{idEvento}/{idAccidentado}")
	public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idEvento, @PathVariable Long idAccidentado) {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> lPgimValorParamDTOTipDocId = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOCatOcupa = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOTipSeguro = null;
		List<PgimEmpresaEventoDTO> lPgimEmpresaEventoDTO = null;

		List<PgimSegAccidentadoDTO> lPgimSegAccidentadoDTO = null;
		PgimAccidentadoDTO pgimAccidentadoDTO = null;

		try {
			lPgimValorParamDTOTipDocId = this.parametroService
					.listarValoresParametro(ConstantesUtil.PARAM_TDI_RUC_DNI_CE);

			// Se excluye el tipo de documento RUC, debido a que la persona accidentada no se registrará con RUC.
			if (lPgimValorParamDTOTipDocId.size() > 0) {
				PgimValorParametroDTO pgimValorParametroDTO2Remove = null;
				for (PgimValorParametroDTO pgimValorParametroDTO : lPgimValorParamDTOTipDocId) {
					if (pgimValorParametroDTO.getIdValorParametro() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.DOIDE_RUC.toString())) {
						pgimValorParametroDTO2Remove = pgimValorParametroDTO;
					}
				}

				if (pgimValorParametroDTO2Remove != null) {
					lPgimValorParamDTOTipDocId.remove(pgimValorParametroDTO2Remove);
				}
			}

			lPgimValorParamDTOCatOcupa = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_CATEGORIA_OCUPACIONAL);

			lPgimValorParamDTOTipSeguro = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_SEGURO);

			lPgimEmpresaEventoDTO = this.empresaEventoService.listarEmpresaEvento(idEvento);

			if (idAccidentado == 0) {
				lPgimSegAccidentadoDTO = new ArrayList<>();
			} else {
				lPgimSegAccidentadoDTO = this.accidentadoService.listarSegurosAccidentado(idAccidentado);
				pgimAccidentadoDTO = this.accidentadoService.obtenerAccidentado(idAccidentado);
				if (pgimAccidentadoDTO.getFeFallecimiento() != null) {
					pgimAccidentadoDTO.setFeFallecimientoDesc(CommonsUtil.convertirFechaACadena(
							pgimAccidentadoDTO.getFeFallecimiento(), ConstantesUtil.FORMATO_FECHA_CORTO));
				}
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

		respuesta.put("lPgimValorParamDTOTipDocId", lPgimValorParamDTOTipDocId);
		respuesta.put("lPgimValorParamDTOCatOcupa", lPgimValorParamDTOCatOcupa);
		respuesta.put("lPgimValorParamDTOTipSeguro", lPgimValorParamDTOTipSeguro);
		respuesta.put("lPgimEmpresaEventoDTO", lPgimEmpresaEventoDTO);

		respuesta.put("lPgimSegAccidentadoDTO", lPgimSegAccidentadoDTO);
		respuesta.put("pgimAccidentadoDTO", pgimAccidentadoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite verificar si existe algún accidentado con los datos proporcionados.
	 * De existir se devolverá una lista con todas las coincidencias, de lo
	 * contrario, se retormará una lista vacía.
	 * 
	 * @param idAccidentado   Identificador interno del accidentado.
	 * @param coClaveTexto	  Identifidcador interno del tipo de documento del
	 *                        accidentado.
	 * @param numeroDocumento Número del documento de identidad del accidentado.
	 * @return
	 */
	@GetMapping("/existeAccidentado/{idAccidentado}/{coClaveTexto}/{numeroDocumento}")
	public ResponseEntity<ResponseDTO> existeAccidentado(@PathVariable Long idAccidentado, @PathVariable String coClaveTexto,
			@PathVariable String numeroDocumento) {

		if (idAccidentado == 0) {
			idAccidentado = null;
		}

		ResponseDTO responseDTO = null;
		String mensajeExcepcion = null;
		List<PgimAccidentadoDTO> lPgimAccidentadoDTO = null;
		Map<String, Object> respuesta = new HashMap<>();

		Long idTipoDocumento = null;
		
		try {
			idTipoDocumento = valorParametroRepository.obtenerIdValorParametro(coClaveTexto);
			
			lPgimAccidentadoDTO = this.accidentadoService.existeAccidentado(idAccidentado, idTipoDocumento,
					numeroDocumento);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			mensajeExcepcion = "Ocurrió un error al realizar verificar si ya exite una persona accidentado: " + e.getMostSpecificCause().getMessage();

			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
		
		// Obtener datos de persona de la PIDO
		PidoBeanOutRO pido = null;
		if (idTipoDocumento.equals(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.DOIDE_DNI.toString())) && numeroDocumento.length() == 8 && lPgimAccidentadoDTO.size() == 0) {
			 try {
				pido = this.pidoService.procesaConsultarCiudadano(numeroDocumento, this.obtenerAuditoria());
			} catch (Exception e) {
				log.error(e.getMessage(), e);

				if (pido != null) {
					pido.setResultCode("0002");
					pido.setDeResultado("No se ha podido ejecutar la consulta al Reniec");				
				}				
			}
		}

		mensajeExcepcion = "Si fue posible determinar la existencia de la persona accidentada";
		respuesta.put("lPgimAccidentadoDTO", lPgimAccidentadoDTO);
		respuesta.put("IntegracionReniec", pido);

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, mensajeExcepcion);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	/***
	 * Permite crear la persona accidentada.
	 * 
	 * @param pgimAccidentadoDTO  Portador de los datos para la creación de la
	 *                            persona accidentada.
	 * @param resultadoValidacion Resultado de la validación aplicada a nivel de la
	 *                            entidad del modelo de datos.
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-ev-acci_IN')")
	@PostMapping("/crearAccidentado")
	public ResponseEntity<ResponseDTO> crearAccidentado(@Valid @RequestBody PgimAccidentadoDTO pgimAccidentadoDTO,
			BindingResult resultadoValidacion) throws Exception {

		ResponseDTO responseDTO = null;
		PgimAccidentadoDTO pgimAccidentadoDTOCreado = null;
		String mensajeExcepcion;

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			mensajeExcepcion = "Se han encontrado inconsistencias para crear a la persona accidentada: " + errores.toString();
			log.error(mensajeExcepcion);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimAccidentadoDTOCreado = this.accidentadoService.crearAccidentado(pgimAccidentadoDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			mensajeExcepcion = "Se han encontrado inconsistencias para crear la persona accidentada, vuelva a intentarlo y si el problema persiste, repórtelo al administrador del sistema.";
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimAccidentadoDTOCreado, "La persona accidentada ha sido creada");

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	/**
	 * Permite modificar los datos de la persona accidentada.
	 * 
	 * @param pgimAccidentadoDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-ev-acci_MO')")
	@PutMapping("/modificarAccidentado")
	public ResponseEntity<ResponseDTO> modificarAccidentado(@Valid @RequestBody PgimAccidentadoDTO pgimAccidentadoDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimAccidentado pgimAccidentadoActual = null;
		PgimAccidentadoDTO pgimAccidentadoDTOModificado = null;
		ResponseDTO responseDTO = null;
		String mensajeExcepcion;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			mensajeExcepcion = "Se han encontrado inconsistencias para modificar la persona accidentada: " + errores.toString();

			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimAccidentadoActual = this.accidentadoService.getByIdAccidentado(pgimAccidentadoDTO.getIdAccidentado());

			if (pgimAccidentadoActual == null) {
				mensajeExcepcion = String.format("La persona accidentada %s que intenta actualizar no existe en la base de datos", pgimAccidentadoDTO.getIdAccidentado());

				responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			mensajeExcepcion = "Ocurrió un error al intentar recuperar la persona accidentada a actualizar: " + e.getMostSpecificCause().getMessage();
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimAccidentadoDTOModificado = this.accidentadoService.modificarAccidentado(pgimAccidentadoDTO, pgimAccidentadoActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			mensajeExcepcion = "Se han encontrado inconsistencias para modificar a la persona accidentada: " + e.getMostSpecificCause().getMessage();
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar la persona accidentada");
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimAccidentadoDTOModificado, "La persona accidentada ha sido modificada");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	/**
	 * Permite eliminar la persona accidentada. Esta eliminación es lógica.
	 * 
	 * @param idAccidentado
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-ev-acci_EL')")
	@DeleteMapping("/eliminarAccidentado/{idAccidentado}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long idAccidentado) throws Exception {

		ResponseDTO responseDTO = null;
		String mensajeExcepcion;
		PgimAccidentado pgimAccidentadoActual = null;

		try {
			pgimAccidentadoActual = this.accidentadoService.getByIdAccidentado(idAccidentado);

			if (pgimAccidentadoActual == null) {
				mensajeExcepcion = String.format("La persona accidentada %s que intenta eliminar no existe en la base de datos", idAccidentado);

				responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			mensajeExcepcion = "Ocurrió un error intentar recuperar la persona accidentada a actualizar: " + e.getMostSpecificCause().getMessage();
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			this.accidentadoService.eliminarAccidentado(pgimAccidentadoActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			mensajeExcepcion = "Ocurrió un error intentar eliminar a la persona accidentada: " + e.getMostSpecificCause().getMessage();
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "La persona accidentada ha sido eliminada");

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

}
