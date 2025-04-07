package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_EXPPAS_EVALUADOR_AUX: 
* @descripción: Vista de expedientes con PAS asignados a evaluador, por división y especialidad
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimExppasEvaluadorAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_EXPPAS_EVALUADOR_AUX Secuencia: PGIM_SEQ_EVALUADOR_AUX
  */
  private Long idExpPasEvaluadorAux;

  /*
  *ID_DIVISION_SUPERVISORA
  */
  private Long idDivisionSupervisora;

  /*
  *NO_DIVISION_SUPERVISORA
  */
  private String noDivisionSupervisora;

  /*
  *DE_DIVISION_SUPERVISORA
  */
  private String deDivisionSupervisora;

  /*
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *ID_PERSONAL_OSI
  */
  private Long idPersonalOsi;

  /*
  *ID_PERSONA
  */
  private Long idPersona;

  /*
  *NO_EVALUADOR
  */
  private String noEvaluador;

  /*
  *NRO_EXP_PAS_P1
  */
  private Long nroExpPasP1;

  /*
  *NRO_EXP_PAS_P2
  */
  private Long nroExpPasP2;

  /*
  *NRO_EXP_PAS_P3
  */
  private Long nroExpPasP3;

  /*
  *NRO_EXP_PAS_P4
  */
  private Long nroExpPasP4;

  /*
  *NRO_EXP_PAS_P5
  */
  private Long nroExpPasP5;

  /*
  *NRO_EXP_PAS_P6
  */
  private Long nroExpPasP6;

  /*
  *NRO_EXP_PAS_TOTAL
  */
  private Long nroExpPasTotal;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}