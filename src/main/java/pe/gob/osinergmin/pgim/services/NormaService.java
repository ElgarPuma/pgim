package pe.gob.osinergmin.pgim.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemTipificacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaEnlaceDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaItemDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemTipificacion;
import pe.gob.osinergmin.pgim.models.entity.PgimNorma;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaItem;

public interface NormaService {
	
	
	/**
	 * Permite listar las normas por palabra clave.
	 * 
	 * @param palabra	Palabra clave utilizada para buscar en la lista de normas
	 * @return
	 */
	List<PgimNormaDTO> listarPorPalabraClave(String palabra);
	
	/**
	 * Permite consultar la lista de normas de acuerdo con al titulo corto o nombre o tipo de norma.
	 * @param pgimNormaAuxDTO
	 * @return
	 */
	List<PgimNormaDTO> listarNormaPorNoCortoONoNormaYTipoNorma(PgimNormaAuxDTO pgimNormaAuxDTO);
	
	
	/**
	 * Permite listar las normas.
	 * 
	 * @param filtroNorma
	 * @param paginador
	 * @return
	 */
    Page<PgimNormaAuxDTO> filtrar(PgimNormaDTO filtroNorma, Pageable paginador);
    
    
    /**
     * Permite crear la norma
     * 
     * @param pgimNormaDTO
     * @param auditoriaDTO
     * @return
     */
    PgimNormaDTO crearNorma(PgimNormaDTO pgimNormaDTO, AuditoriaDTO auditoriaDTO);
    
    
    /**
     * Permite modificar la norma
     * 
     * @param pgimNormaDTO
     * @param pgimNormaActual
     * @param auditoriaDTO
     * @return
     */
    PgimNormaDTO modificarNorma(PgimNormaDTO pgimNormaDTO, PgimNorma pgimNormaActual, AuditoriaDTO auditoriaDTO);
    
    
    /**
     * Permite eliminar la norma
     * 
     * @param pgimNormaActual
     * @param auditoriaDTO
     */
    void eliminarNorma(PgimNorma pgimNormaActual, AuditoriaDTO auditoriaDTO);


    /**
     * Permite obtener un objeto norma por Id
     * 
     * @param idNorma
     * @return
     */
	PgimNormaDTO obtenerNormaPorId(Long idNorma);
	
	
	/**
     * Permite obtener un objeto normaAux por Id
     * 
     * @param idNorma
     * @return
     */
	PgimNormaAuxDTO obtenerNormaAuxPorId(Long idNorma);
	
	
	/**
	 * Permite obtener un entity norma por Id
	 * @param idNorma
	 * @return
	 */
	PgimNorma getByIdNorma(Long idNorma);
	
	
	/**
	 * Permite obtener la lista de enlaces relacionados de la norma por ID de norma
	 * 
	 * @param idNorma
	 * @return
	 */
	List<PgimNormaEnlaceDTO> listarEnlacesNorma(Long idNorma) throws Exception;
	
	
	/***
	 * Permite crear un nuevo enlace
	 * 
	 * @param pgimNormaEnlaceDTO
	 * @return
	 */
	PgimNormaEnlaceDTO crearEnlace(PgimNormaEnlaceDTO pgimNormaEnlaceDTO, AuditoriaDTO auditoriaDTO);
	
	
	/***
     * Permite eliminar un enlace.
     * @param idEnlace Id del enlace a eliminar.
     * 
     * @return
     */
	Long eliminarEnlace(Long idEnlace, AuditoriaDTO auditoriaDTO);
	
	
	/**
	 * Permite obtener la lista de items de la norma por ID de norma
	 * 
	 * @param idNorma
	 * @return
	 */
	Page<PgimNormaItemAuxDTO> listarItemsNorma(Long idNorma, PgimNormaItemDTO filtroPgimNormaItemDTO, Pageable paginador) throws Exception;
	
	
	/**
	 * Permite obtener un entity normaItem por Id
	 * @param idNormaItem
	 * @return
	 */
	PgimNormaItem getByIdNormaItem(Long idNormaItem);
	
	
	/***
	 * Permite crear un nuevo ítem de norma
	 * 
	 * @param pgimNormaItemDTO
	 * @return
	 */
	PgimNormaItemDTO crearItem(PgimNormaItemDTO pgimNormaItemDTO, AuditoriaDTO auditoriaDTO);
	
	
	/**
	 * Permite modificar el ítem de norma
	 * 
	 * @param pgimNormaItemDTO
	 * @param pgimNormaItemActual
	 * @param auditoriaDTO
	 * @return
	 */
    PgimNormaItemDTO modificarNormaItem(PgimNormaItemDTO pgimNormaItemDTO, PgimNormaItem pgimNormaItemActual, AuditoriaDTO auditoriaDTO);
    
    
    /***
     * Permite eliminar un ítem de norma.
     * @param idItem Id del ítem a eliminar.
     * 
     * @return
     */
	Long eliminarItem(Long idItem, AuditoriaDTO auditoriaDTO);
	
	
	/**
	 * Permite eliminar un ítem de norma y según sea el caso eliminar recursivamente los ítems hijos 
	 * Hacer uso del método eliminarItem  
	 * 
	 * @param idItemPadre
	 * @param auditoriaDTO
	 * @param idItemsEliminados
	 * @return
	 */
	ArrayList<Long> eliminarItemEHijos(Long idItemPadre, AuditoriaDTO auditoriaDTO, ArrayList<Long> idItemsEliminados);
    
    
    /**
     * Permite obtener un objeto normaItem por Id
     * 
     * @param idNormaItem
     * @return
     */
	PgimNormaItemDTO obtenerItemPorId(Long idNormaItem);
	
	
	/**
     * Permite obtener lista de normaItems que son hijos de otro Item
     * 
     * @param idNormaItemPadre
     * @return
     */
	List<PgimNormaItemDTO> obtenerItemsHijos(Long idNormaItemPadre);
	
	/**
     * Permite listar los items de tipificación
     * 
	 * @param filtro
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
    Page<PgimNormaDTO> listarTipificacion(PgimNormaDTO filtro, Pageable paginador) throws Exception;
	

    /***
     * Permite eliminar un item de tipificacion dado su id.
     * 
     * @param pgimNormaActual
     */
    void eliminarTipificacion(PgimNorma pgimNormaActual, AuditoriaDTO auditoriaDTO);

    /**
     * Me permite obtener las propiedades del item de tipificacion por el id
     * 
     * @param idNorma
     * @return
     */
    PgimNormaDTO obtenerTipificacionPorId(Long idNorma);

	/**
	 * Me permite listar los items de tipificacion
	 * @param idNorma
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	Page<PgimItemTipificacionDTO> listarItemTipificacion(Long idNorma, Pageable paginador) throws Exception;

	/***
     * Permite obtener un objeto entidad del item de tipificacion con el valor
     * idItemTipificacion.
     * 
     * @param idItemTipificacion Identificador del item de tipificacion.
     * @return
     */
    PgimItemTipificacion getByIdItemTipificacion(Long idItemTipificacion);

    /***
     * Permite eliminar un item de tipificacion dado su id.
     * 
     * @param pgimContratoActual
     */
    void eliminarItemTipificacion(PgimItemTipificacion pgimItemTipificacionActual, AuditoriaDTO auditoriaDTO);

    /**
     * Me permite obtener las propiedades del item de tipificacion por el id
     * 
     * @param idItemTipificacion
     * @return
     */
    PgimItemTipificacionDTO obtenerItemTipificacionPorId(Long idItemTipificacion);

    	/***
	 * Permite crear un nuevo item de tipificación
	 * 
	 * @param pgimMatrizGrpoCrtrioDTO
	 * @return
	 */
	PgimItemTipificacionDTO crearItemTipificacion(PgimItemTipificacionDTO pgimItemTipificacionDTO,
     AuditoriaDTO auditoriaDTO);

    /***
     * Permite modificar un item de tipificación.
     * 
     * @param pgimItemTipificacionDTO
     * @param pgimItemTipificacion
     * @param auditoriaDTO
     * @return
     */
    PgimItemTipificacionDTO modificarItemTipificacion(PgimItemTipificacionDTO pgimItemTipificacionDTO, PgimItemTipificacion pgimItemTipificacion,
            AuditoriaDTO auditoriaDTO);
    
    
	/**
	 * Permite obtener la lista de items de la norma por ID de norma
	 * 
	 * @param idNorma
	 * @return
	 */
	List<PgimNormaItemAuxDTO> listarItemsNormaParaObligacion(Long idNorma) throws Exception;
	
	Page<PgimNormaItemAuxDTO> listarItemsParaObligacionFilter(PgimNormaItemAuxDTO filtroPgimNormaItemAuxDTO, Pageable paginador) throws Exception;
    
	List<PgimNormaItemAuxDTO> obtenerItemNormasHijosPorUbicacion(PgimNormaItemAuxDTO filtroPgimNormaItemAuxDTO)
			throws Exception;
	
	/**
	 * Permite obtener el listado de normas item de una obligación fiscalizable 
	 * @param filtroPgimOblgcnNrmtvaItemDTO
	 * @return
	 * @throws Exception
	 */
	List<PgimOblgcnNrmtvaItemDTO> obtenerItemNormasHijosPorUbicacionPorObligacionNormativa(PgimOblgcnNrmtvaItemDTO filtroPgimOblgcnNrmtvaItemDTO)
			throws Exception;
	
	/**
	 * Me permite listar los items de tipificacion
	 * @param filtro
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	Page<PgimItemTipificacionDTO> listarItemTipificacionReportePaginado(PgimItemTipificacionDTO filtro, Pageable paginador) throws Exception;
	
	/**
	 * Permite obtener el listado de normas vinculados a un cuadro de verificación
	 * @param pgimNormaAuxDTO
	 * @param paginador
	 * @return
	 */
	Page<PgimNormaAuxDTO> listarNormasDeCuadroVerificacion(PgimNormaAuxDTO pgimNormaAuxDTO, Pageable paginador);
	
	List<PgimNormaDTO> listarCuadroTipificacion() throws Exception;


	
}
