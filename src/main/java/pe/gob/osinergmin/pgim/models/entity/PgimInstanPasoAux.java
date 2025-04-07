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
* Clase Entidad para la tabla PGIM_VC_INSTAN_PASO_AUX: 
* @descripción: Vista para todos los datos relevantes de la instancia de paso del proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VC_INSTAN_PASO_AUX")
@Data
@NoArgsConstructor
public class PgimInstanPasoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la instancia de paso del proceso AUX. Secuencia: PGIM_SEQ_INSTANCIA_PASO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INSTANCIA_PASO")
   @SequenceGenerator(name = "PGIM_SEQ_INSTANCIA_PASO", sequenceName = "PGIM_SEQ_INSTANCIA_PASO", allocationSize = 1)
   @Column(name = "ID_INSTANCIA_PASO_AUX", nullable = false)
  private Long idInstanciaPasoAux;

  /*
  *ID_INSTANCIA_PASO
  */
   @Column(name = "ID_INSTANCIA_PASO", nullable = true)
  private Long idInstanciaPaso;

  /*
  *ID_INSTANCIA_PROCESO
  */
   @Column(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private Long idInstanciaProceso;

  /*
  *ID_RELACION_PASO
  */
   @Column(name = "ID_RELACION_PASO", nullable = true)
  private Long idRelacionPaso;

  /*
  *ID_PERSONA_EQP_ORIGEN
  */
   @Column(name = "ID_PERSONA_EQP_ORIGEN", nullable = true)
  private Long idPersonaEqpOrigen;

  /*
  *ID_PERSONA_EQP_DESTINO
  */
   @Column(name = "ID_PERSONA_EQP_DESTINO", nullable = true)
  private Long idPersonaEqpDestino;

  /*
  *FE_INSTANCIA_PASO
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INSTANCIA_PASO", nullable = true)
  private Date feInstanciaPaso;

  /*
  *Mensaje de la instancia de paso del proceso
  */
   @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
  *FL_INICIA_PROCESO
  */
   @Column(name = "FL_INICIA_PROCESO", nullable = true)
  private String flIniciaProceso;

  /*
  *FL_REQUIERE_DESTINATARIO
  */
   @Column(name = "FL_REQUIERE_DESTINATARIO", nullable = true)
  private String flRequiereDestinatario;

  /*
  *FL_REQUIERE_APROBACION
  */
   @Column(name = "FL_REQUIERE_APROBACION", nullable = true)
  private String flRequiereAprobacion;

  /*
  *ID_TIPO_RELACION
  */
   @Column(name = "ID_TIPO_RELACION", nullable = true)
  private Long idTipoRelacion;

  /*
  *DES_NO_TIPO_RELACION
  */
   @Column(name = "DES_NO_TIPO_RELACION", nullable = true)
  private String desNoTipoRelacion;

  /*
  *ID_PASO_PROCESO_ORIGEN
  */
   @Column(name = "ID_PASO_PROCESO_ORIGEN", nullable = true)
  private Long idPasoProcesoOrigen;

  /*
  *NO_PASO_PROCESO_ORIGEN
  */
   @Column(name = "NO_PASO_PROCESO_ORIGEN", nullable = true)
  private String noPasoProcesoOrigen;

  /*
  *DE_PASO_PROCESO_ORIGEN
  */
   @Column(name = "DE_PASO_PROCESO_ORIGEN", nullable = true)
  private String dePasoProcesoOrigen;

  /*
  *ID_PASO_PROCESO_DESTINO
  */
   @Column(name = "ID_PASO_PROCESO_DESTINO", nullable = true)
  private Long idPasoProcesoDestino;

  /*
  *NO_PASO_PROCESO_DESTINO
  */
   @Column(name = "NO_PASO_PROCESO_DESTINO", nullable = true)
  private String noPasoProcesoDestino;

  /*
  *DE_PASO_PROCESO_DESTINO
  */
   @Column(name = "DE_PASO_PROCESO_DESTINO", nullable = true)
  private String dePasoProcesoDestino;

  /*
  *Flag que indica si el paso requiere notificación electrónicamente a través del SNE, de algunos de las subcategorías de documentos. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_NOTIFICABLE_E", nullable = true)
  private String flNotificableE;

  /*
  *ID_FASE_PROCESO_ORIGEN
  */
   @Column(name = "ID_FASE_PROCESO_ORIGEN", nullable = true)
  private Long idFaseProcesoOrigen;

  /*
  *NO_FASE_PROCESO_ORIGEN
  */
   @Column(name = "NO_FASE_PROCESO_ORIGEN", nullable = true)
  private String noFaseProcesoOrigen;

  /*
  *DE_FASE_PROCESO_ORIGEN
  */
   @Column(name = "DE_FASE_PROCESO_ORIGEN", nullable = true)
  private String deFaseProcesoOrigen;

  /*
  *ID_FASE_PROCESO_DESTINO
  */
   @Column(name = "ID_FASE_PROCESO_DESTINO", nullable = true)
  private Long idFaseProcesoDestino;

  /*
  *NO_FASE_PROCESO_DESTINO
  */
   @Column(name = "NO_FASE_PROCESO_DESTINO", nullable = true)
  private String noFaseProcesoDestino;

  /*
  *DE_FASE_PROCESO_DESTINO
  */
   @Column(name = "DE_FASE_PROCESO_DESTINO", nullable = true)
  private String deFaseProcesoDestino;

  /*
  *ID_PERSONA_ORIGEN
  */
   @Column(name = "ID_PERSONA_ORIGEN", nullable = true)
  private Long idPersonaOrigen;

  /*
  *NO_USUARIO_ORIGEN
  */
   @Column(name = "NO_USUARIO_ORIGEN", nullable = true)
  private String noUsuarioOrigen;

  /*
  *CO_USUARIO_SIGED_ORIGEN
  */
   @Column(name = "CO_USUARIO_SIGED_ORIGEN", nullable = true)
  private Long coUsuarioSigedOrigen;

  /*
  *NO_PERSONA_ORIGEN
  */
   @Column(name = "NO_PERSONA_ORIGEN", nullable = true)
  private String noPersonaOrigen;

  /*
  *AP_PATERNO_ORIGEN
  */
   @Column(name = "AP_PATERNO_ORIGEN", nullable = true)
  private String apPaternoOrigen;

  /*
  *AP_MATERNO_ORIGEN
  */
   @Column(name = "AP_MATERNO_ORIGEN", nullable = true)
  private String apMaternoOrigen;

  /*
  *ID_PERSONA_DESTINO
  */
   @Column(name = "ID_PERSONA_DESTINO", nullable = true)
  private Long idPersonaDestino;

  /*
  *NO_USUARIO_DESTINO
  */
   @Column(name = "NO_USUARIO_DESTINO", nullable = true)
  private String noUsuarioDestino;

  /*
  *CO_USUARIO_SIGED_DESTINO
  */
   @Column(name = "CO_USUARIO_SIGED_DESTINO", nullable = true)
  private Long coUsuarioSigedDestino;

  /*
  *NO_PERSONA_DESTINO
  */
   @Column(name = "NO_PERSONA_DESTINO", nullable = true)
  private String noPersonaDestino;

  /*
  *AP_PATERNO_DESTINO
  */
   @Column(name = "AP_PATERNO_DESTINO", nullable = true)
  private String apPaternoDestino;

  /*
  *AP_MATERNO_DESTINO
  */
   @Column(name = "AP_MATERNO_DESTINO", nullable = true)
  private String apMaternoDestino;

  /*
  *ID_ROL_PROCESO_ORIGEN
  */
   @Column(name = "ID_ROL_PROCESO_ORIGEN", nullable = true)
  private Long idRolProcesoOrigen;

  /*
  *NO_ROL_PROCESO_ORIGEN
  */
   @Column(name = "NO_ROL_PROCESO_ORIGEN", nullable = true)
  private String noRolProcesoOrigen;

  /*
  *DE_ROL_PROCESO_ORIGEN
  */
   @Column(name = "DE_ROL_PROCESO_ORIGEN", nullable = true)
  private String deRolProcesoOrigen;

  /*
  *ID_ROL_PROCESO_DESTINO
  */
   @Column(name = "ID_ROL_PROCESO_DESTINO", nullable = true)
  private Long idRolProcesoDestino;

  /*
  *NO_ROL_PROCESO_DESTINO
  */
   @Column(name = "NO_ROL_PROCESO_DESTINO", nullable = true)
  private String noRolProcesoDestino;

  /*
  *DE_ROL_PROCESO_DESTINO
  */
   @Column(name = "DE_ROL_PROCESO_DESTINO", nullable = true)
  private String deRolProcesoDestino;

  /*
  *DE_ENTIDAD_PERSONA_ORIGEN
  */
   @Column(name = "DE_ENTIDAD_PERSONA_ORIGEN", nullable = true)
  private String deEntidadPersonaOrigen;

  /*
  *DE_ENTIDAD_PERSONA_DESTINO
  */
   @Column(name = "DE_ENTIDAD_PERSONA_DESTINO", nullable = true)
  private String deEntidadPersonaDestino;

  /*
  *FL_ES_PASO_ACTIVO
  */
   @Column(name = "FL_ES_PASO_ACTIVO", nullable = true)
  private String flEsPasoActivo;  

  /*
  *Flag que indica si la persona destino está interesada en la instancia de proceso. Posibles valores: "1" = Sí y 0 = "No"
  */
 @Transient
  private String flPersDestinoInteresada;

  /*
  *Nombre completo de la persona origen
  */
 @Transient
  private String descNoCompletoPersonaOrigen;

  /*
  *Nombre completo de la persona destino
  */
 @Transient
  private String descNoCompletoPersonaDestino;

  /*
  *Tipo de acción SIGED. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_ACCION_SIGED. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACCION_SIGED", nullable = true)
  private PgimValorParametro tipoAccionSiged;

  /*
  *Nombre del tipo de acción Siged
  */
   @Column(name = "DE_TIPO_ACCION_SIGED", nullable = true)
  private String deTipoAccionSiged;
  
  /*
  *Nombre del proceso
  */
   @Column(name = "NO_PROCESO", nullable = true)
   private String noProceso;

  /*
  *Etiqueta del objeto del flujo de trabajo
  */
   @Column(name = "NO_ETIQUETA_OTRABAJO", nullable = true)
   private String noEtiquetaOtrabajo;
   
  /*
  *ID_TIPO_SUBFLUJO
  */
   @Column(name = "ID_TIPO_SUBFLUJO", nullable = true)
  private Long idTipoSubflujo;

}