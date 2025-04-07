package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VC_PERSONACON_AUX: 
* @descripción: Vista para obtener el personal de las empresas supervisoras
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VC_PERSONACON_AUX")
@Data
@NoArgsConstructor
public class PgimPersonaconAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista personal contrato AUX:  Secuencia: PGIM_SEQ_XYZ
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_XYZ")
   @SequenceGenerator(name = "PGIM_SEQ_XYZ", sequenceName = "PGIM_SEQ_XYZ", allocationSize = 1)
   @Column(name = "ID_PERSONAL_CON_AUX", nullable = false)
  private Long idPersonalConAux;

  /*
  *Identificador interno del personal del contrato. Tabla padre: PGIM_TD_PERSONAL_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONAL_CONTRATO", nullable = true)
  private PgimPersonalContrato pgimPersonalContrato;

  /*
  *Identificador interno de la persona. Tabla padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = true)
  private PgimPersona pgimPersona;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = true)
  private PgimContrato pgimContrato;

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


}