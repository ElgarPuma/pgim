package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class DocumentoConsultaOutRO {
	
	private String resultCode;
	private String message;
	private String errorCode;
	private String errorMessage;
	private String nroExpediente;
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
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {	
		this.errorMessage = errorMessage;
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

	public String getNroExpediente() {
		return nroExpediente;
	}

	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}

	@Override
	public String toString() {
		return "SIGEDDocumentos [resultCode=" + resultCode + ", message=" + message + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + ", listaArchivo=" + listaArchivo + "]";
	}

}
