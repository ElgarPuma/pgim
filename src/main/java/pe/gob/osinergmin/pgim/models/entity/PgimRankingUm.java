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
* Clase Entidad para la tabla PGIM_TD_RANKING_UM: 
* @descripción: Unidad minera asociada al ranking de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_RANKING_UM")
@Data
@NoArgsConstructor
public class PgimRankingUm implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la unidad minera dentro del ranking. Secuencia: PGIM_SEQ_RANKING_UM
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RANKING_UM")
   @SequenceGenerator(name = "PGIM_SEQ_RANKING_UM", sequenceName = "PGIM_SEQ_RANKING_UM", allocationSize = 1)
   @Column(name = "ID_RANKING_UM", nullable = false)
  private Long idRankingUm;

  /*
  *Identificador interno del ranking de riesgo. Tabla padre: PGIM_TC_RANKING_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_RIESGO", nullable = false)
  private PgimRankingRiesgo pgimRankingRiesgo;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Tipo de inclusión de la UM en el universo de unidades mineras del ranking. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = ID_TIPO_INCLUSION_RANKING. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_INCLUSION_RANKING", nullable = false)
  private PgimValorParametro tipoInclusionRanking;

  /*
  *Valor obtenido de la sumatoria de los puntajes técnicos y de gestión, esto es: Calificación final = alfa x FT + beta x FG, donde el valor de alfa y beta se encuentran registrados en la columna PGIM_TM_CFG_GRUPO_RIESGO.NU_CALIFICACION_GRUPO según el registro que corresponda (técnico o de gestión)
  */
   @Column(name = "MO_PUNTAJE", nullable = true)
  private BigDecimal moPuntaje;
   
  /*
  *Indica si la unidad minera está activa o no en el ranking.  Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "FL_ACTIVO", nullable = false)
  private String flActivo;

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
  *DESC_ID_CONFIGURA_RIESGO
  */
 @Transient
  private Long descIdConfiguraRiesgo;


}