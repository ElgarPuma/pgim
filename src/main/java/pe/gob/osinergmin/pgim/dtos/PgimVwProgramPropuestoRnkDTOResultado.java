package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimVwProgramPropuestoRnkDTOResultado extends PgimVwProgramPropuestoRnkDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.VwProgramPropuestoRnkRepository#obtenerProgramaPropuesta()
	 * @see pe.gob.osinergmin.pgim.models.repository.VwProgramPropuestoRnkRepository#obtenerProgramaPropuestaNoConforme()
	 * @param idPlanSupervisionAux
	 * @param idProgramaSupervision
	 * @param flConforme
	 * @param feGeneracion
	 * @param nuMaxFiscaAnualXuf
	 * @param nuMaxFiscaMensual
	 * @param nuEnero
	 * @param nuFebrero
	 * @param nuMarzo
	 * @param nuAbril
	 * @param nuMayo
	 * @param nuJuno
	 * @param nuJulio
	 * @param nuAgosto
	 * @param nuSeptiembre
	 * @param nuOctubre
	 * @param nuNoviembre
	 * @param nuDiciembre
	 * @param nuOrden
	 * @param idGenPrgRanking
	 * @param idRankingRiesgo
	 * @param noRanking
	 * @param moPuntajeTotalRiesgo
	 * @param moPuntajeProporcion
	 * @param moProporcionEntera
	 * @param caVisitas
	 * @param idRankingUmPr
	 * @param idUnidadMineraPr
	 * @param noUnidadMineraPr
	 * @param enero
	 * @param febrero
	 * @param marzo
	 * @param abril
	 * @param mayo
	 * @param juno
	 * @param julio
	 * @param agosto
	 * @param septiembre
	 * @param octubre
	 * @param noviembre
	 * @param diciembre
	 * @param moPuntajeRiesgo
	 * @param deMensajePrgMes
	 */
	public PgimVwProgramPropuestoRnkDTOResultado(
			Long idPlanSupervisionAux, Long idProgramaSupervision, String flConforme,
			Date feGeneracion, Integer nuMaxFiscaAnualXuf, Integer nuMaxFiscaMensual, Integer nuEnero, Integer nuFebrero,
			Integer nuMarzo, Integer nuAbril, Integer nuMayo, Integer nuJuno, Integer nuJulio, Integer nuAgosto,
			Integer nuSeptiembre, Integer nuOctubre, Integer nuNoviembre, Integer nuDiciembre, Integer nuOrden,
			Long idGenPrgRanking, Long idRankingRiesgo, String noRanking, BigDecimal moPuntajeTotalRiesgo,
			BigDecimal moPuntajeProporcion, Integer moProporcionEntera, Integer caVisitas, Long idRankingUmPr,
			Long idUnidadMineraPr, String noUnidadMineraPr, Integer enero, Integer febrero, Integer marzo, Integer abril,
			Integer mayo, Integer juno, Integer julio, Integer agosto, Integer septiembre, Integer octubre, Integer noviembre,
			Integer diciembre, BigDecimal moPuntajeRiesgo, String deMensajePrgMes) {
		super();

		this.setIdGenPrograma(idPlanSupervisionAux);
		this.setIdProgramaSupervision(idProgramaSupervision);
		this.setFlConforme(flConforme);
		this.setFeGeneracion(feGeneracion);
		this.setNuMaxFiscaAnualXuf(nuMaxFiscaAnualXuf);
		this.setNuMaxFiscaMensual(nuMaxFiscaMensual);
		this.setNuEnero(nuEnero);
		this.setNuFebrero(nuFebrero);
		this.setNuMarzo(nuMarzo);
		this.setNuAbril(nuAbril);
		this.setNuMayo(nuMayo);
		this.setNuJuno(nuJuno);
		this.setNuJulio(nuJulio);
		this.setNuAgosto(nuAgosto);
		this.setNuSeptiembre(nuSeptiembre);
		this.setNuOctubre(nuOctubre);
		this.setNuNoviembre(nuNoviembre);
		this.setNuDiciembre(nuDiciembre);
		this.setNuOrden(nuOrden);
		this.setIdGenPrgRanking(idGenPrgRanking);
		this.setIdRankingRiesgo(idRankingRiesgo);
		this.setNoRanking(noRanking);
		this.setMoPuntajeTotalRiesgo(moPuntajeTotalRiesgo);
		this.setMoPuntajeProporcion(moPuntajeProporcion);
		this.setMoProporcionEntera(moProporcionEntera);
		this.setCaVisitas(caVisitas);
		this.setIdRankingUm(idRankingUmPr);
		this.setIdUnidadMinera(idUnidadMineraPr);
		this.setNoUnidadMinera(noUnidadMineraPr);
		this.setEnero(enero);
		this.setFebrero(febrero);
		this.setMarzo(marzo);
		this.setAbril(abril);
		this.setMayo(mayo);
		this.setJuno(juno);
		this.setJulio(julio);
		this.setAgosto(agosto);
		this.setSeptiembre(septiembre);
		this.setOctubre(octubre);
		this.setNoviembre(noviembre);
		this.setDiciembre(diciembre);
		this.setMoPuntajeRiesgo(moPuntajeRiesgo);
		this.setDeMensajePrgMes(deMensajePrgMes);

	}

}
