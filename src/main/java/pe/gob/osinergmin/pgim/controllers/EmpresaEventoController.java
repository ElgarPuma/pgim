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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaEvento;
import pe.gob.osinergmin.pgim.services.EmpresaEventoService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador del componente empresa evento, en el portal se encuentra en la ruta: info maestra/unidad minera/evento/empresas,
 * y en la ruta: info maestra/agente supervisado/unidad minera/evento/empresas, este componente contiene los metodos necesarios para el crud y validaciones respectivas.
 * contiene los siguientes metodos:
 * listar
 * obtenerConfiguraciones
 * listarPorPalabraClave
 * crearEmpresaEvento
 * delete
 * modificarEmpresaEvento
 * 
 * @descripción: Empresa evento
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 *
 */
@RestController
@Slf4j
@RequestMapping("/empresaeventos")
public class EmpresaEventoController extends BaseController {

    @Autowired
    private EmpresaEventoService empresaEventoService;

    @Autowired
    private ParametroService parametroService;

    @PreAuthorize("hasAnyAuthority('um-ev-empr_AC')")
    @GetMapping("/listar/empevento/{idEvento}")
    public ResponseEntity<List<PgimEmpresaEventoDTO>> listar(@PathVariable Long idEvento) {

        List<PgimEmpresaEventoDTO> emprevent = new ArrayList<>();

        emprevent = empresaEventoService.listarEmpresaEvento(idEvento);

        if (emprevent == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(emprevent);

    }

    /**
     * Permite obtener las configuraciones necesarias para el formulario del evento.
     * 
     * @param idEvento        Identifidador del evento asociado al empresa involucrada.
     * @param idEmpresaEvento Identificador de la empresa del evento; tiene valor
     *                        distinto de cero cuando empresa ya fue incluida como
     *                        involucrada en el evento.
     * @return
     */
    @PreAuthorize("hasAnyAuthority('um-ev-empr_CO')")
    @GetMapping("/obtenerConfiguraciones/{idEvento}/{idEmpresaEvento}")
    public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idEvento, @PathVariable Long idEmpresaEvento) {


        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOCIIU = null;
        PgimEmpresaEventoDTO pgimEmpresaEventoDTO = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoEmpInvolucrada= null;
        
        try {
            lPgimValorParamDTOCIIU = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ACTIVIDAD_CIIU);
            
            lPgimValorParamDTOTipoEmpInvolucrada = this.parametroService.listarValoresParametro(ConstantesUtil.TIPO_EMPRESA_INVOLUCRADA);
            
            if (idEmpresaEvento != 0) {
                pgimEmpresaEventoDTO = this.empresaEventoService.obtenerEmpresaEvento(idEmpresaEvento);
            }

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

        respuesta.put("lPgimValorParamDTOCIIU", lPgimValorParamDTOCIIU);
        respuesta.put("lPgimValorParamDTOTipoEmpInvolucrada", lPgimValorParamDTOTipoEmpInvolucrada);
        respuesta.put("pgimEmpresaEventoDTO", pgimEmpresaEventoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite recuperar la lista de empresas candidatas a ser relacionadas como
     * empresas involucradas de un evento dado.
     * 
     * @param idEvento Identifidador del evento sobre el que se requiere relacionar
     *                 a las empresas.
     * @param palabra  Palabra clave especificada por el usuario para buscar la
     *                 lista de empresas.
     * @return
     */
    @GetMapping("/filtrar/palabraClave/{idEvento}/{palabra}")
    public ResponseEntity<?> listarPorPalabraClave(@PathVariable Long idEvento, @PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimEmpresaEventoDTO> lPgimEmpresaEventoDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimEmpresaEventoDTO = new ArrayList<PgimEmpresaEventoDTO>();
            respuesta.put("mensaje", "No se encontraron empresas para este evento");
            respuesta.put("lPgimUnidadMineraDTO", lPgimEmpresaEventoDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimEmpresaEventoDTO = this.empresaEventoService.listarPorPalabraClave(idEvento, palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las unidades fiscalizables");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las empresas candidatas a asociar al avento");
        respuesta.put("lPgimEmpresaEventoDTO", lPgimEmpresaEventoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /***
	 * Permite crear la empresa involucrada.
	 * 
	 * @param pgimEmpresaEventoDTO Portador de los datos para la creación de la
	 *                             empresa evento.
	 * @param resultadoValidacion  Resultado de la validación aplicada a nivel de la
	 *                             entidad del modelo de datos.
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-ev-empr_IN')")
	@PostMapping("/crearEmpresaEvento")
	public ResponseEntity<?> crearEmpresaEvento(@Valid @RequestBody PgimEmpresaEventoDTO pgimEmpresaEventoDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimEmpresaEventoDTO pgimEmpresaEventoDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear empresa involucrada");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimEmpresaEventoDTOCreado = this.empresaEventoService.crearEmpresaEvento(pgimEmpresaEventoDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear empresa involucrada");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Empresa involucrada ha sido creada");
		respuesta.put("pgimEmpresaEventoDTO", pgimEmpresaEventoDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    /**
	 * Permite eliminar una empresa involucrada. Esta eliminación es lógica.
	 * 
	 * @param idEmpresaEvento
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-ev-empr_EL')")
	@DeleteMapping("/eliminarEmpresaEvento/{idEmpresaEvento}")
	public ResponseEntity<?> delete(@PathVariable Long idEmpresaEvento) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimEmpresaEvento pgimEmpresaEventoActual = null;

		try {
			pgimEmpresaEventoActual = this.empresaEventoService.getByIdEmpresaEvento(idEmpresaEvento);

			if (pgimEmpresaEventoActual == null) {
				mensaje = String.format("La empresa involucrada %s que intenta eliminar no existe en la base de datos",
                idEmpresaEvento);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar la empresa involucrada a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.empresaEventoService.eliminarEmpresaEvento(pgimEmpresaEventoActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar empresa involucrada");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
    @PreAuthorize("hasAnyAuthority('um-ev-empr_MO')")
	@PostMapping("/modificarEmpresaEvento")
	public ResponseEntity<?> modificarEmpresaEvento(@Valid @RequestBody PgimEmpresaEventoDTO pgimEmpresaEventoDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimEmpresaEventoDTO pgimEmpresaEventoDTOCreado = null;
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
			pgimEmpresaEventoDTOCreado = this.empresaEventoService.modificarEmpresaEvento(pgimEmpresaEventoDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar la empresa evento");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La empresa evento ha sido modificada");
		respuesta.put("pgimAccidentadoDTO", pgimEmpresaEventoDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
    
    /**
     * ermite listar las empresas contratistas asociadas a accidentes mortales (eventos) registrados como motivos de una supervisión, 
     * pasibles de ser seleccionadas como responsables de una infracción.
     * 
     * @param idSupervision
     * @param idInfraccion
     * @return
     */
    @GetMapping("/listarContratistaAmSelecInfraccion/{idSupervision}/{idInfraccion}")
    public ResponseEntity<List<PgimEmpresaEventoDTO>> listarContratistaAmSelecInfraccion(@PathVariable Long idSupervision,
    		@PathVariable Long idInfraccion) {

        List<PgimEmpresaEventoDTO> lstPgimEmpresaEventoDTO = new ArrayList<>();

        lstPgimEmpresaEventoDTO = empresaEventoService.listarEmpresaEventoSelecResponsableInfraccion(idSupervision, 
        		ConstantesUtil.PARAM_TE_ACCIDENTE_MORTAL, idInfraccion);

        if (lstPgimEmpresaEventoDTO == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lstPgimEmpresaEventoDTO);

    }
}
