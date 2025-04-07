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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervisionMotivo;
import pe.gob.osinergmin.pgim.services.SupervisionMotivoService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas al motivo de
 * la supervision
 * 
 * @descripción: Supervision motivo
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/supervisionmotivos")
public class SupervisionMotivoController extends BaseController {

	@Autowired
	private SupervisionMotivoService supervisionMotivoService;

	/**
	 * Permite listar los motivos de la supervision + las herramientas de las
	 * paginaciones
	 * 
	 * @param idSupervision
	 */
	@PreAuthorize("hasAnyAuthority('sp-ps-mot_AC')")
	@PostMapping("/listarSupervisionMotivoAux")
	public ResponseEntity<Page<PgimSupervisionMotivoAuxDTO>> listarSupervisionMotivoAux(@RequestBody final Long idSupervision,
			final Pageable paginador) {

		final Page<PgimSupervisionMotivoAuxDTO> motivo = this.supervisionMotivoService
				.listarSupervisionMotivoAux(idSupervision, paginador);
		return new ResponseEntity<Page<PgimSupervisionMotivoAuxDTO>>(motivo, HttpStatus.OK);
	}

	/**
	 * Permite obtener las configuraciones necesarias para el formulario del evento.
	 * 
	 * @param idSupervision     
	 * @param idSupervisionMotivo 
	 * @return
	 */
	@GetMapping("/obtenerConfiguraciones/{idSupervision}/{idSupervisionMotivo}")
	public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idSupervision,
			@PathVariable Long idSupervisionMotivo) {

		Map<String, Object> respuesta = new HashMap<>();
		PgimSupervisionMotivoDTO pgimSupervisionMotivoDTO = null;

		try {
			if (idSupervisionMotivo != 0) {
				pgimSupervisionMotivoDTO = this.supervisionMotivoService.obtenerSupervisionMotivo(idSupervisionMotivo);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("pgimSupervisionMotivoDTO", pgimSupervisionMotivoDTO);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}


	/**
	 * Me permite asociar la supervicion a un evento especifico
	 * 
	 * @param pgimSupervisionMotivoDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('sp-ps-mot_IN')")
	@PostMapping("/crearSupervisionMotivo")
	public ResponseEntity<?> crearSupervisionMotivo(@Valid @RequestBody PgimSupervisionMotivoDTO pgimSupervisionMotivoDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimSupervisionMotivoDTO pgimSupervisionMotivoDTOcreada = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el motivo de la fiscalización");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			// Grabar
			String cadenaIdEvento = pgimSupervisionMotivoDTO.getDescCoEvento();
			pgimSupervisionMotivoDTO.setDescCoEvento(null);
			if (cadenaIdEvento.contains("U")) {
				String[] textSplit = cadenaIdEvento.split("U");
				for (int j = 0; j < textSplit.length; j++) {
					String[] ids = textSplit[j].split("-");
					Long idTipoMotivo = Long.parseLong(ids[0]);
					Long idObjetoMotivo = Long.parseLong(ids[1]);
					
					pgimSupervisionMotivoDTO.setIdTipoMotivoInicio(idTipoMotivo);
					pgimSupervisionMotivoDTO.setIdObjetoMotivoInicio(idObjetoMotivo);

					pgimSupervisionMotivoDTOcreada = this.supervisionMotivoService
							.crearSupervisionMotivo(pgimSupervisionMotivoDTO, this.obtenerAuditoria());
				}
			} else {

				String[] ids = cadenaIdEvento.split("-");
				Long idTipoMotivo = Long.parseLong(ids[0]);
				Long idObjetoMotivo = Long.parseLong(ids[1]);
				
				pgimSupervisionMotivoDTO.setIdTipoMotivoInicio(idTipoMotivo);
				pgimSupervisionMotivoDTO.setIdObjetoMotivoInicio(idObjetoMotivo);
				
				pgimSupervisionMotivoDTOcreada = this.supervisionMotivoService.crearSupervisionMotivo(pgimSupervisionMotivoDTO,
						this.obtenerAuditoria());
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear la selección de motivo(s)");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Genial, se ha seleccionado nuevo(s) motivo(s)");
		respuesta.put("pgimSupMotivoDTO", pgimSupervisionMotivoDTOcreada);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyAuthority('sp-ps-mot_EL')")
	@DeleteMapping("/eliminarSupervisionMotivo/{idSupervisionMotivo}")
	public ResponseEntity<?> delete(@PathVariable Long idSupervisionMotivo) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimSupervisionMotivo pgimSupervisionMotivoActual = null;

		try {
			pgimSupervisionMotivoActual = this.supervisionMotivoService.getByIdSupervisionMotivo(idSupervisionMotivo);

			if (pgimSupervisionMotivoActual == null) {
				mensaje = String.format("El motivo %s que intenta eliminar no existe en la base de datos",
						idSupervisionMotivo);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar el motivo a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.supervisionMotivoService.eliminarSupervisionMotivo(pgimSupervisionMotivoActual,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar un motivo");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}	
	
	
	/**
	 * Permite filtrar los motivos pasibles de selección para una fiscalización, 
     * según la unidad minera de esta
     * 
	 * @param filtroSupervisionMotivoAux
	 * @param paginador
	 * @return
	 */
	@PostMapping("/filtrarSeleccionMotivosPorUM")
	public ResponseEntity<Page<PgimSupervisionMotivoAuxDTO>> filtrarSeleccionMotivosPorUM(
			@RequestBody PgimSupervisionMotivoAuxDTO filtroSupervisionMotivoAux, Pageable paginador) {

		Page<PgimSupervisionMotivoAuxDTO> lPgimSupervisionMotivoAuxDTO = this.supervisionMotivoService
				.filtrarSeleccionMotivosPorUM(filtroSupervisionMotivoAux.getIdUnidadMinera(),
						filtroSupervisionMotivoAux.getIdSupervision(), paginador);
		
		return new ResponseEntity<Page<PgimSupervisionMotivoAuxDTO>>(lPgimSupervisionMotivoAuxDTO, HttpStatus.OK);
	}

}
