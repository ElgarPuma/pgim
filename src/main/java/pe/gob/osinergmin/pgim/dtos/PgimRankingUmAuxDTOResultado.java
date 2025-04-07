package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimRankingUmAuxDTOResultado extends PgimRankingUmAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.RankingUmAuxRepository#listarRankingUm()
     * @see pe.gob.osinergmin.pgim.models.repository.RankingUmAuxRepository#listarRankingUmPorFiltroUM()
     * 
     * @param idRankingUmAux
     * @param idRankingUm
     * @param idRankingRiesgo
     * @param idUnidadMinera
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param coAnonimizacion
     * @param puntajeTecnico
     * @param puntajeGestion
     * @param puntajeGeneral
     * @param nroTecnicoPendiente
     * @param nroGestionPendiente
     * @param datosCompletos
     * @param coTipoInclusionRanking
     * @param noTipoInclusionRanking
     * @param nuCalificacionTecnico
     * @param nuCalificacionGestion
     * @param flActivo
     */
    public PgimRankingUmAuxDTOResultado(
            Long idRankingUmAux, Long idRankingUm, Long idRankingRiesgo, Long idUnidadMinera, String coUnidadMinera,
            String noUnidadMinera, String coAnonimizacion, BigDecimal puntajeTecnico, BigDecimal puntajeGestion,
            BigDecimal puntajeGeneral, Integer nroTecnicoPendiente, Integer nroGestionPendiente,
            String datosCompletos, String coTipoInclusionRanking, String noTipoInclusionRanking,
            BigDecimal nuCalificacionTecnico, BigDecimal nuCalificacionGestion, String flActivo) {
        super();
        this.setIdRankingUmAux(idRankingUmAux);
        this.setIdRankingUm(idRankingUm);
        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setCoAnonimizacion(coAnonimizacion);
        this.setPuntajeTecnico(puntajeTecnico);
        this.setPuntajeGestion(puntajeGestion);
        this.setPuntajeGeneral(puntajeGeneral);
        this.setNroTecnicoPendiente(nroTecnicoPendiente);
        this.setNroGestionPendiente(nroGestionPendiente);
        this.setDescDatosCompletos(datosCompletos);        
        this.setCoTipoInclusionRanking(coTipoInclusionRanking);
        this.setNoTipoInclusionRanking(noTipoInclusionRanking);
        this.setNuCalificacionTecnico(nuCalificacionTecnico);
        this.setNuCalificacionGestion(nuCalificacionGestion);
        this.setFlActivo(flActivo);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.RankingUmAuxRepository#listarRankingUmByIdRanking()
     * 
     * @param idRankingUm
     * @param idRankingRiesgo
     * @param idUnidadMinera
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param coAnonimizacion
     * @param puntajeTecnico
     * @param puntajeGestion
     * @param puntajeGeneral
     * @param nroTecnicoPendiente
     * @param nroGestionPendiente
     * @param datosCompletos
     * @param nuCalificacionTecnico
     * @param nuCalificacionGestion
     */
    public PgimRankingUmAuxDTOResultado(
            Long idRankingUm, Long idRankingRiesgo, Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera,
            String coAnonimizacion, BigDecimal puntajeTecnico, BigDecimal puntajeGestion, BigDecimal puntajeGeneral,
            Integer nroTecnicoPendiente, Integer nroGestionPendiente, String datosCompletos,
            BigDecimal nuCalificacionTecnico, BigDecimal nuCalificacionGestion) {
        super();
        this.setIdRankingUm(idRankingUm);
        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setCoAnonimizacion(coAnonimizacion);
        this.setPuntajeTecnico(puntajeTecnico);
        this.setPuntajeGestion(puntajeGestion);
        this.setPuntajeGeneral(puntajeGeneral);
        this.setNroTecnicoPendiente(nroTecnicoPendiente);
        this.setNroGestionPendiente(nroGestionPendiente);
        this.setDescDatosCompletos(datosCompletos);
        this.setNuCalificacionTecnico(nuCalificacionTecnico);
        this.setNuCalificacionGestion(nuCalificacionGestion);
    }
    
}
