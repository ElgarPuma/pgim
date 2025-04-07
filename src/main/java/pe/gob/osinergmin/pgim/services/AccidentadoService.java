package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSegAccidentadoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAccidentado;

public interface AccidentadoService {

	/**
	 * Permite listar la lista de accidentados de un evento determinado.
	 * 
	 * @param idEvento
	 * @return
	 */
	List<PgimAccidentadoDTO> listarAccidentado(Long idEvento);

	/**
	 * Permite listar los seguros de un determinado accidentado.
	 * 
	 * @param idAccidentado
	 * @return
	 */
	List<PgimSegAccidentadoDTO> listarSegurosAccidentado(Long idAccidentado);

	/**
	 * Permite obtener la persona accidentada de acuerdo con su identificador.
	 * 
	 * @param idAccidentado
	 * @return
	 */
	PgimAccidentadoDTO obtenerAccidentado(Long idAccidentado);

	/**
	 * Permnite verificar si la persona accidentada existe o no.
	 * 
	 * @param idAccidentado
	 * @param idTipoDocumento
	 * @param numeroDocumento
	 * @return
	 */
	List<PgimAccidentadoDTO> existeAccidentado(Long idAccidentado, Long idTipoDocumento, String numeroDocumento);

	/**
	 * Permirte crear una persona accidentada.
	 * 
	 * @param pgimAccidentadoDTO
	 * @return
	 */
	PgimAccidentadoDTO crearAccidentado(@Valid PgimAccidentadoDTO pgimAccidentadoDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener una persona accidentada de acuerdo con su identificador
	 * interno.
	 * 
	 * @param idAccidentado
	 * @return
	 */
	PgimAccidentado getByIdAccidentado(Long idAccidentado);

	/**
	 * Permite modificar los datos de una persona accidentada.
	 * 
	 * @param pgimAccidentadoDTO
	 * @param pgimAccidentadoActual
	 * @return
	 */
	PgimAccidentadoDTO modificarAccidentado(@Valid PgimAccidentadoDTO pgimAccidentadoDTO,
			PgimAccidentado pgimAccidentadoActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permiote eliminar una persona accidentada.
	 * @param pgimAccidentadoActual
	 */
	void eliminarAccidentado(PgimAccidentado pgimAccidentadoActual, AuditoriaDTO auditoriaDTO);

}
