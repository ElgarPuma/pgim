package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VC_INSTAN_PASO_AUX: 
* @descripción: Vista para todos los datos relevantes de la instancia de paso del proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInstanPasoAuxDTO {

  /*
  *Identificador interno de la instancia de paso del proceso AUX. Secuencia: PGIM_SEQ_INSTANCIA_PASO
  */
  private Long idInstanciaPasoAux;

  /*
  *ID_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *ID_INSTANCIA_PROCESO
  */
  private Long idInstanciaProceso;

  /*
  *ID_RELACION_PASO
  */
  private Long idRelacionPaso;

  /*
  *ID_PERSONA_EQP_ORIGEN
  */
  private Long idPersonaEqpOrigen;

  /*
  *ID_PERSONA_EQP_DESTINO
  */
  private Long idPersonaEqpDestino;

  /*
  *FE_INSTANCIA_PASO
  */
  private Date feInstanciaPaso;

  /*
  *FE_INSTANCIA_PASO
  */
  private String feInstanciaPasoDesc;

  /*
  *Mensaje de la instancia de paso del proceso
  */
  private String deMensaje;

  /*
  *FL_INICIA_PROCESO
  */
  private String flIniciaProceso;

  /*
  *FL_REQUIERE_DESTINATARIO
  */
  private String flRequiereDestinatario;

  /*
  *FL_REQUIERE_APROBACION
  */
  private String flRequiereAprobacion;

  /*
  *ID_TIPO_RELACION
  */
  private Long idTipoRelacion;

  /*
  *DES_NO_TIPO_RELACION
  */
  private String desNoTipoRelacion;

  /*
  *ID_PASO_PROCESO_ORIGEN
  */
  private Long idPasoProcesoOrigen;

  /*
  *NO_PASO_PROCESO_ORIGEN
  */
  private String noPasoProcesoOrigen;

  /*
  *DE_PASO_PROCESO_ORIGEN
  */
  private String dePasoProcesoOrigen;

  /*
  *ID_PASO_PROCESO_DESTINO
  */
  private Long idPasoProcesoDestino;

  /*
  *NO_PASO_PROCESO_DESTINO
  */
  private String noPasoProcesoDestino;

  /*
  *DE_PASO_PROCESO_DESTINO
  */
  private String dePasoProcesoDestino;

  /*
  *Flag que indica si el paso requiere notificación electrónicamente a través del SNE, de algunos de las subcategorías de documentos. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flNotificableE;

  /*
  *ID_FASE_PROCESO_ORIGEN
  */
  private Long idFaseProcesoOrigen;

  /*
  *NO_FASE_PROCESO_ORIGEN
  */
  private String noFaseProcesoOrigen;

  /*
  *DE_FASE_PROCESO_ORIGEN
  */
  private String deFaseProcesoOrigen;

  /*
  *ID_FASE_PROCESO_DESTINO
  */
  private Long idFaseProcesoDestino;

  /*
  *NO_FASE_PROCESO_DESTINO
  */
  private String noFaseProcesoDestino;

  /*
  *DE_FASE_PROCESO_DESTINO
  */
  private String deFaseProcesoDestino;

  /*
  *ID_PERSONA_ORIGEN
  */
  private Long idPersonaOrigen;

  /*
  *NO_USUARIO_ORIGEN
  */
  private String noUsuarioOrigen;

  /*
  *CO_USUARIO_SIGED_ORIGEN
  */
  private Long coUsuarioSigedOrigen;

  /*
  *NO_PERSONA_ORIGEN
  */
  private String noPersonaOrigen;

  /*
  *AP_PATERNO_ORIGEN
  */
  private String apPaternoOrigen;

  /*
  *AP_MATERNO_ORIGEN
  */
  private String apMaternoOrigen;

  /*
  *ID_PERSONA_DESTINO
  */
  private Long idPersonaDestino;

  /*
  *NO_USUARIO_DESTINO
  */
  private String noUsuarioDestino;

  /*
  *CO_USUARIO_SIGED_DESTINO
  */
  private Long coUsuarioSigedDestino;

  /*
  *NO_PERSONA_DESTINO
  */
  private String noPersonaDestino;

  /*
  *AP_PATERNO_DESTINO
  */
  private String apPaternoDestino;

  /*
  *AP_MATERNO_DESTINO
  */
  private String apMaternoDestino;

  /*
  *ID_ROL_PROCESO_ORIGEN
  */
  private Long idRolProcesoOrigen;

  /*
  *NO_ROL_PROCESO_ORIGEN
  */
  private String noRolProcesoOrigen;

  /*
  *DE_ROL_PROCESO_ORIGEN
  */
  private String deRolProcesoOrigen;

  /*
  *ID_ROL_PROCESO_DESTINO
  */
  private Long idRolProcesoDestino;

  /*
  *NO_ROL_PROCESO_DESTINO
  */
  private String noRolProcesoDestino;

  /*
  *DE_ROL_PROCESO_DESTINO
  */
  private String deRolProcesoDestino;

  /*
  *DE_ENTIDAD_PERSONA_ORIGEN
  */
  private String deEntidadPersonaOrigen;

  /*
  *DE_ENTIDAD_PERSONA_DESTINO
  */
  private String deEntidadPersonaDestino;

  /*
  *FL_ES_PASO_ACTIVO
  */
  private String flEsPasoActivo;

  /*
  *Flag que indica si la persona destino está interesada en la instancia de proceso. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flPersDestinoInteresada;

  /*
  *Nombre completo de la persona origen
  */
  private String descNoCompletoPersonaOrigen;

  /*
  *Nombre completo de la persona destino
  */
  private String descNoCompletoPersonaDestino;

  /*
  *Tipo de acción SIGED. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_ACCION_SIGED. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idTipoAccionSiged;

  /*
  *Nombre del tipo de acción Siged
  */
  private String deTipoAccionSiged;

  /*
  *Nombre del proceso
  */
  private String noProceso;

  /*
  *Etiqueta del objeto del flujo de trabajo
  */
  private String noEtiquetaOtrabajo;  
  
  /*
  *ID_TIPO_SUBFLUJO
  */
  private Long idTipoSubflujo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * Id del personal del contrato
   */
  private Long descIdPersonalContrato;

  /**
   * Id del personal del Osinergmin
   */
  private Long descIdPersonalOsi;

  /**
   * Número del contrato
   */
  private String descNuContrato;

  /**
   * Foto de la persona destino
   */
  private String descFotoBase64Destino;

  /**
   * Foto de la persona origen
   */
  private String descFotoBase64Origen;
}