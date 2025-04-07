package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_PROGRAM_PROPUESTO_RNK: 
* @descripción: Generación de programa de supervisión a partir de ranking
*
* @author: palominovega
* @version 1.0
* @fecha_de_creación: 05/01/2024
*/
@Entity
@Table(name = "PGIM_VW_PROGRAM_PROPUESTO_RNK")
@Data
@NoArgsConstructor
public class PgimVwProgramPropuestoRnk implements Serializable{

private static final long serialVersionUID = 1L;

   @Id
  @Column(name = "ID_GEN_PROGRAMA", nullable = false)
  private Long idGenPrograma; 
   
   @JoinColumn(name = "ID_PROGRAMA_SUPERVISION", nullable = false)
  private Long idProgramaSupervision;

   @Column(name = "FL_CONFORME", nullable = false)
  private String flConforme;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_GENERACION", nullable = false)
  private Date feGeneracion;

   @Column(name = "NU_MAX_FISCA_ANUAL_XUF", nullable = false)
  private Integer nuMaxFiscaAnualXuf;

   @Column(name = "NU_MAX_FISCA_MENSUAL", nullable = false)
  private Integer nuMaxFiscaMensual;

   @Column(name = "NU_ENERO", nullable = true)
  private Integer nuEnero;

   @Column(name = "NU_FEBRERO", nullable = true)
  private Integer nuFebrero;

   @Column(name = "NU_MARZO", nullable = true)
  private Integer nuMarzo;

   @Column(name = "NU_ABRIL", nullable = true)
  private Integer nuAbril;

   @Column(name = "NU_MAYO", nullable = true)
  private Integer nuMayo;

   @Column(name = "NU_JUNO", nullable = true)
  private Integer nuJuno;

   @Column(name = "NU_JULIO", nullable = true)
  private Integer nuJulio;

   @Column(name = "NU_AGOSTO", nullable = true)
  private Integer nuAgosto;

   @Column(name = "NU_SEPTIEMBRE", nullable = true)
  private Integer nuSeptiembre;

   @Column(name = "NU_OCTUBRE", nullable = true)
  private Integer nuOctubre;

   @Column(name = "NU_NOVIEMBRE", nullable = true)
  private Integer nuNoviembre;

   @Column(name = "NU_DICIEMBRE", nullable = true)
  private Integer nuDiciembre;

   @Column(name = "NU_ORDEN", nullable = false)
  private Integer nuOrden;

   @Column(name = "ID_GEN_PRG_RANKING", nullable = false)
  private Long idGenPrgRanking;

   @Column(name = "ID_RANKING_RIESGO", nullable = false)
  private Long idRankingRiesgo;

   @Column(name = "NO_RANKING", nullable = true)
  private String noRanking;

   @Column(name = "ID_GEN_PRG_UFISCALIZA", nullable = false)
  private Long idGenPrgUfiscaliza;

   @Column(name = "MO_PUNTAJE_RIESGO", nullable = false)
  private BigDecimal moPuntajeRiesgo;

   @Column(name = "MO_PUNTAJE_TOTAL_RIESGO", nullable = false)
  private BigDecimal moPuntajeTotalRiesgo;

   @Column(name = "MO_PUNTAJE_PROPORCION", nullable = false)
  private BigDecimal moPuntajeProporcion;

   @Column(name = "MO_PROPORCION_ENTERA", nullable = true)
  private Integer moProporcionEntera;

   @Column(name = "CA_VISITAS", nullable = true)
  private Integer caVisitas;

   @Column(name = "ID_RANKING_UM", nullable = false)
  private Long idRankingUm;

    @Column(name = "ID_UNIDAD_MINERA", nullable = false)
  private Long idUnidadMinera;
  
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

   @Column(name = "ENERO", nullable = true)
  private Integer enero;

   @Column(name = "FEBRERO", nullable = true)
  private Integer febrero;

   @Column(name = "MARZO", nullable = true)
  private Integer marzo;

   @Column(name = "ABRIL", nullable = true)
  private Integer abril;

   @Column(name = "MAYO", nullable = true)
  private Integer mayo;

   @Column(name = "JUNIO", nullable = true)
  private Integer juno;

   @Column(name = "JULIO", nullable = true)
  private Integer julio;

   @Column(name = "AGOSTO", nullable = true)
  private Integer agosto;

   @Column(name = "SETIEMBRE", nullable = true)
  private Integer septiembre;

   @Column(name = "OCTUBRE", nullable = true)
  private Integer octubre;

   @Column(name = "NOVIEMBRE", nullable = true)
  private Integer noviembre;

   @Column(name = "DICIEMBRE", nullable = true)
  private Integer diciembre;

   @Column(name = "FL_CONFORME_PRG_MES", nullable = true)
  private String flConformePrgMes;
  
   @Column(name = "DE_MENSAJE_PRG_MES", nullable = true)
  private String deMensajePrgMes;

}