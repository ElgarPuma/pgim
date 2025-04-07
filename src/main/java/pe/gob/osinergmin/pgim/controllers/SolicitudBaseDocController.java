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
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudBaseDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemSolBaseDocDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemSolBaseDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSolicitudBaseDoc;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.ConfiguracionBaseService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.SolicitudBaseDocService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a la solicitud base de documentos.
 * 
 * @descripción: Solicitud base documento
 *
 * @author: pablo
 * @version: 1.0
 * @fecha_de_creación: 22/10/2020
 */
@RestController
@Slf4j
@RequestMapping("/solicitudbasedoc")
public class SolicitudBaseDocController extends BaseController {

    @Autowired
    private SolicitudBaseDocService solicitudBaseDocService;
    
    @Autowired
    private ConfiguracionBaseService configuracionBaseService;
    
    @Autowired
	private ValorParametroRepository valorParametroRepository;

    @Autowired
    private ParametroService parametroService;
    
    @PreAuthorize("hasAnyAuthority('cfg004_AC')")
    @PostMapping("/listar")
    public ResponseEntity<Page<PgimSolicitudBaseDocDTO>> listar(
            @RequestBody PgimSolicitudBaseDocDTO filtro, Pageable paginador) throws Exception {

        Page<PgimSolicitudBaseDocDTO> lPgimSolicitudBaseDocDTO = solicitudBaseDocService.listarSolicitudBaseDocPage(filtro, paginador);

        return new ResponseEntity<Page<PgimSolicitudBaseDocDTO>>(lPgimSolicitudBaseDocDTO, HttpStatus.OK);
    }
    
    @GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = null;
        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;

        try {
            lPgimConfiguracionBaseDTO = this.configuracionBaseService.listaCfgBasePorIdTipoCfgBaseSOLBD(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.DOCUMENTOS_BASE_SOLICITUD.toString()));
            lPgimEspecialidadDTO = this.parametroService.filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al obtener las configuraciones base de solicitud base de documentos");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones base de solicitud base de documentos");
        respuesta.put("lPgimConfiguracionBaseDTO", lPgimConfiguracionBaseDTO);
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/obtenerSolicitudBaseDocPorId/{idSolicitudBaseDoc}")
    public ResponseEntity<?> obtenerSolicitudBaseDocPorId(@PathVariable Long idSolicitudBaseDoc) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO = null;
        PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO = null;
        try {
        	pgimSolicitudBaseDocDTO = this.solicitudBaseDocService.obtenerSolicitudBaseDocPorId(idSolicitudBaseDoc);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la plantilla de solicitud de documento base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimSolicitudBaseDocDTO == null) {
            mensaje = String.format("La plantilla de solicitud de documento base con el id: %d no existe en la base de datos", idSolicitudBaseDoc);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }
        
        try {
        	pgimConfiguracionBaseDTO = this.configuracionBaseService.obtenerCfgBasePorId(pgimSolicitudBaseDocDTO.getIdConfiguracionBase());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la configuración de base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        respuesta.put("mensaje", "La plantilla de solicitud de documento base ha sido recuperada");
        respuesta.put("pgimSolicitudBaseDocDTO", pgimSolicitudBaseDocDTO);
        respuesta.put("pgimConfiguracionBaseDTO", pgimConfiguracionBaseDTO);
        
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
	@GetMapping("/listarDetalleSolicitudDoc/{idSolicitudBaseDoc}")
	public ResponseEntity<List<PgimItemSolBaseDocDTO>> listarDetalleSolicitudDoc(
			@PathVariable Long idSolicitudBaseDoc) throws Exception {

		final List<PgimItemSolBaseDocDTO> lPgimItemSolBaseDocDTO = this.solicitudBaseDocService.listarItemsSolicitudBaseDocPorIdSolicitudBaseDoc(idSolicitudBaseDoc);
		
		return new ResponseEntity<List<PgimItemSolBaseDocDTO>>(lPgimItemSolBaseDocDTO, HttpStatus.OK);
	}

    
    /**
     * Permite crear la solicitud de documento
     * 
     * @param PgimSolicitudBaseDocDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PostMapping("/crearPlantilla")
    public ResponseEntity<?> crearPlantilla(
            @Valid @RequestBody PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear la solicitud de documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	pgimSolicitudBaseDocDTOCreado = solicitudBaseDocService.crearPlantilla(pgimSolicitudBaseDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la solicitud de documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La solicitud de documento ha sido creada");
        respuesta.put("pgimSolicitudBaseDocDTO", pgimSolicitudBaseDocDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    /**
     * Permite modificar la solicitud de documento, valida la existencia de la solicitud de documento
     * 
     * @param pgimSolicitudBaseDocDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarPlantilla")
    public ResponseEntity<?> modificarPlantilla(
            @Valid @RequestBody PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimSolicitudBaseDoc pgimSolicitudBaseDocActual = null;
        PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la solicitud de documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimSolicitudBaseDocActual = this.solicitudBaseDocService.getByIdSolicitudBaseDoc(pgimSolicitudBaseDocDTO.getIdSolicitudBaseDoc());

            if (pgimSolicitudBaseDocActual == null) {
                mensaje = String.format("La solicitud de documento %s que intenta actualizar no existe en la base de datos",
                		pgimSolicitudBaseDocDTO.getIdSolicitudBaseDoc());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la solicitud de documento a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        
        try {
        	pgimSolicitudBaseDocDTOModificado = this.solicitudBaseDocService.modificarPlantilla(pgimSolicitudBaseDocDTO, pgimSolicitudBaseDocActual, this.obtenerAuditoria());
        	
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la solicitud de documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La solicitud de documento ha sido modificada");
        respuesta.put("pgimSolicitudBaseDocDTO", pgimSolicitudBaseDocDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    /**
     * Permite eliminar la solicitud de documento
     * 
     * @param idSolicitudBaseDoc
     * @return
     * @throws Exception
     */
    @DeleteMapping("/eliminarPlantilla/{idSolicitudBaseDoc}")
    public ResponseEntity<?> eliminarPlantilla(@PathVariable Long idSolicitudBaseDoc) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimSolicitudBaseDoc pgimSolicitudBaseDocActual = null;

        try {
        	pgimSolicitudBaseDocActual = this.solicitudBaseDocService.getByIdSolicitudBaseDoc(idSolicitudBaseDoc);

            if (pgimSolicitudBaseDocActual == null) {
                mensaje = String.format("La solicitud de documento %s que intenta eliminar no existe en la base de datos",
                		idSolicitudBaseDoc);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la solicitud de documento a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.solicitudBaseDocService.eliminarPlantilla(pgimSolicitudBaseDocActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la solicitud de documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Permite validar que no haya traslape de plantilla.
     * 
     * @param pgimSolicitudBaseDocDTO
     * @return
     */
    @PostMapping("/validarTraslapePlantilla")
    public ResponseEntity<ResponseDTO> validarTraslapePlantilla(@RequestBody PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO) {
        String mensaje = "";
        int nroTraslapes = 0;

        try {
            nroTraslapes = this.solicitudBaseDocService.validarTraslapePlantilla(pgimSolicitudBaseDocDTO.getIdSolicitudBaseDoc(),
                     pgimSolicitudBaseDocDTO.getNoSolicitudBaseDoc());

            if (nroTraslapes >= 1) {
                mensaje = "Ya existe una plantilla con el mismo nombre, por favor elija un nombre distinto";
            } else {
                mensaje = "No se han encontrado traslapes";
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al realizar la validacion de traslapes de plantillas";
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("error", mensaje, nroTraslapes));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", mensaje, nroTraslapes));
    }
    
    /**
     * Permite crear un item de solicitud de documento
     * 
     * @param PgimItemSolBaseDocDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PostMapping("/registrarItemSolicitud")
    public ResponseEntity<?> registrarItemSolicitud(
            @Valid @RequestBody PgimItemSolBaseDocDTO pgimItemSolBaseDocDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimItemSolBaseDocDTO pgimItemSolBaseDocDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el item de la solicitud de documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	pgimItemSolBaseDocDTOCreado = solicitudBaseDocService.registrarItemSolicitud(pgimItemSolBaseDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el item de la solicitud de documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El item de la solicitud de documento ha sido creada");
        respuesta.put("pgimItemSolBaseDocDTO", pgimItemSolBaseDocDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    /**
     * Permite modificar la solicitud de documento, valida la existencia de la solicitud de documento
     * 
     * @param pgimItemSolBaseDocDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarItemSolicitud")
    public ResponseEntity<?> modificarItemSolicitud(
            @Valid @RequestBody PgimItemSolBaseDocDTO pgimItemSolBaseDocDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimItemSolBaseDoc pgimItemSolBaseDocActual = null;
        PgimItemSolBaseDocDTO pgimItemSolBaseDocDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el item de la solicitud de documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimItemSolBaseDocActual = this.solicitudBaseDocService.getByIdItemSolBaseDoc(pgimItemSolBaseDocDTO.getIdItemSolBaseDoc());

            if (pgimItemSolBaseDocActual == null) {
                mensaje = String.format("El item de la solicitud de documento %s que intenta actualizar no existe en la base de datos",
                		pgimItemSolBaseDocDTO.getIdItemSolBaseDoc());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el item de la solicitud de documento a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        
        try {
        	pgimItemSolBaseDocDTOModificado = this.solicitudBaseDocService.modificarItemSolicitud(pgimItemSolBaseDocDTO, pgimItemSolBaseDocActual, this.obtenerAuditoria());
        	
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la solicitud de documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El item de la solicitud de documento ha sido modificada");
        respuesta.put("pgimItemSolBaseDocDTO", pgimItemSolBaseDocDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    
    /**
     * Permite eliminar un item de solicitud de documento
     * 
     * @param idItemSolBaseDoc
     * @return
     * @throws Exception
     */
    @DeleteMapping("/eliminarItemSolicitud/{idItemSolBaseDoc}")
    public ResponseEntity<?> eliminarItemSolicitud(@PathVariable Long idItemSolBaseDoc) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimItemSolBaseDoc pgimItemSolBaseDocActual = null;

        try {
        	pgimItemSolBaseDocActual = this.solicitudBaseDocService.getByIdItemSolBaseDoc(idItemSolBaseDoc);

            if (pgimItemSolBaseDocActual == null) {
                mensaje = String.format("El item de la solicitud de documento %s que intenta eliminar no existe en la base de datos",
                		idItemSolBaseDoc);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el item de la solicitud de documento a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.solicitudBaseDocService.eliminarItemSolicitud(pgimItemSolBaseDocActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar eliminar el item de la solicitud de documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
