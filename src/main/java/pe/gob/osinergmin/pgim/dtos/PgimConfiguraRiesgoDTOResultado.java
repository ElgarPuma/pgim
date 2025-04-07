package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimConfiguraRiesgoDTOResultado extends PgimConfiguraRiesgoDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#obtenerConfiguracionesParaAsignacion()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#obtenerConfiguracionesParaSupervision()	
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#obtenerConfiguracionesPorId()	
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#obtenerConfiguracionesParaAsignacionPadres()
	 * @param idConfiguraRiesgo
	 * @param noConfiguracion
	 * @param idEspecialidad
	 * @param idTipoEstadoConfiguracion
	 * @param descNoValorParametro
	 * @param idTipoConfiguracionRiesgo
	 */
	public PgimConfiguraRiesgoDTOResultado(Long idConfiguraRiesgo
			, String noConfiguracion
			, Long idEspecialidad
			, Long idTipoEstadoConfiguracion
			, String descNoValorParametro
			, Long idTipoConfiguracionRiesgo) {
        super();

        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setNoConfiguracion(noConfiguracion);
        this.setIdEspecialidad(idEspecialidad);
        this.setIdTipoEstadoConfiguracion(idTipoEstadoConfiguracion);		
				this.setDescNoValorParametro(descNoValorParametro);
				this.setIdTipoConfiguracionRiesgo(idTipoConfiguracionRiesgo);		
    }

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#obtenerConfiguracionesParaAsignacion()
	 * @param idConfiguraRiesgo
	 * @param noConfiguracion
	 * @param idEspecialidad
	 * @param idTipoEstadoConfiguracion
	//  * @param flUsarParaSupervision
	 */
	public PgimConfiguraRiesgoDTOResultado(Long idConfiguraRiesgo
			, String noConfiguracion
			, Long idEspecialidad
			, Long idTipoEstadoConfiguracion
			, String descNoValorParametro
			, Long idTipoPeriodo
			, Long idTipoConfiguracionRiesgo
			, String descNoTipoPeriodo
			, String descNoTipoConfiguracionRiesgo
			, Long nuAnioPeriodo
			, String descCoTipoConfiguracionRiesgo
			) {
        super();

        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setNoConfiguracion(noConfiguracion);
        this.setIdEspecialidad(idEspecialidad);
        this.setIdTipoEstadoConfiguracion(idTipoEstadoConfiguracion);
				this.setDescNoValorParametro(descNoValorParametro);
				this.setIdTipoPeriodo(idTipoPeriodo);
				this.setIdTipoConfiguracionRiesgo(idTipoConfiguracionRiesgo);
				this.setDescNoTipoConfiguracionRiesgo(descNoTipoConfiguracionRiesgo);
				this.setDescNoTipoPeriodo(descNoTipoPeriodo);
				this.setNuAnioPeriodo(nuAnioPeriodo);
				this.setDescCoTipoConfiguracionRiesgo(descCoTipoConfiguracionRiesgo);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#listarConfiguraRiesgo()
	 * @param idConfiguraRiesgo
	 * @param idConfiguraRiesgoPadre
	 * @param idEspecialidad
	 * @param descNoEspecialidad
	 * @param idTipoEstadoConfiguracion
	 * @param descNoValorParametro
	 * @param noConfiguracion
	 * @param deConfiguracion
	 * @param feConfiguracion
	 * @param descFeConfiguracionAnio
	 * @param descNoPersonaAsignada
	 * @param descNoFaseActual
	 * @param descNoPasoActual
	 * @param idTipoConfiguracionRiesgo
	 * @param descCoTipoConfiguracionRiesgo
	 * @param descNoTipoConfiguracionRiesgo
	 * @param idTipoPeriodo
	 * @param descNoTipoPeriodo
	 * @param nuAnioPeriodo
	 * @param descUsuarioAsignado
	 * @param flLeido
	 * @param feLectura
	 */
	public PgimConfiguraRiesgoDTOResultado(
		    Long idConfiguraRiesgo, Long idConfiguraRiesgoPadre, Long idEspecialidad, 
			String descNoEspecialidad, Long idTipoEstadoConfiguracion, String descNoValorParametro, 
			String noConfiguracion, String deConfiguracion, Date feConfiguracion, 
			String descFeConfiguracionAnio, String descNoPersonaAsignada, String descNoFaseActual,
			String descNoPasoActual, Long idTipoConfiguracionRiesgo, String descCoTipoConfiguracionRiesgo,
			String descNoTipoConfiguracionRiesgo, Long idTipoPeriodo, String descNoTipoPeriodo,
			Long nuAnioPeriodo, String descUsuarioAsignado, String flLeido,
			Date feLectura, String noPersonaOrigen, String noUsuarioOrigen, 
			Date descFeInstanciaPaso, String descDeMensaje, String descFlPasoActivo,
			Long descIdInstanciaPaso
			) {
        super();

        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setIdConfiguraRiesgoPadre(idConfiguraRiesgoPadre);
        this.setIdEspecialidad(idEspecialidad);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setIdTipoEstadoConfiguracion(idTipoEstadoConfiguracion);
        this.setDescNoValorParametro(descNoValorParametro);
        this.setNoConfiguracion(noConfiguracion);
        this.setDeConfiguracion(deConfiguracion);
        this.setFeConfiguracion(feConfiguracion);
        this.setDescFeConfiguracionAnio(descFeConfiguracionAnio);
        this.setDescNoPersonaAsignada(descNoPersonaAsignada);
		this.setDescNoFaseActual(descNoFaseActual);
		this.setDescNoPasoActual(descNoPasoActual);
		this.setIdTipoConfiguracionRiesgo(idTipoConfiguracionRiesgo);
		this.setDescCoTipoConfiguracionRiesgo(descCoTipoConfiguracionRiesgo);
		this.setDescNoTipoConfiguracionRiesgo(descNoTipoConfiguracionRiesgo);
		this.setIdTipoPeriodo(idTipoPeriodo);
		this.setDescNoTipoPeriodo(descNoTipoPeriodo);
		this.setNuAnioPeriodo(nuAnioPeriodo);
		this.setDescUsuarioAsignado(descUsuarioAsignado);
		this.setFlLeido(flLeido);
		this.setFeLectura(feLectura);
		this.setNoPersonaOrigen(noPersonaOrigen);		
		this.setNoUsuarioOrigen(noUsuarioOrigen);
		this.setDescFeInstanciaPaso(descFeInstanciaPaso);
		this.setDescDeMensaje(descDeMensaje);
		this.setDescFlPasoActivo(descFlPasoActivo);
		this.setDescIdInstanciaPaso(descIdInstanciaPaso);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#obtenerConfiguraRiesgoPorId()
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#obtenerConfiguraRiesgoPorIdConfiguraRiesgoPadre()
	 * 
	 * @param idConfiguraRiesgo
	 * @param idConfiguraRiesgoPadre
	 * @param idEspecialidad
	 * @param descNoEspecialidad
	 * @param idTipoEstadoConfiguracion
	 * @param descNoValorParametro
	 * @param noConfiguracion
	 * @param deConfiguracion
	 * @param feConfiguracion
	 * @param idInstanciaProceso
	 * @param nuAnioPeriodo
	 * @param idTipoPeriodo
	 * @param idTipoConfiguracionRiesgo
	 * @param descNoTipoPeriodo
	 * @param descNoTipoConfiguracionRiesgo
	 * @param descNoConfiguracionPadre
	 */
	public PgimConfiguraRiesgoDTOResultado(Long idConfiguraRiesgo, 
			Long idConfiguraRiesgoPadre, Long idEspecialidad, String descNoEspecialidad, Long idTipoEstadoConfiguracion,
			String descNoValorParametro, String noConfiguracion, String deConfiguracion, Date feConfiguracion, 
			Long idInstanciaProceso, Long nuAnioPeriodo, Long idTipoPeriodo, 
			Long idTipoConfiguracionRiesgo, String descNoTipoPeriodo, String descNoTipoConfiguracionRiesgo,
			String descNoConfiguracionPadre) {
        super();

        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setIdConfiguraRiesgoPadre(idConfiguraRiesgoPadre);
        this.setIdEspecialidad(idEspecialidad);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setIdTipoEstadoConfiguracion(idTipoEstadoConfiguracion);
        this.setDescNoValorParametro(descNoValorParametro);
        this.setNoConfiguracion(noConfiguracion);
        this.setDeConfiguracion(deConfiguracion);
        this.setFeConfiguracion(feConfiguracion);
        this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNuAnioPeriodo(nuAnioPeriodo);
		this.setIdTipoPeriodo(idTipoPeriodo);
		this.setIdTipoConfiguracionRiesgo(idTipoConfiguracionRiesgo);
		this.setDescNoTipoConfiguracionRiesgo(descNoTipoConfiguracionRiesgo);
		this.setDescNoTipoPeriodo(descNoTipoPeriodo);
		this.setDescNoConfiguracionPadre(descNoConfiguracionPadre);

    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#listarConfiguraRiesgoPorIdFactor()
	 * 
	 * @param idConfiguraRiesgo
	 * @param noConfiguracion
	 */
	public PgimConfiguraRiesgoDTOResultado(Long idConfiguraRiesgo, String noConfiguracion) {
        super();

        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setNoConfiguracion(noConfiguracion);

    }


	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository#obtenerConfiguraRiesgoPorIdSupervision()
	 * @param idConfiguraRiesgo
	 * @param noConfiguracion
	 * @param idInstanciaProceso
	 */
	public PgimConfiguraRiesgoDTOResultado(Long idConfiguraRiesgo, String noConfiguracion, Long idInstanciaProceso ){
		super();
		this.setIdConfiguraRiesgo(idConfiguraRiesgo);
		this.setNoConfiguracion(noConfiguracion);
		this.setIdInstanciaProceso(idInstanciaProceso);
	}

}
