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
* Clase Entidad para la tabla PGIM_TD_INSTANCIA_PASO_DOC: 
* @descripción: Documentos de la instancia de paso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_INSTANCIA_PASO_DOC")
@Data
@NoArgsConstructor
public class PgimInstanciaPasoDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del documento enviado en la instancia de paso del proceso. Secuencia: PGIM_SEQ_INSTANCIA_PASO_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INSTANCIA_PASO_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_INSTANCIA_PASO_DOC", sequenceName = "PGIM_SEQ_INSTANCIA_PASO_DOC", allocationSize = 1)
   @Column(name = "ID_INSTANCIA_PASO_DOC", nullable = false)
  private Long idInstanciaPasoDoc;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO", nullable = false)
  private PgimInstanciaPaso pgimInstanciaPaso;

  /*
  *Identificador interno del documento relacionado con el entregable. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = false)
  private PgimDocumento pgimDocumento;

  /*
  *Fecha de la transición de paso del documento
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_TRANSICION_PASO_DOC", nullable = false)
  private Date feTransicionPasoDoc;

  /*
  *Mensaje por cada documento que se envía
  */
   @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

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