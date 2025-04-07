package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimVwPrgrmMontoEspAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimVwPrgrmMontoEspAux;

/**
 * Tiene como nombre Monto del programa por especialidad.
 * 
 * @descripción: Logica de negocio de la entidad Monto del programa por especialidad. 
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface VwPrgrmMontoEspAuxRepository extends JpaRepository<PgimVwPrgrmMontoEspAux, Long>{

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimVwPrgrmMontoEspAuxDTOResultado( "
            + "meaux.idPlanSupervisionAux, meaux.idPlanSupervision, meaux.idEspecialidad, meaux.noEspecialidad, "
            + "meaux.moPartida, meaux.moDisponibleContrato, meaux.contratos, meaux.costoTotal ) "
            + "FROM PgimVwPrgrmMontoEspAux meaux "
            + "WHERE meaux.idPlanSupervisionAux = :idPlanSupervisionAux ")
    List<PgimVwPrgrmMontoEspAuxDTO> listarMontosEspecialidadPorPlan(@Param("idPlanSupervisionAux") Long idPlanSupervisionAux);
}
