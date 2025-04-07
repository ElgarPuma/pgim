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
* Clase Entidad para la tabla PGIM_VW_EXPPAS_ANIO_AUX: 
* @descripción: Vista agregada de la cantidad de expedientes de fiscalización por fase y año
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXPPAS_ANIO_AUX")
@Data
@NoArgsConstructor
public class PgimExppasAnioAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la fase del proceso. Secuencia: PGIM_SEQ_FASE_PROCESO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_FASE_PROCESO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_FASE_PROCESO_AUX", sequenceName = "PGIM_SEQ_FASE_PROCESO_AUX", allocationSize = 1)
   @Column(name = "ID_FASE_PROCESO_AUX", nullable = false)
  private Long idFaseProcesoAux;

  /*
  *ID_FASE_PROCESO
  */
   @Column(name = "ID_FASE_PROCESO", nullable = true)
  private Long idFaseProceso;

  /*
  *NO_FASE_PROCESO
  */
   @Column(name = "NO_FASE_PROCESO", nullable = true)
  private String noFaseProceso;

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
  *TOTAL_FASE
  */
   @Column(name = "TOTAL_FASE", nullable = true)
  private Long totalFase;


}