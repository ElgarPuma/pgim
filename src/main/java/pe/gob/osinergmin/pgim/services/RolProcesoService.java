package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;

public interface RolProcesoService {

	List<PgimRolProcesoDTO> obtenerRolesProceso(Long idProceso, String direccion, Sort.Direction campo);

	List<PgimRolProcesoDTO> obtenerRolesProcesoPorAmbito(String flSoloOsinergmin, Long idProceso);

	// STORY: PGIM-7276: Relación de personal con roles del proceso de fiscalización
	List<PgimRolProcesoDTO> listarRolesPersonalContrato();

	// STORY:PGIM-7233: Validación de existencia de rol "coordinador de empresa
	// supervisora" en el equipo
	List<PgimRolProcesoDTO> obtenerRolCoordinadorESParaReprogramacion(Long idRolProceso);

	/**
	 * Permite obtener el rol del proceso por el Id
	 * 
	 * @param idRolProceso
	 * @return
	 */
	public PgimRolProcesoDTO obtenerRolProcesoPorId(Long idRolProceso);

	/**
	 * Me permite crear el rol del proceso
	 * 
	 * @param pgimRolProcesoDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public PgimRolProcesoDTO crearRolProceso(PgimRolProcesoDTO pgimRolProcesoDTO, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/***
	 * Permite modificar el rol del proceso
	 * 
	 * @param pgimRolProcesoDTO
	 * @param pgimRolProceso
	 * @param auditoriaDTO
	 * @return
	 */
	PgimRolProcesoDTO modificarRolProceso(PgimRolProcesoDTO pgimRolProcesoDTO, PgimRolProceso pgimRolProceso,
			AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite obtener el ID de el rol del proceso
	 * 
	 * @param idRolProceso
	 * @return
	 */
	PgimRolProceso getByIdRolProceso(Long idRolProceso) throws Exception;

	/**
	 * Permite eliminar logicamente el rol de flujos de trabajo del proceso
	 * 
	 * @param pgimRolProcesoActual
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	void eliminarRolProceso(PgimRolProceso pgimRolProcesoActual, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite validar la eliminación del rol de proceso asociado a una tarea
	 * existente
	 * 
	 * @param pgimRolProcesoActual
	 * @param auditoriaDTO
	 * @throws Exception
	 */
	public void validarEliminacionRolProceso(PgimRolProceso pgimRolProcesoActual, AuditoriaDTO auditoriaDTO)
			throws Exception;

}
