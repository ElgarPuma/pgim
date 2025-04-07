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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_SUBCATEGORIA_DOC: 
* @descripción: Subcategoría de documento
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_SUBCATEGORIA_DOC")
@Data
@NoArgsConstructor
public class PgimSubcategoriaDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la subcategoría de documento. Secuencia: PGIM_SEQ_SUBCATEGORIA_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUBCATEGORIA_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_SUBCATEGORIA_DOC", sequenceName = "PGIM_SEQ_SUBCATEGORIA_DOC", allocationSize = 1)
   @Column(name = "ID_SUBCAT_DOCUMENTO", nullable = false)
  private Long idSubcatDocumento;

  /*
  *Identificador interno de la categoría de documento. Tabla padre: PGIM_TM_CATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CATEGORIA_DOCUMENTO", nullable = false)
  private PgimCategoriaDoc pgimCategoriaDoc;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = true)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ORIGEN_DOCUMENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ORIGEN_DOCUMENTO", nullable = true)
  private PgimValorParametro tipoOrigenDocumento;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_FIRMA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_FIRMA", nullable = true)
  private PgimValorParametro tipoFirma;

  /*
  *Tipo de extrensión a utiizar para la generación de documentos. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_EXTENSION_DOC. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_EXTENSION_GEN", nullable = true)
  private PgimValorParametro tipoExtensionGen;

  /*
  *Código de la subcategoría de documento
  */
   @Column(name = "CO_SUBCAT_DOCUMENTO", nullable = false)
  private String coSubcatDocumento;

  /*
  *Nombre de la subcategoría de documento
  */
   @Column(name = "NO_SUBCAT_DOCUMENTO", nullable = false)
  private String noSubcatDocumento;

  /*
  *Flag que indica si el Siged numerará el documento. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_NUMERADO_POR_SIGED", nullable = true)
  private String flNumeradoPorSiged;

  /*
  *Flag que indica si la subcategoría de documento es notificable electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_NOTIFICABLE_E", nullable = true)
  private String flNotificableE;

  /*
  *Identificador interno del tipo de documento Siged. Este valor se utiliza para crear un nuevo documento en el Siged a partir de las acciones de adjuntar o incluir lanzadas desde la PGIM. Si es que tiene no tiene valor entonces no podrá adjuntarse esta subcategoría en el Siged
  */
   @Column(name = "CO_TIPO_DOCUMENTO_SIGED", nullable = true)
  private Long coTipoDocumentoSiged;

  /*
  *Nombre de las extensiones permitidas al momento de adjuntar un documento
  */
   @Column(name = "DE_EXTENSIONES_PERMITIDAS", nullable = true)
  private String deExtensionesPermitidas;

  /*
  *Indicador que señala si el/la fiscalizador/a firmará o no el documento. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "FL_FIRMA_FISCALIZADOR", nullable = true)
  private String flFirmaFiscalizador;

  /*
  *Flag que indica si la subcategoría permite la reserva de número Posibles valores: "1" = Sí y 0 = "No"
  */
  @Column(name = "FL_RESERVA_NUMERO", nullable = false)
  private String flReservaNumero;

  /*
  *Nombre del archivo que se utilizará para adjuntar la reserva de la numeración. Debe tener valor cuando la columna FL_RESERVA_NUMERO = "1"';
  */
  @Column(name = "NO_ARCHIVO_RESERVA ", nullable = true)
  private String noArchivoReserva;
  
  /*
  *Flag que indica si la notificación de esta subcategoría documental permitirá realizarla por archivo; es decir, permitir seleccionar un subconjunto de archivos del documento (principal y/o adicionales) para ser notificados. Posibles valores: "1" = Sí y 0 = "No"
  */
  @Column(name = "FL_PERMITE_NOTIF_POR_ARCHIVO ", nullable = false)
  private String flPermiteNotifPorArchivo;

  /**
   * Flag que indica si la subcategoría se puede adjuntar desde la gestión bibliográfica de la PGIM.Posibles valores: "1" = Sí y 0 = "No"
   */
  @Column(name = "FL_BIBLIOGRAFIA ", nullable = true)
  private String flBibiografia;

  /**
   * Flag que indica si la subcategoría puede ser etiquetada para notificar en cualquier momento. Posibles valores: "1" = Sí y 0 = "No"
   */
  @Column(name = "FL_ETIQUETA_NOTIF_S ", nullable = true)
  private String flEtiquetaNotifS;
  
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
  *Nombre del tipo de extensión a generarse para la subcategoría
  */
 @Transient
  private String descNoTipoExtensionGen;

  /*
  *Código de la categoría de documento
  */
 @Transient
  private String descCoCategoriaDocumento;

  /*
  *Nombre de la categoría de documento
  */
 @Transient
  private String descNoCategoriaDocumento;


}