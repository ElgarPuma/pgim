package pe.gob.osinergmin.pgim.dtos;

public class PgimTdrPlantillaDTOResultado extends PgimTdrPlantillaDTO{
	
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.TdrPlantillaRepository#obtenerTdrPlanilla()
	 * 
	 * @param idTdrPlantilla
	 * @param idConfiguracionBase
	 * @param deTdrObjetivoTexto
	 * @param deTdrAlcanceTexto
	 * @param deTdrMetodologiaTexto
	 * @param deTdrInformeSupervTexto
	 * @param deTdrHonorariosProf
	 */
	public PgimTdrPlantillaDTOResultado(Long idTdrPlantilla, Long idConfiguracionBase, String deTdrObjetivoTexto, String deTdrAlcanceTexto, 
			String deTdrMetodologiaTexto, String deTdrInformeSupervTexto, String deTdrHonorariosProf) {
		super();
		this.setIdTdrPlantilla(idTdrPlantilla); 
		this.setDeTdrObjetivoTexto(deTdrObjetivoTexto); 
		this.setDeTdrAlcanceTexto(deTdrAlcanceTexto); 
		this.setDeTdrMetodologiaTexto(deTdrMetodologiaTexto); 
		this.setDeTdrInformeSupervTexto(deTdrInformeSupervTexto); 
		this.setDeTdrHonorariosProf(deTdrHonorariosProf);
		this.setIdConfiguracionBase(idConfiguracionBase);
		
	}

	/**
	 *  @see pe.gob.osinergmin.pgim.models.repository.TdrPlantillaRepository#obtenerTdrBasePorId()
	 *  @see pe.gob.osinergmin.pgim.models.repository.TdrPlantillaRepository#listarTdrBase()
	 */
	public PgimTdrPlantillaDTOResultado(Long idTdrPlantilla,
			String deTdrObjetivoTexto, String deTdrAlcanceTexto, String deTdrMetodologiaTexto,
			String deTdrInformeSupervTexto, String deTdrHonorariosProf, Long idConfiguracionBase,
			Long descIdEspecialidad, String descNoEspecialidad, String descNoConfiguracionBase, 
			Long descIdTipoConfiguracionBase, String descDeConfiguracionBase) {
		super();
		this.setIdTdrPlantilla(idTdrPlantilla);
		this.setDeTdrObjetivoTexto(deTdrObjetivoTexto);
		this.setDeTdrAlcanceTexto(deTdrAlcanceTexto);
		this.setDeTdrMetodologiaTexto(deTdrMetodologiaTexto);
		this.setDeTdrInformeSupervTexto(deTdrInformeSupervTexto);
		this.setDeTdrHonorariosProf(deTdrHonorariosProf);
		this.setIdConfiguracionBase(idConfiguracionBase);
		this.setDescIdEspecialidad(descIdEspecialidad);
		this.setDescNoEspecialidad(descNoEspecialidad);
		this.setDescNoConfiguracionBase(descNoConfiguracionBase);
		this.setDescIdTipoConfiguracionBase(descIdTipoConfiguracionBase);
		this.setDescDeConfiguracionBase(descDeConfiguracionBase);
	}
	
}
