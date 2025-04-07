package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_EVENTO: 
* @descripción: Evento ocurrido tal como un accidente mortal o incidente peligroso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEventoDTO {

  /*
  *Identificador interno del evento minero. Secuencia: PGIM_SEQ_EVENTO
  */
  private Long idEvento;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Tipo del evento minero asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_EVENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoEvento;

  /*
  *Agente causante del evento minero (tipo accidente) asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = AGENTE_CAUSANTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre:PGIM_TP_VALOR_PARAMETRO
  */
  private Long idAgenteCausante;

  /*
  *Tipo de accidente mortal asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACCIDENTE_MORTAL. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoAccidenteMortal;

  /*
  *Tipo de incidente peligroso asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_INCIDENTE_PELIGROSO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoIncidentePeligro;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Código del evento
  */
  private String coEvento;

  /*
  *Fecha de presentación del evento
  */
  private Date fePresentacion;

  /*
  *Fecha de presentación del evento
  */
  private String fePresentacionDesc;

  /*
  *Fecha de ocurrencia del evento
  */
  private Date feEvento;

  /*
  *Fecha de ocurrencia del evento
  */
  private String feEventoDesc;

  /*
  *Descripción del evento
  */
  private String deEvento;

  /*
  *Lugar del evento
  */
  private String deLugarEvento;

  /*
  *Valor lógico que señala si se va a contabilizar o no el evento para el análisis estadístico posterior de los datos. Los posibles valores son: "1" = No contabilizar y "0" = Contabilizar
  */
  private String esNoContabilizar;

  /*
  *Descripción del motivo por el que se señala que no se va a contabilizar
  */
  private String deMotivoNocontabilizar;

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
  *Nombre del tipo de evento
  */
  private String descIdTipoEvento;

  /*
  *Identificador interno del agente supervisado
  */
  private Long descIdAgenteSupervisado;

  /*
  *Número del expediente Siged asociado al evento
  */
  private String nuExpedienteSiged;

  /*
  *DESC_NO_TIPO_ACCIDENTE_MORTAL
  */
  private String descNoTipoAccidenteMortal;

  /*
  *DESC_NO_TIPO_INCIDENTE_PELIGRO
  */
  private String descNoTipoIncidentePeligro;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}