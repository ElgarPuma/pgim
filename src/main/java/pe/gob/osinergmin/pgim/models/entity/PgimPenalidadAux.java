package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.math.BigDecimal;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_PENALIDAD_AUX: 
* @descripción: Vista de penalidades del contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_PENALIDAD_AUX")
@Data
@NoArgsConstructor
public class PgimPenalidadAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_SUPER_DS_UM_AUX Secuencia: PGIM_SEQ_SUPER_DS_UM_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPER_DS_UM_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_SUPER_DS_UM_AUX", sequenceName = "PGIM_SEQ_SUPER_DS_UM_AUX", allocationSize = 1)
   @Column(name = "ID_PENALIDAD_AUX", nullable = false)
  private Long idPenalidadAux;

  /*
  *ID_LIQUIDACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LIQUIDACION", nullable = true)
  private PgimLiquidacion pgimLiquidacion;

  /*
  *NU_LIQUIDACION
  */
   @Column(name = "NU_LIQUIDACION", nullable = true)
  private String nuLiquidacion;

  /*
  *ID_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = true)
  private PgimContrato pgimContrato;

  /*
  *NU_CONTRATO
  */
   @Column(name = "NU_CONTRATO", nullable = true)
  private String nuContrato;

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
  *FE_CREACION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = true)
  private Date feCreacion;

  /*
  *ID_FASE_ACTUAL
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FASE_ACTUAL", nullable = true)
  private PgimFaseProceso faseActual;

  /*
  *Nombre de la fase actual del proceso
  */
   @Column(name = "NO_FASE_ACTUAL", nullable = true)
  private String noFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_ACTUAL", nullable = true)
  private PgimPasoProceso pasoActual;

  /*
  *Nombre del paso actual del proceso
  */
   @Column(name = "NO_PASO_ACTUAL", nullable = true)
  private String noPasoActual;

  /*
  *MO_ITEM_CONSUMO
  */
   @Column(name = "MO_ITEM_CONSUMO", nullable = true)
  private BigDecimal moItemConsumo;

  /*
  *MO_PENALIDAD_PLAZO
  */
   @Column(name = "MO_PENALIDAD_PLAZO", nullable = true)
  private BigDecimal moPenalidadPlazo;

  /*
  *MO_PENALIDAD_REINCIDENCIA
  */
   @Column(name = "MO_PENALIDAD_REINCIDENCIA", nullable = true)
  private BigDecimal moPenalidadReincidencia;

  /*
  *MO_PENALIDAD_SIN_EPP
  */
   @Column(name = "MO_PENALIDAD_SIN_EPP", nullable = true)
  private BigDecimal moPenalidadSinEpp;

  /*
  *MO_PENALIDAD
  */
   @Column(name = "MO_PENALIDAD", nullable = true)
  private BigDecimal moPenalidad;

  /*
  *Razon social de la supervisora
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Nombre del archivo a exportar
  */
 @Transient
  private String descNoArchivo;

  /*
  *Extensión del archivo a exportar
  */
 @Transient
  private String descExtension;

  /*
  *Fecha inicio usado para el reporte de penalidades por periodo contrato y supervisora
  */
 @Transient
  private Date descFeInicio;

  /*
  *Fecha fin usado para el reporte de penalidades por periodo contrato y supervisora
  */
 @Transient
  private Date descFeFin;

  /*
  *Título del reporte
  */
 @Transient
  private String deTituloReporte;


}