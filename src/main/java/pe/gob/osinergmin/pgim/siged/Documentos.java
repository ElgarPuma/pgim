package pe.gob.osinergmin.pgim.siged;
import java.util.List;

public class Documentos{

	private String resultCode;
	private String message;
	private String errorCode;
	private String errorMessage;
	private List<Documento> listaDocumento;

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

	public List<Documento> getListaDocumento() {
		return listaDocumento;
	}

	public void setListaDocumento(List<Documento> listaDocumento) {
		this.listaDocumento = listaDocumento;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "SIGEDDocumentos [resultCode=" + resultCode + ", message=" + message + ", errorCode=" + errorCode
				+ ", listaDocumento=" + listaDocumento + "]";
	}

}
