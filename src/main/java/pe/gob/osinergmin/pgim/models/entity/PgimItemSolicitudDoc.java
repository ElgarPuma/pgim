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
* Clase Entidad para la tabla PGIM_TD_ITEM_SOLICITUD_DOC: 
* @descripción: Ítem de la solicitud de la documentación
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_ITEM_SOLICITUD_DOC")
@Data
@NoArgsConstructor
public class PgimItemSolicitudDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ítem de la solicitud de documentación. Secuencia: PGIM_SEQ_ITEM_SOLICITUD_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ITEM_SOLICITUD_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_ITEM_SOLICITUD_DOC", sequenceName = "PGIM_SEQ_ITEM_SOLICITUD_DOC", allocationSize = 1)
   @Column(name = "ID_ITEM_SOLICITUD_DOC", nullable = false)
  private Long idItemSolicitudDoc;

  /*
  *Identificador interno de la solicitud de documentación. Tabla padre: PGIM_TC_SOLICITUD_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SOLICITUD_DOC", nullable = false)
  private PgimSolicitudDoc pgimSolicitudDoc;

  /*
  *Fecha y hora de la solicitud de documentación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_SOLICITUD_DOCUMENTACION", nullable = false)
  private Date feSolicitudDocumentacion;

  /*
  *Observación de la solicitud de documentación
  */
   @Column(name = "DE_SOLICITUD_OBSERVACION", nullable = false)
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
  *Estado de la recepción de la documentación requerida. Los posibles valores son: "1" = Recibido y "0" = No recibido
  */
   @Column(name = "ES_RECIBIDO", nullable = false)
  private String esRecibido;

  /*
  *Orden de presentación de la solicitud de la documentación. Inicialmente este dato proviene del respectivo registro, columna PGIM_TM_ITEM_SOL_BASE_DOC.NU_ORDEN.
  */
   @Column(name = "NU_ORDEN", nullable = false)
  private Long nuOrden;

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
  *Identificador interno de la supervisión
  */
 @Transient
  private Long descIdSupervision;

  /*
  *Nombre de la especialidad
  */
 @Transient
  private String descNoEspecialidad;


}