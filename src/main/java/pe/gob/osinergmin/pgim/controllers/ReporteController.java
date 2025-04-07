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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimReporteDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.exception.NotFoundException;
import pe.gob.osinergmin.pgim.models.entity.PgimReporte;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.ReporteService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los reportes de la PGIM.
 * 
 * @descripción: Reportes
 *
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 22/08/2021
 */

@RestController
@Slf4j
@RequestMapping("/reportes")
public class ReporteController extends BaseController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ParametroService parametroService;

    @PostMapping("/listarReporte")
    public ResponseEntity<Page<PgimReporteDTO>> listarReporte(@RequestBody PgimReporteDTO filtroReporteDTO,
            Pageable paginador) throws Exception {

        Page<PgimReporteDTO> lPgimReporteDTO = this.reporteService.listarReporte(filtroReporteDTO, paginador);
        return new ResponseEntity<Page<PgimReporteDTO>>(lPgimReporteDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTONoTipoMateria = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTONoTipoReporte = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTONoGrupo = null;

        try {
            lPgimValorParametroDTONoTipoMateria = this.parametroService
                    .filtrarPorTipoMateria(ConstantesUtil.PARAM_MATERIA);
            lPgimValorParametroDTONoTipoReporte = this.parametroService
                    .filtrarPorTipoReporte(ConstantesUtil.PARAM_REPORTE);
            lPgimValorParametroDTONoGrupo = this.parametroService
            		.filtrarPorNombreParametro(ConstantesUtil.PARAM_REPORTE_GRUPO);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del parametro");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParametroDTONoTipoMateria", lPgimValorParametroDTONoTipoMateria);
        respuesta.put("lPgimValorParametroDTONoTipoReporte", lPgimValorParametroDTONoTipoReporte);
        respuesta.put("lPgimValorParametroDTONoGrupo", lPgimValorParametroDTONoGrupo);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguracionesGenerales/{idReporte}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idReporte) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTONoTipoMateria = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTONoTipoReporte = null;

        try {
            lPgimValorParametroDTONoTipoMateria = this.parametroService
                    .filtrarPorTipoMateria(ConstantesUtil.PARAM_MATERIA);
            lPgimValorParametroDTONoTipoReporte = this.parametroService
                    .filtrarPorTipoReporte(ConstantesUtil.PARAM_REPORTE);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del parametro");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParametroDTONoTipoMateria", lPgimValorParametroDTONoTipoMateria);
        respuesta.put("lPgimValorParametroDTONoTipoReporte", lPgimValorParametroDTONoTipoReporte);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerReportePorId/{idReporte}")
    public ResponseEntity<?> obtenerReportePorId(@PathVariable Long idReporte) {
        PgimReporteDTO pgimReporteDTO = reporteService.obtenerReportePorId(idReporte);
        if (pgimReporteDTO.getIdReporte() == null) {
            throw new NotFoundException("ID NO ENCONTRADO: " + idReporte);
        }
        return new ResponseEntity<PgimReporteDTO>(pgimReporteDTO, HttpStatus.OK);
    }

    @PutMapping("/modificarReporte")
    public ResponseEntity<?> modificarReporte(@Valid @RequestBody PgimReporteDTO pgimReporteDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimReporte pgimReporteActual = null;
        PgimReporteDTO pgimReporteDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar El reporte");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimReporteActual = this.reporteService.getByIdReporte(pgimReporteDTO.getIdReporte());

            if (pgimReporteActual == null) {
                mensaje = String.format("El reporte %s que intenta actualizar no existe en la base de datos",
                        pgimReporteDTO.getIdReporte());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar El reporte a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimReporteDTOModificada = this.reporteService.modificarReporte(pgimReporteDTO, pgimReporteActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar El reporte");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El reporte ha sido modificada");
        respuesta.put("pgimReporteDTOModificada", pgimReporteDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/listarReporteGeneral/{parametro}")
    public ResponseEntity<?> listarReporteGeneral(@PathVariable Long parametro) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimReporteDTO> lPgimReporteDTO = null;

        try {
            lPgimReporteDTO = this.reporteService.listarReporteGeneral(parametro);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de reportes");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los reportes");
        respuesta.put("lPgimReporteDTO", lPgimReporteDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/listarReporteGeneralByCoClaveTexto/{parametro}")
    public ResponseEntity<?> listarReporteGeneralByCoClaveTexto(@PathVariable String parametro) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimReporteDTO> lPgimReporteDTO = null;

        try {
            lPgimReporteDTO = this.reporteService.listarReporteGeneralByCoClaveTexto(parametro);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de reportes");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los reportes");
        respuesta.put("lPgimReporteDTO", lPgimReporteDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
