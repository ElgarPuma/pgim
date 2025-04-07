package pe.gob.osinergmin.pgim.models.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import pe.gob.osinergmin.pgim.models.entity.PgimExppasPendientesAux;
import pe.gob.osinergmin.pgim.dtos.PgimExppasPendientesAuxDTO;

/**
 * @descripción: Logica de negocio de la entidad expedientes PAS pendientes por persona asignada
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
@Repository
public interface ExppasPendientesAuxRepository extends JpaRepository<PgimExppasPendientesAux, String>{
	/**
	 * Permite obtener la lista preparada de expedientes detallados con PAS por persona asiganda usado en reporte correspondiente de manera paginada
	 * @param personaAsignada
	 * @param paginador
	 * @return
	 */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasPendientesAuxDTOResultado( "
    		+ "expas.anioSupervision, expas.feInicioSupervision, expas.feFinSupervision, expas.feInicioSupervisionReal, expas.feFinSupervisionReal, "
			+ "expas.expSigedPas, expas.etiquetaAgenteSupervisado, expas.etiquetaUnidadMinera, expas.noEspecialidad, "
			+ "expas.noDivisionSupervisora, expas.flPropia, expas.etiquetaPasoActual, expas.fechaPasoActual, "
			+ "expas.diasTranscurridos) "
            + "FROM PgimExppasPendientesAux expas "
            + "WHERE 1 = 1 "
            + "AND (:personaAsignada IS NULL OR LOWER(expas.etiquetaPersonaAsignada) LIKE LOWER(CONCAT('%', :personaAsignada, '%')))")
    Page<PgimExppasPendientesAuxDTO> listarReporteExpPersonaAsignadaPaginado(@Param("personaAsignada") String personaAsignada, Pageable paginador);

    /**
     * Permite obtener la lista preparada de expedientes detallados con PAS por persona asiganda usado en reporte correspondiente
     * @param personaAsignada
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasPendientesAuxDTOResultado( "
    		+ "expas.anioSupervision, expas.feInicioSupervision, expas.feFinSupervision, expas.feInicioSupervisionReal, expas.feFinSupervisionReal, "
    		+ "expas.expSigedPas, expas.etiquetaAgenteSupervisado, expas.etiquetaUnidadMinera, expas.noEspecialidad, "
    		+ "expas.noDivisionSupervisora, expas.flPropia, expas.etiquetaPasoActual, expas.fechaPasoActual, "
    		+ "expas.diasTranscurridos) "
    		+ "FROM PgimExppasPendientesAux expas "
    		+ "WHERE 1 = 1 "
    		+ "AND (:personaAsignada IS NULL OR LOWER(expas.etiquetaPersonaAsignada) LIKE LOWER(CONCAT('%', :personaAsignada, '%'))) "
    		)
    List<PgimExppasPendientesAuxDTO> listarReporteExpPersonaAsignada(@Param("personaAsignada") String personaAsignada,
    		Sort sort);

		
}
