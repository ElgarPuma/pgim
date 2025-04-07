package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_RELACIONSC_NOTIF: 
* @descripción: Subcategorías relacionadas para notificación. Identificador interno de la subcategoría de documento que se puede utilizar para notificar electrónicamente y la respectiva constancia de notificación electrónica a modo de subcategoría de documento 
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRelacionscNotifDTO {

  /*
  *Identificador interno de la configuración de qué documento, susceptible de ser notificado electrónicamente, genera qué subcategoría de constancia de dicha notificación. Secuencia: PGIM_SEQ_RELACIONSC_NOTIF
  */
  private Long idRelacionscNotif;

  /*
  *Identificador interno de la categoría de documento constancia. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocConstancia;

  /*
  *Identificador interno de la categoría de documento notificable electrónicamente. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocNotificable;
  
  /*
   *Identificador del tipo de notificación. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_NOTIFICACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
   */
  private Long idTipoNotificacion; 

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

  /**
   * Id de la subcategoria del documento
   */
  private Long idSubcatDocumento;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * Id de la instancia del proceso
   */
  private Long idInstanciaProceso;

  /**
   * Número de expediente del Siged
   */
  private String nuExpedienteSiged;

  /**
   * Id de la instancia del paso
   */
  private Long idInstanciaPaso;
}