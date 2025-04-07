package pe.gob.osinergmin.pgim.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.FiltroCotejoHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoCnsttdoFaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaHchocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.NotFoundException;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimCriterioSprvsion;
import pe.gob.osinergmin.pgim.models.entity.PgimHechoConstatado;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccion;
import pe.gob.osinergmin.pgim.services.HechoConstatadoService;
import pe.gob.osinergmin.pgim.services.MatrizSupervisionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PgimComponenteMinService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.services.CmineroSprvsionService;
import pe.gob.osinergmin.pgim.services.ComponenteHcService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los Hechos
 * Constatados y Matriz de la Supervisión
 * 
 * @descripción: métodos relacionados a los Hechos Constatados y Matriz de la
 *               Supervisión
 *
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 30/09/2020
 */
@RestController
@Slf4j
@RequestMapping("/hechoconstatado")
public class HechoConstatadoController extends BaseController {

	@Autowired
	private HechoConstatadoService hechoConstatadoService;

	@Autowired
	private CmineroSprvsionService cmineroSprvsionService;
	
	@Autowired
	private ComponenteHcService componenteHcService;
	
	@Autowired
	private PgimComponenteMinService componenteMinService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private SupervisionService supervisionService;

	@Autowired
	private MatrizSupervisionService matrizSupervisionService;
	
	@Autowired
	private FlujoTrabajoService flujoTrabajoService;

	/**
	 * Permite obtener los valores de los combos para el listado de la matriz de
	 * supervisión
	 * 
	 * @return Lista de tipo de cumplimiento.
	 */
	@GetMapping("/obtenerConfiguracionesMatriz")
	public ResponseEntity<?> obtenerConfiguracionesMatriz() {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> ltipoCumplimiento = null;
		List<PgimValorParametroDTO> ltipoCumplimientoCuadro = null;
		List<PgimValorParametroDTO> ltipoCumplimientoHechos = null;
		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO = null;

		try {
			ltipoCumplimiento = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_CUMPLIMIENTO);
			
			ltipoCumplimientoCuadro = this.parametroService.filtrarPorNombreParametroPorCuadro(ConstantesUtil.PARAM_TIPO_CUMPLIMIENTO);
			ltipoCumplimientoHechos = this.parametroService.filtrarPorNombreParametroPorHecho(ConstantesUtil.PARAM_TIPO_CUMPLIMIENTO);
			
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("ltipoCumplimiento", ltipoCumplimiento);
		respuesta.put("ltipoCumplimientoCuadro", ltipoCumplimientoCuadro);
		respuesta.put("ltipoCumplimientoHechos", ltipoCumplimientoHechos);
		respuesta.put("pgimMatrizSupervisionDTO", pgimMatrizSupervisionDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite obtener los valores de los combos para el listado de la matriz de
	 * supervisión
	 * 
	 * @return Lista de tipo de cumplimiento.
	 */
	@GetMapping("/obtenerCuadroVerificacionPorIdSupervision/{idSupervision}")
	public PgimMatrizSupervisionDTO obtenerCuadroVerificacionPorIdSupervision(@PathVariable("idSupervision") Long idSupervision) {

		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO = null;

		Map<String, Object> respuesta = new HashMap<>();

		try {
			pgimMatrizSupervisionDTO = this.matrizSupervisionService.obtenerCuadroVerificacionPorIdSupervision(idSupervision);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error intentar obtener el cuadro de verificación de la fiscalización");
			respuesta.put("error", e.getMessage());
			log.error(e.getMessage(), e);

			throw e;
		}

		return pgimMatrizSupervisionDTO;
	}

	/**
	 * Permite recuperar la lista de matriz de supervisión *
	 *
	 * @param idSupervision
	 * @param cogidoCumple
	 * 
	 * @return lista de matriz de supervisión.
	 * @throws Exception
	 */

	@PostMapping("/listarMatrizSupervision")
	public ResponseEntity<Page<PgimMatrizSupervisionAuxDTO>> listarMatrizSupervision(
			@RequestBody FiltroMatrizSupervisionDTO filtroMatrizSupervisionDTO, final Pageable paginador)
			throws Exception {
		
		
		if(filtroMatrizSupervisionDTO.getIdFaseSeleccion() < filtroMatrizSupervisionDTO.getIdFaseActual() && filtroMatrizSupervisionDTO.getIdFaseSeleccion() != ConstantesUtil.PARAM_SUPERVISION_PRE_SUPERVISION) { 
			List<PgimHechoCnsttdoFaseDTO> lstPgimCotejoHechoCnsttdoDTO = 
					this.hechoConstatadoService.listarHechoCnsttdoFase(filtroMatrizSupervisionDTO.getIdSupervision(), filtroMatrizSupervisionDTO.getIdFaseSeleccion(), null);

			if(lstPgimCotejoHechoCnsttdoDTO.size()==0) {
						//Generar data....
						this.hechoConstatadoService.generarDataHistoricaFase(filtroMatrizSupervisionDTO.getIdSupervision(),filtroMatrizSupervisionDTO.getIdFaseSeleccion()); 
					}
		}
		
		

		// Preparamos el orden de la matriz
		Sort sort = Sort.by("nuOrdenGrpoCrtrio");
		sort = sort.ascending();
		Sort sort2 = Sort.by("coMatrizCriterio");
		sort2 = sort2.ascending();
		sort = sort.and(sort2);

		// Preparamos un nuevo paginador
		Pageable paginador2 = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), sort);

		final Page<PgimMatrizSupervisionAuxDTO> lPgimMatrizSupervisionAux = this.hechoConstatadoService
				.listarMatrizSupervision(filtroMatrizSupervisionDTO.getIdSupervision(),
						filtroMatrizSupervisionDTO.getCodigoCumpleSelected(), filtroMatrizSupervisionDTO.getTipoMatriz(),
						filtroMatrizSupervisionDTO.getIdFaseSeleccion(),filtroMatrizSupervisionDTO.getIdFaseActual(), 
						filtroMatrizSupervisionDTO.getDeNorma(), filtroMatrizSupervisionDTO.getDeTipificacion(),
						paginador2);

		return new ResponseEntity<Page<PgimMatrizSupervisionAuxDTO>>(lPgimMatrizSupervisionAux, HttpStatus.OK);
	}

	/**
	 * Permite obtener un registro de la entidad PgimCriterioSprvsion a partir de un
	 * identificador de la tabla (ID)
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * 
	 */

	@GetMapping("/obtenerCriterioSprvsionById/idCriterioSprvsion/{id}")
	public ResponseEntity<PgimCriterioSprvsionDTO> obtenerCriterioSprvsionById(
			@PathVariable("id") Long idCriterioSprvsion) {
		PgimCriterioSprvsionDTO criterioSprvsionDTO = this.hechoConstatadoService
				.obtenerCriterioSprvsionById(idCriterioSprvsion);
		if (criterioSprvsionDTO.getIdCriterioSprvsion() == null) {
			throw new NotFoundException("ID NO ENCONTRADO: " + idCriterioSprvsion);
		}
		return new ResponseEntity<PgimCriterioSprvsionDTO>(criterioSprvsionDTO, HttpStatus.OK);
	}

	/**
	 * Permite recuperar la lista de hechos constatados por criterio de la matriz de
	 * supervision
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * 
	 */
	@PreAuthorize("hasAnyAuthority('sp-hc-heco_AC')")
	@GetMapping("/listarHechosConstatados/{idCriterioSprvsion}/{idRolProceso}")
	public ResponseEntity<List<PgimHechoConstatadoDTO>> listarHechosConstatados(@PathVariable Long idCriterioSprvsion,
			@PathVariable Long idRolProceso) {

		return ResponseEntity.ok(
				this.hechoConstatadoService.listarHechosConstatadosPorCriterioMatriz(idCriterioSprvsion, idRolProceso));
	}
	
	
	/**
	 * Permite recuperar la lista de hechos constatados por criterio de la matriz de
	 * supervision - histórico por fase
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * 
	 */
	@PreAuthorize("hasAnyAuthority('sp-hc-heco_AC')")
	@GetMapping("/listarHechosConstatadosPorCriterioMatrizHistPorFase/{idCriterioSprvsion}/{idFase}")
	public ResponseEntity<List<PgimHechoConstatadoDTO>> listarHechosConstatadosPorCriterioMatrizHistPorFase(@PathVariable Long idCriterioSprvsion,
			@PathVariable Long idFase) {

		return ResponseEntity.ok(
				this.hechoConstatadoService.listarHechosConstatadosPorCriterioMatrizHistPorFase(idCriterioSprvsion, idFase));
	}

	/**
	 * Permite obtener las listas para la configuración del diálogo de registro de
	 * hecho constatado
	 * 
	 * @param idMatrizCriterio
	 * @param idHechoConstatado
	 * @return
	 */
	@GetMapping("/obtenerConfiguracionesDHC/{idMatrizCriterio}/{idHechoConstatado}")
	public ResponseEntity<?> obtenerConfiguracionesDHC(@PathVariable("idMatrizCriterio") Long idMatrizCriterio,
			@PathVariable("idHechoConstatado") Long idHechoConstatado) {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> lPgimValorParamDTOTipoCumplimiento = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOTipoCumplimientoCuadro = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOTipoCumplimientoHecho = null;
		List<PgimObligacionNormaAuxDTO> lObligacionNorma = null;
		Long idSubcatDocumento = 0L;

		try {
			lPgimValorParamDTOTipoCumplimiento = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_CUMPLIMIENTO);

			lPgimValorParamDTOTipoCumplimientoCuadro = this.parametroService.filtrarPorNombreParametroPorCuadro(ConstantesUtil.PARAM_TIPO_CUMPLIMIENTO);
			
			lPgimValorParamDTOTipoCumplimientoHecho = this.parametroService.filtrarPorNombreParametroPorHecho(ConstantesUtil.PARAM_TIPO_CUMPLIMIENTO);
			// Ordenamiento
			Sort sort = Sort.by("deObligacionNormativa");
			sort = sort.ascending();

			if (idHechoConstatado == null || idHechoConstatado.equals(0L)) {
				// listar obligaciones normativas x criterio (cuando es un nuevo HC)
				lObligacionNorma = this.hechoConstatadoService.listarObligacionesNormativasXCriterio(idMatrizCriterio,
						sort);
			} else {
				// listar obligaciones normativas x criterio desde la entidad "Obligación
				// Normativa por H. Constatado" (cuando es una modificación de HC)
				lObligacionNorma = this.hechoConstatadoService.listarObligacionesNormativasPorHC(idHechoConstatado,
						sort);

			}

			PgimHechoConstatadoDTO hechoConstatado = this.hechoConstatadoService
					.obtenerHechoConstatadoDtoPorId(idHechoConstatado);

			if (hechoConstatado != null) {
				PgimSupervisionDTO pgimSupervisionDTO = this.supervisionService
						.obtenerSupervisionByIdSupervision(hechoConstatado.getIdSupervision());

				idSubcatDocumento = CommonsUtil.obtenerIdSubCatInformeSupervByTipoSuperv(pgimSupervisionDTO);
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimValorParamDTOTipoCumplimiento", lPgimValorParamDTOTipoCumplimiento);
		respuesta.put("lPgimValorParamDTOTipoCumplimientoCuadro", lPgimValorParamDTOTipoCumplimientoCuadro);
		respuesta.put("lPgimValorParamDTOTipoCumplimientoHecho", lPgimValorParamDTOTipoCumplimientoHecho); 
		respuesta.put("lObligacionNorma", lObligacionNorma);
		respuesta.put("idSubcatDocumento", idSubcatDocumento);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/***
	 * Permite crear el hecho constatado
	 * 
	 * @param pgimHechoConstatadoDTO Portador de los datos para la creación del
	 *                               hecho constatado.
	 * 
	 * @param resultadoValidacion    Resultado de la validación aplicada a nivel de
	 *                               la entidad del modelo de datos.
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-hc-heco_IN')")
	@PostMapping("/crearHC")
	public ResponseEntity<?> crearHC(@Valid @RequestBody PgimHechoConstatadoDTO pgimHechoConstatadoDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimHechoConstatadoDTO pgimHechoConstatadoDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear al hecho verificado");
			respuesta.put("error", errores.toString());
			respuesta.put("resultado", "Error");

			// return new ResponseEntity<Map<String, Object>>(respuesta,
			// HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			pgimHechoConstatadoDTOCreado = this.hechoConstatadoService.crearHC(pgimHechoConstatadoDTO,
					this.obtenerAuditoria());
			respuesta.put("resultado", "OK");
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear el hecho constatado");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			respuesta.put("resultado", "Error");
			log.error(e.getMostSpecificCause().getMessage(), e);

			// return new ResponseEntity<Map<String, Object>>(respuesta,
			// HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (PgimException e2) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear el hecho constatado");
			respuesta.put("error", e2.getMensaje());
			respuesta.put("resultado", "Error");
			log.error(e2.getMensaje(), e2);
			// return new ResponseEntity<Map<String, Object>>(respuesta,
			// HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		respuesta.put("mensaje", "El hecho verificado ha sido creado");
		respuesta.put("pgimHechoConstatadoDTO", pgimHechoConstatadoDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite modificar los datos de un hecho constatado.
	 * 
	 * @param pgimHechoConstatadoDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-hc-heco_MO')")
	@PutMapping("/modificarHC")
	public ResponseEntity<?> modificarHC(@Valid @RequestBody PgimHechoConstatadoDTO pgimHechoConstatadoDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimHechoConstatado pgimHechoConstatadoActual = null;
		PgimHechoConstatadoDTO pgimHechoConstatadoDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el hecho verificado");
			respuesta.put("error", errores.toString());
			respuesta.put("resultado", "Error");

			// return new ResponseEntity<Map<String, Object>>(respuesta,
			// HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			pgimHechoConstatadoActual = this.hechoConstatadoService
					.getByidHechoConstatado(pgimHechoConstatadoDTO.getIdHechoConstatado());

			if (pgimHechoConstatadoActual == null) {
				mensaje = String.format("El hecho verificado que intenta actualizar no existe en la base de datos",
						pgimHechoConstatadoDTO.getIdHechoConstatado());
				respuesta.put("mensaje", mensaje);
				respuesta.put("error", mensaje);
				respuesta.put("resultado", "Error");

				// return new ResponseEntity<Map<String, Object>>(respuesta,
				// HttpStatus.NOT_FOUND);
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el hecho verificado a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			respuesta.put("resultado", "Error");
			// log.error(e.getMostSpecificCause().getMessage(), e);

			// return new ResponseEntity<Map<String, Object>>(respuesta,
			// HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			pgimHechoConstatadoDTOModificado = this.hechoConstatadoService.modificarHC(pgimHechoConstatadoDTO,
					pgimHechoConstatadoActual, this.obtenerAuditoria());
			respuesta.put("resultado", "OK");
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar al hecho constatado");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			respuesta.put("resultado", "Error");
			// log.error(e.getMostSpecificCause().getMessage(), e);

			// return new ResponseEntity<Map<String, Object>>(respuesta,
			// HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (PgimException e2) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el hecho verificado");
			respuesta.put("error", e2.getMensaje());
			respuesta.put("resultado", "Error");
			log.error(e2.getMensaje(), e2);
			// return new ResponseEntity<Map<String, Object>>(respuesta,
			// HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		respuesta.put("mensaje", "El hecho verificado ha sido modificado");
		respuesta.put("pgimHechoConstatadoDTO", pgimHechoConstatadoDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite eliminar el hecho constatado. Esta eliminación es lógica.
	 * 
	 * @param idHechoConstatado
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-hc-heco_EL')")
	@DeleteMapping("/eliminarHC/{idHechoConstatado}")
	public ResponseEntity<?> eliminarHC(@PathVariable Long idHechoConstatado) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimHechoConstatado pgimHechoConstatadoActual = null;

		try {
			pgimHechoConstatadoActual = this.hechoConstatadoService.getByidHechoConstatado(idHechoConstatado);

			if (pgimHechoConstatadoActual == null) {
				mensaje = String.format("El hecho verificado que intenta eliminar no existe en la base de datos",
						idHechoConstatado);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el hecho verificado a eliminar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.hechoConstatadoService.eliminarHC(pgimHechoConstatadoActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar al hecho verificado");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	/**
	 * Permite reasignar de criterio a un hecho constatado
	 * 
	 * @param pgimHechoConstatadoDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/reasignarCriterioHV")
	public ResponseEntity<ResponseDTO> reasignarCriterioHV(@Valid @RequestBody PgimHechoConstatadoDTO pgimHechoConstatadoDTO,
			BindingResult resultadoValidacion) throws Exception {

		Map<String, Object> respuestaLog = new HashMap<>();
		PgimHechoConstatadoDTO pgimHechoConstatadoDTOModificado = null;
		
		if(pgimHechoConstatadoDTO.getIdInstanciaPaso() != null){
			Long idInstanciaProces = this.flujoTrabajoService
					.obtenerInstanciaPasoAuxPorId(pgimHechoConstatadoDTO.getIdInstanciaPaso()).getIdInstanciaProceso();            
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" );

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Se han encontrado inconsistencias para la reasignación del criterio: "+errores.toString() , 0));
		}

		try {
			pgimHechoConstatadoDTOModificado = this.hechoConstatadoService.reasignarCriterioHV(pgimHechoConstatadoDTO,this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar la reasignación del criterio", 0));
		} catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" );
			log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
        } 
		catch (Exception e) {
			
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" );
			log.error(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar la reasignación del criterio"));
		}

		ResponseDTO responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimHechoConstatadoDTOModificado, "La reasignación del criterio de hecho verificados ha sido completada");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}


	/**
	 * Permite recuperar la lista de cotejo de hechos constatados
	 *
	 * @param idSupervision
	 * @param cogidoCumple
	 * 
	 * @return lista de cotejo de hechos constatados
	 * @throws Exception
	 */

	@PreAuthorize("hasAnyAuthority('sp-ar-cfhc_AC')")
	@PostMapping("/listarCotejoHC")
	public ResponseEntity<Page<PgimCotejoHechoCnsttdoDTO>> listarCotejoHC(
			@RequestBody FiltroCotejoHechoConstatadoDTO filtroCotejoHechoConstatadoDTO, final Pageable paginador)
			throws Exception {

		final Page<PgimCotejoHechoCnsttdoDTO> lPgimCotejoHechoCnsttdo = this.hechoConstatadoService
				.listarCotejoHC(filtroCotejoHechoConstatadoDTO, paginador);

		return new ResponseEntity<Page<PgimCotejoHechoCnsttdoDTO>>(lPgimCotejoHechoCnsttdo, HttpStatus.OK);

	}

	/**
	 * Permite recuperar la lista de cotejo de hechos constatados pendientes de
	 * conformidad por el ET y/o EL
	 * 
	 * @param filtroCotejoHechoConstatadoDTO
	 * 
	 */
	@GetMapping("/listarCotejoHCpendientesConformidad/{idSupervision}")
	public ResponseEntity<List<PgimCotejoHechoCnsttdoDTO>> listarCotejoHCpendientesConformidad(
			@PathVariable Long idSupervision) {

		FiltroCotejoHechoConstatadoDTO filtroCotejoHechoConstatadoDTO = new FiltroCotejoHechoConstatadoDTO();
		filtroCotejoHechoConstatadoDTO.setIdSupervision(idSupervision);
		filtroCotejoHechoConstatadoDTO.setCodigoCumpleSupervisora(0L);
		return ResponseEntity
				.ok(this.hechoConstatadoService.listarCotejoHCpendientesConformidad(filtroCotejoHechoConstatadoDTO));
	}
	/*
	 * @PostMapping("/listarCotejoHCpendientesConformidad") public
	 * ResponseEntity<List<PgimCotejoHechoCnsttdoDTO>>
	 * listarCotejoHCpendientesConformidad(
	 * 
	 * @RequestBody FiltroCotejoHechoConstatadoDTO filtroCotejoHechoConstatadoDTO)
	 * throws Exception{
	 * 
	 * 
	 * final List<PgimCotejoHechoCnsttdoDTO> lPgimCotejoHechoCnsttdo =
	 * this.hechoConstatadoService
	 * .listarCotejoHCpendientesConformidad(filtroCotejoHechoConstatadoDTO);
	 * 
	 * return new
	 * ResponseEntity<List<PgimCotejoHechoCnsttdoDTO>>(lPgimCotejoHechoCnsttdo,
	 * HttpStatus.OK);
	 * 
	 * }
	 * 
	 */

	/**
	 * Permite obtener un hecho constatado, usado en el modo edición y consulta del
	 * frontend.
	 * 
	 * @param idHechoConstatado
	 * @return
	 */
	// @PreAuthorize("hasAnyAuthority('sp-hc-heco_CO')")
	@GetMapping("/obtener/idHechoConstatado/{id}")
	public ResponseEntity<PgimHechoConstatadoDTO> obtener(@PathVariable("id") Long idHechoConstatado) {
		PgimHechoConstatadoDTO hcDTO = this.hechoConstatadoService.obtenerHechoConstatadoDtoPorId(idHechoConstatado);
		if (hcDTO.getIdHechoConstatado() == null) {
			throw new NotFoundException("ID NO ENCONTRADO: " + idHechoConstatado);
		}
		return new ResponseEntity<PgimHechoConstatadoDTO>(hcDTO, HttpStatus.OK);
	}

	/**
	 * Permite recuperar la lista histórica de hechos constatados para un HC en
	 * particular
	 * 
	 * 
	 * @param idHechoConstatado
	 * @return
	 * 
	 */
	@PreAuthorize("hasAnyAuthority('sp-ar-hthc_AC')")
	@GetMapping("/listarHistoricoHechoConstatado/{idHechoConstatado}")
	public ResponseEntity<List<PgimHechoConstatadoDTO>> listarHistoricoHechoConstatado(
			@PathVariable Long idHechoConstatado) {

		return ResponseEntity.ok(this.hechoConstatadoService.listaHistoricaHechosConstatadosPorID(idHechoConstatado));
	}
	
	/**
	 * Permite recuperar la lista de obligaciones fiscalizadas de un criterio de fiscalización
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 */
	@GetMapping("/listarObligacFiscalizadasPorCriterioSuperv/{idCriterioSprvsion}")
	public ResponseEntity<List<PgimOblgcnNrmtvaHchocDTO>> listarObligacFiscalizadasPorCriterioSuperv(
			@PathVariable Long idCriterioSprvsion) {

		return ResponseEntity.ok(this.hechoConstatadoService.listarObligacFiscalizadasPorCriterioSuperv(idCriterioSprvsion));
	}
	
	/**
	 * Permite actualizar las obligaciones fiscalizables de los hechos constatados de un criterio de fiscalización
	 * 
	 * @param pgimCriterioSprvsionDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/actualizarObligacFiscalizablePorCriterioSuperv")
	public ResponseEntity<ResponseDTO> actualizarObligacFiscalizablePorCriterioSuperv(@Valid @RequestBody PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimCriterioSprvsionDTO pgimCriterioSprvsionDTOCU = null;
		
		ResponseDTO responseDTO = null;		
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje = "";

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			mensaje = "Se han encontrado inconsistencias para actualizar las obligaciones fiscalizables del criterio de fiscalización";
			log.error(mensaje);
			log.error(errores.toString());
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, errores.toString());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimCriterioSprvsionDTOCU = this.hechoConstatadoService.actualizarObligacFiscalizablePorCriterioSuperv(pgimCriterioSprvsionDTO, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			mensaje = "Ocurrió un error al intentar actualizar las obligaciones fiscalizables del criterio de fiscalización: " + e.getMessage();
			log.error(mensaje);
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
		 } catch (final PgimException e) {	            
            log.error(e.getMensaje(), e);
            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			
		} catch (Exception e) {
			mensaje = "Ocurrió un error al intentar actualizar las obligaciones fiscalizables del criterio de fiscalización: " +e.getMessage();
			log.error(mensaje);
			log.error(e.getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		respuesta.put("pgimCriterioSprvsionDTO", pgimCriterioSprvsionDTOCU);
		
		mensaje = "Genial, se actualizó las obligaciones fiscalizables en cada uno de los hechos verificados del presente criterio";
		
		
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, mensaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}

	/**
	 * Permite obtener la lista de autorizaciones usados en la tabla autorizacion
	 * del frontend.
	 * 
	 * @param pgimInfraccionAuxDTO
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('sp-ar-infr_AC')")
	@PostMapping("/listarInfraccionPorIdSupervision")
	public ResponseEntity<Page<PgimInfraccionAuxDTO>> listarInfraccionPorIdSupervision(
			@RequestBody PgimInfraccionAuxDTO pgimInfraccionAuxDTO, final Pageable paginador) {

		final Page<PgimInfraccionAuxDTO> lstInfracciones = this.hechoConstatadoService
				.listarInfraccionesSupervisionPaginado(pgimInfraccionAuxDTO, paginador);

		return new ResponseEntity<Page<PgimInfraccionAuxDTO>>(lstInfracciones, HttpStatus.OK);
	}

	/**
	 * Permite obtener la lista de infracciones por identificador interno del PAS.
	 * 
	 * @param pgimInfraccionAuxDTO
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('sp-ar-infr_AC')")
	@PostMapping("/listarInfraccionPorIdPas")
	public ResponseEntity<Page<PgimInfraccionAuxDTO>> listarInfraccionPorIdPas(@RequestBody PgimInfraccionAuxDTO pgimInfraccionAuxDTO, 
			final Pageable paginador) {

		final Page<PgimInfraccionAuxDTO> lstInfracciones = this.hechoConstatadoService.listarInfraccionesPasPaginado(
				pgimInfraccionAuxDTO, paginador);

		return new ResponseEntity<Page<PgimInfraccionAuxDTO>>(lstInfracciones, HttpStatus.OK);
	}

	/**
	 * Permite obtener una infracción, usado en el modo edición y consulta del
	 * frontend.
	 * 
	 * @param idInfraccion
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('sp-ar-infr_CO')")
	@GetMapping("/obtenerInfraccion/idInfraccion/{id}")
	public ResponseEntity<PgimInfraccionDTO> obtenerInfraccion(@PathVariable("id") Long idInfraccion) {
		PgimInfraccionDTO infraccionDTO = this.hechoConstatadoService.obtenerInfraccionDtoPorId(idInfraccion);
		if (infraccionDTO.getIdInfraccion() == null) {
			throw new NotFoundException("ID NO ENCONTRADO: " + idInfraccion);
		}
		return new ResponseEntity<PgimInfraccionDTO>(infraccionDTO, HttpStatus.OK);
	}

	/**
	 * Permite modificar los datos de un hecho constatado.
	 * 
	 * @param pgimInfraccionDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-ar-infr_MO')")
	@PutMapping("/modificarInfraccion")
	public ResponseEntity<?> modificarInfraccion(@Valid @RequestBody PgimInfraccionDTO pgimInfraccionDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimInfraccion pgimInfraccionActual = null;
		PgimInfraccionDTO pgimInfraccionDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la infracción");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimInfraccionActual = this.hechoConstatadoService
					.getInfraccionByidInfraccion(pgimInfraccionDTO.getIdInfraccion());

			if (pgimInfraccionActual == null) {
				mensaje = String.format("La infracción que intenta actualizar no existe en la base de datos",
						pgimInfraccionDTO.getIdInfraccion());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la infracción a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimInfraccionDTOModificado = this.hechoConstatadoService.modificarInfraccion(pgimInfraccionDTO,
					pgimInfraccionActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar la infracción");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La infracción ha sido modificada");
		respuesta.put("pgimInfraccionDTO", pgimInfraccionDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite eliminar un criterio de la matriz de fiscalización
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/eliminarCriterioFiscalizacion/{idCriterioSprvsion}")
	public ResponseEntity<?> eliminarCriterioFiscalizacion(@PathVariable Long idCriterioSprvsion) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimCriterioSprvsion pgimCriterioSprvsionActual = null;

		try {
			pgimCriterioSprvsionActual = this.hechoConstatadoService.findCriterioSprvsionById(idCriterioSprvsion);

			if (pgimCriterioSprvsionActual == null) {
				mensaje = String.format(
						"El criterio de la matriz de fiscalización %s que intenta eliminar no existe en la base de datos",
						idCriterioSprvsion);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje",
					"Ocurrió un error intentar recuperar el criterio de la matriz de fiscalización a eliminar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.hechoConstatadoService.eliminarCriterioFiscalizacion(pgimCriterioSprvsionActual,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar eliminar el criterio de la matriz de fiscalización");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite el registro de la conformidad de cada criterio del cuadro de verificación 
	 * @param filtroMatrizSupervisionDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/registroConformidadCriterios")
	public ResponseEntity<ResponseDTO> registroConformidadCriterios(
			@RequestBody FiltroMatrizSupervisionDTO filtroMatrizSupervisionDTO)
			throws Exception {
        ResponseDTO responseDTO = null;

        try {

					this.hechoConstatadoService.registroConformidadCriterios(filtroMatrizSupervisionDTO, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar registrar las conformidades de los criterios del cuadro de verificación.");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final PgimException e) {
            log.error(e.getMensaje(), e);

             responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar registrar las conformidades de los criterios del cuadro de verificación.");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "Estupendo, los hechos verificados como conformidad de cada criterio del cuadro de verificación han sido registrados.");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}

	/**
	 * <!----------------------------------------------------------------------------------------------------------
	 * PGIM-11253: Agregar la sección “Componentes asociados al Hecho Verificado” en
	 * fase 2 Y 3 de la fiscalización
	 * ------------------------------------------------------------------------------------------------------------>
	 */
	@PreAuthorize("hasAnyAuthority('sp-hc-heco_IN')") // verificar bien este permiso
	@PostMapping("/agregarComponentesMineroSupHcNuevo")
	public ResponseEntity<?> agregarComponentesMineroSupHcNuevo(
			@Valid @RequestBody PgimCmineroSprvsionDTO pgimCmineroSprvsionDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimCmineroSprvsionDTO pgimCmineroSprvsionDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para agregar nuevos(s) componente(s) minero(s) a inspeccionar");
			respuesta.put("error", errores.toString());
			respuesta.put("resultado", "Error");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			pgimCmineroSprvsionDTOCreado = this.cmineroSprvsionService.agregarComponentesMineroSupHcNuevo(
					pgimCmineroSprvsionDTO,
					this.obtenerAuditoria());
			respuesta.put("resultado", "OK");
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar agregar nuevos(s) componente(s) minero(s) a inspeccionar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			respuesta.put("resultado", "Error");
			log.error(e.getMostSpecificCause().getMessage(), e);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (PgimException e2) {
			respuesta.put("mensaje", "Ocurrió un error al intentar agregar nuevos(s) componente(s) minero(s) a inspeccionar");
			respuesta.put("error", e2.getMensaje());
			respuesta.put("resultado", "Error");
			log.error(e2.getMensaje(), e2);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		respuesta.put("mensaje", "El/los nuevo(s) componente(s) ha(n) sido agregado(s)");
		respuesta.put("pgimCmineroSprvsionDTO", pgimCmineroSprvsionDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@GetMapping("/listarComponenteMineroSupervision/{idSupervision}")
	public ResponseEntity<List<PgimCmineroSprvsionDTO>> listarComponenteMineroSupervision(@PathVariable Long idSupervision) {

		 List<PgimCmineroSprvsionDTO> lPgimCmineroSprvsionDTO = this.cmineroSprvsionService.listarComponenteMineroSupervision(idSupervision);
        
        return new ResponseEntity<List<PgimCmineroSprvsionDTO>>(lPgimCmineroSprvsionDTO, HttpStatus.OK);
	}

	@GetMapping("/listarComponenteMineroHc/{idHechoConstatado}")
	public ResponseEntity<List<PgimComponenteHcDTO>> listarComponenteMineroHc(@PathVariable Long idHechoConstatado) {

		 List<PgimComponenteHcDTO> lPgimComponenteHcDTO = this.componenteHcService.listarComponenteMineroHc(idHechoConstatado);
        
        return new ResponseEntity<List<PgimComponenteHcDTO>>(lPgimComponenteHcDTO, HttpStatus.OK);
	}

	@PostMapping("/listarComponenteMineroNoAsociadoHc")
	public ResponseEntity<List<PgimComponenteMineroDTO>> listarComponenteMineroNoAsociadoHc(
			@RequestBody PgimComponenteMineroDTO pgimComponenteMineroDTO) {

		// Extraer el parámetro idUnidadMinera del cuerpo de la solicitud
		Long idUnidadMinera = pgimComponenteMineroDTO.getIdUnidadMinera();
		List<PgimCmineroSprvsionDTO> descListaCmineroSprvsion = pgimComponenteMineroDTO.getDescListaCmineroSprvsion();
		List<PgimComponenteHcDTO> descListaComponenteHcDTO = pgimComponenteMineroDTO.getDescListaComponenteHcDTO();
		String tipoObjeto = pgimComponenteMineroDTO.getTipoObjeto();
		Long idHechoConstatado = pgimComponenteMineroDTO.getIdHechoConstatado();
		// Lógica del servicio
		List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO = this.componenteMinService
				.listarComponenteMineroNoAsociadoHc(idUnidadMinera, descListaCmineroSprvsion, descListaComponenteHcDTO, 
						tipoObjeto, idHechoConstatado);

		return new ResponseEntity<>(lPgimComponenteMineroDTO, HttpStatus.OK);
	}

	/**
	 * <!----------------------------------------------------------------------------------------------------------
	 * PGIM-11253: FIN
	 * ------------------------------------------------------------------------------------------------------------>
	 */
}
