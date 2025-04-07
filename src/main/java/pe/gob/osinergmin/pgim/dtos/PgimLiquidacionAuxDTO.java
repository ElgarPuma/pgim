package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_LIQUIDACION_AUX: 
* @descripción: Vista para obtener la lista de las liquidaciones
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimLiquidacionAuxDTO {

  /*
  *Identificador interno de la liquidación AUX. Secuencia: PGIM_SEQ_LIQUIDACION_AUX
  */
  private Long idLiquidacionAux;

  /*
  *Identificador interno de la liquidación. Tabla padre: PGIM_TC_LIQUIDACION
  */
  private Long idLiquidacion;

  /*
  *Número de la liquidación
  */
  private String nuLiquidacion;
  
  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Identificador interno del tipo de la liquidación
  */
  private Long idTipoEntregableLiquidacion;

  /*
  *Nombre del tipo de entregable
  */
  private String tipoEntregableLiquidacion;

  /*
  *Monto del ítem del consumo del contrato
  */
  private BigDecimal moItemConsumo;

  /*
  *Monto de la penalidad para el ítem
  */
  private BigDecimal moItemPenalidad;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
  private Long idContrato;

  /*
  *Número de contrato
  */
  private String nuContrato;

  /*
  *Descripción de contrato
  */
  private String deContrato;

  /*
  *Identificador interno de la Especialidad
  */
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String noEspecialidad;

  /*
  *Identificador interno de la empresa supervisora. Tabla padre: PGIM_TM_EMPRESA_SUPERVISORA
  */
  private Long idEmpresaSupervisora;

  /*
  *Razón social de la empresa supervisora
  */
  private String noRazonSocialSupervisora;

  /*
  *Número de expediente Siged
  */
  private String nuExpedienteSiged;

  /*
  *Nombre completo de la persona asignada a la instancia de flujo de una liquidación
  */
  private String personaAsignada;

  /*
  *Usuario (Windows) de la persona asignada
  */
  private String usuarioAsignado;

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
  *Identificador interno de la instancia del proceso de liquidación. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long idRelacionPaso;

  /*
  *Tipo de la relación del paso actual. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_RELACION_PASO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoRelacion;

  /*
  *Descripción del tipo de relación del paso actual
  */
  private String tipoRelacionPaso;

  /*
  *Fecha de creación de la liquidación
  */
  private Date feCreacion;

  /*
  *Fecha de creación de la liquidación
  */
  private String feCreacionDesc;

  /*
  **División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idDivisionSupervisora;

  /*
  *Descripción de la división supervisora a cargo de la supervisión de la unidad minera
  */
  private String deDivisionSupervisora;

  /*
  *ID_TIPO_SUPERVISION
  */
  private Long idTipoSupervision;

  /*
  *NO_TIPO_SUPERVISION
  */
  private String noTipoSupervision;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *Descripción del subtipo de supervisión
  */
  private String deSubtipoSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
  private Long idMotivoSupervision;

  /*
  *Descripción del motivo de supervisión
  */
  private String deMotivoSupervision;
  
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
  * Fecha de la instancia del paso
  */
  private Date feInstanciaPaso;

  /*
  *Mensaje de la asignación del paso
  */
  private String deMensaje;  

  /*
  * Flag que señala si el paso está o no activo
  */
  private String flPasoActivo;    

  /*
  *FE_LECTURA
  */
  private String feLecturaDesc;

  /*
  *Flag que indica si existe penalidad "Vinculada al reemplazo del personal". Posibles valores: "1" = Sí y (0 o NULL) = "No".
  */
  private String descFlPenalidadReemplazoPersona;

  /*
  *Penalidad vinculada al reemplazo del personal.
  */
  private BigDecimal descMoPenalidadReemplazoPersona;

  /*
  *Flag de mis asignaciones
  */
  private String descFlagMisAsignaciones;

  /*
  *Usuario (Windows) de la persona responsable
  */
  private String descUsuarioAsignado;

  /*
  *Nombre completo de la persona destino
  */
  private String descPersonaDestino;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;
  
}