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
* Clase Entidad para la tabla PGIM_VW_INFRACCIONXESP_AUX: 
* @descripción: Vista de todas las especialidades y sus infracciones en seis años
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_INFRACCIONXESP_AUX")
@Data
@NoArgsConstructor
public class PgimInfraccionxespAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la especialidad que sumariza las infracciones por año. Secuencia: PGIM_SEQ_ESPECIALIDADINF_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ESPECIALIDADINF_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ESPECIALIDADINF_AUX", sequenceName = "PGIM_SEQ_ESPECIALIDADINF_AUX", allocationSize = 1)
   @Column(name = "ID_ESPECIALIDAD_AUX", nullable = false)
  private Long idEspecialidadAux;

  /*
  *ID_ESPECIALIDAD
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *NRO_EXP_PAS_P1
  */
   @Column(name = "NRO_INFRACCION_P1", nullable = true)
  private Long nroInfraccionP1;

  /*
  *NRO_EXP_PAS_P2
  */
   @Column(name = "NRO_INFRACCION_P2", nullable = true)
  private Long nroInfraccionP2;

  /*
  *NRO_EXP_PAS_P3
  */
   @Column(name = "NRO_INFRACCION_P3", nullable = true)
  private Long nroInfraccionP3;

  /*
  *NRO_EXP_PAS_P4
  */
   @Column(name = "NRO_INFRACCION_P4", nullable = true)
  private Long nroInfraccionP4;

  /*
  *NRO_EXP_PAS_P5
  */
   @Column(name = "NRO_INFRACCION_P5", nullable = true)
  private Long nroInfraccionP5;

  /*
  *NRO_EXP_PAS_P6
  */
   @Column(name = "NRO_INFRACCION_P6", nullable = true)
  private Long nroInfraccionP6;

  /*
  *NRO_EXP_PAS_P7
  */
   @Column(name = "NRO_INFRACCION_TOTAL", nullable = true)
  private Long nroInfraccionTotal;


}