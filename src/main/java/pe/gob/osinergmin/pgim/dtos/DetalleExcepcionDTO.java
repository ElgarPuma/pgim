package pe.gob.osinergmin.pgim.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleExcepcionDTO {
	
	private String proceso;
	
	private String objetoTrabajo;
	
	private String ubicacion;
	
	private String mensajeInfraResumen; 
	
	private List<String> listAcciones; 	

}
