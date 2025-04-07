package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO auxiliar para preparar reporte supervisiones por especialidad
 * a partir de vista de detalle ya existente
 *  
 * @author USER
 *
 */

@Getter
@Setter
@NoArgsConstructor
public class SupervisionxEspecAuxDTO {

	private String noEspecialidad;
	
	private int nuProgramadas;
	
	private int nuNoProgramadas;
	
	private int nuTotalEspecialidad;
}
