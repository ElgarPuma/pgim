package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_INFRACCION_AUX: 
* @descripción: Vista para obtener el listado de las infracciones identificadas a partir de los incumplimientos de los criterios expuestos en la matriz de supervisión correspondiente
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInfraccionAuxDTO {

  /*
  *Identificador interno de la infracción asociada a la obligación normativa de la supervisión. Secuencia: PGIM_SEQ_INFRACCION_AUX
  */
  private Long idInfraccionAux;

  /*
  *Identificador interno de la infracción asociada a la obligación normativa de la supervisión. Tabla: PGIM_TD_INFRACCION
  */
  private Long idInfraccion;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *ID_PAS
  */
  private Long idPas;

  /*
  *Identificador interno del hecho constatado de la supervisión. Tabla padre: PGIM_TC_OBLGCN_NRMTVA_HCHOC
  */
  private Long idOblgcnNrmtvaHchoc;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Identificador interno de la infracción origen (supervisión). Tabla padre: PGIM_TD_INFRACCION
  */
  private Long idInfraccionOrigen;

  /*
  *Código de la tipificación
  */
  private String coTipificacion;

  /*
  *Nombre del ítem de tipificación
  */
  private String noItemTipificacion;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
  private String deBaseLegal;

  /*
  *Flag que indica si la infracción será incluida en el PAS o no. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flIncluirEnPas;

  /*
  *Descripción de la sancion pecuniaria en UIT
  */
  private String deSancionPecuniariaUit;

  /*
  *Descripción de la decisión de incluir o no la infracción en el PAS
  */
  private String deSustento;

  /*
  *Año de la infracción
  */
  private Long feAnioInfraccion;

  /*
  *Código de la unidad minera
  */
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String noUnidadMinera;

  /*
  *RUC del agente supervisado
  */
  private String rucAs;

  /*
  *Razón social del agente supervisado
  */
  private String noRazonSocialAs;

  /*
  *Nombre corto del agente supervisado
  */
  private String noCortoAs;

  /*
  *Fecha de comisión de la detección de la infracción
  */
  private Date feComisionDeteccion;

  /*
  *Fecha de comisión de la detección de la infracción
  */
  private String feComisionDeteccionDesc;

  /*
  *Monto exacto de la multa expresado en UIT determinado por el revisor económico
  */
  private BigDecimal moMultaUit;  

  /*
   *ID_PASO_PROCESO
   */
  private Long idPasoProceso;

  /*
   *ID_FASE_PROCESO
   */
  private Long idFaseProceso;
  
  /*
  *Flag que indica si la infracción está vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flVigente;
  
  /*
  *Flag que indica si el HC de la infracción es un HC vigente o histórico. Los posibles valores son: "V" = Vigente y "H" = Histórico
  */
  private String esVigenteHc;
  
  /*
   *Identificador interno de la hecho verificado. Tabla padre: PGIM_TC_HECHO_CONSTATADO
   */
  private Long idHechoConstatado;

  /*
   *Identificador interno de la hecho verificado(Origen). Tabla padre: PGIM_TC_HECHO_CONSTATADO
   */
  private Long idHechoConstatadoOrigen;
  
  /*
   * ID_FASE_PROCESO_HC
   */
  private Long idFaseProcesoHC;
  
  /*
  *Código del criterio de la matriz de supervisión
  */
  private String descCoMatrizCriterio;

  /*
  *Descripción del criterio de la matriz de supervisión
  */
  private String descDeMatrizCriterio;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
  private String descDeBaseLegal;

  /*
  *Descripción del hecho constatado, indicado por el personal de la supervisión
  */
  private String descDeHechoConstatado;

  /*
  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión, indicado por el personal de la supervisión
  */
  private String descDeComplementoObservacion;

  /*
  *Sustento del hecho constatado
  */
  private String descDeSustento;

  /*
  *Descripción del tipo de cumplimiento del hecho constatado, indicado por el personal de la supervisión
  */
  private String descTipoCumplimiento;

  /*
  *Nombre corto de la norma legal
  */
  private String descNoCortoNorma;
  
  /*
   * Id de la fase actual del objeto de trabajo (supervisión o pas)
   */
  private Long descIdFaseActual;

  /**
   * Código de supervisión
   */
  private String descCoSupervision;

  /**
   * Código Pas
   */
  private String descCoPas;

  /**
   * Descripción de obligación normativa
   */
  private String descDeObligacionNormativa;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}