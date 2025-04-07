package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoSubcomponenteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteMinero;

public interface PgimComponenteMinService {

	List<PgimComponenteMineroDTO> listarComponenteMinero(Long idUnidadMinera, Sort sort);

	List<PgimComponenteMinero> listarComponenteMineroAll();

	/**
	 * Permite la creacion de un nuevo componente minero
	 * 
	 * @param pgimComponenteMineroDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimComponenteMineroDTO crearComponenteMinero(@Valid PgimComponenteMineroDTO pgimComponenteMineroDTO,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite modificar un componente minero
	 * 
	 * @param pgimComponenteMineroDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimComponenteMineroDTO modificarComponenteMinero(@Valid PgimComponenteMineroDTO pgimComponenteMineroDTO,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el id del componente minero
	 * 
	 * @param idComponenteMinero
	 * @return
	 */
	PgimComponenteMinero getByIdComponenteMinero(Long idComponenteMinero);

	/**
	 * Permite eliminar un componente minero
	 * 
	 * @param pgimComponenteMineroActual
	 * @param auditoriaDTO
	 */
	void eliminarComponenteMinero(PgimComponenteMinero pgimComponenteMineroActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener las propiedades del componente minero por el
	 * idComponenteMinero
	 * 
	 * @param idComponenteMinero
	 * @return
	 */
	PgimComponenteMineroDTO obtenerComponenteMineroPorId(Long idComponenteMinero);

	List<PgimComponenteMineroDTO> listarComponentePadrePorUm(Long idUnidadMinera, Long idComponenteMinero);
	
	// List<PgimComponenteMineroDTO> listarComponenteMineroNoAsociadoHc(Long idUnidadMinera);
	List<PgimComponenteMineroDTO> listarComponenteMineroNoAsociadoHc(Long idUnidadMinera, List<PgimCmineroSprvsionDTO> descListaCmineroSprvsion, 
			List<PgimComponenteHcDTO> descListaComponenteHcDTO, String tipoObjeto, Long idHechoConstatado);

	Page<PgimComponenteMineroDTO> listarReporteComponenteUMPaginado(PgimComponenteMineroDTO filtroPgimComponenteMineroDTO, Pageable paginador);
	
	 /***
      * Permite obtener una lista de los subtipos de componentes mineros.
      * 
      * @return
      */
	  List<PgimTipoSubcomponenteDTO> listarSubTipoComponentes();
	  
	/**
	 * Permite listar el universo de componentes 
	 * @return
	 */
    List<PgimComponenteMineroDTO> listarComponentes();
    
    /**
     * Permite listar los componentes mineros de acuerdo con la palabra clave y la unidad minera
     * 
     * @param palabra
     * @param idUnidadMinera
     * @return
     */
    List<PgimComponenteMineroDTO> listarPorPalabraClaveYUm(String palabra, Long idUnidadMinera);

	String crearComponentesMineros(List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	List<PgimComponenteMineroDTO> obtenerNuevosComponentes(List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO) throws Exception;
	
	String crearComponentesMinerosSupervision(List<PgimCmineroSprvsionDTO> lPgimCmineroSprvsionDTO, AuditoriaDTO auditoriaDTO) throws Exception;
}
