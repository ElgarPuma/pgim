package pe.gob.osinergmin.pgim.dtos;

public class PgimRelacionFirmaDTOResultado extends PgimRelacionFirmaDTO {
	
	/**
     * 
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionFirmaRepository#listarRelacionFirmaPorIdRelacion()
     *

     */
    public PgimRelacionFirmaDTOResultado(Long idRelacionFirma, Long idSubcatDocumento
    		, Long idRelacionPaso,String descNoSubcatDocumento, String descNoCategoriaDocumento
    		) {
        super();

        this.setIdRelacionFirma(idRelacionFirma);
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setDescNoSubcatDocumento(descNoSubcatDocumento);
        this.setDescNoCategoriaDocumento(descNoCategoriaDocumento);
        
    }

}
