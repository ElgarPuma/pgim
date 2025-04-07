package pe.gob.osinergmin.pgim.siged;

import java.time.LocalDateTime;


public class FirmaDigitalSiged {

	LocalDateTime fechaFirma;
	String idUsuarioFirma;
	String nombresUsuario;
	String coUsuario;
	
	public String getCoUsuario() {
		return coUsuario;
	}
	public void setCoUsuario(String coUsuario) {
		this.coUsuario = coUsuario;
	}
	public LocalDateTime getFechaFirma() {
		return fechaFirma;
	}
	public void setFechaFirma(LocalDateTime dateTime) {
		this.fechaFirma = dateTime;
	}
	public String getIdUsuarioFirma() {
		return idUsuarioFirma;
	}
	public void setIdUsuarioFirma(String idUsuarioFirma) {
		this.idUsuarioFirma = idUsuarioFirma;
	}
	public String getNombresUsuario() {
		return nombresUsuario;
	}
	public void setNombresUsuario(String nombresUsuario) {
		this.nombresUsuario = nombresUsuario;
	}
	
	
	
}
