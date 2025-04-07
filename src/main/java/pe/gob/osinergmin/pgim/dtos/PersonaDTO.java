package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PersonaDTO  {	
		
	private Long idPersona;	

	private String correo;

	private String direccion;
	
	private String strfecNacimiento;
	
	private String nombreORazonSocial;

	private String nota;
	
	private String nroDocIdentidad;

	private String sexo;

	private String telefono;
	
	private String tipDocIdentidad;
	
	//private String idUbigeo; //Fk de la tabla Ubigeo
	
	private UbigeoDTO ubigeo; //se hizo este cambio para el componente de autocomplete en angular material

}
