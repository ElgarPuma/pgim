package pe.gob.osinergmin.pgim.dtos;

public class PgimSubcategoriaPlantiDTOResultado extends PgimSubcategoriaPlantiDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SubcategoriaPlantiRepository#obtenerSubcategoriaPlantiPorId()
	 * @param idSubcategoriaPlanti
	 * @param idSubcatDocumento
	 * @param noPlantilla
	 */
	public PgimSubcategoriaPlantiDTOResultado(Long idSubcategoriaPlanti, Long idSubcatDocumento,
			String noPlantilla) {
        super();

        this.setIdSubcategoriaPlanti(idSubcategoriaPlanti); 
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setNoPlantilla(noPlantilla);
    }

}