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
* Clase Entidad para la tabla PGIM_TD_PERSONAL_CONTRATO: 
* @descripción: Personal del contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_PERSONAL_CONTRATO")
@Data
@NoArgsConstructor
public class PgimPersonalContrato implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del personal del contrato. Secuencia: PGIM_SEQ_PERSONAL_CONTRATO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PERSONAL_CONTRATO")
   @SequenceGenerator(name = "PGIM_SEQ_PERSONAL_CONTRATO", sequenceName = "PGIM_SEQ_PERSONAL_CONTRATO", allocationSize = 1)
   @Column(name = "ID_PERSONAL_CONTRATO", nullable = false)
  private Long idPersonalContrato;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = false)
  private PgimContrato pgimContrato;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Identificador interno del rol del proceso PGIM. Solo aplica para roles de la fiscalización. Tabla padre PGIM_TM_ROL_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ROL_PROCESO", nullable = true)
  private PgimRolProceso pgimRolProceso;

  /*
  *División supervisora a la que atiende el personal. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro pgimValorParametro;

  /*
  *Nombre Windows de usuario. Este dato se utilizará para obtener el identificador interno del usuario Siged.
  */
   @Column(name = "NO_USUARIO", nullable = false)
  private String noUsuario;

  /*
  *Identificador interno del usuario Siged.
  */
   @Column(name = "CO_USUARIO_SIGED", nullable = false)
  private Long coUsuarioSiged;

  /*
  *Perfil de la persona en el contrato. Este dato se registra a partir del perfil definido en el contrato
  */
   @Column(name = "NO_PERFIL_EN_CONTRATO", nullable = true)
  private String noPerfilEnContrato;

  /*
  *Cargo de la persona en el contrato, este es el cargo que funge la persona en el marco del contrato
  */
   @Column(name = "NO_CARGO_EN_CONTRATO", nullable = true)
  private String noCargoEnContrato;

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
  *Nombre completo de la persona
  */
 @Transient
  private String descPersonaCompleto;

  /*
  *Flag que indica si el contrato se encuentra vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
 @Transient
  private String descFlEstado;

  /*
  *DNI o CE de la persona
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
  *Identificador del tipo de documento de identidad de la persona
  */
 @Transient
  private Long descIdTipoDocIdentidad;

  /*
  *Tipo de documento de identidad de la persona
  */
 @Transient
  private String descTipoDocIdentidad;

  /*
  *Identificador del ubigeo de la persona
  */
 @Transient
  private Long descIdUbigeo;

  /*
  *Descripcion del ubigeo
  */
 @Transient
  private String descUbigeo;

  /*
  *DESC_NO_PREFIJO_PERSONA
  */
 @Transient
  private String descNoPrefijoPersona;

  /*
  *Nombre del rol asociado al proceso de negocio
  */
 @Transient
  private String descNoRolProceso;


}