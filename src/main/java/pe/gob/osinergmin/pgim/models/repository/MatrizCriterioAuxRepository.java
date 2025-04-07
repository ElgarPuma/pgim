package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizCriterioAux;

/**
 * @descripción: Lógica de negocio de la entidad matriz criterio de la supervisión
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface MatrizCriterioAuxRepository extends JpaRepository<PgimMatrizCriterioAux, Long>{
    
        /**
         * Permite listar la matriz de criterio de la supervision
         * @param idMatrizSupervision
         * @param idMatrizGrpoCrtrio
         * @param deMatrizCriterio
         * @param nuObligaciones
         * @param paginador
         * @return
         */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioAuxDTOResultado( "
            + "mtc.idMatrizCriterioAux, mtc.idMatrizCriterio, mtc.idMatrizSupervision, mtc.idMatrizGrpoCrtrio, " 
            + "mtc.coMatrizGrpoCrtrio, " 
            + "mtc.noMatrizGrpoCrtrio, mtc.nuOrdenMatrizGrpoCrtrio, " 
            + "mtc.coMatrizCriterio, mtc.deMatrizCriterio, " 
            + "mtc.nuOrdenMatrizCriterio, mtc.nuObligaciones, mtc.esVigente, mtc.deBaseLegal, mtc.norma, mtc.tipificacion "
            + ") " 
            + "FROM PgimMatrizCriterioAux mtc "
            + "WHERE mtc.idMatrizSupervision = :idMatrizSupervision "
            + "AND (:idMatrizGrpoCrtrio IS NULL OR mtc.idMatrizGrpoCrtrio = :idMatrizGrpoCrtrio) "
            + "AND (:deMatrizCriterio IS NULL OR LOWER(mtc.deMatrizCriterio) LIKE LOWER(CONCAT('%', :deMatrizCriterio, '%'))) "
            + "AND (:nuObligaciones IS NULL OR mtc.nuObligaciones = :nuObligaciones) "
            + "AND (:norma IS NULL OR LOWER(mtc.norma) LIKE LOWER(CONCAT('%', :norma, '%'))) "
            + "AND (:tipificacion IS NULL OR LOWER(mtc.tipificacion) LIKE LOWER(CONCAT('%', :tipificacion, '%'))) "
    )
    Page<PgimMatrizCriterioAuxDTO> listarMatrizCriterio(@Param("idMatrizSupervision") Long idMatrizSupervision,
            @Param("idMatrizGrpoCrtrio") Long idMatrizGrpoCrtrio, 
            @Param("deMatrizCriterio") String deMatrizCriterio, 
            @Param("nuObligaciones") Long nuObligaciones, 
            @Param("norma") String norma, 
            @Param("tipificacion") String tipificacion, 
            Pageable paginador);
    
    /**
     * Permite obtener la lista de criterios del cuadro de verificación
	 * que no forman parte de una fiscalización
     * @param idSupervision
     * @param idMatrizSupervision
     * @param idMatrizGrpoCrtrio
     * @param deMatrizCriterio
     * @param nuObligaciones
     * @param paginador
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioAuxDTOResultado( "
	        + "mtc.idMatrizCriterioAux, mtc.idMatrizCriterio, mtc.idMatrizSupervision, mtc.idMatrizGrpoCrtrio, " 
	        + "mtc.coMatrizGrpoCrtrio, " 
	        + "mtc.noMatrizGrpoCrtrio, mtc.nuOrdenMatrizGrpoCrtrio, " 
	        + "mtc.coMatrizCriterio, mtc.deMatrizCriterio, " 
	        + "mtc.nuOrdenMatrizCriterio, mtc.nuObligaciones, mtc.esVigente, mtc.deBaseLegal, mtc.norma, mtc.tipificacion "
	        + ") " 
	        + "FROM PgimMatrizCriterioAux mtc "
	        + "WHERE mtc.idMatrizSupervision = :idMatrizSupervision "
	        + "AND mtc.esVigente = '1' "
	        + "AND mtc.idMatrizCriterio NOT IN (SELECT mts.idMatrizCriterio FROM PgimMatrizSupervisionAux mts WHERE mts.idMatrizSupervision = :idMatrizSupervision AND mts.idSupervision = :idSupervision) "
	        + "AND (:idMatrizGrpoCrtrio IS NULL OR mtc.idMatrizGrpoCrtrio = :idMatrizGrpoCrtrio) "
	        + "AND (:deMatrizCriterio IS NULL OR LOWER(mtc.deMatrizCriterio) LIKE LOWER(CONCAT('%', :deMatrizCriterio, '%'))) "
	        + "AND (:nuObligaciones IS NULL OR mtc.nuObligaciones = :nuObligaciones) "
                + "AND (:norma IS NULL OR LOWER(mtc.norma) LIKE LOWER(CONCAT('%', :norma, '%'))) "
                + "AND (:tipificacion IS NULL OR LOWER(mtc.tipificacion) LIKE LOWER(CONCAT('%', :tipificacion, '%'))) "
	)
	Page<PgimMatrizCriterioAuxDTO> listarCriteriosNoFiscalizacion(@Param("idSupervision") Long idSupervision, @Param("idMatrizSupervision") Long idMatrizSupervision,
	        @Param("idMatrizGrpoCrtrio") Long idMatrizGrpoCrtrio, 
	        @Param("deMatrizCriterio") String deMatrizCriterio, 
	        @Param("nuObligaciones") Long nuObligaciones, 
	        @Param("norma") String norma, 
                @Param("tipificacion") String tipificacion, 
	        Pageable paginador);
    
	/**
     * Permite obtener el identificador de una matriz de supervisión 
     * por el identificador de una fiscalización.
     * @param idSupervision
     * @return
     */
	@Query("SELECT DISTINCT(mts.idMatrizSupervision) FROM PgimMatrizSupervisionAux mts WHERE mts.idSupervision = :idSupervision "
	)
	Long obtenerIdMatrizSupervisionByIdSupervision(@Param("idSupervision") Long idSupervision);
}
