package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_DOC_ETIQUETA_NOTIF: 
* @descripción: Documento etiqueta notificación
*
* @author: LEGION
* @version 1.0
* @fecha_de_creación: 06/02/2023
*/
@Entity
@Table(name = "PGIM_TD_DOC_ETIQUETA_NOTIF")
@Data
@NoArgsConstructor
public class PgimDocEtiquetaNotif implements Serializable{

    /*
  *Identificador interno del documento etiqueta notifica. Secuencia: PGIM_SEQ_DOC_ETIQUETA_NOTIF
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DOC_ETIQUETA_NOTIF")
   @SequenceGenerator(name = "PGIM_SEQ_DOC_ETIQUETA_NOTIF", sequenceName = "PGIM_SEQ_DOC_ETIQUETA_NOTIF", allocationSize = 1)
   @Column(name = "ID_DOC_ETIQUETA_NOTIF", nullable = false)
  private Long idDocEtiquetaNotif;

  /*
  *Identificador interno del documento etiquetado. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = false)
  private PgimDocumento documentoEtiquetado;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO", nullable = false)
  private PgimInstanciaPaso instanciaPaso;

  /*
  *Flag que indica si desde el paso se pueden etiquetar documentos para notificar de manera electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_ETIQUETA_NOTIF_ACTIVA", nullable = false)
  private String flEtiquetaNotifActiva;

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
    
}
