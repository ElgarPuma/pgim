package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_PRGRM_SUPERVISION_AUX: 
* @descripción: Vista para obtener la lista de los programas de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/12/2020
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPrgrmSupervisionAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_PRGRM_SUPERVISION_AUX:  Secuencia: VW_PRGRM_SUPERVISION_AUX_SEQ
  */
  private Long idProgramaSupervisionAux;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
  private Long idProgramaSupervision;

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
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idDivisionSupervisora;

  /*
  *Descripción de la división supervisora a cargo de la supervisión de la unidad minera.
  */
  private String deDivisionSupervisora;

  /*
  *Descripción del programa de supervisión
  */
  private String noProgramaSupervision;

  /*
  *Año al cual pertenece el plan de supervisión
  */
  private Long nuAnio;

  /*
  *Monto en soles de la partida asignada al programa
  */
  private BigDecimal moPartida;

  /*
  *Costo del monto del programa
  */
  private BigDecimal moCostoTotal;

  /*
  *Nombre de persona asignada
  */
  private String personaAsignada;

  /*
  *Código de usuario asignada
  */
  private String usuarioAsignado;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
  private Long idFaseActual;

  /*
  *Nombre de la fase del proceso
  */
  private String noFaseActual;

  /*
  *Identificador interno del paso actual del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
  private Long idPasoActual;

  /*
  *Nombre del paso del proceso
  */
  private String noPasoActual;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
  private Long idRelacionPaso;

  /*
  *Tipo de la relación del paso. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_RELACION_PASO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idTipoRelacion;

  /*
  *Descripción del tipo de relación
  */
  private String noTipoRelacion;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
  private Long idLineaPrograma;

  /*
  *Fecha de la línea base del programa
  */
  private Date feLineaPrograma;

  /*
  *Fecha de la línea base del programa
  */
  private String feLineaProgramaDesc;

  /*
  *Estado de la línea base actual. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = LINEA_PROGRAMA_ESTADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idLineaProgramaEstado;

  /*
  *Descripción del estado de la línea base actual
  */
  private String estadoLineaBase;
  
  /*
  *FL_LEIDO
  */
  private String flLeido;
   
  /*
  *FE_LECTURA
  */
  private Date feLectura;

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

  /*
  *Nombre de la persona origen
  */
  private String noPersonaOrigen;

  /*
  *Nombre usuario origen
  */
  private String noUsuarioOrigen;

  /*
  *Flag de mis asignaciones
  */
  private String descFlagMisAsignaciones;

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

}