package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_ITEM_TIPIFICACION_AUX: 
* @descripción: Vista para obtener la lista de items de tipificación
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimItemTipificacionAuxDTO {

  /*
  *Identificador interno del ítem de tipificación. Secuencia: PGIM_SEQ_ITEM_TIPIFICA_AUX
  */
  private Long idItemTipificacionAux;

  /*
  *Identificador interno del ítem de tipificación. Secuencia: PGIM_SEQ_ITEM_TIPIFICACION
  */
  private Long idItemTipificacion;

  /*
  *Nombre corto de la norma legal
  */
  private String noCortoNorma;

  /*
  *Identificador interno de la norma legal. Tabla padre PGIM_TM_NORMA
  */
  private Long idNorma;

  /*
  *Código de la tipificación
  */
  private String coTipificacion;

  /*
  *Nombre del ítem de tipificación
  */
  private String noItemTipificacion;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String esVigente;

  /*
  *Número de orden en la presentación
  */
  private Long nuOrden;

  /*
  *Descripción de la sancion pecuniaria en UIT
  */
  private String deSancionPecuniariaUit;

  /*
  *Cantidad de obligaciones relacionadas con las obligaciones (asociación entre ítem de norma y ítem de tipificación
  */
  private Long nuObligaciones;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}