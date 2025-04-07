package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimVwPrgrmMontoEspAuxDTOResultado extends PgimVwPrgrmMontoEspAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.VwPrgrmMontoEspAuxRepository#listarMontosEspecialidadPorPlan()
	 * @param idPlanSupervisionAux
	 * @param idPlanSupervision
	 * @param idEspecialidad
	 * @param noEspecialidad
	 * @param moPartida
	 * @param moDisponibleContrato
	 * @param contratos
	 * @param costoTotal
	 */
    public PgimVwPrgrmMontoEspAuxDTOResultado(Long idPlanSupervisionAux, Long idPlanSupervision, Long idEspecialidad, 
    		String noEspecialidad, BigDecimal moPartida, BigDecimal moDisponibleContrato, String contratos, BigDecimal costoTotal) {
        super();
        this.setIdPlanSupervisionAux(idPlanSupervisionAux);
        this.setIdPlanSupervision(idPlanSupervision);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setMoPartida(moPartida);
        this.setMoDisponibleContrato(moDisponibleContrato);
        this.setContratos(contratos);
        this.setCostoTotal(costoTotal);

    }
}
