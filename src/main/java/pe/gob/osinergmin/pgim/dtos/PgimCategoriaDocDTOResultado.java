package pe.gob.osinergmin.pgim.dtos;


public class PgimCategoriaDocDTOResultado extends PgimCategoriaDocDTO {
	
	public PgimCategoriaDocDTOResultado(Long idCategoriaDocumento, String noCategoriaDocumento,String esRegistro) {
		super();
		this.setIdCategoriaDocumento(idCategoriaDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento);		
		this.setEsRegistro(esRegistro);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CategoriaDocRepository#listarCategoriaYSubCategoriaByIdProcesoIdFase()
	 * @param idCategoriaDocumento
	 * @param coCategoriaDocumento
	 * @param noCategoriaDocumento
	 * @param descIdSubcatDocumento
	 * @param descCoSubcatDocumento
	 * @param descNoSubcatDocumento
	 * @param descIdSubcategoriaPlanti 
	 * @param descNoPlantilla
	 */
	public PgimCategoriaDocDTOResultado(Long idCategoriaDocumento, String coCategoriaDocumento, String noCategoriaDocumento,
			Long descIdSubcatDocumento, String descCoSubcatDocumento, String descNoSubcatDocumento,
			Long descIdSubcategoriaPlanti, String descNoPlantilla) {
		super();
		this.setIdCategoriaDocumento(idCategoriaDocumento);
		this.setCoCategoriaDocumento(coCategoriaDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento);
		this.setDescIdSubcatDocumento(descIdSubcatDocumento);
		this.setDescCoSubcatDocumento(descCoSubcatDocumento);
		this.setDescNoSubcatDocumento(descNoSubcatDocumento);
		this.setDescIdSubcategoriaPlanti(descIdSubcategoriaPlanti);
		this.setDescNoPlantilla(descNoPlantilla);
	}
	


}
