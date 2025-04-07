package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @descripción: Resultado para uso en gráfica AMChart
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 03/03/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimGraficaResultadoDTO {
	
	private String descripcion;
    private Long cantidadEntero;
    private Double cantidadDecimal;

}
