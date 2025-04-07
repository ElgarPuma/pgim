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
* Clase Entidad para la tabla PGIM_TC_CONTRATO: 
* @descripción: Instancia de contrato de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_CONTRATO")
@Data
@NoArgsConstructor
public class PgimContrato implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del contrato. Secuencia: PGIM_SEQ_CONTRATO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONTRATO")
   @SequenceGenerator(name = "PGIM_SEQ_CONTRATO", sequenceName = "PGIM_SEQ_CONTRATO", allocationSize = 1)
   @Column(name = "ID_CONTRATO", nullable = false)
  private Long idContrato;

  /*
  *Identificador interno de la empresa supervisora. Tabla padre: PGIM_TM_EMPRESA_SUPERVISORA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_EMPRESA_SUPERVISORA", nullable = true)
  private PgimEmpresaSupervisora pgimEmpresaSupervisora;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = false)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *Número de contrato
  */
   @Column(name = "NU_CONTRATO", nullable = false)
  private String nuContrato;

  /*
  *Descripción de contrato
  */
   @Column(name = "DE_CONTRATO", nullable = true)
  private String deContrato;

  /*
  *Porcentaje del costo para el entregable tipo acta
  */
   @Column(name = "PC_ENTREGABLE_ACTA", nullable = false)
  private Long pcEntregableActa;

  /*
  *Porcentaje del costo para el entregable tipo informe
  */
   @Column(name = "PC_ENTREGABLE_INFORME", nullable = false)
  private Long pcEntregableInforme;

  /*
  *Porcentaje del costo para el entregable tipo informe
  */
   @Column(name = "PC_ENTREGABLE_FINAL", nullable = false)
  private Long pcEntregableFinal;

  /*
  *Fecha de inicio del contrato de acuerdo con lo firmado
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_CONTRATO", nullable = false)
  private Date feInicioContrato;

  /*
  *Fecha fin del contrato de acuerdo con lo firmado
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_CONTRATO", nullable = false)
  private Date feFinContrato;

  /*
  *Monto del importe del contrato de acuerdo con lo firmado
  */
   @Column(name = "MO_IMPORTE_CONTRATO", nullable = false)
  private BigDecimal moImporteContrato;

  /*
  *Número de días para que el supervisor/a presente el informe de supervisión
  */
   @Column(name = "NU_DIAS_ENTREGA_INFORME", nullable = true)
  private Integer nuDiasEntregaInforme;

  /*
  *Número de días calendario para que el fiscalizador realice la revisión del informe de fiscalización y determine su aprobación u observación
  */
   @Column(name = "NU_DIAS_REVISION_INFORME", nullable = false)
  private Integer nuDiasRevisionInforme;

  /*
  *Número de días para que el supervisor/a presente el informe de supervisión subsanado
  */
   @Column(name = "NU_DIAS_ABSOLUCION_INFORME", nullable = true)
  private Integer nuDiasAbsolucionInforme;

  /*
  *Último valor de la secuencia de la liquidación generado para el contrato, el valor NULL se tomará como 0
  */
   @Column(name = "SE_LIQUIDACION_CONTRATO", nullable = true)
  private Long seLiquidacionContrato;

  /*
  *Flag que indica si el contrato se encuentra vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "FL_ESTADO", nullable = false)
  private String flEstado;

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
  * Nombre Windows de usuario que se utilizará para todos los usuarios del contrato. Este dato se utilizará para obtener el identificador interno del usuario Siged.
  */
  @Column(name = "NO_USUARIO_X_DEFECTO", nullable = true)
  private String noUsuarioXDefecto;

  /*
  *Número de expediente Siged
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *Razón social de la empresa supervisora
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Nombre de la especialidad asociada al contrato de supervisión
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *Saldo del contrato
  */
 @Transient
  private BigDecimal descSaldoContrato;

  /*
  *Mensaje de la validación al validar saldo de contrato
  */
 @Transient
  private String descMensajeValidacionSaldoContrato;

  /*
  *Monto total comprometido del contrato
  */
 @Transient
  private BigDecimal descMontoTotalComprometido;

  /*
  *Resumen del consumo del contrato S/
  */
 @Transient
  private BigDecimal descResumenConsumoContrato;

  /*
  *Monto total pre-comprometido del contrato S/
  */
 @Transient
  private BigDecimal descMontoTotalPreComprometido;

  /*
  *Monto total por liquidar del contrato S/
  */
 @Transient
  private BigDecimal descMontoTotalPorLiquidar;

  /*
  *Monto total liquidado del contrato S/
  */
 @Transient
  private BigDecimal descMontoTotalLiquidado;

  /*
  *Monto total facturado del contrato S/
  */
 @Transient
  private BigDecimal descMontoTotalFacturado;

  /*
  *DESC_ID_PERSONA
  */
 @Transient
  private Long descIdPersona;

  /*
  *DESC_FL_CONSORCIO
  */
 @Transient
  private String descFlConsorcio;


}