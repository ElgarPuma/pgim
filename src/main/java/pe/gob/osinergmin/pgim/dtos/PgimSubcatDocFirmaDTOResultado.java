package pe.gob.osinergmin.pgim.dtos;

public class PgimSubcatDocFirmaDTOResultado extends PgimSubcatDocFirmaDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SubcatDocFirmaRepository#listarPgimSubcatDocFirmaBySubcatAndPaso()
	 * 
	 */	
	
	public PgimSubcatDocFirmaDTOResultado(Long idSubcatDocFirma, Long idSubcatDocumento,
			Long idPasoProceso,  String flFirmaMultiple, String flVistoBueno) {
        super();
        
        this.setIdSubcatDocFirma(idSubcatDocFirma);
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setIdPasoProceso(idPasoProceso);
        this.setFlFirmaMultiple(flFirmaMultiple);
        this.setFlVistoBueno(flVistoBueno);
    }

}
