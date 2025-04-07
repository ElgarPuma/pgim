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
* Clase Entidad para la tabla PGIM_TC_ITEM_PROGRAMA_SUPE: 
* @descripción: Ítem del programa de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_ITEM_PROGRAMA_SUPE")
@Data
@NoArgsConstructor
public class PgimItemProgramaSupe implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del programa de supervisión. Secuencia: PGIM_SEQ_ITEM_PROGRAMA_SUPE
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ITEM_PROGRAMA_SUPE")
   @SequenceGenerator(name = "PGIM_SEQ_ITEM_PROGRAMA_SUPE", sequenceName = "PGIM_SEQ_ITEM_PROGRAMA_SUPE", allocationSize = 1)
   @Column(name = "ID_ITEM_PROGRAMA_SUPE", nullable = false)
  private Long idItemProgramaSupe;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBTIPO_SUPERVISION", nullable = false)
  private PgimSubtipoSupervision pgimSubtipoSupervision;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno del programa de supervisión base desde donde se copiaron los datos de este registro. Tabla padre: PGIM_TC_ITEM_PROGRAMA_SUPE
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ITEM_PROGRAMA_SUPE_BASE", nullable = true)
  private PgimItemProgramaSupe itemProgramaSupeBase;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LINEA_PROGRAMA", nullable = true)
  private PgimLineaPrograma pgimLineaPrograma;

  /*
  *Fecha inicial del mes estimado de ejecución, primero del mes seleccionado
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_MES_ESTIMADO", nullable = false)
  private Date feMesEstimado;

  /*
  *Monto del costo en soles estimado para el ítem de la supervisión
  */
   @Column(name = "MO_COSTO_ESTIMADO_SUPERVISION", nullable = false)
  private BigDecimal moCostoEstimadoSupervision;

  /*
  *Descripción del ítem del programa
  */
   @Column(name = "DE_ITEM_PROGRAMA", nullable = true)
  private String deItemPrograma;

  /*
  *Motivo de la cancelación del ítem del programa
  */
   @Column(name = "DE_MOTIVO_CANCELACION", nullable = true)
  private String deMotivoCancelacion;

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
  *Nombre del subtipo de supervisión
  */
 @Transient
  private String descSubtipoSupervision;

  /*
  *Identificador interno del agente supervisado
  */
 @Transient
  private Long descIdAgenteSupervisado;

  /*
  *Razón social de la empresa supervisora
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Flag de asignado: Sí o No
  */
 @Transient
  private String descFlAsignado;

  /*
  *Año del plan de supervisión
  */
 @Transient
  private Integer descNuAnio;

  /*
  *Nombre de la división supervisora de la unidad minera
  */
 @Transient
  private String descDivisionSupervision;

  /*
  *Nombre del método de minado de la unidad minera
  */
 @Transient
  private String descMetodoMinado;

  /*
  *Nombre de la situación de la unidad minera
  */
 @Transient
  private String descSituacionUm;

  /*
  *Indicador lógico de la unidad minera, que señala si el registro de datos de riesgo será o no mandatorio en el contexto de una fiscalizaciónPosibles valores: "1" = Sí y 0 = "No"
  */
 @Transient
  private String descFlRegistraRiesgos;

  /*
  *Listado de configuraciones de riesgo que aplican sobre la unidad minera del ítem del programa de supervisión
  */
 @Transient
  private String descDeConfiguracionesRiesgo;

  /*
  *Nombre del tipo de actividad
  */
 @Transient
  private String descNoTipoactividad;

  /*
  *Nombre del estado de la UM
  */
 @Transient
  private String descNoEstadoUm;


}