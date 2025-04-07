package pe.gob.osinergmin.pgim.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgFactorRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgGrupoRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgMatrizPares;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgNivelRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRegla;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimEspecialidad;
import pe.gob.osinergmin.pgim.models.entity.PgimFactorRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimGrupoRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimNivelRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.CfgFactorRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.CfgGrupoRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.CfgMatrizParesRepository;
import pe.gob.osinergmin.pgim.models.repository.CfgNivelRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.ConfiguraReglaRepository;
import pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.EspecialidadRepository;
import pe.gob.osinergmin.pgim.models.repository.FactorRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.ConfiguraRiesgoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstRelacionPasoMetodologia;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Configuracion de riesgo
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class ConfiguraRiesgoServiceImpl implements ConfiguraRiesgoService {

	@Autowired
	private ConfiguraRiesgoRepository configuraRiesgoRepository;

	@Autowired
	private ConfiguraReglaRepository configuraReglaRepository;

	@Autowired
	private CfgGrupoRiesgoRepository cfgGrupoRiesgoRepository;

	@Autowired
	private CfgFactorRiesgoRepository cfgFactorRiesgoRepository;

	@Autowired
	private CfgNivelRiesgoRepository cfgNivelRiesgoRepository;

	@Autowired
	private InstanciaProcesService instanciaProcesService;

	@Autowired
	private InstanciaProcesRepository instanciaProcesRepository;

	@Autowired
	private FlujoTrabajoService flujoTrabajoService;

	@Autowired
	private CfgMatrizParesRepository cfgMatrizParesRepository;

	@Autowired
	private FactorRiesgoRepository factorRiesgoRepository;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private EspecialidadRepository especialidadRepository;

	@Autowired
	private RankingSupervisionRepository rankingSupervisionRepository;

	@Autowired
	private RankingRiesgoRepository rankingRiesgoRepository;
	
	@Autowired
	private ValorParametroRepository valorParametroRepository;

	@Override
	public Page<PgimConfiguraRiesgoDTO> filtrar(PgimConfiguraRiesgoDTO filtroConfiguraRiesgo, Pageable paginador,
			AuditoriaDTO auditoriaDTO) {
		
		//Obtenemos permiso "listar todas"
    	boolean flagPermisoTodas = false;
    	for (String permiso : auditoriaDTO.getAuthorities()) {	    		 
    		
    		if(permiso.equals(ConstantesUtil.PERMISO_VER_CONFIG_RIESGOS_TODAS)) {
    			flagPermisoTodas = true;
    		}
    	}

		if (filtroConfiguraRiesgo.getDescFlagMisAsignaciones().equals("1")) {
			filtroConfiguraRiesgo.setDescUsuarioAsignado(auditoriaDTO.getUsername());
		}

		// Para usuarios con tareas enviadas // STORY: PGIM-5455 Consulta de objeto de trabajo enviado
		if (filtroConfiguraRiesgo.getDescFlagMisAsignaciones().equals("2")) {
			filtroConfiguraRiesgo.setNoUsuarioOrigen(auditoriaDTO.getUsername());
		}

		if (filtroConfiguraRiesgo.getTextoBusqueda().equals("")) {
			filtroConfiguraRiesgo.setTextoBusqueda(null);
		}
		
		//Si el usuario no tiene permiso para ver los registros de otros usuarios 
        //se le setea el mismo usaurio para los filtros 
        if(
        	( filtroConfiguraRiesgo.getDescFlagMisAsignaciones()==null || !filtroConfiguraRiesgo.getDescFlagMisAsignaciones().trim().equals("1") )                	
        	&&  !flagPermisoTodas  
        		) {                	
        	filtroConfiguraRiesgo.setDescUsuarioAsignado(auditoriaDTO.getUsername());
        	filtroConfiguraRiesgo.setDescNoPersonaAsignada("");
        	filtroConfiguraRiesgo.setDescFlagMisAsignaciones("1");
        }

		Page<PgimConfiguraRiesgoDTO> pPgimConfiguraRiesgoDTO = this.configuraRiesgoRepository.listarConfiguraRiesgo(
				filtroConfiguraRiesgo.getDescFeConfiguracionAnio(), 
				filtroConfiguraRiesgo.getIdEspecialidad(),
				filtroConfiguraRiesgo.getIdTipoEstadoConfiguracion(), 
				filtroConfiguraRiesgo.getDescIdFaseActual(),
				filtroConfiguraRiesgo.getDescIdPasoActual(), 
				filtroConfiguraRiesgo.getDescFlagMisAsignaciones(),
				filtroConfiguraRiesgo.getNoUsuarioOrigen(),
				filtroConfiguraRiesgo.getDescUsuarioAsignado(), 
				filtroConfiguraRiesgo.getDescNoPersonaAsignada(),
				filtroConfiguraRiesgo.getIdTipoConfiguracionRiesgo(), 
				filtroConfiguraRiesgo.getIdTipoPeriodo(), 
				filtroConfiguraRiesgo.getNuAnioPeriodo(),
				filtroConfiguraRiesgo.getTextoBusqueda(), 
				paginador);

		return pPgimConfiguraRiesgoDTO;
	}

	@Override
	public List<PgimConfiguraRiesgoDTO> obtenerConfiguracionParaAsignacion() {
		List<PgimConfiguraRiesgoDTO> lPgimConfiguraRiesgoDTO = this.configuraRiesgoRepository
				.obtenerConfiguracionesParaAsignacionPadres(EValorParametro.ESCFG_APRBDA.toString());
		return lPgimConfiguraRiesgoDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public PgimConfiguraRiesgoDTO asignarConfiguracionRiesgo(PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {

		PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTOCreado = this.crearConfiguraRiesgo(pgimConfiguraRiesgoDTO,
				auditoriaDTO);

		// Se asegura la instancia del proceso
		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService.asegurarInstanciasProceso(
				ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO, pgimConfiguraRiesgoDTOCreado.getIdConfiguraRiesgo(),
				auditoriaDTO);

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

		PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository
				.findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

		this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);

		// Se crea la asignación
		pgimConfiguraRiesgoDTO.setIdConfiguraRiesgo(pgimConfiguraRiesgoDTOCreado.getIdConfiguraRiesgo());

		PgimInstanciaPasoDTO pgimInstanciaPasoDTO = new PgimInstanciaPasoDTO();
		pgimInstanciaPasoDTO.setIdRelacionPaso(pgimConfiguraRiesgoDTO.getDescIdRelacionPaso());
		pgimInstanciaPasoDTO.setDescIdPersonalOsiDestino(pgimConfiguraRiesgoDTO.getDescIdPersonalOsi());
		pgimInstanciaPasoDTO.setDeMensaje(pgimConfiguraRiesgoDTO.getDescDeMensaje());

		PgimInstanciaPasoDTO pgimInstanciaPasoDTOObtenido = this.flujoTrabajoService.crearInstanciaPasoInicial(pgimInstanciaProcesActual, pgimInstanciaPasoDTO,
				auditoriaDTO);

		// obtener el id de la instancia paso
		pgimConfiguraRiesgoDTOCreado.setDescIdInstanciaPaso(pgimInstanciaPasoDTOObtenido.getIdInstanciaPaso());

		return pgimConfiguraRiesgoDTOCreado;
	}

	private PgimConfiguraRiesgoDTO crearConfiguraRiesgo(PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO,
			AuditoriaDTO auditoriaDTO) {

		// Obtenemos el padre de la configuracion a crear
		PgimConfiguraRiesgo pgimConfiguraRiesgoPadre = null;
		PgimEspecialidad pgimEspecialidad = null;

		// Creando el registro configura riesgo
		PgimConfiguraRiesgo pgimConfiguraRiesgo = new PgimConfiguraRiesgo();

		if (pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo() != null) {
			pgimConfiguraRiesgoPadre = this.configuraRiesgoRepository
					.findById(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo()).orElse(null);
			pgimConfiguraRiesgo.setConfiguraRiesgoPadre(pgimConfiguraRiesgoPadre);
			pgimConfiguraRiesgo.setPgimEspecialidad(pgimConfiguraRiesgoPadre.getPgimEspecialidad());
		} else {
			pgimConfiguraRiesgo.setConfiguraRiesgoPadre(null);
			pgimEspecialidad = this.especialidadRepository.findById(pgimConfiguraRiesgoDTO.getIdEspecialidad())
					.orElse(null);
			pgimConfiguraRiesgo.setPgimEspecialidad(pgimEspecialidad);
		}

		PgimValorParametro tipoEstadoConfiguracion = new PgimValorParametro();
		tipoEstadoConfiguracion.setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ESCFG_CREADA.toString()));
		pgimConfiguraRiesgo.setTipoEstadoConfiguracion(tipoEstadoConfiguracion);

		pgimConfiguraRiesgo.setNoConfiguracion(pgimConfiguraRiesgoDTO.getNoConfiguracion());

		pgimConfiguraRiesgo.setDeConfiguracion(pgimConfiguraRiesgoDTO.getDeConfiguracion());
		pgimConfiguraRiesgo.setFeConfiguracion(pgimConfiguraRiesgoDTO.getFeConfiguracion());

		PgimValorParametro tipoPeriodo = new PgimValorParametro();
		tipoPeriodo.setIdValorParametro(pgimConfiguraRiesgoDTO.getIdTipoPeriodo());
		pgimConfiguraRiesgo.setTipoPeriodo(tipoPeriodo);

		PgimValorParametro tipoConfiguracionRiesgo = new PgimValorParametro();
		tipoConfiguracionRiesgo.setIdValorParametro(pgimConfiguraRiesgoDTO.getIdTipoConfiguracionRiesgo());
		pgimConfiguraRiesgo.setTipoConfiguracionRiesgo(tipoConfiguracionRiesgo);

		pgimConfiguraRiesgo.setNuAnioPeriodo(pgimConfiguraRiesgoDTO.getNuAnioPeriodo());

		pgimConfiguraRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimConfiguraRiesgo.setFeCreacion(new Date());
		pgimConfiguraRiesgo.setUsCreacion(auditoriaDTO.getUsername());
		pgimConfiguraRiesgo.setIpCreacion(auditoriaDTO.getTerminal());

		PgimConfiguraRiesgo pgimConfiguraRiesgoCreado = configuraRiesgoRepository.save(pgimConfiguraRiesgo);

		List<PgimConfiguraReglaDTO> lPgimConfiguraReglaDTO = this.configuraReglaRepository
				.obtenerReglasPorConfiguracion(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());

		// Insertar reglas por configuración
		for (PgimConfiguraReglaDTO pgimConfiguraReglaDTO : lPgimConfiguraReglaDTO) {
			PgimConfiguraRegla pgimConfiguraReglaPadre = configuraReglaRepository
					.findById(pgimConfiguraReglaDTO.getIdConfiguraRegla()).orElse(null);

			PgimConfiguraRegla pgimConfiguraRegla = new PgimConfiguraRegla();
			pgimConfiguraRegla.setPgimConfiguraRiesgo(pgimConfiguraRiesgoCreado);

			pgimConfiguraRegla.setDivisionSupervisora(pgimConfiguraReglaPadre.getDivisionSupervisora());
			pgimConfiguraRegla.setMetodoMinado(pgimConfiguraReglaPadre.getMetodoMinado());
			pgimConfiguraRegla.setSituacion(pgimConfiguraReglaPadre.getSituacion());
			
			// Campos mandatorios
			pgimConfiguraRegla.setTipoActividad(pgimConfiguraReglaPadre.getTipoActividad());
			pgimConfiguraRegla.setEstado(pgimConfiguraReglaPadre.getEstado());

			pgimConfiguraRegla.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimConfiguraRegla.setFeCreacion(new Date());
			pgimConfiguraRegla.setUsCreacion(auditoriaDTO.getUsername());
			pgimConfiguraRegla.setIpCreacion(auditoriaDTO.getTerminal());

			try {
				this.configuraReglaRepository.save(pgimConfiguraRegla);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw e;
			}
		}

		List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTOTemp = new LinkedList<PgimCfgGrupoRiesgoDTO>();
		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTOTemp = new LinkedList<PgimCfgFactorRiesgoDTO>();

		List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTO = this.cfgGrupoRiesgoRepository
				.listarCfgGrupoRiesgoPorConfiguracion(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());

		// Insertamos registros de grupo riesgo
		for (PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO : lPgimCfgGrupoRiesgoDTO) {
			PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoPadre = cfgGrupoRiesgoRepository
					.findById(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo()).orElse(null);

			PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo = new PgimCfgGrupoRiesgo();

			pgimCfgGrupoRiesgo.setPgimConfiguraRiesgo(pgimConfiguraRiesgoCreado);
			pgimCfgGrupoRiesgo.setPgimGrupoRiesgo(pgimCfgGrupoRiesgoPadre.getPgimGrupoRiesgo());
			pgimCfgGrupoRiesgo.setNoGrupoRiesgo(pgimCfgGrupoRiesgoPadre.getNoGrupoRiesgo());
			pgimCfgGrupoRiesgo.setDeGrupoRiesgo(pgimCfgGrupoRiesgoPadre.getDeGrupoRiesgo());
			pgimCfgGrupoRiesgo.setPcFactorCorreccion(pgimCfgGrupoRiesgoPadre.getPcFactorCorreccion());
			pgimCfgGrupoRiesgo.setNuCalificacionGrupo(pgimCfgGrupoRiesgoPadre.getNuCalificacionGrupo());
			pgimCfgGrupoRiesgo.setNuIndiceConsistencia(pgimCfgGrupoRiesgoPadre.getNuIndiceConsistencia());
			pgimCfgGrupoRiesgo.setNuRatioConsistencia(pgimCfgGrupoRiesgoPadre.getNuRatioConsistencia());
			pgimCfgGrupoRiesgo.setNuIndConsistenciaAleatoria(pgimCfgGrupoRiesgoPadre.getNuIndConsistenciaAleatoria());
			pgimCfgGrupoRiesgo.setNuNmax(pgimCfgGrupoRiesgoPadre.getNuNmax());

			pgimCfgGrupoRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimCfgGrupoRiesgo.setFeCreacion(new Date());
			pgimCfgGrupoRiesgo.setUsCreacion(auditoriaDTO.getUsername());
			pgimCfgGrupoRiesgo.setIpCreacion(auditoriaDTO.getTerminal());

			PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoCreado = cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgo);

			PgimCfgGrupoRiesgoDTO cfgGrupoRiesgoDTOTemp = new PgimCfgGrupoRiesgoDTO();
			cfgGrupoRiesgoDTOTemp.setIdCfgGrupoRiesgo(pgimCfgGrupoRiesgoCreado.getIdCfgGrupoRiesgo());
			cfgGrupoRiesgoDTOTemp.setDescIdCfgGrupoRiesgoTemp(pgimCfgGrupoRiesgoPadre.getIdCfgGrupoRiesgo());
			lPgimCfgGrupoRiesgoDTOTemp.add(cfgGrupoRiesgoDTOTemp);

			List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTO = cfgFactorRiesgoRepository
					.listarCfgFactorRiesgoPorConfiguracion(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());

			// Insertamos registros de factor riesgo
			for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTO) {
				PgimCfgFactorRiesgo pgimCfgFactorRiesgoPadre = cfgFactorRiesgoRepository
						.findById(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo()).orElse(null);

				PgimCfgFactorRiesgo pgimCfgFactorRiesgo = new PgimCfgFactorRiesgo();
				pgimCfgFactorRiesgo.setPgimCfgGrupoRiesgo(pgimCfgGrupoRiesgoCreado);
				pgimCfgFactorRiesgo.setPgimFactorRiesgo(pgimCfgFactorRiesgoPadre.getPgimFactorRiesgo());
				pgimCfgFactorRiesgo.setNuOrdenPrioridad(pgimCfgFactorRiesgoPadre.getNuOrdenPrioridad());
				pgimCfgFactorRiesgo.setFlAfectadoGestion(pgimCfgFactorRiesgoPadre.getFlAfectadoGestion());
				pgimCfgFactorRiesgo.setNuSumatoriaColumnaMcp(pgimCfgFactorRiesgoPadre.getNuSumatoriaColumnaMcp());
				pgimCfgFactorRiesgo.setNuVectorResultante(pgimCfgFactorRiesgoPadre.getNuVectorResultante());

				pgimCfgFactorRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimCfgFactorRiesgo.setFeCreacion(new Date());
				pgimCfgFactorRiesgo.setUsCreacion(auditoriaDTO.getUsername());
				pgimCfgFactorRiesgo.setIpCreacion(auditoriaDTO.getTerminal());

				PgimCfgFactorRiesgo pgimCfgFactorRiesgoCreado = cfgFactorRiesgoRepository.save(pgimCfgFactorRiesgo);

				PgimCfgFactorRiesgoDTO cfgFactorRiesgoDTOTemp = new PgimCfgFactorRiesgoDTO();
				cfgFactorRiesgoDTOTemp.setIdCfgFactorRiesgo(pgimCfgFactorRiesgoCreado.getIdCfgFactorRiesgo());
				cfgFactorRiesgoDTOTemp.setDescIdCfgFactorRiesgoTemp(pgimCfgFactorRiesgoPadre.getIdCfgFactorRiesgo());
				cfgFactorRiesgoDTOTemp.setIdCfgGrupoRiesgo(pgimCfgGrupoRiesgoCreado.getIdCfgGrupoRiesgo());
				lPgimCfgFactorRiesgoDTOTemp.add(cfgFactorRiesgoDTOTemp);

				List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgoDTO = cfgNivelRiesgoRepository
						.listarCfgNivelRiesgoPorFactorRiesgo(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());
				// insertando registros nivel riesgo
				for (PgimCfgNivelRiesgoDTO pgimCfgNivelRiesgoDTO : lPgimCfgNivelRiesgoDTO) {
					PgimCfgNivelRiesgo pgimCfgNivelRiesgoPadre = cfgNivelRiesgoRepository
							.findById(pgimCfgNivelRiesgoDTO.getIdCfgNivelRiesgo()).orElse(null);

					PgimCfgNivelRiesgo pgimCfgNivelRiesgo = new PgimCfgNivelRiesgo();
					pgimCfgNivelRiesgo.setPgimCfgFactorRiesgo(pgimCfgFactorRiesgoCreado);
					pgimCfgNivelRiesgo.setPgimNivelRiesgo(pgimCfgNivelRiesgoPadre.getPgimNivelRiesgo());
					pgimCfgNivelRiesgo.setDeEspecificacion(pgimCfgNivelRiesgoPadre.getDeEspecificacion());

					pgimCfgNivelRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimCfgNivelRiesgo.setFeCreacion(new Date());
					pgimCfgNivelRiesgo.setUsCreacion(auditoriaDTO.getUsername());
					pgimCfgNivelRiesgo.setIpCreacion(auditoriaDTO.getTerminal());

					this.cfgNivelRiesgoRepository.save(pgimCfgNivelRiesgo);
				}
			}
		}

		for (PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO : lPgimCfgGrupoRiesgoDTOTemp) {

			List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTO = cfgMatrizParesRepository
					.obtenerCfgMatrizParesPorIdCfgGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getDescIdCfgGrupoRiesgoTemp());
			for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTO : lPgimCfgMatrizParesDTO) {
				PgimCfgMatrizPares pgimCfgMatrizPares = new PgimCfgMatrizPares();

				// Seleccionando el id factor riesgo fila
				for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTOTemp) {
					if (pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo().intValue() == pgimCfgGrupoRiesgoDTO
							.getIdCfgGrupoRiesgo().intValue()) {

						if (pgimCfgMatrizParesDTO.getIdCfgFactorRiesgoFila().intValue() == pgimCfgFactorRiesgoDTO
								.getDescIdCfgFactorRiesgoTemp().intValue()) {
							PgimCfgFactorRiesgo pgimCfgFactorRiesgoFila = new PgimCfgFactorRiesgo();
							pgimCfgFactorRiesgoFila.setIdCfgFactorRiesgo(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());
							pgimCfgMatrizPares.setCfgFactorRiesgoFila(pgimCfgFactorRiesgoFila);
							break;
						}
					}
				}

				// Seleccionando el id factor riesgo columna
				for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTOTemp) {
					if (pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo().intValue() == pgimCfgGrupoRiesgoDTO
							.getIdCfgGrupoRiesgo().intValue()) {

						if (pgimCfgMatrizParesDTO.getIdCfgFactorRiesgoColu().intValue() == pgimCfgFactorRiesgoDTO
								.getDescIdCfgFactorRiesgoTemp().intValue()) {
							PgimCfgFactorRiesgo pgimCfgFactorRiesgoColu = new PgimCfgFactorRiesgo();
							pgimCfgFactorRiesgoColu.setIdCfgFactorRiesgo(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());
							pgimCfgMatrizPares.setCfgFactorRiesgoColu(pgimCfgFactorRiesgoColu);
							break;
						}
					}
				}

				pgimCfgMatrizPares.setFlEsEditable(pgimCfgMatrizParesDTO.getFlEsEditable());
				pgimCfgMatrizPares.setNuNumerador(pgimCfgMatrizParesDTO.getNuNumerador());
				pgimCfgMatrizPares.setNuDenominador(pgimCfgMatrizParesDTO.getNuDenominador());
				pgimCfgMatrizPares.setNuMcp(pgimCfgMatrizParesDTO.getNuMcp());
				pgimCfgMatrizPares.setNuMcpn(pgimCfgMatrizParesDTO.getNuMcpn());

				pgimCfgMatrizPares.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimCfgMatrizPares.setFeCreacion(new Date());
				pgimCfgMatrizPares.setUsCreacion(auditoriaDTO.getUsername());
				pgimCfgMatrizPares.setIpCreacion(auditoriaDTO.getTerminal());

				this.cfgMatrizParesRepository.save(pgimCfgMatrizPares);
			}

		}

		PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTOCreado = this
				.obtenerConfiguraRiesgoPorId(pgimConfiguraRiesgoCreado.getIdConfiguraRiesgo());

		return pgimConfiguraRiesgoDTOCreado;
	}

	@Override
	public PgimConfiguraRiesgoDTO obtenerConfiguraRiesgoPorId(Long idConfiguraRiesgo) {
		return this.configuraRiesgoRepository.obtenerConfiguraRiesgoPorId(idConfiguraRiesgo);
	}

	@Override
	public PgimConfiguraRiesgoDTO obtenerConfiguraRiesgoPorIdSupervision(Long idSupervision) {
		return this.configuraRiesgoRepository.obtenerConfiguraRiesgoPorIdSupervision(idSupervision);
	}

	@Override
	@Transactional(readOnly = false)
	public PgimConfiguraRiesgoDTO aseguraInstanciaProcesoConfiguraRiesgo(PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {

		// Se asegura la instancia del proceso
		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService.asegurarInstanciasProceso(
				ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO, pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo(),
				auditoriaDTO);

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

		PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository
				.findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

		this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);

		// Se crea la asignación
		pgimConfiguraRiesgoDTO.setIdConfiguraRiesgo(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());

		PgimInstanciaPasoDTO pgimInstanciaPasoDTO = new PgimInstanciaPasoDTO();
		// Asignamos el último paso de la configuración de riesgo
		pgimInstanciaPasoDTO.setIdRelacionPaso(ConstRelacionPasoMetodologia.APROBARMETODOLOGIA_METODOLOGIAAPROBADA);
		pgimInstanciaPasoDTO.setDescIdPersonalOsiDestino(pgimConfiguraRiesgoDTO.getDescIdPersonalOsi());
		pgimInstanciaPasoDTO.setDeMensaje(pgimConfiguraRiesgoDTO.getDescDeMensaje());

		this.flujoTrabajoService.crearInstanciaPasoFinalConfiguraRiesgo(pgimInstanciaProcesActual, pgimInstanciaPasoDTO,
				auditoriaDTO);

		pgimConfiguraRiesgoDTO.setIdInstanciaProceso(pgimInstanciaProcesActual.getIdInstanciaProceso());

		return pgimConfiguraRiesgoDTO;
	}

	@Override
	public List<PgimCfgFactorRiesgoDTO> listarCfgFactorTecnicoGestion(Long idCfgGrupoRiesgo, Long idGrupoRiesgo) {
		return this.cfgFactorRiesgoRepository.listarCfgFactorTecnicoGestion(idCfgGrupoRiesgo, idGrupoRiesgo);
	}

	@Override
	public List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesTC(Long idConfiguraRiesgo, Long idGrupoRiesgo) {
		return this.cfgMatrizParesRepository.obtenerCfgMatrizParesTC(idConfiguraRiesgo, idGrupoRiesgo);
	}

	@Override
	public List<PgimCfgMatrizParesDTO> obtenerCfgMatrizNormalizadaTC(Long idConfiguraRiesgo, Long idGrupoRiesgo) {
		return this.cfgMatrizParesRepository.obtenerCfgMatrizNormalizadaTC(idConfiguraRiesgo, idGrupoRiesgo);
	}

	@Override
	public PgimConfiguraRiesgo findById(Long idConfiguraRiesgo) {
		return this.configuraRiesgoRepository.findById(idConfiguraRiesgo).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimConfiguraRiesgoDTO modificarConfiguraRiesgo(PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO,
			PgimConfiguraRiesgo pgimConfiguraRiesgo, AuditoriaDTO auditoriaDTO) {

		pgimConfiguraRiesgo.setNoConfiguracion(pgimConfiguraRiesgoDTO.getNoConfiguracion());
		pgimConfiguraRiesgo.setDeConfiguracion(pgimConfiguraRiesgoDTO.getDeConfiguracion());
		pgimConfiguraRiesgo.setFeConfiguracion(pgimConfiguraRiesgoDTO.getFeConfiguracion());
		pgimConfiguraRiesgo.setTipoConfiguracionRiesgo(new PgimValorParametro());
		pgimConfiguraRiesgo.getTipoConfiguracionRiesgo().setIdValorParametro(pgimConfiguraRiesgoDTO.getIdTipoConfiguracionRiesgo());

		PgimValorParametro tipoPeriodo = new PgimValorParametro();
		tipoPeriodo.setIdValorParametro(pgimConfiguraRiesgoDTO.getIdTipoPeriodo());
		pgimConfiguraRiesgo.setTipoPeriodo(tipoPeriodo);

		PgimValorParametro tipoConfiguracionRiesgo = new PgimValorParametro();
		tipoConfiguracionRiesgo.setIdValorParametro(pgimConfiguraRiesgoDTO.getIdTipoConfiguracionRiesgo());
		pgimConfiguraRiesgo.setTipoConfiguracionRiesgo(tipoConfiguracionRiesgo);

		pgimConfiguraRiesgo.setNuAnioPeriodo(pgimConfiguraRiesgoDTO.getNuAnioPeriodo());
		
		pgimConfiguraRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimConfiguraRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimConfiguraRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimConfiguraRiesgo pgimConfiguraRiesgoModificado = configuraRiesgoRepository.save(pgimConfiguraRiesgo);

		PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTOModificado = this
				.obtenerConfiguraRiesgoPorId(pgimConfiguraRiesgoModificado.getIdConfiguraRiesgo());

		return pgimConfiguraRiesgoDTOModificado;
	}

	@Override
	public PgimCfgGrupoRiesgoDTO obtenerCalculoRatioConsistencia(Long idConfiguraRiesgo, Long idGrupoRiesgo) {
		return this.cfgGrupoRiesgoRepository.obtenerCalculoRatioConsistencia(idConfiguraRiesgo, idGrupoRiesgo);
	}

	@Override
	public List<PgimCfgNivelRiesgoDTO> listarCfgNivelRiesgoPorIdCfgFactorRiesgo(Long idCfgFactorRiesgo) {
		return this.cfgNivelRiesgoRepository.listarCfgNivelRiesgoPorIdCfgFactorRiesgo(idCfgFactorRiesgo);
	}

	@Override
	public PgimCfgFactorRiesgoDTO obtenerCfgFactorTecnicoGestion(Long idCfgFactorRiesgo, Long idGrupoRiesgo) {
		return this.cfgFactorRiesgoRepository.obtenerCfgFactorTecnicoGestion(idCfgFactorRiesgo, idGrupoRiesgo);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimFactorRiesgoDTO crearFactorRiesgo(PgimFactorRiesgoDTO pgimFactorRiesgoDTO, AuditoriaDTO auditoriaDTO) {

		PgimFactorRiesgo pgimFactorRiesgo = new PgimFactorRiesgo();

		PgimGrupoRiesgo pgimGrupoRiesgo = new PgimGrupoRiesgo();
		pgimGrupoRiesgo.setIdGrupoRiesgo(pgimFactorRiesgoDTO.getIdGrupoRiesgo());
		pgimFactorRiesgo.setPgimGrupoRiesgo(pgimGrupoRiesgo);

		PgimEspecialidad pgimEspecialidad = new PgimEspecialidad();
		pgimEspecialidad.setIdEspecialidad(pgimFactorRiesgoDTO.getIdEspecialidad());
		pgimFactorRiesgo.setPgimEspecialidad(pgimEspecialidad);

		PgimValorParametro tipoOrigenDatoRiesgo = new PgimValorParametro();
		tipoOrigenDatoRiesgo.setIdValorParametro(pgimFactorRiesgoDTO.getIdTipoOrigenDatoRiesgo());
		pgimFactorRiesgo.setTipoOrigenDatoRiesgo(tipoOrigenDatoRiesgo);

		pgimFactorRiesgo.setNoFactor(pgimFactorRiesgoDTO.getNoFactor());
		pgimFactorRiesgo.setDeFactor(pgimFactorRiesgoDTO.getDeFactor());

		pgimFactorRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);

		pgimFactorRiesgo.setFeCreacion(auditoriaDTO.getFecha());
		pgimFactorRiesgo.setUsCreacion(auditoriaDTO.getUsername());
		pgimFactorRiesgo.setIpCreacion(auditoriaDTO.getTerminal());
		PgimFactorRiesgo pgimFactorRiesgoCreado = factorRiesgoRepository.save(pgimFactorRiesgo);

		PgimFactorRiesgoDTO pgimFactorRiesgoDTOCreado = this
				.obtenerFactorRiesgoPorId(pgimFactorRiesgoCreado.getIdFactorRiesgo());

		return pgimFactorRiesgoDTOCreado;
	}

	@Override
	public PgimFactorRiesgoDTO obtenerFactorRiesgoPorId(Long idFactorRiesgo) {
		return this.factorRiesgoRepository.obtenerFactorRiesgoPorId(idFactorRiesgo);
	}

	@Override
	public PgimCfgFactorRiesgo cfgFactorRiesgoFindById(Long idCfgFactorRiesgo) {
		return this.cfgFactorRiesgoRepository.findById(idCfgFactorRiesgo).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarCfgFactorRiesgo(PgimCfgFactorRiesgo pgimCfgFactorRiesgoActual, AuditoriaDTO auditoriaDTO) {

		pgimCfgFactorRiesgoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimCfgFactorRiesgoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgFactorRiesgoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgFactorRiesgoActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgFactorRiesgoRepository.save(pgimCfgFactorRiesgoActual);

		// Obtenemos la lista de factores de riesgo
		List<PgimCfgFactorRiesgoDTO> listaCfgFactorRiesgoDTO = this.cfgFactorRiesgoRepository
				.listarCfgFactorRiesgoPorConfiguracion(
						pgimCfgFactorRiesgoActual.getPgimCfgGrupoRiesgo().getIdCfgGrupoRiesgo());
		int factoresCantidad = listaCfgFactorRiesgoDTO.size();		

		// Obtenemos el valor del indice de consistencia aleatoria por la cantida de
		// factores existentes.
		BigDecimal nuIndConsistenciaAleatoria = new BigDecimal(0);

		List<PgimValorParametroDTO> lPgimValorParamDTOICA = this.parametroService
				.filtrarPorNombreParametro(ConstantesUtil.PARAM_INDICE_CONSISTENCIA_ALEATORIA);

		for (PgimValorParametroDTO pgimValorParametroDTO : lPgimValorParamDTOICA) {
			if (pgimValorParametroDTO.getNuOrden().intValue() == factoresCantidad) {
				nuIndConsistenciaAleatoria = pgimValorParametroDTO.getNuValorNumerico();
			}
		}

		// Actualizamos la configuracion del grupo riesgo modificando el numero
		// indicador de consistencia aleatoria
		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoModificado = cfgGrupoRiesgoRepository
				.findById(pgimCfgFactorRiesgoActual.getPgimCfgGrupoRiesgo().getIdCfgGrupoRiesgo()).orElse(null);

		pgimCfgGrupoRiesgoModificado.setNuIndConsistenciaAleatoria(nuIndConsistenciaAleatoria);
		pgimCfgGrupoRiesgoModificado.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoModificado.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoModificado.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoModificado);

		// Se cambia el estado de la configuracion de los niveles de riesgo por
		// configuracion de factor de riesgo eliminado.
		List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgoDTO = this.cfgNivelRiesgoRepository
				.listarCfgNivelRiesgoPorIdCfgFactorRiesgo(pgimCfgFactorRiesgoActual.getIdCfgFactorRiesgo());

		for (PgimCfgNivelRiesgoDTO pgimCfgNivelRiesgoDTO : lPgimCfgNivelRiesgoDTO) {
			PgimCfgNivelRiesgo pgimCfgNivelRiesgo = cfgNivelRiesgoRepository
					.findById(pgimCfgNivelRiesgoDTO.getIdCfgNivelRiesgo()).orElse(null);

			pgimCfgNivelRiesgo.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimCfgNivelRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCfgNivelRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCfgNivelRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

			this.cfgNivelRiesgoRepository.save(pgimCfgNivelRiesgo);
		}

		// Se cambia el estado de los registros asociados a la configuracion por la fila
		List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOFilas = this.cfgMatrizParesRepository
				.obtenerCfgMatrizParesPorIdCfgFactorRiesgoFila(pgimCfgFactorRiesgoActual.getIdCfgFactorRiesgo());

		for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTO : lPgimCfgMatrizParesDTOFilas) {
			PgimCfgMatrizPares pgimCfgMatrizPares = cfgMatrizParesRepository
					.findById(pgimCfgMatrizParesDTO.getIdCfgMatrizPares()).orElse(null);

			pgimCfgMatrizPares.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimCfgMatrizPares.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCfgMatrizPares.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCfgMatrizPares.setIpActualizacion(auditoriaDTO.getTerminal());

			this.cfgMatrizParesRepository.save(pgimCfgMatrizPares);
		}

		// Se cambia el estado de los registros asociados a la configuracion por la
		// columna
		List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOColumnas = this.cfgMatrizParesRepository
				.obtenerCfgMatrizParesPorIdCfgFactorRiesgoColu(pgimCfgFactorRiesgoActual.getIdCfgFactorRiesgo());

		for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTO : lPgimCfgMatrizParesDTOColumnas) {
			PgimCfgMatrizPares pgimCfgMatrizPares = cfgMatrizParesRepository
					.findById(pgimCfgMatrizParesDTO.getIdCfgMatrizPares()).orElse(null);

			pgimCfgMatrizPares.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimCfgMatrizPares.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCfgMatrizPares.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCfgMatrizPares.setIpActualizacion(auditoriaDTO.getTerminal());

			this.cfgMatrizParesRepository.save(pgimCfgMatrizPares);
		}

		// Se realiza todo el cálculo de ratio y matriz MCP y MCPN

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoCalculo = cfgGrupoRiesgoRepository
				.findById(pgimCfgFactorRiesgoActual.getPgimCfgGrupoRiesgo().getIdCfgGrupoRiesgo()).orElse(null);

		Long idCfgGrupoRiesgo = pgimCfgGrupoRiesgoCalculo.getIdCfgGrupoRiesgo();
		Long idGrupoRiesgoCalculo = pgimCfgGrupoRiesgoCalculo.getPgimGrupoRiesgo().getIdGrupoRiesgo();

		// Se valida que se hayan insertado todos los numeradores y denominadores
		List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOIncompleto = this.cfgMatrizParesRepository
				.validarCfgMatrizParesIncompleto(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);

		if (lPgimCfgMatrizParesDTOIncompleto.size() > 0) {
			return;
		}

		// 1. Calculamos la sumatoria de columna MCP
		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTOSumCol = cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTOSumCol) {

			BigDecimal nuSumatoriaColumnaMcp = new BigDecimal(0);

			List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOCol = cfgMatrizParesRepository
					.obtenerCfgMatrizParesPorColumna(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());
			for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOCol : lPgimCfgMatrizParesDTOCol) {
				BigDecimal nuMcpActual = pgimCfgMatrizParesDTOCol.getNuNumerador()
						.divide(pgimCfgMatrizParesDTOCol.getNuDenominador(), ConstantesUtil.PRECISION_DECIMAL,
								RoundingMode.HALF_EVEN);
				nuSumatoriaColumnaMcp = nuSumatoriaColumnaMcp.add(nuMcpActual);
			}

			PgimCfgFactorRiesgo pgimCfgFactorRiesgoSumCol = this.cfgFactorRiesgoRepository
					.findById(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo()).orElse(null);

			pgimCfgFactorRiesgoSumCol.setNuSumatoriaColumnaMcp(nuSumatoriaColumnaMcp);
			pgimCfgFactorRiesgoSumCol.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCfgFactorRiesgoSumCol.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCfgFactorRiesgoSumCol.setIpActualizacion(auditoriaDTO.getTerminal());

			this.cfgFactorRiesgoRepository.save(pgimCfgFactorRiesgoSumCol);
		}

		// 2. Calculamos el numero MCPN, usado para la tabla de matriz de pares
		// normalizada
		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTOMcpn = cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTOMcpn) {

			List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOCol = cfgMatrizParesRepository
					.obtenerCfgMatrizParesPorColumna(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());

			for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOCol : lPgimCfgMatrizParesDTOCol) {
				BigDecimal nuMcpActual = pgimCfgMatrizParesDTOCol.getNuNumerador()
						.divide(pgimCfgMatrizParesDTOCol.getNuDenominador(), ConstantesUtil.PRECISION_DECIMAL,
								RoundingMode.HALF_EVEN);

				BigDecimal nuMcpnActual = nuMcpActual.divide(pgimCfgFactorRiesgoDTO.getNuSumatoriaColumnaMcp(),
						ConstantesUtil.PRECISION_DECIMAL,
						RoundingMode.HALF_EVEN);

				PgimCfgMatrizPares pgimCfgMatrizParesMcpn = cfgMatrizParesRepository
						.findById(pgimCfgMatrizParesDTOCol.getIdCfgMatrizPares()).orElse(null);

				pgimCfgMatrizParesMcpn.setNuMcpn(nuMcpnActual);
				pgimCfgMatrizParesMcpn.setFeActualizacion(auditoriaDTO.getFecha());
				pgimCfgMatrizParesMcpn.setUsActualizacion(auditoriaDTO.getUsername());
				pgimCfgMatrizParesMcpn.setIpActualizacion(auditoriaDTO.getTerminal());

				this.cfgMatrizParesRepository.save(pgimCfgMatrizParesMcpn);
			}

		}

		// 3. Calculamos el vector resultante
		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTOVectorRes = cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTOVectorRes) {
			BigDecimal sumNuMcpn = new BigDecimal(0);
			int cantidadFactores = 0;

			List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOFila = cfgMatrizParesRepository
					.obtenerCfgMatrizParesPorFila(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());

			for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOFila : lPgimCfgMatrizParesDTOFila) {
				cantidadFactores++;
				sumNuMcpn = sumNuMcpn.add(pgimCfgMatrizParesDTOFila.getNuMcpn());
			}

			BigDecimal factores = new BigDecimal(cantidadFactores);
			BigDecimal nuVectorResultante = sumNuMcpn.divide(factores, ConstantesUtil.PRECISION_DECIMAL,
					RoundingMode.HALF_EVEN);

			PgimCfgFactorRiesgo pgimCfgFactorRiesgoSumCol = this.cfgFactorRiesgoRepository
					.findById(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo()).orElse(null);

			pgimCfgFactorRiesgoSumCol.setNuVectorResultante(nuVectorResultante);
			pgimCfgFactorRiesgoSumCol.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCfgFactorRiesgoSumCol.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCfgFactorRiesgoSumCol.setIpActualizacion(auditoriaDTO.getTerminal());

			this.cfgFactorRiesgoRepository.save(pgimCfgFactorRiesgoSumCol);
		}

		// 4. Calculamos NuNmax
		PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTONuMax = this.cfgGrupoRiesgoRepository
				.obtenerCalculoRatioConsistencia(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);
		BigDecimal sumNuNmax = new BigDecimal(0);

		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTONuNmax = this.cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTONuNmax) {
			BigDecimal mul = pgimCfgFactorRiesgoDTO.getNuSumatoriaColumnaMcp()
					.multiply(pgimCfgFactorRiesgoDTO.getNuVectorResultante());
			sumNuNmax = sumNuNmax.add(mul);
		}

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoNuMax = cfgGrupoRiesgoRepository
				.findById(pgimCfgGrupoRiesgoDTONuMax.getIdCfgGrupoRiesgo()).orElse(null);

		pgimCfgGrupoRiesgoNuMax.setNuNmax(sumNuNmax);
		pgimCfgGrupoRiesgoNuMax.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoNuMax.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoNuMax.setIpActualizacion(auditoriaDTO.getTerminal());

		cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoNuMax);

		// 5. Calculamos Indice de consistencia
		PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTONuIndice = cfgGrupoRiesgoRepository
				.obtenerCalculoRatioConsistencia(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);

		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTONuIndice = cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);
		int cantidadFactores = lPgimCfgFactorRiesgoDTONuIndice.size();

		BigDecimal factores = new BigDecimal(cantidadFactores);
		BigDecimal nuNmax = pgimCfgGrupoRiesgoDTONuIndice.getNuNmax();
		BigDecimal one = new BigDecimal(1);

		BigDecimal restaOne = nuNmax.subtract(factores);
		BigDecimal restaTwo = factores.subtract(one);

		BigDecimal nuIndiceConsistencia = null;

		if (!restaTwo.equals(new BigDecimal(0))) {
			nuIndiceConsistencia = restaOne.divide(restaTwo, ConstantesUtil.PRECISION_DECIMAL, RoundingMode.HALF_EVEN);
		}

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoNuIndice = cfgGrupoRiesgoRepository
				.findById(pgimCfgGrupoRiesgoDTONuIndice.getIdCfgGrupoRiesgo()).orElse(null);

		pgimCfgGrupoRiesgoNuIndice.setNuIndiceConsistencia(nuIndiceConsistencia);
		pgimCfgGrupoRiesgoNuIndice.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoNuIndice.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoNuIndice.setIpActualizacion(auditoriaDTO.getTerminal());
		cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoNuIndice);

		// 6. Calculamos el ratio de consistencia
		PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTORatio = cfgGrupoRiesgoRepository
				.obtenerCalculoRatioConsistencia(idCfgGrupoRiesgo, idGrupoRiesgoCalculo);

		BigDecimal nuRatioConsistencia = null;
		if (!pgimCfgGrupoRiesgoDTORatio.getNuIndConsistenciaAleatoria().equals(new BigDecimal(0))) {
			nuRatioConsistencia = pgimCfgGrupoRiesgoDTORatio.getNuIndiceConsistencia()
					.divide(pgimCfgGrupoRiesgoDTORatio.getNuIndConsistenciaAleatoria(),
							ConstantesUtil.PRECISION_DECIMAL, RoundingMode.HALF_EVEN);
		}

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoNuRatio = cfgGrupoRiesgoRepository
				.findById(pgimCfgGrupoRiesgoDTONuIndice.getIdCfgGrupoRiesgo()).orElse(null);

		BigDecimal nuCalificacionGrupo = this.calcularAlphaBeta(pgimCfgGrupoRiesgoDTONuIndice.getIdCfgGrupoRiesgo(),
				idGrupoRiesgoCalculo);

		pgimCfgGrupoRiesgoNuRatio.setNuCalificacionGrupo(nuCalificacionGrupo);
		pgimCfgGrupoRiesgoNuRatio.setNuRatioConsistencia(nuRatioConsistencia);
		pgimCfgGrupoRiesgoNuRatio.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoNuRatio.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoNuRatio.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoNuRatio);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimCfgFactorRiesgoDTO crearCfgFactorRiesgo(PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO,
			List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgo, AuditoriaDTO auditoriaDTO) {

		PgimCfgFactorRiesgo pgimCfgFactorRiesgo = new PgimCfgFactorRiesgo();

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo = new PgimCfgGrupoRiesgo();
		pgimCfgGrupoRiesgo.setIdCfgGrupoRiesgo(pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo());
		pgimCfgFactorRiesgo.setPgimCfgGrupoRiesgo(pgimCfgGrupoRiesgo);

		PgimFactorRiesgo pgimFactorRiesgo = new PgimFactorRiesgo();
		pgimFactorRiesgo.setIdFactorRiesgo(pgimCfgFactorRiesgoDTO.getIdFactorRiesgo());
		pgimCfgFactorRiesgo.setPgimFactorRiesgo(pgimFactorRiesgo);

		pgimCfgFactorRiesgo.setNuOrdenPrioridad(pgimCfgFactorRiesgoDTO.getNuOrdenPrioridad());
		pgimCfgFactorRiesgo.setFlAfectadoGestion(pgimCfgFactorRiesgoDTO.getFlAfectadoGestion());
		pgimCfgFactorRiesgo.setNuSumatoriaColumnaMcp(null);
		pgimCfgFactorRiesgo.setNuVectorResultante(null);

		pgimCfgFactorRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimCfgFactorRiesgo.setFeCreacion(auditoriaDTO.getFecha());
		pgimCfgFactorRiesgo.setUsCreacion(auditoriaDTO.getUsername());
		pgimCfgFactorRiesgo.setIpCreacion(auditoriaDTO.getTerminal());
		PgimCfgFactorRiesgo pgimCfgFactorRiesgoCreado = cfgFactorRiesgoRepository.save(pgimCfgFactorRiesgo);

		// Obtenemos la cantidad de factores de riesgo existentes
		List<PgimCfgFactorRiesgoDTO> listaCfgFactorRiesgoDTO = this.cfgFactorRiesgoRepository
				.listarCfgFactorRiesgoPorConfiguracion(pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo());
		int cantidadFactores = listaCfgFactorRiesgoDTO.size();

		List<PgimCfgFactorRiesgoDTO> listaCfgFactorRiesgoDTOAfecGestion = this.cfgFactorRiesgoRepository
				.listarCfgFactorRiesgoPorConfiguracionAfecGestion(pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo());
		int cantidadFactoresAfecGestion = listaCfgFactorRiesgoDTOAfecGestion.size();

		BigDecimal nuCalificacionGrupo = null;

		if (cantidadFactores > 0) {
			nuCalificacionGrupo = (new BigDecimal(cantidadFactoresAfecGestion)).divide(
					new BigDecimal(cantidadFactores), ConstantesUtil.PRECISION_DECIMAL,
					RoundingMode.HALF_EVEN);
		}

		// Obtenemos el valor del indice de consistencia aleatoria por la cantidad de
		// factores existentes.
		BigDecimal nuIndConsistenciaAleatoria = new BigDecimal(0);
		List<PgimValorParametroDTO> lPgimValorParamDTOICA = this.parametroService
				.filtrarPorNombreParametro(ConstantesUtil.PARAM_INDICE_CONSISTENCIA_ALEATORIA);

		for (PgimValorParametroDTO pgimValorParametroDTO : lPgimValorParamDTOICA) {
			if (pgimValorParametroDTO.getNuOrden().intValue() == cantidadFactores) {
				nuIndConsistenciaAleatoria = pgimValorParametroDTO.getNuValorNumerico();
			}
		}

		// Actualizamos la configuracion del grupo riesgo modificando el numero
		// indicador de consistencia aleatoria
		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoModificado = cfgGrupoRiesgoRepository
				.findById(pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo()).orElse(null);

		pgimCfgGrupoRiesgoModificado.setNuIndConsistenciaAleatoria(nuIndConsistenciaAleatoria);
		pgimCfgGrupoRiesgoModificado.setNuCalificacionGrupo(nuCalificacionGrupo);

		pgimCfgGrupoRiesgoModificado.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoModificado.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoModificado.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoModificado);

		// Obtenemos la lista de la configuración de factores de riesgo existentes,
		// incluido el creado anteriormente
		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTO = cfgFactorRiesgoRepository
				.listarCfgFactorRiesgoPorConfiguracion(pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo());

		// Insertamos columna adicional, para la matriz de pares existente.
		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTOEle : lPgimCfgFactorRiesgoDTO) {
			if (pgimCfgFactorRiesgoDTOEle.getIdCfgFactorRiesgo().intValue() != pgimCfgFactorRiesgoCreado
					.getIdCfgFactorRiesgo().intValue()) {
				PgimCfgMatrizPares pgimCfgMatrizPares = new PgimCfgMatrizPares();

				PgimCfgFactorRiesgo cfgFactorRiesgoFila = new PgimCfgFactorRiesgo();
				cfgFactorRiesgoFila.setIdCfgFactorRiesgo(pgimCfgFactorRiesgoDTOEle.getIdCfgFactorRiesgo());
				pgimCfgMatrizPares.setCfgFactorRiesgoFila(cfgFactorRiesgoFila);

				PgimCfgFactorRiesgo cfgFactorRiesgoColu = new PgimCfgFactorRiesgo();
				cfgFactorRiesgoColu.setIdCfgFactorRiesgo(pgimCfgFactorRiesgoCreado.getIdCfgFactorRiesgo());
				pgimCfgMatrizPares.setCfgFactorRiesgoColu(cfgFactorRiesgoColu);

				pgimCfgMatrizPares.setFlEsEditable("0");
				pgimCfgMatrizPares.setNuNumerador(null);
				pgimCfgMatrizPares.setNuDenominador(null);
				pgimCfgMatrizPares.setNuMcp(null);
				pgimCfgMatrizPares.setNuMcpn(null);

				pgimCfgMatrizPares.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimCfgMatrizPares.setFeCreacion(auditoriaDTO.getFecha());
				pgimCfgMatrizPares.setUsCreacion(auditoriaDTO.getUsername());
				pgimCfgMatrizPares.setIpCreacion(auditoriaDTO.getTerminal());
				cfgMatrizParesRepository.save(pgimCfgMatrizPares);
			}

		}

		// Insertamos el nuevo grupo de matriz de pares para el registro configuración
		// de factor riesgo creado.
		for (PgimCfgFactorRiesgoDTO obj : lPgimCfgFactorRiesgoDTO) {
			PgimCfgMatrizPares pgimCfgMatrizPares = new PgimCfgMatrizPares();

			PgimCfgFactorRiesgo cfgFactorRiesgoFila = new PgimCfgFactorRiesgo();
			cfgFactorRiesgoFila.setIdCfgFactorRiesgo(pgimCfgFactorRiesgoCreado.getIdCfgFactorRiesgo());
			pgimCfgMatrizPares.setCfgFactorRiesgoFila(cfgFactorRiesgoFila);

			PgimCfgFactorRiesgo cfgFactorRiesgoColu = new PgimCfgFactorRiesgo();
			cfgFactorRiesgoColu.setIdCfgFactorRiesgo(obj.getIdCfgFactorRiesgo());
			pgimCfgMatrizPares.setCfgFactorRiesgoColu(cfgFactorRiesgoColu);

			pgimCfgMatrizPares.setFlEsEditable("0");

			if (cfgFactorRiesgoFila.getIdCfgFactorRiesgo().intValue() == cfgFactorRiesgoColu.getIdCfgFactorRiesgo()
					.intValue()) {
				pgimCfgMatrizPares.setNuNumerador(new BigDecimal(1));
				pgimCfgMatrizPares.setNuDenominador(new BigDecimal(1));
				pgimCfgMatrizPares.setNuMcp(new BigDecimal(1));
			} else {
				pgimCfgMatrizPares.setNuNumerador(null);
				pgimCfgMatrizPares.setNuDenominador(null);
				pgimCfgMatrizPares.setNuMcp(null);
			}

			pgimCfgMatrizPares.setNuMcpn(null);

			pgimCfgMatrizPares.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimCfgMatrizPares.setFeCreacion(auditoriaDTO.getFecha());
			pgimCfgMatrizPares.setUsCreacion(auditoriaDTO.getUsername());
			pgimCfgMatrizPares.setIpCreacion(auditoriaDTO.getTerminal());
			cfgMatrizParesRepository.save(pgimCfgMatrizPares);
		}

		// Insertamos los registros para el nivel de riesgo
		for (PgimCfgNivelRiesgoDTO pgimCfgNivelRiesgoDTO : lPgimCfgNivelRiesgo) {
			PgimCfgNivelRiesgo pgimCfgNivelRiesgo = new PgimCfgNivelRiesgo();

			pgimCfgNivelRiesgo.setPgimCfgFactorRiesgo(pgimCfgFactorRiesgoCreado);

			PgimNivelRiesgo pgimNivelRiesgo = new PgimNivelRiesgo();
			pgimNivelRiesgo.setIdNivelRiesgo(pgimCfgNivelRiesgoDTO.getIdNivelRiesgo());
			pgimCfgNivelRiesgo.setPgimNivelRiesgo(pgimNivelRiesgo);

			pgimCfgNivelRiesgo.setDeEspecificacion(pgimCfgNivelRiesgoDTO.getDeEspecificacion());

			pgimCfgNivelRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimCfgNivelRiesgo.setFeCreacion(auditoriaDTO.getFecha());
			pgimCfgNivelRiesgo.setUsCreacion(auditoriaDTO.getUsername());
			pgimCfgNivelRiesgo.setIpCreacion(auditoriaDTO.getTerminal());
			cfgNivelRiesgoRepository.save(pgimCfgNivelRiesgo);
		}

		PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTOCreado = this
				.obtenerCfgFactorRiesgoPorId(pgimCfgFactorRiesgoCreado.getIdCfgFactorRiesgo());

		return pgimCfgFactorRiesgoDTOCreado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimCfgFactorRiesgoDTO modificarCfgFactorRiesgo(PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO,
			List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgo, PgimCfgFactorRiesgo pgimCfgFactorRiesgo,
			AuditoriaDTO auditoriaDTO) {

		pgimCfgFactorRiesgo.setNuOrdenPrioridad(pgimCfgFactorRiesgoDTO.getNuOrdenPrioridad());
		pgimCfgFactorRiesgo.setFlAfectadoGestion(pgimCfgFactorRiesgoDTO.getFlAfectadoGestion());

		pgimCfgFactorRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgFactorRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgFactorRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());
		PgimCfgFactorRiesgo pgimCfgFactorRiesgoModificado = this.cfgFactorRiesgoRepository.save(pgimCfgFactorRiesgo);

		for (PgimCfgNivelRiesgoDTO pgimCfgNivelRiesgoDTO : lPgimCfgNivelRiesgo) {

			PgimCfgNivelRiesgo pgimCfgNivelRiesgo = cfgNivelRiesgoRepository
					.findById(pgimCfgNivelRiesgoDTO.getIdCfgNivelRiesgo()).orElse(null);

			pgimCfgNivelRiesgo.setDeEspecificacion(pgimCfgNivelRiesgoDTO.getDeEspecificacion());

			pgimCfgNivelRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCfgNivelRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCfgNivelRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());
			cfgNivelRiesgoRepository.save(pgimCfgNivelRiesgo);
		}

		PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTOModificado = this
				.obtenerCfgFactorRiesgoPorId(pgimCfgFactorRiesgoModificado.getIdCfgFactorRiesgo());

		// Obtenemos la cantidad de factores de riesgo existentes
		List<PgimCfgFactorRiesgoDTO> listaCfgFactorRiesgoDTO = this.cfgFactorRiesgoRepository
				.listarCfgFactorRiesgoPorConfiguracion(pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo());
		int cantidadFactores = listaCfgFactorRiesgoDTO.size();

		List<PgimCfgFactorRiesgoDTO> listaCfgFactorRiesgoDTOAfecGestion = this.cfgFactorRiesgoRepository
				.listarCfgFactorRiesgoPorConfiguracionAfecGestion(pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo());
		int cantidadFactoresAfecGestion = listaCfgFactorRiesgoDTOAfecGestion.size();

		BigDecimal nuCalificacionGrupo = null;

		if (cantidadFactores > 0) {
			nuCalificacionGrupo = (new BigDecimal(cantidadFactoresAfecGestion)).divide(
					new BigDecimal(cantidadFactores), ConstantesUtil.PRECISION_DECIMAL,
					RoundingMode.HALF_EVEN);
		}

		// Actualizamos la configuracion del grupo riesgo modificando el numero
		// indicador de consistencia aleatoria
		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo = cfgGrupoRiesgoRepository
				.findById(pgimCfgFactorRiesgoDTO.getIdCfgGrupoRiesgo()).orElse(null);

		pgimCfgGrupoRiesgo.setNuCalificacionGrupo(nuCalificacionGrupo);

		pgimCfgGrupoRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgo);

		return pgimCfgFactorRiesgoDTOModificado;
	}

	@Override
	public PgimCfgFactorRiesgoDTO obtenerCfgFactorRiesgoPorId(Long idCfgFactorRiesgo) {
		return this.cfgFactorRiesgoRepository.obtenerCfgFactorRiesgoPorId(idCfgFactorRiesgo);
	}

	@Override
	public List<PgimFactorRiesgoDTO> listarFactorRiesgoNotInCfgFactorRiesgo(Long idConfiguraRiesgo, Long idGrupoRiesgo,
			Long idEspecialidad) {
		return this.factorRiesgoRepository.listarFactorRiesgoNotInCfgFactorRiesgo(idConfiguraRiesgo, idGrupoRiesgo,
				idEspecialidad);
	}

	@Override
	public List<PgimFactorRiesgoDTO> listarFactorRiesgo(Long idConfiguraRiesgo, Long idGrupoRiesgo) {
		return this.factorRiesgoRepository.listarFactorRiesgo(idConfiguraRiesgo, idGrupoRiesgo);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimCfgMatrizParesDTO modificarCfgMatrizParesNumeradorDenominador(
			PgimCfgMatrizParesDTO pgimCfgMatrizParesDTO, AuditoriaDTO auditoriaDTO) {

		PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOActual = cfgMatrizParesRepository
				.obtenerCfgMatrizParesPorFilaYColumna(pgimCfgMatrizParesDTO.getIdCfgFactorRiesgoFila(),
						pgimCfgMatrizParesDTO.getIdCfgFactorRiesgoColu());

		PgimCfgMatrizPares pgimCfgMatrizPares = cfgMatrizParesRepository
				.findById(pgimCfgMatrizParesDTOActual.getIdCfgMatrizPares()).orElse(null);

		pgimCfgMatrizPares.setNuNumerador(pgimCfgMatrizParesDTO.getNuNumerador());
		pgimCfgMatrizPares.setNuDenominador(pgimCfgMatrizParesDTO.getNuDenominador());

		BigDecimal nuMcp = pgimCfgMatrizParesDTO.getNuNumerador().divide(pgimCfgMatrizParesDTO.getNuDenominador(),
				ConstantesUtil.PRECISION_DECIMAL,
				RoundingMode.HALF_EVEN);

		pgimCfgMatrizPares.setNuMcp(nuMcp);

		pgimCfgMatrizPares.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgMatrizPares.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgMatrizPares.setIpActualizacion(auditoriaDTO.getTerminal());
		PgimCfgMatrizPares pgimCfgMatrizParesModificado = cfgMatrizParesRepository.save(pgimCfgMatrizPares);

		PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOInversoActual = cfgMatrizParesRepository
				.obtenerCfgMatrizParesPorFilaYColumna(pgimCfgMatrizParesDTO.getIdCfgFactorRiesgoColu(),
						pgimCfgMatrizParesDTO.getIdCfgFactorRiesgoFila());
		PgimCfgMatrizPares pgimCfgMatrizParesInverso = cfgMatrizParesRepository
				.findById(pgimCfgMatrizParesDTOInversoActual.getIdCfgMatrizPares()).orElse(null);
		pgimCfgMatrizParesInverso.setNuNumerador(pgimCfgMatrizParesDTO.getNuDenominador());
		pgimCfgMatrizParesInverso.setNuDenominador(pgimCfgMatrizParesDTO.getNuNumerador());

		BigDecimal nuMcpInverso = pgimCfgMatrizParesDTO.getNuDenominador()
				.divide(pgimCfgMatrizParesDTO.getNuNumerador(), ConstantesUtil.PRECISION_DECIMAL,
						RoundingMode.HALF_EVEN);
		pgimCfgMatrizParesInverso.setNuMcp(nuMcpInverso);

		pgimCfgMatrizParesInverso.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgMatrizParesInverso.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgMatrizParesInverso.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgMatrizParesRepository.save(pgimCfgMatrizParesInverso);

		PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOModificado = cfgMatrizParesRepository
				.obtenerCfgMatrizParesPorId(pgimCfgMatrizParesModificado.getIdCfgMatrizPares());

		// Se valida que se hayan insertado todos los numeradores y denominadores
		List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOIncompleto = cfgMatrizParesRepository
				.validarCfgMatrizParesIncompleto(pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(),
						pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		if (lPgimCfgMatrizParesDTOIncompleto.size() > 0) {
			return pgimCfgMatrizParesDTOModificado;
		}

		// 1. Calculamos la sumatoria de columna MCP
		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTOSumCol = cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(),
						pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTOSumCol) {

			BigDecimal nuSumatoriaColumnaMcp = new BigDecimal(0);

			List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOCol = this.cfgMatrizParesRepository
					.obtenerCfgMatrizParesPorColumna(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());

			for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOCol : lPgimCfgMatrizParesDTOCol) {
				BigDecimal nuMcpActual = pgimCfgMatrizParesDTOCol.getNuNumerador()
						.divide(pgimCfgMatrizParesDTOCol.getNuDenominador(), ConstantesUtil.PRECISION_DECIMAL,
								RoundingMode.HALF_EVEN);
				nuSumatoriaColumnaMcp = nuSumatoriaColumnaMcp.add(nuMcpActual);
			}

			PgimCfgFactorRiesgo pgimCfgFactorRiesgoSumCol = this.cfgFactorRiesgoRepository
					.findById(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo()).orElse(null);

			pgimCfgFactorRiesgoSumCol.setNuSumatoriaColumnaMcp(nuSumatoriaColumnaMcp);
			pgimCfgFactorRiesgoSumCol.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCfgFactorRiesgoSumCol.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCfgFactorRiesgoSumCol.setIpActualizacion(auditoriaDTO.getTerminal());

			this.cfgFactorRiesgoRepository.save(pgimCfgFactorRiesgoSumCol);

		}

		// 2. Calculamos el numero MCPN, usado para la tabla de matriz de pares
		// normalizada
		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTOMcpn = this.cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(),
						pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTOMcpn) {

			List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOCol = this.cfgMatrizParesRepository
					.obtenerCfgMatrizParesPorColumna(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());

			for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOCol : lPgimCfgMatrizParesDTOCol) {
				BigDecimal nuMcpActual = pgimCfgMatrizParesDTOCol.getNuNumerador()
						.divide(pgimCfgMatrizParesDTOCol.getNuDenominador(), ConstantesUtil.PRECISION_DECIMAL,
								RoundingMode.HALF_EVEN);
				BigDecimal nuMcpnActual = nuMcpActual.divide(pgimCfgFactorRiesgoDTO.getNuSumatoriaColumnaMcp(),
						ConstantesUtil.PRECISION_DECIMAL,
						RoundingMode.HALF_EVEN);

				PgimCfgMatrizPares pgimCfgMatrizParesMcpn = this.cfgMatrizParesRepository
						.findById(pgimCfgMatrizParesDTOCol.getIdCfgMatrizPares()).orElse(null);

				pgimCfgMatrizParesMcpn.setNuMcpn(nuMcpnActual);
				pgimCfgMatrizParesMcpn.setFeActualizacion(auditoriaDTO.getFecha());
				pgimCfgMatrizParesMcpn.setUsActualizacion(auditoriaDTO.getUsername());
				pgimCfgMatrizParesMcpn.setIpActualizacion(auditoriaDTO.getTerminal());

				this.cfgMatrizParesRepository.save(pgimCfgMatrizParesMcpn);
			}
		}

		// 3. Calculamos el vector resultante
		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTOVectorRes = this.cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(),
						pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTOVectorRes) {
			BigDecimal sumNuMcpn = new BigDecimal(0);
			int cantidadFactores = 0;

			List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOFila = cfgMatrizParesRepository
					.obtenerCfgMatrizParesPorFila(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo());

			for (PgimCfgMatrizParesDTO pgimCfgMatrizParesDTOFila : lPgimCfgMatrizParesDTOFila) {
				cantidadFactores++;
				sumNuMcpn = sumNuMcpn.add(pgimCfgMatrizParesDTOFila.getNuMcpn());

			}

			BigDecimal factores = new BigDecimal(cantidadFactores);
			BigDecimal nuVectorResultante = sumNuMcpn.divide(factores, ConstantesUtil.PRECISION_DECIMAL,
					RoundingMode.HALF_EVEN);

			PgimCfgFactorRiesgo pgimCfgFactorRiesgoSumCol = cfgFactorRiesgoRepository
					.findById(pgimCfgFactorRiesgoDTO.getIdCfgFactorRiesgo()).orElse(null);

			pgimCfgFactorRiesgoSumCol.setNuVectorResultante(nuVectorResultante);
			pgimCfgFactorRiesgoSumCol.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCfgFactorRiesgoSumCol.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCfgFactorRiesgoSumCol.setIpActualizacion(auditoriaDTO.getTerminal());

			this.cfgFactorRiesgoRepository.save(pgimCfgFactorRiesgoSumCol);

		}

		// 4. Calculamos NuNmax
		PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTONuMax = this.cfgGrupoRiesgoRepository
				.obtenerCalculoRatioConsistencia(
						pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(), pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		BigDecimal sumNuNmax = new BigDecimal(0);

		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTONuNmax = this.cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(),
						pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTONuNmax) {

			BigDecimal mul = pgimCfgFactorRiesgoDTO.getNuSumatoriaColumnaMcp()
					.multiply(pgimCfgFactorRiesgoDTO.getNuVectorResultante());
			sumNuNmax = sumNuNmax.add(mul);
		}

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoNuMax = this.cfgGrupoRiesgoRepository
				.findById(pgimCfgGrupoRiesgoDTONuMax.getIdCfgGrupoRiesgo()).orElse(null);
		pgimCfgGrupoRiesgoNuMax.setNuNmax(sumNuNmax);
		pgimCfgGrupoRiesgoNuMax.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoNuMax.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoNuMax.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoNuMax);

		// 5. Calculamos Indice de consistencia
		PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTONuIndice = this.cfgGrupoRiesgoRepository
				.obtenerCalculoRatioConsistencia(pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(),
						pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTONuIndice = this.cfgFactorRiesgoRepository
				.listarCfgFactorTecnicoGestion(pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(),
						pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		int cantidadFactores = lPgimCfgFactorRiesgoDTONuIndice.size();

		BigDecimal factores = new BigDecimal(cantidadFactores);
		BigDecimal nuNmax = pgimCfgGrupoRiesgoDTONuIndice.getNuNmax();
		BigDecimal one = new BigDecimal(1);

		BigDecimal restaOne = nuNmax.subtract(factores);
		BigDecimal restaTwo = factores.subtract(one);

		BigDecimal nuIndiceConsistencia = restaOne.divide(restaTwo, ConstantesUtil.PRECISION_DECIMAL,
				RoundingMode.HALF_EVEN);

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoNuIndice = this.cfgGrupoRiesgoRepository
				.findById(pgimCfgGrupoRiesgoDTONuIndice.getIdCfgGrupoRiesgo()).orElse(null);

		pgimCfgGrupoRiesgoNuIndice.setNuIndiceConsistencia(nuIndiceConsistencia);
		pgimCfgGrupoRiesgoNuIndice.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoNuIndice.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoNuIndice.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoNuIndice);

		// 6. Calculamos el ratio de consistencia
		PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTORatio = this.cfgGrupoRiesgoRepository
				.obtenerCalculoRatioConsistencia(
						pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(), pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		BigDecimal nuRatioConsistencia = null;

		if (!pgimCfgGrupoRiesgoDTORatio.getNuIndConsistenciaAleatoria().equals(new BigDecimal(0))) {
			nuRatioConsistencia = pgimCfgGrupoRiesgoDTORatio.getNuIndiceConsistencia()
					.divide(pgimCfgGrupoRiesgoDTORatio.getNuIndConsistenciaAleatoria(),
							ConstantesUtil.PRECISION_DECIMAL, RoundingMode.HALF_EVEN);
		}

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoNuRatio = this.cfgGrupoRiesgoRepository
				.findById(pgimCfgGrupoRiesgoDTONuIndice.getIdCfgGrupoRiesgo()).orElse(null);

		BigDecimal nuCalificacionGrupo = this.calcularAlphaBeta(pgimCfgMatrizParesDTO.getDescIdCfgGrupoRiesgo(),
				pgimCfgMatrizParesDTO.getDescIdGrupoRiesgo());

		pgimCfgGrupoRiesgoNuRatio.setNuCalificacionGrupo(nuCalificacionGrupo);
		pgimCfgGrupoRiesgoNuRatio.setNuRatioConsistencia(nuRatioConsistencia);
		pgimCfgGrupoRiesgoNuRatio.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoNuRatio.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoNuRatio.setIpActualizacion(auditoriaDTO.getTerminal());

		cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoNuRatio);

		return pgimCfgMatrizParesDTOModificado;
	}

	/**
	 * Permite calcular el alfa o el beta de acuerdo con el idGrupoRiesgo
	 * 
	 * @param idCfgGrupoRiesgo
	 * @param idGrupoRiesgo
	 * @return
	 */
	private BigDecimal calcularAlphaBeta(Long idCfgGrupoRiesgo, Long idGrupoRiesgo) {
		BigDecimal resultado = null;

		BigDecimal alpha = null;
		BigDecimal beta = null;

		List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTO = cfgFactorRiesgoRepository
				.listarCfgFactorRiesgoPorConfiguracion(idCfgGrupoRiesgo);

		int nroFactoresAfectados = 0;
		int totalFactoresTecnicos = lPgimCfgFactorRiesgoDTO.size();

		for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lPgimCfgFactorRiesgoDTO) {
			if (pgimCfgFactorRiesgoDTO.getFlAfectadoGestion() == null) {
				continue;
			}
			if (pgimCfgFactorRiesgoDTO.getFlAfectadoGestion().equals("1")) {
				nroFactoresAfectados++;
			}
		}

		BigDecimal numerador = new BigDecimal(nroFactoresAfectados);
		BigDecimal denominador = new BigDecimal(totalFactoresTecnicos);

		if (!denominador.equals(new BigDecimal(0))) {
			alpha = numerador.divide(denominador, ConstantesUtil.PRECISION_DECIMAL, RoundingMode.HALF_EVEN);
		}

		if (idGrupoRiesgo.equals(ConstantesUtil.PARAM_GRUPO_RIESGO_GESTION)) {
			if(alpha != null){
				beta = (new BigDecimal(1)).subtract(alpha);				
			}
			resultado = beta;
		} else if (idGrupoRiesgo.equals(ConstantesUtil.PARAM_GRUPO_RIESGO_TECNICO)) {
			resultado = alpha;
		}

		return resultado;
	}

	@Override
	public Page<PgimFactorRiesgoDTO> listarFactorRiesgoBase(PgimFactorRiesgoDTO filtroFactorRiesgo, Pageable paginador,
			AuditoriaDTO auditoriaDTO) {

		Page<PgimFactorRiesgoDTO> pPgimFactorRiesgoDTO = this.factorRiesgoRepository.listarFactorRiesgoBase(
				filtroFactorRiesgo.getNoFactor(), filtroFactorRiesgo.getIdGrupoRiesgo(),
				filtroFactorRiesgo.getIdEspecialidad(), filtroFactorRiesgo.getIdTipoOrigenDatoRiesgo(),
				filtroFactorRiesgo.getTextoBusqueda(), paginador);

		return pPgimFactorRiesgoDTO;
	}

	@Override
	public PgimFactorRiesgoDTO obtenerFactorRiesgoBase(Long idFactorRiesgo) {
		return this.factorRiesgoRepository.obtenerFactorRiesgoBasePorId(idFactorRiesgo);
	}

	@Override
	public PgimFactorRiesgo factorRiesgoFindById(Long idFactorRiesgo) {
		return this.factorRiesgoRepository.findById(idFactorRiesgo).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimFactorRiesgoDTO modificarFactorRiesgoBase(PgimFactorRiesgoDTO pgimFactorRiesgoDTO,
			PgimFactorRiesgo pgimFactorRiesgo, AuditoriaDTO auditoriaDTO) {

		PgimGrupoRiesgo pgimGrupoRiesgo = new PgimGrupoRiesgo();
		pgimGrupoRiesgo.setIdGrupoRiesgo(pgimFactorRiesgoDTO.getIdGrupoRiesgo());
		pgimFactorRiesgo.setPgimGrupoRiesgo(pgimGrupoRiesgo);

		PgimEspecialidad pgimEspecialidad = new PgimEspecialidad();
		pgimEspecialidad.setIdEspecialidad(pgimFactorRiesgoDTO.getIdEspecialidad());
		pgimFactorRiesgo.setPgimEspecialidad(pgimEspecialidad);

		PgimValorParametro tipoOrigenDatoRiesgo = new PgimValorParametro();
		tipoOrigenDatoRiesgo.setIdValorParametro(pgimFactorRiesgoDTO.getIdTipoOrigenDatoRiesgo());
		pgimFactorRiesgo.setTipoOrigenDatoRiesgo(tipoOrigenDatoRiesgo);

		pgimFactorRiesgo.setNoFactor(pgimFactorRiesgoDTO.getNoFactor());
		pgimFactorRiesgo.setDeFactor(pgimFactorRiesgoDTO.getDeFactor());

		pgimFactorRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimFactorRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimFactorRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimFactorRiesgoDTO pgimFactorRiesgoDTOResultado = this
				.obtenerFactorRiesgoBase(pgimFactorRiesgoDTO.getIdFactorRiesgo());

		return pgimFactorRiesgoDTOResultado;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarFactorRiesgoBase(PgimFactorRiesgo pgimFactorRiesgoActual, AuditoriaDTO auditoriaDTO) {
		
		//Validamos que no se esté usando este factor de riesgo base en alguna configuración de riesgo
		String msjExcepcionControlada = null;
		
		List<PgimConfiguraRiesgoDTO> lPgimConfiguraRiesgoDTO = this.configuraRiesgoRepository.listarConfiguraRiesgoPorIdFactor(
				pgimFactorRiesgoActual.getIdFactorRiesgo());
	
		int cantidadConfigAsociadas = lPgimConfiguraRiesgoDTO.size();

		if (cantidadConfigAsociadas > 0) {
			if (cantidadConfigAsociadas == 1) {
				msjExcepcionControlada = String.format(
						"No se puede eliminar el factor riesgo debido a que está asociado a una Configuración de riesgo, a saber: %s-%s %s. "
						+ "Deberá primero eliminarlo en el marco de la configuración de riesgo indicada para poder continuar.",
						ConstantesUtil.PREFIJO_CFG_RIESGO,
						lPgimConfiguraRiesgoDTO.get(0).getIdConfiguraRiesgo(),
						lPgimConfiguraRiesgoDTO.get(0).getNoConfiguracion());
			} else {
				String configs = "";
				for (PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO : lPgimConfiguraRiesgoDTO) { 
					if(configs.length() > 0) configs = configs + ", ";
					configs = configs + String.format("%s-%s %s", 
							ConstantesUtil.PREFIJO_CFG_RIESGO,
							pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo(),
							pgimConfiguraRiesgoDTO.getNoConfiguracion());
				}
				msjExcepcionControlada = String.format(
						"No se puede eliminar el factor riesgo porque está asociado a las siguientes Configuraciones de riesgo: %s. "
						+ "Deberá primero eliminarlo en el marco de las configuraciones de riesgo indicadas para poder continuar.",
						configs);
				
			}

			throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada);
		}

		pgimFactorRiesgoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimFactorRiesgoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimFactorRiesgoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimFactorRiesgoActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.factorRiesgoRepository.save(pgimFactorRiesgoActual);
	}

	@Override
	public void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso) {

		if (!pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_REGISTRARCONF_APROBARCONF) &&
				!pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_CONFAPROBADA_APROBARCONF)) {
			return;
		}

		String msjExcepcionControlada = null;

		Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso)
				.orElse(null);

		PgimConfiguraRiesgo pgimConfiguraRiesgo = this.configuraRiesgoRepository
				.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

		if (pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_CONFAPROBADA_APROBARCONF)) {

			// Se debe verificar que no exista ninguna fiscalización asociada a esta
			// configuración
			List<PgimRankingSupervisionDTO> lPgimRankingSupervisionDTO = this.rankingSupervisionRepository
					.obtenerRankingsSupervisionPorConfiguracionRiesgo(pgimConfiguraRiesgo.getIdConfiguraRiesgo());

			int cantidadSupervisionesAsociadas = lPgimRankingSupervisionDTO.size();

			if (cantidadSupervisionesAsociadas > 0) {
				if (cantidadSupervisionesAsociadas == 1) {
					msjExcepcionControlada = "No se puede asignar el paso porque existe una fiscalización asociada a esta configuración de riesgo";
				} else {
					msjExcepcionControlada = String.format(
							"No se puede asignar el paso porque existen %d fiscalizaciones asociadas a esta configuración de riesgo ",
							cantidadSupervisionesAsociadas);
				}

				throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
			}

			// Se debe verificar que no exista ningún ranking de riesgo asociado a esta
			// configuración
			List<PgimRankingRiesgoDTO> lPgimRankingRiesgo = this.rankingRiesgoRepository
					.obtenerRankingsPorConfiguracionRiesgo(pgimConfiguraRiesgo.getIdConfiguraRiesgo());

			int cantidadRankignsAsociados = lPgimRankingRiesgo.size();

			if (cantidadRankignsAsociados > 0) {
				if (cantidadRankignsAsociados == 1) {
					msjExcepcionControlada = "No se puede asignar el paso porque existe un ranking de riesgo asociado a esta configuración de riesgo";
				} else {
					msjExcepcionControlada = String.format(
							"No se puede asignar el paso porque existen %d rankings de riesgo asociados a esta configuración de riesgo",
							cantidadRankignsAsociados);
				}

				throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
			}
		}

		if (pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_REGISTRARCONF_APROBARCONF)) {

			List<PgimValorParametroDTO> lPgimValorParamDTORCA = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_INDICE_RATIO_CONSISTENCIA_ACEPTABLE);

			List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTO = this.cfgGrupoRiesgoRepository
					.listarCfgGrupoRiesgoPorConfiguracion(pgimConfiguraRiesgo.getIdConfiguraRiesgo());

			for (PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO : lPgimCfgGrupoRiesgoDTO) {

				List<PgimCfgFactorRiesgoDTO> lPgimCfgFactorRiesgoDTO = this.cfgFactorRiesgoRepository
						.listarCfgFactorTecnicoGestion(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo(),
								pgimCfgGrupoRiesgoDTO.getIdGrupoRiesgo());

				int cantidadFactores = lPgimCfgFactorRiesgoDTO.size();
				String cantidadFactoresString = String.valueOf(lPgimCfgFactorRiesgoDTO.size());

				List<PgimCfgMatrizParesDTO> lPgimCfgMatrizParesDTOIncompleto = this.cfgMatrizParesRepository
						.validarCfgMatrizParesIncompleto(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo(),
								pgimCfgGrupoRiesgoDTO.getIdGrupoRiesgo());

				if (lPgimCfgMatrizParesDTOIncompleto.size() > 0) {
					msjExcepcionControlada = String.format(
							"No se puede asignar el paso porque la tabla matriz de pares para el grupo de configuración de riesgo %s no está completa",
							pgimCfgGrupoRiesgoDTO.getNoGrupoRiesgo());

					throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
				}

				if (pgimCfgGrupoRiesgoDTO.getNuRatioConsistencia() == null) {
					msjExcepcionControlada = String.format(
							"No se ha podido determinar el ratio de consistencia en el grupo de configuración de riesgo %s",
							pgimCfgGrupoRiesgoDTO.getNoGrupoRiesgo());

					throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
				}

				BigDecimal nuRatioConsistencia = pgimCfgGrupoRiesgoDTO.getNuRatioConsistencia()
						.multiply(new BigDecimal(100));

				for (PgimValorParametroDTO pgimValorParametroDTO : lPgimValorParamDTORCA) {

					if (pgimValorParametroDTO.getDeValorAlfanum() == null
							&& pgimValorParametroDTO.getNoValorParametro().equals(cantidadFactoresString)) {

						BigDecimal nuValorNumerico = pgimValorParametroDTO.getNuValorNumerico()
								.multiply(new BigDecimal(100));

						// Validamos si el ratio es mayor al valor numerico
						if (nuRatioConsistencia.compareTo(nuValorNumerico) > 0) {
							if (msjExcepcionControlada != null) {
								msjExcepcionControlada = String.format(
										"%s, no se puede asignar el paso porque el cálculo del ratio del grupo %s no es aceptable",
										msjExcepcionControlada, pgimCfgGrupoRiesgoDTO.getNoGrupoRiesgo());
							} else {
								msjExcepcionControlada = String.format(
										"No se puede asignar el paso porque el cálculo del ratio del grupo %s no es aceptable",
										pgimCfgGrupoRiesgoDTO.getNoGrupoRiesgo());
							}
						}

					} else if (pgimValorParametroDTO.getDeValorAlfanum() != null) {
						BigDecimal deValorAlfanum = new BigDecimal(pgimValorParametroDTO.getDeValorAlfanum())
								.multiply(new BigDecimal(100));

						BigDecimal nuValorNumerico = pgimValorParametroDTO.getNuValorNumerico()
								.multiply(new BigDecimal(100));

						if (cantidadFactores >= deValorAlfanum.intValue()
								&& cantidadFactores <= nuValorNumerico.intValue()) {

							if (nuRatioConsistencia.compareTo(nuValorNumerico) > 0) {
								if (msjExcepcionControlada != null) {
									msjExcepcionControlada = String.format(
											"%s, no se puede asignar el paso porque el cálculo del ratio del grupo %s no es aceptable",
											msjExcepcionControlada, pgimCfgGrupoRiesgoDTO.getNoGrupoRiesgo());
								} else {
									msjExcepcionControlada = "No se puede asignar el paso porque el cálculo del ratio del factor no es aceptable";
								}
							}

						}
					}

				}
			}
		}

		if (msjExcepcionControlada != null) {
			throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}
	}

	@Override
	public void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces,
			PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {

		PgimConfiguraRiesgo pgimConfiguraRiesgo = this.configuraRiesgoRepository
				.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

		Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
		Long nuevoEstadio = null;

		if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REGISTRARCONF_APROBARCONF) ||
				idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_APROBARCONF_CONFAPROBADA)) {

			Integer cantidadReglas = this.configuraReglaRepository
					.obtenerCantidadReglas(pgimConfiguraRiesgo.getIdConfiguraRiesgo());

			if (cantidadReglas == 0) { // STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
				throw new PgimException(TipoResultado.WARNING, "No se puede asignar el paso porque la configuración debe de tener al menos una regla registrada");
			}

			// validación de existencia de factores tecnicos y de gestion
			List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTOTecnico = this.listarCfgGrupoRiesgo(pgimConfiguraRiesgo.getIdConfiguraRiesgo(), ConstantesUtil.PARAM_GRUPO_RIESGO_TECNICO);
			List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTOGestion = this.listarCfgGrupoRiesgo(pgimConfiguraRiesgo.getIdConfiguraRiesgo(), ConstantesUtil.PARAM_GRUPO_RIESGO_GESTION);
			
			if (lPgimCfgGrupoRiesgoDTOTecnico.size() == 0) { 
				throw new PgimException(TipoResultado.WARNING, "No se puede asignar el paso porque la configuración debe de tener al menos un factor técnico registrado");
			}
			if (lPgimCfgGrupoRiesgoDTOGestion.size() == 0) { 
				throw new PgimException(TipoResultado.WARNING, "No se puede asignar el paso porque la configuración debe de tener al menos un factor de gestión registrado");
			}

			// validación: Todas especificaciones de los factores deben estar completas
			Integer cantidadCfgNivelRiesgoEspecNulos = this.cfgNivelRiesgoRepository .obtenerCantidadCfgNivelRiesgoEspecNulos(pgimConfiguraRiesgo.getIdConfiguraRiesgo());
			if (cantidadCfgNivelRiesgoEspecNulos > 0) { 
				throw new PgimException(TipoResultado.WARNING, "No se puede asignar el paso porque la configuración debe de tener registradas todas las especificaciones de los factores");
			}
			
			// validación: Debe existir al menos un factor técnico marcado con "Afectado por gestión de empresa minera"
			Integer cantidadCfgFact = this.cfgGrupoRiesgoRepository.obtenerCantidadFactTecFlAfectadoGestion(pgimConfiguraRiesgo.getIdConfiguraRiesgo(), ConstantesUtil.PARAM_GRUPO_RIESGO_TECNICO);
			if (cantidadCfgFact == 0) { 
				throw new PgimException(TipoResultado.WARNING, "No se puede asignar el paso porque la configuración debe existir al menos un factor técnico marcado con \"Afectado por gestión de empresa minera\"");
			}

			if (pgimConfiguraRiesgo.getTipoConfiguracionRiesgo().getIdValorParametro().equals(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.TIPO_CFG_RIESGO_FISCALIZACION.toString()))) {
				// Se valida que no existan otras configuraciones de riesgo cuyas reglas se
				// traslapen con la presente configuración.
				List<PgimConfiguraReglaDTO> lPgimConfiguraReglaDTO = this.configuraReglaRepository
						.obtenerTraslapesOtrasConfiguraciones(pgimConfiguraRiesgo.getIdConfiguraRiesgo(), EValorParametro.ESCFG_APRBDA.toString(), EValorParametro.TIPO_CFG_RIESGO_FISCALIZACION.toString());
				int cantidadReglasTraslapadas = lPgimConfiguraReglaDTO.size();

				Long idConfiguraRiesgoAnterior = null;
				Long idConfiguraRiesgoActual = null;

				int contadorRegla = 0;

				List<Long> lIdConfiguraRiesgoOtras = new ArrayList<Long>();

				if (cantidadReglasTraslapadas > 0) {
					String mensajeExcepcionControlada = null;

					if (cantidadReglasTraslapadas == 1) {
						mensajeExcepcionControlada = String.format(
								"No se puede asignar el paso porque existe una regla que se traslapa con alguna regla de esta configuración, a saber: <br /><br /><br />",
								cantidadReglasTraslapadas);
					} else {
						mensajeExcepcionControlada = String.format(
								"No se puede asignar el paso porque existen %d reglas que se traslapan con alguna regla de esta configuración, a saber: <br /><br /><br />",
								cantidadReglasTraslapadas);
					}

					for (PgimConfiguraReglaDTO pgimConfiguraReglaDTO : lPgimConfiguraReglaDTO) {
						idConfiguraRiesgoActual = pgimConfiguraReglaDTO.getIdConfiguraRiesgo();

						if (!idConfiguraRiesgoActual.equals(idConfiguraRiesgoAnterior)) {
							lIdConfiguraRiesgoOtras.add(idConfiguraRiesgoActual);

							if (idConfiguraRiesgoAnterior != null) {
								mensajeExcepcionControlada = mensajeExcepcionControlada + "<br /><br />";
							}

							mensajeExcepcionControlada = mensajeExcepcionControlada + String
									.format("<b>Configuración: CFGR-%s. %s</b>%s", idConfiguraRiesgoActual, pgimConfiguraReglaDTO.getDescNoConfiguracion(), "<br /><br />");
							idConfiguraRiesgoAnterior = idConfiguraRiesgoActual;
						}

						contadorRegla++;

						mensajeExcepcionControlada = mensajeExcepcionControlada + String.format(
								"<small><i>%d. División supervisora = <b>%s</b> Y Método de minado = <b>%s</b>; Situación = <b>%s</b>; Tipo de actividad = <b>%s</b>; Estado = <b>%s</b></i></small><br />",
								contadorRegla,
								pgimConfiguraReglaDTO.getDescNoDivisionSupervisora(),
								pgimConfiguraReglaDTO.getDescNoMetodoMinado(),
								pgimConfiguraReglaDTO.getDescNoSituacion(),
								pgimConfiguraReglaDTO.getDescNoTipoActividad(),
								pgimConfiguraReglaDTO.getDescNoEstado());
					}

					throw new PgimException(TipoResultado.WARNING, mensajeExcepcionControlada);
				}

				if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_APROBARCONF_CONFAPROBADA)) {
					PgimConfiguraRiesgo pgimConfiguraRiesgoOtra = null;

					// Entonces se procede a actualizar el resto de configuraciones como no activas
					// para la fiscalización.
					for (Long idConfiguraRiesgo : lIdConfiguraRiesgoOtras) {
						pgimConfiguraRiesgoOtra = this.configuraRiesgoRepository.findById(idConfiguraRiesgo)
								.orElse(null);

						Long isTipoConfigR = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.TIPO_CFG_RIESGO_HISTORICO.toString());
						pgimConfiguraRiesgoOtra.setTipoConfiguracionRiesgo(new PgimValorParametro());
						pgimConfiguraRiesgoOtra.getTipoConfiguracionRiesgo().setIdValorParametro(isTipoConfigR);
						pgimConfiguraRiesgoOtra.setEsRegistro(ConstantesUtil.IND_ACTIVO);
						pgimConfiguraRiesgoOtra.setFeActualizacion(auditoriaDTO.getFecha());
						pgimConfiguraRiesgoOtra.setUsActualizacion(auditoriaDTO.getUsername());
						pgimConfiguraRiesgoOtra.setIpActualizacion(auditoriaDTO.getTerminal());

						this.configuraRiesgoRepository.save(pgimConfiguraRiesgoOtra);
					}

					// Configuramos el que quedará como nuevo para usar para la fiscalizaciòn.
					Long isTipoConfigR = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.TIPO_CFG_RIESGO_FISCALIZACION.toString());
					pgimConfiguraRiesgo.setTipoConfiguracionRiesgo(new PgimValorParametro());
					pgimConfiguraRiesgo.getTipoConfiguracionRiesgo().setIdValorParametro(isTipoConfigR);
					pgimConfiguraRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimConfiguraRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
					pgimConfiguraRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
					pgimConfiguraRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

					this.configuraRiesgoRepository.save(pgimConfiguraRiesgo);
				}
			}
		}

		if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REGISTRARCONF_APROBARCONF)) {
			nuevoEstadio = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ESCFG_PRA_APRBAR.toString());

		} else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_APROBARCONF_CONFAPROBADA)) {
			nuevoEstadio = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ESCFG_APRBDA.toString());

		} else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REGISTRARCONF_CANCELARCONF)) {
			nuevoEstadio = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ESCFG_PRA_CNCLAR.toString());

		} else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_CANCELARCONF_CONFCANCELADA)) {
			nuevoEstadio = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ESCFG_CNCLDA.toString());

		} else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_APROBARCONF_REGISTRARCONF)) {
			nuevoEstadio = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ESCFG_OBSRVDA.toString());

		} else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_CONFAPROBADA_APROBARCONF)) {
			nuevoEstadio = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ESCFG_OBSRVDA.toString());

		} else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_APROBARCONF_CONFARCHIVADA)) {
			nuevoEstadio = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ESCFG_ARCHVDA.toString());
		}

		if (nuevoEstadio != null) {
			PgimValorParametro tipoEstadoConfiguracion = new PgimValorParametro();
			tipoEstadoConfiguracion.setIdValorParametro(nuevoEstadio);
			pgimConfiguraRiesgo.setTipoEstadoConfiguracion(tipoEstadoConfiguracion);

			pgimConfiguraRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
			pgimConfiguraRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
			pgimConfiguraRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

			this.configuraRiesgoRepository.save(pgimConfiguraRiesgo);
		}
	}

	@Override
	public List<PgimConfiguraReglaDTO> listarReglasRiesgoPorConfiguracion(Long idConfiguraRiesgo) {
		return configuraReglaRepository.listarReglasRiesgoPorConfiguracion(idConfiguraRiesgo);
	}

	// PGIM 5007 - pjimenez
	@Override
	public List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgo(Long idConfiguraRiesgo, Long idGrupoRiesgo) {
		return this.cfgGrupoRiesgoRepository.listarCfgGrupoRiesgoPorConfiguracionyGrupo(idConfiguraRiesgo,
				idGrupoRiesgo);
	}

	// PGIM-5008 - pjimenez
	@Transactional(readOnly = false)
	@Override
	public PgimCfgGrupoRiesgoDTO crearCfgGrupoRiesgo(PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO,
			AuditoriaDTO auditoriaDTO) {

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo = new PgimCfgGrupoRiesgo();

		PgimGrupoRiesgo pgimGrupoRiesgo = new PgimGrupoRiesgo();
		pgimGrupoRiesgo.setIdGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getIdGrupoRiesgo());
		pgimCfgGrupoRiesgo.setPgimGrupoRiesgo(pgimGrupoRiesgo);

		PgimConfiguraRiesgo pgimConfiguraRiesgo = new PgimConfiguraRiesgo();
		pgimConfiguraRiesgo.setIdConfiguraRiesgo(pgimCfgGrupoRiesgoDTO.getIdConfiguraRiesgo());
		pgimCfgGrupoRiesgo.setPgimConfiguraRiesgo(pgimConfiguraRiesgo);

		pgimCfgGrupoRiesgo.setNuCalificacionGrupo(null);
		pgimCfgGrupoRiesgo.setNuNmax(null);
		pgimCfgGrupoRiesgo.setNuIndiceConsistencia(null);
		pgimCfgGrupoRiesgo.setNuRatioConsistencia(null);
		pgimCfgGrupoRiesgo.setNuIndConsistenciaAleatoria(null);

		pgimCfgGrupoRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimCfgGrupoRiesgo.setFeCreacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgo.setUsCreacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgo.setIpCreacion(auditoriaDTO.getTerminal());

		pgimCfgGrupoRiesgo.setNoGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getNoGrupoRiesgo());
		pgimCfgGrupoRiesgo.setDeGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getDeGrupoRiesgo());
		pgimCfgGrupoRiesgo.setPcFactorCorreccion(pgimCfgGrupoRiesgoDTO.getPcFactorCorreccion());

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoCreado = cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgo);

		PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTOCreado = this
				.obtenerCfgGrupoRiesgoPorId(pgimCfgGrupoRiesgoCreado.getIdCfgGrupoRiesgo());

		return pgimCfgGrupoRiesgoDTOCreado;
	}

	// PGIM-5008 - pjimenez
	@Override
	public PgimCfgGrupoRiesgoDTO obtenerCfgGrupoRiesgoPorId(Long idCfgGrupoRiesgo) {
		return this.cfgGrupoRiesgoRepository.obtenerCfgGrupoRiesgoPorId(idCfgGrupoRiesgo);
	}

	// PGIM-5009 - pjimenez
	@Override
	public List<PgimCfgFactorRiesgoDTO> listarCfgFactorTecnicoPorCfgGrupo(Long idCfgGrupoRiesgo) {
		return this.cfgFactorRiesgoRepository.listarCfgFactorTecnicoGestion(idCfgGrupoRiesgo);
	}

	// PGIM-5009 - pjimenez
	@Override
	public PgimCfgGrupoRiesgoDTO obtenerCalculoRatioConsistenciaPorCfgGrupo(Long idCfgGrupoRiesgo) {
		return this.cfgGrupoRiesgoRepository.obtenerCalculoRatioConsistenciaPorCfgGrupo(idCfgGrupoRiesgo);
	}

	// PGIM-5009 - pjimenez
	@Override
	public List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesTCPorCfgGrupo(Long idCfgGrupoRiesgo) {
		return this.cfgMatrizParesRepository.obtenerCfgMatrizParesTCPorCfgGrupo(idCfgGrupoRiesgo);
	}

	// PGIM-5009 - pjimenez
	@Override
	public List<PgimCfgMatrizParesDTO> obtenerCfgMatrizNormalizadaTCPorCfgGrupo(Long idCfgGrupoRiesgo) {
		return this.cfgMatrizParesRepository.obtenerCfgMatrizNormalizadaTCPorCfgGrupo(idCfgGrupoRiesgo);
	}

	// PGIM-5009 - pjimenez
	@Override
	public PgimCfgGrupoRiesgo cfgGrupoRiesgoFindById(Long idCfgGrupoRiesgo) {
		return this.cfgGrupoRiesgoRepository.findById(idCfgGrupoRiesgo).orElse(null);
	}

	// PGIM-5009 - pjimenez
	@Transactional(readOnly = false)
	@Override
	public PgimCfgGrupoRiesgoDTO modificarCfgGrupoRiesgo(PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo,
			AuditoriaDTO auditoriaDTO) {

		pgimCfgGrupoRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());
		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoModificado = this.cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgo);

		PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTOModificado = this
				.obtenerCfgGrupoRiesgoPorId(pgimCfgGrupoRiesgoModificado.getIdCfgGrupoRiesgo());

		return pgimCfgGrupoRiesgoDTOModificado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimConfiguraReglaDTO crearReglaRiesgo(PgimConfiguraReglaDTO pgimConfiguraReglaDTO,
			AuditoriaDTO auditoriaDTO) {

		PgimConfiguraRegla pgimConfiguraRegla = new PgimConfiguraRegla();

		PgimConfiguraRiesgo pgimConfiguraRiesgo = new PgimConfiguraRiesgo();
		pgimConfiguraRiesgo.setIdConfiguraRiesgo(pgimConfiguraReglaDTO.getIdConfiguraRiesgo());
		pgimConfiguraRegla.setPgimConfiguraRiesgo(pgimConfiguraRiesgo);

		pgimConfiguraRegla.setDivisionSupervisora(new PgimValorParametro());
		pgimConfiguraRegla.getDivisionSupervisora()
				.setIdValorParametro(pgimConfiguraReglaDTO.getIdDivisionSupervisora());

		pgimConfiguraRegla.setMetodoMinado(new PgimValorParametro());
		pgimConfiguraRegla.getMetodoMinado().setIdValorParametro(pgimConfiguraReglaDTO.getIdMetodoMinado());

		pgimConfiguraRegla.setSituacion(new PgimValorParametro());
		pgimConfiguraRegla.getSituacion().setIdValorParametro(pgimConfiguraReglaDTO.getIdSituacion());

		pgimConfiguraRegla.setTipoActividad(new PgimValorParametro());
		pgimConfiguraRegla.getTipoActividad().setIdValorParametro(pgimConfiguraReglaDTO.getIdTipoActividad());

		pgimConfiguraRegla.setEstado(new PgimValorParametro());
		pgimConfiguraRegla.getEstado().setIdValorParametro(pgimConfiguraReglaDTO.getIdEstado());

		pgimConfiguraRegla.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimConfiguraRegla.setFeCreacion(auditoriaDTO.getFecha());
		pgimConfiguraRegla.setUsCreacion(auditoriaDTO.getUsername());
		pgimConfiguraRegla.setIpCreacion(auditoriaDTO.getTerminal());

		PgimConfiguraRegla pgimConfiguraReglaCreada = this.configuraReglaRepository.save(pgimConfiguraRegla);

		PgimConfiguraReglaDTO pgimConfiguraReglaDTOResultado = this.configuraReglaRepository
				.obtenerReglaPorId(pgimConfiguraReglaCreada.getIdConfiguraRegla());

		return pgimConfiguraReglaDTOResultado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimConfiguraReglaDTO modificarReglaRiesgo(PgimConfiguraReglaDTO pgimConfiguraReglaDTO,
			PgimConfiguraRegla pgimConfiguraRegla, AuditoriaDTO auditoriaDTO) {

		pgimConfiguraRegla.setDivisionSupervisora(new PgimValorParametro());
		pgimConfiguraRegla.getDivisionSupervisora()
				.setIdValorParametro(pgimConfiguraReglaDTO.getIdDivisionSupervisora());

		pgimConfiguraRegla.setMetodoMinado(new PgimValorParametro());
		pgimConfiguraRegla.getMetodoMinado().setIdValorParametro(pgimConfiguraReglaDTO.getIdMetodoMinado());

		pgimConfiguraRegla.setSituacion(new PgimValorParametro());
		pgimConfiguraRegla.getSituacion().setIdValorParametro(pgimConfiguraReglaDTO.getIdSituacion());

		pgimConfiguraRegla.setTipoActividad(new PgimValorParametro());
		pgimConfiguraRegla.getTipoActividad().setIdValorParametro(pgimConfiguraReglaDTO.getIdTipoActividad());

		pgimConfiguraRegla.setEstado(new PgimValorParametro());
		pgimConfiguraRegla.getEstado().setIdValorParametro(pgimConfiguraReglaDTO.getIdEstado());

		pgimConfiguraRegla.setFeActualizacion(auditoriaDTO.getFecha());
		pgimConfiguraRegla.setUsActualizacion(auditoriaDTO.getUsername());
		pgimConfiguraRegla.setIpActualizacion(auditoriaDTO.getTerminal());

		this.configuraReglaRepository.save(pgimConfiguraRegla);

		PgimConfiguraReglaDTO pgimConfiguraReglaDTOResultado = this.configuraReglaRepository
				.obtenerReglaPorId(pgimConfiguraRegla.getIdConfiguraRegla());

		return pgimConfiguraReglaDTOResultado;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarReglaRiesgo(PgimConfiguraRegla pgimConfiguraRegla, AuditoriaDTO auditoriaDTO) {

		pgimConfiguraRegla.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimConfiguraRegla.setFeActualizacion(auditoriaDTO.getFecha());
		pgimConfiguraRegla.setUsActualizacion(auditoriaDTO.getUsername());
		pgimConfiguraRegla.setIpActualizacion(auditoriaDTO.getTerminal());

		this.configuraReglaRepository.save(pgimConfiguraRegla);
	}

	@Override
	public PgimConfiguraRegla configuraReglafindById(Long idreglaRiesgo) {
		return this.configuraReglaRepository.findById(idreglaRiesgo).orElse(null);
	};

	@Override
	public Integer contarCantidadReglasRiesgo(Long idConfiguraRiesgo, Long idConfiguraRegla, Long idDivisionSupervisora,
			Long idMetodoMinado, Long idSituacion, Long idTipoActividad, Long idEstado) {
		Integer cantidad = configuraReglaRepository.contarCantidadReglasRiesgo(idConfiguraRiesgo, idConfiguraRegla,
				idDivisionSupervisora, idMetodoMinado, idSituacion, idTipoActividad, idEstado);

		return cantidad;
	};

	// PGIM-5010 - pjimenez
	@Override
	@Transactional(readOnly = false)
	public void eliminarCfgGrupoRiesgo(PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoActual, AuditoriaDTO auditoriaDTO) {
		pgimCfgGrupoRiesgoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimCfgGrupoRiesgoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimCfgGrupoRiesgoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimCfgGrupoRiesgoActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.cfgGrupoRiesgoRepository.save(pgimCfgGrupoRiesgoActual);

	}
	
	@Override
    public Integer contarConfiguraRiesgoPendientes(AuditoriaDTO auditoriaDTO) {
    	
    	Integer cantidadPendientes = this.configuraRiesgoRepository.contarConfiguraRiesgoPendientes(auditoriaDTO.getUsername()); 
    	
    	return cantidadPendientes;    	
    }
}
