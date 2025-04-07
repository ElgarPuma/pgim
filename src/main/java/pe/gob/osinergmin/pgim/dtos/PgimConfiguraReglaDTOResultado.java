package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimConfiguraReglaDTOResultado extends PgimConfiguraReglaDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraReglaRepository#obtenerReglasPorConfiguracion()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraReglaRepository#obtenerReglaPorId()
	 * @param idConfiguraRegla
	 * @param idConfiguraRiesgo
	 * @param idDivisionSupervisora
	 * @param idMetodoMinado
	 * @param idSituacion
	 * @param idTipoActividad
	 * @param idEstado
	 */
	public PgimConfiguraReglaDTOResultado(Long idConfiguraRegla, Long idConfiguraRiesgo, Long idDivisionSupervisora,
			Long idMetodoMinado, Long idSituacion, Long idTipoActividad, Long idEstado) {
		super();

		this.setIdConfiguraRegla(idConfiguraRegla);
		this.setIdConfiguraRiesgo(idConfiguraRiesgo);
		this.setIdDivisionSupervisora(idDivisionSupervisora);
		this.setIdMetodoMinado(idMetodoMinado);
		this.setIdSituacion(idSituacion);
		this.setIdTipoActividad(idTipoActividad);
		this.setIdEstado(idEstado);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraReglaRepository#listarReglasRiesgoPorConfiguracion()
	 * @param descNuOrden
	 * @param idConfiguraRegla
	 * @param idConfiguraRiesgo
	 * @param descNoDivisionSupervisora
	 * @param descNoMetodoMinado
	 * @param descNoSituacion
	 * @param idDivisionSupervisora
	 * @param idMetodoMinado
	 * @param idSituacion
	 * @param idTipoActividad
	 * @param descNoTipoActividad
	 * @param idEstado
	 * @param descNoEstado
	 */
	public PgimConfiguraReglaDTOResultado(Long descNuOrden, Long idConfiguraRegla, Long idConfiguraRiesgo,
			String descNoDivisionSupervisora, String descNoMetodoMinado, String descNoSituacion,
			Long idDivisionSupervisora, Long idMetodoMinado, Long idSituacion,
			Long idTipoActividad, String descNoTipoActividad, Long idEstado,
			String descNoEstado) {
		super();
		this.setDescNuOrden(descNuOrden);
		this.setIdConfiguraRegla(idConfiguraRegla);
		this.setIdConfiguraRiesgo(idConfiguraRiesgo);
		this.setIdDivisionSupervisora(idDivisionSupervisora);
		this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
		this.setIdMetodoMinado(idMetodoMinado);
		this.setDescNoMetodoMinado(descNoMetodoMinado);
		this.setIdSituacion(idSituacion);
		this.setDescNoSituacion(descNoSituacion);
		this.setIdTipoActividad(idTipoActividad);
		this.setDescNoTipoActividad(descNoTipoActividad);
		this.setIdEstado(idEstado);
		this.setDescNoEstado(descNoEstado);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraReglaRepository#obtenerTraslapesOtrasConfiguraciones()
	 * 
	 * @param idConfiguraRiesgo
	 * @param descNoConfiguracion
	 * @param idConfiguraRegla
	 * @param idDivisionSupervisora
	 * @param descNoDivisionSupervisora
	 * @param idMetodoMinado
	 * @param descNoMetodoMinado
	 * @param idSituacion
	 * @param descNoSituacion
	 * @param idEstado
	 * @param descNoEstado
	 * @param idTipoActividad
	 * @param descNoTipoActividad
	 */
	public PgimConfiguraReglaDTOResultado(Long idConfiguraRiesgo, String descNoConfiguracion, Long idConfiguraRegla,
			Long idDivisionSupervisora, String descNoDivisionSupervisora, Long idMetodoMinado,
			String descNoMetodoMinado, Long idSituacion, String descNoSituacion, Long idEstado, String descNoEstado, Long idTipoActividad, String descNoTipoActividad ) {
		super();

		this.setIdConfiguraRiesgo(idConfiguraRiesgo);
		this.setDescNoConfiguracion(descNoConfiguracion);
		this.setIdConfiguraRegla(idConfiguraRegla);
		this.setIdDivisionSupervisora(idDivisionSupervisora);
		this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
		this.setIdMetodoMinado(idMetodoMinado);
		this.setDescNoMetodoMinado(descNoMetodoMinado);
		this.setIdSituacion(idSituacion);
		this.setDescNoSituacion(descNoSituacion);
		this.setIdEstado(idEstado);
		this.setDescNoEstado(descNoEstado);
		this.setIdTipoActividad(idTipoActividad);
		this.setDescNoTipoActividad(descNoTipoActividad);
	}

}
