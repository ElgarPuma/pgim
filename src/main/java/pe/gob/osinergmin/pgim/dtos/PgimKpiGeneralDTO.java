package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para los KPIs generales del dashboard 
* @descripción: KPIs generales del dashboard
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 02/02/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimKpiGeneralDTO {

	/*
	   * Total de registros. Cantidad de registros analizados. 
	   */
	 private Long nuTotalRegistros; 
	  
	 /*
	   * Máximo:  De la diferencia en días entre la fecha de envío de la primera presentación del informe de fiscalización de empresa supervisora y su respectiva aprobación. 
	   */
	 private Long nuMaximo;
	 
	 /*
	   * Mínimo: De la diferencia en días entre la fecha de envío de la primera presentación del informe de fiscalización de empresa supervisora y su respectiva aprobación.
	   */
	 private Long nuMinimo;
	 
	 /*
	   * Promedio: De la diferencia en días entre la fecha de envío de la primera presentación del informe de fiscalización de empresa supervisora y su respectiva aprobación. 
	   */
	 private Double nuPromedio;
	 
	  

	  
}
