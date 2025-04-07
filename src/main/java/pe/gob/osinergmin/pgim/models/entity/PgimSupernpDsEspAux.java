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
* Clase Entidad para la tabla PGIM_VW_SUPERNP_DS_ESP_AUX: 
* @descripción: Vista de de cantidad de supervisiones no programadas por división supervisora-especialidad
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_SUPERNP_DS_ESP_AUX")
@Data
@NoArgsConstructor
public class PgimSupernpDsEspAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_SUPERNP_DS_ESP_AUX Secuencia: PGIM_SEQ_SUPERNP_DS_ESP_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPERNP_DS_ESP_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_SUPERNP_DS_ESP_AUX", sequenceName = "PGIM_SEQ_SUPERNP_DS_ESP_AUX", allocationSize = 1)
   @Column(name = "ID_SUPERNP_DS_ESP_AUX", nullable = false)
  private Long idSupernpDsEspAux;

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
  *SUP_A1
  */
   @Column(name = "SUP_A1", nullable = true)
  private Long supA1;

  /*
  *SUP_A2
  */
   @Column(name = "SUP_A2", nullable = true)
  private Long supA2;

  /*
  *SUP_A3
  */
   @Column(name = "SUP_A3", nullable = true)
  private Long supA3;

  /*
  *SUP_A4
  */
   @Column(name = "SUP_A4", nullable = true)
  private Long supA4;

  /*
  *SUP_A5
  */
   @Column(name = "SUP_A5", nullable = true)
  private Long supA5;

  /*
  *SUP_A6
  */
   @Column(name = "SUP_A6", nullable = true)
  private Long supA6;

  /*
  *TOTAL_SUPERVISION
  */
   @Column(name = "TOTAL_SUPERVISION", nullable = true)
  private Long totalSupervision;


}