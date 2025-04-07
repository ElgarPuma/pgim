package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_MED_ADM_RPT_AUX: 
* @descripción: Vista para obtener la lista los PAS
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMedAdmRptAuxDTO {

  /*
  *Secuencia: PGIM_SEQ_ID_M_ADM_AUX
  */
  private Long idMedidaAdministrativaAux;

  /*
  *ID_MEDIDA_ADMINISTRATIVA
  */
  private Long idMedidaAdministrativa;

  /*
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *ID_TIPO_MEDIDA_ADMINISTRATIVA
  */
  private Long idTipoMedidaAdministrativa;

  /*
  *DE_TIPO_MEDIDA_ADMINISTRATIVA
  */
  private String deTipoMedidaAdministrativa;

  /*
  *DE_MEDIDA_ADMINISTRATIVA
  */
  private String deMedidaAdministrativa;

  /*
  *FE_MEDIDA_ADMINISTRATIVA
  */
  private Date feMedidaAdministrativa;

  /*
  *FE_MEDIDA_ADMINISTRATIVA
  */
  private String feMedidaAdministrativaDesc;

  /*
  *NU_EXPEDIENTE_SIGED
  */
  private String nuExpedienteSiged;

  /*
  *DESC_CO_MEDIDA_ADMINISTRATIVA
  */
  private String descCoMedidaAdministrativa;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}