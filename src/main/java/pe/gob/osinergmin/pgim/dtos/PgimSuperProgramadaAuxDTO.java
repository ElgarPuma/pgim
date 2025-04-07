package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_SUPER_PROGRAMADA_AUX: 
* @descripción: Vista de supervisiones programadas por estrato (división supervisora) y especialidad
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSuperProgramadaAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_SUPER_PROGRAMADA_AUX Secuencia: PGIM_SEQ_SUPER_PROGRAMADA_AUX
  */
  private Long idSuperProgramadaAux;

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
  *NRO_SUP_PRG_P1
  */
  private Long nroSupPrgP1;

  /*
  *NRO_SUP_PRG_P2
  */
  private Long nroSupPrgP2;

  /*
  *NRO_SUP_PRG_P3
  */
  private Long nroSupPrgP3;

  /*
  *NRO_SUP_PRG_P4
  */
  private Long nroSupPrgP4;

  /*
  *NRO_SUP_PRG_P5
  */
  private Long nroSupPrgP5;

  /*
  *NRO_SUP_PRG_P6
  */
  private Long nroSupPrgP6;

  /*
  *TOTAL_NRO_SUP_PRG
  */
  private Long totalNroSupPrg;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}