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

import java.util.List;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_UNIDAD_MINERA: 
* @descripción: Unidad minera a supervisar
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_UNIDAD_MINERA")
@Data
@NoArgsConstructor
public class PgimUnidadMinera implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la unidad minera. Secuencia: PGIM_SEQ_UNIDAD_MINERA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_UNIDAD_MINERA")
   @SequenceGenerator(name = "PGIM_SEQ_UNIDAD_MINERA", sequenceName = "PGIM_SEQ_UNIDAD_MINERA", allocationSize = 1)
   @Column(name = "ID_UNIDAD_MINERA", nullable = false)
  private Long idUnidadMinera;

  /*
  *Identificador interno del agente supervisado. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_AGENTE_SUPERVISADO", nullable = false)
  private PgimAgenteSupervisado pgimAgenteSupervisado;

  /*
  *Identificador interno del método de explotación de las unidades mineras. Tabla padre: PGIM_TM_MTDO_EXPLOTACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_METODO_EXPLOTACION", nullable = true)
  private PgimMtdoExplotacion pgimMtdoExplotacion;

  /*
  *Identificador interno de otra unidad minera que funge de plata de beneficio destino para la unidad minera en cuestión. Cuando la propiedad del registro <<Tipo de UM>> tenga alguno de los valores siguientes: <<1. UEA>>; <<2.1.3 Concesión minera>>; o <<2.2 Acumulación>>; entonces esta columna podrá tener algún valor. Ahora bien, la lista de códigos de UM factibles de utilizarse para actualizar esta columna deben ser aquellas cuyo tipo es <<2.1.1 Concesión de beneficio>>. Table padre: PGIM_TM_UNIDAD_MINERA.  Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PLNTA_BENEFICIO_DESTINO", nullable = true)
  private PgimUnidadMinera plntaBeneficioDestino;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *Situación de la unidad minera, de acuerdo con lo establecido por el Minem. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = SITUACION_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SITUACION", nullable = true)
  private PgimValorParametro situacion;

  /*
  *Tipo de actividad de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTIVIDAD. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = true)
  private PgimValorParametro tipoActividad;

  /*
  *Subtipo de actividad
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBACTIVIDAD", nullable = true)
  private PgimSubactividad pgimSubactividad;

  /*
  *Estado de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = ESTADO_UM. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESTADO_UM", nullable = true)
  private PgimValorParametro estadoUm;

  /*
  *Tipo de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_UNIDAD_MINERA", nullable = true)
  private PgimValorParametro tipoUnidadMinera;

  /*
  *Tipo de yacimiento de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_YACIMIENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_YACIMIENTO", nullable = true)
  private PgimValorParametro tipoYacimiento;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUSTANCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_SUSTANCIA", nullable = true)
  private PgimValorParametro tipoSustancia;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUSTANCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_METODO_MINADO", nullable = true)
  private PgimValorParametro metodoMinado;

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
  *Código del alias de anonimización
  */
   @Column(name = "CO_ANONIMIZACION", nullable = true)
  private String coAnonimizacion;

  /*
  *Fecha del inicio de la titularidad del agente supervisado
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_TITULARIDAD", nullable = true)
  private Date feInicioTitularidad;

  /*
  *Capacidad instalada de planta expresada en TM/d. Aplica solo cuando el <<Tipo de Unidad Minera>> sea igual a <<Concesión de beneficio>>
  */
   @Column(name = "NU_CPCDAD_INSTLDA_PLANTA", nullable = true)
  private BigDecimal nuCpcdadInstldaPlanta;

  /*
  *Indicador lógico que señala la existencia o no de indicios de cámara subterránea de gas en las operaciones. Aplica solo cuando la columna <<CO_METODO_MINADO>> tenga el valor que representa a <<Subterráneo>>. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_CMRA_SUBTRRANEA_GAS", nullable = true)
  private String flCmraSubtrraneaGas;

  /*
  *Profundidad expresada en metros (m)
  */
   @Column(name = "NU_PROFUNDIDAD", nullable = true)
  private BigDecimal nuProfundidad;

  /*
  *Altura mínima expresada en metros sobre el nivel del mar (msnm)
  */
   @Column(name = "NU_ALTURA_MINIMA", nullable = true)
  private BigDecimal nuAlturaMinima;

  /*
  *Altura máxima expresada en metros sobre el nivel del mar (msnm)
  */
   @Column(name = "NU_ALTURA_MAXIMA", nullable = true)
  private BigDecimal nuAlturaMaxima;

  /*
  *Ubicación, acceso y antecedentes de la unidad minera
  */
   @Column(name = "DE_UBICACION_ACCESO", nullable = true)
  private String deUbicacionAcceso;

  /*
  *Indicador lógico que señala si el registro de datos de riesgo será o no mandatorio en el contexto de una fiscalización. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_REGISTRA_RIESGOS", nullable = false)
  private String flRegistraRiesgos;

  /*
  *Indicador lógico que señala si el registro de la UM tiene componentes de tipo Piques. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_CON_PIQUES", nullable = true)
  private String flConPiques;

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
  *Número de documento de identidad
  */
 @Transient
  private String descCoDocumentoIdentidad;

  /*
  *Razón social de la persona jurídica
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Nombre del código de la actividad de la unidad minera
  */
 @Transient
  private String descIdTipoActividad;

  /*
  *Nombre del código de división supervisora de la unidad minera
  */
 @Transient
  private String descIdDivisonSupervisora;

  /*
  *Nombre del código de la situación de la unidad minera
  */
 @Transient
  private String descIdTipoSituacion;

  /*
  *Nombre del código del tipo de la unidad minera
  */
 @Transient
  private String descIdTipoUnidadMinera;

  /*
  *Nombre de la planta de beneficio destino asociado a la unidad minera.
  */
 @Transient
  private String descIdPlntaBeneficioDestino;

  /*
  *Lista de sustancias seleccionadas de la unidad minera
  */
 @Transient
  private List<PgimSustancia> auxSustanciasUm;

  /*
  *Descripcion del ubigeo
  */
 @Transient
  private String descUbigeo;

  /*
  *DESC_NO_ESTRATO
  */
 @Transient
  private String descNoEstrato;

  /*
  *DESC_NO_DIVISION_SUPERVISORA
  */
 @Transient
  private String descNoDivisionSupervisora;

  /*
  *DESC_NO_TIPO_UNIDAD_MINERA
  */
 @Transient
  private String descNoTipoUnidadMinera;

  /*
  *DESC_NO_SITUACION
  */
 @Transient
  private String descNoSituacion;

  /*
  *DESC_NO_PLNTA_BENEFICIO_DESTINO
  */
 @Transient
  private String descNoPlntaBeneficioDestino;

  /*
  *DESC_NO_TIPO_MINADO
  */
 @Transient
  private String descNoTipoMinado;

  /*
  *DESC_NO_METODO_EXPLOTACION
  */
 @Transient
  private String descNoMetodoExplotacion;

  /*
  *DESC_NO_TIPO_YACIMIENTO
  */
 @Transient
  private String descNoTipoYacimiento;

  /*
  *DESC_NO_TIPO_SUSTANCIA
  */
 @Transient
  private String descNoTipoSustancia;

  /*
  *DESC_BASE64_IMG
  */
 @Transient
  private String descBase64Img;

  /*
  *DESC_NO_METODO_MINADO
  */
 @Transient
  private String descNoMetodoMinado;

  /*
  *DESC_UBIGEO_DEMARCACION
  */
 @Transient
  private String descUbigeoDemarcacion;

  /*
  *DESC_FL_PRINCIPAL_DEMARCACION
  */
 @Transient
  private String descFlPrincipalDemarcacion;

  /*
  *DESC_PORCENTAJE_DEMARCACION
  */
 @Transient
  private BigDecimal descPorcentajeDemarcacion;

  /*
  *DESC_NU_CANTIDAD_PIQUES
  */
 @Transient
  private Long descNuCantPiques;

  /*
  *DESC_NO_ESTADO_UM
  */
 @Transient
  private String descNoEstadoUm;


}