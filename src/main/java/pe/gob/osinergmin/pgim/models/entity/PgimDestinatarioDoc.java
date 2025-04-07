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
* Clase Entidad para la tabla PGIM_TD_DESTINATARIO_DOC: 
* @descripción: Destinatario por documento
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 25/04/2023
*/
@Entity
@Table(name = "PGIM_TD_DESTINATARIO_DOC")
@Data
@NoArgsConstructor
public class PgimDestinatarioDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del destinatario del documento. Secuencia: PGIM_SEQ_DESTINATARIO_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DESTINATARIO_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_DESTINATARIO_DOC", sequenceName = "PGIM_SEQ_DESTINATARIO_DOC", allocationSize = 1)
   @Column(name = "ID_DESTINATARIO_DOC", nullable = false)
  private Long idDestinatarioDoc;

  /*
  *Identificador interno del documento. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = false)
  private PgimDocumento pgimDocumento;

  /*
  *Identificador interno del documento que sirve como constancia de la notificación. Tabla padre: PGIM_TD_DOCUMENTO. Si tiene valor entonces ya se ha notificado, de lo contrario no
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO_CONSTANCIA", nullable = true)
  private PgimDocumento documentoConstancia;

  /*
  *Identificador interno de la persona destintaria del documento. Tabla padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

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


}