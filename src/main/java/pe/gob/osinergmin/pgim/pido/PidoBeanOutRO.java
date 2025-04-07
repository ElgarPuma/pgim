package pe.gob.osinergmin.pgim.pido;

public class PidoBeanOutRO {
	protected String resultCode;
    protected String message;
    protected String nombres;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String direccion;
    protected String estadoCivil;
    protected byte[] foto;
    protected String restriccion;
    protected String ubigeo;
    protected String fechaNacimiento;
    protected String sexo;
    protected String deResultado;
    protected Boolean esCiudadanoValido;
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
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getRestriccion() {
		return restriccion;
	}
	public void setRestriccion(String restriccion) {
		this.restriccion = restriccion;
	}
	public String getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDeResultado() {
		return deResultado;
	}
	public void setDeResultado(String deResultado) {
		this.deResultado = deResultado;
	}
	public Boolean getEsCiudadanoValido() {
		return esCiudadanoValido;
	}
	public void setEsCiudadanoValido(Boolean esCiudadanoValido) {
		this.esCiudadanoValido = esCiudadanoValido;
	}
	
}
