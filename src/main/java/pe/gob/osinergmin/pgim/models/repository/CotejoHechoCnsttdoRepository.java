package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoCnsttdoFaseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCotejoHechoCnsttdo;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @descripción: Lógica de negocio de la entidad Cotejo de hecho constatado
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 05/11/2020
 *  
 */
@Repository
public interface CotejoHechoCnsttdoRepository extends JpaRepository<PgimCotejoHechoCnsttdo, Long> {
	
	/**
     * Permite obtener la lista cotejo de hechos constatados por supervisión paginado
     * @param  idSupervision 
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTOResultado( "
    		+ "chc.pgimHechoConstatado.idHechoConstatado, chc.deHechoConstatado, chc.deComplementoObservacion, "
            + "chc.deSustento, chc.tipoCumplimiento.idValorParametro, chc.deTipoCumplimiento, "
    		+ "chc.pgimSupervision.idSupervision, chc.pgimCriterioSprvsion.idCriterioSprvsion, chc.coMatrizCriterio, "
            + "chc.nuOrdenGrpoCrtrio, chc.hechoConstatadoOsi.idHechoConstatado, chc.deHechoConstatadoOsi, "
            + "chc.deComplementoObservacionOsi, chc.deSustentoOsi, chc.tipoCumplimientoOsi.idValorParametro, "
            + "chc.deTipoCumplimientoOsi, chc.deComentarioOsi, crit.coMatrizCriterio "
            + ") "            
            + "FROM PgimCotejoHechoCnsttdo chc "
            + "LEFT JOIN PgimHechoConstatado hv ON (chc.hechoConstatadoOsi.idHechoConstatado = hv.idHechoConstatado AND hv.esRegistro = '1') "
            + "LEFT JOIN PgimCriterioSprvsion crit ON (hv.pgimCriterioSprvsion.idCriterioSprvsion = crit.idCriterioSprvsion AND crit.esRegistro = '1') "
            + "WHERE chc.pgimSupervision.idSupervision = :idSupervision "            
            + "AND (:codigoCumpleSupervisora = 0L OR chc.coTipoCumplimiento = :codigoCumpleSupervisora) "
            + "AND (:codigoCumpleOsinergmin = 0L OR chc.coTipoCumplimientoOsi = :codigoCumpleOsinergmin) "
            + "ORDER BY chc.nuOrdenGrpoCrtrio,chc.coMatrizCriterio,chc.pgimHechoConstatado.idHechoConstatado ASC "
            )
    Page<PgimCotejoHechoCnsttdoDTO> listarCotejoHechosConstatadosPaginado(
    		@Param("idSupervision") Long idSupervision,
    		@Param("codigoCumpleSupervisora") Long codigoCumpleSupervisora,
    		@Param("codigoCumpleOsinergmin") Long codigoCumpleOsinergmin,
    		Pageable paginador
    		);
    
    
    /**
     * Permite obtener la lista cotejo de hechos constatados por supervisión
     * @param  idSupervision 
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTOResultado "
    		+ "(chc.pgimHechoConstatado.idHechoConstatado,chc.deHechoConstatado,chc.deComplementoObservacion,chc.deSustento, "
    		+ "chc.tipoCumplimiento.idValorParametro,chc.deTipoCumplimiento, "
    		+ "chc.pgimSupervision.idSupervision,chc.pgimCriterioSprvsion.idCriterioSprvsion,chc.coMatrizCriterio,chc.nuOrdenGrpoCrtrio, "    		
    		+ "chc.idFaseProceso, "
    		
    		+ "chc.hechoConstatadoOsi.idHechoConstatado,chc.deHechoConstatadoOsi,chc.deComplementoObservacionOsi,chc.deSustentoOsi, "
    		+ "chc.tipoCumplimientoOsi.idValorParametro,chc.deTipoCumplimientoOsi,chc.deComentarioOsi, "    	
    		+ "chc.idFaseProcesoOsi "
    		
            + ") "            
            + "FROM PgimCotejoHechoCnsttdo chc "
            + "WHERE chc.pgimSupervision.idSupervision = :idSupervision "
            + "AND (:codigoCumple=0L OR chc.coTipoCumplimiento = :codigoCumple) "
            + "ORDER BY chc.pgimHechoConstatado.idHechoConstatado ASC "
            )
    List<PgimCotejoHechoCnsttdoDTO> listarCotejoHechosConstatados(
    		@Param("idSupervision") Long idSupervision,
    		@Param("codigoCumple") Long codigoCumple
    		);
    
    
    /**
     * Permite obtener la lista cotejo de hechos constatados por supervisión, y que estén pendientes de la conformidad del ET y/o EL
     * @param  idSupervision 
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTOResultado "
    		+ "(chc.pgimHechoConstatado.idHechoConstatado,chc.deHechoConstatado,chc.deComplementoObservacion,chc.deSustento, "
    		+ "chc.tipoCumplimiento.idValorParametro,chc.deTipoCumplimiento, "
    		+ "chc.pgimSupervision.idSupervision,chc.pgimCriterioSprvsion.idCriterioSprvsion,chc.coMatrizCriterio,chc.nuOrdenGrpoCrtrio, "    		
    		+ "chc.hechoConstatadoOsi.idHechoConstatado,chc.deHechoConstatadoOsi,chc.deComplementoObservacionOsi,chc.deSustentoOsi, "
    		+ "chc.tipoCumplimientoOsi.idValorParametro,chc.deTipoCumplimientoOsi,chc.deComentarioOsi, crit.coMatrizCriterio "    		
            + ") "            
            + "FROM PgimCotejoHechoCnsttdo chc "
            + "LEFT JOIN PgimHechoConstatado hv ON (chc.hechoConstatadoOsi.idHechoConstatado = hv.idHechoConstatado AND hv.esRegistro = '1') "
            + "LEFT JOIN PgimCriterioSprvsion crit ON (hv.pgimCriterioSprvsion.idCriterioSprvsion = crit.idCriterioSprvsion AND crit.esRegistro = '1') "
            + "WHERE chc.pgimSupervision.idSupervision = :idSupervision "
            + "AND (:codigoCumple=0L OR chc.coTipoCumplimiento = :codigoCumple) "
            + "AND chc.pgimHechoConstatado.idHechoConstatado is not null "
            + "AND chc.hechoConstatadoOsi.idHechoConstatado is null "
            )
    List<PgimCotejoHechoCnsttdoDTO> listarCotejoHCpendientesConformidad(
    		@Param("idSupervision") Long idSupervision,
    		@Param("codigoCumple") Long codigoCumple
    		//Pageable paginador
    		);
    
    
    
    /**
     * Permite obtener la lista histórica de hechos constatados por fase
     * @param  idSupervision
     * @param  idFase
     * @param  feGeneracion
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoCnsttdoFaseDTOResultado "   		
    		+ "(chc.idHechoConstatadoHist,chc.idHechoConstatado,chc.deHechoConstatado,chc.deComplementoObservacion,chc.deSustento, "
    		+ "chc.idTipoCumplimiento,chc.deTipoCumplimiento, "
    		+ "chc.idSupervision,chc.idCriterioSprvsion,chc.coMatrizCriterio,chc.nuOrdenGrpoCrtrio, "    		
    		+ "chc.idHechoConstatadoOsi,chc.deHechoConstatadoOsi,chc.deComplementoObservacionOsi,chc.deSustentoOsi, "
    		+ "chc.idTipoCumplimientoOsi,chc.deTipoCumplimientoOsi,chc.deComentarioOsi "            
    		+ ") "            
            + "FROM PgimHechoCnsttdoFase chc "
            + "WHERE chc.idSupervision = :idSupervision "
            //+ "AND (:codigoCumple=0L OR chc.coTipoCumplimiento = :codigoCumple) "
            + "AND chc.idFaseMuestra = :idFase "
            + "AND (:feGeneracion IS NULL OR chc.feGeneracion = :feGeneracion) "
    		)    
    List<PgimHechoCnsttdoFaseDTO> listarHechoCnsttdoFase(
    		@Param("idSupervision") Long idSupervision,
    		@Param("idFase") Long idFase,
    		@Param("feGeneracion") Date feGeneracion
    		//@Param("codigoCumple") Long codigoCumple
    		);
    
    
    /**
     * Permite obtener la lista histórica de hechos constatados por fase
     * @param  idSupervision
     * @param  idFase
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoCnsttdoFaseDTOResultado "   		
    		+ "(chc.idHechoConstatadoHist,chc.idHechoConstatado,chc.deHechoConstatado,chc.deComplementoObservacion,chc.deSustento, "
    		+ "chc.idTipoCumplimiento,chc.deTipoCumplimiento, "
    		+ "chc.idSupervision,chc.idCriterioSprvsion,chc.coMatrizCriterio,chc.nuOrdenGrpoCrtrio, "    		
    		+ "chc.idHechoConstatadoOsi,chc.deHechoConstatadoOsi,chc.deComplementoObservacionOsi,chc.deSustentoOsi, "
    		+ "chc.idTipoCumplimientoOsi,chc.deTipoCumplimientoOsi,chc.deComentarioOsi "            
    		+ ") "            
            + "FROM PgimHechoCnsttdoFase chc "
            + "WHERE chc.idSupervision = :idSupervision "         
            + "AND chc.idFaseMuestra >= :idFase "            
    		)    
    List<PgimHechoCnsttdoFaseDTO> listarHechoCnsttdoFaseParaEliminar(
    		@Param("idSupervision") Long idSupervision,
    		@Param("idFase") Long idFase
    		);
    
    
    
    /**
     * Permite obtener la lista cotejo de hechos constatados por supervisión paginado
     * @param  idSupervision 
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTOResultado ("
    		+ "chc.idHechoConstatado, chc.deHechoConstatado, chc.deComplementoObservacion, "
            + "chc.deSustento, chc.idTipoCumplimiento, chc.deTipoCumplimiento, "
    		+ "chc.idSupervision, chc.idCriterioSprvsion, chc.coMatrizCriterio, "
            + "chc.nuOrdenGrpoCrtrio, chc.idHechoConstatadoOsi, chc.deHechoConstatadoOsi, "
            + "chc.deComplementoObservacionOsi, chc.deSustentoOsi, chc.idTipoCumplimientoOsi, "
            + "chc.deTipoCumplimientoOsi, chc.deComentarioOsi, crit.coMatrizCriterio "
            + ") "            
            + "FROM PgimHechoCnsttdoFase chc "
            + "LEFT JOIN PgimHechoConstatado hv ON (chc.idHechoConstatadoOsi = hv.idHechoConstatado AND hv.esRegistro = '1') "
            + "LEFT JOIN PgimCriterioSprvsion crit ON (hv.pgimCriterioSprvsion.idCriterioSprvsion = crit.idCriterioSprvsion AND crit.esRegistro = '1') "
            + "WHERE chc.idSupervision = :idSupervision "            
            + "AND (:codigoCumpleSupervisora = 0L OR chc.coTipoCumplimiento = :codigoCumpleSupervisora) "
            + "AND (:codigoCumpleOsinergmin = 0L OR chc.coTipoCumplimientoOsi = :codigoCumpleOsinergmin) "
            + "AND chc.idFaseMuestra = :idFase "
            + "ORDER BY chc.nuOrdenGrpoCrtrio, chc.coMatrizCriterio, chc.idHechoConstatado ASC "
            )
    Page<PgimCotejoHechoCnsttdoDTO> listarCotejoHechosConstatadosPorFasePaginado(
    		@Param("idSupervision") Long idSupervision,
    		@Param("codigoCumpleSupervisora") Long codigoCumpleSupervisora,
    		@Param("codigoCumpleOsinergmin") Long codigoCumpleOsinergmin,
    		@Param("idFase") Long idFase,
    		Pageable paginador
    		);
    

}
