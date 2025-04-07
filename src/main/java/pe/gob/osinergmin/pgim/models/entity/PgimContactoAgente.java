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
* Clase Entidad para la tabla PGIM_TM_CONTACTO_AGENTE: 
* @descripción: Contacto del agente supervisado
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_CONTACTO_AGENTE")
@Data
@NoArgsConstructor
public class PgimContactoAgente implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la persona contacto del agente supervisado. Secuencia: PGIM_SEQ_CONTACTO_AGENTE
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONTACTO_AGENTE")
   @SequenceGenerator(name = "PGIM_SEQ_CONTACTO_AGENTE", sequenceName = "PGIM_SEQ_CONTACTO_AGENTE", allocationSize = 1)
   @Column(name = "ID_CONTACTO_AGENTE", nullable = false)
  private Long idContactoAgente;

  /*
  *Identificador interno de la persona contacto del agente supervisado. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_AGENTE_SUPERVISADO", nullable = false)
  private PgimAgenteSupervisado pgimAgenteSupervisado;

  /*
  *Identificador interno de la persona contacto. Table padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Nombre del cargo del contacto del agente fiscalizado
  */
   @Column(name = "NO_CARGO", nullable = false)
  private String noCargo;

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
  *Nombre de la persona
  */
 @Transient
  private String descNoPersona;

  /*
  *Apellido paterno de la persona
  */
 @Transient
  private String descApPaterno;

  /*
  *Apellido materno de la persona natural
  */
 @Transient
  private String descApMaterno;

  /*
  *Teléfono
  */
 @Transient
  private String descDeTelefono;

  /*
  *Correo electrónico
  */
 @Transient
  private String descDeCorreo;

  /*
  *Razón social de la persona jurídica
  */
 @Transient
  private String descNoRazonSocial;


}