package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_INFRACCIONTOP15_AUX: 
* @descripción: Vista resumen de las top 15 infracciones más frecuentes por año
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInfracciontop15AuxDTO {

  /*
  *Secuencia: PGIM_SEQ_NRO_ITEM_AUX
  */
  private Long nroItemAux;

  /*
  *ANIO_INFRACCION
  */
  private Long anioInfraccion;

  /*
  *NO_CORTO_NORMA
  */
  private String noCortoNorma;

  /*
  *CO_TIPIFICACION
  */
  private String coTipificacion;

  /*
  *NO_ITEM_TIPIFICACION
  */
  private String noItemTipificacion;

  /*
  *NRO_INFRACCIONES
  */
  private Long nroInfracciones;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}