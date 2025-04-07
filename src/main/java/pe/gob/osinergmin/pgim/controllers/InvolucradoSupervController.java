package pe.gob.osinergmin.pgim.controllers;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInvolucradoSuperv;
import pe.gob.osinergmin.pgim.services.InvolucradoSupervService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a
 * InvolucradoSuperv
 * 
 * @descripción: Involucrado superv
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 18/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/involucrados")
public class InvolucradoSupervController extends BaseController {

	@Autowired
	private InvolucradoSupervService involucradoSupervService;

	@Autowired
	private ParametroService parametroService;

	/**
	 * Permite listar los involucrados o representantes de la supervision
	 * 
	 * @param idContrato
	 * 
	 */
	@PreAuthorize("hasAnyAuthority('sp-sc-repr_AC')")
	@GetMapping("/listarInvolucradoSupervision/{idSupervision}/{idValorParametro}")
	public ResponseEntity<List<PgimInvolucradoSupervDTO>> listarInvolucradoSupervision(@PathVariable Long idSupervision,
			@PathVariable Long idValorParametro) throws Exception {

		if (idSupervision == 0) {
			idSupervision = null;
		}
		if (idValorParametro == 0) {
			idValorParametro = null;
		}
		List<PgimInvolucradoSupervDTO> involucrado = new ArrayList<>();
		involucrado = involucradoSupervService.listarInvolucradoSupervision(idSupervision, idValorParametro);

		if (involucrado == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(involucrado);
	}

	/**
	 * Permite obtener las configuraciones necesarias para el formulario del
	 * involucrado de la supervison o representantes.
	 * 
	 * @param idContrato
	 * @param idInvolucradoSuperv
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('sp-sc-repr_CO')")
	@GetMapping("/obtenerConfiguraciones/{idSupervision}/{idInvolucradoSuperv}")
	public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idSupervision,
			@PathVariable Long idInvolucradoSuperv) {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> lPgimValorParamDTOTipoInvolucrado = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOTipoPrefijo = null;

		PgimInvolucradoSupervDTO pgimInvolucradoSupervDTO = null;

		try {

			lPgimValorParamDTOTipoInvolucrado = this.parametroService
					.filtrarPorNombreTipoInvolucrado(ConstantesUtil.PARAM_TIPO_INVOLUCRADO);

			lPgimValorParamDTOTipoPrefijo = this.parametroService
					.filtrarPorNombreTipoPrefijo(ConstantesUtil.PARAM_TIPO_PREFIJO);

			if (idInvolucradoSuperv != 0) {
				pgimInvolucradoSupervDTO = this.involucradoSupervService
						.obtenerInvolucradoSupervision(idInvolucradoSuperv);
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

		respuesta.put("pgimInvolucradoSupervDTO", pgimInvolucradoSupervDTO);
		respuesta.put("lPgimValorParamDTOTipoInvolucrado", lPgimValorParamDTOTipoInvolucrado);
		respuesta.put("lPgimValorParamDTOTipoPrefijo", lPgimValorParamDTOTipoPrefijo);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@GetMapping("/listarPorInvolucrado/palabraClave/{palabra}")
	public ResponseEntity<?> listarPorInvolucrado(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimInvolucradoSupervDTO> lPgimInvolucradoDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimInvolucradoDTO = new ArrayList<PgimInvolucradoSupervDTO>();
			respuesta.put("mensaje", "Se encontraron los involucrados");
			respuesta.put("lPgimInvolucradoDTO", lPgimInvolucradoDTO);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		try {
			lPgimInvolucradoDTO = this.involucradoSupervService.listarPorInvolucradoAiAs(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los involucrados de la supervisión");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "Se encontraron los involucrados");
		respuesta.put("lPgimInvolucradoDTO", lPgimInvolucradoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Me permite crear el involucrado de una supervision
	 * 
	 * @param pgimInvolucradoSupervDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-sc-repr_IN')")
	@PostMapping("/crearInvolucradoSupervision")
	public ResponseEntity<?> crearInvolucradoSupervision(
			@Valid @RequestBody PgimInvolucradoSupervDTO pgimInvolucradoSupervDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimInvolucradoSupervDTO pgimInvolucradoSupervDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear involucrado de una supervision");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimInvolucradoSupervDTOCreado = this.involucradoSupervService
					.crearInvolucradoSupervision(pgimInvolucradoSupervDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear involucrado de una supervision");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "involucrado de una supervision ha sido creado");
		respuesta.put("pgimInvolucradoSupervDTO", pgimInvolucradoSupervDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Me permite modificar el involucrado de una supervision
	 * 
	 * @param pgimInvolucradoSupervDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-sc-repr_MO')")
	@PostMapping("/modificarInvolucradoSupervision")
	public ResponseEntity<?> modificarInvolucradoSupervision(
			@Valid @RequestBody PgimInvolucradoSupervDTO pgimInvolucradoSupervDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimInvolucradoSuperv pgimInvolucradoSupervActual = null;
		PgimInvolucradoSupervDTO pgimInvolucradoSupervDTOModificado = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;
			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());
			respuesta.put("mensaje",
					"Se han encontrado inconsistencias para modificar un involucrado de una supervision");
			respuesta.put("error", errores.toString());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
		try {
			pgimInvolucradoSupervActual = this.involucradoSupervService
					.getByIdInvolucradoSupervision(pgimInvolucradoSupervDTO.getIdInvolucradoSuperv());

			if (pgimInvolucradoSupervActual == null) {
				mensaje = String.format(
						"El involucrado de una supervision %s que intenta actualizar no existe en la base de datos",
						pgimInvolucradoSupervDTO.getIdInvolucradoSuperv());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje",
					"Ocurrió un error intentar recuperar el involucrado de una supervision a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			pgimInvolucradoSupervDTOModificado = this.involucradoSupervService
					.modificarInvolucradoSupervision(pgimInvolucradoSupervDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar un involucrado de una supervision");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "involucrado de una supervision ha sido modificado");
		respuesta.put("pgimInvolucradoSupervDTO", pgimInvolucradoSupervDTOModificado);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyAuthority('sp-sc-repr_EL')")
	@DeleteMapping("/eliminarInvolucradoSupervision/{idInvolucradoSuperv}")
	public ResponseEntity<?> delete(@PathVariable Long idInvolucradoSuperv) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimInvolucradoSuperv pgimEmpresaEventoActual = null;

		try {
			pgimEmpresaEventoActual = this.involucradoSupervService.getByIdInvolucradoSupervision(idInvolucradoSuperv);

			if (pgimEmpresaEventoActual == null) {
				mensaje = String.format("El involucrado %s que intenta eliminar no existe en la base de datos",
						idInvolucradoSuperv);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar el involucrado a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.involucradoSupervService.eliminarInvolucradoSupervision(pgimEmpresaEventoActual,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar un involucrado");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite modificar el campo observacion de acta de inicio
	 * 
	 * @param supervisionDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-m-super_MO')")
	@PostMapping("modificar/supervision/observacionInicio")
	public ResponseEntity<ResponseDTO> modificarObservacionInicioSuper(@RequestBody PgimSupervisionDTO supervisionDTO)
			throws Exception {

		Long rpta = involucradoSupervService.modificarObservacionInicioSuper(supervisionDTO, this.obtenerAuditoria());

		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseDTO("success", "La observación del acta de inicio ha sido actualizada correctamente", rpta));
	}

	/**
	 * Permite modificar el campo observacion de acta de supervisión
	 * 
	 * @param supervisionDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-m-super_MO')")
	@PostMapping("modificar2/supervision2/observacionFin")
	public ResponseEntity<ResponseDTO> modificarObservacionFinSuper(@RequestBody PgimSupervisionDTO supervisionDTO)
			throws Exception {

		Long rpta = involucradoSupervService.modificarObservacionFinSuper(supervisionDTO, this.obtenerAuditoria());

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",
				"La observación del acta de fiscalización ha sido actualizada correctamente", rpta));
	}

	@GetMapping("/existeRepresentanteAi/{idValorParametro}/{idSupervision}/{coDocumentoIdentidad}")
    public ResponseEntity<?> existeRepresentanteAi(@PathVariable Long idValorParametro, @PathVariable Long idSupervision, 
            @PathVariable String coDocumentoIdentidad) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimInvolucradoSupervDTO> lPgimInvolucradoSupervDTO = null;

        if (idValorParametro == 0) {
            idValorParametro = null;
		}

        try {
            lPgimInvolucradoSupervDTO = this.involucradoSupervService.existeRepresentanteAi(idValorParametro, idSupervision, coDocumentoIdentidad);
           
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite un número de expediente Siged");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de un número de expediente Siged");
        respuesta.put("lPgimInvolucradoSupervDTO", lPgimInvolucradoSupervDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
