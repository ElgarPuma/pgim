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
* Clase Entidad para la tabla PGIM_VW_SUPER_PROGRAMADA_AUX: 
* @descripción: Vista de supervisiones programadas por estrato (división supervisora) y especialidad
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_SUPER_PROGRAMADA_AUX")
@Data
@NoArgsConstructor
public class PgimSuperProgramadaAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_SUPER_PROGRAMADA_AUX Secuencia: PGIM_SEQ_SUPER_PROGRAMADA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPER_PROGRAMADA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_SUPER_PROGRAMADA_AUX", sequenceName = "PGIM_SEQ_SUPER_PROGRAMADA_AUX", allocationSize = 1)
   @Column(name = "ID_SUPER_PROGRAMADA_AUX", nullable = false)
  private Long idSuperProgramadaAux;

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
  *NRO_SUP_PRG_P1
  */
   @Column(name = "NRO_SUP_PRG_P1", nullable = true)
  private Long nroSupPrgP1;

  /*
  *NRO_SUP_PRG_P2
  */
   @Column(name = "NRO_SUP_PRG_P2", nullable = true)
  private Long nroSupPrgP2;

  /*
  *NRO_SUP_PRG_P3
  */
   @Column(name = "NRO_SUP_PRG_P3", nullable = true)
  private Long nroSupPrgP3;

  /*
  *NRO_SUP_PRG_P4
  */
   @Column(name = "NRO_SUP_PRG_P4", nullable = true)
  private Long nroSupPrgP4;

  /*
  *NRO_SUP_PRG_P5
  */
   @Column(name = "NRO_SUP_PRG_P5", nullable = true)
  private Long nroSupPrgP5;

  /*
  *NRO_SUP_PRG_P6
  */
   @Column(name = "NRO_SUP_PRG_P6", nullable = true)
  private Long nroSupPrgP6;

  /*
  *TOTAL_NRO_SUP_PRG
  */
   @Column(name = "TOTAL_NRO_SUP_PRG", nullable = true)
  private Long totalNroSupPrg;


}