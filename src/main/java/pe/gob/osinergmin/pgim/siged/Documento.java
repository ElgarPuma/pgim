package pe.gob.osinergmin.pgim.siged;
import java.util.List;

import pe.gob.osinergmin.pgim.utils.CommonsUtil;

public class Documento{

	private String estadoDigitalizacion;
	private String flagBloqueo;
	private String nombreTipoDocumento;
	private String nroDocumento;
	private String asunto;
	private String autor;
	private String delExpediente;
	private String enumerado;
	private String estaEnFlujo;
	private String estado;
	private String estadoAlarma;
	private String fechaAccion;
	private String fechaCreacion;
	private String fechaDocumento;
	private String fechaFirma;
	private String fechaLimiteAtencion;
	private String fechaNumeracion;
	private String firmado;
	private String idAccion;
	private String idDocumento;
	private String idEnumerador;
	private String idExpediente;
	private String idFirmante;
	private String idPropietario;
	private String idTipoDocumento;
	private String idUsrCompartidoEnumerador;
	private String idUsrCompartidoFirmante;
	private String leido;
	private String nombreAcccion;
	private String nombreEnumerador;
	private String nombreFirmante;
	private String nombrePropietario;
	private String nombreUsrCompartidoEnumerador;
	private String nombreUsrCompartidoFirmante;
	private String nroExpediente;
	private String nroFolios;
	private String principal;
	private String remitente;
	private String ultimoAsunto;
	private String usuarioCreador;
	private List<Archivo> archivos;
	
	private String rucCliente;
	private String dniCliente;
	private String razonsocialCliente;
	private String nombreCliente;
		
			
	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getNombreTipoDocumento() {
		return nombreTipoDocumento;
	}

	public void setNombreTipoDocumento(String nombreTipoDocumento) {
		this.nombreTipoDocumento = nombreTipoDocumento;
	}

	public String getEstadoDigitalizacion() {
		return estadoDigitalizacion;
	}

	public void setEstadoDigitalizacion(String estadoDigitalizacion) {
		this.estadoDigitalizacion = estadoDigitalizacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFlagBloqueo() {
		return flagBloqueo;
	}

	public void setFlagBloqueo(String flagBloqueo) {
		this.flagBloqueo = flagBloqueo;
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDelExpediente() {
		return delExpediente;
	}

	public void setDelExpediente(String delExpediente) {
		this.delExpediente = delExpediente;
	}

	public String getEnumerado() {
		return enumerado;
	}

	public void setEnumerado(String enumerado) {
		this.enumerado = enumerado;
	}

	public String getEstaEnFlujo() {
		return estaEnFlujo;
	}

	public void setEstaEnFlujo(String estaEnFlujo) {
		this.estaEnFlujo = estaEnFlujo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoAlarma() {
		return estadoAlarma;
	}

	public void setEstadoAlarma(String estadoAlarma) {
		this.estadoAlarma = estadoAlarma;
	}

	public String getFechaAccion() {
		return fechaAccion;
	}

	public void setFechaAccion(String fechaAccion) {
		this.fechaAccion = fechaAccion;
	}

	public String getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}

	public String getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(String fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public String getFechaLimiteAtencion() {
		return fechaLimiteAtencion;
	}

	public void setFechaLimiteAtencion(String fechaLimiteAtencion) {
		this.fechaLimiteAtencion = fechaLimiteAtencion;
	}

	public String getFechaNumeracion() {
		return fechaNumeracion;
	}

	public void setFechaNumeracion(String fechaNumeracion) {
		this.fechaNumeracion = fechaNumeracion;
	}

	public String getFirmado() {
		return firmado;
	}

	public void setFirmado(String firmado) {
		this.firmado = firmado;
	}

	public String getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(String idAccion) {
		this.idAccion = idAccion;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getIdEnumerador() {
		return idEnumerador;
	}

	public void setIdEnumerador(String idEnumerador) {
		this.idEnumerador = idEnumerador;
	}

	public String getIdExpediente() {
		return idExpediente;
	}

	public void setIdExpediente(String idExpediente) {
		this.idExpediente = idExpediente;
	}

	public String getIdFirmante() {
		return idFirmante;
	}

	public void setIdFirmante(String idFirmante) {
		this.idFirmante = idFirmante;
	}

	public String getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(String idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getIdUsrCompartidoEnumerador() {
		return idUsrCompartidoEnumerador;
	}

	public void setIdUsrCompartidoEnumerador(String idUsrCompartidoEnumerador) {
		this.idUsrCompartidoEnumerador = idUsrCompartidoEnumerador;
	}

	public String getIdUsrCompartidoFirmante() {
		return idUsrCompartidoFirmante;
	}

	public void setIdUsrCompartidoFirmante(String idUsrCompartidoFirmante) {
		this.idUsrCompartidoFirmante = idUsrCompartidoFirmante;
	}

	public String getLeido() {
		return leido;
	}

	public void setLeido(String leido) {
		this.leido = leido;
	}

	public String getNombreAcccion() {
		return nombreAcccion;
	}

	public void setNombreAcccion(String nombreAcccion) {
		this.nombreAcccion = nombreAcccion;
	}

	public String getNombreEnumerador() {
		return nombreEnumerador;
	}

	public void setNombreEnumerador(String nombreEnumerador) {
		this.nombreEnumerador = nombreEnumerador;
	}

	public String getNombreFirmante() {
		return nombreFirmante;
	}

	public void setNombreFirmante(String nombreFirmante) {
		this.nombreFirmante = nombreFirmante;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getNombreUsrCompartidoEnumerador() {
		return nombreUsrCompartidoEnumerador;
	}

	public void setNombreUsrCompartidoEnumerador(String nombreUsrCompartidoEnumerador) {
		this.nombreUsrCompartidoEnumerador = nombreUsrCompartidoEnumerador;
	}

	public String getNombreUsrCompartidoFirmante() {
		return nombreUsrCompartidoFirmante;
	}

	public void setNombreUsrCompartidoFirmante(String nombreUsrCompartidoFirmante) {
		this.nombreUsrCompartidoFirmante = nombreUsrCompartidoFirmante;
	}

	public String getNroExpediente() {
		return nroExpediente;
	}

	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}

	public String getNroFolios() {
		return nroFolios;
	}

	public void setNroFolios(String nroFolios) {
		this.nroFolios = nroFolios;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getUltimoAsunto() {
		return ultimoAsunto;
	}

	public void setUltimoAsunto(String ultimoAsunto) {
		this.ultimoAsunto = ultimoAsunto;
	}

	public List<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}

	public String getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(String usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}
	
	public String getRucCliente() {
		return rucCliente;
	}

	public void setRucCliente(String rucCliente) {
		this.rucCliente = rucCliente;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getRazonsocialCliente() {
		return razonsocialCliente;
	}

	public void setRazonsocialCliente(String razonsocialCliente) {
		this.razonsocialCliente = razonsocialCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	@Override
	public String toString() {
		return "Documento [estadoDigitalizacion=" + estadoDigitalizacion + ", flagBloqueo=" + flagBloqueo
				+ ", nombreTipoDocumento=" + nombreTipoDocumento + ", nroDocumento=" + nroDocumento + ", asunto="
				+ asunto + ", autor=" + autor + ", delExpediente=" + delExpediente + ", enumerado=" + enumerado
				+ ", estaEnFlujo=" + estaEnFlujo + ", estado=" + estado + ", estadoAlarma=" + estadoAlarma
				+ ", fechaAccion=" + fechaAccion + ", fechaCreacion=" + fechaCreacion + ", fechaDocumento="
				+ fechaDocumento + ", fechaFirma=" + fechaFirma + ", fechaLimiteAtencion=" + fechaLimiteAtencion
				+ ", fechaNumeracion=" + fechaNumeracion + ", firmado=" + firmado + ", idAccion=" + idAccion
				+ ", idDocumento=" + idDocumento + ", idEnumerador=" + idEnumerador + ", idExpediente=" + idExpediente
				+ ", idFirmante=" + idFirmante + ", idPropietario=" + idPropietario + ", idTipoDocumento="
				+ idTipoDocumento + ", idUsrCompartidoEnumerador=" + idUsrCompartidoEnumerador
				+ ", idUsrCompartidoFirmante=" + idUsrCompartidoFirmante + ", leido=" + leido + ", nombreAcccion="
				+ nombreAcccion + ", nombreEnumerador=" + nombreEnumerador + ", nombreFirmante=" + nombreFirmante
				+ ", nombrePropietario=" + nombrePropietario + ", nombreUsrCompartidoEnumerador="
				+ nombreUsrCompartidoEnumerador + ", nombreUsrCompartidoFirmante=" + nombreUsrCompartidoFirmante
				+ ", nroExpediente=" + nroExpediente + ", nroFolios=" + nroFolios + ", principal=" + principal
				+ ", remitente=" + remitente + ", ultimoAsunto=" + ultimoAsunto + ", usuarioCreador=" + usuarioCreador
				+ ", archivos=" + archivos + "]";
	}
	
	
	public static String getConstruirXmlFile(Documento obj, String carpetaTmp) throws Exception {
		String xmlSource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<documento>\n" + 
				"    <archivos>\n" + 
				"        <archivo>\n" + 
				"            <descripcion></descripcion>\n" + 
				"            <idArchivo></idArchivo>\n" + 
				"        </archivo>\n" + 
				"    </archivos>\n" + 
				"    <idDocumento>"+ obj.getIdDocumento() +"</idDocumento>\n" + 
				"    <usuarioCreador>"+ obj.getUsuarioCreador() +"</usuarioCreador>\n" + 
				"</documento>\n";
		CommonsUtil comm = new CommonsUtil();
		String nombre = CommonsUtil.generadorNombreUnico();
		String pathFile = comm.stringToDom(xmlSource, nombre, carpetaTmp);
        return pathFile;
    }		
	
	
	
		
}
