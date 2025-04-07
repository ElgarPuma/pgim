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
* Clase Entidad para la tabla PGIM_TD_RANKING_UM_FACTOR: 
* @descripción: Factor por unidad minera y su valoración de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_RANKING_UM_FACTOR")
@Data
@NoArgsConstructor
public class PgimRankingUmFactor implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la valoración del factor de riesgo sobre la UM. Secuencia: PGIM_SEQ_RANKING_UM_FACTOR
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RANKING_UM_FACTOR")
   @SequenceGenerator(name = "PGIM_SEQ_RANKING_UM_FACTOR", sequenceName = "PGIM_SEQ_RANKING_UM_FACTOR", allocationSize = 1)
   @Column(name = "ID_RANKING_UM_FACTOR", nullable = false)
  private Long idRankingUmFactor;

  /*
  *Identificador interno de la unidad minera asociada al ranking. Tabla padre: PGIM_TD_RANKING_UM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_UM_GRUPO", nullable = false)
  private PgimRankingUmGrupo pgimRankingUmGrupo;

  /*
  *Identificador interno del factor de riesgo para el ranking. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FACTOR_RIESGO", nullable = false)
  private PgimFactorRiesgo pgimFactorRiesgo;

  /*
  *Identificador interno de la calificación del nivel de riesgo por factor. Tabla padre: PGIM_TM_NIVEL_RIESGO_FACTOR
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CFG_NIVEL_RIESGO", nullable = true)
  private PgimCfgNivelRiesgo pgimCfgNivelRiesgo;

  /*
  *Valor obtenido de PGIM_TM_NIVEL_RIESGO.NU_VALOR seleccionado
  */
   @Column(name = "MO_CALIFICACION", nullable = true)
  private BigDecimal moCalificacion;

  /*
  *Valor obtenido del producto de el valor de calificación seleccionado (PGIM_TM_NIVEL_RIESGO.NU_VALOR) y el vector resultante para el factor en cuestión (PGIM_TM_CFG_FACTOR_RIESGO.NU_VECTOR_RESULTANTE)
  */
   @Column(name = "MO_PUNTAJE", nullable = true)
  private BigDecimal moPuntaje;

  /*
  *Comentario de la calificación dada a través del nivel de riesgo
  */
   @Column(name = "CM_CALIFICACION", nullable = true)
  private String cmCalificacion;

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
  *Orden de prioridad del factor
  */
 @Transient
  private Long descNuOrdenPrioridad;

  /*
  *Nombre del factor de riesgo
  */
 @Transient
  private String descNoFactor;

  /*
  *Descripción del factor de riesgo
  */
 @Transient
  private String descDeFactor;

  /*
  *Especificación del nivel de riesgo del factor
  */
 @Transient
  private String descDeEspecificacion;

  /*
  *Nombre del origen del factror
  */
 @Transient
  private String descOrigen;

  /*
  *Identificador interno del factor de riesgo. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
 @Transient
  private Long descIdCfgFactorRiesgo;

  /*
  *Promedio de las ponderaciones PGIM_TM_CFG_MATRIZ_PARES.NU_MCPN
  */
 @Transient
  private BigDecimal descNuVectorResultante;

  /*
  *L_PGIM_CFG_NIVEL_RIESGO
  */
 @Transient
  private List<PgimCfgNivelRiesgo> lPgimCfgNivelRiesgo;


}