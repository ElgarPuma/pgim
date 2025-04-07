package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_ITEM_LIQUIDACION: 
* @descripción: Ítem de la liquidación del contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimItemLiquidacionDTO {

  /*
  *Identificador interno de la liquidación. Secuencia: PGIM_SEQ_ITEM_LIQUIDACION
  */
  private Long idItemLiquidacion;

  /*
  *Identificador interno de la liquidación. Tabla padre: PGIM_TC_LIQUIDACION
  */
  private Long idLiquidacion;

  /*
  *Identificador interno del Ítem del consumo del contrato. Tabla padre: PGIM_TD_ITEM_CONSUMO
  */
  private Long idItemConsumo;

  /*
  *Identificador interno del documento relacionado con el entregable. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *Penalidad por incumplimiento de plazo
  */
  private BigDecimal moPenalidadPlazo;

  /*
  *Penalidad por reincidencia de observaciones
  */
  private BigDecimal moPenalidadReincidencia;

  /*
  *Penalidad por presentarse sin EPP
  */
  private BigDecimal moPenalidadSinEpp;

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
  *Monto de penalidad "Por usar equipos de protección personal (EPP) del agente fiscalizado".
  */
  private BigDecimal moPenalidadEppAFiscalizado;

  /*
  *Monto de penalidad "Sin contar con equipos".
  */
  private BigDecimal moPenalidadSinEquipos;

  /*
  *Monto de penalidad "Sin contar con instrumentos de medición".
  */
  private BigDecimal moPenalidadSinInstrumentos;

  /*
  *Monto de penalidad "Contar con equipos defectuosos".
  */
  private BigDecimal moPenalidadEqpDefectuosos;
  /*
  *Monto de penalidad "Contar con instrumentos de medición defectuosos".
  */
  private BigDecimal moPenalidadEqpMedicionDefectuosos;
  /*
  *Monto de penalidad "Equipos sin certificado de calibración vigente".
  */
  private BigDecimal moPenalidadEqpCalibrNvigente;
  /*
  *Monto de penalidad "Instrumentos de medición sin certificado de calibración vigente".
  */
  private BigDecimal moPenalidadInstrCalibrNvigente;
  /*
  *Monto de penalidad "Por alterar los formatos de las actas proporcionados".
  */
  private BigDecimal moPenalidadAlterarFormatos;
  /*
  *Monto de penalidad "Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)".
  */
  private BigDecimal moPenalidadFrustrarFiscalizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Razón social del agente supervisado dueño de la unidad minera
  */
  private String descNoRazonSocial;

  /*
  *Identificador interno de la supervisión
  */
  private Long descIdSupervision;

  /*
  *Código de la supervisión
  */
  private String descCoSupervision;

  /*
  *Descripción del subtipo de la supervisión
  */
  private String descDeSubtipoSupervision;

  /*
  *Nombre valor parametro
  */
  private String descNoValorParametro;

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
  *Número del expediente Siged
  */
  private String descNuExpedienteSiged;

  /*
  *Identificador interno del contrato
  */
  private Long descIdContrato;

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
  *Asunto del documento relacionado con el entregable
  */
  private String descDeAsuntoDocumento;

  /*
  *División de supervisión
  */
  private String descDivisionSupervision;

  /*
   * Monto de otras penalidades
   */
  private BigDecimal descOtrasPenalidades;

  /*
   * Monto subtotal de fiscalización
   */
  private BigDecimal descSubTotalPenalidades;

  /*
  *Cantidad de días de la supervisión
  */
  private Long cantidadDias;

  /*
  *Id instancia del proceso
  */
  private Long descIdInstanciaProceso;

  /*
  *Descripción del motivo de la supervisión
  */
  private String descDeMotivoSupervision;

  /*
  *Total de penalidades
  */
  private BigDecimal descTotalPenalidades;
  
  /*
  *Descripción del tipo de entregable
  */
  private String descDeTipoEntregable;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
    *Flag que indica si el registro de la fiscalización es pre-existente a la implementación de los nuevos tipos de penalidad. Posibles valores: "1" = Sí y (0 o NULL) = "No". Cuando el valor es "1" solo aplica para aquellas fiscalizaciones que hayan transitado por «Revisar y aprobar informe de fiscalización» ==> «Elaborar ficha de evaluación de hechos verificados v1, MCAF y OCAF»
   */
  private String descFlPreexistenteNpenalidades;
}