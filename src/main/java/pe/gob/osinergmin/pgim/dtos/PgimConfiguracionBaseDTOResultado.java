package pe.gob.osinergmin.pgim.dtos;

public class PgimConfiguracionBaseDTOResultado extends PgimConfiguracionBaseDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#listar(org.springframework.data.domain.Pageable)
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#obtenerCfgBasePorId(Long)
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#existeCfg()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#listaCfgBasePorIdTipoCfgBase()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#listaCfgBasePorIdTipoCfgBaseTDR()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#listaCfgBasePorIdTipoCfgBaseCVERIF()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#listaCfgBasePorIdTipoCfgBaseSOLBD()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#listaCfgBasePorTipoCfgBaseYEspecialidad()
	 * @param idConfiguracionBase
	 * @param idTipoConfiguracionBase
	 * @param noCofiguracionBase
	 * @param deCofiguracionBase
	 * @param descNoTipoConfiguracionBase
	 * @param idEspecialidad
	 * @param descNoEspecialidad
	 * @param coClaveTextoTipoConfiguracionBase
	 */ 
	public PgimConfiguracionBaseDTOResultado(Long idConfiguracionBase, Long idTipoConfiguracionBase, String noCofiguracionBase, String deCofiguracionBase, 
		String descNoTipoConfiguracionBase, Long idEspecialidad, String descNoEspecialidad, String coClaveTextoTipoConfiguracionBase) {
		super();
		this.setIdConfiguracionBase(idConfiguracionBase);
		this.setIdTipoConfiguracionBase(idTipoConfiguracionBase);
		this.setNoCofiguracionBase(noCofiguracionBase);
		this.setDeCofiguracionBase(deCofiguracionBase);
		this.setDescNoTipoConfiguracionBase(descNoTipoConfiguracionBase);
		this.setIdEspecialidad(idEspecialidad);
		this.setDescNoEspecialidad(descNoEspecialidad);
		this.setDescCoClaveTextoTipoConfiguracionBase(coClaveTextoTipoConfiguracionBase);

	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#existeCfgBEnTDR()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#existeCfgBEnCVERIF()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository#existeCfgBEnSOLBD()
	 * @param idConfiguracionBase
	 * @param idTipoConfiguracionBase
	 * @param noCofiguracionBase
	 * @param deCofiguracionBase
	 * @param descNoTipoConfiguracionBase
	 * @param idEspecialidad
	 * @param descNoEspecialidad
	 * @param coClaveTextoTipoConfiguracionBase
	 * @param descNoObjetoHijo
	 */ 
	public PgimConfiguracionBaseDTOResultado(Long idConfiguracionBase, Long idTipoConfiguracionBase, String noCofiguracionBase, String deCofiguracionBase, 
		String descNoTipoConfiguracionBase, Long idEspecialidad, String descNoEspecialidad, String coClaveTextoTipoConfiguracionBase, String descNoObjetoHijo) {
		super();
		this.setIdConfiguracionBase(idConfiguracionBase);
		this.setIdTipoConfiguracionBase(idTipoConfiguracionBase);
		this.setNoCofiguracionBase(noCofiguracionBase);
		this.setDeCofiguracionBase(deCofiguracionBase);
		this.setDescNoTipoConfiguracionBase(descNoTipoConfiguracionBase);
		this.setIdEspecialidad(idEspecialidad);
		this.setDescNoEspecialidad(descNoEspecialidad);
		this.setDescCoClaveTextoTipoConfiguracionBase(coClaveTextoTipoConfiguracionBase);
		this.setDescNoObjetoHijo(descNoObjetoHijo);

	}
	

}
