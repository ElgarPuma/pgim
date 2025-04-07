package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.pgim.siged.Archivo;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TD_DOCUMENTO: 
* @descripción: Documento del proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimDocumentoDTO {

  /*
  *Identificador interno del documento. Secuencia: PGIM_SEQ_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocumento;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_ORIGEN_DOCUMENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idTipoOrigenDocumento;

  /*
  *Identificador interno de la fase del proceso en la que se adjuntó el documento. Tabla padre: PGIM_TM_FASE_PROCESO
  */
  private Long idFaseProceso;

  /*
  *Identificador interno del documento desde el que se copio el actual documento. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumentoPadreCopia;
  
  /*
  * Fecha en la que se ha rechazado el documento. Cuando tiene valor señala que el documento ha sido rechazado por su destinatario, cuando no, entonces no existe rechazo alguno.
  */
  private Date feRechazo;

  /*
  *Asunto del documento
  */
  private String deAsuntoDocumento;

  /*
  *Fecha del origen del documento. Cuando el ID_TIPO_ORIGEN_DOCUMENTO = 0 entonces se refiere a la recha de recepción, si es 1, entonces se refiere a la fecha de emisión 
  */
  private Date feOrigenDocumento;

  /*
  *Fecha del origen del documento. Cuando el ID_TIPO_ORIGEN_DOCUMENTO = 0 entonces se refiere a la recha de recepción, si es 1, entonces se refiere a la fecha de emisión 
  */
  private String feOrigenDocumentoDesc;

  /*
  *Identificador interno del documento Siged
  */
  private Long coDocumentoSiged;

  /*
  *Secuencia del documento utilizado para la codificación de la carpeta transitoria (carpeta en el servidor de la aplicación) de carga de sus archivos. Utiliza la secuencia PGIM_SEQ_GEN_DOCUMENTO
  */
  private Long seDocumento;

  /*
  *Flag que indica si el documento ya fue notificado al menos una vez. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flNotificado;

  /*
  *Fecha en la que se envió (asignó) el documento por primera vez de acuerdo con las relaciones pre-establecidas para el flujo de trabajo (tabla PGIM_TM_RELACION_SUBCAT). Cuando tiene el valor NULL significa que el documento aún no fue enviado; por otro lado, si ya tiene una fecha es la fecha de envío formal del documento.
  */
  private Date feEnvioDocumento;

  /*
  *Fecha en la que se envió (asignó) el documento por primera vez de acuerdo con las relaciones pre-establecidas para el flujo de trabajo (tabla PGIM_TM_RELACION_SUBCAT). Cuando tiene el valor NULL significa que el documento aún no fue enviado; por otro lado, si ya tiene una fecha es la fecha de envío formal del documento.
  */
  private String feEnvioDocumentoDesc;

  /*
  *Flag que indica si el documento ya fue notificado al menos una vez. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flReservaActiva;

  /*
  *Flag que indica si el documento ha sido firmado digitalmente o no. Los posibles valores son: "1" = Sí y "0" o NULL = No
  */
  private String flFirmadoDigitalmente;
  
  /*
   *Identificador interno del documento Siged desde el que se copio el actual documento
   */
   private Long coDocumentoSigedPCopia;

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
  *Código de la categoría de documento
  */
  private String coCategoriaDocumento;

  /*
  *Nombre de la categoría de documento
  */
  private String noCategoriaDocumento;

  /*
  *Código de la subcategoría de documento
  */
  private String coSubcatDocumento;

  /*
  *Nombre de la subcategoría de documento
  */
  private String noSubcatDocumento;

  /*
  *Nombre del tipo de documento Siged
  */
  private String nombreTipoDocumento;

  /*
  *Número de documento Siged
  */
  private String numeroDocumento;

  /*
  *Asunto del documento Siged
  */
  private String asuntoSiged;

  /*
  *Código del tipo de documento Siged
  */
  private Long coTipoDocumentoSiged;

  /*
  *Flag que indica si el Siged numerará el documento. Posibles valores: "S" = Sí y "N" = "No"
  */
  private String flNumeradoPorSiged;

  /*
  *Descripción del origen
  */
  private String deOrigen;

  /*
  *Nombre del archivo a ser mostrado en la lista (viene del Siged)
  */
  private String nombreArchivo;

  /*
  *Ruta de Alfresco a ser utilizada para la descarga del archivo
  */
  private String rutaAlfresco;

  /*
  *Valor que señala si el registro se trata de un documento (D) o de un archivo (A)
  */
  private String tipoRegistro;

  /*
  *Identificador interno del archivo que es proporcionado por el Siged
  */
  private Long idArchivo;

  /*
  *Nombre de la subcategoría de documento
  */
  private String noValorParametro;

  /*
  *Código de la categoría del documento y subcategoría del documento
  */
  private String coCatDocAndSubcatDoc;

  /*
  *Existe documento Siged
  */
  private Integer existeDocumentoSiged;

  /*
  *Fecha de creación del documento Siged
  */
  private String fechaCreacionSiged;

  /*
  *Fecha del documento Siged
  */
  private String fechaDocumentoSiged;

  /*
  *Fecha límite de atención Siged
  */
  private String fechaLimiteAtencionSiged;

  /*
  *Fecha de numeración Siged
  */
  private String fechaNumeracionSiged;

  /*
  *Número de expediente Siged
  */
  private String nuExpedienteSiged;

  /*
  *Motivo de la anulación del documento-archivo
  */
  private String motivoAnulacion;

  /*
  *Identificador interno de la categoría del documento.
  */
  private Long idCategoriaDocumento;

  /*
  *Identificador interno del proceso
  */
  private Long idProceso;

  /*
  *Código interno de la instancia de tabla
  */
  private Long coTablaInstancia;

  /*
  *Fecha de origen del documento
  */
  private Date dFeOrigenDocumento;

  /*
  *Fecha de origen del documento
  */
  private String dFeOrigenDocumentoDesc;

  /*
  *Fecha de origen del documento como cadena
  */
  private String sFeOrigenDocumento;
 
  /*
  *Identificador que señala, en caso verdadero (1), si el expediente ha sido recientemente creado, caso contrario (0) no
  */
  private Integer descFlExpedienteCreado;

  /*
  *DESC_ROW_NUM
  */
  private Long descRowNum;

  /*
  *DESC_ENTREGABLE
  */
  private String descEntregable;

  /*
  *DESC_FIRMADO_El
  */
  private Date descFirmadoEl;

  /*
  *DESC_FIRMADO_El
  */
  private String descFirmadoElDesc;

  /*
  *DESC_PRESENTADO_El
  */
  private Date descPresentadoEl;

  /*
  *DESC_PRESENTADO_El
  */
  private String descPresentadoElDesc;

  /*
  *DESC_DIAS_TRANSCURRIDOS
  */
  private Double descDiasTranscurridos;

  /*
  *NO_FASE_PROCESO
  */
  private String noFaseProceso;

  /*
  *DE_FASE_PROCESO
  */
  private String deFaseProceso;

  /*
  *Cantidad de días de demora calculados
  */
  private Integer descCaDiasDemoraCalculados;

  /*
  *Estado de informe
  */
  private String descEstadoInforme;

  /*
  *Identificador del documento de la relación
  */
  private Long descIdDocumentoRelacion;

  /*
  *Valor lógico que señala si se trata de una observación actual
  */
  private boolean descEsObservacionActual;

  /*
  *Número de informe de supervisión en la revisión de informes
  */
  private Integer descNumeroInformeSupervision;

  /*
  *Ordenamiento requerido
  */
  private String descOrdenamiento;

  /*
  *Objeto filtro del documento
  */
  private PgimFiltroDocumentoDTO filtroDocumento;

  /*
  *Indicación de si está firmado o no
  */
  private String descFirmado;

  /*
  *Nombre de los usuarios firmantes
  */
  private String descFirmaUsuario;

  /*
  *Indicación de si el archivo está firmado o no
  */
  private String descFlFirmado;

  /*
  *Flag que indica si el documento es firmado digital o visado digital. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flVistoBueno;

  /*
   * Indicador si exiten penalidades clave
   */
  private String descPenalidadesClave;

  /*
  *Indicador si el usuario logeado en el sistema ha firmado el archivo
  */
  private String descFlFirmaUsuario;

  /*
  *Indicador si el usuario logeado en el sistema es el último firmante
  */
  private String descFlUltimaFirmaUsuario;

  /*
  *Flag que indica si la subcategoría de documento es notificable electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String descFlNotificableE;

  /*
  *Identificador de paso de proceso
  */
  private Long descIdPasoProceso;

  /*
  *Flag que indica si el documento se puede firmar en el paso de proceso. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String descFlFirmaPaso;

  /*
  *Flag que indica si el documento puede ser firmado más de una vez en el mismo paso. Normalmente esto ocurre cuando se trata de un documento firmado por los supervisores de campo de la empresa supervisora. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String descFlFirmaMultiple;

  /*
  *DESC_PRINCIPAL
  */
  private String descPrincipal;

  /*
  *DESC_AUTOR
  */
  private String descAutor;

  /*
  *Identificador interno de la instancia de paso del proceso.
  */
  private Long descIdInstanciaPasoActual;

  /*
  *Asunto de la notificación enviada a través del SNE
  */
  private String descAsuntoNotificacion;

  /*
  *Tipo de supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUPERVISION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long descIdTipoSupervision;

  /*
  *Número del expediente Siged asociado al evento
  */
  private String descNuExpedienteSiged;

  /*
  *Fecha real de inicio de la supervisión
  */
  private Date descFeInicioSupervisionReal;

  /*
  *Fecha real de inicio de la supervisión
  */
  private String descFeInicioSupervisionRealDesc;

  /*
  *Fecha real fin de la supervisión
  */
  private Date descFeFinSupervisionReal;

  /*
  *Fecha real fin de la supervisión
  */
  private String descFeFinSupervisionRealDesc;

   /**
   * Id de supervisión
   */
  private Long descIdSupervision;

  /*
  *Identificador de la persona jurídica destintaria para las notificaciones electrónicas
  */
  private Long descIdPersonaDestinataria;
  
  /*
  *Identificador del destinatario de la persona jurídica destintaria para las notificaciones electrónicas
  */
  private Long descIdDestinatarioDoc;

  /*
  *Lista de identificadores internos de documentos adjuntos
  */
  private List<Long> descLIdDocumentoAdjunto;
  
  /*
  *Cantidad de archivos del documento
  */
  private Integer descCantidadArchivosDoc;

  /*
  *DESC_FL_ADJUNTADO_FORM_PRINCIPAL
  */
  private String descFlAdjuntadoFormPrincipal;
  
  /*
  *Flag que indica si se puede modificar cuando tenga fecha de envío
  */
  private String descFlModificarEnviado;

  /*
  *DESC_FL_ETIQUETA_NOTIF_S
  */
  private String descFlEtiquetaNotifS;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
   * Indicador si esta etiquetado o no
   */
  private String descFlEtiqNotifActiva;

  /*
   * Flag que indica si se permite definir destinatarios.
   */
  private String descFlDefinirDestinatario;

  /*
   * Identificador de tabla PGIM_TD_DOC_ETIQUETA_NOTIF
   */
  private Long descIdDocEtiquetaNotif;
  
  /*
   * Descriptor del listado de nombres de los distinatarios
   */
  private String descNombresDestinatarios;

  /*
   * Procedencia de documentos
   */
  private String descProcedencia;
  
  /*
   * RUC del cliente del siged 
   */
  private String descRucCliente;
  
  /*
   * Razón social del cliente del siged 
   */
  private String descRazonsocialCliente;
  
  /*
   * Index de la lista en donde se encuentra el documento
   */
  private Long descIndexDocumento;  
  
  /*
  * Flag permite notificación por archivo
  */
  private String descFlPermiteNotifPorArchivo;
  
  /*
   * Lista de archivos Siged del documento
   */
  private List<Archivo> descLstArchivos; 
  
  /*
   * Lista de documentos adjuntos al principal
   */
  private List<PgimDocumentoDTO> descLstDocumentoAdjunto;

  /*
   * Flag de incumplimiento por parte del Especialista Técnico, Incumple = "1" y Cumple = "0""
   */
  private String flIncumplimientoET;
}