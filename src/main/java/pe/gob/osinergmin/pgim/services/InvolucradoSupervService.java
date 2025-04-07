package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInvolucradoSuperv;

public interface InvolucradoSupervService {

	/**
	 * Permite listar los involucrados de una supervisión
	 * 
	 * @param filtroInvolucrado
	 * @param paginador
	 * @return
	 */
	List<PgimInvolucradoSupervDTO> listarInvolucradoSupervision(Long idSupervision, Long idValorParametro)
			throws Exception;

	/**
	 * Permite actualizar la observacion de una acta de inicio
	 * 
	 * @param supervisionDTO
	 * @param auditoriaDTO
	 * @return
	 */
	Long modificarObservacionInicioSuper(PgimSupervisionDTO supervisionDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite actualizar la observacion de una acta de supervisión
	 * 
	 * @param supervisionDTO
	 * @param auditoriaDTO
	 * @return
	 */
	Long modificarObservacionFinSuper(PgimSupervisionDTO supervisionDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener las propiedades necesarias
	 * 
	 * @param idInvolucradoSuperv
	 * @return
	 */
	PgimInvolucradoSupervDTO obtenerInvolucradoSupervision(Long idInvolucradoSuperv);

	/**
	 * Permite filtrar por involucrado de una supervisión
	 * 
	 * @param palabraClave
	 * @return
	 */
	List<PgimInvolucradoSupervDTO> listarPorInvolucradoAiAs(String palabraClave);

	List<PgimInvolucradoSupervDTO> existeRepresentanteAi(Long idValorParametro, Long idSupervision, String coDocumentoIdentidad);

	/**
	 * Permite modificar un involucrado de una supervision
	 * 
	 * @param pgimInvolucradoSupervDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimInvolucradoSupervDTO modificarInvolucradoSupervision(@Valid PgimInvolucradoSupervDTO pgimInvolucradoSupervDTO,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite eliminar un involucrado de una supervision
	 * 
	 * @param pgimInvolucradoSupervActual
	 * @param auditoriaDTO
	 */
	void eliminarInvolucradoSupervision(PgimInvolucradoSuperv pgimInvolucradoSupervActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el id de un involucrado "idInvolucradoSuperv"
	 * 
	 * @param idInvolucradoSuperv
	 * @return
	 */
	PgimInvolucradoSuperv getByIdInvolucradoSupervision(Long idInvolucradoSuperv);

	/**
	 * Permite crear un involucrado de una supervision
	 * 
	 * @param pgimInvolucradoSupervDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimInvolucradoSupervDTO crearInvolucradoSupervision(@Valid PgimInvolucradoSupervDTO pgimInvolucradoSupervDTO,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener todas las propiedades de un representante de un agente
	 * supervisado
	 * 
	 * @param idSupervision
	 * @param tipoActaInvolucrado
	 * @return
	 */
	List<PgimInvolucradoSupervDTO> obtenerRepresentantesAgenteSupervisado(Long idSupervision, Long tipoActaInvolucrado);

	/**
	 * Permite obtener todas las propiedades de un representante trabajador
	 * 
	 * @param idSupervision
	 * @param tipoActaInvolucrado
	 * @return
	 */
	List<PgimInvolucradoSupervDTO> obtenerRepresentantesTrabajadores(Long idSupervision, Long tipoActaInvolucrado);
}
