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

import java.math.BigDecimal;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_EMPRESA_EVENTO: 
* @descripción: Empresa involucrada en un evento
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_EMPRESA_EVENTO")
@Data
@NoArgsConstructor
public class PgimEmpresaEvento implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del mapeo de la empresa por evento. Secuencia: PGIM_SEQ_EMPRESA_EVENTO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EMPRESA_EVENTO")
   @SequenceGenerator(name = "PGIM_SEQ_EMPRESA_EVENTO", sequenceName = "PGIM_SEQ_EMPRESA_EVENTO", allocationSize = 1)
   @Column(name = "ID_EMPRESA_EVENTO", nullable = false)
  private Long idEmpresaEvento;

  /*
  *Identificador interno del evento. Tabla padre: PGIM_TM_EVENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_EVENTO", nullable = false)
  private PgimEvento pgimEvento;

  /*
  *Identificador interno de la persona. Tabla padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Agente causante del evento minero asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_EMPRESA_INVOLUCRADA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_EMPRESA_INVOLUCRADA", nullable = false)
  private PgimValorParametro tipoEmpresaInvolucrada;

  /*
  *Tipo de la actividad CIIU (Clasificación Industrial Internacional Uniforme). Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTVIDAD_CIIU. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACTVIDAD_CIIU", nullable = true)
  private PgimValorParametro tipoActvidadCiiu;

  /*
  *Número de trabajadores de sexo masculino
  */
   @Column(name = "NU_TRABAJADORES_M", nullable = false)
  private BigDecimal nuTrabajadoresM;

  /*
  *Número de trabajadores de sexo femenino
  */
   @Column(name = "NU_TRABAJADORES_F", nullable = false)
  private BigDecimal nuTrabajadoresF;

  /*
  *Descripción de la actividad económica principal
  */
   @Column(name = "DE_ACT_ECONOMICA_PRINCIPAL", nullable = true)
  private String deActEconomicaPrincipal;

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
  *Tipo de involucramiento de la empresa en el evento
  */
 @Transient
  private String descEmpresaTipo;

  /*
  *Número de RUC de la persona jurídica del evento
  */
 @Transient
  private String descEmpresaRuc;

  /*
  *Razón social de la persona jurídica del evento
  */
 @Transient
  private String descEmpresaRazonSocial;


}