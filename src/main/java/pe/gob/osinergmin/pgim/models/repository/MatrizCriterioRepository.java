package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizCriterio;

/**
 *  
 * @descripción: Logica de negocio de la entidad Criterio de la Matriz de Supervisión 
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
@Repository
public interface MatrizCriterioRepository extends JpaRepository<PgimMatrizCriterio, Long> {
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioDTOResultado( "
            + "mtc.idMatrizCriterio, mtc.pgimMatrizGrpoCrtrio.pgimMatrizSupervision.idMatrizSupervision, " 
            + "mtc.pgimMatrizGrpoCrtrio.coMatrizGrpoCrtrio, " 
            + "mtc.pgimMatrizGrpoCrtrio.noMatrizGrpoCrtrio, " 
            + "mtc.coMatrizCriterio, mtc.deMatrizCriterio, " 
            + "mtc.deBaseLegal, mtc.nuOrden, mtc.esVigente, "

            + "(select count(1) from PgimOblgcnNrmaCrtrio obnr "
            + "where obnr.pgimMatrizCriterio.idMatrizCriterio = mtc.idMatrizCriterio and obnr.esRegistro = '1' ), "
            + "mtc.pgimMatrizGrpoCrtrio.idMatrizGrpoCrtrio, mtc.flCopiaPorDefecto "

            + ") " 
            + "FROM PgimMatrizCriterio mtc "
            + "WHERE mtc.esRegistro = '1' "
            + "AND mtc.idMatrizCriterio = :idCriterio "
    )
    PgimMatrizCriterioDTO obtenerMatrizCriterioPorId(@Param("idCriterio") Long idCriterio);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioDTOResultado( "
            + "mtc.idMatrizCriterio, mtc.pgimMatrizGrpoCrtrio.pgimMatrizSupervision.idMatrizSupervision, " 
            + "mtc.pgimMatrizGrpoCrtrio.coMatrizGrpoCrtrio, " 
            + "mtc.pgimMatrizGrpoCrtrio.noMatrizGrpoCrtrio, " 
            + "mtc.coMatrizCriterio, mtc.deMatrizCriterio, " 
            + "mtc.deBaseLegal, mtc.nuOrden, mtc.esVigente, "
            + "0L, "
            + "mtc.pgimMatrizGrpoCrtrio.idMatrizGrpoCrtrio, mtc.flCopiaPorDefecto "
            + ") " 
            + "FROM PgimMatrizCriterio mtc "
            + "WHERE mtc.esRegistro = '1' "
            + "AND mtc.pgimMatrizGrpoCrtrio.idMatrizGrpoCrtrio = :idMatrizGrpoCrtrio "
    )
    List<PgimMatrizCriterioDTO> listarMatrizCriterioPorIdGrupoCriterio(@Param("idMatrizGrpoCrtrio") Long idMatrizGrpoCrtrio);
    
    
}
