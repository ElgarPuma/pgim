package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimEntregableLiquidaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEntregableLiquidaAux;

/**
 * Éste interface EntregableLiquidacionAuxRepository
 * que aplica obtener y listar entregables por liquidacion.
 * 
 * @descripción: Logica de negocio de la entidad Entregable liquidacion.
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface EntregableLiquidacionAuxRepository extends JpaRepository<PgimEntregableLiquidaAux, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEntregableLiquidaAuxDTOResultado( "
    + "auxi.idEntregableLiquidaAux, auxi.pgimItemConsumo.idItemConsumo, auxi.idTipoEntregable, "
    + "auxi.deTipoEntregable, auxi.idSupervision, auxi.coSupervision, "
    + "auxi.deTipoSupervision, auxi.idSubtipoSupervision, auxi.deSubtipoSupervision, "
    + "auxi.idMotivoSupervision, auxi.deMotivoSupervision, auxi.feInicioSupervisionReal, "
    + "auxi.feFinSupervisionReal, auxi.idAgenteSupervisado, auxi.coDocumentoIdentidad, "
    + "auxi.noRazonSocial, auxi.moConsumoContrato, auxi.moItemConsumo, "
    + "auxi.idDocumento, auxi.deAsuntoDocumento, auxi.idSubcatDocumento, "
    + "auxi.coSubcatDocumento, auxi.noSubcatDocumento, auxi.deMensaje, "
    + "auxi.feOrigenDocumento, auxi.feInstanciaPaso, auxi.idContrato, "
    + "auxi.nuContrato, auxi.idDivisionSupervisora, auxi.deDivisionSupervisora, "
    + "auxi.coUnidadMinera, auxi.noUnidadMinera, auxi.nuExpedienteSiged, auxi.idTipoEstadioConsumo, "
    + "auxi.deTipoEstadioConsumo, auxi.fePresentaEntregable "
    + ") "
    + "FROM PgimEntregableLiquidaAux auxi "
    + "WHERE auxi.idContrato = :idContrato "
    + "AND auxi.idTipoEntregable = :idTipoEntregable "
    + "AND auxi.idDivisionSupervisora = :idDivisionSupervisora "
    + "AND auxi.idSubtipoSupervision = :idSubtipoSupervision "
    + "AND auxi.idMotivoSupervision = :idMotivoSupervision "
    + "AND NOT EXISTS ( "
    + "                 SELECT 1 "
    + "                 FROM PgimItemLiquidacion itli "
    + "                      INNER JOIN itli.pgimLiquidacion liqu "    
    + "                 WHERE itli.pgimItemConsumo.idItemConsumo = auxi.pgimItemConsumo.idItemConsumo "
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
    + "ORDER BY auxi.feInstanciaPaso DESC"
    )
	List<PgimEntregableLiquidaAuxDTO> obtenerEntregablesALiquidar(@Param("idContrato") Long idContrato, @Param("idTipoEntregable") Long idTipoEntregable, @Param("idDivisionSupervisora") Long idDivisionSupervisora, 
                                                                  @Param("idSubtipoSupervision") Long idSubtipoSupervision, @Param("idMotivoSupervision") Long idMotivoSupervision);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEntregableLiquidaAuxDTOResultado( "
    + "auxi.idEntregableLiquidaAux, auxi.pgimItemConsumo.idItemConsumo, auxi.idTipoEntregable, "
    + "auxi.deTipoEntregable, auxi.idSupervision, auxi.coSupervision, "
    + "auxi.deTipoSupervision, auxi.idSubtipoSupervision, auxi.deSubtipoSupervision, "
    + "auxi.idMotivoSupervision, auxi.deMotivoSupervision, auxi.feInicioSupervisionReal, "
    + "auxi.feFinSupervisionReal, auxi.idAgenteSupervisado, auxi.coDocumentoIdentidad, "
    + "auxi.noRazonSocial, auxi.moConsumoContrato, auxi.moItemConsumo, "
    + "auxi.idDocumento, auxi.deAsuntoDocumento, auxi.idSubcatDocumento, "
    + "auxi.coSubcatDocumento, auxi.noSubcatDocumento, auxi.deMensaje, "
    + "auxi.feOrigenDocumento, auxi.feInstanciaPaso, auxi.idContrato, "
    + "auxi.nuContrato, auxi.idDivisionSupervisora, auxi.deDivisionSupervisora, "
    + "auxi.coUnidadMinera, auxi.noUnidadMinera, auxi.nuExpedienteSiged, auxi.idTipoEstadioConsumo, auxi.deTipoEstadioConsumo, "
    + "auxi.fePresentaEntregable ) "
    + "FROM PgimEntregableLiquidaAux auxi "
    + "WHERE auxi.idContrato = :idContrato "
    + "AND auxi.idTipoEntregable = :idTipoEntregable "
    + "AND NOT EXISTS ( "
    + "                 SELECT 1 "
    + "                 FROM PgimItemLiquidacion itli "
    + "                      INNER JOIN itli.pgimLiquidacion liqu "    
    + "                 WHERE itli.pgimItemConsumo.idItemConsumo = auxi.pgimItemConsumo.idItemConsumo "
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
    + "ORDER BY auxi.feInstanciaPaso DESC"
    )
	List<PgimEntregableLiquidaAuxDTO> obtenerEntregablesALiquidar(@Param("idContrato") Long idContrato, @Param("idTipoEntregable") Long idTipoEntregable);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEntregableLiquidaAuxDTOResultado( "
    + "auxi.idEntregableLiquidaAux, auxi.pgimItemConsumo.idItemConsumo, auxi.idTipoEntregable, "
    + "auxi.deTipoEntregable, auxi.idSupervision, auxi.coSupervision, "
    + "auxi.deTipoSupervision, auxi.idSubtipoSupervision, auxi.deSubtipoSupervision, "
    + "auxi.idMotivoSupervision, auxi.deMotivoSupervision, auxi.feInicioSupervisionReal, "
    + "auxi.feFinSupervisionReal, auxi.idAgenteSupervisado, auxi.coDocumentoIdentidad, "
    + "auxi.noRazonSocial, auxi.moConsumoContrato, auxi.moItemConsumo, "
    + "auxi.idDocumento, auxi.deAsuntoDocumento, auxi.idSubcatDocumento, "
    + "auxi.coSubcatDocumento, auxi.noSubcatDocumento, auxi.deMensaje, "
    + "auxi.feOrigenDocumento, auxi.feInstanciaPaso, auxi.idContrato, "
    + "auxi.nuContrato, auxi.idDivisionSupervisora, auxi.deDivisionSupervisora, "
    + "auxi.coUnidadMinera, auxi.noUnidadMinera, auxi.nuExpedienteSiged, auxi.idTipoEstadioConsumo, auxi.deTipoEstadioConsumo, "
    + "auxi.fePresentaEntregable ) "
    + "FROM PgimEntregableLiquidaAux auxi "
    + "WHERE auxi.idTipoEntregable = :idTipoEntregable "
    + "AND EXISTS ( "
    + "             SELECT 1 "
    + "             FROM PgimItemLiquidacion itli "
    + "                  INNER JOIN itli.pgimLiquidacion liqu "
    + "             WHERE liqu.idLiquidacion = :idLiquidacion "
    + "             AND itli.pgimItemConsumo.idItemConsumo = auxi.pgimItemConsumo.idItemConsumo "
    + "             AND itli.esRegistro = '1' "
    + "             AND liqu.esRegistro = '1' "
    + "             ) "
    + "ORDER BY auxi.feInstanciaPaso DESC"
    )
	List<PgimEntregableLiquidaAuxDTO> listarEntregablesPorLiquidacion(@Param("idLiquidacion") Long idLiquidacion, @Param("idTipoEntregable") Long idTipoEntregable);
}
