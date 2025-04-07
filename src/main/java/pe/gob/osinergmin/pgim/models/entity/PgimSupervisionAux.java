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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VC_SUPERVISION_AUX: 
* @descripción: Vista de la supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VC_SUPERVISION_AUX")
@Data
@NoArgsConstructor
public class PgimSupervisionAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del agente supervisado AUX. Secuencia: PGIM_SEQ_SUPERVISION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPERVISION")
   @SequenceGenerator(name = "PGIM_SEQ_SUPERVISION", sequenceName = "PGIM_SEQ_SUPERVISION", allocationSize = 1)
   @Column(name = "ID_SUPERVISION_AUX", nullable = false)
  private Long idSupervisionAux;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = true)
  private PgimContrato pgimContrato;

  /*
  *Identificador interno del consumo del contrato. Tabla padre: PGIM_TD_CONSUMO_CONTRA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONSUMO_CONTRA", nullable = true)
  private PgimConsumoContra pgimConsumoContra;

  /*
  *idInstanciaPaso
  */
   @Column(name = "ID_INSTANCIA_PASO", nullable = true)
  private Long idInstanciaPaso;

  /*
  *Número de contrato
  */
   @Column(name = "NU_CONTRATO", nullable = true)
  private String nuContrato;

  /*
  *Descripción de contrato
  */
   @Column(name = "DE_CONTRATO", nullable = true)
  private String deContrato;

  /*
  *DESC_PROGRAMA
  */
   @Column(name = "DESC_PROGRAMA", nullable = true)
  private String descPrograma;

  /*
  *DESC_CONTRATO
  */
   @Column(name = "DESC_CONTRATO", nullable = true)
  private String descContrato;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *Código de la supervisión
  */
   @Column(name = "CO_SUPERVISION", nullable = true)
  private String coSupervision;

  /*
  *FE_INICIO_SUPERVISION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION", nullable = true)
  private Date feInicioSupervision;

  /*
  *FE_FIN_SUPERVISION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION", nullable = true)
  private Date feFinSupervision;

  /*
  *ID_AGENTE_SUPERVISADO
  */
   @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private Long idAgenteSupervisado;

  /*
  *AS_NO_RAZON_SOCIAL
  */
   @Column(name = "AS_NO_RAZON_SOCIAL", nullable = true)
  private String asNoRazonSocial;

  /*
  *AS_CO_DOCUMENTO_IDENTIDAD
  */
   @Column(name = "AS_CO_DOCUMENTO_IDENTIDAD", nullable = true)
  private String asCoDocumentoIdentidad;

  /*
  *ID_ESPECIALIDAD
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *ID_TIPO_SUPERVISION
  */
   @Column(name = "ID_TIPO_SUPERVISION", nullable = true)
  private Long idTipoSupervision;

  /*
  *NO_TIPO_SUPERVISION
  */
   @Column(name = "NO_TIPO_SUPERVISION", nullable = true)
  private String noTipoSupervision;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
  private PgimSubtipoSupervision pgimSubtipoSupervision;

  /*
  *Descripción del subtipo de supervisión
  */
   @Column(name = "DE_SUBTIPO_SUPERVISION", nullable = true)
  private String deSubtipoSupervision;

  /*
  *Monto en S/ del consumo del contrato
  */
   @Column(name = "MO_CONSUMO_CONTRATO", nullable = true)
  private BigDecimal moConsumoContrato;

  /*
  *Número de expediente Siged
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *Nombre completo de la persona asignada a la instancia de flujo de una supervisión
  */
   @Column(name = "PERSONA_ASIGNADA", nullable = true)
  private String personaAsignada;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
   @Column(name = "ID_FASE_ACTUAL", nullable = true)
  private Long idFaseActual;

  /*
  *Nombre de la fase actual del proceso
  */
   @Column(name = "NO_FASE_ACTUAL", nullable = true)
  private String noFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
   @Column(name = "ID_PASO_ACTUAL", nullable = true)
  private Long idPasoActual;

  /*
  *NO_PASO_ACTUAL
  */
   @Column(name = "NO_PASO_ACTUAL", nullable = true)
  private String noPasoActual;

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
  *Usuario (Windows) de la persona responsable
  */
   @Column(name = "USUARIO_ASIGNADO", nullable = true)
  private String usuarioAsignado;

  /*
  *Fecha de creación de la fiscalización
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = true)
  private Date feCreacion;

  /*
  *Identificador de la división supervisora
  */
   @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private Long idDivisionSupervisora;

  /*
  *Nombre de la división supervisora
  */
   @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;

  /*
  *Valor lógico que señala si la fiscalización es propia o no
  */
   @Column(name = "FL_PROPIA", nullable = true)
  private String flPropia;

  /*
  *Etiqueta descriptiva sobre si la fiscalización es propia o no
  */
   @Column(name = "NO_FL_PROPIA", nullable = true)
  private String noFlPropia;

  /*
  *FL_FE_ENVIO
  */
   @Column(name = "FL_FE_ENVIO", nullable = true)
  private String flFeEnvio;

  /*
  *Condición lógica que señala si la instancia de paso ha sido leída o no por su destintario
  */
   @Column(name = "FL_LEIDO", nullable = true)
  private String flLeido;

  /*
  *Fecha en la que se abrió el formulario de la instancia de proceso relacionada con la instancia de paso
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_LECTURA", nullable = true)
  private Date feLectura;

  /*
  *NO_PERSONA_ORIGEN
  */
  @Column(name = "NO_PERSONA_ORIGEN", nullable = true)
  private String noPersonaOrigen;

  /*
  *NO_USUARIO_ORIGEN
  */
   @Column(name = "NO_USUARIO_ORIGEN", nullable = true)
  private String noUsuarioOrigen;
  
  /*
  *FE_INSTANCIA_PASO
  */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FE_INSTANCIA_PASO", nullable = true)
  private Date feInstanciaPaso;
  
  /*
  *DE_MENSAJE
  */
   @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
  *FL_PASO_ACTIVO
  */
   @Column(name = "FL_PASO_ACTIVO", nullable = true)
  private String flPasoActivo;

  /*
  *NU_ALERTAS
  */
   @Column(name = "NU_ALERTAS", nullable = true)
  private Long nuAlertas;
  
  /*
  *NU_INTERVALO_ALERTAS
  */
   @Column(name = "NU_INTERVALO_ALERTAS", nullable = true)
  private Long nuIntervaloAlertas;
  
  /*
  *FL_LISTO_CONTINUAR
  */
  @Column(name = "FL_LISTO_CONTINUAR", nullable = true)
  private String flListoContinuar;
  
  /*
  *Usuario (Windows) de la persona responsable
  */
 @Transient
  private String descResponsable;

  /*
  *Cantidad de días
  */
 @Transient
  private Long cantidadDias;

  /*
  *Fecha de inicio real de la fiscalización
  */
 @Transient
  private Date descFeInicioReal;

  /*
  *Fecha de finalización real de la fiscalización
  */
 @Transient
  private Date descFeFinReal;


}