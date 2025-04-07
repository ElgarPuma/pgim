package pe.gob.osinergmin.pgim.security.model;

import java.util.List;

public class SissegListaPermiso {
	
	private Integer codigo;
	
	private List<SissegRol> permisos;
	
	private List<SissegRol> persmisos; // se debe borrar cuando se prescinda del Sisseg-PGIM

	public List<SissegRol> getPersmisos() { // se debe borrar cuando se prescinda del Sisseg-PGIM
		return persmisos;
	}

	public void setPersmisos(List<SissegRol> persmisos) { // se debe borrar cuando se prescinda del Sisseg-PGIM
		this.persmisos = persmisos;
	}

	public List<SissegRol> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<SissegRol> permisos) {
		this.permisos = permisos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
	

}
