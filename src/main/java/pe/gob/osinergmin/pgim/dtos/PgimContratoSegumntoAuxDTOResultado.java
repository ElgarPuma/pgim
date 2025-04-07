package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimContratoSegumntoAuxDTOResultado extends PgimContratoSegumntoAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ContratoSegumntoAuxRepository#listarContratoSeguimientoAux()
	 * @param idContratoSegumntoAux
	 * @param idContrato
	 * @param nuContrato
	 * @param idEmpresaSupervisora
	 * @param idPersona
	 * @param noRazonSocial
	 * @param idEspecialidad
	 * @param noEspecialidad
	 * @param moImporteContrato
	 * @param saldoContrato
	 * @param preComprometido
	 * @param comprometido
	 * @param porLiquidar
	 * @param liquidado
	 * @param facturado
	 * @param totalConsumoContrato
	 */
	public PgimContratoSegumntoAuxDTOResultado(Long idContratoSegumntoAux, Long idContrato, 
			String nuContrato, Long idEmpresaSupervisora, Long idPersona, String noRazonSocial, 
	   		Long idEspecialidad, String noEspecialidad, BigDecimal moImporteContrato,
	   		BigDecimal saldoContrato, BigDecimal preComprometido, BigDecimal comprometido, 
	   		BigDecimal porLiquidar, BigDecimal liquidado, BigDecimal facturado, BigDecimal totalConsumoContrato) {
        super();
        this.setIdContratoSegumntoAux(idContratoSegumntoAux);
        this.setIdContrato(idContrato);
        this.setNuContrato(nuContrato);
        this.setIdEmpresaSupervisora(idEmpresaSupervisora);
        this.setIdPersona(idPersona);
        this.setNoRazonSocial(noRazonSocial);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setMoImporteContrato(moImporteContrato);
        this.setSaldoContrato(saldoContrato);
        this.setPreComprometido(preComprometido);
        this.setComprometido(comprometido);
        this.setPorLiquidar(porLiquidar);
        this.setLiquidado(liquidado);
        this.setFacturado(facturado);
        this.setTotalConsumoContrato(totalConsumoContrato);
    }
}
