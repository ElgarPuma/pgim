package pe.gob.osinergmin.pgim.dtos;

public class PgimFactorRiesgoDTOResultado extends PgimFactorRiesgoDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.FactorRiesgoRepository#obtenerFactorRiesgoPorId()
	 * @param idFactorRiesgo
	 * @param idGrupoRiesgo
	 * @param idEspecialidad
	 * @param idTipoOrigenDatoRiesgo
	 * @param noFactor
	 * @param deFactor
	 */
	public PgimFactorRiesgoDTOResultado(Long idFactorRiesgo, Long idGrupoRiesgo, Long idEspecialidad,
			Long idTipoOrigenDatoRiesgo, String noFactor, String deFactor) {
		super();
		this.setIdFactorRiesgo(idFactorRiesgo);
		this.setIdGrupoRiesgo(idGrupoRiesgo);
		this.setIdEspecialidad(idEspecialidad);
		this.setIdTipoOrigenDatoRiesgo(idTipoOrigenDatoRiesgo);
		this.setNoFactor(noFactor);
		this.setDeFactor(deFactor);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.FactorRiesgoAuxRepository#listarFactorRiesgoAux()
	 * @param idFactorRiesgo
	 * @param idGrupoRiesgo
	 * @param idEspecialidad
	 * @param idTipoOrigenDatoRiesgo
	 * @param noFactor
	 * @param deFactor
	 * @param descNoGrupo
	 * @param descNoEspecialidad
	 * @param descNoValorParametro
	 */
	public PgimFactorRiesgoDTOResultado(Long idFactorRiesgo, Long idGrupoRiesgo, Long idEspecialidad,
			Long idTipoOrigenDatoRiesgo, String noFactor, String deFactor, String descNoGrupo,
			String descNoEspecialidad, String descNoValorParametro) {
		super();
		this.setIdFactorRiesgo(idFactorRiesgo);
		this.setIdGrupoRiesgo(idGrupoRiesgo);
		this.setIdEspecialidad(idEspecialidad);
		this.setIdTipoOrigenDatoRiesgo(idTipoOrigenDatoRiesgo);
		this.setNoFactor(noFactor);
		this.setDeFactor(deFactor);
		this.setDescNoGrupo(descNoGrupo);
		this.setDescNoEspecialidad(descNoEspecialidad); 
		this.setDescNoValorParametro(descNoValorParametro);
	}

}