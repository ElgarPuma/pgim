package pe.gob.osinergmin.pgim.controllers;

import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGenProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLineaProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPresupuestoDsEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimResumenLineaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSuperDsUmAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSuperProgramadaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupernpDsEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimVwPrgrmMontoEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimVwProgramPropuestoRnkDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.dtos.SeguimientoProgramaDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimEstandarPrograma;
import pe.gob.osinergmin.pgim.models.entity.PgimItemProgramaSupe;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSupervision;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.LineaProgramaRepository;
import pe.gob.osinergmin.pgim.services.EstandarProgramaService;
import pe.gob.osinergmin.pgim.services.EstratoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.LogPgimService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PrgrmSupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los
 * programas.
 * 
 * @descripción: Contrato
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/supervisionprogramas")
public class ProgramaController extends BaseController {

	@Autowired
	private PrgrmSupervisionService prgrmSupervisionService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private EstratoService estratoService;

	@Autowired
	private EstandarProgramaService estandarProgramaService;

	@Autowired
	private FlujoTrabajoService flujoTrabajoService;

	@Autowired
	private InstanciaPasoRepository instanciaPasoRepository;

	@Autowired
	private LineaProgramaRepository lineaProgramaRepository;

	@Autowired
	private InstanciaPasoAuxRepository instanciaPasoAuxRepository;

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private LogPgimService logPgimService;

	/**
	 * Permite obtener las configuraciones necesarias para el listado de programas.
	 * Acá se incluyen configuraciones como: Tipo de especialidad y estratos.
	 * 
	 * @return
	 */
	@GetMapping("/obtenerConfiguraciones")
	public ResponseEntity<?> obtenerConfiguraciones() {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
		List<PgimEstratoDTO> lPgimEstratoDTO = null;
		List<PgimValorParametroDTO> lPgimDivisionDTO = null;

		try {
			lPgimEstratoDTO = this.estratoService.listarEstrato();
			lPgimEspecialidadDTO = this.parametroService
					.filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

			lPgimDivisionDTO = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al acceder a las base de datos.");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimEstratoDTO", lPgimEstratoDTO);
		respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
		respuesta.put("lPgimDivisionDTO", lPgimDivisionDTO);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite obtener un listado de programas en el contexto de la paginación de
	 * resultados requerida en la lista den el frontend.
	 * 
	 * @param filtroPrograma Objeto filtro que porta las propiedades que de tener
	 *                       valor, representan criterios filtro específicos esto
	 *                       siempre que la propiedad esté configurada para
	 *                       aplicarse como criterio al momento de las consultas.
	 * @param paginador      Objeto paginador que tiene la información de la página
	 *                       actual, tamaño de la página y criterios de
	 *                       ordenamiento.
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('pr-lista_AC')")
	@PostMapping("/listarProgramas")
	public ResponseEntity<Page<PgimPrgrmSupervisionAuxDTO>> listarProgramas(
			@RequestBody PgimPrgrmSupervisionAuxDTO filtroPrograma, Pageable paginador) throws Exception {

		Page<PgimPrgrmSupervisionAuxDTO> lPgimPrgrmSupervisionDTO = this.prgrmSupervisionService
				.listarProgramasAux(filtroPrograma, paginador, this.obtenerAuditoria());
				
		return new ResponseEntity<Page<PgimPrgrmSupervisionAuxDTO>>(lPgimPrgrmSupervisionDTO, HttpStatus.OK);
	}

	/**
	 * Permite obtener el programa usado en la tarjeta del frontend
	 * 
	 * @param idProgramaSupervision
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('pr-lista_CO')")
	@GetMapping("/obtenerProgramaPorIdInstanciaPaso/{idInstanciaPaso}")
	public ResponseEntity<ResponseDTO> obtenerProgramaPorIdInstanciaPaso(@PathVariable Long idInstanciaPaso) throws Exception {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO = null;
		PgimLineaProgramaDTO pgimLineaProgramaDTO = null;
		PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOActual = null;
		List<PgimLineaProgramaDTO> lPgimLineaProgramaDTO = null;
		
		ResponseDTO responseDTO = null;

		Long idProgramaSupervision = null;

		String rankingUtilizado = "";
		
		try {

			if (idInstanciaPaso != null) {
				PgimInstanPasoAuxDTO pgimInstanPasoAuxDTO = this.instanciaPasoAuxRepository.obtenerInstanciaPasoAuxPorId(idInstanciaPaso);
				if (pgimInstanPasoAuxDTO != null) {
					if (pgimInstanPasoAuxDTO.getIdInstanciaProceso() != null) {
						Long idInstanciaProceso = pgimInstanPasoAuxDTO.getIdInstanciaProceso();
						respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
					} else {
						respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
					}
				} else {
					respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
				}
			} else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}

			idProgramaSupervision = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getCoTablaInstancia();

			pgimPrgrmSupervisionDTO = this.prgrmSupervisionService.obtenerPrograma(idProgramaSupervision);

			if (pgimPrgrmSupervisionDTO == null) {
				mensaje = String.format("El programa con el id: %d no existe en la base de datos", idProgramaSupervision);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
			}
			
			pgimLineaProgramaDTO = this.prgrmSupervisionService.obtenerLineaProgramaActual(idProgramaSupervision);

			pgimPrgrmSupervisionDTO.setDescIdInstanciaPaso(idInstanciaPaso);

			pgimInstanPasoAuxDTOActual = this.flujoTrabajoService
					.obtenerInstanciaPasoAuxPorId(idInstanciaPaso);

			lPgimLineaProgramaDTO = this.prgrmSupervisionService.listarLineaBase(idProgramaSupervision);

			rankingUtilizado = this.prgrmSupervisionService.obtenerRankingsUtilizados(idProgramaSupervision);

		} catch (DataAccessException e) {
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el programa"));
		}catch (final PgimException e) {
                        
            // Manejo de logs
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje()));
        }

		if (pgimInstanPasoAuxDTOActual == null) {
			mensaje = String.format("El programa con el id: %d aún no registra un paso actual en el respectivo flujo de trabajo", idProgramaSupervision);
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
		}

		respuesta.put("pgimPrgrmSupervisionDTO", pgimPrgrmSupervisionDTO);
		respuesta.put("pgimLineaProgramaDTO", pgimLineaProgramaDTO);
		respuesta.put("pgimInstanPasoAuxDTOActual", pgimInstanPasoAuxDTOActual);
		respuesta.put("lPgimLineaProgramaDTO", lPgimLineaProgramaDTO);
		respuesta.put("rankingUtilizado", rankingUtilizado);

		mensaje = "El programa ha sido recuperado";

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, mensaje);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}

	/**
	 * Permite asignar la programación
	 * 
	 * @param pgimPrgrmSupervisionDTO Objetivo portador de datos para la asignación
	 *                                de la programación.
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('pr-lista_IN')")
	@PostMapping("/asignarPrograma")
	public ResponseEntity<ResponseDTO> asignarPrograma(@Valid @RequestBody PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO,
			BindingResult resultadoValidacion) throws Exception {

			Map<String, Object> respuestaLog = new HashMap<>();

		PgimPrgrmSupervisionAuxDTO pgimPrgrmSupervisionDTOAsignado = null;

		 ResponseDTO responseDTO = null;

			if(pgimPrgrmSupervisionDTO.getIdInstanciaProceso() != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(pgimPrgrmSupervisionDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream().map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage())).collect(Collectors.toList());

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(errores.toString());

			responseDTO = new ResponseDTO(TipoResultado.ERROR, errores.toString());

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {

			pgimPrgrmSupervisionDTOAsignado = this.prgrmSupervisionService.asignarPrograma(pgimPrgrmSupervisionDTO, this.obtenerAuditoria());

		} catch (DataAccessException e) {
			
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar el programa");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} catch (PgimException e) {

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);

			responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (Exception e) {

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar asignar el PROBLEMA");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimPrgrmSupervisionDTOAsignado, "Estupendo, el programa ha sido creado y asignado");
		responseDTO.setData(pgimPrgrmSupervisionDTOAsignado);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	/**
	 * Permite obtener la lista de datos estandar de programas usados en la tabla
	 * autorizacion del frontend.
	 * 
	 * @param idLineaPrograma
	 * @return
	 */

	@GetMapping("/listarDatosEstandares/{idLineaPrograma}")
	public ResponseEntity<List<PgimEstandarProgramaDTO>> listarDatosEstandares(@PathVariable Long idLineaPrograma) {
		final List<PgimEstandarProgramaDTO> pgimEstandarProgramaDTO = this.prgrmSupervisionService
				.listarEstandarPrograma(idLineaPrograma);
		return new ResponseEntity<List<PgimEstandarProgramaDTO>>(pgimEstandarProgramaDTO, HttpStatus.OK);
	}

	/**
	 * Permite obtener las listas de los select usados en el dialogo Asignación del
	 * programa
	 * 
	 * @return
	 */
	@GetMapping("/obtenerConfiguracionesGenerales")
	public ResponseEntity<?> obtenerConfiguracionesGenerales() {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = null;
		PgimRelacionPasoDTO pgimRelacionPasoDTOInicial = null;

		try {
			lPgimPrgrmSupervisionDTO = this.prgrmSupervisionService.obtenerProgramaParaAsignacion();
			pgimRelacionPasoDTOInicial = this.flujoTrabajoService
					.obtenerRelacionPasoInicial(ConstantesUtil.PARAM_PROCESO_PROGRAMACION);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimPrgrmSupervisionDTO", lPgimPrgrmSupervisionDTO);
		respuesta.put("pgimRelacionPasoDTOInicial", pgimRelacionPasoDTOInicial);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('pr-dto-est_MO')")
	@PutMapping("/modificarCantidadDiasSupervision")
	public ResponseEntity<?> modificarCantidadDiasSupervision(
			@Valid @RequestBody PgimEstandarProgramaDTO pgimEstandarProgramaDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimEstandarPrograma pgimEstandarProgramaActual = null;
		PgimEstandarProgramaDTO pgimEstandarProgramaDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		Long idInstanciaProces = this.estandarProgramaService.getById(pgimEstandarProgramaDTO.getIdEstandarPrograma()).getPgimLineaPrograma().getPgimPrgrmSupervision().getPgimInstanciaProces().getIdInstanciaProceso();

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el agente fiscalizado");
			respuesta.put("error", errores.toString());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimEstandarProgramaActual = this.estandarProgramaService
					.getById(pgimEstandarProgramaDTO.getIdEstandarPrograma());

			if (pgimEstandarProgramaActual == null) {
				mensaje = String.format("El estandar programa %s que intenta actualizar no existe en la base de datos",
						pgimEstandarProgramaDTO.getIdEstandarPrograma());
				respuesta.put("mensaje", mensaje);
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar el estandar programa a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimEstandarProgramaActual.setCaDiasSupervision(pgimEstandarProgramaDTO.getCaDiasSupervision());
			pgimEstandarProgramaDTOModificado = this.estandarProgramaService
					.modificarEstandarPrograma(pgimEstandarProgramaActual, this.obtenerAuditoria());

			// GDC: ya no es necesario actualziar el costo estimado, cuando se modifica la
			// cantidad de días por supervisión...
			// this.actualizarMoCostoEstimado(pgimEstandarProgramaDTOModificado.getIdLineaPrograma());

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el estandar programa");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La cantidad de días han sido modificados");
		respuesta.put("pgimEstandarProgramaDTO", pgimEstandarProgramaDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@PutMapping("/modificarMontoPartida")
	public ResponseEntity<?> modificarMontoPartida(@Valid @RequestBody PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimPrgrmSupervision pgimPrgrmSupervisionActual = null;
		PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		if(pgimPrgrmSupervisionDTO.getIdInstanciaProceso() != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(pgimPrgrmSupervisionDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}
		
		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el monto de partida");
			respuesta.put("error", errores.toString());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimPrgrmSupervisionActual = this.prgrmSupervisionService
					.getPrgrmSupervisionById(pgimPrgrmSupervisionDTO.getIdProgramaSupervision());

			if (pgimPrgrmSupervisionActual == null) {
				mensaje = String.format("El monto de partida %s que intenta actualizar no existe en la base de datos",
						pgimPrgrmSupervisionDTO.getIdProgramaSupervision());
				respuesta.put("mensaje", mensaje);
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar el monto de partida a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimPrgrmSupervisionActual.setMoPartida(pgimPrgrmSupervisionDTO.getMoPartida());
			pgimPrgrmSupervisionDTOModificado = this.prgrmSupervisionService
					.modificarProgramaSupervision(pgimPrgrmSupervisionActual, this.obtenerAuditoria());

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el monto de partida");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El monto de partida han sido modificada");
		respuesta.put("pgimPrgrmSupervisionDTO", pgimPrgrmSupervisionDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyAuthority('pr-dto-est_MO')")
	@PutMapping("/modificarMontoPorDiaSupervision")
	public ResponseEntity<?> modificarMontoPorDiaSupervision(
			@Valid @RequestBody PgimEstandarProgramaDTO pgimEstandarProgramaDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimEstandarPrograma pgimEstandarProgramaActual = null;
		PgimEstandarProgramaDTO pgimEstandarProgramaDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		Long idInstanciaProces = this.estandarProgramaService.getById(pgimEstandarProgramaDTO.getIdEstandarPrograma()).getPgimLineaPrograma().getPgimPrgrmSupervision().getPgimInstanciaProces().getIdInstanciaProceso();

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el agente fiscalizado");
			respuesta.put("error", errores.toString());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimEstandarProgramaActual = this.estandarProgramaService
					.getById(pgimEstandarProgramaDTO.getIdEstandarPrograma());

			if (pgimEstandarProgramaActual == null) {
				mensaje = String.format("El estandar programa %s que intenta actualizar no existe en la base de datos",
						pgimEstandarProgramaDTO.getIdEstandarPrograma());
				respuesta.put("mensaje", mensaje);
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar el estandar programa a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimEstandarProgramaActual.setMoPorSupervision(pgimEstandarProgramaDTO.getMoPorSupervision());
			pgimEstandarProgramaDTOModificado = this.estandarProgramaService
					.modificarEstandarPrograma(pgimEstandarProgramaActual, this.obtenerAuditoria());

			this.actualizarMoCostoEstimado(pgimEstandarProgramaDTOModificado.getIdLineaPrograma());

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el estandar programa");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La cantidad de días han sido modificados");
		respuesta.put("pgimEstandarProgramaDTO", pgimEstandarProgramaDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@PostMapping("/listarItemsProgramaPendientes")
	public ResponseEntity<Page<PgimItemProgramaSupeDTO>> listarItemsProgramaPendientes(
			@RequestBody PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO, Pageable paginador) throws Exception {

		Map<String, Object> respuestaLog = new HashMap<>();
		
		if(pgimPrgrmSupervisionDTO.getIdProgramaSupervision() != null){

			Long idInstanciaProces = this.prgrmSupervisionService.getPrgrmSupervisionById(pgimPrgrmSupervisionDTO.getIdProgramaSupervision()).getPgimInstanciaProces().getIdInstanciaProceso();
	
			if (idInstanciaProces != null) {
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			} else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		PgimLineaProgramaDTO pgimLineaProgramaDTO = this.prgrmSupervisionService
				.obtenerLineaProgramaActual(pgimPrgrmSupervisionDTO.getIdProgramaSupervision());

		if (pgimLineaProgramaDTO == null) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[No se ha podido determinar la línea base actual para el programa]" ); // STORY:PGIM-7606 // DATA
			throw new PgimException("error", "No se ha podido determinar la línea base actual para el programa");
		}

		Page<PgimItemProgramaSupeDTO> lPgimItemProgramaSupeDTO = this.prgrmSupervisionService
				.listarItemsProgramaPendientes(pgimLineaProgramaDTO.getIdLineaPrograma(), paginador);

		return new ResponseEntity<Page<PgimItemProgramaSupeDTO>>(lPgimItemProgramaSupeDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('pr-sp-prog_AC')")
	@PostMapping("/listarItemsProgramas/{idLineaPrograma}")
	public ResponseEntity<Page<PgimItemProgramaSupeDTO>> listarItemsProgramas(@PathVariable Long idLineaPrograma,
			Pageable paginador) {

		Page<PgimItemProgramaSupeDTO> itemsProgramas = this.prgrmSupervisionService
				.listarItemsProgramas(idLineaPrograma, paginador);
		return new ResponseEntity<Page<PgimItemProgramaSupeDTO>>(itemsProgramas, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('pr-sp-prog_AC')")
	@GetMapping("/listarItemsProgramas2/{idLineaPrograma}")
	public ResponseEntity<List<PgimItemProgramaSupeDTO>> listarItemsProgramas(@PathVariable Long idLineaPrograma) {

		List<PgimItemProgramaSupeDTO> itemsProgramas = new ArrayList<>();
		itemsProgramas = prgrmSupervisionService.listarItemsProgramas(idLineaPrograma);

		if (itemsProgramas == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(itemsProgramas);

	}

	private void actualizarMoCostoEstimado(Long idLineaPrograma) throws Exception {
		List<PgimEstandarProgramaDTO> lpgimEstandarProgramaDTO;
		BigDecimal moPorSupervision;
		List<PgimItemProgramaSupeDTO> itemsProgramas = new ArrayList<>();
		itemsProgramas = prgrmSupervisionService.listarItemsProgramas(idLineaPrograma);

		for (PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO : itemsProgramas) {

			PgimItemProgramaSupe pgimItemProgramaSupe = new PgimItemProgramaSupe();
			pgimItemProgramaSupe = this.prgrmSupervisionService
					.getItemProgramaById(pgimItemProgramaSupeDTO.getIdItemProgramaSupe());

			lpgimEstandarProgramaDTO = this.prgrmSupervisionService.listarCostosEstimados(
					pgimItemProgramaSupeDTO.getIdLineaPrograma(), pgimItemProgramaSupeDTO.getIdSubtipoSupervision());
			moPorSupervision = lpgimEstandarProgramaDTO.get(0).getMoPorSupervision();
			pgimItemProgramaSupe.setMoCostoEstimadoSupervision(moPorSupervision);
			this.prgrmSupervisionService.modificarItemProgramaSupe(pgimItemProgramaSupe, this.obtenerAuditoria());

		}

	}

	@PreAuthorize("hasAnyAuthority('pr-dto-nop_AC')")
	@GetMapping("/listarNoProgramada/{idLineaPrograma}")
	public ResponseEntity<List<PgimEstandarProgramaDTO>> listarNoProgramada(@PathVariable Long idLineaPrograma) {

		if (idLineaPrograma == 0) {
			idLineaPrograma = null;
		}

		List<PgimEstandarProgramaDTO> noProgramada = new ArrayList<>();
		noProgramada = prgrmSupervisionService.listarNoProgramada(idLineaPrograma);

		if (noProgramada == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(noProgramada);
	}

	@PreAuthorize("hasAnyAuthority('pr-dto-nop_MO')")
	@PutMapping("/modificarCantidadSupNoProgramada")
	public ResponseEntity<?> modificarCantidadSupNoProgramada(
			@Valid @RequestBody PgimEstandarProgramaDTO pgimEstandarProgramaDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimEstandarPrograma pgimEstandarProgramaActual = null;
		PgimEstandarProgramaDTO pgimEstandarProgramaDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		Long idInstanciaProces = this.estandarProgramaService.getById(pgimEstandarProgramaDTO.getIdEstandarPrograma()).getPgimLineaPrograma().getPgimPrgrmSupervision().getPgimInstanciaProces().getIdInstanciaProceso();

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje",
					"Se han encontrado inconsistencias para modificar la configuración de las supervisiones no programadas.");
			respuesta.put("error", errores.toString());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimEstandarProgramaActual = this.estandarProgramaService
					.getById(pgimEstandarProgramaDTO.getIdEstandarPrograma());

			if (pgimEstandarProgramaActual == null) {
				mensaje = String.format("El estandar programa %s que intenta actualizar no existe en la base de datos",
						pgimEstandarProgramaDTO.getIdEstandarPrograma());
				respuesta.put("mensaje", mensaje);
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar los datos del programa a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimEstandarProgramaActual.setCaSupervisiones(pgimEstandarProgramaDTO.getCaSupervisiones());
			pgimEstandarProgramaDTOModificado = this.estandarProgramaService
					.modificarEstandarPrograma(pgimEstandarProgramaActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el programa");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La cantidad de supervisiones han sido modificados");
		respuesta.put("pgimEstandarProgramaDTO", pgimEstandarProgramaDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite cancelar un ítem de programa de supervisión *
	 * 
	 * @param idItemProgramaSupe Identificador interno del ítem de programa de
	 *                           supervision
	 * @param motivoCancelacion  Motivo de la cancelación.
	 * 
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('pr-sp-prog_EL')")
	@PostMapping("/cancelarItemProgramaSupe")
	public ResponseEntity<ResponseDTO> cancelarItemProgramaSupe(@RequestBody PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO) throws Exception {
	
		ResponseDTO responseDTO = null;
		String mensaje;
		Long idItemProgramaSupe = pgimItemProgramaSupeDTO.getIdItemProgramaSupe();
		String motivoCancelacion = pgimItemProgramaSupeDTO.getDeMotivoCancelacion();
		
		PgimItemProgramaSupeDTO pgimItemProgramaSupeDTOCancelado = null;

		Map<String, Object> respuestaLog = new HashMap<>();

		Long idInstanciaProces = this.prgrmSupervisionService.getItemProgramaSupeById(idItemProgramaSupe).getPgimLineaPrograma().getPgimPrgrmSupervision().getPgimInstanciaProces().getIdInstanciaProceso();

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {

			pgimItemProgramaSupeDTOCancelado = this.prgrmSupervisionService.obtenerItemProgramaSupeDtoById(idItemProgramaSupe);

			if (pgimItemProgramaSupeDTOCancelado == null) {
				mensaje = String.format(
						"El ítem de programa de supervisión que intenta cancelar no existe en la base de datos",
						idItemProgramaSupe);
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

				responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);				
			}
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el ítem de programa de supervisión a cancelar"));
		}

		if (pgimItemProgramaSupeDTOCancelado.getIdSupervision() != null) {
			mensaje = String.format(
					"La supervisión programada ya ha sido asignada en la etapa de ejecución, por lo tanto, no es posible cancelar el ítem de programa.",
					idItemProgramaSupe);
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA
			responseDTO = new ResponseDTO(TipoResultado.WARNING, mensaje);
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);		
		}

		try {
			pgimItemProgramaSupeDTOCancelado.setDeMotivoCancelacion(motivoCancelacion);
			this.prgrmSupervisionService.cancelarItemProgramaSupe(pgimItemProgramaSupeDTOCancelado, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar cancelar el ítem de programa de supervisión"));
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "Estupendo, se ha cancelado el ítem de programa.");
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		
	}

	/**
	 * Permite obtener los costos reales de un programa de supervisión
	 * 
	 * @param idLineaPrograma
	 * @return
	 */

	@GetMapping("/obtenerCostosReales/{idLineaPrograma}")
	public ResponseEntity<SeguimientoProgramaDTO> obtenerCostosReales(@PathVariable Long idLineaPrograma) {
		final SeguimientoProgramaDTO seguimientoProgramaDTO = this.prgrmSupervisionService
				.obtenerCostosReales(idLineaPrograma);
		return new ResponseEntity<SeguimientoProgramaDTO>(seguimientoProgramaDTO, HttpStatus.OK);
	}

	/**
	 * Permite obtener la lista resumen del programa de acuerdo con la linea base
	 * pasada como parámetro.
	 * 
	 * @param idLineaPrograma
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/obtenerListaResumenPrograma/{idLineaPrograma}")
	public ResponseEntity<Map<String, Object>> obtenerListaResumenPrograma(@PathVariable Long idLineaPrograma) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimResumenLineaAuxDTO> lPgimResumenLineaAuxDTO = new ArrayList<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		Long idInstanciaProces = this.lineaProgramaRepository.findById(idLineaPrograma).orElse(null).getPgimPrgrmSupervision().getPgimInstanciaProces().getIdInstanciaProceso();

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {

			lPgimResumenLineaAuxDTO = this.prgrmSupervisionService.obtenerListaResumenPrograma(idLineaPrograma);

		} catch (DataAccessException e) {
			respuesta.put("mensaje",
					"Ocurrió un error al intentar obtener el resumen del programa de supervisión de acuerdo con la línea base seleccionada");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje",
					"Ocurrió un error al intentar obtener el resumen del programa de supervisión de acuerdo con la línea base seleccionada");
			respuesta.put("error", e.getMessage());

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("lPgimResumenLineaAuxDTO", lPgimResumenLineaAuxDTO);
		respuesta.put("cantidadRegistrosTotal", lPgimResumenLineaAuxDTO.size());

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}

	/**
	 * Me permite listar los programas del plan de supervisión.
	 * 
	 * @param idPlanSupervision
	 * @return
	 */
	@GetMapping("/listarProgramasSupervPlan/{idPlanSupervision}")
	public ResponseEntity<List<PgimPrgrmSupervisionDTO>> listarProgramasSupervPlan(
			@PathVariable Long idPlanSupervision) {

		if (idPlanSupervision == 0) {
			idPlanSupervision = null;
		}

		List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = new ArrayList<>();
		lPgimPrgrmSupervisionDTO = this.prgrmSupervisionService.listarProgramasSupervPlan(idPlanSupervision);

		if (lPgimPrgrmSupervisionDTO == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lPgimPrgrmSupervisionDTO);
	}
	
	
	/**
	 * Permite obtener la lista preparada de supervisiones programadas por división supervisora-especialidad
	 * paginada 
	 * 
	 * @param filtroPgimDocumentoAuxDTO
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarReporteSuperProgramadaPaginado")
	public ResponseEntity<Page<PgimSuperProgramadaAuxDTO>> listarReporteSuperProgramadaPaginado(
			@RequestBody PgimSuperProgramadaAuxDTO filtroPgimSuperProgramadaAuxDTO, Pageable paginador) throws Exception {  
		
		Page<PgimSuperProgramadaAuxDTO> lPgimSuperProgramadaAuxDTO = this.prgrmSupervisionService.listarReporteSuperProgramadaPaginado(filtroPgimSuperProgramadaAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimSuperProgramadaAuxDTO>>(lPgimSuperProgramadaAuxDTO, HttpStatus.OK);
	}
    
	/**
	 * Permite obtener la lista preparada de supervisiones no programadas por división supervisora-especialidad
	 * paginada 
	 * 
	 * @param filtroPgimSupernpDsEspAuxDTO
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarReporteSuperNoProgramadaPaginado")
	public ResponseEntity<Page<PgimSupernpDsEspAuxDTO>> listarReporteSuperNoProgramadaPaginado(
			@RequestBody PgimSupernpDsEspAuxDTO filtroPgimSupernpDsEspAuxDTO, Pageable paginador) throws Exception {  
		
		Page<PgimSupernpDsEspAuxDTO> pPgimSupernpDsEspAuxDTO = this.prgrmSupervisionService.listarReporteSuperNoProgramadaPaginado(filtroPgimSupernpDsEspAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimSupernpDsEspAuxDTO>>(pPgimSupernpDsEspAuxDTO, HttpStatus.OK);
	}
    
	/**
	 * Permite obtener la lista preparada de supervisiones por división supervisora y unidad minera (programadas y no programadas)
	 * paginada 
	 * 
	 * @param filtroPgimSuperDsUmAuxDTO
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarReporteSuperDsUmPaginado")
	public ResponseEntity<Page<PgimSuperDsUmAuxDTO>> listarReporteSuperDsUmPaginado(
			@RequestBody PgimSuperDsUmAuxDTO filtroPgimSuperDsUmAuxDTO, Pageable paginador) throws Exception {  
		
		Page<PgimSuperDsUmAuxDTO> pPgimSuperDsUmAuxDTO = this.prgrmSupervisionService.listarReporteSuperDsUmPaginado(filtroPgimSuperDsUmAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimSuperDsUmAuxDTO>>(pPgimSuperDsUmAuxDTO, HttpStatus.OK);
	}
    
	/**
	 * Permite obtener la lista preparada de presupuesto consolidado por división supervisora y especialidad
	 * paginada 
	 * 
	 * @param filtroPgimPresupuestoDsEspAuxDTO
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarReportePresupDsEspecPaginado")
	public ResponseEntity<Page<PgimPresupuestoDsEspAuxDTO>> listarReportePresupDsEspecPaginado(
			@RequestBody PgimPresupuestoDsEspAuxDTO filtroPgimPresupuestoDsEspAuxDTO, Pageable paginador) throws Exception {  
		
		Page<PgimPresupuestoDsEspAuxDTO> lPgimPresupuestoDsEspAuxDTO = this.prgrmSupervisionService.listarReportePresupDsEspecPaginado(filtroPgimPresupuestoDsEspAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimPresupuestoDsEspAuxDTO>>(lPgimPresupuestoDsEspAuxDTO, HttpStatus.OK);
	}

    @GetMapping("/listarMontosEspecialidadPorPlan/{idPlanSupervision}")
	public ResponseEntity<List<PgimVwPrgrmMontoEspAuxDTO>> listarMontosEspecialidadPorPlan(@PathVariable Long idPlanSupervision) {

		List<PgimVwPrgrmMontoEspAuxDTO> lPgimVwPrgrmMontoEspAuxDTO = this.prgrmSupervisionService.listarMontosEspecialidadPorPlan(idPlanSupervision);

		if (lPgimVwPrgrmMontoEspAuxDTO == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(lPgimVwPrgrmMontoEspAuxDTO);
	}

	/**
	 * Permite obtener las configuraciones necesarias para la generación de itém de programa a partir de ranking
	 * Acá se incluyen configuraciones como: listado de ranking de riesgos, nuMaxFiscaAnualXuf, nuMaxFiscaMensual
	 * @param idProgramaSupervision
	 * @return
	 */
	@GetMapping("/obtenerConfiguracionGeneracionItemPrograma/{idProgramaSupervision}")
	public ResponseEntity<?> obtenerConfiguracionGeneracionItemPrograma(@PathVariable Long idProgramaSupervision) {

		ResponseDTO responseDTO = null;
		Map<String, Object> respuesta = new HashMap<>();
		List<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTO = null;
		Integer nuMaxFiscaAnualXuf = propertiesConfig.getNuMaxFiscalizacionAnualXuf();
		Integer nuMaxFiscaMensual = propertiesConfig.getNuMaxFiscalizacionMensual();

		try {
			lPgimRankingRiesgoDTO = this.prgrmSupervisionService.obtenerRankingAprobXDsEspecialidadAnio(idProgramaSupervision);
								
		} catch (final DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener las configuraciones para generación de item programa");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (final Exception e) {
			log.error(e.getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener las configuraciones para generación de item programa");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		respuesta.put("lPgimRankingRiesgoDTO", lPgimRankingRiesgoDTO);
		respuesta.put("nuMaxFiscaAnualXuf", nuMaxFiscaAnualXuf);
		respuesta.put("nuMaxFiscaMensual", nuMaxFiscaMensual);

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Configuraciones encontradas");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	/**
	 * Permite generar un programa propuesto a partir de los rankig de riesgo
	 * @param pgimGenProgramaDTO
	 * @param lPgimRankingRiesgoDTO
	 * @return
	 * @throws PgimException
	 * @throws IOException
	 * @throws Exception
	 */
	@PostMapping(value = "/generarProgramaPropuesta", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseDTO> generarProgramaPropuesta(@RequestPart("pgimGenProgramaDTO") PgimGenProgramaDTO pgimGenProgramaDTO,
			@RequestPart("lPgimRankingRiesgoDTO") List<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTO) throws PgimException, IOException, Exception {

		ResponseDTO responseDTO = null;
		PgimGenProgramaDTO pgimGenProgramaDTOCreado = null;
		List<PgimVwProgramPropuestoRnkDTO> lPgimVwProgramPropuestoRnkDTO = null;
		Long totalVisitas = 0L;

		if(lPgimRankingRiesgoDTO.size()==0){
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Al menos debe de tener una ranking"));
		}

		try {
			
			pgimGenProgramaDTOCreado = this.prgrmSupervisionService.generarProgramaPropuesta(pgimGenProgramaDTO, lPgimRankingRiesgoDTO, this.obtenerAuditoria());
			lPgimVwProgramPropuestoRnkDTO = this.prgrmSupervisionService.obtenerProgramaPropuestaNoConforme(pgimGenProgramaDTO.getIdProgramaSupervision());
			totalVisitas = this.prgrmSupervisionService.obtenerTotalVisitasProgramaPropuesta(pgimGenProgramaDTO.getIdProgramaSupervision());

		} catch (PgimException e) {
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + e.getMensaje();
			log.error(msjLog, e);            
			TipoResultado tipoResultado = (e.getTipoResultado() != null) ? e.getTipoResultado() : TipoResultado.WARNING; 
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); 

		} catch (DataAccessException e) {
			String mensaje = "Ocurrió un error al intentar generar el programa propuesto: "+ e.getMostSpecificCause().getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
			log.error(msjLog, e);
	
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje)); 
		
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar generar el programa propuesto: "+ e.getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
			log.error(msjLog, e);
	
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
	        
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimGenProgramaDTOCreado, "Se ha generado el programa propuesto");
		responseDTO.setLista(lPgimVwProgramPropuestoRnkDTO);
		responseDTO.setValor(totalVisitas);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	
	/**
	 * Permite obtener el programa propuesto a partir de los ranking involucrados con el programa
	 * @param idProgramaSupervision
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/obtenerProgramaPropuesta")
	public ResponseEntity<Page<PgimVwProgramPropuestoRnkDTO>> obtenerProgramaPropuesta(
			@RequestBody Long idProgramaSupervision, Pageable paginador) throws Exception {  
		
		Page<PgimVwProgramPropuestoRnkDTO> pPgimVwProgramPropuestoRnkDTO = this.prgrmSupervisionService.obtenerProgramaPropuesta(idProgramaSupervision, paginador);
		
		return new ResponseEntity<Page<PgimVwProgramPropuestoRnkDTO>>(pPgimVwProgramPropuestoRnkDTO, HttpStatus.OK);
	}

	/**
	 * Permite registrar como item de programa al programa propuesto en conjunto
	 * @param pgimGenProgramaDTO
	 * @return
	 * @throws PgimException
	 * @throws IOException
	 * @throws Exception
	 */
	@PostMapping("registrarProgramaPropuesta")
	public ResponseEntity<ResponseDTO> registrarProgramaPropuesta(@RequestBody PgimGenProgramaDTO pgimGenProgramaDTO)
			throws PgimException, IOException, Exception {		
		
		ResponseDTO responseDTO = null;

		try {
			
			this.prgrmSupervisionService.registrarProgramaPropuesta(pgimGenProgramaDTO, this.obtenerAuditoria());
			
		} catch (PgimException e) {
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + e.getMensaje();
            log.error(msjLog, e);            
            TipoResultado tipoResultado = (e.getTipoResultado() != null) ? e.getTipoResultado() : TipoResultado.WARNING; 
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); 
            
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar registrar el programa propuesto: "+ e.getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
            log.error(msjLog, e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }	
		
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, null, "El registro del programa propuesto a sido satisfatorio");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}

	/**
	 * Permite validar si es permitido generar un programa propuesta a partir de ranking, 
   * es permitido siempre y cuando el programa aun no tiene alguna fiscalización asignada.
	 * @param idLineaPrograma
	 * @return
	 */
	@GetMapping("/validarGeneracionItemProgramaByRanking/{idLineaPrograma}")
	public ResponseEntity<?> validarGeneracionItemProgramaByRanking(@PathVariable Long idLineaPrograma) {

		ResponseDTO responseDTO = null;
		
		Integer countFiscalizacionesAsignadas = 0;

		try {
			countFiscalizacionesAsignadas = this.prgrmSupervisionService.existeFiscalizacionAsignadaPrograma(idLineaPrograma);
								
		} catch (final DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener la validación");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (final Exception e) {
			log.error(e.getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener la validación");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
		
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, countFiscalizacionesAsignadas, "Validación obtenida");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

}
