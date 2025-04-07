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
* Clase Entidad para la tabla PGIM_TD_ITEM_CONSUMO: 
* @descripción: Ítem del consumo del contrato de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_ITEM_CONSUMO")
@Data
@NoArgsConstructor
public class PgimItemConsumo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ítem del consumo del contrato. Secuencia: PGIM_SEQ_ITEM_CONSUMO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ITEM_CONSUMO")
   @SequenceGenerator(name = "PGIM_SEQ_ITEM_CONSUMO", sequenceName = "PGIM_SEQ_ITEM_CONSUMO", allocationSize = 1)
   @Column(name = "ID_ITEM_CONSUMO", nullable = false)
  private Long idItemConsumo;

  /*
  *Identificador interno del consumo del contrato. Tabla padre: PGIM_TD_CONSUMO_CONTRA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONSUMO_CONTRA", nullable = false)
  private PgimConsumoContra pgimConsumoContra;

  /*
  *Tipo de entregable. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ENTREGABLE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ENTREGABLE", nullable = false)
  private PgimValorParametro tipoEntregable;

  /*
  *Tipo de estadío del consumo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADIO_CONSUMO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ESTADIO_CONSUMO", nullable = false)
  private PgimValorParametro tipoEstadioConsumo;

  /*
  *Porcentaje del costo para el entregable del tipo dado. Se copian los valores de las columna PC_ENTREGABLE_ACTA, PC_ENTREGABLE_INFORME, PC_ENTREGABLE_FINAL de la tabla PGIM_TC_CONTRATO, según corresponda
  */
   @Column(name = "PC_ENTREGABLE", nullable = false)
  private Long pcEntregable;

  /*
  *Monto del ítem del consumo del contrato
  */
   @Column(name = "MO_ITEM_CONSUMO", nullable = false)
  private BigDecimal moItemConsumo;

  /*
  *Monto de la penalidad para el ítem
  */
   @Column(name = "MO_ITEM_PENALIDAD", nullable = true)
  private BigDecimal moItemPenalidad;

  /*
  *Monto de la supervisión fallida
  */
   @Column(name = "MO_ITEM_SUPERVISION_FALLIDA", nullable = true)
  private BigDecimal moItemSupervisionFallida;

  /*
  *Es vigente el consumo del contrato. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "ES_VIGENTE", nullable = true)
  private String esVigente;

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
  *DESC_TIPO_ENTREGABLE
  */
 @Transient
  private String descTipoEntregable;

  /*
  *DESC_ESTADIO_CONSUMO
  */
 @Transient
  private String descEstadioConsumo;

  /*
  *Identificador del tipo de entregable
  */
 @Transient
  private Long descIdTipoEntregable;

  /*
  *Descripción del tipo de entregable
  */
 @Transient
  private String descDeTipoEntregable;

  /*
  *Identificador de la supervisión
  */
 @Transient
  private Long descIdSupervision;

  /*
  *Código de la supervisión
  */
 @Transient
  private String descCoSupervision;

  /*
  *Descripción del tipo de la supervisión
  */
 @Transient
  private String descDeTipoSupervision;

  /*
  *Identificador del subtipo de la supervisión
  */
 @Transient
  private Long descIdSubtipoSupervision;

  /*
  *Descripción del subtipo de la supervisión
  */
 @Transient
  private String descDeSubtipoSupervision;

  /*
  *Descripción del subtipo de la supervisión
  */
 @Transient
  private Long descIdMotivoSupervision;

  /*
  *Descripción del motivo de la supervisión
  */
 @Transient
  private String descDeMotivoSupervision;

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
  *Identificador de agente supervisado
  */
 @Transient
  private Long descIdAgenteSupervisado;

  /*
  *Número de documento de identidad
  */
 @Transient
  private String descCoDocumentoIdentidad;

  /*
  *Nombre de la razón social
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Monto del consumo del contrato
  */
 @Transient
  private BigDecimal descMoConsumoContrato;

  /*
  *Identificador del contrato
  */
 @Transient
  private Long descIdContrato;

  /*
  *Número de contrato
  */
 @Transient
  private String descNuContrato;

  /*
  *Identificador de la división supervisora
  */
 @Transient
  private Long descIdDivisionSupervisora;

  /*
  *Nombre de la división supervisora
  */
 @Transient
  private String descNoDivisionSupervisora;

  /*
  *Código de la unidad minera
  */
 @Transient
  private String descCoUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descNoUnidadMinera;

  /*
  *Número de expediente Siged
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *Identificador del tipo de estadio del consumo del contrato
  */
 @Transient
  private Long descIdTipoEstadioConsumo;

  /*
  *Nombre del tipo de estadio del consumo del contrato
  */
 @Transient
  private String descNoTipoEstadioConsumo;

  /*
  *Identificador pre-comprometido del entregable del contrato
  */
 @Transient
  private Long descPreComprometido;

  /*
  *Identificador del estadio comprometido del entregable del contrato
  */
 @Transient
  private Long descComprometido;

  /*
  *Identificador del estadio por-liquidar del entregable del contrato
  */
 @Transient
  private Long descPorLiquidar;

  /*
  *Identificador del estadio liquidado del entregable del contrato
  */
 @Transient
  private Long descLiquidado;

  /*
  *Identificador del estadio facturado del entregable del contrato
  */
 @Transient
  private Long descFacturado;

  /*
  *DESC_ID_TIPO_SUPERVISION
  */
 @Transient
  private Long descIdTipoSupervision;


}