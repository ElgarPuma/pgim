package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_MATRIZ_CRITERIO: 
* @descripción: Criterio de matriz de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMatrizCriterioDTO {

  /*
  *Identificador interno del criterio de la matriz de supervisión. Secuencia: PGIM_SEQ_MATRIZ_CRITERIO
  */
  private Long idMatrizCriterio;

  /*
  *Identificador interno del grupo de criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_GRPO_CRTRIO
  */
  private Long idMatrizGrpoCrtrio;

  /*
  *Código del criterio de la matriz de supervisión
  */
  private String coMatrizCriterio;

  /*
  *Descripción del criterio de la matriz de supervisión
  */
  private String deMatrizCriterio;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
  private String deBaseLegal;

  /*
  *Número de orden en la presentación
  */
  private Long nuOrden;

  /*
  *Copia por defecto. Señala, cuando es "1", que el criterio se copiará por defecto en la fiscalización relacionada con la matriz de verificación; por otro lado, cuando es "0", señala que el criterio no será copiado de manera automática.
  */
  private String flCopiaPorDefecto;

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
  *Identificador de la matriz de supervisión
  */
  private Long descIdMatrizSupervision;

  /*
  *Identificador de la matriz de supervisión
  */
  private String descCoMatrizGrpoCrtrio;

  /*
  *Descripción del nombre del grupo de criterio de la matriz
  */
  private String descNoMatrizGrpoCrtrio;

  /*
  *Número de obligaciones registradas por criterio
  */
  private Long descNuObligaciones;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}