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
* Clase Entidad para la tabla PGIM_VW_INFRACCION_AUX: 
* @descripción: Vista para obtener el listado de las infracciones identificadas a partir de los incumplimientos de los criterios expuestos en la matriz de supervisión correspondiente
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_INFRACCION_AUX")
@Data
@NoArgsConstructor
public class PgimInfraccionAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la infracción asociada a la obligación normativa de la supervisión. Secuencia: PGIM_SEQ_INFRACCION_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INFRACCION_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_INFRACCION_AUX", sequenceName = "PGIM_SEQ_INFRACCION_AUX", allocationSize = 1)
   @Column(name = "ID_INFRACCION_AUX", nullable = false)
  private Long idInfraccionAux;

  /*
  *Identificador interno de la infracción asociada a la obligación normativa de la supervisión. Tabla: PGIM_TD_INFRACCION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INFRACCION", nullable = true)
  private PgimInfraccion pgimInfraccion;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *ID_PAS
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PAS", nullable = true)
  private PgimPas pgimPas;

  /*
  *Identificador interno del hecho constatado de la supervisión. Tabla padre: PGIM_TC_OBLGCN_NRMTVA_HCHOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_OBLGCN_NRMTVA_HCHOC", nullable = true)
  private PgimOblgcnNrmtvaHchoc pgimOblgcnNrmtvaHchoc;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO", nullable = true)
  private PgimInstanciaPaso pgimInstanciaPaso;

  /*
  *Identificador interno de la infracción origen (supervisión). Tabla padre: PGIM_TD_INFRACCION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INFRACCION_ORIGEN", nullable = true)
  private PgimInfraccion infraccionOrigen;

  /*
  *Código de la tipificación
  */
   @Column(name = "CO_TIPIFICACION", nullable = true)
  private String coTipificacion;

  /*
  *Nombre del ítem de tipificación
  */
   @Column(name = "NO_ITEM_TIPIFICACION", nullable = true)
  private String noItemTipificacion;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
   @Column(name = "DE_BASE_LEGAL", nullable = true)
  private String deBaseLegal;

  /*
  *Flag que indica si la infracción será incluida en el PAS o no. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_INCLUIR_EN_PAS", nullable = true)
  private String flIncluirEnPas;

  /*
  *Descripción de la sancion pecuniaria en UIT
  */
   @Column(name = "DE_SANCION_PECUNIARIA_UIT", nullable = true)
  private String deSancionPecuniariaUit;

  /*
  *Descripción de la decisión de incluir o no la infracción en el PAS
  */
   @Column(name = "DE_SUSTENTO", nullable = true)
  private String deSustento;

  /*
  *Año de la infracción
  */
   @Column(name = "FE_ANIO_INFRACCION", nullable = true)
  private Long feAnioInfraccion;

  /*
  *Código de la unidad minera
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *RUC del agente supervisado
  */
   @Column(name = "RUC_AS", nullable = true)
  private String rucAs;

  /*
  *Razón social del agente supervisado
  */
   @Column(name = "NO_RAZON_SOCIAL_AS", nullable = true)
  private String noRazonSocialAs;

  /*
  *Nombre corto del agente supervisado
  */
   @Column(name = "NO_CORTO_AS", nullable = true)
  private String noCortoAs;

  /*
  *Fecha de comisión de la detección de la infracción
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_COMISION_DETECCION", nullable = true)
  private Date feComisionDeteccion;

  /*
  *Monto exacto de la multa expresado en UIT determinado por el revisor económico
  */
   @Column(name = "MO_MULTA_UIT", nullable = true)
  private BigDecimal moMultaUit;
   
  /*
  *ID_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_PROCESO", nullable = true)
  private PgimPasoProceso pgimPasoProceso;

  /*
  *ID_FASE_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FASE_PROCESO", nullable = true)
  private PgimFaseProceso pgimFaseProceso;
   
  /*
  *Flag que indica si la infracción está vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_VIGENTE", nullable = true)
  private String flVigente;

  /*
  *Flag que indica si el HC de la infracción es un HC vigente o histórico. Los posibles valores son: "V" = Vigente y "H" = Histórico
  */
   @Column(name = "ES_VIGENTE_HC", nullable = true)
  private String esVigenteHc;
   
   /*
    *Identificador interno de la hecho verificado. Tabla padre: PGIM_TC_HECHO_CONSTATADO
    */
   	@JsonIgnore
   	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name = "ID_HECHO_CONSTATADO", nullable = true)
   private PgimHechoConstatado pgimHechoConstatado;
   	
    /*
     *Identificador interno de la hecho verificado (Origen) . Tabla padre: PGIM_TC_HECHO_CONSTATADO
     */
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_HECHO_CONSTATADO_ORIGEN", nullable = true)
   private PgimHechoConstatado pgimHechoConstatadoOrigen; 
   
   /*
    *ID_FASE_PROCESO
    */
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_FASE_PROCESO_HC", nullable = true)
   private PgimFaseProceso pgimFaseProcesoHC;
   
  /*
  *Código del criterio de la matriz de supervisión
  */
 @Transient
  private String descCoMatrizCriterio;

  /*
  *Descripción del criterio de la matriz de supervisión
  */
 @Transient
  private String descDeMatrizCriterio;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
 @Transient
  private String descDeBaseLegal;

  /*
  *Descripción del hecho constatado, indicado por el personal de la supervisión
  */
 @Transient
  private String descDeHechoConstatado;

  /*
  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión, indicado por el personal de la supervisión
  */
 @Transient
  private String descDeComplementoObservacion;

  /*
  *Sustento del hecho constatado
  */
 @Transient
  private String descDeSustento;

  /*
  *Descripción del tipo de cumplimiento del hecho constatado, indicado por el personal de la supervisión
  */
 @Transient
  private String descTipoCumplimiento;

  /*
  *Nombre corto de la norma legal
  */
 @Transient
  private String descNoCortoNorma;


}