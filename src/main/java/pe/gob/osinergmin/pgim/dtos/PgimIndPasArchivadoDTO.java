package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la entidad PGIM_VW_IND_PAS_ARCHIVADO: 
* @descripción: Vista para el indicador de decisión de recomendar el inicio del PAS o de archivado
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 06/03/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimIndPasArchivadoDTO {
	
	/*
	 *ID_SUPERVISION
	 */
	 private Long idSupervision;
	  
	  
	  /*
	   * CO_SUPERVISION
	   */
	  private String coSupervision;

	 /*
	 *ID_ESPECIALIDAD
	 */
	 private Long idEspecialidad;

	 /*
	 *NO_ESPECIALIDAD
	 */
	 private String noEspecialidad;
	 
	  /*
	   *ID_DIVISION_SUPERVISORA
	   */
	  private Long idDivisionSupervisora;

	   /*
	   *NO_DIVISION_SUPERVISORA
	   */
	  private String noDivisionSupervisora;
	   
	  /*
	   *ID_TIPO_SUPERVISION
	   */
	  private Long idTipoSupervision;

	   /*
	   *NO_TIPO_SUPERVISION
	   */
	  private String noTipoSupervision;

	  
	  /*
	   *ID_SUBTIPO_SUPERVISION
	   */
	  private Long idSubtipoSupervision;

	   /*
	   *DE_SUBTIPO_SUPERVISION
	   */
	  private String deSubtipoSupervision;
	  
	  
	  /*
	   *ID_MOTIVO_SUPERVISION
	   */
	  private Long idMotivoSupervision;

	   /*
	   *DE_MOTIVO_SUPERVISION
	   */
	  private String deMotivoSupervision;
	  
	  /*
	   *ID_UNIDAD_MINERA
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
	   *ID_CONTRATO
	   */
	  private Long idContrato;

	   /*
	   *NU_CONTRATO
	   */
	  private String nuContrato;
	  
	  /*
	   *ID_AGENTE_SUPERVISADO
	   */
	  private Long idAgenteSupervisado;

	   /*
	   *NO_AGENTE_SUPERVISADO
	   */
	  private String noAgenteSupervisado;
	  
	  /*
	   *RUC_AGENTE_SUPERVISADO
	   */
	  private String rucAgenteSupervisado;
	  
	  /*
	   *ID_EMPRESA_SUPERVISORA
	   */
	  private Long idEmpresaSupervisora;
	  
	  /*
	   *RUC_EMPRESA_SUPERVISORA
	   */
	  private String rucEmpresaSupervisora;
	  
	  /*
	   *NO_EMPRESA_SUPERVISORA
	   */
	  private String noEmpresaSupervisora;
	  
	  /*
	   * ID_RELACION_PASO
	   */
	  private Long idRelacionPaso;
	  
	  /*
	   *NO_PASO_PROCESO_DECISION
	   */
	  private String noPasoProcesoDecision;
	  
	  /*
	   * FE_APRUEBA_INFORME
	   */
	  private Date feApruebaInforme;
	 
	  /*
	   * FE_DECISION
	   */
	  private Date feDecision;
	  
	  /*
	   * CA_DIAS_DEMORA
	   */
	  private Long caDiasDemora;

}
