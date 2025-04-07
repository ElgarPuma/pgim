package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimExppasespeMesAuxDTOResultado extends PgimExppasespeMesAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppasespeMes
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExppasespeMesPaginado
	 * 
	 * @param idEspecialidad
	 * @param noEspecialidad
	 * @param nroExpPasP1
	 * @param nroExpPasP2
	 * @param nroExpPasP3
	 * @param nroExpPasP4
	 * @param nroExpPasP5
	 * @param nroExpPasP6
	 * @param nroExpPasP7
	 * @param nroExpPasP8
	 * @param nroExpPasP9
	 * @param nroExpPasP10
	 * @param nroExpPasP11
	 * @param nroExpPasP12
	 * @param totalEspecialidad
	 */
	public PgimExppasespeMesAuxDTOResultado(
			BigDecimal idEspecialidad
			, String noEspecialidad
			, BigDecimal nroExpPasP1
			, BigDecimal nroExpPasP2
			, BigDecimal nroExpPasP3
			, BigDecimal nroExpPasP4
			, BigDecimal nroExpPasP5
			, BigDecimal nroExpPasP6
			, BigDecimal nroExpPasP7
			, BigDecimal nroExpPasP8
			, BigDecimal nroExpPasP9
			, BigDecimal nroExpPasP10
			, BigDecimal nroExpPasP11
			, BigDecimal nroExpPasP12
			, BigDecimal totalEspecialidad
	) {
		
		this.setIdEspecialidad(idEspecialidad);
		this.setNoEspecialidad(noEspecialidad);
		this.setNroExpPasP1(nroExpPasP1);
		this.setNroExpPasP2(nroExpPasP2);
		this.setNroExpPasP3(nroExpPasP3);
		this.setNroExpPasP4(nroExpPasP4);
		this.setNroExpPasP5(nroExpPasP5);
		this.setNroExpPasP6(nroExpPasP6);
		this.setNroExpPasP7(nroExpPasP7);
		this.setNroExpPasP8(nroExpPasP8);
		this.setNroExpPasP9(nroExpPasP9);
		this.setNroExpPasP10(nroExpPasP10);
		this.setNroExpPasP11(nroExpPasP11);
		this.setNroExpPasP12(nroExpPasP12);
		this.setTotalEspecialidad(totalEspecialidad);
		
	}

}
