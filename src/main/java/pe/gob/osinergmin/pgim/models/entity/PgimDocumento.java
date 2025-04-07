package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimFiltroDocumentoDTO;
import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_DOCUMENTO: 
* @descripción: Documento del proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_DOCUMENTO")
@Data
@NoArgsConstructor
public class PgimDocumento implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del documento. Secuencia: PGIM_SEQ_DOCUMENTO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DOCUMENTO")
   @SequenceGenerator(name = "PGIM_SEQ_DOCUMENTO", sequenceName = "PGIM_SEQ_DOCUMENTO", allocationSize = 1)
   @Column(name = "ID_DOCUMENTO", nullable = false)
  private Long idDocumento;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOCUMENTO", nullable = false)
  private PgimSubcategoriaDoc pgimSubcategoriaDoc;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = false)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_ORIGEN_DOCUMENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ORIGEN_DOCUMENTO", nullable = false)
  private PgimValorParametro tipoOrigenDocumento;

  /*
  *Identificador interno de la fase del proceso en la que se adjuntó el documento. Tabla padre: PGIM_TM_FASE_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FASE_PROCESO", nullable = false)
  private PgimFaseProceso pgimFaseProceso;

  /*
  *Identificador interno del documento desde el que se copio el actual documento. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO_PADRE_COPIA", nullable = true)
  private PgimDocumento pgimDocumentoPadreCopia;

  /*
  *Asunto del documento
  */
   @Column(name = "DE_ASUNTO_DOCUMENTO", nullable = false)
  private String deAsuntoDocumento;

  /*
  *Fecha del origen del documento. Cuando el ID_TIPO_ORIGEN_DOCUMENTO = 0 entonces se refiere a la recha de recepción, si es 1, entonces se refiere a la fecha de emisión 
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ORIGEN_DOCUMENTO", nullable = true)
  private Date feOrigenDocumento;

  /*
  *Identificador interno del documento Siged
  */
   @Column(name = "CO_DOCUMENTO_SIGED", nullable = true)
  private Long coDocumentoSiged;

  /*
  *Secuencia del documento utilizado para la codificación de la carpeta transitoria (carpeta en el servidor de la aplicación) de carga de sus archivos. Utiliza la secuencia PGIM_SEQ_GEN_DOCUMENTO
  */
   @Column(name = "SE_DOCUMENTO", nullable = true)
  private Long seDocumento;

  /*
  *Flag que indica si el documento ya fue notificado al menos una vez. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_NOTIFICADO", nullable = true)
  private String flNotificado;

  /*
  *Fecha en la que se envió (asignó) el documento por primera vez de acuerdo con las relaciones pre-establecidas para el flujo de trabajo (tabla PGIM_TM_RELACION_SUBCAT). Cuando tiene el valor NULL significa que el documento aún no fue enviado; por otro lado, si ya tiene una fecha es la fecha de envío formal del documento.
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ENVIO_DOCUMENTO", nullable = true)
  private Date feEnvioDocumento;

  /*
  *Flag que indica si el documento ya fue notificado al menos una vez. Los posibles valores son: "1" = Sí y "0" = No
  */
  @Column(name = "FL_RESERVA_ACTIVA", nullable = true)
  private String flReservaActiva;

  /*
  *Flag que indica si el documento ha sido firmado digitalmente o no. Los posibles valores son: "1" = Sí y "0" o NULL = No
  */
  @Column(name = "FL_FIRMADO_DIGITALMENTE ", nullable = true)
  private String flFirmadoDigitalmente;
  
  /*
  *Identificador interno del documento Siged desde el que se copio el actual documento
  */
   @Column(name = "CO_DOCUMENTO_SIGED_P_COPIA", nullable = true)
  private Long coDocumentoSigedPCopia;
   
  /*
  *Fecha en la que se ha rechazado el documento. Cuando tiene valor señala que el documento ha sido rechazado por su destinatario, cuando no, entonces no existe rechazo alguno. 
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_RECHAZO", nullable = true)
  private Date feRechazo;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Usuario creador
  */
   @Column(name = "US_CREACION", nullable = false)
  private String usCreacion;

  /*
  *Terminal de creación
  */
   @Column(name = "IP_CREACION", nullable = false)
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = false)
  private Date feCreacion;

  /*
  *Usuario modificador
  */
   @Column(name = "US_ACTUALIZACION", nullable = true)
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
   @Column(name = "IP_ACTUALIZACION", nullable = true)
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ACTUALIZACION", nullable = true)
  private Date feActualizacion;

  /*
   * Flag de incumplimiento por parte del Especialista Técnico, Incumple = "1" y
   * Cumple = "0""
   */
  @Column(name = "FL_INCUMPLIMIENTO_ET", nullable = true)
  private String flIncumplimientoET;
  
  /*
  *Código de la categoría de documento
  */
 @Transient
  private String coCategoriaDocumento;

  /*
  *Nombre de la categoría de documento
  */
 @Transient
  private String noCategoriaDocumento;

  /*
  *Código de la subcategoría de documento
  */
 @Transient
  private String coSubcatDocumento;

  /*
  *Nombre de la subcategoría de documento
  */
 @Transient
  private String noSubcatDocumento;

  /*
  *Nombre del tipo de documento Siged
  */
 @Transient
  private String nombreTipoDocumento;

  /*
  *Número de documento Siged
  */
 @Transient
  private String numeroDocumento;

  /*
  *Asunto del documento Siged
  */
 @Transient
  private String asuntoSiged;

  /*
  *Código del tipo de documento Siged
  */
 @Transient
  private Long coTipoDocumentoSiged;

  /*
  *Flag que indica si el Siged numerará el documento. Posibles valores: "S" = Sí y "N" = "No"
  */
 @Transient
  private String flNumeradoPorSiged;

  /*
  *Descripción del origen
  */
 @Transient
  private String deOrigen;

  /*
  *Nombre del archivo a ser mostrado en la lista (viene del Siged)
  */
 @Transient
  private String nombreArchivo;

  /*
  *Ruta de Alfresco a ser utilizada para la descarga del archivo
  */
 @Transient
  private String rutaAlfresco;

  /*
  *Valor que señala si el registro se trata de un documento (D) o de un archivo (A)
  */
 @Transient
  private String tipoRegistro;

  /*
  *Identificador interno del archivo que es proporcionado por el Siged
  */
 @Transient
  private Long idArchivo;

  /*
  *Nombre de la subcategoría de documento
  */
 @Transient
  private String noValorParametro;

  /*
  *Código de la categoría del documento y subcategoría del documento
  */
 @Transient
  private String coCatDocAndSubcatDoc;

  /*
  *Existe documento Siged
  */
 @Transient
  private Integer existeDocumentoSiged;

  /*
  *Fecha de creación del documento Siged
  */
 @Transient
  private String fechaCreacionSiged;

  /*
  *Fecha del documento Siged
  */
 @Transient
  private String fechaDocumentoSiged;

  /*
  *Fecha límite de atención Siged
  */
 @Transient
  private String fechaLimiteAtencionSiged;

  /*
  *Fecha de numeración Siged
  */
 @Transient
  private String fechaNumeracionSiged;

  /*
  *Número de expediente Siged
  */
 @Transient
  private String nuExpedienteSiged;

  /*
  *Motivo de la anulación del documento-archivo
  */
 @Transient
  private String motivoAnulacion;

  /*
  *Identificador interno de la categoría del documento.
  */
 @Transient
  private Long idCategoriaDocumento;

  /*
  *Identificador interno del proceso
  */
 @Transient
  private Long idProceso;

  /*
  *Código interno de la instancia de tabla
  */
 @Transient
  private Long coTablaInstancia;

  /*
  *Fecha de origen del documento
  */
 @Transient
  private Date dFeOrigenDocumento;

  /*
  *Fecha de origen del documento como cadena
  */
 @Transient
  private String sFeOrigenDocumento;

  /*
  *Identificador que señala, en caso verdadero (1), si el expediente ha sido recientemente creado, caso contrario (0) no
  */
 @Transient
  private Integer descFlExpedienteCreado;

  /*
  *DESC_ROW_NUM
  */
 @Transient
  private Long descRowNum;

  /*
  *DESC_ENTREGABLE
  */
 @Transient
  private String descEntregable;

  /*
  *DESC_FIRMADO_El
  */
 @Transient
  private Date descFirmadoEl;

  /*
  *DESC_PRESENTADO_El
  */
 @Transient
  private Date descPresentadoEl;

  /*
  *DESC_DIAS_TRANSCURRIDOS
  */
 @Transient
  private Double descDiasTranscurridos;

  /*
  *NO_FASE_PROCESO
  */
 @Transient
  private String noFaseProceso;

  /*
  *DE_FASE_PROCESO
  */
 @Transient
  private String deFaseProceso;

  /*
  *Cantidad de días de demora calculados
  */
 @Transient
  private Integer descCaDiasDemoraCalculados;

  /*
  *Estado de informe
  */
 @Transient
  private String descEstadoInforme;

  /*
  *Identificador del documento de la relación
  */
 @Transient
  private Long descIdDocumentoRelacion;

  /*
  *Valor lógico que señala si se trata de una observación actual
  */
 @Transient
  private boolean descEsObservacionActual;

  /*
  *Número de informe de supervisión en la revisión de informes
  */
 @Transient
  private Integer descNumeroInformeSupervision;

  /*
  *Ordenamiento requerido
  */
 @Transient
  private String descOrdenamiento;

  /*
  *Objeto filtro del documento
  */
 @Transient
  private PgimFiltroDocumentoDTO filtroDocumento;

  /*
  *Indicación de si está firmado o no
  */
 @Transient
  private String descFirmado;

  /*
  *Nombre de los usuarios firmantes
  */
 @Transient
  private String descFirmaUsuario;

  /*
  *Indicación de si el archivo está firmado o no
  */
 @Transient
  private String descFlFirmado;

  /*
  *Indicador si el usuario logeado en el sistema ha firmado el archivo
  */
 @Transient
  private String descFlFirmaUsuario;

  /*
  *Indicador si el usuario logeado en el sistema es el último firmante
  */
 @Transient
  private String descFlUltimaFirmaUsuario;

  /*
  *Flag que indica si la subcategoría de documento es notificable electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
 @Transient
  private String descFlNotificableE;

  /*
  *Identificador de paso de proceso
  */
 @Transient
  private Long descIdPasoProceso;

  /*
  *Flag que indica si el documento se puede firmar en el paso de proceso. Posibles valores: "1" = Sí y 0 = "No"
  */
 @Transient
  private String descFlFirmaPaso;

  /*
  *Flag que indica si el documento puede ser firmado más de una vez en el mismo paso. Normalmente esto ocurre cuando se trata de un documento firmado por los supervisores de campo de la empresa supervisora. Los posibles valores son: "1" = Sí y "0" = No
  */
 @Transient
  private String descFlFirmaMultiple;

  /*
  *DESC_PRINCIPAL
  */
 @Transient
  private String descPrincipal;

  /*
  *DESC_AUTOR
  */
 @Transient
  private String descAutor;

  /*
  *Identificador interno de la instancia de paso del proceso.
  */
 @Transient
  private Long descIdInstanciaPasoActual;

  /*
  *Asunto de la notificación enviada a través del SNE
  */
 @Transient
  private String descAsuntoNotificacion;

  /*
  *Tipo de supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUPERVISION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
 @Transient
  private Long descIdTipoSupervision;

  /*
  *Número del expediente Siged asociado al evento
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *Fecha real de inicio de la supervisión
  */
 @Transient
  private Date descFeInicioSupervisionReal;

  /*
  *Fecha real fin de la supervisión
  */
 @Transient
  private Date descFeFinSupervisionReal;

  /*
  *Identificador de la persona jurídica destintaria para las notificaciones electrónicas
  */
 @Transient
  private Long descIdPersonaDestinataria;

  /*
  *Lista de identificadores internos de documentos adjuntos
  */
 @Transient
  private List<Long> descLIdDocumentoAdjunto;

  /*
  *DESC_FL_ADJUNTADO_FORM_PRINCIPAL
  */
 @Transient
  private String descFlAdjuntadoFormPrincipal;


}