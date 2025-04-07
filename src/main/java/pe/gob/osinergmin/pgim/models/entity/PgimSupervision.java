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

import java.math.BigDecimal;

import javax.persistence.Lob;
import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TC_SUPERVISION: 
* @descripción: Instancia de la supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_SUPERVISION")
@Data
@NoArgsConstructor
public class PgimSupervision implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la supervisión. Secuencia: PGIM_SEQ_SUPERVISION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPERVISION")
   @SequenceGenerator(name = "PGIM_SEQ_SUPERVISION", sequenceName = "PGIM_SEQ_SUPERVISION", allocationSize = 1)
   @Column(name = "ID_SUPERVISION", nullable = false)
  private Long idSupervision;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBTIPO_SUPERVISION", nullable = false)
  private PgimSubtipoSupervision pgimSubtipoSupervision;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PROGRAMA_SUPERVISION", nullable = true)
  private PgimPrgrmSupervision pgimPrgrmSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MOTIVO_SUPERVISION", nullable = true)
  private PgimMotivoSupervision pgimMotivoSupervision;
   
  /*
  *Identificador interno del agente supervisado asociado finalmente a la fiscalización. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private PgimAgenteSupervisado pgimAgenteSupervisado;
   
  /*
  *Razón social del agente fiscalizado al momento de la finalización del flujo de trabajo de la fiscalización
  */
   @Column(name = "NO_RAZON_SOCIAL_AFISCALIZADO", nullable = true)
  private String noRazonSocialAfiscalizado;

  /*
  *Código de supervisión. Formato ejemplo: SP-2020-00001
  */
   @Column(name = "CO_SUPERVISION", nullable = false)
  private String coSupervision;

  /*
  *Fecha programada de inicio de la supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION", nullable = false)
  private Date feInicioSupervision;

  /*
  *Fecha programada fin de la supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION", nullable = false)
  private Date feFinSupervision;

  /*
  *Fecha real de inicio de la supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION_REAL", nullable = true)
  private Date feInicioSupervisionReal;

  /*
  *Fecha real fin de la supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION_REAL", nullable = true)
  private Date feFinSupervisionReal;

  /*
  *Flag que indica si la fiscalización es propia, es decir, se la realizó el personal de Osinergmin. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_PROPIA", nullable = true)
  private String flPropia;

  /*
  *Objetivo del TDR
  */
   @Column(name = "DE_TDR_OBJETIVO_TEXTO", nullable = true)
@Lob
  private String deTdrObjetivoTexto;

  /*
  *Alcance del servicio requerido en el TDR
  */
   @Column(name = "DE_TDR_ALCANCE_TEXTO", nullable = true)
@Lob
  private String deTdrAlcanceTexto;

  /*
  *Metodología del servicio definido para el TDR
  */
   @Column(name = "DE_TDR_METODOLOGIA_TEXTO", nullable = true)
@Lob
  private String deTdrMetodologiaTexto;

  /*
  *Informe de supervisión definido para el TDR
  */
   @Column(name = "DE_TDR_INFORME_SUPERV_TEXTO", nullable = true)
@Lob
  private String deTdrInformeSupervTexto;

  /*
  *Honorarios profesionales definidos para el TDR
  */
   @Column(name = "DE_TDR_HONORARIOS_PROF", nullable = true)
  private String deTdrHonorariosProf;

  /*
  *Observación sobre el inicio de la supervisión. Esta se incluye en el acta de inicio de la supervisión
  */
   @Column(name = "DE_OBSERVACION_INICIO_SUPER_T", nullable = true)
@Lob
  private String deObservacionInicioSuperT;

  /*
  *Observación sobre el fin de la supervisión. Esta se incluye en el acta de fin de la supervisión
  */
   @Column(name = "DE_OBSERVACION_FIN_SUPER_T", nullable = true)
@Lob
  private String deObservacionFinSuperT;

  /*
  *Indicador lógico que señala que se han registrado datos de riesgo en el contexto de la fiscalización. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_REGISTRA_RIESGOS", nullable = true)
  private String flRegistraRiesgos;

  /*
  *Indicador lógico que señala que la fiscalización utilizará - para la contabilización de plazos - los valores de envío para la presentación de los informes, sus observaciones y sus subsanaciones. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_FE_ENVIO", nullable = false)
  private String flFeEnvio;

  /*
  *Flag que indica que la fiscalización no pudo iniciarse debido a causas atribuibles al agente fiscalizado. Posibles valores: "1" = La fiscalización no pudo iniciase por agente fiscalizado, "0" o NULL = "La fiscalización no se vio afectada por agente fiscalizado"
  */
   @Column(name = "FL_NO_INICIADA_AFISCALIZADO", nullable = false)
  private String flNoIniciadaAfiscalizado;

  /*
  *Flag que indica si el registro de la fiscalización es pre-existente a la implementación de los nuevos tipos de penalidad. Posibles valores: "1" = Sí y (0 o NULL) = "No". Cuando el valor es "1" solo aplica para aquellas fiscalizaciones que hayan transitado por «Revisar y aprobar informe de fiscalización» ==> «Elaborar ficha de evaluación de hechos verificados v1, MCAF y OCAF»
  */
   @Column(name = "FL_PREEXISTENTE_NPENALIDADES", nullable = true)
  private String flPreexistenteNpenalidades;
   
  /*
  *Flag que indica si el registro de la fiscalización es pre-existente a la implementación del retorno a la fase 1 desde la fase 2. Posibles valores: "1" = Sí y (0 o NULL) = "No". Cuando el valor es "1" solo aplica para aquellas fiscalizaciones que ya hayan transitado desde la tarea «Preparar inicio fiscalización de campo y validar cuadro de verificación» hacia la tarea «Iniciar fiscalización de campo»
  */
   @Column(name = "FL_PREEXISTENTE_F2_F1", nullable = true)
  private String flPreexistenteF2F1;

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
  *Código de la unidad minera
  */
 @Transient
  private String descCoUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descNoUnidadMinera;

  /*
  *Identificador interno del agente supervisado
  */
 @Transient
  private Long descIdAgenteSupervisado;

  /*
  *Nombre de Razón social de agente supervisado
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Código de documento de identidad de agente supervisado
  */
 @Transient
  private String descCoDocumentoIdentidad;

  /*
  *Identificador interno de la especialidad
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *Nombre de Especialidad
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *Identificador interno del contrato
  */
 @Transient
  private Long descIdContrato;

  /*
  *Número de contrato
  */
 @Transient
  private BigDecimal descNuContrato;

  /*
  *Descripción de contrato
  */
 @Transient
  private String descDeContrato;

  /*
  *Descripción del programa que incluye el año, estrato y especialidad
  */
 @Transient
  private String descPrograma;

  /*
  *Descripción de contrato que incluye el contrato y la empresa supervisora
  */
 @Transient
  private String descContrato;

  /*
  *Nombre de la empresa supervisora
  */
 @Transient
  private String descNoRazonSocialEmpSupervisora;

  /*
  *Nombre del subtipo de supervisión
  */
 @Transient
  private String descSubtipoSupervision;

  /*
  *Número del expediente Siged asociado a la supervisión
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
 @Transient
  private Long descIdRelacionPaso;

  /*
  *Identificador interno del personal de Osinergmin. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
 @Transient
  private Long descIdPersonalOsi;

  /*
  *Mensaje de la instancia de paso del proceso
  */
 @Transient
  private String descDeMensaje;

  /*
  *Identificador de la fase de proceso
  */
 @Transient
  private Long descIdFaseProceso;

  /*
  *Identificador de paso de proceso
  */
 @Transient
  private Long descIdPasoProceso;

  /*
  *nombre de paso del proceso
  */
 @Transient
  private String descNoPasoProcesoDestino;

  /*
  *Nombre completo de la persona destino
  */
 @Transient
  private String descPersonaDestino;

  /*
  *Nombre de la persona responsable
  */
 @Transient
  private String descNoResponsable;

  /*
  *Flag de mi interés
  */
 @Transient
  private String descFlagMiInteres;

  /*
  *Flag de mis asignaciones
  */
 @Transient
  private String descFlagMisAsignaciones;

  /*
  *Muestra la cantidad de personas del equipo que tienen el rol de supervisor
  */
 @Transient
  private Integer descNoProfesionales;

  /*
  *Nombre del tipo de supervisión
  */
 @Transient
  private String descTipoSupervision;

  /*
  *Razon social del agente supervisado
  */
 @Transient
  private String descAgenteSupervisadoNoRazonSocial;

  /*
  *Nombre de unidad minera
  */
 @Transient
  private String descUnidadMineraNoUnidadMinera;

  /*
  *Número de contrato
  */
 @Transient
  private String descDeNuContrato;

  /*
  *Razon social de empresa supervisora
  */
 @Transient
  private String descEmpresaSupervisoraNoRazonSocial;

  /*
  *Razon social de empresa supervisora
  */
 @Transient
  private Long descLnProfesionales;

  /*
  *Distrito
  */
 @Transient
  private String descNoUbigeoDistrito;

  /*
  *Provincia
  */
 @Transient
  private String descNoUbigeoProvincia;

  /*
  *Departamento
  */
 @Transient
  private String descNoUbigeoDepartamento;

  /*
  *Nombre de unidad minera tipo planta beneficio
  */
 @Transient
  private String descPlntaBeneficioDestinoNoUnidadMinera;

  /*
  *Hora inicio de la supervision texto
  */
 @Transient
  private String descHoraInicioSupervisionDesc;

  /*
  *Hora fin de la supervision texto
  */
 @Transient
  private String descHoraFinSupervisionDesc;

  /*
  *Nombre de la persona
  */
 @Transient
  private String descNoPersona;

  /*
  *Apellido paterno de la persona
  */
 @Transient
  private String descApPaterno;

  /*
  *Apellido materno de la persona
  */
 @Transient
  private String descApMaterno;

  /*
  *Descripción de hecho constatado
  */
 @Transient
  private String descDeHechoConstatadoSuperv;

  /*
  *Id hecho constatado
  */
 @Transient
  private Long descIdHechoConstatado;

  /*
  *Descripción del hecho constatado por el Osinergmin
  */
 @Transient
  private String descDeHechoConstatadoOsi;

  /*
  *Descripción del hecho constatado por el Osinergmin
  */
 @Transient
  private String descDeComplementoObsrvcionSuper;

  /*
  *Descripción del cargo
  */
 @Transient
  private String descDeCargo;

  /*
  *Descripción de la entidad
  */
 @Transient
  private String descEntidad;

  /*
  *Descripción del sustento
  */
 @Transient
  private String descDeSustentoSuper;

  /*
  *Descripción del valor parámetro
  */
 @Transient
  private String descNoValorParametro;

  /*
  *Hora de inicio real de la supervisión
  */
 @Transient
  private String horaInicioSupervisionRealDesc;

  /*
  *Hora fin real de la supervisión
  */
 @Transient
  private String horaFinSupervisionRealDesc;

  /*
  *Usuario (Windows) de la persona responsable
  */
 @Transient
  private String descUsuarioAsignado;

  /*
  *Descripción del motivo de la supervisión
  */
 @Transient
  private String descDeMotivoSupervision;

  /*
  *Tipo de supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUPERVISION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
 @Transient
  private Long descIdTipoSupervision;

  /*
  *Monto de consumo del contrato
  */
 @Transient
  private BigDecimal descMoConsumoContrato;

  /*
  *Ítem del programa de supervisión actualmente asociado a la supervisión, esto permite la asignación de esta última
  */
 @Transient
  private Long descIdItemProgramaSupe;

  /*
  *Identificador interno de la unidad minera
  */
 @Transient
  private Long descIdUnidadMinera;

  /*
  *Identificador interno del documento
  */
 @Transient
  private Long descIdDocumento;

  /*
  *Descripción de la fase del proceso
  */
 @Transient
  private String descNoFaseProceso;

  /*
  *Descripción de la fase del proceso
  */
 @Transient
  private Long descIdMatrizSupervision;

  /*
  *Identificador interno de la división supervisora del programa
  */
 @Transient
  private Long descIdDivisionSupervisora;

  /*
  *DESC_DOC_SUPERVISORA
  */
 @Transient
  private String descDocSupervisora;

  /*
  *DESC_ID_PASO_ACTUAL
  */
 @Transient
  private Long descIdPasoActual;


}