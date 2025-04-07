package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemSolBaseDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudBaseDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemSolBaseDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSolicitudBaseDoc;

public interface SolicitudBaseDocService {

	PgimSolicitudBaseDocDTO obtenerSolicitudBaseDocPorId(Long idSolicitudBaseDoc);

	List<PgimItemSolBaseDocDTO> listarItemsSolicitudBaseDocPorIdSolicitudBaseDoc(Long idSolicitudBaseDoc);
	
    Page<PgimSolicitudBaseDocDTO> listarSolicitudBaseDocPage(PgimSolicitudBaseDocDTO filtro, Pageable paginador);
    
    /**
	 * Permite obtener un entity solicitud de documento por Id
	 * @param idSolicitudBaseDoc
	 * @return
	 */
    PgimSolicitudBaseDoc getByIdSolicitudBaseDoc(Long idSolicitudBaseDoc);
    
    /**
     * Permite crear la solicitud de documento
     * 
     * @param pgimSolicitudBaseDocDTO
     * @param auditoriaDTO
     * @return
     */
    PgimSolicitudBaseDocDTO crearPlantilla(PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO, AuditoriaDTO auditoriaDTO);
    
    
    /**
     * Permite modificar la solicitud de documento
     * 
     * @param pgimSolicitudBaseDocDTO
     * @param pgimSolicitudBaseDocActual
     * @param auditoriaDTO
     * @return
     */
    PgimSolicitudBaseDocDTO modificarPlantilla(PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO, PgimSolicitudBaseDoc pgimSolicitudBaseDocActual, AuditoriaDTO auditoriaDTO);
    
    
    /**
     * Permite eliminar la solicitud de documento
     * 
     * @param pgimSolicitudBaseDocActual
     * @param auditoriaDTO
     */
    void eliminarPlantilla(PgimSolicitudBaseDoc pgimSolicitudBaseDocActual, AuditoriaDTO auditoriaDTO);
    
    /**
	 * Permite contar la cantidad de solicitudes de documento (plantilla) que existen dado una configuración base.
	 * @param idSolicitudBaseDoc
	 * @param noSolicitudBaseDoc
	 * @return
	 */
    Integer validarTraslapePlantilla(Long idSolicitudBaseDoc, String noSolicitudBaseDoc);
    
    /**
	 * Permite obtener un entity de item de solicitud de documento por Id
	 * @param idItemSolBaseDoc
	 * @return
	 */
    PgimItemSolBaseDoc getByIdItemSolBaseDoc(Long idItemSolBaseDoc);
    
    /**
	 * Permite obtener un dto de item de solicitud de documento por Id
	 * @param idItemSolBaseDoc
	 * @return
	 */
    PgimItemSolBaseDocDTO obtenerItemSolBaseDocPorId(Long idItemSolBaseDoc);
    
    /**
     * Permite crear un item de solicitud de documento
     * 
     * @param pgimItemSolBaseDocDTO
     * @param auditoriaDTO
     * @return
     */
    PgimItemSolBaseDocDTO registrarItemSolicitud(PgimItemSolBaseDocDTO pgimItemSolBaseDocDTO, AuditoriaDTO auditoriaDTO);
    
    
    /**
     * Permite modificar un item de solicitud de documento
     * 
     * @param pgimItemSolBaseDocDTO
     * @param pgimItemSolBaseDocActual
     * @param auditoriaDTO
     * @return
     */
    PgimItemSolBaseDocDTO modificarItemSolicitud(PgimItemSolBaseDocDTO pgimItemSolBaseDocDTO, PgimItemSolBaseDoc pgimItemSolBaseDocActual, AuditoriaDTO auditoriaDTO);
    
    
    /**
     * Permite eliminar un item de solicitud de documento
     * 
     * @param pgimItemSolBaseDocActual
     * @param auditoriaDTO
     */
    void eliminarItemSolicitud(PgimItemSolBaseDoc pgimItemSolBaseDocActual, AuditoriaDTO auditoriaDTO);
}
