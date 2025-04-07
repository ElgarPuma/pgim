package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizCriterioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizGrpoCrtrioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguracionBase;
import pe.gob.osinergmin.pgim.models.entity.PgimEspecialidad;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizCriterio;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizGrpoCrtrio;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaObligacion;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmaCrtrio;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository;
import pe.gob.osinergmin.pgim.models.repository.MatrizCriterioAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.MatrizCriterioRepository;
import pe.gob.osinergmin.pgim.models.repository.MatrizGrpoCrtrioRepository;
import pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.NormaItemRepository;
import pe.gob.osinergmin.pgim.models.repository.OblgcnNrmaCrtrioRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.ReglaBaseRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.ConfiguracionBaseService;
import pe.gob.osinergmin.pgim.services.MatrizSupervisionService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * 
 * 
 * @descripción: Lógica de negocio de la entidad Matriz de Supervisión
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 13/05/2021
 * 
 */
@Service
@Transactional(readOnly = true)
public class MatrizSupervisionServiceImpl implements MatrizSupervisionService {

	@Autowired
	private MatrizSupervisionRepository matrizSupervisionRepository;

	@Autowired
	private NormaItemRepository normaItemRepository;

	@Autowired
	private MatrizGrpoCrtrioRepository matrizGrpoCrtrioRepository;

	@Autowired
	private MatrizCriterioRepository matrizCriterioRepository;

	@Autowired
	private MatrizCriterioAuxRepository matrizCriterioAuxRepository;

	@Autowired
	private OblgcnNrmaCrtrioRepository oblgcnNrmaCrtrioRepository;

	@Autowired
	private ConfiguracionBaseRepository configuracionBaseRepository;
	
	@Autowired
	private ConfiguracionBaseServiceImpl configuracionBaseServiceImpl;

	@Autowired
	private ReglaBaseRepository reglaBaseRepository;
	
	@Autowired
	private SupervisionRepository supervisionRepository;

	@Autowired
	private ValorParametroRepository valorParametroRepository;	

	@Autowired
	private PrgrmSupervisionRepository prgrmSupervisionRepository;	

	@Autowired
	private ConfiguracionBaseService configuracionBaseService;	

	@Autowired
	private UnidadMineraRepository unidadMineraRepository;	

	@Autowired
	private MatrizSupervisionAuxRepository matrizSupervisionAuxRepository;	

	/**
	 * Permite obtener la lista de grupos de criterios de la matriz de supervisión
	 * por ID de Matriz de Supervision
	 * 
	 * @param idMatrizSupervision
	 * @return
	 */
	@Override
	public List<PgimMatrizGrpoCrtrioDTO> listarMatrizGrpoCrtrio(Long idMatrizSupervision) throws Exception {
		
		List<PgimMatrizGrpoCrtrioDTO> lPgimMatrizGrpoCrtrioDTO = this.matrizGrpoCrtrioRepository
				.listarMatrizGrpoCrtrio(idMatrizSupervision);

		return lPgimMatrizGrpoCrtrioDTO;
	}

	/**
	 * Permite mostrar la lista de matriz de supervision
	 */
	@Override
	public List<PgimMatrizSupervisionDTO> listarMatrizSupervision(PgimMatrizSupervisionDTO filtro) {
		String coTipoCuadroverificacion = EValorParametro.NORMA_RCDCT.toString();
		String descNoGrupoNorma = filtro.getDescNoGrupoNorma();
		
		if(filtro.getDescNoGrupoNorma() != null) {
			if(filtro.getDescNoGrupoNorma().equals("1")){
				descNoGrupoNorma = EValorParametro.NORMA_RCDCT.toString();
			}
		}
				
		List<PgimMatrizSupervisionDTO> lista = this.matrizSupervisionRepository.listarMatrizSupervision(
			filtro.getIdEspecialidad(),
			filtro.getDeMatrizSupervision(),
			filtro.getFlActivo(),
			filtro.getDescNoCortoNorma(),
			filtro.getDescNoNorma(),
			descNoGrupoNorma,
			filtro.getDescCoTipoNorma(),
			coTipoCuadroverificacion
		);
		return lista;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimMatrizSupervisionDTO crearMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {
		PgimMatrizSupervision pgimMatrizSupervision = new PgimMatrizSupervision();

		pgimMatrizSupervision.setDeMatrizSupervision(pgimMatrizSupervisionDTO.getDeMatrizSupervision());

		PgimConfiguracionBase pgimConfiguracionBase = this.configuracionBaseRepository
				.findById(pgimMatrizSupervisionDTO.getIdConfiguracionBase()).orElse(null);

		if (pgimConfiguracionBase != null) {
			pgimMatrizSupervision.setPgimConfiguracionBase(pgimConfiguracionBase);
		} else {
			pgimMatrizSupervision.setPgimConfiguracionBase(null);
		}

		if(pgimMatrizSupervisionDTO.getIdMatrizSupervision() != null){
			pgimMatrizSupervision.setPgimMatrizSupervisionOrigen(new PgimMatrizSupervision());
			pgimMatrizSupervision.getPgimMatrizSupervisionOrigen().setIdMatrizSupervision(pgimMatrizSupervisionDTO.getIdMatrizSupervision());
		}

		pgimMatrizSupervision.setPgimEspecialidad(new PgimEspecialidad());
		pgimMatrizSupervision.getPgimEspecialidad()
				.setIdEspecialidad(pgimConfiguracionBase.getPgimEspecialidad().getIdEspecialidad());

		pgimMatrizSupervision.setFlActivo(ConstantesUtil.IND_INACTIVO);
		pgimMatrizSupervision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimMatrizSupervision.setFeCreacion(auditoriaDTO.getFecha());
		pgimMatrizSupervision.setUsCreacion(auditoriaDTO.getUsername());
		pgimMatrizSupervision.setIpCreacion(auditoriaDTO.getTerminal());

		PgimMatrizSupervision pgimMatrizSupervisionCreado = this.matrizSupervisionRepository
				.save(pgimMatrizSupervision);

		PgimMatrizSupervisionDTO pgimMatrizSupervisionCreadoDTOCreado = this
				.obtenerMatrizSupervisionPorId(pgimMatrizSupervisionCreado.getIdMatrizSupervision());

		return pgimMatrizSupervisionCreadoDTOCreado;
	}

	@Override
	public List<PgimMatrizSupervisionDTO> validarExisteCuadroVerificacion(Long idMatrizSupervision,
			String deMatrizSupervision) {
		List<PgimMatrizSupervisionDTO> lPgimMatrizSupervisionDTO = this.matrizSupervisionRepository
				.validarExisteCuadroVerificacion(idMatrizSupervision, deMatrizSupervision.toUpperCase());
		return lPgimMatrizSupervisionDTO;
	}

	/**
	 * Permite modificar una matriz de supervision.
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimMatrizSupervisionDTO modificarMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO,
			PgimMatrizSupervision pgimMatrizSupervision, AuditoriaDTO auditoriaDTO) {

		pgimMatrizSupervision.setDeMatrizSupervision(pgimMatrizSupervisionDTO.getDeMatrizSupervision());

		// pgimMatrizSupervision.setPgimEspecialidad(new PgimEspecialidad());
		// pgimMatrizSupervision.getPgimEspecialidad().setIdEspecialidad(pgimMatrizSupervisionDTO.getDescIdEspecialidad());

		if (pgimMatrizSupervisionDTO.getIdConfiguracionBase() != null) {
			PgimConfiguracionBase pgimConfiguracionBase = new PgimConfiguracionBase();
			pgimConfiguracionBase.setIdConfiguracionBase(pgimMatrizSupervisionDTO.getIdConfiguracionBase());
			pgimMatrizSupervision.setPgimConfiguracionBase(pgimConfiguracionBase);
		} else {
			pgimMatrizSupervision.setPgimConfiguracionBase(null);
		}

		pgimMatrizSupervision.setFlActivo(pgimMatrizSupervisionDTO.getFlActivo());
		pgimMatrizSupervision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimMatrizSupervision.setFeActualizacion(auditoriaDTO.getFecha());
		pgimMatrizSupervision.setUsActualizacion(auditoriaDTO.getUsername());
		pgimMatrizSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimMatrizSupervision pgimMatrizSupervisionModificado = this.matrizSupervisionRepository
				.save(pgimMatrizSupervision);

		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTOResultado = this
				.obtenerMatrizSupervisionPorId(pgimMatrizSupervisionModificado.getIdMatrizSupervision());

		return pgimMatrizSupervisionDTOResultado;
	}

	/**
	 * Permite obtener las propiedades de matriz de supervision por el
	 * idMatrizSupervision
	 */
	@Override
	public PgimMatrizSupervision getByIdMatrizSupervision(Long idMatrizSupervision) {
		return this.matrizSupervisionRepository.findById(idMatrizSupervision).orElse(null);
	}

	/**
	 * Permite obtener un registro de matriz de supervisión por ID de Matriz de
	 * Supervision
	 * 
	 * @param idMatrizSupervision
	 * @return
	 */
	@Override
	public PgimMatrizSupervisionDTO obtenerMatrizSupervisionPorId(Long idMatrizSupervision) {
		return this.matrizSupervisionRepository.obtenerMatrizSupervisionPorId(idMatrizSupervision);
	}

	/***
	 * Permite crear un nuevo grupo de criterio
	 * 
	 * @param pgimMatrizGrpoCrtrioDTO
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimMatrizGrpoCrtrioDTO crearGrupoCriterio(PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTO,
			AuditoriaDTO auditoriaDTO) {

		PgimMatrizGrpoCrtrio pgimMatrizGrpoCrtrio = new PgimMatrizGrpoCrtrio();

		pgimMatrizGrpoCrtrio.setPgimMatrizSupervision(new PgimMatrizSupervision());
		pgimMatrizGrpoCrtrio.getPgimMatrizSupervision()
				.setIdMatrizSupervision(pgimMatrizGrpoCrtrioDTO.getIdMatrizSupervision());

		pgimMatrizGrpoCrtrio.setCoMatrizGrpoCrtrio(pgimMatrizGrpoCrtrioDTO.getCoMatrizGrpoCrtrio());
		pgimMatrizGrpoCrtrio.setNoMatrizGrpoCrtrio(pgimMatrizGrpoCrtrioDTO.getNoMatrizGrpoCrtrio());
		pgimMatrizGrpoCrtrio.setNuOrden(pgimMatrizGrpoCrtrioDTO.getNuOrden());
		pgimMatrizGrpoCrtrio.setEsVisible(pgimMatrizGrpoCrtrioDTO.getEsVisible());

		pgimMatrizGrpoCrtrio.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimMatrizGrpoCrtrio.setFeCreacion(auditoriaDTO.getFecha());
		pgimMatrizGrpoCrtrio.setUsCreacion(auditoriaDTO.getUsername());
		pgimMatrizGrpoCrtrio.setIpCreacion(auditoriaDTO.getTerminal());

		PgimMatrizGrpoCrtrio pgimMatrizGrpoCrtrioCreado = this.matrizGrpoCrtrioRepository.save(pgimMatrizGrpoCrtrio);

		PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTOCreado = this.matrizGrpoCrtrioRepository
				.obtenerGrupoCriterioPorId(pgimMatrizGrpoCrtrioCreado.getIdMatrizGrpoCrtrio());

		return pgimMatrizGrpoCrtrioDTOCreado;
	}

	/***
	 * Permite obtener un objeto grupo criterio de matriz de supervisión con el
	 * valor idMatrizGrpoCrtrio.
	 * 
	 * @param idMatrizGrpoCrtrio ID del grupo criterio de matriz de supervisión.
	 * @return
	 */
	@Override
	public PgimMatrizGrpoCrtrio getGrpoCrtrioById(Long idMatrizGrpoCrtrio) {
		return this.matrizGrpoCrtrioRepository.findById(idMatrizGrpoCrtrio).orElse(null);
	}

	/***
	 * Permite modificar un grupo de criterio de matriz de supervisión.
	 * 
	 * @param pgimMatrizGrpoCrtrioDTO grupo de criterio DTO que porta los datos con
	 *                                los nuevos valores para la actualización.
	 * @param pgimMatrizGrpoCrtrio    grupo de criterio al que se actualizarán los
	 *                                nuevos valores.
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimMatrizGrpoCrtrioDTO modificarGrupoCriterio(PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTO,
			PgimMatrizGrpoCrtrio pgimMatrizGrpoCrtrio, AuditoriaDTO auditoriaDTO) {

		pgimMatrizGrpoCrtrio.setCoMatrizGrpoCrtrio(pgimMatrizGrpoCrtrioDTO.getCoMatrizGrpoCrtrio());
		pgimMatrizGrpoCrtrio.setNoMatrizGrpoCrtrio(pgimMatrizGrpoCrtrioDTO.getNoMatrizGrpoCrtrio());
		pgimMatrizGrpoCrtrio.setNuOrden(pgimMatrizGrpoCrtrioDTO.getNuOrden());
		pgimMatrizGrpoCrtrio.setEsVisible(pgimMatrizGrpoCrtrioDTO.getEsVisible());

		pgimMatrizGrpoCrtrio.setFeActualizacion(auditoriaDTO.getFecha());
		pgimMatrizGrpoCrtrio.setUsActualizacion(auditoriaDTO.getUsername());
		pgimMatrizGrpoCrtrio.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimMatrizGrpoCrtrio pgimMatrizGrpoCrtrioModificado = this.matrizGrpoCrtrioRepository
				.save(pgimMatrizGrpoCrtrio);

		PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTOResultado = this.matrizGrpoCrtrioRepository
				.obtenerGrupoCriterioPorId(pgimMatrizGrpoCrtrioModificado.getIdMatrizGrpoCrtrio());

		return pgimMatrizGrpoCrtrioDTOResultado;
	}

	/***
	 * Permite eliminar un grupo de criterio de matriz de supervisión.
	 * 
	 * @param idGrupoCriterio Id del grupo de criterio a eliminar.
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public Long eliminarGrupo(Long idGrupoCriterio, AuditoriaDTO auditoriaDTO) {
		Long rpta = 0L;
		PgimMatrizGrpoCrtrio grupoCriterio = null;
		if (CommonsUtil.isNullOrZeroLong(idGrupoCriterio)) {
			grupoCriterio = this.matrizGrpoCrtrioRepository.findById(idGrupoCriterio).orElse(null);
			if (grupoCriterio != null) {
				grupoCriterio.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				grupoCriterio.setFeActualizacion(auditoriaDTO.getFecha());
				grupoCriterio.setUsActualizacion(auditoriaDTO.getUsername());
				grupoCriterio.setIpActualizacion(auditoriaDTO.getTerminal());
				this.matrizGrpoCrtrioRepository.save(grupoCriterio);
				rpta = grupoCriterio.getIdMatrizGrpoCrtrio();
			}
		}
		return rpta;
	}

	/**
	 * Permite obtener un registro de grupo de matriz de supervisión por ID de grupo
	 * 
	 * @param idGrupo
	 * @return
	 */
	@Override
	public PgimMatrizGrpoCrtrioDTO obtenerMatrizGrupoPorId(Long idGrupo) {
		return this.matrizGrpoCrtrioRepository.obtenerGrupoCriterioPorId(idGrupo);
	}

	@Override
	public Page<PgimMatrizCriterioAuxDTO> listarMatrizCriterio(final Long idMatrizSupervision,
			final PgimMatrizCriterioAuxDTO filtroPgimMatrizCriterioAuxDTO, final Pageable paginador) throws Exception {

		Page<PgimMatrizCriterioAuxDTO> pPgimMatrizCriterioAuxDTO = this.matrizCriterioAuxRepository
				.listarMatrizCriterio(idMatrizSupervision, filtroPgimMatrizCriterioAuxDTO.getIdMatrizGrpoCrtrio(),
						filtroPgimMatrizCriterioAuxDTO.getDeMatrizCriterio(),
						filtroPgimMatrizCriterioAuxDTO.getNuObligaciones(), 
						filtroPgimMatrizCriterioAuxDTO.getNorma(), 
						filtroPgimMatrizCriterioAuxDTO.getTipificacion(), 
						paginador);
		return pPgimMatrizCriterioAuxDTO;
	}

	/**
	 * Me permite filtrar por el nombre de grupo de criterio de la matriz de
	 * supervision para la lista de criterios de la matriz
	 * 
	 * @param nombre
	 * @return
	 */
	@Override
	public List<PgimMatrizGrpoCrtrioDTO> filtrarPorGrupoCriterio(Long idMatrizSupervision) {
		List<PgimMatrizGrpoCrtrioDTO> lPgimMatrizGrpoCrtrioDTO = this.matrizGrpoCrtrioRepository
				.filtrarPorGrupoCriterio(idMatrizSupervision);
		return lPgimMatrizGrpoCrtrioDTO;
	}

	/**
	 * Permite obtener un registro de criterio de matriz de supervisión por ID de
	 * criterio
	 * 
	 * @param idCriterio
	 * @return
	 */
	@Override
	public PgimMatrizCriterioDTO obtenerMatrizCriterioPorId(Long idCriterio) {
		return this.matrizCriterioRepository.obtenerMatrizCriterioPorId(idCriterio);
	}

	/***
	 * Permite crear un criterio de matriz de supervisión.
	 * 
	 * @param pgimMatrizCriterioDTO
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimMatrizCriterioDTO crearCriterio(PgimMatrizCriterioDTO pgimMatrizCriterioDTO, AuditoriaDTO auditoriaDTO) {

		PgimMatrizCriterio pgimMatrizCriterio = new PgimMatrizCriterio();

		pgimMatrizCriterio.setPgimMatrizGrpoCrtrio(new PgimMatrizGrpoCrtrio());
		pgimMatrizCriterio.getPgimMatrizGrpoCrtrio()
				.setIdMatrizGrpoCrtrio(pgimMatrizCriterioDTO.getIdMatrizGrpoCrtrio());

		pgimMatrizCriterio.setCoMatrizCriterio(pgimMatrizCriterioDTO.getCoMatrizCriterio());
		pgimMatrizCriterio.setDeMatrizCriterio(pgimMatrizCriterioDTO.getDeMatrizCriterio());
		pgimMatrizCriterio.setDeBaseLegal(pgimMatrizCriterioDTO.getDeBaseLegal());
		pgimMatrizCriterio.setNuOrden(pgimMatrizCriterioDTO.getNuOrden());
		pgimMatrizCriterio.setEsVigente(pgimMatrizCriterioDTO.getEsVigente());
		pgimMatrizCriterio.setFlCopiaPorDefecto(pgimMatrizCriterioDTO.getFlCopiaPorDefecto());

		pgimMatrizCriterio.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimMatrizCriterio.setFeCreacion(auditoriaDTO.getFecha());
		pgimMatrizCriterio.setUsCreacion(auditoriaDTO.getUsername());
		pgimMatrizCriterio.setIpCreacion(auditoriaDTO.getTerminal());

		PgimMatrizCriterio pgimMatrizCriterioCreado = this.matrizCriterioRepository.save(pgimMatrizCriterio);
		PgimMatrizCriterioDTO pgimMatrizCriterioDTOCreado = this.matrizCriterioRepository
				.obtenerMatrizCriterioPorId(pgimMatrizCriterioCreado.getIdMatrizCriterio());
		return pgimMatrizCriterioDTOCreado;
	}

	/***
	 * Permite obtener un objeto criterio de matriz de supervisión con el valor
	 * idMatrizCriterio.
	 * 
	 * @param idMatrizCriterio ID del criterio de matriz de supervisión.
	 * @return
	 */
	@Override
	public PgimMatrizCriterio getMatrizCriterioById(Long idMatrizCriterio) {
		return this.matrizCriterioRepository.findById(idMatrizCriterio).orElse(null);
	}

	/***
	 * Permite modificar un criterio de matriz de supervisión.
	 * 
	 * @param pgimMatrizCriterioDTO criterio DTO que porta los datos con los nuevos
	 *                              valores para la actualización.
	 * @param pgimMatrizCriterio    criterio al que se actualizarán los nuevos
	 *                              valores.
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimMatrizCriterioDTO modificarCriterio(PgimMatrizCriterioDTO pgimMatrizCriterioDTO,
			PgimMatrizCriterio pgimMatrizCriterio, AuditoriaDTO auditoriaDTO) {

		pgimMatrizCriterio.setPgimMatrizGrpoCrtrio(new PgimMatrizGrpoCrtrio());
		pgimMatrizCriterio.getPgimMatrizGrpoCrtrio()
				.setIdMatrizGrpoCrtrio(pgimMatrizCriterioDTO.getIdMatrizGrpoCrtrio());

		pgimMatrizCriterio.setCoMatrizCriterio(pgimMatrizCriterioDTO.getCoMatrizCriterio());
		pgimMatrizCriterio.setDeMatrizCriterio(pgimMatrizCriterioDTO.getDeMatrizCriterio());
		pgimMatrizCriterio.setDeBaseLegal(pgimMatrizCriterioDTO.getDeBaseLegal());
		pgimMatrizCriterio.setNuOrden(pgimMatrizCriterioDTO.getNuOrden());
		pgimMatrizCriterio.setEsVigente(pgimMatrizCriterioDTO.getEsVigente());
		pgimMatrizCriterio.setFlCopiaPorDefecto(pgimMatrizCriterioDTO.getFlCopiaPorDefecto());

		pgimMatrizCriterio.setFeActualizacion(auditoriaDTO.getFecha());
		pgimMatrizCriterio.setUsActualizacion(auditoriaDTO.getUsername());
		pgimMatrizCriterio.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimMatrizCriterio pgimMatrizCriterioModificado = this.matrizCriterioRepository.save(pgimMatrizCriterio);

		PgimMatrizCriterioDTO pgimMatrizCriterioDTOResultado = this.matrizCriterioRepository
				.obtenerMatrizCriterioPorId(pgimMatrizCriterioModificado.getIdMatrizCriterio());

		return pgimMatrizCriterioDTOResultado;
	}

	/***
	 * Permite eliminar un criterio de matriz de supervisión.
	 * 
	 * @param idCriterio Id del criterio a eliminar.
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public Long eliminarCriterio(Long idCriterio, AuditoriaDTO auditoriaDTO) {
		Long rpta = 0L;
		PgimMatrizCriterio criterio = null;
		if (CommonsUtil.isNullOrZeroLong(idCriterio)) {

			//validación para verificar si al menos uno de sus obligaciones estan haciendo usadas en la Fiscalización
			List<PgimOblgcnNrmaCrtrioDTO> lPgimMatrizCriterioAuxDTO = this.oblgcnNrmaCrtrioRepository
				.listarObligacionNrmaCriterioAux(idCriterio);
			
			for (PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTO : lPgimMatrizCriterioAuxDTO) {
				if(pgimOblgcnNrmaCrtrioDTO.getDescNuHijos() > 0){
					throw new PgimException(TipoResultado.ERROR,"El criterio no puede ser eliminado debido a que al menos una de sus obligaciones han sido utilizadas en una fiscalización.");
				}
			}

			criterio = this.matrizCriterioRepository.findById(idCriterio).orElse(null);
			if (criterio != null) {
				criterio.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				criterio.setFeActualizacion(auditoriaDTO.getFecha());
				criterio.setUsActualizacion(auditoriaDTO.getUsername());
				criterio.setIpActualizacion(auditoriaDTO.getTerminal());
				this.matrizCriterioRepository.save(criterio);
				rpta = criterio.getIdMatrizCriterio();
			}
		}
		return rpta;
	}

	/**
	 * Permite obtener la lista de obligaciones normativas de criterios de a matriz
	 * de supervisión por ID de Matriz de criterio
	 * 
	 * @param idMatrizCriterio
	 * @return
	 */
	@Override
	public List<PgimOblgcnNrmaCrtrioDTO> listarObligacionNrmaCriterio(Long idMatrizCriterio)
			throws Exception {

		List<PgimOblgcnNrmaCrtrioDTO> pPgimMatrizCriterioAuxDTO = this.oblgcnNrmaCrtrioRepository
				.listarObligacionNrmaCriterioAux(idMatrizCriterio);

				for(PgimOblgcnNrmaCrtrioDTO data : pPgimMatrizCriterioAuxDTO){

					if(data.getIdNormaItemPadre() != null){
						PgimNormaItemDTO da = this.normaItemRepository.obtenerItemPorId(data.getIdNormaItemPadre());
						data.setDeNormaItemPadre(da.getDeContenidoT());
					} else {
						data.setDeNormaItemPadre("");
					}

				}
		return pPgimMatrizCriterioAuxDTO;
	}

	/**
	 * Permite obtener la lista de obligaciones normativas de criterios por el id
	 * 
	 * @param idOblgcnNrmaCrtrio
	 * @return
	 */
	@Override
	public PgimOblgcnNrmaCrtrioDTO obtenerObligacionNrmaCriterioPorId(Long idOblgcnNrmaCrtrio) {
		return this.oblgcnNrmaCrtrioRepository.obtenerObligacionNrmaCriterioPorId(idOblgcnNrmaCrtrio);
	}

	/**
	 * Permite listar las obligaciones normativas del criterio
	 * 
	 * @param paginador
	 * @return
	 */
	@Override
	public Page<PgimOblgcnNrmaCrtrioDTO> listarObligacionNrmaCriterioSeleccion(Long idMatrizCriterio,
			PgimOblgcnNrmaCrtrioDTO filtroPgimOblgcnNrmaCrtrioDTO, Pageable paginador) throws Exception {
		
		Page<PgimOblgcnNrmaCrtrioDTO> pPgimMatrizCriterioAuxDTO = this.oblgcnNrmaCrtrioRepository
				.listarObligacionNrmaCriterioSeleccion(
						idMatrizCriterio,
						filtroPgimOblgcnNrmaCrtrioDTO.getDescDeContenido(),
						filtroPgimOblgcnNrmaCrtrioDTO.getDescIdTipoNorma(),
						filtroPgimOblgcnNrmaCrtrioDTO.getDescIdDivisionItem(),
						filtroPgimOblgcnNrmaCrtrioDTO.getDescNoCortoNorma(),
						filtroPgimOblgcnNrmaCrtrioDTO.getDescNoNorma(),
						filtroPgimOblgcnNrmaCrtrioDTO.getDescCoTipificacion(),
						filtroPgimOblgcnNrmaCrtrioDTO.getDescNoItemTipificacion(),
						paginador);
						
		for (PgimOblgcnNrmaCrtrioDTO data : pPgimMatrizCriterioAuxDTO.getContent()) {

			if (data.getIdNormaItemPadre() != null) {
				PgimNormaItemDTO da = this.normaItemRepository.obtenerItemPorId(data.getIdNormaItemPadre());
				data.setDeNormaItemPadre(da.getDeContenidoT());
			}else{
				data.setDeNormaItemPadre("");
			}

		}
		return pPgimMatrizCriterioAuxDTO;
	}

	/**
	 * Permite quitar o desvincular una obligación de un criterio de matriz de
	 * supervisión
	 * 
	 * @param idOblgcnNrmaCrtrio
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public Long eliminarObligacionNormaCriterio(Long idOblgcnNrmaCrtrio, AuditoriaDTO auditoriaDTO) {
		Long rpta = 0L;
		PgimOblgcnNrmaCrtrio obligacionNormaCriterio = null;
		if (CommonsUtil.isNullOrZeroLong(idOblgcnNrmaCrtrio)) {
			obligacionNormaCriterio = this.oblgcnNrmaCrtrioRepository.findById(idOblgcnNrmaCrtrio).orElse(null);
			if (obligacionNormaCriterio != null) {
				obligacionNormaCriterio.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				obligacionNormaCriterio.setFeActualizacion(auditoriaDTO.getFecha());
				obligacionNormaCriterio.setUsActualizacion(auditoriaDTO.getUsername());
				obligacionNormaCriterio.setIpActualizacion(auditoriaDTO.getTerminal());
				this.oblgcnNrmaCrtrioRepository.save(obligacionNormaCriterio);
				rpta = obligacionNormaCriterio.getIdOblgcnNrmaCrtrio();
			}
		}
		return rpta;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimOblgcnNrmaCrtrioDTO seleccionarObligacion(@Valid PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTO,
			AuditoriaDTO auditoriaDTO) {
		PgimOblgcnNrmaCrtrio pgimOblgcnNrmaCrtrio = new PgimOblgcnNrmaCrtrio();

		String esVigentes = "1";

		pgimOblgcnNrmaCrtrio.setPgimMatrizCriterio(new PgimMatrizCriterio());
		pgimOblgcnNrmaCrtrio.getPgimMatrizCriterio().setIdMatrizCriterio(pgimOblgcnNrmaCrtrioDTO.getIdMatrizCriterio());

		pgimOblgcnNrmaCrtrio.setPgimNormaObligacion(new PgimNormaObligacion());
		pgimOblgcnNrmaCrtrio.getPgimNormaObligacion()
				.setIdNormaObligacion(pgimOblgcnNrmaCrtrioDTO.getIdNormaObligacion());

		pgimOblgcnNrmaCrtrio.setEsVigente(esVigentes);

		pgimOblgcnNrmaCrtrio.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimOblgcnNrmaCrtrio.setFeCreacion(auditoriaDTO.getFecha());
		pgimOblgcnNrmaCrtrio.setUsCreacion(auditoriaDTO.getUsername());
		pgimOblgcnNrmaCrtrio.setIpCreacion(auditoriaDTO.getTerminal());

		PgimOblgcnNrmaCrtrio pgimOblgcnNrmaCrtrioCreado = this.oblgcnNrmaCrtrioRepository.save(pgimOblgcnNrmaCrtrio);

		PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTOCreado = this
				.obtenerObligacionNrmaCriterioPorId(pgimOblgcnNrmaCrtrioCreado.getIdOblgcnNrmaCrtrio());

		return pgimOblgcnNrmaCrtrioDTOCreado;
	}

	@Override
	public Long obtenerIdMatrizSupervisionByIdSupervision(Long idSupervision) {
		return this.matrizCriterioAuxRepository.obtenerIdMatrizSupervisionByIdSupervision(idSupervision);
	}

	@Override
	public PgimMatrizSupervisionDTO obtenerCuadroVerificacionPorIdSupervision(Long idSupervision) {
		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO = null;
		Long idMatrizSupervision = this.matrizCriterioAuxRepository
				.obtenerIdMatrizSupervisionByIdSupervision(idSupervision);

		if (idMatrizSupervision != null) {
			PgimMatrizSupervision pgimMatrizSupervision = this.matrizSupervisionRepository.findById(idMatrizSupervision)
					.orElse(null);
			if (pgimMatrizSupervision != null) {
				pgimMatrizSupervisionDTO = new PgimMatrizSupervisionDTO();
				BeanUtils.copyProperties(pgimMatrizSupervision, pgimMatrizSupervisionDTO);
			}
		}

		return pgimMatrizSupervisionDTO;
	}

	@Override
	public Page<PgimMatrizCriterioAuxDTO> listarCriteriosNoFiscalizacion(Long idSupervision, Long idMatrizSupervision,
			PgimMatrizCriterioAuxDTO filtroPgimMatrizCriterioAuxDTO, Pageable paginador) throws Exception {

		Page<PgimMatrizCriterioAuxDTO> pPgimMatrizCriterioAuxDTO = this.matrizCriterioAuxRepository
				.listarCriteriosNoFiscalizacion(idSupervision, idMatrizSupervision,
						filtroPgimMatrizCriterioAuxDTO.getIdMatrizGrpoCrtrio(),
						filtroPgimMatrizCriterioAuxDTO.getDeMatrizCriterio(),
						filtroPgimMatrizCriterioAuxDTO.getNuObligaciones(), 
						filtroPgimMatrizCriterioAuxDTO.getNorma(), 
						filtroPgimMatrizCriterioAuxDTO.getTipificacion(), 
						paginador);

		return pPgimMatrizCriterioAuxDTO;

	}

	@Override
	public Page<PgimMatrizSupervisionDTO> listarMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO,
			Pageable paginador) {
				
		Page<PgimMatrizSupervisionDTO> lPgimMatrizSupervisionDTO = this.matrizSupervisionRepository
				.listarMatrizSupervision(pgimMatrizSupervisionDTO.getIdEspecialidad(), paginador);

		// Se determina cuál es la regla de configuración base correspondiente con la fiscalización>
		PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByIdSupervisionCompleta(pgimMatrizSupervisionDTO.getDescIdSupervision());

		PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO = this.prgrmSupervisionRepository.obtenerPrograma(pgimSupervisionDTO.getIdProgramaSupervision());

		Long idTipoCfgBaseCuadroVerificacion = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.CUADROS_BASE_VERIFICACION.toString());

		PgimUnidadMinera umFiltroRegla = this.unidadMineraRepository.findById(pgimSupervisionDTO.getIdUnidadMinera()).orElse(null);

		PgimReglaBaseDTO pgimReglaBaseDTOFiltro = new PgimReglaBaseDTO();

		// Propiedades principales de comparación de configuración regla base obtenida de la fiscalización
		pgimReglaBaseDTOFiltro.setIdDivisionSupervisora(pgimPrgrmSupervisionDTO.getIdDivisionSupervisora());
		pgimReglaBaseDTOFiltro.setIdMotivoSupervision(pgimSupervisionDTO.getIdMotivoSupervision());
		if (umFiltroRegla.getMetodoMinado() != null) {
			pgimReglaBaseDTOFiltro.setIdMetodoMinado(umFiltroRegla.getMetodoMinado().getIdValorParametro());
		}
		pgimReglaBaseDTOFiltro.setIdSubtipoSupervision(pgimSupervisionDTO.getIdSubtipoSupervision());
		pgimReglaBaseDTOFiltro.setFlPropia(pgimSupervisionDTO.getFlPropia());
		pgimReglaBaseDTOFiltro.setFlConPiques(umFiltroRegla.getFlConPiques());
		pgimReglaBaseDTOFiltro.setDescIdTipoConfiguracionBase(idTipoCfgBaseCuadroVerificacion);

		List<PgimReglaBaseDTO> pgimReglaBaseDTOCuadroVerificacion = this.configuracionBaseService.obtenerReglaPorSupervisionCompatible(pgimReglaBaseDTOFiltro);

		//

		if (pgimReglaBaseDTOCuadroVerificacion == null) {
			return lPgimMatrizSupervisionDTO;
		}

		for (PgimMatrizSupervisionDTO pgimMatrizSupervisionDTOIter : lPgimMatrizSupervisionDTO.getContent()) {

			for(PgimReglaBaseDTO pgimReglaBaseDTO: pgimReglaBaseDTOCuadroVerificacion){

				if(pgimMatrizSupervisionDTOIter.getIdConfiguracionBase() != null){
					if (pgimMatrizSupervisionDTOIter.getIdConfiguracionBase().equals(pgimReglaBaseDTO.getIdConfiguracionBase())) {
						pgimMatrizSupervisionDTOIter.setDescEsCompatibleConSupervision("1");
					}
				}
			}

		}

		return lPgimMatrizSupervisionDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public PgimMatrizSupervisionDTO copiarMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTOPadre, AuditoriaDTO auditoriaDTO) throws Exception {
		
		// copiado de la configuración base
		PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO = this.configuracionBaseServiceImpl.obtenerCfgBasePorId(pgimMatrizSupervisionDTOPadre.getIdConfiguracionBase());
		pgimConfiguracionBaseDTO.setNoCofiguracionBase(pgimMatrizSupervisionDTOPadre.getDescNoConfiguracionBase());
		PgimConfiguracionBaseDTO pgimConfiguracionBaseDTOCreado = this.configuracionBaseServiceImpl.crearCfgBase(pgimConfiguracionBaseDTO, auditoriaDTO);
		
		List<PgimReglaBaseDTO> lPgimReglaBaseDTO = this.reglaBaseRepository.listarReglasPorCfgBase(pgimMatrizSupervisionDTOPadre.getIdConfiguracionBase());
		for (PgimReglaBaseDTO pgimReglaBaseDTO : lPgimReglaBaseDTO) {
			pgimReglaBaseDTO.setIdConfiguracionBase(pgimConfiguracionBaseDTOCreado.getIdConfiguracionBase());
			this.configuracionBaseServiceImpl.crearReglaCfgBase(pgimReglaBaseDTO, auditoriaDTO);
		}

		// copiado del cuadro de verificación
		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO = new  PgimMatrizSupervisionDTO();
		pgimMatrizSupervisionDTO.setIdMatrizSupervision(pgimMatrizSupervisionDTOPadre.getIdMatrizSupervision());
		pgimMatrizSupervisionDTO.setDeMatrizSupervision(pgimMatrizSupervisionDTOPadre.getDeMatrizSupervision());
		pgimMatrizSupervisionDTO.setIdConfiguracionBase(pgimConfiguracionBaseDTOCreado.getIdConfiguracionBase());
		pgimMatrizSupervisionDTO.setIdEspecialidad(pgimMatrizSupervisionDTOPadre.getIdEspecialidad());
		PgimMatrizSupervisionDTO pgimMatrizSupervisionDTOCopiada = this.crearMatrizSupervision(pgimMatrizSupervisionDTO, auditoriaDTO);
		
		List<PgimMatrizGrpoCrtrioDTO> lPgimMatrizGrpoCrtrioDTO = this.listarMatrizGrpoCrtrio(pgimMatrizSupervisionDTOPadre.getIdMatrizSupervision());
		for (PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTO : lPgimMatrizGrpoCrtrioDTO) {
			pgimMatrizGrpoCrtrioDTO.setIdMatrizSupervision(pgimMatrizSupervisionDTOCopiada.getIdMatrizSupervision());
			PgimMatrizGrpoCrtrioDTO pgimMatrizGrpoCrtrioDTOCopiada = this.crearGrupoCriterio(pgimMatrizGrpoCrtrioDTO, auditoriaDTO);

			List<PgimMatrizCriterioDTO> lPgimMatrizCriterioDTO = this.matrizCriterioRepository.listarMatrizCriterioPorIdGrupoCriterio(pgimMatrizGrpoCrtrioDTO.getIdMatrizGrpoCrtrio());
			for (PgimMatrizCriterioDTO pgimMatrizCriterioDTO : lPgimMatrizCriterioDTO) {
				pgimMatrizCriterioDTO.setIdMatrizGrpoCrtrio(pgimMatrizGrpoCrtrioDTOCopiada.getIdMatrizGrpoCrtrio());
				this.crearCriterio(pgimMatrizCriterioDTO, auditoriaDTO);
			}
		}
		
		return pgimMatrizSupervisionDTOCopiada;
	}

	@Override
	public void validarEliminacionCuadroVerificacion(PgimMatrizSupervision pgimMatrizSupervisionActual, AuditoriaDTO auditoriaDTO) throws Exception{

		List<PgimMatrizSupervisionAuxDTO> lPgimMatrizSupervisionAuxDTO = this.matrizSupervisionAuxRepository.listarSupervisionesPorCuadroVerificacion(pgimMatrizSupervisionActual.getIdMatrizSupervision());

		if(lPgimMatrizSupervisionAuxDTO.size() > 0){

			for(PgimMatrizSupervisionAuxDTO obj: lPgimMatrizSupervisionAuxDTO){

				PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionPorId(obj.getIdSupervision());

				if(pgimSupervisionDTO != null){
					obj.setDescCoSupervision(pgimSupervisionDTO.getCoSupervision());
				}
			}

			 List<PgimMatrizSupervisionAuxDTO> cantidad = lPgimMatrizSupervisionAuxDTO.stream()
			 .filter(registro -> registro.getDescCoSupervision() != null) // Filtrar valores nulos
			 .collect(Collectors.toMap(PgimMatrizSupervisionAuxDTO::getDescCoSupervision, registro -> registro, (existing, replacement) -> existing))
			 .values()
			 .stream()
			 .collect(Collectors.toList());

			 String mensaje = lPgimMatrizSupervisionAuxDTO.stream().map(registro -> registro.getDescCoSupervision()).filter(descCoSupervision -> descCoSupervision != null)
            																	 .collect(Collectors.joining(", ")); // Unir con una coma y espacio
			 
			 throw new PgimException(TipoResultado.WARNING, mensaje, cantidad.size());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void eliminarCuadroVerificacion(PgimMatrizSupervision pgimMatrizSupervisionActual, AuditoriaDTO auditoriaDTO) throws Exception{

		pgimMatrizSupervisionActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimMatrizSupervisionActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimMatrizSupervisionActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimMatrizSupervisionActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.matrizSupervisionRepository.save(pgimMatrizSupervisionActual);
	}
}

