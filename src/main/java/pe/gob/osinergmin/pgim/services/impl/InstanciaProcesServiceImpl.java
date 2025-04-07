package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProcesoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimAutorizacion;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimDenuncia;
import pe.gob.osinergmin.pgim.models.entity.PgimEqpInstanciaPro;
import pe.gob.osinergmin.pgim.models.entity.PgimEvento;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimMedidaAdm;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsi;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.repository.AutorizacionRepository;
import pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.DenunciaRepository;
import pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.EventoRepository;
import pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository;
import pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository;
import pe.gob.osinergmin.pgim.models.repository.PasRepository;
import pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.ProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.PerfilUserService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Instancia de Proceso
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Service
@Transactional(readOnly = true)
public class InstanciaProcesServiceImpl implements InstanciaProcesService {

	@Autowired
	private InstanciaProcesRepository instanciaProcesRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private AutorizacionRepository autorizacionRepository;

	@Autowired
	private DenunciaRepository denunciaRepository;

	@Autowired
	private SupervisionRepository supervisionRepository;

	@Autowired
	private PasRepository pasRepository;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private PersonalContratoRepository personalContratoRepository;

	@Autowired
	private PersonalOsiRepository personalOsiRepository;

	@Autowired
	private EquipoInstanciaProcesoRepository equipoInstanciaProcesoRepository;

	@Autowired
	private ProcesoRepository procesoRepository;

	@Autowired
	private FaseProcesoRepository faseProcesoRepository;

	@Autowired
	private PasoProcesoRepository pasoProcesoRepository;

	@Autowired
	private PersonalOsiAuxRepository personalOsiAuxRepository;

	@Autowired
	private LiquidacionRepository liquidacionRepository;
	
	@Autowired
    private PrgrmSupervisionRepository prgrmSupervisionRepository;
	
	@Autowired
    private RankingRiesgoRepository rankingRiesgoRepository;
	
	@Autowired
    private ConfiguraRiesgoRepository configuraRiesgoRepository;

	@Autowired
    private MedidaAdmRepository medidaAdmRepository;

	@Autowired
    private PerfilUserService perfilUserService;

	@Autowired
    private InstanciaPasoRepository instanciaPasoRepository;

	public PgimInstanciaProcesDTO obtenerInstanciaProceso(PgimInstanciaProcesDTO instanciaold) {

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOfinal = null;

		// Obtener instancia final del objeto
		List<PgimInstanciaProcesDTO> listInstancias = this.instanciaProcesRepository
				.obtenerInstanciasProceso(instanciaold.getIdProceso(), instanciaold.getCoTablaInstancia());

		if (listInstancias.size() > 0) {
			// Obtener Nombre de la tabla en donde se encuentra la instancia de negocio
			pgimInstanciaProcesDTOfinal = listInstancias.get(0);
		}

		return pgimInstanciaProcesDTOfinal;
	}

	public PgimInstanciaProcesDTO obtenerInstanciaProceso(Long idProcesoActual, Long coTablaInstanciaActual) {
		PgimInstanciaProcesDTO pgimInstanciaProcesDTO = null;

		// Obtener instancia final del objeto
		List<PgimInstanciaProcesDTO> listInstancias = this.instanciaProcesRepository
				.obtenerInstanciasProceso(idProcesoActual, coTablaInstanciaActual);

		if (listInstancias.size() > 0) {
			// Obtener Nombre de la tabla en donde se encuentra la instancia de negocio
			pgimInstanciaProcesDTO = listInstancias.get(0);
		}

		return pgimInstanciaProcesDTO;		
	}

	@Override
	@Transactional(readOnly = false)
	public void actualizarInstProcTablaInstancia(PgimInstanciaProces pgimInstanciaProcesCreada,
			AuditoriaDTO auditoriaDTO) {

		switch (pgimInstanciaProcesCreada.getNoTablaInstancia()) {
			case ConstantesUtil.PARAM_TABLA_TC_SUPERVISION:
				PgimSupervision pgimSupervision = this.supervisionRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimSupervision != null) {
					pgimSupervision.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimSupervision.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimSupervision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimSupervision.setFeActualizacion(new Date());
					pgimSupervision.setUsActualizacion(auditoriaDTO.getUsername());
					pgimSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

					this.supervisionRepository.save(pgimSupervision);
				}
				break;

			case ConstantesUtil.PARAM_TABLA_TC_CONTRATO:
				PgimContrato pgimContrato = this.contratoRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimContrato != null) {
					pgimContrato.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimContrato.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimContrato.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimContrato.setFeActualizacion(new Date());
					pgimContrato.setUsActualizacion(auditoriaDTO.getUsername());
					pgimContrato.setIpActualizacion(auditoriaDTO.getTerminal());

					this.contratoRepository.save(pgimContrato);
				}
				break;

			case ConstantesUtil.PARAM_TABLA_TC_LIQUIDACION:
				PgimLiquidacion pgimLiquidacion = this.liquidacionRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimLiquidacion != null) {
					pgimLiquidacion.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimLiquidacion.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimLiquidacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimLiquidacion.setFeActualizacion(new Date());
					pgimLiquidacion.setUsActualizacion(auditoriaDTO.getUsername());
					pgimLiquidacion.setIpActualizacion(auditoriaDTO.getTerminal());

					this.liquidacionRepository.save(pgimLiquidacion);
				}
				break;

			case ConstantesUtil.PARAM_TABLA_TC_PAS:
				PgimPas pgimPas = this.pasRepository.findById(pgimInstanciaProcesCreada.getCoTablaInstancia())
						.orElse(null);
				if (pgimPas != null) {
					pgimPas.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimPas.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimPas.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimPas.setFeActualizacion(new Date());
					pgimPas.setUsActualizacion(auditoriaDTO.getUsername());
					pgimPas.setIpActualizacion(auditoriaDTO.getTerminal());

					this.pasRepository.save(pgimPas);
				}
				break;

			case ConstantesUtil.PARAM_TABLA_TM_EVENTO:
				PgimEvento pgimEvento = this.eventoRepository.findById(pgimInstanciaProcesCreada.getCoTablaInstancia())
						.orElse(null);
				if (pgimEvento != null) {
					pgimEvento.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimEvento.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimEvento.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimEvento.setFeActualizacion(new Date());
					pgimEvento.setUsActualizacion(auditoriaDTO.getUsername());
					pgimEvento.setIpActualizacion(auditoriaDTO.getTerminal());

					this.eventoRepository.save(pgimEvento);
				}
				break;

			case ConstantesUtil.PARAM_TABLA_TM_AUTORIZACION:
				PgimAutorizacion pgimAutorizacion = this.autorizacionRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimAutorizacion != null) {
					pgimAutorizacion.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimAutorizacion.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimAutorizacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimAutorizacion.setFeActualizacion(new Date());
					pgimAutorizacion.setUsActualizacion(auditoriaDTO.getUsername());
					pgimAutorizacion.setIpActualizacion(auditoriaDTO.getTerminal());

					this.autorizacionRepository.save(pgimAutorizacion);
				}
				break;

			case ConstantesUtil.PARAM_TABLA_TM_DENUNCIA:
				PgimDenuncia pgimDenuncia = this.denunciaRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimDenuncia != null) {
					pgimDenuncia.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimDenuncia.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
							pgimDenuncia.setEsRegistro(ConstantesUtil.IND_ACTIVO);
							pgimDenuncia.setFeActualizacion(new Date());
							pgimDenuncia.setUsActualizacion(auditoriaDTO.getUsername());
							pgimDenuncia.setIpActualizacion(auditoriaDTO.getTerminal());

					this.denunciaRepository.save(pgimDenuncia);
				}
				break;
			
			case ConstantesUtil.PARAM_TABLA_TC_PRGRM_SUPERVISION:
				PgimPrgrmSupervision pgimPrgrmSupervision = this.prgrmSupervisionRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimPrgrmSupervision != null) {
					pgimPrgrmSupervision.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimPrgrmSupervision.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimPrgrmSupervision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimPrgrmSupervision.setFeActualizacion(new Date());
					pgimPrgrmSupervision.setUsActualizacion(auditoriaDTO.getUsername());
					pgimPrgrmSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

					this.prgrmSupervisionRepository.save(pgimPrgrmSupervision);
				}
				break;
				
			case ConstantesUtil.PARAM_TABLA_TC_RANKING_RIESGO:
				PgimRankingRiesgo pgimRankingRiesgo = this.rankingRiesgoRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimRankingRiesgo != null) {
					pgimRankingRiesgo.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimRankingRiesgo.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimRankingRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimRankingRiesgo.setFeActualizacion(new Date());
					pgimRankingRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
					pgimRankingRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

					this.rankingRiesgoRepository.save(pgimRankingRiesgo);
				}
				break;
				
			case ConstantesUtil.PARAM_TABLA_TM_CONFIGURA_RIESGO:
				PgimConfiguraRiesgo pgimConfiguraRiesgo = this.configuraRiesgoRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimConfiguraRiesgo != null) {
					pgimConfiguraRiesgo.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimConfiguraRiesgo.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimConfiguraRiesgo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimConfiguraRiesgo.setFeActualizacion(new Date());
					pgimConfiguraRiesgo.setUsActualizacion(auditoriaDTO.getUsername());
					pgimConfiguraRiesgo.setIpActualizacion(auditoriaDTO.getTerminal());

					this.configuraRiesgoRepository.save(pgimConfiguraRiesgo);
				}
				break;

			case ConstantesUtil.PARAM_TABLA_TC_MEDIDA_ADM:
				PgimMedidaAdm pgimMedidaAdm = this.medidaAdmRepository
						.findById(pgimInstanciaProcesCreada.getCoTablaInstancia()).orElse(null);
				if (pgimMedidaAdm != null) {
					pgimMedidaAdm.setPgimInstanciaProces(new PgimInstanciaProces());
					pgimMedidaAdm.getPgimInstanciaProces()
							.setIdInstanciaProceso(pgimInstanciaProcesCreada.getIdInstanciaProceso());
					pgimMedidaAdm.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimMedidaAdm.setFeActualizacion(new Date());
					pgimMedidaAdm.setUsActualizacion(auditoriaDTO.getUsername());
					pgimMedidaAdm.setIpActualizacion(auditoriaDTO.getTerminal());

					this.medidaAdmRepository.save(pgimMedidaAdm);
				}
				break;
				
			default:
				break;
		}
	}

	@Override
	public PgimInstanciaProces obtenerInstanciaProceso(Long id) {
		return instanciaProcesRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public List<PgimInstanciaProcesDTO> asegurarInstanciasProceso(Long idProcesoActual, Long coTablaInstanciaActual,
			AuditoriaDTO auditoriaDTO) {

		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = new ArrayList<PgimInstanciaProcesDTO>();
		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = null;
		PgimInstanciaProcesDTO pgimInstanciaProcesDTOPadre = null;

		List<PgimInstanciaProcesDTO> listInstancias = this.instanciaProcesRepository.obtenerInstanciasProceso(idProcesoActual, coTablaInstanciaActual);

		if (listInstancias.size() > 0) {
			pgimInstanciaProcesDTOActual = listInstancias.get(0);
		}

		if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_EVENTO)) {

			PgimEvento pgimEvento = this.eventoRepository.findById(coTablaInstanciaActual).orElse(null);

			Long idUnidadMinera = pgimEvento.getPgimUnidadMinera().getIdUnidadMinera();

			// Buscar el padre de la instancia actual
			pgimInstanciaProcesDTOPadre = this.instanciaProcesRepository.obtenerInstanciaProceso(
					ConstantesUtil.PARAM_PROCESO_EVENTO, ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA, idUnidadMinera);

			if (pgimInstanciaProcesDTOPadre == null) {
				PgimInstanciaProces pgimInstanciaProcesUM = new PgimInstanciaProces();

				pgimInstanciaProcesUM.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesUM.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_EVENTO);
				pgimInstanciaProcesUM.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA);
				pgimInstanciaProcesUM.setCoTablaInstancia(idUnidadMinera);
				pgimInstanciaProcesUM.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesUM.setFeCreacion(new Date());
				pgimInstanciaProcesUM.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesUM.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesUM = this.instanciaProcesRepository.save(pgimInstanciaProcesUM);

				pgimInstanciaProcesDTOPadre = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_EVENTO, ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA,
						idUnidadMinera);
			}

			if (pgimInstanciaProcesDTOActual == null) {

				PgimInstanciaProces pgimInstanciaProcesActual = new PgimInstanciaProces();

				pgimInstanciaProcesActual.setInstanciaProcesoPadre(new PgimInstanciaProces());
				pgimInstanciaProcesActual.getInstanciaProcesoPadre()
						.setIdInstanciaProceso(pgimInstanciaProcesDTOPadre.getIdInstanciaProceso());
				pgimInstanciaProcesActual.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesActual.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_EVENTO);
				pgimInstanciaProcesActual.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TM_EVENTO);
				pgimInstanciaProcesActual.setCoTablaInstancia(coTablaInstanciaActual);
				pgimInstanciaProcesActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesActual.setFeCreacion(new Date());
				pgimInstanciaProcesActual.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesActual.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesActual = this.instanciaProcesRepository.save(pgimInstanciaProcesActual);

				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_EVENTO, ConstantesUtil.PARAM_TABLA_TM_EVENTO,
						coTablaInstanciaActual);
			}

		} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_AUTORIZACION)) {

			PgimAutorizacion pgimautorizacion = this.autorizacionRepository.findById(coTablaInstanciaActual)
					.orElse(null);

			Long idUnidadMinera = pgimautorizacion.getPgimUnidadMinera().getIdUnidadMinera();

			// Buscar el padre de la instancia actual
			pgimInstanciaProcesDTOPadre = this.instanciaProcesRepository.obtenerInstanciaProceso(
					ConstantesUtil.PARAM_PROCESO_AUTORIZACION, ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA,
					idUnidadMinera);

			if (pgimInstanciaProcesDTOPadre == null) {
				PgimInstanciaProces pgimInstanciaProcesUM = new PgimInstanciaProces();

				pgimInstanciaProcesUM.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesUM.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_AUTORIZACION);
				pgimInstanciaProcesUM.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA);
				pgimInstanciaProcesUM.setCoTablaInstancia(idUnidadMinera);
				pgimInstanciaProcesUM.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesUM.setFeCreacion(new Date());
				pgimInstanciaProcesUM.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesUM.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesUM = this.instanciaProcesRepository.save(pgimInstanciaProcesUM);

				pgimInstanciaProcesDTOPadre = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_AUTORIZACION, ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA,
						idUnidadMinera);
			}

			if (pgimInstanciaProcesDTOActual == null) {

				PgimInstanciaProces pgimInstanciaProcesActual = new PgimInstanciaProces();

				pgimInstanciaProcesActual.setInstanciaProcesoPadre(new PgimInstanciaProces());
				pgimInstanciaProcesActual.getInstanciaProcesoPadre().setIdInstanciaProceso(pgimInstanciaProcesDTOPadre.getIdInstanciaProceso());
				pgimInstanciaProcesActual.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesActual.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_AUTORIZACION);
				pgimInstanciaProcesActual.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TM_AUTORIZACION);
				pgimInstanciaProcesActual.setCoTablaInstancia(coTablaInstanciaActual);
				pgimInstanciaProcesActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesActual.setFeCreacion(new Date());
				pgimInstanciaProcesActual.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesActual.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesActual = this.instanciaProcesRepository.save(pgimInstanciaProcesActual);

				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_AUTORIZACION, ConstantesUtil.PARAM_TABLA_TM_AUTORIZACION,
						coTablaInstanciaActual);
			}

		}else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_DENUNCIA)) {

			PgimDenuncia pgimDenuncia = this.denunciaRepository.findById(coTablaInstanciaActual)
					.orElse(null);

			Long idUnidadMinera = pgimDenuncia.getPgimUnidadMinera().getIdUnidadMinera();

			// Buscar el padre de la instancia actual
			pgimInstanciaProcesDTOPadre = this.instanciaProcesRepository.obtenerInstanciaProceso(
					ConstantesUtil.PARAM_PROCESO_DENUNCIA, ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA,
					idUnidadMinera);

			if (pgimInstanciaProcesDTOPadre == null) {
				PgimInstanciaProces pgimInstanciaProcesUM = new PgimInstanciaProces();

				pgimInstanciaProcesUM.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesUM.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_DENUNCIA);
				pgimInstanciaProcesUM.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA);
				pgimInstanciaProcesUM.setCoTablaInstancia(idUnidadMinera);
				pgimInstanciaProcesUM.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesUM.setFeCreacion(new Date());
				pgimInstanciaProcesUM.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesUM.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesUM = this.instanciaProcesRepository.save(pgimInstanciaProcesUM);

				pgimInstanciaProcesDTOPadre = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_DENUNCIA, ConstantesUtil.PARAM_TABLA_TM_UNIDAD_MINERA,
						idUnidadMinera);
			}

			if (pgimInstanciaProcesDTOActual == null) {

				PgimInstanciaProces pgimInstanciaProcesActual = new PgimInstanciaProces();

				pgimInstanciaProcesActual.setInstanciaProcesoPadre(new PgimInstanciaProces());
				pgimInstanciaProcesActual.getInstanciaProcesoPadre().setIdInstanciaProceso(pgimInstanciaProcesDTOPadre.getIdInstanciaProceso());
				pgimInstanciaProcesActual.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesActual.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_DENUNCIA);
				pgimInstanciaProcesActual.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TM_DENUNCIA);
				pgimInstanciaProcesActual.setCoTablaInstancia(coTablaInstanciaActual);
				pgimInstanciaProcesActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesActual.setFeCreacion(new Date());
				pgimInstanciaProcesActual.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesActual.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesActual = this.instanciaProcesRepository.save(pgimInstanciaProcesActual);

				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_DENUNCIA, ConstantesUtil.PARAM_TABLA_TM_DENUNCIA,
						coTablaInstanciaActual);
			}

	} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {

			if (pgimInstanciaProcesDTOActual == null) {
				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_SUPERVISION, ConstantesUtil.PARAM_TABLA_TC_SUPERVISION,
						coTablaInstanciaActual);

				pgimInstanciaProcesDTOActual = this.asegurarInstanciaEspecifica(
						ConstantesUtil.PARAM_PROCESO_SUPERVISION, ConstantesUtil.PARAM_TABLA_TC_SUPERVISION,
						coTablaInstanciaActual, null, auditoriaDTO);
			}

		} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {

			if (pgimInstanciaProcesDTOActual == null) {
				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_LIQUIDACION, ConstantesUtil.PARAM_TABLA_TC_LIQUIDACION,
						coTablaInstanciaActual);

				pgimInstanciaProcesDTOActual = this.asegurarInstanciaEspecifica(
						ConstantesUtil.PARAM_PROCESO_LIQUIDACION, ConstantesUtil.PARAM_TABLA_TC_LIQUIDACION,
						coTablaInstanciaActual, null, auditoriaDTO);
			}
		} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)) {

			if (pgimInstanciaProcesDTOActual == null) {
				pgimInstanciaProcesDTOActual = this.asegurarInstanciaEspecifica(
						ConstantesUtil.PARAM_PROCESO_FISCALIZACION, ConstantesUtil.PARAM_TABLA_TC_PAS,
						coTablaInstanciaActual, null, auditoriaDTO);
			}

		} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_CONTRATO)) {

			if (pgimInstanciaProcesDTOActual == null) {
				pgimInstanciaProcesDTOActual = this.asegurarInstanciaEspecifica(ConstantesUtil.PARAM_PROCESO_CONTRATO,
						ConstantesUtil.PARAM_TABLA_TC_CONTRATO, coTablaInstanciaActual, null, auditoriaDTO);
			}

		} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_PROGRAMACION)) {

			if (pgimInstanciaProcesDTOActual == null) {
				PgimInstanciaProces pgimInstanciaProcesActual = new PgimInstanciaProces();

				pgimInstanciaProcesActual.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesActual.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_PROGRAMACION);
				pgimInstanciaProcesActual.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TC_PRGRM_SUPERVISION);
				pgimInstanciaProcesActual.setCoTablaInstancia(coTablaInstanciaActual);
				pgimInstanciaProcesActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesActual.setFeCreacion(new Date());
				pgimInstanciaProcesActual.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesActual.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesActual = this.instanciaProcesRepository.save(pgimInstanciaProcesActual);

				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_PROGRAMACION, ConstantesUtil.PARAM_TABLA_TC_PRGRM_SUPERVISION,
						coTablaInstanciaActual);
			}
		} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS)) {

			if (pgimInstanciaProcesDTOActual == null) {
				PgimInstanciaProces pgimInstanciaProcesActual = new PgimInstanciaProces();

				pgimInstanciaProcesActual.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesActual.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS);
				pgimInstanciaProcesActual.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TC_RANKING_RIESGO);
				pgimInstanciaProcesActual.setCoTablaInstancia(coTablaInstanciaActual);
				pgimInstanciaProcesActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesActual.setFeCreacion(new Date());
				pgimInstanciaProcesActual.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesActual.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesActual = this.instanciaProcesRepository.save(pgimInstanciaProcesActual);

				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS, ConstantesUtil.PARAM_TABLA_TC_RANKING_RIESGO,
						coTablaInstanciaActual);
			}
		} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO)) {

			if (pgimInstanciaProcesDTOActual == null) {
				PgimInstanciaProces pgimInstanciaProcesActual = new PgimInstanciaProces();

				pgimInstanciaProcesActual.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesActual.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO);
				pgimInstanciaProcesActual.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TM_CONFIGURA_RIESGO);
				pgimInstanciaProcesActual.setCoTablaInstancia(coTablaInstanciaActual);
				pgimInstanciaProcesActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesActual.setFeCreacion(new Date());
				pgimInstanciaProcesActual.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesActual.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesActual = this.instanciaProcesRepository.save(pgimInstanciaProcesActual);

				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO, ConstantesUtil.PARAM_TABLA_TM_CONFIGURA_RIESGO,
						coTablaInstanciaActual);
			}
		} else if (idProcesoActual.equals(ConstantesUtil.PARAM_PROCESO_MEDIDA_ADM)) {

			if (pgimInstanciaProcesDTOActual == null) {
				PgimInstanciaProces pgimInstanciaProcesActual = new PgimInstanciaProces();

				pgimInstanciaProcesActual.setPgimProceso(new PgimProceso());
				pgimInstanciaProcesActual.getPgimProceso().setIdProceso(ConstantesUtil.PARAM_PROCESO_MEDIDA_ADM);
				pgimInstanciaProcesActual.setNoTablaInstancia(ConstantesUtil.PARAM_TABLA_TC_MEDIDA_ADM);
				pgimInstanciaProcesActual.setCoTablaInstancia(coTablaInstanciaActual);
				pgimInstanciaProcesActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimInstanciaProcesActual.setFeCreacion(new Date());
				pgimInstanciaProcesActual.setUsCreacion(auditoriaDTO.getUsername());
				pgimInstanciaProcesActual.setIpCreacion(auditoriaDTO.getTerminal());

				pgimInstanciaProcesActual = this.instanciaProcesRepository.save(pgimInstanciaProcesActual);

				pgimInstanciaProcesDTOActual = this.instanciaProcesRepository.obtenerInstanciaProceso(
						ConstantesUtil.PARAM_PROCESO_MEDIDA_ADM, ConstantesUtil.PARAM_TABLA_TC_MEDIDA_ADM,
						coTablaInstanciaActual);
			}
		}
		else {
			String mensajeExcepcion = String.format("El proceso con identificador %d aún no fue implementado para iniciar un flujo", idProcesoActual);
			throw new PgimException(TipoResultado.WARNING.getValor(), mensajeExcepcion);
		}

		lPgimInstanciaProcesDTO.add(pgimInstanciaProcesDTOActual);
		lPgimInstanciaProcesDTO.add(pgimInstanciaProcesDTOPadre);

		return lPgimInstanciaProcesDTO;
	}

	/**
	 * Permite asegurar la instancia específica a procesar.
	 * @param idProceso
	 * @param tablaInstancia
	 * @param coTablaInstancia
	 * @param idInstanciaProcesoPadre
	 * @param auditoriaDTO
	 * @return
	 */
	private PgimInstanciaProcesDTO asegurarInstanciaEspecifica(Long idProceso, String tablaInstancia,
			Long coTablaInstancia, Long idInstanciaProcesoPadre, AuditoriaDTO auditoriaDTO) {
		PgimInstanciaProces pgimInstanciaProcesActual = new PgimInstanciaProces();

		if (idInstanciaProcesoPadre != null) {
			pgimInstanciaProcesActual.setInstanciaProcesoPadre(new PgimInstanciaProces());
			pgimInstanciaProcesActual.getInstanciaProcesoPadre().setIdInstanciaProceso(idInstanciaProcesoPadre);
		}

		pgimInstanciaProcesActual.setPgimProceso(new PgimProceso());
		pgimInstanciaProcesActual.getPgimProceso().setIdProceso(idProceso);
		pgimInstanciaProcesActual.setNoTablaInstancia(tablaInstancia);
		pgimInstanciaProcesActual.setCoTablaInstancia(coTablaInstancia);

		pgimInstanciaProcesActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimInstanciaProcesActual.setFeCreacion(new Date());
		pgimInstanciaProcesActual.setUsCreacion(auditoriaDTO.getUsername());
		pgimInstanciaProcesActual.setIpCreacion(auditoriaDTO.getTerminal());

		pgimInstanciaProcesActual = this.instanciaProcesRepository.save(pgimInstanciaProcesActual);

		PgimInstanciaProcesDTO pgimInstanciaProcesDTO = this.instanciaProcesRepository
				.obtenerInstanciaProceso(idProceso, tablaInstancia, coTablaInstancia);

		return pgimInstanciaProcesDTO;
	}

	@Override
	public PgimInstanciaProces obtenerInstanciaProcesoPorId(Long idInstanciaProceso) {
		return this.instanciaProcesRepository.findById(idInstanciaProceso).orElse(null);
	}
	
	@Override
	public PgimInstanciaProces modificarInstanciaProcesEntity(PgimInstanciaProces pgimInstanciaProces, AuditoriaDTO auditoriaDTO) {

		pgimInstanciaProces.setFeActualizacion(new Date());
		pgimInstanciaProces.setUsActualizacion(auditoriaDTO.getUsername());
		pgimInstanciaProces.setIpActualizacion(auditoriaDTO.getTerminal());

		return this.instanciaProcesRepository.save(pgimInstanciaProces);
	}

	@Override
	@Transactional(readOnly = false)
	public void persistirExpedienteSiged(PgimInstanciaProcesDTO pgimInstanciaProcesDTO, AuditoriaDTO auditoriaDTO) {
		PgimInstanciaProces pgimInstanciaProces = this
				.obtenerInstanciaProceso(pgimInstanciaProcesDTO.getIdInstanciaProceso());
		pgimInstanciaProces.setNuExpedienteSiged(pgimInstanciaProcesDTO.getNuExpedienteSiged());
		pgimInstanciaProces.setFeActualizacion(new Date());
		pgimInstanciaProces.setUsActualizacion(auditoriaDTO.getUsername());
		pgimInstanciaProces.setIpActualizacion(auditoriaDTO.getTerminal());

		this.instanciaProcesRepository.save(pgimInstanciaProces);
	}

	@Override
	public List<PgimInstanciaProcesDTO> listarPorPalabraClave(String palabra) {
		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesRepository
				.listarPorPalabraClave(palabra);

		return lPgimInstanciaProcesDTO;
	}

	/**
	 * Permite listar los participantes de una instancia de proceso
	 * 
	 * @param idInstanciaProceso
	 * @return
	 */
	@Override
	public List<PgimEqpInstanciaProDTO> obtenerParticipantesInstanciaPro(Long idInstanciaProceso) throws Exception {
		List<PgimEqpInstanciaProDTO> participantes = equipoInstanciaProcesoRepository.obtenerParticipantesInstanciaPro(idInstanciaProceso);

		for(PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO: participantes){

			String foto = this.perfilUserService.obtenerFotoPersona(pgimEqpInstanciaProDTO.getDescIdPersona());
			String fotoBase64 = null;

			if(foto != ""){

				fotoBase64 = "data:image/jpeg;base64," + foto;
				pgimEqpInstanciaProDTO.setDescFotoBase64(fotoBase64);

			}else{

				if(pgimEqpInstanciaProDTO.getDescDeOrigen().equals("Osinergmin")){
					fotoBase64 = "assets/images/ico_user.png";
				} else if(pgimEqpInstanciaProDTO.getDescDeOrigen().equals("Supervisora")){
					fotoBase64 = "assets/images/ico_es.png";
				}
				
				pgimEqpInstanciaProDTO.setDescFotoBase64(fotoBase64);

				// if(pgimEqpInstanciaProDTO.getIdPersonalOsi() != null){
				// 	if(pgimEqpInstanciaProDTO.getDescTiSexo() != null){
				// 		if(pgimEqpInstanciaProDTO.getDescTiSexo().equals("1")){
				// 			fotoBase64 = "assets/images/ico_user_mas.png";
				// 		}else{
				// 			fotoBase64 = "assets/images/ico_user_fem.png";
				// 		}
				// 	}else{
				// 		fotoBase64 = "assets/images/ico_user.png";
				// 	}
				// }else{
				// 	fotoBase64 = "assets/images/ico_es.png";
				// }
			}
		}

		return participantes;
	}

	@Override
	public List<PgimFaseProcesoDTO> obtenerFasesPorIdProceso(Long idProceso) {
		List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = this.faseProcesoRepository.obtenerFasesPorIdProceso(idProceso);

		return lPgimFaseProcesoDTO;
	}

	@Override
	public List<PgimFaseProcesoDTO> obtenerFasesPorProceso() {
		List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = this.faseProcesoRepository.obtenerFasesPorProceso();

		return lPgimFaseProcesoDTO;
	}

	@Override
	public List<PgimPasoProcesoDTO> obtenerPasosPorIdProceso(Long idProceso) {
		List<PgimPasoProcesoDTO> lPgimPasosProcesoDTO = this.pasoProcesoRepository.obtenerPasosPorIdProceso(idProceso);
		return lPgimPasosProcesoDTO;
	}

	@Override
	public List<PgimPasoProcesoDTO> obtenerPasosPorFase() {
		List<PgimPasoProcesoDTO> lPgimPasosProcesoDTO = this.pasoProcesoRepository.obtenerPasosPorFase();
		return lPgimPasosProcesoDTO;
	}

	/**
	 * Permite obtener la lista de personas del Osinergmin que coincidan con los
	 * criterios la palabra filtro, y que no se dupliquen con un rol, en el equipo
	 * de la instancia de un proceso
	 * 
	 * @param palabra            Palabra clave empleada para realizar la búsqueda
	 *                           por aproximación.
	 * @param idInstanciaProceso
	 * @param idRolProceso
	 * @return
	 */
	public List<PgimPersonaosiAuxDTO> listarPersonalOsiSinDuplicadosXrol(String palabra, Long idInstanciaProceso,
			Long idRolProceso) {
		List<PgimPersonaosiAuxDTO> lPgimPersonaosiAuxDTO = this.personalOsiAuxRepository
				.listarPersonalOsiSinDuplicadosXrol(palabra, idInstanciaProceso, idRolProceso);
		return lPgimPersonaosiAuxDTO;
	}

	/**
	 * Permite seleccionar el equipo de una instancia de proceso
	 * 
	 * @param PgimEqpInstanciaProDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	@Override
	public void seleccionarEquipoInstancia(PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO, AuditoriaDTO auditoriaDTO)
			throws Exception {

		// Preparamos objeto pgimInstanciaProces
		PgimInstanciaProces pgimInstanciaProces = new PgimInstanciaProces();
		pgimInstanciaProces.setIdInstanciaProceso(pgimEqpInstanciaProDTO.getIdInstanciaProceso());

		// Preparamos objeto PgimRolProceso
		PgimRolProceso pgimRolProceso = new PgimRolProceso();
		pgimRolProceso.setIdRolProceso(pgimEqpInstanciaProDTO.getIdRolProceso());

		if (pgimEqpInstanciaProDTO.getIdPersonalOsi() != null) {

			PgimPersonalOsiDTO pgimPersonalOsiDTO = this.personalOsiRepository.obtenerPersonalOsigPorId(pgimEqpInstanciaProDTO.getIdPersonalOsi());
			// Validamos si la persona ya se encuentra en el equipo de la instancia, con el
			// mismo rol
			List<PgimEqpInstanciaProDTO> lvalidacion = equipoInstanciaProcesoRepository.obtenerPersonaOsiEqpPorRol(
					pgimEqpInstanciaProDTO.getIdInstanciaProceso(), pgimEqpInstanciaProDTO.getIdPersonalOsi(),
					pgimEqpInstanciaProDTO.getIdRolProceso());
			if (lvalidacion.size() > 0) {
				throw new PgimException("error",
						"El personal de Osinergmin ya se encuentra en la lista de participantes, con el rol seleccionado");
			}

			// Preparamos objeto PgimPersonalOsi
			PgimPersonalOsi pgimPersonalOsi = new PgimPersonalOsi();
			pgimPersonalOsi.setIdPersonalOsi(pgimEqpInstanciaProDTO.getIdPersonalOsi());

			
			// Registro de Selección de personal de Osinergmin
			PgimEqpInstanciaPro pgimEqpInstanciaPro = new PgimEqpInstanciaPro();
			pgimEqpInstanciaPro.setPgimInstanciaProces(pgimInstanciaProces);
			pgimEqpInstanciaPro.setPgimRolProceso(pgimRolProceso);
			pgimEqpInstanciaPro.setPgimPersonalOsi(pgimPersonalOsi);
			pgimEqpInstanciaPro.setFlEsResponsable(pgimEqpInstanciaProDTO.getFlEsResponsable());
			pgimEqpInstanciaPro.setNoPrefijoPersonaEquipo(pgimPersonalOsiDTO.getDescNoPrefijoPersona());
			
			pgimEqpInstanciaPro.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimEqpInstanciaPro.setFeCreacion(auditoriaDTO.getFecha());
			pgimEqpInstanciaPro.setUsCreacion(auditoriaDTO.getUsername());
			pgimEqpInstanciaPro.setIpCreacion(auditoriaDTO.getTerminal());

			equipoInstanciaProcesoRepository.save(pgimEqpInstanciaPro);

		} else {

			// Registro de Selección de personal de la supervisora

			for (PgimPersonalContratoDTO personalContratoDTO : pgimEqpInstanciaProDTO.getAuxListaPersonalContrato()) {

				// Validamos si la persona ya se encuentra en el equipo de la instancia, con el
				// mismo rol
				List<PgimEqpInstanciaProDTO> lvalidacion = equipoInstanciaProcesoRepository
						.obtenerPersonaContraEqpPorRol(pgimEqpInstanciaProDTO.getIdInstanciaProceso(),
								personalContratoDTO.getIdPersonalContrato(), pgimEqpInstanciaProDTO.getIdRolProceso());
				if (lvalidacion.size() > 0) {
					throw new PgimException("error",
							"El personal de la Supervisora ya se encuentra en la lista de participantes, con el rol seleccionado");
				}

				// Obtener las propiedades de personas de la supervisora en el personal del contrato
				personalContratoDTO = this.personalContratoRepository.obtenerPersonalContrato(personalContratoDTO.getIdPersonalContrato());
				
				// Preparamos objeto PgimPersonalContrato
				PgimPersonalContrato pgimPersonalContrato = new PgimPersonalContrato();
				pgimPersonalContrato.setIdPersonalContrato(personalContratoDTO.getIdPersonalContrato());

				PgimEqpInstanciaPro pgimEqpInstanciaPro = new PgimEqpInstanciaPro();
				pgimEqpInstanciaPro.setPgimInstanciaProces(pgimInstanciaProces);
				pgimEqpInstanciaPro.setPgimRolProceso(pgimRolProceso);
				pgimEqpInstanciaPro.setPgimPersonalContrato(pgimPersonalContrato);
				pgimEqpInstanciaPro.setFlEsResponsable("0");// Por defecto, el personal externo no puede ser responsable
				
				pgimEqpInstanciaPro.setNoCargoPersonaEquipo(personalContratoDTO.getNoCargoEnContrato()); // STORY: PGIM-6176: FISC. Prefijo y cargo por defecto en selección de personal de la fiscalización
				pgimEqpInstanciaPro.setNoPrefijoPersonaEquipo(personalContratoDTO.getDescNoPrefijoPersona()); // STORY: PGIM-6176: FISC. Prefijo y cargo por defecto en selección de personal de la fiscalización
				pgimEqpInstanciaPro.setNoPerfilPersonaEquipo(personalContratoDTO.getNoPerfilEnContrato()); // STORY: PGIM-6176: FISC. Prefijo y cargo por defecto en selección de personal de la fiscalización

				pgimEqpInstanciaPro.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimEqpInstanciaPro.setFeCreacion(auditoriaDTO.getFecha());
				pgimEqpInstanciaPro.setUsCreacion(auditoriaDTO.getUsername());
				pgimEqpInstanciaPro.setIpCreacion(auditoriaDTO.getTerminal());

				equipoInstanciaProcesoRepository.save(pgimEqpInstanciaPro);

			}

		}

	}

	/**
	 * Permite deseleccionar un participante del equipo de una instancia de proceso
	 * 
	 * @param pgimEqpInstanciaProDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	@Override
	public void deseleccionarEquipoInstancia(PgimEqpInstanciaPro pgimEqpInstanciaPro, AuditoriaDTO auditoriaDTO)
			throws Exception {

		pgimEqpInstanciaPro.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimEqpInstanciaPro.setFeActualizacion(auditoriaDTO.getFecha());
		pgimEqpInstanciaPro.setUsActualizacion(auditoriaDTO.getUsername());
		pgimEqpInstanciaPro.setIpActualizacion(auditoriaDTO.getTerminal());

		this.equipoInstanciaProcesoRepository.save(pgimEqpInstanciaPro);
	}

	/***
	 * Permite obtener un objeto entidad de Equipo de Instancia de Proceso con el
	 * valor idEquipoInstanciaPro.
	 * 
	 * @param idEquipoInstanciaPro
	 * @return
	 */
	@Override
	public PgimEqpInstanciaPro getByidEquipoInstanciaPro(Long idEquipoInstanciaPro) {

		return this.equipoInstanciaProcesoRepository.findById(idEquipoInstanciaPro).orElse(null);
	}

	/**
	 * Permite modificar un participante del equipo de una instancia de proceso
	 * 
	 * @param pgimEqpInstanciaProDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	@Override
	public void modificarEquipoInstancia(PgimEqpInstanciaPro pgimEqpInstanciaProActual,
			PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO, AuditoriaDTO auditoriaDTO) throws Exception {

		pgimEqpInstanciaProActual.setFlEsResponsable(pgimEqpInstanciaProDTO.getFlEsResponsable());// Solo se modificará
																									// el estado del
																									// campo responsable

		pgimEqpInstanciaProActual.setNoCargoPersonaEquipo(pgimEqpInstanciaProDTO.getNoCargoPersonaEquipo());
		pgimEqpInstanciaProActual.setNoPerfilPersonaEquipo(pgimEqpInstanciaProDTO.getNoPerfilPersonaEquipo());
		pgimEqpInstanciaProActual.setNoPrefijoPersonaEquipo(pgimEqpInstanciaProDTO.getNoPrefijoPersonaEquipo());
		
		pgimEqpInstanciaProActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimEqpInstanciaProActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimEqpInstanciaProActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.equipoInstanciaProcesoRepository.save(pgimEqpInstanciaProActual);
	}

	@Override
	public List<PgimInstanciaProcesDTO> existeNuExpediente(Long idInstanciaProceso, String descNuExpedienteSiged) {
		List<PgimInstanciaProcesDTO> pPgimInstanciaProcesDTO = this.instanciaProcesRepository
				.existeNuExpediente(idInstanciaProceso, descNuExpedienteSiged);

		return pPgimInstanciaProcesDTO;
	}

	@Override
	public List<PgimEqpInstanciaProDTO> obtenerParticipantesInstanciaProXRol(Long idInstanciaProceso,
			Long idRolProceso) {
		List<PgimEqpInstanciaProDTO> participantes = equipoInstanciaProcesoRepository
				.obtenerParticipantesInstanciaProXRol(idInstanciaProceso, idRolProceso);
		return participantes;
	}

	@Override
	public List<PgimEqpInstanciaProDTO> obtenerParticipantesRolSupervisor(Long idInstanciaProceso) {
		List<PgimEqpInstanciaProDTO> participantes = equipoInstanciaProcesoRepository
				.obtenerParticipantesRolSupervisor(idInstanciaProceso, ConstantesUtil.PROCESO_ROL_SUPERVISOR);
		return participantes;
	}
	
	@Override
	public List<PgimEqpInstanciaProDTO> listarPersonalAsignableContraConRol(Long idInstanciaProceso, Long idRolProceso, String palabraClave) {
		List<PgimEqpInstanciaProDTO> participantes = equipoInstanciaProcesoRepository
				.listarPersonalAsignableContraConRol(idInstanciaProceso, idRolProceso, palabraClave);
		return participantes;
	}
	
	@Override
	public List<PgimEqpInstanciaProDTO> listarPersonalAsignableOsiConRol(Long idInstanciaProceso, Long idRolProceso, String palabraClave) {
		List<PgimEqpInstanciaProDTO> participantes = equipoInstanciaProcesoRepository
				.listarPersonalAsignableOsiConRol(idInstanciaProceso, idRolProceso, palabraClave);
		return participantes;
	}
	
	
	@Override
	public PgimInstanciaProcesDTO obtenerInstanciaProcesoPorNuExpediente(String nuExpedienteSiged) {
		PgimInstanciaProcesDTO pPgimInstanciaProcesDTO = this.instanciaProcesRepository
				.obtenerInstanciaProcesoPorNuExpediente(nuExpedienteSiged);

		return pPgimInstanciaProcesDTO;
	}

	@Override
	public List<PgimInstanciaProcesDTO> listarInstanciaProcesPorProceso(Long idProceso) {
		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesRepository
				.listarInstanciaProcesPorProceso(idProceso);
		return lPgimInstanciaProcesDTO;
	}

	@Override
	public List<PgimProcesoDTO> listarProcesos() {
		List<PgimProcesoDTO> lPgimProcesoDTO = this.procesoRepository.listarProcesos();

		return lPgimProcesoDTO;
	}

	@Override
	public List<PgimProcesoDTO> listarProcesosForIndicar() {
		List<PgimProcesoDTO> lPgimProcesoDTO = this.procesoRepository.listarProcesosForIndicar();

		return lPgimProcesoDTO;
	}

	@Override
	public PgimInstanciaProces obtenerInstanciaProcesoPorInstanciaPaso(Long idInstanciaPaso) {
	
		PgimInstanciaProces pgimInstanciaProcess = null;

		PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null);

		if (pgimInstanciaPaso != null) {
			pgimInstanciaProcess = this.instanciaProcesRepository
					.findById(pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso()).orElse(null);
		}
		
		return pgimInstanciaProcess;
	}
	

}
