package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_ANTECEDENTE_AUX: 
* @descripción: Vista para la lista de antecedentes detallado
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAntecedenteAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_ANTECEDENTES_AUX Secuencia: PGIM_SEQ_ANTECEDENTES_AUX
  */
  private Long idAntecedenteSupervAux;

  /*
  *ID_ANTECEDENTE_SUPERV
  */
  private Long idAntecedenteSuperv;

  /*
  *ID_SUPERVISION
  */
  private Long idSupervision;

  /*
  *ID_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *DE_ASPECTOS_REVISADOS
  */
  private String deAspectosRevisados;

  /*
  *ID_TIPO_ANTECEDENTE
  */
  private Long idTipoAntecedente;

  /*
  *NO_TIPO_ANTECEDENTE
  */
  private String noTipoAntecedente;

  /*
  *ID_TIPO_ORIGIN
  */
  private Long idTipoOrigin;

  /*
  *NO_TIPO_ORIGIN
  */
  private String noTipoOrigin;

  /*
  *ID_CATEGORIA_DOCUMENTO
  */
  private Long idCategoriaDocumento;

  /*
  *NO_CATEGORIA_DOCUMENTO
  */
  private String noCategoriaDocumento;

  /*
  *ID_SUBCAT_DOCUMENTO
  */
  private Long idSubcatDocumento;

  /*
  *NO_SUBCAT_DOCUMENTO
  */
  private String noSubcatDocumento;

  /*
  *CO_DOCUMENTO_SIGED
  */
  private Long coDocumentoSiged;

  /*
  *DE_ASUNTO_DOCUMENTO
  */
  private String deAsuntoDocumento;

  /*
  *ID_INSTANCIA_PROCESO
  */
  private Long idInstanciaProceso;

  /*
  *NU_EXPEDIENTE_SIGED
  */
  private String nuExpedienteSiged;

  /*
  *COTABLAINSTANCIA
  */
  private Long cotablainstancia;

  /*
  *FECHA
  */
  private String fecha;

  /*
  *CODIGO
  */
  private String codigo;

  /*
  *DESCRIPCION
  */
  private String descripcion;

  /**
   * CO_DOCUMENTO_SIGED_P_COPIA
   * Identificador interno del documento Siged desde el que se copio el actual documento
   */
  private Long coDocumentoSigedPCopia;

  /*
  *TIPO_SUPERVISION
  */
  private String tipoSupervision;

  /*
  *NOMBRE_ARCHIVO
  */
  private String nombreArchivo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}