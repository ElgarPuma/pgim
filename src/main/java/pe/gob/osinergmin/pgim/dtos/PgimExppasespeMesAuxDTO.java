package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_EXPPASESPE_MES_AUX: 
* @descripción: Vista agregada de la cantidad de expedientes de fiscalización notificados de los últimos 12 meses
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimExppasespeMesAuxDTO {

  /*
  *Identificador interno de la especialidad. Secuencia: PGIM_SEQ_ESPECIALIDAD_AUX
  */
  private BigDecimal idEspecialidadAux;

  /*
  *ID_ESPECIALIDAD
  */
  private BigDecimal idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

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
  *NRO_EXP_PAS_P7
  */
  private BigDecimal nroExpPasP7;

  /*
  *NRO_EXP_PAS_P8
  */
  private BigDecimal nroExpPasP8;

  /*
  *NRO_EXP_PAS_P9
  */
  private BigDecimal nroExpPasP9;

  /*
  *NRO_EXP_PAS_P10
  */
  private BigDecimal nroExpPasP10;

  /*
  *NRO_EXP_PAS_P11
  */
  private BigDecimal nroExpPasP11;

  /*
  *NRO_EXP_PAS_P12
  */
  private BigDecimal nroExpPasP12;

  /*
  *TOTAL_ESPECIALIDAD
  */
  private BigDecimal totalEspecialidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}