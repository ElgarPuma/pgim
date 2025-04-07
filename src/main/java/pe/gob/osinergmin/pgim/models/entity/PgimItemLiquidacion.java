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
* Clase Entidad para la tabla PGIM_TD_ITEM_LIQUIDACION: 
* @descripción: Ítem de la liquidación del contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_ITEM_LIQUIDACION")
@Data
@NoArgsConstructor
public class PgimItemLiquidacion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la liquidación. Secuencia: PGIM_SEQ_ITEM_LIQUIDACION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ITEM_LIQUIDACION")
   @SequenceGenerator(name = "PGIM_SEQ_ITEM_LIQUIDACION", sequenceName = "PGIM_SEQ_ITEM_LIQUIDACION", allocationSize = 1)
   @Column(name = "ID_ITEM_LIQUIDACION", nullable = false)
  private Long idItemLiquidacion;

  /*
  *Identificador interno de la liquidación. Tabla padre: PGIM_TC_LIQUIDACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LIQUIDACION", nullable = false)
  private PgimLiquidacion pgimLiquidacion;

  /*
  *Identificador interno del Ítem del consumo del contrato. Tabla padre: PGIM_TD_ITEM_CONSUMO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ITEM_CONSUMO", nullable = false)
  private PgimItemConsumo pgimItemConsumo;

  /*
  *Identificador interno del documento relacionado con el entregable. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = false)
  private PgimDocumento pgimDocumento;

  /*
  *Penalidad por incumplimiento de plazo
  */
   @Column(name = "MO_PENALIDAD_PLAZO", nullable = true)
  private BigDecimal moPenalidadPlazo;

  /*
  *Penalidad por reincidencia de observaciones
  */
   @Column(name = "MO_PENALIDAD_REINCIDENCIA", nullable = true)
  private BigDecimal moPenalidadReincidencia;

  /*
  *Penalidad por presentarse sin EPP
  */
   @Column(name = "MO_PENALIDAD_SIN_EPP", nullable = true)
  private BigDecimal moPenalidadSinEpp;

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
  *Monto de penalidad "Por usar equipos de protección personal (EPP) del agente fiscalizado".
  */
   @Column(name = "MO_EPP_AFISCALIZADO", nullable = true)
  private BigDecimal moPenalidadEppAFiscalizado;

  /*
  *Monto de penalidad "Sin contar con equipos".
  */
   @Column(name = "MO_SIN_EQUIPOS", nullable = true)
  private BigDecimal moPenalidadSinEquipos;

  /*
  *Monto de penalidad "Sin contar con instrumentos de medición".
  */
   @Column(name = "MO_SIN_INSTRUMENTOS", nullable = true)
  private BigDecimal moPenalidadSinInstrumentos;

  /*
  *Monto de penalidad "Contar con equipos defectuosos".
  */
   @Column(name = "MO_EQUIPOS_DEFECTUOSOS", nullable = true)
  private BigDecimal moPenalidadEqpDefectuosos;
  /*
  *Monto de penalidad "Contar con instrumentos de medición defectuosos".
  */
   @Column(name = "MO_EQP_MEDICION_DEFECTUOSOS", nullable = true)
  private BigDecimal moPenalidadEqpMedicionDefectuosos;
  /*
  *Monto de penalidad "Equipos sin certificado de calibración vigente".
  */
   @Column(name = "MO_EQP_CALIBRACION_NVIGENTE", nullable = true)
  private BigDecimal moPenalidadEqpCalibrNvigente;
  /*
  *Monto de penalidad "Instrumentos de medición sin certificado de calibración vigente".
  */
   @Column(name = "MO_INS_CALIBRACION_NVIGENTE", nullable = true)
  private BigDecimal moPenalidadInstrCalibrNvigente;
  /*
  *Monto de penalidad "Por alterar los formatos de las actas proporcionados".
  */
   @Column(name = "MO_ALTERAR_FORMATOS", nullable = true)
  private BigDecimal moPenalidadAlterarFormatos;
  /*
  *Monto de penalidad "Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)".
  */
   @Column(name = "MO_FRUSTRAR_FISCALIZACION", nullable = true)
  private BigDecimal moPenalidadFrustrarFiscalizacion;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descNoUnidadMinera;

  /*
  *Razón social del agente supervisado dueño de la unidad minera
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Identificador interno de la supervisión
  */
 @Transient
  private Long descIdSupervision;

  /*
  *Código de la supervisión
  */
 @Transient
  private String descCoSupervision;

  /*
  *Descripción del subtipo de la supervisión
  */
 @Transient
  private String descDeSubtipoSupervision;

  /*
  *Nombre valor parametro
  */
 @Transient
  private String descNoValorParametro;

  /*
  *Monto en S/ del consumo del contrato
  */
 @Transient
  private BigDecimal descMoConsumoContrato;

  /*
  *Monto en S/ del consumo del entregable del contrato asociado al ítem de consumo
  */
 @Transient
  private BigDecimal descMoItemConsumo;

  /*
  *Identificador interno del documento asociado al ítem de consumo correspondiente a un tipo de entregable
  */
 @Transient
  private Long descIdDocumento;

  /*
  *Número del expediente Siged
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *Identificador interno del contrato
  */
 @Transient
  private Long descIdContrato;

  /*
  *Fecha de inicio real de la supervisión
  */
 @Transient
  private Date descFeInicioSupervisionReal;

  /*
  *Fecha fin real de la supervisión
  */
 @Transient
  private Date descFeFinSupervisionReal;

  /*
  *Asunto del documento relacionado con el entregable
  */
 @Transient
  private String descDeAsuntoDocumento;

  /*
  *División de supervisión
  */
 @Transient
  private String descDivisionSupervision;

  /*
  *Cantidad de días de la supervisión
  */
 @Transient
  private Long cantidadDias;

  /*
  *Id instancia del proceso
  */
 @Transient
  private Long descIdInstanciaProceso;

  /*
  *Descripción del motivo de la supervisión
  */
 @Transient
  private String descDeMotivoSupervision;

  /*
  *Total de penalidades
  */
 @Transient
  private BigDecimal descTotalPenalidades;


}