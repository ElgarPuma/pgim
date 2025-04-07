package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_RANKING_UM_GRUPO: 
* @descripción: Grupo del ranking de riesgo asociado a una unidad minera
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRankingUmGrupoDTO {

  /*
  *Identificador interno de la unidad minera dentro del ranking. Secuencia: PGIM_SEQ_RANKING_UM_GRUPO
  */
  private Long idRankingUmGrupo;

  /*
  *Identificador interno del ranking de riesgo. Tabla padre: PGIM_TC_RANKING_RIESGO
  */
  private Long idRankingUm;

  /*
  *Identificador interno de la valoración de riesgo sobre la UM en una supervisión. Tabla padre: PGIM_TD_RANKING_SUPERVISION. Si tiene valor entonces se trata de una calificación de riesgo dada al factor en el marco de una supervisión.
  */
  private Long idRankingSupervision;

  /*
  *Identififador interno del grupo de riesgo de la configuración asociada al ranking
  */
  private Long idCfgGrupoRiesgo;

  /*
  *Valor obtenido de la sumatoria de los puntajes obtenidos en todos los factores del grupo (PGIM_TD_RANKING_UM_FACTOR.MO_PUNTAJE)
  */
  private BigDecimal moPuntaje;

  /*
  *Indicador lógico que señala si se debe o no registrar los datos de riesgo. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flRegistrar;

  /*
  *Valor alfa para el grupo técnico, valor beta (1 - alfa) para el grupo de gestión. Para calcular alfa, se debe contar la cantidad de factores técnicos de la configuración marcados con PGIM_TM_CFG_FACTOR_RIESGO.FL_AFECTADO_GESTION = "1" entre el total de factores técnicos.
  */
  private BigDecimal nuCalificacionGrupo;

  /*
  *Indicador lógico que señala si se el puntaje del grupo es el máximo o no. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flMaximo;

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
  *Identificador interno del ranking de riesgo. Tabla padre: PGIM_TC_RANKING_RIESGO
  */
  private Long descIdRankingRiesgo;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Identificador interno de la configuración del grupo.
  */
  private Long descIdGrupo;

  /*
  *Nombre del tipo de grupo
  */
  private String descNoGrupo;

  /*
  *Descripción del tipo de grupo
  */
  private String descDeGrupo;

  /*
  *Identificador interno de la configuración del grupo.
  */
  private Long descIdGrupoRiesgo;

  /*
  *Identificador de la fase de proceso
  */
  private Long descIdFaseProceso;

  /*
  *Número de factores pendientes
  */
  private Integer descNroPendiente;

  /*
  *Nombre del grupo de riesgo de la configuración
  */
  private String descNoGrupoRiesgo;

  /*
  *Descripción del grupo de riesgo de la configuración
  */
  private String descDeGrupoRiesgo;

  /*
  *Factor de correción aplicado al valor de riesgo obtenido para el grupo de riesgo
  */
  private BigDecimal descPcFactorCorreccion;

  /*
  *Identificador interno de la configuración de riesgo
  */
  private Long descIdConfiguraRiesgo;

  /*
  *Nombre de la configuración de riesgo
  */
  private String descNoConfiguracion;

 /*
  *descIdInstanciaPaso
  */
  private Long descIdInstanciaPaso;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}