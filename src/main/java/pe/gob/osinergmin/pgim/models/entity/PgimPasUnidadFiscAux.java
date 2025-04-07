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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_PAS_UNIDAD_FISC_AUX: 
* @descripción: Vista para todos los datos PAS asociadas a la unidad fiscalizable
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 07/07/2023
*/
@Entity
@Table(name = "PGIM_VW_PAS_UNIDAD_FISC_AUX")
@Data
@NoArgsConstructor
public class PgimPasUnidadFiscAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del PAS auxiliar. Secuencia: PGIM_SEQ_PAS_UNIDAD_FISC_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PAS_UNIDAD_FISC_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_PAS_UNIDAD_FISC_AUX", sequenceName = "PGIM_SEQ_PAS_UNIDAD_FISC_AUX", allocationSize = 1)
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
  *Código del PAS
  */
   @Column(name = "CO_PAS", nullable = true)
  private String coPas;

  /*
  *Fecha de la constancia electronica Oipas
  */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FE_ORIGEN_DOCUMENTO", nullable = true)
  private Date feOrigenDocumento;

  /*
  *Cantidad de incumplimientos detectados de la fiscalización
  */
   @Column(name = "INCUMPLIMIENTOS", nullable = true)
  private Long incumplimientos;

  /*
  *Cantidad de infracciones detectados de la fiscalización
  */
   @Column(name = "INFRACCIONES", nullable = true)
  private Long infracciones;

  /*
  *Id del programa de la fiscalización
  */
   @Column(name = "ID_PROGRAMA_SUPERVISION", nullable = true)
  private Long idProgramaSupervision;

  /*
  *Programa de la fiscalización
  */
   @Column(name = "DESC_PROGRAMA", nullable = true)
  private String descPrograma;

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
  *Id de la división supervisora
  */
   @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private Long idDivisionSupervisora;

  /*
  *NO_DIVISION_SUPERVISORA
  */
   @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;

  /*
  *Número de expediente Siged del PAS
  */
   @Column(name = "NU_EXPEDIENTE_SIGED_PAS", nullable = true)
  private String nuExpedienteSigedPas;

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
  *Identificador de la instancia PAS del proceso
  */
   @Column(name = "ID_INSTANCIA_PROCESO_PAS", nullable = true)
  private Long idInstanciaProcesoPas;

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
  *FL_PASO_ACTIVO
  */
   @Column(name = "FL_PASO_ACTIVO", nullable = true)
  private String flPasoActivo;  
  
  /*
  *Id del motivo de la fiscalización
  */
   @Column(name = "ID_MOTIVO_SUPERVISION", nullable = true)
  private Long idMotivoSupervision;

  /*
  *descripción del motivo de la fiscalización
  */
   @Column(name = "DE_MOTIVO_SUPERVISION", nullable = true)
  private String deMotivoSupervision;

}