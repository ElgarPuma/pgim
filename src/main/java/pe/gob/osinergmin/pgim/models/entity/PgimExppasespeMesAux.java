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
* Clase Entidad para la tabla PGIM_VW_EXPPASESPE_MES_AUX: 
* @descripción: Vista agregada de la cantidad de expedientes de fiscalización notificados de los últimos 12 meses
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXPPASESPE_MES_AUX")
@Data
@NoArgsConstructor
public class PgimExppasespeMesAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la especialidad. Secuencia: PGIM_SEQ_ESPECIALIDAD_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ESPECIALIDAD_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ESPECIALIDAD_AUX", sequenceName = "PGIM_SEQ_ESPECIALIDAD_AUX", allocationSize = 1)
   @Column(name = "ID_ESPECIALIDAD_AUX", nullable = false)
  private BigDecimal idEspecialidadAux;

  /*
  *ID_ESPECIALIDAD
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private BigDecimal idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

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
  *NRO_EXP_PAS_P7
  */
   @Column(name = "NRO_EXP_PAS_P7", nullable = true)
  private BigDecimal nroExpPasP7;

  /*
  *NRO_EXP_PAS_P8
  */
   @Column(name = "NRO_EXP_PAS_P8", nullable = true)
  private BigDecimal nroExpPasP8;

  /*
  *NRO_EXP_PAS_P9
  */
   @Column(name = "NRO_EXP_PAS_P9", nullable = true)
  private BigDecimal nroExpPasP9;

  /*
  *NRO_EXP_PAS_P10
  */
   @Column(name = "NRO_EXP_PAS_P10", nullable = true)
  private BigDecimal nroExpPasP10;

  /*
  *NRO_EXP_PAS_P11
  */
   @Column(name = "NRO_EXP_PAS_P11", nullable = true)
  private BigDecimal nroExpPasP11;

  /*
  *NRO_EXP_PAS_P12
  */
   @Column(name = "NRO_EXP_PAS_P12", nullable = true)
  private BigDecimal nroExpPasP12;

  /*
  *TOTAL_ESPECIALIDAD
  */
   @Column(name = "TOTAL_ESPECIALIDAD", nullable = true)
  private BigDecimal totalEspecialidad;


}