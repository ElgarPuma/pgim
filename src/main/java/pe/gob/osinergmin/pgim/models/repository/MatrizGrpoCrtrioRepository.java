package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizGrpoCrtrioDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizGrpoCrtrio;

import java.util.List;

/**
 * 
 * @descripción: Logica de negocio de la entidad matriz de supervisión
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 13/05/2021
 * 
 */
@Repository
public interface MatrizGrpoCrtrioRepository extends JpaRepository<PgimMatrizGrpoCrtrio, Long> {
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizGrpoCrtrioDTOResultado("
            + "grpo.idMatrizGrpoCrtrio, grpo.coMatrizGrpoCrtrio, grpo.noMatrizGrpoCrtrio, "
            + "grpo.nuOrden, grpo.esVisible, "
            + "(select count(1) from PgimMatrizCriterio crit "
            + "where crit.pgimMatrizGrpoCrtrio.idMatrizGrpoCrtrio = grpo.idMatrizGrpoCrtrio and crit.esRegistro = '1' ) "
            + ") "
            + "FROM PgimMatrizGrpoCrtrio grpo "            
            + "WHERE grpo.pgimMatrizSupervision.idMatrizSupervision = :idMatrizSupervision "
            + "AND grpo.esRegistro = '1' "
            + "ORDER BY grpo.nuOrden "
            )
	List<PgimMatrizGrpoCrtrioDTO> listarMatrizGrpoCrtrio(@Param("idMatrizSupervision") Long idMatrizSupervision);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizGrpoCrtrioDTOResultado("
            + "grpo.idMatrizGrpoCrtrio,grpo.pgimMatrizSupervision.idMatrizSupervision, grpo.coMatrizGrpoCrtrio, grpo.noMatrizGrpoCrtrio, "
            + "grpo.nuOrden, grpo.esVisible, "
            + "grpo.pgimMatrizSupervision.pgimEspecialidad.noEspecialidad "            
            + ") "
            + "FROM PgimMatrizGrpoCrtrio grpo "            
            + "WHERE grpo.idMatrizGrpoCrtrio = :idMatrizGrpoCrtrio "
            + "AND grpo.esRegistro = '1' "            
            )
	PgimMatrizGrpoCrtrioDTO obtenerGrupoCriterioPorId(@Param("idMatrizGrpoCrtrio") Long idMatrizGrpoCrtrio);

    /**
     * Me permite filtrar por el nombre de grupo de criterio de la matriz de supervision 
     * para la lista de criterios de la matriz
     * @param idMatrizSupervision
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizGrpoCrtrioDTOResultado( "
            + "grpo.idMatrizGrpoCrtrio, grpo.coMatrizGrpoCrtrio, grpo.noMatrizGrpoCrtrio ) "
            + "FROM PgimMatrizGrpoCrtrio grpo " 
            + "WHERE grpo.pgimMatrizSupervision.idMatrizSupervision = :idMatrizSupervision "
            // + "AND grpo.noMatrizGrpoCrtrio = :nombre "
            + "AND grpo.esRegistro = '1' "
            )
    List<PgimMatrizGrpoCrtrioDTO> filtrarPorGrupoCriterio(@Param("idMatrizSupervision") Long idMatrizSupervision);

}
