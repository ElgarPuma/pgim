package pe.gob.osinergmin.pgim.dtos;

public class PgimExppasEspAnioAuxDTOResultado extends PgimExppasEspAnioAuxDTO{

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExpPasEspecAnio
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExpPasEspecAnioPaginado
	 * @param noEspecialidad
	 * @param nroExpPasP1
	 * @param nroExpPasP2
	 * @param nroExpPasP3
	 * @param nroExpPasP4
	 * @param nroExpPasP5
	 * @param nroExpPasP6
	 * @param totalNroExp
	 */
	public PgimExppasEspAnioAuxDTOResultado(
			String noEspecialidad
			, Long nroExpPasP1
			, Long nroExpPasP2
			, Long nroExpPasP3
			, Long nroExpPasP4
			, Long nroExpPasP5
			, Long nroExpPasP6
			, Long totalNroExp
	) {
		super();
		this.setNoEspecialidad(noEspecialidad);
		this.setNroExpPasP1(nroExpPasP1);
		this.setNroExpPasP2(nroExpPasP2);
		this.setNroExpPasP3(nroExpPasP3);
		this.setNroExpPasP4(nroExpPasP4);
		this.setNroExpPasP5(nroExpPasP5);
		this.setNroExpPasP6(nroExpPasP6);
		this.setTotalNroExp(totalNroExp);
		
	}
}
