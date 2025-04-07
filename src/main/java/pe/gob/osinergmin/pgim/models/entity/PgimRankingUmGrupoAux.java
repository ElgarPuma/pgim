package pe.gob.osinergmin.pgim.models.entity;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_RANKING_UM_GRUPO_AUX: 
* @descripción: Vista de ranking de unidades mineras por grupos
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 24/02/2021
*/

@Entity
@Table(name = "PGIM_VW_RANKING_UM_GRUPO_AUX")
@Data
@NoArgsConstructor
public class PgimRankingUmGrupoAux implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /*
  *Identificador interno del PAS auxiliar. Secuencia: PGIM_SEQ_PAS_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PAS_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_PAS_AUX", sequenceName = "PGIM_SEQ_PAS_AUX", allocationSize = 1)
   @Column(name = "ID_RANKING_UM_GRUPO_AUX", nullable = false)
  private Long idRankingUmGrupo;

  /*
  *ID_PAS
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_UM_GRUPO", nullable = true)
  private PgimRankingUmGrupo pgimRankingUmGrupo;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *Identificador interno del agente supervisado relacionado
  */
   @Column(name = "MO_PUNTAJE_TECNICO", nullable = true)
  private BigDecimal moPuntajeTecnico;

  /*
  *Identificador interno del agente supervisado relacionado
  */
   @Column(name = "MO_PUNTAJE_GESTION", nullable = true)
  private BigDecimal moPuntajeGestion;

  
}
