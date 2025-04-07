package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_CONFIGURA_RIESGO_AUX: 
* @descripción: Vista para la lista de configuraciones de riesgo y filtros avanzados
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimConfiguraRiesgoAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_CONFIGURA_RIESGO_AUX. Secuencia: PGIM_SEQ_CONFIGURA_RIESGO_AUX
  */
  private Long idConfiguraRiesgoAux;

  /*
  *Identificador interno de la configuración de riesgo del que fue copiada la configuración. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
  private Long idConfiguraRiesgoPadre;

  /*
  *idInstanciaPaso
  */
  private Long idInstanciaPaso;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String noEspecialidad;

  /*
  *Tipo de estado de configuración. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADO_CONFIGURACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoEstadoConfiguracion;

  /*
  *Nombre del valor de parámetro
  */
  private String noValorParametro;

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
  *Año de la configuración de riesgo
  */
  private String feConfiguracionAnio;

  /*
  *Tipo de configuración de riesgo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CONFIGURACION_RIESGO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoConfiguracionRiesgo;

  /*
  *Nombre del tipo de cofiguración de riesgo
  */
  private String noTipoConfiguracionRiesgo;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Identificador interno de la instancia del proceso
  */
  private Long idInstanciaProceso;

  /*
  *Nombre completo de la persona asignada
  */
  private String noPersonaAsignada;

  /*
  *Nombre windows del usuario asignado
  */
  private String noUsuarioAsignado;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
  private Long idFaseActual;

  /*
  *Nombre de la fase actual del proceso
  */
  private String noFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
  private Long idPasoActual;

  /*
  *Nombre del paso actual del proceso
  */
  private String noPasoActual;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long idRelacionPaso;

  /*
  *Tipo de la relación del paso actual. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_RELACION_PASO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoRelacion;

  /*
  *Código clave de la configuración de riesgo
  */
  private String coTipoConfiguracionRiesgo;
  
  /*
  *FL_LEIDO
  */
  private String flLeido;
  
  /*
  *FE_LECTURA
  */
  private Date feLectura;

  /*
   * NO_PERSONA_ORIGEN
   */
  private String noPersonaOrigen;

  /*
   * NO_USUARIO_ORIGEN
   */  
  private String noUsuarioOrigen;

  /*
   * FE_INSTANCIA_PASO
   */    
  private Date feInstanciaPaso;

  /*
   * DE_MENSAJE
   */      
  private String deMensaje;

  /*
   * FL_PASO_ACTIVO
   */        
  private String flPasoActivo;  

  /*
  *FE_LECTURA
  */
  private String feLecturaDesc;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}