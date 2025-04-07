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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_PASO_SUBCAT: 
* @descripción: Subcategorías por paso de proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_PASO_SUBCAT")
@Data
@NoArgsConstructor
public class PgimPasoSubcat implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la subcategoría por paso. Secuencia: PGIM_SEQ_PASO_SUBCAT
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PASO_SUBCAT")
   @SequenceGenerator(name = "PGIM_SEQ_PASO_SUBCAT", sequenceName = "PGIM_SEQ_PASO_SUBCAT", allocationSize = 1)
   @Column(name = "ID_PASO_SUBCAT", nullable = false)
  private Long idPasoSubcat;

  /*
  *Identificador interno del paso origen del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_PROCESO", nullable = false)
  private PgimPasoProceso pgimPasoProceso;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOCUMENTO", nullable = false)
  private PgimSubcategoriaDoc pgimSubcategoriaDoc;
   
  /*
  *Cantidad de documentos de determinada subcategoría que puede adjuntarse para dicho paso
  */
   @Column(name = "CA_DOCS_ADJUNTAR", nullable = true)
  private Integer caDocsAdjuntar;
   
  /*
  *Flag que indica si se valida la cantidad de documentos a adjuntar considerando solo los nuevos documentos, vale decir que no tienen fecha de envío. Los posibles valores son: "1" = Sí y "0" = No, considera el total
  */
   @Column(name = "FL_VALIDA_SOLO_NUEVOS", nullable = true)
  private String flValidaSoloNuevos;
   
  /*
  *Flag que indica si se permite hacer modificaciones al documento cuando tenga fecha de envío. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_MODIFICAR_ENVIADO", nullable = false)
  private String flModificarEnviado;

  /*
  *Flag que indica si la PGIM se permite o no el adjuntado o inclusión manual de la subcategoría documental en el paso. Los posibles valores son: "1" = Sí y "0" = No.
  */
  @Column(name = "FL_ADJUNTADO_MANUAL", nullable = false)
  private String flAdjuntadoManual;  

  /*
  *Flag que indica si se permite definir destinatarios.
  */
  @Column(name = "FL_DEFINIR_DESTINATARIO", nullable = true)
  private String flDefinirDestinatario;

  /*
  *Flag - sobre escribe el comportamiento determinado para FL_ADJUNTADO_MANUAL - que indica si la PGIM se permite o no el adjuntado o inclusión manual de la subcategoría documental en el paso. Los posibles valores son: "1" = Sí y "0" = No..
  */
  @Column(name = "FL_ADJUNTADO_MANUAL_PROPIA", nullable = true)
  private String flAdjuntadoManualPropia;

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
    *Flag que indica si, en determinado paso, la subcategoría permitirá ser asociado con un documento notificado. Posibles valores: "1" = Sí y 0 = "No"
  */
  @Column(name = "FL_ASOCIAR_DOC_NOTIFICADO", nullable = false)
  private String flAsociarDocNotificado;
}