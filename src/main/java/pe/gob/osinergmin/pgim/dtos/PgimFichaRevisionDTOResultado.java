package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimFichaRevisionDTOResultado extends PgimFichaRevisionDTO {

	/**
	 * 
	 * @see pe.gob.osinergmin.pgim.models.repository.FichaRevisionRepository#obtenerFichaRevisionPorId()
	 * @see pe.gob.osinergmin.pgim.models.repository.FichaRevisionRepository#obtenerListFichaObservacionNoSubsanadasPorInstanciaProc()
	 * @see pe.gob.osinergmin.pgim.models.repository.FichaRevisionRepository#obtenerFichaRevisionPorInstanciaProcess()
	 * 
	 * @param idFichaRevision
	 * @param idDocumento
	 * @param idTipoConformidad
	 * @param feRevisionFicha
	 * @param caDiasParaPresentacion
	 * @param feDesdeParaPresentacion
	 * @param fePresentacion
	 * @param caDiasDemoraCalculados
	 * @param flAplicaPenalidad
	 * @param caDiasDemoraEstablecidos
	 * @param cmPenalidad
	 * @param flObservacionEpp
	 * @param cmObservacionEpp
	 * @param caDiasPlazoPresentacion
	 * @param feHastaParaPresentacion
	 * @param feFirmaActaFiscaliza
	 * @param descFeEnvioDocumento
	 */
	public PgimFichaRevisionDTOResultado(Long idFichaRevision, Long idDocumento, Long idTipoConformidad,
			Date feRevisionFicha, Integer caDiasParaPresentacion, Date feDesdeParaPresentacion, 
			Date fePresentacion, Integer caDiasDemoraCalculados, String flAplicaPenalidad, 
			Integer caDiasDemoraEstablecidos, String cmPenalidad, String flObservacionEpp, 
			String cmObservacionEpp, Integer caDiasPlazoPresentacion, Date feHastaParaPresentacion, 
			Date feFirmaActaFiscaliza, Date descFeEnvioDocumento, String flEppAfiscalizado, String cmEppFiscalizado,
			String flEquiposDefectuosos, String cmEquiposDefectuosos, String flEqpMedicionDefectuosos, String cmEqpMedicionDefectuosos,
			String flSinEquipos, String cmSinEquipos, String flEqpCalibracionNvigente, String cmEqpCalibracionNvigente,
			String flSinInstrumentos, String cmSinInstrumentos, String flInsCalibracionNvigente, String cmInsCalibracionNvigente,
			String flAlterarFormatos, String cmAlterarFormatos, String flFrustrarFiscalizacion, String cmFrustrarFiscalizacion
			) {
		super();
		
		this.setIdFichaRevision(idFichaRevision);
		this.setIdDocumento(idDocumento);
		this.setIdTipoConformidad(idTipoConformidad);
		this.setFeRevisionFicha(feRevisionFicha);
		this.setCaDiasParaPresentacion(caDiasParaPresentacion);
		this.setFeDesdeParaPresentacion(feDesdeParaPresentacion);
		this.setFePresentacion(fePresentacion);
		this.setCaDiasDemoraCalculados(caDiasDemoraCalculados);
		this.setFlAplicaPenalidad(flAplicaPenalidad);
		this.setCaDiasDemoraEstablecidos(caDiasDemoraEstablecidos);
		this.setCmPenalidad(cmPenalidad);
		this.setFlObservacionEpp(flObservacionEpp);
		this.setCmObservacionEpp(cmObservacionEpp);
		this.setCaDiasPlazoPresentacion(caDiasPlazoPresentacion);
		this.setFeHastaParaPresentacion(feHastaParaPresentacion);
		this.setFeFirmaActaFiscaliza(feFirmaActaFiscaliza);
		this.setDescFeEnvioDocumento(descFeEnvioDocumento);
		this.setFlEppAfiscalizado(flEppAfiscalizado);
		this.setCmEppFiscalizado(cmEppFiscalizado);
		this.setFlEquiposDefectuosos(flEquiposDefectuosos);
		this.setCmEquiposDefectuosos(cmEquiposDefectuosos);
		this.setFlEqpMedicionDefectuosos(flEqpMedicionDefectuosos);
		this.setCmEqpMedicionDefectuosos(cmEqpMedicionDefectuosos);
		this.setFlSinEquipos(flSinEquipos);
		this.setCmSinEquipos(cmSinEquipos);
		this.setFlEqpCalibracionNvigente(flEqpCalibracionNvigente);
		this.setCmEqpCalibracionNvigente(cmEqpCalibracionNvigente);
		this.setFlSinInstrumentos(flSinInstrumentos);
		this.setCmSinInstrumentos(cmSinInstrumentos);
		this.setFlInsCalibracionNvigente(flInsCalibracionNvigente);
		this.setCmInsCalibracionNvigente(cmInsCalibracionNvigente);
		this.setFlAlterarFormatos(flAlterarFormatos);
		this.setCmAlterarFormatos(cmAlterarFormatos);
		this.setFlFrustrarFiscalizacion(flFrustrarFiscalizacion);
		this.setCmFrustrarFiscalizacion(cmFrustrarFiscalizacion);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.FichaRevisionRepository#obtenerFichaRevisionPorIdDocumento()
	 * @see pe.gob.osinergmin.pgim.models.repository.FichaRevisionRepository#obtenerListFichaConformidadPorInstanciaProc()
	 * 
	 * @param idFichaRevision
	 * @param idDocumento
	 * @param idTipoConformidad
	 * @param feRevisionFicha
	 * @param caDiasParaPresentacion
	 * @param feDesdeParaPresentacion
	 * @param fePresentacion
	 * @param caDiasDemoraCalculados
	 * @param flAplicaPenalidad
	 * @param caDiasDemoraEstablecidos
	 * @param cmPenalidad
	 * @param flObservacionEpp
	 * @param cmObservacionEpp
	 * @param caDiasPlazoPresentacion
	 * @param feHastaParaPresentacion
	 * @param feFirmaActaFiscaliza
	 * @param descFeEnvioDocumento
	 */
	public PgimFichaRevisionDTOResultado(Long idFichaRevision, Long idDocumento, Long idTipoConformidad,
			Date feRevisionFicha, Integer caDiasParaPresentacion, Date feDesdeParaPresentacion, 
			Date fePresentacion, Integer caDiasDemoraCalculados, String flAplicaPenalidad, 
			Integer caDiasDemoraEstablecidos, String cmPenalidad, String flObservacionEpp, 
			String cmObservacionEpp, Integer caDiasPlazoPresentacion, Date feHastaParaPresentacion, 
			Date feFirmaActaFiscaliza, Date descFeEnvioDocumento, String flEppAfiscalizado, String cmEppFiscalizado,
			String flEquiposDefectuosos, String cmEquiposDefectuosos, String flEqpMedicionDefectuosos, String cmEqpMedicionDefectuosos,
			String flSinEquipos, String cmSinEquipos, String flEqpCalibracionNvigente, String cmEqpCalibracionNvigente,
			String flSinInstrumentos, String cmSinInstrumentos, String flInsCalibracionNvigente, String cmInsCalibracionNvigente,
			String flAlterarFormatos, String cmAlterarFormatos, String flFrustrarFiscalizacion, String cmFrustrarFiscalizacion, String flOtrasPenalidades
			) {
		super();
		
		this.setIdFichaRevision(idFichaRevision);
		this.setIdDocumento(idDocumento);
		this.setIdTipoConformidad(idTipoConformidad);
		this.setFeRevisionFicha(feRevisionFicha);
		this.setCaDiasParaPresentacion(caDiasParaPresentacion);
		this.setFeDesdeParaPresentacion(feDesdeParaPresentacion);
		this.setFePresentacion(fePresentacion);
		this.setCaDiasDemoraCalculados(caDiasDemoraCalculados);
		this.setFlAplicaPenalidad(flAplicaPenalidad);
		this.setCaDiasDemoraEstablecidos(caDiasDemoraEstablecidos);
		this.setCmPenalidad(cmPenalidad);
		this.setFlObservacionEpp(flObservacionEpp);
		this.setCmObservacionEpp(cmObservacionEpp);
		this.setCaDiasPlazoPresentacion(caDiasPlazoPresentacion);
		this.setFeHastaParaPresentacion(feHastaParaPresentacion);
		this.setFeFirmaActaFiscaliza(feFirmaActaFiscaliza);
		this.setDescFeEnvioDocumento(descFeEnvioDocumento);
		this.setFlEppAfiscalizado(flEppAfiscalizado);
		this.setCmEppFiscalizado(cmEppFiscalizado);
		this.setFlEquiposDefectuosos(flEquiposDefectuosos);
		this.setCmEquiposDefectuosos(cmEquiposDefectuosos);
		this.setFlEqpMedicionDefectuosos(flEqpMedicionDefectuosos);
		this.setCmEqpMedicionDefectuosos(cmEqpMedicionDefectuosos);
		this.setFlSinEquipos(flSinEquipos);
		this.setCmSinEquipos(cmSinEquipos);
		this.setFlEqpCalibracionNvigente(flEqpCalibracionNvigente);
		this.setCmEqpCalibracionNvigente(cmEqpCalibracionNvigente);
		this.setFlSinInstrumentos(flSinInstrumentos);
		this.setCmSinInstrumentos(cmSinInstrumentos);
		this.setFlInsCalibracionNvigente(flInsCalibracionNvigente);
		this.setCmInsCalibracionNvigente(cmInsCalibracionNvigente);
		this.setFlAlterarFormatos(flAlterarFormatos);
		this.setCmAlterarFormatos(cmAlterarFormatos);
		this.setFlFrustrarFiscalizacion(flFrustrarFiscalizacion);
		this.setCmFrustrarFiscalizacion(cmFrustrarFiscalizacion);
		this.setDescFlOtrasPenalidades(flOtrasPenalidades);
	}
}
