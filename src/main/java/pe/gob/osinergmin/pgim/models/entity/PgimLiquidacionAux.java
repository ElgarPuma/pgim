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
* Clase Entidad para la tabla PGIM_VW_LIQUIDACION_AUX: 
* @descripción: Vista para obtener la lista de las liquidaciones
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_LIQUIDACION_AUX")
@Data
@NoArgsConstructor
public class PgimLiquidacionAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la liquidación AUX. Secuencia: PGIM_SEQ_LIQUIDACION_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_LIQUIDACION_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_LIQUIDACION_AUX", sequenceName = "PGIM_SEQ_LIQUIDACION_AUX", allocationSize = 1)
   @Column(name = "ID_LIQUIDACION_AUX", nullable = false)
  private Long idLiquidacionAux;

  /*
  *Identificador interno de la liquidación. Tabla padre: PGIM_TC_LIQUIDACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LIQUIDACION", nullable = true)
  private PgimLiquidacion pgimLiquidacion;

  /*
  *Número de la liquidación
  */
   @Column(name = "NU_LIQUIDACION", nullable = true)
  private String nuLiquidacion;

  /*
  *idInstanciaPaso
  */
   @Column(name = "ID_INSTANCIA_PASO", nullable = true)
  private Long idInstanciaPaso;
     
  /*
  *Identificador interno del tipo de la liquidación
  */
   @Column(name = "ID_TIPO_ENTREGABLE_LIQUIDACION", nullable = true)
  private Long idTipoEntregableLiquidacion;

  /*
  *Nombre del tipo de entregable
  */
   @Column(name = "TIPO_ENTREGABLE_LIQUIDACION", nullable = true)
  private String tipoEntregableLiquidacion;

  /*
  *Monto del ítem del consumo del contrato
  */
   @Column(name = "MO_ITEM_CONSUMO", nullable = true)
  private BigDecimal moItemConsumo;

  /*
  *Monto de la penalidad para el ítem
  */
   @Column(name = "MO_ITEM_PENALIDAD", nullable = true)
  private BigDecimal moItemPenalidad;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @Column(name = "ID_CONTRATO", nullable = true)
  private Long idContrato;

  /*
  *Número de contrato
  */
   @Column(name = "NU_CONTRATO", nullable = true)
  private String nuContrato;

  /*
  *Descripción de contrato
  */
   @Column(name = "DE_CONTRATO", nullable = true)
  private String deContrato;

  /*
  *Identificador interno de la Especialidad
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *Identificador interno de la empresa supervisora. Tabla padre: PGIM_TM_EMPRESA_SUPERVISORA
  */
   @Column(name = "ID_EMPRESA_SUPERVISORA", nullable = true)
  private Long idEmpresaSupervisora;

  /*
  *Razón social de la empresa supervisora
  */
   @Column(name = "NO_RAZON_SOCIAL_SUPERVISORA", nullable = true)
  private String noRazonSocialSupervisora;

  /*
  *Número de expediente Siged
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *Nombre completo de la persona asignada a la instancia de flujo de una liquidación
  */
   @Column(name = "PERSONA_ASIGNADA", nullable = true)
  private String personaAsignada;

  /*
  *Usuario (Windows) de la persona asignada
  */
   @Column(name = "USUARIO_ASIGNADO", nullable = true)
  private String usuarioAsignado;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
   @Column(name = "ID_FASE_ACTUAL", nullable = true)
  private Long idFaseActual;

  /*
  *Nombre de la fase actual del proceso
  */
   @Column(name = "NO_FASE_ACTUAL", nullable = true)
  private String noFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
   @Column(name = "ID_PASO_ACTUAL", nullable = true)
  private Long idPasoActual;

  /*
  *Nombre del paso actual del proceso
  */
   @Column(name = "NO_PASO_ACTUAL", nullable = true)
  private String noPasoActual;

  /*
  *Identificador interno de la instancia del proceso de liquidación. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @Column(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
   @Column(name = "ID_RELACION_PASO", nullable = true)
  private Long idRelacionPaso;

  /*
  *Tipo de la relación del paso actual. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_RELACION_PASO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @Column(name = "ID_TIPO_RELACION", nullable = true)
  private Long idTipoRelacion;

  /*
  *Descripción del tipo de relación del paso actual
  */
   @Column(name = "TIPO_RELACION_PASO", nullable = true)
  private String tipoRelacionPaso;

  /*
  *Fecha de creación de la liquidación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = true)
  private Date feCreacion;

  /*
  **División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *Descripción de la división supervisora a cargo de la supervisión de la unidad minera
  */
   @Column(name = "DE_DIVISION_SUPERVISORA", nullable = true)
  private String deDivisionSupervisora;

  /*
  *ID_TIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_SUPERVISION", nullable = true)
  private PgimValorParametro tipoSupervision;

  /*
  *NO_TIPO_SUPERVISION
  */
   @Column(name = "NO_TIPO_SUPERVISION", nullable = true)
  private String noTipoSupervision;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
  private PgimSubtipoSupervision pgimSubtipoSupervision;

  /*
  *Descripción del subtipo de supervisión
  */
   @Column(name = "DE_SUBTIPO_SUPERVISION", nullable = true)
  private String deSubtipoSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MOTIVO_SUPERVISION", nullable = true)
  private PgimMotivoSupervision pgimMotivoSupervision;

  /*
  *Descripción del motivo de supervisión
  */
   @Column(name = "DE_MOTIVO_SUPERVISION", nullable = true)
  private String deMotivoSupervision;

  /*
  *FL_LEIDO
  */
   @Column(name = "FL_LEIDO", nullable = true)
  private String flLeido;

  /*
  *FE_LECTURA
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_LECTURA", nullable = true)
  private Date feLectura;
     
  /*
  *NO_PERSONA_ORIGEN
  */
  @Column(name = "NO_PERSONA_ORIGEN", nullable = true)
  private String noPersonaOrigen;

  /*
  *NO_USUARIO_ORIGEN
  */
   @Column(name = "NO_USUARIO_ORIGEN", nullable = true)
  private String noUsuarioOrigen;

  /*
   * Fecha de la instancia del paso
   */
  @Column(name = "FE_INSTANCIA_PASO", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date feInstanciaPaso;

  /*
   * Mensaje de la asignación del paso
   */
  @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
   * Flag que señala si el paso está o no activo
   */
  @Column(name = "FL_PASO_ACTIVO", nullable = true)
  private String flPasoActivo;
  
  /*
  *Flag de mis asignaciones
  */
 @Transient
  private String descFlagMisAsignaciones;

  /*
  *Usuario (Windows) de la persona responsable
  */
 @Transient
  private String descUsuarioAsignado;

  /*
  *Nombre completo de la persona destino
  */
 @Transient
  private String descPersonaDestino;


}