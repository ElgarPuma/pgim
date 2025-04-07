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
* Clase Entidad para la tabla PGIM_VW_EXPPAS_EVALUADOR_AUX: 
* @descripción: Vista de expedientes con PAS asignados a evaluador, por división y especialidad
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXPPAS_EVALUADOR_AUX")
@Data
@NoArgsConstructor
public class PgimExppasEvaluadorAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_EXPPAS_EVALUADOR_AUX Secuencia: PGIM_SEQ_EVALUADOR_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EVALUADOR_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_EVALUADOR_AUX", sequenceName = "PGIM_SEQ_EVALUADOR_AUX", allocationSize = 1)
   @Column(name = "ID_EXP_PAS_EVALUADOR_AUX", nullable = false)
  private Long idExpPasEvaluadorAux;

  /*
  *ID_DIVISION_SUPERVISORA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *NO_DIVISION_SUPERVISORA
  */
   @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;

  /*
  *DE_DIVISION_SUPERVISORA
  */
   @Column(name = "DE_DIVISION_SUPERVISORA", nullable = true)
  private String deDivisionSupervisora;

  /*
  *ID_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = true)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *ID_PERSONAL_OSI
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONAL_OSI", nullable = true)
  private PgimPersonalOsi pgimPersonalOsi;

  /*
  *ID_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = true)
  private PgimPersona pgimPersona;

  /*
  *NO_EVALUADOR
  */
   @Column(name = "NO_EVALUADOR", nullable = true)
  private String noEvaluador;

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
  *NRO_EXP_PAS_TOTAL
  */
   @Column(name = "NRO_EXP_PAS_TOTAL", nullable = true)
  private Long nroExpPasTotal;


}