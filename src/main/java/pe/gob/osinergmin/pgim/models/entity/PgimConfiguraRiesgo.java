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
* Clase Entidad para la tabla PGIM_TM_CONFIGURA_RIESGO: 
* @descripción: Configuración de la metodología de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_CONFIGURA_RIESGO")
@Data
@NoArgsConstructor
public class PgimConfiguraRiesgo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la configuración del riesgo. Secuencia: PGIM_SEQ_CONFIGURA_RIESGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONFIGURA_RIESGO")
   @SequenceGenerator(name = "PGIM_SEQ_CONFIGURA_RIESGO", sequenceName = "PGIM_SEQ_CONFIGURA_RIESGO", allocationSize = 1)
   @Column(name = "ID_CONFIGURA_RIESGO", nullable = false)
  private Long idConfiguraRiesgo;

  /*
  *Identificador interno de la configuración de riesgo del que fue copiada la configuración. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONFIGURA_RIESGO_PADRE", nullable = true)
  private PgimConfiguraRiesgo configuraRiesgoPadre;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = false)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *Tipo de estado de configuración. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADO_CONFIGURACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ESTADO_CONFIGURACION", nullable = false)
  private PgimValorParametro tipoEstadoConfiguracion;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Tipo de periodo que ubica temporalmente y con mejor precisión a la configuración de riesgo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_PERIODO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_PERIODO", nullable = false)
  private PgimValorParametro tipoPeriodo;

  /*
  *Tipo de configuración de riesgo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CONFIGURACION_RIESGO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_CONFIGURACION_RIESGO", nullable = false)
  private PgimValorParametro tipoConfiguracionRiesgo;

  /*
  *Año del periodo de ubicación en el tiempo de la configuración de riesgo
  */
   @Column(name = "NU_ANIO_PERIODO", nullable = false)
  private Long nuAnioPeriodo;

  /*
  *Nombre de la configuración de riesgo
  */
   @Column(name = "NO_CONFIGURACION", nullable = false)
  private String noConfiguracion;

  /*
  *Descripción de la configuración de riesgo
  */
   @Column(name = "DE_CONFIGURACION", nullable = true)
  private String deConfiguracion;

  /*
  *Fecha referente de la creación de la configuración
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CONFIGURACION", nullable = false)
  private Date feConfiguracion;

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
  *Nombre de la especialidad
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *Nombre valor parametro del tipo de estado
  */
 @Transient
  private String descNoValorParametro;

  /*
  *Año de la configuración de la metodología
  */
 @Transient
  private String descFeConfiguracionAnio;

  /*
  *Flag de mis asignaciones
  */
 @Transient
  private String descFlagMisAsignaciones;

  /*
  *Identificador interno de la relación del paso
  */
 @Transient
  private Long descIdRelacionPaso;

  /*
  *Identificador interno del personal del Osinergmin
  */
 @Transient
  private Long descIdPersonalOsi;

  /*
  *Mensaje de la instancia de paso del proceso
  */
 @Transient
  private String descDeMensaje;

  /*
  *Nombre de la fase actual del proceso
  */
 @Transient
  private String descNoFaseActual;

  /*
  *Mensaje de la instancia de paso del proceso
  */
 @Transient
  private String descNoPasoActual;

  /*
  *Mensaje de la instancia de paso del proceso
  */
 @Transient
  private String descNoPersonaAsignada;

  /*
  *Usuario (Windows) de la persona responsable
  */
 @Transient
  private String descUsuarioAsignado;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
 @Transient
  private Long descIdFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
 @Transient
  private Long descIdPasoActual;

  /*
  *Id utilizado para poblar la matriz de pares
  */
 @Transient
  private Long descIdCfgGrupoRiesgoTemp;

  /*
  *Código clave del tipo de configuración de riesgo
  */
 @Transient
  private String descCoTipoConfiguracionRiesgo;

  /*
  *Nombre del tipo de configuraciòn de riesgo
  */
 @Transient
  private String descNoTipoConfiguracionRiesgo;

  /*
  *Código del tipo de periodo
  */
 @Transient
  private String descCoTipoPeriodo;

  /*
  *Nombre del tipo de periodo
  */
 @Transient
  private String descNoTipoPeriodo;

  /*
  *Nombre de la configuración de riesgo padre
  */
 @Transient
  private String descNoConfiguracionPadre;


}