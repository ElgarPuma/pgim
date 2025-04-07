package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_RANKING_UM: 
* @descripción: Unidad minera asociada al ranking de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRankingUmDTO {

  /*
  *Identificador interno de la unidad minera dentro del ranking. Secuencia: PGIM_SEQ_RANKING_UM
  */
  private Long idRankingUm;

  /*
  *Identificador interno del ranking de riesgo. Tabla padre: PGIM_TC_RANKING_RIESGO
  */
  private Long idRankingRiesgo;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Tipo de inclusión de la UM en el universo de unidades mineras del ranking. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = ID_TIPO_INCLUSION_RANKING. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoInclusionRanking;

  /*
  *Valor obtenido de la sumatoria de los puntajes técnicos y de gestión, esto es: Calificación final = alfa x FT + beta x FG, donde el valor de alfa y beta se encuentran registrados en la columna PGIM_TM_CFG_GRUPO_RIESGO.NU_CALIFICACION_GRUPO según el registro que corresponda (técnico o de gestión)
  */
  private BigDecimal moPuntaje;
  
  /*
  *Indica si la unidad minera está activa o no en el ranking. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String flActivo;

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
  *DESC_ID_CONFIGURA_RIESGO
  */
  private Long descIdConfiguraRiesgo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}