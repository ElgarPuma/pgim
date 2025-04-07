package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class ExpedienteListarPorUsuarioOut {

	private String asunto;
	private String estado;
	private String fechaCreacion;
	private String idProceso;
	private String idPropietario;
	private String msFechaCreacion;
	private String nroExpediente;
	private String RUCcliente;
	private String cliente;
	
	private String resultCode;
	private String errorCode;
	private String message;
	private String errorMessage;
	
	private String codigoCliente;
	private String noProceso;
	
	private List<ExpedienteListarPorUsuarioOut> lExpedienteListarPorUsuario;
	
	private String remitente;
	
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}
	public String getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(String idPropietario) {
		this.idPropietario = idPropietario;
	}
	public String getMsFechaCreacion() {
		return msFechaCreacion;
	}
	public void setMsFechaCreacion(String msFechaCreacion) {
		this.msFechaCreacion = msFechaCreacion;
	}
	public String getNroExpediente() {
		return nroExpediente;
	}
	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}	
	public String getRUCcliente() {
		return RUCcliente;
	}
	public void setRUCcliente(String rUCcliente) {
		RUCcliente = rUCcliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<ExpedienteListarPorUsuarioOut> getlExpedienteListarPorUsuario() {
		return lExpedienteListarPorUsuario;
	}
	public void setlExpedienteListarPorUsuario(List<ExpedienteListarPorUsuarioOut> lExpedienteListarPorUsuario) {
		this.lExpedienteListarPorUsuario = lExpedienteListarPorUsuario;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getNoProceso() {
		return noProceso;
	}
	public void setNoProceso(String noProceso) {
		this.noProceso = noProceso;
	}
	
	
}
