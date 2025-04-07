package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimCfgFactorRiesgoDTOResultado extends PgimCfgFactorRiesgoDTO {

	/**
* @see pe.gob.osinergmin.pgim.models.repository.CfgFactorRiesgoRepository#listarCfgFactorRiesgoPorConfiguracion()
	 * @param idCfgFactorRiesgo
	 * @param idCfgGrupoRiesgo
	 * @param idFactorRiesgo
	 * @param flAfectadoGestion
	 */
	public PgimCfgFactorRiesgoDTOResultado(Long idCfgFactorRiesgo, Long idCfgGrupoRiesgo, Long idFactorRiesgo,
			String flAfectadoGestion) {
		super();
		this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
		this.setIdCfgFactorRiesgo(idCfgFactorRiesgo);
		this.setIdFactorRiesgo(idFactorRiesgo);
		this.setFlAfectadoGestion(flAfectadoGestion);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgFactorRiesgoRepository#listarCfgFactorTecnicoGestion()
	 * @param idCfgFactorRiesgo
	 * @param idCfgGrupoRiesgo
	 * @param idFactorRiesgo
	 * @param nuOrdenPrioridad
	 * @param flAfectadoGestion
	 * @param nuSumatoriaColumnaMcp
	 * @param nuVectorResultante
	 * @param descNoFactor
	 * @param descNoValorParametro
	 */
	public PgimCfgFactorRiesgoDTOResultado(Long idCfgFactorRiesgo, Long idCfgGrupoRiesgo, Long idFactorRiesgo,
			Long nuOrdenPrioridad,
			String flAfectadoGestion, BigDecimal nuSumatoriaColumnaMcp, BigDecimal nuVectorResultante,
			String descNoFactor,
			String descNoValorParametro) {
		super();
		this.setIdCfgFactorRiesgo(idCfgFactorRiesgo);
		this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
		this.setIdFactorRiesgo(idFactorRiesgo);
		this.setNuOrdenPrioridad(nuOrdenPrioridad);
		this.setFlAfectadoGestion(flAfectadoGestion);
		this.setNuSumatoriaColumnaMcp(nuSumatoriaColumnaMcp);
		this.setNuVectorResultante(nuVectorResultante);
		this.setDescNoFactor(descNoFactor);
		this.setDescNoValorParametro(descNoValorParametro);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgFactorRiesgoRepository#obtenerCfgFactorTecnicoGestion()
	 * @param idCfgFactorRiesgo
	 * @param idCfgGrupoRiesgo
	 * @param idFactorRiesgo
	 * @param nuOrdenPrioridad
	 * @param flAfectadoGestion
	 * @param descNoFactor
	 * @param descDeFactor
	 * @param descIdValorParametro
	 */
	public PgimCfgFactorRiesgoDTOResultado(Long idCfgFactorRiesgo, Long idCfgGrupoRiesgo, Long idFactorRiesgo,
			Long nuOrdenPrioridad,
			String flAfectadoGestion, String descNoFactor, String descDeFactor, Long descIdValorParametro) {
		super();
		this.setIdCfgFactorRiesgo(idCfgFactorRiesgo);
		this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
		this.setIdFactorRiesgo(idFactorRiesgo);
		this.setNuOrdenPrioridad(nuOrdenPrioridad);
		this.setFlAfectadoGestion(flAfectadoGestion);
		this.setDescNoFactor(descNoFactor);
		this.setDescDeFactor(descDeFactor);
		this.setDescIdValorParametro(descIdValorParametro);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgFactorRiesgoRepository#obtenerCfgFactorRiesgoPorId()
	 * @param idCfgFactorRiesgo
	 * @param idCfgGrupoRiesgo
	 * @param idFactorRiesgo
	 * @param nuOrdenPrioridad
	 * @param flAfectadoGestion
	 * @param nuSumatoriaColumnaMcp
	 * @param nuVectorResultante
	 */
	public PgimCfgFactorRiesgoDTOResultado(Long idCfgFactorRiesgo, Long idCfgGrupoRiesgo, Long idFactorRiesgo,
			Long nuOrdenPrioridad,
			String flAfectadoGestion, BigDecimal nuSumatoriaColumnaMcp, BigDecimal nuVectorResultante) {
		super();
		this.setIdCfgFactorRiesgo(idCfgFactorRiesgo);
		this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
		this.setIdFactorRiesgo(idFactorRiesgo);
		this.setNuOrdenPrioridad(nuOrdenPrioridad);
		this.setFlAfectadoGestion(flAfectadoGestion);
		this.setNuSumatoriaColumnaMcp(nuSumatoriaColumnaMcp);
		this.setNuVectorResultante(nuVectorResultante);
	}
}