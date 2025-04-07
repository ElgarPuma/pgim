package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaEvento;

public interface EmpresaEventoService {

	List<PgimEmpresaEventoDTO> listarEmpresaEvento(Long idEvento);
		
	/**
	 * Permite listar las empresas candidatas a asociar al evento pasado como argmento y que 
	 * coincida con la palabra clave, la misma que será buscada en las propieades RUC o Razón social.
	 * @param idEvento
	 * @param palabra
	 * @return
	 */
	List<PgimEmpresaEventoDTO> listarPorPalabraClave(Long idEvento, String palabra);

	/**
	 * Permite obtener loa empresa del evento.
	 * @param idEmpresaEvento
	 * @return
	 */
	PgimEmpresaEventoDTO obtenerEmpresaEvento(Long idEmpresaEvento);
	
	/**
	 * Permite listar las empresas asociadas a Eventos registrados como motivos de una supervisión, 
	 * empresas candidatas para ser seleccionables como responsable de una infracción; es decir,
	 * de tipo contratista y que aún no haya sido registrado como responsable de la infracción
	 *  
	 * @param idSupervision
	 * @param idTipoEvento 
	 * @param idInfraccion 
	 * @return
	 */
	List<PgimEmpresaEventoDTO> listarEmpresaEventoSelecResponsableInfraccion(Long idSupervision, Long idTipoEvento,  Long idInfraccion);

	/**
	 * Permirte crear una persona accidentada.
	 * 
	 * @param pgimAccidentadoDTO
	 * @return
	 */
	PgimEmpresaEventoDTO crearEmpresaEvento(@Valid PgimEmpresaEventoDTO pgimEmpresaEventoDTO, AuditoriaDTO auditoriaDTO);

/**
	 * Permite obtener una empresa involucrada de acuerdo con su identificador
	 * interno.
	 * 
	 * @param idEmpresaEvento
	 * @return
	 */
	PgimEmpresaEvento getByIdEmpresaEvento(Long idEmpresaEvento);

/**
	 * Permite modificar los datos de una persona accidentada.
	 * 
	 * @param pgimAccidentadoDTO
	 * @param pgimAccidentadoActual
	 * @return
	 */
	PgimEmpresaEventoDTO modificarEmpresaEvento(@Valid PgimEmpresaEventoDTO pgimEmpresaEventoDTO
			, AuditoriaDTO auditoriaDTO);

	/**
	 * Permiote eliminar una empresa envolucrada.
	 * @param pgimEmpresaEventoActual
	 */
	void eliminarEmpresaEvento(PgimEmpresaEvento pgimEmpresaEventoActual, AuditoriaDTO auditoriaDTO);


}
