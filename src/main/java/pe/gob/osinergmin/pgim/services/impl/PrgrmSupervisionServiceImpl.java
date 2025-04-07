package pe.gob.osinergmin.pgim.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGenProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLineaProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPresupuestoDsEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimResumenLineaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSuperDsUmAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSuperProgramadaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupernpDsEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimVwPrgrmMontoEspAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimVwProgramPropuestoRnkDTO;
import pe.gob.osinergmin.pgim.dtos.SeguimientoProgramaDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimEstandarPrograma;
import pe.gob.osinergmin.pgim.models.entity.PgimGenPrgMes;
import pe.gob.osinergmin.pgim.models.entity.PgimGenPrgRanking;
import pe.gob.osinergmin.pgim.models.entity.PgimGenPrgUfiscaliza;
import pe.gob.osinergmin.pgim.models.entity.PgimGenPrograma;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimItemProgramaSupe;
import pe.gob.osinergmin.pgim.models.entity.PgimLineaPrograma;
import pe.gob.osinergmin.pgim.models.entity.PgimMotivoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUm;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.EstandarProgramaRepository;
import pe.gob.osinergmin.pgim.models.repository.GenPrgMesRepository;
import pe.gob.osinergmin.pgim.models.repository.GenPrgRankingRepository;
import pe.gob.osinergmin.pgim.models.repository.GenPrgUfiscalizaRepository;
import pe.gob.osinergmin.pgim.models.repository.GenProgramaRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemProgramaSupeRepository;
import pe.gob.osinergmin.pgim.models.repository.LineaProgramaRepository;
import pe.gob.osinergmin.pgim.models.repository.MotivoSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSeguimientoAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmRepository;
import pe.gob.osinergmin.pgim.models.repository.ResumenLineaAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.SubTipoSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.models.repository.VwPrgrmMontoEspAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.VwProgramPropuestoRnkRepository;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.PrgrmSupervisionService;
import pe.gob.osinergmin.pgim.services.UnidadMineraService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Supervision
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 20/07/2020
 * @fecha_de_ultima_actualización: 10/08/2020
 */
@Service
@Transactional(readOnly = true)
public class PrgrmSupervisionServiceImpl implements PrgrmSupervisionService {

	@Autowired
	private PrgrmSupervisionRepository prgrmSupervisionRepository;

	@Autowired
	private EstandarProgramaRepository estandarProgramaRepository;

	@Autowired
	private ItemProgramaSupeRepository itemProgramaSupeRepository;

	@Autowired
	private LineaProgramaRepository lineaProgramaRepository;

	@Autowired
	private PrgrmSupervisionAuxRepository prgrmSupervisionAuxRepository;

	@Autowired
	private InstanciaProcesService instanciaProcesService;

	@Autowired
	private InstanciaProcesRepository instanciaProcesRepository;

	@Autowired
	private FlujoTrabajoService flujoTrabajoService;

	@Autowired
	private SubTipoSupervisionRepository subTipoSupervisionRepository;

	@Autowired
	private MotivoSupervisionRepository motivoSupervisionRepository;
	
	@Autowired
	private PrgrmSeguimientoAuxRepository prgrmSeguimientoAuxRepository;

	@Autowired
	private ResumenLineaAuxRepository resumenLineaAuxRepository;
	
	@Autowired
	private VwPrgrmMontoEspAuxRepository vwPrgrmMontoEspAuxRepository;
	
	@Autowired
	private ValorParametroRepository valorParametroRepository;
	
	@Autowired
	private RankingRiesgoRepository rankingRiesgoRepository;
	
	@Autowired
	private GenProgramaRepository genProgramaRepository;

	@Autowired
	private GenPrgRankingRepository genPrgRankingRepository;

	@Autowired
	private RankingUmRepository rankingUmRepository;

	@Autowired
	private GenPrgUfiscalizaRepository genPrgUfiscalizaRepository;

	@Autowired
	private GenPrgMesRepository genPrgMesRepository;

	@Autowired
	private VwProgramPropuestoRnkRepository vwProgramPropuestoRnkRepository;

	@Autowired
	private UnidadMineraService unidadMineraService;

	@Override
	public List<PgimPrgrmSupervisionDTO> obtenerPrgrmSupervision() {
		List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = prgrmSupervisionRepository.obtenerPrgrmSupervision(EValorParametro.PREST_APRBDA.toString());
		return lPgimPrgrmSupervisionDTO;
	}

	@Override
	public List<PgimPrgrmSupervisionDTO> obtenerProgramaParaAsignacion() {
		List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = prgrmSupervisionRepository
				.obtenerProgramaParaAsignacion();
		return lPgimPrgrmSupervisionDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimItemProgramaSupeDTO procesarModificarItemPrograma(PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO,
			PgimItemProgramaSupe pgimItemProgramaSupeActual, AuditoriaDTO auditoriaDTO) {

		PgimItemProgramaSupeDTO PgimItemProgramaSupeDTOModificado = null;

		// Actualizar Sub tipo de supervisión
		pgimItemProgramaSupeActual.setPgimSubtipoSupervision(new PgimSubtipoSupervision());
		pgimItemProgramaSupeActual.getPgimSubtipoSupervision()
				.setIdSubtipoSupervision(pgimItemProgramaSupeDTO.getIdSubtipoSupervision());
		
				// Actualizar costo estimado
		List<PgimEstandarProgramaDTO> lpgimEstandarProgramaDTO;
		BigDecimal moPorSupervision;
		lpgimEstandarProgramaDTO = this.listarCostosEstimados(pgimItemProgramaSupeDTO.getIdLineaPrograma(),
				pgimItemProgramaSupeDTO.getIdSubtipoSupervision());
		moPorSupervision = lpgimEstandarProgramaDTO.get(0).getMoPorSupervision();
	
		pgimItemProgramaSupeActual.setMoCostoEstimadoSupervision(moPorSupervision);
		
		// actualizar fecha estimada
		Calendar feMesEstimado = Calendar.getInstance();
		feMesEstimado.setTime(pgimItemProgramaSupeDTO.getFeMesEstimado());
		feMesEstimado.set(Calendar.DAY_OF_MONTH, 1);

		pgimItemProgramaSupeActual.setFeMesEstimado(feMesEstimado.getTime());
		pgimItemProgramaSupeActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimItemProgramaSupeActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimItemProgramaSupeActual.setIpActualizacion(auditoriaDTO.getTerminal());
		PgimItemProgramaSupe pgimItemProgramaSupeActualizado = this.itemProgramaSupeRepository
				.save(pgimItemProgramaSupeActual);
		PgimItemProgramaSupeDTOModificado = this
				.obtenerItemProgramaSupeDtoById(pgimItemProgramaSupeActualizado.getIdItemProgramaSupe());

		return PgimItemProgramaSupeDTOModificado;

	}

	@Transactional(readOnly = false)
	@Override
	public PgimItemProgramaSupeDTO modificarItemProgramaSupe(PgimItemProgramaSupe pgimItemProgramaSupe,
			AuditoriaDTO auditoriaDTO) {

		pgimItemProgramaSupe.setFeActualizacion(auditoriaDTO.getFecha());
		pgimItemProgramaSupe.setUsActualizacion(auditoriaDTO.getUsername());
		pgimItemProgramaSupe.setIpActualizacion(auditoriaDTO.getTerminal());
		this.itemProgramaSupeRepository.save(pgimItemProgramaSupe);
		
		PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO = this
				.obtenerItemProgramaSupeDtoById(pgimItemProgramaSupe.getIdItemProgramaSupe());

		return pgimItemProgramaSupeDTO;
	}

	@Override
	public PgimItemProgramaSupe getItemProgramaById(Long idItemPrograma) {
		return this.itemProgramaSupeRepository.findById(idItemPrograma).orElse(null);
	}

	/**
	 * Permite asignar un programa, usado en el dialogo Asignar Programa
	 * 
	 * @param pgimPrgrmSupervisionDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimPrgrmSupervisionAuxDTO asignarPrograma(PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {

		// Se asegura la instancia del proceso
		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService.asegurarInstanciasProceso(
				ConstantesUtil.PARAM_PROCESO_PROGRAMACION, pgimPrgrmSupervisionDTO.getIdProgramaSupervision(),
				auditoriaDTO);

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

		PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository
				.findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

		// Se actualiza la instancia del proceso en el registro del programa
		this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);
		pgimPrgrmSupervisionDTO.setIdInstanciaProceso(pgimInstanciaProcesActual.getIdInstanciaProceso());

		// Se crea la asignación
		// pgimPrgrmSupervisionDTO.setIdSupervision(pgimSupervisionDTOCreado.getIdSupervision());

		PgimInstanciaPasoDTO pgimInstanciaPasoDTO = new PgimInstanciaPasoDTO();
		pgimInstanciaPasoDTO.setIdRelacionPaso(pgimPrgrmSupervisionDTO.getDescIdRelacionPaso());
		pgimInstanciaPasoDTO.setDescIdPersonalOsiDestino(pgimPrgrmSupervisionDTO.getDescIdPersonalOsi());
		pgimInstanciaPasoDTO.setDeMensaje(pgimPrgrmSupervisionDTO.getDescDeMensaje());

		this.flujoTrabajoService.crearInstanciaPasoInicial(pgimInstanciaProcesActual, pgimInstanciaPasoDTO,
				auditoriaDTO);

		// Crear línea base
		PgimLineaPrograma pgimLineaPrograma = this.crearLineaBase(pgimPrgrmSupervisionDTO.getIdProgramaSupervision(),
				auditoriaDTO);

		// obtener objeto programaAux de retorno
		PgimPrgrmSupervisionAuxDTO pgimProgramaAuxDTO = this.prgrmSupervisionAuxRepository
				.obtenerProgramaAuxById(pgimPrgrmSupervisionDTO.getIdProgramaSupervision());

		// Generar Datos Estándares de la Programación
		this.crearDatosEstandar(pgimLineaPrograma.getIdLineaPrograma(), pgimProgramaAuxDTO.getIdEspecialidad(),
				auditoriaDTO);

		return pgimProgramaAuxDTO;
	}

	@Transactional(readOnly = false)
	private PgimLineaPrograma crearLineaBase(Long idProgramSupervision, AuditoriaDTO auditoriaDTO) {

		PgimLineaPrograma pgimLineaPrograma = new PgimLineaPrograma();

		pgimLineaPrograma.setPgimPrgrmSupervision(new PgimPrgrmSupervision());
		pgimLineaPrograma.getPgimPrgrmSupervision().setIdProgramaSupervision(idProgramSupervision);

		pgimLineaPrograma.setLineaProgramaEstado(new PgimValorParametro());
		pgimLineaPrograma.getLineaProgramaEstado().setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.PREST_CREADA.toString()));

		pgimLineaPrograma.setFeLineaPrograma(auditoriaDTO.getFecha());

		pgimLineaPrograma.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimLineaPrograma.setFeCreacion(auditoriaDTO.getFecha());
		pgimLineaPrograma.setUsCreacion(auditoriaDTO.getUsername());
		pgimLineaPrograma.setIpCreacion(auditoriaDTO.getTerminal());

		PgimLineaPrograma pgimLineaProgramaCreado = this.lineaProgramaRepository.save(pgimLineaPrograma);

		return pgimLineaProgramaCreado;
	}

	@Transactional(readOnly = false)
	private void crearDatosEstandar(Long idLineaPrograma, Long idEspecialidad, AuditoriaDTO auditoriaDTO) {

		// Obtener subtipos de supervisión x especialidad --> Programadas
		List<PgimSubtipoSupervisionDTO> lpgimSubtipoSupervisionDTO = this.subTipoSupervisionRepository
				.obtenerSubTipoSupervisionPorIdEspecialidad(idEspecialidad);

		for (PgimSubtipoSupervisionDTO pgimSubtipoSupervisionDTO : lpgimSubtipoSupervisionDTO) {

			if (pgimSubtipoSupervisionDTO.getIdTipoSupervision()
					.equals(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.SUPER_FSCLZCION_PRGRMDA.toString()))) {

				PgimEstandarPrograma pgimEstandarPrograma = new PgimEstandarPrograma();

				pgimEstandarPrograma.setPgimLineaPrograma(new PgimLineaPrograma());
				pgimEstandarPrograma.getPgimLineaPrograma().setIdLineaPrograma(idLineaPrograma);

				pgimEstandarPrograma.setPgimSubtipoSupervision(new PgimSubtipoSupervision());
				pgimEstandarPrograma.getPgimSubtipoSupervision()
						.setIdSubtipoSupervision(pgimSubtipoSupervisionDTO.getIdSubtipoSupervision());

				pgimEstandarPrograma.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimEstandarPrograma.setFeCreacion(auditoriaDTO.getFecha());
				pgimEstandarPrograma.setUsCreacion(auditoriaDTO.getUsername());
				pgimEstandarPrograma.setIpCreacion(auditoriaDTO.getTerminal());

				this.estandarProgramaRepository.save(pgimEstandarPrograma);
			}

		}

		// Obtener motivos de supervisión --> no programadas
		List<PgimMotivoSupervisionDTO> lpgimMotivoSupervisionDTO = this.motivoSupervisionRepository
				.obtenerMotivoSupervisionParaDatosEstandar();

		for (PgimMotivoSupervisionDTO pgimMotivoSupervisionDTO : lpgimMotivoSupervisionDTO) {

			if (pgimMotivoSupervisionDTO.getIdTipoSupervision()
					.equals(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.SUPER_FSCLZCION_NO_PRGRMDA.toString()))) {

				PgimEstandarPrograma pgimEstandarPrograma = new PgimEstandarPrograma();

				pgimEstandarPrograma.setPgimLineaPrograma(new PgimLineaPrograma());
				pgimEstandarPrograma.getPgimLineaPrograma().setIdLineaPrograma(idLineaPrograma);

				pgimEstandarPrograma.setPgimMotivoSupervision(new PgimMotivoSupervision());
				pgimEstandarPrograma.getPgimMotivoSupervision()
						.setIdMotivoSupervision(pgimMotivoSupervisionDTO.getIdMotivoSupervision());

				pgimEstandarPrograma.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimEstandarPrograma.setFeCreacion(auditoriaDTO.getFecha());
				pgimEstandarPrograma.setUsCreacion(auditoriaDTO.getUsername());
				pgimEstandarPrograma.setIpCreacion(auditoriaDTO.getTerminal());

				this.estandarProgramaRepository.save(pgimEstandarPrograma);
			}

		}
	}

	@Override
	public Page<PgimPrgrmSupervisionDTO> listarProgramas(PgimPrgrmSupervisionDTO filtroPrograma, Pageable paginador) {
		Page<PgimPrgrmSupervisionDTO> pPgimPrgrmSupervisionDTO = this.prgrmSupervisionRepository.listarProgramas(
				filtroPrograma.getIdDivisionSupervisora(), filtroPrograma.getDescNuAnio(),
				filtroPrograma.getIdEspecialidad(), filtroPrograma.getTextoBusqueda(), paginador);
		return pPgimPrgrmSupervisionDTO;
	}

	/**
	 * Permite obtener la lista de programas (Aux)
	 * 
	 * @param filtroPrograma
	 * @param paginador
	 * @return
	 */
	@Override
	public Page<PgimPrgrmSupervisionAuxDTO> listarProgramasAux(PgimPrgrmSupervisionAuxDTO filtroPrograma,
			Pageable paginador, AuditoriaDTO auditoriaDTO) {

		//Obtenemos permiso "listar todas"
    	boolean flagPermisoTodas = false;
    	for (String permiso : auditoriaDTO.getAuthorities()) {	    		 
    		
    		if(permiso.equals(ConstantesUtil.PERMISO_VER_PROGRAMACIONES_TODAS)) {
    			flagPermisoTodas = true;
    		}
    	}
		
		String usuarioAsignado = "";
		if (filtroPrograma.getDescFlagMisAsignaciones() != null && filtroPrograma.getDescFlagMisAsignaciones().equals("1")) {
			usuarioAsignado = auditoriaDTO.getUsername();
		}

		String usuarioOrigen = "";
		if (filtroPrograma.getDescFlagMisAsignaciones() != null && filtroPrograma.getDescFlagMisAsignaciones().equals("2")) {
			usuarioOrigen = auditoriaDTO.getUsername();
		}
		
		//Si el usuario no tiene permiso para ver los registros de otros usaurios 
        //se le setea el mismo usaurio para los filtros 
        if(
        	( filtroPrograma.getDescFlagMisAsignaciones()==null || !filtroPrograma.getDescFlagMisAsignaciones().trim().equals("1") )                	
        	&&  !flagPermisoTodas  
        		) {                	
        	usuarioAsignado = auditoriaDTO.getUsername();        	
        }

		Page<PgimPrgrmSupervisionAuxDTO> pPgimPrgrmSupervisionDTO = this.prgrmSupervisionAuxRepository
				.listarProgramasAux(
						filtroPrograma.getIdDivisionSupervisora(),
						filtroPrograma.getNuAnio(),
						filtroPrograma.getIdEspecialidad(),
						filtroPrograma.getDescFlagMisAsignaciones(),
						usuarioOrigen,
						usuarioAsignado,
						filtroPrograma.getPersonaAsignada(),
						filtroPrograma.getTextoBusqueda(),
						paginador);

		return pPgimPrgrmSupervisionDTO;
	}

	@Override
	public PgimPrgrmSupervisionDTO obtenerPrograma(Long idProgramaSupervision) {
		return this.prgrmSupervisionRepository.obtenerPrograma(idProgramaSupervision);
	}

	@Override
	public List<PgimEstandarProgramaDTO> listarEstandarPrograma(Long idLineaPrograma) {
		return this.estandarProgramaRepository.listarEstandarPrograma(idLineaPrograma);
	}

	@Override
	public List<PgimEstandarProgramaDTO> listarCostosEstimados(Long idLineaPrograma, Long idSubtipoSupervision) {
		return this.estandarProgramaRepository.listarCostosEstimados(idLineaPrograma, idSubtipoSupervision);
	}

	@Override
	public Page<PgimItemProgramaSupeDTO> listarItemsProgramaPendientes(Long idLineaPrograma, Pageable paginador) {
		Page<PgimItemProgramaSupeDTO> pgimItemProgramaSupeDTO = this.itemProgramaSupeRepository
				.listarItemsProgramaPendientes(idLineaPrograma, paginador, EValorParametro.PREST_APRBDA.toString());

		return pgimItemProgramaSupeDTO;
	}

	@Override
	public Page<PgimItemProgramaSupeDTO> listarItemsProgramas(Long idLineaPrograma, Pageable paginador) {
		Page<PgimItemProgramaSupeDTO> pgimItemProgramaSupeDTO = this.itemProgramaSupeRepository
				.listarItemsProgramas(idLineaPrograma, paginador);
				return pgimItemProgramaSupeDTO;
	}

	@Override
	public List<PgimEstandarProgramaDTO> listarNoProgramada(Long idLineaPrograma) {
		return this.estandarProgramaRepository.listarNoProgramada(idLineaPrograma);
	}

	/**
	 * Permite obtener un objeto de la entidad PgimItemProgramaSupeDTO por
	 * idItemProgramaSupe
	 * 
	 * @param idItemProgramaSupe
	 * @return
	 */
	@Override
	public PgimItemProgramaSupeDTO obtenerItemProgramaSupeDtoById(Long idItemProgramaSupe) {
		PgimItemProgramaSupeDTO itemProgramaSuper = this.itemProgramaSupeRepository
				.getItemProgramaSupeById(idItemProgramaSupe);
		return itemProgramaSuper;
	}

	/**
	 * Permite cancelar un ítem de programa de supervisión
	 * 
	 * @param idItemProgramaSupe
	 * @param motivoCancelacion
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public void cancelarItemProgramaSupe(PgimItemProgramaSupeDTO pgimItemProgramaSupeDTOaCancelar,
			AuditoriaDTO auditoriaDTO) {// throws Exception

		PgimItemProgramaSupe pgimIPSaCancelar = this.itemProgramaSupeRepository
				.findById(pgimItemProgramaSupeDTOaCancelar.getIdItemProgramaSupe()).orElse(null);

		pgimIPSaCancelar.setDeMotivoCancelacion(pgimItemProgramaSupeDTOaCancelar.getDeMotivoCancelacion());
		pgimIPSaCancelar.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimIPSaCancelar.setFeActualizacion(auditoriaDTO.getFecha());
		pgimIPSaCancelar.setUsActualizacion(auditoriaDTO.getUsername());
		pgimIPSaCancelar.setIpActualizacion(auditoriaDTO.getTerminal());

		this.itemProgramaSupeRepository.save(pgimIPSaCancelar);
	}

	@Override
	public PgimLineaProgramaDTO obtenerLineaProgramaActual(Long idProgramaSupervision) {

		return lineaProgramaRepository.obtenerLineaProgramaActual(idProgramaSupervision);
	}

	@Override
	public PgimItemProgramaSupe getItemProgramaSupeById(Long IdItemProgramaSupe) {
		return this.itemProgramaSupeRepository.findById(IdItemProgramaSupe).orElse(null);
	}

	/**
	 * Permite obtener el listaso de líneas base del programa de supervisión.
	 * 
	 * @param idProgramaSupervision
	 * @return
	 */
	@Override
	public List<PgimLineaProgramaDTO> listarLineaBase(Long idProgramaSupervision) {

		List<PgimLineaProgramaDTO> lPgimLineaProgramaDTO = this.lineaProgramaRepository
				.listarLineaBase(idProgramaSupervision);

		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");

		int cantidad = lPgimLineaProgramaDTO.size();

		for (PgimLineaProgramaDTO pgimLineaProgramaDTO : lPgimLineaProgramaDTO) {
			cantidad--;
			dateString = sdfr.format(pgimLineaProgramaDTO.getFeLineaPrograma());
			String feLineaProgramaDesc = "Línea base " + cantidad + " : " + dateString + " ("
					+ pgimLineaProgramaDTO.getFeLineaProgramaDesc() + ")";
			pgimLineaProgramaDTO.setFeLineaProgramaDesc(feLineaProgramaDesc);
		}

		return lPgimLineaProgramaDTO;

	}

	/**
	 * Permite actualizar las Lineas Base
	 * 
	 */
	public void actualizarLineaBase(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
			AuditoriaDTO auditoriaDTO) {

		if (pgimInstanciaProces != null) {

			// Obtenemos el registro de la programación
			PgimPrgrmSupervisionAuxDTO pgimPrgrmSupervisionAuxDTO = this.prgrmSupervisionAuxRepository
					.obtenerProgramaAuxByIdInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());

			if (pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
					.equals(ConstantesUtil.PARAM_RELACION_APROBAR_PROG_REALIZAR_SEGUIMIENTO)) {
				// APROBAR NUEVA LB CREADA Y PASAR A SUSTITUIDA A LA LB APROBADA ANTERIOR

				// 1.CAMBIAR DE ESTADO DE LA LB APROBADA A SUSTITUIDA
				List<PgimLineaProgramaDTO> listLBaprobadas = this.lineaProgramaRepository.listarLineaBasePorEstado(
						pgimPrgrmSupervisionAuxDTO.getIdProgramaSupervision(), this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.PREST_APRBDA.toString()));

				for (PgimLineaProgramaDTO pgimLineaProgramaDTO : listLBaprobadas) {

					PgimLineaPrograma pgimLBanterior = this.lineaProgramaRepository
							.findById(pgimLineaProgramaDTO.getIdLineaPrograma()).orElse(null);

					pgimLBanterior.setLineaProgramaEstado(this.valorParametroRepository.obtenerValorParametro(EValorParametro.PREST_SSTTUIDA.toString()));

					pgimLBanterior.setFeActualizacion(auditoriaDTO.getFecha());
					pgimLBanterior.setUsActualizacion(auditoriaDTO.getUsername());
					pgimLBanterior.setIpActualizacion(auditoriaDTO.getTerminal());

					this.lineaProgramaRepository.save(pgimLBanterior);
				}

				// 2.CAMBIAR DE ESTADO DE LA LB CREADA A APROBADA (NUEVA LB)
				List<PgimLineaProgramaDTO> listLBcreadas = this.lineaProgramaRepository.listarLineaBasePorEstado(
						pgimPrgrmSupervisionAuxDTO.getIdProgramaSupervision(), this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.PREST_CREADA.toString()));

				if (listLBcreadas.size() == 0)
					throw new PgimException(TipoResultado.WARNING, "No se encontró la línea base a aprobar"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario

				if (listLBcreadas.size() > 1)
					throw new PgimException(TipoResultado.WARNING, "Se encontró más de una línea base a aprobar"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario

				PgimLineaPrograma pgimLBnueva = this.lineaProgramaRepository
						.findById(listLBcreadas.get(0).getIdLineaPrograma()).orElse(null);
				
				PgimValorParametro lineaProgramaEstado = new PgimValorParametro();
				lineaProgramaEstado.setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.PREST_APRBDA.toString()));				
				pgimLBnueva.setLineaProgramaEstado(lineaProgramaEstado);
				pgimLBnueva.setFeActualizacion(auditoriaDTO.getFecha());
				pgimLBnueva.setUsActualizacion(auditoriaDTO.getUsername());
				pgimLBnueva.setIpActualizacion(auditoriaDTO.getTerminal());

				this.lineaProgramaRepository.save(pgimLBnueva);

			} else if (pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
					.equals(ConstantesUtil.PARAM_RELACION_REALIZAR_SEGUIMIENTO_ELABORAR_PROG)) {
				// CREAR UNA NUEVA LB (BORRADOR) PARA MODIFICAR EL PROGRAMA

				// 1.-generar nueva LB en estado creada --> PGIM_TC_LINEA_PROGRAMA
				PgimLineaPrograma pgimLBaCrear = new PgimLineaPrograma();
				pgimLBaCrear.setPgimPrgrmSupervision(new PgimPrgrmSupervision());
				pgimLBaCrear.getPgimPrgrmSupervision()
						.setIdProgramaSupervision(pgimPrgrmSupervisionAuxDTO.getIdProgramaSupervision());
				pgimLBaCrear.setLineaProgramaEstado(new PgimValorParametro());
				pgimLBaCrear.getLineaProgramaEstado().setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.PREST_CREADA.toString()));
				pgimLBaCrear.setFeLineaPrograma(auditoriaDTO.getFecha());
				pgimLBaCrear.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimLBaCrear.setFeCreacion(auditoriaDTO.getFecha());
				pgimLBaCrear.setUsCreacion(auditoriaDTO.getUsername());
				pgimLBaCrear.setIpCreacion(auditoriaDTO.getTerminal());
				PgimLineaPrograma pgimLineaProgramaCreada = this.lineaProgramaRepository.save(pgimLBaCrear);

				// 2.-obtener lista de datos estandar actuales
				List<PgimEstandarProgramaDTO> lPgimEstandarProgramaDTO = this.estandarProgramaRepository
						.listarEstandarPrograma(pgimPrgrmSupervisionAuxDTO.getIdLineaPrograma());

				// 3.-crear datos estandar para la nueva LB --> PGIM_TD_ESTANDAR_PROGRAMA
				for (PgimEstandarProgramaDTO pgimEstandarProgramaDTO : lPgimEstandarProgramaDTO) {
					PgimEstandarPrograma pgimEPaCrear = new PgimEstandarPrograma();

					if (pgimEstandarProgramaDTO.getIdSubtipoSupervision() != null) {
						pgimEPaCrear.setPgimSubtipoSupervision(new PgimSubtipoSupervision());
						pgimEPaCrear.getPgimSubtipoSupervision()
								.setIdSubtipoSupervision(pgimEstandarProgramaDTO.getIdSubtipoSupervision());
					}

					if (pgimEstandarProgramaDTO.getIdMotivoSupervision() != null) {
						pgimEPaCrear.setPgimMotivoSupervision(new PgimMotivoSupervision());
						pgimEPaCrear.getPgimMotivoSupervision()
								.setIdMotivoSupervision(pgimEstandarProgramaDTO.getIdMotivoSupervision());
					}

					pgimEPaCrear.setPgimLineaPrograma(new PgimLineaPrograma());
					pgimEPaCrear.getPgimLineaPrograma()
							.setIdLineaPrograma(pgimLineaProgramaCreada.getIdLineaPrograma());

					pgimEPaCrear.setEstandarProgramaBase(new PgimEstandarPrograma());
					pgimEPaCrear.getEstandarProgramaBase()
							.setIdEstandarPrograma(pgimEstandarProgramaDTO.getIdEstandarPrograma());

					pgimEPaCrear.setMoPorSupervision(pgimEstandarProgramaDTO.getMoPorSupervision());
					pgimEPaCrear.setCaDiasSupervision(pgimEstandarProgramaDTO.getCaDiasSupervision());
					pgimEPaCrear.setCaSupervisiones(pgimEstandarProgramaDTO.getCaSupervisiones());
					pgimEPaCrear.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimEPaCrear.setFeCreacion(auditoriaDTO.getFecha());
					pgimEPaCrear.setUsCreacion(auditoriaDTO.getUsername());
					pgimEPaCrear.setIpCreacion(auditoriaDTO.getTerminal());

					this.estandarProgramaRepository.save(pgimEPaCrear);

				}

				// 4.-obtener lista de items del programa actuales
				List<PgimItemProgramaSupeDTO> lPgimItemProgramaSupeDTO = this.itemProgramaSupeRepository
						.listarItemsPorLB(pgimPrgrmSupervisionAuxDTO.getIdLineaPrograma());

				// 5.-crear items del programa para la nueva LB --> PGIM_TC_ITEM_PROGRAMA_SUPE
				for (PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO : lPgimItemProgramaSupeDTO) {
					PgimItemProgramaSupe pgimIPSaCrear = new PgimItemProgramaSupe();

					pgimIPSaCrear.setPgimSubtipoSupervision(new PgimSubtipoSupervision());
					pgimIPSaCrear.getPgimSubtipoSupervision()
							.setIdSubtipoSupervision(pgimItemProgramaSupeDTO.getIdSubtipoSupervision());

					pgimIPSaCrear.setPgimUnidadMinera(new PgimUnidadMinera());
					pgimIPSaCrear.getPgimUnidadMinera().setIdUnidadMinera(pgimItemProgramaSupeDTO.getIdUnidadMinera());

					if (pgimItemProgramaSupeDTO.getIdSupervision() != null) {
						pgimIPSaCrear.setPgimSupervision(new PgimSupervision());
						pgimIPSaCrear.getPgimSupervision().setIdSupervision(pgimItemProgramaSupeDTO.getIdSupervision());
					}

					pgimIPSaCrear.setItemProgramaSupeBase(new PgimItemProgramaSupe());
					pgimIPSaCrear.getItemProgramaSupeBase()
							.setIdItemProgramaSupe(pgimItemProgramaSupeDTO.getIdItemProgramaSupe());

					pgimIPSaCrear.setPgimLineaPrograma(new PgimLineaPrograma());
					pgimIPSaCrear.getPgimLineaPrograma()
							.setIdLineaPrograma(pgimLineaProgramaCreada.getIdLineaPrograma());

					pgimIPSaCrear.setFeMesEstimado(pgimItemProgramaSupeDTO.getFeMesEstimado());
					pgimIPSaCrear
							.setMoCostoEstimadoSupervision(pgimItemProgramaSupeDTO.getMoCostoEstimadoSupervision());
					pgimIPSaCrear.setDeItemPrograma(pgimItemProgramaSupeDTO.getDeItemPrograma());

					pgimIPSaCrear.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimIPSaCrear.setFeCreacion(auditoriaDTO.getFecha());
					pgimIPSaCrear.setUsCreacion(auditoriaDTO.getUsername());
					pgimIPSaCrear.setIpCreacion(auditoriaDTO.getTerminal());

					this.itemProgramaSupeRepository.save(pgimIPSaCrear);

				}
			} else if (pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
					.equals(ConstantesUtil.PARAM_RELACION_REALIZAR_SEGUIMIENTO_CERRAR_PROG)) {
				// CERRAR UNA LB (FIN DEL PERIODO DE TRABAJO)

				// 1.-obtener LB actual (aprobada)
				PgimLineaPrograma pgimLBaprobada = this.lineaProgramaRepository
						.findById(pgimPrgrmSupervisionAuxDTO.getIdLineaPrograma()).orElse(null);

				// 2.- Actualizar estado de la LB aprobada a cerrada
				Long estadoCerrado = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.PREST_CRRDA.toString());
				pgimLBaprobada.setLineaProgramaEstado(new PgimValorParametro());
				pgimLBaprobada.getLineaProgramaEstado().setIdValorParametro(estadoCerrado);

				pgimLBaprobada.setFeActualizacion(auditoriaDTO.getFecha());
				pgimLBaprobada.setUsActualizacion(auditoriaDTO.getUsername());
				pgimLBaprobada.setIpActualizacion(auditoriaDTO.getTerminal());

				this.lineaProgramaRepository.save(pgimLBaprobada);

			} else if (pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
					.equals(ConstantesUtil.PARAM_RELACION_ANULAR_LB_REALIZAR_SEGUIMIENTO)) {
				// ANULAR UNA LB CREADA (BORRADOR)

				// 1.-obtener LB actual (CREADA)
				PgimLineaPrograma pgimLBcreada = this.lineaProgramaRepository
						.findById(pgimPrgrmSupervisionAuxDTO.getIdLineaPrograma()).orElse(null);

				// 2.- Actualizar estado de la LB creada a anulada
				Long estadoAnulado = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.PREST_ANLDA.toString());
				pgimLBcreada.setLineaProgramaEstado(new PgimValorParametro());
				pgimLBcreada.getLineaProgramaEstado().setIdValorParametro(estadoAnulado);

				pgimLBcreada.setFeActualizacion(auditoriaDTO.getFecha());
				pgimLBcreada.setUsActualizacion(auditoriaDTO.getUsername());
				pgimLBcreada.setIpActualizacion(auditoriaDTO.getTerminal());

				this.lineaProgramaRepository.save(pgimLBcreada);

			}

		}

	}

	/**
	 * Permite validar la transición de un paso a otro.
	 * 
	 * @param pgimRelacionPaso  Objeto entidad de la relación de paso involucrada.
	 * @param pgimInstanciaPaso Objeto de instancia de paso involucrada.
	 */
	@Override
	public void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso) {

		String msjExcepcionControlada = null;

		Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso)
				.orElse(null);

		// Obtenemos el registro de la programación
		PgimPrgrmSupervisionAuxDTO pgimPrgrmSupervisionAuxDTO = this.prgrmSupervisionAuxRepository
				.obtenerProgramaAuxByIdInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());

		if (pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_ELABORAR_PROG_VISAR_PROG)
				|| pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_VISAR_PROG_REVISAR_PROG)) {

			// 1.-Validar registro de datos estándares
			List<PgimEstandarProgramaDTO> lPgimEstandarProgramaDTO = this.estandarProgramaRepository
					.listarEstandarPrograma(pgimPrgrmSupervisionAuxDTO.getIdLineaPrograma());
			for (PgimEstandarProgramaDTO pgimEstandarProgramaDTO : lPgimEstandarProgramaDTO) {
				if (pgimEstandarProgramaDTO.getMoPorSupervision() == null
						|| pgimEstandarProgramaDTO.getCaDiasSupervision() == null)
					msjExcepcionControlada = "<br>- Todos los datos estándares deben ser registrados";
			}

			// 2.-Validar registro de datos de supervisiones NO programadas
			boolean valDatosNP = false;
			for (PgimEstandarProgramaDTO pgimEstandarProgramaDTO : lPgimEstandarProgramaDTO) {
				if (pgimEstandarProgramaDTO.getDescTipoSupervision().equals("No programada")
						&& pgimEstandarProgramaDTO.getCaSupervisiones() == null)
					valDatosNP = true;
			}

			if (valDatosNP) {
				if (msjExcepcionControlada == null)
					msjExcepcionControlada = "<br>- Todas las cantidades de supervisiones NO programadas deben ser registradas";
				else
					msjExcepcionControlada = msjExcepcionControlada
							+ "<br>- Todas las cantidades de supervisiones NO programadas deben ser registradas";
			}

			// 3.-Validar registro de datos de supervisiones programadas
			List<PgimItemProgramaSupeDTO> lgimItemProgramaSupeDTO = this.itemProgramaSupeRepository
					.listarItemsProgramas(pgimPrgrmSupervisionAuxDTO.getIdLineaPrograma());
			if (lgimItemProgramaSupeDTO.size() == 0) {
				if (msjExcepcionControlada == null)
					msjExcepcionControlada = "<br>- No se ha programado las supervisiones a las unidades fiscalizadas";
				else
					msjExcepcionControlada = msjExcepcionControlada
							+ "<br>- No se ha programado las supervisiones a las unidades fiscalizadas";
			}

		} else if (pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_VISAR_PROG_ANULAR_LB)) {
			// Validar que no se pueda anular la primera LB
			List<PgimLineaProgramaDTO> listLBaprobadas = this.lineaProgramaRepository.listarLineaBasePorEstado(
					pgimPrgrmSupervisionAuxDTO.getIdProgramaSupervision(), this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.PREST_APRBDA.toString()));

			if (listLBaprobadas.size() == 0) {
				msjExcepcionControlada = "<br>- No se puede anular la línea base, debido a que es la primera línea base del programa";
			}

		}

		if (msjExcepcionControlada != null) {
			throw new PgimException(TipoResultado.WARNING, "No se puede asignar el paso porque: " + msjExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}
	}

	@Override
	public void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces,
			PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {
		// Si el proceso es de tipo PROGRAMA (PGIM_TC_PRGRM_SUPERVISION), actualizar las
		// líneas base
		this.actualizarLineaBase(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
	}

	@Override
	public List<PgimItemProgramaSupeDTO> listarItemsProgramas(Long idLineaPrograma) {
		return this.itemProgramaSupeRepository.listarItemsProgramas(idLineaPrograma);
	}
	
	
	/**
	 * obtener los costos reales de un programa de supervisión
	 * @param idProgramaSupervision
	 * @return
	 */
	@Override
	public SeguimientoProgramaDTO obtenerCostosReales(Long idLineaPrograma) {
		return this.prgrmSeguimientoAuxRepository.obtenerCostosRealesPrograma(idLineaPrograma, this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.SUPER_FSCLZCION_NO_PRGRMDA.toString()),
			    this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.SUPER_FSCLZCION_PRGRMDA.toString()));
	}

	@Override
	public List<PgimResumenLineaAuxDTO> obtenerListaResumenPrograma(Long idLineaPrograma) {
		return this.resumenLineaAuxRepository.obtenerListaResumenPrograma(idLineaPrograma);
	}

	@Override
	public List<PgimPrgrmSupervisionDTO> listarProgramasSupervPlan(Long idPlanSupervision) {
		
		Long idInstanciaPaso = 0L;

		List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = this.prgrmSupervisionRepository.listarProgramasSupervPlan(idPlanSupervision);
		
		for(PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO: lPgimPrgrmSupervisionDTO){

			PgimPrgrmSupervisionAuxDTO pgimPrgrmSupervisionAuxDTO = this.prgrmSupervisionAuxRepository.obtenerProgramaAuxById(pgimPrgrmSupervisionDTO.getIdProgramaSupervision());

			if(pgimPrgrmSupervisionAuxDTO != null){
				idInstanciaPaso = pgimPrgrmSupervisionAuxDTO.getIdInstanciaPaso();
			}

			pgimPrgrmSupervisionDTO.setDescIdInstanciaPaso(idInstanciaPaso);
		}
		 return lPgimPrgrmSupervisionDTO;
	}

	@Override
	public PgimPrgrmSupervision getPrgrmSupervisionById(Long idProgramaSupervision) {
		return this.prgrmSupervisionRepository.findById(idProgramaSupervision).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPrgrmSupervisionDTO modificarProgramaSupervision(PgimPrgrmSupervision pgimPrgrmSupervision,
			AuditoriaDTO auditoriaDTO) {

		pgimPrgrmSupervision.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPrgrmSupervision.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPrgrmSupervision.setIpActualizacion(auditoriaDTO.getTerminal());
		PgimPrgrmSupervision pgimPrgrmSupervisionModificado = prgrmSupervisionRepository.save(pgimPrgrmSupervision);

		PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTOModificado = this
				.obtenerPrograma(pgimPrgrmSupervisionModificado.getIdProgramaSupervision());
				
		return pgimPrgrmSupervisionDTOModificado;
	}

	@Override
    public Page<PgimSuperProgramadaAuxDTO> listarReporteSuperProgramadaPaginado(PgimSuperProgramadaAuxDTO filtroPgimSuperProgramadaAuxDTO, Pageable paginador) throws Exception{
		Page<PgimSuperProgramadaAuxDTO> lPgimSuperProgramadaAuxDTO = this.prgrmSupervisionRepository
                .listarReporteSuperProgramadaPaginado(filtroPgimSuperProgramadaAuxDTO.getIdDivisionSupervisora(), paginador);
	      return lPgimSuperProgramadaAuxDTO;       
    }
	
	@Override
	public Page<PgimSupernpDsEspAuxDTO> listarReporteSuperNoProgramadaPaginado(PgimSupernpDsEspAuxDTO filtroPgimSupernpDsEspAuxDTO, Pageable paginador) throws Exception{
		Page<PgimSupernpDsEspAuxDTO> pPgimSupernpDsEspAuxDTO = this.prgrmSupervisionRepository
				.listarReporteSuperNoProgramadaPaginado(filtroPgimSupernpDsEspAuxDTO.getIdDivisionSupervisora(), paginador);
		return pPgimSupernpDsEspAuxDTO;       
	}
	
	@Override
	public Page<PgimSuperDsUmAuxDTO> listarReporteSuperDsUmPaginado(PgimSuperDsUmAuxDTO filtroPgimSuperDsUmAuxDTO, Pageable paginador) throws Exception{
		Page<PgimSuperDsUmAuxDTO> pPgimSuperDsUmAuxDTO = this.prgrmSupervisionRepository
				.listarReporteSuperDsUmPaginado(filtroPgimSuperDsUmAuxDTO.getIdDivisionSupervisora(), filtroPgimSuperDsUmAuxDTO.getNoRazonSocial(), paginador);
		return pPgimSuperDsUmAuxDTO;       
	}
	
	@Override
	public Page<PgimPresupuestoDsEspAuxDTO> listarReportePresupDsEspecPaginado(PgimPresupuestoDsEspAuxDTO filtroPgimPresupuestoDsEspAuxDTO, Pageable paginador) throws Exception{
		Page<PgimPresupuestoDsEspAuxDTO> lPgimPresupuestoDsEspAuxDTO = this.prgrmSupervisionRepository
				.listarReportePresupDsEspecPaginado(filtroPgimPresupuestoDsEspAuxDTO.getIdDivisionSupervisora(), paginador);
		return lPgimPresupuestoDsEspAuxDTO;  
	}
	    
	public List<PgimVwPrgrmMontoEspAuxDTO> listarMontosEspecialidadPorPlan(Long idPlanSupervisionAux) {
		return this.vwPrgrmMontoEspAuxRepository.listarMontosEspecialidadPorPlan(idPlanSupervisionAux);
	}
	
	@Override
    public Integer contarProgramasPendientes(AuditoriaDTO auditoriaDTO) {
    	
    	Integer cantidadPendientes = this.prgrmSupervisionAuxRepository.contarProgramasPendientes(auditoriaDTO.getUsername()); 
    	
    	return cantidadPendientes;    	
    }

	@Override
	public List<PgimPrgrmSupervisionDTO> obtenerProgramaAutocompletado(String descPrograma) {
		List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = this.prgrmSupervisionRepository.obtenerProgramaAutocompletado(descPrograma);
		return lPgimPrgrmSupervisionDTO;
	}

	@Override
	public List<PgimRankingRiesgoDTO> obtenerRankingAprobXDsEspecialidadAnio(Long idProgramaSupervision) {
		
		Long idPasoProcesoRankingAprob = ConstantesUtil.PARAM_PROCESO_PASO_RANKING_APROBRADO;
		Long idTipoCfgRiesgoFiscalizacion = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.TIPO_CFG_RIESGO_FISCALIZACION.toString());

		PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO = this.obtenerPrograma(idProgramaSupervision);
		Integer anio = pgimPrgrmSupervisionDTO.getDescNuAnio().intValue() - 1;
		
		List<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTO = this.rankingRiesgoRepository.obtenerRankingAprobXDsEspecialidadAnio(
			pgimPrgrmSupervisionDTO.getIdEspecialidad(), pgimPrgrmSupervisionDTO.getIdDivisionSupervisora(), 
			anio, idPasoProcesoRankingAprob, idTipoCfgRiesgoFiscalizacion );

		// Colando el número de orden
		IntStream.range(0, lPgimRankingRiesgoDTO.size())
    	.forEach(i -> lPgimRankingRiesgoDTO.get(i).setDescNuOrden( Long.valueOf(i + 1)));

		return lPgimRankingRiesgoDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimGenProgramaDTO generarProgramaPropuesta(PgimGenProgramaDTO pgimGenProgramaDTO,
			List<PgimRankingRiesgoDTO> lPgimRankingRiesgoDTO, AuditoriaDTO auditoriaDTO) throws Exception {
			
			// Eliminar los programas propuestos generados de existir y las cuales no se han definido para el programa determinado
			List<PgimGenProgramaDTO> lPgimGenProgramaDTOCValidar = this.genProgramaRepository.validadExistenciaProgramaPropuesta(pgimGenProgramaDTO.getIdProgramaSupervision());
			for (PgimGenProgramaDTO pgimGenProgramaDTO2 : lPgimGenProgramaDTOCValidar) {
				if(pgimGenProgramaDTO2.getFlConforme().equals(ConstantesUtil.FL_IND_NO)){
					this.eliminarProgramaPropuesta(pgimGenProgramaDTO2.getIdGenPrograma(), auditoriaDTO);
				}
			}
			
			// Registro de la tabla cabeza para la generación del programa propuesta
			PgimGenPrograma pgimGenPrograma = new PgimGenPrograma();
			
			PgimPrgrmSupervision pgimPrgrmSupervision = new PgimPrgrmSupervision();
			pgimPrgrmSupervision.setIdProgramaSupervision(pgimGenProgramaDTO.getIdProgramaSupervision());
			pgimGenPrograma.setPgimPrgrmSupervision(pgimPrgrmSupervision);

			pgimGenPrograma.setFlConforme(ConstantesUtil.IND_INACTIVO);
			pgimGenPrograma.setFeGeneracion(auditoriaDTO.getFecha());
			pgimGenPrograma.setNuMaxFiscaAnualXuf(pgimGenProgramaDTO.getNuMaxFiscaAnualXuf());
			pgimGenPrograma.setNuMaxFiscaMensual(pgimGenProgramaDTO.getNuMaxFiscaMensual());
			
			pgimGenPrograma.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimGenPrograma.setFeCreacion(auditoriaDTO.getFecha());
			pgimGenPrograma.setUsCreacion(auditoriaDTO.getUsername());
			pgimGenPrograma.setIpCreacion(auditoriaDTO.getTerminal());

			PgimGenPrograma pgimGenProgramaCreado = this.genProgramaRepository.save(pgimGenPrograma);
			
			List<Object> lPrgRanking = new ArrayList<>();

			// Registrar los ranking que están interviniendo
			for (PgimRankingRiesgoDTO pgimRankingRiesgoDTO : lPgimRankingRiesgoDTO) {
				
				PgimGenPrgRanking pgimGenPrgRanking = new PgimGenPrgRanking();
				 
				pgimGenPrgRanking.setPgimGenPrograma(pgimGenProgramaCreado);

				PgimRankingRiesgo pgimRankingRiesgo = new PgimRankingRiesgo();
				pgimRankingRiesgo.setIdRankingRiesgo(pgimRankingRiesgoDTO.getIdRankingRiesgo());
				pgimGenPrgRanking.setPgimRankingRiesgo(pgimRankingRiesgo);

				pgimGenPrgRanking.setMoPuntajeTotalRiesgo(pgimRankingRiesgoDTO.getDescPuntajeTotalRanking());
				pgimGenPrgRanking.setNuOrden(pgimRankingRiesgoDTO.getDescNuOrden().intValue());

				pgimGenPrgRanking.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimGenPrgRanking.setFeCreacion(auditoriaDTO.getFecha());
				pgimGenPrgRanking.setUsCreacion(auditoriaDTO.getUsername());
				pgimGenPrgRanking.setIpCreacion(auditoriaDTO.getTerminal());

				PgimGenPrgRanking pgimGenPrgRankingCreado = this.genPrgRankingRepository.save(pgimGenPrgRanking);

				// Registrar las unidades fiscalizables pertenecientes al ranking en cuestion
				List<PgimGenPrgUfiscaliza> lPgimGenPrgUfiscaliza = this.registrarUfRanking( pgimGenPrgRankingCreado, pgimGenProgramaCreado, auditoriaDTO );
				lPrgRanking.add(lPgimGenPrgUfiscaliza);
			}
			
			// Registrar las visitas para cada unidad fiscalizable
			List<PgimGenPrgMes> lPgimGenPrgMes = this.registrarVisitas(lPrgRanking, pgimGenPrograma, auditoriaDTO);

			// Registrar los totales mensuales
			Integer nuMes = 1;
			do {
				BigDecimal nuMesBig = new BigDecimal(nuMes);
				this.registrarTotalesv2(pgimGenProgramaCreado, nuMesBig, lPgimGenPrgMes);
				nuMes ++;

			} while (nuMes < 13);

			PgimGenProgramaDTO pgimGenProgramaDTOCreado = this.genProgramaRepository.obtenerGenProgramaPorId(pgimGenProgramaCreado.getIdGenPrograma());
			return pgimGenProgramaDTOCreado;
	}

	/**
	 * Permite registrar las unidades fiscalizables ha partir del ranking intervenido 
	 * las cuales se van a convertir en item del programada de la sección programados
	 * @param pgimGenPrgRanking
	 * @param pgimGenPrograma
	 * @param auditoriaDTO
	 */
	List<PgimGenPrgUfiscaliza> registrarUfRanking( PgimGenPrgRanking pgimGenPrgRanking, PgimGenPrograma pgimGenPrograma, AuditoriaDTO auditoriaDTO ){

		List<PgimRankingUmDTO> lPgimRankingUmDTO = this.rankingUmRepository.listarRankingUmByIdRanking(pgimGenPrgRanking.getPgimRankingRiesgo().getIdRankingRiesgo());
				
		List<PgimGenPrgUfiscaliza> lPgimGenPrgUfiscaliza = new ArrayList<PgimGenPrgUfiscaliza>();

		for (PgimRankingUmDTO pgimRankingUmDTO : lPgimRankingUmDTO) {

			BigDecimal numerador = pgimRankingUmDTO.getMoPuntaje();
			BigDecimal denominador = pgimGenPrgRanking.getMoPuntajeTotalRiesgo();

			if (numerador == null) {
				numerador = new BigDecimal(0);
			}

			BigDecimal moPuntajeProporcion = new BigDecimal(0);
			if (!denominador.equals(new BigDecimal(0))) {
				moPuntajeProporcion = numerador.divide(denominador, ConstantesUtil.PRECISION_DECIMAL, RoundingMode.HALF_EVEN);
			}

			BigDecimal moPuntajeProporcionPorcentual = moPuntajeProporcion.multiply( new BigDecimal(100));
			BigDecimal moPuntajeProporcionMultiplicado = moPuntajeProporcionPorcentual.multiply( new BigDecimal(pgimGenPrograma.getNuMaxFiscaAnualXuf()) );
			Integer moProporcionEntera = moPuntajeProporcionMultiplicado.intValue();
			Integer caVisitas = Math.min(moProporcionEntera, pgimGenPrograma.getNuMaxFiscaAnualXuf());
			if(caVisitas.equals(0)){
				caVisitas = 1;
			}

			PgimGenPrgUfiscaliza pgimGenPrgUfiscaliza = new PgimGenPrgUfiscaliza();

			PgimRankingUm pgimRankingUm = new PgimRankingUm();
			pgimRankingUm.setIdRankingUm(pgimRankingUmDTO.getIdRankingUm());
			pgimGenPrgUfiscaliza.setPgimRankingUm(pgimRankingUm);

			pgimGenPrgUfiscaliza.setMoPuntajeRiesgo(numerador);
			pgimGenPrgUfiscaliza.setPgimGenPrgRanking(pgimGenPrgRanking);
			pgimGenPrgUfiscaliza.setMoPuntajeProporcion(moPuntajeProporcion);
			pgimGenPrgUfiscaliza.setMoProporcionEntera(moProporcionEntera);
			pgimGenPrgUfiscaliza.setCaVisitas(caVisitas);

			pgimGenPrgUfiscaliza.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimGenPrgUfiscaliza.setFeCreacion(auditoriaDTO.getFecha());
			pgimGenPrgUfiscaliza.setUsCreacion(auditoriaDTO.getUsername());
			pgimGenPrgUfiscaliza.setIpCreacion(auditoriaDTO.getTerminal());
			
			PgimGenPrgUfiscaliza pgimGenPrgUfiscalizaCreada = this.genPrgUfiscalizaRepository.save(pgimGenPrgUfiscaliza);
			pgimGenPrgUfiscalizaCreada.setDescIdUnidadMinera(pgimRankingUmDTO.getIdUnidadMinera());

			lPgimGenPrgUfiscaliza.add(pgimGenPrgUfiscalizaCreada);
		}

		return lPgimGenPrgUfiscaliza;

	}

	
	/**
	 * Permite registrar las visitas programas a la unidad minera fiscalizable en su mes correspondiente
	 * @param lPrgRanking
	 * @param pgimGenPrograma
	 * @param auditoriaDTO
	 * @return
	 */
	List<PgimGenPrgMes> registrarVisitas(List<Object> lPrgRanking, PgimGenPrograma pgimGenPrograma, AuditoriaDTO auditoriaDTO){

		Integer indexRanking = 0;
		List<PgimGenPrgMes> lPgimGenPrgMes = new ArrayList<PgimGenPrgMes>();

		for (Object prgRanking : lPrgRanking) {
			Integer cantidadTotalVisitasRanking = 0;
			
			List<PgimGenPrgUfiscaliza> lPgimGenPrgUfiscaliza = (List<PgimGenPrgUfiscaliza>) prgRanking;

			for (PgimGenPrgUfiscaliza pgimGenPrgUfiscaliza : lPgimGenPrgUfiscaliza) {
				cantidadTotalVisitasRanking += pgimGenPrgUfiscaliza.getCaVisitas();
			}
						
			Integer mesProgramado = 1;
			Integer nuFiscaMensual = 0;
			Integer cantidadVisitasActual = 0;
			Integer cantidadVisitasPorUMActual = 0;
			Integer nuMaxFiscaMensual = pgimGenPrograma.getNuMaxFiscaMensual();
			
			do {
							
				for (PgimGenPrgUfiscaliza pgimGenPrgUfiscaliza : lPgimGenPrgUfiscaliza) {
					Boolean flTrimestreNoValido = false;
					Boolean flExisteUM = false;
					String mensaje = "";
					String flConforme = ConstantesUtil.FL_IND_SI;
	
					if(cantidadVisitasPorUMActual >= pgimGenPrgUfiscaliza.getCaVisitas()){
						continue;
					}
	
					// Validar sí la unidad fiscalizable ya a sido registrado anteriormente
					Long existeVisitaUM = this.existeVisitaUF(lPgimGenPrgMes, pgimGenPrgUfiscaliza.getDescIdUnidadMinera(), pgimGenPrgUfiscaliza.getIdGenPrgUfiscaliza());
	
					if(existeVisitaUM > 0){
						flExisteUM = true; 
						mensaje = "La unidad fiscalizable ya fue programada, motivo por el cual ha sido omitida en el presente ranking";
						flConforme = ConstantesUtil.FL_IND_NO;
	
					}else{
						// el segundo o n-enesima ranking y primera visita
						if(indexRanking > 0 && cantidadVisitasPorUMActual.equals(0)){
							mesProgramado = this.obtenerMesLibre(lPgimGenPrgMes, 1, nuMaxFiscaMensual );
						}
		
						// es la segunda o n-enesima visita
						if(!cantidadVisitasPorUMActual.equals(0) ){
							PgimGenPrgMes pgimGenPrgMesExistente = this.obtenerUltimaVisitaUF(lPgimGenPrgMes, pgimGenPrgUfiscaliza.getIdGenPrgUfiscaliza());
							
							if(pgimGenPrgMesExistente != null){
								int nuMes = pgimGenPrgMesExistente.getNuMes().intValue();
								int trimestre = (int) Math.ceil(nuMes / 3.0);
								int trimestreRegistrar = trimestre + 2;
								mesProgramado = this.obtenerMesLibre(lPgimGenPrgMes, trimestreRegistrar, nuMaxFiscaMensual);
								
								if(trimestreRegistrar > 4 ){
									flTrimestreNoValido = true;					
								}
		
							}
		
						}
	
					}
		
					if(mesProgramado > 12 ){
	
						mensaje = "No se ha podido programar debido a que todos los meses ya tienen cubierta su capacidad operativa máxima";
						flConforme = ConstantesUtil.FL_IND_NO;
	
						if(flTrimestreNoValido){
							mensaje = "No se ha podido programar la segunda visita, debido a que no existe un subsiguiente trimestre disponible dentro del año";
						}

						if(flExisteUM){
							mensaje = "La unidad fiscalizable ya fue programada, motivo por el cual ha sido omitida en el presente ranking";
						}
						
					}
									
					PgimGenPrgMes pgimGenPrgMes = new PgimGenPrgMes();
					
					pgimGenPrgMes.setPgimGenPrgUfiscaliza(pgimGenPrgUfiscaliza);
					pgimGenPrgMes.setFlConforme(flConforme);
					pgimGenPrgMes.setNuMes(new BigDecimal(mesProgramado));
					pgimGenPrgMes.setDeMensaje(mensaje);
					
					pgimGenPrgMes.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimGenPrgMes.setFeCreacion(auditoriaDTO.getFecha());
					pgimGenPrgMes.setUsCreacion(auditoriaDTO.getUsername());
					pgimGenPrgMes.setIpCreacion(auditoriaDTO.getTerminal());
			
					PgimGenPrgMes pgimGenPrgMesCreada = this.genPrgMesRepository.save(pgimGenPrgMes);
					pgimGenPrgMesCreada.setDescIdUnidadMinera(pgimGenPrgUfiscaliza.getDescIdUnidadMinera());

					lPgimGenPrgMes.add(pgimGenPrgMesCreada);
					
					if(flConforme.equals("1")){
						nuFiscaMensual ++;
					}
					
					if(nuFiscaMensual >= nuMaxFiscaMensual){
						mesProgramado ++;
						nuFiscaMensual = 0;
					}
		
					cantidadVisitasActual ++;
		
				}
				
				cantidadVisitasPorUMActual ++; // genero la siguiente visita
	
			} while (cantidadVisitasActual < cantidadTotalVisitasRanking);
			
			indexRanking ++;
		}
	
		return lPgimGenPrgMes;
	}

	/**
	 * Permite eliminar logicamente al programa propuesto (PgimGenPrograma)
	 * @param idGenPrograma
	 * @param auditoriaDTO
	 */
	void eliminarProgramaPropuesta(Long idGenPrograma, AuditoriaDTO auditoriaDTO){

		PgimGenPrograma pgimGenPrograma = this.genProgramaRepository.findById(idGenPrograma).orElse(null);

		pgimGenPrograma.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimGenPrograma.setFeActualizacion(auditoriaDTO.getFecha());
		pgimGenPrograma.setUsActualizacion(auditoriaDTO.getUsername());
		pgimGenPrograma.setIpActualizacion(auditoriaDTO.getTerminal());

		this.genProgramaRepository.save(pgimGenPrograma);

	}

	/**
	 * Permite obtener el mes disponible( con cupos disponibles ) para el registro 
	 * de las visitas de las unidades fiscalizables.
	 * @param lPgimGenPrgMes
	 * @param trimestre
	 * @param nuMaxFiscaMensual
	 * @return
	 */
	Integer obtenerMesLibre (List<PgimGenPrgMes> lPgimGenPrgMes, Integer trimestre, Integer nuMaxFiscaMensual ){
		Integer primerMesTrimestral = 0;
		Integer mesDisponible = 0;
		Integer nuFiscaMensual = 0;

		switch (trimestre) {
			case 1:
				primerMesTrimestral = 1;
				break;
			case 2:
				primerMesTrimestral = 4;
				break;
			case 3:
				primerMesTrimestral = 7;
				break;
			case 4:
				primerMesTrimestral = 10;
				break;
			default:
				primerMesTrimestral = 13; // mes invalido
				break;
		}

		mesDisponible = primerMesTrimestral;
		
		// verificar el mes disponible en este trimestre o en el subsiguiente
		do {
			BigDecimal nuMesRegistrar = new BigDecimal(mesDisponible);
			Long nuFiscaMensualAux = lPgimGenPrgMes.stream().filter(pgimGenPrgMes -> {
    			return (pgimGenPrgMes.getFlConforme().equals("1") 
						&& pgimGenPrgMes.getNuMes().equals(nuMesRegistrar));
    		}).count();

			nuFiscaMensual = nuFiscaMensualAux.intValue();
			
			if((nuFiscaMensual >= nuMaxFiscaMensual)){
				mesDisponible ++;
			}

		} while (nuFiscaMensual >= nuMaxFiscaMensual);

		return mesDisponible;

	}
	
	/**
	 * Permite obtener la cantidad de visitas de la unidad minera que se programado hasta ese momento
	 * que no pertenescan al ranking precedente
	 * @param lPgimGenPrgMes
	 * @param idUnidadMinera
	 * @param idGenPrgUfiscaliza
	 * @return
	 */
	Long existeVisitaUF(List<PgimGenPrgMes> lPgimGenPrgMes, Long idUnidadMinera, Long idGenPrgUfiscaliza) {
		return lPgimGenPrgMes.stream().filter(pgimGenPrgMes -> {
    			return (pgimGenPrgMes.getDescIdUnidadMinera().equals(idUnidadMinera) 
							&& pgimGenPrgMes.getFlConforme().equals("1")
    					&& !pgimGenPrgMes.getPgimGenPrgUfiscaliza().getIdGenPrgUfiscaliza().equals(idGenPrgUfiscaliza));
    		}).count();
        	
	}

	/**
	 * Permite obtener las
	 * @param lPgimGenPrgMes
	 * @param idGenPrgUfiscaliza
	 * @return
	 */
	PgimGenPrgMes obtenerUltimaVisitaUF(List<PgimGenPrgMes> lPgimGenPrgMes, Long idGenPrgUfiscaliza){
		PgimGenPrgMes pgimGenPrgMesSeleccinado	= null;
		List<PgimGenPrgMes> lPgimGenPrgMesAux = lPgimGenPrgMes.stream()
			.filter(pgimGenPrgMes -> {
							return (pgimGenPrgMes.getPgimGenPrgUfiscaliza().getIdGenPrgUfiscaliza().equals(idGenPrgUfiscaliza) 
							&& pgimGenPrgMes.getFlConforme().equals("1"));
			}).collect(Collectors.toList());
		
		lPgimGenPrgMesAux = lPgimGenPrgMesAux.stream()
			.sorted(Comparator.comparing(PgimGenPrgMes::getIdGenPrgMes).reversed())
			.collect(Collectors.toList());
				
		if(lPgimGenPrgMesAux.size() > 0){
			pgimGenPrgMesSeleccinado = lPgimGenPrgMesAux.get(0); 
		}
			
		return pgimGenPrgMesSeleccinado;
	}

	/**
	 * Permite registar el los totales mensuales de las visitas a las unidades fiscalizadbles.
	 * @param pgimGenPrograma
	 * @param mesProgramado
	 * @param lPgimGenPrgMes
	 */
	void registrarTotalesv2(PgimGenPrograma pgimGenPrograma, BigDecimal mesProgramado, List<PgimGenPrgMes> lPgimGenPrgMes ){
		Integer nuFiscaMensual = 0;

		Long nuFiscaMensualAux = lPgimGenPrgMes.stream().filter(pgimGenPrgMes -> {
				return (pgimGenPrgMes.getFlConforme().equals("1") 
					&& pgimGenPrgMes.getNuMes().equals(mesProgramado));
			}).count();

		nuFiscaMensual = nuFiscaMensualAux.intValue();

		switch (mesProgramado.intValue()) {
			case 1:
				pgimGenPrograma.setNuEnero(nuFiscaMensual);
				break;
			case 2:
				pgimGenPrograma.setNuFebrero(nuFiscaMensual);
				break;
			case 3:
				pgimGenPrograma.setNuMarzo(nuFiscaMensual);
				break;
			case 4:
				pgimGenPrograma.setNuAbril(nuFiscaMensual);
				break;
			case 5:
				pgimGenPrograma.setNuMayo(nuFiscaMensual);
				break;
			case 6:
				pgimGenPrograma.setNuJuno(nuFiscaMensual);
				break;
			case 7:
				pgimGenPrograma.setNuJulio(nuFiscaMensual);
				break;
			case 8:
				pgimGenPrograma.setNuAgosto(nuFiscaMensual);
				break;
			case 9:
				pgimGenPrograma.setNuSeptiembre(nuFiscaMensual);
				break;
			case 10:
				pgimGenPrograma.setNuOctubre(nuFiscaMensual);
				break;
			case 11:
				pgimGenPrograma.setNuNoviembre(nuFiscaMensual);
				break;
			case 12:
				pgimGenPrograma.setNuDiciembre(nuFiscaMensual);
				break;
		
			default:
				break;
		}
	}

	
	@Override
	public List<PgimVwProgramPropuestoRnkDTO> obtenerProgramaPropuestaNoConforme(Long idProgramaSupervision) {

			List<PgimVwProgramPropuestoRnkDTO> lPgimVwProgramPropuestoRnkDTO = this.vwProgramPropuestoRnkRepository.obtenerProgramaPropuestaNoConforme(idProgramaSupervision);

			return lPgimVwProgramPropuestoRnkDTO;
	}

	@Override
	public Page<PgimVwProgramPropuestoRnkDTO> obtenerProgramaPropuesta(Long idProgramaSupervision, Pageable paginador) {

			Page<PgimVwProgramPropuestoRnkDTO> pPgimVwProgramPropuestoRnkDTO = this.vwProgramPropuestoRnkRepository.obtenerProgramaPropuesta(idProgramaSupervision, paginador);

			return pPgimVwProgramPropuestoRnkDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public void registrarProgramaPropuesta(PgimGenProgramaDTO pgimGenProgramaDTO, AuditoriaDTO auditoriaDTO)
			throws Exception {
		
		// Eliminar el programa generado de existir
		List<PgimGenProgramaDTO> lPgimGenProgramaDTOCValidar = this.genProgramaRepository.validadExistenciaProgramaPropuesta(pgimGenProgramaDTO.getIdProgramaSupervision());
		for (PgimGenProgramaDTO pgimGenProgramaDTO2 : lPgimGenProgramaDTOCValidar) {
			this.eliminarProgramaPropuesta(pgimGenProgramaDTO2.getIdGenPrograma(), auditoriaDTO);
		}

		// actualizado el flag conforme, la cual indica los items del programa se sido generado a partir de desde programa propuesto. 
		PgimGenPrograma pgimGenPrograma = this.genProgramaRepository.findById(pgimGenProgramaDTO.getIdGenPrograma()).orElse(null);
		pgimGenPrograma.setFlConforme(ConstantesUtil.FL_IND_SI);
		pgimGenPrograma.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimGenPrograma.setFeActualizacion(auditoriaDTO.getFecha());
		pgimGenPrograma.setUsActualizacion(auditoriaDTO.getUsername());
		pgimGenPrograma.setIpActualizacion(auditoriaDTO.getTerminal());
		this.genProgramaRepository.save(pgimGenPrograma);


		// eliminar todos los items de programa
		PgimLineaProgramaDTO pgimLineaProgramaDTO = this.obtenerLineaProgramaActual(pgimGenProgramaDTO.getIdProgramaSupervision());
		Long idLineaPrograma = pgimLineaProgramaDTO.getIdLineaPrograma();

		List<PgimItemProgramaSupeDTO> lPgimItemProgramaSupeDTO = this.listarItemsProgramas(idLineaPrograma);
		for (PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO : lPgimItemProgramaSupeDTO) {
			 this.cancelarItemProgramaSupe(pgimItemProgramaSupeDTO, auditoriaDTO);
		}

		// obtención de datos para el registro de los items a partir del programa propuesto
		PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO = this.obtenerPrograma(pgimGenProgramaDTO.getIdProgramaSupervision());
		Long idEspecialidad = pgimPrgrmSupervisionDTO.getIdEspecialidad();
		List<PgimSubtipoSupervisionDTO> lpgimSubtipoSupervisionDTO = this.subTipoSupervisionRepository.obtenerSubTipoSuperForGenPrg(idEspecialidad);
		
		if(lpgimSubtipoSupervisionDTO.size() == 0){
			throw new PgimException(TipoResultado.ERROR, "Error al registrar: No se encontró el subtipo de supervisión por defecto para el programa propuesto");
		}
		 
		Long idSubtipoSupervision = lpgimSubtipoSupervisionDTO.get(0).getIdSubtipoSupervision();
		String noSubtipoSupervision = lpgimSubtipoSupervisionDTO.get(0).getDescTipoSupervision();
		List<PgimEstandarProgramaDTO> lpgimEstandarProgramaDTO = this.listarCostosEstimados(idLineaPrograma, idSubtipoSupervision);
		BigDecimal moPorSupervision = lpgimEstandarProgramaDTO.get(0).getMoPorSupervision();

		if(moPorSupervision == null){
			throw new PgimException(TipoResultado.ERROR, "Error al registrar: No se ha definido el costo estimado para las fiscalizaciones del subtipo " + noSubtipoSupervision);
		}
		
		// Registro de items a partir del programa propuesto
		List<PgimGenProgramaDTO> lPgimGenProgramaDTO = this.genProgramaRepository.obtenerProgramaPropuesta(pgimGenProgramaDTO.getIdGenPrograma());
		
		for (PgimGenProgramaDTO pgimGenProgramaDTOCrear : lPgimGenProgramaDTO) {

			Calendar calendar = Calendar.getInstance();
			Integer mes  = pgimGenProgramaDTOCrear.getDescNuMes().intValue() - 1;
			calendar.set(Calendar.YEAR, pgimPrgrmSupervisionDTO.getDescNuAnio().intValue());
			calendar.set(Calendar.MONTH, mes);
			Date fecha = calendar.getTime();
			
			PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO = new PgimItemProgramaSupeDTO();
			pgimItemProgramaSupeDTO.setIdSubtipoSupervision(idSubtipoSupervision);
			pgimItemProgramaSupeDTO.setIdUnidadMinera(pgimGenProgramaDTOCrear.getDescIdUnidadMinera());
			pgimItemProgramaSupeDTO.setIdLineaPrograma(idLineaPrograma);
			pgimItemProgramaSupeDTO.setFeMesEstimado(fecha);
			pgimItemProgramaSupeDTO.setMoCostoEstimadoSupervision(moPorSupervision);
			
			this.unidadMineraService.crearUnidadMineraPrograma(pgimItemProgramaSupeDTO, auditoriaDTO);
			
		}
	}

	@Override
	public Integer existeFiscalizacionAsignadaPrograma(Long idLineaPrograma) {
		Integer countFiscalizacionesAsignadas = itemProgramaSupeRepository.existeFiscalizacionAsignadaPrograma(idLineaPrograma);
		return countFiscalizacionesAsignadas;
	}

	@Override
	public String obtenerRankingsUtilizados(Long idProgramaSupervision) {
		String rankingUtilizado = "";

		List<PgimGenProgramaDTO> lPgimGenProgramaDTO = this.genProgramaRepository.obtenerRankingsInvolucrados(idProgramaSupervision);

		for (PgimGenProgramaDTO pgimGenProgramaDTO : lPgimGenProgramaDTO) {
			rankingUtilizado += pgimGenProgramaDTO.getDescNoRankingRiesgo() + ", ";
		}

		rankingUtilizado = rankingUtilizado.trim();
		rankingUtilizado = (rankingUtilizado.length() > 0) ? rankingUtilizado.substring(0, rankingUtilizado.length() - 1) : "";

		return rankingUtilizado;
	}


	@Override
	public Long obtenerTotalVisitasProgramaPropuesta(Long idProgramaSupervision) {
		Long totalVisitas = vwProgramPropuestoRnkRepository.obtenerTotalVisitasProgramaPropuesta(idProgramaSupervision);
		return totalVisitas;
	}

	
}
