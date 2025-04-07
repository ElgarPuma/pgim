package pe.gob.osinergmin.pgim.siged;

import java.io.ByteArrayOutputStream;

public class DescargaArchivo {

	private String resultCode;
	private String nombre;
	private String idArchivo;
	private String errorCode;
	private String message;
	private ByteArrayOutputStream file;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ByteArrayOutputStream getFile() {
		return file;
	}
	public void setFile(ByteArrayOutputStream file) {
		this.file = file;
	}
	
	
	
}
