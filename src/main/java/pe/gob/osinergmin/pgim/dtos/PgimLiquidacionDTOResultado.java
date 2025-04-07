package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimLiquidacionDTOResultado extends PgimLiquidacionDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository#obtenerLiquidacionPorId()
	 * @param idLiquidacion
	 * @param idContrato
	 * @param descNuContrato
	 * @param idTipoEntregable
	 * @param descNoValorParametro
	 * @param idInstanciaProceso
	 * @param nuLiquidacion
	 * @param descNoRazonSocial
	 * @param descNuExpedienteSiged
	 * @param descNuExpedienteSigedContrato
	 * @param descDeDivisionSupervisora
	 * @param descDeTipoSupervision
	 * @param descDeSubtipoSupervision
	 * @param descDeMotivoSupervision
	 */
	public PgimLiquidacionDTOResultado(Long idLiquidacion, Long idContrato, String descNuContrato,
			Long idTipoEntregable, String descNoValorParametro, Long idInstanciaProceso, String nuLiquidacion,
			String descNoRazonSocial, String descNuExpedienteSiged, String descNuExpedienteSigedContrato,
			String descDeDivisionSupervisora, String descDeTipoSupervision, String descDeSubtipoSupervision,
			String descDeMotivoSupervision, String flPenalidadReemplazoPersona, BigDecimal moPenalidadReemplazoPersona) {
		super();

		this.setIdLiquidacion(idLiquidacion);
		this.setIdContrato(idContrato);
		this.setDescNuContrato(descNuContrato);
		this.setIdTipoEntregable(idTipoEntregable);
		this.setDescNoValorParametro(descNoValorParametro);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNuLiquidacion(nuLiquidacion);
		this.setDescNoRazonSocial(descNoRazonSocial);
		this.setDescNuExpedienteSiged(descNuExpedienteSiged);
		this.setDescNuExpedienteSigedContrato(descNuExpedienteSigedContrato);

		this.setDescDeDivisionSupervisora(descDeDivisionSupervisora);
		this.setDescDeTipoSupervision(descDeTipoSupervision);
		this.setDescDeSubtipoSupervision(descDeSubtipoSupervision);
		this.setDescDeMotivoSupervision(descDeMotivoSupervision);
		this.setFlPenalidadReemplazoPersona(flPenalidadReemplazoPersona);
		this.setMoPenalidadReemplazoPersona(moPenalidadReemplazoPersona);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository#validarPenalidadPorEspecialidad()
	 * @param idLiquidacion
	 * @param idContrato
	 * @param descNuContrato
	 * @param idTipoEntregable
	 * @param idInstanciaProceso
	 * @param nuLiquidacion
	 * @param descNoRazonSocial
	 * @param descNuExpedienteSiged
	 */
	public PgimLiquidacionDTOResultado(Long idLiquidacion, Long idContrato, Long descIdEspecialidad,
			String descNuContrato, Long idTipoEntregable, String descNoValorParametro, Long idInstanciaProceso,
			String nuLiquidacion, String descNoRazonSocial, String descNuExpedienteSiged) {
		super();

		this.setIdLiquidacion(idLiquidacion);
		this.setIdContrato(idContrato);
		this.setDescIdEspecialidad(descIdEspecialidad);
		this.setDescNuContrato(descNuContrato);
		this.setIdTipoEntregable(idTipoEntregable);
		this.setDescNoValorParametro(descNoValorParametro);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNuLiquidacion(nuLiquidacion);
		this.setDescNoRazonSocial(descNoRazonSocial);
		this.setDescNuExpedienteSiged(descNuExpedienteSiged);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository#listarLiquidaciones()
	 * @param idLiquidacion
	 * @param idContrato
	 * @param idTipoEntregable
	 * @param idInstanciaProceso
	 * @param nuLiquidacion
	 * @param descNuContrato
	 * @param descNoValorParametro
	 * @param descNoRazonSocial
	 * @param descNuExpedienteSiged
	 */
	public PgimLiquidacionDTOResultado(Long idLiquidacion, Long idContrato, Long idTipoEntregable,
			Long idInstanciaProceso, String nuLiquidacion, String descNuContrato, String descNoValorParametro,
			String descNoRazonSocial, String descNuExpedienteSiged) {
		super();

		this.setIdLiquidacion(idLiquidacion);
		this.setIdContrato(idContrato);
		this.setIdTipoEntregable(idTipoEntregable);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNuLiquidacion(nuLiquidacion);
		this.setDescNuContrato(descNuContrato);
		this.setDescNoValorParametro(descNoValorParametro);
		this.setDescNoRazonSocial(descNoRazonSocial);
		this.setDescNuExpedienteSiged(descNuExpedienteSiged);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository#obtenerActasPresentadasPendienteLiquidar()
	 * @param descNoUnidadMinera
	 * @param descNoRazonSocial
	 * @param descCoSupervision
	 * @param descDeSubtipoSupervision
	 * @param descNoValorParametro
	 * @param descMoConsumoContrato
	 * @param descFeOrigenDocumento
	 * @param descFeInstanciaPaso
	 * @param descMoItemConsumo
	 * @param descIdDocumento
	 * @params descIdItemConsumo
	 * @params idLiquidacion
	 * @params descIdSupervision
	 * @params descCoDocumentoSiged
	 * @params descNuExpedienteSiged
	 */
	public PgimLiquidacionDTOResultado(String descNoUnidadMinera, String descNoRazonSocial, String descCoSupervision,
			String descDeSubtipoSupervision, String descNoValorParametro, BigDecimal descMoConsumoContrato,
			Date descFeOrigenDocumento, Date descFeInstanciaPaso, BigDecimal descMoItemConsumo, Long descIdDocumento,
			Long descIdItemConsumo, Long idLiquidacion, Long descIdSupervision, Long descCoDocumentoSiged,
			String descNuExpedienteSiged, String descDeAsuntoDocumento) {
		super();
		this.setDescNoUnidadMinera(descNoUnidadMinera);
		this.setDescNoRazonSocial(descNoRazonSocial);
		this.setDescCoSupervision(descCoSupervision);
		this.setDescDeSubtipoSupervision(descDeSubtipoSupervision);
		this.setDescNoValorParametro(descNoValorParametro);
		this.setDescMoConsumoContrato(descMoConsumoContrato);
		this.setDescFeOrigenDocumento(descFeOrigenDocumento);
		this.setDescFeInstanciaPaso(descFeInstanciaPaso);
		this.setDescMoItemConsumo(descMoItemConsumo);
		this.setDescIdDocumento(descIdDocumento);
		this.setDescIdItemConsumo(descIdItemConsumo);
		this.setIdLiquidacion(idLiquidacion);
		this.setDescIdSupervision(descIdSupervision);
		this.setDescCoDocumentoSiged(descCoDocumentoSiged);
		this.setDescNuExpedienteSiged(descNuExpedienteSiged);
		this.setDescDeAsuntoDocumento(descDeAsuntoDocumento);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository#listarPorNumeroLiquidacion()
	 * @param idLiquidacion
	 * @param nuLiquidacion
	 * @param descNuContrato
	 */
	public PgimLiquidacionDTOResultado(Long idLiquidacion, String nuLiquidacion, String descNuContrato) {
		super();
		this.setIdLiquidacion(idLiquidacion);
		this.setNuLiquidacion(nuLiquidacion);
		this.setDescNuContrato(descNuContrato);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository#obtenerLiquidacionPorIdLiquidacion()
	 * @param idLiquidacion
	 * @param idContrato
	 * @param idTipoEntregable
	 * @param idInstanciaProceso
	 * @param nuLiquidacion
	 * @param descIdSupervision
	 */
	public PgimLiquidacionDTOResultado(Long idLiquidacion, Long idContrato, Long idTipoEntregable,
			Long idInstanciaProceso, String nuLiquidacion, Long descIdSupervision) {
		super();

		this.setIdLiquidacion(idLiquidacion);
		this.setIdContrato(idContrato);
		this.setIdTipoEntregable(idTipoEntregable);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNuLiquidacion(nuLiquidacion);
		this.setDescIdSupervision(descIdSupervision);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository#listarLiquidacionesContrato()
	 * @param idLiquidacion
	 * @param idContrato
	 * @param idTipoEntregable
	 * @param idInstanciaProceso
	 * @param nuLiquidacion
	 * @param descNuContrato
	 * @param descNoValorParametro
	 * @param descNoRazonSocial
	 * @param descNuExpedienteSiged
	 */
	public PgimLiquidacionDTOResultado(Long idLiquidacion, Long idContrato, Long idTipoEntregable,
			Long idInstanciaProceso, String nuLiquidacion, String descNoValorParametro) {
		super();

		this.setIdLiquidacion(idLiquidacion);
		// this.setDescNumOrdenLiquidacion(descNumOrdenLiquidacion);
		this.setIdContrato(idContrato);
		this.setIdTipoEntregable(idTipoEntregable);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNuLiquidacion(nuLiquidacion);
		this.setDescNoValorParametro(descNoValorParametro);
	}
}
