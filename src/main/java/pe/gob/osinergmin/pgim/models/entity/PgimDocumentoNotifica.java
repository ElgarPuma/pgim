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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_DOCUMENTO_NOTIFICA: 
* @descripción: Registro de las notificaciones realizadas por documento
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_DOCUMENTO_NOTIFICA")
@Data
@NoArgsConstructor
public class PgimDocumentoNotifica implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del documento notificado. Secuencia: PGIM_SEQ_DOCUMENTO_NOTIFICA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DOCUMENTO_NOTIFICA")
   @SequenceGenerator(name = "PGIM_SEQ_DOCUMENTO_NOTIFICA", sequenceName = "PGIM_SEQ_DOCUMENTO_NOTIFICA", allocationSize = 1)
   @Column(name = "ID_DOCUMENTO_NOTIFICA", nullable = false)
  private Long idDocumentoNotifica;

  /*
  *Identificador interno del documento notificado. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO_NOTIFICADO", nullable = false)
  private PgimDocumento documentoNotificado;

  /*
  *Identificador interno del documento constancia del documento notificado. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO_CONSTANCIA", nullable = false)
  private PgimDocumento documentoConstancia;

  /*
  *Código de la notificación generada por el SNE
  */
   @Column(name = "CO_NOTIFICACION", nullable = false)
  private String coNotificacion;

  /*
  *Asunto de la notificación enviada a través del SNE
  */
   @Column(name = "DE_ASUNTO_NOTIFICACION", nullable = false)
  private String deAsuntoNotificacion;

  /*
  *Fecha y hora de creación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_NOTIFICACION", nullable = false)
  private Date feNotificacion;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo, "0" = Inactivo
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


}