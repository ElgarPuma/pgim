package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalContrato;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PersonalContratoService;
import pe.gob.osinergmin.pgim.services.RolProcesoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Controlador para la gestión de las funcionalidades relacionadas al personal
 * del contrato
 * 
 * @descripción: Personal del contrato
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/personalcontratos")
public class PersonalContratoController extends BaseController {

	@Autowired
	private PersonalContratoService personalContratoService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
    private RolProcesoService rolProcesoService;
	
	/**
	 * Me permite filtrar las personas de un contrato por DNI, Nombres y Apellidos completos
	 * @param idContrato
	 * @param palabra
	 * @return
	 */
	@GetMapping("/listarPorPersona/palabraClave/{idContrato}/{palabra}")
	public ResponseEntity<?> listarPorPersona(@PathVariable Long idContrato, @PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimPersonalContratoDTO> lPgimPersonaDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimPersonaDTO = new ArrayList<PgimPersonalContratoDTO>();
			respuesta.put("mensaje", "Se encontraron las personas jurídicas");
			respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

		try {
			lPgimPersonaDTO = this.personalContratoService.listarPorPersona(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la persona jurídica");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron las personas jurídicas");
		respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite listar los personales de contrato + las herramientas de las paginaciones
	 * @param idContrato
	 */
	@PreAuthorize("hasAnyAuthority('co-persona_AC')")
	@PostMapping("/listarPersonalContrato")
	public ResponseEntity<Page<PgimPersonalContratoDTO>> listarPersonalContrato(@RequestBody final Long idContrato,
			final Pageable paginador) {

		final Page<PgimPersonalContratoDTO> personal = this.personalContratoService.listarPersonalContrato(idContrato,
				paginador);
		return new ResponseEntity<Page<PgimPersonalContratoDTO>>(personal, HttpStatus.OK);
	}

	/**
	 * Permite obtener las configuraciones necesarias para el formulario del evento.
	 * 
	 * @param idContrato         Identifidador del evento asociado al contrato.
	 * @param idPersonalContrato Identificador de personal de contrato
	 * @return
	 */
	@GetMapping("/obtenerConfiguraciones/{idContrato}/{idPersonalContrato}")
	public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idContrato, @PathVariable Long idPersonalContrato) {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimValorParametroDTO> lPgimValorParamDTOTipDocId = null;
		PgimPersonalContratoDTO pgimPersonalContratoDTO = null;
		List<PgimRolProcesoDTO> lPgimRolProcesoDTOSupervisora = null;
		List<PgimValorParametroDTO> lPgimValorParametro = null;

		try {

			lPgimValorParamDTOTipDocId = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_DOCUMENTO_IDENTIDAD);
			lPgimValorParametro = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);

			lPgimRolProcesoDTOSupervisora = this.rolProcesoService.listarRolesPersonalContrato(); // STORY: PGIM-7276: Relación de personal con roles del proceso de fiscalización

			// Se excluye el tipo de documento RUC, debido a que el personal de contrato no se registrará con RUC.
			if (lPgimValorParamDTOTipDocId.size() > 0) {
				PgimValorParametroDTO pgimValorParametroDTO2Remove = null;
				for (PgimValorParametroDTO pgimValorParametroDTO : lPgimValorParamDTOTipDocId) {
					if (pgimValorParametroDTO.getIdValorParametro().equals(this.parametroService.obtenerIdValorParametroDesdeClaveTexto(EValorParametro.DOIDE_RUC.toString()))) {
						pgimValorParametroDTO2Remove = pgimValorParametroDTO;
					}
				}

				if (pgimValorParametroDTO2Remove != null) {
					lPgimValorParamDTOTipDocId.remove(pgimValorParametroDTO2Remove);
				}
			}

			if (idPersonalContrato != 0) {
				pgimPersonalContratoDTO = this.personalContratoService.obtenerPersonalContrato(idPersonalContrato);
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

		respuesta.put("pgimPersonalContratoDTO", pgimPersonalContratoDTO);
		respuesta.put("lPgimValorParamDTOTipDocId", lPgimValorParamDTOTipDocId);
		respuesta.put("lPgimRolProcesoDTOSupervisora", lPgimRolProcesoDTOSupervisora);
		respuesta.put("lPgimValorParametro", lPgimValorParametro);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Me permite agregar el personal de un contrato
	 * "Este metodo esta en uso"
	 * @param pgimPersonalContratoDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('co-persona_IN')")
	@PostMapping("/asignarPersonalContrato")
	public ResponseEntity<?> agregarPersonalContrato(
			@Valid @RequestBody PgimPersonalContratoDTO pgimPersonalContratoDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimPersonalContratoDTO pgimPersonalContratoDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para agregar personal de contrato");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimPersonalContratoDTOCreado = this.personalContratoService
					.agregarPersonalContrato(pgimPersonalContratoDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar agregar personal de contrato");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "personal de contrato ha sido agregado");
		respuesta.put("pgimPersonalContratoDTO", pgimPersonalContratoDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Permite eliminar un personal de contrato. Esta eliminación es lógica.
	 * 
	 * @param idPersonalContrato
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('co-persona_EL')")
	@DeleteMapping("/eliminarPersonalContrato/{idPersonalContrato}")
	public ResponseEntity<?> delete(@PathVariable Long idPersonalContrato) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimPersonalContrato pgimPersonalContratoActual = null;

		try {
			pgimPersonalContratoActual = this.personalContratoService.getByIdPersonalContrato(idPersonalContrato);

			if (pgimPersonalContratoActual == null) {
				mensaje = String.format("El personal de contrato %s que intenta eliminar no existe en la base de datos",
						idPersonalContrato);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar personal de contrato a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.personalContratoService.eliminarPersonalContrato(pgimPersonalContratoActual, this.obtenerAuditoria());
			;
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar un personal de contrato");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}


	/**
	 * Me permite modificar el personal de un contrato
	 * "Este metodo se esta en uso todavia"
	 * @param pgimPersonalContratoDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('co-persona_MO')")
	@PostMapping("/modificarPersonalContrato")
	public ResponseEntity<?> modificarPersonalContrato(
			@Valid @RequestBody PgimPersonalContratoDTO pgimPersonalContratoDTO, BindingResult resultadoValidacion)
			throws Exception {

		PgimPersonalContratoDTO pgimPersonalContratoDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear a la empresa evento");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimPersonalContratoDTOCreado = this.personalContratoService
					.modificarPersonalContratoAsig(pgimPersonalContratoDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar la empresa evento");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La empresa evento ha sido modificada");
		respuesta.put("pgimPersonalContratoDTO", pgimPersonalContratoDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/**
	 * Este método me permite validar las personas de los Contratos que estan
	 * Vigente o no Vigente
	 * 
	 * @param idContrato
	 * @param idPersona
	 * @param idPersonalContrato
	 * @return
	 */
	@GetMapping("/existePersonalContrato/{idContrato}/{idPersona}/{idPersonalContrato}")
	public ResponseEntity<ResponseDTO> existePersonalContrato(@PathVariable Long idContrato,
			@PathVariable Long idPersona, @PathVariable Long idPersonalContrato) {
		String mensaje = "";
		Number existe = 0;

		if (idContrato == 0) {
			idContrato = null;
		}

		if (idPersonalContrato == 0) {
			idPersonalContrato = null;
		}

		if (idPersona == 0) {
			idPersona = null;
		}
		List<PgimPersonalContratoDTO> lPgimPersonalContratoDTO = null;
		try {
			lPgimPersonalContratoDTO = this.personalContratoService.existePersonalContrato(idContrato, idPersona, idPersonalContrato);
			if (lPgimPersonalContratoDTO != null) {
				existe = lPgimPersonalContratoDTO.size();
			}
			mensaje = "Si fue posible determinar la validadcion de la persona";
		} catch (DataAccessException e) {
			mensaje = "Ocurrió un error al realizar la validacion de la persona";
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error", mensaje, existe));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", mensaje, existe));
	}

	@GetMapping("/existeNoUsuario/{idPersonalContrato}/{noUsuario}")
    public ResponseEntity<?> existeNoUsuario(@PathVariable Long idPersonalContrato,
            @PathVariable String noUsuario) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonalContratoDTO> lPgimContratoDTO = null;

        if (idPersonalContrato == 0) {
            idPersonalContrato = null;
        }

        try {
            lPgimContratoDTO = this.personalContratoService.existeNoUsuario(idPersonalContrato, noUsuario.toUpperCase());
           
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite un usuario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de un usuario");
        respuesta.put("lPgimContratoDTO", lPgimContratoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
