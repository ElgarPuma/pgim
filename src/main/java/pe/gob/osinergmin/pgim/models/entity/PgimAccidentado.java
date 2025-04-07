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
* Clase Entidad para la tabla PGIM_TM_ACCIDENTADO: 
* @descripción: Persona accidentada en un evento dado
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_ACCIDENTADO")
@Data
@NoArgsConstructor
public class PgimAccidentado implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del mapeo del accidentado. Secuencia: PGIM_SEQ_ACCIDENTADO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ACCIDENTADO")
   @SequenceGenerator(name = "PGIM_SEQ_ACCIDENTADO", sequenceName = "PGIM_SEQ_ACCIDENTADO", allocationSize = 1)
   @Column(name = "ID_ACCIDENTADO", nullable = false)
  private Long idAccidentado;

  /*
  *Identificador interno del evento por empresa. Tabla padre: PGIM_TM_EMPRESA_EVENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_EMPRESA_EVENTO", nullable = false)
  private PgimEmpresaEvento pgimEmpresaEvento;

  /*
  *Identificador interno de la persona accidentada. Tabla padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Categoría ocupacional del accidentado. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = CATEGORIA_OCUPACIONAL. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CATEGORIA_OCUPACIONAL", nullable = false)
  private PgimValorParametro categoriaOcupacional;

  /*
  *Fecha del fallecimiento del accidentado
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FALLECIMIENTO", nullable = true)
  private Date feFallecimiento;

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
  *Identificador del tipo de documento de identidad del accidentado
  */
 @Transient
  private Long descAccidentadoIdTipoDi;

  /*
  *Tipo de documento de identidad del accidentado
  */
 @Transient
  private String descAccidentadoTipoDi;

  /*
  *Número de DNI o carne de extranjería de la persona accidentada
  */
 @Transient
  private String descAccidentadoDnice;

  /*
  *Nombre de la persona accidentada
  */
 @Transient
  private String descAccidentadoNombres;

  /*
  *Apellido paterno de la persona accidentada
  */
 @Transient
  private String descAccidentadoApPaterno;

  /*
  *Apellido materno de la persona accidentada
  */
 @Transient
  private String descAccidentadoApMaterno;

  /*
  *Fecha de nacimiento de la persona accidentada
  */
 @Transient
  private Date descAccidentadoFeNacimiento;

  /*
  *Domicilio de la persona accidentada
  */
 @Transient
  private String descAccidentadoDomicilio;

  /*
  *Sexo de la persona accidentada
  */
 @Transient
  private String descAccidentadoSexo;

  /*
  *Identificador del ubigeo de la persona accidentada
  */
 @Transient
  private Long descAccidentadoIdUbigeo;

  /*
  *Descripción del ubigeo de la persona accidentada
  */
 @Transient
  private String descAccidentadoUbigeo;

  /*
  *Teléfono de la persona accidentada
  */
 @Transient
  private String descAccidentadoTelefono;

  /*
  *Lista de seguros de la persona accidentada
  */
 @Transient
  private List<PgimValorParametro> auxAccidentadoSeguros;

  /*
  *Identificador interno de la empleadora de la persona accidentada
  */
 @Transient
  private Long descAccidentadoIdEmpresa;

  /*
  *Tipo de involucramiento de la persona jurídica correspondiente a la empresa del evento
  */
 @Transient
  private String descEmpresaTipo;

  /*
  *Número de RUC de la persona jurídica correspondiente a la empresa del evento
  */
 @Transient
  private String descEmpresaRuc;

  /*
  *Razón social de la persona jurídica correspondiente a la empresa del evento
  */
 @Transient
  private String descEmpresaRazonSocial;


}