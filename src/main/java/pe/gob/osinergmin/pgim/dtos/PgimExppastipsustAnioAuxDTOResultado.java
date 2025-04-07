package pe.gob.osinergmin.pgim.dtos;

public class PgimExppastipsustAnioAuxDTOResultado extends PgimExppastipsustAnioAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppastipsustAnioPaginado
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppastipsustAnio
	 * 
	 * @param noTipoSustancia
	 * @param nroExpPasP1
	 * @param nroExpPasP2
	 * @param nroExpPasP3
	 * @param nroExpPasP4
	 * @param nroExpPasP5
	 * @param nroExpPasP6
	 * @param totalNroExp
	 */
	public PgimExppastipsustAnioAuxDTOResultado(
			String noTipoSustancia
			, Long nroExpPasP1
			, Long nroExpPasP2
			, Long nroExpPasP3
			, Long nroExpPasP4
			, Long nroExpPasP5
			, Long nroExpPasP6
			, Long totalNroExp
	) {
		super();
		this.setNoTipoSustancia(noTipoSustancia);
		this.setNroExpPasP1(nroExpPasP1);
		this.setNroExpPasP2(nroExpPasP2);
		this.setNroExpPasP3(nroExpPasP3);
		this.setNroExpPasP4(nroExpPasP4);
		this.setNroExpPasP5(nroExpPasP5);
		this.setNroExpPasP6(nroExpPasP6);
		this.setTotalNroExp(totalNroExp);
		
	}

}
