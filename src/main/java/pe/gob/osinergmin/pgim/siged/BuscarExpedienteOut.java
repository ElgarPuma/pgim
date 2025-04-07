package pe.gob.osinergmin.pgim.siged;

import java.math.BigInteger;

public class BuscarExpedienteOut {

	private String arAcceso;
	private BigInteger arErrorCode;
	private String arMessage;
	private BigInteger arResultCode;
	
	private String asunto;
	private String clienteCorreo;
	private String clienteDireccionAlternativa;
	private String clienteDireccionPrincipal;

	private BigInteger cCodigoCliente;
	private BigInteger cCodigoTipoIdentificacion;
	private String cNombreCliente;
	private String cNumeroIdentificacion;
	
	private String clienteTelefono;
	private String clienteUbigeoAlternativo;
	private String clienteUbigeoPrincipal;
	private BigInteger errorCode;
	private String esExpedienteSym;
	private String estado;
	private String fechaArchivar;
	private String fechaCreacion;
	private BigInteger idCreador;
	private BigInteger idExpediente;
	private String idProceso;
	private BigInteger idPropietario;
	private String message;
	private BigInteger msFechaArchivar;
	private BigInteger msFechaCreacion;
	private String nombreCreador;
	private String nombreProceso;
	private String nombrePropietario;
	private String nroExpediente;
	private String remitente;
	private BigInteger resultCode;
	
	public String getArAcceso() {
		return arAcceso;
	}
	public void setArAcceso(String arAcceso) {
		this.arAcceso = arAcceso;
	}
	public BigInteger getArErrorCode() {
		return arErrorCode;
	}
	public void setArErrorCode(BigInteger arErrorCode) {
		this.arErrorCode = arErrorCode;
	}
	public String getArMessage() {
		return arMessage;
	}
	public void setArMessage(String arMessage) {
		this.arMessage = arMessage;
	}
	public BigInteger getArResultCode() {
		return arResultCode;
	}
	public void setArResultCode(BigInteger arResultCode) {
		this.arResultCode = arResultCode;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getClienteCorreo() {
		return clienteCorreo;
	}
	public void setClienteCorreo(String clienteCorreo) {
		this.clienteCorreo = clienteCorreo;
	}
	public String getClienteDireccionAlternativa() {
		return clienteDireccionAlternativa;
	}
	public void setClienteDireccionAlternativa(String clienteDireccionAlternativa) {
		this.clienteDireccionAlternativa = clienteDireccionAlternativa;
	}
	public String getClienteDireccionPrincipal() {
		return clienteDireccionPrincipal;
	}
	public void setClienteDireccionPrincipal(String clienteDireccionPrincipal) {
		this.clienteDireccionPrincipal = clienteDireccionPrincipal;
	}
	public BigInteger getcCodigoCliente() {
		return cCodigoCliente;
	}
	public void setcCodigoCliente(BigInteger cCodigoCliente) {
		this.cCodigoCliente = cCodigoCliente;
	}
	public BigInteger getcCodigoTipoIdentificacion() {
		return cCodigoTipoIdentificacion;
	}
	public void setcCodigoTipoIdentificacion(BigInteger cCodigoTipoIdentificacion) {
		this.cCodigoTipoIdentificacion = cCodigoTipoIdentificacion;
	}
	public String getcNombreCliente() {
		return cNombreCliente;
	}
	public void setcNombreCliente(String cNombreCliente) {
		this.cNombreCliente = cNombreCliente;
	}
	public String getcNumeroIdentificacion() {
		return cNumeroIdentificacion;
	}
	public void setcNumeroIdentificacion(String cNumeroIdentificacion) {
		this.cNumeroIdentificacion = cNumeroIdentificacion;
	}
	public String getClienteTelefono() {
		return clienteTelefono;
	}
	public void setClienteTelefono(String clienteTelefono) {
		this.clienteTelefono = clienteTelefono;
	}
	public String getClienteUbigeoAlternativo() {
		return clienteUbigeoAlternativo;
	}
	public void setClienteUbigeoAlternativo(String clienteUbigeoAlternativo) {
		this.clienteUbigeoAlternativo = clienteUbigeoAlternativo;
	}
	public String getClienteUbigeoPrincipal() {
		return clienteUbigeoPrincipal;
	}
	public void setClienteUbigeoPrincipal(String clienteUbigeoPrincipal) {
		this.clienteUbigeoPrincipal = clienteUbigeoPrincipal;
	}
	public BigInteger getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(BigInteger errorCode) {
		this.errorCode = errorCode;
	}
	public String getEsExpedienteSym() {
		return esExpedienteSym;
	}
	public void setEsExpedienteSym(String esExpedienteSym) {
		this.esExpedienteSym = esExpedienteSym;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaArchivar() {
		return fechaArchivar;
	}
	public void setFechaArchivar(String fechaArchivar) {
		this.fechaArchivar = fechaArchivar;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public BigInteger getIdCreador() {
		return idCreador;
	}
	public void setIdCreador(BigInteger idCreador) {
		this.idCreador = idCreador;
	}
	public BigInteger getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(BigInteger idExpediente) {
		this.idExpediente = idExpediente;
	}
	public String getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}
	public BigInteger getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(BigInteger idPropietario) {
		this.idPropietario = idPropietario;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BigInteger getMsFechaArchivar() {
		return msFechaArchivar;
	}
	public void setMsFechaArchivar(BigInteger msFechaArchivar) {
		this.msFechaArchivar = msFechaArchivar;
	}
	public BigInteger getMsFechaCreacion() {
		return msFechaCreacion;
	}
	public void setMsFechaCreacion(BigInteger msFechaCreacion) {
		this.msFechaCreacion = msFechaCreacion;
	}
	public String getNombreCreador() {
		return nombreCreador;
	}
	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}
	public String getNombreProceso() {
		return nombreProceso;
	}
	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	public String getNroExpediente() {
		return nroExpediente;
	}
	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public BigInteger getResultCode() {
		return resultCode;
	}
	public void setResultCode(BigInteger resultCode) {
		this.resultCode = resultCode;
	}
	
	
	
}
