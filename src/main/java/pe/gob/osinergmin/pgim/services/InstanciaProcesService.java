package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProcesoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEqpInstanciaPro;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;

public interface InstanciaProcesService {

	PgimInstanciaProces obtenerInstanciaProceso(Long id);
	
	/**
	 * Permite obtener la instancia de proceso de la instancia de paso pasada como parámetro.
	 * @param idInstanciaPaso
	 * @return
	 */
	PgimInstanciaProces obtenerInstanciaProcesoPorInstanciaPaso(Long idInstanciaPaso);

	/**
	 * Permite asegurar que el registro de instancia tenga su identificador de
	 * instancia de proceso configurado.
	 * @param pgimInstanciaProcesCreada
	 * @param auditoriaDTO
	 */
	void actualizarInstProcTablaInstancia(PgimInstanciaProces pgimInstanciaProcesCreada, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite asegurar que el registro de instancia tenga su identificador de
	 * instancia de proceso configurado.
	 * @param pgimInstanciaProcesCreada
	 * @param auditoriaDTO
	 */
	PgimInstanciaProcesDTO obtenerInstanciaProceso(Long idProcesoActual, Long coTablaInstanciaActual);

	/**
	 * Permite asegurar la existencias de las instancias de proceso de acuerdo con
	 * el objeto pasado como argumento.
	 * 
	 * @param idProcesoActual              Identificador interno de la instancia de
	 *                                     proceso actual.
	 * @param coTablaInstanciaActual       Identificador interno de la instancia de
	 *                                     tabla de negocio.
	 * @param auditoriaDTO       		   Objeto de la auditoría del proceso.
	 */
	List<PgimInstanciaProcesDTO> asegurarInstanciasProceso(Long idProcesoActual, Long coTablaInstanciaActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener la instancia de proceso de acuerdo con el identifidador
	 * interno.
	 * 
	 * @param idInstanciaProceso Identificador interno del proceso.
	 * @return
	 */
	PgimInstanciaProces obtenerInstanciaProcesoPorId(Long idInstanciaProceso);
	
	/**
	 * Permite modificar un entity de instancia de proceso dado
	 * 
	 * @param pgimInstanciaProces
	 * @param auditoriaDTO
	 * @return
	 */
	PgimInstanciaProces modificarInstanciaProcesEntity(PgimInstanciaProces pgimInstanciaProces, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite persistir el valor del expediente en la instancia de proceso.
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 */
	void persistirExpedienteSiged(PgimInstanciaProcesDTO pgimInstanciaProcesDTO, AuditoriaDTO auditoriaDTO);

	/***
     * Permite listar las instancias procesos por numero de expediente por palabra clave.
     * 
     * @param palabra Palabra clave numero de expediente para la lista de instancia proceso.
     * @return
     */
    List<PgimInstanciaProcesDTO> listarPorPalabraClave(String palabra);
    
    /**
     * Permite listar los participantes de una instancia de proceso
     * @param idInstanciaProceso
     * @return
     */
    List<PgimEqpInstanciaProDTO> obtenerParticipantesInstanciaPro(Long idInstanciaProceso) throws Exception;

    /**
     * Permite listar los participantes de una instancia de proceso
     * @param idInstanciaProceso
     * @return
     */
    List<PgimEqpInstanciaProDTO> obtenerParticipantesInstanciaProXRol(Long idInstanciaProceso, Long idRolProceso);

    /**
	 * Permite listar los procesos.
	 * @return
	 */
	List<PgimProcesoDTO> listarProcesos();

	/**
	 * Permite listar los procesos que puedes ser usados en el modulo de indicadores
	 * @return
	 */
	List<PgimProcesoDTO> listarProcesosForIndicar();

	/**
	 * Permite listar las fases de un proceso dado.
	 * @param idProceso Identificador del proceso.
	 * @return
	 */
	List<PgimFaseProcesoDTO> obtenerFasesPorIdProceso(Long idProceso);
     List<PgimFaseProcesoDTO> obtenerFasesPorProceso();

	/**
	 * Permite listar los pasos de un proceso dado.
	 * @param idProceso Identificador del proceso.
	 * @return
	 */
	List<PgimPasoProcesoDTO> obtenerPasosPorIdProceso(Long idProceso);
     List<PgimPasoProcesoDTO> obtenerPasosPorFase();
	
	/**
     * Permite obtener la lista de personas del Osinergmin que coincidan con los criterios la palabra filtro,
     * y que no se dupliquen con un rol, en el equipo de la instancia de un proceso
     * @param palabra Palabra clave empleada para realizar la búsqueda por aproximación.
     * @param idInstanciaProceso
     * @param idRolProceso
     * @return
     */
	List<PgimPersonaosiAuxDTO> listarPersonalOsiSinDuplicadosXrol(String palabra,Long idInstanciaProceso,Long idRolProceso);
	
	
	/**
     * Permite seleccionar el equipo de una instancia de proceso
     * 
     * @param PgimEqpInstanciaProDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */	
	void seleccionarEquipoInstancia(PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO, AuditoriaDTO auditoriaDTO)
            throws Exception;
		
	/**
     * Permite deseleccionar un participante del equipo de una instancia de proceso
     * 
     * @param pgimEqpInstanciaProDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */	
    void deseleccionarEquipoInstancia(PgimEqpInstanciaPro pgimEqpInstanciaPro, AuditoriaDTO auditoriaDTO)
    		throws Exception;
    
    /***
     * Permite obtener un objeto entidad de Equipo de Instancia de Proceso con el valor idEquipoInstanciaPro.
     * @param idEquipoInstanciaPro
     * @return
     */
    PgimEqpInstanciaPro getByidEquipoInstanciaPro(Long idEquipoInstanciaPro);
    
    
    /**
     * Permite modificar un participante del equipo de una instancia de proceso
     * 
     * @param pgimEqpInstanciaProDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    void modificarEquipoInstancia(PgimEqpInstanciaPro pgimEqpInstanciaProActual,PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO, AuditoriaDTO auditoriaDTO)
    		throws Exception;
	
			
	List<PgimInstanciaProcesDTO> existeNuExpediente(Long idInstanciaProceso, String descNuExpedienteSiged);
	
	List<PgimEqpInstanciaProDTO> obtenerParticipantesRolSupervisor(Long idInstanciaProceso);	
	
    List<PgimEqpInstanciaProDTO> listarPersonalAsignableContraConRol(Long idInstanciaProceso, Long idRolProceso, String palabraClave);
    
    List<PgimEqpInstanciaProDTO> listarPersonalAsignableOsiConRol(Long idInstanciaProceso, Long idRolProceso, String palabraClave);    
    
    PgimInstanciaProcesDTO obtenerInstanciaProcesoPorNuExpediente(String nuExpedienteSiged);

	List<PgimInstanciaProcesDTO> listarInstanciaProcesPorProceso(Long idProceso);
	

}
