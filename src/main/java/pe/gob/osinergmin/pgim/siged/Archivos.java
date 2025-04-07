package pe.gob.osinergmin.pgim.siged;
import java.util.List;

public class Archivos{

	private String resultCode;
	private String message;
	private String errorCode;
	private List<Archivo> listaArchivo;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public List<Archivo> getListaArchivo() {
		return listaArchivo;
	}

	public void setListaArchivo(List<Archivo> listaArchivo) {
		this.listaArchivo = listaArchivo;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		return "Archivos [resultCode=" + resultCode + ", message=" + message + ", errorCode=" + errorCode
				+ ", listaArchivo=" + listaArchivo + "]";
	}

	

}
