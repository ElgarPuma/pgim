package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Builder
@Getter
@Setter
@NoArgsConstructor
public class EmpresaSupervisoraDTO {	
	
	//Datos empresa supervisora
	
	private Long idEmpresaSupervisora;

	private String activo;
	
	//Datos persona
	
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
	
	//Datos de Ubigeo
	
	//private String idUbigeo; //Fk de la tabla Ubigeo
	
	private UbigeoDTO ubigeo; //se hizo este cambio para el componente de autocomplete en angular material	

	
	//Constructor para rebir el query del JPA
	public EmpresaSupervisoraDTO(Long idEmpresaSupervisora, Long idPersona, String direccion, String correo,
			String nombreORazonSocial, String nota, String tipDocIdentidad, String nroDocIdentidad,
			String telefono) {
		super();
		this.idEmpresaSupervisora = idEmpresaSupervisora;		
		this.idPersona = idPersona;
		this.correo = correo;
		this.direccion = direccion;		
		this.nombreORazonSocial = nombreORazonSocial;
		this.nota = nota;
		this.nroDocIdentidad = nroDocIdentidad;		
		this.telefono = telefono;
		this.tipDocIdentidad = tipDocIdentidad;
	}
	
	
	
	
	

}
