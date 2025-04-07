package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_PAS_RPT_AUX: 
* @descripción: Vista para obtener la lista de PAS para reporte
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPasRptAuxDTO {

  /*
  *Secuencia: PGIM_SEQ_ID_PAS_AUX
  */
  private Long idPasAux;

  /*
  *ID_PAS
  */
  private Long idPas;

  /*
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

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
  *NO_TIPO_SUPERVISION
  */
  private String noTipoSupervision;

  /*
  *ID_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *DE_SUBTIPO_SUPERVISION
  */
  private String deSubtipoSupervision;

  /*
  *FE_INICIO_SUPERVISION
  */
  private Date feInicioSupervision;

  /*
  *FE_INICIO_SUPERVISION
  */
  private String feInicioSupervisionDesc;

  /*
  *FE_FIN_SUPERVISION
  */
  private Date feFinSupervision;

  /*
  *FE_FIN_SUPERVISION
  */
  private String feFinSupervisionDesc;

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
  *ETIQUETA_PASO_ACTUAL
  */
  private String etiquetaPasoActual;

  /*
  *NU_EXPEDIENTE_SIGED
  */
  private String nuExpedienteSiged;

  /*
  *FE_CREACION_PAS
  */
  private Date feCreacionPas;

  /*
  *FE_CREACION_PAS
  */
  private String feCreacionPasDesc;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}