package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TC_HECHO_CONSTATADO: 
* @descripción: Hecho constatado de la supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimHechoConstatadoDTO {

  /*
  *Identificador interno del hecho constatado de la supervisión. Secuencia: PGIM_SEQ_HECHO_CONSTATADO
  */
  private Long idHechoConstatado;

  /*
  *Identificador interno de la configuración de criterios de la matriz de supervisión, tomada para una supervisión en particular. Tabla padre: PGIM_TC_CRITERIO_SPRVSION
  */
  private Long idCriterioSprvsion;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Tipo de cumplimiento del hecho constatado. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CUMPLIMIENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoCumplimiento;

  /*
  *Identificador interno del rol del proceso. Tabla foránea PGIM_TM_ROL_PROCESO
  */
  private Long idRolProceso;

  /*
  *Identificador interno del hecho constatado origen de la supervisión. Tabla: PGIM_TC_HECHO_CONSTATADO
  */
  private Long idHechoConstatadoOrigen;

  /*
  *Identificador interno del hecho constatado reemplazante. Tabla: PGIM_TC_HECHO_CONSTATADO
  */
  private Long idHechoConstatadoRmplznte;

  /*
  *Descripción del hecho constatado
  */
  private String deHechoConstatadoT;

  /*
  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión.
  */
  private String deComplementoObservacion;

  /*
  *Sustento del hecho constatado
  */
  private String deSustentoT;

  /*
  *Flag que indica si el hecho constatado ha sido incluido en el acta de supervisión. Los posibles valores son: "1" = Incluido y "0" = No incluido
  */
  private String flIncluidoActaSupervision;

  /*
  *Comentario del personal responsable de Osinergmin (Especialista técnico o legal)
  */
  private String deComentarioOsiT;

  /*
  *Indica si el registro de hecho constatado es vigente o histórico. Valores: V=Vigente; H=Histórico
  */
  private String esVigente;

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
  *Descripción del tipo de cumplimiento.
  */
  private String descTipoCumplimiento;

  /*
  *Lista de Obligaciones de obligaciones
  */
  private List<PgimObligacionNormaAuxDTO> descListaObligaciones;
  private List<PgimCmineroSprvsionDTO> descListaCmineroSprvsion;
  private List<PgimComponenteHcDTO> descListaComponenteHcDTO;

  /*
  *ID de la fase del proceso de supervisión actual
  */
  private Long descFaseProcesoSupervisionActual;

  /*
  *Nombre valor parametro
  */
  private String descNoValorParametro;

  /*
  *Descripción de la obligación normativa
  */
  private String descDeObligacionNormativa;

  /*
  *Descripción del ítem de la norma
  */
  private String descDeNormaItem;

  /*
  *Descripción del incumplimiento detectado
  */
  private String descIncumplimientoDetectado;

  /*
  *ID de la fase del proceso de supervisión donde se ha ubicado al componente angular que muestra al hecho constatado
  */
  private Long descFaseProcesoSupervisionUbicado;

  /*
  *ID de la fase del proceso de supervisión donde se generó al hecho constatado
  */
  private Long descFaseProcesoSupervision;

  /*
  *Nombre del rol en el proceso
  */
  private String descNoRolProceso;

  /*
  *Nombre del responsable del registro o actualización del hecho constatado
  */
  private String descResponsable;

  /*
  *Fecha y hora histórico
  */
  private Date descFeHistorico;

  /*
  *Fecha y hora histórico
  */
  private String descFeHistoricoDesc;

  /*
  *Identificador del hecho constatado de la supervisión a reemplazar
  */
  private Long descIdHechoConstatadoSupervision;

  /*
  *Flag que indica si el hecho constatado fue registrado por un especialista técnico o legal de Osinergmin
  */
  private boolean descFlEspecialistaOsi;

  /*
  *Nombre de la fase auxiliar del hecho constatado
  */
  private String descNoFaseProcesoAux;
  
  /*
   * Código de criterio de matriz 
   */
  private String descCoMatrizCriterio;
  
  /*
   * Número de orden de grupo de criterio
   */
  private Long descNuOrdenGrpoCrtrio;
  
  /*
   * Número de Orden de criterio
   */
  private Long descNuOrdenCriterio;
  
  /*
   * Código de tipo de cumplimiento
   */
  private Long descCoTipoCumplimiento;

  /*
   * Nombre de la fase en donde se originó el hecho constatado
   */
  private String descNoFaseDestino;
  
  /*
   * Nombre del paso donde se originó el hecho constatado
   */
  private String descNoPasoDestino;
  
  /*
   * Identificador del matriz criterio del cuadro de verificación
   */
  private Long descIdMatrizCriterio;
  
  /*
   * Descriptor de la descripción de criterio de matriz 
   */
  private String descDeMatrizCriterio;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
     * Id de la especialidad
     */
  private Long descIdEspecialidad;
}