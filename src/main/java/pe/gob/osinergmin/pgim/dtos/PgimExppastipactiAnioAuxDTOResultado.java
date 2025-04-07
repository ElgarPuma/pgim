package pe.gob.osinergmin.pgim.dtos;

public class PgimExppastipactiAnioAuxDTOResultado extends PgimExppastipactiAnioAuxDTO {
	
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppastipactiAnioPaginado
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppastipactiAnio
	 * 
	 * @param noTipoActividad
	 * @param nroExpPasP1
	 * @param nroExpPasP2
	 * @param nroExpPasP3
	 * @param nroExpPasP4
	 * @param nroExpPasP5
	 * @param nroExpPasP6
	 * @param totalNroExp
	 */
	public PgimExppastipactiAnioAuxDTOResultado(
			String noTipoActividad
			, Long nroExpPasP1
			, Long nroExpPasP2
			, Long nroExpPasP3
			, Long nroExpPasP4
			, Long nroExpPasP5
			, Long nroExpPasP6
			, Long totalNroExp
	) {
		super();
		this.setNoTipoActividad(noTipoActividad);
		this.setNroExpPasP1(nroExpPasP1);
		this.setNroExpPasP2(nroExpPasP2);
		this.setNroExpPasP3(nroExpPasP3);
		this.setNroExpPasP4(nroExpPasP4);
		this.setNroExpPasP5(nroExpPasP5);
		this.setNroExpPasP6(nroExpPasP6);
		this.setTotalNroExp(totalNroExp);
		
	}

}
