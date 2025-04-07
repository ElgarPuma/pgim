package pe.gob.osinergmin.pgim.services.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConsumoContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCorrelativoSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCotejoHechoCnsttdoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFiltroDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFiltroItemDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemRecepcionDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemSolBaseDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemSolicitudDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaHchocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSeguimientoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudBaseDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionAgolDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTdrPlantillaDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimCmineroSprvsion;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteMinero;
import pe.gob.osinergmin.pgim.models.entity.PgimConsumoContra;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimCorrelativoSuperv;
import pe.gob.osinergmin.pgim.models.entity.PgimCriterioSprvsion;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimEqpInstanciaPro;
import pe.gob.osinergmin.pgim.models.entity.PgimHechoConstatado;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimInstrmntoXSuperv;
import pe.gob.osinergmin.pgim.models.entity.PgimInvolucradoSuperv;
import pe.gob.osinergmin.pgim.models.entity.PgimItemConsumo;
import pe.gob.osinergmin.pgim.models.entity.PgimItemLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimItemProgramaSupe;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizCriterio;
import pe.gob.osinergmin.pgim.models.entity.PgimMotivoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmtvaHchoc;
import pe.gob.osinergmin.pgim.models.entity.PgimPasoProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervisionAgol;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository;
import pe.gob.osinergmin.pgim.models.repository.CmineroSprvsionRepository;
import pe.gob.osinergmin.pgim.models.repository.ConsumoContraRepository;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.CorrelativoSupervRepository;
import pe.gob.osinergmin.pgim.models.repository.CotejoHechoCnsttdoRepository;
import pe.gob.osinergmin.pgim.models.repository.CriterioSprvsionRepository;
import pe.gob.osinergmin.pgim.models.repository.DocumentoRelacionRepository;
import pe.gob.osinergmin.pgim.models.repository.DocumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.FichaObservacionRepository;
import pe.gob.osinergmin.pgim.models.repository.FichaRevisionRepository;
import pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.InvolucradoSupervRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemConsumoContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemLiquidacionRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemProgramaSupeRepository;
import pe.gob.osinergmin.pgim.models.repository.OblgcnNrmtvaHchocRepository;
import pe.gob.osinergmin.pgim.models.repository.ObligacionNormaAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonaRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSeguimientoAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmFactorRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionSubcatRepository;
import pe.gob.osinergmin.pgim.models.repository.SolicitudBaseDocRepository;
import pe.gob.osinergmin.pgim.models.repository.SubTipoSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervInstrumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionAgolRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.TdrPlantillaRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.AntecedenteSupervService;
import pe.gob.osinergmin.pgim.services.ConfiguracionBaseService;
import pe.gob.osinergmin.pgim.services.ContratoService;
import pe.gob.osinergmin.pgim.services.DocumentoRequeridoService;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.HechoConstatadoService;
import pe.gob.osinergmin.pgim.services.InfraccionService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.PasService;
import pe.gob.osinergmin.pgim.services.SolicitudBaseDocService;
import pe.gob.osinergmin.pgim.services.SupervInstrumentoService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.services.TarifarioContratoService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstPasoProcesoSupervision;
import pe.gob.osinergmin.pgim.utils.ConstRelacionPasoFiscalizacion;
import pe.gob.osinergmin.pgim.utils.ConstSubCategoriaDocumento;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.ETipoAccionCrud;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Supervision
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class SupervisionServiceImpl implements SupervisionService {

        @Autowired
        private SupervisionRepository supervisionRepository;

        @Autowired
        private SubcategoriaDocRepository subcategoriaDocRepository;

        @Autowired
        private ConsumoContraRepository consumoContraRepository;

        @Autowired
        private InstanciaProcesService instanciaProcesService;

        @Autowired
        private InstanciaProcesRepository instanciaProcesRepository;

        @Autowired
        private FlujoTrabajoService flujoTrabajoService;

        @Autowired
        private CorrelativoSupervRepository correlativoSupervRepository;

        @Autowired
        private HechoConstatadoService hechoConstatadoService;

        @Autowired
        private ContratoService contratoService;

        @Autowired
        private ItemProgramaSupeRepository itemProgramaSupeRepository;

        @Autowired
        private ContratoRepository contratoRepository;

        @Autowired
        private ItemConsumoContratoRepository itemConsumoContratoRepository;

        @Autowired
        private InstanciaPasoRepository instanciaPasoRepository;

        @Autowired
        private RelacionPasoRepository relacionPasoRepository;

        @Autowired
        private PasService pasService;

        @Autowired
        private InfraccionService infraccionService;

        @Autowired
        private PersonaRepository personaRepository;

        @Autowired
        private TarifarioContratoService tarifarioContratoService;

        @Autowired
        private ItemLiquidacionRepository itemLiquidacionRepository;

        @Autowired
        private FichaRevisionRepository fichaRevisionRepository;

        @Autowired
        private DocumentoRequeridoService documentoRequeridoService;

        @Autowired
        private DocumentoService documentoService;

        @Autowired
        private EquipoInstanciaProcesoRepository equipoInstanciaProcesoRepository;

        @Autowired
        private DocumentoRepository documentoRepository;

        @Autowired
        private CotejoHechoCnsttdoRepository cotejoHechoCnsttdoRepository;

        @Autowired
        private RankingRiesgoServiceImpl rankingRiesgoServiceImpl;

        @Autowired
        private RankingUmGrupoRepository rankingUmGrupoRepository;

        @Autowired
        private RankingUmFactorRepository rankingUmFactorRepository;

        @Autowired
        private SupervisionAgolRepository supervisionAgolRepository;

        @Autowired
        private PrgrmSeguimientoAuxRepository prgrmSeguimientoAuxRepository;

        @Autowired
        private AntecedenteSupervService antecedenteSupervService;

        @Autowired
        private UnidadMineraRepository unidadMineraRepository;

        @Autowired
        private InvolucradoSupervRepository involucradoSupervRepository;

        @Autowired
        private TdrPlantillaRepository tdrPlantillaRepository;

        @Autowired
        private PrgrmSupervisionRepository prgrmSupervisionRepository;

        @Autowired
        private ValorParametroRepository valorParametroRepository;

        @Autowired
        private SolicitudBaseDocService solicitudBaseDocService;

        @Autowired
        private PasoProcesoRepository pasoProcesoRepository;

        @Autowired
        private ConfiguracionBaseService configuracionBaseService;

        @Autowired
        private SolicitudBaseDocRepository solicitudBaseDocRepository;

        @Autowired
        private CriterioSprvsionRepository criterioSprvsionRepository;

        @Autowired
        private DocumentoRelacionRepository documentoRelacionRepository;

        @Autowired
        private SupervInstrumentoRepository supervInstrumentoRepository;

        @Autowired
        private SupervInstrumentoService supervInstrumentoService;

        @Autowired
        private HechoConstatadoRepository hechoConstatadoRepository;

        @Autowired
        private OblgcnNrmtvaHchocRepository oblgcnNrmtvaHchocRepository;

        @Autowired
        private HechoConstatadoServiceImpl hechoConstatadoServiceImpl;

        @Autowired
        private RelacionSubcatRepository relacionSubcatRepository;

        @Autowired
        private AgenteSupervisadoRepository agenteSupervisadoRepository;

        @Autowired
        private SubTipoSupervisionRepository subTipoSupervisionRepository;

        @Autowired
        private ObligacionNormaAuxRepository obligacionNormaAuxRepository;

        @Autowired
        private FichaObservacionRepository fichaObservacionRepository;

        @Autowired
        private CmineroSprvsionRepository cmineroSprvsionRepository;

        @Override
        public Page<PgimSupervisionAuxDTO> filtrar(PgimSupervisionDTO filtroSupervisionDTO, Pageable paginador,
                        AuditoriaDTO auditoriaDTO) {

                // Obtenemos permiso "Mi interés" y permiso "listar todas"
                boolean flagPermisoMiInteres = false;
                boolean flagPermisoTodas = false;
                for (String permiso : auditoriaDTO.getAuthorities()) {

                        if (permiso.equals(ConstantesUtil.PERMISO_VER_SUPERVISIONES_MI_INTERES)) {
                                flagPermisoMiInteres = true;
                        }

                        if (permiso.equals(ConstantesUtil.PERMISO_VER_SUPERVISIONES_TODAS)) {
                                flagPermisoTodas = true;
                        }
                }

                String usuarioInteres = "";
                if (filtroSupervisionDTO.getDescFlagMiInteres().equals("1") && flagPermisoMiInteres) {
                        usuarioInteres = auditoriaDTO.getUsername();
                }

                // REVIEW: Para usuarios con tareas enviadas
                if (filtroSupervisionDTO.getDescFlagMisAsignaciones().trim().equals("2")) {
                        filtroSupervisionDTO.setNoUsuarioOrigen(auditoriaDTO.getUsername());
                }

                // Si el usuario no tiene permiso para ver los registros de otros usaurios, o no
                // tiene permiso para ver los registros de su interés
                // se le setea el mismo usaurio para los filtros
                if ((((filtroSupervisionDTO.getDescUsuarioAsignado() == null
                                || filtroSupervisionDTO.getDescUsuarioAsignado().trim().equals(""))
                                && (filtroSupervisionDTO.getDescPersonaDestino() == null
                                                || filtroSupervisionDTO.getDescPersonaDestino().trim().equals("")))
                                || (filtroSupervisionDTO.getDescUsuarioAsignado() != null
                                                && !filtroSupervisionDTO.getDescUsuarioAsignado().trim()
                                                                .equals(auditoriaDTO.getUsername())))
                                && ((filtroSupervisionDTO.getDescFlagMiInteres().equals("1") && !flagPermisoMiInteres)
                                                || (filtroSupervisionDTO.getDescFlagMiInteres().equals("0")
                                                                && !flagPermisoTodas))) {

                        filtroSupervisionDTO.setDescUsuarioAsignado(auditoriaDTO.getUsername());

                } else if ((filtroSupervisionDTO.getDescPersonaDestino() != null
                                && !filtroSupervisionDTO.getDescPersonaDestino().trim().equals(""))
                                && ((filtroSupervisionDTO.getDescFlagMiInteres().equals("1") && !flagPermisoMiInteres)
                                                || (filtroSupervisionDTO.getDescFlagMiInteres().equals("0")
                                                                && !flagPermisoTodas))) {

                        filtroSupervisionDTO.setDescUsuarioAsignado(auditoriaDTO.getUsername());
                        filtroSupervisionDTO.setDescPersonaDestino("");
                }

                Page<PgimSupervisionAuxDTO> pPgimSupervisionDTO = this.supervisionRepository.listarSupervisiones(
                                filtroSupervisionDTO.getDescCoUnidadMinera(),
                                filtroSupervisionDTO.getDescNoUnidadMinera(),
                                filtroSupervisionDTO.getDescCoDocumentoIdentidad(),
                                filtroSupervisionDTO.getDescNoRazonSocial(),
                                filtroSupervisionDTO.getDescIdEspecialidad(),
                                filtroSupervisionDTO.getDescIdTipoSupervision(),
                                filtroSupervisionDTO.getDescIdDivisionSupervisora(),
                                filtroSupervisionDTO.getFlPropia(),
                                filtroSupervisionDTO.getCoSupervision(),
                                filtroSupervisionDTO.getDescNuExpedienteSiged(),
                                filtroSupervisionDTO.getDescIdFaseProceso(),
                                filtroSupervisionDTO.getDescIdPasoProceso(),
                                filtroSupervisionDTO.getDescPersonaDestino(),
                                filtroSupervisionDTO.getDescNoResponsable(),
                                filtroSupervisionDTO.getNoUsuarioOrigen(),
                                filtroSupervisionDTO.getDescUsuarioAsignado(),
                                usuarioInteres,
                                filtroSupervisionDTO.getDescFlagMisAsignaciones(),
                                filtroSupervisionDTO.getTextoBusqueda(),
                                paginador);

                return pPgimSupervisionDTO;
        }

        @Override
        public List<PgimSupervisionAuxDTO> listarFiscalizacionesParaCalendario(Date inicio, Date fin) {

                List<PgimSupervisionAuxDTO> pPgimSupervisionDTO = this.supervisionRepository
                                .listarFiscalizacionesParaCalendario(inicio, fin);

                return pPgimSupervisionDTO;
        }

        @Override
        public List<PgimSupervisionDTO> listarPorPalabraClave(String palabra) {
                List<PgimSupervisionDTO> lPgimSupervisionDTO = this.supervisionRepository
                                .listarPorPalabraClave(palabra);

                return lPgimSupervisionDTO;
        }

        @Override
        public List<PgimSupervisionDTO> listarPorPalabraClaveNuExpedienteSiged(String palabra) {
                List<PgimSupervisionDTO> lPgimSupervisionDTO = this.supervisionRepository
                                .listarPorPalabraClaveNuExpedienteSiged(palabra);

                return lPgimSupervisionDTO;
        }

        @Override
        public PgimSupervisionDTO obtenerSupervisionByIdSupervision(Long idSupervision) {
                return supervisionRepository.obtenerSupervisionByIdSupervision(idSupervision);
        }

        @Override
        public PgimSupervisionDTO obtenerSupervisionPorId(Long idSupervision) throws Exception {
                PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository
                                .obtenerSupervisionPorId(idSupervision);

                return pgimSupervisionDTO;

        }

        @Override
        public PgimSupervisionDTO obtenerSupervisionByidInstanciaProceso(Long idInstanciaProceso) {
                return this.supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
        }

        @Override
        public PgimSupervisionDTO obtenerSupervisionPorIdSupervision(Long idSupervision) {
                return this.supervisionRepository.obtenerSupervision(idSupervision);
        }

        @Transactional(readOnly = false)
        @Override
        public PgimSupervisionDTO asignarSupervision(PgimSupervisionDTO pgimSupervisionDTO, AuditoriaDTO auditoriaDTO)
                        throws Exception {

                // Se crea la supervisión
                PgimSupervisionDTO pgimSupervisionDTOCreado = this.crearSupervision(pgimSupervisionDTO, auditoriaDTO);

                // Se asegura la instancia del proceso
                List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService
                                .asegurarInstanciasProceso(ConstantesUtil.PARAM_PROCESO_SUPERVISION,
                                                pgimSupervisionDTOCreado.getIdSupervision(), auditoriaDTO);

                PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

                PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository
                                .findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

                // Se actualiza la instancia del proceso en el registro de la supervión
                this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);

                // Se crea la asignación
                pgimSupervisionDTO.setIdSupervision(pgimSupervisionDTOCreado.getIdSupervision());

                PgimInstanciaPasoDTO pgimInstanciaPasoDTO = new PgimInstanciaPasoDTO();
                pgimInstanciaPasoDTO.setIdRelacionPaso(pgimSupervisionDTO.getDescIdRelacionPaso());
                pgimInstanciaPasoDTO.setDescIdPersonalOsiDestino(pgimSupervisionDTO.getDescIdPersonalOsi());
                pgimInstanciaPasoDTO.setDeMensaje(pgimSupervisionDTO.getDescDeMensaje());

                PgimInstanciaPasoDTO pgimInstanciaPasoDTOCreada = this.flujoTrabajoService.crearInstanciaPasoInicial(
                                pgimInstanciaProcesActual, pgimInstanciaPasoDTO, auditoriaDTO);
                pgimSupervisionDTOCreado.setDescIdInstanciaPaso(pgimInstanciaPasoDTOCreada.getIdInstanciaPaso());

                // Abogado/a y Coordinador/a de empresa supervisora por defecto
                // Realizar el registro de personales del contrato que fungen el rol de Abogado/a y Coordinador/a de ES para la fiscalización no propia
                if(pgimSupervisionDTO.getFlPropia().equals("0")){
                        this.flujoTrabajoService.asegurarPersonalContratoInstanciaProceso(pgimInstanciaProcesActual.getIdInstanciaProceso(), pgimSupervisionDTO.getDescIdContrato(), pgimSupervisionDTO.getIdProgramaSupervision(), auditoriaDTO);
                }

                if (pgimSupervisionDTO.getDescIdTipoSupervision().equals(this.valorParametroRepository
                                .obtenerIdValorParametro(EValorParametro.SUPER_FSCLZCION_PRGRMDA.toString()))) {

                        PgimUnidadMinera pgimUnidadMinera = this.unidadMineraRepository
                                        .findById(pgimSupervisionDTO.getIdUnidadMinera()).orElse(null);

                        if (pgimUnidadMinera == null) {
                                throw new PgimException(TipoResultado.WARNING, // STORY: PGIM-6667: Mejora de la gestión
                                                                               // de mensajes para el usuario
                                                String.format("No se ha encontrado ninguna unidad fiscalizada asociada a la supervisión '%s'",
                                                                pgimSupervisionDTO.getCoSupervision()));
                        }

                        if (pgimUnidadMinera.getFlRegistraRiesgos().equals("1")) {
                                this.rankingRiesgoServiceImpl.crearRankingRiesgoSupervision(pgimSupervisionDTOCreado,
                                                pgimUnidadMinera,
                                                auditoriaDTO);
                        }

                }

                return pgimSupervisionDTOCreado;
        }

        @Transactional(readOnly = false)
        private PgimSupervisionDTO crearSupervision(PgimSupervisionDTO pgimSupervisionDTO, AuditoriaDTO auditoriaDTO) {

                PgimSupervision pgimSupervision = new PgimSupervision();

                PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO = this.prgrmSupervisionRepository
                                .obtenerPrograma(pgimSupervisionDTO.getIdProgramaSupervision());

                // obtener filtros para obtener plantillas de TDR y plantillas de solicutudes de
                // documentos
                Long idTipoCfgBaseTRD = this.valorParametroRepository
                                .obtenerIdValorParametro(EValorParametro.TDR_BASE.toString());
                Long idTipoCfgBaseDocBaseSol = this.valorParametroRepository
                                .obtenerIdValorParametro(EValorParametro.DOCUMENTOS_BASE_SOLICITUD.toString());

                PgimUnidadMinera umFiltroRegla = this.unidadMineraRepository
                                .findById(pgimSupervisionDTO.getIdUnidadMinera()).orElse(null);

                PgimReglaBaseDTO pgimReglaBaseDTOFiltro = new PgimReglaBaseDTO();
                pgimReglaBaseDTOFiltro.setIdDivisionSupervisora(pgimPrgrmSupervisionDTO.getIdDivisionSupervisora());
                pgimReglaBaseDTOFiltro.setIdMotivoSupervision(pgimSupervisionDTO.getIdMotivoSupervision());
                pgimReglaBaseDTOFiltro.setIdSubtipoSupervision(pgimSupervisionDTO.getIdSubtipoSupervision());
                pgimReglaBaseDTOFiltro.setFlPropia(pgimSupervisionDTO.getFlPropia());
                pgimReglaBaseDTOFiltro.setFlConPiques(umFiltroRegla.getFlConPiques());
                if (umFiltroRegla.getMetodoMinado() != null) {
                        pgimReglaBaseDTOFiltro.setIdMetodoMinado(umFiltroRegla.getMetodoMinado().getIdValorParametro());
                }

                // Obtener plantilla de TRD
                pgimReglaBaseDTOFiltro.setDescIdTipoConfiguracionBase(idTipoCfgBaseTRD);
                PgimReglaBaseDTO pgimReglaBaseDTOTdr = configuracionBaseService
                                .obtenerReglaPorSupervision(pgimReglaBaseDTOFiltro);

                PgimTdrPlantillaDTO pgimTdrPlantillaDTO = null;
                if (pgimReglaBaseDTOTdr != null) {
                        pgimTdrPlantillaDTO = this.tdrPlantillaRepository
                                        .obtenerTdrPlanilla(pgimReglaBaseDTOTdr.getIdConfiguracionBase());
                }

                if (pgimTdrPlantillaDTO != null) {
                        pgimSupervision.setDeTdrObjetivoTexto(pgimTdrPlantillaDTO.getDeTdrObjetivoTexto());
                        pgimSupervision.setDeTdrAlcanceTexto(pgimTdrPlantillaDTO.getDeTdrAlcanceTexto());
                        pgimSupervision.setDeTdrInformeSupervTexto(pgimTdrPlantillaDTO.getDeTdrInformeSupervTexto());
                        pgimSupervision.setDeTdrMetodologiaTexto(pgimTdrPlantillaDTO.getDeTdrMetodologiaTexto());
                        pgimSupervision.setDeTdrHonorariosProf(pgimTdrPlantillaDTO.getDeTdrHonorariosProf());
                } else {
                        pgimSupervision.setDeTdrObjetivoTexto("Sin plantilla");
                        pgimSupervision.setDeTdrAlcanceTexto("Sin plantilla");
                        pgimSupervision.setDeTdrInformeSupervTexto("Sin plantilla");
                        pgimSupervision.setDeTdrMetodologiaTexto("Sin plantilla");
                        pgimSupervision.setDeTdrHonorariosProf("Sin plantilla");
                }
                // fin plantilla TRD

                PgimPrgrmSupervision pgimPrgrmSupervision = new PgimPrgrmSupervision();
                pgimPrgrmSupervision.setIdProgramaSupervision(pgimSupervisionDTO.getIdProgramaSupervision());
                pgimSupervision.setPgimPrgrmSupervision(pgimPrgrmSupervision);

                PgimSubtipoSupervision pgimSubtipoSupervision = new PgimSubtipoSupervision();
                pgimSubtipoSupervision.setIdSubtipoSupervision(pgimSupervisionDTO.getIdSubtipoSupervision());
                pgimSupervision.setPgimSubtipoSupervision(pgimSubtipoSupervision);

                pgimSupervision.setPgimMotivoSupervision(new PgimMotivoSupervision());
                pgimSupervision.getPgimMotivoSupervision()
                                .setIdMotivoSupervision(pgimSupervisionDTO.getIdMotivoSupervision());

                PgimUnidadMinera pgimUnidadMinera = new PgimUnidadMinera();
                pgimUnidadMinera.setIdUnidadMinera(pgimSupervisionDTO.getIdUnidadMinera());
                pgimSupervision.setPgimUnidadMinera(pgimUnidadMinera);

                pgimSupervision.setFlPropia(pgimSupervisionDTO.getFlPropia());
                pgimSupervision.setFeInicioSupervision(pgimSupervisionDTO.getFeInicioSupervision());
                pgimSupervision.setFeFinSupervision(pgimSupervisionDTO.getFeFinSupervision());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                pgimSupervision.setCoSupervision(obtenerSiguienteCorrelativo(sdf.format(new Date()), auditoriaDTO));

                pgimSupervision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimSupervision.setFeCreacion(auditoriaDTO.getFecha());
                pgimSupervision.setUsCreacion(auditoriaDTO.getUsername());
                pgimSupervision.setIpCreacion(auditoriaDTO.getTerminal());
                pgimSupervision.setFlFeEnvio(pgimSupervisionDTO.getFlFeEnvio());

                PgimSupervision pgimSupervisionCreado = this.supervisionRepository.save(pgimSupervision);

                if(pgimSupervisionDTO.getDescIdEspecialidad() == ConstantesUtil.PARAM_ESPECIALIDAD_PLANTA_BENEFICIO && pgimSupervisionDTO.getDescListPgimCmineroSprvsionDTO() != null){
                        for(PgimCmineroSprvsionDTO pgimCmineroSprvsionDTO : pgimSupervisionDTO.getDescListPgimCmineroSprvsionDTO()){
                                PgimCmineroSprvsion pgimCmineroSprvsion = new PgimCmineroSprvsion();

                                PgimSupervision pgimSupervisionCm = new PgimSupervision();
                                pgimSupervisionCm.setIdSupervision(pgimSupervisionCreado.getIdSupervision());
                                pgimCmineroSprvsion.setPgimSupervision(pgimSupervisionCm);

                                PgimComponenteMinero pgimComponenteMinero = new PgimComponenteMinero();
                                pgimComponenteMinero.setIdComponenteMinero(pgimCmineroSprvsionDTO.getIdComponenteMinero());
                                pgimCmineroSprvsion.setPgimComponenteMinero(pgimComponenteMinero);

                                pgimCmineroSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                                pgimCmineroSprvsion.setFeCreacion(auditoriaDTO.getFecha());
                                pgimCmineroSprvsion.setUsCreacion(auditoriaDTO.getUsername());
                                pgimCmineroSprvsion.setIpCreacion(auditoriaDTO.getTerminal());

                                PgimCmineroSprvsion pgimCmineroSprvsionCreado = this.cmineroSprvsionRepository.save(pgimCmineroSprvsion);
                                
                        }
                }

                // obtener plantilla de solicitud de docs
                pgimReglaBaseDTOFiltro.setDescIdTipoConfiguracionBase(idTipoCfgBaseDocBaseSol);
                PgimReglaBaseDTO pgimReglaBaseDTODocBaseSol = configuracionBaseService
                                .obtenerReglaPorSupervision(pgimReglaBaseDTOFiltro);

                PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO = null;
                if (pgimReglaBaseDTODocBaseSol != null) {
                        pgimSolicitudBaseDocDTO = solicitudBaseDocRepository
                                        .obtenerSolicitudbaseDocByIdConfiguracionBase(
                                                        pgimReglaBaseDTODocBaseSol.getIdConfiguracionBase());
                }

                List<PgimItemSolBaseDocDTO> lPgimItemSolBaseDocDTO = new ArrayList<PgimItemSolBaseDocDTO>();

                if (pgimSolicitudBaseDocDTO != null) {

                        lPgimItemSolBaseDocDTO = solicitudBaseDocService
                                        .listarItemsSolicitudBaseDocPorIdSolicitudBaseDoc(
                                                        pgimSolicitudBaseDocDTO.getIdSolicitudBaseDoc());

                        PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOCreado = new PgimItemSolicitudDocDTO();

                        for (PgimItemSolBaseDocDTO pgimItemSolBaseDocDTO : lPgimItemSolBaseDocDTO) {

                                PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO = new PgimItemSolicitudDocDTO();
                                pgimItemSolicitudDocDTO
                                                .setFeSolicitudDocumentacion(pgimSupervisionDTO.getFeFinSupervision());
                                pgimItemSolicitudDocDTO.setDeSolicitudObservacion(
                                                pgimItemSolBaseDocDTO.getDeSolicitudObservacion());

                                // BUG: PGIM-6172: La lista de documentos requeridos sale desordenada
                                pgimItemSolicitudDocDTO.setNuOrden(pgimItemSolBaseDocDTO.getNuOrden().longValue());

                                pgimItemSolicitudDocDTO
                                                .setIdSolicitudDoc(pgimItemSolicitudDocDTOCreado.getIdSolicitudDoc());

                                pgimItemSolicitudDocDTO.setDescIdSupervision(pgimSupervisionCreado.getIdSupervision());

                                pgimItemSolicitudDocDTOCreado = documentoRequeridoService
                                                .crearItemSolicitudDoc(pgimItemSolicitudDocDTO, auditoriaDTO);

                        }
                }

                // fin plantilla de solicitud de docs

                BigDecimal moConsumo = new BigDecimal(0);

                if (pgimSupervisionDTO.getFlPropia().equals("0")) {
                        PgimConsumoContra pgimConsumoContra = new PgimConsumoContra();
                        pgimConsumoContra.setPgimSupervision(pgimSupervisionCreado);

                        PgimContrato pgimContrato = new PgimContrato();
                        pgimContrato.setIdContrato(pgimSupervisionDTO.getDescIdContrato());
                        pgimConsumoContra.setPgimContrato(pgimContrato);

                        PgimConsumoContraDTO pgimConsumoContraDTO = this.contratoService
                                        .calcularCostoSupervision(pgimSupervisionDTO, false);

                        if (pgimConsumoContraDTO == null) {
                                throw new PgimException(TipoResultado.WARNING,
                                                String.format("No se ha podido determinar el consumo del contrato"));
                        }

                        moConsumo = pgimConsumoContraDTO.getMoConsumoContrato();

                        pgimConsumoContra.setMoConsumoContrato(moConsumo);

                        pgimConsumoContra.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimConsumoContra.setFeCreacion(auditoriaDTO.getFecha());
                        pgimConsumoContra.setUsCreacion(auditoriaDTO.getUsername());
                        pgimConsumoContra.setIpCreacion(auditoriaDTO.getTerminal());

                        this.consumoContraRepository.save(pgimConsumoContra);

                        Long cantidadDiasEntre = this.contratoService.diasCalendarioSupervision(pgimSupervisionDTO,
                                        false);
                        PgimCostoUnitarioDTO pgimCostoUnitarioDTO = this.contratoService
                                        .obtenerCostoUnitario(pgimSupervisionDTO, false, cantidadDiasEntre);

                        this.crearItemsConsumoContrato(pgimConsumoContra,
                                        this.valorParametroRepository.obtenerIdValorParametro(
                                                        EValorParametro.ESCON_PRE_CMPRMTDO.toString()),
                                        pgimCostoUnitarioDTO,
                                        BigDecimal.valueOf(1),
                                        auditoriaDTO);
                }

                // Procesamos la relación con el programa:
                PgimItemProgramaSupe pgimItemProgramaSupe = null;

                if (pgimSupervisionDTO.getDescIdTipoSupervision()
                                .equals(this.valorParametroRepository
                                                .obtenerIdValorParametro(
                                                                EValorParametro.SUPER_FSCLZCION_PRGRMDA.toString()))) {

                        pgimItemProgramaSupe = this.itemProgramaSupeRepository
                                        .findById(pgimSupervisionDTO.getDescIdItemProgramaSupe()).orElse(null);
                        pgimItemProgramaSupe.setPgimSupervision(pgimSupervision);

                        pgimItemProgramaSupe.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimItemProgramaSupe.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimItemProgramaSupe.setIpActualizacion(auditoriaDTO.getTerminal());

                        itemProgramaSupeRepository.save(pgimItemProgramaSupe);
                }

                PgimSupervisionDTO pgimSupervisionDTOCreado = null;
                try {
                        pgimSupervisionDTOCreado = this
                                        .obtenerSupervisionByIdSupervision(pgimSupervisionCreado.getIdSupervision());
                } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        throw new PgimException(TipoResultado.ERROR, e.getMessage());
                }

                return pgimSupervisionDTOCreado;
        }

        /**
         * Permite crear los items de consumo.
         * @param pgimConsumoContra
         * @param idtipoEstadioConsumo
         * @param pgimCostoUnitarioDTO
         * @param factor
         * @param auditoriaDTO
         */
        private void crearItemsConsumoContrato(PgimConsumoContra pgimConsumoContra, Long idtipoEstadioConsumo,
                        PgimCostoUnitarioDTO pgimCostoUnitarioDTO, BigDecimal factor, AuditoriaDTO auditoriaDTO) {

                PgimContrato pgimContrato = this.contratoRepository
                                .findById(pgimConsumoContra.getPgimContrato().getIdContrato()).orElse(null);

                String mensajeAviso = "";

                PgimItemConsumo pgimItemConsumo = null;
                BigDecimal moItemConsumo = null;

                // Actas de supervisión
                // ====================
                pgimItemConsumo = new PgimItemConsumo();

                pgimItemConsumo.setPgimConsumoContra(new PgimConsumoContra());
                pgimItemConsumo.getPgimConsumoContra().setIdConsumoContra(pgimConsumoContra.getIdConsumoContra());

                pgimItemConsumo.setTipoEntregable(new PgimValorParametro());

                pgimItemConsumo.getTipoEntregable().setIdValorParametro(this.valorParametroRepository
                                .obtenerIdValorParametro(EValorParametro.ENTRE_ACTAS_FSCLZCION.toString()));

                pgimItemConsumo.setTipoEstadioConsumo(new PgimValorParametro());
                pgimItemConsumo.getTipoEstadioConsumo().setIdValorParametro(idtipoEstadioConsumo);

                pgimItemConsumo.setPcEntregable(pgimContrato.getPcEntregableActa());

                // moItemConsumo = consumoContrato.multiply(pcEntregableActa).divide(pc100);
                moItemConsumo = pgimCostoUnitarioDTO.getMoCostoActa();
                if (moItemConsumo == null) {
                        mensajeAviso = "El costo del acta de fiscalización aún no está definido, por favor regístrelo en el respectivo contrato";
                        log.error(mensajeAviso);
                        throw new PgimException(TipoResultado.WARNING, mensajeAviso); // STORY: PGIM-6667: Mejora de la
                                                                                      // gestión de mensajes para el
                                                                                      // usuario
                }

                pgimItemConsumo.setMoItemConsumo(moItemConsumo.multiply(factor));

                pgimItemConsumo.setEsVigente(ConstantesUtil.IND_ACTIVO);

                pgimItemConsumo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimItemConsumo.setFeCreacion(auditoriaDTO.getFecha());
                pgimItemConsumo.setUsCreacion(auditoriaDTO.getUsername());
                pgimItemConsumo.setIpCreacion(auditoriaDTO.getTerminal());

                this.itemConsumoContratoRepository.save(pgimItemConsumo);

                // Informes de supervisión
                // =======================
                pgimItemConsumo = new PgimItemConsumo();

                pgimItemConsumo.setPgimConsumoContra(new PgimConsumoContra());
                pgimItemConsumo.getPgimConsumoContra().setIdConsumoContra(pgimConsumoContra.getIdConsumoContra());

                pgimItemConsumo.setTipoEntregable(new PgimValorParametro());
                pgimItemConsumo.getTipoEntregable().setIdValorParametro(this.valorParametroRepository
                                .obtenerIdValorParametro(EValorParametro.ENTRE_INFRMES_FSCLZCION.toString()));

                pgimItemConsumo.setTipoEstadioConsumo(new PgimValorParametro());
                pgimItemConsumo.getTipoEstadioConsumo().setIdValorParametro(idtipoEstadioConsumo);

                pgimItemConsumo.setPcEntregable(pgimContrato.getPcEntregableInforme());

                // moItemConsumo = consumoContrato.multiply(pcEntregableInforme).divide(pc100);
                moItemConsumo = pgimCostoUnitarioDTO.getMoCostoInformeSupervision();
                if (moItemConsumo == null) {
                        mensajeAviso = "El costo del informe de fiscalización aún no está definido, por favor regístrelo en el respectivo contrato";
                        log.error(mensajeAviso);
                        throw new PgimException(TipoResultado.WARNING, mensajeAviso); // STORY: PGIM-6667: Mejora de la
                                                                                      // gestión de mensajes para el
                                                                                      // usuario
                }

                pgimItemConsumo.setMoItemConsumo(moItemConsumo.multiply(factor));

                pgimItemConsumo.setEsVigente(ConstantesUtil.IND_ACTIVO);

                pgimItemConsumo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimItemConsumo.setFeCreacion(auditoriaDTO.getFecha());
                pgimItemConsumo.setUsCreacion(auditoriaDTO.getUsername());
                pgimItemConsumo.setIpCreacion(auditoriaDTO.getTerminal());

                this.itemConsumoContratoRepository.save(pgimItemConsumo);

                // Informes de gestión
                // ===================
                pgimItemConsumo = new PgimItemConsumo();

                pgimItemConsumo.setPgimConsumoContra(new PgimConsumoContra());
                pgimItemConsumo.getPgimConsumoContra().setIdConsumoContra(pgimConsumoContra.getIdConsumoContra());

                pgimItemConsumo.setTipoEntregable(new PgimValorParametro());
                pgimItemConsumo.getTipoEntregable()
                                .setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(
                                                EValorParametro.ENTRE_INFRMES_GSTION.toString()));

                pgimItemConsumo.setTipoEstadioConsumo(new PgimValorParametro());
                pgimItemConsumo.getTipoEstadioConsumo().setIdValorParametro(idtipoEstadioConsumo);

                pgimItemConsumo.setPcEntregable(pgimContrato.getPcEntregableFinal());

                moItemConsumo = pgimCostoUnitarioDTO.getMoCostoInformeGestion();
                if (moItemConsumo == null) {
                        mensajeAviso = "El costo del informe de gestión aún no está definido, por favor regístrelo en el respectivo contrato";
                        log.error(mensajeAviso);
                        throw new PgimException(TipoResultado.WARNING, mensajeAviso); // STORY: PGIM-6667: Mejora de la
                                                                                      // gestión de mensajes para el
                                                                                      // usuario
                }

                pgimItemConsumo.setMoItemConsumo(moItemConsumo.multiply(factor));

                pgimItemConsumo.setEsVigente(ConstantesUtil.IND_ACTIVO);

                pgimItemConsumo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimItemConsumo.setFeCreacion(auditoriaDTO.getFecha());
                pgimItemConsumo.setUsCreacion(auditoriaDTO.getUsername());
                pgimItemConsumo.setIpCreacion(auditoriaDTO.getTerminal());

                this.itemConsumoContratoRepository.save(pgimItemConsumo);
        }

        private String obtenerSiguienteCorrelativo(String anio, AuditoriaDTO auditoriaDTO) {

                Long bdAnio = new Long(anio);
                PgimCorrelativoSupervDTO pgimCorrelativoSupervDTO = supervisionRepository
                                .obtenerCorrelativoByAnio(bdAnio);
                PgimCorrelativoSuperv pgimCorrelativoSupervActual;

                if (pgimCorrelativoSupervDTO == null) {
                        // Si el registro no existe para el año, lo creamos
                        PgimCorrelativoSuperv pgimCorrelativoSuperv = new PgimCorrelativoSuperv();

                        pgimCorrelativoSuperv.setNuAnioCorrelativo(bdAnio);
                        pgimCorrelativoSuperv.setSeUltimoCorrelativo(1L);
                        pgimCorrelativoSuperv.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimCorrelativoSuperv.setFeCreacion(auditoriaDTO.getFecha());
                        pgimCorrelativoSuperv.setUsCreacion(auditoriaDTO.getUsername());
                        pgimCorrelativoSuperv.setIpCreacion(auditoriaDTO.getTerminal());
                        pgimCorrelativoSupervActual = correlativoSupervRepository.save(pgimCorrelativoSuperv);
                } else {
                        // Si el registro existe, incrementamos en 1
                        PgimCorrelativoSuperv pgimCorrelativoSuperv = correlativoSupervRepository
                                        .findById(pgimCorrelativoSupervDTO.getIdCorrelativoSuperv()).orElse(null);
                        Long incremento = 1L + pgimCorrelativoSuperv.getSeUltimoCorrelativo();
                        pgimCorrelativoSuperv.setSeUltimoCorrelativo(incremento);
                        pgimCorrelativoSuperv.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimCorrelativoSuperv.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimCorrelativoSuperv.setIpActualizacion(auditoriaDTO.getTerminal());
                        pgimCorrelativoSupervActual = correlativoSupervRepository.save(pgimCorrelativoSuperv);
                }

                String coSupervision = "";

                coSupervision = "SP-" + anio + "-" + String.format("%05d",
                                Long.valueOf(pgimCorrelativoSupervActual.getSeUltimoCorrelativo()));

                return coSupervision;
        }

        @Override
        public PgimSupervisionDTO obtenerAsignacionSupervisionPorId(Long idSupervision) {
                return this.supervisionRepository.obtenerAsignacionSupervisionPorId(idSupervision);
        }

        @Override
        public List<PgimSupervisionDTO> listarSupervisionesTraslapadas(Long idUnidadMinera, Date feInicioSupervision,
                        Date feFinSupervision) throws Exception {
                List<PgimSupervisionDTO> lPgimSupervisionDTO = this.supervisionRepository
                                .listarSupervisionesTraslapadas(idUnidadMinera, feInicioSupervision,
                                                feFinSupervision);

                List<PgimSupervisionDTO> lPgimSupervisionDTOTraslapes = new ArrayList<PgimSupervisionDTO>();

                PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = null;
                PgimPasoProceso pgimPasoProceso = null;
                PgimRelacionPaso pgimRelacionPaso = null;
                PgimValorParametro pgimValorParametro = null;

                for (PgimSupervisionDTO pgimSupervisionDTO : lPgimSupervisionDTO) {

                        pgimInstanciaPasoDTOActual = this.flujoTrabajoService
                                        .obtenerInstanciaPasoActualPorIdInstanciaPaso(
                                                        pgimSupervisionDTO.getDescIdInstanciaPaso());

                        pgimPasoProceso = this.pasoProcesoRepository
                                        .findById(pgimInstanciaPasoDTOActual.getIdPasoProcesoDestino())
                                        .orElse(null);

                        if (!pgimPasoProceso.getPgimFaseProceso().getIdFaseProceso()
                                        .equals(ConstantesUtil.PARAM_SUPERVISION_PRE_SUPERVISION) &&
                                        !pgimPasoProceso.getPgimFaseProceso().getIdFaseProceso()
                                                        .equals(ConstantesUtil.PARAM_SUPERVISION_SUPERVISION_CAMPO)) {
                                continue;
                        }
                        // Ahora veficicamos que la relación de paso no esté finalizada.
                        pgimRelacionPaso = this.relacionPasoRepository
                                        .findById(pgimInstanciaPasoDTOActual.getIdRelacionPaso()).orElse(null);
                        pgimValorParametro = this.valorParametroRepository
                                        .findById(pgimRelacionPaso.getTipoRelacion().getIdValorParametro())
                                        .orElse(null);

                        if (pgimValorParametro.getCoClaveTexto().equals(EValorParametro.REPAS_CNCLAR_FLJO.toString())
                                        || pgimValorParametro.getCoClaveTexto()
                                                        .equals(EValorParametro.REPAS_FNLZAR_FLJO.toString())) {
                                continue;
                        }
                        // Se tienen traslapes
                        lPgimSupervisionDTOTraslapes.add(pgimSupervisionDTO);
                }

                return lPgimSupervisionDTOTraslapes;
        }

        @Override
        public PgimSupervision getByIdSupervision(Long idSupervision) {
                return this.supervisionRepository.findById(idSupervision).orElse(null);
        }

        @Transactional(readOnly = false)
        @Override
        public PgimSupervisionDTO registrarFechasRealesSupervision(PgimSupervisionDTO pgimSupervisionDTO,
                        PgimSupervision pgimSupervision, AuditoriaDTO auditoriaDTO) {

                PgimSupervision pgimSupervisionActual = pgimSupervision;

                pgimSupervisionActual.setFeInicioSupervisionReal(pgimSupervisionDTO.getFeInicioSupervisionReal());
                pgimSupervisionActual.setFeFinSupervisionReal(pgimSupervisionDTO.getFeFinSupervisionReal());

                pgimSupervisionActual.setFeActualizacion(auditoriaDTO.getFecha());
                pgimSupervisionActual.setUsActualizacion(auditoriaDTO.getUsername());
                pgimSupervisionActual.setIpActualizacion(auditoriaDTO.getTerminal());

                PgimSupervision pgimSupervisionModificado = supervisionRepository.save(pgimSupervisionActual);

                // Actualizar consumo del contrato
                this.actualizarConsumoContrato(pgimSupervisionActual.getIdSupervision(),
                                this.valorParametroRepository.obtenerIdValorParametro(
                                                EValorParametro.ESCON_CMPRMTDO.toString()),
                                true, auditoriaDTO);

                PgimSupervisionDTO pgimSupervisionDTOModificado = this
                                .obtenerSupervisionPorIdSupervision(pgimSupervisionModificado.getIdSupervision());

                return pgimSupervisionDTOModificado;
        }

        @Override
        public List<PgimSupervisionDTO> listarSupervisionesFechaInicioReal(Long idSupervision,
                        Date feInicioRealSupervision) {
                return this.supervisionRepository.listarSupervisionesFechaInicioReal(idSupervision,
                                feInicioRealSupervision);
        }

        @Transactional(readOnly = false)
        @Override
        public PgimSupervisionDTO redefinirFechaSupervision(PgimSupervisionDTO pgimSupervisionDTO,
                        PgimSupervision pgimSupervision, AuditoriaDTO auditoriaDTO) {

                PgimSupervision pgimSupervisionActual = pgimSupervision;

                pgimSupervisionActual.setFeInicioSupervision(pgimSupervisionDTO.getFeInicioSupervision());
                pgimSupervisionActual.setFeFinSupervision(pgimSupervisionDTO.getFeFinSupervision());

                pgimSupervisionActual.setFeActualizacion(auditoriaDTO.getFecha());
                pgimSupervisionActual.setUsActualizacion(auditoriaDTO.getUsername());
                pgimSupervisionActual.setIpActualizacion(auditoriaDTO.getTerminal());

                PgimSupervision pgimSupervisionModificado = supervisionRepository.save(pgimSupervisionActual);

                // Consultamos si ya se ha registrado la relación
                // PARAM_RELACION_APROBAR_TDR_CREDENCIAL_DEFINIR_ANTECEDENTES para la instancia
                // de proceso
                List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTOhist = this.instanciaPasoRepository
                                .obtenerInstanciaPasosXrelacion(
                                                pgimSupervisionActual.getPgimInstanciaProces().getIdInstanciaProceso(),
                                                ConstantesUtil.PARAM_RELACION_APROBAR_TDR_CREDENCIAL_DEFINIR_ANTECEDENTES);

                // Determinamos el estadio actual de consumo del contrato
                Long idTipoEstadioConsumo = 0L;
                Boolean esFechaReal = false;

                if (lPgimInstanciaPasoDTOhist.size() > 0) {
                        idTipoEstadioConsumo = this.valorParametroRepository
                                        .obtenerIdValorParametro(EValorParametro.ESCON_CMPRMTDO.toString());
                } else {
                        idTipoEstadioConsumo = this.valorParametroRepository
                                        .obtenerIdValorParametro(EValorParametro.ESCON_PRE_CMPRMTDO.toString());

                }

                this.actualizarConsumoContrato(pgimSupervision.getIdSupervision(), idTipoEstadioConsumo, esFechaReal,
                                auditoriaDTO);

                PgimSupervisionDTO pgimSupervisionDTOModificado = this
                                .obtenerSupervisionPorIdSupervision(pgimSupervisionModificado.getIdSupervision());

                return pgimSupervisionDTOModificado;
        }

        /**
         * Permite actualizar el monto del consumo del contrato
         * 
         */
        private void actualizarConsumoContrato(Long idSupervision, Long idTipoEstadioConsumo, boolean flagFechaReal,
                        AuditoriaDTO auditoriaDTO) {

                // Obtenemos el objeto
                PgimSupervisionDTO pgimSupervisionDTOactual = this.supervisionRepository
                                .obtenerSupervisionPorId(idSupervision);

                // Consultar si la supervisión no es propia
                if (pgimSupervisionDTOactual.getFlPropia().equals("0")) {

                        // Obtenemnos el registro de consumo de contrato actual
                        List<PgimConsumoContraDTO> lConsumoContraDtoActual = this.consumoContraRepository
                                        .obtenerMontoConsumo(pgimSupervisionDTOactual.getIdSupervision());

                        if (lConsumoContraDtoActual.size() == 0 || lConsumoContraDtoActual.size() > 1) {
                                throw new PgimException("error",
                                                "No se ha podido obtener el registro de consumo de contrato actual.");
                        }

                        // Obtenemos el nuevo consumo de contrato, para las fechas modificadas
                        PgimConsumoContraDTO pgimConsumoContraDtoNuevo = this.contratoService
                                        .calcularCostoSupervision(pgimSupervisionDTOactual, flagFechaReal);

                        // Validamos si el monto a cambiado
                        if (!lConsumoContraDtoActual.get(0).getMoConsumoContrato()
                                        .equals(pgimConsumoContraDtoNuevo.getMoConsumoContrato())) {

                                // Si el monto ha cambiado, procedemos con la actualización del consumo del
                                // contrato
                                PgimConsumoContra pgimConsumoContra = this.consumoContraRepository
                                                .findById(lConsumoContraDtoActual.get(0).getIdConsumoContra())
                                                .orElse(null);
                                pgimConsumoContra
                                                .setMoConsumoContrato(pgimConsumoContraDtoNuevo.getMoConsumoContrato());
                                pgimConsumoContra.setFeActualizacion(auditoriaDTO.getFecha());
                                pgimConsumoContra.setUsActualizacion(auditoriaDTO.getUsername());
                                pgimConsumoContra.setIpActualizacion(auditoriaDTO.getTerminal());
                                this.consumoContraRepository.save(pgimConsumoContra);

                                // obtener lista de items de consumo vigentes (anteriores)
                                List<PgimItemConsumoDTO> lPgimItemConsumoDTOanterior = this.itemConsumoContratoRepository
                                                .obtenerListaItemConsumoVigentes(
                                                                lConsumoContraDtoActual.get(0).getIdConsumoContra());
                                // setear como no vigentes los items de consumo anteriores
                                for (PgimItemConsumoDTO pgimItemConsumoDTO : lPgimItemConsumoDTOanterior) {
                                        PgimItemConsumo pgimItemConsumoAnterior = this.itemConsumoContratoRepository
                                                        .findById(pgimItemConsumoDTO.getIdItemConsumo()).orElse(null);
                                        pgimItemConsumoAnterior.setEsVigente(ConstantesUtil.IND_INACTIVO);
                                        pgimItemConsumoAnterior.setFeActualizacion(auditoriaDTO.getFecha());
                                        pgimItemConsumoAnterior.setUsActualizacion(auditoriaDTO.getUsername());
                                        pgimItemConsumoAnterior.setIpActualizacion(auditoriaDTO.getTerminal());
                                        this.itemConsumoContratoRepository.save(pgimItemConsumoAnterior);
                                }

                                Long cantidadDiasEntre = this.contratoService
                                                .diasCalendarioSupervision(pgimSupervisionDTOactual, flagFechaReal);

                                PgimCostoUnitarioDTO pgimCostoUnitarioDTO = this.contratoService.obtenerCostoUnitario(
                                                pgimSupervisionDTOactual, flagFechaReal, cantidadDiasEntre);

                                this.crearItemsConsumoContrato(pgimConsumoContra, idTipoEstadioConsumo,
                                                pgimCostoUnitarioDTO, BigDecimal.valueOf(1), auditoriaDTO);
                        }
                }
        }

        @Override
        public void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso,
                        PgimInstanciaPaso pgimInstanciaPaso) {

                if (!pgimRelacionPaso.getIdRelacionPaso().equals(
                                ConstRelacionPasoFiscalizacion.F1_APROBARREVANTECEDENTES_F1_PREPARARINICIOFISCACAMPO)
                                && !pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_INICIASUPERV_SOLDOCCAMPO)
                                && !pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_PREACTASUPER_ELAINFO)
                                && !pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_ELABORAR_FEHV_APROBAR_OCAF)
                                && !pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARPAS)
                                && !pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARARCH)
                                && !pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME)
                                && !pgimRelacionPaso.getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF)
                                && !pgimRelacionPaso.getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RELACION_DEFINIR_ANTECEDENTES_REV_ANTECEDENTES)
                                && !pgimRelacionPaso.getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RELACION_REVISAR_ANTECEDENTES_APROBAR_REV_ANTECEDENTES)
                                && !pgimRelacionPaso.getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RELACION_APROBAR_CAMBIOS_INSTRUMENTOS_MEDICION)
                                && !pgimRelacionPaso.getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD)
                                && !pgimRelacionPaso.getIdRelacionPaso().equals(
                                                ConstRelacionPasoFiscalizacion.F2_INICIARFISCALIZACIONCAMPO_F2_REALIZARMEDICIONESYVERIFICARHECHOS)
                                && !pgimRelacionPaso.getIdRelacionPaso().equals(
                                        ConstRelacionPasoFiscalizacion.F2_INICIARFISCALIZACIONCAMPO_F1_PREPARARINICIOFISCACAMPO)
                                                ) {
                        return;
                }

                String msjExcepcionControlada = null;

                Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();
                PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso)
                                .orElse(null);

                PgimSupervision pgimSupervision = this.supervisionRepository
                                .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);
                PgimUnidadMinera pgimUnidadMinera = this.unidadMineraRepository
                                .findById(pgimSupervision.getPgimUnidadMinera().getIdUnidadMinera()).orElse(null);

                if (pgimRelacionPaso.getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_INICIASUPERV_SOLDOCCAMPO)) {
                        if (pgimSupervision.getFeInicioSupervisionReal() == null) {
                                msjExcepcionControlada = "No se puede asignar el paso porque aún no se ha definido la Fecha de inicio real de la supervisión";
                        }
                } else if (pgimRelacionPaso.getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_PREACTASUPER_ELAINFO)) {
                        if (pgimSupervision.getFeFinSupervisionReal() == null) {
                                msjExcepcionControlada = "No se puede asignar el paso porque aún no se ha definido la Fecha de fin real de la supervisión";
                        } else {

                                Long idSupervision = 0L;
                                if (pgimSupervision.getIdSupervision() != null) {
                                        idSupervision = pgimSupervision.getIdSupervision();
                                }


                                // lista de documentos cargados por instancia de proceso y documento con la subcategoria de "Acta de fiscalización(27L)"
                                List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository.listarDocPorInstanciaYSubCategoria(idInstanciaProceso, ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION);

                                // Conversión de fecha
                                SimpleDateFormat transformarFecha = new SimpleDateFormat("dd'/'MM'/'yyyy");

                                if(lPgimDocumentoDTO.size() == 1){
                                        for(PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTO){

                                                // Fecha de finalizacion real de la fiscalización
                                                String feFinSupervisionReal = transformarFecha.format(pgimSupervision.getFeFinSupervisionReal());

                                                // Fecha del acta de fiscalización que ha sido adjuntado
                                                String feOrigenDocumento = transformarFecha.format(pgimDocumentoDTO.getFeOrigenDocumento());

                                                // https://relazta.atlassian.net/browse/PGIM-9453 -> Fecha de acta de fiscalización igual que la fecha fin real de fiscalización
                                                if(!feOrigenDocumento.equals(feFinSupervisionReal)){

                                                        msjExcepcionControlada = "¡Atención! La fecha del acta de fiscalización no coincide con la fecha de finalización real de la fiscalización. " 
                                                                                + "Para continuar con la tarea 'Elaborar y presentar informe de fiscalización', ambas fechas deben ser iguales. " 
                                                                                + "Por favor, verifica y corrige las fechas antes de continuar.";

                                                        throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada);
                                                }
                                        }
                                }

                                List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTO = this.hechoConstatadoService
                                                .obtenerHechosConstatados(idSupervision,
                                                                ConstantesUtil.PARAM_HC_ROL_SUPERVISOR);

                                // validar existencia de instrumentos modificados
                                Integer cantidadInstrumRegist = this.supervInstrumentoRepository
                                                .cantidadInstrumentos(idSupervision,
                                                                ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_REGISTRADO);
                                Integer cantidadInstrumModif = this.supervInstrumentoRepository
                                                .cantidadInstrumentos(idSupervision,
                                                                ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_MODIFICADO);
                                Integer cantidadInstrumNodisp = this.supervInstrumentoRepository
                                                .cantidadInstrumentos(idSupervision,
                                                                ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_NODISPONIBLE);
                                Integer cantidadInstrumReemp = this.supervInstrumentoRepository
                                                .cantidadInstrumentos(idSupervision,
                                                                ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_REEMPLAZADO);

                                if (cantidadInstrumRegist > 0 || cantidadInstrumModif > 0 || cantidadInstrumNodisp > 0
                                                || cantidadInstrumReemp > 0) {
                                        msjExcepcionControlada = "No se puede asignar el paso, porque hay cambios en los instrumentos de medición que aún no han sido aprobados";
                                }

                                Map<String, String> validaObligaciones = this.validarObligacionesSeccionadasPorHC(idSupervision, ConstantesUtil.PARAM_HC_ROL_SUPERVISOR);
                                if(validaObligaciones.get("respuesta").equals(ConstantesUtil.FL_IND_SI)){
                                        msjExcepcionControlada = validaObligaciones.get("mensaje");
                                }
                                

                        }
                
                } else if (pgimRelacionPaso.getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_ELABORAR_FEHV_APROBAR_OCAF) ||
                                pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARPAS)
                                ||
                                pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARARCH)) {
                        List<PgimCotejoHechoCnsttdoDTO> lPgimCotejoHechoCnsttdoDTO = cotejoHechoCnsttdoRepository
                                        .listarCotejoHCpendientesConformidad(pgimSupervision.getIdSupervision(), ConstantesUtil.PARAM_COD_NO_CUMPLE_TC);
                        if (lPgimCotejoHechoCnsttdoDTO.size() > 0) {
                                msjExcepcionControlada = "No se puede asignar el paso, primero debe confirmar todos los hechos verificados categorizados como incumplimientos por el fiscalizador";
                        }
                        
                        Map<String, String> validaObligaciones = this.validarObligacionesSeccionadasPorHC(pgimSupervision.getIdSupervision(), ConstantesUtil.PARAM_HC_ROL_ESP_TECNICO);
                        if(validaObligaciones.get("respuesta").equals(ConstantesUtil.FL_IND_SI)){
                                msjExcepcionControlada = validaObligaciones.get("mensaje");
                        }

                } else if (pgimRelacionPaso.getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME) ||
                                pgimRelacionPaso.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF)) {

                        if (pgimSupervision.getPgimMotivoSupervision().getTipoSupervision().getIdValorParametro()
                                        .equals(this.valorParametroRepository
                                                        .obtenerIdValorParametro(EValorParametro.SUPER_FSCLZCION_PRGRMDA
                                                                        .toString()))
                                        && pgimUnidadMinera.getFlRegistraRiesgos().equals("1")) {

                                if (this.numeroRegRiesgosFaltantes(pgimSupervision.getIdSupervision()) > 0) {
                                        msjExcepcionControlada = "No se puede asignar el paso, primero debe completar los factores de riesgo de la supervisión";
                                }

                                if (this.existenGruposRiesgoFaltantes(pgimSupervision.getIdSupervision())) {
                                        msjExcepcionControlada = "No se puede asignar el paso, porque debe existir al menos un grupo técnico y un grupo de gestión en condición para registrar";
                                }

                                List<PgimDocumentoDTO> lPgimDocumentoFRPT = this.documentoRepository
                                                .obtenerDocPorInstanciaYSubCategoria(idInstanciaProceso,
                                                                ConstantesUtil.PARAM_SC_FICHA_RIESGOS_PARAM_TECNICOS);

                                if (lPgimDocumentoFRPT.size() == 0) {

                                        if (msjExcepcionControlada != null && msjExcepcionControlada.length() > 0)
                                                msjExcepcionControlada = msjExcepcionControlada + "<br>";
                                        else
                                                msjExcepcionControlada = "";

                                        msjExcepcionControlada = msjExcepcionControlada
                                                        + "No se puede asignar el paso, primero debe adjuntar la Ficha de parámetros técnicos para riesgos de la supervisión";
                                }

                                List<PgimDocumentoDTO> lPgimDocumentoFRPG = this.documentoRepository
                                                .obtenerDocPorInstanciaYSubCategoria(idInstanciaProceso,
                                                                ConstantesUtil.PARAM_SC_FICHA_RIESGOS_PARAM_GESTION);

                                if (lPgimDocumentoFRPG.size() == 0) {

                                        if (msjExcepcionControlada != null && msjExcepcionControlada.length() > 0)
                                                msjExcepcionControlada = msjExcepcionControlada + "<br>";
                                        else
                                                msjExcepcionControlada = "";

                                        msjExcepcionControlada = msjExcepcionControlada
                                                        + "No se puede asignar el paso, primero debe adjuntar la Ficha de parámetros de gestión para riesgos de la fiscalización";
                                }
                                

                        }
                        
                        // Valida que haya adjuntado un informe de fiscalización si el previo ha sido rechazado
                        
                        List<PgimDocumentoDTO> lPgimDocumentoDTOInformes = this.documentoRepository.listarDocPorInstanciaYSubCategoria(idInstanciaProceso,
                                                        ConstantesUtil.PARAM_SC_INFORME_SUPERVISION);
                        
                        List<PgimDocumentoDTO> lPgimDocumentoDTOInfRechazados = lPgimDocumentoDTOInformes.stream().filter(pgimDocumentoDTO -> {
                            return pgimDocumentoDTO.getFeRechazo() != null;
                        }).collect(Collectors.toList());

                        
                        if (lPgimDocumentoDTOInformes.size() > 0 && lPgimDocumentoDTOInformes.size() == lPgimDocumentoDTOInfRechazados.size()) {
                                // si todos los informes figuran como rechazados, lanzamos la validación para que adjunte un nuevo informe de fiscalización
                                if (msjExcepcionControlada != null && msjExcepcionControlada.length() > 0)
                                        msjExcepcionControlada = msjExcepcionControlada + "<br>";
                                else
                                        msjExcepcionControlada = "";
                                
                                String subcatDocumento = lPgimDocumentoDTOInformes.get(0).getNoSubcatDocumento();
                                msjExcepcionControlada = msjExcepcionControlada
                                                + "No se puede asignar el paso, primero debe adjuntar un nuevo <b>" + subcatDocumento + "</b> debido a que el anterior ha sido rechazado";
                        }

                        Map<String, String> validaObligaciones = this.validarObligacionesSeccionadasPorHC(pgimSupervision.getIdSupervision(), ConstantesUtil.PARAM_HC_ROL_SUPERVISOR);
                        if(validaObligaciones.get("respuesta").equals(ConstantesUtil.FL_IND_SI)){
                                msjExcepcionControlada = validaObligaciones.get("mensaje");
                        }

                } else if (pgimRelacionPaso.getIdRelacionPaso().equals(
                                ConstantesUtil.PARAM_RELACION_DEFINIR_ANTECEDENTES_REV_ANTECEDENTES)) {

                        Long idSupervision = 0L;
                        if (pgimSupervision.getIdSupervision() != null) {
                                idSupervision = pgimSupervision.getIdSupervision();
                        }

                        Integer cantidadAntecedentes = this.antecedenteSupervService
                                        .cantidadAntecedentes(idSupervision);

                        if (cantidadAntecedentes == 0) {
                                msjExcepcionControlada = "";
                                msjExcepcionControlada = msjExcepcionControlada
                                                + "No se puede asignar el paso, primero debe definir al menos un <b>Antecedente</b>";
                        }
                } else if (pgimRelacionPaso.getIdRelacionPaso().equals(
                                ConstantesUtil.PARAM_RELACION_REVISAR_ANTECEDENTES_APROBAR_REV_ANTECEDENTES)) {

                        Long idSupervision = 0L;
                        if (pgimSupervision.getIdSupervision() != null) {
                                idSupervision = pgimSupervision.getIdSupervision();
                        }

                        Integer cantidadInstrum = this.supervInstrumentoRepository
                                        .cantidadInstrumentos(idSupervision, null);

                        if (cantidadInstrum == 0 && pgimSupervision.getFlPropia().equals("0")) {

                                Long idSubtipoSuperv = pgimSupervision.getPgimSubtipoSupervision()
                                                .getIdSubtipoSupervision();
                                PgimSubtipoSupervisionDTO tipoSuperv = this.subTipoSupervisionRepository
                                                .obtenerSubTipoSupervisionById(idSubtipoSuperv);
                                Long idTipoSuperv = tipoSuperv.getIdTipoSupervision();

                                if (!(idTipoSuperv.equals(ConstantesUtil.PARAM_TIPO_SUPERVISION_PROGRAMADA)
                                                && idSubtipoSuperv.equals(
                                                                ConstantesUtil.PARAM_SUBTIPO_PROG_CON_EST_ESTABILIDAD))
                                                &&
                                                !(idTipoSuperv.equals(
                                                                ConstantesUtil.PARAM_TIPO_SUPERVISION_NOPROGRAMADA)
                                                                && idSubtipoSuperv.equals(
                                                                                ConstantesUtil.PARAM_SUBTIPO_NOPROG_CON_EST_ESTABILIDAD))) {
                                        msjExcepcionControlada = "";
                                        msjExcepcionControlada = msjExcepcionControlada
                                                        + "No se puede asignar el paso, porque debe existir al menos un <b>instrumento de medición</b>";
                                }

                        }

                } else if (pgimRelacionPaso.getIdRelacionPaso().equals(
                                ConstRelacionPasoFiscalizacion.F1_APROBARREVANTECEDENTES_F1_PREPARARINICIOFISCACAMPO)) {

                        List<PgimCriterioSprvsionDTO> lPgimCriterioSprvsionDTOExistentes = this.criterioSprvsionRepository
                                        .obtenerMatrizSupervision(pgimSupervision.getIdSupervision());

                        if (lPgimCriterioSprvsionDTOExistentes.size() == 0) {
                                msjExcepcionControlada = "";
                                msjExcepcionControlada = msjExcepcionControlada
                                                + "No se puede asignar el paso debido a que no existe criterio alguno en el cuadro de verificación de la fiscalización (ver 1. Pre-fiscalización / VERIFICACIÓN) ";
                        }
                } else if (pgimRelacionPaso.getIdRelacionPaso().equals(
                                ConstRelacionPasoFiscalizacion.F2_INICIARFISCALIZACIONCAMPO_F2_REALIZARMEDICIONESYVERIFICARHECHOS)) {

                        List<PgimDocumentoDTO> lPgimDocumento = this.documentoService.obtenerDocumentosPorSubcategoria(idInstanciaProceso, ConstSubCategoriaDocumento.ACTA_INICIO_FICALIZACION);

                        if (lPgimDocumento.size() > 0) {
                                msjExcepcionControlada = "No se pudo completar la asignación de la tarea porque existe al menos un documento con la subcategoría «Acta de inicio de fiscalizaci\u00F3n», elimine los documentos con esta subcategoría y vuelva a intentar";
                        }
                        
                } else if (pgimRelacionPaso.getIdRelacionPaso().equals(
                        ConstRelacionPasoFiscalizacion.F2_INICIARFISCALIZACIONCAMPO_F1_PREPARARINICIOFISCACAMPO)) {
                        
                                Sort sort = Sort.by("feOrigenDocumento");

                                List<PgimDocumentoDTO> lPgimDocumento = this.documentoRepository.obtenerDocumentosInstanciaFase(idInstanciaProceso, 
                                                ConstantesUtil.PARAM_SUPERVISION_SUPERVISION_CAMPO, sort);
                
                                if (lPgimDocumento.size() > 0) {
                                        msjExcepcionControlada = "No se pudo completar la asignación de la tarea porque existe al menos un documento en el marco de la fase «2. Fiscalización de campo», elimine los documentos de dicha fase y vuelva a intentar";
                                }                        
                        
                } else if (pgimRelacionPaso.getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD)){
                        
                        List<PgimDocumentoDTO> lPgimDocConformidad = this.documentoService.obtenerDocumentosPorSubcategoria(idInstanciaProceso, ConstSubCategoriaDocumento.FICHA_CONFORMIDAD_FISCALIZACION);
                        List<PgimDocumentoDTO> lPgimDocInfSustentatorio = this.documentoService.obtenerDocumentosPorSubcategoria(idInstanciaProceso, ConstSubCategoriaDocumento.INFORME_SUSTENTATORIO_PENALIDAD);


                        if (lPgimDocConformidad.size() > 0) {
                                Long idDocumento = lPgimDocConformidad.get(0).getIdDocumento();
                                PgimFichaRevisionDTO pgimFichaRevisionDTO =  this.fichaRevisionRepository.obtenerFichaRevisionPorIdDocumento(idDocumento);
                                if(pgimFichaRevisionDTO.getDescFlOtrasPenalidades().equals(ConstantesUtil.FL_IND_SI) && lPgimDocInfSustentatorio.size() == 0){
                                        msjExcepcionControlada = "No se pudo completar la asignación de la tarea porque existe al menos una penalidad clave, por favor adjunte el documento con la subcategoría «Informe sustentatorio de aplicación de penalidad» y vuelva a intentar";

                                }else if(pgimFichaRevisionDTO.getDescFlOtrasPenalidades().equals(ConstantesUtil.FL_IND_NO) && lPgimDocInfSustentatorio.size() > 0){
                                        msjExcepcionControlada = "No se pudo completar la asignación de la tarea porque existe al menos un documento con la subcategoría «Informe sustentatorio de aplicación de penalidad», sin embargo no existe ninguna penalidad clave. Elimine los documentos con esta subcategoría y vuelva a intentar";
                                }

                        }
                }

                if (msjExcepcionControlada != null) {
                        throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada);
                }
        }

        public Map<String, String> validarObligacionesSeccionadasPorHC(Long idSupervision, Long idRol){

                String iSinObligaciones = ConstantesUtil.FL_IND_NO;
                String mensaje = "";
                int count = 0;
                List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTOIncumpl = this.hechoConstatadoService
                        .listarHechosConstatadosConNoCumplimientos(idSupervision, idRol);

                for (PgimHechoConstatadoDTO pgimHechoConstatadoDTOIncumpl : lPgimHechoConstatadoDTOIncumpl) {
                        
                        List<PgimObligacionNormaAuxDTO> lPgimObligacionNormaAuxDTO = this.obligacionNormaAuxRepository
                                .lObligacionesNormativasPorHCSelec(pgimHechoConstatadoDTOIncumpl.getIdHechoConstatado());
                        
                        if(lPgimObligacionNormaAuxDTO.size() == 0){
                                iSinObligaciones = ConstantesUtil.FL_IND_SI;
                                count ++;
                        }
                        
                }
                
                if(iSinObligaciones.equals(ConstantesUtil.FL_IND_SI)){
                        mensaje = "No se puede asignar el paso, porque hay 1 hecho verificado categorizado como INCUMPLIMIENTO al que le falta especificar la(s) obligación(es) normativa(s) que incumplen.";
                        if(count>1){
                                mensaje = String.format("No se puede asignar el paso, porque hay %s hechos verificados categorizados como INCUMPLIMIENTOS "
                                                + "a los que les falta especificar la(s) obligación(es) normativa(s) que incumplen.", count);
                        }
                }
                
                Map<String, String> dResp = new HashMap<>();
                dResp.put("respuesta", iSinObligaciones);
                dResp.put("mensaje", mensaje);      
                dResp.put("count", String.valueOf(count));

                return dResp;

        }

        public int numeroRegRiesgosFaltantes(Long idSupervision) {
                int numFaltantes = 0;

                List<PgimRankingUmGrupoDTO> lPgimRankingUmGrupoDTO = this.rankingUmGrupoRepository
                                .listarRankingUmGrupoPorSupervision(idSupervision);

                for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO : lPgimRankingUmGrupoDTO) {
                        if (pgimRankingUmGrupoDTO.getFlRegistrar().equals("0")) {
                                continue;
                        }

                        List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = this.rankingUmFactorRepository
                                        .listarFactoresFiscalizacionPorIdGrupo(
                                                        pgimRankingUmGrupoDTO.getIdRankingUmGrupo(),
                                                        EValorParametro.ORDAR_FSCLZCION.toString());

                        for (PgimRankingUmFactorDTO pgimRankingUmFactorDTO : lPgimRankingUmFactorDTO) {
                                if (pgimRankingUmFactorDTO.getIdCfgNivelRiesgo() == null) {
                                        numFaltantes++;
                                }
                        }

                }

                return numFaltantes;
        }

        /**
         * Permite verificar si para la supervisión no existe al menos un grupo técnico
         * ni tampoco al menos un grupo de gestión
         * 
         * @param idSupervision
         * @return
         */
        private boolean existenGruposRiesgoFaltantes(Long idSupervision) {
                List<PgimRankingUmGrupoDTO> lPgimRankingUmGrupoDTO = this.rankingUmGrupoRepository
                                .listarRankingUmGrupoPorSupervision(idSupervision);

                int contadorGruposTecnicos = 0;
                int contadorGruposGestion = 0;

                for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO : lPgimRankingUmGrupoDTO) {
                        if (pgimRankingUmGrupoDTO.getFlRegistrar().equals("0")) {
                                continue;
                        }
                        if (pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo()
                                        .equals(ConstantesUtil.PARAM_GRUPO_RIESGO_TECNICO)) {
                                contadorGruposTecnicos++;
                        } else if (pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo()
                                        .equals(ConstantesUtil.PARAM_GRUPO_RIESGO_GESTION)) {
                                contadorGruposGestion++;
                        }
                }

                if (contadorGruposTecnicos == 0 || contadorGruposGestion == 0) {
                        return true;
                }

                return false;
        }

        @Transactional(readOnly = false)
        @Override
        public PgimSupervisionDTO modificarSupervisionTDR(PgimSupervisionDTO pgimSupervisionDTO,
                        AuditoriaDTO auditoriaDTO) throws Exception {

                PgimSupervision pgimSupervisionActual = getByIdSupervision(pgimSupervisionDTO.getIdSupervision());

                pgimSupervisionActual.setDeTdrObjetivoTexto(pgimSupervisionDTO.getDeTdrObjetivoTexto());
                pgimSupervisionActual.setDeTdrAlcanceTexto(pgimSupervisionDTO.getDeTdrAlcanceTexto());
                pgimSupervisionActual.setDeTdrInformeSupervTexto(pgimSupervisionDTO.getDeTdrInformeSupervTexto());
                pgimSupervisionActual.setDeTdrMetodologiaTexto(pgimSupervisionDTO.getDeTdrMetodologiaTexto());
                pgimSupervisionActual.setDeTdrHonorariosProf(pgimSupervisionDTO.getDeTdrHonorariosProf());

                pgimSupervisionActual.setIpActualizacion(auditoriaDTO.getTerminal());
                pgimSupervisionActual.setFeActualizacion(auditoriaDTO.getFecha());
                pgimSupervisionActual.setUsActualizacion(auditoriaDTO.getUsername());

                PgimSupervision pgimSupervisionModificado = supervisionRepository.save(pgimSupervisionActual);

                PgimSupervisionDTO pgimSupervisionDTOModificado = obtenerSupervisionPorId(
                                pgimSupervisionModificado.getIdSupervision());

                return pgimSupervisionDTOModificado;
        }

        @Override
        public PgimSupervisionDTO obtenerValoresTDR(Long idSupervision) {
                return supervisionRepository.obtenerValoresTDR(idSupervision);
        }

        @Override
        public PgimSupervisionDTO obtenerSupervisionPorIdAux(Long idSupervision) {
                return supervisionRepository.obtenerSupervisionPorIdAux(idSupervision);
        }

        @Override
        public PgimSupervisionDTO obtenerValoresActaSupervision(Long idSupervision) {
                return supervisionRepository.obtenerValoresActaSupervision(idSupervision);
        }

        @Override
        public PgimSupervisionDTO obtenerValoresActaInicioSupervision(Long idSupervision) {
                return supervisionRepository.obtenerValoresActaInicioSupervision(idSupervision);
        }

        @Override
        public PgimSupervisionDTO obtenerValoresFichaHechosConstatados(Long idSupervision) {
                return supervisionRepository.obtenerValoresFichaHechosConstatados(idSupervision);
        }

        @Override
        public BigDecimal obtenerMontoConsumo(Long idSupervision) {
                BigDecimal moConsumoContrato = BigDecimal.ZERO;

                List<PgimConsumoContraDTO> lPgimConsumoContraDTO = this.consumoContraRepository
                                .obtenerMontoConsumo(idSupervision);

                for (PgimConsumoContraDTO pgimConsumoContraDTO : lPgimConsumoContraDTO) {
                        moConsumoContrato = moConsumoContrato.add(pgimConsumoContraDTO.getMoConsumoContrato());
                }

                return moConsumoContrato;
        }

        @Override
        public List<PgimSupervisionDTO> obtenerActasPresentadasPendienteLiquidar() {
                return this.supervisionRepository.obtenerActasPresentadasPendienteLiquidar();
        }

        /**
         * Permite actualizar el monto de consumo del contrato
         * 
         */
        public void actualizarConsumoContrato(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {

                if (pgimInstanciaProces == null) {
                        return;
                }

                // Obtenemos el registro de la supervisión
                PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository
                                .obtenerSupervisionByidInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());

                if (pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_APROBAR_TDR_CREDENCIAL_DEFINIR_ANTECEDENTES)) {
                        // GENERAR REGISTROS ITEMS DE CONSUMO PARA EL ESTADIO DE COMPROMETIDO

                        // Consultar si la supervisión no es propia
                        if (pgimSupervisionDTO.getFlPropia().equals("0")) {

                                // Obtenemnos el registro de consumo de contrato actual
                                List<PgimConsumoContraDTO> lConsumoContraDtoActual = this.consumoContraRepository
                                                .obtenerMontoConsumo(pgimSupervisionDTO.getIdSupervision());

                                if (lConsumoContraDtoActual.size() == 0 || lConsumoContraDtoActual.size() > 1) {
                                        throw new PgimException(TipoResultado.WARNING,
                                                        "No se ha podido obtener el registro de consumo de contrato actual."); // STORY:
                                                                                                                               // PGIM-6667:
                                                                                                                               // Mejora
                                                                                                                               // de
                                                                                                                               // la
                                                                                                                               // gestión
                                                                                                                               // de
                                                                                                                               // mensajes
                                                                                                                               // para
                                                                                                                               // el
                                                                                                                               // usuario
                                }

                                // Obtenemos el objeto PgimConsumoContra
                                PgimConsumoContra pgimConsumoContra = this.consumoContraRepository
                                                .findById(lConsumoContraDtoActual.get(0).getIdConsumoContra())
                                                .orElse(null);

                                // obtener lista de items de consumo vigentes (anteriores)
                                List<PgimItemConsumoDTO> lPgimItemConsumoDTOanterior = this.itemConsumoContratoRepository
                                                .obtenerListaItemConsumoVigentes(
                                                                lConsumoContraDtoActual.get(0).getIdConsumoContra());
                                // setear como no vigentes los items de consumo anteriores
                                for (PgimItemConsumoDTO pgimItemConsumoDTO : lPgimItemConsumoDTOanterior) {
                                        PgimItemConsumo pgimItemConsumoAnterior = this.itemConsumoContratoRepository
                                                        .findById(pgimItemConsumoDTO.getIdItemConsumo()).orElse(null);
                                        pgimItemConsumoAnterior.setEsVigente(ConstantesUtil.IND_INACTIVO);
                                        pgimItemConsumoAnterior.setFeActualizacion(auditoriaDTO.getFecha());
                                        pgimItemConsumoAnterior.setUsActualizacion(auditoriaDTO.getUsername());
                                        pgimItemConsumoAnterior.setIpActualizacion(auditoriaDTO.getTerminal());
                                        this.itemConsumoContratoRepository.save(pgimItemConsumoAnterior);
                                }

                                // creamos nuevos items de consumo para el nuevo estadio
                                Long cantidadDiasEntre = this.contratoService
                                                .diasCalendarioSupervision(pgimSupervisionDTO, false);
                                PgimCostoUnitarioDTO pgimCostoUnitarioDTO = this.contratoService
                                                .obtenerCostoUnitario(pgimSupervisionDTO, false, cantidadDiasEntre);

                                this.crearItemsConsumoContrato(pgimConsumoContra,
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.ESCON_CMPRMTDO.toString()),
                                                pgimCostoUnitarioDTO, BigDecimal.valueOf(1), auditoriaDTO);

                        }

                } else if (pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_COORD_SUPER_CANCELAR_SUPER)
                                || pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RELACION_GENERA_TDR_CREDENCIAL_CANCELAR_SUPER)
                                || pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_GESTIONAR_SALDO_CANCELAR_SUPER)
                                || pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RELACION_DEFINIR_ANTECEDENTES_CANCELAR_SUPER)
                                || pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RELACION_APROBAR_REV_ANTECEDENTES_CANCELAR_SUPER)) {

                        // GENERAR REGISTROS ITEMS DE CONSUMO PARA LA SUPERVISION CANCELADA

                        // Consultar si la supervisión no es propia
                        if (pgimSupervisionDTO.getFlPropia().equals("0")) {

                                // Consultamos si ya se ha registrado la relación
                                // PARAM_RELACION_APROBAR_TDR_CREDENCIAL_DEFINIR_ANTECEDENTES para la instancia
                                // de proceso
                                List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTOhist = this.instanciaPasoRepository
                                                .obtenerInstanciaPasosXrelacion(
                                                                pgimInstanciaProces.getIdInstanciaProceso(),
                                                                ConstantesUtil.PARAM_RELACION_APROBAR_TDR_CREDENCIAL_DEFINIR_ANTECEDENTES);
                                // Determinamos el estadio actual de consumo del contrato
                                Long idTipoEstadioConsumo = 0L;
                                if (lPgimInstanciaPasoDTOhist.size() > 0) {
                                        idTipoEstadioConsumo = this.valorParametroRepository.obtenerIdValorParametro(
                                                        EValorParametro.ESCON_CMPRMTDO.toString());
                                } else {
                                        idTipoEstadioConsumo = this.valorParametroRepository.obtenerIdValorParametro(
                                                        EValorParametro.ESCON_PRE_CMPRMTDO.toString());
                                }

                                // Obtenemnos el registro de consumo de contrato actual
                                List<PgimConsumoContraDTO> lConsumoContraDtoActual = this.consumoContraRepository
                                                .obtenerMontoConsumo(pgimSupervisionDTO.getIdSupervision());

                                if (lConsumoContraDtoActual.size() == 0 || lConsumoContraDtoActual.size() > 1) {
                                        throw new PgimException(
                                                        TipoResultado.WARNING, // STORY: PGIM-6667: Mejora de la gestión
                                                                               // de mensajes para el usuario
                                                        "No se ha podido obtener el registro de consumo de contrato actual.");
                                }

                                // actualizamos a 0L el monto total del consumo del contrato
                                PgimConsumoContra pgimConsumoContra = this.consumoContraRepository
                                                .findById(lConsumoContraDtoActual.get(0).getIdConsumoContra())
                                                .orElse(null);
                                BigDecimal bd = new BigDecimal("0.0");
                                pgimConsumoContra.setMoConsumoContrato(bd);
                                pgimConsumoContra.setFeActualizacion(auditoriaDTO.getFecha());
                                pgimConsumoContra.setUsActualizacion(auditoriaDTO.getUsername());
                                pgimConsumoContra.setIpActualizacion(auditoriaDTO.getTerminal());
                                this.consumoContraRepository.save(pgimConsumoContra);

                                // obtener lista de items de consumo vigentes (anteriores)
                                List<PgimItemConsumoDTO> lPgimItemConsumoDTOanterior = this.itemConsumoContratoRepository
                                                .obtenerListaItemConsumoVigentes(
                                                                lConsumoContraDtoActual.get(0).getIdConsumoContra());
                                // setear como no vigentes los items de consumo anteriores
                                for (PgimItemConsumoDTO pgimItemConsumoDTO : lPgimItemConsumoDTOanterior) {
                                        PgimItemConsumo pgimItemConsumoAnterior = this.itemConsumoContratoRepository
                                                        .findById(pgimItemConsumoDTO.getIdItemConsumo()).orElse(null);
                                        pgimItemConsumoAnterior.setEsVigente(ConstantesUtil.IND_INACTIVO);
                                        pgimItemConsumoAnterior.setFeActualizacion(auditoriaDTO.getFecha());
                                        pgimItemConsumoAnterior.setUsActualizacion(auditoriaDTO.getUsername());
                                        pgimItemConsumoAnterior.setIpActualizacion(auditoriaDTO.getTerminal());
                                        this.itemConsumoContratoRepository.save(pgimItemConsumoAnterior);
                                }

                                // Creamos nuevos items de consumo
                                Long cantidadDiasEntre = this.contratoService
                                                .diasCalendarioSupervision(pgimSupervisionDTO, false);
                                PgimCostoUnitarioDTO pgimCostoUnitarioDTO = this.contratoService
                                                .obtenerCostoUnitario(pgimSupervisionDTO, false, cantidadDiasEntre);

                                this.crearItemsConsumoContrato(pgimConsumoContra, idTipoEstadioConsumo,
                                                pgimCostoUnitarioDTO, BigDecimal.valueOf(0), auditoriaDTO);
                        }
                }
        }

        @Override
        public List<PgimRelacionPasoDTO> filtrarPasosSiguientes(
                        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimInstanciaPaso pgimInstanciaPasoActual, PgimRelacionPaso pgimRelacionPasoActual) {

                Long idInstanciaProceso = pgimInstanciaPasoActual.getPgimInstanciaProces().getIdInstanciaProceso();
                PgimInstanciaProces pgimInstanciaProces = instanciaProcesRepository.findById(idInstanciaProceso)
                                .orElse(null);

                Long idSupervision = pgimInstanciaProces.getCoTablaInstancia();
                PgimSupervision pgimSupervision = this.supervisionRepository.findById(idSupervision).orElse(null);

                PgimSupervisionDTO pgimSupervisionDTO = new PgimSupervisionDTO();
                pgimSupervisionDTO.setFlPropia(pgimSupervision.getFlPropia());

                lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesPropia(pgimSupervision,
                                lPgimRelacionPasoDTOSiguientes, pgimRelacionPasoActual);

                if (pgimRelacionPasoActual.getIdRelacionPaso().equals(
                                ConstRelacionPasoFiscalizacion.F1_PREPARARINICIOFISCACAMPO_F2_INICIARFISCALIZACIONCAMPO)) {

                        lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesFiscalizacionImpedida(
                                        pgimSupervision,
                                        lPgimRelacionPasoDTOSiguientes, pgimRelacionPasoActual);

                } else if (pgimRelacionPasoActual.getIdRelacionPaso().equals(
                                ConstRelacionPasoFiscalizacion.F1_APROBARREVANTECEDENTES_F1_APRBRINSTRMNTOSMDCION)) {

                        lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesSoloOrigen(
                                        lPgimRelacionPasoDTOSiguientes, pgimRelacionPasoActual);

                } else if (pgimRelacionPasoActual.getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_INISUPCAMP_REPROSUP)
                                || pgimRelacionPasoActual.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_SOLDOCUM_REPROSUP)
                                || pgimRelacionPasoActual.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_MEDICIONES_REPROSUP)
                                || pgimRelacionPasoActual.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RELACION_PRESENTARACTA_REPROSUP)) {

                        lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesSoloOrigen(
                                        lPgimRelacionPasoDTOSiguientes, pgimRelacionPasoActual);

                } else if (pgimRelacionPasoActual.getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RP_APROBAR_CAMBIO_INSTRUM_INICIAR_SUP_CAMPO)
                                || pgimRelacionPasoActual.getIdRelacionPaso()
                                                .equals(ConstantesUtil.PARAM_RP_APROBAR_CAMBIO_INSTRUM_SOLICITAR_DOCUM)
                                || pgimRelacionPasoActual.getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RP_APROBAR_CAMBIO_INSTRUM_REALIZAR_MEDICIONES)
                                || pgimRelacionPasoActual.getIdRelacionPaso().equals(
                                                ConstantesUtil.PARAM_RP_APROBAR_CAMBIO_INSTRUM_PRESENTAR_ACTA_FISC)) {

                        lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesAprobacionCambioInstrum(
                                        lPgimRelacionPasoDTOSiguientes, pgimRelacionPasoActual);

                } else if (pgimRelacionPasoActual.getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME)) {
                        List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository
                                        .obtenerDocumentosDescendentes(idInstanciaProceso, CommonsUtil
                                                        .obtenerIdSubCatInformeSupervByTipoSuperv(pgimSupervisionDTO));
                        int cantDocumentoConformidad = 0;
                        for (PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTO) {
                                PgimDocumentoDTO documentoConformidad = this.documentoRepository
                                                .obtenerDocumentoObsAprob(pgimDocumentoDTO.getIdDocumento(),
                                                                ConstantesUtil.PARAM_SC_FICHA_CONFORMIDAD_INFORME_FISCALIZACION);
                                if (documentoConformidad != null) {
                                        cantDocumentoConformidad++;
                                }
                        }

                        if (cantDocumentoConformidad == 0) {
                                lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                                .filter(pgimRelacionPasoDTO -> {
                                                        return (pgimRelacionPasoDTO.getIdRelacionPaso().equals(ConstRelacionPasoFiscalizacion.F4_REVISARYAPROBARINFSUPE_F3_ELABORARYPRESENTARINFDESUPE)
                                                                || pgimRelacionPasoDTO.getIdRelacionPaso().equals(ConstRelacionPasoFiscalizacion.F4_REVISARYAPROBARINFSUPE_F3_ELBRARINFCRCTRSTCASYCNDCIONESOFRCDAS));
                                                }).collect(Collectors.toList());

                                return lPgimRelacionPasoDTOSiguientes;
                        } else {
                                lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                                .filter(pgimRelacionPasoDTO -> {
                                                        return (pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                        ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD));
                                                }).collect(Collectors.toList());

                                return lPgimRelacionPasoDTOSiguientes;
                        }
                }

                if (pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso()
                                .equals(ConstPasoProcesoSupervision.CONFIRMAR_HECHOS_VER_INFRAC)) {
                        lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesPASoArchivo(
                                        lPgimRelacionPasoDTOSiguientes, pgimRelacionPasoActual, pgimSupervision);
                }

                return lPgimRelacionPasoDTOSiguientes;
        }

        private List<PgimRelacionPasoDTO> filtrarPasosSiguientesFiscalizacionImpedida(PgimSupervision pgimSupervision,
                        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimRelacionPaso pgimRelacionPasoActual) {

                String flNoIniciadaAfiscalizado = (pgimSupervision.getFlNoIniciadaAfiscalizado() == null ? "0"
                                : pgimSupervision.getFlNoIniciadaAfiscalizado());

                if (flNoIniciadaAfiscalizado.equals("1")) {

                        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                        .filter(pgimRelacionPasoDTO -> {
                                                return pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                ConstRelacionPasoFiscalizacion.F2_INICIARFISCALIZACIONCAMPO_F2_REALIZARMEDICIONESYVERIFICARHECHOS);
                                        }).collect(Collectors.toList());

                } else {

                        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                        .filter(pgimRelacionPasoDTO -> {
                                                return !pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                ConstRelacionPasoFiscalizacion.F2_INICIARFISCALIZACIONCAMPO_F2_REALIZARMEDICIONESYVERIFICARHECHOS);
                                        }).collect(Collectors.toList());

                }

                return lPgimRelacionPasoDTOSiguientes;
        }

        private List<PgimRelacionPasoDTO> filtrarPasosSiguientesPASoArchivo(
                        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimRelacionPaso pgimRelacionPasoActual, PgimSupervision pgimSupervision) {

                List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTO = this.hechoConstatadoService
                                .obtenerHechosConstatadosPorTipoCumplimiento(pgimSupervision.getIdSupervision(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.CUMPL_NO.toString()));

                if (lPgimHechoConstatadoDTO.size() == 0) {
                        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                        .filter(pgimRelacionPasoDTO -> {
                                                boolean resultado = false;

                                                resultado = (!(pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARPAS)));

                                                return resultado;
                                        }).collect(Collectors.toList());
                } else {
                        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                        .filter(pgimRelacionPasoDTO -> {
                                                boolean resultado = false;

                                                resultado = (!(pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARARCH))
                                                                && !pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                                ConstantesUtil.PARAM_RELACION_REVISARHC_CONTINUARARCH));

                                                return resultado;
                                        }).collect(Collectors.toList());
                }

                return lPgimRelacionPasoDTOSiguientes;
        }

        private List<PgimRelacionPasoDTO> filtrarPasosSiguientesPropia(PgimSupervision pgimSupervision,
                        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimRelacionPaso pgimRelacionPasoActual) {
                if (pgimSupervision.getFlPropia().equals("1")) {
                        // Entonces debemos quitar la relación
                        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                        .filter(pgimRelacionPasoDTO -> {
                                                boolean resultado = false;

                                                resultado = (!(pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                ConstantesUtil.PARAM_RELACION_COORDSUPER_FIRMARDJI))
                                                                && !pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                                ConstantesUtil.PARAM_RELACION_GENTDR_FIRMARDJI)
                                                                && !pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                                ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME))
                                                                || pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                                ConstantesUtil.PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF);

                                                return resultado;
                                        }).collect(Collectors.toList());

                } else if (pgimSupervision.getFlPropia().equals("0")) {

                        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                        .filter(pgimRelacionPasoDTO -> {
                                                boolean resultado = false;

                                                resultado = (!(pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                ConstantesUtil.PARAM_RELACION_COORSUPER_GENERARTDR))
                                                                && !(pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                                ConstantesUtil.PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF)))
                                                                || pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                                ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME);

                                                return resultado;
                                        }).collect(Collectors.toList());

                }

                return lPgimRelacionPasoDTOSiguientes;

        }

        @Override
        public void actualizarFechasReales(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO) {

                Long idRelacionPasoActual = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                if (idRelacionPasoActual.equals(ConstantesUtil.PARAM_RELACION_PREPARARINICAMPO_INICIARCAMPO)) {

                        PgimSupervision pgimSupervision = this.supervisionRepository
                                        .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                        pgimSupervision.setFeInicioSupervisionReal(pgimSupervision.getFeInicioSupervision());

                        Calendar calendarFechaFinSupervision = Calendar.getInstance();
                        calendarFechaFinSupervision.setTime(pgimSupervision.getFeFinSupervision());
                        calendarFechaFinSupervision.set(Calendar.HOUR_OF_DAY, 23);
                        calendarFechaFinSupervision.set(Calendar.MINUTE, 59);
                        calendarFechaFinSupervision.set(Calendar.SECOND, 59);
                        calendarFechaFinSupervision.set(Calendar.MILLISECOND, 0);

                        pgimSupervision.setFeFinSupervisionReal(calendarFechaFinSupervision.getTime());

                        pgimSupervision.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimSupervision.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

                        this.supervisionRepository.save(pgimSupervision);
                        
                }else if (idRelacionPasoActual.equals(ConstRelacionPasoFiscalizacion.F2_INICIARFISCALIZACIONCAMPO_F1_PREPARARINICIOFISCACAMPO)) {
                        
                                // Actualizamos las fechas reales de la fiscalización a NULL
                                PgimSupervision pgimSupervision = this.supervisionRepository
                                            .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);
                                        
                                pgimSupervision.setFeInicioSupervisionReal(null);
                                pgimSupervision.setFeFinSupervisionReal(null);
                                
                                pgimSupervision.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimSupervision.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimSupervision.setIpActualizacion(auditoriaDTO.getTerminal());
                        
                        this.supervisionRepository.save(pgimSupervision);                	
                        
                }
        }

        /**
         * Filtra los pasos siguientes en el marco de la reprogramación de una
         * supervisión.
         * 
         * @param lPgimRelacionPasoDTOSiguientes
         * @param pgimRelacionPasoActual
         * @return
         */
        private List<PgimRelacionPasoDTO> filtrarPasosSiguientesSoloOrigen(
                        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimRelacionPaso pgimRelacionPasoActual) {

                lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream().filter(pgimRelacionPasoDTO -> {
                        PgimRelacionPaso pgimRelacionPasoAverficar = this.relacionPasoRepository
                                        .findById(pgimRelacionPasoDTO.getIdRelacionPaso()).orElse(null);

                        return pgimRelacionPasoAverficar.getPasoProcesoDestino().getIdPasoProceso()
                                        .equals(pgimRelacionPasoActual.getPasoProcesoOrigen().getIdPasoProceso());
                }).collect(Collectors.toList());

                return lPgimRelacionPasoDTOSiguientes;
        }

        /**
         * Filtra los pasos siguientes en el marco de la aprobación del cambio
         * de instrumentos de medición.
         * 
         * @param lPgimRelacionPasoDTOSiguientes
         * @param pgimRelacionPasoActual
         * @return
         */
        private List<PgimRelacionPasoDTO> filtrarPasosSiguientesAprobacionCambioInstrum(
                        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimRelacionPaso pgimRelacionPasoActual) {

                lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream().filter(pgimRelacionPasoDTO -> {
                        PgimRelacionPaso pgimRelacionPasoAverficar = this.relacionPasoRepository
                                        .findById(pgimRelacionPasoDTO.getIdRelacionPaso()).orElse(null);

                        return pgimRelacionPasoAverficar.getPasoProcesoDestino().getIdPasoProceso()
                                        .equals(pgimRelacionPasoActual.getPasoProcesoOrigen().getIdPasoProceso());
                }).collect(Collectors.toList());

                return lPgimRelacionPasoDTOSiguientes;
        }

        @Override
        public Page<PgimSupervisionAuxDTO> listarSeguimientosSupervisiones(Long idContrato,
                        PgimSupervisionDTO filtroSupervisionDTO, Pageable paginador, AuditoriaDTO auditoriaDTO) {
                String username = "";
                // if (filtroSupervisionDTO.getDescFlagMiInteres().equals("1")) {
                // username = auditoriaDTO.getUsername();
                // }
                Page<PgimSupervisionAuxDTO> pPgimSupervisionDTO = this.supervisionRepository
                                .listarSeguimientosSupervisiones(idContrato,
                                                filtroSupervisionDTO.getDescCoUnidadMinera(),
                                                filtroSupervisionDTO.getDescNoUnidadMinera(),
                                                filtroSupervisionDTO.getDescCoDocumentoIdentidad(),
                                                filtroSupervisionDTO.getDescNoRazonSocial(),
                                                filtroSupervisionDTO.getIdProgramaSupervision(),
                                                filtroSupervisionDTO.getIdSubtipoSupervision(),
                                                filtroSupervisionDTO.getCoSupervision(),
                                                filtroSupervisionDTO.getDescNuExpedienteSiged(),
                                                filtroSupervisionDTO.getDescIdFaseProceso(),
                                                filtroSupervisionDTO.getDescIdPasoProceso(),
                                                filtroSupervisionDTO.getDescPersonaDestino(),
                                                filtroSupervisionDTO.getDescNoResponsable(),
                                                filtroSupervisionDTO.getDescUsuarioAsignado(), username,
                                                filtroSupervisionDTO.getTextoBusqueda(), paginador);
                return pPgimSupervisionDTO;
        }

        @Override
        public void completarConInicioPAS(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO) throws Exception {

                Long idRelacion = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                if (idRelacion.equals(ConstantesUtil.PARAM_RP_COMPLETAR_FISC_PAS__FISC_COMPLETADA_PAS)) {
                        Long idEquipoInstanciaPro = pgimInstanciaPaso.getPersonaEqpDestino().getIdEquipoInstanciaPro();
                        PgimEqpInstanciaPro pEqpInstanciaPro = this.equipoInstanciaProcesoRepository
                                        .findById(idEquipoInstanciaPro).orElse(null);
                        Long idPersonalOsi = pEqpInstanciaPro.getPgimPersonalOsi().getIdPersonalOsi();

                        this.pasService.iniciarPAS(idPersonalOsi, pgimInstanciaProces.getCoTablaInstancia(),
                                        auditoriaDTO);
                }
        }

        @Override
        public void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

                this.generarCuadroVerificacion(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.asegurarFlagRiesgoSupervision(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.procesarSupervisionFallida(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.actualizarFechasReales(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.completarConInicioPAS(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                // Actualizar los hechos constatados
                this.hechoConstatadoService.actualizarHCxSupervision(pgimInstanciaProces, pgimInstanciaPaso,
                                auditoriaDTO);

                // Actualizar el consumo del contrato
                this.actualizarConsumoContrato(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.copiarRepresentantesParaActaRecepcion(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.copiarRepresentantesParaActaFiscalizacion(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.copiarRepresentantesParaActaRequerimientoYRecepcion(pgimInstanciaProces, pgimInstanciaPaso,
                                auditoriaDTO);

                this.registrarExitoDeAgol(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.asegurarEstadoInstrumentosFisc(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.configurarFechaEnvio(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

                this.asegurarHistoricoInfracciones(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
                
                this.persistirIdAgenteFiscalizado(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
                
                this.persistirRazonSocialAgenteFiscalizado(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
                
                this.rechazarInformeFiscalizacion(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
                
                //- Los informes de fiscalización propia ya no serán numerados por la PGIM, sino por el Siged en el momento de la firma digital.
                // this.documentoService.enumerarDocumentoSigedEnFlujo(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
        }

        /**
         * Permite configurar la fecha de envío de los documentos que estén configurados
         * para envío según la relación de pasos.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @throws ParseException 
         */
        private void configurarFechaEnvio(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws ParseException {

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository.listarDocumentosParaActualizarEnvio(idRelacionPaso, pgimInstanciaProces.getIdInstanciaProceso());
                
                Long idInstanciaProceso = pgimInstanciaProces.getIdInstanciaProceso();
                
                PgimSupervisionDTO pgimSupervisionDTOPropia = this.supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
                	
                	if (pgimSupervisionDTOPropia.getFlPropia().equals("0")) {
                		
                		/**
                         * ---------------------------------------------------
                         * -----------------------PGIM-11133----------------------------
                         * ---------------------------------------------------
                         */
                        // obtener los documentos y ordenarlos de manera descendente los id
                        List<PgimDocumentoDTO> listaDocsOrdenada = lPgimDocumentoDTO.stream().sorted(Comparator.comparing(PgimDocumentoDTO::getIdDocumento).reversed()).collect(Collectors.toList());

                        List<PgimDocumentoDTO> lDocumentoInforme = this.documentoRepository.obtenerDocumentoInformeSupervisionAux(pgimInstanciaProces.getIdInstanciaProceso(), ConstantesUtil.PARAM_SC_INFORME_SUPERVISION);
                        
                        List<PgimDocumentoDTO> documentosInformeIncumplido = lDocumentoInforme.stream()
                                        .filter(documentoOrden -> "1".equals(documentoOrden.getFlIncumplimientoET()))
                                        .collect(Collectors.toList());

                        if(listaDocsOrdenada.size() > 0 && documentosInformeIncumplido.size() == 0){

                                List<PgimDocumentoDTO> listaDocsInformeOrdenada = lDocumentoInforme.stream()
                                                .sorted(Comparator.comparing(PgimDocumentoDTO::getIdDocumento).reversed())
                                                .collect(Collectors.toList());
                                
                             if (listaDocsInformeOrdenada.size() != 0 ) {
                                	
                                PgimDocumentoDTO informeDocActual = listaDocsInformeOrdenada.get(0);

                                // obtener la ficha de revisión del documento mas reciente
                                PgimFichaRevisionDTO fichaRevision = this.fichaRevisionRepository.obtenerFichaRevisionPorIdDocumento(listaDocsOrdenada.get(0).getIdDocumento());

                                List<PgimFichaObservacionDTO> documentosFichaObsFiltrados = null;

                                // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                                if (fichaRevision != null) {

                                        // obtener ficha de observaciones
                                        List<PgimFichaObservacionDTO> lFichaObservacion = this.fichaObservacionRepository.obtenerFichaObservacionPorIdFichaRevision(fichaRevision.getIdFichaRevision());

                                        // obtener ficha de observaciones que no estan subsanadas
                                        documentosFichaObsFiltrados = lFichaObservacion.stream().filter(documento -> "0".equals(documento.getFlSubsanada())).collect(Collectors.toList());

                                        PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByidInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());

                                        PgimContratoDTO pgimContratoDTO = this.contratoRepository.obtenerContratoPorId(pgimSupervisionDTO.getDescIdContrato());

                                        if (documentosFichaObsFiltrados.size() != 0  ) {

                                                Date fechaPresentacion = fichaRevision.getFePresentacion();
                                                
                                                	Date fechaPresentacionCalculado = sumarDias(fechaPresentacion, pgimContratoDTO.getNuDiasRevisionInforme());
                                                    // Date fechaDeFuego = formatter.parse("10/12/2024"); // auditoriaDTO.getFecha() = fecha de envío de la observación no conformidad por el ET

                                                    // fecha de presentación del informa + 2 dias según contrato quien va revisar el ET es menor que la fecha de envio de observaciones
                                                    if (fechaPresentacionCalculado.before(auditoriaDTO.getFecha())) {// auditoriaDTO.getFecha()

                                                            PgimDocumento informeFiscalziacionActualizar = this.documentoRepository.findById(informeDocActual.getIdDocumento()).orElse(null);

                                                            // Si es "1" = "Incumple" | "0" = "Cumple"
                                                            informeFiscalziacionActualizar.setFlIncumplimientoET("1");

                                                            informeFiscalziacionActualizar.setFeActualizacion(auditoriaDTO.getFecha());
                                                            informeFiscalziacionActualizar.setUsActualizacion(auditoriaDTO.getUsername());
                                                            informeFiscalziacionActualizar.setIpActualizacion(auditoriaDTO.getTerminal());

                                                            this.documentoRepository.save(informeFiscalziacionActualizar);
                                                    }
                                                
                                        }
                                }
                            }
                        }
                	
                        /**
                         * ---------------------------------------------------
                         * -----------------------Fin----------------------------
                         * ---------------------------------------------------
                         */
                        
                	}

                

                PgimDocumento pgimDocumento = null;

                for (PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTO) {
                        pgimDocumento = this.documentoRepository.findById(pgimDocumentoDTO.getIdDocumento()).orElse(null);
                        if (pgimDocumento == null) {
                                continue;
                        }

                        PgimRelacionSubcatDTO pgimRelacionSubcatDTO = this.relacionSubcatRepository.obtenerConfigRelacionSubcat(pgimDocumento.getPgimSubcategoriaDoc().getIdSubcatDocumento(), idRelacionPaso);

                        if (pgimDocumento.getFeEnvioDocumento() != null && pgimRelacionSubcatDTO.getFlActualizarFechaEnvio().equals("0")) {
                                continue;
                        }

                        pgimDocumento.setFeEnvioDocumento(auditoriaDTO.getFecha());

                        pgimDocumento.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimDocumento.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimDocumento.setIpActualizacion(auditoriaDTO.getTerminal());

                        this.documentoRepository.save(pgimDocumento);
                }
        }

        public Date sumarDias(Date fecha, int dias) {
                LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate nuevaFecha = localDate.plusDays(dias);
                return Date.from(nuevaFecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        private void copiarRepresentantesParaActaRecepcion(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                if (!idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_SOLICITARDOC_REALIZARMEDICIONES)) {
                        return;
                }

                PgimSupervision pgimSupervision = this.supervisionRepository
                                .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                List<PgimInvolucradoSupervDTO> pgimInvolucradoSupervDTO = this.involucradoSupervRepository
                                .listarInvolucradoSupervision(pgimSupervision.getIdSupervision(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.ACINV_ACTA_RCPCION_DCMNTCION
                                                                                .toString()));

                if (pgimInvolucradoSupervDTO.size() > 0) {
                        // Nada que hacer debido a que el usuario ya ingreso información.
                        return;
                }

                List<PgimInvolucradoSupervDTO> pgimInvolucradoSupervDTOReq = this.involucradoSupervRepository
                                .listarInvolucradoSupervision(pgimSupervision.getIdSupervision(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.ACINV_ACTA_RQUERMIENTO_DCMNTCION
                                                                                .toString()));

                this.copiarPersonas(pgimInvolucradoSupervDTOReq, pgimSupervision.getIdSupervision(),
                                this.valorParametroRepository.obtenerIdValorParametro(
                                                EValorParametro.ACINV_ACTA_RCPCION_DCMNTCION.toString()),
                                auditoriaDTO);
        }

        private void copiarRepresentantesParaActaFiscalizacion(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                if (!idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REALIZARMEDICIONES_PRESENTARACTAFISC)) {
                        return;
                }

                PgimSupervision pgimSupervision = this.supervisionRepository
                                .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                List<PgimInvolucradoSupervDTO> pgimInvolucradoSupervDTO = this.involucradoSupervRepository
                                .listarInvolucradoSupervision(pgimSupervision.getIdSupervision(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.ACINV_ACTA_SPRVSION.toString()));

                if (pgimInvolucradoSupervDTO.size() > 0) {
                        // Nada que hacer debido a que el usuario ya ingreso información.
                        return;
                }

                List<PgimInvolucradoSupervDTO> pgimInvolucradoSupervDTOReq = this.involucradoSupervRepository
                                .listarInvolucradoSupervision(pgimSupervision.getIdSupervision(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.ACINV_ACTA_INCIO.toString()));

                this.copiarPersonas(pgimInvolucradoSupervDTOReq, pgimSupervision.getIdSupervision(),
                                this.valorParametroRepository.obtenerIdValorParametro(
                                                EValorParametro.ACINV_ACTA_SPRVSION.toString()),
                                auditoriaDTO);
        }

        /**
         * Permite copiar las personas involucradas en la acta de inicio hacia las actas
         * de requerimiento de documentación
         * y requerimiento de documentación siempre y cuando estas actas no tengan aún
         * involucrados.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         */
        private void copiarRepresentantesParaActaRequerimientoYRecepcion(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                if (!idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_INICIASUPERV_SOLDOCCAMPO)) {
                        return;
                }

                PgimSupervision pgimSupervision = this.supervisionRepository
                                .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                List<PgimInvolucradoSupervDTO> pgimInvolucradoSupervDTOReq = this.involucradoSupervRepository
                                .listarInvolucradoSupervision(pgimSupervision.getIdSupervision(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.ACINV_ACTA_INCIO.toString()));

                // copiar las personas involucrado de Acta de incio hacia la acta de
                // requerimiento de documentación
                List<PgimInvolucradoSupervDTO> pgimInvolucradoReqDoc = this.involucradoSupervRepository
                                .listarInvolucradoSupervision(pgimSupervision.getIdSupervision(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.ACINV_ACTA_RQUERMIENTO_DCMNTCION
                                                                                .toString()));

                if (pgimInvolucradoReqDoc.size() == 0) {
                        this.copiarPersonas(pgimInvolucradoSupervDTOReq, pgimSupervision.getIdSupervision(),
                                        this.valorParametroRepository.obtenerIdValorParametro(
                                                        EValorParametro.ACINV_ACTA_RQUERMIENTO_DCMNTCION.toString()),
                                        auditoriaDTO);
                }

                // copiar las personas involucrado de Acta de incio hacia la acta de
                // requerimiento de documentación
                List<PgimInvolucradoSupervDTO> pgimInvolucradoRecpDoc = this.involucradoSupervRepository
                                .listarInvolucradoSupervision(pgimSupervision.getIdSupervision(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.ACINV_ACTA_RCPCION_DCMNTCION
                                                                                .toString()));

                if (pgimInvolucradoRecpDoc.size() == 0) {
                        this.copiarPersonas(pgimInvolucradoSupervDTOReq, pgimSupervision.getIdSupervision(),
                                        this.valorParametroRepository.obtenerIdValorParametro(
                                                        EValorParametro.ACINV_ACTA_RCPCION_DCMNTCION.toString()),
                                        auditoriaDTO);
                }

        }

        private void copiarPersonas(List<PgimInvolucradoSupervDTO> pgimInvolucradoSupervDTOReq, Long idSupervision,
                        Long idTipoActaInvolucrado, AuditoriaDTO auditoriaDTO) {

                PgimInvolucradoSuperv pgimInvolucradoSuperv = null;
                for (PgimInvolucradoSupervDTO pgimInvolucradoSupervDTOIter : pgimInvolucradoSupervDTOReq) {
                        pgimInvolucradoSuperv = new PgimInvolucradoSuperv();

                        pgimInvolucradoSuperv.setPgimPersona(new PgimPersona());
                        pgimInvolucradoSuperv.getPgimPersona()
                                        .setIdPersona(pgimInvolucradoSupervDTOIter.getIdPersona());

                        pgimInvolucradoSuperv.setPgimSupervision(new PgimSupervision());
                        pgimInvolucradoSuperv.getPgimSupervision().setIdSupervision(idSupervision);

                        pgimInvolucradoSuperv.setTipoInvolucrado(new PgimValorParametro());
                        pgimInvolucradoSuperv.getTipoInvolucrado()
                                        .setIdValorParametro(pgimInvolucradoSupervDTOIter.getIdTipoInvolucrado());

                        pgimInvolucradoSuperv.setTipoActaInvolucrado(new PgimValorParametro());
                        pgimInvolucradoSuperv.getTipoActaInvolucrado()
                                        .setIdValorParametro(idTipoActaInvolucrado);

                        pgimInvolucradoSuperv.setTipoPrefijoInvolucrado(new PgimValorParametro());
                        pgimInvolucradoSuperv.getTipoPrefijoInvolucrado().setIdValorParametro(
                                        pgimInvolucradoSupervDTOIter.getIdTipoPrefijoInvolucrado());

                        pgimInvolucradoSuperv.setDeCargo(pgimInvolucradoSupervDTOIter.getDeCargo());

                        pgimInvolucradoSuperv.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimInvolucradoSuperv.setFeCreacion(auditoriaDTO.getFecha());
                        pgimInvolucradoSuperv.setUsCreacion(auditoriaDTO.getUsername());
                        pgimInvolucradoSuperv.setIpCreacion(auditoriaDTO.getTerminal());

                        this.involucradoSupervRepository.save(pgimInvolucradoSuperv);
                }
        }

        private void asegurarFlagRiesgoSupervision(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                if (!idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME) &&
                                !idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF)) {
                        return;
                }

                PgimSupervision pgimSupervision = this.supervisionRepository
                                .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                if (!pgimSupervision.getPgimMotivoSupervision().getTipoSupervision().getIdValorParametro()
                                .equals(this.valorParametroRepository
                                                .obtenerIdValorParametro(
                                                                EValorParametro.SUPER_FSCLZCION_PRGRMDA.toString()))) {
                        return;
                }

                PgimUnidadMinera pgimUnidadMinera = this.unidadMineraRepository
                                .findById(pgimSupervision.getPgimUnidadMinera().getIdUnidadMinera())
                                .orElse(null);

                pgimSupervision.setFlRegistraRiesgos(pgimUnidadMinera.getFlRegistraRiesgos());
                pgimSupervision.setFeActualizacion(auditoriaDTO.getFecha());
                pgimSupervision.setUsActualizacion(auditoriaDTO.getUsername());
                pgimSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

                this.supervisionRepository.save(pgimSupervision);
        }

        private void procesarSupervisionFallida(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {

                PgimSupervision pgimSupervision = this.supervisionRepository
                                .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                if (!idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_FIRMARMEMO_COMPLETARSUPFALLIDA)) {
                        return;
                }

                if (pgimSupervision.getFlPropia().equals("1")) {
                        return;
                }

                List<PgimConsumoContra> lPgimConsumoContra = this.consumoContraRepository
                                .findByPgimSupervisionAndEsRegistro(pgimSupervision, "1");

                if (lPgimConsumoContra.size() == 0) {
                        String mensajeExcepcion = String.format(
                                        "No se ha encontrado ningún consumo de contrato registrado para la supervisión con identificador interno %d",
                                        pgimSupervision.getIdSupervision());
                        throw new PgimException(TipoResultado.WARNING, mensajeExcepcion); // STORY: PGIM-6667: Mejora de
                                                                                          // la gestión de mensajes para
                                                                                          // el usuario

                } else if (lPgimConsumoContra.size() > 1) {
                        String mensajeExcepcion = String.format(
                                        "Se ha encontrado más de un consumo de contrato registrado para la supervisión con identificador interno %d",
                                        pgimSupervision.getIdSupervision());
                        throw new PgimException(TipoResultado.WARNING, mensajeExcepcion); // STORY: PGIM-6667: Mejora de
                                                                                          // la gestión de mensajes para
                                                                                          // el usuario
                }

                PgimConsumoContra pgimConsumoContra = lPgimConsumoContra.get(0);

                List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO = this.tarifarioContratoService
                                .listarTarifariosCumplenRegla(pgimConsumoContra.getPgimContrato().getIdContrato(),
                                                pgimSupervision.getPgimSubtipoSupervision().getIdSubtipoSupervision(),
                                                pgimSupervision.getPgimMotivoSupervision().getIdMotivoSupervision());

                PgimTarifarioReglaDTO pgimTarifarioReglaDTO = null;

                if (lPgimTarifarioReglaDTO.size() == 1) {
                        pgimTarifarioReglaDTO = lPgimTarifarioReglaDTO.get(0);
                } else if (lPgimTarifarioReglaDTO.size() == 0) {
                        throw new PgimException(TipoResultado.WARNING, // STORY: PGIM-6667: Mejora de la gestión de
                                                                       // mensajes para el usuario
                                        "No se ha encontrado ninguna regla válida para determinar el tarifario del contrato seleccionado, por favor coordine con el Especialista Administrativo del contrato");
                } else {
                        throw new PgimException(TipoResultado.WARNING, // STORY: PGIM-6667: Mejora de la gestión de
                                                                       // mensajes para el usuario
                                        "Se ha encontrado más de una regla válida para determinar el tarifario del contrato seleccionado, por favor coordine con el Especialista Administrativo del contrato");
                }

                Long idTarifarioContrato = pgimTarifarioReglaDTO.getIdTarifarioContrato();

                PgimTarifarioContratoDTO pgimTarifarioContratoDTO = this.tarifarioContratoService
                                .obtenerTarifarioContratoPorId(idTarifarioContrato);

                if (pgimTarifarioContratoDTO.getMoSupervisionFallida() == null) {
                        String mensajeExcepcion = String.format(
                                        "No se ha definido el monto de la supervisión fallida para el tarifario de nombre '%s'",
                                        pgimTarifarioContratoDTO.getNoTarifario());
                        throw new PgimException(TipoResultado.WARNING, mensajeExcepcion); // STORY: PGIM-6667: Mejora de
                                                                                          // la gestión de mensajes para
                                                                                          // el usuario
                }

                pgimConsumoContra.setMoConsumoContrato(pgimTarifarioContratoDTO.getMoSupervisionFallida());

                pgimConsumoContra.setFeActualizacion(auditoriaDTO.getFecha());
                pgimConsumoContra.setUsActualizacion(auditoriaDTO.getUsername());
                pgimConsumoContra.setIpActualizacion(auditoriaDTO.getTerminal());

                this.consumoContraRepository.save(pgimConsumoContra);

                List<PgimItemConsumo> lPgimItemConsumo = this.itemConsumoContratoRepository
                                .findByPgimConsumoContraAndEsVigente(pgimConsumoContra, "1");

                for (PgimItemConsumo pgimItemConsumo : lPgimItemConsumo) {
                        pgimItemConsumo.setEsVigente("0");

                        pgimItemConsumo.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimItemConsumo.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimItemConsumo.setIpActualizacion(auditoriaDTO.getTerminal());

                        this.itemConsumoContratoRepository.save(pgimItemConsumo);
                }

                PgimItemConsumo pgimItemConsumoSupFallida = new PgimItemConsumo();

                pgimItemConsumoSupFallida.setPgimConsumoContra(pgimConsumoContra);
                pgimItemConsumoSupFallida.setTipoEntregable(new PgimValorParametro());
                pgimItemConsumoSupFallida.getTipoEntregable()
                                .setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(
                                                EValorParametro.ENTRE_INFRMES_SPRVSION_FLLDA.toString()));

                pgimItemConsumoSupFallida.setTipoEstadioConsumo(new PgimValorParametro());
                pgimItemConsumoSupFallida.getTipoEstadioConsumo()
                                .setIdValorParametro(this.valorParametroRepository
                                                .obtenerIdValorParametro(EValorParametro.ESCON_CMPRMTDO.toString()));

                pgimItemConsumoSupFallida.setPcEntregable(0L);
                pgimItemConsumoSupFallida.setMoItemConsumo(pgimConsumoContra.getMoConsumoContrato());
                pgimItemConsumoSupFallida.setEsVigente("1");

                pgimItemConsumoSupFallida.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimItemConsumoSupFallida.setFeCreacion(auditoriaDTO.getFecha());
                pgimItemConsumoSupFallida.setUsCreacion(auditoriaDTO.getUsername());
                pgimItemConsumoSupFallida.setIpCreacion(auditoriaDTO.getTerminal());

                this.itemConsumoContratoRepository.save(pgimItemConsumoSupFallida);
        }

        private void registrarExitoDeAgol(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();

                PgimSupervisionAgolDTO pgimSupervisionAgolDTO = new PgimSupervisionAgolDTO();
                pgimSupervisionAgolDTO.setIdSupervision(pgimInstanciaProces.getCoTablaInstancia());

                if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_SOLICITARDOC_REALIZARMEDICIONES)) {

                        this.insertarSupervisionAgol(pgimSupervisionAgolDTO,
                                        ConstantesUtil.PARAM_SURVEY_ESTADO_SUPERV_INICIADA, auditoriaDTO);
                }

                if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_PREACTASUPER_ELAINFO)) {

                        this.insertarSupervisionAgol(pgimSupervisionAgolDTO,
                                        ConstantesUtil.PARAM_SURVEY_ESTADO_SUPERV_FINALIZADA, auditoriaDTO);
                }
        }

        /**
         * Permite listar las supervisiones correspondientes a un contrato cuyos montos
         * de consumo de contrato se encuentran en el estadio "pre-comprometido"
         * 
         * @param idContrato
         * @param paginador
         * @return
         */
        @Override
        public Page<PgimSupervisionAuxDTO> listarSupervisionesPrecomprometidasXcontrato(Long idContrato,
                        Pageable paginador) {

                Page<PgimSupervisionAuxDTO> pPgimSupervisionDTO = this.supervisionRepository
                                .listarSupervisionesPrecomprometidasXcontrato(idContrato, paginador,
                                                EValorParametro.ESCON_PRE_CMPRMTDO.toString());

                for (PgimSupervisionAuxDTO pgimSupervisionAuxDTO : pPgimSupervisionDTO) {

                        String nombreResponsables = "";
                        List<PgimPersonaDTO> lResponsables = this.personaRepository.listarResponsablesXinstanciaProc(
                                        pgimSupervisionAuxDTO.getIdInstanciaProceso());

                        for (PgimPersonaDTO responsable : lResponsables) {

                                if (nombreResponsables == "") {
                                        nombreResponsables = responsable.getNoPersona();
                                } else {
                                        nombreResponsables = nombreResponsables + ", " + responsable.getNoPersona();
                                }
                        }

                        pgimSupervisionAuxDTO.setDescResponsable(nombreResponsables);
                }

                return pPgimSupervisionDTO;
        }

        @Override
        public PgimFichaRevisionDTO obtenerFichaRevision(Long idItemLiquidacion, Long idTipoEntregable) {

                PgimItemLiquidacion pgimItemLiquidacion = this.itemLiquidacionRepository.findById(idItemLiquidacion)
                                .orElse(null);
                PgimSupervision pgimSupervision = pgimItemLiquidacion.getPgimItemConsumo().getPgimConsumoContra()
                                .getPgimSupervision();
                PgimInstanciaProces pgimInstanciaProces = pgimSupervision.getPgimInstanciaProces();

                List<PgimFichaRevisionDTO> lPgimFichaRevisionDTO = this.fichaRevisionRepository
                                .obtenerFichaRevisionPorInstanciaProcess(pgimInstanciaProces.getIdInstanciaProceso());

                PgimFichaRevisionDTO pgimFichaRevisionDTO = null;
                if (lPgimFichaRevisionDTO.size() == 1) {
                        pgimFichaRevisionDTO = lPgimFichaRevisionDTO.get(0);
                } else {
                        throw new PgimException("error",
                                        "Ya existe una ficha de conformidad de informe de supervisión, solo debe existir una por supervisión");
                }

                return pgimFichaRevisionDTO;
        }

        @Override
        public PgimContratoDTO obtenerContrato(Long idItemLiquidacion) {

                PgimItemLiquidacion pgimItemLiquidacion = this.itemLiquidacionRepository.findById(idItemLiquidacion)
                                .orElse(null);

                PgimContrato pgimContrato = this.contratoRepository.findById(pgimItemLiquidacion.getPgimItemConsumo()
                                .getPgimConsumoContra().getPgimContrato().getIdContrato()).orElse(null);

                PgimContratoDTO pgimContratoDTO = new PgimContratoDTO();

                BeanUtils.copyProperties(pgimContrato, pgimContratoDTO);

                return pgimContratoDTO;
        }

        @Override
        public PgimItemConsumoDTO obtenerItemConsumo(Long idItemLiquidacion) {
                PgimItemLiquidacion pgimItemLiquidacion = this.itemLiquidacionRepository.findById(idItemLiquidacion)
                                .orElse(null);

                PgimItemConsumo pgimItemConsumo = this.itemConsumoContratoRepository
                                .findById(pgimItemLiquidacion.getPgimItemConsumo().getIdItemConsumo()).orElse(null);

                PgimItemConsumoDTO pgimItemConsumoDTO = new PgimItemConsumoDTO();

                BeanUtils.copyProperties(pgimItemConsumo, pgimItemConsumoDTO);

                return pgimItemConsumoDTO;
        }

        @Override
        public List<PgimSubcategoriaDocDTO> filtrarSubCategoriasDoc(Long coTablaInstancia,
                        List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO) throws Exception {

                PgimSupervision pgimSupervision = this.supervisionRepository.findById(coTablaInstancia).orElse(null);

                Long idInstanciaProceso = pgimSupervision.getPgimInstanciaProces().getIdInstanciaProceso();

                PgimInstanciaPasoDTO pgimInstanciaPasoDTO = this.flujoTrabajoService
                                .obtenerInstanciaPasoActualPorIdInstanciaPaso(idInstanciaProceso);

                PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository
                                .findById(pgimInstanciaPasoDTO.getIdInstanciaPaso()).orElse(null);

                Long idPasoProceso = pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso();
                Long idFaseProceso = pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino()
                                .getPgimFaseProceso().getIdFaseProceso();

                Long idSubcatDocumentoFiltro = null;

                if (idFaseProceso.equals(ConstantesUtil.PARAM_SUPERVISION_POST_SUPERVISION_CAMPO)) {
                        long idSubCatInformeSupervisionFallida = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_FALLIDA_PROPIA;

                        PgimSupervisionDTO pgimSupervisionDTO = new PgimSupervisionDTO();
                        pgimSupervisionDTO.setFlPropia("1");

                        if (pgimSupervision.getFlPropia().equals("1")) {
                                idSubCatInformeSupervisionFallida = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_FALLIDA;
                                pgimSupervisionDTO.setFlPropia("0");
                        }

                        long[] idSubcatDocumentoFiltroEliminar = {
                                        CommonsUtil.obtenerIdSubCatInformeSupervByTipoSuperv(pgimSupervisionDTO),
                                        idSubCatInformeSupervisionFallida };

                        lPgimSubcategoriaDocDTO = this.eliminarItemSubCategoriasPorIdSubcat(lPgimSubcategoriaDocDTO,
                                        idSubcatDocumentoFiltroEliminar);

                }

                if (idPasoProceso.equals(ConstPasoProcesoSupervision.ELABORAR_INFORME_SUPERVISION_FALLIDA)) {

                        idSubcatDocumentoFiltro = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_FALLIDA; // ConstSubCategoriaDocumento.INFORME_DE_SUPERVISION_FALLIDA;
                                                                                                       // // se
                                                                                                       // reemplazo
                                                                                                       // por las const
                                                                                                       // de
                                                                                                       // ConstantesUtil

                        if (pgimSupervision.getFlPropia().equals("1")) {
                                idSubcatDocumentoFiltro = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_FALLIDA_PROPIA;
                        }

                        lPgimSubcategoriaDocDTO = this.filtrarSubCategoriasPorIdSubcat(lPgimSubcategoriaDocDTO,
                                        idSubcatDocumentoFiltro);

                } else if (idPasoProceso.equals(ConstPasoProcesoSupervision.ELABORAR_MEMORANDO_SUPERVISION_FALLIDA)) {
                        idSubcatDocumentoFiltro = ConstSubCategoriaDocumento.MEMORANDO_DE_SUPERVISION_FALLIDA;
                        lPgimSubcategoriaDocDTO = this.filtrarSubCategoriasPorIdSubcat(lPgimSubcategoriaDocDTO,
                                        idSubcatDocumentoFiltro);
                }

                return lPgimSubcategoriaDocDTO;
        }

        private List<PgimSubcategoriaDocDTO> eliminarItemSubCategoriasPorIdSubcat(
                        List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO, long[] idSubcatDocumentoFiltro) {

                for (int i = 0; i < idSubcatDocumentoFiltro.length; i++) {
                        long id = idSubcatDocumentoFiltro[i];
                        lPgimSubcategoriaDocDTO.removeIf(pgimSubcategoriaDocDTO -> pgimSubcategoriaDocDTO
                                        .getIdSubcatDocumento().equals(id));
                }

                return lPgimSubcategoriaDocDTO;
        }

        private List<PgimSubcategoriaDocDTO> filtrarSubCategoriasPorIdSubcat(
                        List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO, final long idSubcatDocumentoFiltro) {
                lPgimSubcategoriaDocDTO = lPgimSubcategoriaDocDTO.stream()
                                .filter(pgimSubcategoriaDocDTO -> pgimSubcategoriaDocDTO.getIdSubcatDocumento()
                                                .equals(idSubcatDocumentoFiltro))
                                .collect(Collectors.toList());

                return lPgimSubcategoriaDocDTO;
        }

        @Override
        public void procesarAccionesAdicionales(PgimDocumentoDTO pgimDocumentoDTO, Long idDocumento,
                        AuditoriaDTO auditoriaDTO) {

                PgimFiltroDocumentoDTO pgimFiltroDocumento = pgimDocumentoDTO.getFiltroDocumento();

                if (pgimFiltroDocumento == null) {
                        return;
                }

                if (pgimFiltroDocumento.getPgimFiltroItemDocumento() == null) {
                        return;
                }

                PgimFiltroItemDocumentoDTO pgimFiltroItemDocumentoDTO = pgimFiltroDocumento
                                .getPgimFiltroItemDocumento()[0];

                String nombreTabla = pgimFiltroItemDocumentoDTO.getNombreTabla();
                Long idItemSolicitudDoc = pgimFiltroItemDocumentoDTO.getIdTabla();

                if (nombreTabla.equals("PGIM_TD_ITEM_SOLICITUD_DOC")) {
                        PgimItemRecepcionDocDTO pgimItemRecepcionDocDTO = new PgimItemRecepcionDocDTO();
                        pgimItemRecepcionDocDTO.setIdItemSolicitudDoc(idItemSolicitudDoc);
                        pgimItemRecepcionDocDTO.setIdDocumento(idDocumento);

                        this.documentoRequeridoService
                                        .crearItemRecepcionDoc(pgimItemRecepcionDocDTO, auditoriaDTO);
                }
        }

        @Override
        public Page<PgimPrgrmSeguimientoAuxDTO> listarProgramaSeguimiento(Long idLineaPrograma,
                        PgimPrgrmSeguimientoAuxDTO filtroPrgrmSeguimientoAuxDTO, Pageable paginador) {

                Page<PgimPrgrmSeguimientoAuxDTO> pPgimSupervisionDTO = this.prgrmSeguimientoAuxRepository
                                .listarProgramaSeguimiento(idLineaPrograma,
                                                filtroPrgrmSeguimientoAuxDTO.getIdFaseActual(),
                                                filtroPrgrmSeguimientoAuxDTO.getIdPasoActual(),
                                                filtroPrgrmSeguimientoAuxDTO.getDescNoResponsable(), paginador);
                return pPgimSupervisionDTO;
        }

        @Override
        public PgimPrgrmSeguimientoAuxDTO obtenerSeguimientoPorSupervisionId(Long idSupervision, Long idLineaPrograma) {
                return this.prgrmSeguimientoAuxRepository.obtenerSeguimientoPorSupervisionId(idSupervision,
                                idLineaPrograma);
        }

        @Override
        public PgimPrgrmSeguimientoAuxDTO obtenerSeguimientoPorItemProgramaId(Long idItemProgramaSupe,
                        Long idLineaPrograma) {
                return this.prgrmSeguimientoAuxRepository.obtenerSeguimientoPorItemProgramaId(idItemProgramaSupe,
                                idLineaPrograma);
        }

        /**
         * Permite registrar la entidad supervisión Agol,
         * en caso ya exista un registro para dicha supervisión solo actualiza su
         * estado.
         * 
         * @param pgimSupervisionAgolDTO
         * @return
         * @throws Exception
         */
        @Transactional(readOnly = false)
        @Override
        public PgimSupervisionAgolDTO insertarSupervisionAgol(PgimSupervisionAgolDTO pgimSupervisionAgolDTO,
                        String flActivo,
                        AuditoriaDTO auditoriaDTO) throws Exception {

                PgimSupervisionAgol pgimSupervisionAgol = null;

                List<PgimSupervisionAgolDTO> lstPgimSupervisionAgolDTOActual = this.supervisionAgolRepository
                                .listarRegistrosPorSupervision(pgimSupervisionAgolDTO.getIdSupervision());

                if (lstPgimSupervisionAgolDTOActual.size() > 0) {
                        PgimSupervisionAgolDTO pgimSupervisionAgolDTOActual = lstPgimSupervisionAgolDTOActual.get(0);
                        pgimSupervisionAgol = this.supervisionAgolRepository
                                        .findById(pgimSupervisionAgolDTOActual.getIdSupervisionAgol()).orElse(null);

                }

                if (pgimSupervisionAgol == null) {
                        pgimSupervisionAgol = new PgimSupervisionAgol();

                        pgimSupervisionAgol.setPgimSupervision(new PgimSupervision());
                        pgimSupervisionAgol.getPgimSupervision()
                                        .setIdSupervision(pgimSupervisionAgolDTO.getIdSupervision());

                        pgimSupervisionAgol.setFlActivo(flActivo);
                        pgimSupervisionAgol.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimSupervisionAgol.setFeCreacion(auditoriaDTO.getFecha());
                        pgimSupervisionAgol.setUsCreacion(auditoriaDTO.getUsername());
                        pgimSupervisionAgol.setIpCreacion(auditoriaDTO.getTerminal());

                } else {
                        pgimSupervisionAgol.setFlActivo(flActivo);
                        pgimSupervisionAgol.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimSupervisionAgol.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimSupervisionAgol.setIpActualizacion(auditoriaDTO.getTerminal());
                }

                PgimSupervisionAgol pgimSupervisionAgolCreadoModificado = this.supervisionAgolRepository
                                .save(pgimSupervisionAgol);

                // cargamos el objeto de retorno
                PgimSupervisionAgolDTO pgimSupervisionAgolDTOcreadoModif = new PgimSupervisionAgolDTO();
                pgimSupervisionAgolDTOcreadoModif
                                .setIdSupervisionAgol(pgimSupervisionAgolCreadoModificado.getIdSupervisionAgol());
                pgimSupervisionAgolDTOcreadoModif.setIdSupervision(
                                pgimSupervisionAgolCreadoModificado.getPgimSupervision().getIdSupervision());
                pgimSupervisionAgolDTOcreadoModif.setFlActivo(pgimSupervisionAgolCreadoModificado.getFlActivo());
                return pgimSupervisionAgolDTOcreadoModif;
        }

        @Override
        public List<PgimSupervisionDTO> listarPorCoSupervision(String coSupervision) {
                List<PgimSupervisionDTO> lPgimSupervisionDTO = this.supervisionRepository
                                .listarPorCoSupervision(coSupervision);

                return lPgimSupervisionDTO;
        }

        @Override
        public Page<PgimSupervisionAuxDTO> listarReporteSupervisionPaginado(
                        PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO, Pageable paginador) throws Exception {
                Page<PgimSupervisionAuxDTO> lPgimSupervisionAuxDTO = this.supervisionRepository
                                .listarSupervisionesReportePaginado(filtroPgimSupervisionAuxDTO.getCoUnidadMinera(),
                                                filtroPgimSupervisionAuxDTO.getNoUnidadMinera(),
                                                filtroPgimSupervisionAuxDTO.getAsCoDocumentoIdentidad(),
                                                filtroPgimSupervisionAuxDTO.getAsNoRazonSocial(),
                                                filtroPgimSupervisionAuxDTO.getIdEspecialidad(),
                                                filtroPgimSupervisionAuxDTO.getIdSubtipoSupervision(),
                                                filtroPgimSupervisionAuxDTO.getIdFaseActual(), paginador,
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.REPAS_CNCLAR_FLJO.toString()),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.REPAS_FNLZAR_FLJO.toString()));
                return lPgimSupervisionAuxDTO;
        }

        @Override
        public List<PgimSupervisionAuxDTO> listarReporteSupervision(PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO)
                        throws Exception {
                List<PgimSupervisionAuxDTO> lPgimSupervisionAuxDTO = this.supervisionRepository
                                .listarSupervisionesReporte(filtroPgimSupervisionAuxDTO.getCoUnidadMinera(),
                                                filtroPgimSupervisionAuxDTO.getNoUnidadMinera(),
                                                filtroPgimSupervisionAuxDTO.getAsCoDocumentoIdentidad(),
                                                filtroPgimSupervisionAuxDTO.getAsNoRazonSocial(),
                                                filtroPgimSupervisionAuxDTO.getIdEspecialidad(),
                                                filtroPgimSupervisionAuxDTO.getIdSubtipoSupervision(),
                                                filtroPgimSupervisionAuxDTO.getIdFaseActual(),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.REPAS_CNCLAR_FLJO.toString()),
                                                this.valorParametroRepository.obtenerIdValorParametro(
                                                                EValorParametro.REPAS_FNLZAR_FLJO.toString()));
                return lPgimSupervisionAuxDTO;
        }

        @Override
        public List<PgimSupervisionAuxDTO> listarSupervisionxAnioReporte(
                        PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO) throws Exception {
                List<PgimSupervisionAuxDTO> lPgimSupervisionAuxDTO = this.supervisionRepository
                                .listarSupervisionxAnioReporte(
                                                filtroPgimSupervisionAuxDTO.getFeInicioSupervisionDesc());
                return lPgimSupervisionAuxDTO;
        }

        @Override
        public List<PgimItemConsumoDTO> obtenerEntregablesSupervision(Long idSupervision) {
                List<PgimItemConsumoDTO> lPgimItemConsumoDTO = this.itemConsumoContratoRepository
                                .obtenerListaItemConsumoVigentesSupervision(idSupervision);

                return lPgimItemConsumoDTO;
        }

        /**
         * Permite obtener datos de cabecera para el formato de revision antecedente
         * 
         * @param idSupervision
         * @return
         */
        @Override
        public PgimSupervisionDTO obtenerSupervisionRevisionAntecedente(Long idSupervision) {
                return supervisionRepository.obtenerSupervisionRevisionAntecedente(idSupervision);
        }

        @Override
        public Page<PgimSupervisionDTO> obtenerFiscalizacionPorUnidadMineraPaginado(PgimSupervisionDTO filtroSupervisionDTO, Pageable paginador) throws Exception {

                paginador = this.activarOrdenamiento(filtroSupervisionDTO, paginador);

                Page<PgimSupervisionDTO> pPgimSupervisionDTO = this.supervisionRepository.obtenerFiscalizacionPorUnidadMineraPaginado(
                        filtroSupervisionDTO.getIdUnidadMinera(),
                        filtroSupervisionDTO.getCoSupervision(),
                        filtroSupervisionDTO.getDescIdEspecialidad(),
                        filtroSupervisionDTO.getDescContrato(),
                        filtroSupervisionDTO.getFeInicioSupervisionReal(),
                        filtroSupervisionDTO.getFeFinSupervisionReal(),
                        filtroSupervisionDTO.getDescPersonaDestino(),
                        filtroSupervisionDTO.getDescIdFaseProceso(),
                        filtroSupervisionDTO.getDescIdPasoProceso(),
                        paginador);

                return pPgimSupervisionDTO;
        }
        
        /**
         * Permite agregar la data de configuración de la lista de criterios del
         * cuadro de verificación a una fiscalización en particular
         * 
         * @param listaPgimCriterioSprvsionDTO
         * @param pgimSupervisionDTO
         * @param auditoriaDTO
         *                                     return ResponseDTO
         */
        @Transactional(readOnly = false)
        @Override
        public ResponseEntity<ResponseDTO> agregarCriteriosFiscalizacion(
                        PgimCriterioSprvsionDTO[] listaPgimCriterioSprvsionDTO, PgimSupervisionDTO pgimSupervisionDTO,
                        AuditoriaDTO auditoriaDTO) {
                ResponseDTO respuesta = null;
                PgimCriterioSprvsionDTO pgimCriterioSprvsionDTOActual = new PgimCriterioSprvsionDTO();
                PgimCriterioSprvsion pgimCriterioSprvsion = null;

                for (PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO : listaPgimCriterioSprvsionDTO) {
                        pgimCriterioSprvsionDTOActual = this.criterioSprvsionRepository
                                        .obtenerConfiguracionCriterioSupervision(
                                                        pgimSupervisionDTO.getDescIdEspecialidad(),
                                                        pgimCriterioSprvsionDTO.getIdMatrizCriterio());

                        pgimCriterioSprvsion = new PgimCriterioSprvsion();
                        pgimCriterioSprvsion.setPgimSupervision(new PgimSupervision());
                        pgimCriterioSprvsion.getPgimSupervision()
                                        .setIdSupervision(pgimSupervisionDTO.getIdSupervision());

                        pgimCriterioSprvsion.setPgimMatrizCriterio(new PgimMatrizCriterio());
                        pgimCriterioSprvsion.getPgimMatrizCriterio()
                                        .setIdMatrizCriterio(pgimCriterioSprvsionDTOActual.getIdMatrizCriterio());                        

                        pgimCriterioSprvsion.setCoMatrizCriterio(pgimCriterioSprvsionDTOActual.getCoMatrizCriterio());
                        pgimCriterioSprvsion.setDeMatrizCriterio(pgimCriterioSprvsionDTOActual.getDeMatrizCriterio());
                        pgimCriterioSprvsion.setEsVigenteMatrizCriterio(
                                        pgimCriterioSprvsionDTOActual.getEsVigenteMatrizCriterio());
                        pgimCriterioSprvsion.setDeBaseLegal(pgimCriterioSprvsionDTOActual.getDeBaseLegal());
                        pgimCriterioSprvsion
                                        .setIdMatrizGrpoCrtrio(pgimCriterioSprvsionDTOActual.getIdMatrizGrpoCrtrio());
                        pgimCriterioSprvsion
                                        .setIdMatrizSupervision(pgimCriterioSprvsionDTOActual.getIdMatrizSupervision());
                        pgimCriterioSprvsion
                                        .setCoMatrizGrpoCrtrio(pgimCriterioSprvsionDTOActual.getCoMatrizGrpoCrtrio());
                        pgimCriterioSprvsion
                                        .setNoMatrizGrpoCrtrio(pgimCriterioSprvsionDTOActual.getNoMatrizGrpoCrtrio());
                        pgimCriterioSprvsion.setNuOrdenGrpoCrtrio(pgimCriterioSprvsionDTOActual.getNuOrdenGrpoCrtrio());
                        pgimCriterioSprvsion
                                        .setEsVisibleGrpoCrtrio(pgimCriterioSprvsionDTOActual.getEsVisibleGrpoCrtrio());
                        pgimCriterioSprvsion.setNuOrdenCriterio(pgimCriterioSprvsionDTOActual.getNuOrdenCriterio());
                        
                        if(pgimSupervisionDTO.getDescIdInstanciaPaso() != null) {
	                        pgimCriterioSprvsion.setPgimInstanciaPaso(new PgimInstanciaPaso());
	                        pgimCriterioSprvsion.getPgimInstanciaPaso()
	                                        .setIdInstanciaPaso(pgimSupervisionDTO.getDescIdInstanciaPaso());
                        }
                        
                        pgimCriterioSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimCriterioSprvsion.setFeCreacion(auditoriaDTO.getFecha());
                        pgimCriterioSprvsion.setUsCreacion(auditoriaDTO.getUsername());
                        pgimCriterioSprvsion.setIpCreacion(auditoriaDTO.getTerminal());

                        this.criterioSprvsionRepository.save(pgimCriterioSprvsion);
                }

                respuesta = new ResponseDTO("success", "El(Los) criterio(s) ha(n) sido agregado(s) a la fiscalización");
                return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }

        /**
         * Permite agregar un criterios del
         * cuadro de verificación a una fiscalización en particular
         * 
         * @param PgimCriterioSprvsionDTO
         * @param pgimSupervisionDTO
         * @param auditoriaDTO
         * @return
         */
        @Transactional(readOnly = false)
        @Override
        public PgimCriterioSprvsionDTO agregarCriterioFiscalizacion(
                        PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO, PgimSupervisionDTO pgimSupervisionDTO,
                        AuditoriaDTO auditoriaDTO) {

                PgimCriterioSprvsionDTO pgimCriterioSprvsionDTOActual = new PgimCriterioSprvsionDTO();
                PgimCriterioSprvsion pgimCriterioSprvsion = null;

                pgimCriterioSprvsionDTOActual = this.criterioSprvsionRepository
                                .obtenerConfiguracionCriterioSupervision(
                                                pgimSupervisionDTO.getDescIdEspecialidad(),
                                                pgimCriterioSprvsionDTO.getIdMatrizCriterio());

                pgimCriterioSprvsion = new PgimCriterioSprvsion();
                pgimCriterioSprvsion.setPgimSupervision(new PgimSupervision());
                pgimCriterioSprvsion.getPgimSupervision()
                                .setIdSupervision(pgimSupervisionDTO.getIdSupervision());

                pgimCriterioSprvsion.setPgimMatrizCriterio(new PgimMatrizCriterio());
                pgimCriterioSprvsion.getPgimMatrizCriterio()
                                .setIdMatrizCriterio(pgimCriterioSprvsionDTOActual.getIdMatrizCriterio());

                pgimCriterioSprvsion.setCoMatrizCriterio(pgimCriterioSprvsionDTOActual.getCoMatrizCriterio());
                pgimCriterioSprvsion.setDeMatrizCriterio(pgimCriterioSprvsionDTOActual.getDeMatrizCriterio());
                pgimCriterioSprvsion.setEsVigenteMatrizCriterio(
                                pgimCriterioSprvsionDTOActual.getEsVigenteMatrizCriterio());
                pgimCriterioSprvsion.setDeBaseLegal(pgimCriterioSprvsionDTOActual.getDeBaseLegal());
                pgimCriterioSprvsion
                                .setIdMatrizGrpoCrtrio(pgimCriterioSprvsionDTOActual.getIdMatrizGrpoCrtrio());
                pgimCriterioSprvsion
                                .setIdMatrizSupervision(pgimCriterioSprvsionDTOActual.getIdMatrizSupervision());
                pgimCriterioSprvsion
                                .setCoMatrizGrpoCrtrio(pgimCriterioSprvsionDTOActual.getCoMatrizGrpoCrtrio());
                pgimCriterioSprvsion
                                .setNoMatrizGrpoCrtrio(pgimCriterioSprvsionDTOActual.getNoMatrizGrpoCrtrio());
                pgimCriterioSprvsion.setNuOrdenGrpoCrtrio(pgimCriterioSprvsionDTOActual.getNuOrdenGrpoCrtrio());
                pgimCriterioSprvsion
                                .setEsVisibleGrpoCrtrio(pgimCriterioSprvsionDTOActual.getEsVisibleGrpoCrtrio());
                pgimCriterioSprvsion.setNuOrdenCriterio(pgimCriterioSprvsionDTOActual.getNuOrdenCriterio());
                pgimCriterioSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimCriterioSprvsion.setFeCreacion(auditoriaDTO.getFecha());
                pgimCriterioSprvsion.setUsCreacion(auditoriaDTO.getUsername());
                pgimCriterioSprvsion.setIpCreacion(auditoriaDTO.getTerminal());

                PgimCriterioSprvsion pgimCriterioSprvsionCreado = this.criterioSprvsionRepository
                                .save(pgimCriterioSprvsion);

                PgimCriterioSprvsionDTO pgimCriterioSprvsionDTOCreado = this.criterioSprvsionRepository
                                .obtenerCriterioSprvsionById(pgimCriterioSprvsionCreado.getIdCriterioSprvsion());

                return pgimCriterioSprvsionDTOCreado;
        }

        @Override
        public void generarCuadroVerificacion(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {

                if (pgimInstanciaProces == null) {
                        return;
                }

                if (!pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
                                .equals(ConstantesUtil.PARAM_RELACION_REVISAR_ANTECEDENTES_APROBAR_REV_ANTECEDENTES)) {
                        return;
                }

                // Obtenemos el registro de la supervisión
                PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository
                                .obtenerSupervisionByidInstanciaProceso(
                                                pgimInstanciaProces.getIdInstanciaProceso());

                // Verificamos si existen criterios vigentes en la fiscalización
                List<PgimCriterioSprvsionDTO> lPgimCriterioSprvsionDTOExistentes = this.criterioSprvsionRepository
                                .obtenerMatrizSupervision(pgimSupervisionDTO.getIdSupervision());

                if (lPgimCriterioSprvsionDTOExistentes.size() > 0) {
                        return;
                }

                PgimPrgrmSupervisionDTO pgimPrgrmSupervisionDTO = this.prgrmSupervisionRepository
                                .obtenerPrograma(pgimSupervisionDTO.getIdProgramaSupervision());

                Long idTipoCfgBaseCuadroVerificacion = this.valorParametroRepository
                                .obtenerIdValorParametro(
                                                EValorParametro.CUADROS_BASE_VERIFICACION.toString());

                PgimUnidadMinera umFiltroRegla = this.unidadMineraRepository
                                .findById(pgimSupervisionDTO.getIdUnidadMinera()).orElse(null);

                PgimReglaBaseDTO pgimReglaBaseDTOFiltro = new PgimReglaBaseDTO();
                pgimReglaBaseDTOFiltro.setIdDivisionSupervisora(
                                pgimPrgrmSupervisionDTO.getIdDivisionSupervisora());
                pgimReglaBaseDTOFiltro
                                .setIdMotivoSupervision(pgimSupervisionDTO.getIdMotivoSupervision());

                if (umFiltroRegla.getMetodoMinado() != null) {
                        pgimReglaBaseDTOFiltro.setIdMetodoMinado(
                                        umFiltroRegla.getMetodoMinado().getIdValorParametro());
                }

                pgimReglaBaseDTOFiltro
                                .setIdSubtipoSupervision(pgimSupervisionDTO.getIdSubtipoSupervision());
                pgimReglaBaseDTOFiltro.setFlPropia(pgimSupervisionDTO.getFlPropia());
                pgimReglaBaseDTOFiltro.setFlConPiques(umFiltroRegla.getFlConPiques());

                pgimReglaBaseDTOFiltro.setDescIdTipoConfiguracionBase(idTipoCfgBaseCuadroVerificacion);

                PgimReglaBaseDTO pgimReglaBaseDTOCuadroVerificacion = configuracionBaseService
                                .obtenerReglaPorSupervision(pgimReglaBaseDTOFiltro);

                if (pgimReglaBaseDTOCuadroVerificacion == null) {
                        return;
                }

                List<PgimCriterioSprvsionDTO> lPgimCriterioSprvsionDTO = null;
                lPgimCriterioSprvsionDTO = this.criterioSprvsionRepository
                                .obtenerConfiguracionBaseCriteriosSupervision(
                                                pgimReglaBaseDTOCuadroVerificacion
                                                                .getIdConfiguracionBase());

                this.procesarCreacionCriterios(pgimSupervisionDTO, lPgimCriterioSprvsionDTO, auditoriaDTO);
        }

        @Transactional(readOnly = false)
        @Override
        public void generarCuadroVerificacion(PgimSupervisionDTO pgimSupervisionDTO, Long idPasoActual,
                        AuditoriaDTO auditoriaDTO) {

                // Verificamos si existen criterios vigentes en la fiscalización
                List<PgimCriterioSprvsionDTO> lPgimCriterioSprvsionDTOExistentes = this.criterioSprvsionRepository
                                .obtenerMatrizSupervision(pgimSupervisionDTO.getIdSupervision());

                if (lPgimCriterioSprvsionDTOExistentes.size() > 0) {
                        if (idPasoActual.equals(ConstPasoProcesoSupervision.REPROGRAMAR) || idPasoActual.equals(ConstPasoProcesoSupervision.APROBAR_REV_ANTECEDENTES_VALIDAR_CUADRO)) {// revisar
                                this.eliminarCuadroVerificacion(lPgimCriterioSprvsionDTOExistentes, auditoriaDTO);
                        } else {
                                throw new PgimException("error",
                                                String.format("Ya existen criterios configurados para esta fiscalización. Cantidad: '%s'",
                                                                lPgimCriterioSprvsionDTOExistentes.size()));
                        }
                }

                List<PgimCriterioSprvsionDTO> lPgimCriterioSprvsionDTO = null;
                Long idMatrizCriterio = pgimSupervisionDTO.getDescIdMatrizSupervision();
                lPgimCriterioSprvsionDTO = this.criterioSprvsionRepository
                                .obtenerCritiosPorMatrizSupervision(idMatrizCriterio);

                this.procesarCreacionCriterios(pgimSupervisionDTO, lPgimCriterioSprvsionDTO, auditoriaDTO);
        }

        private void eliminarCuadroVerificacion(List<PgimCriterioSprvsionDTO> lPgimCriterioSprvsionDTOExistentes,
                        AuditoriaDTO auditoriaDTO) {
                for (PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO : lPgimCriterioSprvsionDTOExistentes) {
                        PgimCriterioSprvsion pgimCriterioSprvsion = this.hechoConstatadoServiceImpl
                                        .findCriterioSprvsionById(pgimCriterioSprvsionDTO.getIdCriterioSprvsion());

                        List<PgimHechoConstatadoDTO> pgimHechoConstatadoDTOExistentes = this.hechoConstatadoRepository
                                        .obtenerLstHechosConstatadosDTO(pgimCriterioSprvsionDTO.getIdSupervision(),
                                                        pgimCriterioSprvsionDTO.getIdCriterioSprvsion());

                        for (PgimHechoConstatadoDTO pgimHechoConstatadoDTO : pgimHechoConstatadoDTOExistentes) {
                                PgimHechoConstatado pgimHechoConstatado = this.hechoConstatadoServiceImpl
                                                .getByidHechoConstatado(pgimHechoConstatadoDTO.getIdHechoConstatado());

                                List<PgimOblgcnNrmtvaHchocDTO> obligNormativasDTOExistentes = this.oblgcnNrmtvaHchocRepository
                                                .obtenerOblgNormativaPorHechoDTO(
                                                                pgimHechoConstatado.getIdHechoConstatado());

                                for (PgimOblgcnNrmtvaHchocDTO pgimOblgcnNrmtvaHchocDTO : obligNormativasDTOExistentes) {
                                        PgimOblgcnNrmtvaHchoc pgimOblgcnNrmtvaHchoc = this.oblgcnNrmtvaHchocRepository
                                                        .findById(pgimOblgcnNrmtvaHchocDTO.getIdOblgcnNrmtvaHchoc())
                                                        .orElse(null);

                                        pgimOblgcnNrmtvaHchoc.setEsRegistro(ConstantesUtil.IND_INACTIVO);

                                        pgimOblgcnNrmtvaHchoc.setFeActualizacion(auditoriaDTO.getFecha());
                                        pgimOblgcnNrmtvaHchoc.setUsActualizacion(auditoriaDTO.getUsername());
                                        pgimOblgcnNrmtvaHchoc.setIpActualizacion(auditoriaDTO.getTerminal());
                                }

                                pgimHechoConstatado.setEsRegistro(ConstantesUtil.IND_INACTIVO);

                                pgimHechoConstatado.setFeActualizacion(auditoriaDTO.getFecha());
                                pgimHechoConstatado.setUsActualizacion(auditoriaDTO.getUsername());
                                pgimHechoConstatado.setIpActualizacion(auditoriaDTO.getTerminal());
                        }

                        pgimCriterioSprvsion.setEsRegistro(ConstantesUtil.IND_INACTIVO);

                        pgimCriterioSprvsion.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimCriterioSprvsion.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimCriterioSprvsion.setIpActualizacion(auditoriaDTO.getTerminal());
                }

        }

        private void procesarCreacionCriterios(PgimSupervisionDTO pgimSupervisionDTO,
                        List<PgimCriterioSprvsionDTO> lPgimCriterioSprvsionDTO, AuditoriaDTO auditoriaDTO) {

                for (PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO : lPgimCriterioSprvsionDTO) {

                        PgimCriterioSprvsion pgimCriterioSprvsion = new PgimCriterioSprvsion();

                        pgimCriterioSprvsion.setPgimSupervision(new PgimSupervision());
                        pgimCriterioSprvsion.getPgimSupervision().setIdSupervision(
                                        pgimSupervisionDTO.getIdSupervision());

                        pgimCriterioSprvsion.setPgimMatrizCriterio(new PgimMatrizCriterio());
                        pgimCriterioSprvsion.getPgimMatrizCriterio()
                                        .setIdMatrizCriterio(pgimCriterioSprvsionDTO
                                                        .getIdMatrizCriterio());

                        pgimCriterioSprvsion.setCoMatrizCriterio(
                                        pgimCriterioSprvsionDTO.getCoMatrizCriterio());
                        pgimCriterioSprvsion.setDeMatrizCriterio(
                                        pgimCriterioSprvsionDTO.getDeMatrizCriterio());
                        pgimCriterioSprvsion.setEsVigenteMatrizCriterio(
                                        pgimCriterioSprvsionDTO.getEsVigenteMatrizCriterio());
                        pgimCriterioSprvsion.setDeBaseLegal(
                                        pgimCriterioSprvsionDTO.getDeBaseLegal());
                        pgimCriterioSprvsion.setIdMatrizGrpoCrtrio(
                                        pgimCriterioSprvsionDTO.getIdMatrizGrpoCrtrio());
                        pgimCriterioSprvsion.setIdMatrizSupervision(
                                        pgimCriterioSprvsionDTO.getIdMatrizSupervision());
                        pgimCriterioSprvsion.setCoMatrizGrpoCrtrio(
                                        pgimCriterioSprvsionDTO.getCoMatrizGrpoCrtrio());
                        pgimCriterioSprvsion.setNoMatrizGrpoCrtrio(
                                        pgimCriterioSprvsionDTO.getNoMatrizGrpoCrtrio());
                        pgimCriterioSprvsion.setNuOrdenGrpoCrtrio(
                                        pgimCriterioSprvsionDTO.getNuOrdenGrpoCrtrio());
                        pgimCriterioSprvsion.setEsVisibleGrpoCrtrio(
                                        pgimCriterioSprvsionDTO.getEsVisibleGrpoCrtrio());
                        pgimCriterioSprvsion.setNuOrdenCriterio(
                                        pgimCriterioSprvsionDTO.getNuOrdenCriterio());

                        pgimCriterioSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimCriterioSprvsion.setFeCreacion(auditoriaDTO.getFecha());
                        pgimCriterioSprvsion.setUsCreacion(auditoriaDTO.getUsername());
                        pgimCriterioSprvsion.setIpCreacion(auditoriaDTO.getTerminal());

                        this.criterioSprvsionRepository.save(pgimCriterioSprvsion);
                }
        }

        @Override
        public void asegurarConsistenciaCambiosDocumento(Long idDocumento, ETipoAccionCrud tipoAccionCrud) {

                PgimDocumento pgimDocumento = this.documentoRepository.findById(idDocumento).orElse(null);
                Long idProceso = pgimDocumento.getPgimInstanciaProces().getPgimProceso().getIdProceso();

                if (!idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
                        // Se debe validar si es que es posible anular el documento
                        return;
                }

                Long idSubcatDocumento = pgimDocumento.getPgimSubcategoriaDoc().getIdSubcatDocumento();
                List<PgimDocumentoRelacionDTO> lPgimDocumentoRelacionDTO = null;

                if (idSubcatDocumento.equals(ConstantesUtil.PARAM_SC_INFORME_SUPERVISION)) {
                        // Se debe validar que el informe aún no esté relacionado con alguna revisión
                        // como documento padre.
                        lPgimDocumentoRelacionDTO = this.documentoRelacionRepository
                                        .obtenerListDocRelacionPorIdDocumentoPadre(idDocumento);
                } else if (idSubcatDocumento.equals(ConstantesUtil.PARAM_SUBCAT_DOC_OIS)
                                || idSubcatDocumento.equals(ConstantesUtil.PARAM_SUBCAT_DOC_CIS)) {
                        // Se debe validar que el informe aún no esté relacionado con alguna revisión
                        // como documento hijo
                        lPgimDocumentoRelacionDTO = this.documentoRelacionRepository
                                        .obtenerListDocRelacionPorIdHijo(idDocumento);
                } else {
                        return;
                }

                if (lPgimDocumentoRelacionDTO.size() == 0) {
                        return;
                }

                PgimSubcategoriaDoc pgimSubcategoriaDoc = this.subcategoriaDocRepository.findById(idSubcatDocumento)
                                .orElse(null);

                String mensaje = "";

                if (tipoAccionCrud == ETipoAccionCrud.ELIMINAR) {
                        mensaje = "No se puede eliminar el documento '%s: %s' debido a que se encuentra relacionado con la revisión de informes. Para eliminarlo, hágalo desde la fase '%s' y pestaña '%s'";
                } else if (tipoAccionCrud == ETipoAccionCrud.MODIFICAR) {
                        mensaje = "No se puede modificar el documento '%s: %s' debido a que se encuentra relacionado con la revisión de informes. Para modificarlo, debe quitar toda referencia a él en la fase '%s' y pestaña '%s'";
                } else {
                        throw new PgimException(TipoResultado.ERROR, "El tipo " + tipoAccionCrud.toString()
                                        + "No está implementado para asegurar la consistencia de cambios en el documento"); // STORY:
                                                                                                                            // PGIM-6667:
                                                                                                                            // Mejora
                                                                                                                            // de
                                                                                                                            // la
                                                                                                                            // gestión
                                                                                                                            // de
                                                                                                                            // mensajes
                                                                                                                            // para
                                                                                                                            // el
                                                                                                                            // usuario
                }

                String mensajeExcepcion = String.format(mensaje,
                                pgimSubcategoriaDoc.getCoSubcatDocumento(), pgimSubcategoriaDoc.getNoSubcatDocumento(),
                                "Fase 4. Revisión de informe de fiscalización",
                                "CONFORMIDAD");

                throw new PgimException(TipoResultado.WARNING, mensajeExcepcion); // STORY: PGIM-6667: Mejora de la
                                                                                  // gestión de mensajes para el usuario
        }

        @Transactional(readOnly = false)
        private void asegurarEstadoInstrumentosFisc(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
                String estadoNuevo;

                List<PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO = this.supervInstrumentoRepository
                                .listarAll(pgimInstanciaProces.getCoTablaInstancia());

                if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_GENERA_TDR_CREDENCIAL_APROBAR_TDR) ||
                                idRelacionPaso.equals(ConstantesUtil.PARAM_RP_APROBAR_REV_ANTECED_PREPARAR_INI_CAMPO)) {

                        for (PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO : lPgimInstrmntoXSupervDTO) {
                                estadoNuevo = null;

                                if (pgimInstrmntoXSupervDTO.getIdEstadoInstrumento()
                                                .equals(ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_REGISTRADO) ||
                                                pgimInstrmntoXSupervDTO.getIdEstadoInstrumento().equals(
                                                                ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_MODIFICADO)) {
                                        estadoNuevo = EValorParametro.TIPO_ESTDO_INSTRMNTO_APROBADO.toString();

                                } else if (pgimInstrmntoXSupervDTO.getIdEstadoInstrumento()
                                                .equals(ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_NODISPONIBLE)) {
                                        estadoNuevo = EValorParametro.TIPO_ESTDO_INSTRMNTO_N_DSPNBLE_APROB.toString();

                                } else if (pgimInstrmntoXSupervDTO.getIdEstadoInstrumento()
                                                .equals(ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_REEMPLAZADO)) {
                                        estadoNuevo = EValorParametro.TIPO_ESTDO_INSTRMNTO_RMPLZDO_APROB.toString();
                                }

                                if (estadoNuevo != null) {
                                        PgimInstrmntoXSuperv pgimInstrmntoXSuperv = this.supervInstrumentoRepository
                                                        .findById(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv())
                                                        .orElse(null);
                                        this.supervInstrumentoService.cambiarEstadoInstrumentoXSuperv(estadoNuevo,
                                                        pgimInstrmntoXSuperv, auditoriaDTO);
                                }
                        }
                }

                if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_APROBAR_TDR_GENERA_TDR_CREDENCIAL) ||
                                idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_APROBAR_TDR_COORDSUPER) ||
                                idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_DEFINIR_ANTECEDENTES_COORDSUPER) ||
                                idRelacionPaso.equals(
                                                ConstantesUtil.PARAM_RELACION_APROBAR_REV_ANTECEDENTES_COORDSUPER)) {

                        for (PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO : lPgimInstrmntoXSupervDTO) {
                                estadoNuevo = null;

                                if (pgimInstrmntoXSupervDTO.getIdEstadoInstrumento()
                                                .equals(ConstantesUtil.PARAM_ESTADO_INSTRUMENTO_FISC_APROBADO)) {
                                        estadoNuevo = EValorParametro.TIPO_ESTDO_INSTRMNTO_REGISTRADO.toString();
                                }

                                if (estadoNuevo != null) {
                                        PgimInstrmntoXSuperv pgimInstrmntoXSuperv = this.supervInstrumentoRepository
                                                        .findById(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv())
                                                        .orElse(null);
                                        this.supervInstrumentoService.cambiarEstadoInstrumentoXSuperv(estadoNuevo,
                                                        pgimInstrmntoXSuperv, auditoriaDTO);
                                }
                        }

                }

        }

        @Transactional(readOnly = false)
        private void asegurarHistoricoInfracciones(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

	        Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
	        
	        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_DETERMINAR_REQ_ARCHIVAR__CONFIRMAR_HC_INFRAC) ||
	        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_DETERMINAR_REQ_ARCHIVAR__COMPLETAR_FISC_ARCHIVANDO) ||
	        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_FISC_COMPL_PAS__CONFIRMAR_HC_INFRACCIONES )
	        	) {
	        	PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this.flujoTrabajoService
	                    .obtenerInstanciaPasoActualPorIdInstanciaPaso(pgimInstanciaPaso.getIdInstanciaPaso());
	        	
	        	this.infraccionService.copiarInfraccionesSupervision(pgimInstanciaProces.getCoTablaInstancia(), 
	        			pgimInstanciaPasoDTOActual, null, true, auditoriaDTO);                 
	        }     
        } 
        
        @Transactional(readOnly = false)
        private void persistirIdAgenteFiscalizado(PgimInstanciaProces pgimInstanciaProces,
                PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

	        Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
	        
	        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_PREACTASUPER_ELAINFO) ||
	        	idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_INICIASUPERVCAMPO_ELABORAR_INF_FISC_FALLIDA) ||
	        	idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_COORD_SUPER_CANCELAR_SUPER )
	        	) {
	        	
	        	Long idSupervision = pgimInstanciaProces.getCoTablaInstancia();
	        	
	        	PgimSupervision pgimSupervision = this.supervisionRepository.findById(idSupervision).orElse(null);
	 	        
	 	        if(pgimSupervision == null) {
	 	        	throw new PgimException(TipoResultado.ERROR, "No se pudo encontrar la fiscalización con id " + idSupervision + " para persistir el identificador del agente fiscalizado");
	 	        }
	 	        
	 	        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.
		        		obtenerAgenteSupervisadoPorIdUm(pgimSupervision.getPgimUnidadMinera().getIdUnidadMinera());
	 	        
	 	        PgimAgenteSupervisado pgimAgenteSupervisado = this.agenteSupervisadoRepository.findById(pgimAgenteSupervisadoDTO.getIdAgenteSupervisado()).orElse(null);
	 	        
	 	        pgimSupervision.setPgimAgenteSupervisado(pgimAgenteSupervisado);	 	        
	 	        pgimSupervision.setFeActualizacion(auditoriaDTO.getFecha());
	 	        pgimSupervision.setUsActualizacion(auditoriaDTO.getUsername());
	 	        pgimSupervision.setIpActualizacion(auditoriaDTO.getTerminal());
	 	        
	 	        this.supervisionRepository.save(pgimSupervision);
	        	
	        }     
        }  
        
        @Transactional(readOnly = false)
        private void persistirRazonSocialAgenteFiscalizado(PgimInstanciaProces pgimInstanciaProces,
                PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

	        Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
	        
	        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_FISC_COMPL_INICIO_PAS__FISC_COMPLETADA_PAS) ||
	        	idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_FIRMARMEMO_COMPLETARSUPFALLIDA) ||
	        	idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_COORD_SUPER_CANCELAR_SUPER) ||
	        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_COMPLETAR_FISC_ARCH__FISC_ARCHIVADA)
	        	) {
	        	
	        	Long idSupervision = pgimInstanciaProces.getCoTablaInstancia();
	        	
	        	PgimSupervision pgimSupervision = this.supervisionRepository.findById(idSupervision).orElse(null);
	 	        
	 	        if(pgimSupervision == null) {
	 	        	throw new PgimException(TipoResultado.ERROR, "No se pudo encontrar la fiscalización con id " + idSupervision + " para persistir la razón social del agente fiscalizado");
	 	        	
	 	        }else if(pgimSupervision.getPgimAgenteSupervisado() == null) {
	 	        	throw new PgimException(TipoResultado.ERROR, "La fiscalización no tiene registrado el agente fiscalizado el cual se necesita para persistir su razón social");
	 	        }
	 	        
	 	        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.obtenerAgenteSupervisadoPorIdAs(pgimSupervision.getPgimAgenteSupervisado().getIdAgenteSupervisado());
	 	        
	 	        if(pgimAgenteSupervisadoDTO == null) {
	 	        	throw new PgimException(TipoResultado.ERROR, "No se pudo encontrar el agente fiscalizado para persistir su razón social");
	 	        }
	 	       
	 	        pgimSupervision.setNoRazonSocialAfiscalizado(pgimAgenteSupervisadoDTO.getDescNoRazonSocial());	 	        
	 	        pgimSupervision.setFeActualizacion(auditoriaDTO.getFecha());
	 	        pgimSupervision.setUsActualizacion(auditoriaDTO.getUsername());
	 	        pgimSupervision.setIpActualizacion(auditoriaDTO.getTerminal());
	 	        
	 	        this.supervisionRepository.save(pgimSupervision);
	        	
	        }     
        }  
        
        @Transactional(readOnly = false)
        private void rechazarInformeFiscalizacion(PgimInstanciaProces pgimInstanciaProces,
                PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

	        Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
	        
	        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REVIS_APROB_INFORME_FISC__ELAB_INFORME_CONDICIONES_OFRECIDAS)){
	        	
	        	List<PgimDocumentoDTO> lPgimDocumentoDTOObservacion = this.documentoRepository.listarDocPorInstanciaYSubCategoria(
	        			pgimInstanciaProces.getIdInstanciaProceso(), ConstantesUtil.PARAM_SC_FICHA_OBSERVACION_INFORME_FISCALIZACION);
	        	
	        	if(lPgimDocumentoDTOObservacion.size() > 0) {
	        		String msjException = String.format("No se puede rechazar el informe de fiscalización porque ya tiene al menos un documento que indica que fue observado, a saber: %s", 
	        				lPgimDocumentoDTOObservacion.get(0).getNoSubcatDocumento());	        		
	        		throw new PgimException(TipoResultado.ERROR, msjException);
	        	}
	        	
	        	List<PgimDocumentoDTO> lPgimDocumentoDTOConformidad = this.documentoRepository.listarDocPorInstanciaYSubCategoria(
	        			pgimInstanciaProces.getIdInstanciaProceso(), ConstantesUtil.PARAM_SC_FICHA_CONFORMIDAD_INFORME_FISCALIZACION);
	        	
	        	if(lPgimDocumentoDTOConformidad.size() > 0) {
	        		String msjException = String.format("No se puede rechazar el informe de fiscalización porque ya tiene el documento que lo aprueba, a saber: %s", 
	        				lPgimDocumentoDTOConformidad.get(0).getNoSubcatDocumento());
	        		throw new PgimException(TipoResultado.ERROR, msjException);
	        	}	        	
	        	
	        	List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository.listarDocPorInstanciaYSubCategoria(
	        			pgimInstanciaProces.getIdInstanciaProceso(), ConstantesUtil.PARAM_SC_INFORME_SUPERVISION);

	        	if(lPgimDocumentoDTO.size() == 0) {
	        		throw new PgimException(TipoResultado.ERROR, "No se encontró el informe de fiscalización que se va a rechazar");
	        	}
	        	
        		// tomamos el primer item ya que está ordenado descendentemente
        		PgimDocumentoDTO pgimDocumentoDTO = lPgimDocumentoDTO.get(0);
        		
        		PgimDocumento pgimDocumento = this.documentoRepository.findById(pgimDocumentoDTO.getIdDocumento()).orElse(null);
        		
        		if(pgimDocumento .getFeRechazo() != null) {
        			throw new PgimException(TipoResultado.ERROR, "No se puede rechazar el último informe de fiscalización porque ya figura como rechazado.");
        		}
        		
        		pgimDocumento.setFeRechazo(auditoriaDTO.getFecha()); 			
    			pgimDocumento.setFeActualizacion(auditoriaDTO.getFecha());
    			pgimDocumento.setUsActualizacion(auditoriaDTO.getUsername());
    			pgimDocumento.setIpActualizacion(auditoriaDTO.getTerminal());
    			
    			this.documentoRepository.save(pgimDocumento);		        	
	        	
	        }     
        }        
       
        
        @Override
        public Integer contarSupervPendientes(AuditoriaDTO auditoriaDTO) {

                Integer cantidadSupervPendientes = this.supervisionRepository
                                .contarSupervisionesPendientes(auditoriaDTO.getUsername());

                return cantidadSupervPendientes;
        }

        @Override
        @Transactional(readOnly = false)
        public ResponseEntity<ResponseDTO> actualizarImpedimentoAgenteFiscalizado(Long idSupervision,
                        boolean supervisionImpedida, AuditoriaDTO auditoriaDTO) throws Exception {

                ResponseDTO respuesta = null;
                PgimSupervision pgimSupervision = this.supervisionRepository.findById(idSupervision).orElse(null);

                if (pgimSupervision == null) {
                        respuesta = new ResponseDTO(TipoResultado.ERROR,
                                        "No se ha encontrado la fiscalización con el identificador "
                                                        + idSupervision.toString());

                        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
                }

                pgimSupervision.setFlNoIniciadaAfiscalizado((supervisionImpedida ? "1" : "0"));
                pgimSupervision.setFeActualizacion(auditoriaDTO.getFecha());
                pgimSupervision.setUsActualizacion(auditoriaDTO.getUsername());
                pgimSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

                this.supervisionRepository.save(pgimSupervision);

                respuesta = new ResponseDTO(TipoResultado.SUCCESS,
                                "Se ha cambiado la condición de impedimento de realización de la fiscalización");

                return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }


        public Pageable activarOrdenamiento(PgimSupervisionDTO filtroSupervisionDTO, Pageable paginador) throws Exception {


        if(filtroSupervisionDTO.getSortColumna() != null && filtroSupervisionDTO.getSortDirection() != null){

            if(filtroSupervisionDTO.getSortColumna().equals("coSupervision") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "coSupervision"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("coSupervision") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "coSupervision"));
            }
            
            else if(filtroSupervisionDTO.getSortColumna().equals("descNoEspecialidad") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "noEspecialidad"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descNoEspecialidad") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "noEspecialidad"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("descContrato") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "pgimContrato.nuContrato"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descContrato") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "pgimContrato.nuContrato"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("feInicioSupervisionReal") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "pgimSupervision.feInicioSupervisionReal"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("feInicioSupervisionReal") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "pgimSupervision.feInicioSupervisionReal"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("feFinSupervisionReal") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "pgimSupervision.feFinSupervisionReal"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("feFinSupervisionReal") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "pgimSupervision.feFinSupervisionReal"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("descPersonaDestino") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "personaAsignada"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descPersonaDestino") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "personaAsignada"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("descNoFaseProceso") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "noFaseActual"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descNoFaseProceso") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "noFaseActual"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("descNoPasoProcesoDestino") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "noPasoActual"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descNoPasoProcesoDestino") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "noPasoActual"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("descTipoSupervision") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "noTipoSupervision"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descTipoSupervision") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "noTipoSupervision"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("descPrograma") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "descPrograma"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descPrograma") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "descPrograma"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("descSubtipoSupervision") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "pgimSubtipoSupervision.deSubtipoSupervision"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descSubtipoSupervision") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "pgimSubtipoSupervision.deSubtipoSupervision"));
            }

            else if(filtroSupervisionDTO.getSortColumna().equals("descDeMotivoSupervision") && filtroSupervisionDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "pgimSupervision.pgimMotivoSupervision.deMotivoSupervision"));
            } else if(filtroSupervisionDTO.getSortColumna().equals("descDeMotivoSupervision") && filtroSupervisionDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "pgimSupervision.pgimMotivoSupervision.deMotivoSupervision"));
            }
            
            else{
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize());
            }
           
        }
        return paginador;
    }
}