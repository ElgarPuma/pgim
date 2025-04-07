package pe.gob.osinergmin.pgim.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_OBLIGACION_NORMA_AUX: 
* @descripción: Vista para obtener la lista de obligaciones normativas
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimObligacionNormaAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_OBLIGACION_NORMA_AUX:  Secuencia: PGIM_SEQ_XYZ
  */
  private Long idObligacionNormaAux;

  /*
  *Identificador interno de la obligación normativa por criterio de la matriz de supervisión. Tabla: PGIM_TM_OBLGCN_NRMA_CRTRIO
  */
  private Long idOblgcnNrmaCrtrio;

  /*
  *Identificador interno del criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_CRITERIO
  */
  private Long idMatrizCriterio;

  /*
  *Identificador interno de la obligación normativa. Tabla padre: PGIM_TM_NORMA_OBLIGACION
  */
  private Long idNormaObligacion;

  /*
  *Indica si el registro de la tabla PGIM_TM_OBLGCN_NRMA_CRTRIO, es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String esVigenteOblgcnNrmaCrtrio;

  /*
  *Indica el estado del registro de la tabla PGIM_TM_OBLGCN_NRMA_CRTRIO. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistroOblgcnNrmaCrtrio;

  /*
  *Identificador interno del ítem de norma legal. Tabla padre PGIM_TM_NORMA_ITEM
  */
  private Long idNormaItem;

  /*
  *Identificador interno del ítem de tipificación. Tabla padre PGIM_TM_ITEM_TIPIFICACION
  */
  private Long idItemTipificacion;

  /*
  *Descripción de  la obligación normativa
  */
  private String deNormaObligacionT;

  /*
  *Indica si el registro de la tabla PGIM_TM_NORMA_OBLIGACION, es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String esVigenteNormaObligacion;

  /*
  *Indica el estado del registro de la tabla PGIM_TM_NORMA_OBLIGACION. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistroNormaObligacion;

  /*
  *Descripción de la obligación normativa obtenido a través de la función: PGIM_FUN_OBLIGACION_NORMATIVA
  */
  private String deObligacionNormativa;

  /*
  *Detalle del contenido del ítem de norma
  */
  private String deNormaItem;

  /*
  *Flag que indica si la obligación normativa ha sido seleccionada (Auxiliar)
  */
  private boolean descSeleccionado;

  /*
  *Identificador interno de la obligación normativa por hecho constatado de la supervisión. Tabla: PGIM_TC_OBLGCN_NRMTVA_HCHOC
  */
  private Long idOblgcnNrmtvaHchoc;

  /*
  * Código de tipificación
  */
  private String descCoTipificacion;

  /*
  * Nombre del ítem de la tipificación
  */
  private String descNoItemTipificacion;

  /*
  * Número norma del ítem de la tipificación
  */
  private String descNroNormaItemTipificacion;

  /*
   * lista de ítems norma que pertenecen a la obligación 
   */
  private List<PgimNormaItemAuxDTO> listPgimNormaItemAux;
  
  /*
   * lista de ítems norma que pertenecen a la obligación registrados 
   */
  private List<PgimOblgcnNrmtvaItemDTO> listPgimOblgcnNrmtvaItemDTO;

  
  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}