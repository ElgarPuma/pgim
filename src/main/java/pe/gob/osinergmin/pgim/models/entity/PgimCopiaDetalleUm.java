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
* Clase Entidad para la tabla PGIM_TZ_COPIA_DETALLE_UM: 
* @descripción: Detalle de las copias realizadas por cada trabajo de copia
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TZ_COPIA_DETALLE_UM")
@Data
@NoArgsConstructor
public class PgimCopiaDetalleUm implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de cada una de las filas de las unidades mineras copiadas en la el trabajo de copia. Secuencia: PGIM_SEQ_COPIA_DETALLE_UM
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_COPIA_DETALLE_UM")
   @SequenceGenerator(name = "PGIM_SEQ_COPIA_DETALLE_UM", sequenceName = "PGIM_SEQ_COPIA_DETALLE_UM", allocationSize = 1)
   @Column(name = "ID_COPIA_DETALLE_UM", nullable = false)
  private Long idCopiaDetalleUm;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TZ_TRABAJO_COPIA_UM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TRABAJO_COPIA_UM", nullable = false)
  private PgimTrabajoCopiaUm pgimTrabajoCopiaUm;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Identificador interno del agente supervisado relacionado. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_AGENTE_SUPERVISADO", nullable = false)
  private PgimAgenteSupervisado pgimAgenteSupervisado;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = false)
  private PgimValorParametro divisionSupervisora;

  /*
  *Situación de la unidad minera, de acuerdo con lo establecido por el Minem. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = SITUACION_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SITUACION", nullable = false)
  private PgimValorParametro situacion;

  /*
  *Tipo de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_UNIDAD_MINERA", nullable = false)
  private PgimValorParametro tipoUnidadMinera;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUSTANCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_METODO_MINADO", nullable = true)
  private PgimValorParametro metodoMinado;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUSTANCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_SUSTANCIA", nullable = true)
  private PgimValorParametro tipoSustancia;

  /*
  *Tipo de actividad de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTIVIDAD. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = true)
  private PgimValorParametro tipoActividad;

  /*
  *RUC del agente supervisado
  */
   @Column(name = "RUC_AGENTE_SUPERVISADO", nullable = false)
  private String rucAgenteSupervisado;

  /*
  *Razón social del agente supervisado
  */
   @Column(name = "RAZON_SOCIAL_AS", nullable = false)
  private String razonSocialAs;

  /*
  *Código de la unidad minera que tiene estricta correspondencia con la codificación del Ingemmet
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = false)
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = false)
  private String noUnidadMinera;

  /*
  *Fecha de copia mensual. La copia se debe realizar a las 23:59 del último día del mes
  */
   @Column(name = "CO_ANONIMIZACION", nullable = true)
  private String coAnonimizacion;

  /*
  *Capacidad instalada de planta expresada en TM/d. Aplica solo cuando el <<Tipo de Unidad Minera>> sea igual a <<Concesión de beneficio>>
  */
   @Column(name = "NU_CPCDAD_INSTLDA_PLANTA", nullable = true)
  private BigDecimal nuCpcdadInstldaPlanta;

  /*
  *Código de la unidad minera que tiene estricta correspondencia con la codificación del Ingemmet y que se refiere a la planta beneficio destino
  */
   @Column(name = "CO_UNIDAD_MINERA_PLANTA", nullable = true)
  private String coUnidadMineraPlanta;

  /*
  *Nombre de la unidad minera de la planta de beneficio destino
  */
   @Column(name = "NO_UNIDAD_MINERA_PLANTA", nullable = true)
  private String noUnidadMineraPlanta;

  /*
  *Fecha del inicio de la titularidad del agente supervisado
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_TITULARIDAD", nullable = true)
  private Date feInicioTitularidad;

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
  *Nombre de la división supervisora
  */
 @Transient
  private String descNoDivisionSupervisora;

  /*
  *Nombre de la situación de la unidad minera
  */
 @Transient
  private String descNoSituacion;

  /*
  *Nombre del tipo de la unidad minera
  */
 @Transient
  private String descNoTipoUnidadMinera;

  /*
  *Nombre del método de minado
  */
 @Transient
  private String descNoMetodoMinado;

  /*
  *Nombre del tipo de sustancia
  */
 @Transient
  private String descNoTipoSustancia;

  /*
  *Nombre del tipo de actividad
  */
 @Transient
  private String descNoTipoActividad;


}