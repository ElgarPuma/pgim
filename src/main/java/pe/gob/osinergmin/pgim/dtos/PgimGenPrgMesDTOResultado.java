package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimGenPrgMesDTOResultado extends PgimGenPrgMesDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.GenPrgMesRepository#obtenerGenPrgMes(Long)
	 * @param idGenPrgMes
	 * @param idGenPrgUfiscaliza
	 * @param flConformePrgMes
	 * @param nuMes
	 * @param deMensaje
	 */
	public PgimGenPrgMesDTOResultado(
		Long idGenPrgMes, Long idGenPrgUfiscaliza, String flConformePrgMes, 
		BigDecimal nuMes, String deMensaje
	){
		super();
		
		this.setIdGenPrgMes(idGenPrgMes);
		this.setIdGenPrgUfiscaliza(idGenPrgUfiscaliza);
		this.setFlConforme(flConformePrgMes);
		this.setNuMes(nuMes);
		this.setDeMensaje(deMensaje);
		

	}
	
}
