package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimExppasdsuAnioAuxDTOResultado extends PgimExppasdsuAnioAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppasdsuAnio
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppasdsuAnioPaginado
	 * 
	 * @param noDivisionSupervisora
	 * @param noFaseProceso
	 * @param nroExpPasP1
	 * @param nroExpPasP2
	 * @param nroExpPasP3
	 * @param nroExpPasP4
	 * @param nroExpPasP5
	 * @param nroExpPasP6
	 * @param totalFase
	 */
	public PgimExppasdsuAnioAuxDTOResultado(
			String noDivisionSupervisora
			, String noFaseProceso
			, BigDecimal nroExpPasP1
			, BigDecimal nroExpPasP2
			, BigDecimal nroExpPasP3
			, BigDecimal nroExpPasP4
			, BigDecimal nroExpPasP5
			, BigDecimal nroExpPasP6
			, BigDecimal totalFase
	) {
		super();
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setNoFaseProceso(noFaseProceso);
		this.setNroExpPasP1(nroExpPasP1);
		this.setNroExpPasP2(nroExpPasP2);
		this.setNroExpPasP3(nroExpPasP3);
		this.setNroExpPasP4(nroExpPasP4);
		this.setNroExpPasP5(nroExpPasP5);
		this.setNroExpPasP6(nroExpPasP6);
		this.setTotalFase(totalFase);
		
	}

}
