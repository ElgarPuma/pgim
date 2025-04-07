package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_INVOLUCRADO_SUPERV: 
* @descripción: Persona involucrada en la supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInvolucradoSupervDTO {

  /*
  *Identificador interno del involucrado en la supervisión. Secuencia: PGIM_SEQ_INVOLUCRADO_SUPERV
  */
  private Long idInvolucradoSuperv;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Tipo de involucramiento registrado para la persona sobre la supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTA_INVOLUCRADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoActaInvolucrado;

  /*
  *Tipo de involucramiento registrado para la persona sobre la supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_INVOLUCRADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoInvolucrado;

  /*
  *Tipo de prefijo para la persona involucrada en la supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_PREFIJO_INVOLUCRADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoPrefijoInvolucrado;

  /*
  *Identificador interno de la solicitud de documentación. Tabla padre: PGIM_TC_SOLICITUD_DOC
  */
  private Long idSolicitudDoc;

  /*
  *Descripción del cargo de la persona
  */
  private String deCargo;

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
  *Nombre completo de la persona
  */
  private String descPersonaCompleto;

  /*
  *DNI o CE de la persona
  */
  private String descCoDocumentoIdentidad;

  /*
  *Identificador interno del tipo de involucrado
  */
  private String descIdTipoInvolucrado;

  /*
  *Nombre del prefijo de la persona
  */
  private String descIdTipoPrefijoInvolucrado;

  /*
  *Nombre de la persona
  */
  private String descNoPersona;

  /*
  *Apellido paterno
  */
  private String descApPaterno;

  /*
  *Apellido materno
  */
  private String descApMaterno;

  /*
  *Entidad
  */
  private String descEntidad;

  /*
  *Tipo de documento de identidad
  */
  private String descTipoDocIdentidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}