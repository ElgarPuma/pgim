package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.GrupoEntregableLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContrLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEntregableLiquidaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimItemLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;

public interface LiquidacionService {

	/**
	 * Permite crear la liquidación
	 * 
	 * @param pgimLiquidacionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimLiquidacionDTO crearLiquidacion(PgimLiquidacionDTO pgimLiquidacionDTO, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite obtener la lista de liquidaciones usando un filtro y paginador
	 * 
	 * @param filtroLiquidacion
	 * @param paginador
	 * @param auditoriaDTO
	 * @return
	 */
	Page<PgimLiquidacionAuxDTO> listarLiquidaciones(PgimLiquidacionAuxDTO filtroLiquidacion, Pageable paginador,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener la lista de liquidaciones aux usando un filtro y paginador para el reporte detallado de liquidaciones
	 * 
	 * @param filtroLiquidacion
	 * @param paginador
	 * @return
	 */
	Page<PgimContrLiquidacionAuxDTO> listarContrLiquidacionAux(PgimContrLiquidacionAuxDTO filtroLiquidacion, Pageable paginador);			

	/**
	 * Permite obtener la lista de liquidaciones del contrato usando un filtro y
	 * paginador
	 * 
	 * @param idContrato
	 * @param filtroLiquidacion
	 * @param paginador
	 * @param auditoriaDTO
	 * @return
	 */
	Page<PgimLiquidacionAuxDTO> listarLiquidacionesContrato(Long idContrato, PgimLiquidacionAuxDTO filtroLiquidacion,
			Pageable paginador, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el objeto PgimLiquidacionDTO por el id liquidación
	 * 
	 * @param idLiquidacion
	 * @return
	 */
	PgimLiquidacionDTO obtenerLiquidacionPorId(Long idLiquidacion);

	/**
	 * Me permite validar uno de los campos de Item liquidacion de la Penalidad en
	 * el dialogo por especialidad Validar "moPenalidadReincidencia"
	 * 
	 * @param idLiquidacion
	 * @return
	 */
	PgimLiquidacionDTO validarPenalidadPorEspecialidad(Long idLiquidacion);

	/**
	 * Permite obtener la liquidación enriquecida con datos complementarios.
	 */
	PgimLiquidacionAuxDTO obtenerLiquidacionAuxPorId(Long idLiquidacion);

	/**
	 * Permite eliminar una liquidación
	 * 
	 * @param pgimLiquidacionActual
	 * @param auditoriaDTO
	 */
	void eliminarLiquidacion(PgimLiquidacion pgimLiquidacionActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el objeto PgimLiquidacion por el id liquidación
	 * 
	 * @param idLiquidacion
	 * @return
	 */
	PgimLiquidacion findById(Long idLiquidacion);

	/**
	 * Permite lista la lista de entregables pendientes de liquidar usado en el
	 * dialogo del boton seleccionar
	 * 
	 * @param idLiquidacion
	 * @param idTipoEntregable
	 * @return
	 * @throws Exception
	 */
	List<PgimLiquidacionDTO> obtenerEntregablesALiquidar(Long idLiquidacion, Long idTipoEntregable) throws Exception;

	/**
	 * Permite obtener la lista de numeros de liquidacion, filtro por palabra
	 * 
	 * @param palabra
	 * @return
	 */
	List<PgimLiquidacionDTO> listarPorNumeroLiquidacion(String palabra);

	/**
	 * Permite obtener la lista de los items de liquidación por id liquidación
	 * 
	 * @param idLiquidacion
	 * @return
	 */
	List<PgimItemLiquidacionDTO> listarItemLiquidacionActasInformes(Long idLiquidacion);

	/**
	 * Permite obtener la lista de los items de liquidación por id liquidación
	 * 
	 * @param idLiquidacion
	 * @return
	 */
	List<PgimEntregableLiquidaAuxDTO> listarEntregablesPorLiquidacion(Long idLiquidacion);

	/**
	 * Permite eliminar un item de liquidación.
	 * 
	 * @param pgimItemLiquidacionActual
	 * @param auditoriaDTO
	 */
	void eliminarItemLiquidacion(PgimItemLiquidacion pgimItemLiquidacionActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el objeto PgimItemLiquidacion por el id item liquidacion
	 * 
	 * @param idItemLiquidacion
	 * @return
	 */
	PgimItemLiquidacion findByIdIL(Long idItemLiquidacion);

	/**
	 * Permite crear un item de liquidación.
	 * 
	 * @param lPgimLiquidacionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	List<PgimItemLiquidacionDTO> crearItemLiquidacion(List<PgimLiquidacionDTO> lPgimLiquidacionDTO,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite validar la transición de pasos en el flujo de trabajo de una
	 * liquidación.
	 * 
	 * @param pgimRelacionPaso
	 * @param pgimInstanciaPaso
	 */
	void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso);

	/**
	 * Permite realizar acciones dada la transición de pasos en el flujo de trabajo
	 * de la liquidación
	 */
	void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite filtrar los pasos siguientes de acuerdo con la lógica de negocio de
	 * la supervisión.
	 * 
	 * @param lPgimRelacionPasoDTOSiguientes
	 * @param pgimInstanciaPasoActual
	 * @param pgimRelacionPasoActual
	 * @return
	 */
	List<PgimRelacionPasoDTO> filtrarPasosSiguientes(List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
			PgimInstanciaPaso pgimInstanciaPasoActual, PgimRelacionPaso pgimRelacionPasoActual);

	/**
	 * Permite obtener la liquidación enriquecida con datos complementarios por
	 * idInstanciaProceso.
	 */
	PgimLiquidacionAuxDTO obtenerLiquidacionAuxPorInstanciaProceso(Long idInstanciaProceso);

	/**
	 * Permite crear un item de liquidación de informe de penalidad.
	 * 
	 * @param lPgimLiquidacionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimItemLiquidacionDTO crearItemLiquidacionInformePenalidad(@Valid PgimLiquidacionDTO lPgimLiquidacionDTO,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite modificar los datos de la liquidacion de informe de penalidad
	 * 
	 * @param pgimItemLiquidacionDTO
	 * @param pgimItemLiquidacionDTOActual
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimItemLiquidacionDTO modificarItemLiquidacionInfoPenalidad(@Valid PgimItemLiquidacionDTO pgimItemLiquidacionDTO,
			AuditoriaDTO auditoriaDTO);

	PgimItemLiquidacion getByIdItemLiquidacion(Long idItemLiquidacion);

	/**
	 * Permite obtener las propiedades necesarias del item de liquidacion
	 * 
	 * @param idItemLiquidacion
	 * @return
	 */
	PgimItemLiquidacionDTO obtenerItemLiquidacionPorId(Long idItemLiquidacion);

	/**
	 * Permite filtrar la lista de subcategorías a partir de la información actual
	 * de la liquidación y la lista de subcategorías previstas
	 * para la fase.
	 * 
	 * @param idLiquidacion
	 * @param idInstanciaPaso
	 * @param lPgimSubcategoriaDocDTO
	 * @return
	 * @throws Exception
	 */
	List<PgimSubcategoriaDocDTO> filtrarSubCategoriasDoc(Long idLiquidacion, Long idInstanciaPaso, 
			List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO) throws Exception;

	/**
	 * Permite obtener la lista de entregables por liquidar que cumplan con los
	 * criterios enviados a modo de argumentos
	 * 
	 * @param idContrato
	 * @param idTipoEntregable
	 * @param idDivisionSupervisora
	 * @param idSubtipoSupervision
	 * @param idMotivoSupervision
	 * @return
	 */
	List<PgimEntregableLiquidaAuxDTO> obtenerEntregablesALiquidar(Long idContrato, Long idTipoEntregable,
			Long idDivisionSupervisora, Long idSubtipoSupervision, Long idMotivoSupervision);

	/**
	 * Me permite listar los entregables por contrato
	 * @param idContrato
	 * @param paginador
	 * @return
	 */
	Page<PgimItemConsumoDTO> listarEntregablesPorContrato(Long idContrato, Long descPreComprometido,
	Long descComprometido, Long descPorLiquidar, Long descLiquidado, Long descFacturado, PgimItemConsumoDTO filtro, Pageable paginador);

	/**
	 * Me permite listar los entregables que estan por liquidar de un contrato
	 * STORY: PGIM-6809: Lista de entregables disponibles para liquidar
	 * @param filtro
	 * @param paginador
	 * @return
	 */
	Page<PgimItemConsumoDTO> listarEntregablesPorLiquidarPorContrato(PgimItemConsumoDTO filtro, Pageable paginador);
	
	/**
	 * Permite agrupar una lista dada de entregables listos para liquidar 
	 * 
	 * @param lPgimItemConsumoDTO	Lista de entregables que será agrupada
	 * @return
	 * @throws Exception
	 */
	List<GrupoEntregableLiquidacionDTO> agruparEntregablesLiquidar(List<PgimItemConsumoDTO> lPgimItemConsumoDTO) throws Exception;
	
	/**
	 * Permite realizar la copia de documentos de Liquidación al expediente Siged del contrato asociado
	 */
	void copiarDocsLiquidacionAExpedienteContrato(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso, PgimLiquidacion pgimLiquidacion, AuditoriaDTO auditoriaDTO);
	
	/**
	 * Permite obtener la cantidad de liquidaciones pendientes de atención por el usuario en sesión
	 * 
	 * @param auditoriaDTO
	 * @return
	 */
	Integer contarLiquidacionesPendientes(AuditoriaDTO auditoriaDTO);

	/**
	 * Permite aplicar o nó aplicar la penalidad por reemplazo del personal para completar la calificación de tipos de penalidades a aplicar en una liquidación
	 * @param pgimLiquidacionDTO
	 * @return
	 * @throws Exception
	 */
	public PgimLiquidacionDTO aplicarPenalidadReemplazoPersonal(PgimLiquidacionDTO pgimLiquidacionDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite modificar el monto de las penalidades por reemplazo de personal a nivel de la liquidación
	 * @param pgimLiquidacionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public PgimLiquidacionDTO modificarPenalidadReemplazoPer(PgimLiquidacionDTO pgimLiquidacionDTO, AuditoriaDTO auditoriaDTO) throws Exception;
}
