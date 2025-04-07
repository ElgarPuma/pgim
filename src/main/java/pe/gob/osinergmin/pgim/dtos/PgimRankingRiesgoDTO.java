package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
* DTO para la entidad PGIM_TC_RANKING_RIESGO: 
* @descripción: Ranking de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRankingRiesgoDTO {

  /*
  *Identificador interno del ranking de riesgo. Secuencia: PGIM_SEQ_RANKING_RIESGO
  */
  private Long idRankingRiesgo;

  /*
  *Identificador interno de la configuración del riesgo. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
  private Long idConfiguraRiesgo;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Nombre del ranking de riesgo
  */
  private String noRanking;

  /*
  *Descripción del ranking de riesgo
  */
  private String deRanking;

  /*
  *Fecha de generación del ranking
  */
  private Date feGeneracionRanking;

  /*
  *Fecha de generación del ranking
  */
  private String feGeneracionRankingDesc;

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
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long descIdEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String descNoEspecialidad;

  /*
  *Tipo de estado de configuración. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADO_CONFIGURACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long descIdTipoEstadoConfiguracion;

  /*
  *Tipo de estado de configuración.
  */
  private String descTipoEstadoConfiguracion;

  /*
  *Año de configuración del ranking
  */
  private String descFeGeneracionRankingAnio;

  /*
  *Nombre de la configuración de riesgo
  */
  private String descNoConfiguracion;

  /*
  *Descripción de la configuración de riesgo
  */
  private String descDeConfiguracion;

  /*
  *Fecha referente de la creación de la configuración
  */
  private Date descFeConfiguracion;

  /*
  *Fecha referente de la creación de la configuración
  */
  private String descFeConfiguracionDesc;

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
  *Nombre de la persona asignada al paso actual del fliujo del ranking de riesgo
  */
  private String descNoPersonaAsignada;

  /*
  *Usuario (Windows) de la persona responsable
  */
  private String usuarioAsignado;

  /*
  *Flag de mis asignaciones
  */
  private String descFlagMisAsignaciones;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
  private Long descIdFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
  private Long descIdPasoActual;

  /*
  *DESC_CO_TIPO_CONFIGURACION_RIESGO
  */
  private String descCoTipoConfiguracionRiesgo;

  /*
  *DESC_NO_TIPO_CONFIGURACION_RIESGO
  */
  private String descNoTipoConfiguracionRiesgo;

  /*
  *DESC_NO_TIPO_PERIODO
  */
  private String descNoTipoPeriodo;

  /*
  *DESC_NU_ANIO_PERIODO
  */
  private Long descNuAnioPeriodo;

  /*
  *DESC_ID_TIPO_CONFIGURACION_RIESGO
  */
  private Long descIdTipoConfiguracionRiesgo;

  /*
  *DESC_ID_TIPO_PERIODO
  */
  private Long descIdTipoPeriodo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /******nuevo******** */
  /*
  *FL_LEIDO
  */
  private String flLeido;

  /*
  *FE_LECTURA
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
   * NO_USUARIO_ORIGEN
   */  
  private String descNoUsuarioOrigen;

  /*
   * FE_INSTANCIA_PASO
   */  
  private Date descFeInstanciaPaso;

  /*
   * Mensaje de la instancia de paso del proceso
   */  
  private String descDeMensaje;

  /*
   * FL_PASO_ACTIVO
   */  
  private String DescFlPasoActivo;

  /*
  * descIdInstanciaPaso
  */
  private Long descIdInstanciaPaso;    
  
  /**
   * Cantidad de unidades minera que contiene el ranking
   */
  private Long descCantUnidadMinera;    
  
  /**
   * Puntaje total que tiene el ranking
   */
  private BigDecimal descPuntajeTotalRanking;
  
  /**
   * numero de orden del ranking usado para la 
   * generación de item de programa a partir de un ranking
   */
  private Long descNuOrden;


}