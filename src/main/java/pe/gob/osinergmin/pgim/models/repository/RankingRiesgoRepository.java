package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingRiesgo;

import java.util.List;

/**
 * Tiene como nombre Proceso administrativo sancionador.
 * 
 * @descripción: Logica de negocio de la entidad Ranking de Riesgo
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface RankingRiesgoRepository extends JpaRepository<PgimRankingRiesgo, Long> {

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTOResultado( "
                        + "prraux.idRankingRiesgoAux, pfr.idConfiguraRiesgo, pip.idInstanciaProceso, "
                        + "prraux.noRanking, prr.deRanking, prraux.feGeneracionRanking, "
                        + "prraux.feGeneracionRankingAnio, pe.noEspecialidad, tec.noValorParametro, "
                        + "pfr.noConfiguracion, pfr.feConfiguracion, pfr.deConfiguracion, "
                        + "prraux.noFaseActual, prraux.noPasoActual, prraux.noPersonaAsignada, "
                        + "tcr.noValorParametro, tcr.coClaveTexto, tpc.noValorParametro, "
                        + "pfr.nuAnioPeriodo, prraux.noUsuarioAsignado, prraux.flLeido, "
                        + "prraux.feLectura, prraux.feInstanciaPaso, prraux.noPersonaOrigen, "
                        + "prraux.flPasoActivo, prraux.deMensaje, prraux.idInstanciaPaso "
                        + ") "
                        + "FROM PgimRankingRiesgoAux prraux "
                        + "     INNER JOIN prraux.pgimRankingRiesgo prr "
                        + "     INNER JOIN prr.pgimConfiguraRiesgo pfr "
                        + "     INNER JOIN pfr.tipoPeriodo tpc "
                        + "     INNER JOIN pfr.tipoConfiguracionRiesgo tcr "
                        + "     INNER JOIN pfr.tipoEstadoConfiguracion tec "
                        + "     LEFT OUTER JOIN prr.pgimInstanciaProces pip "
                        + "     INNER JOIN pfr.pgimEspecialidad pe "
                        + "WHERE prr.esRegistro = '1' "
                        + "AND (:feGeneracionRankingAnio IS NULL OR LOWER(prraux.feGeneracionRankingAnio) LIKE LOWER(CONCAT('%', :feGeneracionRankingAnio, '%')) ) "
                        + "AND (:idEspecialidad IS NULL OR LOWER(pe.idEspecialidad) LIKE LOWER(CONCAT('%', :idEspecialidad, '%')) ) "
                        + "AND (:idTipoEstadoConfiguracion IS NULL OR LOWER(tec.idValorParametro) LIKE LOWER(CONCAT('%', :idTipoEstadoConfiguracion, '%')) ) "
                        + "AND (:idTipoConfiguracionRiesgo IS NULL OR LOWER(tcr.idValorParametro) LIKE LOWER(CONCAT('%', :idTipoConfiguracionRiesgo, '%')) ) "
                        + "AND (:idFaseProceso IS NULL OR prraux.idFaseActual = :idFaseProceso ) "
                        + "AND (:descIdTipoPeriodo IS NULL OR tpc.idValorParametro = :descIdTipoPeriodo ) "
                        + "AND (:descNuAnioPeriodo IS NULL OR pfr.nuAnioPeriodo = :descNuAnioPeriodo ) "
                        + "AND (:idPasoProceso IS NULL OR prraux.idPasoActual = :idPasoProceso) "
                        + "AND ( "
                         // Mis tareas = '1'
                        + " ( "
                        + "     :flagMisAsignaciones = '1' "
                        + "     AND prraux.flPasoActivo = '1' "
                        + "     AND LOWER(prraux.noUsuarioAsignado) = LOWER(:usuarioAsignado) "
                        + "     AND prraux.idTipoRelacion NOT IN (291, 292) "
                        + " ) "
                        + "OR "
                        // Tareas enviadas = '2'
                        + " ("
                        + "     :flagMisAsignaciones = '2' "
                        + "     AND LOWER(prraux.noUsuarioOrigen) = LOWER(:usuarioOrigen) "
                        + "     AND (:descPersonaDestino IS NULL "
                        + "          OR LOWER(prraux.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
                        + "         ) " 
                        + " ) "
                        + "OR "
                         // Todas las tareas
                        + " ("
                        + "     :flagMisAsignaciones = '0' "
                        + "     AND prraux.flPasoActivo = '1' "
                        + "     AND (:descPersonaDestino IS NULL "
                        + "          OR LOWER(prraux.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
                        + "         )"
                        + " ) "
                        + " ) "
                        + "AND (:textoBusqueda IS NULL "
                        + "     OR LOWER(prr.noRanking) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "	OR LOWER(pe.gob.osinergmin.pgim.utils.ConstantesUtil.PREFIJO_RANK_RIESGO || '-' || prraux.idRankingRiesgoAux) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + ") ")
        Page<PgimRankingRiesgoDTO> listarRankingRiesgo(
                        @Param("feGeneracionRankingAnio") String feGeneracionRankingAnio,
                        @Param("idEspecialidad") Long idEspecialidad,
                        @Param("idTipoEstadoConfiguracion") Long idTipoEstadoConfiguracion,
                        @Param("idFaseProceso") Long idFaseProceso,
                        @Param("idPasoProceso") Long idPasoProceso,
                        @Param("flagMisAsignaciones") String flagMisAsignaciones,
                        @Param("usuarioOrigen") String usuarioOrigen,
                        @Param("usuarioAsignado") String usuarioAsignado,
                        @Param("descPersonaDestino") String descPersonaDestino,
                        @Param("idTipoConfiguracionRiesgo") Long idTipoConfiguracionRiesgo,
                        @Param("descIdTipoPeriodo") Long descIdTipoPeriodo,
                        @Param("descNuAnioPeriodo") Long descNuAnioPeriodo,
                        @Param("textoBusqueda") String textoBusqueda,
                        Pageable paginador);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTOResultado( "
                        + "prraux.idRankingRiesgoAux, pfr.idConfiguraRiesgo, pip.idInstanciaProceso, "
                        + "prraux.noRanking, prr.deRanking, prraux.feGeneracionRanking, "
                        + "prraux.feGeneracionRankingAnio, pe.noEspecialidad, tec.noValorParametro, "
                        + "pfr.noConfiguracion, pfr.feConfiguracion,  pfr.deConfiguracion, "
                        + "prraux.noFaseActual, prraux.noPasoActual, prraux.noPersonaAsignada, "
                        + "tcr.noValorParametro, tcr.coClaveTexto, tpc.noValorParametro, "
                        + "pfr.nuAnioPeriodo, prraux.noUsuarioAsignado, prraux.flLeido, "
                        + "prraux.feLectura, prraux.feInstanciaPaso, prraux.noPersonaOrigen, "
                        + "prraux.flPasoActivo, prraux.deMensaje, prraux.idInstanciaPaso "
                        + ") "
                        + "FROM PgimRankingRiesgoAux prraux "
                        + "     INNER JOIN prraux.pgimRankingRiesgo prr "
                        + "     INNER JOIN prr.pgimConfiguraRiesgo pfr "
                        + "     INNER JOIN pfr.tipoPeriodo tpc "
                        + "     INNER JOIN pfr.tipoConfiguracionRiesgo tcr "
                        + "     INNER JOIN pfr.tipoEstadoConfiguracion tec "
                        + "     LEFT OUTER JOIN prr.pgimInstanciaProces pip "
                        + "     INNER JOIN pfr.pgimEspecialidad pe "
                        + "WHERE prraux.flPasoActivo = '1' "
                        + "AND prr.esRegistro = '1' "
                        + "AND pfr.idConfiguraRiesgo = :idConfiguraRiesgo "
                        + "AND (prraux.idTipoRelacion NOT IN (291, 292)) ")
        Page<PgimRankingRiesgoDTO> listarConfiguracionRankingRiesgo(
                        @Param("idConfiguraRiesgo") Long idConfiguraRiesgo,
                        Pageable paginador);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTOResultado("
                        + "prr.idRankingRiesgo, pfr.idConfiguraRiesgo, pip.idInstanciaProceso "
                        + ") "
                        + "FROM PgimRankingRiesgo prr "
                        + "INNER JOIN prr.pgimConfiguraRiesgo pfr "
                        + "INNER JOIN prr.pgimInstanciaProces pip "
                        + "WHERE prr.esRegistro = '1' "
                        + "AND (:palabraClave IS NULL OR LOWER(pip.nuExpedienteSiged) "
                        + "LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + " )")
        List<PgimRankingRiesgoDTO> listarPorNuExpedienteSiged(@Param("palabraClave") String palabraClave);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTOResultado("
                        + "prr.idRankingRiesgo, pfr.idConfiguraRiesgo, pip.idInstanciaProceso, prr.noRanking, prr.deRanking, prr.feGeneracionRanking, "
                        + "pe.noEspecialidad, tec.noValorParametro, pfr.noConfiguracion, "
                        + "tcr.idValorParametro, tcr.noValorParametro, tcr.coClaveTexto, tpc.noValorParametro, pfr.nuAnioPeriodo "
                        + ") "
                        + "FROM PgimRankingRiesgo prr "
                        + "INNER JOIN prr.pgimConfiguraRiesgo pfr "
                        + "INNER JOIN pfr.tipoPeriodo tpc "
                        + "INNER JOIN pfr.tipoConfiguracionRiesgo tcr "
                        + "INNER JOIN pfr.tipoEstadoConfiguracion tec "
                        + "LEFT OUTER JOIN prr.pgimInstanciaProces pip "
                        + "INNER JOIN pfr.pgimEspecialidad pe "
                        + "WHERE prr.esRegistro = '1' "
                        + "AND prr.idRankingRiesgo = :idRankingRiesgo ")
        PgimRankingRiesgoDTO obtenerRankingRiesgoPorId(@Param("idRankingRiesgo") Long idRankingRiesgo);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTOResultado("
                        + "prr.idRankingRiesgo, pfr.idConfiguraRiesgo, pip.idInstanciaProceso, prr.noRanking, prr.deRanking, prr.feGeneracionRanking, "
                        + "pe.noEspecialidad, tec.noValorParametro, pfr.noConfiguracion, "
                        + "tcr.idValorParametro, tcr.noValorParametro, tcr.coClaveTexto, tpc.noValorParametro, pfr.nuAnioPeriodo "
                        + ") "
                        + "FROM PgimRankingRiesgo prr "
                        + "INNER JOIN prr.pgimConfiguraRiesgo pfr "
                        + "INNER JOIN pfr.tipoPeriodo tpc "
                        + "INNER JOIN pfr.tipoConfiguracionRiesgo tcr "
                        + "INNER JOIN pfr.tipoEstadoConfiguracion tec "
                        + "LEFT OUTER JOIN prr.pgimInstanciaProces pip "
                        + "INNER JOIN pfr.pgimEspecialidad pe "
                        + "WHERE prr.esRegistro = '1' "
                        + "AND pip.idInstanciaProceso = :idInstanciaProceso ")
        PgimRankingRiesgoDTO obtenerRankingRiesgoByIdInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTOResultado("
                        + "prr.idRankingRiesgo, pfr.idConfiguraRiesgo, pip.idInstanciaProceso, prr.noRanking, prr.deRanking, prr.feGeneracionRanking, "
                        + "pe.noEspecialidad, tec.noValorParametro, pfr.noConfiguracion, "
                        + "tcr.idValorParametro, tcr.noValorParametro, tcr.coClaveTexto, tpc.noValorParametro, pfr.nuAnioPeriodo "
                        + ") "
                        + "FROM PgimRankingRiesgo prr "
                        + "INNER JOIN prr.pgimConfiguraRiesgo pfr "
                        + "INNER JOIN pfr.tipoPeriodo tpc "
                        + "INNER JOIN pfr.tipoConfiguracionRiesgo tcr "
                        + "INNER JOIN pfr.tipoEstadoConfiguracion tec "
                        + "LEFT OUTER JOIN prr.pgimInstanciaProces pip "
                        + "INNER JOIN pfr.pgimEspecialidad pe "
                        + "WHERE prr.esRegistro = '1' "
                        + "AND pfr.idConfiguraRiesgo = :idConfiguraRiesgo ")
        List<PgimRankingRiesgoDTO> obtenerRankingsPorConfiguracionRiesgo(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);
        
       
        /**
         * Permite obtener la cantidad de rankings de riesgo pendientes de atención que tiene un usuario 
         * 
         * @param usuarioAsignado
         * @return
         */
        @Query("SELECT COUNT(prraux.idRankingRiesgoAux) "
                + "FROM PgimRankingRiesgoAux prraux "                
                + "WHERE "                
                + "prraux.flPasoActivo = '1' "
                + "AND LOWER(prraux.noUsuarioAsignado) = LOWER(:usuarioAsignado) "
                + "AND prraux.idTipoRelacion NOT IN (291, 292) "
                )
        Integer contarRankingRiesgoPendientes(
                @Param("usuarioAsignado") String usuarioAsignado
                );

        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTOResultado("
                + "rari.idRankingRiesgo, cori.idConfiguraRiesgo, 'RANK-' || rari.idRankingRiesgo || '. ' ||  rari.noRanking, "
                + "rari.deRanking, rari.feGeneracionRanking, espe.idEspecialidad, "
                + "espe.noEspecialidad, cori.noConfiguracion, cori.deConfiguracion, "
                + "tipe.noValorParametro, cori.nuAnioPeriodo, cori.feConfiguracion, "
                + "(SELECT COUNT(rum.pgimUnidadMinera.idUnidadMinera) "
                + "     FROM PgimRankingUm rum "
                + "     WHERE rari.idRankingRiesgo = rum.pgimRankingRiesgo.idRankingRiesgo "
                + "     AND rum.esRegistro = '1' "
                + "     AND rum.flActivo = '1' "
                + "     ), "

                + "(SELECT  NVL(SUM(rum.moPuntaje),0) "
                + "     FROM PgimRankingUm rum "
                + "     WHERE rari.idRankingRiesgo = rum.pgimRankingRiesgo.idRankingRiesgo "
                + "     AND rum.esRegistro = '1' "
                + "     AND rum.flActivo = '1' "
                + "     ), "
                + "ROWNUM "
                + ") "
                + "FROM PgimRankingRiesgo rari "
                + "INNER JOIN PgimConfiguraRiesgo cori ON (rari.pgimConfiguraRiesgo.idConfiguraRiesgo = cori.idConfiguraRiesgo "
                +"      AND cori.tipoConfiguracionRiesgo.idValorParametro = :idTipoCfgRiesgoFiscalizacion " 
                +"      AND cori.esRegistro = 1 AND rari.esRegistro = 1) "
                // + "INNER JOIN cori.tipoConfiguracionRiesgo tipe "
                + "INNER JOIN cori.tipoPeriodo tipe "
                + "INNER JOIN cori.pgimEspecialidad espe "
                + "WHERE 1 = 1 "
                + "AND EXTRACT(YEAR FROM rari.feGeneracionRanking) = :anio "
                + "AND espe.idEspecialidad = :idEspecialidad "
                + "AND EXISTS ( "
                + "     SELECT 1 "
                + "     FROM PgimConfiguraRegla core "
                + "     WHERE 1 = 1 "
                + "	AND core.divisionSupervisora.idValorParametro = :idDivisionSupervisora " 
                + "     AND core.esRegistro = '1' "
                + " )"
                // Asegurando que se trata de únicamente rankings aprobados.
                + "AND EXISTS ( "
                + "     SELECT 1 "
                + "     FROM PgimInstanciaProces inpr "
                + "     INNER JOIN PgimInstanciaPaso inta ON (inpr.idInstanciaProceso = inta.pgimInstanciaProces.idInstanciaProceso "
                + "             AND inta.flEsPasoActivo = '1' AND inta.esRegistro = '1') "
                + "     INNER JOIN PgimRelacionPaso reta ON (inta.pgimRelacionPaso.idRelacionPaso = reta.idRelacionPaso) "
                + "     INNER JOIN PgimPasoProceso tade ON (reta.pasoProcesoDestino.idPasoProceso = tade.idPasoProceso "
                + "             AND tade.idPasoProceso = :idPasoProcesoRankingAprob ) "
                + "     WHERE 1 = 1 "
                + "	AND inpr.idInstanciaProceso = rari.pgimInstanciaProces.idInstanciaProceso " 
                + " )"
                + "ORDER BY rari.feGeneracionRanking DESC "
                )
        List<PgimRankingRiesgoDTO> obtenerRankingAprobXDsEspecialidadAnio(@Param("idEspecialidad") Long idEspecialidad,
                @Param("idDivisionSupervisora") Long idDivisionSupervisora, 
                @Param("anio") Integer anio, 
                @Param("idPasoProcesoRankingAprob") Long idPasoProcesoRankingAprob, 
                @Param("idTipoCfgRiesgoFiscalizacion") Long idTipoCfgRiesgoFiscalizacion);

}
