package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionCmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTO;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Autorizacion
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 03/09/2020
 * @fecha_de_ultima_actualización: 03/09/2020 
 */
public interface AutorizacionService {
    
    /**
     * Permite listar las autorizaciones
     * @param idUnidadMinera
     * @return
     */
    Page<PgimAutorizacionDTO> listarAutorizacion(Long idUnidadMinera, Pageable paginador);
    // List<PgimAutorizacionDTO> listarAutorizacion(Long idUnidadMinera);
    
    PgimAutorizacionDTO obtenerAutorizacionById(Long idAutorizacion);
	
	PgimAutorizacionDTO crearAutorizacion(PgimAutorizacionDTO autorizacionDTO, AuditoriaDTO auditoriaDTO);
	
	PgimAutorizacionDTO modificarAutorizacion(PgimAutorizacionDTO autorizacionDTO, AuditoriaDTO auditoriaDTO);
	
	void eliminarAutorizacion(PgimAutorizacionDTO pgimAutorizacionDTO, AuditoriaDTO auditoriaDTO);
	
	List<PgimAutorizacionCmDTO> getListAutorizacionCmByIdAutorizacion(Long idAutorizacion);
	
}
