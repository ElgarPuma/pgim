package pe.gob.osinergmin.pgim.security.model;


import org.apache.commons.lang3.StringUtils;

public class SissegModulo {
	
	private Long idModulo;
	
	private String nombre;
	

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNombre() {
		return StringUtils.isNotBlank(nombre)?nombre.trim():"";
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}