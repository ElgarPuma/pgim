package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_ITEM_TIPIFICACION_AUX: 
* @descripción: Vista para obtener la lista de items de tipificación
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_ITEM_TIPIFICACION_AUX")
@Data
@NoArgsConstructor
public class PgimItemTipificacionAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ítem de tipificación. Secuencia: PGIM_SEQ_ITEM_TIPIFICA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ITEM_TIPIFICA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ITEM_TIPIFICA_AUX", sequenceName = "PGIM_SEQ_ITEM_TIPIFICA_AUX", allocationSize = 1)
   @Column(name = "ID_ITEM_TIPIFICACION_AUX", nullable = false)
  private Long idItemTipificacionAux;

  /*
  *Identificador interno del ítem de tipificación. Secuencia: PGIM_SEQ_ITEM_TIPIFICACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ITEM_TIPIFICACION", nullable = true)
  private PgimItemTipificacion pgimItemTipificacion;

  /*
  *Nombre corto de la norma legal
  */
   @Column(name = "NO_CORTO_NORMA", nullable = true)
  private String noCortoNorma;

  /*
  *Identificador interno de la norma legal. Tabla padre PGIM_TM_NORMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA", nullable = true)
  private PgimNorma pgimNorma;

  /*
  *Código de la tipificación
  */
   @Column(name = "CO_TIPIFICACION", nullable = true)
  private String coTipificacion;

  /*
  *Nombre del ítem de tipificación
  */
   @Column(name = "NO_ITEM_TIPIFICACION", nullable = true)
  private String noItemTipificacion;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "ES_VIGENTE", nullable = true)
  private String esVigente;

  /*
  *Número de orden en la presentación
  */
   @Column(name = "NU_ORDEN", nullable = true)
  private Long nuOrden;

  /*
  *Descripción de la sancion pecuniaria en UIT
  */
   @Column(name = "DE_SANCION_PECUNIARIA_UIT", nullable = true)
  private String deSancionPecuniariaUit;

  /*
  *Cantidad de obligaciones relacionadas con las obligaciones (asociación entre ítem de norma y ítem de tipificación
  */
   @Column(name = "NU_OBLIGACIONES", nullable = true)
  private Long nuObligaciones;


}