package pe.gob.osinergmin.pgim.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSectorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubsectorDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.ProcesoService;
import pe.gob.osinergmin.pgim.siged.ProcesosSiged;

import org.springframework.data.domain.Sort;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los
 * procesos
 * 
 * @descripción: Procesos
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 14/12/2023
 */
@RestController
@Slf4j
@RequestMapping("/procesos")
public class ProcesoController extends BaseController {

    @Autowired
    private ProcesoService procesoService;

    @Autowired
    private InstanciaProcesService instanciaProcesService;


    @GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() throws Exception {

        final Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();
        List<PgimSectorDTO> lPgimSectorDTO = null;
        List<PgimSubsectorDTO> lPgimSubsectorDTO = null;
        ProcesosSiged procesosSiged = null;

        try {
                        
            lPgimSectorDTO = this.procesoService.listaSectores();

            lPgimSubsectorDTO = this.procesoService.listaSubsectores();

            procesosSiged = this.procesoService.listarProcesosSiged();
            
        } catch (final PgimException e) {
                        
            // Manejo de logs
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las configuraciones generales");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron las configuraciones generales");

        
        respuesta.put("lPgimSectorDTO", lPgimSectorDTO);
        respuesta.put("lPgimSubsectorDTO", lPgimSubsectorDTO);
        respuesta.put("procesosSiged", procesosSiged);


        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('cfg010_MO')")
    @PostMapping("/crearProceso")
    public ResponseEntity<?> crearProceso(
            @Valid @RequestBody PgimProcesoDTO pgimProcesoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimProcesoDTO pgimProcesoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el flujo de trabajo");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimProcesoDTOCreado = procesoService.crearProceso(pgimProcesoDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el flujo de trabajo");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El flujo de trabajo ha sido creado");
        respuesta.put("pgimProcesoDTOCreado", pgimProcesoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('cfg010_MO')")
    @PutMapping("/modificarProceso")
    public ResponseEntity<?> modificarProceso(
            @Valid @RequestBody PgimProcesoDTO pgimProcesoDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimProceso pgimProcesoActual = null;
        PgimProcesoDTO pgimProcesoDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el flujo de trabajo");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	pgimProcesoActual = procesoService.ObtenerProceso(pgimProcesoDTO.getIdProceso());

            if (pgimProcesoActual == null) {
                mensaje = String.format("El fllujo de trabajo %s que intenta actualizar no existe en la base de datos",
                		pgimProcesoDTO.getIdProceso());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el flujo de trabajo a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        try {
        	pgimProcesoDTOModificado = this.procesoService.modificarProceso(pgimProcesoDTO,
            		pgimProcesoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el agente fiscalizado");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El flujo de trabajo ha sido modificado");
        respuesta.put("pgimProcesoDTOModificado", pgimProcesoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('cfg010_EL')")
    @DeleteMapping("/eliminarProceso/{idProceso}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long idProceso) throws Exception {
		ResponseDTO responseDTO = null;
		String mensaje;

		PgimProceso pgimProcesoActual = null;
        List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = null;

		try {
			pgimProcesoActual = this.procesoService.ObtenerProceso(idProceso);

			if (pgimProcesoActual == null) {
				mensaje = String.format("El flujo de trabajo %s que intenta eliminar no existe en la base de datos",
						idProceso);
                responseDTO = new ResponseDTO("error", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}
		} catch (DataAccessException e) {
			mensaje = String.format("mensaje", "Ocurrió un error intentar recuperar el  flujo de trabajo a eliminar",
			e.getMessage());
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

        try {
			lPgimInstanciaProcesDTO = this.instanciaProcesService.listarInstanciaProcesPorProceso(idProceso);

			if (lPgimInstanciaProcesDTO.size() > 0) {
				mensaje = String.format("No se puede eliminar el flujo de trabajo porque este no debe tener ninguna instancia de proceso asociada.",
						idProceso);
                responseDTO = new ResponseDTO("validation", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}
		} catch (DataAccessException e) {
			mensaje = String.format("mensaje", "Ocurrió un error intentar validar el  flujo de trabajo a eliminar",
			e.getMessage());
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			this.procesoService.eliminarProceso(pgimProcesoActual, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			mensaje = String.format("mensaje", "Ocurrió un error intentar eliminar el flujo de trabajo",
            e.getMessage());
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		responseDTO = new ResponseDTO("success", "El flujo de trabajo ha sido eliminado");

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

    @PreAuthorize("hasAnyAuthority('cfg010_AC')")
    @GetMapping("/listarProcesos")
    public ResponseEntity<List<PgimProcesoDTO>> listarProcesos(@RequestParam(name = "sort", defaultValue = "idProceso,asc") String sort) throws Exception {

        String[] camposOrdenamiento = sort.split(",");
        String campo = camposOrdenamiento[0];
        Sort.Direction direccion = Sort.Direction.fromString(camposOrdenamiento[1]);
        
        List<PgimProcesoDTO> lPgimProcesoDTO = this.procesoService.listarProceso(campo, direccion);

        return new ResponseEntity<List<PgimProcesoDTO>>(lPgimProcesoDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener los datos del flujo de trabajo del proceso por Id
     * @param idProceso
     * @return
     */
    @GetMapping("/obtenerProcesoPorId/{idProceso}")
    public ResponseEntity<?> obtenerProcesoPorId(@PathVariable Long idProceso) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimProcesoDTO pgimProcesoDTO = null;

        try {
            pgimProcesoDTO = this.procesoService.obtenerProcesoPorId(idProceso);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el flujo de trabajo del proceso.");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimProcesoDTO == null) {
            mensaje = String.format("El flujo de trabajo del proceso con el id: %d no existe en la base de datos", idProceso);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El flujo de trabajo del proceso ha sido recuperado");
        respuesta.put("pgimProcesoDTO", pgimProcesoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
