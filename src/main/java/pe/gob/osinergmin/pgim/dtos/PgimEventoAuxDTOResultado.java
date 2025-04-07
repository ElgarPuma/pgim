package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimEventoAuxDTOResultado extends PgimEventoAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.EventoRepository#listarEventosReporte
	 * @see pe.gob.osinergmin.pgim.models.repository.EventoRepository#listarEventosTotal
	 * 
	 * @param idEvento
	 * @param coEvento
	 * @param deEvento
	 * @param noTipoEvento
	 * @param feEvento
	 * @param feAnioEvento
	 * @param deLugarEvento
	 * @param coUnidadMinera
	 * @param noUnidadMinera
	 * @param rucAs
	 * @param noRazonSocialAs
	 * @param noCortoAs
	 * @param noAgenteCausante
	 * @param noTipoAccidenteMortal
	 */
	public PgimEventoAuxDTOResultado(Long idEvento, String coEvento, String deEvento, String noTipoEvento, Date feEvento, Long feAnioEvento, String deLugarEvento, 
			String coUnidadMinera, String noUnidadMinera, String rucAs, String noRazonSocialAs, String noCortoAs, String noAgenteCausante, String noTipoAccidenteMortal ) {
		super();
		this.setIdEvento(idEvento);
		this.setCoEvento(coEvento);
		this.setDeEvento(deEvento);
		this.setNoTipoEvento(noTipoEvento);
		this.setFeEvento(feEvento);
		this.setFeAnioEvento(feAnioEvento);
		this.setDeLugarEvento(deLugarEvento);
		this.setCoUnidadMinera(coUnidadMinera);
		this.setNoUnidadMinera(noUnidadMinera);
		this.setRucAs(rucAs);
		this.setNoRazonSocialAs(noRazonSocialAs);
		this.setNoCortoAs(noCortoAs);
		this.setNoAgenteCausante(noAgenteCausante);
		this.setNoTipoAccidenteMortal(noTipoAccidenteMortal);
	}

}
