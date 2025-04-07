package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_RANKING_SUPERVISION: 
* @descripción: Puntaje de riesgo por supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRankingSupervisionDTO {

  /*
  *Identificador interno de la valoración de riesgo sobre la UM en una supervisión. Secuencia: PGIM_SEQ_RANKING_SUPERVISION
  */
  private Long idRankingSupervision;

  /*
  *Identificador interno de la supervisión de donde proviene la calificación. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno de la configuración del riesgo. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
  private Long idConfiguraRiesgo;

  /*
  *Valor obtenido de la sumatoria de los puntajes técnicos y de gestión
  */
  private BigDecimal moPuntaje;

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
  *Identificador de la especialidad
  */
  private Long descIdEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String descNoEspecialidad;

  /*
  *Identificador interno de la unidad minera
  */
  private Long descIdUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}