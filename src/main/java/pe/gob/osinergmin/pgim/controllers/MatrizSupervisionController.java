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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizGrpoCrtrioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizCriterio;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizGrpoCrtrio;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizSupervision;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.ConfiguracionBaseService;
import pe.gob.osinergmin.pgim.services.MatrizSupervisionService;
import pe.gob.osinergmin.pgim.services.NormaService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Controlador del componente matriz de supervisión
 * 
 * @descripción: Controlador del componente matriz de supervisión
 *
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 13/05/2021
 *
 */
@RestController
@Slf4j
@RequestMapping("/matrizsupervision")
public class MatrizSupervisionController extends BaseController {

	@Autowired
	private MatrizSupervisionService matrizSupervisionService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private ConfiguracionBaseService configuracionBaseService;

	@Autowired
	private SupervisionService supervisionService;

	@Autowired
	private ValorParametroRepository valorParametroRepository;
	
	@Autowired
	private NormaService normaService;

	/**
	 * Permite mostrar la lista de matriz de supervision
	 *
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('ms-lista_AC')")
	@PostMapping("/listarMatrizSupervision")
	public ResponseEntity<?> listarMatrizSupervision(@RequestBody PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimMatrizSupervisionDTO> lPgimMatrizSupervisionDTO = null;

		try {
			lPgimMatrizSupervisionDTO = this.matrizSupervisionService.listarMatrizSupervision(pgimMatrizSupervisionDTO);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de matriz de la supervisión");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron las matrices de la supervisión");
		respuesta.put("lPgimMatrizSupervisionDTO", lPgimMatrizSupervisionDTO);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite obtener la lista de grupos de criterios de la matriz de supervisión
	 * por ID de Matriz de Supervision
	 * 
	 * @param idMatrizSupervision
	 * @return
	 * @throws Exception
	 */

	@PreAuthorize("hasAnyAuthority('ms-grupo_AC')")
	@GetMapping("/listarGruposCriterioMatriz/{idMatrizSupervision}")
	public ResponseEntity<List<PgimMatrizGrpoCrtrioDTO>> listarGruposCriterioMatriz(
			@PathVariable Long idMatrizSupervision) throws Exception {

		final List<PgimMatrizGrpoCrtrioDTO> lPgimMatrizGrpoCrtrioDTO = this.matrizSupervisionService
				.listarMatrizGrpoCrtrio(idMatrizSupervision);
				
		return new ResponseEntity<List<PgimMatrizGrpoCrtrioDTO>>(lPgimMatrizGrpoCrtrioDTO, HttpStatus.OK);

	}

	/**
	 * Permite obtener la matriz de supervisión por id
	 * 
	 * @param idMatrizSupervision
	 * @return
	 */

	@GetMapping("/obtenerMatrizSupervisionPorId/{idMatrizSupervision}")
	public ResponseEntity<?> obtenerMatrizSupervisionPorId(@PathVariable Long idMatrizSupervision) {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO = null;
		PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO = null;
		try {
			pgimMatrizSupervisionDTO = this.matrizSupervisionService.obtenerMatrizSupervisionPorId(idMatrizSupervision);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el cuadro de verificación");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el cuadro de verificación");
			respuesta.put("error", e.getMessage());

			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (pgimMatrizSupervisionDTO == null) {
			mensaje = String.format("El cuadro de verificación con el id: %d no existe en la base de datos",
					idMatrizSupervision);
			respuesta.put("mensaje", mensaje);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		}

		try {
			pgimConfiguracionBaseDTO = this.configuracionBaseService
					.obtenerCfgBasePorId(pgimMatrizSupervisionDTO.getIdConfiguracionBase());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la configuración base");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la configuración base");
			respuesta.put("error", e.getMessage());

			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El cuadro de verificación ha sido recuperado");
		respuesta.put("pgimMatrizSupervisionDTO", pgimMatrizSupervisionDTO);
		respuesta.put("pgimConfiguracionBaseDTO", pgimConfiguracionBaseDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/***
	 * Permite crear un grupo de criterio.
	 * 
	 * @param pgimMatrizGrpoCrtrioDTO Portador de los datos para la creación del
	 *                                grupo criterio.
	 * @param resultadoValidacion     Resultado de la validación aplicada a nivel de
	 *                                la entidad del modelo de datos.
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-grupo_IN')")
	@PostMapping("/registrarGrupo")
	public ResponseEntity<?> registrarGrupo(@Valid @RequestBody PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el grupo de criterio");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimMatrizGrpoCrtrioDTOCreado = this.matrizSupervisionService.crearGrupoCriterio(pgimMatrizGrpoCrtrioDTO,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear el grupo de criterio");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La unidad fiscalizable ha sido creada");
		respuesta.put("pgimMatrizGrpoCrtrioDTO", pgimMatrizGrpoCrtrioDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite modificar un grupo de criterio
	 * 
	 * @param pgimMatrizGrpoCrtrioDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-grupo_MO')")
	@PutMapping("/modificarGrupo")
	public ResponseEntity<?> modificarGrupo(@Valid @RequestBody PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimMatrizGrpoCrtrio pgimMatrizGrpoCrtrioActual = null;
		PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el grupo de criterio");
			respuesta.put("error", errores.toString());
			log.error("Error al modificar el GRUPO CRITERIO: " + errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimMatrizGrpoCrtrioActual = this.matrizSupervisionService
					.getGrpoCrtrioById(pgimMatrizGrpoCrtrioDTO.getIdMatrizGrpoCrtrio());

			if (pgimMatrizGrpoCrtrioActual == null) {
				mensaje = String.format("El grupo de criterio %s que intenta actualizar no existe en la base de datos",
						pgimMatrizGrpoCrtrioDTO.getIdMatrizGrpoCrtrio());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el grupo de criterio a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimMatrizGrpoCrtrioDTOModificado = this.matrizSupervisionService.modificarGrupoCriterio(
					pgimMatrizGrpoCrtrioDTO, pgimMatrizGrpoCrtrioActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el grupo de criterio");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "el grupo de criterio ha sido modificado");
		respuesta.put("pgimMatrizGrpoCrtrioDTO", pgimMatrizGrpoCrtrioDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite eliminar el grupo criterio.
	 * 
	 * @param idGrupoCriterio
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-grupo_EL')")
	@PostMapping("/eliminarGrupo/idGrupo/{id}")
	public ResponseEntity<ResponseDTO> eliminarGrupo(@PathVariable("id") Long idGrupoCriterio) throws Exception {

		try {
			Long rpta = this.matrizSupervisionService.eliminarGrupo(idGrupoCriterio, this.obtenerAuditoria());
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",
					"Grupo criterio de la matriz de supervisión eliminado correctamente", rpta));
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDTO("error",
							"Error al intentar eliminar el grupo criterio de la matriz de supervisión: ",
							e.getMostSpecificCause().getMessage()));

		}
	}

	/**
	 * Permite obtener un grupo de matriz de supervisión por id
	 * 
	 * @param idGrupo
	 * @return
	 */

	@GetMapping("/obtenerGrupoPorId/{idGrupo}")
	public ResponseEntity<?> obtenerGrupoPorId(@PathVariable Long idGrupo) {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTO = null;

		try {
			pgimMatrizGrpoCrtrioDTO = this.matrizSupervisionService.obtenerMatrizGrupoPorId(idGrupo);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el grupo de la matríz de supervisión");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (pgimMatrizGrpoCrtrioDTO == null) {
			mensaje = String.format("El grupo de matríz de supervisión con el id: %d no existe en la base de datos",
					idGrupo);
			respuesta.put("mensaje", mensaje);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		}

		respuesta.put("mensaje", "El grupo de la matríz de supervisión ha sido recuperado");
		respuesta.put("pgimMatrizGrpoCrtrioDTO", pgimMatrizGrpoCrtrioDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/***
	 * Permite obtener las configuraciones necesarias para el formulario de
	 * creación/edición de los matrices de supervision. Acá se incluyen
	 * configuraciones como: tipo de especialidad.
	 * 
	 * @param idMatrizSupervision
	 * @return
	 */
	@GetMapping("/obtenerConfiguracionesGenerales/{idMatrizSupervision}")
	public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idMatrizSupervision) {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
		List<PgimValorParametroDTO> lPgimValorParametroDTO = null;

		try {

			lPgimEspecialidadDTO = this.parametroService
					.filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
			
			lPgimValorParametroDTO = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_NOMBRE_TIPO_NORMA);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

		respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
		respuesta.put("lTipoNorma", lPgimValorParametroDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/***
	 * Permite obtener las configuraciones necesarias para el formulario de
	 * creación/edición de los matrices de supervision. Acá se incluyen
	 * configuraciones como: tipo de especialidad.
	 * 
	 * @param idMatrizSupervision
	 * @return
	 */
	@GetMapping("/obtenerConfiguraciones/{idMatrizSupervision}")
	public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idMatrizSupervision) {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimMatrizGrpoCrtrioDTO> lPgimMatrizGrpoCrtrioDTO = null;

		try {

			lPgimMatrizGrpoCrtrioDTO = this.matrizSupervisionService.filtrarPorGrupoCriterio(idMatrizSupervision);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

		respuesta.put("lPgimMatrizGrpoCrtrioDTO", lPgimMatrizGrpoCrtrioDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@GetMapping("/obtenerConfiguracionesParametros")
	public ResponseEntity<?> obtenerConfiguracionesParametros() throws Exception {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = null;

		try {
			// lPgimConfiguracionBaseDTO =
			// this.configuracionBaseService.listaCfgBasePorIdTipoCfgBaseCVERIF(461L);
			lPgimConfiguracionBaseDTO = this.configuracionBaseService
					.listaCfgBasePorIdTipoCfgBaseCVERIF(this.valorParametroRepository
							.obtenerIdValorParametro(EValorParametro.CUADROS_BASE_VERIFICACION.toString()));

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimConfiguracionBaseDTO", lPgimConfiguracionBaseDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PostMapping("/validarExisteCuadroVerificacion")
	public ResponseEntity<?> validarExisteCuadroVerificacion(@Valid @RequestBody PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimMatrizSupervisionDTO> lPgimMatrizSupervisionDTO = null;

		if (pgimMatrizSupervisionDTO.getIdMatrizSupervision() == 0) {
			pgimMatrizSupervisionDTO.setIdMatrizSupervision(null);
		}

		try {
			lPgimMatrizSupervisionDTO = this.matrizSupervisionService.validarExisteCuadroVerificacion(pgimMatrizSupervisionDTO.getIdMatrizSupervision(), pgimMatrizSupervisionDTO.getDeMatrizSupervision().toUpperCase());

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite una persona jurídica");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Si fue posible determinar la existencia de una persona jurídica");
		respuesta.put("lPgimMatrizSupervisionDTO", lPgimMatrizSupervisionDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@PostMapping("/crearMatrizSupervision")
	public ResponseEntity<?> crearMatrizSupervision(
			@Valid @RequestBody PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear una matriz de supervisión");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimMatrizSupervisionDTOCreado = this.matrizSupervisionService
					.crearMatrizSupervision(pgimMatrizSupervisionDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear una matriz de supervisión");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La matriz de supervisión ha sido creada");
		respuesta.put("pgimMatrizSupervisionDTOCreado", pgimMatrizSupervisionDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite modificar una matriz de supervision.
	 *
	 * @param pgimMatrizSupervisionDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-lista_MO')")
	@PutMapping("/modificarMatrizSupervision")
	public ResponseEntity<?> modificarMatrizSupervision(
			@Valid @RequestBody PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimMatrizSupervision pgimMatrizSupervisionActual = null;
		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTOModificada = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la matríz de supervisión");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimMatrizSupervisionActual = this.matrizSupervisionService
					.getByIdMatrizSupervision(pgimMatrizSupervisionDTO.getIdMatrizSupervision());

			if (pgimMatrizSupervisionActual == null) {
				mensaje = String.format(
						"La matríz de supervisión %s que intenta actualizar no existe en la base de datos",
						pgimMatrizSupervisionDTO.getIdMatrizSupervision());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar la matríz de supervisión a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimMatrizSupervisionDTOModificada = this.matrizSupervisionService.modificarMatrizSupervision(
					pgimMatrizSupervisionDTO, pgimMatrizSupervisionActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar la matríz de supervisión");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La matríz de supervisión ha sido modificada");
		respuesta.put("pgimMatrizSupervisionDTOModificada", pgimMatrizSupervisionDTOModificada);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite obtener la lista criterio de la matriz de supervisión por ID de
	 * Matriz de Supervision
	 * 
	 * @param idMatrizSupervision
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-criteri_AC')")
	@PostMapping("/listarMatrizCriterio/{idMatrizSupervision}")
	public ResponseEntity<Page<PgimMatrizCriterioAuxDTO>> listarMatrizCriterio(
			@PathVariable final Long idMatrizSupervision,
			@RequestBody final PgimMatrizCriterioAuxDTO filtroPgimMatrizCriterioAuxDTO, final Pageable paginador)
			throws Exception {

		final Page<PgimMatrizCriterioAuxDTO> lPgimMatrizCriterioDTO = this.matrizSupervisionService
				.listarMatrizCriterio(idMatrizSupervision, filtroPgimMatrizCriterioAuxDTO, paginador);
		return new ResponseEntity<Page<PgimMatrizCriterioAuxDTO>>(lPgimMatrizCriterioDTO, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('ms-cri-obl_AC')")
	@GetMapping("/listarObligacionNrmaCriterio/{idMatrizCriterio}")
	public ResponseEntity<?> listarObligacionNrmaCriterio(@PathVariable Long idMatrizCriterio) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimOblgcnNrmaCrtrioDTO> lPgimOblgcnNrmaCrtrioDTO = null;

		try {
			lPgimOblgcnNrmaCrtrioDTO = this.matrizSupervisionService.listarObligacionNrmaCriterio(idMatrizCriterio);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta las obligaciones del cuadro de verificación");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los contratos SIAF");
		respuesta.put("lPgimOblgcnNrmaCrtrioDTO", lPgimOblgcnNrmaCrtrioDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite obtener el critero de matriz de supervisión por id
	 * 
	 * @param idCriterio
	 * @return
	 */
	@GetMapping("/obtenerCriterioPorId/{idCriterio}")
	public ResponseEntity<?> obtenerCriterioPorId(@PathVariable Long idCriterio) {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		PgimMatrizCriterioDTO pgimMatrizCriterioDTO = null;

		try {
			pgimMatrizCriterioDTO = this.matrizSupervisionService.obtenerMatrizCriterioPorId(idCriterio);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el criterio de la matríz de supervisión");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (pgimMatrizCriterioDTO == null) {
			mensaje = String.format("El criterio de matríz de supervisión con el id: %d no existe en la base de datos",
					idCriterio);
			respuesta.put("mensaje", mensaje);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		}

		respuesta.put("mensaje", "el criterio de la matríz de supervisión ha sido recuperado");
		respuesta.put("pgimMatrizCriterioDTO", pgimMatrizCriterioDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/***
	 * Permite crear un nuevo criterio de matriz de supervisión
	 * 
	 * @param pgimMatrizCriterioDTO Portador de los datos para la creación del
	 *                              criterio de MS.
	 * @param resultadoValidacion   Resultado de la validación aplicada a nivel de
	 *                              la entidad del modelo de datos.
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-criteri_IN')")
	@PostMapping("/crearCriterio")
	public ResponseEntity<?> crearCriterio(@Valid @RequestBody PgimMatrizCriterioDTO pgimMatrizCriterioDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimMatrizCriterioDTO pgimMatrizCriterioDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			log.error(
					"Error al registrar el GRUPO : Se han encontrado inconsistencias para crear el criterio de matriz de supervisión.");

			respuesta.put("mensaje",
					"Se han encontrado inconsistencias para crear el criterio de matriz de supervisión");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimMatrizCriterioDTOCreado = this.matrizSupervisionService.crearCriterio(pgimMatrizCriterioDTO,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			respuesta.put("mensaje", "Ocurrió un error al intentar crear el criterio de matriz de supervisión");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El criterio de matriz de supervisión ha sido creado");
		respuesta.put("pgimMatrizCriterioDTO", pgimMatrizCriterioDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/***
	 * Permite modificar un criterio de matriz de supervisión
	 * 
	 * @param pgimMatrizCriterioDTO Portador de los datos para la modificación del
	 *                              criterio de MS.
	 * @param resultadoValidacion   Resultado de la validación aplicada a nivel de
	 *                              la entidad del modelo de datos.
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-criteri_MO')")
	@PutMapping("/modificarCriterio")
	public ResponseEntity<?> modificarCriterio(@Valid @RequestBody PgimMatrizCriterioDTO pgimMatrizCriterioDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimMatrizCriterio pgimMatrizCriterioActual = null;
		PgimMatrizCriterioDTO pgimMatrizCriterioDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje",
					"Se han encontrado inconsistencias para modificar el criterio de matriz de supervisión");
			respuesta.put("error", errores.toString());
			log.error("Error al modificar el CRITERIO DE MATRIZ: " + errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimMatrizCriterioActual = this.matrizSupervisionService
					.getMatrizCriterioById(pgimMatrizCriterioDTO.getIdMatrizCriterio());

			if (pgimMatrizCriterioActual == null) {
				mensaje = String.format(
						"El criterio de matriz de supervisión %s que intenta actualizar no existe en la base de datos",
						pgimMatrizCriterioDTO.getIdMatrizCriterio());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje",
					"Ocurrió un error intentar recuperar el criterio de matriz de supervisión a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			pgimMatrizCriterioDTOModificado = this.matrizSupervisionService.modificarCriterio(pgimMatrizCriterioDTO,
					pgimMatrizCriterioActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el criterio de matriz de supervisión");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "El criterio de matriz de supervisión ha sido modificado");
		respuesta.put("pgimMatrizCriterioDTO", pgimMatrizCriterioDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite eliminar el criterio.
	 * 
	 * @param idCriterio
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-criteri_EL')")
	@PostMapping("/eliminarCriterio/idCriterio/{id}")
	public ResponseEntity<ResponseDTO> eliminarCriterio(@PathVariable("id") Long idCriterio) throws Exception {

		try {
			Long rpta = this.matrizSupervisionService.eliminarCriterio(idCriterio, this.obtenerAuditoria());
			return ResponseEntity.status(HttpStatus.CREATED).body(
					new ResponseDTO("success", "Criterio de la matriz de supervisión eliminado correctamente", rpta));
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDTO("error",
							"Error al intentar eliminar el criterio de la matriz de supervisión: ",
							e.getMostSpecificCause().getMessage()));

		}
	}

	@GetMapping("/obtenerObligacionNrmaCriterioPorId/{idOblgcnNrmaCrtrio}")
	public ResponseEntity<?> obtenerObligacionNrmaCriterioPorId(@PathVariable Long idOblgcnNrmaCrtrio) {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTO = null;

		try {
			pgimOblgcnNrmaCrtrioDTO = this.matrizSupervisionService
					.obtenerObligacionNrmaCriterioPorId(idOblgcnNrmaCrtrio);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la obligación normativa del criterio");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (pgimOblgcnNrmaCrtrioDTO == null) {
			mensaje = String.format("La obligación normativa del criterio con el id: %d no existe en la base de datos",
					idOblgcnNrmaCrtrio);
			respuesta.put("mensaje", mensaje);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		}

		respuesta.put("mensaje", "La obligación normativa del criterio ha sido recuperado");
		respuesta.put("pgimOblgcnNrmaCrtrioDTO", pgimOblgcnNrmaCrtrioDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite listar las obligaciones normativas del criterio
	 * 
	 * @param paginador
	 * @return
	 */
	@PostMapping("/listarObligacionNrmaCriterioSeleccion/{idMatrizCriterio}")
	public ResponseEntity<Page<PgimOblgcnNrmaCrtrioDTO>> listarObligacionNrmaCriterioSeleccion(
			@PathVariable final Long idMatrizCriterio,
			@RequestBody final PgimOblgcnNrmaCrtrioDTO filtroPgimOblgcnNrmaCrtrioDTO, final Pageable paginador)
			throws Exception {

		final Page<PgimOblgcnNrmaCrtrioDTO> lPgimOblgcnNrmaCrtrioDTO = this.matrizSupervisionService
				.listarObligacionNrmaCriterioSeleccion(idMatrizCriterio, filtroPgimOblgcnNrmaCrtrioDTO, paginador);
		return new ResponseEntity<Page<PgimOblgcnNrmaCrtrioDTO>>(lPgimOblgcnNrmaCrtrioDTO, HttpStatus.OK);

	}

	@GetMapping("/obtenerConfiguracionesObligaciones")
	public ResponseEntity<?> obtenerConfiguracionesObligaciones() {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> lPgimValorParametroDTOTipoNorma = null;
		List<PgimValorParametroDTO> lPgimValorParametroDTODivisionItem = null;

		try {

			lPgimValorParametroDTOTipoNorma = this.parametroService
					.filtrarPorTipoNorma(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
			lPgimValorParametroDTODivisionItem = this.parametroService
					.filtrarPorDivisionItem(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

		respuesta.put("lPgimValorParametroDTOTipoNorma", lPgimValorParametroDTOTipoNorma);
		respuesta.put("lPgimValorParametroDTODivisionItem", lPgimValorParametroDTODivisionItem);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite quitar o desvincular una obligación de un criterio de matriz de
	 * supervisión
	 * 
	 * @param idOblgcnNrmaCrtrio
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('ms-cri-obl_EL')")
	@PostMapping("/eliminarObligacionNormaCriterio/idOblgcnNrmaCrtrio/{id}")
	public ResponseEntity<ResponseDTO> eliminarObligacionNormaCriterio(@PathVariable("id") Long idOblgcnNrmaCrtrio)
			throws Exception {

		try {
			Long rpta = this.matrizSupervisionService.eliminarObligacionNormaCriterio(idOblgcnNrmaCrtrio,
					this.obtenerAuditoria());
			return ResponseEntity.status(HttpStatus.CREATED).body(
					new ResponseDTO("success",
							"Obligación desvinculada correctamente del criterio de matriz de supervisión ", rpta));
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDTO("error",
							"Error al intentar desvincular la obligación del criterio de matriz de supervisión: ",
							e.getMostSpecificCause().getMessage()));

		}
	}

	@PreAuthorize("hasAnyAuthority('ms-cri-obl_IN')")
	@PostMapping("/seleccionarObligacion")
	public ResponseEntity<?> seleccionarObligacion(@Valid @RequestBody PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para seleccionar una obligación normativa");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimOblgcnNrmaCrtrioDTOCreado = this.matrizSupervisionService.seleccionarObligacion(pgimOblgcnNrmaCrtrioDTO,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar seleccionar una obligación normativa");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La obligación normativa ha sido seleccionado");
		respuesta.put("pgimOblgcnNrmaCrtrioDTO", pgimOblgcnNrmaCrtrioDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/***
	 * Permite obtener las configuraciones necesarias para la lista
	 * de criterios del cuadro de verificación desde una fiscalización.
	 * 
	 * @param idSupervision
	 * @return
	 */
	@GetMapping("/obtenerConfiguracionesDesdeFiscalizacion/{idSupervision}")
	public ResponseEntity<ResponseDTO> obtenerConfiguracionesDesdeFiscalizacion(@PathVariable Long idSupervision) {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		List<PgimMatrizGrpoCrtrioDTO> lPgimMatrizGrpoCrtrioDTO = null;

		ResponseDTO responseDTO = null;

		Long idMatrizSupervision = null;
		try {
			idMatrizSupervision = this.matrizSupervisionService
					.obtenerIdMatrizSupervisionByIdSupervision(idSupervision);
		} catch (DataAccessException e) {

			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el identificador de la matríz de supervisión"));
		} catch (Exception e) {

			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el identificador de la matríz de supervisión"));
		}

		if (idMatrizSupervision == null) {
			mensaje = "No se ha logrado identificar un cuadro de verificación compatible para la creación del cuadro de verificación de esta fiscalización";
			respuesta.put("mensaje", mensaje);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.WARNING, mensaje));
		}

		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO = null;

		try {
			pgimMatrizSupervisionDTO = this.matrizSupervisionService.obtenerMatrizSupervisionPorId(idMatrizSupervision);
		} catch (DataAccessException e) {

			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar la matríz de supervisión"));
		}

		if (pgimMatrizSupervisionDTO == null) {
			mensaje = String.format("La matríz de supervisión con el id: %d no existe en la base de datos",
					idMatrizSupervision);
			respuesta.put("mensaje", mensaje);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
		}

		try {

			lPgimMatrizGrpoCrtrioDTO = this.matrizSupervisionService.filtrarPorGrupoCriterio(idMatrizSupervision);

		} catch (DataAccessException e) {

			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener los grupos de criterios de la matriz de fiscalización"));
		}

		respuesta.put("pgimMatrizSupervisionDTO", pgimMatrizSupervisionDTO);
		respuesta.put("lPgimMatrizGrpoCrtrioDTO", lPgimMatrizGrpoCrtrioDTO);

		mensaje = "Se obtuvieron todas las configuraciones";

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, mensaje);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	/**
	 * Permite obtener la lista de criterios del cuadro de verificación
	 * que no forman parte de una fiscalización
	 * 
	 * @param idSupervision
	 * @param idMatrizSupervision
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarCriteriosNoFiscalizacion/{idSupervision}/{idMatrizSupervision}")
	public ResponseEntity<Page<PgimMatrizCriterioAuxDTO>> listarCriteriosNoFiscalizacion(
			@PathVariable final Long idSupervision, @PathVariable final Long idMatrizSupervision,
			@RequestBody final PgimMatrizCriterioAuxDTO filtroPgimMatrizCriterioAuxDTO, final Pageable paginador)
			throws Exception {

		final Page<PgimMatrizCriterioAuxDTO> lPgimMatrizCriterioDTO = this.matrizSupervisionService
				.listarCriteriosNoFiscalizacion(idSupervision, idMatrizSupervision, filtroPgimMatrizCriterioAuxDTO,
						paginador);
		return new ResponseEntity<Page<PgimMatrizCriterioAuxDTO>>(lPgimMatrizCriterioDTO, HttpStatus.OK);

	}

	/**
	 * Permite listar los cuadros de verificación disponibles según la especialidad.
	 * 
	 * @param paginador
	 * @return
	 */
	@PostMapping("/obtenerCuadrosVerificacionPorIdEspecialidad")
	public ResponseEntity<Page<PgimMatrizSupervisionDTO>> obtenerCuadrosVerificacionPorIdEspecialidad(
			@RequestBody final PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO, final Pageable paginador)
			throws Exception {

		final Page<PgimMatrizSupervisionDTO> lPgimMatrizSupervisionDTO = this.matrizSupervisionService.listarMatrizSupervision(pgimMatrizSupervisionDTO, paginador);

		return new ResponseEntity<Page<PgimMatrizSupervisionDTO>>(lPgimMatrizSupervisionDTO, HttpStatus.OK);

	}

	/**
	 * Permite asignar el cuadro de verificación a una fiscalización que no tenga criterios asignados.
	 * @param pgimSupervisionDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
    @PreAuthorize("hasAnyAuthority('sp-lista_MO')")
    @PostMapping("/asignarCuadroVerificacion")
    public ResponseEntity<ResponseDTO> asignarCuadroVerificacion(@Valid @RequestBody PgimSupervisionDTO pgimSupervisionDTO,
            BindingResult resultadoValidacion) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        ResponseDTO responseDTO = null;
        String mensajeExcepcion;

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            mensajeExcepcion = "Se han encontrado inconsistencias al intentar asignar el caudro de verificación: " + errores.toString();
            log.error(mensajeExcepcion);
            responseDTO = new ResponseDTO("error", mensajeExcepcion);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
			PgimSupervisionDTO pgimSupervisionDTOAsegurada = this.supervisionService.obtenerSupervisionByIdSupervision(pgimSupervisionDTO.getIdSupervision());
			pgimSupervisionDTOAsegurada.setDescIdMatrizSupervision(pgimSupervisionDTO.getDescIdMatrizSupervision());
            this.supervisionService.generarCuadroVerificacion(pgimSupervisionDTOAsegurada, pgimSupervisionDTO.getDescIdPasoActual(), this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            mensajeExcepcion = "Se han encontrado inconsistencias al asignar el cuadro de verificación: "
                    + e.getMostSpecificCause().getMessage();
            respuesta.put("mensaje", "Ocurrió un error al intentar asignar el cuadro de verificación");

            responseDTO = new ResponseDTO("error", mensajeExcepcion);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);
            responseDTO = new ResponseDTO("error", e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            throw e;
        }

        responseDTO = new ResponseDTO("success", "Ok",
                "Estupendo, el cuadro de verificación ha sido asignado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

	/**
	 * Permite obtener el listado de normas que están vinculado a un cuadro de verificación
	 * @param pgimNormaAuxDTO
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarNormasDeCuadroVerificacion")
	public ResponseEntity<Page<PgimNormaAuxDTO>> listarNormasDeCuadroVerificacion(
			@RequestBody final PgimNormaAuxDTO pgimNormaAuxDTO, final Pageable paginador)
			throws Exception {

		final Page<PgimNormaAuxDTO> lPgimNormaAuxDTO = this.normaService.listarNormasDeCuadroVerificacion(pgimNormaAuxDTO, paginador);

		return new ResponseEntity<Page<PgimNormaAuxDTO>>(lPgimNormaAuxDTO, HttpStatus.OK);

	}

	@PostMapping("/copiarMatrizSupervision")
	public ResponseEntity<ResponseDTO> copiarMatrizSupervision(@Valid @RequestBody PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO,
					BindingResult resultadoValidacion) throws Exception {

			ResponseDTO responseDTO = null;
			String mensajeExcepcion;
			PgimMatrizSupervisionDTO pgimMatrizSupervisionDTOCopiado = null;

			if (resultadoValidacion.hasErrors()) {
					List<String> errores = null;

					errores = resultadoValidacion.getFieldErrors().stream()
									.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
									.collect(Collectors.toList());

					mensajeExcepcion = "Se han encontrado inconsistencias al intentar copiar el caudro de verificación: " + errores.toString();
					log.error(mensajeExcepcion);
					responseDTO = new ResponseDTO("error", mensajeExcepcion);

					return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}

			try {

				pgimMatrizSupervisionDTOCopiado = this.matrizSupervisionService.copiarMatrizSupervision(pgimMatrizSupervisionDTO,
					this.obtenerAuditoria());
					
			} catch (DataAccessException e) {
				log.error(e.getMostSpecificCause().getMessage(), e);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar copiar el cuadro de verificación: "+e.getMostSpecificCause().getMessage() , 0));

			} catch (final PgimException e) {
				// Excepcion controlada que deberá ser manejada por el frontend
				log.error(e.getMensaje(), e);
					
				TipoResultado tipoResultado;
				if (e.getTipoResultado() == null) {
						tipoResultado = TipoResultado.WARNING;
				} else {
						tipoResultado = e.getTipoResultado();
				}           

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
			} catch (final Exception e) {
				log.error(e.getMessage(), e);

				responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar copiar el cuadro de verificación");
				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

			}

			responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimMatrizSupervisionDTOCopiado,
					"Estupendo, el cuadro de verificación ha sido copiado, generando el siguiente: " + "CVERIF-"
							+pgimMatrizSupervisionDTOCopiado.getIdMatrizSupervision()+". " + pgimMatrizSupervisionDTOCopiado.getDeMatrizSupervision());

			return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	
    @DeleteMapping("/validarEliminacionCuadroVerificacion/{idMatrizSupervision}")
    public ResponseEntity<ResponseDTO> validarEliminacionCuadroVerificacion(@PathVariable Long idMatrizSupervision) throws Exception {
        
		String mensaje;
        PgimMatrizSupervision pgimMatrizActual = null;

        try {
            pgimMatrizActual = this.matrizSupervisionService.getByIdMatrizSupervision(idMatrizSupervision);

            if (pgimMatrizActual == null) {
                mensaje = String.format("El cuadro de verificación %s que intenta validar no existe en la base de datos", idMatrizSupervision);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            }

        } catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar el cuadro de verificación a actualizar", e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));

        }

        try {
            this.matrizSupervisionService.validarEliminacionCuadroVerificacion(pgimMatrizActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar validar la eliminación un cuadro de verificación", e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));

        } catch (final PgimException e) {

            log.error(e.getMensaje(), e);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), e.getValor()));
            
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, "El cuadro de verificación ha sido eliminado"));
    }
	
	@PreAuthorize("hasAnyAuthority('ms-lista_MO')")
	@DeleteMapping("/eliminarCuadroVerificacion/{idMatrizSupervision}")
    public ResponseEntity<ResponseDTO> eliminarCuadroVerificacion(@PathVariable Long idMatrizSupervision) throws Exception {
        
		String mensaje;
        PgimMatrizSupervision pgimMatrizActual = null;

        try {
            pgimMatrizActual = this.matrizSupervisionService.getByIdMatrizSupervision(idMatrizSupervision);

            if (pgimMatrizActual == null) {
                mensaje = String.format("El cuadro de verificación %s que intenta eliminar no existe en la base de datos", idMatrizSupervision);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            }

        } catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar el cuadro de verificación a actualizar", e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));

        }

        try {
            this.matrizSupervisionService.eliminarCuadroVerificacion(pgimMatrizActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar eliminar un cuadro de verificación", e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));

        } catch (final PgimException e) {

            log.error(e.getMensaje(), e);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje()));
            
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, "El cuadro de verificación ha sido eliminado"));
    }
}
