package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_NORMA_OBLIGACION_AUX: 
* @descripción: Vista para obtener la lista de obligaciones de norma con información adicional
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 24/05/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimNormaObligacionAuxDTO {

  /*
  *Identificador interno de la obligación de norma por criterio de matriz de supervisión AUX. Secuencia: PGIM_SEQ_OBLGCN_NRMA_CRTRIO_AUX
  */
  private Long idNormaObligacionAux;

  /*
  *ID_NORMA_OBLIGACION
  */
  private Long idNormaObligacion;

  /*
  *NO_CORTO_NORMA
  */
  private String noCortoNorma;

  /*
  *NO_TIPO_NORMA
  */
  private String noTipoNorma;

  /*
  *ID_TIPO_NORMA
  */
  private Long idTipoNorma;

  /*
  *NO_NORMA
  */
  private String noNorma;

  /*
  *CO_ITEM
  */
  private String coItem;

  /*
  *DE_CONTENIDO
  */
  private String deContenidoT;

  /*
  *FL_VIGENTE_ITEM
  */
  private String flVigenteItem;

  /*
  *ID_DIVISION_ITEM
  */
  private Long idDivisionItem;

  /*
  *UBICACION_ITEM
  */
  private String ubicacionItem;

  /*
  *DE_NORMA_OBLIGACION
  */
  private String deNormaObligacionT;

  /*
  *ES_VIGENTE_NORMA_OBLIGACION
  */
  private String esVigenteNormaObligacion;

  /*
  *DE_SANCION_PECUNIARIA_UIT
  */
  private String deSancionPecuniariaUit;

  /*
  *CO_TIPIFICACION
  */
  private String coTipificacion;

  /*
  *NO_ITEM_TIPIFICACION
  */
  private String noItemTipificacion;

  /*
  *ES_VIGENTE_TIPIFICACION
  */
  private String esVigenteTipificacion;

  /*
  *CO_NORMA_TIPIFICACION
  */
  private String coNormaTipificacion;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;
  
  /*
   * Identificador interno del ítem de tipificación. Tabla padre PGIM_TM_ITEM_TIPIFICACION
   */
  private Long idItemTipificacion; 
  
  /*
   * Nombre de la división
   */
  private String descNoDivisionItem;
  

}