package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimRankingUmFactorDTOResultado extends PgimRankingUmFactorDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmFactorRepository#listarRankingUmFactorPorRankingGrupoUmAndGrupoRiezgo()
	 * @param idRankingUmFactor
	 * @param idRankingUmGrupo
	 * @param idFactorRiesgo
	 * @param idCfgNivelRiesgo
	 * @param moCalificacion
	 * @param moPuntaje
	 * @param cmCalificacion
	 * @param descNuOrdenPrioridad
	 * @param descNoFactor
	 * @param descOrigen
	 * @param descIdCfgFactorRiesgo
	 * @param descNuVectorResultante
	 */
	public PgimRankingUmFactorDTOResultado(Long idRankingUmFactor, Long idRankingUmGrupo, 
			Long idFactorRiesgo, Long idCfgNivelRiesgo, BigDecimal moCalificacion, BigDecimal moPuntaje, 
			String cmCalificacion, Long descNuOrdenPrioridad, String descNoFactor, String descOrigen,
			Long descIdCfgFactorRiesgo, BigDecimal descNuVectorResultante) {
        super();
        this.setIdRankingUmFactor(idRankingUmFactor);
        this.setIdRankingUmGrupo(idRankingUmGrupo);
        this.setIdFactorRiesgo(idFactorRiesgo);
        this.setIdCfgNivelRiesgo(idCfgNivelRiesgo);
        this.setMoCalificacion(moCalificacion);
        this.setMoPuntaje(moPuntaje);
        this.setCmCalificacion(cmCalificacion);
        this.setDescNuOrdenPrioridad(descNuOrdenPrioridad);
        this.setDescNoFactor(descNoFactor);
        this.setDescOrigen(descOrigen);
        this.setDescIdCfgFactorRiesgo(descIdCfgFactorRiesgo);
        this.setDescNuVectorResultante(descNuVectorResultante);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmFactorRepository#obtenerRankingUMFactorPorId()
	 */
	
	public PgimRankingUmFactorDTOResultado(Long idRankingUmFactor, Long idRankingUmGrupo, 
			Long idFactorRiesgo) {
        super();
        this.setIdRankingUmFactor(idRankingUmFactor);
        this.setIdRankingUmGrupo(idRankingUmGrupo);
        this.setIdFactorRiesgo(idFactorRiesgo);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmFactorRepository#listarRankingUMFactorPorRankingUmGrupo()
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmFactorRepository#listarFactoresIncluidoPendientesPorIdGrupo
	 */
	
	public PgimRankingUmFactorDTOResultado(Long idRankingUmFactor, Long idRankingUmGrupo, 
			Long idFactorRiesgo, Long idCfgNivelRiesgo, BigDecimal moCalificacion, BigDecimal moPuntaje,
			String cmCalificacion, String descNoFactor, String descDeFactor, String descDeEspecificacion
			) {
        super();
        this.setIdRankingUmFactor(idRankingUmFactor);
        this.setIdRankingUmGrupo(idRankingUmGrupo);
        this.setIdFactorRiesgo(idFactorRiesgo);
        this.setIdCfgNivelRiesgo(idCfgNivelRiesgo);
        this.setMoCalificacion(moCalificacion);
        this.setMoPuntaje(moPuntaje);
        this.setCmCalificacion(cmCalificacion);
        this.setDescNoFactor(descNoFactor);
        this.setDescDeFactor(descDeFactor);
        this.setDescDeEspecificacion(descDeEspecificacion);
    }
}