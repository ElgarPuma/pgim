package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_RANKING_FACTOR_UM: 
* @descripción: Factor por unidad minera y su valoración de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 16/02/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRankingFactorUmDTO {

  /*
  *Identificador interno de la valoración del factor de riesgo sobre la UM. Secuencia: PGIM_SEQ_RANKING_FACTOR_UM
  */
  private Long idRankingFactorUm;

  /*
  *Identificador interno de la unidad minera asociada al ranking. Tabla padre: PGIM_TD_RANKING_UM
  */
  private Long idRankingUm;

  /*
  *Identificador interno del ranking dado al factor de riesgo en el marco de una supervisión dada. Si tiene valor entonces se trata de una calificación de riesgo dada al factor en el marco de una supervisión. Tabla padre: PGIM_TD_RANKING_SUPERVISION
  */
  private Long idRankingSupervision;

  /*
  *Identificador interno del factor de riesgo para el ranking. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
  private Long idFactorRiesgo;

  /*
  *Identificador interno de la calificación del nivel de riesgo por factor. Tabla padre: PGIM_TM_NIVEL_RIESGO_FACTOR
  */
  private Long idNivelRiesgoFactor;

  /*
  *Valor obtenido luego de multiplicar la valoración (PGIM_TM_NIVEL_RIESGO.NU_VALOR) por el valor del vector unitario de la metodología.
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
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}