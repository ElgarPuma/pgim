package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizSupervisionAux;

import java.util.List;

/**
 * Esta interface MatrizSupervisionAuxRepository esta conformado pos los métodos de listar
 * matriz de supervisión.
 * 
 * @descripción: Lógica de negocio de la entidad Matriz de Supervision Auxiliar
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 30/09/2020
 *  
 */
@Repository
public interface MatrizSupervisionAuxRepository extends JpaRepository<PgimMatrizSupervisionAux, Long> {
	
	/**
     * Permite obtener la lista de la matriz de supervisión.
     * @param  idSupervision
     * @param  tipoMatriz
     * @return
     */
     @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTOResultado(aux.idMatrizSupervisionAux,aux.idCriterioSprvsion, aux.idMatrizCriterio, "
            + "aux.coMatrizCriterio, aux.deMatrizCriterio, aux.deBaseLegal, "
            + "aux.idMatrizGrpoCrtrio, aux.coMatrizGrpoCrtrio, aux.noMatrizGrpoCrtrio,aux.idSupervision "
            + ", case when :tipoMatriz='S' then aux.cantidadHcSupervision else aux.cantidadHcAprobado end "
            + ", case when :tipoMatriz='S' then aux.codigoCumpleSupervision else aux.codigoCumpleAprobado end "
            + ", case when :tipoMatriz='S' then aux.descripcionCumpleSupervision else aux.descripcionCumpleAprobado end, aux.deNorma, aux.deTipificacion "
            + ", aux.idInstanciaPasoCriterio, aux.idPasoProcesoAgregoCs, aux.idMatrizSupervision "
            + ")  "
            + "FROM PgimMatrizSupervisionAux aux "
            + "where aux.idSupervision = :idSupervision "
            
            + "AND (:deNorma IS NULL OR LOWER(TRIM(aux.deNorma)) LIKE LOWER(CONCAT('%', :deNorma, '%')) ) "
            + "AND (:deTipificacion IS NULL OR LOWER(TRIM(aux.deTipificacion)) LIKE LOWER(CONCAT('%', :deTipificacion, '%')) ) "
            + "AND (:codigoCumple=0L OR :tipoMatriz<>'S' OR  aux.codigoCumpleSupervision = :codigoCumple) "
            + "AND (:codigoCumple=0L OR :tipoMatriz<>'A' OR  aux.codigoCumpleAprobado = :codigoCumple) "
        //              + "AND ( "
        //              + ":tipoMatriz<>'S' OR (:coClaveSiCumple = 0L OR aux.codigoCumpleSupervision = :coClaveSiCumple "
        //              + "OR aux.codigoCumpleSupervision = :coClaveNoCumple "
        //              + "OR aux.codigoCumpleSupervision = :coClaveNoAplica "
        //              + "OR aux.codigoCumpleSupervision = :coClaveArchivado) "
        //              + ") "

        //     + "AND ( " 
        //     + ":tipoMatriz<>'A' OR (:coClaveSiCumple = 0L OR aux.codigoCumpleAprobado = :coClaveSiCumple " 
        //     + "OR aux.codigoCumpleAprobado = :coClaveNoCumple " 
        //     + "OR aux.codigoCumpleAprobado = :coClaveNoAplica "
        //     + "OR aux.codigoCumpleAprobado = :coClaveArchivado) "
        //     + ") "

    )
    Page<PgimMatrizSupervisionAuxDTO> listarMatrizSupervision(@Param("idSupervision") Long idSupervision
    ,@Param("deNorma") String deNorma
    ,@Param("deTipificacion") String deTipificacion
            ,@Param("codigoCumple") Long codigoCumple
        //     ,@Param("coClaveSiCumple") Long coClaveSiCumple
        //     ,@Param("coClaveNoCumple") Long coClaveNoCumple
        //     ,@Param("coClaveNoAplica") Long coClaveNoAplica
        //     ,@Param("coClaveArchivado") Long coClaveArchivado
            ,@Param("tipoMatriz") String tipoMatriz //S=Supervisión; A=Aprobación
            , Pageable paginador);
     @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTOResultado(aux.idMatrizSupervisionAux,aux.idCriterioSprvsion, aux.idMatrizCriterio, "
            + "aux.coMatrizCriterio, aux.deMatrizCriterio, aux.deBaseLegal, "
            + "aux.idMatrizGrpoCrtrio, aux.coMatrizGrpoCrtrio, aux.noMatrizGrpoCrtrio,aux.idSupervision "
            + ", case when :tipoMatriz='S' then aux.cantidadHcSupervision else aux.cantidadHcAprobado end "
            + ", case when :tipoMatriz='S' then aux.codigoCumpleSupervision else aux.codigoCumpleAprobado end "
            + ", case when :tipoMatriz='S' then aux.descripcionCumpleSupervision else aux.descripcionCumpleAprobado end, aux.deNorma, aux.deTipificacion "
            + ", aux.idInstanciaPasoCriterio, aux.idPasoProcesoAgregoCs, aux.idMatrizSupervision "
            + ")  "
            + "FROM PgimMatrizSupervisionAux aux "
            + "where aux.idSupervision = :idSupervision "
            
            + "AND (:deNorma IS NULL OR LOWER(TRIM(aux.deNorma)) LIKE LOWER(CONCAT('%', :deNorma, '%')) ) "
            + "AND (:deTipificacion IS NULL OR LOWER(TRIM(aux.deTipificacion)) LIKE LOWER(CONCAT('%', :deTipificacion, '%')) ) "
            + "AND (:codigoCumple=0L OR :tipoMatriz<>'S' OR  aux.codigoCumpleSupervision = :codigoCumple) "
            + "AND (:codigoCumple=0L OR :tipoMatriz<>'A' OR  aux.codigoCumpleAprobado = :codigoCumple) "
        //              + "AND ( "
        //              + ":tipoMatriz<>'S' OR (:coClaveSiCumple = 0L OR aux.codigoCumpleSupervision = :coClaveSiCumple "
        //              + "OR aux.codigoCumpleSupervision = :coClaveNoCumple "
        //              + "OR aux.codigoCumpleSupervision = :coClaveNoAplica "
        //              + "OR aux.codigoCumpleSupervision = :coClaveArchivado) "
        //              + ") "

        //     + "AND ( " 
        //     + ":tipoMatriz<>'A' OR (:coClaveSiCumple = 0L OR aux.codigoCumpleAprobado = :coClaveSiCumple " 
        //     + "OR aux.codigoCumpleAprobado = :coClaveNoCumple " 
        //     + "OR aux.codigoCumpleAprobado = :coClaveNoAplica "
        //     + "OR aux.codigoCumpleAprobado = :coClaveArchivado) "
        //     + ") "

    )
    List<PgimMatrizSupervisionAuxDTO> listarMatrizSupervisionList(@Param("idSupervision") Long idSupervision
    ,@Param("deNorma") String deNorma
    ,@Param("deTipificacion") String deTipificacion
            ,@Param("codigoCumple") Long codigoCumple
        //     ,@Param("coClaveSiCumple") Long coClaveSiCumple
        //     ,@Param("coClaveNoCumple") Long coClaveNoCumple
        //     ,@Param("coClaveNoAplica") Long coClaveNoAplica
        //     ,@Param("coClaveArchivado") Long coClaveArchivado
            ,@Param("tipoMatriz") String tipoMatriz //S=Supervisión; A=Aprobación
        );


    /**
     * Permite obtener la lista de la matriz de supervisión histórico por fase
     * @param  idSupervision
     * @param  idFaseProceso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTOResultado(aux.idMatrizSprvsionHist,aux.idCriterioSprvsion, aux.idMatrizCriterio, "
            + "aux.coMatrizCriterio, aux.deMatrizCriterio, aux.deBaseLegal, "
            + "aux.idMatrizGrpoCrtrio, aux.coMatrizGrpoCrtrio, aux.noMatrizGrpoCrtrio,aux.idSupervision "
            + ", aux.cantidadHc, aux.codigoCumple, aux.descripcionCumple, aux.deNorma, aux.deTipificacion "
            + ", aux.idInstanciaPasoCriterio, aux.idPasoProcesoAgregoCs, aux.idMatrizSupervision "
            + ")  "
            + "FROM PgimMatrizSprvsionHist aux "
            + "where aux.idSupervision = :idSupervision "
            + "AND aux.idFaseProceso = :idFaseProceso "
            + "AND (:codigoCumple=0L OR aux.codigoCumple = :codigoCumple) "
             + "AND (:deNorma IS NULL OR LOWER(TRIM(aux.deNorma)) LIKE LOWER(CONCAT('%', :deNorma, '%')) ) "
            + "AND (:deTipificacion IS NULL OR LOWER(TRIM(aux.deTipificacion)) LIKE LOWER(CONCAT('%', :deTipificacion, '%')) ) "
            )
    Page<PgimMatrizSupervisionAuxDTO> listarMatrizSupervisionFaseHist(@Param("idSupervision") Long idSupervision
    		,@Param("codigoCumple") Long codigoCumple
    		,@Param("idFaseProceso") Long idFaseProceso
            ,@Param("deNorma") String deNorma
    		,@Param("deTipificacion") String deTipificacion
    		, Pageable paginador);
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTOResultado(aux.idMatrizSprvsionHist,aux.idCriterioSprvsion, aux.idMatrizCriterio, "
            + "aux.coMatrizCriterio, aux.deMatrizCriterio, aux.deBaseLegal, "
            + "aux.idMatrizGrpoCrtrio, aux.coMatrizGrpoCrtrio, aux.noMatrizGrpoCrtrio,aux.idSupervision "
            + ", aux.cantidadHc, aux.codigoCumple, aux.descripcionCumple, aux.deNorma, aux.deTipificacion "
            + ", aux.idInstanciaPasoCriterio, aux.idPasoProcesoAgregoCs, aux.idMatrizSupervision "
            + ")  "
            + "FROM PgimMatrizSprvsionHist aux "
            + "where aux.idSupervision = :idSupervision "
            + "AND aux.idFaseProceso = :idFaseProceso "
            + "AND (:codigoCumple=0L OR aux.codigoCumple = :codigoCumple) "
             + "AND (:deNorma IS NULL OR LOWER(TRIM(aux.deNorma)) LIKE LOWER(CONCAT('%', :deNorma, '%')) ) "
            + "AND (:deTipificacion IS NULL OR LOWER(TRIM(aux.deTipificacion)) LIKE LOWER(CONCAT('%', :deTipificacion, '%')) ) "
            )
    List<PgimMatrizSupervisionAuxDTO> listarMatrizSupervisionFaseHistList(@Param("idSupervision") Long idSupervision
    		,@Param("codigoCumple") Long codigoCumple
    		,@Param("idFaseProceso") Long idFaseProceso
            ,@Param("deNorma") String deNorma
    		,@Param("deTipificacion") String deTipificacion
    		);
    
    

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTOResultado(aux.idMatrizSupervisionAux,aux.idCriterioSprvsion, aux.idMatrizCriterio, "
            + "aux.coMatrizCriterio, aux.deMatrizCriterio, aux.deBaseLegal, "
            + "aux.idMatrizGrpoCrtrio, aux.coMatrizGrpoCrtrio, aux.noMatrizGrpoCrtrio,aux.idSupervision "
            + ", case when :tipoMatriz='S' then aux.cantidadHcSupervision else aux.cantidadHcAprobado end "
            + ", case when :tipoMatriz='S' then aux.codigoCumpleSupervision else aux.codigoCumpleAprobado end "
            + ", case when :tipoMatriz='S' then aux.descripcionCumpleSupervision else aux.descripcionCumpleAprobado end, aux.deNorma, aux.deTipificacion "
            + ", aux.idInstanciaPasoCriterio, aux.idPasoProcesoAgregoCs, aux.idMatrizSupervision "
            + ")  "
            + "FROM PgimMatrizSupervisionAux aux "
            + "where aux.idSupervision = :idSupervision "
            + "AND (:codigoCumple=0L OR :tipoMatriz<>'S' OR  aux.codigoCumpleSupervision = :codigoCumple) "
            + "AND (:codigoCumple=0L OR :tipoMatriz<>'A' OR  aux.codigoCumpleAprobado = :codigoCumple) "
            + "ORDER BY aux.coMatrizCriterio asc " 
            )
    List<PgimMatrizSupervisionAuxDTO> listarMatrizSupervisionDescarga(@Param("idSupervision") Long idSupervision
    		,@Param("codigoCumple") Long codigoCumple, @Param("tipoMatriz") String tipoMatriz);

        /**
         * Permite obtener la lista del cuadro de verificación(matriz de supervisión) para hacer 
         * el registro de la conformidad de cada criterio perteneciente al cuadro de verificación
         * @param idSupervision
         * @param tipoMatriz
         * @param codigoCumple
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTOResultado(aux.idMatrizSupervisionAux,aux.idCriterioSprvsion, aux.idMatrizCriterio, "
                + "aux.coMatrizCriterio, aux.deMatrizCriterio, aux.deBaseLegal, "
                + "aux.idMatrizGrpoCrtrio, aux.coMatrizGrpoCrtrio, aux.noMatrizGrpoCrtrio,aux.idSupervision "
                + ", case when :tipoMatriz='S' then aux.cantidadHcSupervision else aux.cantidadHcAprobado end "
                + ", case when :tipoMatriz='S' then aux.codigoCumpleSupervision else aux.codigoCumpleAprobado end "
                + ", case when :tipoMatriz='S' then aux.descripcionCumpleSupervision else aux.descripcionCumpleAprobado end, aux.deNorma, aux.deTipificacion "
                + ", aux.idInstanciaPasoCriterio, aux.idPasoProcesoAgregoCs, aux.idMatrizSupervision "
                + ")  "
                + "FROM PgimMatrizSupervisionAux aux "
                + "where aux.idSupervision = :idSupervision "
                + "AND (:codigoCumple=0L OR :tipoMatriz<>'S' OR  aux.codigoCumpleSupervision = :codigoCumple) "
                + "AND (:codigoCumple=0L OR :tipoMatriz<>'A' OR  aux.codigoCumpleAprobado = :codigoCumple) "
                )
        List<PgimMatrizSupervisionAuxDTO> listarMatrizSupervisionAddConformidad(@Param("idSupervision") Long idSupervision, 
                @Param("tipoMatriz") String tipoMatriz, //S=Supervisión; A=Aprobación
                @Param("codigoCumple") Long codigoCumple 
                );


        @Query("SELECT DISTINCT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTOResultado( " 
                + "aux.idSupervision"
                + ")  "
                + "FROM PgimMatrizSupervisionAux aux "
                + "WHERE aux.idMatrizSupervision = :idMatrizSupervision "
                )
        List<PgimMatrizSupervisionAuxDTO> listarSupervisionesPorCuadroVerificacion(@Param("idMatrizSupervision") Long idMatrizSupervision);
}
