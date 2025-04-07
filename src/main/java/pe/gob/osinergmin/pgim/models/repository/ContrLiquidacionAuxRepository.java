package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimContrLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContrLiquidacionAux;

/**
 * @descripción: Logica de negocio de la entidad PgimContrLiquidacionAux
 * 
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 16/02/2023
 */
@Repository
public interface ContrLiquidacionAuxRepository extends JpaRepository<PgimContrLiquidacionAux, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContrLiquidacionAuxDTOResultado( "
        + "liqu.idContrLiquidacionAux, liqu.idEspecialidad, liqu.noEspecialidad, liqu.noDivisionSupervisora,  liqu.coSupervision, liqu.feInicioSupervisionReal,  "
        + "liqu.feFinSupervisionReal, liqu.coExpedienteSigedFisc,  liqu.deSubtipoFisc, liqu.deMotivoFisc, liqu.coUnidadMinera, liqu.noUnidadMinera, liqu.coRucAFisc,   "
        + "liqu.noRazonSocialAFisc, liqu.noFaseOrigenFisc, liqu.noPasoOrigenFisc, liqu.noFaseDestinoFisc,  liqu.noPasoDestinoFisc, liqu.noPersonaOrigenFisc,  "
        + "liqu.noPersonaDestinoFisc, liqu.feTareaFisc,  liqu.deMensajeTareaFisc, liqu.nuContrato, liqu.feInicioContrato, liqu.feFinContrato, liqu.montoConsumoContrato,  "
        + "liqu.noTipoEntregable, liqu.noEstadoConsumo, liqu.montoItemConsumo, liqu.montoItemPenalidad, liqu.montoItemFiscFallida, liqu.nuLiquidacion,  "
        + "liqu.coExpSigedLiquidacion, liqu.noFaseOrigenLiquidacion,  liqu.noPasoOrigenLiquidacion, liqu.noFaseDestinoLiquidacion,  "
        + "liqu.noPasoDestinoLiquidacion,  liqu.noPersonaOrigenLiqu, liqu.noPersonaDestinoLiqu, liqu.feTareaLiquidacion, liqu.deMensajeTareaLiqu,  "
        + "liqu.idDivisionSupervisora,liqu.idFaseDestinoFisc,liqu.idPasoDestinoFisc,liqu.idTipoEntregable,liqu.idFaseDestinoLiquidacion, " 
        + "liqu.idPasoDestinoLiquidacion,liqu.fiscEsPropia,liqu.idEstadoConsumo "
        + ") "
        + "FROM PgimContrLiquidacionAux liqu "
        + "WHERE 1 = 1 "
        + "AND (:idEspecialidad IS NULL OR liqu.idEspecialidad=:idEspecialidad ) " 
        + "AND (:idDivisionSupervisora IS NULL OR liqu.idDivisionSupervisora = :idDivisionSupervisora) "
        + "AND (:coSupervision IS NULL OR liqu.coSupervision LIKE :coSupervision) "
        + "AND (:coUnidadMinera IS NULL OR liqu.coUnidadMinera LIKE :coUnidadMinera) "
        + "AND (:idFaseDestinoFisc IS NULL OR liqu.idFaseDestinoFisc =: idFaseDestinoFisc) "
        + "AND (:idPasoDestinoFisc IS NULL OR liqu.idPasoDestinoFisc =: idPasoDestinoFisc) "
        + "AND (:idTipoEntregable IS NULL OR liqu.idTipoEntregable =: idTipoEntregable) "
        + "AND (:nuLiquidacion IS NULL OR liqu.nuLiquidacion LIKE :nuLiquidacion ) "
        + "AND (:coExpSigedLiquidacion IS NULL OR liqu.coExpSigedLiquidacion LIKE CONCAT('%', :coExpSigedLiquidacion, '%') ) "
        + "AND (:idFaseDestinoLiquidacion IS NULL OR liqu.idFaseDestinoLiquidacion =: idFaseDestinoLiquidacion) "
        + "AND (:idPasoDestinoLiquidacion IS NULL OR liqu.idPasoDestinoLiquidacion =: idPasoDestinoLiquidacion) "
        + "AND (:idEstadoConsumo IS NULL OR liqu.idEstadoConsumo =: idEstadoConsumo) "
        )
    Page<PgimContrLiquidacionAuxDTO> listarContrLiquidacionAuxPaginado(
        @Param("idEspecialidad") Long idEspecialidad,
        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
        @Param("coSupervision") String coSupervision,
        @Param("coUnidadMinera") String coUnidadMinera,
        @Param("idFaseDestinoFisc") Long idFaseDestinoFisc,
        @Param("idPasoDestinoFisc") Long idPasoDestinoFisc,
        @Param("idTipoEntregable") Long idTipoEntregable,
        @Param("nuLiquidacion") String nuLiquidacion,
        @Param("coExpSigedLiquidacion") String coExpSigedLiquidacion,
        @Param("idFaseDestinoLiquidacion") Long idFaseDestinoLiquidacion,
        @Param("idPasoDestinoLiquidacion") Long idPasoDestinoLiquidacion,
        @Param("idEstadoConsumo") Long idEstadoConsumo,
        Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContrLiquidacionAuxDTOResultado( "
        + "liqu.idContrLiquidacionAux, liqu.idEspecialidad, liqu.noEspecialidad, liqu.noDivisionSupervisora,  liqu.coSupervision, liqu.feInicioSupervisionReal,  "
        + "liqu.feFinSupervisionReal, liqu.coExpedienteSigedFisc,  liqu.deSubtipoFisc, liqu.deMotivoFisc, liqu.coUnidadMinera, liqu.noUnidadMinera, liqu.coRucAFisc,   "
        + "liqu.noRazonSocialAFisc, liqu.noFaseOrigenFisc, liqu.noPasoOrigenFisc, liqu.noFaseDestinoFisc,  liqu.noPasoDestinoFisc, liqu.noPersonaOrigenFisc,  "
        + "liqu.noPersonaDestinoFisc, liqu.feTareaFisc,  liqu.deMensajeTareaFisc, liqu.nuContrato, liqu.feInicioContrato, liqu.feFinContrato, liqu.montoConsumoContrato,  "
        + "liqu.noTipoEntregable, liqu.noEstadoConsumo, liqu.montoItemConsumo, liqu.montoItemPenalidad, liqu.montoItemFiscFallida, liqu.nuLiquidacion,  "
        + "liqu.coExpSigedLiquidacion, liqu.noFaseOrigenLiquidacion,  liqu.noPasoOrigenLiquidacion, liqu.noFaseDestinoLiquidacion,  "
        + "liqu.noPasoDestinoLiquidacion,  liqu.noPersonaOrigenLiqu, liqu.noPersonaDestinoLiqu, liqu.feTareaLiquidacion, liqu.deMensajeTareaLiqu,  "
        + "liqu.idDivisionSupervisora,liqu.idFaseDestinoFisc,liqu.idPasoDestinoFisc,liqu.idTipoEntregable,liqu.idFaseDestinoLiquidacion, " 
        + "liqu.idPasoDestinoLiquidacion,liqu.fiscEsPropia,liqu.idEstadoConsumo "
        + ") "
        + "FROM PgimContrLiquidacionAux liqu "
        + "WHERE 1 = 1 "
        + "AND (:idEspecialidad IS NULL OR liqu.idEspecialidad=:idEspecialidad ) " 
        + "AND (:idDivisionSupervisora IS NULL OR liqu.idDivisionSupervisora = :idDivisionSupervisora) "
        + "AND (:coSupervision IS NULL OR liqu.coSupervision LIKE :coSupervision) "
        + "AND (:coUnidadMinera IS NULL OR liqu.coUnidadMinera LIKE :coUnidadMinera) "
        + "AND (:idFaseDestinoFisc IS NULL OR liqu.idFaseDestinoFisc =: idFaseDestinoFisc) "
        + "AND (:idPasoDestinoFisc IS NULL OR liqu.idPasoDestinoFisc =: idPasoDestinoFisc) "
        + "AND (:idTipoEntregable IS NULL OR liqu.idTipoEntregable =: idTipoEntregable) "
        + "AND (:nuLiquidacion IS NULL OR liqu.nuLiquidacion LIKE :nuLiquidacion ) "
        + "AND (:coExpSigedLiquidacion IS NULL OR liqu.coExpSigedLiquidacion LIKE CONCAT('%', :coExpSigedLiquidacion, '%') ) "
        + "AND (:idFaseDestinoLiquidacion IS NULL OR liqu.idFaseDestinoLiquidacion =: idFaseDestinoLiquidacion) "
        + "AND (:idPasoDestinoLiquidacion IS NULL OR liqu.idPasoDestinoLiquidacion =: idPasoDestinoLiquidacion) "
        + "AND (:idEstadoConsumo IS NULL OR liqu.idEstadoConsumo =: idEstadoConsumo) "
        )
    List<PgimContrLiquidacionAuxDTO> listarContrLiquidacionAux(
        @Param("idEspecialidad") Long idEspecialidad,
        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
        @Param("coSupervision") String coSupervision,
        @Param("coUnidadMinera") String coUnidadMinera,
        @Param("idFaseDestinoFisc") Long idFaseDestinoFisc,
        @Param("idPasoDestinoFisc") Long idPasoDestinoFisc,
        @Param("idTipoEntregable") Long idTipoEntregable,
        @Param("nuLiquidacion") String nuLiquidacion,
        @Param("coExpSigedLiquidacion") String coExpSigedLiquidacion,
        @Param("idFaseDestinoLiquidacion") Long idFaseDestinoLiquidacion,
        @Param("idPasoDestinoLiquidacion") Long idPasoDestinoLiquidacion,
        @Param("idEstadoConsumo") Long idEstadoConsumo,
        Sort sort);
    
}
