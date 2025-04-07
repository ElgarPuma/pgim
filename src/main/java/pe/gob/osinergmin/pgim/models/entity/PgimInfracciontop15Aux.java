package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_INFRACCIONTOP15_AUX: 
* @descripción: Vista resumen de las top 15 infracciones más frecuentes por año
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_INFRACCIONTOP15_AUX")
@Data
@NoArgsConstructor
public class PgimInfracciontop15Aux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Secuencia: PGIM_SEQ_NRO_ITEM_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_NRO_ITEM_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_NRO_ITEM_AUX", sequenceName = "PGIM_SEQ_NRO_ITEM_AUX", allocationSize = 1)
   @Column(name = "NRO_ITEM_AUX", nullable = false)
  private Long nroItemAux;

  /*
  *ANIO_INFRACCION
  */
   @Column(name = "ANIO_INFRACCION", nullable = true)
  private Long anioInfraccion;

  /*
  *NO_CORTO_NORMA
  */
   @Column(name = "NO_CORTO_NORMA", nullable = true)
  private String noCortoNorma;

  /*
  *CO_TIPIFICACION
  */
   @Column(name = "CO_TIPIFICACION", nullable = true)
  private String coTipificacion;

  /*
  *NO_ITEM_TIPIFICACION
  */
   @Column(name = "NO_ITEM_TIPIFICACION", nullable = true)
  private String noItemTipificacion;

  /*
  *NRO_INFRACCIONES
  */
   @Column(name = "NRO_INFRACCIONES", nullable = true)
  private Long nroInfracciones;


}