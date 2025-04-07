package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PgimItemLiquidacionDTOResultado extends PgimItemLiquidacionDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemLiquidacionRepository#listarItemLiquidacion()
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemLiquidacionRepository#obtenerItemLiquidacionPorId()
	 * @param idItemLiquidacion
	 * @param idLiquidacion
	 * @param idItemConsumo
	 * @param idDocumento
	 * @param moPenalidadPlazo
	 * @param moPenalidadReincidencia
	 * @param moPenalidadSinEpp
	 * @param moPenalidadRemplazoPersona
	 */
	public PgimItemLiquidacionDTOResultado(Long idItemLiquidacion, Long idLiquidacion, Long idItemConsumo,
			Long idDocumento, BigDecimal moPenalidadPlazo, BigDecimal moPenalidadReincidencia,
			BigDecimal moPenalidadSinEpp,
			BigDecimal moPenalidadEppAFiscalizado, BigDecimal moPenalidadEqpDefectuosos,
			BigDecimal moPenalidadEqpMedicionDefectuosos, BigDecimal moPenalidadSinEquipos,
			BigDecimal moPenalidadEqpCalibrNvigente, BigDecimal moPenalidadSinInstrumentos,
			BigDecimal moPenalidadInstrCalibrNvigente, BigDecimal moPenalidadAlterarFormatos,
			BigDecimal moPenalidadFrustrarFiscalizacion
			) {
		super();

		this.setIdItemLiquidacion(idItemLiquidacion);
		this.setIdLiquidacion(idLiquidacion);
		this.setIdItemConsumo(idItemConsumo);
		this.setIdDocumento(idDocumento);
		this.setMoPenalidadPlazo(moPenalidadPlazo);
		this.setMoPenalidadReincidencia(moPenalidadReincidencia);
		this.setMoPenalidadSinEpp(moPenalidadSinEpp);
		this.setMoPenalidadEppAFiscalizado(moPenalidadEppAFiscalizado);
		this.setMoPenalidadEqpDefectuosos(moPenalidadEqpDefectuosos);
		this.setMoPenalidadEqpMedicionDefectuosos(moPenalidadEqpMedicionDefectuosos);
		this.setMoPenalidadSinEquipos(moPenalidadSinEquipos);
		this.setMoPenalidadEqpCalibrNvigente(moPenalidadEqpCalibrNvigente);
		this.setMoPenalidadSinInstrumentos(moPenalidadSinInstrumentos);
		this.setMoPenalidadInstrCalibrNvigente(moPenalidadInstrCalibrNvigente);
		this.setMoPenalidadAlterarFormatos(moPenalidadAlterarFormatos);
		this.setMoPenalidadFrustrarFiscalizacion(moPenalidadFrustrarFiscalizacion);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemLiquidacionRepository#listarItemLiquidacionActasInformes()
	 * @param descNoUnidadMinera
	 * @param descNoRazonSocial
	 * @param descCoSupervision
	 * @param descDeSubtipoSupervision
	 * @param descNoValorParametro
	 * @param descMoConsumoContrato
	 * @param descMoItemConsumo
	 * @param descIdDocumento
	 * @param idLiquidacion
	 * @param idItemLiquidacion
	 * @param descNuExpedienteSiged
	 * @param descIdContrato
	 * @param descFeInicioSupervisionReal
	 * @param descFeFinSupervisionReal
	 * @param descDeAsuntoDocumento
	 * @param descDivisionSupervision
	 * @param descIdInstanciaProceso
	 * @param descDeMotivoSupervision
	 * @param descTotalPenalidades
	 * @param moPenalidadPlazo
	 * @param moPenalidadReincidencia
	 * @param moPenalidadSinEpp
	 * @param moPenalidadRemplazoPersona
	 * @param descIdSupervision
	 */
	public PgimItemLiquidacionDTOResultado(String descNoUnidadMinera, String descNoRazonSocial,
			String descCoSupervision, String descDeSubtipoSupervision, String descNoValorParametro,
			BigDecimal descMoConsumoContrato, BigDecimal descMoItemConsumo, Long descIdDocumento, Long idLiquidacion,
			Long idItemLiquidacion, String descNuExpedienteSiged, Long descIdContrato, Date descFeInicioSupervisionReal,
			Date descFeFinSupervisionReal, String descDeAsuntoDocumento, String descDivisionSupervision,
			Long descIdInstanciaProceso, String descDeMotivoSupervision, BigDecimal descTotalPenalidades,
			BigDecimal moPenalidadPlazo, BigDecimal moPenalidadReincidencia, BigDecimal moPenalidadSinEpp,
			Long descIdSupervision, BigDecimal moPenalidadEppAFiscalizado, BigDecimal moPenalidadSinEquipos, 
			BigDecimal moPenalidadSinInstrumentos, BigDecimal moPenalidadEqpDefectuosos, BigDecimal moPenalidadEqpMedicionDefectuosos, 
			BigDecimal moPenalidadEqpCalibrNvigente, BigDecimal moPenalidadInstrCalibrNvigente, 
			BigDecimal descOtrasPenalidades, BigDecimal moPenalidadAlterarFormatos, BigDecimal moPenalidadFrustrarFiscalizacion, BigDecimal descSubTotalPenalidades, String descFlPreexistenteNpenalidades) {

		super();
		this.setDescNoUnidadMinera(descNoUnidadMinera);
		this.setDescNoRazonSocial(descNoRazonSocial);
		this.setDescCoSupervision(descCoSupervision);
		this.setDescDeSubtipoSupervision(descDeSubtipoSupervision);
		this.setDescNoValorParametro(descNoValorParametro);
		this.setDescMoConsumoContrato(descMoConsumoContrato);
		this.setDescMoItemConsumo(descMoItemConsumo);
		this.setDescIdDocumento(descIdDocumento);
		this.setIdLiquidacion(idLiquidacion);
		this.setIdItemLiquidacion(idItemLiquidacion);
		this.setDescNuExpedienteSiged(descNuExpedienteSiged);
		this.setDescIdContrato(descIdContrato);
		this.setDescFeInicioSupervisionReal(descFeInicioSupervisionReal);
		this.setDescFeFinSupervisionReal(descFeFinSupervisionReal);
		this.setDescDeAsuntoDocumento(descDeAsuntoDocumento);
		this.setDescDivisionSupervision(descDivisionSupervision);
		this.setDescIdInstanciaProceso(descIdInstanciaProceso);
		this.setDescDeMotivoSupervision(descDeMotivoSupervision);
		this.setDescTotalPenalidades(descTotalPenalidades);

		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
		this.setDescFeInicioSupervisionRealDesc(sdfr.format(descFeInicioSupervisionReal));
		this.setDescFeFinSupervisionRealDesc(sdfr.format(descFeFinSupervisionReal));

		// Calcular cantidad de días entre feInicioSupervision y feFinSupervision
		TimeZone timeZona = TimeZone.getTimeZone("America/Lima");
		Calendar calendarioIni = Calendar.getInstance(timeZona);
		calendarioIni.setTime(descFeInicioSupervisionReal);
		Calendar calendarioFin = Calendar.getInstance(timeZona);
		calendarioFin.setTime(descFeFinSupervisionReal);
		LocalDate fechaLocalInicial = LocalDate.of(calendarioIni.get(Calendar.YEAR),
				calendarioIni.get(Calendar.MONTH) + 1, calendarioIni.get(Calendar.DAY_OF_MONTH));
		LocalDate fechaLocalFinal = LocalDate.of(calendarioFin.get(Calendar.YEAR),
				calendarioFin.get(Calendar.MONTH) + 1, calendarioFin.get(Calendar.DAY_OF_MONTH));
		Long cantidadDiasEntre = ChronoUnit.DAYS.between(fechaLocalInicial, fechaLocalFinal);
		if (cantidadDiasEntre >= 0) {
			cantidadDiasEntre++;
		}

		this.setCantidadDias(cantidadDiasEntre);

		this.setMoPenalidadPlazo(moPenalidadPlazo);
		this.setMoPenalidadReincidencia(moPenalidadReincidencia);
		this.setMoPenalidadSinEpp(moPenalidadSinEpp);

		this.setDescIdSupervision(descIdSupervision);
		this.setMoPenalidadEppAFiscalizado(moPenalidadEppAFiscalizado);
		this.setMoPenalidadSinEquipos(moPenalidadSinEquipos);
		this.setMoPenalidadSinInstrumentos(moPenalidadSinInstrumentos);
		this.setMoPenalidadEqpDefectuosos(moPenalidadEqpDefectuosos);
		this.setMoPenalidadEqpMedicionDefectuosos(moPenalidadEqpMedicionDefectuosos);
		this.setMoPenalidadEqpCalibrNvigente(moPenalidadEqpCalibrNvigente);
		this.setMoPenalidadInstrCalibrNvigente(moPenalidadInstrCalibrNvigente);
		this.setDescOtrasPenalidades(descOtrasPenalidades);
		this.setMoPenalidadAlterarFormatos(moPenalidadAlterarFormatos);
		this.setMoPenalidadFrustrarFiscalizacion(moPenalidadFrustrarFiscalizacion);
		this.setDescSubTotalPenalidades(descSubTotalPenalidades);
		this.setDescFlPreexistenteNpenalidades(descFlPreexistenteNpenalidades);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemLiquidacionRepository#listarItemLiquidacionPorItemConsumo()
	 * 
	 * @param idItemLiquidacion
	 * @param idLiquidacion
	 * @param idItemConsumo
	 * @param idDocumento
	 */
	public PgimItemLiquidacionDTOResultado(Long idItemLiquidacion, Long idLiquidacion, 
			Long idItemConsumo, Long idDocumento, String descCoSupervision, String descDeTipoEntregable
			) {
		super();

		this.setIdItemLiquidacion(idItemLiquidacion);
		this.setIdLiquidacion(idLiquidacion);
		this.setIdItemConsumo(idItemConsumo);
		this.setIdDocumento(idDocumento);
		this.setDescCoSupervision(descCoSupervision);
		this.setDescDeTipoEntregable(descDeTipoEntregable);
	}
}
