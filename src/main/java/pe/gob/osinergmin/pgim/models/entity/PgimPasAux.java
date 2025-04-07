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
* Clase Entidad para la tabla PGIM_VW_PAS_AUX: 
* @descripción: Vista de Procesos administrativos de sanción (PAS)
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_PAS_AUX")
@Data
@NoArgsConstructor
public class PgimPasAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del PAS auxiliar. Secuencia: PGIM_SEQ_PAS_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PAS_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_PAS_AUX", sequenceName = "PGIM_SEQ_PAS_AUX", allocationSize = 1)
   @Column(name = "ID_PAS_AUX", nullable = false)
  private Long idPasAux;

  /*
  *ID_PAS
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PAS", nullable = true)
  private PgimPas pgimPas;

  /*
  /*
  *Identificador de la instancia del paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @Column(name = "ID_INSTANCIA_PASO", nullable = true)
  private Long idInstanciaPaso;

  /*
  *Fecha de creación del PAS
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION_PAS", nullable = true)
  private Date feCreacionPas;

  /*
  *Anño de creación del PAS
  */
   @Column(name = "FE_CREACION_PAS_ANIO", nullable = true)
  private String feCreacionPasAnio;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @Column(name = "ID_SUPERVISION", nullable = true)
  private Long idSupervision;

  /*
  *Código de la supervisión
  */
   @Column(name = "CO_SUPERVISION", nullable = true)
  private String coSupervision;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

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
  *Identificador interno del agente supervisado relacionado
  */
   @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private Long idAgenteSupervisado;

  /*
  *Código de identididad del agente supervisado
  */
   @Column(name = "AS_CO_DOCUMENTO_IDENTIDAD", nullable = true)
  private String asCoDocumentoIdentidad;

  /*
  *Razón social del agente supervisado
  */
   @Column(name = "AS_NO_RAZON_SOCIAL", nullable = true)
  private String asNoRazonSocial;

  /*
  *Número de expediente Siged del PAS
  */
   @Column(name = "NU_EXPEDIENTE_SIGED_PAS", nullable = true)
  private String nuExpedienteSigedPas;

  /*
  *Número de expediente Siged de la supervisión
  */
   @Column(name = "NU_EXPEDIENTE_SIGED_SUP", nullable = true)
  private String nuExpedienteSigedSup;

  /*
  *FL_PROPIA
  */
   @Column(name = "FL_PROPIA", nullable = true)
  private String flPropia;

  /*
  *Nombre completo de la persona asignada
  */
   @Column(name = "NO_PERSONA_ASIGNADA", nullable = true)
  private String noPersonaAsignada;

  /*
  *Nombre windows del usuario asignado
  */
   @Column(name = "NO_USUARIO_ASIGNADO", nullable = true)
  private String noUsuarioAsignado;

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
  *Nombre del paso actual del proceso
  */
   @Column(name = "NO_PASO_ACTUAL", nullable = true)
  private String noPasoActual;

  /*
  *Identificador interno de la relación de paso
  */
   @Column(name = "ID_RELACION_PASO", nullable = true)
  private Long idRelacionPaso;

  /*
  *Identificador interno del tipo de relación
  */
   @Column(name = "ID_TIPO_RELACION", nullable = true)
  private Long idTipoRelacion;

  /*
  *NO_DIVISION_SUPERVISORA
  */
   @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;

  /*
  *Identificador de la instancia PAS del proceso
  */
   @Column(name = "ID_INSTANCIA_PROCESO_PAS", nullable = true)
  private Long idInstanciaProcesoPas;

  /*
  *DIAS_TRANSCURRIDOS
  */
   @Column(name = "DIAS_TRANSCURRIDOS", nullable = true)
  private Long diasTranscurridos;
   
  /*
  *FL_LEIDO
  */
   @Column(name = "FL_LEIDO", nullable = true)
  private String flLeido;

  /*
  *FE_LECTURA
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
  *Nombre de la persona responsable
  */
 @Transient
  private String descNoResponsable;

  /*
  *Flag de mis asignaciones
  */
 @Transient
  private String descFlagMisAsignaciones;

  /*
  *Usuario (Windows) de la persona responsable
  */
 @Transient
  private String descUsuarioAsignado;

  /*
  *Nombre completo de la persona destino
  */
 @Transient
  private String descPersonaDestino;

  /*
  *Flag de mi interés
  */
 @Transient
  private String descFlagMiInteres;


}