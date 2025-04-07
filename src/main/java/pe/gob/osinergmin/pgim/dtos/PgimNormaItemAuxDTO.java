package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_NORMA_ITEM_AUX: 
* @descripción: Vista auxiliar para la consulta de los ítems de las normas
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimNormaItemAuxDTO {

  /*
  *Identificador auxiliar del ítem de la norma. Secuencia: PGIM_SEQ_NORMA_ITEM_AUX
  */
  private Long idNormaItemAux;

  /*
  *Identificador interno del ítem de norma legal. Tabla padre PGIM_TM_NORMA_ITEM
  */
  private Long idNormaItem;

  /*
  *Identificador interno del ítem de norma legal padre. Tabla padre PGIM_TM_NORMA_ITEM
  */
  private Long idNormaItemPadre;

  /*
  *Identificador interno de la norma
  */
  private Long idNorma;

  /*
  *Código del ítem de la norma
  */
  private String coItem;

  /*
  *Contenido del ítem de norma
  */
  private String deContenidoT;

  /*
  *Indicador de la vigencia o no vigencia del ítem de norma
  */
  private String flVigente;

  /*
  *Indicador del estado de registro
  */
  private String esRegistro;

  /*
  *División de Ítem de Norma. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_DIVISION_ITEM. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idDivisionItem;

  /*
  *Descripción de la división (tipo) del ítem de la norma
  */
  private String deDivisionItem;

  /*
  *Número de nivel en la jerarquía de los ítems de normas
  */
  private Long nuNivel;

  /*
  *Ubicación del ítem de norma
  */
  private String ubicacionItem;

  /*
  *Jerarquía del ítem de norma
  */
  private String jerarquiaItem;

  /*
  *Nombre de la norma legal
  */
  private String descNoNorma;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}