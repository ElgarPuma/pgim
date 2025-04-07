package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_INFRACCIONXUM_AUX: 
* @descripción: Vista de todas las unidades mineras y sus infracciones en seis años
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInfraccionxumAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_INFRACCIONXUM_AUX. Secuencia: PGIM_SEQ_INF_UNIDAD_MINERA_AUX
  */
  private Long idUnidadMineraAux;

  /*
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
  private String noUnidadMinera;

  /*
  *ID_TIPO_UNIDAD_MINERA
  */
  private Long idTipoUnidadMinera;

  /*
  *NO_TIPO_UNIDAD_MINERA
  */
  private String noTipoUnidadMinera;

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