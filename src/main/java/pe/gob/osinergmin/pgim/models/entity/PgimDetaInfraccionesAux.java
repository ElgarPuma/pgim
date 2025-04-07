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
* Clase Entidad para la tabla PGIM_VW_DETA_INFRACCIONES_AUX: 
* @descripción: Vista detallada de infracciones en el expediente PAS
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_DETA_INFRACCIONES_AUX")
@Data
@NoArgsConstructor
public class PgimDetaInfraccionesAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la infracción. Secuencia: PGIM_SEQ_FASE_PROCESO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_FASE_PROCESO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_FASE_PROCESO_AUX", sequenceName = "PGIM_SEQ_FASE_PROCESO_AUX", allocationSize = 1)
   @Column(name = "ID_INFRACCION_AUX", nullable = false)
  private Long idInfraccionAux;

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
  *ASUNTO_RESOLUCION
  */
   @Column(name = "ASUNTO_RESOLUCION", nullable = true)
  private String asuntoResolucion;

  /*
  *FECHA_EMISION_RESOLUCION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FECHA_EMISION_RESOLUCION", nullable = true)
  private Date fechaEmisionResolucion;

  /*
  *ETIQUETA_INFRACCION
  */
   @Column(name = "ETIQUETA_INFRACCION", nullable = true)
  private String etiquetaInfraccion;

  /*
  *DE_SANCION_PECUNIARIA_UIT
  */
   @Column(name = "DE_SANCION_PECUNIARIA_UIT", nullable = true)
  private String deSancionPecuniariaUit;

  /*
  *ID_AGENTE_SUPERVISADO
  */
   @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private Long idAgenteSupervisado;

  /*
  *DI_AGENTE_SUPERVISADO
  */
   @Column(name = "DI_AGENTE_SUPERVISADO", nullable = true)
  private String diAgenteSupervisado;

  /*
  *ID_UNIDAD_MINERA
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *ID_DIVISION_SUPERVISORA
  */
   @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private Long idDivisionSupervisora;

  /*
  *NO_DIVISION_SUPERVISORA
  */
   @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;

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
  *FL_PROPIA
  */
   @Column(name = "FL_PROPIA", nullable = true)
  private String flPropia;


}