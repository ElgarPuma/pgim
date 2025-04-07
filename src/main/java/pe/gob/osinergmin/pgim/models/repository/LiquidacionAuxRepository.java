package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimLiquidacionAux;

/**
 * 
 * @descripción: Logica de negocio de la entidad liquidación
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 01/12/2020
 * @fecha_de_ultima_actualización:
 */
@Repository
public interface LiquidacionAuxRepository extends JpaRepository<PgimLiquidacionAux, Long> {

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTOResultado( "
                        + "liqu.idLiquidacionAux, liqu.pgimLiquidacion.idLiquidacion, liqu.nuLiquidacion, "
                        + "liqu.idTipoEntregableLiquidacion, liqu.tipoEntregableLiquidacion, liqu.moItemConsumo, "
                        + "liqu.moItemPenalidad, liqu.idContrato, liqu.nuContrato, "
                        + "liqu.deContrato, liqu.idEspecialidad, liqu.noEspecialidad, "
                        + "liqu.idEmpresaSupervisora, liqu.noRazonSocialSupervisora, liqu.nuExpedienteSiged, "
                        + "liqu.personaAsignada, liqu.usuarioAsignado, liqu.idFaseActual, "
                        + "liqu.noFaseActual, liqu.idPasoActual, liqu.noPasoActual, "
                        + "liqu.idInstanciaProceso, liqu.idRelacionPaso, liqu.idTipoRelacion, "
                        + "liqu.tipoRelacionPaso, liqu.feCreacion, liqu.flLeido, "
                        + "liqu.feLectura, liqu.feInstanciaPaso, liqu.noPersonaOrigen, "
                        + "liqu.flPasoActivo, liqu.deMensaje, liqu.idInstanciaPaso "
                        + ") "
                        + "FROM PgimLiquidacionAux liqu "
                        + "WHERE 1 = 1 "
                        + "AND (:nuLiquidacion IS NULL OR LOWER(liqu.nuLiquidacion) LIKE LOWER(CONCAT('%', :nuLiquidacion, '%')) ) "
                        + "AND (:nuContrato IS NULL OR LOWER(liqu.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
                        + "AND (:noRazonSocialSupervisora IS NULL OR LOWER(liqu.noRazonSocialSupervisora) LIKE LOWER(CONCAT('%', :noRazonSocialSupervisora, '%')) ) "
                        + "AND (:nuExpedienteSiged IS NULL OR liqu.nuExpedienteSiged LIKE CONCAT('%', :nuExpedienteSiged, '%') ) "
                        + "AND (:idFaseActual IS NULL OR liqu.idFaseActual = :idFaseActual) "
                        + "AND (:idPasoActual IS NULL OR liqu.idPasoActual = :idPasoActual) "
                        + "AND (:idTipoEntregableLiquidacion IS NULL OR liqu.idTipoEntregableLiquidacion = :idTipoEntregableLiquidacion) "
                        + "AND (:idEspecialidad IS NULL OR liqu.idEspecialidad = :idEspecialidad) " 
                        + "AND ( "
                        // Mis tareas                        
                        + "         ("
                        + "             :flagMisAsignaciones = '1' "
                        + "             AND liqu.flPasoActivo = '1' "                        
                        + "             AND LOWER(liqu.usuarioAsignado) = LOWER(:usuarioAsignado) "
                        + "             AND liqu.idTipoRelacion NOT IN (291, 292) "
                        + "          ) "
                        + "         OR "
                        // Tareas enviadas
                        + "         ("
                        + "             :flagMisAsignaciones = '2' "
                        + "             AND LOWER(liqu.noUsuarioOrigen) = LOWER(:noUsuarioOrigen)"
                        + "             AND ("
                        + "                     :personaAsignada IS NULL "
                        + "                     OR LOWER(liqu.personaAsignada) LIKE LOWER(CONCAT('%', :personaAsignada, '%')) "
                        + "                  ) " 
                        + "          ) "
                        + "         OR "
                        // Todas las tareas
                        + "         ("
                        + "             :flagMisAsignaciones = '0' "
                        + "             AND liqu.flPasoActivo = '1' "
                        + "             AND ("
                        + "                     :personaAsignada IS NULL "
                        + "                      OR LOWER(liqu.personaAsignada) LIKE LOWER(CONCAT('%', :personaAsignada, '%')) "
                        + "                  )"
                        + "          ) " 
                        + "     ) " 
                        + "AND ( "
                        + ":textoBusqueda IS NULL OR LOWER(liqu.nuLiquidacion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(liqu.nuContrato) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(liqu.noRazonSocialSupervisora) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(liqu.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
                        + "OR LOWER(liqu.personaAsignada) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
                        + ")")
        Page<PgimLiquidacionAuxDTO> listarLiquidaciones(@Param("nuLiquidacion") String nuLiquidacion,
                        @Param("nuContrato") String nuContrato,
                        @Param("noRazonSocialSupervisora") String noRazonSocialSupervisora,
                        @Param("nuExpedienteSiged") String nuExpedienteSiged,
                        @Param("idTipoEntregableLiquidacion") Long idTipoEntregableLiquidacion,
                        @Param("idFaseActual") Long idFaseActual,
                        @Param("idPasoActual") Long idPasoActual,
                        @Param("idEspecialidad") Long idEspecialidad,
                        @Param("flagMisAsignaciones") String flagMisAsignaciones,
                        @Param("noUsuarioOrigen") String noUsuarioOrigen,
                        @Param("usuarioAsignado") String usuarioAsignado,
                        @Param("personaAsignada") String personaAsignada,
                        @Param("textoBusqueda") String textoBusqueda,
                        Pageable paginador);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTOResultado( "
                        + "liqu.idLiquidacionAux, liqu.pgimLiquidacion.idLiquidacion, liqu.nuLiquidacion, "
                        + "liqu.idTipoEntregableLiquidacion, liqu.tipoEntregableLiquidacion, liqu.moItemConsumo, "
                        + "liqu.moItemPenalidad, liqu.idContrato, liqu.nuContrato, "
                        + "liqu.deContrato, liqu.idEspecialidad, liqu.noEspecialidad, "
                        + "liqu.idEmpresaSupervisora, liqu.noRazonSocialSupervisora, liqu.nuExpedienteSiged, "
                        + "liqu.personaAsignada, liqu.usuarioAsignado, liqu.idFaseActual, "
                        + "liqu.noFaseActual, liqu.idPasoActual, liqu.noPasoActual, "
                        + "liqu.idInstanciaProceso, liqu.idRelacionPaso, liqu.idTipoRelacion, "
                        + "liqu.tipoRelacionPaso, liqu.feCreacion, liqu.flLeido, "
                        + "liqu.feLectura, liqu.feInstanciaPaso, liqu.noPersonaOrigen, "
                        + "liqu.flPasoActivo, liqu.deMensaje, liqu.idInstanciaPaso "
                        + ") "
                        + "FROM PgimLiquidacionAux liqu "
                        + "WHERE liqu.idLiquidacionAux = :idLiquidacion "
                        + "AND liqu.flPasoActivo = '1'"
                        )
	PgimLiquidacionAuxDTO obtenerLiquidacionAuxPorId(@Param("idLiquidacion") Long idLiquidacion);
        
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTOResultado( "
                + "liqu.idLiquidacionAux, liqu.pgimLiquidacion.idLiquidacion, liqu.nuLiquidacion, "
                + "liqu.idTipoEntregableLiquidacion, liqu.tipoEntregableLiquidacion, liqu.moItemConsumo, "
                + "liqu.moItemPenalidad, liqu.idContrato, liqu.nuContrato, "
                + "liqu.deContrato, liqu.idEspecialidad, liqu.noEspecialidad, "
                + "liqu.idEmpresaSupervisora, liqu.noRazonSocialSupervisora, liqu.nuExpedienteSiged, "
                + "liqu.personaAsignada, liqu.usuarioAsignado, liqu.idFaseActual, "
                + "liqu.noFaseActual, liqu.idPasoActual, liqu.noPasoActual, "
                + "liqu.idInstanciaProceso, liqu.idRelacionPaso, liqu.idTipoRelacion, "
                + "liqu.tipoRelacionPaso, liqu.feCreacion, liqu.divisionSupervisora.idValorParametro, "
                + "liqu.deDivisionSupervisora, liqu.tipoSupervision.idValorParametro, liqu.noTipoSupervision, "
                + "liqu.pgimSubtipoSupervision.idSubtipoSupervision, liqu.deSubtipoSupervision, liqu.pgimMotivoSupervision.idMotivoSupervision, "
                + "liqu.deMotivoSupervision, liqu.pgimLiquidacion.flPenalidadReemplazoPersona, liqu.pgimLiquidacion.moPenalidadReemplazoPersona "
                + ") "
                + "FROM PgimLiquidacionAux liqu "
                + "WHERE 1 = 1 "
                + "AND liqu.idInstanciaProceso = :idInstanciaProceso "
                + "AND liqu.flPasoActivo = '1'"
                )
       PgimLiquidacionAuxDTO obtenerLiquidacionAuxPorInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);

       @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTOResultado( "
                    + "liqu.idLiquidacionAux, liqu.pgimLiquidacion.idLiquidacion, liqu.nuLiquidacion, "
                    + "liqu.idTipoEntregableLiquidacion, liqu.tipoEntregableLiquidacion, liqu.moItemConsumo, "
                    + "liqu.moItemPenalidad, liqu.idContrato, liqu.nuContrato, "
                    + "liqu.deContrato, liqu.idEspecialidad, liqu.noEspecialidad, "
                    + "liqu.idEmpresaSupervisora, liqu.noRazonSocialSupervisora, liqu.nuExpedienteSiged, "
                    + "liqu.personaAsignada, liqu.usuarioAsignado, liqu.idFaseActual, "
                    + "liqu.noFaseActual, liqu.idPasoActual, liqu.noPasoActual, "
                    + "liqu.idInstanciaProceso, liqu.idRelacionPaso, liqu.idTipoRelacion, "
                    + "liqu.tipoRelacionPaso, liqu.feCreacion, liqu.flLeido, "
                    + "liqu.feLectura, liqu.feInstanciaPaso, liqu.noPersonaOrigen, "
                    + "liqu.flPasoActivo, liqu.deMensaje, liqu.idInstanciaPaso "
                    + ") "
                    + "FROM PgimLiquidacionAux liqu "
                    + "WHERE 1 = 1 "
                    + "AND liqu.flPasoActivo = '1' "
                    + "AND (:idContrato IS NULL OR liqu.idContrato = :idContrato) "
                    + "AND (:nuLiquidacion IS NULL OR LOWER(liqu.nuLiquidacion) LIKE LOWER(CONCAT('%', :nuLiquidacion, '%')) ) "
                    + "AND (:nuContrato IS NULL OR LOWER(liqu.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
                    + "AND (:noRazonSocialSupervisora IS NULL OR LOWER(liqu.noRazonSocialSupervisora) LIKE LOWER(CONCAT('%', :noRazonSocialSupervisora, '%')) ) "
                    + "AND (:nuExpedienteSiged IS NULL OR liqu.nuExpedienteSiged LIKE CONCAT('%', :nuExpedienteSiged, '%') ) "
                    + "AND (:idFaseActual IS NULL OR liqu.idFaseActual = :idFaseActual) "
                    + "AND (:idPasoActual IS NULL OR liqu.idPasoActual = :idPasoActual) "
                    + "AND (:idTipoEntregableLiquidacion IS NULL OR liqu.idTipoEntregableLiquidacion = :idTipoEntregableLiquidacion) "
                    + "AND (:idEspecialidad IS NULL OR liqu.idEspecialidad = :idEspecialidad) " 
                    + "AND ( "
                    + ":textoBusqueda IS NULL OR LOWER(liqu.nuLiquidacion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                    + "OR LOWER(liqu.nuContrato) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                    + "OR LOWER(liqu.noRazonSocialSupervisora) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                    + "OR LOWER(liqu.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                    + "OR LOWER(liqu.personaAsignada) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
                    + ")")
    Page<PgimLiquidacionAuxDTO> listarLiquidacionesContrato(@Param("idContrato") Long idContrato,
                    @Param("nuLiquidacion") String nuLiquidacion, 
                    @Param("nuContrato") String nuContrato,
                    @Param("noRazonSocialSupervisora") String noRazonSocialSupervisora,
                    @Param("nuExpedienteSiged") String nuExpedienteSiged,
                    @Param("idTipoEntregableLiquidacion") Long idTipoEntregableLiquidacion,
                    @Param("idFaseActual") Long idFaseActual, 
                    @Param("idPasoActual") Long idPasoActual,
                    @Param("idEspecialidad") Long idEspecialidad,
                    @Param("textoBusqueda") String textoBusqueda, Pageable paginador);
       
       
       /**
        * Permite obtener la cantidad de liquidaciones pendientes de atención que tiene un usuario 
        * 
        * @param usuarioAsignado
        * @return
        */
       @Query("SELECT COUNT(liqu.idLiquidacionAux)"
               + "FROM PgimLiquidacionAux liqu "
               + "WHERE "               
               + "liqu.flPasoActivo = '1' "                        
               + "AND LOWER(liqu.usuarioAsignado) = LOWER(:usuarioAsignado) "
               + "AND liqu.idTipoRelacion NOT IN (291, 292) "               
               )
       Integer contarLiquidacionesPendientes(
               @Param("usuarioAsignado") String usuarioAsignado
               );

}
