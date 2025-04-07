package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGenProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLineaProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPresupuestoDsEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimResumenLineaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSuperDsUmAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSuperProgramadaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupernpDsEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimVwPrgrmMontoEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimVwProgramPropuestoRnkDTO;
import pe.gob.osinergmin.pgim.dtos.SeguimientoProgramaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimItemProgramaSupe;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;

public interface PrgrmSupervisionService {

	List<PgimPrgrmSupervisionDTO> obtenerPrgrmSupervision();

	List<PgimPrgrmSupervisionDTO> obtenerProgramaParaAsignacion();

	/**
	 * Permite obtener la lista de programas
	 * 
	 * @param filtroPrograma
	 * @param paginador
	 * @return
	 */
	Page<PgimPrgrmSupervisionDTO> listarProgramas(PgimPrgrmSupervisionDTO filtroPrograma, Pageable paginador);

	/**
	 * Permite obtener la lista de programas (Aux)
	 * 
	 * @param filtroPrograma
	 * @param paginador
	 * @return
	 */
	Page<PgimPrgrmSupervisionAuxDTO> listarProgramasAux(PgimPrgrmSupervisionAuxDTO filtroPrograma, Pageable paginador,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el programa para mostrarlos en la ficha
	 * 
	 * @param idProgramaSupervision
	 * @return
	 */
	PgimPrgrmSupervisionDTO obtenerPrograma(Long idProgramaSupervision);

	/**
	 * Permite asignar un programa, usado en el dialogo Asignar Programa
	 * 
	 * @param pgimPrgrmSupervisionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimPrgrmSupervisionAuxDTO asignarPrograma(PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Lista los datos estándares de los programas
	 * 
	 * @param idLineaPrograma
	 * @return
	 */
	List<PgimEstandarProgramaDTO> listarEstandarPrograma(Long idLineaPrograma);

	/**
	 * Lista los datos de los montos establecidos para costos estándares de los
	 * programas
	 * 
	 * @param idLineaPrograma
	 * @param idSubtipoSupervision
	 * @return
	 */
	List<PgimEstandarProgramaDTO> listarCostosEstimados(Long idLineaPrograma, Long idSubtipoSupervision);

	/**
	 * Lista de supervisiones no programadas
	 * 
	 * @param idLineaPrograma
	 * @return
	 */
	List<PgimEstandarProgramaDTO> listarNoProgramada(Long idLineaPrograma);

	/**
	 * Permite listar los items de un programa dado. Vale decir que solo se retornan
	 * los ítems que aún no fueron utilizados para asignar una supervisión.
	 * 
	 * @param idProgramaSupervision
	 * @param paginador
	 * @return
	 */
	Page<PgimItemProgramaSupeDTO> listarItemsProgramaPendientes(Long idProgramaSupervision, Pageable paginador);

	Page<PgimItemProgramaSupeDTO> listarItemsProgramas(Long idLineaPrograma, Pageable paginador);

	List<PgimItemProgramaSupeDTO> listarItemsProgramas(Long idLineaPrograma);

	/**
	 * Permite obtener un objeto de la entidad PgimItemProgramaSupeDTO por
	 * idItemProgramaSupe
	 * 
	 * @param idItemProgramaSupe
	 * @return
	 */
	PgimItemProgramaSupeDTO obtenerItemProgramaSupeDtoById(Long idItemProgramaSupe);

	PgimItemProgramaSupe getItemProgramaSupeById(Long IdItemProgramaSupe);

	/**
	 * Permite cancelar un ítem de programa de supervisión
	 * 
	 * @param pgimItemProgramaSupeDTOaCancelar
	 * @param auditoriaDTO
	 * @return
	 */
	void cancelarItemProgramaSupe(PgimItemProgramaSupeDTO pgimItemProgramaSupeDTOaCancelar, AuditoriaDTO auditoriaDTO);// throws
																														// Exception

	/**
	 * Permite obtener la línea base actual del programa de supervisión.
	 * 
	 * @param idProgramaSupervision
	 * @return
	 */
	PgimLineaProgramaDTO obtenerLineaProgramaActual(Long idProgramaSupervision);

	/**
	 * Permite obtener el listaso de líneas base del programa de supervisión.
	 * 
	 * @param idProgramaSupervision
	 * @return
	 */
	List<PgimLineaProgramaDTO> listarLineaBase(Long idProgramaSupervision);

	/**
	 * Permite actualizar las Lineas Base
	 * 
	 */
	void actualizarLineaBase(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite validar la transición de un paso a otro.
	 * 
	 * @param pgimRelacionPaso  Objeto entidad de la relación de paso involucrada.
	 * @param pgimInstanciaPaso Objeto de instancia de paso involucrada.
	 */
	void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso);

	/**
	 * Permite obtener la entidad Item programa de supervisión
	 * 
	 * @param idItemPrograma
	 * @return
	 */
	PgimItemProgramaSupe getItemProgramaById(Long idItemPrograma);

	/**
	 * Permite procesar y grabar un item de programa de supervisión
	 * 
	 * @param pgimItemProgramaSupeDTO
	 * @param pgimItemProgramaSupeActual
	 * @param auditoriaDTO
	 * @return
	 */
	PgimItemProgramaSupeDTO procesarModificarItemPrograma(PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO,
			PgimItemProgramaSupe pgimItemProgramaSupeActual, AuditoriaDTO auditoriaDTO);

	PgimItemProgramaSupeDTO modificarItemProgramaSupe(PgimItemProgramaSupe pgimItemProgramaSupe,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite realizar las acciones necesarias en la transición de pasos.
	 * 
	 * @param pgimInstanciaProces
	 * @param pgimInstanciaPaso
	 * @param auditoriaDTO
	 */
	void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
			AuditoriaDTO auditoriaDTO);

	/**
	 * obtener los costos reales de un programa de supervisión
	 * 
	 * @param idLineaPrograma
	 * @return
	 */
	SeguimientoProgramaDTO obtenerCostosReales(Long idLineaPrograma);

	/**
	 * Permite obtener la lista resumen del programa de supervisión de acuerdo con
	 * la linea base del programa.
	 * 
	 * @param idLineaPrograma
	 * @return
	 */
	List<PgimResumenLineaAuxDTO> obtenerListaResumenPrograma(Long idLineaPrograma);

	/**
	 * Me permite lista los programas del plan de supervisión
	 * 
	 * @param idPlanSupervision
	 * @return
	 */
	List<PgimPrgrmSupervisionDTO> listarProgramasSupervPlan(Long idPlanSupervision);

	/**
	 * Permite obtener la entidad programa de supervisión
	 * 
	 * @param idProgramaSupervision
	 * @return
	 */
	PgimPrgrmSupervision getPrgrmSupervisionById(Long idProgramaSupervision);

	/**
	 * Me permite modificar un programa de supervisión
	 * 
	 * @param pgimPrgrmSupervision
	 * @param auditoriaDTO
	 * @return
	 */
	PgimPrgrmSupervisionDTO modificarProgramaSupervision(PgimPrgrmSupervision pgimPrgrmSupervision,
			AuditoriaDTO auditoriaDTO);
	
	
    /**
     * Permite obtener la lista preparada de supervisiones programadas por división supervisora-especialidad paginada
     * @param filtroPgimSuperProgramadaAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimSuperProgramadaAuxDTO> listarReporteSuperProgramadaPaginado(PgimSuperProgramadaAuxDTO filtroPgimSuperProgramadaAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista preparada de supervisiones no programadas por división supervisora-especialidad
     * @param filtroPgimSupernpDsEspAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimSupernpDsEspAuxDTO> listarReporteSuperNoProgramadaPaginado(PgimSupernpDsEspAuxDTO filtroPgimSupernpDsEspAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista preparada de supervisiones por división supervisora y unidad minera (programadas y no programadas)
     * @param filtroPgimSuperDsUmAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimSuperDsUmAuxDTO> listarReporteSuperDsUmPaginado(PgimSuperDsUmAuxDTO filtroPgimSuperDsUmAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista preparada de presupuesto consolidado por división supervisora y especialidad
     * @param filtroPgimPresupuestoDsEspAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimPresupuestoDsEspAuxDTO> listarReportePresupDsEspecPaginado(PgimPresupuestoDsEspAuxDTO filtroPgimPresupuestoDsEspAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista de montos por especialidad
     * @param idPlanSupervisionAux
     * @return
     */
    List<PgimVwPrgrmMontoEspAuxDTO> listarMontosEspecialidadPorPlan(Long idPlanSupervisionAux);
    
    /**
     * Permite obtener la cantidad de programaciones pendientes de atención por el usuario en sesión
     * 
     * @param auditoriaDTO
     * @return
     */
    Integer contarProgramasPendientes(AuditoriaDTO auditoriaDTO);

	/**
	 * Autocompletar programa
	 * @param descPrograma
	 * @return
	 */
	List<PgimPrgrmSupervisionDTO> obtenerProgramaAutocompletado(String descPrograma);

	/**
	 * Permite obtener los ranking de riesgos involucrados en le programa 
	 * a partir de su especilidad, división supervisora y año
	 * @param idProgramaSupervision
	 * @return
	 */
	List<PgimRankingRiesgoDTO> obtenerRankingAprobXDsEspecialidadAnio(Long idProgramaSupervision);

	/**
	 * Permite generar un programa propuesto a partir de los rankig de riesgo involucrados con el programa
	 * @param pgimGenProgramaDTO
	 * @param lPgimRankingRiesgoDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimGenProgramaDTO generarProgramaPropuesta(PgimGenProgramaDTO pgimGenProgramaDTO, List<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite obtener el programa propuesto que fue generado a partir de los ranking involucrados con el programa
	 * @param idProgramaSupervision
	 * @param paginador
	 * @return
	 */
	Page<PgimVwProgramPropuestoRnkDTO> obtenerProgramaPropuesta(Long idProgramaSupervision, Pageable paginador);

	/**
	 * Permite obtener el programa propuesto que fue generado a partir de los ranking involucrados con el programa
	 * sin embargo tuvieron algúna excepción
	 * @param idProgramaSupervision
	 * @return
	 */
	List<PgimVwProgramPropuestoRnkDTO> obtenerProgramaPropuestaNoConforme(Long idProgramaSupervision);

	/**
	 * Permite registrar como item de programa al programa propuesto
	 * @param pgimGenProgramaDTO
	 * @param auditoriaDTO
	 * @throws Exception
	 */
	void registrarProgramaPropuesta(PgimGenProgramaDTO pgimGenProgramaDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite obtener la cantidad de fiscalizaciones asignadas en un programa determinado
	 * @param idLineaPrograma
	 * @return
	 */
	Integer existeFiscalizacionAsignadaPrograma(Long idLineaPrograma);
	
	/**
	 * Permite obtener los rankings(nombres) que han intervenido en la generación del programa propuesto
	 * y esta ha sido registrado en el programa.
	 * @param idProgramaSupervision
	 * @return
	 */
	String obtenerRankingsUtilizados(Long idProgramaSupervision);

	/**
	 * Permite obtener el total de visitas de las unidades fiscalizadas para el programa propuesto
	 * @param idProgramaSupervision
	 * @return
	 */
	Long obtenerTotalVisitasProgramaPropuesta(Long idProgramaSupervision);

}
