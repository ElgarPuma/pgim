package pe.gob.osinergmin.pgim.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.dtos.Ranking;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgGrupoRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgNivelRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimFactorRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUm;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUmFactor;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUmGrupo;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.CfgFactorRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.CfgGrupoRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.CfgNivelRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.ConfiguraReglaRepository;
import pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.NivelRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmFactorRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.RankingRiesgoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Ranking riesgo
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */

@Service
@Slf4j
@Transactional(readOnly = true)
public class RankingRiesgoServiceImpl implements RankingRiesgoService {

	@Autowired
	private RankingRiesgoRepository rankingRiesgoRepository;

	@Autowired
	private ConfiguraRiesgoRepository configuraRiesgoRepository;

	@Autowired
	private InstanciaProcesService instanciaProcesService;

	@Autowired
	private InstanciaProcesRepository instanciaProcesRepository;

	@Autowired
	private FlujoTrabajoService flujoTrabajoService;

	@Autowired
	private RankingUmFactorRepository rankingUmFactorRepository;

	@Autowired
	private CfgNivelRiesgoRepository cfgNivelRiesgoRepository;

	@Autowired
	private NivelRiesgoRepository nivelRiesgoRepository;

	@Autowired
	private RankingUmGrupoRepository rankingUmGrupoRepository;

	@Autowired
	private RankingUmRepository rankingUmRepository;

	@Autowired
	private CfgGrupoRiesgoRepository cfgGrupoRiesgoRepository;

	@Autowired
	private ConfiguraReglaRepository configuraReglaRepository;

	@Autowired
	private UnidadMineraRepository unidadMineraRepository;

	@Autowired
	private CfgFactorRiesgoRepository cfgFactorRiesgoRepository;

	@Autowired
	private RankingUmAuxRepository rankingUmAuxRepository;

	@Autowired
	private RankingSupervisionRepository rankingSupervisionRepository;

	@Autowired
	private SupervisionRepository supervisionRepository;

	@Autowired
	private ValorParametroRepository valorParametroRepository;

	@Autowired
	private RankingUmServiceImpl rankingUmServiceImpl;

	@Override
	public Page<PgimRankingRiesgoDTO> filtrar(PgimRankingRiesgoDTO filtroRankingRiesgo, Pageable paginador,
			AuditoriaDTO auditoriaDTO) {

		if (filtroRankingRiesgo.getDescFlagMisAsignaciones().equals("1")) {
			filtroRankingRiesgo.setUsuarioAsignado(auditoriaDTO.getUsername());
		}

		// Para usuarios con tareas enviadas
		if (filtroRankingRiesgo.getDescFlagMisAsignaciones().equals("2")) {
			filtroRankingRiesgo.setNoUsuarioOrigen(auditoriaDTO.getUsername());
		}

		if (filtroRankingRiesgo.getTextoBusqueda().equals("")) {
			filtroRankingRiesgo.setTextoBusqueda(null);
		}
		
		Page<PgimRankingRiesgoDTO> pPgimRankingRiesgoDTO = this.rankingRiesgoRepository.listarRankingRiesgo(
				filtroRankingRiesgo.getDescFeGeneracionRankingAnio(), 
				filtroRankingRiesgo.getDescIdEspecialidad(),
				filtroRankingRiesgo.getDescIdTipoEstadoConfiguracion(), 
				filtroRankingRiesgo.getDescIdFaseActual(),
				filtroRankingRiesgo.getDescIdPasoActual(), 
				filtroRankingRiesgo.getDescFlagMisAsignaciones(),
				filtroRankingRiesgo.getNoUsuarioOrigen(), 
				filtroRankingRiesgo.getUsuarioAsignado(), 
				filtroRankingRiesgo.getDescNoPersonaAsignada(),
				filtroRankingRiesgo.getDescIdTipoConfiguracionRiesgo(),
				filtroRankingRiesgo.getDescIdTipoPeriodo(),
				filtroRankingRiesgo.getDescNuAnioPeriodo(),
				filtroRankingRiesgo.getTextoBusqueda(), 
				paginador);
				
		return pPgimRankingRiesgoDTO;
	}

	@Override
	public List<PgimRankingRiesgoDTO> listarPorNuExpedienteSiged(String palabra) {
		List<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTO = this.rankingRiesgoRepository
				.listarPorNuExpedienteSiged(palabra);

		return lPgimRankingRiesgoDTO;
	}

	@Override
	public PgimRankingRiesgoDTO obtenerRankingRiesgoPorId(Long idRankingRiesgo) {
		return this.rankingRiesgoRepository.obtenerRankingRiesgoPorId(idRankingRiesgo);
	}

	@Override
	public List<PgimConfiguraRiesgoDTO> obtenerConfiguracionParaAsignacion() {
		List<PgimConfiguraRiesgoDTO> lPgimConfiguraRiesgoDTO = this.configuraRiesgoRepository
				.obtenerConfiguracionesParaAsignacion(EValorParametro.ESCFG_APRBDA.toString(), EValorParametro.ESCFG_ARCHVDA.toString());
		return lPgimConfiguraRiesgoDTO;
	}

	/**
	 * Permite asignar un ranking, usado en el dialogo Asignar Ranking de Riesgos
	 * 
	 * @param pgimRankingRiesgoDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimRankingRiesgoDTO asignarRanking(PgimRankingRiesgoDTO pgimRankingRiesgoDTO, AuditoriaDTO auditoriaDTO)
			throws Exception {

		// Se crea el ranking
		PgimRankingRiesgoDTO pgimRankingRiesgoDTOCreado = this.crearRanking(pgimRankingRiesgoDTO, auditoriaDTO);

		// Se asegura la instancia del proceso
		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService.asegurarInstanciasProceso(
				ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS, pgimRankingRiesgoDTOCreado.getIdRankingRiesgo(),
				auditoriaDTO);

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

		PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository
				.findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

		// Se actualiza la instancia del proceso en el registro del ranking
		this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);

		// Se crea la asignación
		pgimRankingRiesgoDTO.setIdRankingRiesgo(pgimRankingRiesgoDTOCreado.getIdRankingRiesgo());

		PgimInstanciaPasoDTO pgimInstanciaPasoDTO = new PgimInstanciaPasoDTO();
		pgimInstanciaPasoDTO.setIdRelacionPaso(pgimRankingRiesgoDTO.getDescIdRelacionPaso());
		pgimInstanciaPasoDTO.setDescIdPersonalOsiDestino(pgimRankingRiesgoDTO.getDescIdPersonalOsi());
		pgimInstanciaPasoDTO.setDeMensaje(pgimRankingRiesgoDTO.getDescDeMensaje());

		PgimInstanciaPasoDTO pgimInstanciaPasoDTOObtenido = this.flujoTrabajoService.crearInstanciaPasoInicial(pgimInstanciaProcesActual, pgimInstanciaPasoDTO,
				auditoriaDTO);

		// obtener el id de la instancia paso
		pgimRankingRiesgoDTOCreado.setDescIdInstanciaPaso(pgimInstanciaPasoDTOObtenido.getIdInstanciaPaso());

		// Generar data del ranking
		this.generarDataRanking(pgimRankingRiesgoDTOCreado, auditoriaDTO);

		return pgimRankingRiesgoDTOCreado;
	}

	@Transactional(readOnly = false)
	private PgimRankingRiesgoDTO crearRanking(PgimRankingRiesgoDTO pgimRankingRiesgoDTO, AuditoriaDTO auditoriaDTO) {

		PgimRankingRiesgo pgimRankingRiesgo = new PgimRankingRiesgo();

		PgimConfiguraRiesgo pgimConfiguraRiesgo = new PgimConfiguraRiesgo();
		pgimConfiguraRiesgo.setIdConfiguraRiesgo(pgimRankingRiesgoDTO.getIdConfiguraRiesgo());
		pgimRankingRiesgo.setPgimConfiguraRiesgo(pgimConfiguraRiesgo);

		PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = this.configuraRiesgoRepository
				.obtenerConfiguracionesPorId(pgimRankingRiesgoDTO.getIdConfiguraRiesgo());

		pgimRankingRiesgo.setNoRanking(pgimConfiguraRiesgoDTO.getNoConfiguracion());

		pgimRankingRiesgo.setDeRanking(pgimRankingRiesgoDTO.getDeRanking());
		pgimRankingRiesgo.setFeGeneracionRanking(pgimRankingRiesgoDTO.getFeGeneracionRanking());

		pgimRankingRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimRankingRiesgo.setFeCreacion(auditoriaDTO.getFecha());
		pgimRankingRiesgo.setUsCreacion(auditoriaDTO.getUsername());
		pgimRankingRiesgo.setIpCreacion(auditoriaDTO.getTerminal());

		PgimRankingRiesgo pgimRankingRiesgoCreado = this.rankingRiesgoRepository.save(pgimRankingRiesgo);

		PgimRankingRiesgoDTO pgimRankingRiesgoDTOCreado = null;

		try {
			pgimRankingRiesgoDTOCreado = this.obtenerRankingRiesgoPorId(pgimRankingRiesgoCreado.getIdRankingRiesgo());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		return pgimRankingRiesgoDTOCreado;
	}

	@Override
	public List<PgimRankingUmFactorDTO> listarRankingUmFactor(Long idRankingUmGrupo, Long idGrupoRiesgo) {
		List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = rankingUmFactorRepository
				.listarRankingUmFactorPorRankingGrupoUmAndGrupoRiesgo(idRankingUmGrupo, idGrupoRiesgo);

		return lPgimRankingUmFactorDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public Ranking modificarRankingUmFactor(Ranking ranking, AuditoriaDTO auditoriaDTO) {

		List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = ranking.getlPgimRankingUmFactorDTO();

		int cantRankingUmFactor = 0;
		Long idRankingUmGrupo = 0L;

		if (ranking.getIdRankingUmGrupo() != null) {
			idRankingUmGrupo = ranking.getIdRankingUmGrupo();
		}

		BigDecimal totalMoPuntaje = new BigDecimal(0);
		PgimRankingUmFactor pgimRankingUmFactor = null;

		for (PgimRankingUmFactorDTO pgimRankingUmFactorDTO : lPgimRankingUmFactorDTO) {

			idRankingUmGrupo = pgimRankingUmFactorDTO.getIdRankingUmGrupo();

			pgimRankingUmFactor = this.rankingUmFactorRepository
					.findById(pgimRankingUmFactorDTO.getIdRankingUmFactor()).orElse(null);

			if (pgimRankingUmFactorDTO.getIdCfgNivelRiesgo() != null) {
				cantRankingUmFactor++;

				// ID_CFG_NIVEL_RIESGO
				PgimCfgNivelRiesgo pgimCfgNivelRiesgo = this.cfgNivelRiesgoRepository
						.findById(pgimRankingUmFactorDTO.getIdCfgNivelRiesgo()).orElse(null);
				pgimRankingUmFactor.setPgimCfgNivelRiesgo(pgimCfgNivelRiesgo);

				// MO_CALIFICACION
				pgimRankingUmFactor.setMoCalificacion(pgimRankingUmFactorDTO.getMoCalificacion());

				// MO_PUNTAJE
				pgimRankingUmFactor.setMoPuntaje(pgimRankingUmFactorDTO.getMoPuntaje());

				totalMoPuntaje = totalMoPuntaje.add(pgimRankingUmFactorDTO.getMoPuntaje());
			}

			// CM_CALIFICACION
			pgimRankingUmFactor.setCmCalificacion(pgimRankingUmFactorDTO.getCmCalificacion());

			pgimRankingUmFactor.setFeActualizacion(auditoriaDTO.getFecha());
			pgimRankingUmFactor.setUsActualizacion(auditoriaDTO.getUsername());
			pgimRankingUmFactor.setIpActualizacion(auditoriaDTO.getTerminal());

			this.rankingUmFactorRepository.save(pgimRankingUmFactor);
		}

		PgimRankingUmGrupo pgimRankingUmGrupo = this.rankingUmGrupoRepository.findById(idRankingUmGrupo).orElse(null);

		if (cantRankingUmFactor == lPgimRankingUmFactorDTO.size()) {
			PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo = this.cfgGrupoRiesgoRepository
					.findById(pgimRankingUmGrupo.getPgimCfgGrupoRiesgo().getIdCfgGrupoRiesgo()).orElse(null);

			BigDecimal totalMoPuntajeCorregido = totalMoPuntaje.multiply(pgimCfgGrupoRiesgo.getPcFactorCorreccion());
			pgimRankingUmGrupo.setMoPuntaje(totalMoPuntajeCorregido);

			pgimRankingUmGrupo.setFeActualizacion(auditoriaDTO.getFecha());
			pgimRankingUmGrupo.setUsActualizacion(auditoriaDTO.getUsername());
			pgimRankingUmGrupo.setIpActualizacion(auditoriaDTO.getTerminal());

			this.rankingUmGrupoRepository.save(pgimRankingUmGrupo);
		} else {

			pgimRankingUmGrupo.setMoPuntaje(null);

			pgimRankingUmGrupo.setFeActualizacion(auditoriaDTO.getFecha());
			pgimRankingUmGrupo.setUsActualizacion(auditoriaDTO.getUsername());
			pgimRankingUmGrupo.setIpActualizacion(auditoriaDTO.getTerminal());

			this.rankingUmGrupoRepository.save(pgimRankingUmGrupo);
		}

		List<PgimRankingUmGrupoDTO> lPgimRankingUmGrupoDTO;

		if (pgimRankingUmGrupo.getPgimRankingUm() != null) {
			lPgimRankingUmGrupoDTO = this.rankingUmGrupoRepository
					.listarRankingUmGrupoPorRankingUm(pgimRankingUmGrupo.getPgimRankingUm().getIdRankingUm());
		} else {
			lPgimRankingUmGrupoDTO = this.rankingUmGrupoRepository.listarRankingUmGrupoPorIdRankingSuper(
					pgimRankingUmGrupo.getPgimRankingSupervision().getIdRankingSupervision());
		}

		int contadorMoPuntaje = 0;

		BigDecimal moPuntajeTecnico = new BigDecimal(0);
		BigDecimal moPuntajeMaximoTecnico = new BigDecimal(0);
		PgimRankingUmGrupoDTO pgimRankingUmGrupoDTOMaximoTecnico = null;
		BigDecimal valorAlfa = new BigDecimal(0);

		BigDecimal moPuntajeGestion = new BigDecimal(0);
		BigDecimal moPuntajeMaximoGestion = new BigDecimal(0);
		PgimRankingUmGrupoDTO pgimRankingUmGrupoDTOMaximoGestion = null;

		PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo = null;
		for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO : lPgimRankingUmGrupoDTO) {

			if (pgimRankingUmGrupoDTO.getMoPuntaje() != null) {
				contadorMoPuntaje++;

				pgimCfgGrupoRiesgo = this.cfgGrupoRiesgoRepository
						.findById(pgimRankingUmGrupoDTO.getIdCfgGrupoRiesgo()).orElse(null);

				if (pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo().equals(ConstantesUtil.PARAM_GRUPO_RIESGO_TECNICO)) {

					moPuntajeTecnico = pgimRankingUmGrupoDTO.getMoPuntaje();

					if (moPuntajeTecnico.compareTo(moPuntajeMaximoTecnico) >= 0) {
						valorAlfa = pgimCfgGrupoRiesgo.getNuCalificacionGrupo();
						pgimRankingUmGrupoDTOMaximoTecnico = pgimRankingUmGrupoDTO;
						moPuntajeMaximoTecnico = moPuntajeTecnico;
					}
				} else if (pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo()
						.equals(ConstantesUtil.PARAM_GRUPO_RIESGO_GESTION)) {

					moPuntajeGestion = pgimRankingUmGrupoDTO.getMoPuntaje();

					if (moPuntajeGestion.compareTo(moPuntajeMaximoGestion) >= 0) {
						pgimRankingUmGrupoDTOMaximoGestion = pgimRankingUmGrupoDTO;
						moPuntajeMaximoGestion = moPuntajeGestion;
					}
				} else {
					throw new PgimException("error",
							String.format("No se ha implementado este tipo grupo de riesgo, a saber: %s",
									pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo()));
				}

			}
		}

		BigDecimal valorTotalFactores = null;

		if (pgimRankingUmGrupoDTOMaximoTecnico != null) {
			BigDecimal valorFactoresTecnicos = pgimRankingUmGrupoDTOMaximoTecnico.getMoPuntaje().multiply(valorAlfa);

			BigDecimal valorBeta = (new BigDecimal(1)).subtract(valorAlfa);			

			if (pgimRankingUmGrupoDTOMaximoGestion != null) {
				BigDecimal valorFactoresGestion = pgimRankingUmGrupoDTOMaximoGestion.getMoPuntaje().multiply(valorBeta);
				valorTotalFactores = valorFactoresTecnicos.add(valorFactoresGestion);
			}			

			// - Actualizar el valor correspondiente.
			PgimRankingUmGrupo pgimRankingUmGrupoAsegurado;

			for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO : lPgimRankingUmGrupoDTO) {

				pgimRankingUmGrupoAsegurado = this.rankingUmGrupoRepository
						.findById(pgimRankingUmGrupoDTO.getIdRankingUmGrupo()).orElse(null);

				if (pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo().equals(ConstantesUtil.PARAM_GRUPO_RIESGO_TECNICO)) {

					if (pgimRankingUmGrupoDTOMaximoTecnico.getIdRankingUmGrupo()
							.equals(pgimRankingUmGrupoDTO.getIdRankingUmGrupo())) {
						pgimRankingUmGrupoAsegurado.setFlMaximo("1");
						pgimRankingUmGrupoAsegurado.setNuCalificacionGrupo(valorAlfa);
					} else {
						pgimRankingUmGrupoAsegurado.setFlMaximo("0");
						pgimRankingUmGrupoAsegurado.setNuCalificacionGrupo(null);
					}

				} else if (pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo()
						.equals(ConstantesUtil.PARAM_GRUPO_RIESGO_GESTION)
						&& pgimRankingUmGrupoDTOMaximoGestion != null) {

					if (pgimRankingUmGrupoDTOMaximoGestion.getIdRankingUmGrupo()
							.equals(pgimRankingUmGrupoDTO.getIdRankingUmGrupo())) {
						pgimRankingUmGrupoAsegurado.setFlMaximo("1");
						pgimRankingUmGrupoAsegurado.setNuCalificacionGrupo(valorBeta);
					} else {
						pgimRankingUmGrupoAsegurado.setFlMaximo("0");
						pgimRankingUmGrupoAsegurado.setNuCalificacionGrupo(null);
					}
				}

				pgimRankingUmGrupo.setFeActualizacion(auditoriaDTO.getFecha());
				pgimRankingUmGrupo.setUsActualizacion(auditoriaDTO.getUsername());
				pgimRankingUmGrupo.setIpActualizacion(auditoriaDTO.getTerminal());

				this.rankingUmGrupoRepository.save(pgimRankingUmGrupo);
			}
		}

		if (pgimRankingUmGrupo.getPgimRankingUm() != null) {
			PgimRankingUm pgimRankingUm = rankingUmRepository
					.findById(pgimRankingUmGrupo.getPgimRankingUm().getIdRankingUm()).orElse(null);

			if (pgimRankingUm != null) {
				// Obtenemos la lista de grupos de la configuración de riesgos
				List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTO = this.cfgGrupoRiesgoRepository
						.listarCfgGrupoRiesgoPorIdRankingUm(
								pgimRankingUm.getIdRankingUm());

				// Comparamos si la cantidad de grupos con puntaje corresponde con la cantidad
				// de grupos de la configuración
				if (contadorMoPuntaje == lPgimCfgGrupoRiesgoDTO.size()) {
					pgimRankingUm.setMoPuntaje(valorTotalFactores);

					pgimRankingUm.setFeActualizacion(auditoriaDTO.getFecha());
					pgimRankingUm.setUsActualizacion(auditoriaDTO.getUsername());
					pgimRankingUm.setIpActualizacion(auditoriaDTO.getTerminal());

					this.rankingUmRepository.save(pgimRankingUm);
				} else {
					pgimRankingUm.setMoPuntaje(null);

					pgimRankingUm.setFeActualizacion(auditoriaDTO.getFecha());
					pgimRankingUm.setUsActualizacion(auditoriaDTO.getUsername());
					pgimRankingUm.setIpActualizacion(auditoriaDTO.getTerminal());

					this.rankingUmRepository.save(pgimRankingUm);
				}
			}
		} else {
			PgimRankingSupervision pgimRankingSupervision = this.rankingSupervisionRepository
					.findById(pgimRankingUmGrupo.getPgimRankingSupervision().getIdRankingSupervision()).orElse(null);

			if (pgimRankingSupervision != null) {
				// Obtenemos la lista de grupos de la configuración de riesgos
				List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTO = this.cfgGrupoRiesgoRepository
						.listarCfgGrupoRiesgoPorIdRankingSupervision(
								pgimRankingSupervision.getIdRankingSupervision());

				// Comparamos si la cantidad de grupos con puntaje corresponde con la cantidad
				// de grupos de la configuración
				if (contadorMoPuntaje == lPgimCfgGrupoRiesgoDTO.size()) {
					pgimRankingSupervision.setMoPuntaje(valorTotalFactores);

					pgimRankingSupervision.setFeActualizacion(auditoriaDTO.getFecha());
					pgimRankingSupervision.setUsActualizacion(auditoriaDTO.getUsername());
					pgimRankingSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

					this.rankingSupervisionRepository.save(pgimRankingSupervision);
				} else {
					pgimRankingSupervision.setMoPuntaje(null);

					pgimRankingSupervision.setFeActualizacion(auditoriaDTO.getFecha());
					pgimRankingSupervision.setUsActualizacion(auditoriaDTO.getUsername());
					pgimRankingSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

					this.rankingSupervisionRepository.save(pgimRankingSupervision);
				}
			}
		}

		return ranking;
	}

	@Override
	public List<PgimCfgNivelRiesgoDTO> listarCfgNivelRiesgo(Long idCfgGrupoRiesgo) {
		List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgoDTO = this.cfgNivelRiesgoRepository
				.listarCfgNivelRiesgoPorGrupoRiesgo(idCfgGrupoRiesgo);

		return lPgimCfgNivelRiesgoDTO;
	}

	@Override
	public List<PgimNivelRiesgoDTO> listarNivelRiesgo() {

		return this.nivelRiesgoRepository.listarNivelRiesgo();
	}

	@Transactional(readOnly = false)
	@Override
	public PgimRankingRiesgoDTO modificarRankingRiesgo(PgimRankingRiesgoDTO pgimRankingRiesgoDTO,
			PgimRankingRiesgo pgimRankingRiesgo, AuditoriaDTO auditoriaDTO) {
		pgimRankingRiesgo = this.configurarValores(pgimRankingRiesgoDTO, pgimRankingRiesgo);

		pgimRankingRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimRankingRiesgo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimRankingRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimRankingRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimRankingRiesgo pgimRankingRiesgoModificado = this.rankingRiesgoRepository.save(pgimRankingRiesgo);

		PgimRankingRiesgoDTO pgimRankingRiesgoDTOResultado = this
				.obtenerRankingRiesgoPorId(pgimRankingRiesgoModificado.getIdRankingRiesgo());

		return pgimRankingRiesgoDTOResultado;
	}

	@Transactional(readOnly = false)
	private PgimRankingRiesgo configurarValores(PgimRankingRiesgoDTO pgimRankingRiesgoDTO,
			PgimRankingRiesgo pgimRankingRiesgo) {

		pgimRankingRiesgo.setNoRanking(pgimRankingRiesgoDTO.getNoRanking());
		pgimRankingRiesgo.setFeGeneracionRanking(pgimRankingRiesgoDTO.getFeGeneracionRanking());
		pgimRankingRiesgo.setDeRanking(pgimRankingRiesgoDTO.getDeRanking());
		return pgimRankingRiesgo;

	}

	@Override
	public PgimRankingRiesgo getByIdRankingRiesgo(Long idRankingRiesgo) {
		return this.rankingRiesgoRepository.findById(idRankingRiesgo).orElse(null);
	}

	@Override
	public PgimRankingUmGrupoDTO obtenerUnidadMineraPorRankingGrupoUmAndGrupoRiesgo(Long idRankingUmGrupo,
			Long idGrupoRiesgo) {
		PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO = rankingUmGrupoRepository
				.obtenerUnidadMineraPorRankingGrupoUmAndGrupoRiesgo(idRankingUmGrupo, idGrupoRiesgo);
		return pgimRankingUmGrupoDTO;
	}

	@Transactional(readOnly = false)
	private void generarDataRanking(PgimRankingRiesgoDTO pgimRankingRiesgoDTOCreado, AuditoriaDTO auditoriaDTO) {

		try {
			// Obtener configuración
			PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = this.configuraRiesgoRepository
					.obtenerConfiguracionesPorId(pgimRankingRiesgoDTOCreado.getIdConfiguraRiesgo());

			// Obtener regla
			List<PgimConfiguraReglaDTO> lpgimConfiguraReglaDTO = this.configuraReglaRepository
					.obtenerReglasPorConfiguracion(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());

			// Obtener grupos a partir de Configuración riesgo
			List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTO = this.cfgGrupoRiesgoRepository
					.listarCfgGrupoRiesgoPorConfiguracion(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());

			for (PgimConfiguraReglaDTO pgimConfiguraReglaDTO : lpgimConfiguraReglaDTO) {
				// Obtener UM según criterios establecidos la(s)regla(s) de su configuración
				// idDivisionSupervisora - idMetodoMinado - idSituacion - IdTipoActividad - IdEstado
				List<PgimUnidadMineraDTO> lpgimUnidadMineraDTO = this.unidadMineraRepository.listarPorConfiguraRegla(
						pgimConfiguraReglaDTO.getIdSituacion(), pgimConfiguraReglaDTO.getIdDivisionSupervisora(),
						pgimConfiguraReglaDTO.getIdMetodoMinado(), pgimConfiguraReglaDTO.getIdTipoActividad(),
						pgimConfiguraReglaDTO.getIdEstado(), pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo(), pgimRankingRiesgoDTOCreado.getIdRankingRiesgo());

				for (PgimUnidadMineraDTO pgimUnidadMineraDTO : lpgimUnidadMineraDTO) {
					// Crear registros en PGIM_TD_RANKING_UM
					PgimRankingUm pgimRankingUm = new PgimRankingUm();
					PgimRankingRiesgo pgimConfiguraRiesgo = new PgimRankingRiesgo();
					pgimConfiguraRiesgo.setIdRankingRiesgo(pgimRankingRiesgoDTOCreado.getIdRankingRiesgo());
					pgimRankingUm.setPgimRankingRiesgo(pgimConfiguraRiesgo);

					PgimUnidadMinera pgimUnidadMinera = new PgimUnidadMinera();
					pgimUnidadMinera.setIdUnidadMinera(pgimUnidadMineraDTO.getIdUnidadMinera());
					pgimRankingUm.setPgimUnidadMinera(pgimUnidadMinera);
					pgimRankingUm.setTipoInclusionRanking(new PgimValorParametro());
					pgimRankingUm.getTipoInclusionRanking().setIdValorParametro(this.valorParametroRepository
					.obtenerIdValorParametro(EValorParametro.TIPO_AUTOMATICO.toString()));
					pgimRankingUm.setFlActivo(ConstantesUtil.FL_IND_SI);

					pgimRankingUm.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimRankingUm.setFeCreacion(auditoriaDTO.getFecha());
					pgimRankingUm.setUsCreacion(auditoriaDTO.getUsername());
					pgimRankingUm.setIpCreacion(auditoriaDTO.getTerminal());

					PgimRankingUm pgimRankingUmCreado = this.rankingUmRepository.save(pgimRankingUm);

					// Crear registros en PGIM_TD_RANKING_UM_GRUPO
					// A partir de Configuración riesgo

					for (PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO : lPgimCfgGrupoRiesgoDTO) {
						PgimRankingUmGrupo pgimRankingUmGrupo = new PgimRankingUmGrupo();

						PgimRankingUm pgimRankingUmTmp = new PgimRankingUm();
						pgimRankingUmTmp.setIdRankingUm(pgimRankingUmCreado.getIdRankingUm());
						pgimRankingUmGrupo.setPgimRankingUm(pgimRankingUmTmp);

						PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoTmp = new PgimCfgGrupoRiesgo();
						pgimCfgGrupoRiesgoTmp.setIdCfgGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());
						pgimRankingUmGrupo.setPgimCfgGrupoRiesgo(pgimCfgGrupoRiesgoTmp);
						pgimRankingUmGrupo.setFlRegistrar("1");
						pgimRankingUmGrupo.setNuCalificacionGrupo(null);
						pgimRankingUmGrupo.setFlMaximo("0");

						pgimRankingUmGrupo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
						pgimRankingUmGrupo.setFeCreacion(auditoriaDTO.getFecha());
						pgimRankingUmGrupo.setUsCreacion(auditoriaDTO.getUsername());
						pgimRankingUmGrupo.setIpCreacion(auditoriaDTO.getTerminal());

						PgimRankingUmGrupo pgimRankingUmGrupoCreado = this.rankingUmGrupoRepository
								.save(pgimRankingUmGrupo);

						// Crear registros en PGIM_TD_RANKING_UM_FACTOR
						// Obtener factores a partir de la Configuración de factores por grupo riesgo
						List<PgimCfgFactorRiesgoDTO> lpgimCfgFactorRiesgoDTO = this.cfgFactorRiesgoRepository
								.listarCfgFactorRiesgoPorConfiguracion(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());

						for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lpgimCfgFactorRiesgoDTO) {
							PgimRankingUmFactor pgimRankingUmFactor = new PgimRankingUmFactor();

							PgimRankingUmGrupo pgimRankingUmGrupoTmp = new PgimRankingUmGrupo();
							pgimRankingUmGrupoTmp.setIdRankingUmGrupo(pgimRankingUmGrupoCreado.getIdRankingUmGrupo());
							pgimRankingUmFactor.setPgimRankingUmGrupo(pgimRankingUmGrupoTmp);

							PgimFactorRiesgo pgimFactorRiesgo = new PgimFactorRiesgo();
							pgimFactorRiesgo.setIdFactorRiesgo(pgimCfgFactorRiesgoDTO.getIdFactorRiesgo());
							pgimRankingUmFactor.setPgimFactorRiesgo(pgimFactorRiesgo);

							pgimRankingUmFactor.setEsRegistro(ConstantesUtil.IND_ACTIVO);
							pgimRankingUmFactor.setFeCreacion(auditoriaDTO.getFecha());
							pgimRankingUmFactor.setUsCreacion(auditoriaDTO.getUsername());
							pgimRankingUmFactor.setIpCreacion(auditoriaDTO.getTerminal());

							this.rankingUmFactorRepository.save(pgimRankingUmFactor);
						}
					}

					// Completar los valores de los factores de riesgo desde las fiscalizaciones
					// realizadas, para el RANKING_UM generado
					this.completarValoresRiesgoDesdeFiscalizacion(pgimRankingRiesgoDTOCreado, pgimRankingUmCreado,
							auditoriaDTO);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PgimException("error",
					"Ocurrió un error al intentar procesar la generación del ranking de unidades fiscalizadas");
		}

	}

	public void completarValoresRiesgoDesdeFiscalizacion(PgimRankingRiesgoDTO pgimRankingRiesgoDTOCreado,
			PgimRankingUm pgimRankingUmCreado, AuditoriaDTO auditoriaDTO) {

		// Obtener los datos de riesgo registrados en las fiscalizaciones asociadas a la unidad minera y a la configuración de riesgo.
		List<PgimRankingSupervisionDTO> lPgimRankingSupervisionDTO = this.rankingSupervisionRepository
				.listarRankingSupervisionPorUmConfig(pgimRankingUmCreado.getPgimUnidadMinera().getIdUnidadMinera(),
						pgimRankingRiesgoDTOCreado.getIdConfiguraRiesgo());

		if (lPgimRankingSupervisionDTO.size() == 0) {
			return;
		}

		if (pgimRankingUmCreado.getPgimUnidadMinera().getIdUnidadMinera().equals(327L)) {
			log.info("Pausa");
		}

		// Lista de grupos de ranking del ranking generado
		List<PgimRankingUmGrupoDTO> lPgimRankingUmGrupoGenerado = this.rankingUmGrupoRepository
				.listarRankingUmGrupoPorRankingUm(pgimRankingUmCreado.getIdRankingUm());

		// Lista de grupos de ranking de la fiscalización más reciente efectuada sobre
		// la unidad minera
		PgimRankingSupervisionDTO pgimRankingSupervisionDTOReciente = lPgimRankingSupervisionDTO.get(0);

		// Actualizamos el puntaje de PGIM_TD_RANKING_UM
		pgimRankingUmCreado.setMoPuntaje(pgimRankingSupervisionDTOReciente.getMoPuntaje());

		pgimRankingUmCreado.setFeActualizacion(auditoriaDTO.getFecha());
		pgimRankingUmCreado.setUsActualizacion(auditoriaDTO.getUsername());
		pgimRankingUmCreado.setIpActualizacion(auditoriaDTO.getTerminal());

		this.rankingUmRepository.save(pgimRankingUmCreado);

		List<PgimRankingUmGrupoDTO> lRankingUmGrupoSupervision = this.rankingUmGrupoRepository
				.listarRankingUmGrupoPorIdRankingSuperTodos(
						pgimRankingSupervisionDTOReciente.getIdRankingSupervision());

		for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTOGenerado : lPgimRankingUmGrupoGenerado) {

			for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTOSuperv : lRankingUmGrupoSupervision) {

				// Comparamos los grupos
				if (pgimRankingUmGrupoDTOGenerado.getIdCfgGrupoRiesgo()
						.equals(pgimRankingUmGrupoDTOSuperv.getIdCfgGrupoRiesgo())) {

					// Lista de factores de riesgo del grupo generado
					List<PgimRankingUmFactorDTO> lPgimRankingUmFactorGenerado = this.rankingUmFactorRepository
							.listarFactoresIncluidoPendientesPorIdGrupo(
									pgimRankingUmGrupoDTOGenerado.getIdRankingUmGrupo());

					// Lista de factores de riesgo del grupo de la supervisión
					List<PgimRankingUmFactorDTO> lPgimRankingUmFactorSupervision = this.rankingUmFactorRepository
							.listarRankingUMFactorPorRankingUmGrupo(
									pgimRankingUmGrupoDTOSuperv.getIdRankingUmGrupo());

					for (PgimRankingUmFactorDTO pgimRankingUmFactorGenerado : lPgimRankingUmFactorGenerado) {

						for (PgimRankingUmFactorDTO primgRankingUmFactorSupervision : lPgimRankingUmFactorSupervision) {

							// comparamos los fatores
							if (pgimRankingUmFactorGenerado.getIdFactorRiesgo()
									.equals(primgRankingUmFactorSupervision.getIdFactorRiesgo())) {

								// actualizamos el factor generado en el nuevo ranking
								PgimRankingUmFactor pgimRankingUmFactor = rankingUmFactorRepository
										.findById(pgimRankingUmFactorGenerado.getIdRankingUmFactor()).orElse(null);

								if (primgRankingUmFactorSupervision.getIdCfgNivelRiesgo() != null) {
									// ID_CFG_NIVEL_RIESGO
									PgimCfgNivelRiesgo pgimCfgNivelRiesgo = cfgNivelRiesgoRepository
											.findById(primgRankingUmFactorSupervision.getIdCfgNivelRiesgo())
											.orElse(null);
									pgimRankingUmFactor.setPgimCfgNivelRiesgo(pgimCfgNivelRiesgo);

									// MO_CALIFICACION
									pgimRankingUmFactor
											.setMoCalificacion(primgRankingUmFactorSupervision.getMoCalificacion());

									// MO_PUNTAJE
									pgimRankingUmFactor
											.setMoPuntaje(primgRankingUmFactorSupervision.getMoPuntaje());

									// CM_CALIFICACION
									pgimRankingUmFactor
											.setCmCalificacion(primgRankingUmFactorSupervision.getCmCalificacion());
								}

								pgimRankingUmFactor.setFeActualizacion(auditoriaDTO.getFecha());
								pgimRankingUmFactor.setUsActualizacion(auditoriaDTO.getUsername());
								pgimRankingUmFactor.setIpActualizacion(auditoriaDTO.getTerminal());

								this.rankingUmFactorRepository.save(pgimRankingUmFactor);
							}
						}
					}

					// Actualizamos el grupo
					PgimRankingUmGrupo pgimRankingUmGrupo = this.rankingUmGrupoRepository
							.findById(pgimRankingUmGrupoDTOGenerado.getIdRankingUmGrupo()).orElse(null);

					// ID_RANKING_SUPER
					PgimRankingSupervision pgimRankingSupervision = this.rankingSupervisionRepository
							.findById(pgimRankingUmGrupoDTOSuperv.getIdRankingSupervision()).orElse(null);

					pgimRankingUmGrupo.setPgimRankingSupervision(pgimRankingSupervision);

					pgimRankingUmGrupo.setMoPuntaje(pgimRankingUmGrupoDTOSuperv.getMoPuntaje());
					pgimRankingUmGrupo.setFlRegistrar(pgimRankingUmGrupoDTOSuperv.getFlRegistrar());
					pgimRankingUmGrupo.setNuCalificacionGrupo(pgimRankingUmGrupoDTOSuperv.getNuCalificacionGrupo());
					pgimRankingUmGrupo.setFlMaximo(pgimRankingUmGrupoDTOSuperv.getFlMaximo());

					pgimRankingUmGrupo.setFeActualizacion(auditoriaDTO.getFecha());
					pgimRankingUmGrupo.setUsActualizacion(auditoriaDTO.getUsername());
					pgimRankingUmGrupo.setIpActualizacion(auditoriaDTO.getTerminal());

					this.rankingUmGrupoRepository.save(pgimRankingUmGrupo);
				}
			}
		}
	}

	@Override
	public PgimRankingUmDTO obtenerRankingUMPorId(Long idRankingUm) {
		return this.rankingUmRepository.obtenerRankingUmPorId(idRankingUm);
	}

	@Override
	public PgimRankingUmGrupoDTO obtenerRankingUMGrupoPorId(Long idRankingUmGrupo) {
		return this.rankingUmGrupoRepository.obtenerRankingUmGrupoPorId(idRankingUmGrupo);
	}

	@Override
	public PgimRankingUmFactorDTO obtenerRankingUMFactorPorId(Long IdRankingUmFactor) {
		return this.rankingUmFactorRepository.obtenerRankingUMFactorPorId(IdRankingUmFactor);
	}

	@Override
	public void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso) {

		if (!pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_CALIFICARIESGO_APRUEBARANKING)
				&& !pgimRelacionPaso.getIdRelacionPaso()
						.equals(ConstantesUtil.PARAM_RELACION_APRUEBARANKING_RANKINGAPROBADO)) {
			return;
		}

		String msjExcepcionControlada = null;

		Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso)
				.orElse(null);

		PgimRankingRiesgo pgimRankingRiesgo = this.rankingRiesgoRepository
				.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

		if (pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_CALIFICARIESGO_APRUEBARANKING)
				|| pgimRelacionPaso.getIdRelacionPaso()
						.equals(ConstantesUtil.PARAM_RELACION_APRUEBARANKING_RANKINGAPROBADO)) {

			List<PgimRankingUmAuxDTO> listaRum = this.rankingUmAuxRepository
					.listarRankingUmByIdRanking(pgimRankingRiesgo.getIdRankingRiesgo());

			for (PgimRankingUmAuxDTO pgimRankingUmAuxDTO : listaRum) {

				if (pgimRankingUmAuxDTO.getNroGestionPendiente() > 0
						|| pgimRankingUmAuxDTO.getNroTecnicoPendiente() > 0) {
					msjExcepcionControlada = "No se puede asignar el paso porque aún no se han calificado todos los factores de riesgo";
				}

			}

		}

		if (msjExcepcionControlada != null) { //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
			throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada);
		}

	}

	/**
	 * Permite crear la data base para el registro de riesgos de la supervisión
	 * 
	 * @param pgimSupervisionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void crearRankingRiesgoSupervision(PgimSupervisionDTO pgimSupervisionDTO, PgimUnidadMinera pgimUnidadMinera,
			AuditoriaDTO auditoriaDTO)
			throws Exception {
		Long idConfiguraRiesgoCumple = 0L;

		List<PgimConfiguraRiesgoDTO> lPgimConfiguraRiesgoDTO = this.configuraRiesgoRepository
				.obtenerConfiguracionesParaSupervision(pgimSupervisionDTO.getDescIdEspecialidad(), EValorParametro.ESCFG_APRBDA.toString(), EValorParametro.TIPO_CFG_RIESGO_FISCALIZACION.toString());

		boolean reglaDivisionSupervisora;
		boolean reglaMetodoMinado;
		boolean reglaSituacion;
		boolean reglaTipoActividad;
		boolean reglaEstadoUM;

		for (PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO : lPgimConfiguraRiesgoDTO) {

			List<PgimConfiguraReglaDTO> lPgimConfiguraReglaDTO = this.configuraReglaRepository
					.obtenerReglasPorConfiguracion(pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo());

			for (PgimConfiguraReglaDTO pgimConfiguraReglaDTO : lPgimConfiguraReglaDTO) {
				// Se evalúan las reglas AND
				reglaDivisionSupervisora = false;
				reglaMetodoMinado = false;
				reglaSituacion = false;
				reglaTipoActividad = false;
				reglaEstadoUM = false;

				// 1. Evaluando la aplicabilidad de la regla por División supervisora
				if (pgimConfiguraReglaDTO.getIdDivisionSupervisora() != null
						&& pgimUnidadMinera.getDivisionSupervisora() != null) {
					if (pgimConfiguraReglaDTO.getIdDivisionSupervisora()
							.equals(pgimUnidadMinera.getDivisionSupervisora().getIdValorParametro())) {
						reglaDivisionSupervisora = true;
					}
				} else {
					if (pgimConfiguraReglaDTO.getIdDivisionSupervisora() == null) {
						reglaDivisionSupervisora = true;
					}
				}

				// 2. Evaluando la aplicabilidad de la regla por Método de minado
				if (pgimConfiguraReglaDTO.getIdMetodoMinado() != null && pgimUnidadMinera.getMetodoMinado() != null) {
					if (pgimConfiguraReglaDTO.getIdMetodoMinado()
							.equals(pgimUnidadMinera.getMetodoMinado().getIdValorParametro())) {
						reglaMetodoMinado = true;
					}
				} else {
					if (pgimConfiguraReglaDTO.getIdMetodoMinado() == null) {
						reglaMetodoMinado = true;
					}
				}

				// 3. Evaluando la aplicabilidad de la regla por Situación
				if (pgimConfiguraReglaDTO.getIdSituacion() != null && pgimUnidadMinera.getSituacion() != null) {
					if (pgimConfiguraReglaDTO.getIdSituacion()
							.equals(pgimUnidadMinera.getSituacion().getIdValorParametro())) {
						reglaSituacion = true;
					}
				} else {
					if (pgimConfiguraReglaDTO.getIdSituacion() == null) {
						reglaSituacion = true;
					}
				}

				// 4. Evaluando la aplicabilidad de la regla por Tipo de actividad de la UM
				if (pgimConfiguraReglaDTO.getIdTipoActividad() != null && pgimUnidadMinera.getTipoActividad() != null) {
					if (pgimConfiguraReglaDTO.getIdTipoActividad()
							.equals(pgimUnidadMinera.getTipoActividad().getIdValorParametro())) {
						reglaTipoActividad = true;
					}
				} else {
					if (pgimConfiguraReglaDTO.getIdTipoActividad() == null) {
						reglaTipoActividad = true;
					}
				}

				// 5. Evaluando la aplicabilidad de la regla por Estado de la UM
				if (pgimConfiguraReglaDTO.getIdEstado() != null && pgimUnidadMinera.getEstadoUm() != null) {
					if (pgimConfiguraReglaDTO.getIdEstado()
							.equals(pgimUnidadMinera.getEstadoUm().getIdValorParametro())) {
						reglaEstadoUM = true;
					}
				} else {
					if (pgimConfiguraReglaDTO.getIdEstado() == null) {
						reglaEstadoUM = true;
					}
				}

				if (reglaDivisionSupervisora && reglaMetodoMinado && reglaSituacion && reglaTipoActividad
						&& reglaEstadoUM) {
					// Seleccionamos el id de la configuración que cumple con la UM
					idConfiguraRiesgoCumple = pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo();

					break;
				}
			}

			if (!idConfiguraRiesgoCumple.equals(0L)) {
				break;
			}
		}

		if (idConfiguraRiesgoCumple.equals(0L)) {

			PgimValorParametro eDivisionSupervisora = this.valorParametroRepository
					.findById(pgimUnidadMinera.getDivisionSupervisora().getIdValorParametro()).orElse(null);

			PgimValorParametro eSituacion = this.valorParametroRepository
					.findById(pgimUnidadMinera.getSituacion().getIdValorParametro()).orElse(null);

			PgimValorParametro eMetodoMinado = null;
			if (pgimUnidadMinera.getMetodoMinado() != null) {
				eMetodoMinado = this.valorParametroRepository
						.findById(pgimUnidadMinera.getMetodoMinado().getIdValorParametro()).orElse(null);
			}

			PgimValorParametro eTipoActividad = null;
			if (pgimUnidadMinera.getTipoActividad() != null) {
				eTipoActividad = this.valorParametroRepository
						.findById(pgimUnidadMinera.getTipoActividad().getIdValorParametro()).orElse(null);
			}

			PgimValorParametro eEstado = null;
			if (pgimUnidadMinera.getEstadoUm() != null) {
				eEstado = this.valorParametroRepository
						.findById(pgimUnidadMinera.getEstadoUm().getIdValorParametro()).orElse(null);
			}

			String noUnidadMinera = pgimUnidadMinera.getNoUnidadMinera();
			String divisionSupervisora = "";
			String deSituacion = "";
			String deMetodoMinado = "";
			String deTipoActividad = "";
			String deEstado = "";

			if (divisionSupervisora != null) {
				divisionSupervisora = eDivisionSupervisora.getDeValorParametro();
			}

			if (eSituacion != null) {
				deSituacion = eSituacion.getDeValorParametro();
			}

			if (eMetodoMinado != null) {
				deMetodoMinado = eMetodoMinado.getDeValorParametro();
			}

			if (eTipoActividad != null) {
				deTipoActividad = eTipoActividad.getDeValorParametro();
			}

			if (eEstado != null) {
				deEstado = eEstado.getDeValorParametro();
			}

			//STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
			String mensajeExcepcion = String.format(
					"No existe configuración de riesgo alguna que corresponda con las propiedades clave de la unidad fiscalizada '%s'; "
							+ "a saber:<br /><br />"
							+ "+ División supervisora = <b>'%s'</b><br />"
							+ "+ Método de minado = <b>'%s'</b><br />"
							+ "+ Situación = <b>'%s'</b><br />"
							+ "+ Tipo de actividad = <b>'%s'</b><br />"
							+ "+ Estado = <b>'%s'</b><br />"
							+ "<br /><br />"
							+ "Sin la configuración de riesgo apropiada el/la fiscalizador/a no podrá registrar los datos de riesgo de la fiscalización",
					noUnidadMinera, (divisionSupervisora.equals("") ? "Ninguna" : divisionSupervisora),
					(deMetodoMinado.equals("") ? "Ninguno" : deMetodoMinado),
					(deSituacion.equals("") ? "Ninguna" : deSituacion),
					(deTipoActividad.equals("") ? "Ninguno" : deTipoActividad),
					(deEstado.equals("") ? "Ninguno" : deEstado)
					);

			throw new PgimException(TipoResultado.WARNING, mensajeExcepcion);
		}

		/////////////////////////////////
		// Registrar Ranking Supervisión
		/////////////////////////////////

		PgimRankingSupervision pgimRankingSupervision = new PgimRankingSupervision();

		PgimSupervision pgimSupervision = new PgimSupervision();
		pgimSupervision.setIdSupervision(pgimSupervisionDTO.getIdSupervision());
		pgimRankingSupervision.setPgimSupervision(pgimSupervision);

		PgimConfiguraRiesgo pgimConfiguraRiesgo = new PgimConfiguraRiesgo();
		pgimConfiguraRiesgo.setIdConfiguraRiesgo(idConfiguraRiesgoCumple);
		pgimRankingSupervision.setPgimConfiguraRiesgo(pgimConfiguraRiesgo);

		pgimRankingSupervision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimRankingSupervision.setFeCreacion(auditoriaDTO.getFecha());
		pgimRankingSupervision.setUsCreacion(auditoriaDTO.getUsername());
		pgimRankingSupervision.setIpCreacion(auditoriaDTO.getTerminal());

		PgimRankingSupervision pgimRankingSupervisionCreado = this.rankingSupervisionRepository
				.save(pgimRankingSupervision);

		///////////////////////////////////////////////////
		// Obtener grupos a partir de Configuración riesgo
		///////////////////////////////////////////////////

		List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTO = this.cfgGrupoRiesgoRepository
				.listarCfgGrupoRiesgoPorConfiguracion(idConfiguraRiesgoCumple);

		///////////////////////////////////////////////
		// Crear registros en PGIM_TD_RANKING_UM_GRUPO
		///////////////////////////////////////////////

		for (PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO : lPgimCfgGrupoRiesgoDTO) {

			PgimRankingUmGrupo pgimRankingUmGrupo = new PgimRankingUmGrupo();

			// PgimRankingSupervision pgimRankingSupervisionTmp = new
			// PgimRankingSupervision();
			// pgimRankingSupervisionTmp.setIdRankingSupervision(pgimRankingSupervisionCreado.getIdRankingSupervision());
			pgimRankingUmGrupo.setPgimRankingSupervision(pgimRankingSupervisionCreado);

			PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoTmp = new PgimCfgGrupoRiesgo();
			pgimCfgGrupoRiesgoTmp.setIdCfgGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());
			pgimRankingUmGrupo.setPgimCfgGrupoRiesgo(pgimCfgGrupoRiesgoTmp);

			pgimRankingUmGrupo.setFlRegistrar("1");
			pgimRankingUmGrupo.setFlMaximo("0");

			pgimRankingUmGrupo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimRankingUmGrupo.setFeCreacion(auditoriaDTO.getFecha());
			pgimRankingUmGrupo.setUsCreacion(auditoriaDTO.getUsername());
			pgimRankingUmGrupo.setIpCreacion(auditoriaDTO.getTerminal());

			PgimRankingUmGrupo pgimRankingUmGrupoCreado = this.rankingUmGrupoRepository.save(pgimRankingUmGrupo);

			// Crear registros en PGIM_TD_RANKING_UM_FACTOR
			// Obtener factores a partir de la Configuración de factores por grupo riesgo
			List<PgimCfgFactorRiesgoDTO> lpgimCfgFactorRiesgoDTO = this.cfgFactorRiesgoRepository
					.listarCfgFactorRiesgoPorConfiguracion(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());

			for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lpgimCfgFactorRiesgoDTO) {
				PgimRankingUmFactor pgimRankingUmFactor = new PgimRankingUmFactor();

				PgimRankingUmGrupo pgimRankingUmGrupoTmp = new PgimRankingUmGrupo();
				pgimRankingUmGrupoTmp.setIdRankingUmGrupo(pgimRankingUmGrupoCreado.getIdRankingUmGrupo());
				pgimRankingUmFactor.setPgimRankingUmGrupo(pgimRankingUmGrupoTmp);

				PgimFactorRiesgo pgimFactorRiesgo = new PgimFactorRiesgo();
				pgimFactorRiesgo.setIdFactorRiesgo(pgimCfgFactorRiesgoDTO.getIdFactorRiesgo());
				pgimRankingUmFactor.setPgimFactorRiesgo(pgimFactorRiesgo);

				pgimRankingUmFactor.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimRankingUmFactor.setFeCreacion(auditoriaDTO.getFecha());
				pgimRankingUmFactor.setUsCreacion(auditoriaDTO.getUsername());
				pgimRankingUmFactor.setIpCreacion(auditoriaDTO.getTerminal());

				this.rankingUmFactorRepository.save(pgimRankingUmFactor);
			}
		}

	}

	/**
	 * Permite obtener la lista de grupos de factores de riesgo x supervisión
	 * 
	 * @param idSupervision
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorSupervision(Long idSupervision) throws Exception {

		List<PgimRankingUmGrupoDTO> lPgimRankingUmGrupoDTO = this.rankingUmGrupoRepository
				.listarRankingUmGrupoPorSupervision(idSupervision);

		for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO : lPgimRankingUmGrupoDTO) {

			List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = this.rankingUmFactorRepository
					.listarFactoresIncluidoPendientesPorIdGrupo(pgimRankingUmGrupoDTO.getIdRankingUmGrupo());
			int cant = 0;
			for (PgimRankingUmFactorDTO factor : lPgimRankingUmFactorDTO) {
				if (factor.getMoPuntaje() == null) {
					cant++;
				}
			}

			pgimRankingUmGrupoDTO.setDescNroPendiente(cant);
		}

		return lPgimRankingUmGrupoDTO;
	}

	/**
	 * Permite obtener un registro de la tabla PGIM_TD_RANKING_SUPERVISION a través
	 * de su ID
	 * 
	 * @param idRankingSupervision
	 * @return
	 */
	@Override
	public PgimRankingSupervisionDTO obtenerRankingSupervisionPorId(Long idRankingSupervision) {
		return this.rankingSupervisionRepository.obtenerRankingSupervisionPorId(idRankingSupervision);
	}

	/**
	 * Permite listar los registros de la tabla PGIM_TD_RANKING_UM_FACTOR a través
	 * del ID_RANKING_UM_GRUPO
	 * 
	 * @param idRankingSupervision
	 * @return
	 */
	@Override
	public List<PgimRankingUmFactorDTO> listarRankingUMFactorPorRankingUmGrupo(Long idRankingUmGrupo) {
		return this.rankingUmFactorRepository.listarRankingUMFactorPorRankingUmGrupo(idRankingUmGrupo);
	}
	
	/**
	 * Permite listar los registros RANKING_UM_FACTOR por Id del RANKING_UM
	 * 
	 * @param idRankingUm
	 * @return
	 */
	@Override
	public List<PgimRankingUmFactorDTO> listarRankingUMFactorPorRankingUm(Long idRankingUm) {
		return this.rankingUmFactorRepository.listarRankingUMFactorPorRankingUm(idRankingUm);
	}

	/**
	 * Permite obtener el registro de supervisión por idRankingUmGrupo
	 * 
	 * @param idRankingUmGrupo
	 * @return
	 */
	@Override
	public PgimSupervisionDTO obtenerSupervisionPorIdRankingUmGrupo(Long idRankingUmGrupo) {

		return this.supervisionRepository.obtenerSupervisionByIdRankingUmGrupo(idRankingUmGrupo);
	}

	@Override
	public List<PgimNivelRiesgoDTO> listarNivelRiesgoDesc() {
		return this.nivelRiesgoRepository.listarNivelRiesgoDesc();
	}

	@Override
	public List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgo(Long idUnidadMinera, Long idGrupoRiesgo) {

		return this.cfgGrupoRiesgoRepository.listarCfgGrupoRiesgo(idUnidadMinera, idGrupoRiesgo);
	}

	@Override
	public List<PgimRankingUmGrupoDTO> listarGruposRiesgoPorRanking(Long idRankingUm, Long idGrupoRiesgo) {

		return this.rankingUmGrupoRepository.listarRankingUmGrupoPorRankingUm(idRankingUm, idGrupoRiesgo);
	}

	@Override
	public List<PgimRankingUmGrupoDTO> listarGruposRiesgoPorSupervision(Long idRankingSupervision, Long idGrupoRiesgo) {
		return this.rankingUmGrupoRepository.listarRankingUmGrupoPorRankingSupervision(idRankingSupervision,
				idGrupoRiesgo);
	}

	@Override
	public Page<PgimRankingRiesgoDTO> listarConfiguracionRankingRiesgo(Long idConfiguraRiesgo, Pageable paginador,
			AuditoriaDTO auditoriaDTO) {
		Page<PgimRankingRiesgoDTO> pPgimRankingRiesgoDTO = this.rankingRiesgoRepository.listarConfiguracionRankingRiesgo(idConfiguraRiesgo, paginador);
		return pPgimRankingRiesgoDTO;
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<PgimRankingUmDTO> crearRankingUm(List<PgimRankingUmDTO> lPgimRankingUmDTO, AuditoriaDTO auditoriaDTO)
			throws Exception {

		List<PgimRankingUmDTO> lPgimRankingUmDTOCreado = new ArrayList<>();
		
	    for (PgimRankingUmDTO pgimRankingUmDTO : lPgimRankingUmDTO) {     
	    	
	    	// Crear registros en PGIM_TD_RANKING_UM
			PgimRankingUm pgimRankingUm = new PgimRankingUm();
			
			PgimRankingRiesgo pgimRankingRiesgo = new PgimRankingRiesgo();
			pgimRankingRiesgo.setIdRankingRiesgo(pgimRankingUmDTO.getIdRankingRiesgo());
			pgimRankingUm.setPgimRankingRiesgo(pgimRankingRiesgo);

			PgimUnidadMinera pgimUnidadMinera = new PgimUnidadMinera();
			pgimUnidadMinera.setIdUnidadMinera(pgimRankingUmDTO.getIdUnidadMinera());
			pgimRankingUm.setPgimUnidadMinera(pgimUnidadMinera);
			
			//indicamos que la UM se está agregando al ranking de manera "manual"
			pgimRankingUm.setTipoInclusionRanking(new PgimValorParametro());
			pgimRankingUm.getTipoInclusionRanking().setIdValorParametro(ConstantesUtil.PARAM_TIPO_INCLUSION_RANKING_MANUAL); 
	        
			pgimRankingUm.setFlActivo(ConstantesUtil.FL_IND_SI);
			pgimRankingUm.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimRankingUm.setFeCreacion(auditoriaDTO.getFecha());
			pgimRankingUm.setUsCreacion(auditoriaDTO.getUsername());
			pgimRankingUm.setIpCreacion(auditoriaDTO.getTerminal());

			PgimRankingUm pgimRankingUmCreado = this.rankingUmRepository.save(pgimRankingUm);
 
			// Crear registros en PGIM_TD_RANKING_UM_GRUPO 			 
			// Obtener grupos a partir de Configuración riesgo 
			List<PgimCfgGrupoRiesgoDTO> lPgimCfgGrupoRiesgoDTO = this.cfgGrupoRiesgoRepository
					.listarCfgGrupoRiesgoPorConfiguracion(pgimRankingUmDTO.getDescIdConfiguraRiesgo());

			for (PgimCfgGrupoRiesgoDTO pgimCfgGrupoRiesgoDTO : lPgimCfgGrupoRiesgoDTO) {
				PgimRankingUmGrupo pgimRankingUmGrupo = new PgimRankingUmGrupo();

				pgimRankingUmGrupo.setPgimRankingUm(pgimRankingUmCreado);

				PgimCfgGrupoRiesgo pgimCfgGrupoRiesgoTmp = new PgimCfgGrupoRiesgo();
				pgimCfgGrupoRiesgoTmp.setIdCfgGrupoRiesgo(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());
				pgimRankingUmGrupo.setPgimCfgGrupoRiesgo(pgimCfgGrupoRiesgoTmp);
				
				pgimRankingUmGrupo.setFlRegistrar("1");
				pgimRankingUmGrupo.setNuCalificacionGrupo(null);
				pgimRankingUmGrupo.setFlMaximo("0");

				pgimRankingUmGrupo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimRankingUmGrupo.setFeCreacion(auditoriaDTO.getFecha());
				pgimRankingUmGrupo.setUsCreacion(auditoriaDTO.getUsername());
				pgimRankingUmGrupo.setIpCreacion(auditoriaDTO.getTerminal());

				PgimRankingUmGrupo pgimRankingUmGrupoCreado = this.rankingUmGrupoRepository
						.save(pgimRankingUmGrupo);

				// Crear registros en PGIM_TD_RANKING_UM_FACTOR
				// Obtener factores a partir de la Configuración de factores por grupo riesgo
				List<PgimCfgFactorRiesgoDTO> lpgimCfgFactorRiesgoDTO = this.cfgFactorRiesgoRepository
						.listarCfgFactorRiesgoPorConfiguracion(pgimCfgGrupoRiesgoDTO.getIdCfgGrupoRiesgo());

				for (PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO : lpgimCfgFactorRiesgoDTO) {
					PgimRankingUmFactor pgimRankingUmFactor = new PgimRankingUmFactor();

					pgimRankingUmFactor.setPgimRankingUmGrupo(pgimRankingUmGrupoCreado);

					PgimFactorRiesgo pgimFactorRiesgo = new PgimFactorRiesgo();
					pgimFactorRiesgo.setIdFactorRiesgo(pgimCfgFactorRiesgoDTO.getIdFactorRiesgo());
					pgimRankingUmFactor.setPgimFactorRiesgo(pgimFactorRiesgo);

					pgimRankingUmFactor.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimRankingUmFactor.setFeCreacion(auditoriaDTO.getFecha());
					pgimRankingUmFactor.setUsCreacion(auditoriaDTO.getUsername());
					pgimRankingUmFactor.setIpCreacion(auditoriaDTO.getTerminal());

					this.rankingUmFactorRepository.save(pgimRankingUmFactor);
				}
			}

			// Completar los valores de los factores de riesgo desde las fiscalizaciones
			// realizadas, para el RANKING_UM generado
			PgimRankingRiesgoDTO pgimRankingRiesgoDTOCreado = this.obtenerRankingRiesgoPorId(pgimRankingUmDTO.getIdRankingRiesgo());
			this.completarValoresRiesgoDesdeFiscalizacion(pgimRankingRiesgoDTOCreado, pgimRankingUmCreado,
					auditoriaDTO);			
	            

			// Para retornar los RANKING_UM creados
	        PgimRankingUmDTO pgimRankingUmDTOCreado = this.rankingUmRepository.obtenerRankingUmPorId(pgimRankingUmCreado.getIdRankingUm());						
	        lPgimRankingUmDTOCreado.add(pgimRankingUmDTOCreado);

	    }  

	    return lPgimRankingUmDTOCreado;
	}
	

	@Transactional(readOnly = false)
	@Override
	public void eliminarRankingSupervision(Long idRankingSupervision, AuditoriaDTO auditoriaDTO) {

		PgimRankingSupervision pgimRankingSupervisionActual = this.rankingSupervisionRepository.findById(idRankingSupervision).orElse(null);

		// Obtenemos lista de RANKING_UM_GRUPO a eliminar
		List<PgimRankingUmGrupoDTO> lRankingUmGrupoSupervision = this.rankingUmGrupoRepository
			.listarRankingUmGrupoPorIdRankingSuperTodos(
					pgimRankingSupervisionActual.getIdRankingSupervision());

		for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO : lRankingUmGrupoSupervision) {
			
				// Obtenemos lista de RANKING_UM_FACTOR a eliminar
				List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = this.rankingUmFactorRepository
					.listarFactoresIncluidoPendientesPorIdGrupo(pgimRankingUmGrupoDTO.getIdRankingUmGrupo());
				
				for (PgimRankingUmFactorDTO pgimRankingUmFactorDTO : lPgimRankingUmFactorDTO) {        		
					// Eliminamos cada RANKING_UM_FACTOR        		
					PgimRankingUmFactor pgimRankingUmFactorActual = this.rankingUmFactorRepository.findById(pgimRankingUmFactorDTO.getIdRankingUmFactor()).orElse(null);
					this.rankingUmServiceImpl.eliminarRankingUmFactor(pgimRankingUmFactorActual, auditoriaDTO);        		        		
				}
				
				// Eliminamos cada RANKING_UM_GRUPO        		
				PgimRankingUmGrupo pgimRankingUmGrupoActual = this.rankingUmGrupoRepository.findById(pgimRankingUmGrupoDTO.getIdRankingUmGrupo()).orElse(null);
			this.rankingUmServiceImpl.eliminarRankingUmGrupo(pgimRankingUmGrupoActual, auditoriaDTO);
	}

		// Finalmente eliminamos el RANKING_SUPERVISION
			
		pgimRankingSupervisionActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimRankingSupervisionActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimRankingSupervisionActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimRankingSupervisionActual.setIpActualizacion(auditoriaDTO.getTerminal());

			this.rankingSupervisionRepository.save(pgimRankingSupervisionActual);
	} 

	@Override
	public PgimRankingSupervisionDTO obtenerRankingSupervisionPorIdSupervision(Long idSupervision) {
		return this.rankingSupervisionRepository.obtenerRankingSupervisionPorIdSupervision(idSupervision);
	}
	
	@Override
    public Integer contarRankingRiesgoPendientes(AuditoriaDTO auditoriaDTO) {
    	
    	Integer cantidadPendientes = this.rankingRiesgoRepository.contarRankingRiesgoPendientes(auditoriaDTO.getUsername()); 
    	
    	return cantidadPendientes;    	
    }

}