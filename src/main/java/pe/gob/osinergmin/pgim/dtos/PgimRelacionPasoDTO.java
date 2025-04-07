package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_RELACION_PASO: 
* @descripción: Relación del paso del proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRelacionPasoDTO {

  /*
  *Identificador interno de la relación del paso del proceso. Secuencia: PGIM_SEQ_RELACION_PASO
  */
  private Long idRelacionPaso;

  /*
  *Identificador interno del paso origen del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
  private Long idPasoProcesoOrigen;

  /*
  *Identificador interno del paso destino del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
  private Long idPasoProcesoDestino;

  /*
  *Tipo de la relación del paso. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_RELACION_PASO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoRelacion;

  /*
  *Flag que indica si la relación inicia el proceso. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flIniciaProceso;

  /*
  *Flag que indica si para la transición al paso se requiere especificar destinatario. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flRequiereDestinatario;

  /*
  *Flag que indica si para la transición al paso se requiere aprobación. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flRequiereAprobacion;

  /*
  *Flag que indica si la transición se invocará a través de una tarea agrupada, es decir que no estará disponible para selección por parte del usuario al momento de asignar una tarea. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flRelacionAgrupada;

  /*
  *Tipo de acción SIGED. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_ACCION_SIGED. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idTipoAccionSiged;

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
  *Nombre del paso de origen
  */
  private String descNoPasoProcesoOrigen;

  /*
  *Descripción del paso de origen
  */
  private String descDePasoProcesoOrigen;

  /*
  *Nombre del paso de destino
  */
  private String descNoPasoProcesoDestino;

  /*
  *Descripción del paso de destino
  */
  private String descDePasoProcesoDestino;

  /*
  *Identificador interno del rol del proceso asociado al paso de origen
  */
  private Long descIdRolProcesoOrigen;

  /*
  *Nombre del rol del proceso asociado al paso de origen
  */
  private String descNoRolProcesoOrigen;

  /*
  *Identificador interno del rol del proceso asociado al paso de destino
  */
  private Long descIdRolProcesoDestino;

  /*
  *Nombre del rol del proceso asociado al paso de destino
  */
  private String descNoRolProcesoDestino;

  /*
  *Nombre del tipo de relación de paso
  */
  private String descNoTipoRelacion;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long descIdRelacionPaso;

  /*
  *Identificador interno del personal de Osinergmin. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
  private Long descIdPersonalOsi;

  /*
  *Mensaje de la instancia de paso del proceso
  */
  private String descDeMensaje;

  /*
  *Nombre del tipo de acción Siged
  */
  private String descTipoAccionSiged;

  /*
  *Flag que indica si la tarea es agrupadora esto es, que incluye asignación paralela de otras tareas. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String descTareaFlAgrupadoraDestino;  

  /**
   * Descriptor de identificador interno de proceso asociado al paso de origen
   */
  private Long descIdProcesoOrigen;

  /**
   * Descriptor de identificador interno de la fase de proceso asociado al paso de origen
   */
  private Long descIdFaseProcesoOrigen;

  /**
   * Descriptor de nombre de la fase del proceso asociado al paso de origen
   */
  private String descNoFaseProcesoOrigen;

  /**
   * Descriptor de identificador interno de proceso asociado al paso de destino
   */
  private Long descIdProcesoDestino;

  /**
   * Descriptor de identificador interno de la fase de proceso asociado al paso de destino
   */
  private Long descIdFaseProcesoDestino;

  /**
   * Descriptor de nombre de la fase del proceso asociado al paso de destino
   */
  private String descNoFaseProcesoDestino;
  

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}