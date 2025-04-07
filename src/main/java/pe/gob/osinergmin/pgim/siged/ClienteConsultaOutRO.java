package pe.gob.osinergmin.pgim.siged;

public class ClienteConsultaOutRO {
	
	private String resultCode;
	private String message;
	private String errorCode;
	
	private String correo;
	private String nombreCiudadano;
	private String apellidoPaternoCiudadano;
	private String apellidoMaternoCiudadano;
	private String celular;
	private String telefonoOtro;
	private String estado;
	private String fechaCreacion;
	private String idCliente;
	private String nombre;
	private String nroIdentificacion;
	private String tipoIdentificacion;
	
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombreCiudadano() {
		return nombreCiudadano;
	}
	public void setNombreCiudadano(String nombreCiudadano) {
		this.nombreCiudadano = nombreCiudadano;
	}
	public String getApellidoPaternoCiudadano() {
		return apellidoPaternoCiudadano;
	}
	public void setApellidoPaternoCiudadano(String apellidoPaternoCiudadano) {
		this.apellidoPaternoCiudadano = apellidoPaternoCiudadano;
	}
	public String getApellidoMaternoCiudadano() {
		return apellidoMaternoCiudadano;
	}
	public void setApellidoMaternoCiudadano(String apellidoMaternoCiudadano) {
		this.apellidoMaternoCiudadano = apellidoMaternoCiudadano;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefonoOtro() {
		return telefonoOtro;
	}
	public void setTelefonoOtro(String telefonoOtro) {
		this.telefonoOtro = telefonoOtro;
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
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNroIdentificacion() {
		return nroIdentificacion;
	}
	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	
	@Override
	public String toString() {
		return "ClienteConsultaOutRO [resultCode=" + resultCode + ", correo=" + correo 
				+ ", nombreCiudadano="+ nombreCiudadano 
				+ ", apellidoPaternoCiudadano="+apellidoPaternoCiudadano
				+ ", apellidoMaternoCiudadano="+apellidoMaternoCiudadano
				+ ", celular="+celular
				+ ", telefonoOtro="+telefonoOtro
				+ ", estado="+estado
				+ ", fechaCreacion="+fechaCreacion
				+ ", idCliente="+idCliente
				+ ", nombre="+nombre
				+ ", nroIdentificacion="+nroIdentificacion
				+ ", tipoIdentificacion="+tipoIdentificacion
				+ ", errorCode=" + errorCode + ", message=" + message + "]";
	}
	

}
