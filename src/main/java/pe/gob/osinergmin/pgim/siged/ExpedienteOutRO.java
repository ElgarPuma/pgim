package pe.gob.osinergmin.pgim.siged;

import pe.gob.osinergmin.pgim.dtos.DetalleExcepcionDTO;

public class ExpedienteOutRO {

	private String resultCode;
	private String codigoExpediente;
	private String idDocumento;
	private String errorCode;
	private String message;
	private DetalleExcepcionDTO detalleExcepcionDTO;
	
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
	public String getCodigoExpediente() {
		return codigoExpediente;
	}
	public void setCodigoExpediente(String codigoExpediente) {
		this.codigoExpediente = codigoExpediente;
	}
	public String getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	public DetalleExcepcionDTO getDetalleExcepcionDTO() {
		return detalleExcepcionDTO;
	}
	public void setDetalleExcepcionDTO(DetalleExcepcionDTO detalleExcepcionDTO) {
		this.detalleExcepcionDTO = detalleExcepcionDTO;
	}
	@Override
	public String toString() {
		return "ExpedienteOutRO [resultCode=" + resultCode + ", codigoExpediente=" + codigoExpediente + ", idDocumento="
				+ idDocumento + ", errorCode=" + errorCode + ", message=" + message + "]";
	}
	
	
	
}
