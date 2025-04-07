package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemSelectAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaObligacion;


public interface NormaObligacionService {

	/**
	 * Permite listar las obligaciones de tipificacion.
	 * 
	 * @param filtroNormaObligacion
	 * @param paginador
	 * @return
	 */
    Page<PgimNormaObligacionAuxDTO> filtrar(Long idItemTipificacion, Pageable paginador);
    
    
    /**
     * Permite crear la norma-obligacion
     * 
     * @param lPgimNormaObligacionDTO
     * @param auditoriaDTO
     * @return
     */
    PgimNormaItemSelectAuxDTO crearNormaObligacionList(PgimNormaItemSelectAuxDTO pgimNormaItemSelectAuxDTO, AuditoriaDTO auditoriaDTO);
    
    /**
     * Permite crear la norma-obligacion
     * 
     * @param lPgimNormaObligacionDTO
     * @param auditoriaDTO
     * @return
     */
    PgimNormaObligacionDTO crearNormaObligacion(PgimNormaObligacionDTO lPgimNormaObligacionDTO, AuditoriaDTO auditoriaDTO);
    
    /**
     * Obtener una normaObligacionDTO 
     * @param idNormaObligacion
     * @return
     */
    PgimNormaObligacionDTO obtenerNormaObligacionPorId(Long idNormaObligacion);
    
    /**
     * Obtener NormaObligacionAux 
     * @param idNormaObligacion
     * @return
     */
    PgimNormaObligacionAuxDTO obtenerNormaObligacionAuxPorId(Long idNormaObligacion);
    
    /**
     * Eliminar una norMaObligacion
     * @param pgimNormaObligacionDTO
     * @param auditoriaDTO
     */
    void eliminarNormaObligacion(PgimNormaObligacionDTO pgimNormaObligacionDTO, AuditoriaDTO auditoriaDTO);
    
    /**
     * Obtener una normaObligacion 
     * @param idNormaObligacion
     * @return
     */
    PgimNormaObligacion getByIdNormaObligacion(Long idNormaObligacion);
    
    
	/**
	 * Permite modificar una obligacion
	 * 
	 * @param pgimNormaObligacionDTO
	 * @param pgimNormaObligacion
	 * @param auditoriaDTO
	 * @return
	 */
    PgimNormaObligacionAuxDTO modificarNormaObligacion(PgimNormaObligacionDTO pgimNormaObligacionDTO, PgimNormaObligacion pgimNormaObligacion, AuditoriaDTO auditoriaDTO);
    
    
}
