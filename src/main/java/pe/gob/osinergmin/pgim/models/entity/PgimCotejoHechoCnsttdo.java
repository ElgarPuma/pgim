package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.Lob;
import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_COTEJO_HECHO_CNSTTDO: 
* @descripción: Vista para obtener la lista de hechos constatados para cotejar lo indicado por el personal encargado de la supervisión vs lo indicado por los especialistas técnico y/o legal de Osinergmin
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_COTEJO_HECHO_CNSTTDO")
@Data
@NoArgsConstructor
public class PgimCotejoHechoCnsttdo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_COTEJO_HECHO_CNSTTDO:  Secuencia: PGIM_SEQ_ASD
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ASD")
   @SequenceGenerator(name = "PGIM_SEQ_ASD", sequenceName = "PGIM_SEQ_ASD", allocationSize = 1)
   @Column(name = "ID_COTEJO_HECHO_CNSTTDO", nullable = false)
  private Long idCotejoHechoCnsttdo;

  /*
  *Identificador interno del hecho constatado de la supervisión. Tabla: PGIM_TC_HECHO_CONSTATADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_HECHO_CONSTATADO", nullable = true)
  private PgimHechoConstatado pgimHechoConstatado;

  /*
  *Identificador del tipo de cumplimiento del hecho constatado, indicado por el personal de la supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CUMPLIMIENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_CUMPLIMIENTO", nullable = true)
  private PgimValorParametro tipoCumplimiento;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno de la configuración de criterios de la matriz de supervisión, tomada para una supervisión en particular. Tabla padre: PGIM_TC_CRITERIO_SPRVSION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CRITERIO_SPRVSION", nullable = true)
  private PgimCriterioSprvsion pgimCriterioSprvsion;

  /*
  *Identificador interno del hecho constatado reemplazante, o del nuevo hecho constatado indicado por el personal de Osinergmin (Especialista técnico o legal). Tabla:  PGIM_TC_HECHO_CONSTATADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_HECHO_CONSTATADO_OSI", nullable = true)
  private PgimHechoConstatado hechoConstatadoOsi;

  /*
  *Identificador del Tipo de cumplimiento del hecho constatado, indicado por el personal de Osinergmin (Especialista técnico o legal). Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CUMPLIMIENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_CUMPLIMIENTO_OSI", nullable = true)
  private PgimValorParametro tipoCumplimientoOsi;

  /*
  *Descripción del hecho constatado, indicado por el personal de la supervisión
  */
   @Column(name = "DE_HECHO_CONSTATADO", nullable = true)
   @Lob
  private String deHechoConstatado;

  /*
  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión, indicado por el personal de la supervisión
  */
   @Column(name = "DE_COMPLEMENTO_OBSERVACION", nullable = true)
  private String deComplementoObservacion;

  /*
  *Sustento del hecho constatado, indicado por el personal de la supervisión
  */
   @Column(name = "DE_SUSTENTO", nullable = true)
@Lob
  private String deSustento;

  /*
  *Descripción del tipo de cumplimiento del hecho constatado, indicado por el personal de la supervisión
  */
   @Column(name = "DE_TIPO_CUMPLIMIENTO", nullable = true)
  private String deTipoCumplimiento;

  /*
  *Código del tipo de cumplimiento del hecho constatado, indicado por el personal de la supervisión
  */
   @Column(name = "CO_TIPO_CUMPLIMIENTO", nullable = true)
  private Long coTipoCumplimiento;

  /*
  *Código del criterio de la matriz de supervisión
  */
   @Column(name = "CO_MATRIZ_CRITERIO", nullable = true)
  private String coMatrizCriterio;

  /*
  *Número de orden en la presentación de grupo de supervisión
  */
   @Column(name = "NU_ORDEN_GRPO_CRTRIO", nullable = true)
  private Long nuOrdenGrpoCrtrio;

  /*
  *Descripción del hecho constatado, indicado por el personal de Osinergmin (Especialista técnico o legal)
  */
   @Column(name = "DE_HECHO_CONSTATADO_OSI", nullable = true)
  private String deHechoConstatadoOsi;

  /*
  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión, indicado por el personal de Osinergmin (Especialista técnico o legal).
  */
   @Column(name = "DE_COMPLEMENTO_OBSERVACION_OSI", nullable = true)
  private String deComplementoObservacionOsi;

  /*
  *Sustento del hecho constatado, indicado por el personal de Osinergmin (Especialista técnico o legal).
  */
   @Column(name = "DE_SUSTENTO_OSI", nullable = true)
@Lob
  private String deSustentoOsi;

  /*
  * Código del tipo de cumplimiento del hecho constatado, indicado por el personal del Osinergmin
  */
   @Column(name = "CO_TIPO_CUMPLIMIENTO_OSI", nullable = true)
  private String coTipoCumplimientoOsi;

  /*
  *Descripción del Tipo de cumplimiento del hecho constatado, indicado por el personal de Osinergmin (Especialista técnico o legal)
  */
   @Column(name = "DE_TIPO_CUMPLIMIENTO_OSI", nullable = true)
  private String deTipoCumplimientoOsi;

  /*
  *Comentario del personal responsable de Osinergmin (Especialista técnico o legal)
  */
   @Column(name = "DE_COMENTARIO_OSI", nullable = true)
@Lob
  private String deComentarioOsi;

  /*
  *Número de orden en la presentación del criterio
  */
   @Column(name = "NU_ORDEN_CRITERIO", nullable = true)
  private Long nuOrdenCriterio;
   
  /*
   *Id de fase del hecho constatado
   */
  @Column(name = "ID_FASE_PROCESO", nullable = true)
  private Long idFaseProceso;
  
  /*
   *Id de hecho constatado origen
   */
  @Column(name = "ID_HECHO_CONSTATADO_ORIGEN", nullable = true)
  private Long idHechoConstatadoOrigen;
  
  /*
   *Id de hecho constatado reemplazante
   */
  @Column(name = "ID_HECHO_CONSTATADO_RMPLZNTE", nullable = true)
  private Long idHechoConstatadoRmplznte;
  
  /*
   *Id de fase del hecho constatado (OSI)
   */
  @Column(name = "ID_FASE_PROCESO_OSI", nullable = true)
  private Long idFaseProcesoOsi;
  
  /*
   *Id de hecho constatado origen (OSI)
   */
  @Column(name = "ID_HECHO_CONSTATADO_ORIGEN_OSI", nullable = true)
  private Long idHechoConstatadoOrigenOsi;
  
  /*
   *Id de hecho constatado reemplazante (OSI)
   */
  @Column(name = "ID_HECHO_CNSTTDO_RMPLZNTE_OSI", nullable = true)
  private Long idHechoCnsttdoRmplznteOsi;  

  /*
  *Comentario o Hecho Constatado del personal responsable de Osinergmin (Especialista técnico o legal)
  */
 @Transient
  private String descComentarioHechoConstatadoOsi;


}