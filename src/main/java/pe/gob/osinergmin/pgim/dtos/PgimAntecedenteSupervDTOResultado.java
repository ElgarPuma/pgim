package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimAntecedenteSupervDTOResultado extends PgimAntecedenteSupervDTO{
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AntecedenteSupervRepository#obtenerAntecedenteSupervDTOPorId()
	 * 
	 * @param AntecedenteSuperv
	 * @param idSupervision
	 * @param idDocumento
	 * @param idTipoAntecedente
	 */
	public PgimAntecedenteSupervDTOResultado(Long AntecedenteSuperv, Long idSupervision, Long idDocumento, Long idTipoAntecedente, 
			Long tipoSupervision, String nuExpedienteSiged, Date feInicioSupervisionReal, Date feFinSupervisionReal, String deAspectosRevisados ) {
		super();
		
		this.setIdDocumento(idDocumento);
		this.setIdSupervision(idSupervision);
		this.setIdDocumento(idDocumento);
		this.setIdTipoAntecedente(idTipoAntecedente);
		
		this.setIdTipoSupervision(tipoSupervision); 
		this.setNuExpedienteSiged(nuExpedienteSiged); 
		this.setFeInicioSupervisionReal(feInicioSupervisionReal); 
		this.setFeFinSupervisionReal(feFinSupervisionReal);
		this.setDeAspectosRevisados(deAspectosRevisados);
		
		
	}

}
