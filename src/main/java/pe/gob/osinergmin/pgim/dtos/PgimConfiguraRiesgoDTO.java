package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_CONFIGURA_RIESGO: 
* @descripción: Configuración de la metodología de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimConfiguraRiesgoDTO {

  /*
  *Identificador interno de la configuración del riesgo. Secuencia: PGIM_SEQ_CONFIGURA_RIESGO
  */
  private Long idConfiguraRiesgo;

  /*
  *Identificador interno de la configuración de riesgo del que fue copiada la configuración. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
  private Long idConfiguraRiesgoPadre;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Tipo de estado de configuración. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADO_CONFIGURACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoEstadoConfiguracion;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Tipo de periodo que ubica temporalmente y con mejor precisión a la configuración de riesgo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_PERIODO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoPeriodo;

  /*
  *Tipo de configuración de riesgo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CONFIGURACION_RIESGO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoConfiguracionRiesgo;

  /*
  *Año del periodo de ubicación en el tiempo de la configuración de riesgo
  */
  private Long nuAnioPeriodo;

  /*
  *Nombre de la configuración de riesgo
  */
  private String noConfiguracion;

  /*
  *Descripción de la configuración de riesgo
  */
  private String deConfiguracion;

  /*
  *Fecha referente de la creación de la configuración
  */
  private Date feConfiguracion;

  /*
  *Fecha referente de la creación de la configuración
  */
  private String feConfiguracionDesc;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Usuario creador
  */
  private String usCreacion;

  /*
  *Terminal de creación
  */
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /*
  *Usuario modificador
  */
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private Date feActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  *Nombre de la especialidad
  */
  private String descNoEspecialidad;

  /*
  *Nombre valor parametro del tipo de estado
  */
  private String descNoValorParametro;

  /*
  *Año de la configuración de la metodología
  */
  private String descFeConfiguracionAnio;

  /*
  *Flag de mis asignaciones
  */
  private String descFlagMisAsignaciones;

  /*
  *Identificador interno de la relación del paso
  */
  private Long descIdRelacionPaso;

  /*
  *Identificador interno del personal del Osinergmin
  */
  private Long descIdPersonalOsi;

  /*
  *Nombre de la fase actual del proceso
  */
  private String descNoFaseActual;

  /*
  *Mensaje de la instancia de paso del proceso
  */
  private String descNoPasoActual;

  /*
  *Mensaje de la instancia de paso del proceso
  */
  private String descNoPersonaAsignada;

  /*
  *Usuario (Windows) de la persona responsable
  */
  private String descUsuarioAsignado;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
  private Long descIdFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
  private Long descIdPasoActual;

  /*
  *Id utilizado para poblar la matriz de pares
  */
  private Long descIdCfgGrupoRiesgoTemp;

  /*
  *Código clave del tipo de configuración de riesgo
  */
  private String descCoTipoConfiguracionRiesgo;

  /*
  *Nombre del tipo de configuraciòn de riesgo
  */
  private String descNoTipoConfiguracionRiesgo;

  /*
  *Código del tipo de periodo
  */
  private String descCoTipoPeriodo;

  /*
  *Nombre del tipo de periodo
  */
  private String descNoTipoPeriodo;

  /*
  *Nombre de la configuración de riesgo padre
  */
  private String descNoConfiguracionPadre;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
  *Propiedad leído y no leído
  */
  private String flLeido;
   
  /*
  *Fecha de lectura
  */
  private Date feLectura;

  /*
  *Nombre de la persona origen
  */
  private String noPersonaOrigen;

  /*
  *Nombre usuario origen
  */
  private String noUsuarioOrigen;  

  /*
  *
  */
  private Date descFeInstanciaPaso;  

  /*
   * Mensaje de la instancia de paso del proceso
   */
  private String descDeMensaje;

  /*
  *Propiedad leído y no leído
  */
  private String descFlPasoActivo;

  /*
  * descIdInstanciaPaso
  */
  private Long descIdInstanciaPaso;  

}