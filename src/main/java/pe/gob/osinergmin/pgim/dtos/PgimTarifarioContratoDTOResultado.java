package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimTarifarioContratoDTOResultado extends PgimTarifarioContratoDTO{

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.TarifarioContratoRepository#listarTarifarioContrato
	 * @see pe.gob.osinergmin.pgim.models.repository.TarifarioContratoRepository#obtenerTarifarioContratoPorId
	 * @param idTarifarioContrato
	 * @param idContrato
	 * @param noTarifario
	 * @param moSupervisionFallida
	 */
	public PgimTarifarioContratoDTOResultado(Long idTarifarioContrato, Long idContrato, String noTarifario,
			BigDecimal moSupervisionFallida) {
		super();
		this.setIdTarifarioContrato(idTarifarioContrato);
		this.setIdContrato(idContrato);
		this.setNoTarifario(noTarifario);
		this.setMoSupervisionFallida(moSupervisionFallida);
	}
}
