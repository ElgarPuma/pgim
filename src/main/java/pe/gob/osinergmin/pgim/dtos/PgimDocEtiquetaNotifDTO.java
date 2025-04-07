package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_TD_DOC_ETIQUETA_NOTIF: 
* @descripción: Documentos etiquetados para notificación
*
* @author: LEEGION
* @version: 1.0
* @fecha_de_creación: 06/02/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimDocEtiquetaNotifDTO {

  /*
  *Identificador interno del documento etiqueta notifica. Secuencia: PGIM_SEQ_DOC_ETIQUETA_NOTIF
  */
  private Long idDocEtiquetaNotif;

  /*
  *Identificador interno del documento etiquetado. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumentoEtiquetado;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Flag que indica si desde el paso se pueden etiquetar documentos para notificar de manera electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flEtiquetaNotifActiva;

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
    
    
}
