package pe.gob.osinergmin.pgim.controllers;

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
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMedidaAdm;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.MedidaAdmService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a las
 * Medidas administrativas.
 * 
 * @descripción: Medida administrativa
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 08/09/2021
 */


@RestController
@Slf4j
@RequestMapping("/medidasadministrativas")
public class MedidaAdmController extends BaseController {

    @Autowired
    private MedidaAdmService medidaAdmService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;

    // @PreAuthorize("hasAnyAuthority('co-lista_AC')")
    @PostMapping("/listarMedidaAdministrativa")
    public ResponseEntity<Page<PgimMedidaAdmDTO>> listarMedidaAdministrativa(
            @RequestBody PgimMedidaAdmDTO filtroMedidaAdmDTO, Pageable paginador) throws Exception {

        Page<PgimMedidaAdmDTO> lPgimMedidaAdmDTO = this.medidaAdmService.listarMedidaAdministrativa(filtroMedidaAdmDTO,
                paginador, this.obtenerAuditoria());
        return new ResponseEntity<Page<PgimMedidaAdmDTO>>(lPgimMedidaAdmDTO, HttpStatus.OK);
    }

    @PostMapping("/listarMedidaAdministrativaSupervPas/{idTipoObjeto}")
    public ResponseEntity<Page<PgimMedidaAdmDTO>> listarMedidaAdministrativaSupervPas(@PathVariable Long idTipoObjeto,
            Pageable paginador) throws Exception {

        Page<PgimMedidaAdmDTO> lPgimMedidaAdmDTO = this.medidaAdmService
                .listarMedidaAdministrativaSupervPas(idTipoObjeto, paginador);
        return new ResponseEntity<Page<PgimMedidaAdmDTO>>(lPgimMedidaAdmDTO, HttpStatus.OK);
    }

    @PostMapping("/listarMedidaAdministrativaSupervision/{idSupervision}")
    public ResponseEntity<Page<PgimMedidaAdmDTO>> listarMedidaAdministrativaSupervision(
            @PathVariable Long idSupervision, Pageable paginador) throws Exception {

        Page<PgimMedidaAdmDTO> lPgimMedidaAdmDTO = this.medidaAdmService
                .listarMedidaAdministrativaSupervision(idSupervision, paginador);
                
        return new ResponseEntity<Page<PgimMedidaAdmDTO>>(lPgimMedidaAdmDTO, HttpStatus.OK);
    }

    @PostMapping("/listarMedidaAdministrativaPas/{idPas}")
    public ResponseEntity<Page<PgimMedidaAdmDTO>> listarMedidaAdministrativaPas(@PathVariable Long idPas,
            Pageable paginador) throws Exception {

        Page<PgimMedidaAdmDTO> lPgimMedidaAdmDTO = this.medidaAdmService.listarMedidaAdministrativaPas(idPas,
                paginador);
        return new ResponseEntity<Page<PgimMedidaAdmDTO>>(lPgimMedidaAdmDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('um012_AC')")
    @PostMapping("/listarMedidaAdministrativaUm/{idUnidadMinera}")
    public ResponseEntity<Page<PgimMedidaAdmDTO>> listarMedidaAdministrativaUm(@PathVariable Long idUnidadMinera,
            Pageable paginador) throws Exception {

        Page<PgimMedidaAdmDTO> lPgimMedidaAdmDTO = this.medidaAdmService.listarMedidaAdministrativaUm(idUnidadMinera,
                paginador);
        return new ResponseEntity<Page<PgimMedidaAdmDTO>>(lPgimMedidaAdmDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoMedidaAdm = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoObjeto = null;

        try {
            lPgimValorParametroDTOTipoMedidaAdm = this.parametroService
                    .filtrarPorNombreTipoMedidaAdm(ConstantesUtil.PARAM_TIPO_MEDIDA_ADMINISTRATIVA);
            lPgimValorParametroDTOTipoObjeto = this.parametroService
                    .filtrarPorNombreTipoObjeto(ConstantesUtil.PARAM_TIPO_OBJ_RELACIONADO_MED_ADM);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del parametro");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParametroDTOTipoMedidaAdm", lPgimValorParametroDTOTipoMedidaAdm);
        respuesta.put("lPgimValorParametroDTOTipoObjeto", lPgimValorParametroDTOTipoObjeto);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrar/listarPorCoMedidaAdministrativa/{palabra}")
    public ResponseEntity<?> listarPorCoMedidaAdministrativa(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimMedidaAdmDTO> lPgimMedidaAdmDTOCodigo = null;

        if (palabra.equals("_vacio_")) {
            lPgimMedidaAdmDTOCodigo = new ArrayList<PgimMedidaAdmDTO>();
            respuesta.put("mensaje", "No se encontraron código de medida administrativa");
            respuesta.put("lPgimMedidaAdmDTOCodigo", lPgimMedidaAdmDTOCodigo);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimMedidaAdmDTOCodigo = this.medidaAdmService.listarPorCoMedidaAdministrativa(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de código de medida administrativa");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los código de medida administrativa");
        respuesta.put("lPgimMedidaAdmDTOCodigo", lPgimMedidaAdmDTOCodigo);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrar/listarPorNumeroExpediente/{nuExpediente}")
    public ResponseEntity<?> listarPorNumeroExpediente(@PathVariable String nuExpediente) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimMedidaAdmDTO> lPgimMedidaAdmDTONuExpediente = null;

        if (nuExpediente.equals("_vacio_")) {
            lPgimMedidaAdmDTONuExpediente = new ArrayList<PgimMedidaAdmDTO>();
            respuesta.put("mensaje", "No se encontraron código de medida administrativa");
            respuesta.put("lPgimMedidaAdmDTONuExpediente", lPgimMedidaAdmDTONuExpediente);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimMedidaAdmDTONuExpediente = this.medidaAdmService.listarPorNumeroExpediente(nuExpediente);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de código de medida administrativa");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los código de medida administrativa");
        respuesta.put("lPgimMedidaAdmDTONuExpediente", lPgimMedidaAdmDTONuExpediente);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ma001_IN')")
    @PostMapping("/crearMedidaAdministrativa")
    public ResponseEntity<?> crearMedidaAdministrativa(@Valid @RequestBody PgimMedidaAdmDTO pgimMedidaAdmDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimMedidaAdmDTO pgimMedidaAdmDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear una nueva medida administrativa");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimMedidaAdmDTOCreado = this.medidaAdmService.crearMedidaAdministrativa(pgimMedidaAdmDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear una medida administrativa");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La medida administrativa ha sido creada");
        respuesta.put("pgimMedidaAdmDTOCreado", pgimMedidaAdmDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguraciones/generales/{idMedidaAdministrativa}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idMedidaAdministrativa) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoMedidaAdm = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoObjeto = null;

        try {

            lPgimValorParametroDTOTipoMedidaAdm = this.parametroService
                    .filtrarPorNombreTipoMedidaAdm(ConstantesUtil.PARAM_TIPO_MEDIDA_ADMINISTRATIVA);
            lPgimValorParametroDTOTipoObjeto = this.parametroService
                    .filtrarPorNombreTipoObjeto(ConstantesUtil.PARAM_TIPO_OBJ_RELACIONADO_MED_ADM);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

        respuesta.put("lPgimValorParametroDTOTipoMedidaAdm", lPgimValorParametroDTOTipoMedidaAdm);
        respuesta.put("lPgimValorParametroDTOTipoObjeto", lPgimValorParametroDTOTipoObjeto);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ma001_MO')")
    @PutMapping("/modificarMedidaAdministrativa")
    public ResponseEntity<?> modificarMedidaAdministrativa(@Valid @RequestBody PgimMedidaAdmDTO pgimMedidaAdmDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimMedidaAdm pgimMedidaAdmActual = null;
        PgimMedidaAdmDTO pgimMedidaAdmDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la medida administrativa");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimMedidaAdmActual = this.medidaAdmService
                    .getByIdMedidaAdministrativa(pgimMedidaAdmDTO.getIdMedidaAdministrativa());

            if (pgimMedidaAdmActual == null) {
                mensaje = String.format(
                        "La medida administrativa %s que intenta actualizar no existe en la base de datos",
                        pgimMedidaAdmDTO.getIdMedidaAdministrativa());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la medida administrativa a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimMedidaAdmDTOModificada = this.medidaAdmService.modificarMedidaAdministrativa(pgimMedidaAdmDTO,
                    pgimMedidaAdmActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la medida administrativa");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La medida administrativa ha sido modificada");
        respuesta.put("pgimMedidaAdmDTOModificada", pgimMedidaAdmDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    // @PreAuthorize("hasAnyAuthority('co-lista_CO')")
    @GetMapping("/obtenerMedidaAdministrativaPorIdInstanciaPaso/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerMedidaAdministrativaPorIdInstanciaPaso(@PathVariable Long idInstanciaPaso)
            throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimMedidaAdmDTO pgimMedidaAdmDTO = null;
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOActual = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;

        Long idMedidaAdministrativa = null;

        try {
            idMedidaAdministrativa = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null)
                    .getPgimInstanciaProces().getCoTablaInstancia();

            pgimMedidaAdmDTO = this.medidaAdmService.obtenerMedidaAdministrativaPorId(idMedidaAdministrativa);

            if (pgimMedidaAdmDTO == null) {
                mensaje = String.format("La medida administrativa con el id: %d no existe en la base de datos",
                        idMedidaAdministrativa);
                respuesta.put("mensaje", mensaje);
    
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
            }    

            PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this.flujoTrabajoService
                    .obtenerInstanciaPasoActualPorIdInstanciaPaso(idInstanciaPaso);
            
            pgimMedidaAdmDTO.setDescIdInstanciaPaso(pgimInstanciaPasoDTOActual.getIdInstanciaPaso());

            pgimInstanPasoAuxDTOActual = this.flujoTrabajoService
                    .obtenerInstanciaPasoAuxPorId(pgimInstanciaPasoDTOActual.getIdInstanciaPaso());

            lPgimFaseProcesoDTO = this.flujoTrabajoService
                    .obtenerFasesInstanciaProceso(pgimInstanciaPasoDTOActual.getIdInstanciaProceso());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la medida administrativa");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La medida administrativa ha sido recuperado");
        respuesta.put("pgimMedidaAdmDTO", pgimMedidaAdmDTO);
        respuesta.put("pgimInstanPasoAuxDTOActual", pgimInstanPasoAuxDTOActual);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerMedidaAdministrativaPorIdPas/{idPas}")
    public ResponseEntity<?> obtenerMedidaAdministrativaPorIdPas(@PathVariable Long idPas) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimMedidaAdmDTO pgimMedidaAdmDTO = null;

        try {
            pgimMedidaAdmDTO = this.medidaAdmService.obtenerMedidaAdministrativaPorIdPas(idPas);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la medida administrativa");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimMedidaAdmDTO == null) {
            mensaje = String.format("La medida administrativa con el id: %d no existe en la base de datos", idPas);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La medida administrativa ha sido recuperado");
        respuesta.put("pgimMedidaAdmDTO", pgimMedidaAdmDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ma001_EL')")
    @DeleteMapping("/eliminarMedidaAdministrativa/{idMedidaAdministrativa}")
    public ResponseEntity<?> eliminarMedidaAdministrativa(@PathVariable Long idMedidaAdministrativa) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimMedidaAdm pgimMedidaAdmActual = null;

        try {
            pgimMedidaAdmActual = this.medidaAdmService.getByIdMedidaAdministrativa(idMedidaAdministrativa);

            if (pgimMedidaAdmActual == null) {
                mensaje = String.format("La unidad fiscalizada %s que intenta eliminar no existe en la base de datos",
                        idMedidaAdministrativa);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la unidad fiscalizada a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.medidaAdmService.eliminarMedidaAdministrativa(pgimMedidaAdmActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la unidad fiscalizada");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
