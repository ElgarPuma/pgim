package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_RELACION_SUBCAT: 
* @descripción: Subcategoría de documento por relación
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRelacionSubcatDTO {

  /*
  *Identificador interno de la relación y la subcategoría de documentos. Secuencia: PGIM_SEQ_RELACION_SUBCAT
  */
  private Long idRelacionSubcat;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
  private Long idRelacionPaso;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre PGIM_SEQ_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocumento;

  /*
  *Flag que indica si la relación inicia el proceso. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flSubcatDocRequerido;
  
  /*
  *Flag que indica si se requiere registrar la fecha de envío del documento que esté siendo asignado por primera vez en la transición definida por la relación (ID_RELACION_PASO). Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flRegistrarFechaEnvio;

  /*
  *Flag que indica si se requiere actualizar la fecha de envío del documento que esté siendo asignado en la transición definida por la relación (ID_RELACION_PASO). Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flActualizarFechaEnvio;

  /*
  *Flag que indica si se requiere al menos un documento de las sub-categorías de documentos asociados a la relación. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flAlMenosUno;

  /*
  *Flag que indica si se debe validar, al momento de realizar la transición, la existencia de una reserva de numeración en el documento de la subcategoría documental. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flValidaReservaActiva;   

  /*
  *Flag que indica si, en determinada transición, se debe validar que se haya notificado a todos los responsables de las infracciones; vale decir, que todos ellos tengan registrado al menos 1 constancia/cargo de notificación electrónica/física en la tabla PGIM_TD_DESTINATARIO_DOC, en el marco de una fiscalización/PAS con motivo Accidente mortal. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flValidarNotifResponsable;
  
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
  *Código de la subcategoría de documento
  */
  private String descCoSubcatDocumento;

  /*
  *Nombre de la subcategoría de documento
  */
  private String descNoSubcatDocumento;

  /*
  *Código de la categoría de documento
  */
  private String descCoCategoriaDocumento;

  /*
  *Nombre de la categoría de documento
  */
  private String descNoCategoriaDocumento;

  /*
  *Identificador interno de la especialidad asociada con la sub-categoría del documento
  */
  private Long descIdEspecialidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * Me permite concatenar las propiedaddes del documento
   */
  private String nombreDocCadena;
}