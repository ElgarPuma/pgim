package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPlanSupervision;

import java.util.List;

/**
 * @descripción: Logica de negocio de la entidad Plan de supervisión
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface PlanSupervisionRepository extends JpaRepository<PgimPlanSupervision, Long> {
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTOResultado( "
			+ "ps.idPlanSupervision, ps.nuAnio, ps.dePlanSupervision "
            + ")  "
			+ "FROM PgimPlanSupervision ps " 
            + "WHERE ps.esRegistro = '1' "
			+ "AND (:anio IS NULL OR LOWER(ps.nuAnio) LIKE LOWER(CONCAT('%', :anio, '%')) ) "
			+ "AND (:textoBusqueda IS NULL OR LOWER(ps.dePlanSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) )  "
            )
	Page<PgimPlanSupervisionDTO> listarPlanSupervision(@Param("anio") Long anio, @Param("textoBusqueda") String textoBusqueda,
			Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTOResultado( "
			+ "ps.idPlanSupervision, ps.nuAnio, ps.dePlanSupervision "
            + ")  "
			+ "FROM PgimPlanSupervision ps " 
            + "WHERE ps.esRegistro = '1' " 
			+ "AND ps.idPlanSupervision =:idPlanSupervision"
            )
	PgimPlanSupervisionDTO obtenerPlanSupervisionPorId(@Param("idPlanSupervision") Long idPlanSupervision);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTOResultado( "
			+ "ps.idPlanSupervision, ps.nuAnio "
            + ")  "
			+ "FROM PgimPlanSupervision ps " 
            + "WHERE ps.esRegistro = '1' "
			+ "AND (:nuAnio IS NULL OR ps.nuAnio = :nuAnio) ORDER BY ps.nuAnio DESC "
     		)
	List<PgimPlanSupervisionDTO> filtrarPorAnio(@Param("nuAnio") Long nuAnio);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTOResultado( "
			+ "ps.idPlanSupervision, ps.nuAnio "
            + ")  "
			+ "FROM PgimPlanSupervision ps " 
            + "WHERE ps.esRegistro = '1' " 
			+ "AND (:idPlanSupervision IS NULL OR ps.idPlanSupervision != :idPlanSupervision) "
			+ "AND ps.nuAnio = :nuAnio "
            )
	List<PgimPlanSupervisionDTO> existeNuAnio(@Param("idPlanSupervision") Long idPlanSupervision, 
			@Param("nuAnio") Long nuAnio);
}
