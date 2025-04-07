package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para los filtros del dashboard de indicadores  
* @descripción: DTO para los filtros del dashboard de indicadores
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 03/03/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class FiltroIndicadorDTO {
	
	/*
	 * ID_INDICADOR
	 */
	private Long idIndicador;
	
	/*
	 * Fecha desde
	 */
	private Date feDesde;
	
	/*
	 * Fecha Hasta
	 */
	private Date feHasta;
	
	 /*
	 *ID_ESPECIALIDAD
	 */
	 private Long idEspecialidad;
	 
	  /*
	   *ID_DIVISION_SUPERVISORA
	   */
	 private Long idDivisionSupervisora;

	  /*
	   *ID_TIPO_SUPERVISION
	   */
	 private Long idTipoSupervision;
	  
	  /*
	   *ID_SUBTIPO_SUPERVISION
	   */
	 private Long idSubTipoSupervision;

	  /*
	   *ID_MOTIVO_SUPERVISION
	   */
	 private Long idMotivoSupervision;

	 /*
	  * Agente supervisado
	  */
	 private String noAgenteSupervisado;
		  
	  /*
	   * Unidad  minera
	   */
	 private String noUnidadMinera;
	  
	   /*
	   * Contrato
	   */
	 private String nuContrato;
	  	  
	  /*
	   * Empresa supervisora
	   */
	 private String noEmpresaSupervisora;
	 
	 
	 /*
	  * ID tipo de indicador (texto)  
	  */
	private String descIdIndicadorTexto;
	  
	
}
