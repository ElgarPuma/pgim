package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_SUPERNP_DS_ESP_AUX: 
* @descripción: Vista de de cantidad de supervisiones no programadas por división supervisora-especialidad
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSupernpDsEspAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_SUPERNP_DS_ESP_AUX Secuencia: PGIM_SEQ_SUPERNP_DS_ESP_AUX
  */
  private Long idSupernpDsEspAux;

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
  *SUP_A1
  */
  private Long supA1;

  /*
  *SUP_A2
  */
  private Long supA2;

  /*
  *SUP_A3
  */
  private Long supA3;

  /*
  *SUP_A4
  */
  private Long supA4;

  /*
  *SUP_A5
  */
  private Long supA5;

  /*
  *SUP_A6
  */
  private Long supA6;

  /*
  *TOTAL_SUPERVISION
  */
  private Long totalSupervision;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}