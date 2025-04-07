package pe.gob.osinergmin.pgim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;


@RestControllerAdvice
@Slf4j
public class ExceptionGlobalResponse extends ResponseEntityExceptionHandler {		


	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ResponseDTO> notFoundExeption (NotFoundException ex) {		
		log.error(ex.getMessage(), ex);
		
		ResponseDTO response = new ResponseDTO("error", ex.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);		
	}


	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ResponseDTO> badRequestException (BadRequestException ex) {		
		log.error(ex.getMessage(), ex);

		ResponseDTO response = new ResponseDTO("error", ex.getMessage());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);		
	}


	@ExceptionHandler(MethodNotAllowedException.class)
	public final ResponseEntity<ResponseDTO> methodNotAllowedException (MethodNotAllowedException ex) {		
		log.error(ex.getMessage(), ex);
		
		ResponseDTO response = new ResponseDTO("error", ex.getMessage());

		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);		
	}	

	@ExceptionHandler(PgimException.class)
	public final ResponseEntity<ResponseDTO> pgimExceptionPersonalizado(PgimException ex) {	
		log.error("Ingreso a PgimException global===========");		
		log.error(ex.getMensaje(), ex); 

		ResponseDTO response = new ResponseDTO(ex.getTipoResultado(), ex.getMensaje());			

		return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);		
	}	

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<ResponseDTO> accesoDenegadoException(AccessDeniedException ex) {	
		log.error("Ingreso a accesoDenegadoException global===========");
		log.error(ex.getMessage(), ex);

		ResponseDTO response = new ResponseDTO("error", "No estas autorizado para acceder a este recurso");			

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);		
	}	


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseDTO> manejarTodasExcepciones(Exception ex) {			
		log.error("Ingreso a Exception global===========");
		log.error(ex.getMessage(), ex);	
		
		ex.printStackTrace();
		
		String message = ex.getCause()!=null?(ex.getCause().getCause()!=null?ex.getCause().getCause().getMessage():ex.toString()):
			ex.toString();					

		ResponseDTO response = new ResponseDTO("error",  message);			

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}



}
