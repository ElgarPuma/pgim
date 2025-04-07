package pe.gob.osinergmin.pgim.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroCotejoHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoCnsttdoFaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaHchocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaItemDTO;
import pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaconAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimCmineroSprvsion;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteHc;
import pe.gob.osinergmin.pgim.models.entity.PgimCriterioSprvsion;
import pe.gob.osinergmin.pgim.models.entity.PgimHechoCnsttdoFase;
import pe.gob.osinergmin.pgim.models.entity.PgimHechoConstatado;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccion;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccionContra;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizCriterio;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaItem;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmaCrtrio;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmtvaHchoc;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmtvaItem;
import pe.gob.osinergmin.pgim.models.entity.PgimPasoProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.ComponenteHcRepository;
import pe.gob.osinergmin.pgim.models.repository.CotejoHechoCnsttdoRepository;
import pe.gob.osinergmin.pgim.models.repository.CriterioSprvsionRepository;
import pe.gob.osinergmin.pgim.models.repository.HechoCnsttdoFaseRepository;
import pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionContraRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.OblgcnNrmtvaHchocRepository;
import pe.gob.osinergmin.pgim.models.repository.OblgcnNrmtvaItemRepository;
import pe.gob.osinergmin.pgim.models.repository.ObligacionNormaAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalConAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.services.ComponenteHcService;
import pe.gob.osinergmin.pgim.services.HechoConstatadoService;
import pe.gob.osinergmin.pgim.services.InfraccionService;
import pe.gob.osinergmin.pgim.services.MatrizSupervisionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.ETipoAccionCrud;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Hecho verificado
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Service
@Transactional(readOnly = true)
public class HechoConstatadoServiceImpl implements HechoConstatadoService {

	@Autowired
	private MatrizSupervisionAuxRepository matrizSupervisionAuxRepository;
	
	@Autowired
	private MatrizSupervisionService matrizSupervisionService;

	@Autowired
	private SupervisionRepository supervisionRepository;

	@Autowired
	private CriterioSprvsionRepository criterioSprvsionRepository;

	@Autowired
	private HechoConstatadoRepository hechoConstatadoRepository;

	@Autowired
	private ObligacionNormaAuxRepository obligacionNormaAuxRepository;

	@Autowired
	private ComponenteHcRepository componenteHcRepository;

	@Autowired
	private OblgcnNrmtvaHchocRepository oblgcnNrmtvaHchocRepository;

	@Autowired
	private InstanciaPasoRepository instanciaPasoRepository;

	@Autowired
	private CotejoHechoCnsttdoRepository cotejoHechoCnsttdoRepository;

	@Autowired
	private PersonalOsiAuxRepository personalOsiAuxRepository;

	@Autowired
	private PersonalConAuxRepository personalConAuxRepository;

	@Autowired
	private InfraccionRepository infraccionRepository;

	@Autowired
	private InfraccionAuxRepository infraccionAuxRepository;

	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private FlujoTrabajoServiceImpl flujoTrabajoService;
	
	@Autowired
	private SupervisionService supervisionService;
	
	@Autowired
	private HechoCnsttdoFaseRepository hechoCnsttdoFaseRepository;
	
	@Autowired
	private RelacionPasoRepository relacionPasoRepository;
	
	@Autowired
	private OblgcnNrmtvaItemRepository oblgcnNrmtvaItemRepository;
	
	@Autowired
	private InfraccionService infraccionService;
	
	@Autowired
	private InfraccionContraRepository infraccionContraRepository;
	
	@Autowired
	private ComponenteHcService componenteHcService;
		

	@Override
	public List<PgimHechoConstatadoDTO> obtenerHechosConstatados(Long idSupervision, Long rol) {
		return hechoConstatadoRepository.listarHechosConstatadosPorSupervision(idSupervision, rol);
	}

	@Override
	public List<PgimHechoConstatadoDTO> listarHechosConstatadosConNoCumplimientos(Long idSupervision, Long rol) {
		return hechoConstatadoRepository.listarHechosConstatadosConNoCumplimientos(idSupervision, rol);
	}

	@Override
	public List<PgimHechoConstatadoDTO> obtenerHechosConstatadosIncumplimiento(Long idSupervision, Long rol) {
		return hechoConstatadoRepository.obtenerHechosConstatadosIncumplimiento(idSupervision, rol);
	}

	@Override
	public List<PgimHechoConstatadoDTO> obtenerHechosConstatados(Long idSupervision, Long rol1, Long rol2) {
		return hechoConstatadoRepository.listarHechosConstatadosPorSupervision(idSupervision, rol1, rol2);
	}

	@Override
	public List<PgimHechoConstatadoDTO> obtenerHechosConstatadosIncumplimiento(Long idSupervision, Long rol1,
			Long rol2) {
		return hechoConstatadoRepository.obtenerHechosConstatadosIncumplimiento(idSupervision, rol1, rol2);
	}

	@Override
	public List<PgimHechoConstatadoDTO> obtenerHechosConstatadosFicha(Long idSupervision, Long rol1, Long rol2) {
		return hechoConstatadoRepository.obtenerHechosConstatadosFicha(idSupervision, rol1, rol2);
	}
	
	@Override
	public List<PgimHechoConstatadoDTO> obtenerHechosConstatadosParaFEHV(Long idSupervision, Long rol1, Long rol2) {
		return hechoConstatadoRepository.listarHechosConstatadosPorSupervisionParaFEHV(idSupervision, rol1, rol2);
	}

	/**
	 * Permite recuperar la lista de criterios de la matriz de supervision
	 * 
	 * 
	 * @param idSupervision
	 * @param cogidoCumple
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<PgimMatrizSupervisionAuxDTO> listarMatrizSupervision(final Long idSupervision,
			final List<Long> codigoCumpleSelected,
			final String tipoMatriz, final Long idFaseSeleccion, final Long idFaseActual, final String deNorma,
			final String deTipificacion, final Pageable paginador) throws Exception {

		if (idFaseSeleccion < idFaseActual && idFaseSeleccion != ConstantesUtil.PARAM_SUPERVISION_PRE_SUPERVISION) {

			Map<Long, String> estadoMap = new HashMap<>();

			for (Long codigoCumple : codigoCumpleSelected) {
				if (codigoCumple.equals(2L)) {
					estadoMap.put(2L, "Si cumple");
				} else if (codigoCumple.equals(3L)) {
					estadoMap.put(3L, "No cumple");
				} else if (codigoCumple.equals(1L)) {
					estadoMap.put(1L, "No aplica");
				} else if (codigoCumple.equals(4L)) {
					estadoMap.put(4L, "Archivado");
				}
			}

			// Si necesitas un conjunto de valores
			List<String> estadosFiltro = new ArrayList<>(estadoMap.values());

			Page<PgimMatrizSupervisionAuxDTO> lPgimMatrizSupervisionAuxDTO = this.matrizSupervisionAuxRepository
					.listarMatrizSupervisionFaseHist(idSupervision,
							0L,
							idFaseSeleccion,
							deNorma,
							deTipificacion,
							paginador);

			List<PgimMatrizSupervisionAuxDTO> lPgimMatrizSupervisionAuxDTOList = this.matrizSupervisionAuxRepository
					.listarMatrizSupervisionFaseHistList(idSupervision,
							0L,
							idFaseSeleccion,
							deNorma,
							deTipificacion);

			List<PgimMatrizSupervisionAuxDTO> filtrado = null;

			int cantidadRegistros = 0;

			filtrado = lPgimMatrizSupervisionAuxDTOList.stream()
					.filter(dto -> estadosFiltro.contains(dto.getDescripcionCumple()))
					.sorted(Comparator.comparing(PgimMatrizSupervisionAuxDTO::getCoMatrizCriterio))
					.collect(Collectors.toList());

			cantidadRegistros = filtrado.size();

			if (filtrado.size() == 0) {

				if (estadosFiltro.size() != 0) {
					filtrado = lPgimMatrizSupervisionAuxDTO.getContent().stream()
							.filter(dto -> estadosFiltro.contains(dto.getDescripcionCumple()))
							.sorted(Comparator.comparing(PgimMatrizSupervisionAuxDTO::getCoMatrizCriterio))
							.collect(Collectors.toList());
				} else {
					filtrado = lPgimMatrizSupervisionAuxDTO.getContent();
				}
				cantidadRegistros = (int) lPgimMatrizSupervisionAuxDTO.getTotalElements();
			}

			// Crear un nuevo Page a partir de los resultados filtrados
			Page<PgimMatrizSupervisionAuxDTO> paginaFiltrada = new PageImpl<>(filtrado, paginador, cantidadRegistros);

			// TipoMatriz: S=Supervisión; A=Aprobación
			return paginaFiltrada;

		} else {

			if (idFaseSeleccion.equals(2L)) {
				return this.matrizSupervisionAuxRepository
						.listarMatrizSupervision(idSupervision,
								deNorma,
								deTipificacion,
								0L,
								tipoMatriz,
								paginador);
			}

			Map<Long, String> estadoMap = new HashMap<>();

			for (Long codigoCumple : codigoCumpleSelected) {
				if (codigoCumple.equals(2L)) {
					estadoMap.put(2L, "Si cumple");
				} else if (codigoCumple.equals(3L)) {
					estadoMap.put(3L, "No cumple");
				} else if (codigoCumple.equals(1L)) {
					estadoMap.put(1L, "No aplica");
				} else if (codigoCumple.equals(4L)) {
					estadoMap.put(4L, "Archivado");
				}
			}

			// Si necesitas un conjunto de valores
			List<String> estadosFiltro = new ArrayList<>(estadoMap.values());

			Page<PgimMatrizSupervisionAuxDTO> lPgimMatrizSupervisionAuxDTO = this.matrizSupervisionAuxRepository
					.listarMatrizSupervision(idSupervision,
							deNorma,
							deTipificacion,
							0L,
							tipoMatriz,
							paginador);

			for (PgimMatrizSupervisionAuxDTO pgimMatrizSupervisionAuxDTO : lPgimMatrizSupervisionAuxDTO) {

				// Variables para contar los estados
				int countSiCumple = 0; // 307
				int countNoCumple = 0; // 308
				int countCNoAplica = 0; // 309
				int countCArchivado = 0; // 532, 533, 534, 535, 536, 537

				List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTO = this.listarHechosConstatadosPorCriterioMatriz(
						pgimMatrizSupervisionAuxDTO.getIdCriterioSprvsion(), 0L);

				for (PgimHechoConstatadoDTO pgimHechoConstatadoDTO : lPgimHechoConstatadoDTO) {
					Long tipoCumplimiento = pgimHechoConstatadoDTO.getIdTipoCumplimiento();

					if (tipoCumplimiento.equals(532L) ||
							tipoCumplimiento.equals(533L) ||
							tipoCumplimiento.equals(534L) ||
							tipoCumplimiento.equals(535L) ||
							tipoCumplimiento.equals(536L) ||
							tipoCumplimiento.equals(537L)) {
						countCArchivado++;
					} else if (tipoCumplimiento.equals(307L)) {
						countSiCumple++;
					} else if (tipoCumplimiento.equals(308L)) {
						countNoCumple++;
					} else if (tipoCumplimiento.equals(309L)) {
						countCNoAplica++;
					}
				}

				/*
				 * Si existen mas de un hecho con diferente estado archivado, la columna
				 * mostrará “Archivado”.
				 */
				if (countCArchivado > 1) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");

					/**
					 * Si existen estados “No cumple” y uno o más de tipo archivado, la columna
					 * mostrará “No cumple”. Basta que un hecho sea “No cumple” para que se muestre
					 * “No cumple”.
					 */
					if (countNoCumple > 0 && countCArchivado > 0) {
						pgimMatrizSupervisionAuxDTO.setDescripcionCumple("No cumple");
					}
				}

				/**
				 * Si existen estados “No cumple” y uno o más de tipo archivado, la columna
				 * mostrará “No cumple”. Basta que un hecho sea “No cumple” para que se muestre
				 * “No cumple”.
				 */
				else if (countNoCumple > 0 && countCArchivado > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("No cumple");
				}

				/**
				 * Si existen estados “Sí cumple” y uno o más de tipo archivado, la columna
				 * mostrará “Archivado”
				 */
				else if (countSiCumple > 0 && countCArchivado > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");
				}

				/**
				 * Si existen estados “No aplica” y uno o más de tipo archivado, la columna
				 * mostrará “Archivado”.
				 */
				else if (countCNoAplica > 0 && countCArchivado > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");
				}

				/**
				 * Si existen estados “No aplica” y uno o más de tipo “No cumple”, la columna
				 * mostrará “No cumple”.
				 */
				else if (countCNoAplica > 0 && countNoCumple > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("No cumple");
				}

				/**
				 * Si existen estados “No aplica” y uno o más de tipo “Sí cumple”, la columna
				 * mostrará “Sí cumple”.
				 */
				else if (countCNoAplica > 0 && countSiCumple > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Si cumple");
				}

				/**
				 * Si existen estados “Si cumple” y estados “No aplica” y uno o más de tipo
				 * archivado, la columna mostrará “Archivado”.
				 */
				else if (countSiCumple > 0 && countCNoAplica > 0 && countCArchivado > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");
				} else if (countCArchivado == 1) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");
				}
			}

			List<PgimMatrizSupervisionAuxDTO> lPgimMatrizSupervisionAuxDTOList = this.matrizSupervisionAuxRepository
					.listarMatrizSupervisionList(idSupervision,
							deNorma,
							deTipificacion,
							0L,
							// coClaveSiCumple,coClaveNoCumple,coClaveNoAplica,
							// coClaveArchivado,
							tipoMatriz);

			for (PgimMatrizSupervisionAuxDTO pgimMatrizSupervisionAuxDTO : lPgimMatrizSupervisionAuxDTOList) {

				// Variables para contar los estados
				int countSiCumple = 0; // 307
				int countNoCumple = 0; // 308
				int countCNoAplica = 0; // 309
				int countCArchivado = 0; // 532, 533, 534, 535, 536, 537

				List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTO = this.listarHechosConstatadosPorCriterioMatriz(
						pgimMatrizSupervisionAuxDTO.getIdCriterioSprvsion(), 0L);

				for (PgimHechoConstatadoDTO pgimHechoConstatadoDTO : lPgimHechoConstatadoDTO) {
					Long tipoCumplimiento = pgimHechoConstatadoDTO.getIdTipoCumplimiento();

					if (tipoCumplimiento.equals(532L) ||
							tipoCumplimiento.equals(533L) ||
							tipoCumplimiento.equals(534L) ||
							tipoCumplimiento.equals(535L) ||
							tipoCumplimiento.equals(536L) ||
							tipoCumplimiento.equals(537L)) {
						countCArchivado++;
					} else if (tipoCumplimiento.equals(307L)) {
						countSiCumple++;
					} else if (tipoCumplimiento.equals(308L)) {
						countNoCumple++;
					} else if (tipoCumplimiento.equals(309L)) {
						countCNoAplica++;
					}
				}

				/*
				 * Si existen mas de un hecho con diferente estado archivado, la columna
				 * mostrará “Archivado”.
				 */
				if (countCArchivado > 1) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");

					/**
					 * Si existen estados “No cumple” y uno o más de tipo archivado, la columna
					 * mostrará “No cumple”. Basta que un hecho sea “No cumple” para que se muestre
					 * “No cumple”.
					 */
					if (countNoCumple > 0 && countCArchivado > 0) {
						pgimMatrizSupervisionAuxDTO.setDescripcionCumple("No cumple");
					}
				}

				/**
				 * Si existen estados “No cumple” y uno o más de tipo archivado, la columna
				 * mostrará “No cumple”. Basta que un hecho sea “No cumple” para que se muestre
				 * “No cumple”.
				 */
				else if (countNoCumple > 0 && countCArchivado > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("No cumple");
				}

				/**
				 * Si existen estados “Sí cumple” y uno o más de tipo archivado, la columna
				 * mostrará “Archivado”
				 */
				else if (countSiCumple > 0 && countCArchivado > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");
				}

				/**
				 * Si existen estados “No aplica” y uno o más de tipo archivado, la columna
				 * mostrará “Archivado”.
				 */
				else if (countCNoAplica > 0 && countCArchivado > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");
				}

				/**
				 * Si existen estados “No aplica” y uno o más de tipo “No cumple”, la columna
				 * mostrará “No cumple”.
				 */
				else if (countCNoAplica > 0 && countNoCumple > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("No cumple");
				}

				/**
				 * Si existen estados “No aplica” y uno o más de tipo “Sí cumple”, la columna
				 * mostrará “Sí cumple”.
				 */
				else if (countCNoAplica > 0 && countSiCumple > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Si cumple");
				}

				/**
				 * Si existen estados “Si cumple” y estados “No aplica” y uno o más de tipo
				 * archivado, la columna mostrará “Archivado”.
				 */
				else if (countSiCumple > 0 && countCNoAplica > 0 && countCArchivado > 0) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");
				} else if (countCArchivado == 1) {
					pgimMatrizSupervisionAuxDTO.setDescripcionCumple("Archivado");
				}
			}

			List<PgimMatrizSupervisionAuxDTO> filtrado = null;

			int cantidadRegistros = 0;

			filtrado = lPgimMatrizSupervisionAuxDTOList.stream()
					.filter(dto -> estadosFiltro.contains(dto.getDescripcionCumple()))
					.sorted(Comparator.comparing(PgimMatrizSupervisionAuxDTO::getCoMatrizCriterio))
					.collect(Collectors.toList());

			cantidadRegistros = filtrado.size();

			if (filtrado.size() == 0) {

				if (estadosFiltro.size() != 0) {
					filtrado = lPgimMatrizSupervisionAuxDTO.getContent().stream()
							.filter(dto -> estadosFiltro.contains(dto.getDescripcionCumple()))
							.sorted(Comparator.comparing(PgimMatrizSupervisionAuxDTO::getCoMatrizCriterio))
							.collect(Collectors.toList());
				} else {
					filtrado = lPgimMatrizSupervisionAuxDTO.getContent();
				}
				cantidadRegistros = (int) lPgimMatrizSupervisionAuxDTO.getTotalElements();
			}

			// Crear un nuevo Page a partir de los resultados filtrados
			Page<PgimMatrizSupervisionAuxDTO> paginaFiltrada = new PageImpl<>(filtrado, paginador, cantidadRegistros);

			// TipoMatriz: S=Supervisión; A=Aprobación
			return paginaFiltrada;
		}

	}
	
	@Override
	public List<PgimHechoCnsttdoFaseDTO> listarHechoCnsttdoFase(Long idSupervision,Long idFaseSeleccion,Date feGeneracion){
		return this.cotejoHechoCnsttdoRepository.listarHechoCnsttdoFase(idSupervision, idFaseSeleccion, feGeneracion);
	}	

	

	/**
	 * Permite generar la data de configuración de criterios de la matriz de
	 * fiscalización para una fiscalización en particular
	 * 
	 * 
	 * @param idSupervision
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	@Override
	public void generarDataConfiguracionCriterioMatriz(Long idSupervision, AuditoriaDTO auditoriaDTO) {// throws
																										// Exception{

		PgimSupervisionDTO pgimSupervisionDTO = supervisionRepository.obtenerSupervisionPorId(idSupervision);

		List<PgimCriterioSprvsionDTO> lPgimCriterioSprvsionDTO = criterioSprvsionRepository
				.generarConfiguracionCriteriosSupervision(pgimSupervisionDTO.getDescIdEspecialidad());

		for (PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO : lPgimCriterioSprvsionDTO) {

			PgimCriterioSprvsion pgimCriterioSprvsion = new PgimCriterioSprvsion();

			pgimCriterioSprvsion.setPgimSupervision(new PgimSupervision());
			pgimCriterioSprvsion.getPgimSupervision().setIdSupervision(idSupervision);

			pgimCriterioSprvsion.setPgimMatrizCriterio(new PgimMatrizCriterio());
			pgimCriterioSprvsion.getPgimMatrizCriterio()
					.setIdMatrizCriterio(pgimCriterioSprvsionDTO.getIdMatrizCriterio());

			pgimCriterioSprvsion.setCoMatrizCriterio(pgimCriterioSprvsionDTO.getCoMatrizCriterio());
			pgimCriterioSprvsion.setDeMatrizCriterio(pgimCriterioSprvsionDTO.getDeMatrizCriterio());
			pgimCriterioSprvsion.setEsVigenteMatrizCriterio(pgimCriterioSprvsionDTO.getEsVigenteMatrizCriterio());
			pgimCriterioSprvsion.setDeBaseLegal(pgimCriterioSprvsionDTO.getDeBaseLegal());
			pgimCriterioSprvsion.setIdMatrizGrpoCrtrio(pgimCriterioSprvsionDTO.getIdMatrizGrpoCrtrio());
			pgimCriterioSprvsion.setIdMatrizSupervision(pgimCriterioSprvsionDTO.getIdMatrizSupervision());
			pgimCriterioSprvsion.setCoMatrizGrpoCrtrio(pgimCriterioSprvsionDTO.getCoMatrizGrpoCrtrio());
			pgimCriterioSprvsion.setNoMatrizGrpoCrtrio(pgimCriterioSprvsionDTO.getNoMatrizGrpoCrtrio());
			pgimCriterioSprvsion.setNuOrdenGrpoCrtrio(pgimCriterioSprvsionDTO.getNuOrdenGrpoCrtrio());
			pgimCriterioSprvsion.setEsVisibleGrpoCrtrio(pgimCriterioSprvsionDTO.getEsVisibleGrpoCrtrio());
			pgimCriterioSprvsion.setNuOrdenCriterio(pgimCriterioSprvsionDTO.getNuOrdenCriterio());
			pgimCriterioSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimCriterioSprvsion.setFeCreacion(auditoriaDTO.getFecha());
			pgimCriterioSprvsion.setUsCreacion(auditoriaDTO.getUsername());
			pgimCriterioSprvsion.setIpCreacion(auditoriaDTO.getTerminal());

			this.criterioSprvsionRepository.save(pgimCriterioSprvsion);
		}
	}

	/**
	 * Permite obtener un registro de la entidad PgimCriterioSprvsion a partir de un
	 * identificador de la tabla (ID)
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * 
	 */
	@Override
	public PgimCriterioSprvsionDTO obtenerCriterioSprvsionById(Long idCriterioSprvsion) {

		PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO = this.criterioSprvsionRepository.obtenerCriterioSprvsionById(idCriterioSprvsion);
		PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionPorId(pgimCriterioSprvsionDTO.getIdSupervision());
		if(pgimSupervisionDTO != null){
			pgimCriterioSprvsionDTO.setDescIdEspecialidad(pgimSupervisionDTO.getDescIdEspecialidad());
			pgimCriterioSprvsionDTO.setDescIdUnidadMinera(pgimSupervisionDTO.getIdUnidadMinera());
		}
		
		return pgimCriterioSprvsionDTO;
	}

	/**
	 * Permite recuperar la lista de hechos verificados por criterio de la matriz de
	 * supervision
	 * 
	 * 
	 * @param idCriterioSprvsion
	 * @return
	 * 
	 */
	@Override
	public List<PgimHechoConstatadoDTO> listarHechosConstatadosPorCriterioMatriz(Long idCriterioSprvsion,
			Long idRolProceso) {

		List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTO = this.hechoConstatadoRepository
				.listarHechosConstatadosPorCriterioMatriz(idCriterioSprvsion, ConstantesUtil.PARAM_COD_NINGUNO_TC,
						idRolProceso);

		return lPgimHechoConstatadoDTO;
	}
	
	
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
	@Override
	public List<PgimHechoConstatadoDTO> listarHechosConstatadosPorCriterioMatrizHistPorFase(Long idCriterioSprvsion,
			Long idFase) {

		List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTO = this.hechoConstatadoRepository
				.listarHechosConstatadosPorCriterioMatrizHistPorFase(idCriterioSprvsion, idFase);

		return lPgimHechoConstatadoDTO;
	}
	
	

	/**
	 * 
	 * 
	 * Permite recuperar la lista de obligaciones normativas por criterio de matriz
	 * de fiscalización
	 * 
	 * @param idMatrizCriterio
	 * @return
	 * 
	 */
	@Override
	public List<PgimObligacionNormaAuxDTO> listarObligacionesNormativasXCriterio(Long idMatrizCriterio, Sort sort) {

		List<PgimObligacionNormaAuxDTO> lPgimOblgcnNrmaCrtrioDTO = this.obligacionNormaAuxRepository
				.listarObligacionesNormativasPorCriterioMatriz(idMatrizCriterio, sort);

		return lPgimOblgcnNrmaCrtrioDTO;
	}

	/**
	 * 
	 * 
	 * Permite recuperar la lista de obligaciones normativas por hecho verificado
	 * 
	 * @param idHechoConstatado
	 * @return
	 * 
	 */
	@Override
	public List<PgimObligacionNormaAuxDTO> listarObligacionesNormativasPorHC(Long idHechoConstatado, Sort sort) {
		List<PgimObligacionNormaAuxDTO> lPgimOblgcnNrmaCrtrioDTO = this.obligacionNormaAuxRepository
				.listarObligacionesNormativasPorHC(idHechoConstatado, sort);
		
		for (Iterator<PgimObligacionNormaAuxDTO> iterator = lPgimOblgcnNrmaCrtrioDTO.iterator(); iterator.hasNext();) {
			PgimObligacionNormaAuxDTO pgimObligacionNormaAuxDTO = (PgimObligacionNormaAuxDTO) iterator.next();
			
			List<PgimOblgcnNrmtvaItemDTO> lPgimOblgcnNrmtvaItemDTO = this.oblgcnNrmtvaItemRepository.
					listarItemsNormasXobligacionNorma(pgimObligacionNormaAuxDTO.getIdOblgcnNrmtvaHchoc());
			
			pgimObligacionNormaAuxDTO.setListPgimOblgcnNrmtvaItemDTO(lPgimOblgcnNrmtvaItemDTO);	
		}


		return lPgimOblgcnNrmaCrtrioDTO;
	}

	/**
	 * Permirte crear un hecho verificado
	 * 
	 * @param pgimHechoConstatadoDTO
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimHechoConstatadoDTO crearHC(@Valid PgimHechoConstatadoDTO pgimHechoConstatadoDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {
		
		//Se quito validación por historia PGIM-11150
		/*// Validación 01: Si ya se registró un HC con TC = NO APLICA, para el criterio
		// de fiscalización de la MS, no permitir registrar más hechos verificados
		List<PgimHechoConstatadoDTO> lPgimHCval01 = this.hechoConstatadoRepository
				.listarHechosConstatadosPorCriterioMatriz(pgimHechoConstatadoDTO.getIdCriterioSprvsion(),
						ConstantesUtil.PARAM_COD_NO_APLICA_TC, ConstantesUtil.PROCESO_ROL_NINGUNO);
		if (lPgimHCval01.size() > 0) {
			// Verificar que el nuevo HC no esté reemplazando al HC con resultado "No
			// APlica"
			if (!lPgimHCval01.get(0).getIdHechoConstatado()
					.equals(pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision()))
				throw new PgimException("error",
						"Si ya registró un hecho verificado con tipo de cumplimiento 'NO APLICA', ya no se puede registrar más hechos verificados para el criterio de fiscalización.");
		}

		// Validación 02: Si ya se registró un HC, no permitir registrar HC con TC = NO
		// APLICA
		PgimValorParametroDTO tipoCumplimiento = this.parametroService
				.obtenerValorParametroPorID(pgimHechoConstatadoDTO.getIdTipoCumplimiento());
		if (tipoCumplimiento.getCoClave().equals(ConstantesUtil.PARAM_COD_NO_APLICA_TC)) {

			if (pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision() == null) {
				// Si no es HC reemplazante, verificar que no existan otros HC vigentes
				List<PgimHechoConstatadoDTO> lPgimHCval02 = this.hechoConstatadoRepository
						.listarHechosConstatadosPorCriterioMatriz(pgimHechoConstatadoDTO.getIdCriterioSprvsion(),
								ConstantesUtil.PARAM_COD_NINGUNO_TC, ConstantesUtil.PROCESO_ROL_NINGUNO);
								
				Se quito validación por historia PGIM-11150			
				if (lPgimHCval02.size() > 0) {
					throw new PgimException("error",
							"No es posible registrar un hecho verificado con tipo de cumplimiento 'NO APLICA', porque ya existen registros de hechos verificados para el criterio de fiscalización.");
				}
			} 
			Se quito validación por historia PGIM-11150
			else {

				// Si es un HC reemplazante, verificar que no haya otros HC adicionalmente al HC
				// que se está reemplazando
				List<PgimHechoConstatadoDTO> lPgimHCval03 = this.hechoConstatadoRepository
						.listarHechosConstatadosPorCriterioMatriz(pgimHechoConstatadoDTO.getIdCriterioSprvsion(),
								ConstantesUtil.PARAM_COD_NINGUNO_TC, ConstantesUtil.PROCESO_ROL_NINGUNO);
				for (PgimHechoConstatadoDTO hcItem3 : lPgimHCval03) {

					if (!hcItem3.getIdHechoConstatado()
							.equals(pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision())) {
						throw new PgimException("error",
								"No es posible modificar el hecho verificado a tipo de cumplimiento 'NO APLICA', porque ya existen otros registros de hechos verificados para el criterio de fiscalización.");
					}
				}

			}

		}

		*/

		PgimHechoConstatado pgimHC = new PgimHechoConstatado();

		pgimHC.setPgimCriterioSprvsion(new PgimCriterioSprvsion());
		pgimHC.getPgimCriterioSprvsion().setIdCriterioSprvsion(pgimHechoConstatadoDTO.getIdCriterioSprvsion());

		pgimHC.setPgimSupervision(new PgimSupervision());
		pgimHC.getPgimSupervision().setIdSupervision(pgimHechoConstatadoDTO.getIdSupervision());

		pgimHC.setPgimInstanciaPaso(new PgimInstanciaPaso());
		pgimHC.getPgimInstanciaPaso().setIdInstanciaPaso(pgimHechoConstatadoDTO.getIdInstanciaPaso());

		pgimHC.setTipoCumplimiento(new PgimValorParametro());
		pgimHC.getTipoCumplimiento().setIdValorParametro(pgimHechoConstatadoDTO.getIdTipoCumplimiento());

		pgimHC.setDeHechoConstatadoT(pgimHechoConstatadoDTO.getDeHechoConstatadoT());
		pgimHC.setDeComplementoObservacion(pgimHechoConstatadoDTO.getDeComplementoObservacion());
		pgimHC.setDeSustentoT(pgimHechoConstatadoDTO.getDeSustentoT());

		if (pgimHechoConstatadoDTO.getDeComentarioOsiT() != null)
			pgimHC.setDeComentarioOsiT(pgimHechoConstatadoDTO.getDeComentarioOsiT());

		if (pgimHechoConstatadoDTO.getIdHechoConstatadoOrigen() != null) {
			pgimHC.setHechoConstatadoOrigen(new PgimHechoConstatado());
			pgimHC.getHechoConstatadoOrigen().setIdHechoConstatado(pgimHechoConstatadoDTO.getIdHechoConstatadoOrigen());
		}

		if (pgimHechoConstatadoDTO.getIdInstanciaPaso() != null) {
			PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository
					.findById(pgimHechoConstatadoDTO.getIdInstanciaPaso()).orElse(null);
			if (pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso() != null)
				pgimHC.setPgimRolProceso(
						pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso());

		}

		/*
		 * if(pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision()==null) { //Si
		 * no es un HC reemplazante, el nuevo HC no está incluído en el acta de
		 * fiscalización (por defecto) pgimHC.setFlIncluidoActaSupervision(ConstantesUtil.
		 * INCLUIDO_ACTA_SUPERVISION_NO); } else{ //Si es un HC reemplazante, el nuevo
		 * HC asume el valor del HC que está reemplzando
		 * pgimHC.setFlIncluidoActaSupervision(pgimHechoConstatadoDTO.
		 * getFlIncluidoActaSupervision()); }
		 */
		pgimHC.setFlIncluidoActaSupervision(ConstantesUtil.INCLUIDO_ACTA_SUPERVISION_NO);
		pgimHC.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_VIGENTE);
		if(pgimHechoConstatadoDTO.getDescIdMatrizCriterio() != null) {
			pgimHC.setFlIncluidoActaSupervision(pgimHechoConstatadoDTO.getFlIncluidoActaSupervision());
		}

		pgimHC.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimHC.setFeCreacion(auditoriaDTO.getFecha());
		pgimHC.setUsCreacion(auditoriaDTO.getUsername());
		pgimHC.setIpCreacion(auditoriaDTO.getTerminal());

		PgimHechoConstatado pgimHCCreado = this.hechoConstatadoRepository.save(pgimHC);

		// registramos los componentes asociados a los hechos verificados --ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO
		if(pgimHechoConstatadoDTO.getDescIdEspecialidad().equals(ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO) && 
			pgimHechoConstatadoDTO.getDescListaCmineroSprvsion() != null && 
			pgimHechoConstatadoDTO.getDescListaCmineroSprvsion().size() != 0){

			for (PgimCmineroSprvsionDTO componenteMineroSupervision : pgimHechoConstatadoDTO.getDescListaCmineroSprvsion()) {
	
				PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();
	
				pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
				pgimComponenteHc.getPgimHechoConstatado().setIdHechoConstatado(pgimHCCreado.getIdHechoConstatado());
	
				pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
				pgimComponenteHc.getPgimCmineroSprvsion().setIdCmineroSprvsion(componenteMineroSupervision.getIdCmineroSprvsion());
	
				if (componenteMineroSupervision.isDescSelected())
					pgimComponenteHc.setFlAplica("1");
				else
					pgimComponenteHc.setFlAplica("0");
	
				pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
				pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
				pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());
	
				this.componenteHcRepository.save(pgimComponenteHc);
			}
		}

		// registramos las obligaciones
		for (PgimObligacionNormaAuxDTO obligacion : pgimHechoConstatadoDTO.getDescListaObligaciones()) {

			PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchoc = new PgimOblgcnNrmtvaHchoc();

			oblgcnNrmtvaHchoc.setPgimHechoConstatado(new PgimHechoConstatado());
			oblgcnNrmtvaHchoc.getPgimHechoConstatado().setIdHechoConstatado(pgimHCCreado.getIdHechoConstatado());

			oblgcnNrmtvaHchoc.setPgimOblgcnNrmaCrtrio(new PgimOblgcnNrmaCrtrio());
			oblgcnNrmtvaHchoc.getPgimOblgcnNrmaCrtrio().setIdOblgcnNrmaCrtrio(obligacion.getIdOblgcnNrmaCrtrio());

			if (obligacion.isDescSeleccionado())
				oblgcnNrmtvaHchoc.setEsAplica("1");
			else
				oblgcnNrmtvaHchoc.setEsAplica("0");

			oblgcnNrmtvaHchoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			oblgcnNrmtvaHchoc.setFeCreacion(auditoriaDTO.getFecha());
			oblgcnNrmtvaHchoc.setUsCreacion(auditoriaDTO.getUsername());
			oblgcnNrmtvaHchoc.setIpCreacion(auditoriaDTO.getTerminal());

			PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchocCreada = this.oblgcnNrmtvaHchocRepository.save(oblgcnNrmtvaHchoc);

			// Crear infracción cuando el HC no cumple, la obligación ha sido seleccionada,
			// y el responsable es un especialista de Osinergmin
			if (pgimHechoConstatadoDTO.getIdTipoCumplimiento().equals(ConstantesUtil.PARAM_ID_NO_CUMPLE_TC)
					&& obligacion.isDescSeleccionado()
					&& (pgimHC.getPgimRolProceso().getIdRolProceso().equals(ConstantesUtil.PROCESO_ROL_ESP_TECNICO)
							|| pgimHC.getPgimRolProceso().getIdRolProceso()
									.equals(ConstantesUtil.PROCESO_ROL_ESP_LEGAL))) {
				this.crearInfraccion(oblgcnNrmtvaHchocCreada.getIdOblgcnNrmtvaHchoc(), pgimHechoConstatadoDTO.getIdInstanciaPaso(), auditoriaDTO);
			}
			
			// registramos ítems normas pertenecientes a la obligación
			List<PgimOblgcnNrmtvaItem> lPgimOblgcnNrmtvaItemActual = this.listarItemsNormasXobligacionNorma(oblgcnNrmtvaHchocCreada.getIdOblgcnNrmtvaHchoc());
			List<PgimOblgcnNrmtvaItemDTO> lPgimOblgcnNrmtvaItemDTOModificado = new ArrayList<>();
			if(obligacion.getListPgimOblgcnNrmtvaItemDTO() != null) {
				lPgimOblgcnNrmtvaItemDTOModificado = obligacion.getListPgimOblgcnNrmtvaItemDTO();
			}
		
			this.crearOModificarItemNormaHijoObligacion(lPgimOblgcnNrmtvaItemDTOModificado, lPgimOblgcnNrmtvaItemActual, oblgcnNrmtvaHchocCreada.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);

		}

		// Actualizar HC reemplazante en el HC recogido en la supervision
		if (pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision() != null) {
			// this.actualizarHCreemplazante(pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision(),pgimHCCreado.getIdHechoConstatado());
			PgimHechoConstatado pgimHechoConstatadoActualiza = this.hechoConstatadoRepository
					.findById(pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision()).orElse(null);
			pgimHechoConstatadoActualiza.setHechoConstatadoRmplznte(new PgimHechoConstatado());
			pgimHechoConstatadoActualiza.getHechoConstatadoRmplznte()
					.setIdHechoConstatado(pgimHCCreado.getIdHechoConstatado());
			this.hechoConstatadoRepository.save(pgimHechoConstatadoActualiza);
		}

		PgimHechoConstatadoDTO pgimHcDTOCreado = this.hechoConstatadoRepository
				.obtenerHechoConstatadoDtoPorId(pgimHCCreado.getIdHechoConstatado());
		pgimHcDTOCreado.setDescIdEspecialidad(pgimHechoConstatadoDTO.getDescIdEspecialidad());

		return pgimHcDTOCreado;

	}

	/**
	 * Permite modificar los datos de un hecho verificado.
	 * 
	 * @param pgimHechoConstatadoDTO
	 * @param pgimHechoConstatadoActual
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimHechoConstatadoDTO modificarHC(@Valid PgimHechoConstatadoDTO pgimHechoConstatadoDTO,
			PgimHechoConstatado pgimHechoConstatadoActual, AuditoriaDTO auditoriaDTO) throws Exception {
	
		PgimHechoConstatado pgimHechoConstatadoModifReempl = null;
		PgimRolProceso pgimRolProcesoAutenticado = new PgimRolProceso();
		boolean flagModificarTC = false;
		PgimPasoProceso pasoProcesoDestino = null;

		if (pgimHechoConstatadoDTO.getIdInstanciaPaso() != null) {
			PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository
					.findById(pgimHechoConstatadoDTO.getIdInstanciaPaso()).orElse(null);
			if (pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso() != null){
				pgimRolProcesoAutenticado = pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino()
						.getPgimRolProceso();
				pasoProcesoDestino = pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino();

			}
			else
				throw new PgimException("error",
						"No se encontró el Rol del usuario actual, para la modificación del hecho verificado.");
		} else
			throw new PgimException("error",
					"No se encontró el ID de la instancia de paso actual, para la modificación del hecho verificado.");

		// No se debe permitir modificar la “Descripción” y el "tipo de resultado" de
		// los hechos verificados
		// debido a que estos ya forman parte del acta de fiscalización que ya estaría
		// firmada,
		// esto es para los hechos verificados cuyo resultado es “No cumple”

		if (!pgimHechoConstatadoActual.getFlIncluidoActaSupervision().equals(ConstantesUtil.INCLUIDO_ACTA_SUPERVISION_SI)) {

			//Se quito validación por historia PGIM-11150
			/* 
			// Esta validación solo aplica si se cambia de tipo de cumplimiento, y si el
			// tipo de cumplimiento es diferente a nulo (por bloqueo del control)

			// Si el tipo de cumplimiento ha sido modificado, entonces ingresar a la
			// validación
			if (!pgimHechoConstatadoActual.getTipoCumplimiento().getIdValorParametro()
					.equals(pgimHechoConstatadoDTO.getIdTipoCumplimiento())) {

				// Validación 01: Si ya se registró otro HC, no permitir modificar el HC a TC =
				// NO APLICA
				PgimValorParametroDTO tipoCumplimiento = this.parametroService
						.obtenerValorParametroPorID(pgimHechoConstatadoDTO.getIdTipoCumplimiento());
				if (tipoCumplimiento.getCoClave().equals(ConstantesUtil.PARAM_COD_NO_APLICA_TC)) {

					List<PgimHechoConstatadoDTO> lPgimHCval01 = this.hechoConstatadoRepository
							.listarHechosConstatadosPorCriterioMatriz(pgimHechoConstatadoDTO.getIdCriterioSprvsion(),
									ConstantesUtil.PARAM_COD_NINGUNO_TC, ConstantesUtil.PROCESO_ROL_NINGUNO);
					
					for (PgimHechoConstatadoDTO hcItem : lPgimHCval01) {

						if (!hcItem.getIdHechoConstatado().equals(pgimHechoConstatadoDTO.getIdHechoConstatado())) {
							throw new PgimException("error",
									"No es posible modificar el hecho verificado a tipo de cumplimiento 'NO APLICA', porque ya existen otros registros de hechos verificados para el criterio de fiscalización.");
						}
					}
				}
			}
			*/

			flagModificarTC = true;

		}

		String usuarioActualizadorHc = (pgimHechoConstatadoActual.getUsActualizacion() != null) ? 
				pgimHechoConstatadoActual.getUsActualizacion() : pgimHechoConstatadoActual.getUsCreacion(); 

		if (pgimRolProcesoAutenticado.getIdRolProceso().equals(pgimHechoConstatadoActual.getPgimRolProceso().getIdRolProceso())
			&& pasoProcesoDestino.getIdPasoProceso().equals(pgimHechoConstatadoActual.getPgimInstanciaPaso().getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso())
			&& auditoriaDTO.getUsername().equals(usuarioActualizadorHc)
			) {

			/////////////////////////////////////////////////////////////////////////////////
			// Si el IdRolProceso y Usuario es el mismo, se actualiza el mismo regitro de HC
			/////////////////////////////////////////////////////////////////////////////////

			if (flagModificarTC) {
				pgimHechoConstatadoActual.setTipoCumplimiento(new PgimValorParametro());
				pgimHechoConstatadoActual.getTipoCumplimiento()
						.setIdValorParametro(pgimHechoConstatadoDTO.getIdTipoCumplimiento());

				pgimHechoConstatadoActual.setDeHechoConstatadoT(pgimHechoConstatadoDTO.getDeHechoConstatadoT());
			}

			// La instancia de paso solo se registra en la creación del HC
			// pgimHechoConstatadoActual.setPgimInstanciaPaso(new PgimInstanciaPaso());
			// pgimHechoConstatadoActual.getPgimInstanciaPaso().setIdInstanciaPaso(pgimHechoConstatadoDTO.getIdInstanciaPaso());

			pgimHechoConstatadoActual.setDeComplementoObservacion(pgimHechoConstatadoDTO.getDeComplementoObservacion());
			pgimHechoConstatadoActual.setDeSustentoT(pgimHechoConstatadoDTO.getDeSustentoT());

			if (pgimHechoConstatadoDTO.getIdHechoConstatadoOrigen() != null) {
				pgimHechoConstatadoActual.setHechoConstatadoOrigen(new PgimHechoConstatado());
				pgimHechoConstatadoActual.getHechoConstatadoOrigen()
						.setIdHechoConstatado(pgimHechoConstatadoDTO.getIdHechoConstatadoOrigen());
			}

			if (pgimHechoConstatadoDTO.getIdInstanciaPaso() != null) {
				PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository
						.findById(pgimHechoConstatadoDTO.getIdInstanciaPaso()).orElse(null);
				if(pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso() != null){
					pgimHechoConstatadoActual.setPgimInstanciaPaso(pgimInstanciaPaso);
				}
				if (pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso() != null)
					pgimHechoConstatadoActual.setPgimRolProceso(
							pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso());

			}

			if (pgimHechoConstatadoDTO.getDeComentarioOsiT() != null)
				pgimHechoConstatadoActual.setDeComentarioOsiT(pgimHechoConstatadoDTO.getDeComentarioOsiT());

			pgimHechoConstatadoActual.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_VIGENTE);

			pgimHechoConstatadoActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimHechoConstatadoActual.setFeActualizacion(auditoriaDTO.getFecha());
			pgimHechoConstatadoActual.setUsActualizacion(auditoriaDTO.getUsername());
			pgimHechoConstatadoActual.setIpActualizacion(auditoriaDTO.getTerminal());

			pgimHechoConstatadoModifReempl = this.hechoConstatadoRepository.save(pgimHechoConstatadoActual);

			// Actualizamos las obligaciones
			for (PgimObligacionNormaAuxDTO obligacion : pgimHechoConstatadoDTO.getDescListaObligaciones()) {

				PgimOblgcnNrmtvaHchoc pgimOblgcnNrmtvaHchoc = this.oblgcnNrmtvaHchocRepository
						.findById(obligacion.getIdOblgcnNrmtvaHchoc()).orElse(null);

				boolean bValidacion = false;
				if (pgimOblgcnNrmtvaHchoc.getEsAplica().equals("1"))
					bValidacion = true;
				else
					bValidacion = false;

				if (obligacion.isDescSeleccionado() != bValidacion) {
					// Actualizar obligación

					if (obligacion.isDescSeleccionado())
						pgimOblgcnNrmtvaHchoc.setEsAplica("1");
					else
						pgimOblgcnNrmtvaHchoc.setEsAplica("0");

					pgimOblgcnNrmtvaHchoc.setFeActualizacion(auditoriaDTO.getFecha());
					pgimOblgcnNrmtvaHchoc.setUsActualizacion(auditoriaDTO.getUsername());
					pgimOblgcnNrmtvaHchoc.setIpActualizacion(auditoriaDTO.getTerminal());

					this.oblgcnNrmtvaHchocRepository.save(pgimOblgcnNrmtvaHchoc);

				}

				// INFRACCIONES:

				// Si el HC tiene valor diferente a NO Cumple, eliminar todas las infracciones
				if (!pgimHechoConstatadoActual.getTipoCumplimiento().getIdValorParametro()
						.equals(ConstantesUtil.PARAM_ID_NO_CUMPLE_TC)) {
					this.eliminarInfraccion(pgimOblgcnNrmtvaHchoc.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);
				} else if (pgimHechoConstatadoActual.getTipoCumplimiento().getIdValorParametro()
						.equals(ConstantesUtil.PARAM_ID_NO_CUMPLE_TC)
						&& (pgimHechoConstatadoActual.getPgimRolProceso().getIdRolProceso()
								.equals(ConstantesUtil.PROCESO_ROL_ESP_TECNICO)
								|| pgimHechoConstatadoActual.getPgimRolProceso().getIdRolProceso()
										.equals(ConstantesUtil.PROCESO_ROL_ESP_LEGAL))) {
					// Si el HC tiene valor NO Cumple, crear para las obligaciones seleccionadas, y
					// eliminar para las no seleccionadas
					if (obligacion.isDescSeleccionado())
						this.crearInfraccion(pgimOblgcnNrmtvaHchoc.getIdOblgcnNrmtvaHchoc(), pgimHechoConstatadoDTO.getIdInstanciaPaso(), auditoriaDTO);
					else
						this.eliminarInfraccion(pgimOblgcnNrmtvaHchoc.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);

				}
				
				// registramos ítems normas pertenecientes a la obligación
				List<PgimOblgcnNrmtvaItem> lPgimOblgcnNrmtvaItemActual = this.listarItemsNormasXobligacionNorma(pgimOblgcnNrmtvaHchoc.getIdOblgcnNrmtvaHchoc());
				List<PgimOblgcnNrmtvaItemDTO> lPgimOblgcnNrmtvaItemDTOModificado = new ArrayList<>();
				if(obligacion.getListPgimOblgcnNrmtvaItemDTO() != null) {
					lPgimOblgcnNrmtvaItemDTOModificado = obligacion.getListPgimOblgcnNrmtvaItemDTO();
				}
			
				this.crearOModificarItemNormaHijoObligacion(lPgimOblgcnNrmtvaItemDTOModificado, lPgimOblgcnNrmtvaItemActual, pgimOblgcnNrmtvaHchoc.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);
				
			}

			// registramos los componentes asociados a los hechos verificados
			// --ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO
			if (pgimHechoConstatadoDTO.getDescIdEspecialidad()
					.equals(ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO) &&
					pgimHechoConstatadoDTO.getDescListaCmineroSprvsion() != null &&
					pgimHechoConstatadoDTO.getDescListaCmineroSprvsion().size() != 0) {

				for (PgimCmineroSprvsionDTO componenteMineroSupervision : pgimHechoConstatadoDTO
						.getDescListaCmineroSprvsion()) {

					PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();

					pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
					pgimComponenteHc.getPgimHechoConstatado()
							.setIdHechoConstatado(pgimHechoConstatadoModifReempl.getIdHechoConstatado());

					pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
					pgimComponenteHc.getPgimCmineroSprvsion()
							.setIdCmineroSprvsion(componenteMineroSupervision.getIdCmineroSprvsion());

					if (componenteMineroSupervision.isDescSelected())
						pgimComponenteHc.setFlAplica("1");
					else
						pgimComponenteHc.setFlAplica("0");

					pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
					pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
					pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());

					this.componenteHcRepository.save(pgimComponenteHc);
				}
			}

			// Actualizamos los componentes asociados a los hechos verificados
			// --ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO
			if (pgimHechoConstatadoDTO.getDescIdEspecialidad()
					.equals(ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO) &&
					pgimHechoConstatadoDTO.getDescListaComponenteHcDTO() != null &&
					pgimHechoConstatadoDTO.getDescListaComponenteHcDTO().size() != 0) {

				for (PgimComponenteHcDTO componenteHechoVerificado : pgimHechoConstatadoDTO
						.getDescListaComponenteHcDTO()) {

					PgimComponenteHc pgimComponenteHc = this.componenteHcService
							.getByIdComponenteHc(componenteHechoVerificado.getIdComponenteHc());

					boolean bValidacion = false;
					if (pgimComponenteHc.getFlAplica().equals("1"))
						bValidacion = true;
					else
						bValidacion = false;

					if (componenteHechoVerificado.isDescSelected() != bValidacion) {

						if (componenteHechoVerificado.isDescSelected())
							pgimComponenteHc.setFlAplica("1");
						else
							pgimComponenteHc.setFlAplica("0");

						pgimComponenteHc.setFeActualizacion(auditoriaDTO.getFecha());
						pgimComponenteHc.setUsActualizacion(auditoriaDTO.getUsername());
						pgimComponenteHc.setIpActualizacion(auditoriaDTO.getTerminal());

						this.componenteHcRepository.save(pgimComponenteHc);
					}
				}
			}
		} else {

			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// Si el IdRolProceso o Usuario es diferente, se genera un nuevo registro de HC, y se marca como histórico el registro anterior
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			PgimHechoConstatado pgimHC = new PgimHechoConstatado();

			pgimHC.setPgimCriterioSprvsion(new PgimCriterioSprvsion());
			pgimHC.getPgimCriterioSprvsion()
					.setIdCriterioSprvsion(pgimHechoConstatadoActual.getPgimCriterioSprvsion().getIdCriterioSprvsion());

			pgimHC.setPgimSupervision(new PgimSupervision());
			pgimHC.getPgimSupervision()
					.setIdSupervision(pgimHechoConstatadoActual.getPgimSupervision().getIdSupervision());

			pgimHC.setPgimInstanciaPaso(new PgimInstanciaPaso());
			pgimHC.getPgimInstanciaPaso()
					.setIdInstanciaPaso(pgimHechoConstatadoActual.getPgimInstanciaPaso().getIdInstanciaPaso());

			if (flagModificarTC) {
				pgimHC.setTipoCumplimiento(new PgimValorParametro());
				pgimHC.getTipoCumplimiento().setIdValorParametro(pgimHechoConstatadoDTO.getIdTipoCumplimiento());

				pgimHC.setDeHechoConstatadoT(pgimHechoConstatadoDTO.getDeHechoConstatadoT());
			} else {
				pgimHC.setTipoCumplimiento(new PgimValorParametro());
				pgimHC.getTipoCumplimiento()
						.setIdValorParametro(pgimHechoConstatadoActual.getTipoCumplimiento().getIdValorParametro());

				pgimHC.setDeHechoConstatadoT(pgimHechoConstatadoActual.getDeHechoConstatadoT());
			}

			pgimHC.setDeComplementoObservacion(pgimHechoConstatadoDTO.getDeComplementoObservacion());
			pgimHC.setDeSustentoT(pgimHechoConstatadoDTO.getDeSustentoT());
			
			if (pgimHechoConstatadoDTO.getDeComentarioOsiT() != null)
				pgimHC.setDeComentarioOsiT(pgimHechoConstatadoDTO.getDeComentarioOsiT());

			// pgimHC.setPgimRolProceso(pgimRolProcesoAutenticado);
			if (pgimHechoConstatadoDTO.getIdInstanciaPaso() != null) {
				PgimInstanciaPaso pgimInstanciaPasoNuevo = this.instanciaPasoRepository
						.findById(pgimHechoConstatadoDTO.getIdInstanciaPaso()).orElse(null);
				if(pgimInstanciaPasoNuevo.getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso() != null){
					pgimHC.setPgimInstanciaPaso(pgimInstanciaPasoNuevo);
				}
				if (pgimInstanciaPasoNuevo.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso() != null)
					pgimHC.setPgimRolProceso(
							pgimInstanciaPasoNuevo.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso());
			}

			// pgimHC.setFlIncluidoActaSupervision(ConstantesUtil.INCLUIDO_ACTA_SUPERVISION_NO);
			pgimHC.setFlIncluidoActaSupervision(pgimHechoConstatadoActual.getFlIncluidoActaSupervision());

			// seteamos el HC Origen, el registro vigente anterior
			pgimHC.setHechoConstatadoOrigen(pgimHechoConstatadoActual);

			pgimHC.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_VIGENTE);

			pgimHC.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimHC.setFeCreacion(auditoriaDTO.getFecha());
			pgimHC.setUsCreacion(auditoriaDTO.getUsername());
			pgimHC.setIpCreacion(auditoriaDTO.getTerminal());

			// Crear el nuevo HC vigente
			pgimHechoConstatadoModifReempl = this.hechoConstatadoRepository.save(pgimHC);

			// registramos los componentes asociados a los hechos verificados
			// --ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO
			if (pgimHechoConstatadoDTO.getDescIdEspecialidad()
					.equals(ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO) &&
					pgimHechoConstatadoDTO.getDescListaCmineroSprvsion() != null &&
					pgimHechoConstatadoDTO.getDescListaCmineroSprvsion().size() != 0) {

				for (PgimCmineroSprvsionDTO componenteMineroSupervision : pgimHechoConstatadoDTO
						.getDescListaCmineroSprvsion()) {

					PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();

					pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
					pgimComponenteHc.getPgimHechoConstatado()
							.setIdHechoConstatado(pgimHechoConstatadoModifReempl.getIdHechoConstatado());

					pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
					pgimComponenteHc.getPgimCmineroSprvsion()
							.setIdCmineroSprvsion(componenteMineroSupervision.getIdCmineroSprvsion());

					if (componenteMineroSupervision.isDescSelected())
						pgimComponenteHc.setFlAplica("1");
					else
						pgimComponenteHc.setFlAplica("0");

					pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
					pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
					pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());

					this.componenteHcRepository.save(pgimComponenteHc);
				}
			}

			// Actualizamos los componentes asociados a los hechos verificados
			// --ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO
			if (pgimHechoConstatadoDTO.getDescIdEspecialidad().equals(ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO) &&
					pgimHechoConstatadoDTO.getDescListaComponenteHcDTO() != null &&
					pgimHechoConstatadoDTO.getDescListaComponenteHcDTO().size() != 0) {

				for (PgimComponenteHcDTO componenteHechoVerificado : pgimHechoConstatadoDTO
						.getDescListaComponenteHcDTO()) {

					PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();

					pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
					pgimComponenteHc.getPgimHechoConstatado().setIdHechoConstatado(pgimHC.getIdHechoConstatado());

					pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
					pgimComponenteHc.getPgimCmineroSprvsion().setIdCmineroSprvsion(componenteHechoVerificado.getIdCmineroSprvsion());

					if (componenteHechoVerificado.isDescSelected())
						pgimComponenteHc.setFlAplica("1");
					else
						pgimComponenteHc.setFlAplica("0");
					pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);

					pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
					pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
					pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());

					this.componenteHcRepository.save(pgimComponenteHc);
				}
			}
			
			// Registrar las obligaciones
			for (PgimObligacionNormaAuxDTO obligacion : pgimHechoConstatadoDTO.getDescListaObligaciones()) {

				PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchoc = new PgimOblgcnNrmtvaHchoc();

				oblgcnNrmtvaHchoc.setPgimHechoConstatado(new PgimHechoConstatado());
				oblgcnNrmtvaHchoc.getPgimHechoConstatado().setIdHechoConstatado(pgimHC.getIdHechoConstatado());

				oblgcnNrmtvaHchoc.setPgimOblgcnNrmaCrtrio(new PgimOblgcnNrmaCrtrio());
				oblgcnNrmtvaHchoc.getPgimOblgcnNrmaCrtrio().setIdOblgcnNrmaCrtrio(obligacion.getIdOblgcnNrmaCrtrio());

				if (obligacion.isDescSeleccionado())
					oblgcnNrmtvaHchoc.setEsAplica("1");
				else
					oblgcnNrmtvaHchoc.setEsAplica("0");

				oblgcnNrmtvaHchoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				oblgcnNrmtvaHchoc.setFeCreacion(auditoriaDTO.getFecha());
				oblgcnNrmtvaHchoc.setUsCreacion(auditoriaDTO.getUsername());
				oblgcnNrmtvaHchoc.setIpCreacion(auditoriaDTO.getTerminal());

				PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchocCreada = this.oblgcnNrmtvaHchocRepository
						.save(oblgcnNrmtvaHchoc);

				//Redireccionar la infracción actual, de existir, a la nueva obligación normativa creada
				List<PgimInfraccionDTO> lPgimInfraccionDTOActual = this.infraccionRepository.listarInfraccionXobligacionNorma(obligacion.getIdOblgcnNrmtvaHchoc());
				
				for (PgimInfraccionDTO pgimInfraccionDTO : lPgimInfraccionDTOActual) { 
					PgimInfraccion pgimInfraccion = this.infraccionRepository.findById(pgimInfraccionDTO.getIdInfraccion()).orElse(null);						
					pgimInfraccion.setPgimOblgcnNrmtvaHchoc(oblgcnNrmtvaHchocCreada); // evitamos actualizar datos de auditoría para mantener integridad de histórico de la infracción
					this.infraccionRepository.save(pgimInfraccion);
				}
				
				// Crear infracción cuando el HC no cumple, la obligación ha sido seleccionada,
				// y el responsable es un especialista de Osinergmin
				if (pgimHechoConstatadoDTO.getIdTipoCumplimiento().equals(ConstantesUtil.PARAM_ID_NO_CUMPLE_TC)
						&& obligacion.isDescSeleccionado()
						&& (pgimHC.getPgimRolProceso().getIdRolProceso().equals(ConstantesUtil.PROCESO_ROL_ESP_TECNICO)
								|| pgimHC.getPgimRolProceso().getIdRolProceso()
										.equals(ConstantesUtil.PROCESO_ROL_ESP_LEGAL))) {
					
					this.crearInfraccion(oblgcnNrmtvaHchocCreada.getIdOblgcnNrmtvaHchoc(), pgimHechoConstatadoDTO.getIdInstanciaPaso(), auditoriaDTO);
				
				}else {
					// Eliminar infracción 
					this.eliminarInfraccion(oblgcnNrmtvaHchocCreada.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);
				}
				
				// registramos ítems normas pertenecientes a la obligación
				List<PgimOblgcnNrmtvaItem> lPgimOblgcnNrmtvaItemActual = this.listarItemsNormasXobligacionNorma(oblgcnNrmtvaHchocCreada.getIdOblgcnNrmtvaHchoc());
				List<PgimOblgcnNrmtvaItemDTO> lPgimOblgcnNrmtvaItemDTOModificado = new ArrayList<>();
				if(obligacion.getListPgimOblgcnNrmtvaItemDTO() != null) {
					lPgimOblgcnNrmtvaItemDTOModificado = obligacion.getListPgimOblgcnNrmtvaItemDTO();
				}
			
				this.crearOModificarItemNormaHijoObligacion(lPgimOblgcnNrmtvaItemDTOModificado, lPgimOblgcnNrmtvaItemActual, oblgcnNrmtvaHchocCreada.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);

			}

			// Actualizar el HC anterior como histórico
			pgimHechoConstatadoActual.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_HISTORICO);
			this.hechoConstatadoRepository.save(pgimHechoConstatadoActual);

			// Actualizar HC reemplazante en el HC recogido en la supervision
			if (pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision() != null) {
				PgimHechoConstatado pgimHechoConstatadoSupervision = this.hechoConstatadoRepository
						.findById(pgimHechoConstatadoDTO.getDescIdHechoConstatadoSupervision()).orElse(null);
				pgimHechoConstatadoSupervision.setHechoConstatadoRmplznte(pgimHC);
				this.hechoConstatadoRepository.save(pgimHechoConstatadoSupervision);
			}
			else {
				
				List<PgimHechoConstatadoDTO> lhcr = this.hechoConstatadoRepository
						.listarHechosConstatadosPorHechoConstatadoRmplznte(pgimHechoConstatadoActual.getIdHechoConstatado());
				
				for (PgimHechoConstatadoDTO hcDTOaReemplazar : lhcr) {
					PgimHechoConstatado pgimHechoConstatadoSupervision = this.hechoConstatadoRepository
							.findById(hcDTOaReemplazar.getIdHechoConstatado()).orElse(null);
					pgimHechoConstatadoSupervision.setHechoConstatadoRmplznte(pgimHechoConstatadoModifReempl);
					this.hechoConstatadoRepository.save(pgimHechoConstatadoSupervision);
				}
			}

		}

		PgimHechoConstatadoDTO pgimHcDTOModificado = this.hechoConstatadoRepository
				.obtenerHechoConstatadoDtoPorId(pgimHechoConstatadoModifReempl.getIdHechoConstatado());
		pgimHcDTOModificado.setDescIdEspecialidad(pgimHechoConstatadoDTO.getDescIdEspecialidad());
		return pgimHcDTOModificado;
	}

	/**
	 * Permite copiar un hecho verificado determinado y poner el anterior como histórico
	 * 
	 * @param idHechoConstatado		Id del hecho constatado que será copiado
	 * @param idInstanciaPasoActual	Id de la instancia de paso en el momento de la copia
	 * @param auditoriaDTO			Objeto que porta los datos de auditoría
	 * @return
	 */
	public PgimHechoConstatado copiarHC(Long idHechoConstatado, Long idInstanciaPasoActual, AuditoriaDTO auditoriaDTO) {
		
		PgimHechoConstatado pgimHechoConstatadoActual = this.hechoConstatadoRepository.findById(idHechoConstatado).orElse(null);
		
		if (pgimHechoConstatadoActual == null) {
			throw new PgimException(TipoResultado.ERROR, "No se ha encontrado el hecho verificado con Id " + idHechoConstatado + " para realizar su copia");
		}
		
		if (idInstanciaPasoActual == null) {
			throw new PgimException(TipoResultado.ERROR, "Se requiere el Id de la instancia de paso actual para realizar la copia del hecho verificado");
		}
		
		PgimHechoConstatado pgimHC = new PgimHechoConstatado();

		pgimHC.setPgimCriterioSprvsion(new PgimCriterioSprvsion());
		pgimHC.getPgimCriterioSprvsion()
				.setIdCriterioSprvsion(pgimHechoConstatadoActual.getPgimCriterioSprvsion().getIdCriterioSprvsion());

		pgimHC.setPgimSupervision(new PgimSupervision());
		pgimHC.getPgimSupervision()
				.setIdSupervision(pgimHechoConstatadoActual.getPgimSupervision().getIdSupervision());

		pgimHC.setPgimInstanciaPaso(new PgimInstanciaPaso());
		pgimHC.getPgimInstanciaPaso()
				.setIdInstanciaPaso(idInstanciaPasoActual); 

		pgimHC.setTipoCumplimiento(new PgimValorParametro());
		pgimHC.getTipoCumplimiento()
				.setIdValorParametro(pgimHechoConstatadoActual.getTipoCumplimiento().getIdValorParametro());
		
		PgimInstanciaPaso pgimInstanciaPasoActual = this.instanciaPasoRepository.findById(idInstanciaPasoActual).orElse(null);
		if (pgimInstanciaPasoActual != null) {
			pgimHC.setPgimRolProceso(new PgimRolProceso());
			pgimHC.getPgimRolProceso()
				.setIdRolProceso(pgimInstanciaPasoActual.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso().getIdRolProceso());
		}		
		
		if(pgimHechoConstatadoActual.getHechoConstatadoRmplznte() != null) {
			pgimHC.setHechoConstatadoRmplznte(new PgimHechoConstatado());
			pgimHC.getHechoConstatadoRmplznte()
				.setIdHechoConstatado(pgimHechoConstatadoActual.getHechoConstatadoRmplznte().getIdHechoConstatado());
		}

		// Seteamos como HC Origen, el registro vigente anterior
		pgimHC.setHechoConstatadoOrigen(pgimHechoConstatadoActual);

		pgimHC.setDeHechoConstatadoT(pgimHechoConstatadoActual.getDeHechoConstatadoT());
		pgimHC.setDeSustentoT(pgimHechoConstatadoActual.getDeSustentoT());
		pgimHC.setDeComentarioOsiT(pgimHechoConstatadoActual.getDeComentarioOsiT());
		pgimHC.setDeComplementoObservacion(pgimHechoConstatadoActual.getDeComplementoObservacion());
		pgimHC.setFlIncluidoActaSupervision(pgimHechoConstatadoActual.getFlIncluidoActaSupervision());
		pgimHC.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_VIGENTE);
		
		pgimHC.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimHC.setFeCreacion(auditoriaDTO.getFecha());
		pgimHC.setUsCreacion(auditoriaDTO.getUsername());
		pgimHC.setIpCreacion(auditoriaDTO.getTerminal());

		// Crear el nuevo HC vigente
		PgimHechoConstatado pgimHechoConstatadoNuevo = this.hechoConstatadoRepository.save(pgimHC);

		// Copiar las obligaciones fiscalizadas
		List<PgimOblgcnNrmtvaHchocDTO> lPgimOblgcnNrmtvaHchocDTO = this.oblgcnNrmtvaHchocRepository.obtenerOblgNormativaPorHechoDTO(pgimHechoConstatadoActual.getIdHechoConstatado());
		
		for (PgimOblgcnNrmtvaHchocDTO obligacion : lPgimOblgcnNrmtvaHchocDTO) {

			PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchoc = new PgimOblgcnNrmtvaHchoc();

			oblgcnNrmtvaHchoc.setPgimHechoConstatado(new PgimHechoConstatado());
			oblgcnNrmtvaHchoc.getPgimHechoConstatado().setIdHechoConstatado(pgimHechoConstatadoNuevo.getIdHechoConstatado());

			oblgcnNrmtvaHchoc.setPgimOblgcnNrmaCrtrio(new PgimOblgcnNrmaCrtrio());
			oblgcnNrmtvaHchoc.getPgimOblgcnNrmaCrtrio().setIdOblgcnNrmaCrtrio(obligacion.getIdOblgcnNrmaCrtrio());

			oblgcnNrmtvaHchoc.setEsAplica(obligacion.getEsAplica());

			oblgcnNrmtvaHchoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			oblgcnNrmtvaHchoc.setFeCreacion(auditoriaDTO.getFecha());
			oblgcnNrmtvaHchoc.setUsCreacion(auditoriaDTO.getUsername());
			oblgcnNrmtvaHchoc.setIpCreacion(auditoriaDTO.getTerminal());

			PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchocCreada = this.oblgcnNrmtvaHchocRepository.save(oblgcnNrmtvaHchoc);

			// Redireccionar la infracción actual, de existir, a la nueva obligación normativa creada
			List<PgimInfraccionDTO> lPgimInfraccionDTOActual = this.infraccionRepository.listarInfraccionXobligacionNorma(obligacion.getIdOblgcnNrmtvaHchoc());
			
			for (PgimInfraccionDTO pgimInfraccionDTO : lPgimInfraccionDTOActual) { 
				PgimInfraccion pgimInfraccion = this.infraccionRepository.findById(pgimInfraccionDTO.getIdInfraccion()).orElse(null);						
				pgimInfraccion.setPgimOblgcnNrmtvaHchoc(oblgcnNrmtvaHchocCreada); // evitamos actualizar datos de auditoría para mantener integridad de histórico de la infracción
				this.infraccionRepository.save(pgimInfraccion);
			}			
			
			// Copiar ítems normas pertenecientes a la obligación
			List<PgimOblgcnNrmtvaItem> lPgimOblgcnNrmtvaItemActual = this.listarItemsNormasXobligacionNorma(obligacion.getIdOblgcnNrmtvaHchoc());
			
			for (PgimOblgcnNrmtvaItem pgimOblgcnNrmtvaItem : lPgimOblgcnNrmtvaItemActual) {				
			
				PgimOblgcnNrmtvaItem pgimOblgcnNrmtvaItemNew = new PgimOblgcnNrmtvaItem();    
	
				pgimOblgcnNrmtvaItemNew.setPgimOblgcnNrmtvaHchoc(new PgimOblgcnNrmtvaHchoc());
				pgimOblgcnNrmtvaItemNew.getPgimOblgcnNrmtvaHchoc().setIdOblgcnNrmtvaHchoc(oblgcnNrmtvaHchocCreada.getIdOblgcnNrmtvaHchoc());
				
				pgimOblgcnNrmtvaItemNew.setPgimNormaItem(new PgimNormaItem());
				pgimOblgcnNrmtvaItemNew.getPgimNormaItem().setIdNormaItem(pgimOblgcnNrmtvaItem.getPgimNormaItem().getIdNormaItem());	
						
				pgimOblgcnNrmtvaItemNew.setOblgcnNrmtvaItemPadre(new PgimOblgcnNrmtvaItem());
				pgimOblgcnNrmtvaItemNew.getOblgcnNrmtvaItemPadre().setIdOblgcnNrmtvaItem(pgimOblgcnNrmtvaItem.getIdOblgcnNrmtvaItem());
				
				pgimOblgcnNrmtvaItemNew.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimOblgcnNrmtvaItemNew.setFeCreacion(auditoriaDTO.getFecha());
				pgimOblgcnNrmtvaItemNew.setUsCreacion(auditoriaDTO.getUsername());
				pgimOblgcnNrmtvaItemNew.setIpCreacion(auditoriaDTO.getTerminal());
						
				this.oblgcnNrmtvaItemRepository.save(pgimOblgcnNrmtvaItemNew);
			}
		}

		// Actualizar el HC anterior como histórico
		pgimHechoConstatadoActual.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_HISTORICO);		
		this.hechoConstatadoRepository.save(pgimHechoConstatadoActual); // evitamos actualizar datos de auditoría

		// Actualizar HC reemplazante en el HC recogido en la supervision
		List<PgimHechoConstatadoDTO> lhcr = this.hechoConstatadoRepository
				.listarHechosConstatadosPorHechoConstatadoRmplznte(pgimHechoConstatadoActual.getIdHechoConstatado());
		
		for (PgimHechoConstatadoDTO hcDTOaReemplazar : lhcr) {
			PgimHechoConstatado pgimHechoConstatadoSupervision = this.hechoConstatadoRepository
					.findById(hcDTOaReemplazar.getIdHechoConstatado()).orElse(null);
			pgimHechoConstatadoSupervision.setHechoConstatadoRmplznte(pgimHechoConstatadoNuevo);
			this.hechoConstatadoRepository.save(pgimHechoConstatadoSupervision); // evitamos actualizar datos de auditoría
		}

		return pgimHechoConstatadoNuevo;
	}
	
	
	@Transactional(readOnly = false)
	private void crearOModificarItemNormaHijoObligacion(List<PgimOblgcnNrmtvaItemDTO> lPgimOblgcnNrmtvaItemDTONueva, 
			List<PgimOblgcnNrmtvaItem> lPgimOblgcnNrmtvaItemActual, Long idOblgcnNrmtvaHchoc, AuditoriaDTO auditoriaDTO) {
		// 1° Actualizar estado de los PgimOblgcnNrmtvaItem ya existentes, activamos o desactivamos según corresponda

		for (PgimOblgcnNrmtvaItem pgimOblgcnNrmtvaItem : lPgimOblgcnNrmtvaItemActual) {
			pgimOblgcnNrmtvaItem.setEsRegistro(ConstantesUtil.IND_INACTIVO);

			for (PgimOblgcnNrmtvaItemDTO pgimOblgcnNrmtvaItemDTONuevo : lPgimOblgcnNrmtvaItemDTONueva) {  
				
				if(pgimOblgcnNrmtvaItemDTONuevo.getIdNormaItem().equals(pgimOblgcnNrmtvaItem.getPgimNormaItem().getIdNormaItem())) {
					pgimOblgcnNrmtvaItem.setEsRegistro(ConstantesUtil.IND_ACTIVO);	
					lPgimOblgcnNrmtvaItemDTONueva.remove(pgimOblgcnNrmtvaItemDTONuevo); // lo retira de la lista para que ya no se vuelva a registrar en el paso 2°	    		
					
					break;
				}
			}
					
			pgimOblgcnNrmtvaItem.setFeActualizacion(auditoriaDTO.getFecha());
			pgimOblgcnNrmtvaItem.setUsActualizacion(auditoriaDTO.getUsername());
			pgimOblgcnNrmtvaItem.setIpActualizacion(auditoriaDTO.getTerminal());
				
			this.oblgcnNrmtvaItemRepository.save(pgimOblgcnNrmtvaItem);
		}

		// 2° Registrar los PgimOblgcnNrmtvaItem nuevos 

		for (PgimOblgcnNrmtvaItemDTO pgimOblgcnNrmtvaItemDTONuevo : lPgimOblgcnNrmtvaItemDTONueva) {

			PgimOblgcnNrmtvaItem pgimOblgcnNrmtvaItemNew = new PgimOblgcnNrmtvaItem();    

			pgimOblgcnNrmtvaItemNew.setPgimNormaItem(new PgimNormaItem());
			pgimOblgcnNrmtvaItemNew.getPgimNormaItem().setIdNormaItem(pgimOblgcnNrmtvaItemDTONuevo.getIdNormaItem());

			pgimOblgcnNrmtvaItemNew.setPgimOblgcnNrmtvaHchoc(new PgimOblgcnNrmtvaHchoc());
			pgimOblgcnNrmtvaItemNew.getPgimOblgcnNrmtvaHchoc().setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
					
			if(pgimOblgcnNrmtvaItemDTONuevo.getIdOblgcnNrmtvaItem() != null ) {
				pgimOblgcnNrmtvaItemNew.setOblgcnNrmtvaItemPadre(new PgimOblgcnNrmtvaItem());
				pgimOblgcnNrmtvaItemNew.getOblgcnNrmtvaItemPadre().setIdOblgcnNrmtvaItem( pgimOblgcnNrmtvaItemDTONuevo.getIdOblgcnNrmtvaItem());
			}
			
			pgimOblgcnNrmtvaItemNew.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimOblgcnNrmtvaItemNew.setFeCreacion(auditoriaDTO.getFecha());
			pgimOblgcnNrmtvaItemNew.setUsCreacion(auditoriaDTO.getUsername());
			pgimOblgcnNrmtvaItemNew.setIpCreacion(auditoriaDTO.getTerminal());
					
			this.oblgcnNrmtvaItemRepository.save(pgimOblgcnNrmtvaItemNew);

		}
	}

	public List<PgimOblgcnNrmtvaItem> listarItemsNormasXobligacionNorma(Long idOblgcnNrmtvaHchoc) {
		return this.oblgcnNrmtvaItemRepository.findAllById(this.oblgcnNrmtvaItemRepository.listarIdItemsNormasXobligacionNorma(idOblgcnNrmtvaHchoc));
	}
		
	@Transactional(readOnly = false)
	@Override
	public PgimHechoConstatadoDTO reasignarCriterioHV(@Valid PgimHechoConstatadoDTO pgimHechoConstatadoDTO, AuditoriaDTO auditoriaDTO) throws Exception {
		
		PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO = this.criterioSprvsionRepository.obtenerCriterioSprvsionByIdMatrizCriterioYIdSupervision(
				pgimHechoConstatadoDTO.getDescIdMatrizCriterio(), pgimHechoConstatadoDTO.getIdSupervision());
		PgimHechoConstatado pgimHechoConstatadoActual = this.hechoConstatadoRepository.findById(pgimHechoConstatadoDTO.getIdHechoConstatado()).orElse(null);
		PgimHechoConstatadoDTO pgimHechoConstatadoDTOReasignado = new PgimHechoConstatadoDTO();
		PgimRolProceso pgimRolProcesoAutenticado = new PgimRolProceso();
		PgimPasoProceso pasoProcesoDestino = null;
		Long idCriterioSprvsion = null;
		
		if (pgimHechoConstatadoDTO.getIdInstanciaPaso() != null) {
			PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository
					.findById(pgimHechoConstatadoDTO.getIdInstanciaPaso()).orElse(null);
			if (pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getPgimRolProceso() != null){
				pgimRolProcesoAutenticado = pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino()
						.getPgimRolProceso();
				pasoProcesoDestino = pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino();

			}
			else
				throw new PgimException(TipoResultado.ERROR,
						"No se encontró el Rol del usuario actual, para la modificación del hecho verificado.");
		} else
			throw new PgimException(TipoResultado.ERROR,
					"No se encontró el ID de la instancia de paso actual, para la modificación del hecho verificado.");
		
		String usuarioActualizadorHc = (pgimHechoConstatadoActual.getUsActualizacion() != null) ? 
				pgimHechoConstatadoActual.getUsActualizacion() : pgimHechoConstatadoActual.getUsCreacion(); 
		
	
		if(pgimCriterioSprvsionDTO != null) {

			if(pgimCriterioSprvsionDTO.getIdCriterioSprvsion().equals(pgimHechoConstatadoDTO.getIdCriterioSprvsion())){
				throw new PgimException(TipoResultado.WARNING, "El hecho verificado ya se encuentra en este criterio.");
			}
			
			idCriterioSprvsion = pgimCriterioSprvsionDTO.getIdCriterioSprvsion();

		}else {
		
			//registro del nuevo criterio de supervisión
			PgimCriterioSprvsionDTO pgimCriterioSprvsionDTONuevo = new PgimCriterioSprvsionDTO();
			pgimCriterioSprvsionDTONuevo.setIdMatrizCriterio(pgimHechoConstatadoDTO.getDescIdMatrizCriterio());
			
			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository
                    .obtenerSupervisionByIdSupervision(pgimHechoConstatadoDTO.getIdSupervision());
			
			PgimCriterioSprvsionDTO pgimCriterioSprvsionDTOCreado = this.supervisionService.agregarCriterioFiscalizacion(
					pgimCriterioSprvsionDTONuevo, pgimSupervisionDTO, auditoriaDTO);
			
			idCriterioSprvsion = pgimCriterioSprvsionDTOCreado.getIdCriterioSprvsion();

		}
		
		
		if (pgimRolProcesoAutenticado.getIdRolProceso()
			.equals(pgimHechoConstatadoActual.getPgimRolProceso().getIdRolProceso())
			&& pasoProcesoDestino.getIdPasoProceso().equals(pgimHechoConstatadoActual.getPgimInstanciaPaso().getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso())
			&& auditoriaDTO.getUsername().equals(usuarioActualizadorHc)
			) {
			
			//Actualizado el HV => Cambio del criterio
			pgimHechoConstatadoActual.setPgimCriterioSprvsion(new PgimCriterioSprvsion());
			pgimHechoConstatadoActual.getPgimCriterioSprvsion()
					.setIdCriterioSprvsion(idCriterioSprvsion);
			
			PgimHechoConstatado pgimHechoConstatadoModificado = this.hechoConstatadoRepository.save(pgimHechoConstatadoActual);
			pgimHechoConstatadoDTOReasignado = this.hechoConstatadoRepository
					.obtenerHechoConstatadoDtoPorId(pgimHechoConstatadoModificado.getIdHechoConstatado());
			
			//Eliminar las obligaciones fiscalizadas actuales e infracciones  
			this.eliminarObligacionesNormativas(pgimHechoConstatadoDTO.getIdHechoConstatado(), auditoriaDTO);		
			
			//Registrar las nuevas obligaciones perteneciente al nuevo criterio
			this.registrarObligacionesFiscalizables(pgimHechoConstatadoDTO.getDescIdMatrizCriterio(),
					pgimHechoConstatadoDTO.getIdHechoConstatado(), auditoriaDTO);
			
		}else {
			PgimHechoConstatadoDTO pgimHechoConstatadoDTONuevo = pgimHechoConstatadoDTO;
			pgimHechoConstatadoDTONuevo.setIdCriterioSprvsion(idCriterioSprvsion);
			pgimHechoConstatadoDTONuevo.setIdHechoConstatadoOrigen(pgimHechoConstatadoDTO.getIdHechoConstatado());
			
			
			Sort sort = Sort.by("deObligacionNormativa");
			sort = sort.ascending();
			List<PgimObligacionNormaAuxDTO> lPgimOblgcnNrmaCrtrioDTO = this.listarObligacionesNormativasXCriterio(pgimHechoConstatadoDTO.getDescIdMatrizCriterio(), sort);
			pgimHechoConstatadoDTONuevo.setDescListaObligaciones(lPgimOblgcnNrmaCrtrioDTO); 
			
			pgimHechoConstatadoDTOReasignado = this.crearHC(pgimHechoConstatadoDTONuevo, auditoriaDTO);
			
			// Actualizar el HV anterior como histórico
			pgimHechoConstatadoActual.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_HISTORICO);
			this.hechoConstatadoRepository.save(pgimHechoConstatadoActual);

			// Poner como no vigente a las infracciones del HV anterior
			List<PgimObligacionNormaAuxDTO> lPgimOblgcnNrmaCrtrioDTOAnterior = this.obligacionNormaAuxRepository
					.listarObligacionesNormativasPorHC(pgimHechoConstatadoActual.getIdHechoConstatado(), sort);

			for (PgimObligacionNormaAuxDTO pgimObligacionNormaAuxDTO : lPgimOblgcnNrmaCrtrioDTOAnterior) {
				
				this.infraccionService.registrarInfracionNoVigente(pgimObligacionNormaAuxDTO.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);
			}
			
			// Actualizar HC reemplazante en el HC recogido en la supervision
			PgimHechoConstatado pgimHechoConstatadoModifReempl = this.hechoConstatadoRepository.findById(pgimHechoConstatadoDTOReasignado.getIdHechoConstatado()).orElse(null);
			List<PgimHechoConstatadoDTO> lhcr = this.hechoConstatadoRepository
					.listarHechosConstatadosPorHechoConstatadoRmplznte(pgimHechoConstatadoActual.getIdHechoConstatado());
			
			for (PgimHechoConstatadoDTO hcDTOaReemplazar : lhcr) {
				PgimHechoConstatado pgimHechoConstatadoSupervision = this.hechoConstatadoRepository
						.findById(hcDTOaReemplazar.getIdHechoConstatado()).orElse(null);
				pgimHechoConstatadoSupervision.setHechoConstatadoRmplznte(pgimHechoConstatadoModifReempl);
				this.hechoConstatadoRepository.save(pgimHechoConstatadoSupervision);
			}
			
		}
		
		return pgimHechoConstatadoDTOReasignado;
	}
	
	/**
	 * Permite registrar las obligaciones fiscalizadas para un HV a partir de idMatrizCriterio
	 * 
	 * @param idMatrizCriterio
	 * @param idHechoConstatado
	 * @param auditoriaDTO
	 */
	@Transactional(readOnly = false)
	private void registrarObligacionesFiscalizables(Long idMatrizCriterio, Long idHechoConstatado, AuditoriaDTO auditoriaDTO) {

		// Ordenamiento
		Sort sort = Sort.by("deObligacionNormativa");
		sort = sort.ascending();

		// listar obligaciones normativas x criterio (cuando es un nuevo HC)
		List<PgimObligacionNormaAuxDTO> lObligacionNorma = this.listarObligacionesNormativasXCriterio(idMatrizCriterio, sort);
		
		for (PgimObligacionNormaAuxDTO obligacion : lObligacionNorma) {

			PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchoc = new PgimOblgcnNrmtvaHchoc();

			oblgcnNrmtvaHchoc.setPgimHechoConstatado(new PgimHechoConstatado());
			oblgcnNrmtvaHchoc.getPgimHechoConstatado().setIdHechoConstatado(idHechoConstatado);

			oblgcnNrmtvaHchoc.setPgimOblgcnNrmaCrtrio(new PgimOblgcnNrmaCrtrio());
			oblgcnNrmtvaHchoc.getPgimOblgcnNrmaCrtrio().setIdOblgcnNrmaCrtrio(obligacion.getIdOblgcnNrmaCrtrio());

			oblgcnNrmtvaHchoc.setEsAplica("0");

			oblgcnNrmtvaHchoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			oblgcnNrmtvaHchoc.setFeCreacion(auditoriaDTO.getFecha());
			oblgcnNrmtvaHchoc.setUsCreacion(auditoriaDTO.getUsername());
			oblgcnNrmtvaHchoc.setIpCreacion(auditoriaDTO.getTerminal());

			this.oblgcnNrmtvaHchocRepository.save(oblgcnNrmtvaHchoc);
		
		}
	}
	
	/**
	 * Permite eliminar las obligaciones fiscalizadas de un hecho verificado y sus infracciones relacionadas
	 * @param idHechoConstatado
	 * @param auditoriaDTO
	 */
	@Transactional(readOnly = false)
	private void eliminarObligacionesNormativas(Long idHechoConstatado, AuditoriaDTO auditoriaDTO) {

		// Eliminar obligaciones del HC
		Sort sort = Sort.by("deObligacionNormativa");
		sort = sort.ascending();
		List<PgimObligacionNormaAuxDTO> lPgimObligacionNormaAuxDTO = this.listarObligacionesNormativasPorHC(idHechoConstatado, sort);

		for (PgimObligacionNormaAuxDTO pgimObligacionNormaAuxDTO : lPgimObligacionNormaAuxDTO) {
			
			this.eliminarObligacionNormativaFiscalizada(pgimObligacionNormaAuxDTO.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);			
		}
	}
	
	/**
	 * Permite eliminar una obligación normativa fiscalizada y sus ítems e infracciones relacionadas
	 * @param idOblgcnNrmtvaHchoc
	 * @param auditoriaDTO
	 */
	private void eliminarObligacionNormativaFiscalizada(Long idOblgcnNrmtvaHchoc, AuditoriaDTO auditoriaDTO) {

		PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchoc = this.oblgcnNrmtvaHchocRepository.findById(idOblgcnNrmtvaHchoc).orElse(null);;
		
		oblgcnNrmtvaHchoc.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		oblgcnNrmtvaHchoc.setFeActualizacion(auditoriaDTO.getFecha());
		oblgcnNrmtvaHchoc.setUsActualizacion(auditoriaDTO.getUsername());
		oblgcnNrmtvaHchoc.setIpActualizacion(auditoriaDTO.getTerminal());

		this.oblgcnNrmtvaHchocRepository.save(oblgcnNrmtvaHchoc);
		
		//Eliminar los items de la obligación normativa fiscalizada
		List<PgimOblgcnNrmtvaItem> lPgimOblgcnNrmtvaItemActual = this.listarItemsNormasXobligacionNorma(idOblgcnNrmtvaHchoc);
		
		for (PgimOblgcnNrmtvaItem pgimOblgcnNrmtvaItem : lPgimOblgcnNrmtvaItemActual) {

			pgimOblgcnNrmtvaItem.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimOblgcnNrmtvaItem.setFeActualizacion(auditoriaDTO.getFecha());
			pgimOblgcnNrmtvaItem.setUsActualizacion(auditoriaDTO.getUsername());
			pgimOblgcnNrmtvaItem.setIpActualizacion(auditoriaDTO.getTerminal());
				
			this.oblgcnNrmtvaItemRepository.save(pgimOblgcnNrmtvaItem);
		}
		
		//Eliminar las infraciones que tiene la obligación normativa
		this.eliminarInfraccion(idOblgcnNrmtvaHchoc, auditoriaDTO);
	}
	

	/**
	 * Permite obtener un hecho verificado de acuerdo con su identificador interno.
	 * 
	 * @param idHechoConstatado
	 * @return
	 */
	@Override
	public PgimHechoConstatado getByidHechoConstatado(Long idHechoConstatado) {
		return this.hechoConstatadoRepository.findById(idHechoConstatado).orElse(null);
	}

	/**
	 * Permite eliminar lógicamente un hecho verificado.
	 * 
	 * @param pgimHechoConstatadoActual
	 */
	@Transactional(readOnly = false)
	@Override
	public void eliminarHC(PgimHechoConstatado pgimHechoConstatadoActual, AuditoriaDTO auditoriaDTO) {

		// Validar que el HC no sea un HC reemplazante
		List<PgimHechoConstatadoDTO> listaValida = this.hechoConstatadoRepository
				.listarHechosConstatadosPorHechoConstatadoRmplznte(pgimHechoConstatadoActual.getIdHechoConstatado());

		if (listaValida.size() > 0) {
			throw new PgimException("error",
					"El hecho verificado no se puede eliminar, el registro se encuentra vinculado a un hecho verificado por parte de Osinergmin. Solo es posible modificar la información correspondiente.");
		}

		if(pgimHechoConstatadoActual.getFlIncluidoActaSupervision().equals(ConstantesUtil.INCLUIDO_ACTA_SUPERVISION_SI)){
			throw new PgimException(TipoResultado.WARNING,"El hecho verificado no se puede eliminar, el registro figura en el Acta de fiscalización.");
		}

		pgimHechoConstatadoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimHechoConstatadoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimHechoConstatadoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimHechoConstatadoActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.hechoConstatadoRepository.save(pgimHechoConstatadoActual);

		// Eliminar obligaciones fiscalizadas e infracciones del HC
		this.eliminarObligacionesNormativas(pgimHechoConstatadoActual.getIdHechoConstatado(), auditoriaDTO);

	}

	/**
	 * Permite actualizar o crear los hechos verificados de la fiscalización, al
	 * cambiar de paso de fiscalización.
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public void actualizarHCxSupervision(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
			AuditoriaDTO auditoriaDTO) {

		if (pgimInstanciaProces != null && pgimInstanciaPaso != null) {

			// Obtenemos el registro de la fiscalización
			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository
					.obtenerSupervisionByidInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());

			if (pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
					.equals(ConstantesUtil.PARAM_RELACION_PREACTASUPER_ELAINFO)) {

				// obtenemos la lista de HC de la fiscalización
				List<PgimHechoConstatadoDTO> lHechoConstatado = this.hechoConstatadoRepository
						.listarHechosConstatadosPorSupervision(pgimSupervisionDTO.getIdSupervision(),
								ConstantesUtil.PROCESO_ROL_SUPERVISOR);

				for (PgimHechoConstatadoDTO pgimHechoConstatadoDTO : lHechoConstatado) {

					if (pgimHechoConstatadoDTO.getIdTipoCumplimiento().equals(ConstantesUtil.PARAM_ID_NO_CUMPLE_TC)) {

						// Si el HC tiene valor NO CUMPLE, se registra en el Acta de fiscalización

						PgimHechoConstatado pgimHechoConstatadoActualiza = this.hechoConstatadoRepository
								.findById(pgimHechoConstatadoDTO.getIdHechoConstatado()).orElse(null);
						pgimHechoConstatadoActualiza
								.setFlIncluidoActaSupervision(ConstantesUtil.INCLUIDO_ACTA_SUPERVISION_SI);
						pgimHechoConstatadoActualiza.setFeActualizacion(auditoriaDTO.getFecha());
						pgimHechoConstatadoActualiza.setUsActualizacion(auditoriaDTO.getUsername());
						pgimHechoConstatadoActualiza.setIpActualizacion(auditoriaDTO.getTerminal());
						this.hechoConstatadoRepository.save(pgimHechoConstatadoActualiza);

					}

				}

			} else if (pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
					.equals(ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME)
					|| pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
					.equals(ConstantesUtil.PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF)
				) {
				
				Long rolRegistroHC = ConstantesUtil.PROCESO_ROL_SUPERVISOR;

				// obtenemos la lista de HC de la fiscalización
				List<PgimHechoConstatadoDTO> lHechoConstatado = this.hechoConstatadoRepository
						.listarHechosConstatadosPorSupervision(pgimSupervisionDTO.getIdSupervision(), rolRegistroHC);

				for (PgimHechoConstatadoDTO pgimHechoConstatadoDTO : lHechoConstatado) {

					// Creamos nuevo vigente
					PgimHechoConstatado pgimHechoConstatadoNuevo = new PgimHechoConstatado();

					pgimHechoConstatadoNuevo.setPgimCriterioSprvsion(new PgimCriterioSprvsion());
					pgimHechoConstatadoNuevo.getPgimCriterioSprvsion()
							.setIdCriterioSprvsion(pgimHechoConstatadoDTO.getIdCriterioSprvsion());

					pgimHechoConstatadoNuevo.setPgimSupervision(new PgimSupervision());
					pgimHechoConstatadoNuevo.getPgimSupervision()
							.setIdSupervision(pgimHechoConstatadoDTO.getIdSupervision());

					pgimHechoConstatadoNuevo.setPgimInstanciaPaso(new PgimInstanciaPaso());
					pgimHechoConstatadoNuevo.getPgimInstanciaPaso()
							.setIdInstanciaPaso(pgimHechoConstatadoDTO.getIdInstanciaPaso());

					pgimHechoConstatadoNuevo.setTipoCumplimiento(new PgimValorParametro());
					pgimHechoConstatadoNuevo.getTipoCumplimiento()
							.setIdValorParametro(pgimHechoConstatadoDTO.getIdTipoCumplimiento());

					pgimHechoConstatadoNuevo.setPgimRolProceso(new PgimRolProceso());
					pgimHechoConstatadoNuevo.getPgimRolProceso()
							.setIdRolProceso(pgimHechoConstatadoDTO.getIdRolProceso());

					pgimHechoConstatadoNuevo.setHechoConstatadoOrigen(new PgimHechoConstatado());
					pgimHechoConstatadoNuevo.getHechoConstatadoOrigen()
							.setIdHechoConstatado(pgimHechoConstatadoDTO.getIdHechoConstatado());

					pgimHechoConstatadoNuevo.setDeHechoConstatadoT(pgimHechoConstatadoDTO.getDeHechoConstatadoT());
					pgimHechoConstatadoNuevo
							.setDeComplementoObservacion(pgimHechoConstatadoDTO.getDeComplementoObservacion());
					pgimHechoConstatadoNuevo.setDeSustentoT(pgimHechoConstatadoDTO.getDeSustentoT());
					pgimHechoConstatadoNuevo.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_VIGENTE);
					pgimHechoConstatadoNuevo
							.setFlIncluidoActaSupervision(pgimHechoConstatadoDTO.getFlIncluidoActaSupervision());

					pgimHechoConstatadoNuevo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimHechoConstatadoNuevo.setFeCreacion(auditoriaDTO.getFecha());
					pgimHechoConstatadoNuevo.setUsCreacion(auditoriaDTO.getUsername());
					pgimHechoConstatadoNuevo.setIpCreacion(auditoriaDTO.getTerminal());

					PgimHechoConstatado pgimHCCreado = this.hechoConstatadoRepository.save(pgimHechoConstatadoNuevo);

					// Actualizamos el HC anterior como Histórico
					PgimHechoConstatado pgimHechoConstatadoActualiza = this.hechoConstatadoRepository.findById(pgimHechoConstatadoDTO.getIdHechoConstatado()).orElse(null);

					pgimHechoConstatadoActualiza.setEsVigente(ConstantesUtil.PARAM_HC_ESTADO_HISTORICO);
					// pgimHechoConstatadoActualiza.setFeActualizacion(auditoriaDTO.getFecha());
					// pgimHechoConstatadoActualiza.setUsActualizacion(auditoriaDTO.getUsername());
					// pgimHechoConstatadoActualiza.setIpActualizacion(auditoriaDTO.getTerminal());
					this.hechoConstatadoRepository.save(pgimHechoConstatadoActualiza);


					/**
					 * <!----------------------------------------------------------------------------------------------------------
					 * PGIM-11253: Agregar la sección “Componentes asociados al Hecho Verificado” en
					 * fase 2 Y 3 de la fiscalización
					 * ------------------------------------------------------------------------------------------------------------>
					 */
    				List<PgimComponenteHcDTO> lPgimComponenteHcDTO = this.componenteHcRepository.listarComponenteMineroHc(pgimHechoConstatadoDTO.getIdHechoConstatado());

					for (PgimComponenteHcDTO componenteHechoVerificado : lPgimComponenteHcDTO) {

						PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();

						pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
						pgimComponenteHc.getPgimHechoConstatado().setIdHechoConstatado(pgimHCCreado.getIdHechoConstatado());

						pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
						pgimComponenteHc.getPgimCmineroSprvsion().setIdCmineroSprvsion(componenteHechoVerificado.getIdCmineroSprvsion());

							pgimComponenteHc.setFlAplica(componenteHechoVerificado.getFlAplica());
						
						pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);

						pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
						pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
						pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());

						this.componenteHcRepository.save(pgimComponenteHc);
					}
					/**
					 * <!----------------------------------------------------------------------------------------------------------
					 * PGIM-11253: FIN
					 * ------------------------------------------------------------------------------------------------------------>
					 */

					// obtenemos las obligaciones del HC anterior
					Sort sort = Sort.by("deObligacionNormativa");
					sort = sort.ascending();
					List<PgimObligacionNormaAuxDTO> lPgimOblgcnNrmaCrtrioDTO = this.listarObligacionesNormativasPorHC(pgimHechoConstatadoDTO.getIdHechoConstatado(), sort);
					
					// registramos las obligaciones
					for (PgimObligacionNormaAuxDTO obligacion : lPgimOblgcnNrmaCrtrioDTO) {

						PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchoc = new PgimOblgcnNrmtvaHchoc();

						oblgcnNrmtvaHchoc.setPgimHechoConstatado(new PgimHechoConstatado());
						oblgcnNrmtvaHchoc.getPgimHechoConstatado()
								.setIdHechoConstatado(pgimHCCreado.getIdHechoConstatado());

						oblgcnNrmtvaHchoc.setPgimOblgcnNrmaCrtrio(new PgimOblgcnNrmaCrtrio());
						oblgcnNrmtvaHchoc.getPgimOblgcnNrmaCrtrio()
								.setIdOblgcnNrmaCrtrio(obligacion.getIdOblgcnNrmaCrtrio());

						if (obligacion.isDescSeleccionado())
							oblgcnNrmtvaHchoc.setEsAplica("1");
						else
							oblgcnNrmtvaHchoc.setEsAplica("0");

						oblgcnNrmtvaHchoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
						oblgcnNrmtvaHchoc.setFeCreacion(auditoriaDTO.getFecha());
						oblgcnNrmtvaHchoc.setUsCreacion(auditoriaDTO.getUsername());
						oblgcnNrmtvaHchoc.setIpCreacion(auditoriaDTO.getTerminal());

						this.oblgcnNrmtvaHchocRepository.save(oblgcnNrmtvaHchoc);
						
						// registramos ítems normas pertenecientes a la obligación
						List<PgimOblgcnNrmtvaItem> lPgimOblgcnNrmtvaItemActual = this.listarItemsNormasXobligacionNorma(oblgcnNrmtvaHchoc.getIdOblgcnNrmtvaHchoc());
						List<PgimOblgcnNrmtvaItemDTO> lPgimOblgcnNrmtvaItemDTOModificado = new ArrayList<>();
						if(obligacion.getListPgimOblgcnNrmtvaItemDTO() != null) {
							lPgimOblgcnNrmtvaItemDTOModificado = obligacion.getListPgimOblgcnNrmtvaItemDTO();
						}
					
						this.crearOModificarItemNormaHijoObligacion(lPgimOblgcnNrmtvaItemDTOModificado, lPgimOblgcnNrmtvaItemActual, oblgcnNrmtvaHchoc.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);


					}

				}

			}
			
			//Eliminamos HC históricos por fase, en caso haya un retorno
			PgimRelacionPaso pgimRelacionPasoActual = this.relacionPasoRepository.findById(pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()).orElse(null);
			if(pgimRelacionPasoActual!=null) {
				
				List<PgimHechoCnsttdoFaseDTO> lstPgimCotejoHechoCnsttdoDTO = new ArrayList<PgimHechoCnsttdoFaseDTO>();
				
				if(pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso().equals(ConstantesUtil.PARAM_PROCESO_PASO_SOLICITAR_DOC_A_UM)
						|| pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso().equals(ConstantesUtil.PARAM_PROCESO_PASO_REALIZAR_MED_Y_CONSTATAR_HEC)
						|| pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso().equals(ConstantesUtil.PARAM_PROCESO_PASO_PRESENTAR_ACTA_SUPER)
						) {	
					lstPgimCotejoHechoCnsttdoDTO = 
							this.cotejoHechoCnsttdoRepository.listarHechoCnsttdoFaseParaEliminar(pgimSupervisionDTO.getIdSupervision(), ConstantesUtil.PARAM_SUPERVISION_SUPERVISION_CAMPO);														
				}
				else if(pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso().equals(ConstantesUtil.PARAM_PROCESO_PASO_PRESENTAR_INFO_SUPER)) {
					lstPgimCotejoHechoCnsttdoDTO = 
							this.cotejoHechoCnsttdoRepository.listarHechoCnsttdoFaseParaEliminar(pgimSupervisionDTO.getIdSupervision(), ConstantesUtil.PARAM_SUPERVISION_POST_SUPERVISION_CAMPO);
					}
				else if(pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso().equals(ConstantesUtil.PARAM_PROCESO_PASO_ELABORAR_FICHA_HECHOS_VERIFICADOS)) {
					lstPgimCotejoHechoCnsttdoDTO = 
							this.cotejoHechoCnsttdoRepository.listarHechoCnsttdoFaseParaEliminar(pgimSupervisionDTO.getIdSupervision(), ConstantesUtil.PARAM_SUPERVISION_REV_INFO_SUPERVISION);
				}
				else if(pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso().equals(ConstantesUtil.PARAM_PROCESO_PASO_CONFIRMAR_HC_E_INFRA)
						|| pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso().equals(ConstantesUtil.PARAM_PROCESO_PASO_VISAR_HC)
						) {
					lstPgimCotejoHechoCnsttdoDTO = 
							this.cotejoHechoCnsttdoRepository.listarHechoCnsttdoFaseParaEliminar(pgimSupervisionDTO.getIdSupervision(), ConstantesUtil.PARAM_SUPERVISION_APROB_RESULTADOS);
					}
				
				//Si hay registros en la lista, se deben eliminar físicamente (la lista histórica se volverá a generar posteriormente, incluyendo los nuevos posibles valores)
				for (PgimHechoCnsttdoFaseDTO pgimHechoCnsttdoFaseDTO : lstPgimCotejoHechoCnsttdoDTO) {
					//borrar HC histórico
					this.borrarHChistorico(pgimHechoCnsttdoFaseDTO);
				}	
				
				
			}
			
			

		}

	}
	
	@Transactional(readOnly = false)
	public void borrarHChistorico(PgimHechoCnsttdoFaseDTO pgimHechoCnsttdoFaseDTO) {
		
		PgimHechoCnsttdoFase pgimHechoCnsttdoFase = this.hechoCnsttdoFaseRepository.findById(pgimHechoCnsttdoFaseDTO.getIdHechoConstatadoHist()).orElse(null);
		
		if(pgimHechoCnsttdoFase != null) {
				this.hechoCnsttdoFaseRepository.delete(pgimHechoCnsttdoFase);
		}
	}
	

	/**
	 * Permite recuperar la lista de cotejo de hechos verificados
	 * 
	 * 
	 * @param filtroCotejoHechoConstatadoDTO
	 *
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public Page<PgimCotejoHechoCnsttdoDTO> listarCotejoHC(FiltroCotejoHechoConstatadoDTO filtroCotejoHechoConstatadoDTO,
			Pageable paginador) throws Exception {
		
		if(filtroCotejoHechoConstatadoDTO.getIdFaseSeleccion() < filtroCotejoHechoConstatadoDTO.getIdFaseActual()) { 		
			
			List<PgimHechoCnsttdoFaseDTO> lstPgimCotejoHechoCnsttdoDTO = this.cotejoHechoCnsttdoRepository
					.listarHechoCnsttdoFase(filtroCotejoHechoConstatadoDTO.getIdSupervision(),
							filtroCotejoHechoConstatadoDTO.getIdFaseSeleccion(), null);

			if(lstPgimCotejoHechoCnsttdoDTO.size()==0) {
				//Generar data....
				this.generarDataHistoricaFase(filtroCotejoHechoConstatadoDTO.getIdSupervision(),
						filtroCotejoHechoConstatadoDTO.getIdFaseSeleccion());
			}
			
			//listar data histórica por fase paginada.....
			return this.cotejoHechoCnsttdoRepository.listarCotejoHechosConstatadosPorFasePaginado(
					filtroCotejoHechoConstatadoDTO.getIdSupervision(),
					filtroCotejoHechoConstatadoDTO.getCodigoCumpleSupervisora(),
					filtroCotejoHechoConstatadoDTO.getCodigoCumpleOsinergmin(),
					filtroCotejoHechoConstatadoDTO.getIdFaseSeleccion(),
					paginador);
			
			
		}else {

			return this.cotejoHechoCnsttdoRepository.listarCotejoHechosConstatadosPaginado(
					filtroCotejoHechoConstatadoDTO.getIdSupervision(),
					filtroCotejoHechoConstatadoDTO.getCodigoCumpleSupervisora(),
					filtroCotejoHechoConstatadoDTO.getCodigoCumpleOsinergmin(),
					paginador);
		}
	}
	
	@Transactional(readOnly = false)
	public void generarDataHistoricaFase(Long idSupervision,Long idFase) throws ParseException {
		
		List<PgimCotejoHechoCnsttdoDTO> lstPgimCotejoHechoCnsttdoDTO =
				this.cotejoHechoCnsttdoRepository.listarCotejoHechosConstatados(
						idSupervision, 0L);
		
		for (PgimCotejoHechoCnsttdoDTO pgimCotejoHechoCnsttdoDTO : lstPgimCotejoHechoCnsttdoDTO) {
			
			//if(pgimCotejoHechoCnsttdoDTO.getIdFaseProceso() > idFase) {
				
				Long idHechoConstatado = pgimCotejoHechoCnsttdoDTO.getIdHechoConstatado();
				PgimHechoConstatadoDTO pgimHechoConstatadoDTOSel = null;
				boolean flagCondicion = false;
				
				do {
					
					PgimHechoConstatadoDTO pgimHechoConstatadoDTOValida = this.hechoConstatadoRepository.obtenerHechoConstatadoDtoPorId(idHechoConstatado);
					
					if(pgimHechoConstatadoDTOValida == null || pgimHechoConstatadoDTOValida.getIdHechoConstatado()==null)
					{
						flagCondicion = true;
						pgimHechoConstatadoDTOSel = null;
					}
					else {
					
						if(pgimHechoConstatadoDTOValida.getDescFaseProcesoSupervision() > idFase) {
							idHechoConstatado = pgimHechoConstatadoDTOValida.getIdHechoConstatadoOrigen();
						}
						else {
							flagCondicion = true;
							pgimHechoConstatadoDTOSel = pgimHechoConstatadoDTOValida;
						}
					}
					
					
				} while (!flagCondicion);
				
				
				PgimHechoConstatadoDTO pgimHechoConstatadoDTOReempl = null;				
				Long idHechoConstatadoReempl = null;
				
				
				if(pgimHechoConstatadoDTOSel != null && pgimHechoConstatadoDTOSel.getIdHechoConstatado()!=null) {				
					idHechoConstatadoReempl = pgimHechoConstatadoDTOSel.getIdHechoConstatadoRmplznte();
				}
				else if(pgimCotejoHechoCnsttdoDTO.getIdHechoConstatadoOsi()!= null) {
					idHechoConstatadoReempl = pgimCotejoHechoCnsttdoDTO.getIdHechoConstatadoOsi();	
				}
				
				
				if(idHechoConstatadoReempl != null ) {
								
					flagCondicion = false;
					
					do {
						
						PgimHechoConstatadoDTO pgimHechoConstatadoDTOValida2 = this.hechoConstatadoRepository.obtenerHechoConstatadoDtoPorId(idHechoConstatadoReempl);
						
						if(pgimHechoConstatadoDTOValida2 == null || pgimHechoConstatadoDTOValida2.getIdHechoConstatado()==null)
						{
							flagCondicion = true;
							pgimHechoConstatadoDTOReempl = null;
						}
						else {
						
							if(pgimHechoConstatadoDTOValida2.getDescFaseProcesoSupervision() > idFase) {
								idHechoConstatadoReempl = pgimHechoConstatadoDTOValida2.getIdHechoConstatadoOrigen();
							}
							else {
								flagCondicion = true;
								pgimHechoConstatadoDTOReempl = pgimHechoConstatadoDTOValida2;
							}
						}
						
						
					} while (!flagCondicion);
					
				}
				
				
				
				
				
				if(
					(pgimHechoConstatadoDTOSel != null && pgimHechoConstatadoDTOSel.getIdHechoConstatado()!=null)
						|| (pgimHechoConstatadoDTOReempl != null && pgimHechoConstatadoDTOReempl.getIdHechoConstatado()!=null)
						) {
						
						
						//Generamos registro histórico
						PgimHechoCnsttdoFase pgimHechoCnsttdoFase = new PgimHechoCnsttdoFase(); 
						
						DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					    String strFechaGeneracion = df.format(new Date());					    		    
						String strFechaGen= strFechaGeneracion.substring(0, 2)+"/"+strFechaGeneracion.substring(3, 5)+"/"+strFechaGeneracion.substring(6, 10);			
						Date fechaGeneracion=new SimpleDateFormat("dd/MM/yyyy").parse(strFechaGen);
						pgimHechoCnsttdoFase.setFeGeneracion(fechaGeneracion);
												
						
						pgimHechoCnsttdoFase.setIdFaseMuestra(idFase);
						
						if(pgimHechoConstatadoDTOSel != null && pgimHechoConstatadoDTOSel.getIdHechoConstatado()!=null) {
							pgimHechoCnsttdoFase.setIdHechoConstatado(pgimHechoConstatadoDTOSel.getIdHechoConstatado());
							pgimHechoCnsttdoFase.setIdCriterioSprvsion(pgimHechoConstatadoDTOSel.getIdCriterioSprvsion());
							pgimHechoCnsttdoFase.setCoMatrizCriterio(pgimHechoConstatadoDTOSel.getDescCoMatrizCriterio());
							pgimHechoCnsttdoFase.setNuOrdenGrpoCrtrio(pgimHechoConstatadoDTOSel.getDescNuOrdenGrpoCrtrio());
							pgimHechoCnsttdoFase.setNuOrdenCrtrio(pgimHechoConstatadoDTOSel.getDescNuOrdenCriterio());
							pgimHechoCnsttdoFase.setIdSupervision(pgimHechoConstatadoDTOSel.getIdSupervision());
							pgimHechoCnsttdoFase.setIdInstanciaPaso(pgimHechoConstatadoDTOSel.getIdInstanciaPaso());
							pgimHechoCnsttdoFase.setDeHechoConstatado(pgimHechoConstatadoDTOSel.getDeHechoConstatadoT());
							pgimHechoCnsttdoFase.setDeSustento(pgimHechoConstatadoDTOSel.getDeSustentoT());
							pgimHechoCnsttdoFase.setIdTipoCumplimiento(pgimHechoConstatadoDTOSel.getIdTipoCumplimiento());
							pgimHechoCnsttdoFase.setDeTipoCumplimiento(pgimHechoConstatadoDTOSel.getDescTipoCumplimiento());
							pgimHechoCnsttdoFase.setCoTipoCumplimiento(pgimHechoConstatadoDTOSel.getDescCoTipoCumplimiento());
							pgimHechoCnsttdoFase.setIdRolProceso(pgimHechoConstatadoDTOSel.getIdRolProceso());
							pgimHechoCnsttdoFase.setIdHechoConstatadoOrigen(pgimHechoConstatadoDTOSel.getIdHechoConstatadoOrigen());
							
							pgimHechoCnsttdoFase.setDeComplementoObservacion(pgimHechoConstatadoDTOSel.getDeComplementoObservacion());
							pgimHechoCnsttdoFase.setFlIncluidoActaSupervision(pgimHechoConstatadoDTOSel.getFlIncluidoActaSupervision());
							pgimHechoCnsttdoFase.setEsVigente(pgimHechoConstatadoDTOSel.getEsVigente());
							pgimHechoCnsttdoFase.setIdFaseProceso(pgimHechoConstatadoDTOSel.getDescFaseProcesoSupervision());
														
							pgimHechoCnsttdoFase.setUsCreacion(pgimHechoCnsttdoFase.getUsCreacion());
							pgimHechoCnsttdoFase.setIpCreacion(pgimHechoCnsttdoFase.getIpCreacion());
						}
						else {
							pgimHechoCnsttdoFase.setDeHechoConstatado("<No registrado>");
							pgimHechoCnsttdoFase.setDeComplementoObservacion("<No registrado>");
							pgimHechoCnsttdoFase.setDeSustento("<No registrado>");
							pgimHechoCnsttdoFase.setDeTipoCumplimiento("-");
							pgimHechoCnsttdoFase.setCoTipoCumplimiento(0L);
						}
						
						
						if(pgimHechoConstatadoDTOReempl != null && pgimHechoConstatadoDTOReempl.getIdHechoConstatado()!=null) {
							
							if(pgimHechoConstatadoDTOSel != null && pgimHechoConstatadoDTOSel.getIdHechoConstatado()!=null) {
								pgimHechoCnsttdoFase.setIdHechoConstatadoRmplznte(pgimHechoConstatadoDTOReempl.getIdHechoConstatado());
							}
							else {
								pgimHechoCnsttdoFase.setIdSupervision(pgimHechoConstatadoDTOReempl.getIdSupervision());
								pgimHechoCnsttdoFase.setIdCriterioSprvsion(pgimHechoConstatadoDTOReempl.getIdCriterioSprvsion());
								pgimHechoCnsttdoFase.setCoMatrizCriterio(pgimHechoConstatadoDTOReempl.getDescCoMatrizCriterio());
								pgimHechoCnsttdoFase.setNuOrdenGrpoCrtrio(pgimHechoConstatadoDTOReempl.getDescNuOrdenGrpoCrtrio());
								
								pgimHechoCnsttdoFase.setUsCreacion(pgimHechoConstatadoDTOReempl.getUsCreacion());
								pgimHechoCnsttdoFase.setIpCreacion(pgimHechoConstatadoDTOReempl.getIpCreacion());
							}
							
							pgimHechoCnsttdoFase.setIdHechoConstatadoOsi(pgimHechoConstatadoDTOReempl.getIdHechoConstatado());
							pgimHechoCnsttdoFase.setDeHechoConstatadoOsi(pgimHechoConstatadoDTOReempl.getDeHechoConstatadoT());
							pgimHechoCnsttdoFase.setDeComplementoObservacionOsi(pgimHechoConstatadoDTOReempl.getDeComplementoObservacion());
							pgimHechoCnsttdoFase.setDeSustentoOsi(pgimHechoConstatadoDTOReempl.getDeSustentoT());
							pgimHechoCnsttdoFase.setIdTipoCumplimientoOsi(pgimHechoConstatadoDTOReempl.getIdTipoCumplimiento());
							pgimHechoCnsttdoFase.setDeTipoCumplimientoOsi(pgimHechoConstatadoDTOReempl.getDescTipoCumplimiento());
							pgimHechoCnsttdoFase.setCoTipoCumplimientoOsi(pgimHechoConstatadoDTOReempl.getDescCoTipoCumplimiento());
							pgimHechoCnsttdoFase.setDeComentarioOsi(pgimHechoConstatadoDTOReempl.getDeComentarioOsiT());
							pgimHechoCnsttdoFase.setIdFaseProcesoOsi(pgimHechoConstatadoDTOReempl.getDescFaseProcesoSupervision());
							pgimHechoCnsttdoFase.setIdHechoConstatadoOrigenOsi(pgimHechoConstatadoDTOReempl.getIdHechoConstatadoOrigen());
							pgimHechoCnsttdoFase.setIdHechoConstatadoRmplznteOsi(pgimHechoConstatadoDTOReempl.getIdHechoConstatadoRmplznte());
							
						}
											
						pgimHechoCnsttdoFase.setEsRegistro(ConstantesUtil.IND_ACTIVO);
						pgimHechoCnsttdoFase.setFeCreacion(new Date());						
						
						this.hechoCnsttdoFaseRepository.save(pgimHechoCnsttdoFase);
									
				
				}
				
			
		}
		
	}
	
	
	
	

	/**
	 * Permite recuperar la lista de cotejo de hechos verificados
	 * 
	 * 
	 * @param filtroCotejoHechoConstatadoDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PgimCotejoHechoCnsttdoDTO> listarCotejoHCpendientesConformidad(
			FiltroCotejoHechoConstatadoDTO filtroCotejoHechoConstatadoDTO) {

		return this.cotejoHechoCnsttdoRepository.listarCotejoHCpendientesConformidad(
				filtroCotejoHechoConstatadoDTO.getIdSupervision(), filtroCotejoHechoConstatadoDTO.getCodigoCumpleSupervisora());
	}

	/**
	 * Permite obtener un objeto DTO de un hecho verificado de acuerdo con su
	 * identificador interno.
	 * 
	 * @param idHechoConstatado
	 * @return
	 */
	@Override
	public PgimHechoConstatadoDTO obtenerHechoConstatadoDtoPorId(Long idHechoConstatado) {

		PgimHechoConstatadoDTO hcDTO = this.hechoConstatadoRepository.obtenerHechoConstatadoDtoPorId(idHechoConstatado);

		if (hcDTO != null) {

			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionPorId(hcDTO.getIdSupervision());
			if (pgimSupervisionDTO != null) {
				hcDTO.setDescIdEspecialidad(pgimSupervisionDTO.getDescIdEspecialidad());
			}

			List<PgimPersonaosiAuxDTO> lPgimPersonaosiAuxDTO = new ArrayList<PgimPersonaosiAuxDTO>();

			if (hcDTO.getUsActualizacion() != null)// || !hcDTO.getUsActualizacion().equals("")
				lPgimPersonaosiAuxDTO = this.personalOsiAuxRepository.listarPersonalOsi(hcDTO.getUsActualizacion());
			else
				lPgimPersonaosiAuxDTO = this.personalOsiAuxRepository.listarPersonalOsi(hcDTO.getUsCreacion());

			String strResponsable = "";

			if (lPgimPersonaosiAuxDTO.size() > 0) {
				strResponsable = lPgimPersonaosiAuxDTO.get(0).getNoCompletoPersona() + " | ";
			} else {

				List<PgimPersonaconAuxDTO> lPgimPersonaconAuxDTO = new ArrayList<PgimPersonaconAuxDTO>();

				if (hcDTO.getUsActualizacion() != null) // || !hcDTO.getUsActualizacion().equals("")
					lPgimPersonaconAuxDTO = this.personalConAuxRepository
							.listarPersonalContratoXusuario(hcDTO.getUsActualizacion());
				else
					lPgimPersonaconAuxDTO = this.personalConAuxRepository
							.listarPersonalContratoXusuario(hcDTO.getUsCreacion());

				if (lPgimPersonaconAuxDTO.size() > 0)
					strResponsable = lPgimPersonaconAuxDTO.get(0).getNoCompletoPersona() + " | ";

			}

			strResponsable = strResponsable + hcDTO.getDescNoRolProceso();
			hcDTO.setDescResponsable(strResponsable);

		}

		return hcDTO;
	}

	/**
	 * Permite recuperar la lista histórica de hechos verificados para un ID de HC
	 * 
	 * 
	 * @param idHechoConstatado
	 * @return
	 * 
	 */
	@Override
	public List<PgimHechoConstatadoDTO> listaHistoricaHechosConstatadosPorID(Long idHechoConstatado) {

		List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTO = new ArrayList<PgimHechoConstatadoDTO>();

		PgimHechoConstatadoDTO pgimHechoConstatadoDTO = this.obtenerHechoConstatadoDtoPorId(idHechoConstatado);

		if (pgimHechoConstatadoDTO != null) {

			// Adicionamos el HC reemplazante, y sus históricos
			if (pgimHechoConstatadoDTO.getIdHechoConstatadoRmplznte() != null) {

				Long idHC = pgimHechoConstatadoDTO.getIdHechoConstatadoRmplznte();

				while (idHC != null) {

					PgimHechoConstatadoDTO pgimHCdto = this.obtenerHechoConstatadoDtoPorId(idHC);

					if (pgimHCdto != null) {
						lPgimHechoConstatadoDTO.add(pgimHCdto);
						idHC = pgimHCdto.getIdHechoConstatadoOrigen();
					} else
						idHC = null;

				}

			}

			// Adicionamos el HC inicial a la lista
			lPgimHechoConstatadoDTO.add(pgimHechoConstatadoDTO);

			// Adicionamos los HC históricos
			if (pgimHechoConstatadoDTO.getIdHechoConstatadoOrigen() != null) {

				Long idHCorigen = pgimHechoConstatadoDTO.getIdHechoConstatadoOrigen();

				while (idHCorigen != null) {

					PgimHechoConstatadoDTO pgimHCorigen = this.obtenerHechoConstatadoDtoPorId(idHCorigen);

					if (pgimHCorigen != null) {
						lPgimHechoConstatadoDTO.add(pgimHCorigen);
						idHCorigen = pgimHCorigen.getIdHechoConstatadoOrigen();
					} else {
						idHCorigen = null;
					}
				}
			}
		}

		return lPgimHechoConstatadoDTO;
	}
	
	@Override
	public List<PgimOblgcnNrmtvaHchocDTO> listarObligacFiscalizadasPorCriterioSuperv(Long idCriterioSprvsion) {
		
		List<PgimOblgcnNrmtvaHchocDTO> lPgimOblgcnNrmtvaHchocDTO = new ArrayList<PgimOblgcnNrmtvaHchocDTO>();

		lPgimOblgcnNrmtvaHchocDTO = this.oblgcnNrmtvaHchocRepository.listarOblgFiscalizadasPorCriterioSuperv(idCriterioSprvsion);
		
		for (PgimOblgcnNrmtvaHchocDTO pgimOblgcnNrmtvaHchocDTO : lPgimOblgcnNrmtvaHchocDTO) {
			
			PgimOblgcnNrmaCrtrioDTO PgimOblgcnNrmaCrtrioDTO = this.matrizSupervisionService.obtenerObligacionNrmaCriterioPorId(pgimOblgcnNrmtvaHchocDTO.getIdOblgcnNrmaCrtrio());
			pgimOblgcnNrmtvaHchocDTO.setPgimOblgcnNrmaCrtrioDTO(PgimOblgcnNrmaCrtrioDTO);
		}
		
		return lPgimOblgcnNrmtvaHchocDTO;		
	}
	
	@Transactional(readOnly = false)
	@Override
	public PgimCriterioSprvsionDTO actualizarObligacFiscalizablePorCriterioSuperv(PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO, 
			AuditoriaDTO auditoriaDTO) throws Exception {
		
		List<PgimOblgcnNrmaCrtrioDTO> lPgimOblgcnNrmaCrtrioDTO = pgimCriterioSprvsionDTO.getListaObligacFiscalizable();
		
		List<PgimHechoConstatado> lPgimHechoConstatadoNuevos = new ArrayList<PgimHechoConstatado>(); 
		
		// Filtramos solo las obligaciones con acciones de nuestro interés: agregar o crear, eliminar 
		lPgimOblgcnNrmaCrtrioDTO = lPgimOblgcnNrmaCrtrioDTO.stream().filter(elemento -> {
			return (elemento.getDescAccionActualiza().equals(ETipoAccionCrud.AGREGAR.toString()) 
					|| elemento.getDescAccionActualiza().equals(ETipoAccionCrud.CREAR.toString())
					|| elemento.getDescAccionActualiza().equals(ETipoAccionCrud.ELIMINAR.toString())
					);
		}).collect(Collectors.toList()); 
		
		if (lPgimOblgcnNrmaCrtrioDTO.size() > 0 ) {
			//Obtenemos los hechos constatados vigentes del criterio de fiscalización, ya sea del fiscalizador ó de los especialistas técnico/legal  
			List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTO = this.listarHechosConstatadosPorCriterioMatriz(pgimCriterioSprvsionDTO.getIdCriterioSprvsion(), 
					pgimCriterioSprvsionDTO.getDescIdRolProceso());	
			
			// Sacamos una copia de los hechos verificados que serán actualizados para guardar el histórico
			for (PgimHechoConstatadoDTO pgimHechoConstatadoDTO : lPgimHechoConstatadoDTO) {		
				
				PgimHechoConstatado pgimHechoConstatadoNuevo = this.copiarHC(pgimHechoConstatadoDTO.getIdHechoConstatado(), 
						pgimCriterioSprvsionDTO.getDescIdInstanciaPasoActual(), auditoriaDTO);
				
				lPgimHechoConstatadoNuevos.add(pgimHechoConstatadoNuevo);				
			}			
		}		
		
		for (PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTO : lPgimOblgcnNrmaCrtrioDTO) {
			
			if(pgimOblgcnNrmaCrtrioDTO.getDescAccionActualiza().equals(ETipoAccionCrud.AGREGAR.toString()) || pgimOblgcnNrmaCrtrioDTO.getDescAccionActualiza().equals(ETipoAccionCrud.CREAR.toString())) {
				
				// Para cada hecho constatado del criterio de fiscalización añadimos la nueva obligación fiscalizable
				for (PgimHechoConstatado pgimHechoConstatado : lPgimHechoConstatadoNuevos) {
					
					PgimOblgcnNrmtvaHchoc oblgcnNrmtvaHchoc = new PgimOblgcnNrmtvaHchoc();

					oblgcnNrmtvaHchoc.setPgimHechoConstatado(new PgimHechoConstatado());
					oblgcnNrmtvaHchoc.getPgimHechoConstatado().setIdHechoConstatado(pgimHechoConstatado.getIdHechoConstatado());

					oblgcnNrmtvaHchoc.setPgimOblgcnNrmaCrtrio(new PgimOblgcnNrmaCrtrio());
					oblgcnNrmtvaHchoc.getPgimOblgcnNrmaCrtrio().setIdOblgcnNrmaCrtrio(pgimOblgcnNrmaCrtrioDTO.getIdOblgcnNrmaCrtrio());

					oblgcnNrmtvaHchoc.setEsAplica("0");

					oblgcnNrmtvaHchoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					oblgcnNrmtvaHchoc.setFeCreacion(auditoriaDTO.getFecha());
					oblgcnNrmtvaHchoc.setUsCreacion(auditoriaDTO.getUsername());
					oblgcnNrmtvaHchoc.setIpCreacion(auditoriaDTO.getTerminal());

					this.oblgcnNrmtvaHchocRepository.save(oblgcnNrmtvaHchoc);
					
				}
				
			}else if(pgimOblgcnNrmaCrtrioDTO.getDescAccionActualiza().equals(ETipoAccionCrud.ELIMINAR.toString())) {
			
				// Para cada hecho constatado del criterio de fiscalización eliminamos la obligación fiscalizada indicada
				for (PgimHechoConstatado pgimHechoConstatado : lPgimHechoConstatadoNuevos) {
				
					List<PgimOblgcnNrmtvaHchocDTO> lPgimOblgcnNrmtvaHchocDTO = this.oblgcnNrmtvaHchocRepository.obtenerOblgNormativaPorHechoDTO(pgimHechoConstatado.getIdHechoConstatado());
					
					for (PgimOblgcnNrmtvaHchocDTO obligacionFiscalizada : lPgimOblgcnNrmtvaHchocDTO) {
						
						if(obligacionFiscalizada.getIdOblgcnNrmaCrtrio().equals(pgimOblgcnNrmaCrtrioDTO.getIdOblgcnNrmaCrtrio())){
							// eliminamos
							this.eliminarObligacionNormativaFiscalizada(obligacionFiscalizada.getIdOblgcnNrmtvaHchoc(), auditoriaDTO);							
						}						
					}
				}
		 
			}
		}
		
		return pgimCriterioSprvsionDTO;
		
	}
	
	
	/**
	 * Permite crear una infraccion
	 * 
	 * @param idOblgcnNrmtvaHchoc
	 * @param idInstanciaPaso
	 * @param auditoriaDTO
	 */
	@Transactional(readOnly = false)
	@Override
	public void crearInfraccion(Long idOblgcnNrmtvaHchoc, Long idInstanciaPaso, AuditoriaDTO auditoriaDTO) {

		List<PgimInfraccionDTO> lPgimInfraccionDTO = this.infraccionRepository
				.listarInfraccionXobligacionNorma(idOblgcnNrmtvaHchoc);

		// si ya existe una infracción para el ID de la obligación normativa de HC
		// (idOblgcnNrmtvaHchoc), ya no se crea la infracción
		if (lPgimInfraccionDTO.size() > 0)
			return;
		else {

			PgimInfraccion pgimInfraccion = new PgimInfraccion();
			pgimInfraccion.setPgimOblgcnNrmtvaHchoc(new PgimOblgcnNrmtvaHchoc());
			pgimInfraccion.getPgimOblgcnNrmtvaHchoc().setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
			// Por defecto se incluye en el PAS
			pgimInfraccion.setFlIncluirEnPas("1");
			// Por defecto esta vigente
			pgimInfraccion.setFlVigente("1");
			//Registro de la instancia de paso
			pgimInfraccion.setPgimInstanciaPaso(new PgimInstanciaPaso());
            pgimInfraccion.getPgimInstanciaPaso().setIdInstanciaPaso(idInstanciaPaso);
            
			pgimInfraccion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimInfraccion.setFeCreacion(auditoriaDTO.getFecha());
			pgimInfraccion.setUsCreacion(auditoriaDTO.getUsername());
			pgimInfraccion.setIpCreacion(auditoriaDTO.getTerminal());

			this.infraccionRepository.save(pgimInfraccion);
		}
	}

	/**
	 * Permite eliminar lógicamente una infracción, a partir de la obligación
	 * normativa.
	 * 
	 * @param idOblgcnNrmtvaHchoc
	 */
	@Transactional(readOnly = false)
	@Override
	public void eliminarInfraccion(Long idOblgcnNrmtvaHchoc, AuditoriaDTO auditoriaDTO) {

		List<PgimInfraccionDTO> lPgimInfraccionDTO = this.infraccionRepository
				.listarInfraccionXobligacionNorma(idOblgcnNrmtvaHchoc);

		for (PgimInfraccionDTO pgimInfraccionDTO : lPgimInfraccionDTO) {
			PgimInfraccion pgimInfraccionActualizar = this.infraccionRepository
					.findById(pgimInfraccionDTO.getIdInfraccion()).orElse(null);
			pgimInfraccionActualizar.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimInfraccionActualizar.setFeActualizacion(auditoriaDTO.getFecha());
			pgimInfraccionActualizar.setUsActualizacion(auditoriaDTO.getUsername());
			pgimInfraccionActualizar.setIpActualizacion(auditoriaDTO.getTerminal());
			
			this.infraccionRepository.save(pgimInfraccionActualizar);
			
			//Eliminamos registros de contratistas responsables (PGIM_TD_INFRACCION_CONTRA), de tenerlo
			List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO =  this.infraccionContraRepository.
					listarContratistaByIdInfraccion(pgimInfraccionDTO.getIdInfraccion());
			
			for (PgimInfraccionContraDTO pgimInfraccionContraDTO : lstPgimInfraccionContraDTO) {
				PgimInfraccionContra pgimInfraccionContraActualizar = this.infraccionContraRepository
						.findById(pgimInfraccionContraDTO.getIdInfraccionContra()).orElse(null);
				pgimInfraccionContraActualizar.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				pgimInfraccionContraActualizar.setFeActualizacion(auditoriaDTO.getFecha());
				pgimInfraccionContraActualizar.setUsActualizacion(auditoriaDTO.getUsername());
				pgimInfraccionContraActualizar.setIpActualizacion(auditoriaDTO.getTerminal());
				
				this.infraccionContraRepository.save(pgimInfraccionContraActualizar);
			}
		}

	}

	/**
	 * Permite obtener un objeto DTO de una infracción de acuerdo con su
	 * identificador interno.
	 * 
	 * @param idInfraccion
	 * @return
	 */
	@Override
	public PgimInfraccionDTO obtenerInfraccionDtoPorId(Long idInfraccion) {

		PgimInfraccionDTO infraccionDTO = this.infraccionRepository.obtenerInfraccionPorId(idInfraccion);

		if (infraccionDTO != null && infraccionDTO.getDescIdHechoConstatado() != null) {
			List<PgimHechoConstatadoDTO> lhcr = this.hechoConstatadoRepository
					.listarHechosConstatadosPorHechoConstatadoRmplznte(infraccionDTO.getDescIdHechoConstatado());
			if (lhcr.size() > 0) {
				infraccionDTO.setDescIdHechoConstatadoReemplazado(lhcr.get(0).getIdHechoConstatado());
			}
		}

		return infraccionDTO;
	}

	@Override
	public Page<PgimInfraccionAuxDTO> listarInfraccionesSupervisionPaginado(PgimInfraccionAuxDTO pgimInfraccionAuxDTOFiltro, Pageable paginador) {
		
		Page<PgimInfraccionAuxDTO> pPgimInfraccionAuxDTO = null;
		Long idFaseSeleccionada = pgimInfraccionAuxDTOFiltro.getIdFaseProceso();
		Long idFaseMaxima = 0L;
		
		PgimSupervision pgimSupervision = this.supervisionRepository.findById(pgimInfraccionAuxDTOFiltro.getIdSupervision()).orElse(null);
		List<PgimFaseProcesoDTO> lstPgimFaseProcesoDTO = this.flujoTrabajoService.obtenerFasesInstanciaProceso(pgimSupervision.getPgimInstanciaProces().getIdInstanciaProceso());
		if(lstPgimFaseProcesoDTO.size() > 0) idFaseMaxima = lstPgimFaseProcesoDTO.get(lstPgimFaseProcesoDTO.size()-1).getIdFaseProceso();
		
		if(idFaseSeleccionada < pgimInfraccionAuxDTOFiltro.getDescIdFaseActual() || idFaseSeleccionada < idFaseMaxima ) {
			pPgimInfraccionAuxDTO = this.infraccionAuxRepository.listarInfraccionPorIdSupervisionYFase(pgimInfraccionAuxDTOFiltro.getIdSupervision(), 
				pgimInfraccionAuxDTOFiltro.getIdFaseProceso(), ConstantesUtil.FL_IND_NO, paginador);
		
		}else {
			pPgimInfraccionAuxDTO = this.infraccionAuxRepository.listarInfraccionPorIdSupervisionYFase(pgimInfraccionAuxDTOFiltro.getIdSupervision(), 
					pgimInfraccionAuxDTOFiltro.getIdFaseProceso(), ConstantesUtil.FL_IND_SI, paginador); 
		}
		
		return pPgimInfraccionAuxDTO;
	}

	@Override
	public Page<PgimInfraccionAuxDTO> listarInfraccionesPasPaginado(PgimInfraccionAuxDTO pgimInfraccionAuxDTOFiltro, Pageable paginador) {

		return this.infraccionAuxRepository.listarInfraccionPorIdPasYFase(pgimInfraccionAuxDTOFiltro.getIdPas(), 
				null, ConstantesUtil.FL_IND_SI, paginador);
	}

	@Override
	public List<PgimInfraccionAuxDTO> listarInfraccionPorIdSupervision(Long idSupervision) {
		return this.infraccionAuxRepository.listarInfraccionPorIdSupervision(idSupervision);
	}

	@Override
	public List<PgimInfraccionAuxDTO> listarInfraccionPorIdPas(Long idPas) {
		return this.infraccionAuxRepository.listarInfraccionPorIdPas(idPas);
	}

	/**
	 * Permite obtener una infracción de acuerdo con su identificador interno.
	 * 
	 * @param idInfraccion
	 * @return
	 */
	@Override
	public PgimInfraccion getInfraccionByidInfraccion(Long idInfraccion) {
		return this.infraccionRepository.findById(idInfraccion).orElse(null);
	}

	/**
	 * Permite modificar los datos de una infracción.
	 * 
	 * @param pgimInfraccionDTO
	 * @param pgimInfraccionActual
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public PgimInfraccionDTO modificarInfraccion(@Valid PgimInfraccionDTO pgimInfraccionDTO,
			PgimInfraccion pgimInfraccionActual, AuditoriaDTO auditoriaDTO) throws Exception {
		
		PgimInfraccionDTO pgimInfraccionDTOModificado = null;
		PgimPasoProceso pasoProcesoDestinoNuevo = null;
		String usuarioActualizador = (pgimInfraccionActual.getUsActualizacion() != null) ? pgimInfraccionActual.getUsActualizacion() : pgimInfraccionActual.getUsCreacion(); 
		
		if (pgimInfraccionDTO.getIdInstanciaPaso() != null) {
			PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(pgimInfraccionDTO.getIdInstanciaPaso()).orElse(null);				
			pasoProcesoDestinoNuevo = pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino();
		} else {
			throw new PgimException(TipoResultado.ERROR, "No se encontró el ID de la instancia de paso actual, para la modificación de la infracción.");
		}		

		//Si es el mismo paso y el mismo usuario se modifica la infracción actual; caso contrario, copiar la infracción y poner la anterior como no vigente
		
		if (pasoProcesoDestinoNuevo.getIdPasoProceso().equals(pgimInfraccionActual.getPgimInstanciaPaso().getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso())
			&& auditoriaDTO.getUsername().equals(usuarioActualizador)
			) {

			pgimInfraccionActual.setFlIncluirEnPas(pgimInfraccionDTO.getFlIncluirEnPas());
			pgimInfraccionActual.setDeSustento(pgimInfraccionDTO.getDeSustento());
	
			if (pgimInfraccionDTO.getFlVigente().equals("1")) {
				pgimInfraccionActual.setMoMultaUit(pgimInfraccionDTO.getMoMultaUit());
				pgimInfraccionActual.setFeComisionDeteccion(pgimInfraccionDTO.getFeComisionDeteccion());
				pgimInfraccionActual.setDeSustento(pgimInfraccionDTO.getDeSustento());
			}
			
			//Registro de la instancia de paso
			pgimInfraccionActual.setPgimInstanciaPaso(new PgimInstanciaPaso());
	        pgimInfraccionActual.getPgimInstanciaPaso().setIdInstanciaPaso(pgimInfraccionDTO.getIdInstanciaPaso());
	
			pgimInfraccionActual.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimInfraccionActual.setFeActualizacion(auditoriaDTO.getFecha());
			pgimInfraccionActual.setUsActualizacion(auditoriaDTO.getUsername());
			pgimInfraccionActual.setIpActualizacion(auditoriaDTO.getTerminal());
	
			PgimInfraccion pgimInfraccion = this.infraccionRepository.save(pgimInfraccionActual);
	
			pgimInfraccionDTOModificado = this.infraccionRepository.obtenerInfraccionPorId(pgimInfraccion.getIdInfraccion());
			
		}else {
			
			PgimInfraccionAuxDTO pgimInfraccionAuxDTO = new PgimInfraccionAuxDTO();
			pgimInfraccionAuxDTO.setIdInfraccion(pgimInfraccionDTO.getIdInfraccion());
			pgimInfraccionAuxDTO.setIdOblgcnNrmtvaHchoc(pgimInfraccionDTO.getIdOblgcnNrmtvaHchoc());
			pgimInfraccionAuxDTO.setFlIncluirEnPas(pgimInfraccionDTO.getFlIncluirEnPas());
			pgimInfraccionAuxDTO.setDeSustento(pgimInfraccionDTO.getDeSustento());
			pgimInfraccionAuxDTO.setFeComisionDeteccion(pgimInfraccionDTO.getFeComisionDeteccion());
			pgimInfraccionAuxDTO.setMoMultaUit(pgimInfraccionDTO.getMoMultaUit());			
			
			Long idPas = (pgimInfraccionActual.getPgimPas() != null) ? pgimInfraccionActual.getPgimPas().getIdPas()	: null;
			
			pgimInfraccionDTOModificado = this.infraccionService.realizarCopiaInfraccion(pgimInfraccionAuxDTO, 
					pgimInfraccionDTO.getIdInstanciaPaso(), idPas, true, auditoriaDTO);
		}

		return pgimInfraccionDTOModificado;
	}

	@Override
	public List<PgimMatrizSupervisionAuxDTO> listarMatrizSupervisionDescarga(final Long idSupervision,
			final Long codigoCumple, final String tipoMatriz) throws Exception {

		return this.matrizSupervisionAuxRepository.listarMatrizSupervisionDescarga(idSupervision, codigoCumple,
				tipoMatriz);
	}

	@Override
	public List<PgimCriterioSprvsionDTO> obtenerMatrizSupervision(final Long idSupervision) throws Exception {
		return this.criterioSprvsionRepository.obtenerMatrizSupervision(idSupervision);
	}

	@Override
	public List<PgimHechoConstatadoDTO> obtenerHechosConstatadosPorTipoCumplimiento(Long idSupervision,
			Long idTipoCumplimiento) {
		return this.hechoConstatadoRepository.obtenerHechosConstatadosPorTipoCumplimiento(idSupervision,
				idTipoCumplimiento);
	}
	
	@Override
	public PgimCriterioSprvsion findCriterioSprvsionById(Long idCriterioSprvsion) {
		return this.criterioSprvsionRepository.findById(idCriterioSprvsion).orElse(null);
	}
	
	@Transactional(readOnly = false)
    @Override
	public void eliminarCriterioFiscalizacion(PgimCriterioSprvsion pgimCriterioSprvsionActual, AuditoriaDTO auditoriaDTO) {
		pgimCriterioSprvsionActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimCriterioSprvsionActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimCriterioSprvsionActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimCriterioSprvsionActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.criterioSprvsionRepository.save(pgimCriterioSprvsionActual);
        
        // Eliminamos los hechos verificados e infracciones relacionados.
        List<PgimHechoConstatadoDTO> lstPgimHechoConstatadoDTO = this.hechoConstatadoRepository.obtenerLstHechosConstatadosDTO(
        		pgimCriterioSprvsionActual.getPgimSupervision().getIdSupervision(), pgimCriterioSprvsionActual.getIdCriterioSprvsion());

				for (PgimHechoConstatadoDTO pgimHechoConstatadoDTO : lstPgimHechoConstatadoDTO) {
					
					// Validar que el HC no sea un HC reemplazante
					List<PgimHechoConstatadoDTO> listaValida = this.hechoConstatadoRepository
							.listarHechosConstatadosPorHechoConstatadoRmplznte(pgimHechoConstatadoDTO.getIdHechoConstatado());

					if (listaValida.size() > 0) {
						throw new PgimException("error",
								"El criterio no puede ser eliminado debido a que tiene registrado al menos un hecho verificado que se encuentra vinculado a un hecho verificado por parte de Osinergmin.");
					}

					if(pgimHechoConstatadoDTO.getFlIncluidoActaSupervision().equals(ConstantesUtil.INCLUIDO_ACTA_SUPERVISION_SI)){
						throw new PgimException(TipoResultado.WARNING,"El criterio no puede ser eliminado debido a que tiene registrado al menos un hecho verificado que figura en el Acta de fiscalización.");
					}					
				}
						
        for (PgimHechoConstatadoDTO pgimHechoConstatadoDTO : lstPgimHechoConstatadoDTO) {
        	PgimHechoConstatado pgimHechoConstatado = this.getByidHechoConstatado(pgimHechoConstatadoDTO.getIdHechoConstatado());
        	this.eliminarHC(pgimHechoConstatado, auditoriaDTO); // internamente elimina todos los registros relacionados			
		}
        
	}

	@Override
	@Transactional(readOnly = false)
	public void registroConformidadCriterios(FiltroMatrizSupervisionDTO filtroMatrizSupervisionDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {

			List<PgimMatrizSupervisionAuxDTO>	lPgimMatrizSupervisionAuxDTO = this.matrizSupervisionAuxRepository.
					listarMatrizSupervisionAddConformidad(filtroMatrizSupervisionDTO.getIdSupervision(),
					filtroMatrizSupervisionDTO.getTipoMatriz(), filtroMatrizSupervisionDTO.getCodigoCumple());
			
			Long idTipoComplimiento = this.parametroService.obtenerIdValorParametroDesdeClaveTexto(EValorParametro.CUMPL_SI.toString());

			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByIdSupervision(filtroMatrizSupervisionDTO.getIdSupervision());
			
			PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this.flujoTrabajoService.obtenerInstanciaPasoActualPorIdInstanciaPaso(pgimSupervisionDTO.getDescIdInstanciaPaso());

			Sort sort = Sort.by("deObligacionNormativa");
			sort = sort.ascending();

			for (PgimMatrizSupervisionAuxDTO pgimMatrizSupervisionAuxDTO : lPgimMatrizSupervisionAuxDTO) {

				if(pgimMatrizSupervisionAuxDTO.getCantidadHc() == null){

					List<PgimObligacionNormaAuxDTO> descListaObligaciones = this.obligacionNormaAuxRepository.listarObligacionesNormativasPorCriterioMatrizAllSelecion(pgimMatrizSupervisionAuxDTO.getIdMatrizCriterio(),sort);
	
					PgimHechoConstatadoDTO pgimHechoConstatadoDTO = new PgimHechoConstatadoDTO();
					pgimHechoConstatadoDTO.setIdCriterioSprvsion(pgimMatrizSupervisionAuxDTO.getIdCriterioSprvsion());
					pgimHechoConstatadoDTO.setIdSupervision(pgimMatrizSupervisionAuxDTO.getIdSupervision());
					pgimHechoConstatadoDTO.setIdTipoCumplimiento(idTipoComplimiento);
					pgimHechoConstatadoDTO.setDeHechoConstatadoT(filtroMatrizSupervisionDTO.getDeHechoConstatadoT());
					pgimHechoConstatadoDTO.setDeSustentoT(filtroMatrizSupervisionDTO.getDeSustentoT());
					pgimHechoConstatadoDTO.setDescListaObligaciones(descListaObligaciones);
					pgimHechoConstatadoDTO.setIdInstanciaPaso(pgimInstanciaPasoDTOActual.getIdInstanciaPaso());
	
					this.crearHC(pgimHechoConstatadoDTO, auditoriaDTO);

				}
				
			}

	}
}	
