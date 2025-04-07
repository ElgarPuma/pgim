package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TC_INSTANCIA_PASO: 
* @descripción: Instancia de paso de procedimiento administrativo sancionador
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInstanciaPasoDTO {

  /*
  *Identificador interno de la instancia de paso del proceso. Secuencia: PGIM_SEQ_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long idRelacionPaso;

  /*
  *Identificador interno de la persona origen del equipo de supervisión. Table padre: PGIM_TC_EQUIPO_SUPERV
  */
  private Long idPersonaEqpOrigen;

  /*
  *Identificador interno de la persona destino del equipo de supervisión. Table padre: PGIM_TC_EQUIPO_SUPERV
  */
  private Long idPersonaEqpDestino;

  /*
  *Identificador interno de la instancia de paso origen. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPasoPadre;

  /*
  *Identificador interno de la persona del equipo de supervisión que solicita una aprobación. Table padre: PGIM_TC_EQP_INSTANCIA_PRO
  */
  private Long idPersonaEqpSolic;

  /*
  *Identificador interno del paso de proceso origen. Table padre: PGIM_TM_PASO_PROCESO
  */
  private Long idPasoProcesoOrigen;

  /*
  *Identificador interno del paso de proceso destino. Table padre: PGIM_TM_PASO_PROCESO
  */
  private Long idPasoProcesoDestino;

  /*
  *Fecha de la instancia en la que se registra la transición de pasos
  */
  private Date feInstanciaPaso;

  /*
  *Fecha de la instancia en la que se registra la transición de pasos
  */
  private String feInstanciaPasoDesc;

  /*
  *Mensaje de la instancia de paso del proceso
  */
  private String deMensaje;

  /*
  *Flag de lectura o revisión de la alerta. Los posibles valores son: "1" = Leído y "0" = No leído
  */
  private String flLeido;

  /*
  *Fecha de lectura
  */
  private Date feLectura;

  /*
  *Fecha de lectura
  */
  private String feLecturaDesc;
  
  /*
  *Flag que indica si es el último paso dado en la instancia de proceso. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flEsPasoActivo;
  
  /*
  *Tipo de subflujo que tiene un valor cuando la instancia de paso ha sido creada a través de una asignación paralela (tarea agrupadora), vale decir que cuando es NULL indica que ha sido creada a través de una asignación convencional. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUBFLUJO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idTipoSubflujo;

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
  *Identificador interno del personal de Osinergmin origen. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
  private Long descIdPersonalOsiOrigen;

  /*
  *Identificador interno del personal del contrato origen. Tabla padre: PGIM_TD_PERSONAL_CONTRATO
  */
  private Long descIdPersonalContratoOrigen;

  /*
  *Identificador interno del personal de Osinergmin destino. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
  private Long descIdPersonalOsiDestino;

  /*
  *Identificador interno del personal del contrato destino. Tabla padre: PGIM_TD_PERSONAL_CONTRATO
  */
  private Long descIdPersonalContratoDestino;

  /*
  *Identificador interno de la especialidad
  */
  private Long descIdEspecialidad;

  /*
  *Lista de documento seleccionados para asociación
  */
  private List<PgimDocumentoDTO> descListIdDocumentos;

  /*
  *Lista de instancias de paso para la asignación paralela
  */
  private List<PgimInstanciaPasoDTO> descListInstanciaPaso;

  /*
  *Flag que indica si la tarea es agrupadora esto es, que incluye asignación paralela de otras tareas. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String descTareaFlAgrupadoraDestino;  

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * Nombre del paso
   */
  private String descNoPasoProceso;


}