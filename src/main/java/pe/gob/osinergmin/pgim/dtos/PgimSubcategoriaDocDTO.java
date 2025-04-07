package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
* DTO para la entidad PGIM_TM_SUBCATEGORIA_DOC: 
* @descripción: Subcategoría de documento
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSubcategoriaDocDTO {

  /*
  *Identificador interno de la subcategoría de documento. Secuencia: PGIM_SEQ_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocumento;

  /*
  *Identificador interno de la categoría de documento. Tabla padre: PGIM_TM_CATEGORIA_DOC
  */
  private Long idCategoriaDocumento;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ORIGEN_DOCUMENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoOrigenDocumento;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_FIRMA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoFirma;

  /*
  *Tipo de extrensión a utiizar para la generación de documentos. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_EXTENSION_DOC. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoExtensionGen;

  /*
  *Código de la subcategoría de documento
  */
  private String coSubcatDocumento;

  /*
  *Nombre de la subcategoría de documento
  */
  private String noSubcatDocumento;

  /*
  *Flag que indica si el Siged numerará el documento. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flNumeradoPorSiged;

  /*
  *Flag que indica si la subcategoría de documento es notificable electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flNotificableE;

  /*
  *Identificador interno del tipo de documento Siged. Este valor se utiliza para crear un nuevo documento en el Siged a partir de las acciones de adjuntar o incluir lanzadas desde la PGIM. Si es que tiene no tiene valor entonces no podrá adjuntarse esta subcategoría en el Siged
  */
  private Long coTipoDocumentoSiged;

  /*
  *Nombre de las extensiones permitidas al momento de adjuntar un documento
  */
  private String deExtensionesPermitidas;

  /*
  *Indicador que señala si el/la fiscalizador/a firmará o no el documento. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String flFirmaFiscalizador;

  /*
  *Flag que indica si la subcategoría permite la reserva de número Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flReservaNumero;

  /*
  *Flag que indica si el Siged numerará el documento. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String descFlNumeradoPorSiged;

  /*
  *Flag - sobre escribe el comportamiento determinado para FL_ADJUNTADO_MANUAL - que indica si la PGIM se permite o no el adjuntado o inclusión manual de la subcategoría documental en el paso. Los posibles valores son: "1" = Sí y "0" = No..
  */
  private String descFlAdjuntadoManualPropia;

  /*
  *Flag que indica si la PGIM se permite o no el adjuntado o inclusión manual de la subcategoría documental en el paso. Los posibles valores son: "1" = Sí y "0" = No.
  */
  private String DescFlAdjuntadoManual;

  /*
  *Nombre del archivo que se utilizará para adjuntar la reserva de la numeración. Debe tener valor cuando la columna FL_RESERVA_NUMERO = "1"';
  */
  private String noArchivoReserva;
  
  /*
  *Flag que indica si la notificación de esta subcategoría documental permitirá realizarla por archivo; es decir, permitir seleccionar un subconjunto de archivos del documento (principal y/o adicionales) para ser notificados. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flPermiteNotifPorArchivo;

  /**
   * Flag que indica si la subcategoría se puede adjuntar desde la gestión bibliográfica de la PGIM.Posibles valores: "1" = Sí y 0 = "No"
   */
  private String flBibiografia;

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
   * Id de fase proceso para incluir
   */
  private Long descIdFaseProceso;

  /*
  *Nombre del tipo de extensión a generarse para la subcategoría
  */
  private String descNoTipoExtensionGen;

  /*
  *Código de la categoría de documento
  */
  private String descCoCategoriaDocumento;

  /*
  *Nombre de la categoría de documento
  */
  private String descNoCategoriaDocumento;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}