package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_INFRACCIONXESP_MES_AUX: 
* @descripción: Vista de todas las especialidades y sus infracciones por mes y año
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInfraccionxespMesAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_INFRACCIONXESP_MES_AUX. Secuencia: PGIM_SEQ_INFRACCIONXESP_MES_AUX
  */
  private Long idInfraccionxespMesAux;

  /*
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *ANIO
  */
  private String anio;

  /*
  *NRO_INFRACCION_P1
  */
  private Long nroInfraccionP1;

  /*
  *NRO_INFRACCION_P2
  */
  private Long nroInfraccionP2;

  /*
  *NRO_INFRACCION_P3
  */
  private Long nroInfraccionP3;

  /*
  *NRO_INFRACCION_P4
  */
  private Long nroInfraccionP4;

  /*
  *NRO_INFRACCION_P5
  */
  private Long nroInfraccionP5;

  /*
  *NRO_INFRACCION_P6
  */
  private Long nroInfraccionP6;

  /*
  *NRO_INFRACCION_P7
  */
  private Long nroInfraccionP7;

  /*
  *NRO_INFRACCION_P8
  */
  private Long nroInfraccionP8;

  /*
  *NRO_INFRACCION_P9
  */
  private Long nroInfraccionP9;

  /*
  *NRO_INFRACCION_P10
  */
  private Long nroInfraccionP10;

  /*
  *NRO_INFRACCION_P11
  */
  private Long nroInfraccionP11;

  /*
  *NRO_INFRACCION_P12
  */
  private Long nroInfraccionP12;

  /*
  *NRO_INFRACCION_TOTAL
  */
  private Long nroInfraccionTotal;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}