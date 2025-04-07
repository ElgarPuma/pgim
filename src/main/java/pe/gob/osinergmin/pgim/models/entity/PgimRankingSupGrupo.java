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
* Clase Entidad para la tabla PGIM_TD_RANKING_SUP_GRUPO: 
* @descripción: Grupo del ranking de riesgo asociado a una supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 17/02/2021
*/
@Entity
@Table(name = "PGIM_TD_RANKING_SUP_GRUPO")
@Data
@NoArgsConstructor
public class PgimRankingSupGrupo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la valoración de riesgo sobre la UM en una supervisión. Secuencia: PGIM_SEQ_RANKING_SUPERVISION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RANKING_SUPERVISION")
   @SequenceGenerator(name = "PGIM_SEQ_RANKING_SUPERVISION", sequenceName = "PGIM_SEQ_RANKING_SUPERVISION", allocationSize = 1)
   @Column(name = "ID_RANKING_SUP_GRUPO", nullable = false)
  private Long idRankingSupGrupo;

  /*
  *Identificador interno de la supervisión de donde proviene la calificación. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_SUPERVISION", nullable = false)
  private PgimRankingSupervision pgimRankingSupervision;

  /*
  *Valor obtenido de la sumatoria de calificaciones de PGIM_TM_NIVEL_RIESGO.NU_VALOR de los factores asociados al grupo
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