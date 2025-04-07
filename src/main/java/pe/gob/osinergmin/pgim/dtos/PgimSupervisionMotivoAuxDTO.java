package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_SUPERVISION_MOTIVO_AUX: 
* @descripción: Vista de motivos para una fiscalización no programada
*
* @author: promero
* @version: 1.0
* @fecha_de_creación: 24/02/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSupervisionMotivoAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_SUPERVISION_MOTIVO_AUX. 
  */
  private Long idMotivoAux;

  /*
  *ID_MOTIVO
  */
  private Long idMotivo;

  /*
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *ID_TIPO_MOTIVO
  */
  private Long idTipoMotivo;

  /*
  *DE_TIPO_MOTIVO
  */
  private String deTipoMotivo;  

  /*
  *DE_MOTIVO
  */
  private String deMotivo;

  /*
  *FE_MOTIVO
  */
  private Date feMotivo;

  /*
  *FE_MOTIVO
  */
  private String feMotivoDesc;

  /*
  *CO_MOTIVO
  */
  private String coMotivo;

  /*
  *DE_SUBTIPO_MOTIVO
  */
  private String deSubtipoMotivo;

  /*
  *ID_SUBTIPO_MOTIVO
  */
  private Long idSubtipoMotivo;

  /*
  *ID_INSTANCIA_PROCESO_MOTIVO
  */
  private Long idInstanciaProcesoMotivo;

  /*
  *ID_SUPERVISION_MOTIVO
  */
  private Long idSupervisionMotivo; 

  /*
  *ID_SUPERVISION
  */
  private Long idSupervision;

  /*
  *ID_OBJETO_MOTIVO_INICIO
  */
  private Long idObjetoMotivoInicio;

  /*
  *ID_TIPO_MOTIVO_INICIO
  */
  private Long idTipoMotivoInicio;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;
  
  /*
  * Id de la instancia de paso de la medida administrativa
  */
  private Long descIdInstanciaPasoMa;


}