package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRelacionAccionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionAccion;

/**
 * Interfaz que determina el comportamiento requerido para la gestión de la
 * relación de acciones de los pasos de flujo de trabajo.
 * 
 * @descripción: Repositorio que incluye la interacción directa con las tablas
 *               relacionadas con la relación de acciones de flujo de trabajo.
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 05/08/2020
 * @fecha_de_ultima_actualización: 05/08/2020
 */
@Repository
public interface RelacionAccionRepository extends JpaRepository<PgimRelacionAccion, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionAccionDTOResultado("
                        + "racc.idRelacionAccion, repa.idRelacionPaso, repp.idRelacionPaso, " 
                        + "racc.deAccion, ppde.noPasoProceso, ropo.noRolProceso, " 	
                        + "racc.flActivarInstanciaPaso "
                        + ")"
                        + "FROM PgimRelacionAccion racc " 
                        + "     INNER JOIN racc.pgimRelacionPaso repa "
                        + "     LEFT OUTER JOIN racc.pgimRelacionPasoParalela repp "
                        + "     LEFT OUTER JOIN repp.pasoProcesoDestino ppde "
                        + "     LEFT OUTER JOIN ppde.pgimRolProceso ropo "
                        + "WHERE repa.idRelacionPaso = :idRelacionPaso "
                        + "AND racc.esRegistro = '1' "
                        + "AND repa.esRegistro = '1' "
                        + "ORDER BY racc.nuOrden "
                        )
    List<PgimRelacionAccionDTO> obtenerRelacionAccion(@Param("idRelacionPaso") Long idRelacionPaso);
    
}
