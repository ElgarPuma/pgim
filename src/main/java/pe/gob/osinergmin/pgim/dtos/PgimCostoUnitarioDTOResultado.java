package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimCostoUnitarioDTOResultado extends PgimCostoUnitarioDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CostoUnitarioRepository#obtenerCostoUnitarioPorId
	 * @see pe.gob.osinergmin.pgim.models.repository.CostoUnitarioRepository#obtenerCostoUnitarioPorIdTarifarioContrato
	 * @param idCostoUnitario
	 * @param idTarifarioContrato
	 * @param unDiaCostoUnitario
	 * @param moCostoUnitario
	 * @param moCostoActa
	 * @param moCostoInformeSupervision
	 * @param moCostoInformeGestion
	 * @param esRegistro
	 */
	  public PgimCostoUnitarioDTOResultado(Long idCostoUnitario, Long idTarifarioContrato,
		  Integer unDiaCostoUnitario, BigDecimal moCostoUnitario, BigDecimal moCostoActa, BigDecimal moCostoInformeSupervision, BigDecimal moCostoInformeGestion, String esRegistro) {
        super();
        this.setIdCostoUnitario(idCostoUnitario);
        this.setIdTarifarioContrato(idTarifarioContrato);
        this.setUnDiaCostoUnitario(unDiaCostoUnitario);
        this.setMoCostoUnitario(moCostoUnitario);
		this.setMoCostoActa(moCostoActa);		
		this.setMoCostoInformeSupervision(moCostoInformeSupervision);
		this.setMoCostoInformeGestion(moCostoInformeGestion);

        this.setEsRegistro(esRegistro);
    }
}
