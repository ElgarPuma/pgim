package pe.gob.osinergmin.pgim.dtos;

public class PgimGrupoRiesgoDTOResultado extends PgimGrupoRiesgoDTO {

	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.GrupoRiesgoRepository#listarPgimGrupoRiesgoDTO()
	 * @param idGrupoRiesgo
	 * @param noGrupo
	 * @param deGrupo
	 */
	public PgimGrupoRiesgoDTOResultado(Long idGrupoRiesgo, String noGrupo, String deGrupo) {            
        super();
        
        this.setIdGrupoRiesgo(idGrupoRiesgo);
		this.setNoGrupo(noGrupo);
		this.setDeGrupo(deGrupo);
    }

}