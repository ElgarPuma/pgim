package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_SOLICITUD_BASE_DOC: 
* @descripción: Solicitud de documentación plantilla base
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSolicitudBaseDocDTO {

  /*
  *Identificador interno de la solicitud base (plantilla) de documentación. Secuencia: PGIM_SEQ_SOLICITUD_BASE_DOC
  */
  private Long idSolicitudBaseDoc;

  /*
  *Identificador interno de la instancia del proceso PGIM. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la configuración base para los datos en el marco de la fiscalización. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
  private Long idConfiguracionBase;

  /*
  *Nombre de la solicitud base de de documentos (Nombre de la plantilla)
  */
  private String noSolicitudBaseDoc;

  /*
  *Descripción de la solicitud base de de documentos (Nombre de la plantilla)
  */
  private String deSolicitudBaseDoc;

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
  *DESC_NO_CONFIGURACION_BASE
  */
  private String descNoConfiguracionBase;

  /*
  *Nombre de la división supervisora
  */
  private String descNoDivisionSupervisora;

  /*
  *Nombre del subtipo de supervisión
  */
  private String descNoSubtipoSupervision;

  /*
  *Identificador interno de la especialidad
  */
  private Long descIdEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String descNoEspecialidad;

  /*
  *Identificador del tipo de supervisión
  */
  private Long descIdTipoSupervision;

  /*
  *Nombre del tipo de supervisión
  */
  private String descNoTipoSupervision;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}