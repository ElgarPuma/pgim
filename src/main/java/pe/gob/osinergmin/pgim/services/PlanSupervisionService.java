package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPlanSupervision;

public interface PlanSupervisionService {

    /**
     * Me permite listar y filtrar los planes de supervisión
     * 
     * @param filtroPlanSupervision
     * @param paginador
     * @return
     */
    Page<PgimPlanSupervisionDTO> listarPlanSupervision(PgimPlanSupervisionDTO filtroPlanSupervision,
            Pageable paginador);

    /**
     * Permite obtener los datos del plan de supervision por el id
     * 
     * @param idPlanSupervision
     * @return
     */
    PgimPlanSupervisionDTO obtenerPlanSupervisionPorId(Long idPlanSupervision);

    /**
     * Permite crear un nuevo plan de supervisión
     * 
     * @param pgimPlanSupervisionDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    PgimPlanSupervisionDTO crearPlanSupervision(@Valid PgimPlanSupervisionDTO pgimPlanSupervisionDTO,
            AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Permite modificar el plan de supervisión
     * 
     * @param pgimPlanSupervisionDTO
     * @param auditoriaDTO
     * @return
     */
    PgimPlanSupervisionDTO modificarPlanSupervision(PgimPlanSupervisionDTO pgimPlanSupervisionDTO,
            PgimPlanSupervision pgimPlanSupervision, AuditoriaDTO auditoriaDTO);

    /**
     * permite obtener por el los datos del plan de supervisión
     * 
     * @param idPlanSupervision
     * @return
     */
    PgimPlanSupervision getByIdPlanSupervision(Long idPlanSupervision);

    /**
     * Permite eliminar un plan de supervisión
     * 
     * @param pgimPlanSupervisionActual
     * @param auditoriaDTO
     */
    void eliminarPlanSupervision(PgimPlanSupervision pgimPlanSupervisionActual, AuditoriaDTO auditoriaDTO);

    /**
     * Me permite filtrar por el año del plan de supervisión
     * 
     * @param nuAnio
     * @return
     */
    List<PgimPlanSupervisionDTO> filtrarPorAnio(Long nuAnio);

    /**
     * Permite validar que no se registre otro plan de supervisión con el mismo año
     * @return
     */
    List<PgimPlanSupervisionDTO> existeNuAnio(Long idPlanSupervision, Long nuAnio);
}
