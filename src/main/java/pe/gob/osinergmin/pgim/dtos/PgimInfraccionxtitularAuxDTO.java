package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_INFRACCIONXTITULAR_AUX: 
* @descripción: Vista de todos los agentes supervisados y sus infracciones en seis años
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInfraccionxtitularAuxDTO {

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_INFRACXTITULAR_AUX
  */
  private Long idAgenteSupervisadoAux;

  /*
  *ID_PERSONA
  */
  private Long idPersona;

  /*
  *CO_DOCUMENTO_IDENTIDAD
  */
  private String coDocumentoIdentidad;

  /*
  *NO_RAZON_SOCIAL
  */
  private String noRazonSocial;

  /*
  *NO_CORTO_AGENTE_SUPERVISADO
  */
  private String noCortoAgenteSupervisado;

  /*
  *ID_ESTRATO
  */
  private Long idEstrato;

  /*
  *NO_ESTRATO
  */
  private String noEstrato;

  /*
  *NO_CORTO_ESTRATO
  */
  private String noCortoEstrato;

  /*
  *NRO_INFRACCION_P1
  */
  private Long nroInfraccionP1;

  /*
  *NRO_INFRACCION_P2
  */
  private Long nroInfraccionP2;

  /*
  *NRO_INFRACCION_P3
  */
  private Long nroInfraccionP3;

  /*
  *NRO_INFRACCION_P4
  */
  private Long nroInfraccionP4;

  /*
  *NRO_INFRACCION_P5
  */
  private Long nroInfraccionP5;

  /*
  *NRO_INFRACCION_P6
  */
  private Long nroInfraccionP6;

  /*
  *NRO_INFRACCION_TOTAL
  */
  private Long nroInfraccionTotal;

  /*
  *DESC_NU_INFRACCIONES_MIN
  */
  private Long descNuInfraccionesMin;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}