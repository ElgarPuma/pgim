package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la vista PGIM_VW_RANKING_RIESGO_AUX: 
* @descripción: Ranking de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 24/02/2021
*/
@Entity
@Table(name = "PGIM_VW_RANKING_RIESGO_AUX")
@Data
@NoArgsConstructor
public class PgimRankingRiesgoAux implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    
    /*
  *Identificador interno de la liquidación AUX. Secuencia: PGIM_SEQ_ENTRE_LIQ_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ENTRE_LIQ_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ENTRE_LIQ_AUX", sequenceName = "PGIM_SEQ_ENTRE_LIQ_AUX", allocationSize = 1)
   @Column(name = "ID_RANKING_RIESGO_AUX", nullable = false)
  private Long idRankingRiesgoAux;

  /*
  *ID_RANKING_UM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_RIESGO", nullable = true)
  private PgimRankingRiesgo pgimRankingRiesgo;

  /*
  *ID_INSTANCIA_PASO
  */
   @Column(name = "ID_INSTANCIA_PASO", nullable = true)
  private Long idInstanciaPaso;

  /*
  *ID_RANKING_RIESGO
  */
   @Column(name = "ID_CONFIGURA_RIESGO", nullable = true)
  private Long idConfiguraRiesgo;

  /*
  *ID_UNIDAD_MINERA
  */
   @Column(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private Long idInstanciaProceso;

  /*
  *CO_UNIDAD_MINERA
  */
   @Column(name = "NO_RANKING", nullable = true)
  private String noRanking;

  /*
  *NO_UNIDAD_MINERA
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_GENERACION_RANKING", nullable = true)
  private Date feGeneracionRanking;

  /*
  *NO_UNIDAD_MINERA
  */
   @Column(name = "FE_GENERACION_RANKING_ANIO", nullable = true)
  private String feGeneracionRankingAnio;

  /*
  *CO_ANONIMIZACION
  */
   @Column(name = "NO_PERSONA_ASIGNADA", nullable = true)
  private String noPersonaAsignada;

  /*
  *PUNTAJE_TECNICO
  */
   @Column(name = "NO_USUARIO_ASIGNADO", nullable = true)
  private String noUsuarioAsignado;

  /*
  *PUNTAJE_GESTION
  */
   @Column(name = "ID_FASE_ACTUAL", nullable = true)
  private Long idFaseActual;

  /*
  *PUNTAJE_GENERAL
  */
   @Column(name = "NO_FASE_ACTUAL", nullable = true)
  private String noFaseActual;

  /*
  *NRO_TECNICO_PENDIENTE
  */
   @Column(name = "ID_PASO_ACTUAL", nullable = true)
  private Long idPasoActual;

  /*
  *NRO_GESTION_PENDIENTE
  */
   @Column(name = "NO_PASO_ACTUAL", nullable = true)
  private String noPasoActual;

  /*
  *ID_RANKING_UM_GRUPO_TECNICO
  */
   @Column(name = "ID_RELACION_PASO", nullable = true)
  private Long idRelacionPaso;

  /*
  *ID_RANKING_UM_GRUPO_GESTION
  */
   @Column(name = "ID_TIPO_RELACION", nullable = true)
  private Long idTipoRelacion;
   
  /*
  *FL_LEIDO
  */
   @Column(name = "FL_LEIDO", nullable = true)
  private String flLeido;

  /*
  *FE_LECTURA
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_LECTURA", nullable = true)
  private Date feLectura;
     
  /*
  *NO_PERSONA_ORIGEN
  */
  @Column(name = "NO_PERSONA_ORIGEN", nullable = true)
  private String noPersonaOrigen;

  /*
  *NO_USUARIO_ORIGEN
  */
   @Column(name = "NO_USUARIO_ORIGEN", nullable = true)
  private String noUsuarioOrigen;

  /*
  *FE_INSTANCIA_PASO
  */
  @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INSTANCIA_PASO", nullable = true)
  private Date feInstanciaPaso;

  /*
  *DE_MENSAJE
  */
   @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
  *FL_PASO_ACTIVO
  */
   @Column(name = "FL_PASO_ACTIVO", nullable = true)
  private String flPasoActivo;

}
