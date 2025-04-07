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
* Clase Entidad para la tabla PGIM_TD_CONSUMO_CONTRA: 
* @descripción: Consumo del contrato de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_CONSUMO_CONTRA")
@Data
@NoArgsConstructor
public class PgimConsumoContra implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del consumo del contrato. Secuencia: PGIM_SEQ_CONSUMO_CONTRA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONSUMO_CONTRA")
   @SequenceGenerator(name = "PGIM_SEQ_CONSUMO_CONTRA", sequenceName = "PGIM_SEQ_CONSUMO_CONTRA", allocationSize = 1)
   @Column(name = "ID_CONSUMO_CONTRA", nullable = false)
  private Long idConsumoContra;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = true)
  private PgimContrato pgimContrato;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *Monto de consumo del contrato
  */
   @Column(name = "MO_CONSUMO_CONTRATO", nullable = false)
  private BigDecimal moConsumoContrato;

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
  *Nombre del tarifario del contrato
  */
 @Transient
  private String descNoTarifario;

  /*
  *Nombre del tarifario del contrato
  */
 @Transient
  private BigDecimal descMoSupervisionFallida;

  /*
  *Costo unitario determinado en el tarifario
  */
 @Transient
  private BigDecimal descMoCostoUnitario;

  /*
  *Cantidad de días calculados entre el inicio y final de la supervisión
  */
 @Transient
  private Long descDiasCalculadosInicioFin;

  /*
  *Saldo S/ del contrato
  */
 @Transient
  private BigDecimal descSaldoContrato;

  /*
  *Monto S/ faltante
  */
 @Transient
  private BigDecimal descMontoFaltante;


}