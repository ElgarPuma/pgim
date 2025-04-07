package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_SOLICITUD_DOC: 
* @descripción: Solicitud de documentación
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSolicitudDocDTO {

  /*
  *Identificador interno de la solicitud de documentación. Secuencia: PGIM_SEQ_SOLICITUD_DOC
  */
  private Long idSolicitudDoc;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

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
  *Identificador interno del ítem de solicitud del documento
  */
  private Long descIdItemSolicitudDoc;

  /*
  *Fecha de la solicitud de la documentación
  */
  private Date descFeSolicitudDocumentacion;

  /*
  *Fecha de la solicitud de la documentación
  */
  private String descFeSolicitudDocumentacionDesc;

  /*
  *Observación de la solicitud de documentación
  */
  private String descDeSolicitudObservacion;

  /*
  *Fecha de la recepción de la documentación
  */
  private Date descFeRecepcionDocumentacion;

  /*
  *Fecha de la recepción de la documentación
  */
  private String descFeRecepcionDocumentacionDesc;

  /*
  *Observación de la recepción de documentación
  */
  private String descDeRecepcionObservacion;

  /*
  *Es recibido
  */
  private String descEsRecibido;

  /*
  *Descripción del tipo de supervisión
  */
  private String descTipoSupervision;

  /*
  *Descripción de la especialidad
  */
  private String descNoEspecialidad;

  /*
  *Fecha real de inicio de la supervisión
  */
  private Date descFeInicioSupervisionReal;

  /*
  *Fecha real de inicio de la supervisión
  */
  private String descFeInicioSupervisionRealDesc;

  /*
  *Fecha real fin de la supervisión
  */
  private Date descFeFinSupervisionReal;

  /*
  *Fecha real fin de la supervisión
  */
  private String descFeFinSupervisionRealDesc;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}