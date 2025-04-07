package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.math.BigDecimal;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_RANKING_UM_AUX: 
* @descripción: Vista unidadades mineras calificadas por factores técnicos, gestión y el respectivo puntaje total
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_RANKING_UM_AUX")
@Data
@NoArgsConstructor
public class PgimRankingUmAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la liquidación AUX. Secuencia: PGIM_SEQ_ENTRE_LIQ_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ENTRE_LIQ_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ENTRE_LIQ_AUX", sequenceName = "PGIM_SEQ_ENTRE_LIQ_AUX", allocationSize = 1)
   @Column(name = "ID_RANKING_UM_AUX", nullable = false)
  private Long idRankingUmAux;

  /*
  *ID_RANKING_UM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_UM", nullable = true)
  private PgimRankingUm pgimRankingUm;

  /*
  *Identificador interno del ranking de riesgo. Secuencia: PGIM_SEQ_RANKING_RIESGO
  */
   @Column(name = "ID_RANKING_RIESGO", nullable = true)
  private Long idRankingRiesgo;

  /*
  *ID_UNIDAD_MINERA
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *CO_ANONIMIZACION
  */
   @Column(name = "CO_ANONIMIZACION", nullable = true)
  private String coAnonimizacion;

  /*
  *PUNTAJE_TECNICO
  */
   @Column(name = "PUNTAJE_TECNICO", nullable = true)
  private BigDecimal puntajeTecnico;

  /*
  *PUNTAJE_GESTION
  */
   @Column(name = "PUNTAJE_GESTION", nullable = true)
  private BigDecimal puntajeGestion;

  /*
  *PUNTAJE_GENERAL
  */
   @Column(name = "PUNTAJE_GENERAL", nullable = true)
  private BigDecimal puntajeGeneral;

  /*
  *NRO_TECNICO_PENDIENTE
  */
   @Column(name = "NRO_TECNICO_PENDIENTE", nullable = true)
  private Integer nroTecnicoPendiente;

  /*
  *NRO_GESTION_PENDIENTE
  */
   @Column(name = "NRO_GESTION_PENDIENTE", nullable = true)
  private Integer nroGestionPendiente;

  /*
  *CO_TIPO_INCLUSION_RANKING
  */
   @Column(name = "CO_TIPO_INCLUSION_RANKING", nullable = true)
  private String coTipoInclusionRanking;

  /*
  *NO_TIPO_INCLUSION_RANKING
  */
   @Column(name = "NO_TIPO_INCLUSION_RANKING", nullable = true)
  private String noTipoInclusionRanking;

  /*
  *NU_CALIFICACION_TECNICO
  */
   @Column(name = "NU_CALIFICACION_TECNICO", nullable = true)
  private BigDecimal nuCalificacionTecnico;

  /*
  *NU_CALIFICACION_GESTION
  */
   @Column(name = "NU_CALIFICACION_GESTION", nullable = true)
  private BigDecimal nuCalificacionGestion;
   
  /*
  *Indica si la unidad minera está activa o no en el ranking.  Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "FL_ACTIVO", nullable = false)
  private String flActivo;

  /*
  *DESC_DATOS_COMPLETOS
  */
 @Transient
  private String descDatosCompletos;

  /*
  *Código del nivel de riesgo
  */
 @Transient
  private String descCoNivelRiesgo;

  /*
  *Valor del nivel inferior, números entre 0 y 100 en la calificación total del ranking de una UM
  */
 @Transient
  private BigDecimal descNuValorInferior;

  /*
  *Valor del nivel superior, números entre 0 y 100 en la calificación total del ranking de una UM
  */
 @Transient
  private BigDecimal descNuValorSuperior;

  /*
  *Código del color en hexadecimal del nivel de riesgo
  */
 @Transient
  private String descCoColorHexadecimal;


}