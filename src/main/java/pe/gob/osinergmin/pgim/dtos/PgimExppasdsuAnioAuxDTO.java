package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_EXPPASDSU_ANIO_AUX: 
* @descripción: Vista agregada de la cantidad de expedientes de fiscalización por división supeprvisora, fase y año
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimExppasdsuAnioAuxDTO {

  /*
  *Identificador interno de los expedientes PAS por división superisora. Secuencia: PGIM_SEQ_DIV_SUPERVISORA_AUX
  */
  private BigDecimal idDivisionSupervisoraAux;

  /*
  *ID_DIVISION_SUPERVISORA
  */
  private BigDecimal idDivisionSupervisora;

  /*
  *NO_DIVISION_SUPERVISORA
  */
  private String noDivisionSupervisora;

  /*
  *NU_ORDEN
  */
  private BigDecimal nuOrden;

  /*
  *ID_FASE_PROCESO
  */
  private BigDecimal idFaseProceso;

  /*
  *NO_FASE_PROCESO
  */
  private String noFaseProceso;

  /*
  *NRO_EXP_PAS_P1
  */
  private BigDecimal nroExpPasP1;

  /*
  *NRO_EXP_PAS_P2
  */
  private BigDecimal nroExpPasP2;

  /*
  *NRO_EXP_PAS_P3
  */
  private BigDecimal nroExpPasP3;

  /*
  *NRO_EXP_PAS_P4
  */
  private BigDecimal nroExpPasP4;

  /*
  *NRO_EXP_PAS_P5
  */
  private BigDecimal nroExpPasP5;

  /*
  *NRO_EXP_PAS_P6
  */
  private BigDecimal nroExpPasP6;

  /*
  *TOTAL_FASE
  */
  private BigDecimal totalFase;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}