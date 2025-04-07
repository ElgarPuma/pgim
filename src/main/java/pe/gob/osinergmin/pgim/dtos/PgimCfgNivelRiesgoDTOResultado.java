package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimCfgNivelRiesgoDTOResultado extends PgimCfgNivelRiesgoDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgNivelRiesgoRepository#listarCfgNivelRiesgoPorGrupoRiezgo()
	 * @param idCfgNivelRiesgo
	 * @param idCfgFactorRiesgo
	 * @param idNivelRiesgo
	 * @param deEspecificacion
	 * @param descNuOrden
	 * @param descNoNivelRiesgo
	 * @param descNuValor
	 */
	public PgimCfgNivelRiesgoDTOResultado(Long idCfgNivelRiesgo, Long idCfgFactorRiesgo, Long idNivelRiesgo,
			String deEspecificacion, Long descNuOrden, String descNoNivelRiesgo, BigDecimal descNuValor) {
        super();
        this.setIdCfgNivelRiesgo(idCfgNivelRiesgo);
        this.setIdCfgFactorRiesgo(idCfgFactorRiesgo);
        this.setIdNivelRiesgo(idNivelRiesgo);
        this.setDeEspecificacion(deEspecificacion);
        this.setDescNuOrden(descNuOrden);
        this.setDescNoNivelRiesgo(descNoNivelRiesgo);
        this.setDescNuValor(descNuValor);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgNivelRiesgoRepository#listarCfgNivelRiesgoPorFactorRiesgo()
	 * @param idCfgNivelRiesgo
	 * @param idCfgFactorRiesgo
	 * @param idNivelRiesgo
	 * @param deEspecificacion
	 */
	public PgimCfgNivelRiesgoDTOResultado(Long idCfgNivelRiesgo, Long idCfgFactorRiesgo, Long idNivelRiesgo,
			String deEspecificacion) {
        super();
        this.setIdCfgNivelRiesgo(idCfgNivelRiesgo);
        this.setIdCfgFactorRiesgo(idCfgFactorRiesgo);
        this.setIdNivelRiesgo(idNivelRiesgo);
        this.setDeEspecificacion(deEspecificacion);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgNivelRiesgoRepository#listarCfgNivelRiesgoPorIdCfgFactorRiesgo()
	 * @param idCfgNivelRiesgo
	 * @param idCfgFactorRiesgo
	 * @param idNivelRiesgo
	 * @param descNoNivelRiesgo
	 * @param descNuOrden
	 * @param deEspecificacion
	 */
	public PgimCfgNivelRiesgoDTOResultado(Long idCfgNivelRiesgo, Long idCfgFactorRiesgo, Long idNivelRiesgo,
			String descNoNivelRiesgo, Long descNuOrden, String deEspecificacion) {
        super();
        this.setIdCfgNivelRiesgo(idCfgNivelRiesgo);
        this.setIdCfgFactorRiesgo(idCfgFactorRiesgo);
        this.setIdNivelRiesgo(idNivelRiesgo);
        this.setDescNoNivelRiesgo(descNoNivelRiesgo);
        this.setDescNuOrden(descNuOrden);
        this.setDeEspecificacion(deEspecificacion);
    }
}