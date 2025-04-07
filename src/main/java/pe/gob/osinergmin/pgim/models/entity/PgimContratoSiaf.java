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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_CONTRATO_SIAF: 
* @descripción: Detalle SIAF del contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_CONTRATO_SIAF")
@Data
@NoArgsConstructor
public class PgimContratoSiaf implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del detalle del contrato SIAF. Secuencia: PGIM_SEQ_CONTRATO_SIAF
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONTRATO_SIAF")
   @SequenceGenerator(name = "PGIM_SEQ_CONTRATO_SIAF", sequenceName = "PGIM_SEQ_CONTRATO_SIAF", allocationSize = 1)
   @Column(name = "ID_CONTRATO_SIAF", nullable = false)
  private Long idContratoSiaf;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = false)
  private PgimContrato pgimContrato;

  /*
  *Número del SIAF
  */
   @Column(name = "NU_SIAF", nullable = false)
  private String nuSiaf;

  /*
  *Año al cual pertenece el código SIAF
  */
   @Column(name = "NU_ANIO", nullable = false)
  private Long nuAnio;

  /*
  *Monto del presupuesto SIAF
  */
   @Column(name = "MO_PRESUPUESTO_SIAF", nullable = false)
  private BigDecimal moPresupuestoSiaf;

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


}