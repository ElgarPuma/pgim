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
* Clase Entidad para la tabla PGIM_VW_EXPPASTIPSUST_ANIO_AUX: 
* @descripción: Vista de expedientes con PAS por tipo de sustancia de UM y año
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXPPASTIPSUST_ANIO_AUX")
@Data
@NoArgsConstructor
public class PgimExppastipsustAnioAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_EXPPASTIPSUST_ANIO_AUX. Secuencia: PGIM_SEQ_TIPO_SUSTANCIA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TIPO_SUSTANCIA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_TIPO_SUSTANCIA_AUX", sequenceName = "PGIM_SEQ_TIPO_SUSTANCIA_AUX", allocationSize = 1)
   @Column(name = "ID_TIPO_SUSTANCIA_AUX", nullable = false)
  private Long idTipoSustanciaAux;

  /*
  *ID_TIPO_SUSTANCIA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_SUSTANCIA", nullable = true)
  private PgimValorParametro tipoSustancia;

  /*
  *NO_TIPO_SUSTANCIA
  */
   @Column(name = "NO_TIPO_SUSTANCIA", nullable = true)
  private String noTipoSustancia;

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