package pe.gob.osinergmin.pgim.siged;

public class DocumentoOutRO {

	private String resultCode;
	private String codigoDocumento;
	private String errorCode;
	private String message;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getCodigoDocumento() {
		return codigoDocumento;
	}
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
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
	@Override
	public String toString() {
		return "DocumentoOutRO [resultCode=" + resultCode + ", codigoDocumento=" + codigoDocumento + ", errorCode="
				+ errorCode + ", message=" + message + "]";
	}
	
	
	
}
