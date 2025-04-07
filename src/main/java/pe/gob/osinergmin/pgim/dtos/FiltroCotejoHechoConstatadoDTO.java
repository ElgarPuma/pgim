package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
*  
* @descripción: DTO para los filtros de la lista de cotejo de hechos constatados
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 05/11/2020
*/
@Getter
@Setter
@NoArgsConstructor
public class FiltroCotejoHechoConstatadoDTO {

	/*
	 *ID de la supervisión
	*/
	private Long idSupervision;
	
	/*
	 * Código de cumplimiento de la Supervisora
	*/
	private Long codigoCumpleSupervisora;

	/*
	 * Código de cumplimiento del Osinergmin
	*/
	private Long codigoCumpleOsinergmin;

	/**
	 * Id de la fase seleccionada
	 */
	private Long idFaseSeleccion;
	
	/**
	 * Id de la fase actual
	 */
	private Long idFaseActual;
}

