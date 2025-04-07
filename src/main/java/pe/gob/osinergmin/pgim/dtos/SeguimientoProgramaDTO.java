package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
*  
* @descripción: DTO para el componente de seguimiento del programa 
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 11/05/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class SeguimientoProgramaDTO {
	
	/*
     * Costo real total del programa 
     */
    private BigDecimal realCostoTotal;
    
    /*
     * Costo real de las supervisiones no programadas del programa 
     */
    private BigDecimal realCostoNoProgramadas;
    
    /*
     * Costo real de las supervisiones programadas del programa 
     */
    private BigDecimal realCostoProgramadas;

}
