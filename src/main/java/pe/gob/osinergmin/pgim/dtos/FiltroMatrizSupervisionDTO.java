package pe.gob.osinergmin.pgim.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para los filtros de la matriz de supervisión: 
* @descripción: DTO para los filtros de la matriz de supervisión
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 02/11/2020
*/
@Getter
@Setter
@NoArgsConstructor
public class FiltroMatrizSupervisionDTO {
	
	/*
	 *ID de la supervisión
	*/
	private Long idSupervision;
	
	/*
	 *código de cumplimiento
	*/
	private Long codigoCumple;
	private List<Long> codigoCumpleSelected;
	
	/*
	 *tipo de matriz
	*/
	private String tipoMatriz;

	/**
	 * descriptor del sustento del hecho verificado
	 */
	private String deSustentoT;

	/**
	 * descriptor de la descripcion del hecho verificado
	 */
	private String deHechoConstatadoT;

	/*
	* descIdSubcategoriaDoc
	*/
  	private Long descIdSubcategoriaDoc;	
	
	/**
	 * Id de la fase seleccionada
	 */
	private Long idFaseSeleccion;
	
	/**
	 * Id de la fase actual
	 */
	private Long idFaseActual;

	/**
   * Descripción de la obligación normativa
   */
	private String deNorma;

	/**
   * Descripción de ítem de tipificación
   */
	private String deTipificacion;
}
