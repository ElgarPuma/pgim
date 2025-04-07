package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_EXPPASTIPACTI_ANIO_AUX: 
* @descripción: Vista de expedientes con PAS por tipo de actividad y año
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimExppastipactiAnioAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_EXPPASTIPACTI_ANIO_AUX. Secuencia: PGIM_SEQ_TIPO_ACTIVIDAD_AUX
  */
  private Long idTipoActividadAux;

  /*
  *ID_TIPO_ACTIVIDAD
  */
  private Long idTipoActividad;

  /*
  *NO_TIPO_ACTIVIDAD
  */
  private String noTipoActividad;

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
  *TOTAL_NRO_EXP
  */
  private Long totalNroExp;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}