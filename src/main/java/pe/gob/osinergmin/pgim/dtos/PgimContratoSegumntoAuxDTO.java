package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_CONTRATO_SEGUMNTO_AUX: 
* @descripción: Vista de seguimiento de contratos
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimContratoSegumntoAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_CONTRATO_SEGUMNTO_AUX Secuencia: PGIM_SEQ_CONTRATO_SEGUMNTO_AUX
  */
  private Long idContratoSegumntoAux;

  /*
  *ID_CONTRATO
  */
  private Long idContrato;

  /*
  *NU_CONTRATO
  */
  private String nuContrato;

  /*
  *ID_EMPRESA_SUPERVISORA
  */
  private Long idEmpresaSupervisora;

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
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *MO_IMPORTE_CONTRATO
  */
  private BigDecimal moImporteContrato;

  /*
  *SALDO_CONTRATO
  */
  private BigDecimal saldoContrato;

  /*
  *PRE_COMPROMETIDO
  */
  private BigDecimal preComprometido;

  /*
  *COMPROMETIDO
  */
  private BigDecimal comprometido;

  /*
  *POR_LIQUIDAR
  */
  private BigDecimal porLiquidar;

  /*
  *LIQUIDADO
  */
  private BigDecimal liquidado;

  /*
  *FACTURADO
  */
  private BigDecimal facturado;

  /*
  *TOTAL_CONSUMO_CONTRATO
  */
  private BigDecimal totalConsumoContrato;

  /*
  *SALDO_PRECOMPROMETIDO
  */
  private BigDecimal saldoPrecomprometido;

  /*
  *SALDO_COMPROMETIDO
  */
  private BigDecimal saldoComprometido;

  /*
  *SALDO_POR_LIQUIDAR
  */
  private BigDecimal saldoPorLiquidar;

  /*
  *SALDO_LIQUIDADO
  */
  private BigDecimal saldoLiquidado;

  /*
  *SALDO_FACTURADO
  */
  private BigDecimal saldoFacturado;

  /*
  *DESC_EXTENSION
  */
  private String descExtension;

  /*
  *DESC_NO_ARCHIVO
  */
  private String descNoArchivo;

  /*
  *DE_TITULO_REPORTE
  */
  private String deTituloReporte;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}