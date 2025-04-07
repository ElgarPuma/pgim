package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

import java.util.List;

/**
* DTO para la entidad PGIM_TD_RANKING_UM_FACTOR: 
* @descripción: Factor por unidad minera y su valoración de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRankingUmFactorDTO {

  /*
  *Identificador interno de la valoración del factor de riesgo sobre la UM. Secuencia: PGIM_SEQ_RANKING_UM_FACTOR
  */
  private Long idRankingUmFactor;

  /*
  *Identificador interno de la unidad minera asociada al ranking. Tabla padre: PGIM_TD_RANKING_UM
  */
  private Long idRankingUmGrupo;

  /*
  *Identificador interno del factor de riesgo para el ranking. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
  private Long idFactorRiesgo;

  /*
  *Identificador interno de la calificación del nivel de riesgo por factor. Tabla padre: PGIM_TM_NIVEL_RIESGO_FACTOR
  */
  private Long idCfgNivelRiesgo;

  /*
  *Valor obtenido de PGIM_TM_NIVEL_RIESGO.NU_VALOR seleccionado
  */
  private BigDecimal moCalificacion;

  /*
  *Valor obtenido del producto de el valor de calificación seleccionado (PGIM_TM_NIVEL_RIESGO.NU_VALOR) y el vector resultante para el factor en cuestión (PGIM_TM_CFG_FACTOR_RIESGO.NU_VECTOR_RESULTANTE)
  */
  private BigDecimal moPuntaje;

  /*
  *Comentario de la calificación dada a través del nivel de riesgo
  */
  private String cmCalificacion;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Usuario creador
  */
  private String usCreacion;

  /*
  *Terminal de creación
  */
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /*
  *Usuario modificador
  */
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private Date feActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  *Orden de prioridad del factor
  */
  private Long descNuOrdenPrioridad;

  /*
  *Nombre del factor de riesgo
  */
  private String descNoFactor;

  /*
  *Descripción del factor de riesgo
  */
  private String descDeFactor;

  /*
  *Especificación del nivel de riesgo del factor
  */
  private String descDeEspecificacion;

  /*
  *Nombre del origen del factror
  */
  private String descOrigen;

  /*
  *Identificador interno del factor de riesgo. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
  private Long descIdCfgFactorRiesgo;

  /*
  *Promedio de las ponderaciones PGIM_TM_CFG_MATRIZ_PARES.NU_MCPN
  */
  private BigDecimal descNuVectorResultante;

  /*
  *L_PGIM_CFG_NIVEL_RIESGO
  */
  private List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}