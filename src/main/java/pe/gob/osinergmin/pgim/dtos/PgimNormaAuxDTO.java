package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_NORMA_AUX: 
* @descripción: Vista auxiliar para la consulta y visualización de las normas
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimNormaAuxDTO {

  /*
  *Identificador interno de la norma legal. Secuencia: PGIM_SEQ_NORMA_AUX
  */
  private Long idNormaAux;

  /*
  *Identificador interno de la norma
  */
  private Long idNorma;

  /*
  *Nombre corto de la norma legal
  */
  private String noCortoNorma;

  /*
  *Nombre de la norma legal
  */
  private String noNorma;

  /*
  *Flag que indica si la norma se encuentra vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flVigente;

  /*
  *Fecha de la publicación de la norma
  */
  private Date fePublicacion;

  /*
  *Fecha de la publicación de la norma
  */
  private String fePublicacionDesc;

  /*
  *Tipo de norma
  */
  private Long idTipoNorma;

  /*
  *Nombre del tipo de norma
  */
  private String noTipoNorma;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;
  
  /*
   * Descriptor del identificados interno del cuadro de verificación
   */
  private Long descIdMatrizSupervision;
  
  /*
   * Descriptor de nombre del grupo que pertenece la norma: Cuadro de verificación o Obligación normativa (no tipificación)
   */
  private String descNoGrupoNorma; 

  /*
   * descriptop del codigo del tipo de norma
   */
  private String descCoTipoNorma;

}