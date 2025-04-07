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
* Clase Entidad para la tabla PGIM_TD_COSTO_UNITARIO: 
* @descripción: Costo unitario en S/ detallado por día para le regla del contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_COSTO_UNITARIO")
@Data
@NoArgsConstructor
public class PgimCostoUnitario implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del costo unitario por día del contrato. Secuencia: PGIM_SEQ_COSTO_UNITARIO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_COSTO_UNITARIO")
   @SequenceGenerator(name = "PGIM_SEQ_COSTO_UNITARIO", sequenceName = "PGIM_SEQ_COSTO_UNITARIO", allocationSize = 1)
   @Column(name = "ID_COSTO_UNITARIO", nullable = false)
  private Long idCostoUnitario;

  /*
  *Identificador interno del tarifario del contrato. Tabla padre: PGIM_TD_TARIFARIO_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TARIFARIO_CONTRATO", nullable = false)
  private PgimTarifarioContrato pgimTarifarioContrato;

  /*
  *Número de día para el costo unitario
  */
   @Column(name = "UN_DIA_COSTO_UNITARIO", nullable = false)
  private Integer unDiaCostoUnitario;

  /*
  *Monto del costo unitario del día
  */
   @Column(name = "MO_COSTO_UNITARIO", nullable = false)
  private BigDecimal moCostoUnitario;

  /*
  *Monto del costo del acta
  */
   @Column(name = "MO_COSTO_ACTA", nullable = true)
  private BigDecimal moCostoActa;

  /*
  *Monto del costo del informe de supervisión
  */
   @Column(name = "MO_COSTO_INFORME_SUPERVISION", nullable = true)
  private BigDecimal moCostoInformeSupervision;

  /*
  *Monto del costo del informe de supervisión
  */
   @Column(name = "MO_COSTO_INFORME_GESTION", nullable = true)
  private BigDecimal moCostoInformeGestion;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Usuario creador
  */
   @Column(name = "US_CREACION", nullable = false)
  private String usCreacion;

  /*
  *Terminal de creación
  */
   @Column(name = "IP_CREACION", nullable = false)
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = false)
  private Date feCreacion;

  /*
  *Usuario modificador
  */
   @Column(name = "US_ACTUALIZACION", nullable = true)
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
   @Column(name = "IP_ACTUALIZACION", nullable = true)
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ACTUALIZACION", nullable = true)
  private Date feActualizacion;

  /*
  *Costo S/ del acta de supervisión
  */
 @Transient
  private String descCostoActa;

  /*
  *Costo S/ del informe de supervisión
  */
 @Transient
  private String descCostoInformeSupervision;

  /*
  *Costo S/ del informe de gestión
  */
 @Transient
  private String descCostoInformeGestion;

  /*
  *Monto del costo unitario del día
  */
 @Transient
  private String descMoCostoUnitario;

  /*
  *Nombre del tarifario del contrato
  */
 @Transient
  private String descNoTarifario;


}