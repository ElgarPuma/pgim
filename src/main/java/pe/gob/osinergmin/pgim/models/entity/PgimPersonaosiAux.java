package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VC_PERSONAOSI_AUX: 
* @descripción: Vista para obtener el personal del Osinergmin
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VC_PERSONAOSI_AUX")
@Data
@NoArgsConstructor
public class PgimPersonaosiAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del personal Osinergmin AUX. Secuencia: PGIM_SEQ_PERSONAL_OSI
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PERSONAL_OSI")
   @SequenceGenerator(name = "PGIM_SEQ_PERSONAL_OSI", sequenceName = "PGIM_SEQ_PERSONAL_OSI", allocationSize = 1)
   @Column(name = "ID_PERSONAL_OSI_AUX", nullable = false)
  private Long idPersonalOsiAux;

  /*
  *Identificador interno de la supervisión
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONAL_OSI", nullable = true)
  private PgimPersonalOsi pgimPersonalOsi;

  /*
  *Identificador interno de la persona
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = true)
  private PgimPersona pgimPersona;

  /*
  *Nombre del usuario Windows
  */
   @Column(name = "NO_USUARIO", nullable = true)
  private String noUsuario;

  /*
  *Identificador interno del documento de identidad
  */
   @Column(name = "ID_TIPO_DOC_IDENTIDAD", nullable = true)
  private Long idTipoDocIdentidad;

  /*
  *Nombre del tipo de documento de identidad
  */
   @Column(name = "NO_TIPO_DOC_IDENTIDAD", nullable = true)
  private String noTipoDocIdentidad;

  /*
  *Código del documento de identidad
  */
   @Column(name = "CO_DOCUMENTO_IDENTIDAD", nullable = true)
  private String coDocumentoIdentidad;

  /*
  *Nombre de la persona
  */
   @Column(name = "NO_PERSONA", nullable = true)
  private String noPersona;

  /*
  *Apellido paterno de la persona
  */
   @Column(name = "AP_PATERNO", nullable = true)
  private String apPaterno;

  /*
  *Apellido materno de la persona
  */
   @Column(name = "AP_MATERNO", nullable = true)
  private String apMaterno;

  /*
  *Nombre completo de la persona
  */
   @Column(name = "NO_COMPLETO_PERSONA", nullable = true)
  private String noCompletoPersona;

  /*
  *Nombre del rol en el proceso
  */
 @Transient
  private String descNoRolProceso;


}