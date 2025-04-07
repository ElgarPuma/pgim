package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la entidad Filtro para Validar el Saldo del Contrato para la Supervision: 
* @descripción: DTO para la entidad Filtro para Validar el Saldo del Contrato para la Supervision
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 18/12/2020
*/
@Getter
@Setter
@NoArgsConstructor
public class FiltroValidaSaldoContratoXsupervisionDTO {
	
	/*
	 * DTO del Contrato
	*/
	
	private PgimContratoDTO pgimContratoDTO;
	
	/*
	 * DTO de la supervisión
	*/
	private PgimSupervisionDTO pgimSupervisionDTO;
	  
}
