package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimCfgGrupoRiesgoDTOResultado extends PgimCfgGrupoRiesgoDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgGrupoRiesgoRepository#listarCfgGrupoRiesgoPorConfiguracion()
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgGrupoRiesgoRepository#listarCfgGrupoRiesgoPorIdRankingUm()
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgGrupoRiesgoRepository#listarCfgGrupoRiesgoPorIdRankingSupervision()
	 * @param idCfgGrupoRiesgo
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @param noGrupoRiesgo
	 * @param deGrupoRiesgo
	 * @param pcFactorCorreccion
	 * @param nuRatioConsistencia
	 */
	public PgimCfgGrupoRiesgoDTOResultado(Long idCfgGrupoRiesgo, Long idConfiguraRiesgo, Long idGrupoRiesgo,
			String noGrupoRiesgo, String deGrupoRiesgo, BigDecimal pcFactorCorreccion, 
			BigDecimal nuRatioConsistencia) {
		super();
		this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
		this.setIdConfiguraRiesgo(idConfiguraRiesgo);
		this.setIdGrupoRiesgo(idGrupoRiesgo);
		this.setNoGrupoRiesgo(noGrupoRiesgo);
		this.setDeGrupoRiesgo(deGrupoRiesgo);
		this.setPcFactorCorreccion(pcFactorCorreccion);
		this.setNuRatioConsistencia(nuRatioConsistencia);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgGrupoRiesgoRepository#obtenerCalculoRatioConsistencia()
	 * @param idCfgGrupoRiesgo
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @param nuCalificacionGrupo
	 * @param nuIndiceConsistencia
	 * @param nuRatioConsistencia
	 * @param nuIndConsistenciaAleatoria
	 * @param nuNmax
	 */
	public PgimCfgGrupoRiesgoDTOResultado(Long idCfgGrupoRiesgo, Long idConfiguraRiesgo, Long idGrupoRiesgo,
			BigDecimal nuCalificacionGrupo, BigDecimal nuIndiceConsistencia, BigDecimal nuRatioConsistencia,
			BigDecimal nuIndConsistenciaAleatoria, BigDecimal nuNmax) {
		super();
		this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
		this.setIdConfiguraRiesgo(idConfiguraRiesgo);
		this.setIdGrupoRiesgo(idGrupoRiesgo);
		this.setNuCalificacionGrupo(nuCalificacionGrupo);
		this.setNuIndiceConsistencia(nuIndiceConsistencia);
		this.setNuRatioConsistencia(nuRatioConsistencia);
		this.setNuIndConsistenciaAleatoria(nuIndConsistenciaAleatoria);
		this.setNuNmax(nuNmax);
	}

	// PGIM-5007 - pjimenez
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgGrupoRiesgoRepository#listarCfgGrupoRiesgoPorConfiguracionyGrupo()
	 * @param idCfgGrupoRiesgo
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @param nuCalificacionGrupo
	 * @param nuIndiceConsistencia
	 * @param nuRatioConsistencia
	 * @param nuIndConsistenciaAleatoria
	 * @param nuNmax
	 * @param noGrupoRiesgo
	 */
	public PgimCfgGrupoRiesgoDTOResultado(Long idCfgGrupoRiesgo, Long idConfiguraRiesgo, Long idGrupoRiesgo,
			BigDecimal nuCalificacionGrupo, BigDecimal nuIndiceConsistencia, BigDecimal nuRatioConsistencia,
			BigDecimal nuIndConsistenciaAleatoria, BigDecimal nuNmax, String noGrupoRiesgo) {
		super();
		this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
		this.setIdConfiguraRiesgo(idConfiguraRiesgo);
		this.setIdGrupoRiesgo(idGrupoRiesgo);
		this.setNuCalificacionGrupo(nuCalificacionGrupo);
		this.setNuIndiceConsistencia(nuIndiceConsistencia);
		this.setNuRatioConsistencia(nuRatioConsistencia);
		this.setNuIndConsistenciaAleatoria(nuIndConsistenciaAleatoria);
		this.setNuNmax(nuNmax);
		this.setNoGrupoRiesgo(noGrupoRiesgo);
	}

	// PGIM-5008 - pjimenez
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgGrupoRiesgoRepository#obtenerCfgGrupoRiesgoPorId()
	 * @param idCfgGrupoRiesgo
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @param nuCalificacionGrupo
	 * @param nuIndiceConsistencia
	 * @param nuRatioConsistencia
	 * @param nuIndConsistenciaAleatoria
	 * @param nuNmax
	 * @param noGrupoRiesgo
	 * @param deGrupoRiesgo
	 * @param pcFactorCorreccion
	 */
	public PgimCfgGrupoRiesgoDTOResultado(Long idCfgGrupoRiesgo, Long idConfiguraRiesgo, Long idGrupoRiesgo,
			BigDecimal nuCalificacionGrupo, BigDecimal nuIndiceConsistencia, BigDecimal nuRatioConsistencia,
			BigDecimal nuIndConsistenciaAleatoria, BigDecimal nuNmax, String noGrupoRiesgo, String deGrupoRiesgo,
			BigDecimal pcFactorCorreccion) {
		super();
		this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
		this.setIdConfiguraRiesgo(idConfiguraRiesgo);
		this.setIdGrupoRiesgo(idGrupoRiesgo);
		this.setNuCalificacionGrupo(nuCalificacionGrupo);
		this.setNuIndiceConsistencia(nuIndiceConsistencia);
		this.setNuRatioConsistencia(nuRatioConsistencia);
		this.setNuIndConsistenciaAleatoria(nuIndConsistenciaAleatoria);
		this.setNuNmax(nuNmax);
		this.setNoGrupoRiesgo(noGrupoRiesgo);
		this.setDeGrupoRiesgo(deGrupoRiesgo);
		this.setPcFactorCorreccion(pcFactorCorreccion);
	}
}