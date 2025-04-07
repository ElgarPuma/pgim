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
* Clase Entidad para la tabla PGIM_TM_ITEM_TIPIFICACION: 
* @descripción: Item de tipificación de norma legal
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_ITEM_TIPIFICACION")
@Data
@NoArgsConstructor
public class PgimItemTipificacion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ítem de tipificación. Secuencia: PGIM_SEQ_ITEM_TIPIFICACION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ITEM_TIPIFICACION")
   @SequenceGenerator(name = "PGIM_SEQ_ITEM_TIPIFICACION", sequenceName = "PGIM_SEQ_ITEM_TIPIFICACION", allocationSize = 1)
   @Column(name = "ID_ITEM_TIPIFICACION", nullable = false)
  private Long idItemTipificacion;

  /*
  *Identificador interno de la norma legal. Tabla padre PGIM_TM_NORMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA", nullable = false)
  private PgimNorma pgimNorma;

  /*
  *Código de la tipificación
  */
   @Column(name = "CO_TIPIFICACION ", nullable = false)
  private String coTipificacion ;

  /*
  *Nombre del ítem de tipificación
  */
   @Column(name = "NO_ITEM_TIPIFICACION", nullable = false)
  private String noItemTipificacion;

  /*
  *Base legal
  */
   @Column(name = "DE_BASE_LEGAL", nullable = true)
  private String deBaseLegal;

  /*
  *Descripción de la sancion pecuniaria en UIT
  */
   @Column(name = "DE_SANCION_PECUNIARIA_UIT", nullable = true)
  private String deSancionPecuniariaUit;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "ES_VIGENTE", nullable = false)
  private String esVigente;

  /*
  *Número de orden en la presentación
  */
   @Column(name = "NU_ORDEN", nullable = true)
  private Long nuOrden;

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
  *Número de obligaciones registradas por tipificación
  */
 @Transient
  private Long descNuObligaciones;

  /*
  *DESC_NO_CORTO_NORMA
  */
 @Transient
  private String descNoCortoNorma;


}