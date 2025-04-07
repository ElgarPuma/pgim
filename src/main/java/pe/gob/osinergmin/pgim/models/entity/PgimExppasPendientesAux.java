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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_EXPPAS_PENDIENTES_AUX: 
* @descripción: Vista expedientes PAS pendientes por persona asignada
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXPPAS_PENDIENTES_AUX")
@Data
@NoArgsConstructor
public class PgimExppasPendientesAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_EXP_SIGED_PAS_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EXP_SIGED_PAS_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_EXP_SIGED_PAS_AUX", sequenceName = "PGIM_SEQ_EXP_SIGED_PAS_AUX", allocationSize = 1)
   @Column(name = "EXP_SIGED_PAS_AUX", nullable = false)
  private String idExpSigedPasAux;

  /*
  *ANIO_SUPERVISION
  */
   @Column(name = "ANIO_SUPERVISION", nullable = true)
  private Long anioSupervision;

  /*
  *CO_SUPERVISION
  */
   @Column(name = "CO_SUPERVISION", nullable = true)
  private String coSupervision;

  /*
  *FE_INICIO_SUPERVISION_REAL
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION_REAL", nullable = true)
  private Date feInicioSupervisionReal;

  /*
  *FE_FIN_SUPERVISION_REAL
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION_REAL", nullable = true)
  private Date feFinSupervisionReal;

  /*
  *EXP_SIGED_SUPERVISION
  */
   @Column(name = "EXP_SIGED_SUPERVISION", nullable = true)
  private String expSigedSupervision;

  /*
  *EXP_SIGED_PAS
  */
   @Column(name = "EXP_SIGED_PAS", nullable = true)
  private String expSigedPas;

  /*
  *ETIQUETA_AGENTE_SUPERVISADO
  */
   @Column(name = "ETIQUETA_AGENTE_SUPERVISADO", nullable = true)
  private String etiquetaAgenteSupervisado;

  /*
  *ETIQUETA_UNIDAD_MINERA
  */
   @Column(name = "ETIQUETA_UNIDAD_MINERA", nullable = true)
  private String etiquetaUnidadMinera;

  /*
  *NO_ESPECIALIDAD
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *ETIQUETA_PASO_ACTUAL
  */
   @Column(name = "ETIQUETA_PASO_ACTUAL", nullable = true)
  private String etiquetaPasoActual;

  /*
  *FECHA_PASO_ACTUAL
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FECHA_PASO_ACTUAL", nullable = true)
  private Date fechaPasoActual;

  /*
  *DIAS_TRANSCURRIDOS
  */
   @Column(name = "DIAS_TRANSCURRIDOS", nullable = true)
  private Long diasTranscurridos;

  /*
  *ETIQUETA_PERSONA_ASIGNADA
  */
   @Column(name = "ETIQUETA_PERSONA_ASIGNADA", nullable = true)
  private String etiquetaPersonaAsignada;

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
  *FE_FIN_SUPERVISION
  */
   @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;

  /*
  *FL_PROPIA
  */
   @Column(name = "FL_PROPIA", nullable = true)
  private String flPropia;


}