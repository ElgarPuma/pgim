package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_RANKING_SUP_GRUPO: 
* @descripción: Grupo del ranking de riesgo asociado a una supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 17/02/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRankingSupGrupoDTO {

  /*
  *Identificador interno de la valoración de riesgo sobre la UM en una supervisión. Secuencia: PGIM_SEQ_RANKING_SUPERVISION
  */
  private Long idRankingSupGrupo;

  /*
  *Identificador interno de la supervisión de donde proviene la calificación. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idRankingSupervision;

  /*
  *Valor obtenido de la sumatoria de calificaciones de PGIM_TM_NIVEL_RIESGO.NU_VALOR de los factores asociados al grupo
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