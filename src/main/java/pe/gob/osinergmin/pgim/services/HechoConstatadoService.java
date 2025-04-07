package pe.gob.osinergmin.pgim.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroCotejoHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoCnsttdoFaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaHchocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCriterioSprvsion;
import pe.gob.osinergmin.pgim.models.entity.PgimHechoConstatado;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccion;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;

public interface HechoConstatadoService {

	List<PgimHechoConstatadoDTO> obtenerHechosConstatados(Long idSupervision, Long rol);

	/**
	 * Permite obtener la lista de hechos constatados que registran no cumplimiento.
	 * @param idSupervision
	 * @param rol
	 * @return
	 */
	List<PgimHechoConstatadoDTO> listarHechosConstatadosConNoCumplimientos(Long idSupervision, Long rol);

	List<PgimHechoConstatadoDTO> obtenerHechosConstatadosIncumplimiento(Long idSupervision, Long rol);

	List<PgimHechoConstatadoDTO> obtenerHechosConstatados(Long idSupervision, Long rol1, Long rol2);

	List<PgimHechoConstatadoDTO> obtenerHechosConstatadosIncumplimiento(Long idSupervision, Long rol1, Long rol2);

	List<PgimHechoConstatadoDTO> obtenerHechosConstatadosFicha(Long idSupervision, Long rol1, Long rol2);
	
	/**
	 * Permite obtener la lista de hechos constatados utilizado para la generación de las Fichas de evaluación de HV
	 * 
	 * @param idSupervision
	 * @param rol1
	 * @param rol2
	 * @return
	 */
	List<PgimHechoConstatadoDTO> obtenerHechosConstatadosParaFEHV(Long idSupervision, Long rol1, Long rol2);

	/**
	 * Permite recuperar la lista de criterios de la matriz de supervision
	 * 
	 * 
	 * @param idSupervision
	 * @param cogidoCumple
	 * @return
	 * @throws Exception
	 */
	// public List<PgimMatrizSupervisionAuxDTO> listarMatrizSupervision(Long
	// idSupervision, Long codigoCumple) throws Exception;
	Page<PgimMatrizSupervisionAuxDTO> listarMatrizSupervision(Long idSupervision, List<Long> codigoCumpleSelected, String tipoMatriz,
			Long idFaseSeleccion, Long idFaseActual,String deNorma, String deTipificacion, Pageable paginador) throws Exception;

	
	/**
	 * Permite recuperar la lista de hechos constatados por fase
	 * 
	 * 
	 * @param idSupervision
	 * @param idFaseSeleccion
	 * @param feGeneracion
	 * 
	 * @return
	 * @throws Exception
	 */
	List<PgimHechoCnsttdoFaseDTO> listarHechoCnsttdoFase(Long idSupervision,Long idFaseSeleccion,Date feGeneracion);
	
	/**
	 * Permite generar la data de configuración de criterios de la matriz de
	 * supervisión para una supervisión en particular
	 * 
	 * 
	 * @param idSupervision
	 * @return
	 * @throws Exception
	 */
	void generarDataConfiguracionCriterioMatriz(Long idSupervision, AuditoriaDTO auditoriaDTO);// throws Exception;

	/**
	 * Permite obtener un registro de la entidad PgimCriterioSprvsion a partir de un
	 * identificador de la tabla (ID)
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * 
	 */
	PgimCriterioSprvsionDTO obtenerCriterioSprvsionById(Long idCriterioSprvsion);

	/**
	 * Permite recuperar la lista de hechos constatados por criterio de la matriz de
	 * supervision
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * 
	 */
	List<PgimHechoConstatadoDTO> listarHechosConstatadosPorCriterioMatriz(Long idCriterioSprvsion, Long idRolProceso);
	
	
	/**
	 * Permite recuperar la lista de hechos verificados por criterio de la matriz de
	 * supervision - Histórico por fase
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @param idFase
	 * @return
	 * 
	 */
	List<PgimHechoConstatadoDTO> listarHechosConstatadosPorCriterioMatrizHistPorFase(Long idCriterioSprvsion,
			Long idFase);

	/**
	 * 
	 * 
	 * Permite recuperar la lista de obligaciones normativas por criterio de matriz
	 * de supervisión
	 * 
	 * @param idMatrizCriterio
	 * @return
	 * 
	 */
	List<PgimObligacionNormaAuxDTO> listarObligacionesNormativasXCriterio(Long idMatrizCriterio, Sort sort);

	/**
	 * 
	 * 
	 * Permite recuperar la lista de obligaciones normativas por hecho constatado
	 * 
	 * @param idHechoConstatado
	 * @return
	 * 
	 */
	List<PgimObligacionNormaAuxDTO> listarObligacionesNormativasPorHC(Long idHechoConstatado, Sort sort);

	/**
	 * Permirte crear un hecho constatado
	 * 
	 * @param pgimHechoConstatadoDTO
	 * @return
	 */
	PgimHechoConstatadoDTO crearHC(@Valid PgimHechoConstatadoDTO pgimHechoConstatadoDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite modificar los datos de un hecho constatado.
	 * 
	 * @param pgimHechoConstatadoDTO
	 * @param pgimHechoConstatadoActual
	 * @return
	 */
	PgimHechoConstatadoDTO modificarHC(@Valid PgimHechoConstatadoDTO pgimHechoConstatadoDTO,
			PgimHechoConstatado pgimHechoConstatadoActual, AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * Permite reasignar de criterio a un hecho constatado
	 * 
	 * @param pgimHechoConstatadoDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimHechoConstatadoDTO reasignarCriterioHV(@Valid PgimHechoConstatadoDTO pgimHechoConstatadoDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite obtener un hecho constatado de acuerdo con su identificador interno.
	 * 
	 * @param idHechoConstatado
	 * @return
	 */
	PgimHechoConstatado getByidHechoConstatado(Long idHechoConstatado);

	/**
	 * Permite eliminar lógicamente un hecho constatado.
	 * 
	 * @param pgimHechoConstatadoActual
	 */
	void eliminarHC(PgimHechoConstatado pgimHechoConstatadoActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite actualizar o crear los hechos constatados de la supervisión, al
	 * cambiar de paso de supervisión.
	 * 
	 */
	void actualizarHCxSupervision(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite recuperar la lista de cotejo de hechos constatados
	 * 
	 * 
	 * @param filtroCotejoHechoConstatadoDTO
	 * @return
	 * @throws Exception
	 */
	Page<PgimCotejoHechoCnsttdoDTO> listarCotejoHC(FiltroCotejoHechoConstatadoDTO filtroCotejoHechoConstatadoDTO,
			Pageable paginador) throws Exception;
	
	/**
	 * Permite generar la data histórica de hechos constatados por fase
	 * 
	 * 
	 * @param idSupervision
	 * @param idFase
	 * 
	 * @return
	 * @throws Exception
	 */
	void generarDataHistoricaFase(Long idSupervision,Long idFase) throws ParseException;

	/**
	 * Permite recuperar la lista de cotejo de hechos constatados
	 * 
	 * 
	 * @param filtroCotejoHechoConstatadoDTO
	 * @return
	 * @throws Exception
	 */
	List<PgimCotejoHechoCnsttdoDTO> listarCotejoHCpendientesConformidad(
			FiltroCotejoHechoConstatadoDTO filtroCotejoHechoConstatadoDTO);

	/**
	 * Permite obtener un DTO de un hecho constatado de acuerdo con su identificador
	 * interno.
	 * 
	 * @param idHechoConstatado
	 * @return
	 */
	PgimHechoConstatadoDTO obtenerHechoConstatadoDtoPorId(Long idHechoConstatado);

	/**
	 * Permite recuperar la lista histórica de hechos constatados para un ID de HC
	 * 
	 * 
	 * @param idHechoConstatado
	 * @return
	 * 
	 */
	List<PgimHechoConstatadoDTO> listaHistoricaHechosConstatadosPorID(Long idHechoConstatado);
	
	/**
	 * Permite obtener la lista de obligaciones fiscalizadas de una fiscalización.
	 * 
	 * @param idSupervision
	 * @return
	 */
	List<PgimOblgcnNrmtvaHchocDTO> listarObligacFiscalizadasPorCriterioSuperv(Long idSupervision);
	
	/**
	 * Permite actualizar las obligaciones fiscalizables de los hechos constatados de un criterio de fiscalización
	 * 
	 * @param pgimCriterioSprvsionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimCriterioSprvsionDTO actualizarObligacFiscalizablePorCriterioSuperv(PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite crear una infraccion
	 * 
	 * @param idOblgcnNrmtvaHchoc
	 */
	void crearInfraccion(Long idOblgcnNrmtvaHchoc, Long idInstanciaPaso, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite eliminar lógicamente una infracción, a partir de la obligación
	 * normativa.
	 * 
	 * @param idOblgcnNrmtvaHchoc
	 */
	void eliminarInfraccion(Long idOblgcnNrmtvaHchoc, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener un objeto DTO de una infracción de acuerdo con su
	 * identificador interno.
	 * 
	 * @param idInfraccion
	 * @return
	 */
	PgimInfraccionDTO obtenerInfraccionDtoPorId(Long idInfraccion);

	/**
	 * Permite listar las infracciones de una supervisión de manera paginada, 
	 * según filtros (idSupervision, fase seleccionada)
	 * 
	 * @param pgimInfraccionAuxDTOFiltro
	 * @param paginador
	 * @return
	 */
	Page<PgimInfraccionAuxDTO> listarInfraccionesSupervisionPaginado(PgimInfraccionAuxDTO pgimInfraccionAuxDTOFiltro, Pageable paginador);

	/**
	 * Permite listar las infracciones de un PAS de manera paginada, 
	 * según filtros (idPas, fase seleccionada)
	 * 
	 * @param pgimInfraccionAuxDTOFiltro
	 * @param paginador
	 * @return
	 */
	Page<PgimInfraccionAuxDTO> listarInfraccionesPasPaginado(PgimInfraccionAuxDTO pgimInfraccionAuxDTOFiltro, Pageable paginador);

	/**
	 * Permite obtener la lista de infracciones vigentes asociadas a una determinada
	 * supervisión.
	 * 
	 * @param idSupervision
	 * @return
	 */
	List<PgimInfraccionAuxDTO> listarInfraccionPorIdSupervision(Long idSupervision);

	/**
	 * Permite obtener la lista de infracciones vigentes asociadas a un determinado PAS.
	 * 
	 * @param idPas
	 * @return
	 */
	List<PgimInfraccionAuxDTO> listarInfraccionPorIdPas(Long idPas);

	/**
	 * Permite obtener una infracción de acuerdo con su identificador interno.
	 * 
	 * @param idInfraccion
	 * @return
	 */
	PgimInfraccion getInfraccionByidInfraccion(Long idInfraccion);

	/**
	 * Permite modificar los datos de una infracción.
	 * 
	 * @param pgimInfraccionDTO
	 * @param pgimInfraccionActual
	 * @return
	 */
	PgimInfraccionDTO modificarInfraccion(@Valid PgimInfraccionDTO pgimInfraccionDTO,
			PgimInfraccion pgimInfraccionActual, AuditoriaDTO auditoriaDTO) throws Exception;

	List<PgimMatrizSupervisionAuxDTO> listarMatrizSupervisionDescarga(final Long idSupervision, final Long codigoCumple,
			final String tipoMatriz) throws Exception;

	List<PgimCriterioSprvsionDTO> obtenerMatrizSupervision(final Long idSupervision) throws Exception;

	/**
	 * Permite obtener la lista de hechos constatados de acuerdo con los parámetros
	 * enviados.
	 * 
	 * @param idSupervision
	 * @param idTipoCumplimiento
	 * @return
	 */
	List<PgimHechoConstatadoDTO> obtenerHechosConstatadosPorTipoCumplimiento(Long idSupervision,
			Long idTipoCumplimiento);
	
	/**
	 * Permite obtener un registro de la entidad PgimCriterioSprvsion a partir de un
	 * identificador de la tabla (ID)
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * 
	 */
	PgimCriterioSprvsion findCriterioSprvsionById(Long idCriterioSprvsion);
		
	/**
     * Permite eliminar un criterio de una matriz de fiscalización
     * 
     * @param pgimCriterioSprvsionActual
     * @param auditoriaDTO
     */
	void eliminarCriterioFiscalizacion(PgimCriterioSprvsion pgimCriterioSprvsionActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite registras las conformidades de cada criterio del cuadro de verificación
   * siempre y cuando esta no tenga un hecho verificado registrado
	 * 
	 * @param filtroMatrizSupervisionDTO
	 * @param auditoriaDTO
	 */
	void registroConformidadCriterios(FiltroMatrizSupervisionDTO filtroMatrizSupervisionDTO, AuditoriaDTO auditoriaDTO) throws Exception;

}
