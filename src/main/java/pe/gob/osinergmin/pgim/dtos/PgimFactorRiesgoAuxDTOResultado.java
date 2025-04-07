package pe.gob.osinergmin.pgim.dtos;

public class PgimFactorRiesgoAuxDTOResultado extends PgimFactorRiesgoAuxDTO{

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.FactorRiesgoAuxRepository#listarFactorRiesgoAux()
	 * @param idFactorRiesgoAux
	 * @param idGrupoRiesgo
	 * @param idEspecialidad
	 * @param idTipoOrigenDatoRiesgo
	 * @param noFactor
	 * @param deFactor
	 */
	public PgimFactorRiesgoAuxDTOResultado(Long idFactorRiesgoAux, Long idGrupoRiesgo, Long idEspecialidad, 
		  Long idTipoOrigenDatoRiesgo, String noFactor, String deFactor) {
		super();
		this.setIdFactorRiesgoAux(idFactorRiesgoAux);
		this.setIdGrupoRiesgo(idGrupoRiesgo);
		this.setIdEspecialidad(idEspecialidad);
		this.setIdTipoOrigenDatoRiesgo(idTipoOrigenDatoRiesgo);
		this.setNoFactor(noFactor);
		this.setDeFactor(deFactor);
	}
}