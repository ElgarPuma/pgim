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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_PRESUPUESTO_DS_ESP_AUX: 
* @descripción: Vista de presupuesto consolidado por división supervisora-especialidad
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_PRESUPUESTO_DS_ESP_AUX")
@Data
@NoArgsConstructor
public class PgimPresupuestoDsEspAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_PRESUPUESTO_DS_ESP_AUX Secuencia: PGIM_SEQ_PRESUPUESTO_DS_ESP_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PRESUPUESTO_DS_ESP_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_PRESUPUESTO_DS_ESP_AUX", sequenceName = "PGIM_SEQ_PRESUPUESTO_DS_ESP_AUX", allocationSize = 1)
   @Column(name = "ID_PRESUPUESTO_DS_ESP_AUX", nullable = false)
  private Long idPresupuestoDsEspAux;

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
  *MONTO_P1
  */
   @Column(name = "MONTO_P1", nullable = true)
  private BigDecimal montoP1;

  /*
  *MONTO_P2
  */
   @Column(name = "MONTO_P2", nullable = true)
  private BigDecimal montoP2;

  /*
  *MONTO_P3
  */
   @Column(name = "MONTO_P3", nullable = true)
  private BigDecimal montoP3;

  /*
  *MONTO_P4
  */
   @Column(name = "MONTO_P4", nullable = true)
  private BigDecimal montoP4;

  /*
  *MONTO_P5
  */
   @Column(name = "MONTO_P5", nullable = true)
  private BigDecimal montoP5;

  /*
  *MONTO_P6
  */
   @Column(name = "MONTO_P6", nullable = true)
  private BigDecimal montoP6;

  /*
  *TOTAL_MONTO
  */
   @Column(name = "TOTAL_MONTO", nullable = true)
  private BigDecimal totalMonto;


}