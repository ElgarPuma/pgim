package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_ENTREGABLE_LIQUIDA_AUX: 
* @descripción: Vista para obtener la lista de entregables de la supervisión, a saber: 346: Actas de supervisión; 347: Informes de supervisión; 359: Informes de supervisión fallida
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEntregableLiquidaAuxDTO {

  /*
  *Identificador interno de la liquidación AUX. Secuencia: PGIM_SEQ_ENTRE_LIQ_AUX
  */
  private Long idEntregableLiquidaAux;

  /*
  *ID_ITEM_CONSUMO
  */
  private Long idItemConsumo;

  /*
  *ID_TIPO_ENTREGABLE
  */
  private Long idTipoEntregable;

  /*
  *DE_TIPO_ENTREGABLE
  */
  private String deTipoEntregable;

  /*
  *ID_SUPERVISION
  */
  private Long idSupervision;

  /*
  *CO_SUPERVISION
  */
  private String coSupervision;

  /*  
  *ID_TIPO_SUPERVISION
  */  

  private Long idTipoSupervision;

  /*
  *DE_TIPO_SUPERVISION
  */
  private String deTipoSupervision;

  /*
  *ID_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *DE_SUBTIPO_SUPERVISION
  */
  private String deSubtipoSupervision;

  /*
  *ID_MOTIVO_SUPERVISION
  */
  private Long idMotivoSupervision;

  /*
  *DE_MOTIVO_SUPERVISION
  */
  private String deMotivoSupervision;

  /*
  *FE_INICIO_SUPERVISION_REAL
  */
  private Date feInicioSupervisionReal;

  /*
  *FE_INICIO_SUPERVISION_REAL
  */
  private String feInicioSupervisionRealDesc;

  /*
  *FE_FIN_SUPERVISION_REAL
  */
  private Date feFinSupervisionReal;

  /*
  *FE_FIN_SUPERVISION_REAL
  */
  private String feFinSupervisionRealDesc;

  /*
  *ID_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *CO_DOCUMENTO_IDENTIDAD
  */
  private String coDocumentoIdentidad;

  /*
  *NO_RAZON_SOCIAL
  */
  private String noRazonSocial;

  /*
  *MO_CONSUMO_CONTRATO
  */
  private BigDecimal moConsumoContrato;

  /*
  *MO_ITEM_CONSUMO
  */
  private BigDecimal moItemConsumo;

  /*
  *ID_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *DE_ASUNTO_DOCUMENTO
  */
  private String deAsuntoDocumento;

  /*
  *ID_SUBCAT_DOCUMENTO
  */
  private Long idSubcatDocumento;

  /*
  *CO_SUBCAT_DOCUMENTO
  */
  private String coSubcatDocumento;

  /*
  *NO_SUBCAT_DOCUMENTO
  */
  private String noSubcatDocumento;

  /*
  *Mensaje de la instancia de paso del proceso
  */
  private String deMensaje;

  /*
  *FE_ORIGEN_DOCUMENTO
  */
  private Date feOrigenDocumento;

  /*
  *FE_ORIGEN_DOCUMENTO
  */
  private String feOrigenDocumentoDesc;

  /*
  *FE_INSTANCIA_PASO
  */
  private Date feInstanciaPaso;

  /*
  *FE_INSTANCIA_PASO
  */
  private String feInstanciaPasoDesc;

  /*
  *ID_CONTRATO
  */
  private Long idContrato;

  /*
  *NU_CONTRATO
  */
  private String nuContrato;

  /*
  *ID_DIVISION_SUPERVISORA
  */
  private Long idDivisionSupervisora;

  /*
  *DE_DIVISION_SUPERVISORA
  */
  private String deDivisionSupervisora;

  /*
  *CO_UNIDAD_MINERA
  */
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
  private String noUnidadMinera;

  /*
  *NU_EXPEDIENTE_SIGED
  */
  private String nuExpedienteSiged;

  /*
  *ID_TIPO_ESTADIO_CONSUMO
  */
  private Long idTipoEstadioConsumo;

  /*
  *DE_TIPO_ESTADIO_CONSUMO
  */
  private String deTipoEstadioConsumo;
  
  /*
  * FE_PRESENTA_ENTREGABLE
  */
  private Date fePresentaEntregable;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}