package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_PRGRM_SUPERV_PLAN_AUX: 
* @descripción: Vista para obtener la lista de los programas del plan de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPrgrmSupervPlanAuxDTO {

  /*
  *Secuencia: PGIM_SEQ_PRG_SUPERVISION_AUX
  */
  private Long idProgramaSupervisionAux;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
  private Long idProgramaSupervision;

  /*
  *ID_PLAN_SUPERVISION
  */
  private Long idPlanSupervision;

  /*
  *ID_DIVISION_SUPERVISORA
  */
  private Long idDivisionSupervisora;

  /*
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *ID_LINEA_PROGRAMA
  */
  private Long idLineaPrograma;

  /*
  *ID_LINEA_PROGRAMA_ESTADO
  */
  private Long idLineaProgramaEstado;

  /*
  *NO_DIVISION_SUPERVISION
  */
  private String noDivisionSupervision;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *ESTADO
  */
  private String estado;

  /*
  *MO_PARTIDA
  */
  private BigDecimal moPartida;

  /*
  *MO_COSTO_TOTAL
  */
  private BigDecimal moCostoTotal;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}