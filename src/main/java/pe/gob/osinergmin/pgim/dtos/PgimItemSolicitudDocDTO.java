package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_ITEM_SOLICITUD_DOC: 
* @descripción: Ítem de la solicitud de la documentación
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimItemSolicitudDocDTO {

  /*
  *Identificador interno del ítem de la solicitud de documentación. Secuencia: PGIM_SEQ_ITEM_SOLICITUD_DOC
  */
  private Long idItemSolicitudDoc;

  /*
  *Identificador interno de la solicitud de documentación. Tabla padre: PGIM_TC_SOLICITUD_DOC
  */
  private Long idSolicitudDoc;

  /*
  *Fecha y hora de la solicitud de documentación
  */
  private Date feSolicitudDocumentacion;

  /*
  *Fecha y hora de la solicitud de documentación
  */
  private String feSolicitudDocumentacionDesc;

  /*
  *Observación de la solicitud de documentación
  */
  private String deSolicitudObservacion;

  /*
  *Fecha y hora de la recepción de documentación
  */
  private Date feRecepcionDocumentacion;

  /*
  *Fecha y hora de la recepción de documentación
  */
  private String feRecepcionDocumentacionDesc;

  /*
  *Observación de la recepción de documentación
  */
  private String deRecepcionObservacion;

  /*
  *Estado de la recepción de la documentación requerida. Los posibles valores son: "1" = Recibido y "0" = No recibido
  */
  private String esRecibido;

  /*
  *Orden de presentación de la solicitud de la documentación. Inicialmente este dato proviene del respectivo registro, columna PGIM_TM_ITEM_SOL_BASE_DOC.NU_ORDEN.
  */
  private Long nuOrden;

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
  *Identificador interno de la supervisión
  */
  private Long descIdSupervision;

  /*
  *Nombre de la especialidad
  */
  private String descNoEspecialidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}