package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_RELACION_SUBCAT: 
* @descripción: Subcategoría de documento por relación
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_RELACION_SUBCAT")
@Data
@NoArgsConstructor
public class PgimRelacionSubcat implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la relación y la subcategoría de documentos. Secuencia: PGIM_SEQ_RELACION_SUBCAT
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RELACION_SUBCAT")
   @SequenceGenerator(name = "PGIM_SEQ_RELACION_SUBCAT", sequenceName = "PGIM_SEQ_RELACION_SUBCAT", allocationSize = 1)
   @Column(name = "ID_RELACION_SUBCAT", nullable = false)
  private Long idRelacionSubcat;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RELACION_PASO", nullable = false)
  private PgimRelacionPaso pgimRelacionPaso;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre PGIM_SEQ_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOCUMENTO", nullable = false)
  private PgimSubcategoriaDoc pgimSubcategoriaDoc;

  /*
  *Flag que indica si la relación inicia el proceso. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_SUBCAT_DOC_REQUERIDO", nullable = false)
  private String flSubcatDocRequerido;

  /*
  *Flag que indica si se requiere registrar la fecha de envío del documento que esté siendo asignado por primera vez en la transición definida por la relación (ID_RELACION_PASO). Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_REGISTRAR_FECHA_ENVIO", nullable = false)
  private String flRegistrarFechaEnvio;
     
  /*
  *Flag que indica si se requiere actualizar la fecha de envío del documento que esté siendo asignado en la transición definida por la relación (ID_RELACION_PASO). Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_ACTUALIZAR_FECHA_ENVIO", nullable = false)
  private String flActualizarFechaEnvio;

  /*
  *Flag que indica si se requiere al menos un documento de las sub-categorías de documentos asociados a la relación. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_AL_MENOS_UNO", nullable = true)
  private String flAlMenosUno; 

  /*
  *Flag que indica si se debe validar, al momento de realizar la transición, la existencia de una reserva de numeración en el documento de la subcategoría documental. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_VALIDA_RESERVA_ACTIVA", nullable = true)
  private String flValidaReservaActiva; 
  
  /*
  *Flag que indica si, en determinada transición, se debe validar que se haya notificado a todos los responsables de las infracciones; vale decir, que todos ellos tengan registrado al menos 1 constancia/cargo de notificación electrónica/física en la tabla PGIM_TD_DESTINATARIO_DOC, en el marco de una fiscalización/PAS con motivo Accidente mortal. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_VALIDAR_NOTIF_RESPONSABLE", nullable = false)
  private String flValidarNotifResponsable;
  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Usuario creador
  */
   @Column(name = "US_CREACION", nullable = false)
  private String usCreacion;

  /*
  *Terminal de creación
  */
   @Column(name = "IP_CREACION", nullable = false)
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = false)
  private Date feCreacion;

  /*
  *Usuario modificador
  */
   @Column(name = "US_ACTUALIZACION", nullable = true)
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
   @Column(name = "IP_ACTUALIZACION", nullable = true)
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ACTUALIZACION", nullable = true)
  private Date feActualizacion;

  /*
  *Código de la subcategoría de documento
  */
 @Transient
  private String descCoSubcatDocumento;

  /*
  *Nombre de la subcategoría de documento
  */
 @Transient
  private String descNoSubcatDocumento;

  /*
  *Código de la categoría de documento
  */
 @Transient
  private String descCoCategoriaDocumento;

  /*
  *Nombre de la categoría de documento
  */
 @Transient
  private String descNoCategoriaDocumento;

  /*
  *Identificador interno de la especialidad asociada con la sub-categoría del documento
  */
 @Transient
  private Long descIdEspecialidad;


}