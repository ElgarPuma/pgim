package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.ItemMedicionIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedicionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMedicion;

public interface MedicionService {

	/**
	 * Permite obtener el listado de las mediciones de flujos de trabajo
	 * @param pgimMedicionDTOFiltro
	 * @param paginador
	 * @return
	 */
	Page<PgimMedicionDTO> listarMediciones(PgimMedicionDTO pgimMedicionDTOFiltro, Pageable paginador);

	/**
	 * Permite hacer modificaciones en la medición de flujos de trabajo
	 * @param pgimMedicionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimMedicionDTO crearMedicion(PgimMedicionDTO pgimMedicionDTO,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite hacer modificaciones en la medición de flujos de trabajo
	 * @param pgimMedicionDTO
	 * @param pgimMedicionActual
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimMedicionDTO modificarMedicion(PgimMedicionDTO pgimMedicionDTO, PgimMedicion pgimMedicionActual, 
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite hacer modificaciones en la medición de flujos de trabajo
	 * @param pgimMedicionDTO
	 * @param pgimMedicionActual
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimMedicionDTO publicarMedicion(PgimMedicionDTO pgimMedicionDTO, PgimMedicion pgimMedicionActual, 
			AuditoriaDTO auditoriaDTO) throws Exception;
    
	/**
	 * Permite eliminar logicamente una medición de flujos de trabajo
	 * @param pgimMedicionActual
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	void eliminarMedicion(PgimMedicion pgimMedicionActual, 
			AuditoriaDTO auditoriaDTO) throws Exception;
	
  /**
	 * Permite obtener la medición del flujo de trabajo por su identificador interno
	 * @param idMedicion
	 * @return
	 */
	PgimMedicionDTO obtenerMedicionPorId(Long idMedicion);
	
	/**
	 * Permite obtener la medición del flujo de trabajo( Entity ) por su identificador interno
	 * @param idMedicion
	 * @return
	 */
	PgimMedicion getByIdMedicion(Long idMedicion);
	
	/**
	 * Permite listar los items de una medición de indicadores por actor de negocio
	 * 
	 * @param filtro
	 * @return
	 */
	List<ItemMedicionIndicadorDTO> listarItemsMedicionPorActorNegocio(FiltroIndicadorDTO filtro);
	
	/**
	 * Permite listar los items de una medición de indicadores por característica de fiscalización
	 * 
	 * @param filtro
	 * @return
	 */
	List<ItemMedicionIndicadorDTO> listarItemsMedicionPorCaracteristica(FiltroIndicadorDTO filtro);

	List<PgimMedicionDTO> obtenerMedicionesPorIdIndicador(Long indicador);

}
