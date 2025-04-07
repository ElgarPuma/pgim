package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TC_SUPERVISION: 
* @descripción: Instancia de la supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSupervisionDTO {

  /*
  *Identificador interno de la supervisión. Secuencia: PGIM_SEQ_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
  private Long idProgramaSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
  private Long idMotivoSupervision;
  
  /*
  *Identificador interno del agente supervisado asociado finalmente a la fiscalización. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *Razón social del agente fiscalizado al momento de la finalización del flujo de trabajo de la fiscalización
  */
  private String noRazonSocialAfiscalizado;
   
  /*
  *Código de supervisión. Formato ejemplo: SP-2020-00001
  */
  private String coSupervision;

  /*
  *Fecha programada de inicio de la supervisión
  */
  private Date feInicioSupervision;

  /*
  *Fecha programada de inicio de la supervisión
  */
  private String feInicioSupervisionDesc;

  /*
  *Fecha programada fin de la supervisión
  */
  private Date feFinSupervision;

  /*
  *Fecha programada fin de la supervisión
  */
  private String feFinSupervisionDesc;

  /*
  *Fecha real de inicio de la supervisión
  */
  private Date feInicioSupervisionReal;

  /*
  *Fecha real de inicio de la supervisión
  */
  private String feInicioSupervisionRealDesc;

  /*
  *Fecha real fin de la supervisión
  */
  private Date feFinSupervisionReal;

  /*
  *Fecha real fin de la supervisión
  */
  private String feFinSupervisionRealDesc;

  /*
  *Flag que indica si la fiscalización es propia, es decir, se la realizó el personal de Osinergmin. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flPropia;

  /*
  *Objetivo del TDR
  */
  private String deTdrObjetivoTexto;

  /*
  *Alcance del servicio requerido en el TDR
  */
  private String deTdrAlcanceTexto;

  /*
  *Metodología del servicio definido para el TDR
  */
  private String deTdrMetodologiaTexto;

  /*
  *Informe de supervisión definido para el TDR
  */
  private String deTdrInformeSupervTexto;

  /*
  *Honorarios profesionales definidos para el TDR
  */
  private String deTdrHonorariosProf;

  /*
  *Observación sobre el inicio de la supervisión. Esta se incluye en el acta de inicio de la supervisión
  */
  private String deObservacionInicioSuperT;

  /*
  *Observación sobre el fin de la supervisión. Esta se incluye en el acta de fin de la supervisión
  */
  private String deObservacionFinSuperT;

  /*
  *Indicador lógico que señala que se han registrado datos de riesgo en el contexto de la fiscalización. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flRegistraRiesgos;

  /*
  *Indicador lógico que señala que la fiscalización utilizará - para la contabilización de plazos - los valores de envío para la presentación de los informes, sus observaciones y sus subsanaciones. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flFeEnvio;

  /*
  *Flag que indica que la fiscalización no pudo iniciarse debido a causas atribuibles al agente fiscalizado. Posibles valores: "1" = La fiscalización no pudo iniciase por agente fiscalizado, "0" o NULL = "La fiscalización no se vio afectada por agente fiscalizado"
  */
  private String flNoIniciadaAfiscalizado;
  
  /*
  * Flag que indica si el registro de la fiscalización es pre-existente a la implementación del retorno a la fase 1 desde la fase 2. Posibles valores: "1" = Sí y (0 o NULL) = "No". Cuando el valor es "1" solo aplica para aquellas fiscalizaciones que ya hayan transitado desde la tarea «Preparar inicio fiscalización de campo y validar cuadro de verificación» hacia la tarea «Iniciar fiscalización de campo»
  */
  private String flPreexistenteF2F1;

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

  private PgimCmineroSprvsionDTO[] descListPgimCmineroSprvsionDTO;

  /*
  *Código de la unidad minera
  */
  private String descCoUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Identificador interno del agente supervisado
  */
  private Long descIdAgenteSupervisado;

  /*
  *Nombre de Razón social de agente supervisado
  */
  private String descNoRazonSocial;

  /*
  *Código de documento de identidad de agente supervisado
  */
  private String descCoDocumentoIdentidad;

  /*
  *Identificador interno de la especialidad
  */
  private Long descIdEspecialidad;

  /*
  *Nombre de Especialidad
  */
  private String descNoEspecialidad;

  /*
  *Identificador interno del contrato
  */
  private Long descIdContrato;

  /*
  *Número de contrato
  */
  private BigDecimal descNuContrato;

  /*
  *Descripción de contrato
  */
  private String descDeContrato;

  /*
  *Descripción del programa que incluye el año, estrato y especialidad
  */
  private String descPrograma;

  /*
  *Descripción de contrato que incluye el contrato y la empresa supervisora
  */
  private String descContrato;

  /*
  *Nombre de la empresa supervisora
  */
  private String descNoRazonSocialEmpSupervisora;

  /*
  *Nombre del subtipo de supervisión
  */
  private String descSubtipoSupervision;

  /*
  *Número del expediente Siged asociado a la supervisión
  */
  private String descNuExpedienteSiged;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long descIdRelacionPaso;

  /*
  *Identificador interno del personal de Osinergmin. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
  private Long descIdPersonalOsi;

  /*
  *Mensaje de la instancia de paso del proceso
  */
  private String descDeMensaje;

  /*
  *Identificador de la fase de proceso
  */
  private Long descIdFaseProceso;

  /*
  *Identificador de paso de proceso
  */
  private Long descIdPasoProceso;

  /*
  *nombre de paso del proceso
  */
  private String descNoPasoProcesoDestino;

  /*
  *Nombre completo de la persona destino
  */
  private String descPersonaDestino;

  /*
  *Nombre de la persona responsable
  */
  private String descNoResponsable;

  /*
  *Flag de mi interés
  */
  private String descFlagMiInteres;

  /*
  *Flag de mis asignaciones
  */
  private String descFlagMisAsignaciones;

  /*
  *Muestra la cantidad de personas del equipo que tienen el rol de supervisor
  */
  private Integer descNoProfesionales;

  /*
  *Nombre del tipo de supervisión
  */
  private String descTipoSupervision;

  /*
  *Razon social del agente supervisado
  */
  private String descAgenteSupervisadoNoRazonSocial;

  /*
  *Nombre de unidad minera
  */
  private String descUnidadMineraNoUnidadMinera;

  /*
  *Número de contrato
  */
  private String descDeNuContrato;

  /*
  *Razon social de empresa supervisora
  */
  private String descEmpresaSupervisoraNoRazonSocial;

  /*
  *Identificador de empresa supervisora
  */
  private Long descIdPersonaEmpSuperv;

  /*
  *Razon social de empresa supervisora
  */
  private Long descLnProfesionales;

  /*
  *Distrito
  */
  private String descNoUbigeoDistrito;

  /*
  *Provincia
  */
  private String descNoUbigeoProvincia;

  /*
  *Departamento
  */
  private String descNoUbigeoDepartamento;

  /*
  *Nombre de unidad minera tipo planta beneficio
  */
  private String descPlntaBeneficioDestinoNoUnidadMinera;

  /*
  *Hora inicio de la supervision texto
  */
  private String descHoraInicioSupervisionDesc;

  /*
  *Hora fin de la supervision texto
  */
  private String descHoraFinSupervisionDesc;

  /*
  *Nombre de la persona
  */
  private String descNoPersona;

  /*
  *Apellido paterno de la persona
  */
  private String descApPaterno;

  /*
  *Apellido materno de la persona
  */
  private String descApMaterno;

  /*
  *Descripción de hecho constatado
  */
  private String descDeHechoConstatadoSuperv;

  /*
  *Id hecho constatado
  */
  private Long descIdHechoConstatado;

  /*
  *Descripción del hecho constatado por el Osinergmin
  */
  private String descDeHechoConstatadoOsi;

  /*
  *Descripción del hecho constatado por el Osinergmin
  */
  private String descDeComplementoObsrvcionSuper;

  /*
  *Descripción del cargo
  */
  private String descDeCargo;

  /*
  *Descripción de la entidad
  */
  private String descEntidad;

  /*
  *Descripción del sustento
  */
  private String descDeSustentoSuper;

  /*
  *Descripción del valor parámetro
  */
  private String descNoValorParametro;

  /*
  *Hora de inicio real de la supervisión
  */
  private String horaInicioSupervisionRealDesc;

  /*
  *Hora fin real de la supervisión
  */
  private String horaFinSupervisionRealDesc;

  /*
  *Usuario (Windows) de la persona responsable
  */
  private String descUsuarioAsignado;

  /*
  *Descripción del motivo de la supervisión
  */
  private String descDeMotivoSupervision;

  /*
  *Tipo de supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUPERVISION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long descIdTipoSupervision;

  /*
  *Monto de consumo del contrato
  */
  private BigDecimal descMoConsumoContrato;

  /*
  *Ítem del programa de supervisión actualmente asociado a la supervisión, esto permite la asignación de esta última
  */
  private Long descIdItemProgramaSupe;

  /*
  *Identificador interno de la unidad minera
  */
  private Long descIdUnidadMinera;

  /*
   * Tipo de unidad minera
   */
  private String descNoTipoUnidadMinera;

  /*
  *Identificador interno del documento
  */
  private Long descIdDocumento;

  /*
  *Descripción de la fase del proceso
  */
  private String descNoFaseProceso;

  /*
  *Descripción de la fase del proceso
  */
  private Long descIdMatrizSupervision;

  /*
  *Identificador interno de la división supervisora del programa
  */
  private Long descIdDivisionSupervisora;

  /*
  *DESC_DOC_SUPERVISORA
  */
  private String descDocSupervisora;

  /*
  *DESC_ID_PASO_ACTUAL
  */
  private Long descIdPasoActual;

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

  /**
   * número de alertas de cumplimiento de plazo que tiene la fiscalización 
   */
  private Long nuAlertas;
  
  /**
   * numero de intervalo en la cual se encuentra la alerta de cumplimiento de plazo
   */
  private Long nuIntervaloAlertas;

  /**
   * descIdInstanciaPaso
   */
  private Long descIdInstanciaPaso;

  /*
   * descIdSubcategoriaDoc
   */
  private Long descIdSubcategoriaDoc;

  /*
   * Titulo del reporte exportado
   */
  private String deTituloReporte;

  /*
   * Nombre del archivo
   */
  private String descNoArchivo;

    /**
   * Columna a la que vamos a ordenar
   */
  private String sortColumna;

  /**
   * Tipo de ordenamiento ascendente(asc) o descendente(desc)
   */
  private String sortDirection;
}