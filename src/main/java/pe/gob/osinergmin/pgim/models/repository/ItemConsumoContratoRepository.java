package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConsumoContra;
import pe.gob.osinergmin.pgim.models.entity.PgimItemConsumo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Ésta interface ItemConsumoContratoRepository
 * 
 * @descripción: Logica de negocio para gestionar la interación con la base de
 *               datos, específicamente con el item de consumo.
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 02/12/2020
 * @fecha_de_ultima_actualización:
 */
@Repository
public interface ItemConsumoContratoRepository extends JpaRepository<PgimItemConsumo, Long> {

        /**
         * Permite obtener el listado de pasos x Relación de paso.
         * 
         * @param idConsumoContra
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTOResultado( "
                        + "ico.idItemConsumo, ico.pgimConsumoContra.idConsumoContra, ico.tipoEntregable.idValorParametro, "
                        + "ico.tipoEstadioConsumo.idValorParametro, ico.pcEntregable, ico.moItemConsumo, "
                        + "ico.moItemPenalidad,ico.moItemSupervisionFallida,ico.esVigente) "
                        + "FROM PgimItemConsumo ico " 
                        + "WHERE ico.esRegistro = '1' " 
                        + "AND ico.esVigente = '1' "
                        + "AND ico.pgimConsumoContra.idConsumoContra = :idConsumoContra ")
        List<PgimItemConsumoDTO> obtenerListaItemConsumoVigentes(@Param("idConsumoContra") Long idConsumoContra);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTOResultado( "
                        + "ico.idItemConsumo, coco.idConsumoContra, tien.idValorParametro, "
                        + "tien.noValorParametro, tiec.idValorParametro, tiec.noValorParametro, "
                        + "ico.pcEntregable, ico.moItemConsumo, ico.moItemPenalidad, "
                        + "ico.moItemSupervisionFallida, ico.esVigente) "
                        + "FROM PgimItemConsumo ico "
                        + "INNER JOIN ico.pgimConsumoContra coco "
                        + "INNER JOIN ico.tipoEntregable tien "
                        + "INNER JOIN ico.tipoEstadioConsumo tiec "
                        + "INNER JOIN coco.pgimSupervision supe "
                        + "WHERE ico.esRegistro = '1' "
                        + "AND ico.esVigente = '1' "
                        + "AND coco.esRegistro = '1' "
                        + "AND tiec.esRegistro = '1' "
                        + "AND tien.esRegistro = '1' "
                        + "AND supe.esRegistro = '1' "
                        + "AND supe.idSupervision = :idSupervision ")
        List<PgimItemConsumoDTO> obtenerListaItemConsumoVigentesSupervision(@Param("idSupervision") Long idSupervision);

        @Query("SELECT SUM(itco.moItemConsumo) " + "FROM PgimItemConsumo itco "
                        + "INNER JOIN itco.pgimConsumoContra coco "
                        + "WHERE coco.pgimContrato.idContrato = :idContrato " + "AND itco.esVigente = '1' "
                        + "AND itco.esRegistro = '1' " + "AND coco.esRegistro = '1'")
        BigDecimal obtenerConsumoContratoTotalizado(@Param("idContrato") Long idContrato);

        List<PgimItemConsumo> findByPgimConsumoContraAndEsVigente(PgimConsumoContra pgimConsumoContra, String string);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTOResultado( "
                        + "auxi.idItemConsumo, auxi.tipoEntregable.idValorParametro, "
                        + "auxi.tipoEntregable.noValorParametro, auxi.pgimConsumoContra.pgimSupervision.idSupervision, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.coSupervision, "
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimSubtipoSupervision.tipoSupervision.noValorParametro, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimSubtipoSupervision.idSubtipoSupervision, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimSubtipoSupervision.deSubtipoSupervision, "
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimMotivoSupervision.idMotivoSupervision, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimMotivoSupervision.deMotivoSupervision, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.feInicioSupervisionReal, "
                        + "auxi.pgimConsumoContra.pgimSupervision.feFinSupervisionReal, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.pgimAgenteSupervisado.idAgenteSupervisado, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.pgimAgenteSupervisado.pgimPersona.coDocumentoIdentidad, "
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.pgimAgenteSupervisado.pgimPersona.noRazonSocial, " 
                        + "auxi.pgimConsumoContra.moConsumoContrato, auxi.moItemConsumo, "
                        + "auxi.pgimConsumoContra.pgimContrato.idContrato, auxi.pgimConsumoContra.pgimContrato.nuContrato, "
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.divisionSupervisora.idValorParametro, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.divisionSupervisora.noValorParametro, "
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.coUnidadMinera, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.noUnidadMinera, " 
                        + "auxi.pgimConsumoContra.pgimSupervision.pgimInstanciaProces.nuExpedienteSiged, " 
                        + "auxi.tipoEstadioConsumo.idValorParametro, auxi.tipoEstadioConsumo.noValorParametro) "
                        + "FROM PgimItemConsumo auxi "
                        + "WHERE auxi.pgimConsumoContra.pgimContrato.idContrato = :idContrato "
                        + "AND auxi.esVigente = '1' "
                        + "AND auxi.esRegistro = '1' "
                        + "AND auxi.tipoEntregable.esRegistro = '1' "
                        + "AND auxi.pgimConsumoContra.esRegistro = '1' "
                        + "AND auxi.pgimConsumoContra.pgimSupervision.esRegistro = '1' "
                        + "AND auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.esRegistro = '1' "
                        + "AND auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.pgimAgenteSupervisado.esRegistro = '1' "
                        + "AND auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.pgimAgenteSupervisado.pgimPersona.esRegistro = '1' "
                        + "AND auxi.pgimConsumoContra.pgimContrato.esRegistro = '1' "
                        + "AND auxi.pgimConsumoContra.pgimSupervision.pgimUnidadMinera.divisionSupervisora.esRegistro = '1' "
                        + "AND auxi.pgimConsumoContra.pgimSupervision.pgimInstanciaProces.esRegistro = '1' "
                        + "AND auxi.tipoEstadioConsumo.esRegistro = '1' "
                        + "AND (auxi.tipoEstadioConsumo.idValorParametro = :descPreComprometido "
                        + "OR auxi.tipoEstadioConsumo.idValorParametro = :descComprometido "
                        + "OR auxi.tipoEstadioConsumo.idValorParametro = :descPorLiquidar "
                        + "OR auxi.tipoEstadioConsumo.idValorParametro = :descLiquidado "
                        + "OR auxi.tipoEstadioConsumo.idValorParametro = :descFacturado "
                        + ") "
                        + "AND (:descIdTipoEntregable IS NULL OR auxi.tipoEntregable.idValorParametro = :descIdTipoEntregable ) ")
        Page<PgimItemConsumoDTO> listarEntregablesPorContrato(@Param("idContrato") Long idContrato,
                        @Param("descPreComprometido") Long descPreComprometido, @Param("descComprometido") Long descComprometido,
                        @Param("descPorLiquidar") Long descPorLiquidar, @Param("descLiquidado") Long descLiquidado,
                        @Param("descFacturado") Long descFacturado, @Param("descIdTipoEntregable") Long descIdTipoEntregable,
                        Pageable paginador);

        /**
         * STORY: PGIM-6809: Lista de entregables disponibles para liquidar
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTOResultado( "
                        + "auxi.idEntregableLiquidaAux, auxi.moItemConsumo, itcons.moItemPenalidad, "
                        + "itcons.moItemSupervisionFallida, auxi.idTipoEntregable, auxi.deTipoEntregable, "
                        + "auxi.idTipoEstadioConsumo, auxi.deTipoEstadioConsumo, auxi.idContrato, "
                        + "auxi.nuContrato, auxi.idSupervision, auxi.coSupervision, " 
                        + "auxi.idSubtipoSupervision, auxi.deSubtipoSupervision, auxi.idTipoSupervision, "
                        + "auxi.deTipoSupervision, auxi.idMotivoSupervision, auxi.deMotivoSupervision, "
                        + "auxi.coUnidadMinera, auxi.noUnidadMinera, auxi.idDivisionSupervisora, "
                        + "auxi.deDivisionSupervisora "
                        + ") "
                        + "FROM PgimEntregableLiquidaAux auxi "
                        + "     INNER JOIN auxi.pgimItemConsumo itcons "
                        + "WHERE 1 = 1 "
                        + "AND auxi.idTipoEntregable != pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMESGESTION "
                        + "AND NOT EXISTS ( "
                        + "                 SELECT 1 "
                        + "                 FROM PgimItemLiquidacion itli "
                        + "                      INNER JOIN itli.pgimLiquidacion liqu "
                        + "                 WHERE itli.pgimItemConsumo.idItemConsumo = itcons.idItemConsumo "
                        + "                 AND itli.esRegistro = '1' "
                        + "                 AND liqu.esRegistro = '1' "
                        + "                 AND NOT EXISTS ( "
                        + "                                 SELECT 1 "
                        + "                                 FROM PgimInstanciaPaso inpa "
                        + "                                      INNER JOIN inpa.pgimInstanciaProces inpr "
                        + "                                      INNER JOIN inpa.pgimRelacionPaso repa "
                        + "                                 WHERE inpr.idInstanciaProceso = liqu.pgimInstanciaProces.idInstanciaProceso  "
                        + "                                 AND repa.idRelacionPaso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_RELACION_ELABORARSOLLQ_LIQANULADA "
                        + "                                 AND inpa.flEsPasoActivo = '1' "
                        + "                                 AND inpa.esRegistro = '1' "
                        + "                                 AND inpr.esRegistro = '1' "
                        + "                                 AND repa.esRegistro = '1' "
                        + "                                 ) "
                        + "                 ) "
                        + "AND (:descIdContrato IS NULL OR auxi.idContrato = :descIdContrato) "
                        + "AND (:descNuContrato IS NULL OR LOWER(auxi.nuContrato) LIKE LOWER(CONCAT('%', :descNuContrato, '%')) ) "
                        + "AND (:descIdTipoEntregable IS NULL OR auxi.idTipoEntregable = :descIdTipoEntregable) "
                        + "AND (:descCoSupervision IS NULL OR LOWER(auxi.coSupervision) LIKE LOWER(CONCAT('%', :descCoSupervision, '%')) ) "
                        + "AND (:descIdTipoSupervision IS NULL OR auxi.idTipoSupervision = :descIdTipoSupervision) "
                        + "AND (:descIdSubtipoSupervision IS NULL OR auxi.idSubtipoSupervision = :descIdSubtipoSupervision) "
                        + "AND (:idMotivoSupervision IS NULL OR auxi.idMotivoSupervision = :idMotivoSupervision) "
                        + "AND (:textoBusqueda IS NULL OR ( "
                        + "LOWER(auxi.nuContrato) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(auxi.coSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(auxi.coUnidadMinera || ': ' || auxi.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + ")) "
                        )
        Page<PgimItemConsumoDTO> listarEntregablesPorLiquidarPorContrato(@Param("descIdContrato") Long descIdContrato,
                                                                         @Param("descNuContrato") String descNuContrato, 
                                                                         @Param("descIdTipoEntregable") Long descIdTipoEntregable,
                                                                         @Param("descCoSupervision") String descCoSupervision, 
                                                                         @Param("descIdTipoSupervision") Long descIdTipoSupervision,
                                                                         @Param("descIdSubtipoSupervision") Long descIdSubtipoSupervision, 
                                                                         @Param("idMotivoSupervision") Long idMotivoSupervision,
                                                                         @Param("textoBusqueda") String textoBusqueda, 
                                                                         Pageable paginador);
             
        
        /**
         * Permite obtener la cantidad de entregables listos para liquidar 
         * que cumplen con determinados atributos de un Grupo
         * 
         * @param descIdContrato
         * @param descIdTipoEntregable
         * @param descIdDivisionSupervisora
         * @param descIdTipoSupervision
         * @param descIdSubtipoSupervision
         * @param idMotivoSupervision
         * @return
         */
        @Query("SELECT count(auxi.idEntregableLiquidaAux) "
                + "FROM PgimEntregableLiquidaAux auxi "
                + "     INNER JOIN auxi.pgimItemConsumo itcons "
                + "WHERE 1 = 1 "
                + "AND auxi.idTipoEntregable != pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMESGESTION "
                + "AND NOT EXISTS ( "
                + "                 SELECT 1 "
                + "                 FROM PgimItemLiquidacion itli "
                + "                      INNER JOIN itli.pgimLiquidacion liqu "
                + "                 WHERE itli.pgimItemConsumo.idItemConsumo = itcons.idItemConsumo "
                + "                 AND itli.esRegistro = '1' "
                + "                 AND liqu.esRegistro = '1' "
                + "                 AND NOT EXISTS ( "
                + "                                 SELECT 1 "
                + "                                 FROM PgimInstanciaPaso inpa "
                + "                                      INNER JOIN inpa.pgimInstanciaProces inpr "
                + "                                      INNER JOIN inpa.pgimRelacionPaso repa "
                + "                                 WHERE inpr.idInstanciaProceso = liqu.pgimInstanciaProces.idInstanciaProceso  "
                + "                                 AND repa.idRelacionPaso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_RELACION_ELABORARSOLLQ_LIQANULADA "
                + "                                 AND inpa.flEsPasoActivo = '1' "
                + "                                 AND inpa.esRegistro = '1' "
                + "                                 AND inpr.esRegistro = '1' "
                + "                                 AND repa.esRegistro = '1' "
                + "                                 ) "
                + "                 ) "
                + "AND (auxi.idContrato = :descIdContrato) "
                + "AND (auxi.idTipoEntregable = :descIdTipoEntregable) "
                + "AND (auxi.idDivisionSupervisora = :descIdDivisionSupervisora) "
                + "AND (auxi.idTipoSupervision = :descIdTipoSupervision) "
                + "AND (auxi.idSubtipoSupervision = :descIdSubtipoSupervision) "
                + "AND (auxi.idMotivoSupervision = :idMotivoSupervision) "
                )
        Integer obtenerCantEntregablesXGrupo(@Param("descIdContrato") Long descIdContrato,
                                             @Param("descIdTipoEntregable") Long descIdTipoEntregable,
                                             @Param("descIdDivisionSupervisora") Long descIdDivisionSupervisora,
                                             @Param("descIdTipoSupervision") Long descIdTipoSupervision,
                                             @Param("descIdSubtipoSupervision") Long descIdSubtipoSupervision, 
                                             @Param("idMotivoSupervision") Long idMotivoSupervision
                                             );
   
}