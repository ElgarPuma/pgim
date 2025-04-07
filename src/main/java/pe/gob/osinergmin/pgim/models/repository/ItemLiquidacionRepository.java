package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemLiquidacion;

/**
 * Ésta interface ItemLiquidacionRepository
 * 
 * @descripción: Logica de negocio para gestionar la interación con la base de datos, específicamente con el item de liquidación.
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 02/12/2020
 * @fecha_de_ultima_actualización: 
 */
public interface ItemLiquidacionRepository extends JpaRepository<PgimItemLiquidacion, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTOResultado("
            + "pil.idItemLiquidacion, pil.pgimLiquidacion.idLiquidacion, pil.pgimItemConsumo.idItemConsumo, "
            + "pil.pgimDocumento.idDocumento, pil.moPenalidadPlazo, pil.moPenalidadReincidencia, pil.moPenalidadSinEpp, "
            + "pil.moPenalidadEppAFiscalizado, pil.moPenalidadEqpDefectuosos, "
            + "pil.moPenalidadEqpMedicionDefectuosos, pil.moPenalidadSinEquipos, "
            + "pil.moPenalidadEqpCalibrNvigente, pil.moPenalidadSinInstrumentos, "
            + "pil.moPenalidadInstrCalibrNvigente, pil.moPenalidadAlterarFormatos, pil.moPenalidadFrustrarFiscalizacion "
            + ") "
            + "FROM PgimItemLiquidacion pil "
            + "WHERE pil.esRegistro = '1' ")
    List<PgimItemLiquidacionDTO> listarItemLiquidacion();
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTOResultado("
			+ "unmi.noUnidadMinera, pers.noRazonSocial, supe.coSupervision, "
            + "supe.pgimSubtipoSupervision.deSubtipoSupervision, supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro, "
            + "coco.moConsumoContrato, itco.moItemConsumo, itlq.pgimDocumento.idDocumento, "
            + "liqu.idLiquidacion, itlq.idItemLiquidacion, "
            + "supe.pgimInstanciaProces.nuExpedienteSiged,"
            + "coco.pgimContrato.idContrato,"
            + "supe.feInicioSupervisionReal,"
            + "supe.feFinSupervisionReal, itlq.pgimDocumento.deAsuntoDocumento, "
            + "supe.pgimPrgrmSupervision.divisionSupervisora.noValorParametro, "
            + "supe.pgimInstanciaProces.idInstanciaProceso, "
            + "supe.pgimMotivoSupervision.deMotivoSupervision, "
            + "(itlq.moPenalidadPlazo + itlq.moPenalidadReincidencia + itlq.moPenalidadSinEpp),"
            + "itlq.moPenalidadPlazo, itlq.moPenalidadReincidencia, itlq.moPenalidadSinEpp, supe.idSupervision, "
            + "itlq.moPenalidadEppAFiscalizado, itlq.moPenalidadSinEquipos, itlq.moPenalidadSinInstrumentos, itlq.moPenalidadEqpDefectuosos, "
            + "itlq.moPenalidadEqpMedicionDefectuosos, itlq.moPenalidadEqpCalibrNvigente, itlq.moPenalidadInstrCalibrNvigente, " 
            + "(NVL(itlq.moPenalidadSinEpp,0.00) + NVL(itlq.moPenalidadEppAFiscalizado,0.00) + NVL(itlq.moPenalidadSinEquipos,0.00) + NVL(itlq.moPenalidadSinInstrumentos,0.00) + NVL(itlq.moPenalidadEqpDefectuosos,0.00) + NVL(itlq.moPenalidadEqpMedicionDefectuosos,0.00) + NVL(itlq.moPenalidadEqpCalibrNvigente,0.00) + NVL(itlq.moPenalidadInstrCalibrNvigente,0.00) ), "
            + "itlq.moPenalidadAlterarFormatos, itlq.moPenalidadFrustrarFiscalizacion, " 
            + "(NVL(itlq.moPenalidadPlazo,0.00) + NVL(itlq.moPenalidadSinEpp,0.00) + NVL(itlq.moPenalidadEppAFiscalizado,0.00) + NVL(itlq.moPenalidadSinEquipos,0.00) + NVL(itlq.moPenalidadSinInstrumentos,0.00) + NVL(itlq.moPenalidadEqpDefectuosos,0.00) + NVL(itlq.moPenalidadEqpMedicionDefectuosos,0.00) + NVL(itlq.moPenalidadEqpCalibrNvigente,0.00) + NVL(itlq.moPenalidadInstrCalibrNvigente,0.00) + NVL(itlq.moPenalidadAlterarFormatos,0.00) + NVL(itlq.moPenalidadFrustrarFiscalizacion,0.00)), " 
            + "supe.flPreexistenteNpenalidades "
            + ") "
            + "FROM PgimItemLiquidacion itlq "
            + "     INNER JOIN itlq.pgimLiquidacion liqu "
            + "     INNER JOIN itlq.pgimItemConsumo itco "
            + "     INNER JOIN itco.pgimConsumoContra coco "
            + "     LEFT OUTER JOIN coco.pgimSupervision supe "
            + "     LEFT OUTER JOIN supe.pgimUnidadMinera unmi "
            + "     LEFT OUTER JOIN unmi.pgimAgenteSupervisado agfi "
            + "     LEFT OUTER JOIN agfi.pgimPersona pers "
            + "WHERE liqu.idLiquidacion = :idLiquidacion "
            + "AND itlq.esRegistro = '1' "
            + "AND itco.esVigente = '1' "
            + "AND itco.esRegistro = '1' "
            + "AND coco.esRegistro = '1' "
            + "AND NOT EXISTS ( "
            + "                 SELECT 1 "
            + "                 FROM PgimInstanciaPaso inpa "
            + "                      INNER JOIN inpa.pgimInstanciaProces inpr "
            + "                      INNER JOIN inpa.pgimRelacionPaso repa "
            + "                 WHERE inpr.idInstanciaProceso = liqu.pgimInstanciaProces.idInstanciaProceso  "
            + "                 AND repa.idRelacionPaso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_RELACION_ELABORARSOLLQ_LIQANULADA "
            + "                 AND inpa.esRegistro = '1' "
            + "                 AND inpr.esRegistro = '1' "
            + "                 AND repa.esRegistro = '1' "
            + "                 ) "            
            )
    List<PgimItemLiquidacionDTO> listarItemLiquidacionActasInformes(@Param("idLiquidacion") Long idLiquidacion);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTOResultado("
            + "pil.idItemLiquidacion, pil.pgimLiquidacion.idLiquidacion, pil.pgimItemConsumo.idItemConsumo, "
            + "pil.pgimDocumento.idDocumento, pil.moPenalidadPlazo, pil.moPenalidadReincidencia, pil.moPenalidadSinEpp, "
            + "pil.moPenalidadEppAFiscalizado, pil.moPenalidadEqpDefectuosos, "
            + "pil.moPenalidadEqpMedicionDefectuosos, pil.moPenalidadSinEquipos, "
            + "pil.moPenalidadEqpCalibrNvigente, pil.moPenalidadSinInstrumentos, "
            + "pil.moPenalidadInstrCalibrNvigente, pil.moPenalidadAlterarFormatos, pil.moPenalidadFrustrarFiscalizacion "
            + ") "
            + "FROM PgimItemLiquidacion pil "
            + "WHERE pil.idItemLiquidacion = :idItemLiquidacion "
            + "AND pil.esRegistro = '1' ")
	PgimItemLiquidacionDTO obtenerItemLiquidacionPorId(@Param("idItemLiquidacion") Long idItemLiquidacion);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTOResultado("
    + "pil.idItemLiquidacion, pil.pgimLiquidacion.idLiquidacion, pil.pgimItemConsumo.idItemConsumo, "
    + "pil.pgimDocumento.idDocumento, pil.moPenalidadPlazo, pil.moPenalidadReincidencia, pil.moPenalidadSinEpp, "
    + "pil.moPenalidadEppAFiscalizado, pil.moPenalidadEqpDefectuosos, "
    + "pil.moPenalidadEqpMedicionDefectuosos, pil.moPenalidadSinEquipos, "
    + "pil.moPenalidadEqpCalibrNvigente, pil.moPenalidadSinInstrumentos, "
    + "pil.moPenalidadInstrCalibrNvigente, pil.moPenalidadAlterarFormatos, pil.moPenalidadFrustrarFiscalizacion "
    + ") "
    + "FROM PgimItemLiquidacion pil INNER JOIN pil.pgimLiquidacion liq "
    + "WHERE liq.idLiquidacion = :idLiquidacion "
    + "AND liq.tipoEntregable.idValorParametro = :idTipoEntregable "
    + "AND pil.esRegistro = '1' ")    
	List<PgimItemLiquidacionDTO> obtenerItemsLiquidacionTipoEntregable(@Param("idLiquidacion") Long idLiquidacion, @Param("idTipoEntregable") Long idTipoEntregable);
    
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTOResultado("
            + "pil.idItemLiquidacion, pil.pgimLiquidacion.idLiquidacion, pil.pgimItemConsumo.idItemConsumo, "
            + "pil.pgimDocumento.idDocumento, supe.coSupervision, itco.tipoEntregable.noValorParametro "
            + ") "
            + "FROM PgimItemLiquidacion pil "
            + "INNER JOIN pil.pgimLiquidacion liqu "
            + "INNER JOIN pil.pgimItemConsumo itco "
            + "INNER JOIN itco.pgimConsumoContra coco "
            + "LEFT JOIN coco.pgimSupervision supe "
            + "WHERE pil.pgimItemConsumo.idItemConsumo = :idItemConsumo "
            + "AND pil.esRegistro = '1' "
            + "AND liqu.esRegistro = '1' "
            + "AND NOT EXISTS ( "
            + "                 SELECT 1 "
            + "                 FROM PgimInstanciaPaso inpa "
            + "                      INNER JOIN inpa.pgimInstanciaProces inpr "
            + "                      INNER JOIN inpa.pgimRelacionPaso repa "
            + "                 WHERE inpr.idInstanciaProceso = liqu.pgimInstanciaProces.idInstanciaProceso  "
            + "                 AND repa.idRelacionPaso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_RELACION_ELABORARSOLLQ_LIQANULADA "
            + "                 AND inpa.esRegistro = '1' "
            + "                 AND inpr.esRegistro = '1' "
            + "                 AND repa.esRegistro = '1' "
            + "                 ) "
            )
	List<PgimItemLiquidacionDTO> listarItemLiquidacionPorItemConsumo(@Param("idItemConsumo") Long idItemConsumo);

}
