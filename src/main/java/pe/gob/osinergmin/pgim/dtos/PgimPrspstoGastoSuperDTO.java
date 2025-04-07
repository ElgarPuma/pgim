package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_PRSPSTO_GASTO_SUPER: 
* @descripción: Vista para reporte ad-hoc de presupuesto y gasto de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPrspstoGastoSuperDTO {

  /*
  *Identificador interno de la vista PGIM_VW_PRSPSTO_GASTO_SUPER. Secuencia: PGIM_SEQ_ID_CONTRATO_AUX
  */
  private Long idContratoAux;

  /*
  *ID_CONTRATO
  */
  private Long idContrato;

  /*
  *NU_CONTRATO
  */
  private String nuContrato;

  /*
  *FE_INICIO_CONTRATO
  */
  private Date feInicioContrato;

  /*
  *FE_INICIO_CONTRATO
  */
  private String feInicioContratoDesc;

  /*
  *ID_EMPRESA_SUPERVISORA
  */
  private Long idEmpresaSupervisora;

  /*
  *ID_PERSONA
  */
  private Long idPersona;

  /*
  *NU_RUC_EMPRESA_SUPERVISORA
  */
  private String nuRucEmpresaSupervisora;

  /*
  *DE_EMPRESA_SUPERVISORA
  */
  private String deEmpresaSupervisora;

  /*
  *MO_IMPORTE_CONTRATO
  */
  private BigDecimal moImporteContrato;

  /*
  *MO_CONSUMO_CONTRATO
  */
  private BigDecimal moConsumoContrato;

  /*
  *MO_SALDO
  */
  private BigDecimal moSaldo;

  /*
  *MO_ACTA_SUPERVISION
  */
  private BigDecimal moActaSupervision;

  /*
  *NU_ACTA_SUPERVISION
  */
  private Long nuActaSupervision;

  /*
  *MO_INFORME_SUPERVISION
  */
  private BigDecimal moInformeSupervision;

  /*
  *NU_INFORME_SUPERVISION
  */
  private Long nuInformeSupervision;

  /*
  *MO_SUPERVISION_FALLIDA
  */
  private BigDecimal moSupervisionFallida;

  /*
  *NU_SUPERVISION_FALLIDA
  */
  private Long nuSupervisionFallida;

  /*
  *MO_INFORME_GESTION
  */
  private BigDecimal moInformeGestion;

  /*
  *NU_INFORME_GESTION
  */
  private Long nuInformeGestion;

  /*
  *DESC_NU_ANIO
  */
  private Long descNuAnio;

  /*
  *DESC_NO_ARCHIVO
  */
  private String descNoArchivo;

  /*
  *DESC_EXTENSION
  */
  private String descExtension;

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