package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimPrspstoGastoSuperDTOResultado extends PgimPrspstoGastoSuperDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PrspstoGastoSuperRepository#listarPrspstoGastoSuper()
	 * @param idContratoAux
	 * @param idContrato
	 * @param nuContrato
	 * @param feInicioContrato
	 * @param idEmpresaSupervisora
	 * @param idPersona
	 * @param deEmpresaSupervisora
	 * @param moImporteContrato
	 * @param moConsumoContrato
	 * @param moSaldo
	 * @param moActaSupervision
	 * @param nuActaSupervision
	 * @param moInformeSupervision
	 * @param nuInformeSupervision
	 * @param moSupervisionFallida
	 * @param nuSupervisionFallida
	 * @param moInformeGestion
	 * @param nuInformeGestion
	 */
	public PgimPrspstoGastoSuperDTOResultado(Long idContratoAux, Long idContrato, String nuContrato,
	  Date feInicioContrato, Long idEmpresaSupervisora, Long idPersona,
	  String deEmpresaSupervisora, BigDecimal moImporteContrato, BigDecimal moConsumoContrato, BigDecimal moSaldo,
	  BigDecimal moActaSupervision, Long nuActaSupervision, BigDecimal moInformeSupervision, Long nuInformeSupervision,
	  BigDecimal moSupervisionFallida, Long nuSupervisionFallida, BigDecimal moInformeGestion, Long nuInformeGestion) {
	        super();
	        this.setIdContratoAux(idContratoAux);
	        this.setIdContrato(idContrato);
	        this.setNuContrato(nuContrato);
	        this.setFeInicioContrato(feInicioContrato);
	        this.setIdEmpresaSupervisora(idEmpresaSupervisora);
	        this.setIdPersona(idPersona);
	        this.setDeEmpresaSupervisora(deEmpresaSupervisora);
	        this.setMoImporteContrato(moImporteContrato);
	        this.setMoConsumoContrato(moConsumoContrato);
	        this.setMoSaldo(moSaldo);
	        this.setMoActaSupervision(moActaSupervision);
	        this.setNuActaSupervision(nuActaSupervision);
	        this.setMoInformeSupervision(moInformeSupervision);
	        this.setNuInformeSupervision(nuInformeSupervision);
	        this.setMoSupervisionFallida(moSupervisionFallida);
	        this.setNuSupervisionFallida(nuSupervisionFallida);
	        this.setMoInformeGestion(moInformeGestion);
	        this.setNuInformeGestion(nuInformeGestion);
	    }
}