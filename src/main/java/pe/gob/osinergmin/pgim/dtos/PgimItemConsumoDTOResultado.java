package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimItemConsumoDTOResultado extends PgimItemConsumoDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemConsumoContratoRepository#obtenerListaItemConsumoVigentes()
	 * 
	 */
	public PgimItemConsumoDTOResultado(Long idItemConsumo, Long idConsumoContra, Long idTipoEntregable,
			Long idTipoEstadioConsumo, Long pcEntregable, BigDecimal moItemConsumo, BigDecimal moItemPenalidad,
			BigDecimal moItemSupervisionFallida, String esVigente) {
		super();

		this.setIdItemConsumo(idItemConsumo);
		this.setIdConsumoContra(idConsumoContra);
		this.setIdTipoEntregable(idTipoEntregable);
		this.setIdTipoEstadioConsumo(idTipoEstadioConsumo);
		this.setPcEntregable(pcEntregable);
		this.setMoItemConsumo(moItemConsumo);
		this.setMoItemPenalidad(moItemPenalidad);
		this.setMoItemSupervisionFallida(moItemSupervisionFallida);
		this.setEsVigente(esVigente);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemConsumoContratoRepository#obtenerListaItemConsumoVigentesSupervision()
	 * @param idItemConsumo
	 * @param idConsumoContra
	 * @param idTipoEntregable
	 * @param descTipoEntregable
	 * @param idTipoEstadioConsumo
	 * @param descEstadioConsumo
	 * @param pcEntregable
	 * @param moItemConsumo
	 * @param moItemPenalidad
	 * @param moItemSupervisionFallida
	 * @param esVigente
	 */	
	public PgimItemConsumoDTOResultado(Long idItemConsumo, Long idConsumoContra, Long idTipoEntregable,
			String descTipoEntregable, Long idTipoEstadioConsumo, String descEstadioConsumo, Long pcEntregable,
			BigDecimal moItemConsumo, BigDecimal moItemPenalidad, BigDecimal moItemSupervisionFallida,
			String esVigente) {
		super();

		this.setIdItemConsumo(idItemConsumo);
		this.setIdConsumoContra(idConsumoContra);
		this.setIdTipoEntregable(idTipoEntregable);
		this.setDescTipoEntregable(descTipoEntregable);
		this.setIdTipoEstadioConsumo(idTipoEstadioConsumo);
		this.setDescEstadioConsumo(descEstadioConsumo);
		this.setPcEntregable(pcEntregable);
		this.setMoItemConsumo(moItemConsumo);
		this.setMoItemPenalidad(moItemPenalidad);
		this.setMoItemSupervisionFallida(moItemSupervisionFallida);
		this.setEsVigente(esVigente);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemConsumoContratoRepository#listarEntregablesPorContrato()
	 */	
	public PgimItemConsumoDTOResultado(Long idItemConsumo, Long descIdTipoEntregable, 
			String descDeTipoEntregable, Long descIdSupervision, String descCoSupervision,
			String descDeTipoSupervision, Long descIdSubtipoSupervision, String descDeSubtipoSupervision,
			Long descIdMotivoSupervision, String descDeMotivoSupervision, Date descFeInicioSupervisionReal,
			Date descFeFinSupervisionReal, Long descIdAgenteSupervisado, String descCoDocumentoIdentidad,
			String descNoRazonSocial, BigDecimal descMoConsumoContrato, BigDecimal moItemConsumo, 
			Long descIdContrato, String descNuContrato, Long descIdDivisionSupervisora,
			String descNoDivisionSupervisora, String descCoUnidadMinera, String descNoUnidadMinera,
			String descNuExpedienteSiged, Long descIdTipoEstadioConsumo, String descNoTipoEstadioConsumo
			) {
		super();

		this.setIdItemConsumo(idItemConsumo);
		this.setDescIdTipoEntregable(descIdTipoEntregable);
		this.setDescDeTipoEntregable(descDeTipoEntregable);
		this.setDescIdSupervision(descIdSupervision);
		this.setDescCoSupervision(descCoSupervision);
		this.setDescDeTipoSupervision(descDeTipoSupervision);
		this.setDescIdSubtipoSupervision(descIdSubtipoSupervision);
		this.setDescDeSubtipoSupervision(descDeSubtipoSupervision);
		this.setDescIdMotivoSupervision(descIdMotivoSupervision);
		this.setDescDeMotivoSupervision(descDeMotivoSupervision);
		this.setDescFeInicioSupervisionReal(descFeInicioSupervisionReal);
		this.setDescFeFinSupervisionReal(descFeFinSupervisionReal);
		this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
		this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
		this.setDescNoRazonSocial(descNoRazonSocial);
		this.setDescMoConsumoContrato(descMoConsumoContrato);
		this.setMoItemConsumo(moItemConsumo);
		this.setDescIdContrato(descIdContrato);
		this.setDescNuContrato(descNuContrato);
		this.setDescIdDivisionSupervisora(descIdDivisionSupervisora);
		this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
		this.setDescCoUnidadMinera(descCoUnidadMinera);
		this.setDescNoUnidadMinera(descNoUnidadMinera);
		this.setDescNuExpedienteSiged(descNuExpedienteSiged);
		this.setDescIdTipoEstadioConsumo(descIdTipoEstadioConsumo);
		this.setDescNoTipoEstadioConsumo(descNoTipoEstadioConsumo);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemConsumoContratoRepository#listarEntregablesPorLiquidarPorContrato()
	 */	
	public PgimItemConsumoDTOResultado(Long idItemConsumo, BigDecimal moItemConsumo, BigDecimal moItemPenalidad, BigDecimal moItemSupervisionFallida, Long descIdTipoEntregable, 
										String descDeTipoEntregable, Long idTipoEstadioConsumo, String descNoTipoEstadioConsumo, 
									   Long descIdContrato, String descNuContrato, Long descIdSupervision, String descCoSupervision, Long descIdSubtipoSupervision, String descDeSubtipoSupervision,
									   Long descIdTipoSupervision, String descDeTipoSupervision, Long descIdMotivoSupervision, String descDeMotivoSupervision, String descCoUnidadMinera, String descNoUnidadMinera,
									   Long descIdDivisionSupervisora, String descNoDivisionSupervisora
			) {
		super();

		this.setIdItemConsumo(idItemConsumo);
		this.setMoItemConsumo(moItemConsumo);
		this.setMoItemPenalidad(moItemPenalidad);
		this.setMoItemSupervisionFallida(moItemSupervisionFallida);
		this.setDescIdTipoEntregable(descIdTipoEntregable);
		this.setDescDeTipoEntregable(descDeTipoEntregable);
		this.setIdTipoEstadioConsumo(idTipoEstadioConsumo);
		this.setDescNoTipoEstadioConsumo(descNoTipoEstadioConsumo);
		this.setDescIdContrato(descIdContrato);
		this.setDescNuContrato(descNuContrato);
		this.setDescIdSupervision(descIdSupervision);
		this.setDescCoSupervision(descCoSupervision);
		this.setDescIdSubtipoSupervision(descIdSubtipoSupervision);
		this.setDescDeSubtipoSupervision(descDeSubtipoSupervision);
		this.setDescIdTipoSupervision(descIdTipoSupervision);
		this.setDescDeTipoSupervision(descDeTipoSupervision);
		this.setDescIdMotivoSupervision(descIdMotivoSupervision);
		this.setDescDeMotivoSupervision(descDeMotivoSupervision);
		this.setDescCoUnidadMinera(descCoUnidadMinera);
		this.setDescNoUnidadMinera(descNoUnidadMinera);
		this.setDescIdDivisionSupervisora(descIdDivisionSupervisora);
		this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
		
	}
}
