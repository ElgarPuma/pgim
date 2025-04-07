package pe.gob.osinergmin.pgim.controllers;

import java.util.Date;
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
import pe.gob.osinergmin.pgim.dtos.PgimTdrPlantillaDTO;
import pe.gob.osinergmin.pgim.services.ConfiguracionBaseService;
import pe.gob.osinergmin.pgim.services.TdrPlantillaService;
import pe.gob.osinergmin.pgim.utils.EValorParametro;
import pe.gob.osinergmin.pgim.models.entity.PgimTdrPlantilla;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;

/**
 * Controlador para la gestión de las funcionalidades relacionadas al Terminos de referencia.
 * 
 * @descripción: Terminos de referencia
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/10/2020
 */
@RestController
@Slf4j
@RequestMapping("/tdrbases")
public class TdrPlantillaController extends BaseController {

    @Autowired
    private TdrPlantillaService tdrPlantillaService;
    
    @Autowired
    private ConfiguracionBaseService configuracionBaseService;
    
    @Autowired
    private ValorParametroRepository valorParametroRepository;

    @PreAuthorize("hasAnyAuthority('cfg003_AC')")
    @PostMapping("/listarTdrBase")
    public ResponseEntity<Page<PgimTdrPlantillaDTO>> listarTdrBase(@RequestBody PgimTdrPlantillaDTO filtro,
            Pageable paginador) throws Exception {

        Page<PgimTdrPlantillaDTO> lPgimTdrPlantillaDTO = this.tdrPlantillaService.listarTdrBase(filtro, paginador);
        return new ResponseEntity<Page<PgimTdrPlantillaDTO>>(lPgimTdrPlantillaDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerTdrBasePorId/{idTdrPlantilla}")
    public ResponseEntity<?> obtenerReportePorId(@PathVariable Long idTdrPlantilla) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimTdrPlantillaDTO pgimTdrPlantillaDTO  = null;
        PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO = null;
        try {
        	pgimTdrPlantillaDTO = this.tdrPlantillaService.obtenerTdrBasePorId(idTdrPlantilla);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la plantilla de TDR");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimTdrPlantillaDTO == null) {
            mensaje = String.format("La plantilla de TDR con el id: %d no existe en la base de datos", idTdrPlantilla);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }
        
        try {
        	pgimConfiguracionBaseDTO = this.configuracionBaseService.obtenerCfgBasePorId(pgimTdrPlantillaDTO.getIdConfiguracionBase());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la configuración base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        respuesta.put("mensaje", "La plantilla de TDR ha sido recuperada");
        respuesta.put("pgimTdrPlantillaDTO", pgimTdrPlantillaDTO);
        respuesta.put("pgimConfiguracionBaseDTO", pgimConfiguracionBaseDTO);
        
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/crearTdrBase")
    public ResponseEntity<?> crearTdrBase(@Valid @RequestBody PgimTdrPlantillaDTO pgimTdrPlantillaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimTdrPlantillaDTO pgimTdrPlantillaDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear TDR base");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimTdrPlantillaDTOCreado = this.tdrPlantillaService.crearTdrBase(pgimTdrPlantillaDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear TDR base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "TDR base ha sido creada");
        respuesta.put("pgimTdrPlantillaDTOCreado", pgimTdrPlantillaDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarTdrBase")
    public ResponseEntity<?> modificarTdrBase(@Valid @RequestBody PgimTdrPlantillaDTO pgimTdrPlantillaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimTdrPlantilla pgimTdrPlantillaActual = null;
        PgimTdrPlantillaDTO pgimTdrPlantillaDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar El TDR base");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimTdrPlantillaActual = this.tdrPlantillaService.getByIdTdrBase(pgimTdrPlantillaDTO.getIdTdrPlantilla());

            if (pgimTdrPlantillaActual == null) {
                mensaje = String.format("El TDR base %s que intenta actualizar no existe en la base de datos",
                        pgimTdrPlantillaDTO.getIdTdrPlantilla());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar El TDR base a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimTdrPlantillaDTOModificada = this.tdrPlantillaService.modificarTdrBase(pgimTdrPlantillaDTO,
                    pgimTdrPlantillaActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar El TDR base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El TDR base ha sido modificada");
        respuesta.put("pgimTdrPlantillaDTOModificada", pgimTdrPlantillaDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = null;

        // para la fecha por defecto
        PgimTdrPlantillaDTO pgimTdrPlantillaDTO = new PgimTdrPlantillaDTO();
        Date fecha = new Date();
        pgimTdrPlantillaDTO.setFeCreacion(fecha);

        try {
            // lPgimConfiguracionBaseDTO = this.configuracionBaseService.listaCfgBasePorIdTipoCfgBaseTDR(459L);
            lPgimConfiguracionBaseDTO = this.configuracionBaseService.listaCfgBasePorIdTipoCfgBaseTDR(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.TDR_BASE.toString()));

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimConfiguracionBaseDTO", lPgimConfiguracionBaseDTO);
        respuesta.put("pgimTdrPlantillaDTO", pgimTdrPlantillaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarTdrBase/{idTdrPlantilla}")
    public ResponseEntity<?> eliminarTdrBase(@PathVariable Long idTdrPlantilla) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimTdrPlantilla pgimTdrPlantillaActual = null;

        try {
            pgimTdrPlantillaActual = this.tdrPlantillaService.getByIdTdrBase(idTdrPlantilla);

            if (pgimTdrPlantillaActual == null) {
                mensaje = String.format("La empresa supervisora %s que intenta eliminar no existe en la base de datos",
                idTdrPlantilla);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la empresa supervisora a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.tdrPlantillaService.eliminarTdrBase(pgimTdrPlantillaActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la empresa supervisora");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
