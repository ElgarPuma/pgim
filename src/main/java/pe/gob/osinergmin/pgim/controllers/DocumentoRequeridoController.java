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
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimItemRecepcionDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemSolicitudDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemRecepcionDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimItemSolicitudDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSolicitudDoc;
import pe.gob.osinergmin.pgim.services.DocumentoRequeridoService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los documentos requeridos
 * 
 * @descripción: Documento requerido
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 22/10/2020
 */


@RestController
@Slf4j
@RequestMapping("/documentosrequeridos")
public class DocumentoRequeridoController extends BaseController{
	
	@Autowired
	private DocumentoRequeridoService documentoRequeridoService;
	
	@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_IN')")
	@PostMapping("/crearDocumentoRequerido")
    public ResponseEntity<?> crearDocumentoRequerido(
            @Valid @RequestBody PgimSolicitudDocDTO pgimSolicitudDocDTO, BindingResult resultadoValidacion)
            throws Exception {

		PgimSolicitudDocDTO pgimSolicitudDocDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el documento requerido");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimSolicitudDocDTOCreado = documentoRequeridoService.crearDocumentoRequerido(pgimSolicitudDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el documento requerido");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El documento requerido ha sido creado");
        respuesta.put("pgimSolicitudDocDTO", pgimSolicitudDocDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
	
	@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_CO')")
	@GetMapping("/obtenerDocumentoRequerido/{idSolicitudDoc}")
    public ResponseEntity<?> obtenerDocumentoRequerido(@PathVariable Long idSolicitudDoc) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimSolicitudDocDTO pgimSolicitudDocDTO = null;

        try {
        	pgimSolicitudDocDTO = this.documentoRequeridoService.obtenerDocumentoRequerido(idSolicitudDoc);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el documento requerido");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimSolicitudDocDTO == null) {
            mensaje = String.format("El documento requerido con el id: %d no existe en la base de datos", idSolicitudDoc);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El documento requerido ha sido recuperado");
        respuesta.put("pgimSolicitudDocDTO", pgimSolicitudDocDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
	@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_MO')")
	@PutMapping("/modificarDocumentoRequerido")
    public ResponseEntity<?> modificarDocumentoRequerido(
            @Valid @RequestBody PgimSolicitudDocDTO pgimSolicitudDocDTO, BindingResult resultadoValidacion)
            throws Exception {

        PgimSolicitudDoc pgimSolicitudDocActual = null;
        PgimSolicitudDocDTO pgimSolicitudDocDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el documento requerido");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	pgimSolicitudDocActual = documentoRequeridoService.findById(pgimSolicitudDocDTO.getIdSolicitudDoc());

            if (pgimSolicitudDocActual == null) {
                mensaje = String.format("El documento requerido %s que intenta actualizar no existe en la base de datos",
                		pgimSolicitudDocDTO.getIdSolicitudDoc());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el documento requerido a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        try {
        	pgimSolicitudDocDTOModificado = documentoRequeridoService.modificarDocumentoRequerido(pgimSolicitudDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el documento requerido");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El documento requerido ha sido modificado");
        respuesta.put("pgimSolicitudDocDTO", pgimSolicitudDocDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
	
	@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_EL')")
	@DeleteMapping("/eliminarDocumentoRequerido/{idSolicitudDoc}")
    public ResponseEntity<?> eliminarDocumentoRequerido(@PathVariable Long idSolicitudDoc) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimSolicitudDoc pgimSolicitudDocActual = null;

        try {
        	pgimSolicitudDocActual = documentoRequeridoService.findById(idSolicitudDoc);

            if (pgimSolicitudDocActual == null) {
                mensaje = String.format("El documento requerido %s que intenta eliminar no existe en la base de datos",
                		idSolicitudDoc);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el documento requerido a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
        	documentoRequeridoService.eliminarDocumentoRequerido(pgimSolicitudDocActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar el documento requerido");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

	@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_AC')")
    @GetMapping("/listarDocumentoRequerido/{idSupervision}")
    public ResponseEntity<?> listarDocumentoRequerido(@PathVariable Long idSupervision) {
	
        Map<String, Object> respuesta = new HashMap<String, Object>();

        List<PgimSolicitudDocDTO> lPgimSolicitudDocDTO = null;

        try {
        	lPgimSolicitudDocDTO = documentoRequeridoService.listarDocumentoRequerido(idSupervision);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la lista de Dosc requeridos");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La lista de Docs. requeridos han sido recuperado");
        respuesta.put("lPgimSolicitudDocDTO", lPgimSolicitudDocDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }
    
    //@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_IN')")
    @PostMapping("/crearItemRecepcionDoc")
    public ResponseEntity<?> crearItemRecepcionDoc(
            @Valid @RequestBody PgimItemRecepcionDocDTO pgimItemRecepcionDocDTO, BindingResult resultadoValidacion)
            throws Exception {

		PgimItemRecepcionDocDTO pgimItemRecepcionDocDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el item de recepción del documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimItemRecepcionDocDTOCreado = documentoRequeridoService.crearItemRecepcionDoc(pgimItemRecepcionDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el documento requerido");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El documento requerido ha sido creado");
        respuesta.put("pgimItemRecepcionDocDTO", pgimItemRecepcionDocDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    //@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_IN')")
    @PostMapping("/crearItemSolicitudDoc")
    public ResponseEntity<?> crearItemSolicitudDoc(
            @Valid @RequestBody PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO, BindingResult resultadoValidacion)
            throws Exception {

		PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el item de solicitud del documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimItemSolicitudDocDTOCreado = documentoRequeridoService.crearItemSolicitudDoc(pgimItemSolicitudDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el item de solicitud del documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El item de solicitud del documento requerido ha sido creado");
        respuesta.put("pgimItemSolicitudDocDTO", pgimItemSolicitudDocDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    @PutMapping("/modificarItemSolicitudDoc")
    public ResponseEntity<?> modificarItemSolicitudDoc(
            @Valid @RequestBody PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO, BindingResult resultadoValidacion)
            throws Exception {

		PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOModificado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el item de solicitud del documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimItemSolicitudDocDTOModificado = documentoRequeridoService.modificarItemSolicitudDoc(pgimItemSolicitudDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el item de solicitud del documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El item de solicitud del documento ha sido creado");
        respuesta.put("pgimItemSolicitudDocDTO", pgimItemSolicitudDocDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarFechasRequerimiento")
    public ResponseEntity<?> modificarFechasRequerimiento(
            @Valid @RequestBody PgimSolicitudDocDTO pgimSolicitudDocDTO, BindingResult resultadoValidacion)
            throws Exception {

		List<PgimItemSolicitudDocDTO> lPgimSolicitudDocDTOModificado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para actualizar las fechas de requerimiento.");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	lPgimSolicitudDocDTOModificado = documentoRequeridoService.modificarFechasRequerimiento(pgimSolicitudDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar actualizar las fechas de requerimiento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Las fechas de requerimiento fueron actualizadas");
        respuesta.put("lPgimItemSolicitudDocDTO", lPgimSolicitudDocDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarFechasRecepcion")
    public ResponseEntity<?> modificarFechasRecepcion(
            @Valid @RequestBody PgimSolicitudDocDTO pgimSolicitudDocDTO, BindingResult resultadoValidacion)
            throws Exception {

		List<PgimItemSolicitudDocDTO> lPgimSolicitudDocDTOModificado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para actualizar las fechas de recepción.");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	lPgimSolicitudDocDTOModificado = documentoRequeridoService.modificarFechasRecepcion(pgimSolicitudDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar actualizar las fechas de recepción");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Las fechas de recepción fueron actualizadas");
        respuesta.put("lPgimItemSolicitudDocDTO", lPgimSolicitudDocDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    // STORY: PGIM-6222: Drag & drop para ordenamiento de documentos solicitados en la fisc
    @PutMapping("/modificarOrden")
    public ResponseEntity<?> modificarOrden(
            @Valid @RequestBody PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO, BindingResult resultadoValidacion)
            throws Exception {

		PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOModificado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para ordenar y actualizar el item de solicitud del documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimItemSolicitudDocDTOModificado = documentoRequeridoService.modificarOrden(pgimItemSolicitudDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar ordenar y actualizar el item de solicitud del documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El item de solicitud del documento ha sido ordenado y actualizado");
        respuesta.put("pgimItemSolicitudDocDTO", pgimItemSolicitudDocDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    //@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_MO')")
    @PutMapping("/modificarSolicitudDoc")
    public ResponseEntity<?> modificarSolicitudDoc(
            @Valid @RequestBody PgimSolicitudDocDTO pgimSolicitudDocDTO, BindingResult resultadoValidacion)
            throws Exception {

		PgimSolicitudDocDTO pgimSolicitudDocDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear el item de recepción del documento");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimSolicitudDocDTOCreado = documentoRequeridoService.modificarSolicitudDoc(pgimSolicitudDocDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear el documento requerido");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El documento requerido ha sido creado");
        respuesta.put("pgimSolicitudDocDTO", pgimSolicitudDocDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    @GetMapping("/obtenerSolicitudDocxIdSupervision/{idSupervision}")
    public ResponseEntity<?> obtenerSolicitudDocxIdSupervision(@PathVariable Long idSupervision) {
	
        Map<String, Object> respuesta = new HashMap<>();

        PgimSolicitudDocDTO pgimSolicitudDocDTO = null;

        try {
        	pgimSolicitudDocDTO = documentoRequeridoService.obtenerSolicitudDocxIdSupervision(idSupervision);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al obtener la solicitud del documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La solicitud de documento ha sido recuperado");
        respuesta.put("pgimSolicitudDocDTO", pgimSolicitudDocDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    //@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_AC')")
    @GetMapping("/listarItemSolicitudDocxIdSolicitudDoc/{idSolicitudDoc}")
    public ResponseEntity<?> listarItemSolicitudDocxIdSolicitudDoc(@PathVariable Long idSolicitudDoc) {
	
        Map<String, Object> respuesta = new HashMap<>();

        List<PgimItemSolicitudDocDTO> lPgimItemSolicitudDocDTO = null;

        try {
        	lPgimItemSolicitudDocDTO = documentoRequeridoService.listarItemSolicitudDocxIdSolicitudDoc(idSolicitudDoc);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la lista los items solicitados");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La lista de los items solicitados requeridos han sido recuperado");
        respuesta.put("lPgimItemSolicitudDocDTO", lPgimItemSolicitudDocDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }
    
    //@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_EL')")
    @DeleteMapping("/eliminarItemSolicitudDoc/{idItemSolicitudDoc}")
    public ResponseEntity<?> eliminarItemSolicitudDoc(@PathVariable Long idItemSolicitudDoc) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimItemSolicitudDoc pgimItemSolicitudDocActual = null;

        try {
        	pgimItemSolicitudDocActual = documentoRequeridoService.itemSolicitudDocfindById(idItemSolicitudDoc);

            if (pgimItemSolicitudDocActual == null) {
                mensaje = String.format("El item solicitud documento %s que intenta eliminar no existe en la base de datos",
                		idItemSolicitudDoc);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el item solicitud documento a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
        	documentoRequeridoService.eliminarItemSolicitudDoc(pgimItemSolicitudDocActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar el item solicitud documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/listarItemRecepcionDocxIdItemSolicitudDoc/{idItemSolicitudDoc}")
    public ResponseEntity<?> listarItemRecepcionDocxIdItemSolicitudDoc(@PathVariable Long idItemSolicitudDoc) {
	
        Map<String, Object> respuesta = new HashMap<>();

        List<PgimItemRecepcionDocDTO> lPgimItemRecepcionDocDTO = null;

        try {
        	lPgimItemRecepcionDocDTO = documentoRequeridoService.listarItemRecepcionDocxIdItemSolicitudDoc(idItemSolicitudDoc);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la lista los items requeridos");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La lista de los items requeridoshan sido recuperado");
        respuesta.put("lPgimItemRecepcionDocDTO", lPgimItemRecepcionDocDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }
    
    //@PreAuthorize("hasAnyAuthority('sp-sc-dcrq_EL')")
    @DeleteMapping("/eliminarItemRecepcionDoc/{idItemRecepcionDoc}")
    public ResponseEntity<?> eliminarItemRecepcionDoc(@PathVariable Long idItemRecepcionDoc) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimItemRecepcionDoc pgimItemRecepcionDocActual = null;

        try {
        	pgimItemRecepcionDocActual = documentoRequeridoService.itemRecepcionDocfindById(idItemRecepcionDoc);

            if (pgimItemRecepcionDocActual == null) {
                mensaje = String.format("El item recepción documento %s que intenta eliminar no existe en la base de datos",
                		idItemRecepcionDoc);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el item recepción documento a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
        	documentoRequeridoService.eliminarItemRecepcionDoc(pgimItemRecepcionDocActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar el item recepción documento");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
