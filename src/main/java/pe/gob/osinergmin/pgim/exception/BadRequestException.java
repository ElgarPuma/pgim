package pe.gob.osinergmin.pgim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = -1679039055686369085L;

	public BadRequestException(String mensaje) {
		super(mensaje);
	}

}
