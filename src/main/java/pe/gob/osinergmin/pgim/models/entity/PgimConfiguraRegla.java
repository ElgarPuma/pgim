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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_CONFIGURA_REGLA: 
* @descripción: Configuración de las reglas de configuración para el filtrado de las UM. Cada condición expresada en una fila debe ser evaluada en conjunto con el conector lógico AND. Por otro lado, una fila respecto a las demás debe ser evaluada con el conector lógico OR
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_CONFIGURA_REGLA")
@Data
@NoArgsConstructor
public class PgimConfiguraRegla implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la regla de la configuración de riesgo para el filtrado de las UM. Secuencia: PGIM_SEQ_CONFIGURA_REGLA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONFIGURA_REGLA")
   @SequenceGenerator(name = "PGIM_SEQ_CONFIGURA_REGLA", sequenceName = "PGIM_SEQ_CONFIGURA_REGLA", allocationSize = 1)
   @Column(name = "ID_CONFIGURA_REGLA", nullable = false)
  private Long idConfiguraRegla;

  /*
  *Identificador interno de la configuración del riesgo. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONFIGURA_RIESGO", nullable = false)
  private PgimConfiguraRiesgo pgimConfiguraRiesgo;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = METODO_MINADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_METODO_MINADO", nullable = true)
  private PgimValorParametro metodoMinado;

  /*
  *Situación de la unidad minera, de acuerdo con lo establecido por el Minem. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = SITUACION_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SITUACION", nullable = true)
  private PgimValorParametro situacion;

  /*
  *Tipo de actividad de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTIVIDAD. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = false)
  private PgimValorParametro tipoActividad;

  /*
  *Estado de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = ESTADO_UM. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESTADO", nullable = false)
  private PgimValorParametro estado;

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
  *Nombre de la configuración de riesgo
  */
 @Transient
  private String descNoConfiguracion;

  /*
  *DESC_NU_ORDEN
  */
 @Transient
  private Long descNuOrden;

  /*
  *DESC_NO_DIVISION_SUPERVISORA
  */
 @Transient
  private String descNoDivisionSupervisora;

  /*
  *DESC_NO_METODO_MINADO
  */
 @Transient
  private String descNoMetodoMinado;

  /*
  *DESC_NO_SITUACION
  */
 @Transient
  private String descNoSituacion;

  /*
  *DESC_ID_ESPECIALIDAD
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *Nombre del tipo de la actividad de la UM
  */
 @Transient
  private String descNoTipoActividad;

  /*
  *Nombre del estado de la UM
  */
 @Transient
  private String descNoEstado;


}