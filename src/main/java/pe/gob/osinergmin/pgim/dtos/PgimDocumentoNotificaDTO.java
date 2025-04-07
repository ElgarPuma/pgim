package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_DOCUMENTO_NOTIFICA: 
* @descripción: Registro de las notificaciones realizadas por documento
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimDocumentoNotificaDTO {

  /*
  *Identificador interno del documento notificado. Secuencia: PGIM_SEQ_DOCUMENTO_NOTIFICA
  */
  private Long idDocumentoNotifica;

  /*
  *Identificador interno del documento notificado. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumentoNotificado;

  /*
  *Identificador interno del documento constancia del documento notificado. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumentoConstancia;

  /*
  *Código de la notificación generada por el SNE
  */
  private String coNotificacion;

  /*
  *Asunto de la notificación enviada a través del SNE
  */
  private String deAsuntoNotificacion;

  /*
  *Fecha y hora de creación
  */
  private Date feNotificacion;

  /*
  *Fecha y hora de creación
  */
  private String feNotificacionDesc;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo, "0" = Inactivo
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


}