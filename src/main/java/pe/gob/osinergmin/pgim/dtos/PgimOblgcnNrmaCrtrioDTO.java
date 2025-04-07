package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_OBLGCN_NRMA_CRTRIO: 
* @descripción: Obligación normativa por criterio de matriz de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimOblgcnNrmaCrtrioDTO {

  /*
  *Identificador interno de la obligación normativa por criterio de la matriz de supervisión. Secuencia: PGIM_SEQ_OBLGCN_NRMA_CRTRIO
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
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String esVigente;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Usuario creador
  */
  private String usCreacion;

  /*
  *Terminal de creación
  */
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /*
  *Usuario modificador
  */
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private Date feActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  *Nombre corto de la norma legal
  */
  private String descNoCortoNorma;

  /*
  *Código del ítem de la norma
  */
  private String descCoItem;

  /*
  *Contenido del ítem de norma
  */
  private String descDeContenido;

  /*
  *Contenido del ítem de norma padre
  */
  private String deNormaItemPadre;

  /*
  *Descripción de  la obligación normativa
  */
  private String descDeNormaObligacion;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String descEsVigenteNormaObligacion;

  /*
  *Descripción de la sancion pecuniaria en UIT
  */
  private String descDeSancionPecuniariaUit;

  /*
  *Código de la tipificación
  */
  private String descCoTipificacion;

  /*
  *Nombre del ítem de tipificación
  */
  private String descNoItemTipificacion;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String descEsVigenteTipificacion;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String descFlVigenteItem;

  /*
  *Nombre del tipo de norma
  */
  private String descNoTipoNorma;

  /*
  *Ubicación del item de norma
  */
  private String descUbicacionItem;

  /*
  *Número o código de la norma de tipificación
  */
  private String descCoNormaTipificacion;

  /*
  *Tipo de norma
  */
  private Long descIdTipoNorma;

  /*
  *Tipo division del item
  */
  private Long descIdDivisionItem;

  /*
  *Nombre de la norma legal
  */
  private String descNoNorma;

  /*
  *DESC_NU_HIJOS
  */
  private Long descNuHijos;
  
  /*
  * Auxiliar para indicar la acción de actualización que se realizará en el objeto de trabajo, sobre una obligación normativa.
  */
  private String descAccionActualiza; 

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  private Long idNormaItemPadre;

  private Long idNormaItem;

}