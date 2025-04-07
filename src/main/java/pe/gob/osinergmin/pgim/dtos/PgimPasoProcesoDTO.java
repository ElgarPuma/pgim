package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_PASO_PROCESO: 
* @descripción: Paso de proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPasoProcesoDTO {

  /*
  *Identificador interno del paso de proceso PGIM. Secuencia: PGIM_SEQ_PASO_PROCESO
  */
  private Long idPasoProceso;

  /*
  *Identificador interno de la fase del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
  private Long idFaseProceso;

  /*
  *Identificador interno del rol del proceso PGIM. Tabla padre PGIM_TM_ROL_PROCESO
  */
  private Long idRolProceso;

  /*
  *Código del paso del proceso
  */
  private String coPasoProceso;

  /*
  *Nombre del paso del proceso
  */
  private String noPasoProceso;

  /*
  *Descripción del paso del proceso
  */
  private String dePasoProceso;

  /*
  *Flag que indica si desde el paso se pueden notificar documentos de manera electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flNotificableE;

  /*
  *Flag que indica si la tarea es agrupadora esto es, que incluye asignación paralela de otras tareas. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flAgrupadora;
  
  /*
   *Código del subproceso en caso exista
   */
   private String coSubproceso;
   
   /*
    *Código que señala si el paso, en el caso que sea un paso de subproceso, es el inicio del subproceso "I" o el fin del subproceso "F"; en cualquier otro caso el valor es nulo
    */
   private String coTipoPasoSubproceso;

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
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
  *Flag que indica si desde el paso se pueden etiquetar documentos para notificar de manera electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flEtiquetarNotificacion;

  /*
   * Identificador de instancia de paso
   */
  private Long descInstanciaPaso;

  /*
   * Identificador de proceso
   */
  private Long descIdProceso;

  /**
   * descriptor del nombre de la fase 
   */
  private String descNoFase;

}