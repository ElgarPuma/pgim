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
* Clase Entidad para la tabla PGIM_VW_EXPPASTIPACTI_ANIO_AUX: 
* @descripción: Vista de expedientes con PAS por tipo de actividad y año
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXPPASTIPACTI_ANIO_AUX")
@Data
@NoArgsConstructor
public class PgimExppastipactiAnioAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_EXPPASTIPACTI_ANIO_AUX. Secuencia: PGIM_SEQ_TIPO_ACTIVIDAD_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TIPO_ACTIVIDAD_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_TIPO_ACTIVIDAD_AUX", sequenceName = "PGIM_SEQ_TIPO_ACTIVIDAD_AUX", allocationSize = 1)
   @Column(name = "ID_TIPO_ACTIVIDAD_AUX", nullable = false)
  private Long idTipoActividadAux;

  /*
  *ID_TIPO_ACTIVIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = true)
  private PgimValorParametro tipoActividad;

  /*
  *NO_TIPO_ACTIVIDAD
  */
   @Column(name = "NO_TIPO_ACTIVIDAD", nullable = true)
  private String noTipoActividad;

  /*
  *NRO_EXP_PAS_P1
  */
   @Column(name = "NRO_EXP_PAS_P1", nullable = true)
  private Long nroExpPasP1;

  /*
  *NRO_EXP_PAS_P2
  */
   @Column(name = "NRO_EXP_PAS_P2", nullable = true)
  private Long nroExpPasP2;

  /*
  *NRO_EXP_PAS_P3
  */
   @Column(name = "NRO_EXP_PAS_P3", nullable = true)
  private Long nroExpPasP3;

  /*
  *NRO_EXP_PAS_P4
  */
   @Column(name = "NRO_EXP_PAS_P4", nullable = true)
  private Long nroExpPasP4;

  /*
  *NRO_EXP_PAS_P5
  */
   @Column(name = "NRO_EXP_PAS_P5", nullable = true)
  private Long nroExpPasP5;

  /*
  *NRO_EXP_PAS_P6
  */
   @Column(name = "NRO_EXP_PAS_P6", nullable = true)
  private Long nroExpPasP6;

  /*
  *TOTAL_NRO_EXP
  */
   @Column(name = "TOTAL_NRO_EXP", nullable = true)
  private Long totalNroExp;


}