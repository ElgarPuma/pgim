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

import java.util.List;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TC_LIQUIDACION: 
* @descripción: Liquidación del contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_LIQUIDACION")
@Data
@NoArgsConstructor
public class PgimLiquidacion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la liquidación. Secuencia: PGIM_SEQ_LIQUIDACION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_LIQUIDACION")
   @SequenceGenerator(name = "PGIM_SEQ_LIQUIDACION", sequenceName = "PGIM_SEQ_LIQUIDACION", allocationSize = 1)
   @Column(name = "ID_LIQUIDACION", nullable = false)
  private Long idLiquidacion;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = false)
  private PgimContrato pgimContrato;

  /*
  *Tipo de entregable. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ENTREGABLE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ENTREGABLE", nullable = false)
  private PgimValorParametro tipoEntregable;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
  private PgimSubtipoSupervision pgimSubtipoSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MOTIVO_SUPERVISION", nullable = true)
  private PgimMotivoSupervision pgimMotivoSupervision;

  /*
  *Número de la liquidación
  */
   @Column(name = "NU_LIQUIDACION", nullable = true)
  private String nuLiquidacion;

  /*
  *Flag que indica si existe penalidad "Vinculada al reemplazo del personal". Posibles valores: "1" = Sí y (0 o NULL) = "No".
  */
  @Column(name = "FL_REEMPLAZO_PERSONAL", nullable = true)
  private String flPenalidadReemplazoPersona;

  /*
  *Penalidad vinculada al reemplazo del personal.
  */
  @Column(name = "MO_PENALIDAD_REMPLAZO_PERSONA", nullable = true)
  private BigDecimal moPenalidadReemplazoPersona;

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
  *Identificador interno de la especialidad.
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *Número de contrato
  */
 @Transient
  private String descNuContrato;

  /*
  *Nombre valor parametro
  */
 @Transient
  private String descNoValorParametro;

  /*
  *Razón social de la empresa supervisora
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Número de expediente Siged
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *Número de expediente Siged del contrato
  */
 @Transient
  private String descNuExpedienteSigedContrato;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
 @Transient
  private Long descIdRelacionPaso;

  /*
  *Mensaje de la instancia de paso del proceso
  */
 @Transient
  private String descDeMensaje;

  /*
  *Flag de mis asignaciones
  */
 @Transient
  private String descFlagMisAsignaciones;

  /*
  *Nombre completo de la persona destino
  */
 @Transient
  private String descPersonaDestino;

  /*
  *Usuario (Windows) de la persona responsable
  */
 @Transient
  private String descUsuarioAsignado;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descNoUnidadMinera;

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
  *Fecha de origen del documento asociado al entregable
  */
 @Transient
  private Date descFeOrigenDocumento;

  /*
  *Fecha de la instancia de paso
  */
 @Transient
  private Date descFeInstanciaPaso;

  /*
  *Identificador interno del ítem de consumo
  */
 @Transient
  private Long descIdItemConsumo;

  /*
  *Identificador interno de la supervisión
  */
 @Transient
  private Long descIdSupervision;

  /*
  *Código del tipo de documento Siged
  */
 @Transient
  private Long descCoDocumentoSiged;

  /*
  *Asunto del documento relacionado con el entregable
  */
 @Transient
  private String descDeAsuntoDocumento;

  /*
  *Identificador interno de la persona del contrato como destinatario.
  */
 @Transient
  private Long descIdPersonalContrato;

  /*
  *Nombre de la división supervisora a cargo de la supervisión de la unidad minera asociada a la supervisión.
  */
 @Transient
  private String descDeDivisionSupervisora;

  /*
  *Nombre del tipo de la supervisión
  */
 @Transient
  private String descDeTipoSupervision;

  /*
  *Descripción del motivo de la supervisión
  */
 @Transient
  private String descDeMotivoSupervision;

  /*
  *Lista de entregables para la liquidación
  */
 @Transient
  private List<PgimEntregableLiquidaAux> descListaEntregables;


}