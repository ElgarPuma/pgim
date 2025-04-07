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
* Clase Entidad para la tabla PGIM_VW_INFRACCIONXUM_AUX: 
* @descripción: Vista de todas las unidades mineras y sus infracciones en seis años
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_INFRACCIONXUM_AUX")
@Data
@NoArgsConstructor
public class PgimInfraccionxumAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_INFRACCIONXUM_AUX. Secuencia: PGIM_SEQ_INF_UNIDAD_MINERA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INF_UNIDAD_MINERA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_INF_UNIDAD_MINERA_AUX", sequenceName = "PGIM_SEQ_INF_UNIDAD_MINERA_AUX", allocationSize = 1)
   @Column(name = "ID_UNIDAD_MINERA_AUX", nullable = false)
  private Long idUnidadMineraAux;

  /*
  *ID_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = true)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *ID_TIPO_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_UNIDAD_MINERA", nullable = true)
  private PgimValorParametro tipoUnidadMinera;

  /*
  *NO_TIPO_UNIDAD_MINERA
  */
   @Column(name = "NO_TIPO_UNIDAD_MINERA", nullable = true)
  private String noTipoUnidadMinera;

  /*
  *NRO_INFRACCION_P1
  */
   @Column(name = "NRO_INFRACCION_P1", nullable = true)
  private Long nroInfraccionP1;

  /*
  *NRO_INFRACCION_P2
  */
   @Column(name = "NRO_INFRACCION_P2", nullable = true)
  private Long nroInfraccionP2;

  /*
  *NRO_INFRACCION_P3
  */
   @Column(name = "NRO_INFRACCION_P3", nullable = true)
  private Long nroInfraccionP3;

  /*
  *NRO_INFRACCION_P4
  */
   @Column(name = "NRO_INFRACCION_P4", nullable = true)
  private Long nroInfraccionP4;

  /*
  *NRO_INFRACCION_P5
  */
   @Column(name = "NRO_INFRACCION_P5", nullable = true)
  private Long nroInfraccionP5;

  /*
  *NRO_INFRACCION_P6
  */
   @Column(name = "NRO_INFRACCION_P6", nullable = true)
  private Long nroInfraccionP6;

  /*
  *NRO_INFRACCION_TOTAL
  */
   @Column(name = "NRO_INFRACCION_TOTAL", nullable = true)
  private Long nroInfraccionTotal;


}