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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_RANKING_FACTOR_UM: 
* @descripción: Factor por unidad minera y su valoración de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 16/02/2021
*/
@Entity
@Table(name = "PGIM_TD_RANKING_FACTOR_UM")
@Data
@NoArgsConstructor
public class PgimRankingFactorUm implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la valoración del factor de riesgo sobre la UM. Secuencia: PGIM_SEQ_RANKING_FACTOR_UM
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RANKING_FACTOR_UM")
   @SequenceGenerator(name = "PGIM_SEQ_RANKING_FACTOR_UM", sequenceName = "PGIM_SEQ_RANKING_FACTOR_UM", allocationSize = 1)
   @Column(name = "ID_RANKING_FACTOR_UM", nullable = false)
  private Long idRankingFactorUm;

  /*
  *Identificador interno de la unidad minera asociada al ranking. Tabla padre: PGIM_TD_RANKING_UM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_UM", nullable = false)
  private PgimRankingUm pgimRankingUm;

  /*
  *Identificador interno del ranking dado al factor de riesgo en el marco de una supervisión dada. Si tiene valor entonces se trata de una calificación de riesgo dada al factor en el marco de una supervisión. Tabla padre: PGIM_TD_RANKING_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_SUPERVISION", nullable = true)
  private PgimRankingSupervision pgimRankingSupervision;

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
   @JoinColumn(name = "ID_NIVEL_RIESGO_FACTOR", nullable = false)
  private PgimNivelRiesgoFactor pgimNivelRiesgoFactor;

  /*
  *Valor obtenido luego de multiplicar la valoración (PGIM_TM_NIVEL_RIESGO.NU_VALOR) por el valor del vector unitario de la metodología.
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


}