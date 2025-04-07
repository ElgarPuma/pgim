package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_DOCUMENTO_AUX: 
* @descripción: Vista de documentos por expediente
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimDocumentoAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_DOCUMENTOS_AUX Secuencia: PGIM_SEQ_DOCUMENTO_AUX
  */
  private Long idDocumentoAux;

  /*
  *NU_EXPEDIENTE_SIGED
  */
  private String nuExpedienteSiged;

  /*
  *NO_FASE_PROCESO
  */
  private String noFaseProceso;

  /*
  *CO_CATEGORIA_DOCUMENTO
  */
  private String coCategoriaDocumento;

  /*
  *NO_CATEGORIA_DOCUMENTO
  */
  private String noCategoriaDocumento;

  /*
  *CO_SUBCAT_DOCUMENTO
  */
  private String coSubcatDocumento;

  /*
  *NO_SUBCAT_DOCUMENTO
  */
  private String noSubcatDocumento;

  /*
  *DE_ASUNTO_DOCUMENTO
  */
  private String deAsuntoDocumento;

  /*
  *ID_TIPO_ORIGEN_DOCUMENTO
  */
  private Long idTipoOrigenDocumento;

  /*
  *NO_TIPO_ORIGEN_DOCUMENTO
  */
  private String noTipoOrigenDocumento;

  /*
  *NO_USUARIO
  */
  private String noUsuario;

  /*
  *FE_CREACION
  */
  private Date feCreacion;

  /*
  *FE_CREACION
  */
  private String feCreacionDesc;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}