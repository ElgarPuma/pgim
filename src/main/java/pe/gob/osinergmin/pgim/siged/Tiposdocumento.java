package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class Tiposdocumento {

	private String resultCode;
	private String message;
	private String errorCode;
	private String errorMessage;
	private List<TipoDocumento> listTipoDocumento;
	
	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
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
	
	public List<TipoDocumento> getListTipoDocumento() {
		return listTipoDocumento;
	}
	
	public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
	}
	
	
	
}
