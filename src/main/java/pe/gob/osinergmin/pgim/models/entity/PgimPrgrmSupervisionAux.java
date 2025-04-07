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
* Clase Entidad para la tabla PGIM_VW_PRGRM_SUPERVISION_AUX: 
* @descripción: Vista para obtener la lista de los programas de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/12/2020
*/
@Entity
@Table(name = "PGIM_VW_PRGRM_SUPERVISION_AUX")
@Data
@NoArgsConstructor
public class PgimPrgrmSupervisionAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_PRGRM_SUPERVISION_AUX:  Secuencia: VW_PRGRM_SUPERVISION_AUX_SEQ
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XXX")
   @SequenceGenerator(name = "XXX", sequenceName = "XXX", allocationSize = 1)
   @Column(name = "ID_PROGRAMA_SUPERVISION_AUX", nullable = false)
  private Long idProgramaSupervisionAux;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PROGRAMA_SUPERVISION", nullable = true)
  private PgimPrgrmSupervision pgimPrgrmSupervision;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = true)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *ID_INSTANCIA_PASO
  */
  @Column(name = "ID_INSTANCIA_PASO", nullable = true)
  private Long idInstanciaPaso;  

  /*
  *Nombre de la especialidad
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *Descripción de la división supervisora a cargo de la supervisión de la unidad minera.
  */
   @Column(name = "DE_DIVISION_SUPERVISORA", nullable = true)
  private String deDivisionSupervisora;

  /*
  *Descripción del programa de supervisión
  */
   @Column(name = "NO_PROGRAMA_SUPERVISION", nullable = true)
  private String noProgramaSupervision;

  /*
  *Año al cual pertenece el plan de supervisión
  */
   @Column(name = "NU_ANIO", nullable = true)
  private Long nuAnio;

  /*
  *Monto en soles de la partida asignada al programa
  */
   @Column(name = "MO_PARTIDA", nullable = true)
  private BigDecimal moPartida;

  /*
  *Costo del monto del programa
  */
   @Column(name = "MO_COSTO_TOTAL", nullable = true)
  private BigDecimal moCostoTotal;

  /*
  *Nombre de persona asignada
  */
   @Column(name = "PERSONA_ASIGNADA", nullable = true)
  private String personaAsignada;

  /*
  *Código de usuario asignada
  */
   @Column(name = "USUARIO_ASIGNADO", nullable = true)
  private String usuarioAsignado;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FASE_ACTUAL", nullable = true)
  private PgimFaseProceso faseActual;

  /*
  *Nombre de la fase del proceso
  */
   @Column(name = "NO_FASE_ACTUAL", nullable = true)
  private String noFaseActual;

  /*
  *Identificador interno del paso actual del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_ACTUAL", nullable = true)
  private PgimPasoProceso pasoActual;

  /*
  *Nombre del paso del proceso
  */
   @Column(name = "NO_PASO_ACTUAL", nullable = true)
  private String noPasoActual;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RELACION_PASO", nullable = true)
  private PgimRelacionPaso pgimRelacionPaso;

  /*
  *Tipo de la relación del paso. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_RELACION_PASO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_RELACION", nullable = true)
  private PgimValorParametro tipoRelacion;

  /*
  *Descripción del tipo de relación
  */
   @Column(name = "NO_TIPO_RELACION", nullable = true)
  private String noTipoRelacion;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LINEA_PROGRAMA", nullable = true)
  private PgimLineaPrograma pgimLineaPrograma;

  /*
  *Fecha de la línea base del programa
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_LINEA_PROGRAMA", nullable = true)
  private Date feLineaPrograma;

  /*
  *Estado de la línea base actual. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = LINEA_PROGRAMA_ESTADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LINEA_PROGRAMA_ESTADO", nullable = true)
  private PgimValorParametro lineaProgramaEstado;

  /*
  *Descripción del estado de la línea base actual
  */
   @Column(name = "ESTADO_LINEA_BASE", nullable = true)
  private String estadoLineaBase;
   
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
   * FE_INSTANCIA_PASO
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FE_INSTANCIA_PASO", nullable = true)
  private Date feInstanciaPaso;

  /*
   * DE_MENSAJE
   */
  @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
   * FL_PASO_ACTIVO
   */
  @Column(name = "FL_PASO_ACTIVO", nullable = true)
  private String flPasoActivo;

}