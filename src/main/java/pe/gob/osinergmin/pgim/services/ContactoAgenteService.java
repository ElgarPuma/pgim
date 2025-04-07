package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContactoAgenteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimContactoAgente;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;

public interface ContactoAgenteService {

	/**
	 * Permite obtener la lista de contactos del agente supervisado
	 * @param idAgenteSupervisado
	 * @param paginador
	 * @return
	 */
	Page<PgimContactoAgenteDTO> filtrar(Long idAgenteSupervisado, Pageable paginador);

	/**
	 * Permite crear el contacto del agente supervisado
	 * @param pgimContactoAgenteDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimContactoAgenteDTO crearContactoAgente(PgimContactoAgenteDTO pgimContactoAgenteDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite modificar el contacto del agente supervisado
	 * @param pgimContactoAgenteDTO
	 * @param pgimPersona
	 * @param pgimAgenteSupervisado
	 * @param auditoriaDTO
	 * @return
	 */
	PgimContactoAgenteDTO modificarContactoAgente(PgimContactoAgenteDTO pgimContactoAgenteDTO,
    		PgimPersona pgimPersona, PgimAgenteSupervisado pgimAgenteSupervisado, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite eliminar el contacto del agente supervisado
	 * @param pgimContactoAgente
	 * @param auditoriaDTO
	 */
    void eliminarContactoAgente(PgimContactoAgente pgimContactoAgente, AuditoriaDTO auditoriaDTO);
    
    /**
     * Permite obtener el registro DTO del contacto del agente supervisado por id
     * @param idContactoAgente
     * @return
     */
    PgimContactoAgenteDTO obtenerContactoAgentePorId(Long idContactoAgente);
    
    /**
     * Permite obtener la entidad del contacto del agente supervisado
     * @param idContactoAgente
     * @return
     */
    PgimContactoAgente getByIdContactoAgente(Long idContactoAgente);
}
