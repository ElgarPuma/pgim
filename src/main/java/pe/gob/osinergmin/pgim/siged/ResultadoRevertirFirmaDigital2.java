package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class ResultadoRevertirFirmaDigital2 {

	private String resultCode;
	private String errorCode;
	private String message;
	private List<ArchivoRevertir> archivosRevertir;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
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
	public List<ArchivoRevertir> getArchivosRevertir() {
		return archivosRevertir;
	}
	public void setArchivosRevertir(List<ArchivoRevertir> archivosRevertir) {
		this.archivosRevertir = archivosRevertir;
	}

}
