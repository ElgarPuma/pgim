package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UbigeoDTO {	

	private String idUbigeo;
	
	private String nombre;

	public UbigeoDTO(String idUbigeo, String nombre) {
		super();
		this.idUbigeo = idUbigeo;
		this.nombre = nombre;
	}	
	


}
