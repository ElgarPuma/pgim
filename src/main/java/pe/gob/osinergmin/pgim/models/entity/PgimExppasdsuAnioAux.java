package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_EXPPASDSU_ANIO_AUX: 
* @descripción: Vista agregada de la cantidad de expedientes de fiscalización por división supeprvisora, fase y año
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXPPASDSU_ANIO_AUX")
@Data
@NoArgsConstructor
public class PgimExppasdsuAnioAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de los expedientes PAS por división superisora. Secuencia: PGIM_SEQ_DIV_SUPERVISORA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DIV_SUPERVISORA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_DIV_SUPERVISORA_AUX", sequenceName = "PGIM_SEQ_DIV_SUPERVISORA_AUX", allocationSize = 1)
   @Column(name = "ID_DIVISION_SUPERVISORA_AUX", nullable = false)
  private BigDecimal idDivisionSupervisoraAux;

  /*
  *ID_DIVISION_SUPERVISORA
  */
   @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private BigDecimal idDivisionSupervisora;

  /*
  *NO_DIVISION_SUPERVISORA
  */
   @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;

  /*
  *NU_ORDEN
  */
   @Column(name = "NU_ORDEN", nullable = true)
  private BigDecimal nuOrden;

  /*
  *ID_FASE_PROCESO
  */
   @Column(name = "ID_FASE_PROCESO", nullable = true)
  private BigDecimal idFaseProceso;

  /*
  *NO_FASE_PROCESO
  */
   @Column(name = "NO_FASE_PROCESO", nullable = true)
  private String noFaseProceso;

  /*
  *NRO_EXP_PAS_P1
  */
   @Column(name = "NRO_EXP_PAS_P1", nullable = true)
  private BigDecimal nroExpPasP1;

  /*
  *NRO_EXP_PAS_P2
  */
   @Column(name = "NRO_EXP_PAS_P2", nullable = true)
  private BigDecimal nroExpPasP2;

  /*
  *NRO_EXP_PAS_P3
  */
   @Column(name = "NRO_EXP_PAS_P3", nullable = true)
  private BigDecimal nroExpPasP3;

  /*
  *NRO_EXP_PAS_P4
  */
   @Column(name = "NRO_EXP_PAS_P4", nullable = true)
  private BigDecimal nroExpPasP4;

  /*
  *NRO_EXP_PAS_P5
  */
   @Column(name = "NRO_EXP_PAS_P5", nullable = true)
  private BigDecimal nroExpPasP5;

  /*
  *NRO_EXP_PAS_P6
  */
   @Column(name = "NRO_EXP_PAS_P6", nullable = true)
  private BigDecimal nroExpPasP6;

  /*
  *TOTAL_FASE
  */
   @Column(name = "TOTAL_FASE", nullable = true)
  private BigDecimal totalFase;


}