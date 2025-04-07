package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimRankingUmGrupoDTOResultado extends PgimRankingUmGrupoDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#obtenerUnidadMineraPorRankingGrupoUmAndGrupoRiezgo()
	 * @param descNoUnidadMinera
	 */
	public PgimRankingUmGrupoDTOResultado(String descNoUnidadMinera) {
        super();
        this.setDescNoUnidadMinera(descNoUnidadMinera);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#listarRankingUmGrupoPorRankingUm()
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#obtenerRankingUmGrupoPorId()
	 * @param idRankingUmGrupo
	 * @param idRankingUm
	 * @param idRankingSupervision
	 * @param idCfgGrupoRiesgo
	 * @param moPuntaje
	 * @param descIdGrupoRiesgo
	 */
	public PgimRankingUmGrupoDTOResultado(Long idRankingUmGrupo, Long idRankingUm, Long idRankingSupervision,
			Long idCfgGrupoRiesgo, BigDecimal moPuntaje, Long descIdGrupoRiesgo, Long descIdRankingRiesgo) {
        super();
        this.setIdRankingUmGrupo(idRankingUmGrupo); 
        this.setIdRankingUm(idRankingUm);
        this.setIdRankingSupervision(idRankingSupervision);
        this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
        this.setMoPuntaje(moPuntaje);
		this.setDescIdGrupoRiesgo(descIdGrupoRiesgo);
		this.setDescIdRankingRiesgo(descIdRankingRiesgo);
    }
		
	/**
	 * 
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#listarRankingUmGrupoPorSupervision()
	 * 
	 * @param idRankingUmGrupo
	 * @param idRankingUm
	 * @param idRankingSupervision
	 * @param idCfgGrupoRiesgo
	 * @param moPuntaje
	 * @param descNoGrupo
	 * @param descDeGrupo
	 * @param descIdGrupoRiesgo
	 * @param descNoGrupoRiesgo
	 * @param descDeGrupoRiesgo
	 * @param descPcFactorCorreccion
	 * @param flRegistrar
	 * @param flMaximo
	 * @param descIdConfiguracionRiesgo
	 * @param descNoConfiguracionRiesgo
	 */
	public PgimRankingUmGrupoDTOResultado(Long idRankingUmGrupo, Long idRankingUm, Long idRankingSupervision,
			Long idCfgGrupoRiesgo, BigDecimal moPuntaje, String descNoGrupo, String descDeGrupo,
			Long descIdGrupoRiesgo, String descNoGrupoRiesgo, String descDeGrupoRiesgo, 
			BigDecimal descPcFactorCorreccion, String flRegistrar, String flMaximo,
			Long descIdConfiguraRiesgo, String descNoConfiguracion) {
        super();
        this.setIdRankingUmGrupo(idRankingUmGrupo); 
        this.setIdRankingUm(idRankingUm);
        this.setIdRankingSupervision(idRankingSupervision);
        this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
        this.setMoPuntaje(moPuntaje);
		this.setDescNoGrupo(descNoGrupo);
        this.setDescDeGrupo(descDeGrupo);
        this.setDescIdGrupoRiesgo(descIdGrupoRiesgo);
		this.setDescNoGrupoRiesgo(descNoGrupoRiesgo);
		this.setDescDeGrupoRiesgo(descDeGrupoRiesgo);
		this.setDescPcFactorCorreccion(descPcFactorCorreccion);
		this.setFlRegistrar(flRegistrar);
		this.setFlMaximo(flMaximo);
		this.setDescIdConfiguraRiesgo(descIdConfiguraRiesgo);
		this.setDescNoConfiguracion(descNoConfiguracion);
    }

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#listarRankingUmGrupoPorRankingSupervision()
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#listarRankingUmGrupoPorSupervision()
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#listarRankingUmGrupoPorIdRankingSuper()
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#listarRankingUmGrupoPorIdRankingSuperTodos()
	 * @see pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository#listarRankingUmGrupoPorRankingUm()
	 * @param idRankingUmGrupo
	 * @param idRankingUm
	 * @param idRankingSupervision
	 * @param idCfgGrupoRiesgo
	 * @param moPuntaje
	 * @param descNoGrupo
	 * @param descDeGrupo
	 * @param descIdGrupoRiesgo
	 * @param descNoGrupoRiesgo
	 * @param descDeGrupoRiesgo
	 * @param flRegistrar
	 * @param flMaximo
	 */
	public PgimRankingUmGrupoDTOResultado(Long idRankingUmGrupo, Long idRankingUm, Long idRankingSupervision,
			Long idCfgGrupoRiesgo, BigDecimal moPuntaje, String descNoGrupo, String descDeGrupo,
			Long descIdGrupoRiesgo, String descNoGrupoRiesgo, String descDeGrupoRiesgo, 
			BigDecimal descPcFactorCorreccion, String flRegistrar, String flMaximo, Long descIdRankingRiesgo) {
        super();
        this.setIdRankingUmGrupo(idRankingUmGrupo); 
        this.setIdRankingUm(idRankingUm);
        this.setIdRankingSupervision(idRankingSupervision);
        this.setIdCfgGrupoRiesgo(idCfgGrupoRiesgo);
        this.setMoPuntaje(moPuntaje);
		this.setDescNoGrupo(descNoGrupo);
        this.setDescDeGrupo(descDeGrupo);
        this.setDescIdGrupoRiesgo(descIdGrupoRiesgo);
		this.setDescNoGrupoRiesgo(descNoGrupoRiesgo);
		this.setDescDeGrupoRiesgo(descDeGrupoRiesgo);
		this.setDescPcFactorCorreccion(descPcFactorCorreccion);
		this.setFlRegistrar(flRegistrar);
		this.setFlMaximo(flMaximo);
		this.setDescIdRankingRiesgo(descIdRankingRiesgo);
    }
}