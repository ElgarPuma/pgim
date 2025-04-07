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
* Clase Entidad para la tabla PGIM_TM_PERSONA_CONSORCIO: 
* @descripción: Configuración de las personas jurídicas que conforman un consorcio
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_PERSONA_CONSORCIO")
@Data
@NoArgsConstructor
public class PgimPersonaConsorcio implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la persona. Secuencia: PGIM_SEQ_PERSONA_CONSORCIO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PERSONA_CONSORCIO")
   @SequenceGenerator(name = "PGIM_SEQ_PERSONA_CONSORCIO", sequenceName = "PGIM_SEQ_PERSONA_CONSORCIO", allocationSize = 1)
   @Column(name = "ID_PERSONA_CONSORCIO", nullable = false)
  private Long idPersonaConsorcio;

  /*
  *Identificador interno de la persona jurídica que tiene el tipo consorcio. Se debe precisar que una persona jurídica consorcio no puede conformar parte de otro consorcio
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA_JURIDICA_CONSORCIO", nullable = false)
  private PgimPersona personaJuridicaConsorcio;

  /*
  *Identificador interno de la persona jurídica que no es consorcio pero que forma parte de uno. Se debe precisar que una persona jurídica puede conformar más de un consorcio
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Valor lógico que señala si la persona es principal o representa al consorcio. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_ES_PRINCIPAL", nullable = false)
  private String flEsPrincipal;

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
  *DESC_CO_DOCUMENTO_IDENTIDAD
  */
 @Transient
  private String descCoDocumentoIdentidad;

  /*
  *DESC_CO_DOCUMENTO_IDENTIDAD_CONSORCIO
  */
 @Transient
  private String descCoDocumentoIdentidadConsorcio;

  /*
  *DESC_NO_RAZON_SOCIAL
  */
 @Transient
  private String descNoRazonSocial;


}