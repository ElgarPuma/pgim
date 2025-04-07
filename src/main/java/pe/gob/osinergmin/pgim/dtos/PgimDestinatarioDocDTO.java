package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO para la entidad PGIM_TD_DESTINATARIO_DOC:
 * 
 * @descripción: Destinatario por documento
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 25/04/2023
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimDestinatarioDocDTO {

  /*
   * Identificador interno del destinatario del documento. Secuencia:
   * PGIM_SEQ_DESTINATARIO_DOC
   */
  private Long idDestinatarioDoc;

  /*
   * Identificador interno del documento. Tabla padre: PGIM_TD_DOCUMENTO
   */
  private Long idDocumento;

  /*
   * Identificador interno del documento que sirve como constancia de la
   * notificación. Tabla padre: PGIM_TD_DOCUMENTO. Si tiene valor entonces ya se
   * ha notificado, de lo contrario no
   */
  private Long idDocumentoConstancia;

  /*
   * Identificador interno de la persona destintaria del documento. Tabla padre:
   * PGIM_TM_PERSONA
   */
  private Long idPersona;

  /*
   * Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
   */
  private String esRegistro;

  /*
   * Usuario creador
   */
  private String usCreacion;

  /*
   * Terminal de creación
   */
  private String ipCreacion;

  /*
   * Fecha y hora de creación
   */
  private Date feCreacion;

  /*
   * Fecha y hora de creación
   */
  private String feCreacionDesc;

  /*
   * Usuario modificador
   */
  private String usActualizacion;

  /*
   * Terminal de modificación
   */
  private String ipActualizacion;

  /*
   * Fecha y hora de modificación
   */
  private Date feActualizacion;

  /*
   * Fecha y hora de modificación
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

  /*
   * Nombre de la subcategoria del documento.
   */
  private String descNoSubcatDocumento;

  /*
   * Nombre de la subcategoria del documento de tipo constancia.
   */
  private String descNoSubcatDocumentoConstancia;

  /*
   * Nombre de la persona jurídica.
   */
  private String descNoRazonSocial;
  
  /*
   * RUC de la persona jurídica.
   */
  private String descCoDocumentoIdentidad;

  /**
   * Identificador del destinatario documento actual
   */
  private Long idDestinatarioDocActual;

  /**
   * Número de documento generado por el siged
   */
  private String numeroDocumento;

  /**
   * Número de expediente
   */
  private String nuExpedienteSiged;

  /**
   * Descripción del asunto del documento notificable
   */
  private String descDeAsuntoDocumento;
}