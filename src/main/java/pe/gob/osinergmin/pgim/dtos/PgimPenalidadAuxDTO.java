package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_PENALIDAD_AUX: 
* @descripción: Vista de penalidades del contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPenalidadAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_SUPER_DS_UM_AUX Secuencia: PGIM_SEQ_SUPER_DS_UM_AUX
  */
  private Long idPenalidadAux;

  /*
  *ID_LIQUIDACION
  */
  private Long idLiquidacion;

  /*
  *NU_LIQUIDACION
  */
  private String nuLiquidacion;

  /*
  *ID_CONTRATO
  */
  private Long idContrato;

  /*
  *NU_CONTRATO
  */
  private String nuContrato;

  /*
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *FE_CREACION
  */
  private Date feCreacion;

  /*
  *FE_CREACION
  */
  private String feCreacionDesc;

  /*
  *ID_FASE_ACTUAL
  */
  private Long idFaseActual;

  /*
  *Nombre de la fase actual del proceso
  */
  private String noFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
  private Long idPasoActual;

  /*
  *Nombre del paso actual del proceso
  */
  private String noPasoActual;

  /*
  *MO_ITEM_CONSUMO
  */
  private BigDecimal moItemConsumo;

  /*
  *MO_PENALIDAD_PLAZO
  */
  private BigDecimal moPenalidadPlazo;

  /*
  *MO_PENALIDAD_REINCIDENCIA
  */
  private BigDecimal moPenalidadReincidencia;

  /*
  *MO_PENALIDAD_SIN_EPP
  */
  private BigDecimal moPenalidadSinEpp;

  /*
  *MO_PENALIDAD
  */
  private BigDecimal moPenalidad;

  /*
  *Razon social de la supervisora
  */
  private String descNoRazonSocial;

  /*
  *Nombre del archivo a exportar
  */
  private String descNoArchivo;

  /*
  *Extensión del archivo a exportar
  */
  private String descExtension;

  /*
  *Fecha inicio usado para el reporte de penalidades por periodo contrato y supervisora
  */
  private Date descFeInicio;

  /*
  *Fecha inicio usado para el reporte de penalidades por periodo contrato y supervisora
  */
  private String descFeInicioDesc;

  /*
  *Fecha fin usado para el reporte de penalidades por periodo contrato y supervisora
  */
  private Date descFeFin;

  /*
  *Fecha fin usado para el reporte de penalidades por periodo contrato y supervisora
  */
  private String descFeFinDesc;

  /*
  *Título del reporte
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