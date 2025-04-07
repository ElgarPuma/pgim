package pe.gob.osinergmin.pgim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends RuntimeException {
	
	private static final long serialVersionUID = 2798360373784050205L;

	public MethodNotAllowedException(String mensaje) {
		super(mensaje);
	}

}
