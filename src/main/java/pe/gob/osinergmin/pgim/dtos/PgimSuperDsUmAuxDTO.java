package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_SUPER_DS_UM_AUX: 
* @descripción: Vista de cantidad de supervisiones por división supervisora y unidad minera
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSuperDsUmAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_SUPER_DS_UM_AUX Secuencia: PGIM_SEQ_SUPER_DS_UM_AUX
  */
  private Long idSuperDsUmAux;

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
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
  private String noUnidadMinera;

  /*
  *ID_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *ID_PERSONA
  */
  private Long idPersona;

  /*
  *NU_RUC
  */
  private String nuRuc;

  /*
  *NO_RAZON_SOCIAL
  */
  private String noRazonSocial;

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