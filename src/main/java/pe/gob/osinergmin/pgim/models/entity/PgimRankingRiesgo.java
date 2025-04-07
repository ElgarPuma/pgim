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
* Clase Entidad para la tabla PGIM_TC_RANKING_RIESGO: 
* @descripción: Ranking de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_RANKING_RIESGO")
@Data
@NoArgsConstructor
public class PgimRankingRiesgo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ranking de riesgo. Secuencia: PGIM_SEQ_RANKING_RIESGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RANKING_RIESGO")
   @SequenceGenerator(name = "PGIM_SEQ_RANKING_RIESGO", sequenceName = "PGIM_SEQ_RANKING_RIESGO", allocationSize = 1)
   @Column(name = "ID_RANKING_RIESGO", nullable = false)
  private Long idRankingRiesgo;

  /*
  *Identificador interno de la configuración del riesgo. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONFIGURA_RIESGO", nullable = false)
  private PgimConfiguraRiesgo pgimConfiguraRiesgo;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Nombre del ranking de riesgo
  */
   @Column(name = "NO_RANKING", nullable = false)
  private String noRanking;

  /*
  *Descripción del ranking de riesgo
  */
   @Column(name = "DE_RANKING", nullable = true)
  private String deRanking;

  /*
  *Fecha de generación del ranking
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_GENERACION_RANKING", nullable = false)
  private Date feGeneracionRanking;

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
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *Nombre de la especialidad
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *Tipo de estado de configuración. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADO_CONFIGURACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
 @Transient
  private Long descIdTipoEstadoConfiguracion;

  /*
  *Tipo de estado de configuración.
  */
 @Transient
  private String descTipoEstadoConfiguracion;

  /*
  *Año de configuración del ranking
  */
 @Transient
  private String descFeGeneracionRankingAnio;

  /*
  *Nombre de la configuración de riesgo
  */
 @Transient
  private String descNoConfiguracion;

  /*
  *Descripción de la configuración de riesgo
  */
 @Transient
  private String descDeConfiguracion;

  /*
  *Fecha referente de la creación de la configuración
  */
 @Transient
  private Date descFeConfiguracion;

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
  *Nombre de la persona asignada al paso actual del fliujo del ranking de riesgo
  */
 @Transient
  private String descNoPersonaAsignada;

  /*
  *Usuario (Windows) de la persona responsable
  */
 @Transient
  private String usuarioAsignado;

  /*
  *Flag de mis asignaciones
  */
 @Transient
  private String descFlagMisAsignaciones;

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
  *DESC_CO_TIPO_CONFIGURACION_RIESGO
  */
 @Transient
  private String descCoTipoConfiguracionRiesgo;

  /*
  *DESC_NO_TIPO_CONFIGURACION_RIESGO
  */
 @Transient
  private String descNoTipoConfiguracionRiesgo;

  /*
  *DESC_NO_TIPO_PERIODO
  */
 @Transient
  private String descNoTipoPeriodo;

  /*
  *DESC_NU_ANIO_PERIODO
  */
 @Transient
  private Long descNuAnioPeriodo;

  /*
  *DESC_ID_TIPO_CONFIGURACION_RIESGO
  */
 @Transient
  private Long descIdTipoConfiguracionRiesgo;

  /*
  *DESC_ID_TIPO_PERIODO
  */
 @Transient
  private Long descIdTipoPeriodo;
  
  /*
   * NO_USUARIO_ORIGEN
   */
  @Transient
  private String descNoUsuarioOrigen;

  /*
   * FE_INSTANCIA_PASO
   */
  @Transient
  private Date descFeInstanciaPaso;

  /*
   * Mensaje de la instancia de paso del proceso
   */
  @Transient
  private String descDeMensaje;

  /*
   * FL_PASO_ACTIVO
   */
  @Transient
  private String DescFlPasoActivo;

}