package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_INFRACCIONXESP_AUX: 
* @descripción: Vista de todas las especialidades y sus infracciones en seis años
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInfraccionxespAuxDTO {

  /*
  *Identificador interno de la especialidad que sumariza las infracciones por año. Secuencia: PGIM_SEQ_ESPECIALIDADINF_AUX
  */
  private Long idEspecialidadAux;

  /*
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *NRO_EXP_PAS_P1
  */
  private Long nroInfraccionP1;

  /*
  *NRO_EXP_PAS_P2
  */
  private Long nroInfraccionP2;

  /*
  *NRO_EXP_PAS_P3
  */
  private Long nroInfraccionP3;

  /*
  *NRO_EXP_PAS_P4
  */
  private Long nroInfraccionP4;

  /*
  *NRO_EXP_PAS_P5
  */
  private Long nroInfraccionP5;

  /*
  *NRO_EXP_PAS_P6
  */
  private Long nroInfraccionP6;

  /*
  *NRO_EXP_PAS_P7
  */
  private Long nroInfraccionTotal;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}