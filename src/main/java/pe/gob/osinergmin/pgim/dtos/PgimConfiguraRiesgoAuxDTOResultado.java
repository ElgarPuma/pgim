package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimConfiguraRiesgoAuxDTOResultado extends PgimConfiguraRiesgoAuxDTO {

	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoAuxRepository#listarConfiguraRiesgoAux()
	 * @param idConfiguraRiesgoAux
	 * @param idConfiguraRiesgoPadre
	 * @param idEspecialidad
	 * @param noEspecialidad
	 * @param idTipoEstadoConfiguracion
	 * @param noValorParametro
	 * @param noConfiguracion
	 * @param deConfiguracion
	 * @param feConfiguracion
	 * @param feConfiguracionAnio
	 * @param idTipoConfiguracionRiesgo
	 * @param noTipoConfiguracionRiesgo
	 */
	public PgimConfiguraRiesgoAuxDTOResultado(Long idConfiguraRiesgoAux, 
			Long idConfiguraRiesgoPadre, Long idEspecialidad, String noEspecialidad, Long idTipoEstadoConfiguracion,
			String noValorParametro, String noConfiguracion, String deConfiguracion, Date feConfiguracion, String feConfiguracionAnio,
			Long idTipoConfiguracionRiesgo, String noTipoConfiguracionRiesgo) {
        super();

        this.setIdConfiguraRiesgoAux(idConfiguraRiesgoAux);
        this.setIdConfiguraRiesgoPadre(idConfiguraRiesgoPadre);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setIdTipoEstadoConfiguracion(idTipoEstadoConfiguracion);
        this.setNoValorParametro(noValorParametro);
        this.setNoConfiguracion(noConfiguracion);
        this.setDeConfiguracion(deConfiguracion);
        this.setFeConfiguracion(feConfiguracion);
        this.setFeConfiguracionAnio(feConfiguracionAnio);
        this.setIdTipoConfiguracionRiesgo(idTipoConfiguracionRiesgo);
		this.setNoTipoConfiguracionRiesgo(noTipoConfiguracionRiesgo);
    }
}