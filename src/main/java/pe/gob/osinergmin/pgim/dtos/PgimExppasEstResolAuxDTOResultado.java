package pe.gob.osinergmin.pgim.dtos;

public class PgimExppasEstResolAuxDTOResultado extends PgimExppasEstResolAuxDTO {
		
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppasEstResolPaginado
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppasEstResol
	 * 
	 * @param idDivisionSupervisora
	 * @param deDivisionSupervisora
	 * @param coEstado
	 * @param noEstado
	 * @param anioSuper
	 * @param nuGeomecania
	 * @param nuGeotecania
	 * @param nuPlantaBene
	 * @param nuTransporte
	 * @param nuVentilacion
	 * @param nuTotal
	 */
	public PgimExppasEstResolAuxDTOResultado(
			Long idDivisionSupervisora
			, String deDivisionSupervisora
			, String noDivisionSupervisora
			, String coEstado
			, String noEstado
			, String anioSuper
			, Long nuGeomecania
			, Long nuGeotecania
			, Long nuPlantaBene
			, Long nuTransporte
			, Long nuVentilacion
			, Long nuTotal
	) {
		super();
		this.setIdDivisionSupervisora(idDivisionSupervisora);
		this.setDeDivisionSupervisora(deDivisionSupervisora);
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setCoEstado(coEstado);
		this.setNoEstado(noEstado);
		this.setAnioSuper(anioSuper);
		this.setNuGeomecania(nuGeomecania);
		this.setNuGeotecania(nuGeotecania);
		this.setNuPlantaBene(nuPlantaBene);
		this.setNuTransporte(nuTransporte);
		this.setNuVentilacion(nuVentilacion);
		this.setNuTotal(nuTotal);
		
	}

}
