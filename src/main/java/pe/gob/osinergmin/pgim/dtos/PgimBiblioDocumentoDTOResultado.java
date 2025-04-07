package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;


public class PgimBiblioDocumentoDTOResultado extends PgimBiblioDocumentoDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.BiblioDocumentoRepository#
	 * @param idBiblioDocumento
	 * @param idSubcatDocumento
	 * @param idTipoMedioIngreso
	 * @param idPersonaEmisora
	 * @param nuDocumento
	 * @param deAsuntoDocumento
	 * @param deSumillaDocumento
	 * @param feEmisionDocumento
	 * @param nuExpedienteSiged
	 */
	public PgimBiblioDocumentoDTOResultado(Long idBiblioDocumento, Long idSubcatDocumento, Long idTipoMedioIngreso, 
		Long idPersonaEmisora, String nuDocumento, String deAsuntoDocumento, 
		String deSumillaDocumento, Date feEmisionDocumento, String nuExpedienteSiged,
		String descNoSubcatDocumento, String descNoPersonaEmisora
			) {

		super();
		this.setIdBiblioDocumento(idBiblioDocumento);
		this.setIdSubcatDocumento(idSubcatDocumento);
		this.setIdTipoMedioIngreso(idTipoMedioIngreso);
		this.setIdPersonaEmisora(idPersonaEmisora);
		this.setNuDocumento(nuDocumento);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setDeSumillaDocumento(deSumillaDocumento);
		this.setFeEmisionDocumento(feEmisionDocumento);
		this.setNuExpedienteSiged(nuExpedienteSiged);
		this.setDescNoSubcatDocumento(descNoSubcatDocumento);
		this.setDescNoPersonaEmisora(descNoPersonaEmisora);
		
	}

	 
}
