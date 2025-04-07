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
* Clase Entidad para la tabla PGIM_VW_SUPER_DS_UM_AUX: 
* @descripción: Vista de cantidad de supervisiones por división supervisora y unidad minera
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_SUPER_DS_UM_AUX")
@Data
@NoArgsConstructor
public class PgimSuperDsUmAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_SUPER_DS_UM_AUX Secuencia: PGIM_SEQ_SUPER_DS_UM_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPER_DS_UM_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_SUPER_DS_UM_AUX", sequenceName = "PGIM_SEQ_SUPER_DS_UM_AUX", allocationSize = 1)
   @Column(name = "ID_SUPER_DS_UM_AUX", nullable = false)
  private Long idSuperDsUmAux;

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
  *ID_AGENTE_SUPERVISADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private PgimAgenteSupervisado pgimAgenteSupervisado;

  /*
  *ID_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = true)
  private PgimPersona pgimPersona;

  /*
  *NU_RUC
  */
   @Column(name = "NU_RUC", nullable = true)
  private String nuRuc;

  /*
  *NO_RAZON_SOCIAL
  */
   @Column(name = "NO_RAZON_SOCIAL", nullable = true)
  private String noRazonSocial;

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