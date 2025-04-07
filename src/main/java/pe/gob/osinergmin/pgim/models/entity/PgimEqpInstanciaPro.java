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

import java.util.List;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TC_EQP_INSTANCIA_PRO: 
* @descripción: Equipo de la instancia del proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_EQP_INSTANCIA_PRO")
@Data
@NoArgsConstructor
public class PgimEqpInstanciaPro implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del equipo de la instancia del proceso. Secuencia: PGIM_SEQ_EQP_INSTANCIA_PRO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EQP_INSTANCIA_PRO")
   @SequenceGenerator(name = "PGIM_SEQ_EQP_INSTANCIA_PRO", sequenceName = "PGIM_SEQ_EQP_INSTANCIA_PRO", allocationSize = 1)
   @Column(name = "ID_EQUIPO_INSTANCIA_PRO", nullable = false)
  private Long idEquipoInstanciaPro;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = false)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno del rol del proceso PGIM. Tabla padre PGIM_TM_ROL_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ROL_PROCESO", nullable = false)
  private PgimRolProceso pgimRolProceso;

  /*
  *Identificador interno del personal del contrato. Tabla padre: PGIM_TD_PERSONAL_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONAL_CONTRATO", nullable = true)
  private PgimPersonalContrato pgimPersonalContrato;

  /*
  *Identificador interno del personal de Osinergmin. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONAL_OSI", nullable = true)
  private PgimPersonalOsi pgimPersonalOsi;

  /*
  *Flag que indica si el personal del equipo es o no responsable
  */
   @Column(name = "FL_ES_RESPONSABLE", nullable = true)
  private String flEsResponsable;

  /*
  *Nombre del perfil asignado a la persona del equipo. Para las personas del Osinergmin no se debe colocar valor alguno. Este valor se copiará desde la PGIM_TD_PERSONAL_CONTRATO.NO_PERFIL_EN_CONTRATO
  */
   @Column(name = "NO_PERFIL_PERSONA_EQUIPO", nullable = true)
  private String noPerfilPersonaEquipo;

  /*
  *Nombre del prefijo para asignar al personal del equipo. Este valor se copiará desde la columna PGIM_TM_PERSONA.NO_PREFIJO_PERSONA y aplica tanto para el personal del Osinergmin como para el personal de la empresa supervisora
  */
   @Column(name = "NO_PREFIJO_PERSONA_EQUIPO", nullable = true)
  private String noPrefijoPersonaEquipo;

  /*
  *Cargo de la persona en el equipo, este es el cargo que funge la persona en el equipo de la fiscalización. Para el caso del personal de la empresa supervisora, se debe obtener desde la columna PGIM_TD_PERSONAL_CONTRATO.NO_CARGO_EN_CONTRATO y, para el caso del personal del Osinergmin, debe ir el valor NULL
  */
   @Column(name = "NO_CARGO_PERSONA_EQUIPO", nullable = true)
  private String noCargoPersonaEquipo;

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
  *Identificador interno del personal de Osinergmin. Tabla padre: PGIM_TC_PERSONAL_OSI/PGIM_TD_PERSONAL_CONTRATO
  */
 @Transient
  private Long descIdPersona;

  /*
  *Nombre del usuario Windows
  */
 @Transient
  private String descNoUsuario;

  /*
  *Identificador interno del documento de identidad
  */
 @Transient
  private Long descIdTipoDocIdentidad;

  /*
  *Nombre del tipo de documento de identidad
  */
 @Transient
  private String descNoTipoDocIdentidad;

  /*
  *Código del documento de identidad
  */
 @Transient
  private String descCoDocumentoIdentidad;

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
  *Apellido materno de la persona
  */
 @Transient
  private String descApMaterno;

  /*
  *Nombre completo de la persona
  */
 @Transient
  private String descNoCompletoPersona;

  /*
  *Nombre del rol en el proceso
  */
 @Transient
  private String descNoRolProceso;

  /*
  *Nombre completo de la personal asociada al personal del Osinergmin
  */
 @Transient
  private String descNombreOsinergmin;

  /*
  *Nombre completo de la personal asociada al personal del contrato
  */
 @Transient
  private String descNombreContrato;

  /*
  *Descriptivo que señala si se trata de un personal del Osinergmin o un personal de la empresa supervisora
  */
 @Transient
  private String descDeOrigen;

  /*
  *Listado del personal del contrato
  */
 @Transient
  private List<PgimPersonalContrato> auxListaPersonalContrato;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
 @Transient
  private Long descIdPersonal;

  /*
  *Descripción corta del nombre de la persona con fines de codificar el nombre del documento Tabla padre: PGIM_TM_PERSONA
  */
 @Transient
  private String descNoPersonaDoc;

  /*
  *Nombre original del archivo
  */
 @Transient
  private String descNoOriginalArchivo;


}