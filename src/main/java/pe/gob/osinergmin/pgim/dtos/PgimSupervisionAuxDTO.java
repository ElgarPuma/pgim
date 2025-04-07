package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VC_SUPERVISION_AUX: 
* @descripción: Vista de la supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSupervisionAuxDTO {

  /*
  *Identificador interno del agente supervisado AUX. Secuencia: PGIM_SEQ_SUPERVISION
  */
  private Long idSupervisionAux;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *idInstanciaPaso
  */
  private Long idInstanciaPaso;  

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
  private Long idContrato;

  /*
  *Identificador interno del consumo del contrato. Tabla padre: PGIM_TD_CONSUMO_CONTRA
  */
  private Long idConsumoContra;

  /*
  *Número de contrato
  */
  private String nuContrato;

  /*
  *Descripción de contrato
  */
  private String deContrato;

  /*
  *DESC_PROGRAMA
  */
  private String descPrograma;

  /*
  *DESC_CONTRATO
  */
  private String descContrato;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
  private String noUnidadMinera;

  /*
  *Código de la supervisión
  */
  private String coSupervision;

  /*
  *FE_INICIO_SUPERVISION
  */
  private Date feInicioSupervision;

  /*
  *FE_INICIO_SUPERVISION
  */
  private String feInicioSupervisionDesc;

  /*
  *FE_FIN_SUPERVISION
  */
  private Date feFinSupervision;

  /*
  *FE_FIN_SUPERVISION
  */
  private String feFinSupervisionDesc;

  /*
  *ID_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *AS_NO_RAZON_SOCIAL
  */
  private String asNoRazonSocial;

  /*
  *AS_CO_DOCUMENTO_IDENTIDAD
  */
  private String asCoDocumentoIdentidad;

  /*
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String noEspecialidad;

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
  *Monto en S/ del consumo del contrato
  */
  private BigDecimal moConsumoContrato;

  /*
  *Número de expediente Siged
  */
  private String nuExpedienteSiged;

  /*
  *Nombre completo de la persona asignada a la instancia de flujo de una supervisión
  */
  private String personaAsignada;

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
  *NO_PASO_ACTUAL
  */
  private String noPasoActual;

  /*
  *ID_INSTANCIA_PROCESO
  */
  private Long idInstanciaProceso;

  /*
  *ID_RELACION_PASO
  */
  private Long idRelacionPaso;

  /*
  *ID_TIPO_RELACION
  */
  private Long idTipoRelacion;

  /*
  *DES_NO_TIPO_RELACION
  */
  private String desNoTipoRelacion;

  /*
  *Usuario (Windows) de la persona responsable
  */
  private String usuarioAsignado;

  /*
  *Fecha de creación de la fiscalización
  */
  private Date feCreacion;

  /*
  *Fecha de creación de la fiscalización
  */
  private String feCreacionDesc;

  /*
  *Identificador de la división supervisora
  */
  private Long idDivisionSupervisora;

  /*
  *Nombre de la división supervisora
  */
  private String noDivisionSupervisora;

  /*
  *Valor lógico que señala si la fiscalización es propia o no
  */
  private String flPropia;

  /*
  *Etiqueta descriptiva sobre si la fiscalización es propia o no
  */
  private String noFlPropia;

  /*
  *FL_FE_ENVIO
  */
  private String flFeEnvio;

  /*
  *Condición lógica que señala si la instancia de paso ha sido leída o no por su destintario
  */
  private String flLeido;

  /*
  *Fecha en la que se abrió el formulario de la instancia de proceso relacionada con la instancia de paso
  */
  private Date feLectura;

  private String noPersonaOrigen;

  private String noUsuarioOrigen;

  private Date feInstanciaPaso;

  private String deMensaje;

  private String flPasoActivo;
  
  /**
   * número de alertas de cumplimiento de plazo que tiene la fiscalización 
   */
  private Long nuAlertas;
  
  /**
   * numero de intervalo en la cual se encuentra la alerta de cumplimiento de plazo
   */
  private Long nuIntervaloAlertas;

  /*
  *FL_LISTO_CONTINUAR
  */
  private String flListoContinuar;

  /*
  *Fecha en la que se abrió el formulario de la instancia de proceso relacionada con la instancia de paso
  */
  private String feLecturaDesc;

  /*
  *Usuario (Windows) de la persona responsable
  */
  private String descResponsable;

  /*
  *Cantidad de días
  */
  private Long cantidadDias;

  /*
  *Fecha de inicio real de la fiscalización
  */
  private Date descFeInicioReal;

  /*
  *Fecha de inicio real de la fiscalización
  */
  private String descFeInicioRealDesc;

  /*
  *Fecha de finalización real de la fiscalización
  */
  private Date descFeFinReal;

  /*
  *Fecha de finalización real de la fiscalización
  */
  private String descFeFinRealDesc;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
   * Fecha de inicio de fiscalizaciómn real
   */
  private Date feInicioSupervisionReal;

  /*
   * Fecha fin de fiscalizaciómn real
   */
  private Date feFinSupervisionReal;

  /*
   * Me permite setear cuando hay traslape de fiscalizaciones
   */
  private String flTraslape;
}