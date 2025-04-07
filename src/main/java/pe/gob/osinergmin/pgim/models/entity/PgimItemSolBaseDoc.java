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

import java.math.BigDecimal;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_ITEM_SOL_BASE_DOC: 
* @descripción: Maestro de los ítems de la solicitud de la documentación
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_ITEM_SOL_BASE_DOC")
@Data
@NoArgsConstructor
public class PgimItemSolBaseDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ítem de la solicitud de documentación. Secuencia: PGIM_SEQ_ITEM_SOL_BASE_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ITEM_SOL_BASE_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_ITEM_SOL_BASE_DOC", sequenceName = "PGIM_SEQ_ITEM_SOL_BASE_DOC", allocationSize = 1)
   @Column(name = "ID_ITEM_SOL_BASE_DOC", nullable = false)
  private Long idItemSolBaseDoc;

  /*
  *Identificador interno de la solicitud base (plantilla) de documentación. Tabla padre: PGIM_TM_SOLICITUD_BASE_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SOLICITUD_BASE_DOC", nullable = false)
  private PgimSolicitudBaseDoc pgimSolicitudBaseDoc;

  /*
  *Descripción de la solicitud de documentación
  */
   @Column(name = "DE_SOLICITUD_OBSERVACION", nullable = false)
  private String deSolicitudObservacion;

  /*
  *Orden de presentación de la solicitud de la documentación
  */
   @Column(name = "NU_ORDEN", nullable = false)
  private BigDecimal nuOrden;

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