package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la vista PGIM_VW_IND_PAS_ARCHIVADO: 
* @descripción: Vista para el indicador de decisión de recomendar el inicio del PAS o de archivado
*
* @author: gdelaguila
* @version 1.0
* @fecha_de_creación: 06/03/2023
*/
@Entity
@Table(name = "PGIM_VW_IND_PAS_ARCHIVADO")
@Data
@NoArgsConstructor
public class PgimIndPasArchivado implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 *ID_SUPERVISION
	 */
	  @Id
	  @Column(name = "ID_SUPERVISION", nullable = false)
	 private Long idSupervision;
	  
	  
	  /*
	   * CO_SUPERVISION
	   */
	  @Column(name = "CO_SUPERVISION", nullable = true)
	  private String coSupervision;

	 /*
	 *ID_ESPECIALIDAD
	 */
	  @Column(name = "ID_ESPECIALIDAD", nullable = true)
	 private Long idEspecialidad;

	 /*
	 *NO_ESPECIALIDAD
	 */
	  @Column(name = "NO_ESPECIALIDAD", nullable = true)
	 private String noEspecialidad;
	 
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
	   *ID_SUBTIPO_SUPERVISION
	   */
	  @Column(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
	  private Long idSubtipoSupervision;

	   /*
	   *DE_SUBTIPO_SUPERVISION
	   */
	  @Column(name = "DE_SUBTIPO_SUPERVISION", nullable = true)
	  private String deSubtipoSupervision;
	  
	  
	  /*
	   *ID_MOTIVO_SUPERVISION
	   */
	  @Column(name = "ID_MOTIVO_SUPERVISION", nullable = true)
	  private Long idMotivoSupervision;

	   /*
	   *DE_MOTIVO_SUPERVISION
	   */
	  @Column(name = "DE_MOTIVO_SUPERVISION", nullable = true)
	  private String deMotivoSupervision;
	  
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
	   *NO_UNIDAD_MINERA
	   */
	  @Column(name = "NO_UNIDAD_MINERA", nullable = true)
	  private String noUnidadMinera;
	  
	  /*
	   *ID_CONTRATO
	   */
	  @Column(name = "ID_CONTRATO", nullable = true)
	  private Long idContrato;

	   /*
	   *NU_CONTRATO
	   */
	  @Column(name = "NU_CONTRATO", nullable = true)
	  private String nuContrato;
	  
	  /*
	   *ID_AGENTE_SUPERVISADO
	   */
	  @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
	  private Long idAgenteSupervisado;

	   /*
	   *NO_AGENTE_SUPERVISADO
	   */
	  @Column(name = "NO_AGENTE_SUPERVISADO", nullable = true)
	  private String noAgenteSupervisado;
	  
	  /*
	   *RUC_AGENTE_SUPERVISADO
	   */
	  @Column(name = "RUC_AGENTE_SUPERVISADO", nullable = true)
	  private String rucAgenteSupervisado;
	  
	  /*
	   *ID_EMPRESA_SUPERVISORA
	   */
	  @Column(name = "ID_EMPRESA_SUPERVISORA", nullable = true)
	  private Long idEmpresaSupervisora;
	  
	  /*
	   *RUC_EMPRESA_SUPERVISORA
	   */
	  @Column(name = "RUC_EMPRESA_SUPERVISORA", nullable = true)
	  private String rucEmpresaSupervisora;
	  
	  /*
	   *NO_EMPRESA_SUPERVISORA
	   */
	  @Column(name = "NO_EMPRESA_SUPERVISORA", nullable = true)
	  private String noEmpresaSupervisora;
	  
	  /*
	   * ID_RELACION_PASO
	   */
	  @Column(name = "ID_RELACION_PASO", nullable = true)
	  private Long idRelacionPaso;
	  
	  /*
	   *NO_PASO_PROCESO_DECISION
	   */
	  @Column(name = "NO_PASO_PROCESO_DECISION", nullable = true)
	  private String noPasoProcesoDecision;
	  
	  /*
	   * FE_APRUEBA_INFORME
	   */
	  @Column(name = "FE_APRUEBA_INFORME", nullable = true)
	  private Date feApruebaInforme;
	 
	  /*
	   * FE_DECISION
	   */
	  @Column(name = "FE_DECISION", nullable = true)
	  private Date feDecision;
	  
	  /*
	   * CA_DIAS_DEMORA
	   */
	  @Column(name = "CA_DIAS_DEMORA", nullable = true)
	  private Long caDiasDemora;
	
}
