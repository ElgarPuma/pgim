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
* Clase Entidad para la tabla PGIM_TC_SOLICITUD_DOC: 
* @descripción: Solicitud de documentación
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_SOLICITUD_DOC")
@Data
@NoArgsConstructor
public class PgimSolicitudDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la solicitud de documentación. Secuencia: PGIM_SEQ_SOLICITUD_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SOLICITUD_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_SOLICITUD_DOC", sequenceName = "PGIM_SEQ_SOLICITUD_DOC", allocationSize = 1)
   @Column(name = "ID_SOLICITUD_DOC", nullable = false)
  private Long idSolicitudDoc;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;

  /*
  *Fecha y hora de la solicitud de documentación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_SOLICITUD_DOCUMENTACION", nullable = false)
  private Date feSolicitudDocumentacion;

  /*
  *Observación de la solicitud de documentación
  */
   @Column(name = "DE_SOLICITUD_OBSERVACION", nullable = true)
  private String deSolicitudObservacion;

  /*
  *Fecha y hora de la recepción de documentación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_RECEPCION_DOCUMENTACION", nullable = true)
  private Date feRecepcionDocumentacion;

  /*
  *Observación de la recepción de documentación
  */
   @Column(name = "DE_RECEPCION_OBSERVACION", nullable = true)
  private String deRecepcionObservacion;

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
  *Identificador interno del ítem de solicitud del documento
  */
 @Transient
  private Long descIdItemSolicitudDoc;

  /*
  *Fecha de la solicitud de la documentación
  */
 @Transient
  private Date descFeSolicitudDocumentacion;

  /*
  *Observación de la solicitud de documentación
  */
 @Transient
  private String descDeSolicitudObservacion;

  /*
  *Fecha de la recepción de la documentación
  */
 @Transient
  private Date descFeRecepcionDocumentacion;

  /*
  *Observación de la recepción de documentación
  */
 @Transient
  private String descDeRecepcionObservacion;

  /*
  *Es recibido
  */
 @Transient
  private String descEsRecibido;

  /*
  *Descripción del tipo de supervisión
  */
 @Transient
  private String descTipoSupervision;

  /*
  *Descripción de la especialidad
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *Fecha real de inicio de la supervisión
  */
 @Transient
  private Date descFeInicioSupervisionReal;

  /*
  *Fecha real fin de la supervisión
  */
 @Transient
  private Date descFeFinSupervisionReal;


}