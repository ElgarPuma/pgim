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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_CONFIGURA_RIESGO_AUX: 
* @descripción: Vista para la lista de configuraciones de riesgo y filtros avanzados
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_CONFIGURA_RIESGO_AUX")
@Data
@NoArgsConstructor
public class PgimConfiguraRiesgoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_CONFIGURA_RIESGO_AUX. Secuencia: PGIM_SEQ_CONFIGURA_RIESGO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONFIGURA_RIESGO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_CONFIGURA_RIESGO_AUX", sequenceName = "PGIM_SEQ_CONFIGURA_RIESGO_AUX", allocationSize = 1)
   @Column(name = "ID_CONFIGURA_RIESGO_AUX", nullable = false)
  private Long idConfiguraRiesgoAux;

  /*
  *Identificador interno de la configuración de riesgo del que fue copiada la configuración. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
   @Column(name = "ID_CONFIGURA_RIESGO_PADRE", nullable = true)
  private Long idConfiguraRiesgoPadre;

  /*
  *ID_INSTANCIA_PASO
  */
   @Column(name = "ID_INSTANCIA_PASO", nullable = true)
  private Long idInstanciaPaso;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *Tipo de estado de configuración. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADO_CONFIGURACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @Column(name = "ID_TIPO_ESTADO_CONFIGURACION", nullable = true)
  private Long idTipoEstadoConfiguracion;

  /*
  *Nombre del valor de parámetro
  */
   @Column(name = "NO_VALOR_PARAMETRO", nullable = true)
  private String noValorParametro;

  /*
  *Nombre de la configuración de riesgo
  */
   @Column(name = "NO_CONFIGURACION", nullable = true)
  private String noConfiguracion;

  /*
  *Descripción de la configuración de riesgo
  */
   @Column(name = "DE_CONFIGURACION", nullable = true)
  private String deConfiguracion;

  /*
  *Fecha referente de la creación de la configuración
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CONFIGURACION", nullable = true)
  private Date feConfiguracion;

  /*
  *Año de la configuración de riesgo
  */
   @Column(name = "FE_CONFIGURACION_ANIO", nullable = true)
  private String feConfiguracionAnio;

  /*
  *Tipo de configuración de riesgo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CONFIGURACION_RIESGO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @Column(name = "ID_TIPO_CONFIGURACION_RIESGO", nullable = true)
  private Long idTipoConfiguracionRiesgo;

  /*
  *Nombre del tipo de cofiguración de riesgo
  */
   @Column(name = "NO_TIPO_CONFIGURACION_RIESGO", nullable = true)
  private String noTipoConfiguracionRiesgo;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = true)
  private String esRegistro;

  /*
  *Identificador interno de la instancia del proceso
  */
   @Column(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private Long idInstanciaProceso;

  /*
  *Nombre completo de la persona asignada
  */
   @Column(name = "NO_PERSONA_ASIGNADA", nullable = true)
  private String noPersonaAsignada;

  /*
  *Nombre windows del usuario asignado
  */
   @Column(name = "NO_USUARIO_ASIGNADO", nullable = true)
  private String noUsuarioAsignado;

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
  *Código clave de la configuración de riesgo
  */
   @Column(name = "CO_TIPO_CONFIGURACION_RIESGO", nullable = true)
  private String coTipoConfiguracionRiesgo;
   
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
  *FE_INSTANCIA_PASO
  */
  @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INSTANCIA_PASO", nullable = true)
  private Date feInstanciaPaso;
  
  /*
  *DE_MENSAJE
  */
  @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
  *FL_PASO_ACTIVO
  */
  @Column(name = "FL_PASO_ACTIVO", nullable = true)
  private String flPasoActivo;

}