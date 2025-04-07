package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la apreciar la muestra de img en los reportes
* @descripción: DTO para la apreciar la muestra de img en los reportes
*
* @author: palominovega
* @version: 1.0
* @fecha_de_creación: 18/12/2020
*/
@Getter
@Setter
@NoArgsConstructor
public class ImgReporteDTO {

	private Integer numOrden;
	private String base64Img;
	
}
