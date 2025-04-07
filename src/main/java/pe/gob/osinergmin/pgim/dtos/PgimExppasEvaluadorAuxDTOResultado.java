package pe.gob.osinergmin.pgim.dtos;

public class PgimExppasEvaluadorAuxDTOResultado extends PgimExppasEvaluadorAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExpPasPerDsEspecPaginado
	 * @param noDivisionSupervisora
	 * @param noEspecialidad
	 * @param noEvaluador
	 * @param nroExpPasP1
	 * @param nroExpPasP2
	 * @param nroExpPasP3
	 * @param nroExpPasP4
	 * @param nroExpPasP5
	 * @param nroExpPasP6
	 * @param totalNroExp
	 */
	public PgimExppasEvaluadorAuxDTOResultado(

			String noDivisionSupervisora
			,String noEspecialidad
			,String noEvaluador
			, Long nroExpPasP1
			, Long nroExpPasP2
			, Long nroExpPasP3
			, Long nroExpPasP4
			, Long nroExpPasP5
			, Long nroExpPasP6
			, Long totalNroExp
	) {
		super();
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setNoEspecialidad(noEspecialidad);
		this.setNoEvaluador(noEvaluador);
		this.setNroExpPasP1(nroExpPasP1);
		this.setNroExpPasP2(nroExpPasP2);
		this.setNroExpPasP3(nroExpPasP3);
		this.setNroExpPasP4(nroExpPasP4);
		this.setNroExpPasP5(nroExpPasP5);
		this.setNroExpPasP6(nroExpPasP6);
		this.setNroExpPasTotal(totalNroExp);
		
	}

}
