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
* Clase Entidad para la tabla PGIM_TM_EMPRESA_SUPERVISORA: 
* @descripción: Empresa supervisora
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_EMPRESA_SUPERVISORA")
@Data
@NoArgsConstructor
public class PgimEmpresaSupervisora implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la empresa supervisora. Secuencia: PGIM_SEQ_EMPRESA_SUPERVISORA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EMPRESA_SUPERVISORA")
   @SequenceGenerator(name = "PGIM_SEQ_EMPRESA_SUPERVISORA", sequenceName = "PGIM_SEQ_EMPRESA_SUPERVISORA", allocationSize = 1)
   @Column(name = "ID_EMPRESA_SUPERVISORA", nullable = false)
  private Long idEmpresaSupervisora;

  /*
  *Identificador interno de la persona. Tabla padre: PGIM_TM_PERSONA
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

  /*
  *Principal actividad económica
  */
   @Column(name = "DE_PRINCIPAL_ACTIVIDAD_ECNMCA", nullable = true)
  private String dePrincipalActividadEcnmca;

  /*
  *Número de documento de identidad de la empresa supervisora
  */
 @Transient
  private String descCoDocumentoIdentidad;

  /*
  *Razón social de la empresa supervisora
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Teléfono de la empresa supervisora
  */
 @Transient
  private String descDeTelefono;

  /*
  *Teléfono 02 de la empresa supervisora
  */
 @Transient
  private String descDeTelefono2;

  /*
  *Correo electrónico de la empresa supervisora
  */
 @Transient
  private String descDeCorreo;

  /*
  *Correo electrónico 02 de la empresa supervisora
  */
 @Transient
  private String descDeCorreo2;

  /*
  *Número de contratos vigentes
  */
 @Transient
  private Long descNuContratoVigente;


}