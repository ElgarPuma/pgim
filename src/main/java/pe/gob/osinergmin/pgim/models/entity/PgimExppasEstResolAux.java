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
* Clase Entidad para la tabla PGIM_VW_EXPPAS_EST_RESOL_AUX: 
* @descripción: Vista de expedientes con PAS por estado de resolución, por división supervisora, especialidad y año
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXPPAS_EST_RESOL_AUX")
@Data
@NoArgsConstructor
public class PgimExppasEstResolAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_EXPPAS_EST_RESOL_AUX Secuencia: PGIM_SEQ_EST_RESOL_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EST_RESOL_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_EST_RESOL_AUX", sequenceName = "PGIM_SEQ_EST_RESOL_AUX", allocationSize = 1)
   @Column(name = "ID_EST_RESOL_AUX", nullable = false)
  private Long idEstResolAux;

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
  *CO_ESTADO
  */
   @Column(name = "CO_ESTADO", nullable = true)
  private String coEstado;

  /*
  *NO_ESTADO
  */
   @Column(name = "NO_ESTADO", nullable = true)
  private String noEstado;

  /*
  *ANIO_SUPER
  */
   @Column(name = "ANIO_SUPER", nullable = true)
  private String anioSuper;

  /*
  *NU_GEOMECANIA
  */
   @Column(name = "NU_GEOMECANIA", nullable = true)
  private Long nuGeomecania;

  /*
  *NU_GEOTECANIA
  */
   @Column(name = "NU_GEOTECANIA", nullable = true)
  private Long nuGeotecania;

  /*
  *NU_PLANTA_BENE
  */
   @Column(name = "NU_PLANTA_BENE", nullable = true)
  private Long nuPlantaBene;

  /*
  *NU_TRANSPORTE
  */
   @Column(name = "NU_TRANSPORTE", nullable = true)
  private Long nuTransporte;

  /*
  *NU_VENTILACION
  */
   @Column(name = "NU_VENTILACION", nullable = true)
  private Long nuVentilacion;

  /*
  *NU_TOTAL
  */
   @Column(name = "NU_TOTAL", nullable = true)
  private Long nuTotal;


}