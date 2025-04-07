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
* Clase Entidad para la tabla PGIM_TD_GEN_PRG_RANKING: 
* @descripción: Generación de programa por ranking de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 27/12/2023
*/
@Entity
@Table(name = "PGIM_TD_GEN_PRG_RANKING")
@Data
@NoArgsConstructor
public class PgimGenPrgRanking implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la generación del programa por ranking de riesgo. Secuencia: PGIM_SEQ_GEN_PRG_RANKING
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_GEN_PRG_RANKING")
   @SequenceGenerator(name = "PGIM_SEQ_GEN_PRG_RANKING", sequenceName = "PGIM_SEQ_GEN_PRG_RANKING", allocationSize = 1)
   @Column(name = "ID_GEN_PRG_RANKING", nullable = false)
  private Long idGenPrgRanking;

  /*
  *Identificador interno de la propuesta de programa. Tabla padre: PGIM_TC_GEN_PROGRAMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_GEN_PROGRAMA", nullable = false)
  private PgimGenPrograma pgimGenPrograma;

  /*
  *Identificador interno del ranking de riesgo. Tabla padre: PGIM_TC_RANKING_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_RIESGO", nullable = false)
  private PgimRankingRiesgo pgimRankingRiesgo;

  /*
  *Puntaje total de riesgo de todas las unidades fiscalizables dentro del respectivo ranking de riesgo
  */
   @Column(name = "MO_PUNTAJE_TOTAL_RIESGO", nullable = false)
  private BigDecimal moPuntajeTotalRiesgo;

  /*
  *Número de orden del ranking de riesgo en la generación
  */
   @Column(name = "NU_ORDEN", nullable = false)
  private Integer nuOrden;

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