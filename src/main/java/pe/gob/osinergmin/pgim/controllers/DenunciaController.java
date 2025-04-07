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
import pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.exception.NotFoundException;
import pe.gob.osinergmin.pgim.models.entity.PgimDenuncia;
import pe.gob.osinergmin.pgim.services.DenunciaService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a las
 * Denuncias de las unidades mineras
 * 
 * @descripción: Denuncia
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 08/09/2021
 */
@RestController
@Slf4j
@RequestMapping("/denuncias")
public class DenunciaController extends BaseController {

    @Autowired
    private DenunciaService denunciaService;

    @Autowired
    private ParametroService parametroService;

    // @PreAuthorize("hasAnyAuthority('co-lista_AC')")
    @PostMapping("/listarDenuncia")
    public ResponseEntity<Page<PgimDenunciaDTO>> listarDenuncia(@RequestBody Long idDenuncia, Pageable paginador)
            throws Exception {

        Page<PgimDenunciaDTO> lPgimDenunciaDTO = this.denunciaService.listarDenuncia(idDenuncia, paginador);
        return new ResponseEntity<Page<PgimDenunciaDTO>>(lPgimDenunciaDTO, HttpStatus.OK);
    }

    @PostMapping("/listarDenunciaGeneral")
    public ResponseEntity<Page<PgimDenunciaDTO>> listarDenunciaGeneral(@RequestBody PgimDenunciaDTO filtroDenunciaDTO,
            Pageable paginador) throws Exception {

        Page<PgimDenunciaDTO> lPgimDenunciaDTO = this.denunciaService.listarDenunciaGeneral(filtroDenunciaDTO,
                paginador);
        return new ResponseEntity<Page<PgimDenunciaDTO>>(lPgimDenunciaDTO, HttpStatus.OK);
    }

    @PostMapping("/crearDenuncia")
    public ResponseEntity<?> crearDenuncia(@Valid @RequestBody PgimDenunciaDTO pgimDenunciaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimDenunciaDTO pgimDenunciaDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear una denuncia");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimDenunciaDTOCreado = this.denunciaService.crearDenuncia(pgimDenunciaDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear una denuncia");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La denuncia ha sido creada");
        respuesta.put("pgimDenunciaDTOCreado", pgimDenunciaDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguracionesGenerales/{idDenuncia}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idDenuncia) {
        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOMedioDenuncia = null;
        PgimDenunciaDTO maxCoDenuncia = null;
        PgimDenunciaDTO maxCoDenunciaPorId = null;
        try {

            lPgimValorParametroDTOMedioDenuncia = this.parametroService
                    .filtrarPorMedioDenuncia(ConstantesUtil.PARAM_MEDIO_DENUNCIA);

            maxCoDenuncia = this.denunciaService.obtenerMaxCodigoDenuncia();

            maxCoDenunciaPorId = this.denunciaService.obtenerMaxCodigoDenunciaPorId(idDenuncia);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar La denuncia");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La denuncia ha sido recuperado");
        respuesta.put("lPgimValorParametroDTOMedioDenuncia", lPgimValorParametroDTOMedioDenuncia);
        respuesta.put("maxCoDenuncia", maxCoDenuncia);
        respuesta.put("maxCoDenunciaPorId", maxCoDenunciaPorId);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOMedioDenuncia = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoDocumento = null;

        try {
            lPgimValorParametroDTOMedioDenuncia = this.parametroService
                    .filtrarPorMedioDenuncia(ConstantesUtil.PARAM_MEDIO_DENUNCIA);
            lPgimValorParametroDTOTipoDocumento = this.parametroService
                    .filtrarPorTipoDocIdentidadDenuncia(ConstantesUtil.PARAM_TIPO_DOCUMENTO_IDENTIDAD);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del parametro");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParametroDTOMedioDenuncia", lPgimValorParametroDTOMedioDenuncia);
        respuesta.put("lPgimValorParametroDTOTipoDocumento", lPgimValorParametroDTOTipoDocumento);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerDenunciaPorId/{idDenuncia}")
    public ResponseEntity<?> obtenerDenunciaPorId(@PathVariable Long idDenuncia) {
        PgimDenunciaDTO pgimDenunciaDTO = denunciaService.obtenerDenunciaPorId(idDenuncia);
        if (pgimDenunciaDTO.getIdDenuncia() == null) {
            throw new NotFoundException("ID NO ENCONTRADO: " + idDenuncia);
        }
        return new ResponseEntity<PgimDenunciaDTO>(pgimDenunciaDTO, HttpStatus.OK);
    }

    // @PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
    @PutMapping("/modificarDenuncia")
    public ResponseEntity<?> modificarDenuncia(@Valid @RequestBody PgimDenunciaDTO pgimDenunciaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimDenuncia pgimDenunciaActual = null;
        PgimDenunciaDTO legFamiliarDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar La denuncia");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimDenunciaActual = this.denunciaService.getByIdDenuncia(pgimDenunciaDTO.getIdDenuncia());

            if (pgimDenunciaActual == null) {
                mensaje = String.format("La denuncia %s que intenta actualizar no existe en la base de datos",
                        pgimDenunciaDTO.getIdDenuncia());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar La denuncia a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            legFamiliarDTOModificada = this.denunciaService.modificarDenuncia(pgimDenunciaDTO, pgimDenunciaActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar La denuncia");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La denuncia ha sido modificada");
        respuesta.put("legFamiliarDTOModificada", legFamiliarDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    // @PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
    @DeleteMapping("/eliminarDenuncia/{idDenuncia}")
    public ResponseEntity<?> eliminarDenuncia(@PathVariable Long idDenuncia) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimDenuncia pgimDenunciaActual = null;

        try {
            pgimDenunciaActual = this.denunciaService.getByIdDenuncia(idDenuncia);

            if (pgimDenunciaActual == null) {
                mensaje = String.format("La denuncia %s que intenta eliminar no existe en la base de datos",
                        idDenuncia);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar La denuncia a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.denunciaService.eliminarDenuncia(pgimDenunciaActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar una denuncia");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite filtrar las personas de un contrato por DNI, Nombres y Apellidos
     * completos
     * 
     * @param idContrato
     * @param palabra
     * @return
     */
    @GetMapping("/listarPorPersona/palabraClave/{idUnidadMinera}/{palabra}")
    public ResponseEntity<?> listarPorPersona(@PathVariable Long idUnidadMinera, @PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimDenunciaDTO> lPgimPersonaDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaDTO = new ArrayList<PgimDenunciaDTO>();
            respuesta.put("mensaje", "Se encontraron las personas naturales");
            respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaDTO = this.denunciaService.listarPorPersona(idUnidadMinera, palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la persona natural");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las personas naturales");
        respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/listarPorPersonaGeneral/palabraClave/{palabra}")
    public ResponseEntity<?> listarPorPersonaGeneral(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimDenunciaDTO> lPgimPersonaDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaDTO = new ArrayList<PgimDenunciaDTO>();
            respuesta.put("mensaje", "Se encontraron las personas naturales");
            respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaDTO = this.denunciaService.listarPorPersonaGeneral(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la persona natural");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las personas naturales");
        respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
}
