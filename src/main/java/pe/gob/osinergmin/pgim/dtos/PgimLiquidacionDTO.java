package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

import java.util.List;

/**
* DTO para la entidad PGIM_TC_LIQUIDACION: 
* @descripción: Liquidación del contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimLiquidacionDTO {

  /*
  *Identificador interno de la liquidación. Secuencia: PGIM_SEQ_LIQUIDACION
  */
  private Long idLiquidacion;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
  private Long idContrato;

  /*
  *Tipo de entregable. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ENTREGABLE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoEntregable;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idDivisionSupervisora;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
  private Long idMotivoSupervision;

  /*
  *Número de la liquidación
  */
  private String nuLiquidacion;

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
  *Identificador interno de la especialidad.
  */
  private Long descIdEspecialidad;

  /*
  *Número de contrato
  */
  private String descNuContrato;

  /*
  *Nombre valor parametro
  */
  private String descNoValorParametro;

  /*
  *Razón social de la empresa supervisora
  */
  private String descNoRazonSocial;

  /*
  *Número de expediente Siged
  */
  private String descNuExpedienteSiged;

  /*
  *Número de expediente Siged del contrato
  */
  private String descNuExpedienteSigedContrato;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long descIdRelacionPaso;

  /*
  *Mensaje de la instancia de paso del proceso
  */
  private String descDeMensaje;

  /*
  *Flag de mis asignaciones
  */
  private String descFlagMisAsignaciones;

  /*
  *Nombre completo de la persona destino
  */
  private String descPersonaDestino;

  /*
  *Usuario (Windows) de la persona responsable
  */
  private String descUsuarioAsignado;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Código de la supervisión
  */
  private String descCoSupervision;

  /*
  *Descripción del subtipo de la supervisión
  */
  private String descDeSubtipoSupervision;

  /*
  *Monto en S/ del consumo del contrato
  */
  private BigDecimal descMoConsumoContrato;

  /*
  *Monto en S/ del consumo del entregable del contrato asociado al ítem de consumo
  */
  private BigDecimal descMoItemConsumo;

  /*
  *Identificador interno del documento asociado al ítem de consumo correspondiente a un tipo de entregable
  */
  private Long descIdDocumento;

  /*
  *Fecha de origen del documento asociado al entregable
  */
  private Date descFeOrigenDocumento;

  /*
  *Fecha de origen del documento asociado al entregable
  */
  private String descFeOrigenDocumentoDesc;

  /*
  *Fecha de la instancia de paso
  */
  private Date descFeInstanciaPaso;

  /*
  *Fecha de la instancia de paso
  */
  private String descFeInstanciaPasoDesc;

  /*
  *Identificador interno del ítem de consumo
  */
  private Long descIdItemConsumo;

  /*
  *Identificador interno de la supervisión
  */
  private Long descIdSupervision;

  /*
  *Código del tipo de documento Siged
  */
  private Long descCoDocumentoSiged;

  /*
  *Asunto del documento relacionado con el entregable
  */
  private String descDeAsuntoDocumento;

  /*
  *Identificador interno de la persona del contrato como destinatario.
  */
  private Long descIdPersonalContrato;

  /*
  *Nombre de la división supervisora a cargo de la supervisión de la unidad minera asociada a la supervisión.
  */
  private String descDeDivisionSupervisora;

  /*
  *Nombre del tipo de la supervisión
  */
  private String descDeTipoSupervision;

  /*
  *Descripción del motivo de la supervisión
  */
  private String descDeMotivoSupervision;

  /*
  *Lista de entregables para la liquidación
  */
  private List<PgimEntregableLiquidaAuxDTO> descListaEntregables;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * descIdInstanciaPaso
   */
  private Long descIdInstanciaPaso;

  /*
  *Flag que indica si existe penalidad "Vinculada al reemplazo del personal". Posibles valores: "1" = Sí y (0 o NULL) = "No".
  */
  private String flPenalidadReemplazoPersona;

  /*
  *Penalidad vinculada al reemplazo del personal.
  */
  private BigDecimal moPenalidadReemplazoPersona;
}