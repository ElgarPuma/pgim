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
* Clase Entidad para la tabla PGIM_TD_RANKING_UM_GRUPO: 
* @descripción: Grupo del ranking de riesgo asociado a una unidad minera
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_RANKING_UM_GRUPO")
@Data
@NoArgsConstructor
public class PgimRankingUmGrupo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la unidad minera dentro del ranking. Secuencia: PGIM_SEQ_RANKING_UM_GRUPO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RANKING_UM_GRUPO")
   @SequenceGenerator(name = "PGIM_SEQ_RANKING_UM_GRUPO", sequenceName = "PGIM_SEQ_RANKING_UM_GRUPO", allocationSize = 1)
   @Column(name = "ID_RANKING_UM_GRUPO", nullable = false)
  private Long idRankingUmGrupo;

  /*
  *Identificador interno del ranking de riesgo. Tabla padre: PGIM_TC_RANKING_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_UM", nullable = true)
  private PgimRankingUm pgimRankingUm;

  /*
  *Identificador interno de la valoración de riesgo sobre la UM en una supervisión. Tabla padre: PGIM_TD_RANKING_SUPERVISION. Si tiene valor entonces se trata de una calificación de riesgo dada al factor en el marco de una supervisión.
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_SUPERVISION", nullable = true)
  private PgimRankingSupervision pgimRankingSupervision;

  /*
  *Identififador interno del grupo de riesgo de la configuración asociada al ranking
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CFG_GRUPO_RIESGO", nullable = false)
  private PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo;

  /*
  *Valor obtenido de la sumatoria de los puntajes obtenidos en todos los factores del grupo (PGIM_TD_RANKING_UM_FACTOR.MO_PUNTAJE)
  */
   @Column(name = "MO_PUNTAJE", nullable = true)
  private BigDecimal moPuntaje;

  /*
  *Indicador lógico que señala si se debe o no registrar los datos de riesgo. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_REGISTRAR", nullable = false)
  private String flRegistrar;

  /*
  *Valor alfa para el grupo técnico, valor beta (1 - alfa) para el grupo de gestión. Para calcular alfa, se debe contar la cantidad de factores técnicos de la configuración marcados con PGIM_TM_CFG_FACTOR_RIESGO.FL_AFECTADO_GESTION = "1" entre el total de factores técnicos.
  */
   @Column(name = "NU_CALIFICACION_GRUPO", nullable = true)
  private BigDecimal nuCalificacionGrupo;

  /*
  *Indicador lógico que señala si se el puntaje del grupo es el máximo o no. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_MAXIMO", nullable = false)
  private String flMaximo;

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
  *Identificador interno del ranking de riesgo. Tabla padre: PGIM_TC_RANKING_RIESGO
  */
 @Transient
  private Long descIdRankingRiesgo;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descNoUnidadMinera;

  /*
  *Identificador interno de la configuración del grupo.
  */
 @Transient
  private Long descIdGrupo;

  /*
  *Nombre del tipo de grupo
  */
 @Transient
  private String descNoGrupo;

  /*
  *Descripción del tipo de grupo
  */
 @Transient
  private String descDeGrupo;

  /*
  *Identificador interno de la configuración del grupo.
  */
 @Transient
  private Long descIdGrupoRiesgo;

  /*
  *Identificador de la fase de proceso
  */
 @Transient
  private Long descIdFaseProceso;

  /*
  *Número de factores pendientes
  */
 @Transient
  private Integer descNroPendiente;

  /*
  *Nombre del grupo de riesgo de la configuración
  */
 @Transient
  private String descNoGrupoRiesgo;

  /*
  *Descripción del grupo de riesgo de la configuración
  */
 @Transient
  private String descDeGrupoRiesgo;

  /*
  *Factor de correción aplicado al valor de riesgo obtenido para el grupo de riesgo
  */
 @Transient
  private BigDecimal descPcFactorCorreccion;

  /*
  *Identificador interno de la configuración de riesgo
  */
 @Transient
  private Long descIdConfiguraRiesgo;

  /*
  *Nombre de la configuración de riesgo
  */
 @Transient
  private String descNoConfiguracion;


}