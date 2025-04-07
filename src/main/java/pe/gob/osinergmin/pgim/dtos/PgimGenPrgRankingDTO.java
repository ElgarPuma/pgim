package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_GEN_PRG_RANKING: 
* @descripción: Generación de programa por ranking de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 27/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimGenPrgRankingDTO {

  /*
  *Identificador interno de la generación del programa por ranking de riesgo. Secuencia: PGIM_SEQ_GEN_PRG_RANKING
  */
  private Long idGenPrgRanking;

  /*
  *Identificador interno de la propuesta de programa. Tabla padre: PGIM_TC_GEN_PROGRAMA
  */
  private Long idGenPrograma;

  /*
  *Identificador interno del ranking de riesgo. Tabla padre: PGIM_TC_RANKING_RIESGO
  */
  private Long idRankingRiesgo;

  /*
  *Puntaje total de riesgo de todas las unidades fiscalizables dentro del respectivo ranking de riesgo
  */
  private BigDecimal moPuntajeTotalRiesgo;

  /*
  *Número de orden del ranking de riesgo en la generación
  */
  private Integer nuOrden;

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