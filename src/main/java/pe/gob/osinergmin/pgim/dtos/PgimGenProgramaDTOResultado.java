package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimGenProgramaDTOResultado extends PgimGenProgramaDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.GenProgramaRepository#validadExistenciaProgramaPropuesta()
	 * @param idGenPrograma
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
	 */
	public PgimGenProgramaDTOResultado(
			Long idGenPrograma, Long idProgramaSupervision, String flConforme,
			Date feGeneracion, Integer nuMaxFiscaAnualXuf,
			Integer nuMaxFiscaMensual, Integer nuEnero, Integer nuFebrero,
			Integer nuMarzo, Integer nuAbril, Integer nuMayo,
			Integer nuJuno, Integer nuJulio, Integer nuAgosto,
			Integer nuSeptiembre, Integer nuOctubre, Integer nuNoviembre,
			Integer nuDiciembre) {
		super();
		this.setIdGenPrograma(idGenPrograma);
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

	}

	public PgimGenProgramaDTOResultado(Long idGenPrograma, Long idProgramaSupervision, Long descIdUnidadMinera,
			BigDecimal descNuMes, String descNoUnidadMinera) {
		super();
		this.setIdGenPrograma(idGenPrograma);
		this.setIdProgramaSupervision(idProgramaSupervision);
		this.setDescIdUnidadMinera(descIdUnidadMinera);
		this.setDescNuMes(descNuMes);
		this.setDescNoUnidadMinera(descNoUnidadMinera);

	}

	public PgimGenProgramaDTOResultado(Long idGenPrograma, Long idProgramaSupervision, Long descIdRanking,
			String descNoRanking) {
		super();
		this.setIdGenPrograma(idGenPrograma);
		this.setIdProgramaSupervision(idProgramaSupervision);
		this.setDescIdRankingRiesgo(descIdRanking);
		this.setDescNoRankingRiesgo(descNoRanking);

	}

}
