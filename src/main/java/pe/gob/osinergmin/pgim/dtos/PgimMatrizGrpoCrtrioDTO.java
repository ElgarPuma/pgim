package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_MATRIZ_GRPO_CRTRIO: 
* @descripción: Grupo de criterio de matriz de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMatrizGrpoCrtrioDTO {

  /*
  *Identificador interno del grupo de criterio de la matriz de supervisión. Secuencia: PGIM_SEQ_MATRIZ_GRPO_CRTRIO
  */
  private Long idMatrizGrpoCrtrio;

  /*
  *Identificador interno de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_SUPERVISION
  */
  private Long idMatrizSupervision;

  /*
  *Código del grupo de criterio de la matriz de supervisión
  */
  private String coMatrizGrpoCrtrio;

  /*
  *Nombre del grupo de criterio de la matriz de supervisión
  */
  private String noMatrizGrpoCrtrio;

  /*
  *Número de orden en la presentación
  */
  private Long nuOrden;

  /*
  *Flag que indica si el grupo de criterio es visible. Los posibles valores son: "1" = Visible y "0" = No visible
  */
  private String esVisible;

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
  *Cantidadde criterios registrados en el grupo de la matriz
  */
  private Long descCantidadCriterios;

  /*
  *Nombre de la especialidad
  */
  private String descEspecialidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}