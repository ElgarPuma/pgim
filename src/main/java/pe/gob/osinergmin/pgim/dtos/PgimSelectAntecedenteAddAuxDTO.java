package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimSelectAntecedenteAddAuxDTO {
	
	/**
	 * id de supervision
	 */
	private Long idSupervision;
	
	/***
	 *Listado de Selección de Antecedente
	 */
	private PgimSelectAntecedenteAuxDTO auxListaSelectAntecedente[];
	
}
