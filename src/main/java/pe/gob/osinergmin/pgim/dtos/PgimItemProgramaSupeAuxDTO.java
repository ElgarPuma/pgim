package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_ITEM_PROGRAMA_SUPE_AUX: 
* @descripción: Vista para el soporte del listado de ítems del programa de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimItemProgramaSupeAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_ITEM_PROGRAMA_SUPE_AUX. Secuencia: PGIM_SEQ_ITEM_PROGRAMA_SUPE_AUX
  */
  private Long idItemProgramaSupeAux;

  /*
  *Identificador interno del ítem del programa de supervisión
  */
  private Long idItemProgramaSupe;

  /*
  *Listado de configuraciones de riesgo que aplican sobre la unidad minera del ítem del programa de supervisión
  */
  private String deConfiguracionesRiesgo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}