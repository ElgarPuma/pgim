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
* Clase Entidad para la tabla PGIM_TC_PERSONAL_OSI: 
* @descripción: Personal de Osinergmin
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_PERSONAL_OSI")
@Data
@NoArgsConstructor
public class PgimPersonalOsi implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del personal de Osinergmin. Secuencia: PGIM_SEQ_PERSONAL_OSI
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PERSONAL_OSI")
   @SequenceGenerator(name = "PGIM_SEQ_PERSONAL_OSI", sequenceName = "PGIM_SEQ_PERSONAL_OSI", allocationSize = 1)
   @Column(name = "ID_PERSONAL_OSI", nullable = false)
  private Long idPersonalOsi;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Nombre Windows de usuario. Este dato se utilizará para obtener el identificador interno del usuario Siged.
  */
   @Column(name = "NO_USUARIO", nullable = false)
  private String noUsuario;

  /*
  *Identificador interno del usuario Siged.
  */
   @Column(name = "CO_USUARIO_SIGED", nullable = true)
  private Long coUsuarioSiged;

  /*
  *Indicador lógico que determina si la persona del Osinergmin se encuentra activa o no en el marco de las asignaciones de pasos de flujos de trabajo
  */
   @Column(name = "FL_ACTIVO", nullable = false)
  private String flActivo;

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
  *Nombre completo del personal de Osinergmin
  */
 @Transient
  private String descPersonaCompleto;

  /*
  *Nombre del personal de Osinergmin
  */
 @Transient
  private String descNoPersona;

  /*
  *Apellido paterno del personal de Osinergmin
  */
 @Transient
  private String descApPaterno;

  /*
  *Apellido materno del personal de Osinergmin
  */
 @Transient
  private String descApMaterno;

  /*
  *Número de documento de identidad del personal de Osinergmin
  */
 @Transient
  private String descCoDocumentoIdentidad;

  /*
  *Tipo documento de identidad del personal de Osinergmin
  */
 @Transient
  private String descTipoDocumentoIdentidad;

  /*
  *Correo del personal de Osinergmin
  */
 @Transient
  private String descDeCorreo;

  /*
  *Número de teléfono del personal de Osinergmin
  */
 @Transient
  private String descDeTelefono;

  /*
  *DESC_NO_PREFIJO_PERSONA
  */
 @Transient
  private String descNoPrefijoPersona;

  /*
  *Sexo de la persona
  */
 @Transient
  private String descTiSexo;


}