package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizGrpoCrtrioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizCriterio;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizGrpoCrtrio;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizSupervision;

/**
 * Interfaz para la gestión de los servicios relacionados con la matriz de
 * supervisión.
 * 
 * @descripción: Matriz de Supervisión
 *
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 13/05/2021
 */
public interface MatrizSupervisionService {

	/**
	 * Permite mostrar la lista de matriz de supervision
	 * 
	 * @return
	 */
	List<PgimMatrizSupervisionDTO> listarMatrizSupervision(PgimMatrizSupervisionDTO filtro);

	/**
	 * Permite obtener la lista de grupos de criterios de la matriz de supervisión
	 * por ID de Matriz de Supervision
	 * 
	 * @param idMatrizSupervision
	 * @return
	 */
	List<PgimMatrizGrpoCrtrioDTO> listarMatrizGrpoCrtrio(Long idMatrizSupervision) throws Exception;

	/**
	 * Permite obtener la lista criterio de la matriz de supervisión por ID de
	 * Matriz de Supervision
	 * 
	 * @param idMatrizSupervision
	 * @return
	 */
	Page<PgimMatrizCriterioAuxDTO> listarMatrizCriterio(Long idMatrizSupervision,
			PgimMatrizCriterioAuxDTO filtroPgimMatrizCriterioAuxDTO, Pageable paginador) throws Exception;

	/***
	 * Permite obtener un objeto entidad de matríz de supervision con el valor
	 * idMatrizSupervision.
	 * 
	 * @param idMatrizSupervision Identificador de matríz de supervisión.
	 * @return
	 */
	PgimMatrizSupervision getByIdMatrizSupervision(Long idMatrizSupervision);

	/**
	 * Permite obtener un registro de matriz de supervisión por ID de Matriz de
	 * Supervision
	 * 
	 * @param idMatrizSupervision
	 * @return
	 */
	PgimMatrizSupervisionDTO obtenerMatrizSupervisionPorId(Long idMatrizSupervision);

	/***
	 * Permite crear un nuevo grupo de criterio
	 * 
	 * @param pgimMatrizGrpoCrtrioDTO
	 * @return
	 */
	PgimMatrizGrpoCrtrioDTO crearGrupoCriterio(PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTO,
			AuditoriaDTO auditoriaDTO);

	/***
	 * Permite obtener un objeto grupo criterio de matriz de supervisión con el
	 * valor idMatrizGrpoCrtrio.
	 * 
	 * @param idMatrizGrpoCrtrio ID del grupo criterio de matriz de supervisión.
	 * @return
	 */
	PgimMatrizGrpoCrtrio getGrpoCrtrioById(Long idMatrizGrpoCrtrio);

	/***
     * Permite modificar un grupo de criterio de matriz de supervisión.
     * @param pgimMatrizGrpoCrtrioDTO grupo de criterio DTO que porta los datos con los nuevos valores para la actualización.
     * @param pgimMatrizGrpoCrtrio grupo de criterio al que se actualizarán los nuevos valores.
     * @return
     */
	PgimMatrizGrpoCrtrioDTO modificarGrupoCriterio(PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTO, PgimMatrizGrpoCrtrio pgimMatrizGrpoCrtrio, AuditoriaDTO auditoriaDTO);
	
	
	/***
     * Permite eliminar un grupo de criterio de matriz de supervisión.
     * @param idGrupoCriterio Id del grupo de criterio a eliminar.
     * 
     * @return
     */
	Long eliminarGrupo(Long idGrupoCriterio, AuditoriaDTO auditoriaDTO);
	
	/**
	 * Permite obtener un registro de grupo de  matriz de supervisión por ID de grupo 
	 * @param idGrupo
	 * @return
	 */
	PgimMatrizGrpoCrtrioDTO obtenerMatrizGrupoPorId(Long idGrupo);

	/**
	 * Permite crear la matriz de supervisión
	 * @param pgimMatrizSupervisionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimMatrizSupervisionDTO crearMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite validar la existencia de un cuadro de verificación
	 * @param idMatrizSupervision
	 * @param deMatrizSupervision
	 * @return
	 */
	List<PgimMatrizSupervisionDTO> validarExisteCuadroVerificacion(Long idMatrizSupervision, String deMatrizSupervision);

	/***
	 * Permite modificar una matriz de supervision.
	 * 
	 * @param pgimMatrizSupervisionDTO
	 * @param pgimMatrizSupervision
	 * @return
	 */
	PgimMatrizSupervisionDTO modificarMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO,
			PgimMatrizSupervision pgimMatrizSupervision, AuditoriaDTO auditoriaDTO);

	/**
	 * Me permite filtrar por el nombre de grupo de criterio de la matriz de
	 * supervision para la lista de criterios de la matriz
	 * 
	 * @param nombre
	 * @return
	 */
	List<PgimMatrizGrpoCrtrioDTO> filtrarPorGrupoCriterio(Long idMatrizSupervision);
	
	
	/**
	 * Permite obtener un registro de criterio de  matriz de supervisión por ID de criterio 
	 * @param idCriterio
	 * @return
	 */
	PgimMatrizCriterioDTO obtenerMatrizCriterioPorId(Long idCriterio);
	
	/***
     * Permite crear un criterio de matriz de supervisión.
     * @param pgimMatrizCriterioDTO
     * @return
     */
	PgimMatrizCriterioDTO crearCriterio(PgimMatrizCriterioDTO pgimMatrizCriterioDTO, AuditoriaDTO auditoriaDTO);
	
	/***
	 * Permite obtener un objeto criterio de matriz de supervisión con el
	 * valor idMatrizCriterio.
	 * 
	 * @param idMatrizCriterio ID del criterio de matriz de supervisión.
	 * @return
	 */	
	PgimMatrizCriterio getMatrizCriterioById(Long idMatrizCriterio);
	
	/***
     * Permite modificar un criterio de matriz de supervisión.
     * @param pgimMatrizCriterioDTO criterio DTO que porta los datos con los nuevos valores para la actualización.
     * @param pgimMatrizCriterio criterio al que se actualizarán los nuevos valores.
     * @return
     */
	PgimMatrizCriterioDTO modificarCriterio(PgimMatrizCriterioDTO pgimMatrizCriterioDTO, PgimMatrizCriterio pgimMatrizCriterio, AuditoriaDTO auditoriaDTO);

	/***
     * Permite eliminar un criterio de matriz de supervisión.
     * @param idCriterio Id del criterio a eliminar.
     * 
     * @return
     */
	Long eliminarCriterio(Long idCriterio, AuditoriaDTO auditoriaDTO);	
	
	/**
	 * Permite obtener la lista de obligaciones normativas de criterios de a matriz de supervisión por ID de
	 * Matriz de criterio
	 * 
	 * @param idMatrizCriterio
	 * @return
	 */
	List<PgimOblgcnNrmaCrtrioDTO> listarObligacionNrmaCriterio(Long idMatrizCriterio) throws Exception;

	/**
	 * Permite obtener la lista de obligaciones normativas de criterios por el id
	 * @param idOblgcnNrmaCrtrio
	 * @return
	 */
	PgimOblgcnNrmaCrtrioDTO obtenerObligacionNrmaCriterioPorId(Long idOblgcnNrmaCrtrio);

	/**
	 * Permite listar las obligaciones normativas del criterio
	 * @param paginador
	 * @return
	 */
	Page<PgimOblgcnNrmaCrtrioDTO> listarObligacionNrmaCriterioSeleccion(Long idMatrizCriterio, PgimOblgcnNrmaCrtrioDTO filtroPgimOblgcnNrmaCrtrioDTO, Pageable paginador) throws Exception;

	/**
	 * Permite quitar o desvincular una obligación de un criterio de matriz de supervisión
	 * 
	 * @param idOblgcnNrmaCrtrio
	 * @return
	 * @throws Exception
	 */
	Long eliminarObligacionNormaCriterio(Long idOblgcnNrmaCrtrio, AuditoriaDTO auditoriaDTO);
	
	PgimOblgcnNrmaCrtrioDTO seleccionarObligacion(@Valid PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTO, AuditoriaDTO auditoriaDTO);
	
	/***
     * Permite obtener el identificador de una matriz de supervisión 
     * por el identificador de una fiscalización.
     * @param idSupervision Id de la fiscalización.
     * 
     * @return
     */
	Long obtenerIdMatrizSupervisionByIdSupervision(Long idSupervision);	
	
	/**
	 * Permite obtener la lista de criterios del cuadro de verificación
	 * que no forman parte de una fiscalización
	 * 
	 * @param idSupervision 
	 * @param idMatrizSupervision
	 * @return
	 */
	Page<PgimMatrizCriterioAuxDTO> listarCriteriosNoFiscalizacion(Long idSupervision, Long idMatrizSupervision,
			PgimMatrizCriterioAuxDTO filtroPgimMatrizCriterioAuxDTO, Pageable paginador) throws Exception;

	/**
	 * Permite obtener el cuadro de verificación asociado con la fiscalización en cuestión.
	 * @param idSupervision
	 * @return
	 */
    PgimMatrizSupervisionDTO obtenerCuadroVerificacionPorIdSupervision(Long idSupervision);

	/**
	 * Permite obtener el listado de cuadros de verificación de acuerdo con la especialidad en cuestión.
	 * @param pgimMatrizSupervisionDTO
	 * @param paginador
	 * @return
	 */
	Page<PgimMatrizSupervisionDTO> listarMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO,
			Pageable paginador);

	/**
	 * Permite hacer el copiado de un cuadro de verificación seleccionado desde del FE
	 * @param pgimMatrizSupervisionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimMatrizSupervisionDTO copiarMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTOPadre, AuditoriaDTO auditoriaDTO) throws Exception;

	public void validarEliminacionCuadroVerificacion(PgimMatrizSupervision pgimMatrizSupervisionActual, AuditoriaDTO auditoriaDTO) throws Exception;
	public void eliminarCuadroVerificacion(PgimMatrizSupervision pgimMatrizSupervisionActual, AuditoriaDTO auditoriaDTO) throws Exception;
}
