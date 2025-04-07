package pe.gob.osinergmin.pgim.dtos;

public class PgimBiblioEntidadDTOResultado extends PgimBiblioEntidadDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.BiblioEntidadRepository#listaBiblioEntidadPorIddoc(Long)
	 * @param idBiblioEntidad
	 * @param idEntidadNegocio
	 * @param idBiblioDocumento
	 * @param idRegistroEntidadNegocio
	 * @param descNoEntidadNegocio
	 * @param descNoTipoEntidadNegocio
	 */
	public PgimBiblioEntidadDTOResultado(Long idBiblioEntidad, Long idEntidadNegocio, Long idBiblioDocumento, 
		Long idRegistroEntidadNegocio, String descNoTipoEntidadNegocio, String descNoEntidadNegocio
			) {

		super();
		this.setIdBiblioEntidad(idBiblioEntidad);
		this.setIdEntidadNegocio(idEntidadNegocio);
		this.setIdBiblioDocumento(idBiblioDocumento);
		this.setIdRegistroEntidadNegocio(idRegistroEntidadNegocio);
		this.setDescNoTipoEntidadNegocio(descNoTipoEntidadNegocio);		
		this.setDescNoEntidadNegocio(descNoEntidadNegocio);
		
	}


	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.BiblioEntidadRepository#listaBiblioEntidadPorIddoc(Long)
	 * @param idRegistroEntidadNegocio
	 * @param descNoEntidadNegocio
	 */
	public PgimBiblioEntidadDTOResultado(Long idRegistroEntidadNegocio, String descNoEntidadNegocio
			) {

		super();
		this.setIdRegistroEntidadNegocio(idRegistroEntidadNegocio);
		this.setDescNoEntidadNegocio(descNoEntidadNegocio);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.BiblioEntidadRepository#listarBiblioEntidadPorIddocBasic(Long)
	 * 
	 * @param idBiblioEntidad
	 * @param idEntidadNegocio
	 * @param idBiblioDocumento
	 * @param idRegistroEntidadNegocio
	 */
	public PgimBiblioEntidadDTOResultado(Long idBiblioEntidad, Long idEntidadNegocio, Long idBiblioDocumento, 
		Long idRegistroEntidadNegocio
			) {

		super();
		this.setIdBiblioEntidad(idBiblioEntidad);
		this.setIdEntidadNegocio(idEntidadNegocio);
		this.setIdBiblioDocumento(idBiblioDocumento);
		this.setIdRegistroEntidadNegocio(idRegistroEntidadNegocio);
		
	}
	

}
