package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgFactorRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgGrupoRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRegla;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimFactorRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;

public interface ConfiguraRiesgoService {

	/**
	 * Permite obtener la lista de las configuraciones de riesgo
	 * 
	 * @param filtroConfiguraRiesgo
	 * @param paginador
	 * @param auditoriaDTO
	 * @return
	 */
	Page<PgimConfiguraRiesgoDTO> filtrar(PgimConfiguraRiesgoDTO filtroConfiguraRiesgo, Pageable paginador,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener las configuraciones para la asignación
	 * 
	 * @return
	 */
	List<PgimConfiguraRiesgoDTO> obtenerConfiguracionParaAsignacion();

	/**
	 * Permite la asignacion de la configuración del riesgo
	 * 
	 * @param pgimConfiguraRiesgoDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimConfiguraRiesgoDTO asignarConfiguracionRiesgo(PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO,
			AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite obtener la información de configura riesgo por el id
	 * 
	 * @param idConfiguraRiesgo
	 * @return
	 */
	PgimConfiguraRiesgoDTO obtenerConfiguraRiesgoPorId(Long idConfiguraRiesgo);

	/**
	 * Permite obtener la información de configura riesgo por el id de supervisión 
	 * @param idSupervision
	 * @return
	 */
	PgimConfiguraRiesgoDTO obtenerConfiguraRiesgoPorIdSupervision(Long idSupervision);

	/**
	 * Permite generar la instancia de proceso para una configuracion de riesgo
	 * 
	 * @param pgimConfiguraRiesgoDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimConfiguraRiesgoDTO aseguraInstanciaProcesoConfiguraRiesgo(PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO,
			AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite obtener la lista de los factores tecnicos o de gestión
	 * 
	 * @param idCfgGrupoRiesgo
	 * @param idGrupoRiesgo
	 * @return
	 */
	List<PgimCfgFactorRiesgoDTO> listarCfgFactorTecnicoGestion(Long idCfgGrupoRiesgo, Long idGrupoRiesgo);

	/**
	 * Permite obtener la lista de la matriz de pares para la tabla cruzada
	 * 
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @return
	 */
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesTC(Long idConfiguraRiesgo, Long idGrupoRiesgo);

	/**
	 * Permite obtener la lista de la matriz normalizada para la tabla cruzada
	 * 
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @return
	 */
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizNormalizadaTC(Long idConfiguraRiesgo, Long idGrupoRiesgo);

	/**
	 * Permite obtener la entidad PgimConfiguraRiesgo por el id
	 * 
	 * @param idConfiguraRiesgo
	 * @return
	 */
	PgimConfiguraRiesgo findById(Long idConfiguraRiesgo);

	/**
	 * Permite modificar el detalle de la configuración de riesgo
	 * 
	 * @param pgimConfiguraRiesgoDTO
	 * @param pgimConfiguraRiesgo
	 * @param auditoriaDTO
	 * @return
	 */
	PgimConfiguraRiesgoDTO modificarConfiguraRiesgo(PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO,
			PgimConfiguraRiesgo pgimConfiguraRiesgo, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el detalle de cálculo de ratio
	 * 
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @return
	 */
	PgimCfgGrupoRiesgoDTO obtenerCalculoRatioConsistencia(Long idConfiguraRiesgo, Long idGrupoRiesgo);

	/**
	 * Permite obtener los niveles de riesgo usando el id_cfg_factor_riesgo
	 * 
	 * @param idCfgFactorRiesgo
	 * @return
	 */
	List<PgimCfgNivelRiesgoDTO> listarCfgNivelRiesgoPorIdCfgFactorRiesgo(Long idCfgFactorRiesgo);

	/**
	 * Permite obtener el detalle del factor de riesgo ténico o gestión
	 * 
	 * @param idCfgFactorRiesgo
	 * @param idGrupoRiesgo
	 * @return
	 */
	PgimCfgFactorRiesgoDTO obtenerCfgFactorTecnicoGestion(Long idCfgFactorRiesgo, Long idGrupoRiesgo);

	/**
	 * Permite crear el factor riesgo base
	 * 
	 * @param pgimFactorRiesgoDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimFactorRiesgoDTO crearFactorRiesgo(PgimFactorRiesgoDTO pgimFactorRiesgoDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el registro de factor riesgo por el ID
	 * 
	 * @param idFactorRiesgo
	 * @return
	 */
	PgimFactorRiesgoDTO obtenerFactorRiesgoPorId(Long idFactorRiesgo);

	/**
	 * Permite obtener la entidad PgimCfgFactorRiesgo por el ID
	 * 
	 * @param idCfgFactorRiesgo
	 * @return
	 */
	PgimCfgFactorRiesgo cfgFactorRiesgoFindById(Long idCfgFactorRiesgo);

	/**
	 * Permite eliminar la configuracion del factor riesgo técnico o gestión por ID
	 * 
	 * @param pgimCfgFactorRiesgoActual
	 * @param auditoriaDTO
	 */
	void eliminarCfgFactorRiesgo(PgimCfgFactorRiesgo pgimCfgFactorRiesgoActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite crear un registro de configuracion factor riesgo y crea su
	 * configuracion de niveles de riesgo
	 * 
	 * @param pgimCfgFactorRiesgoDTO
	 * @param lPgimCfgNivelRiesgo
	 * @param auditoriaDTO
	 * @return
	 */
	PgimCfgFactorRiesgoDTO crearCfgFactorRiesgo(PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO,
			List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgo, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite modificar una configuracion de factor de riesgo y sus configuraciones
	 * de niveles de riesgo
	 * 
	 * @param pgimCfgFactorRiesgoDTO
	 * @param lPgimCfgNivelRiesgo
	 * @param pgimCfgFactorRiesgo
	 * @param auditoriaDTO
	 * @return
	 */
	PgimCfgFactorRiesgoDTO modificarCfgFactorRiesgo(PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO,
			List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgo, PgimCfgFactorRiesgo pgimCfgFactorRiesgo,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el registro de configuracion de factor de riesgo por ID
	 * 
	 * @param idCfgFactorRiesgo
	 * @return
	 */
	PgimCfgFactorRiesgoDTO obtenerCfgFactorRiesgoPorId(Long idCfgFactorRiesgo);

	/**
	 * Permite obtener la lista de los factores de riesgo base, que no estan
	 * incluidos en las configuraciones de riesgo base
	 * 
	 * @param idCfgGrupoRiesgo
	 * @param idGrupoRiesgo
	 * @param idEspecialidad
	 * @return
	 */
	List<PgimFactorRiesgoDTO> listarFactorRiesgoNotInCfgFactorRiesgo(Long idCfgGrupoRiesgo, Long idGrupoRiesgo,
			Long idEspecialidad);

	/**
	 * Permite obtener la lista de los factores de riesgo base
	 * 
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @return
	 */
	List<PgimFactorRiesgoDTO> listarFactorRiesgo(Long idConfiguraRiesgo, Long idGrupoRiesgo);

	/**
	 * Permite modificar el numerador y denominador de la matriz de pares
	 * seleccioanda y su inverso
	 * 
	 * @param pgimCfgMatrizParesDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimCfgMatrizParesDTO modificarCfgMatrizParesNumeradorDenominador(PgimCfgMatrizParesDTO pgimCfgMatrizParesDTO,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite listar los registros de la tabla factor de riesgo
	 * 
	 * @param filtroFactorRiesgo
	 * @param paginador
	 * @param auditoriaDTO
	 * @return
	 */
	Page<PgimFactorRiesgoDTO> listarFactorRiesgoBase(PgimFactorRiesgoDTO filtroFactorRiesgo, Pageable paginador,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener el detalle del factor de riesgo por el ID
	 * 
	 * @param idFactorRiesgo
	 * @return
	 */
	PgimFactorRiesgoDTO obtenerFactorRiesgoBase(Long idFactorRiesgo);

	/**
	 * Permite obtener la entidad de la tabla factor riesgo por el ID
	 * 
	 * @param idFactorRiesgo
	 * @return
	 */
	PgimFactorRiesgo factorRiesgoFindById(Long idFactorRiesgo);

	/**
	 * Permite modificar la entidad factor riesgo
	 * 
	 * @param pgimFactorRiesgoDTO
	 * @param pgimFactorRiesgo
	 * @param auditoriaDTO
	 * @return
	 */
	PgimFactorRiesgoDTO modificarFactorRiesgoBase(PgimFactorRiesgoDTO pgimFactorRiesgoDTO,
			PgimFactorRiesgo pgimFactorRiesgo, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite eliminar el factor riesgo por el ID
	 * 
	 * @param pgimFactorRiesgoActual
	 * @param auditoriaDTO
	 */
	void eliminarFactorRiesgoBase(PgimFactorRiesgo pgimFactorRiesgoActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite validar la transición de paso de la configuración
	 * 
	 * @param pgimRelacionPaso
	 * @param pgimInstanciaPaso
	 */
	void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso);

	/**
	 * Permite realizar las acciones pertinentes de acuerdo al paso
	 * 
	 * @param pgimInstanciaProces
	 * @param pgimInstanciaPaso
	 * @param auditoriaDTO
	 */
	void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces,
			PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO);

	/**
	 * Se utiliza para visualizar la lista de reglas de riesgo por configuración de
	 * riesgo
	 * 
	 * @param idConfiguraRiesgo
	 * @return
	 */
	List<PgimConfiguraReglaDTO> listarReglasRiesgoPorConfiguracion(Long idConfiguraRiesgo);

	// PGIM-5007 - pjimenez
	/**
	 * Permite obtener la lista de los grupos de riesgo
	 * 
	 * @param idConfiguraRiesgo
	 * @param idGrupoRiesgo
	 * @return
	 */
	List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgo(Long idConfiguraRiesgo, Long idGrupoRiesgo);

	// PGIM-5008 - pjimenez
	/**
	 * Permite crear un registro de configuracion grupo riesgo
	 * 
	 * @param pgimCfgGrupoRiesgoDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimCfgGrupoRiesgoDTO crearCfgGrupoRiesgo(PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO, AuditoriaDTO auditoriaDTO);

	// PGIM-5008 - pjimenez
	/**
	 * Permite obtener el registro de configuracion de grupo de riesgo por ID
	 * 
	 * @param idCfgGrupoRiesgo
	 * @return
	 */
	PgimCfgGrupoRiesgoDTO obtenerCfgGrupoRiesgoPorId(Long idCfgGrupoRiesgo);

	// PGIM-5009 - pjimenez
	/**
	 * Permite obtener la lista de los factores tecnicos o de gestión
	 * 
	 * @param idCfgGrupoRiesgo
	 * @return
	 */
	List<PgimCfgFactorRiesgoDTO> listarCfgFactorTecnicoPorCfgGrupo(Long idCfgGrupoRiesgo);

	// PGIM-5009 - pjimenez
	/**
	 * Permite obtener el detalle de cálculo de ratio
	 * 
	 * @param idCfgGrupoRiesgo
	 * @return
	 */
	PgimCfgGrupoRiesgoDTO obtenerCalculoRatioConsistenciaPorCfgGrupo(Long idCfgGrupoRiesgo);

	// PGIM-5009 - pjimenez
	/**
	 * Permite obtener la entidad PgimCfgGrupoRiesgo por el ID
	 * 
	 * @param idCfgGrupoRiesgo
	 * @return
	 */
	PgimCfgGrupoRiesgo cfgGrupoRiesgoFindById(Long idCfgGrupoRiesgo);

	// PGIM-5009 - pjimenez
	/**
	 * Permite obtener la lista de la matriz de pares para la tabla cruzada
	 * 
	 * @param idCfgGrupoRiesgo
	 * @return
	 */
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesTCPorCfgGrupo(Long idCfgGrupoRiesgo);

	// PGIM-5009 - pjimenez
	/**
	 * Permite obtener la lista de la matriz normalizada para la tabla cruzada
	 * 
	 * @param idCfgGrupoRiesgo
	 * @return
	 */
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizNormalizadaTCPorCfgGrupo(Long idCfgGrupoRiesgo);

	// PGIM-5009 - pjimenez
	/**
	 * Permite modificar los datos generales de una configuracion de grupo de riesgo
	 * 
	 * @param pgimCfgGrupoRiesgo
	 * @param auditoriaDTO
	 * @return
	 */
	PgimCfgGrupoRiesgoDTO modificarCfgGrupoRiesgo(PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo, AuditoriaDTO auditoriaDTO);

	PgimConfiguraReglaDTO crearReglaRiesgo(PgimConfiguraReglaDTO pgimConfiguraReglaDTO, AuditoriaDTO auditoriaDTO);

	PgimConfiguraReglaDTO modificarReglaRiesgo(PgimConfiguraReglaDTO pgimConfiguraReglaDTO,
			PgimConfiguraRegla pgimConfiguraRegla, AuditoriaDTO auditoriaDTO);

	void eliminarReglaRiesgo(PgimConfiguraRegla pgimConfiguraRegla, AuditoriaDTO auditoriaDTO);

	PgimConfiguraRegla configuraReglafindById(Long idreglaRiesgo);

	/**
	 * Permite contar la cantidad de reglas de riesgo que existen la configuración
	 * de riesgo y la terna de propiedades de las reglas.
	 * @param idConfiguraRiesgo
	 * @param idConfiguraRegla
	 * @param idDivisionSupervisora
	 * @param idMetodoMinado
	 * @param idSituacion
	 * @param idTipoActividad
	 * @param idEstado
	 * @return
	 */
	Integer contarCantidadReglasRiesgo(Long idConfiguraRiesgo, Long idConfiguraRegla, Long idDivisionSupervisora,
			Long idMetodoMinado, Long idSituacion, Long idTipoActividad, Long idEstado);

	// PGIM-5010 - pjimenez
	/**
	 * Permite eliminar la configuracion del grupo riesgo técnico o gestión por ID
	 * 
	 * @param pgimCfgGrupoRiesgoActual
	 * @param auditoriaDTO
	 */
	void eliminarCfgGrupoRiesgo(PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoActual, AuditoriaDTO auditoriaDTO);
		
	/**
	 * Permite obtener la cantidad de configuraciones de riesgo pendientes de atención por el usuario en sesión
	 * 
	 * @param auditoriaDTO
	 * @return
	 */
	Integer contarConfiguraRiesgoPendientes(AuditoriaDTO auditoriaDTO);
}
