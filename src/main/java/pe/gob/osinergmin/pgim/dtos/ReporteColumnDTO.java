package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReporteColumnDTO {
	
	private String nameColumn; // nombre de la columna, coincide con el nombre de la columna de tabla o vista de la BD  
	private String labelColumn; // etiqueta de la columna que se mostrará en el reporte
	private String horizAlignColumn; // alineación horizontal en la columna
	private String typeColumn; // tipo del valor de la  columna (fecha, cadena, decimal, etc)    	
	private int widthColum; // ancho de la columna en puntos 20th

}
