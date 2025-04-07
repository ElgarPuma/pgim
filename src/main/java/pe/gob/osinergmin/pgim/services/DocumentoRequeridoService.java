package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemRecepcionDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemSolicitudDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemRecepcionDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimItemSolicitudDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSolicitudDoc;

public interface DocumentoRequeridoService {

	/**
	 * Permite la creación de la tabla PGIM_TC_SOLICITUD_DOC y PGIM_ITEM_SOLICITUD_DOC
	 * @param pgimSolicitudDocDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimSolicitudDocDTO crearDocumentoRequerido(PgimSolicitudDocDTO pgimSolicitudDocDTO, AuditoriaDTO auditoriaDTO);
	
	/**
	 * Permite obtener la cabecera y el detalle del documento requerido
	 * @param idSolicitudDoc
	 * @return
	 */
	PgimSolicitudDocDTO obtenerDocumentoRequerido(Long idSolicitudDoc);
	
	/**
	 * Permite obtener la entidad de la tabla PGIM_TC_SOLICITUD_DOC
	 * @param idSolicitudDoc
	 * @return
	 */
	PgimSolicitudDoc findById(Long idSolicitudDoc);
	
	/**
	 * Permite modificar las tablas PGIM_TC_SOLICITUD_DOC y PGIM_ITEM_SOLICITUD_DOC
	 * @param pgimSolicitudDocDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimSolicitudDocDTO modificarDocumentoRequerido(PgimSolicitudDocDTO pgimSolicitudDocDTO, AuditoriaDTO auditoriaDTO);
	
	/**
	 * Permite cambiar el estado a '0' del registro de la tabla PGIM_TC_SOLICITUD_DOC
	 * @param pgimSolicitudDoc
	 * @param auditoriaDTO
	 */
	void eliminarDocumentoRequerido(PgimSolicitudDoc pgimSolicitudDoc, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener la lista de los Docs. requeridos
	 * 
	 * @param idSupervision
	 * @return
	 */
	List<PgimSolicitudDocDTO> listarDocumentoRequerido(Long idSupervision);
	
	/**
	 * Permite crear un registro de la tabla PGIM_ITEM_RECEPCION_DOC
	 * @param pgimItemRecepcionDocDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimItemRecepcionDocDTO crearItemRecepcionDoc(PgimItemRecepcionDocDTO pgimItemRecepcionDocDTO,
            AuditoriaDTO auditoriaDTO);
	
    PgimItemSolicitudDocDTO crearItemSolicitudDoc(PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO);

    PgimItemSolicitudDocDTO modificarItemSolicitudDoc(PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO);

    PgimSolicitudDocDTO modificarSolicitudDoc(PgimSolicitudDocDTO pgimSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO);
    
    PgimSolicitudDocDTO obtenerSolicitudDocxIdSupervision(Long idSupervision);
    
    List<PgimItemSolicitudDocDTO> listarItemSolicitudDocxIdSolicitudDoc(Long idSolicitudDoc);
    
    void eliminarItemSolicitudDoc(PgimItemSolicitudDoc pgimItemSolicitudDoc, AuditoriaDTO auditoriaDTO);
    
    PgimItemSolicitudDoc itemSolicitudDocfindById(Long idItemSolicitudDoc);
    
    List<PgimItemRecepcionDocDTO> listarItemRecepcionDocxIdItemSolicitudDoc(Long idItemSolicitudDoc);
    
    void eliminarItemRecepcionDoc(PgimItemRecepcionDoc pgimItemRecepcionDoc, AuditoriaDTO auditoriaDTO);
    
    PgimItemRecepcionDoc itemRecepcionDocfindById(Long idItemRecepcionDoc);

	PgimItemSolicitudDocDTO modificarOrden(PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO, AuditoriaDTO auditoriaDTO);

	List<PgimItemSolicitudDocDTO> modificarFechasRequerimiento(PgimSolicitudDocDTO pgimSolicitudDocDTO,
			AuditoriaDTO obtenerAuditoria);

	List<PgimItemSolicitudDocDTO> modificarFechasRecepcion(PgimSolicitudDocDTO pgimSolicitudDocDTO,
			AuditoriaDTO obtenerAuditoria);

}
