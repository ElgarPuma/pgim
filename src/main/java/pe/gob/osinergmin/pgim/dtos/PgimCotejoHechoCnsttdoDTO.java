package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_COTEJO_HECHO_CNSTTDO: 
* @descripción: Vista para obtener la lista de hechos constatados para cotejar lo indicado por el personal encargado de la supervisión vs lo indicado por los especialistas técnico y/o legal de Osinergmin
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCotejoHechoCnsttdoDTO {

  /*
  *Identificador interno de la vista PGIM_VW_COTEJO_HECHO_CNSTTDO:  Secuencia: PGIM_SEQ_ASD
  */
  private Long idCotejoHechoCnsttdo;

  /*
  *Identificador interno del hecho constatado de la supervisión. Tabla: PGIM_TC_HECHO_CONSTATADO
  */
  private Long idHechoConstatado;

  /*
  *Identificador del tipo de cumplimiento del hecho constatado, indicado por el personal de la supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CUMPLIMIENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoCumplimiento;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno de la configuración de criterios de la matriz de supervisión, tomada para una supervisión en particular. Tabla padre: PGIM_TC_CRITERIO_SPRVSION
  */
  private Long idCriterioSprvsion;

  /*
  *Identificador interno del hecho constatado reemplazante, o del nuevo hecho constatado indicado por el personal de Osinergmin (Especialista técnico o legal). Tabla:  PGIM_TC_HECHO_CONSTATADO
  */
  private Long idHechoConstatadoOsi;

  /*
  *Identificador del Tipo de cumplimiento del hecho constatado, indicado por el personal de Osinergmin (Especialista técnico o legal). Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CUMPLIMIENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoCumplimientoOsi;

  /*
  *Descripción del hecho constatado, indicado por el personal de la supervisión
  */
  private String deHechoConstatado;

  /*
  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión, indicado por el personal de la supervisión
  */
  private String deComplementoObservacion;

  /*
  *Sustento del hecho constatado, indicado por el personal de la supervisión
  */
  private String deSustento;

  /*
  *Descripción del tipo de cumplimiento del hecho constatado, indicado por el personal de la supervisión
  */
  private String deTipoCumplimiento;

  /*
  *Código del tipo de cumplimiento del hecho constatado, indicado por el personal de la supervisión
  */
  private Long coTipoCumplimiento;

  /*
  *Código del criterio de la matriz de supervisión
  */
  private String coMatrizCriterio;

  /*
  *Número de orden en la presentación de grupo de supervisión
  */
  private Long nuOrdenGrpoCrtrio;

  /*
  *Descripción del hecho constatado, indicado por el personal de Osinergmin (Especialista técnico o legal)
  */
  private String deHechoConstatadoOsi;

  /*
  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión, indicado por el personal de Osinergmin (Especialista técnico o legal).
  */
  private String deComplementoObservacionOsi;

  /*
  *Sustento del hecho constatado, indicado por el personal de Osinergmin (Especialista técnico o legal).
  */
  private String deSustentoOsi;

  /*
  * Código del tipo de cumplimiento del hecho constatado, indicado por el personal del Osinergmin
  */
  private String coTipoCumplimientoOsi;

  /*
  *Descripción del Tipo de cumplimiento del hecho constatado, indicado por el personal de Osinergmin (Especialista técnico o legal)
  */
  private String deTipoCumplimientoOsi;

  /*
  *Comentario del personal responsable de Osinergmin (Especialista técnico o legal)
  */
  private String deComentarioOsi;

  /*
  *Número de orden en la presentación del criterio
  */
  private Long nuOrdenCriterio;

  /*
  *Comentario o Hecho Constatado del personal responsable de Osinergmin (Especialista técnico o legal)
  */
  private String descComentarioHechoConstatadoOsi;
  
  
  /*
   *Id de fase del hecho constatado
   */
  private Long idFaseProceso;
  
  /*
   *Id de hecho constatado origen
   */
  private Long idHechoConstatadoOrigen;
  
  /*
   *Id de hecho constatado reemplazante
   */
  private Long idHechoConstatadoRmplznte;
  
  /*
   *Id de fase del hecho constatado (OSI)
   */
  private Long idFaseProcesoOsi;
  
  /*
   *Id de hecho constatado origen (OSI)
   */
  private Long idHechoConstatadoOrigenOsi;
  
  /*
   *Id de hecho constatado reemplazante (OSI)
   */
  private Long idHechoCnsttdoRmplznteOsi;
  
  /*
  *Código del criterio de la matriz de supervisión (OSI)
  */
  private String descCoMatrizCriterioOsi;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}