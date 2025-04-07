package pe.gob.osinergmin.pgim.siged;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Archivo {

	private String estadoDigitalizacion;
	private String fechaCreacion;
	private String flagBloqueo;
	private String idArchivo;
	private String nombre;
	private String rutaAlfresco;
	private String idDocumento;
	private List<FirmaDigitalSiged> firmaDigitalSiged;
	private List<VersionArchivo> versiones;
	
	public List<FirmaDigitalSiged> getFirmaDigitalSiged() {
		return firmaDigitalSiged;
	}

	public void setFirmaDigitalSiged(List<FirmaDigitalSiged> firmaDigitalSiged) {
		this.firmaDigitalSiged = firmaDigitalSiged;
	}

	public String getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRutaAlfresco() {
		return rutaAlfresco;
	}

	public void setRutaAlfresco(String rutaAlfresco) {
		this.rutaAlfresco = rutaAlfresco;
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

	public Date getFechaCreacionDate() {
		Date myFecha = null;
		if (this.fechaCreacion != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			try {
				myFecha = formatter.parse(this.fechaCreacion);
			} catch (ParseException e) {
				myFecha = null;
				log.error(e.getMessage(), e);
			}
		}

		return myFecha;
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
	
	public String getIdDocumento() {
		return idDocumento;
	}
	
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	
	public List<VersionArchivo> getVersiones() {
		return versiones;
	}

	public void setVersiones(List<VersionArchivo> versiones) {
		this.versiones = versiones;
	}

	@Override
	public String toString() {
		return "SIGEDArchivo [estadoDigitalizacion=" + estadoDigitalizacion + ", fechaCreacion=" + fechaCreacion
				+ ", flagBloqueo=" + flagBloqueo + ", idArchivo=" + idArchivo + ", nombre=" + nombre + ", rutaAlfresco="
				+ rutaAlfresco + ", idDocumento=" + idDocumento  + "]";
	}

}

