package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_DEMARCACION_AUX: 
* @descripción: Vista para obtener las unidades mineras y sus correspondientes demarcaciones geográficas
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimDemarcacionAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_DEMARCACION_AUX. Secuencia: PGIM_SEQ_DEMARCACION_UM_AUX
  */
  private Long idDemarcacionUmAux;

  /*
  *Identicador interno de la demarcación de la UM
  */
  private Long idDemarcacionUm;

  /*
  *Identificador interno del agente fiscalizado
  */
  private Long idAgenteSupervisado;

  /*
  *Código del documento de identidad del agente fiscalizado
  */
  private String coDocumentoIdentidadAf;

  /*
  *Razón social del agente fiscalizado
  */
  private String noRazonSocialAf;

  /*
  *Identificador interno de la unidad minera
  */
  private Long idUnidadMinera;

  /*
  *Código de la unidad minera
  */
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String noUnidadMinera;

  /*
  *Identificador interno del tipo de la unidad minera
  */
  private Long idTipoUnidadMinera;

  /*
  *Nombre del tipo de la unidad minera
  */
  private String noTipoUnidadMinera;

  /*
  *Identificador interno de la división supervisora de la unidad minera
  */
  private Long idDivisionSupervisora;

  /*
  *Nombre de la división supervisora de la unidad minera
  */
  private String noDivisionSupervisora;

  /*
  *Identificador interno del ubigeo como departamento
  */
  private Long idDepartamentoDemarcacion;

  /*
  *Identificador interno del ubigeo como provincia
  */
  private Long idProvinciaDemarcacion;

  /*
  *Identificador interno del ubigeo como distrito
  */
  private Long idDistritoDemarcacion;

  /*
  *Nombre del departamento de la demarcación de la unidad minera
  */
  private String noDepartamentoDemarcacion;

  /*
  *Nombre de la provincia de la demarcación de la unidad minera
  */
  private String noProvinciaDemarcacion;

  /*
  *Nombre del distrito de la demarcación de la unidad minera
  */
  private String noDistritoDemarcacion;

  /*
  *Valor lógico que señala si la demarcación es o no principal para la unidad minera
  */
  private String flPrincipalDemarcacion;

  /*
  *Valor que señala si la demarcación es o no principal para la unidad minera
  */
  private String principalDemarcacion;

  /*
  *Valor en porcentaje que señala cuál es el porcentaje de la demarcación representa para la unidad minera
  */
  private BigDecimal pcDemarcacion;

  /*
  *RUC y razón social del agente fiscalizado
  */
  private String descRazonSocial;

  /*
  *Código y nombre de la unidad minera
  */
  private String descUnidadMinera;

  /*
  *Departamento, provincia y distrito del ubigeo
  */
  private String descUbigeo;

  /*
  *DESC_FL_DEMARCACION
  */
  private String descFlDemarcacion;

  /*
  *DESC_CON_DEMARCACION
  */
  private String descConDemarcacion;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}