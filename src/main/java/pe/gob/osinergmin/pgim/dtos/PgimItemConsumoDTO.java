package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_ITEM_CONSUMO: 
* @descripción: Ítem del consumo del contrato de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimItemConsumoDTO {

  /*
  *Identificador interno del ítem del consumo del contrato. Secuencia: PGIM_SEQ_ITEM_CONSUMO
  */
  private Long idItemConsumo;

  /*
  *Identificador interno del consumo del contrato. Tabla padre: PGIM_TD_CONSUMO_CONTRA
  */
  private Long idConsumoContra;

  /*
  *Tipo de entregable. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ENTREGABLE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoEntregable;

  /*
  *Tipo de estadío del consumo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADIO_CONSUMO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idTipoEstadioConsumo;

  /*
  *Porcentaje del costo para el entregable del tipo dado. Se copian los valores de las columna PC_ENTREGABLE_ACTA, PC_ENTREGABLE_INFORME, PC_ENTREGABLE_FINAL de la tabla PGIM_TC_CONTRATO, según corresponda
  */
  private Long pcEntregable;

  /*
  *Monto del ítem del consumo del contrato
  */
  private BigDecimal moItemConsumo;

  /*
  *Monto de la penalidad para el ítem
  */
  private BigDecimal moItemPenalidad;

  /*
  *Monto de la supervisión fallida
  */
  private BigDecimal moItemSupervisionFallida;

  /*
  *Es vigente el consumo del contrato. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String esVigente;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Usuario creador
  */
  private String usCreacion;

  /*
  *Terminal de creación
  */
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /*
  *Usuario modificador
  */
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private Date feActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  *DESC_TIPO_ENTREGABLE
  */
  private String descTipoEntregable;

  /*
  *DESC_ESTADIO_CONSUMO
  */
  private String descEstadioConsumo;

  /*
  *Identificador del tipo de entregable
  */
  private Long descIdTipoEntregable;

  /*
  *Descripción del tipo de entregable
  */
  private String descDeTipoEntregable;

  /*
  *Identificador de la supervisión
  */
  private Long descIdSupervision;

  /*
  *Código de la supervisión
  */
  private String descCoSupervision;

  /*
  *Descripción del tipo de la supervisión
  */
  private String descDeTipoSupervision;

  /*
  *Identificador del subtipo de la supervisión
  */
  private Long descIdSubtipoSupervision;

  /*
  *Descripción del subtipo de la supervisión
  */
  private String descDeSubtipoSupervision;

  /*
  *Descripción del subtipo de la supervisión
  */
  private Long descIdMotivoSupervision;

  /*
  *Descripción del motivo de la supervisión
  */
  private String descDeMotivoSupervision;

  /*
  *Fecha de inicio real de la supervisión
  */
  private Date descFeInicioSupervisionReal;

  /*
  *Fecha de inicio real de la supervisión
  */
  private String descFeInicioSupervisionRealDesc;

  /*
  *Fecha fin real de la supervisión
  */
  private Date descFeFinSupervisionReal;

  /*
  *Fecha fin real de la supervisión
  */
  private String descFeFinSupervisionRealDesc;

  /*
  *Identificador de agente supervisado
  */
  private Long descIdAgenteSupervisado;

  /*
  *Número de documento de identidad
  */
  private String descCoDocumentoIdentidad;

  /*
  *Nombre de la razón social
  */
  private String descNoRazonSocial;

  /*
  *Monto del consumo del contrato
  */
  private BigDecimal descMoConsumoContrato;

  /*
  *Identificador del contrato
  */
  private Long descIdContrato;

  /*
  *Número de contrato
  */
  private String descNuContrato;

  /*
  *Identificador de la división supervisora
  */
  private Long descIdDivisionSupervisora;

  /*
  *Nombre de la división supervisora
  */
  private String descNoDivisionSupervisora;

  /*
  *Código de la unidad minera
  */
  private String descCoUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Número de expediente Siged
  */
  private String descNuExpedienteSiged;

  /*
  *Identificador del tipo de estadio del consumo del contrato
  */
  private Long descIdTipoEstadioConsumo;

  /*
  *Nombre del tipo de estadio del consumo del contrato
  */
  private String descNoTipoEstadioConsumo;

  /*
  *Identificador pre-comprometido del entregable del contrato
  */
  private Long descPreComprometido;

  /*
  *Identificador del estadio comprometido del entregable del contrato
  */
  private Long descComprometido;

  /*
  *Identificador del estadio por-liquidar del entregable del contrato
  */
  private Long descPorLiquidar;

  /*
  *Identificador del estadio liquidado del entregable del contrato
  */
  private Long descLiquidado;

  /*
  *Identificador del estadio facturado del entregable del contrato
  */
  private Long descFacturado;

  /*
  *DESC_ID_TIPO_SUPERVISION
  */
  private Long descIdTipoSupervision;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}