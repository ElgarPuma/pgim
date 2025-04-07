package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimEventoDTOResultado extends PgimEventoDTO {

	public PgimEventoDTOResultado(Long idEvento, Long idUnidadMinera, Long idTipoEvento, Long idAgenteCausante,
			Long idTipoAccidenteMortal, String coEvento, Date fePresentacion, Date feEvento, String deEvento,
			String deLugarEvento, String esNoContabilizar, String deMotivoNocontabilizar, String esRegistro,
			String usCreacion, String ipCreacion, Date feCreacion, String usActualizacion, String ipActualizacion,
			Date feActualizacion, String fePresentacionDesc, String feEventoDesc, String descIdTipoEvento,
			Long idTipoIncidentePeligro, Long descIdAgenteSupervisado, String nuExpedienteSiged) {

		super();

		this.setIdEvento(idEvento);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setIdTipoEvento(idTipoEvento);
		this.setIdAgenteCausante(idAgenteCausante);
		this.setIdTipoAccidenteMortal(idTipoAccidenteMortal);
		this.setCoEvento(coEvento);
		this.setFePresentacion(fePresentacion);
		this.setFeEvento(feEvento);
		this.setDeEvento(deEvento);
		this.setDeLugarEvento(deLugarEvento);
		this.setEsNoContabilizar(esNoContabilizar);
		this.setDeMotivoNocontabilizar(deMotivoNocontabilizar);
		this.setEsRegistro(esRegistro);
		this.setUsCreacion(usCreacion);
		this.setIpCreacion(ipCreacion);
		this.setFeCreacion(feCreacion);
		this.setUsActualizacion(usActualizacion);
		this.setIpActualizacion(ipActualizacion);
		this.setFeActualizacion(feActualizacion);
		this.setFePresentacionDesc(fePresentacionDesc);
		this.setFeEventoDesc(feEventoDesc);
		this.setDescIdTipoEvento(descIdTipoEvento);
		this.setIdTipoIncidentePeligro(idTipoIncidentePeligro);
		this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
		this.setNuExpedienteSiged(nuExpedienteSiged);
	}

	public PgimEventoDTOResultado(Long idEvento, String coEvento, String descIdTipoEvento, Date feEvento) {

		super();

		this.setIdEvento(idEvento);
		this.setCoEvento(coEvento);
		this.setDescIdTipoEvento(descIdTipoEvento);
		this.setFeEvento(feEvento);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.EventoRepository#listarEventosGral
	 * @param idEvento
	 * @param coEvento
	 * @param deEvento
	 * @param descIdTipoEvento
	 * @param feEvento
	 */
	public PgimEventoDTOResultado(Long idEvento, String coEvento, String deEvento, String descIdTipoEvento, Date feEvento) {
		super();
		this.setIdEvento(idEvento);
		this.setCoEvento(coEvento);
		this.setDeEvento(deEvento);
		this.setDescIdTipoEvento(descIdTipoEvento);
		this.setFeEvento(feEvento);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.EventoRepository#getEventosPorUnidadMinera
	 * @param idEvento
	 * @param idUnidadMinera
	 * @param idTipoEvento
	 * @param idAgenteCausante
	 * @param idTipoAccidenteMortal
	 * @param coEvento
	 * @param fePresentacion
	 * @param feEvento
	 * @param deEvento
	 * @param deLugarEvento
	 * @param esNoContabilizar
	 * @param deMotivoNocontabilizar
	 * @param descIdTipoEvento
	 * @param idTipoIncidentePeligro
	 * @param descNoTipoAccidenteMortal
	 * @param descNoTipoIncidentePeligro
	 */
	public PgimEventoDTOResultado(Long idEvento, Long idUnidadMinera, Long idTipoEvento, Long idAgenteCausante,
			Long idTipoAccidenteMortal, String coEvento, Date fePresentacion, Date feEvento, String deEvento,
			String deLugarEvento, String esNoContabilizar, String deMotivoNocontabilizar, String descIdTipoEvento,
			Long idTipoIncidentePeligro, String descNoTipoAccidenteMortal, String descNoTipoIncidentePeligro) {

		super();

		this.setIdEvento(idEvento);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setIdTipoEvento(idTipoEvento);
		this.setIdAgenteCausante(idAgenteCausante);
		this.setIdTipoAccidenteMortal(idTipoAccidenteMortal);
		this.setCoEvento(coEvento);
		this.setFePresentacion(fePresentacion);
		this.setFeEvento(feEvento);
		this.setDeEvento(deEvento);
		this.setDeLugarEvento(deLugarEvento);
		this.setEsNoContabilizar(esNoContabilizar);
		this.setDeMotivoNocontabilizar(deMotivoNocontabilizar);
		this.setDescIdTipoEvento(descIdTipoEvento);
		this.setIdTipoIncidentePeligro(idTipoIncidentePeligro);
		this.setDescNoTipoAccidenteMortal(descNoTipoAccidenteMortal); 
		this.setDescNoTipoIncidentePeligro(descNoTipoIncidentePeligro);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.EventoRepository#listarEventoPorUnidadMinera
	 * @param idEvento
	 */
	public PgimEventoDTOResultado(Long idEvento) {

		super();
		this.setIdEvento(idEvento);
	}

}
