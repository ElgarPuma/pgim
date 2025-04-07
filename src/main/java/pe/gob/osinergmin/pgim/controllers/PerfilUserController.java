package pe.gob.osinergmin.pgim.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.services.PerfilUserService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas al perfil de usuario 
 * 
 * @descripción: Controlador de perfil de usuario
 *
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 18/05/2023
 *
 */
@RestController
@Slf4j
@RequestMapping("/perfiluser")
public class PerfilUserController extends BaseController {
	
	@Autowired
	private PerfilUserService perfilUserService;
	

	/**
	 * Permite guardar la foto para el usuario en sesión
	 * 
	 * @param photo	Archivo multipartFile de la foto a guardar
	 * @return
	 */
	@PostMapping("/guardarFoto")
    public ResponseEntity<ResponseDTO> guardarFoto(@RequestPart("photo") MultipartFile photo) {
		
		try {
			log.info("Inicio de solicitud para guardar foto de usuario " + this.obtenerAuditoria().getUsername());
		
            if (photo.isEmpty()) {
            	String mensajeException = "El archivo de foto está vacío.";
            	log.error(mensajeException);
            	return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeException));
            }
            
            this.perfilUserService.guardarFotoUsuario(photo, this.obtenerAuditoria().getUsername());

        } catch (PgimException e) {
        	String mensajeException = "Lo sentimos, operación no concretada. " + e.getMensaje();
        	log.error(e.getMensaje(), e);
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), mensajeException));
        	
        } catch (IOException e) {
        	String mensajeException = "Ocurrió un error al actualizar la foto: " + e.getMessage();
        	log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeException));
            
        } catch (Exception e) {
        	String mensajeException = "Ocurrió un error al actualizar la foto: " + e.getMessage();
        	log.error(e.getMessage(), e);
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeException));
        }
        
        String mensaje = "Genial, la foto ha sido actualizada";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, mensaje));
    }
	
	/**
	 * Permite obtener la foto de usuario a partir de su idPersona, en formato base64
	 * 
	 * @param idPersona
	 * @return
	 */
	@GetMapping("/obtenerFotoPefil/{idPersona}")
    public ResponseEntity<ResponseDTO> getPhoto(@PathVariable Long idPersona) {
		
		log.info("Inicio de solicitud para obtener la foto de usuario, id persona: " + idPersona);
		
		String photoBase64 = "";
		
        try {

        	photoBase64 = this.perfilUserService.obtenerFotoPersona(idPersona);        	
            
        } catch (Exception e) {
        	String mensajeException = "Ocurrió un error al recuperar la foto de perfil: " + e.getMessage();
        	log.error(e.getMessage(), e);
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeException));
        }
        
        String mensaje = "Genial, la foto de perfil ha sido recuperada";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, photoBase64, mensaje));
    }
	
	/**
	 * Permite quitar la foto de usuario a partir de su idPersona
	 * 
	 * @param idPersona
	 * @return
	 */
	@GetMapping("/quitarFotoPerfil/{idPersona}")
    public ResponseEntity<ResponseDTO> quitarFoto(@PathVariable Long idPersona) {
		
		log.info("Inicio de solicitud para quitar la foto de usuario, id persona: " + idPersona);
		
        try {

        	this.perfilUserService.eliminarFotoPersona(idPersona);        	
            
        } catch (Exception e) {
        	String mensajeException = "Ocurrió un error al intentar quitar la foto de perfil: " + e.getMessage();
        	log.error(e.getMessage(), e);
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeException));
        }
        
        String mensaje = "Genial, su foto de perfil ha sido quitada";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, mensaje));
    }

}
