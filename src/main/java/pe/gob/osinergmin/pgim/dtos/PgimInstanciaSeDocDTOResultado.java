package pe.gob.osinergmin.pgim.dtos;

public class PgimInstanciaSeDocDTOResultado extends PgimInstanciaSeDocDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InstanciaSeDocRepository#obtenerSeCorrelativoPorInstanciaYSubcat
	 * 
	 * @param idInstanciaSeDoc
	 * @param idInstanciaProceso
	 * @param idSubcatDocumento
	 * @param seCorrelativo
	 */
	public PgimInstanciaSeDocDTOResultado(Long idInstanciaSeDoc, Long idInstanciaProceso, Long idSubcatDocumento, Long seCorrelativo) {
        super();

        this.setIdInstanciaSeDoc(idInstanciaSeDoc);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setSeCorrelativo(seCorrelativo);
    }

}
