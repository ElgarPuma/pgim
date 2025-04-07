package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimRankingRiesgoDTOResultado extends PgimRankingRiesgoDTO {

    /**
     * Permite potar los datos del ranking de riesgo.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository#listarRankingRiesgo()
     * @see pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository#listarConfiguracionRankingRiesgo()
     * 
     * @param idRankingRiesgo
     * @param idConfiguraRiesgo
     * @param idInstanciaProceso
     * @param noRanking
     * @param deRanking
     * @param feGeneracionRanking
     * @param descFeGeneracionRankingAnio
     * @param descNoEspecialidad
     * @param descTipoEstadoConfiguracion
     * @param descNoConfiguracion
     * @param descFeConfiguracion
     * @param descDeConfiguracion
     * @param descNoFaseActual
     * @param descNoPasoActual
     * @param descNoPersonaAsignada
     * @param descNoTipoConfiguracionRiesgo
     * @param descCoTipoConfiguracionRiesgo
     * @param descNoTipoPeriodo
     * @param descNuAnioPeriodo
     * @param usuarioAsignado
     * @param flLeido
     * @param feLectura
     * @param feInstanciaPaso
     * @param noPersonaOrigen
     * @param flPasoActivo
     * @param deMensaje
     * @param desIdInstanciaPaso
     */
    public PgimRankingRiesgoDTOResultado(Long idRankingRiesgo, Long idConfiguraRiesgo, Long idInstanciaProceso,
                                         String noRanking, String deRanking, Date feGeneracionRanking,
                                         String descFeGeneracionRankingAnio, String descNoEspecialidad, String descTipoEstadoConfiguracion,
                                         String descNoConfiguracion, Date descFeConfiguracion, String descDeConfiguracion,
                                         String descNoFaseActual, String descNoPasoActual, String descNoPersonaAsignada,
                                         String descNoTipoConfiguracionRiesgo, String descCoTipoConfiguracionRiesgo, String descNoTipoPeriodo,
                                         Long descNuAnioPeriodo, String usuarioAsignado, String flLeido,
                                         Date feLectura, Date feInstanciaPaso, String noPersonaOrigen,
                                         String flPasoActivo, String deMensaje, Long desIdInstanciaPaso) {
        super();

        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setNoRanking(noRanking);
        this.setDeRanking(deRanking);
        this.setFeGeneracionRanking(feGeneracionRanking);
        this.setDescFeGeneracionRankingAnio(descFeGeneracionRankingAnio);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescTipoEstadoConfiguracion(descTipoEstadoConfiguracion);
        this.setDescNoConfiguracion(descNoConfiguracion);
        this.setDescFeConfiguracion(descFeConfiguracion);
        this.setDescNoFaseActual(descNoFaseActual);
        this.setDescNoPasoActual(descNoPasoActual);
        this.setDescNoPersonaAsignada(descNoPersonaAsignada);
        this.setDescNoTipoConfiguracionRiesgo(descNoTipoConfiguracionRiesgo);
        this.setDescCoTipoConfiguracionRiesgo(descCoTipoConfiguracionRiesgo);
        this.setDescNoTipoPeriodo(descNoTipoPeriodo);
        this.setDescNuAnioPeriodo(descNuAnioPeriodo);
        this.setUsuarioAsignado(usuarioAsignado);
        this.setFlLeido(flLeido);
        this.setFeLectura(feLectura);
        this.setDescFeInstanciaPaso(feInstanciaPaso);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setDescFlPasoActivo(flPasoActivo);
        this.setDescDeMensaje(deMensaje);
        this.setDescIdInstanciaPaso(desIdInstanciaPaso);
    }

    /**
     * Permite potar los datos del ranking de riesgo.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository#listarPorNuExpedienteSiged()
     * @param idRankingRiesgo
     * @param idConfiguraRiesgo
     * @param idInstanciaProceso
     * @param descNuExpedienteSiged
     */
    public PgimRankingRiesgoDTOResultado(
            Long idRankingRiesgo,
            Long idConfiguraRiesgo,
            Long idInstanciaProceso
    // String descNuExpedienteSiged
    ) {
        super();
        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setIdInstanciaProceso(idInstanciaProceso);
        // this.setDescNuExpedienteSiged(descNuExpedienteSiged);
    }

    /**
     * Permite potar los datos del ranking de riesgo.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository#obtenerRankingRiesgoPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository#obtenerRankingRiesgoByIdInstanciaProceso()
     * @see pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository#obtenerRankingsPorConfiguracionRiesgo()
     * @param idRankingRiesgo
     * @param idConfiguraRiesgo
     * @param idInstanciaProceso
     * @param noRanking
     * @param deRanking
     * @param feGeneracionRanking
     * @param descNoEspecialidad
     */
    public PgimRankingRiesgoDTOResultado(
            Long idRankingRiesgo,
            Long idConfiguraRiesgo,
            Long idInstanciaProceso,
            String noRanking,
            String deRanking,
            Date feGeneracionRanking,
            String descNoEspecialidad,
            String descTipoEstadoConfiguracion,
            String descNoConfiguracion,
            Long descIdTipoConfiguracionRiesgo,
            String descNoTipoConfiguracionRiesgo,
            String descCoTipoConfiguracionRiesgo,
            String descNoTipoPeriodo,
            Long descNuAnioPeriodo) {
        super();
        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setNoRanking(noRanking);
        this.setDeRanking(deRanking);
        this.setFeGeneracionRanking(feGeneracionRanking);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescTipoEstadoConfiguracion(descTipoEstadoConfiguracion);
        this.setDescNoConfiguracion(descNoConfiguracion);
        this.setDescIdTipoConfiguracionRiesgo(descIdTipoConfiguracionRiesgo);
        this.setDescNoTipoConfiguracionRiesgo(descNoTipoConfiguracionRiesgo);
        this.setDescCoTipoConfiguracionRiesgo(descCoTipoConfiguracionRiesgo);
        this.setDescNoTipoPeriodo(descNoTipoPeriodo);
        this.setDescNuAnioPeriodo(descNuAnioPeriodo);
    }

    /**
     *  @see pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository#obtenerRankingAprobXDsEspecialidadAnio(Long, Long, Long, Long, String)
     * @param idRankingRiesgo
     * @param idConfiguraRiesgo
     * @param noRanking
     * @param deRanking
     * @param feGeneracionRanking
     * @param descIdEspecialidad
     * @param descNoEspecialidad
     * @param descNoConfiguracion
     * @param descDeConfiguracion
     * @param descNoTipoPeriodo
     * @param descNuAnioPeriodo
     * @param descFeConfiguracion
     */
    public PgimRankingRiesgoDTOResultado(Long idRankingRiesgo,
            Long idConfiguraRiesgo,
            String noRanking,
            String deRanking,
            Date feGeneracionRanking,
            Long descIdEspecialidad,
            String descNoEspecialidad,
            String descNoConfiguracion,
            String descDeConfiguracion,
            String descNoTipoPeriodo,
            Long descNuAnioPeriodo,
            Date descFeConfiguracion,
            Long descCantUnidadMinera,
            BigDecimal descPuntajeTotalRanking, 
            Long nuOrden){
        super();        
        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setNoRanking(noRanking);
        this.setDeRanking(deRanking);
        this.setFeGeneracionRanking(feGeneracionRanking);
        this.setDescIdEspecialidad(descIdEspecialidad);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescNoConfiguracion(descNoConfiguracion);
        this.setDescDeConfiguracion(descDeConfiguracion);
        this.setDescNoTipoPeriodo(descNoTipoPeriodo);
        this.setDescNuAnioPeriodo(descNuAnioPeriodo);
        this.setDescFeConfiguracion(descFeConfiguracion);
        this.setDescCantUnidadMinera(descCantUnidadMinera);
        this.setDescPuntajeTotalRanking(descPuntajeTotalRanking);
        this.setDescNuOrden(nuOrden);

    }

}
