package pe.gob.osinergmin.pgim.security.model;

import java.util.List;

public class SissegListaModulo {
	
	private Integer codigo;
	
	private List<SissegModulo> modulos;

	public List<SissegModulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<SissegModulo> modulos) {
		this.modulos = modulos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	

	

}
