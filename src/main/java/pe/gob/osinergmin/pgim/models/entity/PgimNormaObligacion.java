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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_NORMA_OBLIGACION: 
* @descripción: Obligación normativa
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_NORMA_OBLIGACION")
@Data
@NoArgsConstructor
public class PgimNormaObligacion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la obligación normativa. Secuencia: PGIM_SEQ_NORMA_OBLIGACION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_NORMA_OBLIGACION")
   @SequenceGenerator(name = "PGIM_SEQ_NORMA_OBLIGACION", sequenceName = "PGIM_SEQ_NORMA_OBLIGACION", allocationSize = 1)
   @Column(name = "ID_NORMA_OBLIGACION", nullable = false)
  private Long idNormaObligacion;

  /*
  *Identificador interno del ítem de norma legal. Tabla padre PGIM_TM_NORMA_ITEM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA_ITEM", nullable = false)
  private PgimNormaItem pgimNormaItem;

  /*
  *Identificador interno del ítem de tipificación. Tabla padre PGIM_TM_ITEM_TIPIFICACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ITEM_TIPIFICACION", nullable = false)
  private PgimItemTipificacion pgimItemTipificacion;

  /*
  *Descripción de  la obligación normativa
  */
   @Column(name = "DE_NORMA_OBLIGACION_T", nullable = true)
   @Lob
  private String deNormaObligacionT;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "ES_VIGENTE", nullable = false)
  private String esVigente;

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