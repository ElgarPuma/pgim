package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la entidad PGIM_VW_IND_APROBACION_INFORME: 
* @descripción: Vista para el indicador de aprobación de los informes de fiscalización
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 02/03/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimIndAprobacionInformeDTO {

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
	   *FL_FE_ENVIO
	   */
	 private String flFeEnvio;
	  
	  /*
	   * FE_PRESENTA_INFORME
	   */
	 private Date fePresentaInforme;
	  
	  /*
	   * FE_APRUEBA_INFORME
	   */
	 private Date feApruebaInforme;
	  
	  
	  /*
	   * CA_DIAS_DEMORA
	   */
	 private Long caDiasDemora;
	  
	  
	 
	
}
