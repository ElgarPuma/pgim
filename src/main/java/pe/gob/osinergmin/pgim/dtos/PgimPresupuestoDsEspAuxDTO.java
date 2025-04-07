package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_PRESUPUESTO_DS_ESP_AUX: 
* @descripción: Vista de presupuesto consolidado por división supervisora-especialidad
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPresupuestoDsEspAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_PRESUPUESTO_DS_ESP_AUX Secuencia: PGIM_SEQ_PRESUPUESTO_DS_ESP_AUX
  */
  private Long idPresupuestoDsEspAux;

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
  *MONTO_P1
  */
  private BigDecimal montoP1;

  /*
  *MONTO_P2
  */
  private BigDecimal montoP2;

  /*
  *MONTO_P3
  */
  private BigDecimal montoP3;

  /*
  *MONTO_P4
  */
  private BigDecimal montoP4;

  /*
  *MONTO_P5
  */
  private BigDecimal montoP5;

  /*
  *MONTO_P6
  */
  private BigDecimal montoP6;

  /*
  *TOTAL_MONTO
  */
  private BigDecimal totalMonto;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}