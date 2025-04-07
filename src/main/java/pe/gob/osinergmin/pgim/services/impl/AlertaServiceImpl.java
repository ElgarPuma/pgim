package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAlertaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimAlerta;
import pe.gob.osinergmin.pgim.models.entity.PgimAlertaDetalle;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimIprocesoAlerta;
import pe.gob.osinergmin.pgim.models.entity.PgimLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.AlertaDetalleRepository;
import pe.gob.osinergmin.pgim.models.repository.AlertaRepository;
import pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository;
import pe.gob.osinergmin.pgim.models.repository.PasRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.AlertaService;
import pe.gob.osinergmin.pgim.services.EqpInstanciaProService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.PrgrmSupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestion de la interaccion con la base de datos y otros
 * servicios.
 * 
 * @descripci�n: L�gica de negocio de la entidad Alerta
 * 
 * @author: HRUIZ
 * @version: 1.0 @fecha_de_creacion: 02/09/2020 @fecha_de_ultima_actualizacion:
 *           02/09/2020
 */
@Service
@Transactional(readOnly = true)
public class AlertaServiceImpl implements AlertaService {

        @Autowired
        private AlertaRepository alertaRepository;

        @Autowired
        private AlertaDetalleRepository alertaDetalleRepository;

        @Autowired
        private ValorParametroRepository valorParametroRepository;

        @Autowired
        private SupervisionRepository supervisionRepository;

        @Autowired
        private LiquidacionRepository liquidacionRepository;

        @Autowired
        private ContratoRepository contratoRepository;

        @Autowired
        private PrgrmSupervisionService prgrmSupervisionService;

        @Autowired
        private UnidadMineraRepository unidadMineraRepository;

        @Autowired
        private PasRepository pasRepository;

        @Autowired
        private RankingRiesgoRepository rankingRiesgoRepository;

        @Autowired
        private ConfiguraRiesgoRepository configuraRiesgoRepository;

        @Autowired
        private InstanciaPasoRepository instanciaPasoRepository;

        @Autowired
        private FlujoTrabajoService flujoTrabajoService;

        @Autowired
        private InstanciaProcesRepository instanciaProcesRepository;
        
        @Autowired
        private EqpInstanciaProService eqpInstanciaProService;   

        private static String objeto = "";

        @Override
        public Page<PgimAlertaDetalleDTO> listarAlerta(PgimAlertaDetalleDTO filtroAlertaDetalleDTO,
                        AuditoriaDTO auditoriaDTO, Pageable paginador) {
                String userName = auditoriaDTO.getUsername();

                Page<PgimAlertaDetalleDTO> lPgimAlertaDetalleDTO = this.alertaDetalleRepository
                                .listarAlertasByNoUsuarioDestino(
                                                filtroAlertaDetalleDTO.getDescIdProceso(),
                                                filtroAlertaDetalleDTO.getDescIdFaseProceso(),
                                                filtroAlertaDetalleDTO.getDescIdPasoProceso(),
                                                filtroAlertaDetalleDTO.getFlLeido(),
                                                filtroAlertaDetalleDTO.getDescIdTipoAlerta(),
                                                userName,
                                                paginador);

                return lPgimAlertaDetalleDTO;
        }

        @Override
        public PgimAlertaDetalleDTO obtenerAlertaDetalleByIdAlerta(Long idAlerta) {
                return this.alertaDetalleRepository.obtenerAlertaDetalleByIdAlerta(idAlerta);
        }

        @Override
        public PgimAlertaDetalle getByIdDetalleAlerta(Long idAlertaDetalle) {
                return this.alertaDetalleRepository.findById(idAlertaDetalle).orElse(null);
        }

        @Transactional(readOnly = false)
        @Override
        public PgimAlertaDetalleDTO modificarAlertaLeido(PgimAlertaDetalleDTO pgimAlertaDetalleDTO,
                        PgimAlertaDetalle pgimAlertaDetalle, AuditoriaDTO auditoriaDTO) {

                pgimAlertaDetalle.setFlLeido(pgimAlertaDetalleDTO.getFlLeido());

                pgimAlertaDetalle.setFeActualizacion(auditoriaDTO.getFecha());
                pgimAlertaDetalle.setUsActualizacion(auditoriaDTO.getUsername());
                pgimAlertaDetalle.setIpActualizacion(auditoriaDTO.getTerminal());

                PgimAlertaDetalle pgimAlertaDetalleModificado = alertaDetalleRepository.save(pgimAlertaDetalle);

                PgimAlertaDetalleDTO pgimAlertaDetalleDTOModificado = this
                                .obtenerAlertaDetalleById(pgimAlertaDetalleModificado.getIdDetalleAlerta());

                return pgimAlertaDetalleDTOModificado;
        }

        @Override
        public PgimAlertaDetalleDTO obtenerAlertaDetalleById(Long idAlertaDetalle) {
                return this.alertaDetalleRepository.obtenerAlertaDetalleById(idAlertaDetalle);
        }

        @Override
        public PgimAlertaDetalleDTO obtenerAlertaDetalleByInstanciaPaso(Long idInstanciaPaso, Long idTipoAlerta) {
                return this.alertaDetalleRepository.obtenerAlertaDetalleByInstanciaPaso(idInstanciaPaso, idTipoAlerta);
        }

        @Override
        @Transactional(readOnly = false)
        public void enviarAlertaTransicionPaso(PgimInstanciaProces pgimInstanciaProces,
                        PgimRelacionPaso pgimRelacionPaso, Map<String, String> lNoUsuarios, Long idTipoAlerta,
                        Long idProceso, Long idInstanciaPaso, AuditoriaDTO auditoriaDTO) {

                PgimAlerta pgimAlerta = this.asegurarAlerta(pgimInstanciaProces,
                                pgimRelacionPaso, lNoUsuarios, idTipoAlerta,
                                idProceso, idInstanciaPaso, auditoriaDTO);

                this.enviarAlertaDetalle(pgimAlerta, auditoriaDTO, idInstanciaPaso, lNoUsuarios.get("USUORIGEN"),
                                lNoUsuarios.get("USUDESTINO"),
                                String.format(ConstantesUtil.PARAM_DETALLE_ALERTA_PASO, objeto),
                                this.valorParametroRepository
                                                .obtenerIdValorParametro(EValorParametro.DEALE_PSO_ASGNDO.toString()));
        }

        private PgimAlerta asegurarAlerta(PgimInstanciaProces pgimInstanciaProces,
                        PgimRelacionPaso pgimRelacionPaso, Map<String, String> lNoUsuarios, Long idTipoAlerta,
                        Long idProceso, Long idInstanciaPaso, AuditoriaDTO auditoriaDTO) {
                PgimAlerta pgimAlerta = new PgimAlerta();

                String noAlerta = "";
                String diEnlace = "";

                PgimValorParametro pgimValorParametro = valorParametroRepository.getOne(idTipoAlerta);

                // FISCALLIZACIÓN
                if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
                        PgimSupervision pgimSupervision = this.supervisionRepository
                                        .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                        PgimUnidadMineraDTO pgimUnidadMineraDTO = unidadMineraRepository
                                        .obtenerUnidadMineraPorId(
                                                        pgimSupervision.getPgimUnidadMinera().getIdUnidadMinera());

                        noAlerta = String.format(ConstantesUtil.PARAM_NO_ALERTA_SUP,
                                        pgimUnidadMineraDTO.getNoUnidadMinera(), pgimSupervision.getCoSupervision());

                        // diEnlace = String.format(ConstantesUtil.PARAM_ENLACE_REENVIAR_SUP,
                        // pgimInstanciaProces.getCoTablaInstancia());

                        diEnlace = idProceso + "-" + pgimInstanciaProces.getCoTablaInstancia();

                        objeto = "fiscalización";
                }

                // LIQUIDACIÓN
                if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {
                        PgimLiquidacion pgimLiquidacion = this.liquidacionRepository
                                        .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                        PgimContratoDTO pgimContratoDTO = this.contratoRepository
                                        .obtenerContratoPorId(pgimLiquidacion.getPgimContrato().getIdContrato());

                        noAlerta = String.format(ConstantesUtil.PARAM_NO_ALERTA_LIQ,
                                        pgimContratoDTO.getNuContrato(), pgimLiquidacion.getNuLiquidacion());

                        diEnlace = idProceso + "-" + pgimInstanciaProces.getCoTablaInstancia();

                        objeto = "liquidación";

                }

                // PROGRAMACIÓN
                if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_PROGRAMACION)) {
                        PgimPrgrmSupervisionDTO ppgimPrgrmSupervisionDTO = this.prgrmSupervisionService
                                        .obtenerPrograma(pgimInstanciaProces.getCoTablaInstancia());

                        noAlerta = String.format(ConstantesUtil.PARAM_NO_ALERTA_PRO,
                                        ppgimPrgrmSupervisionDTO.getDescNuAnio(),
                                        ppgimPrgrmSupervisionDTO.getDescNoDivisionSupervisora()
                                                        + ": "
                                                        + ppgimPrgrmSupervisionDTO.getDescDeEspecialidad());

                        diEnlace = idProceso + "-" + pgimInstanciaProces.getCoTablaInstancia();

                        objeto = "programación";

                }

                // PAS
                if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)) {
                        PgimPas pgimAs = pasRepository.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                        PgimSupervision pgimSupervision = supervisionRepository
                                        .findById(pgimAs.getPgimSupervision().getIdSupervision()).orElse(null);

                        PgimUnidadMineraDTO pgimUnidadMineraDTO = unidadMineraRepository
                                        .obtenerUnidadMineraPorId(
                                                        pgimSupervision.getPgimUnidadMinera().getIdUnidadMinera());

                        noAlerta = String.format(ConstantesUtil.PARAM_NO_ALERTA_FIS,
                        pgimAs.getIdPas(), pgimUnidadMineraDTO.getNoUnidadMinera(), pgimSupervision.getCoSupervision());

                        diEnlace = idProceso + "-" + pgimInstanciaProces.getCoTablaInstancia();

                        objeto = "PAS";

                }

                // RANKING DE RIESGO
                if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS)) {

                        PgimRankingRiesgo ranking = rankingRiesgoRepository
                                        .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                        noAlerta = String.format(ConstantesUtil.PARAM_NO_ALERTA_RNK,
                                        ranking.getIdRankingRiesgo(), ranking.getNoRanking());

                        diEnlace = idProceso + "-" + pgimInstanciaProces.getCoTablaInstancia();

                        objeto = "ranking de riesgo";

                }

                // CONFIGURACIÓN DE RIESGO
                if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO)) {

                        PgimConfiguraRiesgo configura = configuraRiesgoRepository
                                        .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                        noAlerta = String.format(ConstantesUtil.PARAM_NO_ALERTA_CONF,
                                        configura.getIdConfiguraRiesgo(), configura.getNoConfiguracion());

                        diEnlace = idProceso + "-" + pgimInstanciaProces.getCoTablaInstancia();

                        objeto = "configuración de riesgo";

                }

                String detalleAlerta = String.format(ConstantesUtil.PARAM_ALERTA_TRANS_PASO,
                                pgimRelacionPaso.getPasoProcesoOrigen().getNoPasoProceso(),
                                lNoUsuarios.get("USUORIGEN"),
                                pgimRelacionPaso.getPasoProcesoDestino().getNoPasoProceso(),
                                lNoUsuarios.get("USUDESTINO"));

                pgimAlerta.setTipoAlerta(pgimValorParametro);
                pgimAlerta.setNoAlerta(noAlerta);
                pgimAlerta.setDeAlerta(detalleAlerta);
                pgimAlerta.setFeAlerta(auditoriaDTO.getFecha());
                pgimAlerta.setDiEnlace(diEnlace);

                if (idInstanciaPaso != null) {
                        pgimAlerta.setPgimInstanciaPaso(new PgimInstanciaPaso());
                        pgimAlerta.getPgimInstanciaPaso().setIdInstanciaPaso(idInstanciaPaso);
                }

                pgimAlerta.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimAlerta.setFeCreacion(auditoriaDTO.getFecha());
                pgimAlerta.setUsCreacion(auditoriaDTO.getUsername());
                pgimAlerta.setIpCreacion(auditoriaDTO.getTerminal());
                pgimAlerta = alertaRepository.save(pgimAlerta);

                return pgimAlerta;
        }

        @Transactional(readOnly = false)
        public Long enviarAlertaDetalle(PgimAlerta pgimAlerta, AuditoriaDTO auditoriaDTO, Long idInstanciaPaso,
                        String noUsuarioOrig, String noUsuarioDest,
                        String mensajeAsunto, Long idTipoDetalleAlerta) {

                PgimValorParametro pgimValorParametro = valorParametroRepository.getOne(idTipoDetalleAlerta);

                PgimAlertaDetalle alertaDetalle = new PgimAlertaDetalle();
                alertaDetalle.setPgimAlerta(pgimAlerta);
                alertaDetalle.setTipoDetalleAlerta(pgimValorParametro);
                alertaDetalle.setNoUsuarioDestino(noUsuarioDest);
                alertaDetalle.setFlLeido(ConstantesUtil.IND_INACTIVO);

                if (noUsuarioOrig != null) {
                        if (!noUsuarioOrig.equals(noUsuarioDest)) {
                                // NO A MI MISMO
                                alertaDetalle.setFlLeido(ConstantesUtil.IND_INACTIVO);
                        } else if (noUsuarioOrig.equals(noUsuarioDest)) {
                                // A MI MISMO
                                alertaDetalle.setFlLeido(ConstantesUtil.IND_ACTIVO);
                        }
                } else {
                        PgimInstanciaPaso pgimInstanciaPasoActual = null;

                        if (idInstanciaPaso != null) {
                                pgimInstanciaPasoActual = this.instanciaPasoRepository
                                                .findById(idInstanciaPaso).orElse(null);
                        }
        
                        if (pgimInstanciaPasoActual != null) {
                                if (pgimInstanciaPasoActual.getDeMensaje()
                                                .equals("Iniciar proceso administrativo sancionador")) {
                                        alertaDetalle.setFlLeido(ConstantesUtil.IND_INACTIVO);
                                }
                        }
                }                

                alertaDetalle.setDeDetalleAlerta(mensajeAsunto);
                alertaDetalle.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                alertaDetalle.setFeCreacion(auditoriaDTO.getFecha());
                alertaDetalle.setUsCreacion(auditoriaDTO.getUsername());
                alertaDetalle.setIpCreacion(auditoriaDTO.getTerminal());
                alertaDetalle = alertaDetalleRepository.save(alertaDetalle);
                return alertaDetalle.getIdDetalleAlerta();
        }

        @Override
        @Transactional(readOnly = false)
        public void enviarAlertaParaInteresados(PgimInstanciaProces pgimInstanciaProces,
                        PgimRelacionPaso pgimRelacionPaso, List<String> lNoInteresados, Long idTipoAlerta,
                        AuditoriaDTO auditoriaDTO, Long idProceso, Map<String, String> lNoUsuarios,
                        PgimInstanciaPaso pgimInstanciaPaso) {

                PgimSupervision pgimSupervision = null;
                PgimPas pgimPas = null;
                PgimUnidadMineraDTO pgimUnidadMineraDTO = null;
                String codigoObjeto = null;

                if (lNoInteresados.size() == 0) {
                        return;
                }

                PgimValorParametro pgimValorParametro = valorParametroRepository.getOne(idTipoAlerta);

                if(pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_PAS)){
                        
                        pgimPas = this.pasRepository.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                        pgimUnidadMineraDTO = unidadMineraRepository.obtenerUnidadMineraPorId(pgimPas.getPgimSupervision().getPgimUnidadMinera().getIdUnidadMinera());

                        codigoObjeto = String.format(ConstantesUtil.PARAM_NO_ALERTA_FIS, pgimPas.getIdPas(), pgimUnidadMineraDTO.getNoUnidadMinera(), pgimPas.getPgimSupervision().getCoSupervision());


                } else if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_SUPERVISION)) {

                        pgimSupervision = this.supervisionRepository.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                        pgimUnidadMineraDTO = unidadMineraRepository.obtenerUnidadMineraPorId(pgimSupervision.getPgimUnidadMinera().getIdUnidadMinera());

                        codigoObjeto = String.format(ConstantesUtil.PARAM_NO_ALERTA_SUP, pgimUnidadMineraDTO.getNoUnidadMinera(), pgimSupervision.getCoSupervision());

                }

                final String detalleAlerta = String.format(ConstantesUtil.PARAM_ALERTA_TRANS_PASO,
                                pgimRelacionPaso.getPasoProcesoOrigen().getNoPasoProceso(),
                                lNoUsuarios.get("USUORIGEN"),
                                pgimRelacionPaso.getPasoProcesoDestino().getNoPasoProceso(),
                                lNoUsuarios.get("USUDESTINO"));

                PgimAlerta pgimAlerta = new PgimAlerta();
                pgimAlerta.setTipoAlerta(pgimValorParametro);
                pgimAlerta.setNoAlerta(codigoObjeto);
                pgimAlerta.setDeAlerta(detalleAlerta);
                pgimAlerta.setFeAlerta(auditoriaDTO.getFecha());

                pgimAlerta.setDiEnlace(idProceso + "-" + pgimInstanciaProces.getCoTablaInstancia());

                pgimAlerta.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimAlerta.setFeCreacion(auditoriaDTO.getFecha());
                pgimAlerta.setUsCreacion(auditoriaDTO.getUsername());
                pgimAlerta.setIpCreacion(auditoriaDTO.getTerminal());
                pgimAlerta = alertaRepository.save(pgimAlerta);

                Long idInstanciaPaso = null;
                if (pgimInstanciaPaso != null) {
                        idInstanciaPaso = pgimInstanciaPaso.getIdInstanciaPaso();
                }

                String noUsuarioOrigen = null;
                if (lNoUsuarios != null) {
                        noUsuarioOrigen = lNoUsuarios.get("USUORIGEN");
                }

                for (int i = 0; i < lNoInteresados.size(); i++) {
                        this.enviarAlertaDetalle(pgimAlerta, auditoriaDTO, idInstanciaPaso, noUsuarioOrigen,
                                        lNoInteresados.get(i),
                                        ConstantesUtil.PARAM_DETALLE_ALERTA_SUP_INTERESADO,
                                        this.valorParametroRepository.obtenerIdValorParametro(
                                                        EValorParametro.DEALE_PRA_CNCIMIENTO.toString()));
                }

        }

        @Override
        @Transactional(readOnly = false)
        public void enviarAlertaParaResponsables(PgimInstanciaProces pgimInstanciaProces,
                        PgimRelacionPaso pgimRelacionPaso, Long idInstanciaPaso, List<String> lNoResposables, Long idTipoAlerta,
                        AuditoriaDTO auditoriaDTO, Long idProceso, Map<String, String> lNoUsuarios) {
                if (lNoResposables.size() == 0) {
                        return;
                }
                Long idRelacionPaso = pgimRelacionPaso.getIdRelacionPaso();

                boolean notificar = false;
                if (Long.compare(idRelacionPaso, ConstantesUtil.PARAM_RELACION_PREPARARINICAMPO_INICIARCAMPO) == 0) {
                		idTipoAlerta = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ALERT_FSCLZCION_CMPO_INCIADA.toString());
                        notificar = true;
                } else if (Long.compare(idRelacionPaso, ConstantesUtil.PARAM_ALERTA_SUPER_FALLIDA_REL) == 0) {
                        idTipoAlerta = this.valorParametroRepository.obtenerIdValorParametro(
                                        EValorParametro.ALERT_FSCLIZCION_CMPO_FLLDA.toString());
                        notificar = true;
                } else if (Long.compare(idRelacionPaso, ConstantesUtil.PARAM_ALERTA_SUPER_FINALIZADA_REL) == 0) {
                        idTipoAlerta = this.valorParametroRepository.obtenerIdValorParametro(
                                        EValorParametro.ALERT_FSCLZCION_CMPO_FNLZADA.toString());
                        notificar = true;
                } else if (Long.compare(idRelacionPaso, ConstantesUtil.PARAM_ALERTA_SUPER_CANCELADA_REL1) == 0
                                || Long.compare(idRelacionPaso, ConstantesUtil.PARAM_ALERTA_SUPER_CANCELADA_REL2) == 0
                                || Long.compare(idRelacionPaso, ConstantesUtil.PARAM_ALERTA_SUPER_CANCELADA_REL3) == 0
                                || Long.compare(idRelacionPaso, ConstantesUtil.PARAM_ALERTA_SUPER_CANCELADA_REL4) == 0
                                || Long.compare(idRelacionPaso,
                                                ConstantesUtil.PARAM_ALERTA_SUPER_CANCELADA_REL5) == 0) {
                        idTipoAlerta = this.valorParametroRepository
                                        .obtenerIdValorParametro(EValorParametro.ALERT_FSCLZCION_CNCLDA.toString());
                        notificar = true;
                } else if (Long.compare(idRelacionPaso, ConstantesUtil.PARAM_ALERTA_SUPER_FINALIZADA_IAIP_REL) == 0) {
                        idTipoAlerta = this.valorParametroRepository.obtenerIdValorParametro(
                                        EValorParametro.ALERT_FSCLZCION_FNLZDA_CON_IAIP.toString());
                        notificar = true;
                }

                if (!notificar) {
                        return;
                }

                PgimAlerta pgimAlerta = this.asegurarAlerta(pgimInstanciaProces,
                                pgimRelacionPaso, lNoUsuarios, idTipoAlerta,
                                idProceso, null, auditoriaDTO);

                String noUsuarioOrigen = null;
                if (lNoUsuarios != null) {
                        noUsuarioOrigen = lNoUsuarios.get("USUORIGEN");
                }

                for (int i = 0; i < lNoResposables.size(); i++) {
                        this.enviarAlertaDetalle(pgimAlerta, auditoriaDTO, null, noUsuarioOrigen, lNoResposables.get(i),
                                        String.format(ConstantesUtil.PARAM_DETALLE_ALERTA_RESPONSABLE, objeto),
                                        this.valorParametroRepository.obtenerIdValorParametro(
                                                        EValorParametro.DEALE_PRA_CNCIMIENTO.toString()));
                }
        }

        @Override
        public ResponseEntity<ResponseDTO> obtenerRuta(PgimAlertaDetalleDTO pgimAlertaDetalleDTO) {
                String[] elementos = pgimAlertaDetalleDTO.getDescDiEnlace().split("-");
                Long idProceso = Long.parseLong(elementos[0]);
                Long idObjetoProceso = Long.parseLong(elementos[1]);
                Long idInstanciaPaso = 0L;
                String diEnlace = "";

                List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesRepository
                        .obtenerInstanciasProceso(idProceso, idObjetoProceso);

                if (lPgimInstanciaProcesDTO.size() == 1) {

                        List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTOActuales = this.instanciaPasoRepository
                                .obtenerInstanciaPasosActualesPorIdInstanciaProceso(lPgimInstanciaProcesDTO.get(0).getIdInstanciaProceso());
                        if (lPgimInstanciaPasoDTOActuales.size() > 0) {
                                idInstanciaPaso = lPgimInstanciaPasoDTOActuales.get(0).getIdInstanciaPaso();
                        }else{
                                String msgExcepcion = "No se ha encontrado ningún paso actual";
                                throw new PgimException(TipoResultado.WARNING, msgExcepcion);
                        }

                } else {
                        String msgExcepcion = String.format(
                                        "Se ha encontrado más de una instancia de proceso para el id de proceso d% y código de instancia d% ",
                                        idProceso, idObjetoProceso);
                        throw new PgimException(TipoResultado.WARNING, msgExcepcion);
                }

                // RUTA FISCALIZACIÓN
                if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
                        diEnlace = String.format(ConstantesUtil.PARAM_ENLACE_REENVIAR_SUP,
                                        idInstanciaPaso);
                }

                // RUTA LIQUIDACIÓN
                else if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {
                        diEnlace = String.format(ConstantesUtil.PARAM_ENLACE_REENVIAR_LIQ,
                                        idInstanciaPaso);
                }

                // RUTA PROGRAMACIÓN
                else if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_PROGRAMACION)) {
                        diEnlace = String.format(ConstantesUtil.PARAM_ENLACE_REENVIAR_PRO,
                                        idInstanciaPaso);
                }

                // RUTA PAS
                else if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)) {
                        diEnlace = String.format(ConstantesUtil.PARAM_ENLACE_REENVIAR_PAS,
                                        idInstanciaPaso);
                }

                // RUTA RANKING DE RIESGO
                else if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS)) {
                        diEnlace = String.format(ConstantesUtil.PARAM_ENLACE_REENVIAR_RNK,
                                        idInstanciaPaso);
                }

                // RUTA CONFIGURACIÓN DE RIESGO
                else if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO)) {
                        diEnlace = String.format(ConstantesUtil.PARAM_ENLACE_REENVIAR_CONF,
                                        idInstanciaPaso);
                }

                ResponseDTO respuesta = new ResponseDTO(TipoResultado.SUCCESS, diEnlace); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }

        @Override
        public PgimAlertaDetalleDTO contarAlertasPendientes(AuditoriaDTO auditoriaDTO) {

                String userName = auditoriaDTO.getUsername();

                return this.alertaDetalleRepository.contarAlertasPendientes(userName);
        }


        @Override
        // @Transactional(readOnly = false)
        public PgimAlerta enviarAlerta(PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO,
                List<String> lNoUsuarios, Long idInstanciaPaso, String codTipoAlerta, AuditoriaDTO auditoriaDTO) throws Exception{

                // Registro de alerta en PGIM_TC_ALERTA
                String noAlerta = "";
                String diEnlace = "";
                String detalleAlerta = pgimIprocesoAlertaDTO.getDescDeAlerta();
                String descDetalleAlerta = pgimIprocesoAlertaDTO.getDescDetAlerta();
                Long idTipoAlerta = this.valorParametroRepository.obtenerIdValorParametro(codTipoAlerta);

                PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository
                        .findById(pgimIprocesoAlertaDTO.getIdInstanciaProceso()).orElse(null);

                PgimInstanPasoAuxDTO pgimInstanPasoAuxDTO = this.flujoTrabajoService
                        .obtenerInstanciaPasoAuxPorId(idInstanciaPaso);

                noAlerta = pgimInstanPasoAuxDTO.getNoEtiquetaOtrabajo();
                diEnlace = pgimInstanciaProces.getPgimProceso().getIdProceso() + "-" + pgimInstanciaProces.getCoTablaInstancia();

                PgimAlerta pgimAlerta = new PgimAlerta();
                pgimAlerta.setTipoAlerta(new PgimValorParametro());
                pgimAlerta.getTipoAlerta().setIdValorParametro(idTipoAlerta);;
                
                if(pgimIprocesoAlertaDTO.getIdIprocesoAlerta() != null) {
                	pgimAlerta.setPgimIprocesoAlerta(new PgimIprocesoAlerta());
                	pgimAlerta.getPgimIprocesoAlerta().setIdIprocesoAlerta(pgimIprocesoAlertaDTO.getIdIprocesoAlerta());
                }
                
                pgimAlerta.setNoAlerta(noAlerta);
                pgimAlerta.setDeAlerta(detalleAlerta);
                pgimAlerta.setFeAlerta(auditoriaDTO.getFecha());
                pgimAlerta.setDiEnlace(diEnlace);

                if (idInstanciaPaso != null) {
                        pgimAlerta.setPgimInstanciaPaso(new PgimInstanciaPaso());
                        pgimAlerta.getPgimInstanciaPaso().setIdInstanciaPaso(idInstanciaPaso);
                }

                pgimAlerta.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimAlerta.setFeCreacion(auditoriaDTO.getFecha());
                pgimAlerta.setUsCreacion(auditoriaDTO.getUsername());
                pgimAlerta.setIpCreacion(auditoriaDTO.getTerminal());
                pgimAlerta = alertaRepository.save(pgimAlerta);

                // registro del detalle en PGIM_TD_ALERTA_DETALLE
                Long idTipoDetalleAlerta = this.valorParametroRepository
                        .obtenerIdValorParametro(EValorParametro.DEALE_PRA_TMAR_ACCION.toString());
                for (int i = 0; i < lNoUsuarios.size(); i++) {
                        this.enviarAlertaDetalle(pgimAlerta, auditoriaDTO, idInstanciaPaso, 
                                        null, lNoUsuarios.get(i), descDetalleAlerta,
                                        idTipoDetalleAlerta);
                }
                
                return pgimAlerta;
        }
        
        
        @Override
        public PgimAlerta registrarAlertaYDetalle(Long idInstanciaProceso, PgimAlertaDTO pgimAlertaDTO,
                List<String> lNoUsuariosDestino, AuditoriaDTO auditoriaDTO) throws Exception {

                // Registro de alerta en PGIM_TC_ALERTA
                String noAlerta = "";
                String diEnlace = "";
                Long idInstanciaPaso = pgimAlertaDTO.getIdInstanciaPaso();

                PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository
                        .findById(idInstanciaProceso).orElse(null);
                
                PgimInstanPasoAuxDTO pgimInstanPasoAuxDTO = new PgimInstanPasoAuxDTO();

                if(idInstanciaPaso != null) {
                	pgimInstanPasoAuxDTO = this.flujoTrabajoService
                			.obtenerInstanciaPasoAuxPorId(idInstanciaPaso);                	
                }

                noAlerta = pgimInstanPasoAuxDTO.getNoEtiquetaOtrabajo();
                diEnlace = pgimInstanciaProces.getPgimProceso().getIdProceso() + "-" + pgimInstanciaProces.getCoTablaInstancia();

                PgimAlerta pgimAlerta = new PgimAlerta();
                
                pgimAlerta.setTipoAlerta(new PgimValorParametro());
                pgimAlerta.getTipoAlerta().setIdValorParametro(pgimAlertaDTO.getIdTipoAlerta());;
                
                if(pgimAlertaDTO.getIdIprocesoAlerta() != null) {
                	pgimAlerta.setPgimIprocesoAlerta(new PgimIprocesoAlerta());
                	pgimAlerta.getPgimIprocesoAlerta().setIdIprocesoAlerta(pgimAlertaDTO.getIdIprocesoAlerta());
                }
                
                if (idInstanciaPaso != null) {
                    pgimAlerta.setPgimInstanciaPaso(new PgimInstanciaPaso());
                    pgimAlerta.getPgimInstanciaPaso().setIdInstanciaPaso(idInstanciaPaso);
                }
                
                pgimAlerta.setNoAlerta(noAlerta);
                pgimAlerta.setDeAlerta(pgimAlertaDTO.getDeAlerta());
                pgimAlerta.setFeAlerta(auditoriaDTO.getFecha());
                pgimAlerta.setDiEnlace(diEnlace);               

                pgimAlerta.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimAlerta.setFeCreacion(auditoriaDTO.getFecha());
                pgimAlerta.setUsCreacion(auditoriaDTO.getUsername());
                pgimAlerta.setIpCreacion(auditoriaDTO.getTerminal());
                
                PgimAlerta pgimAlertaCreada = alertaRepository.save(pgimAlerta);

                // registro del detalle en PGIM_TD_ALERTA_DETALLE
                for (int i = 0; i < lNoUsuariosDestino.size(); i++) {
                        this.enviarAlertaDetalle(pgimAlertaCreada, auditoriaDTO, idInstanciaPaso, 
                                        null, lNoUsuariosDestino.get(i), pgimAlertaDTO.getDescDeDetalleAlerta(),
                                        pgimAlertaDTO.getDescIdTipoDetalleAlerta());
                }
                
                return pgimAlertaCreada;
        }

        @Override
        @Transactional(readOnly = false)
        public void crearAlertasEspecificas(PgimInstanciaProces pgimInstanciaProces,
                PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

	        Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();	        
	
	        // Alerta por aprobación de informe de fiscalización	        
	        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD)) {
	        	
	        	String codTipoAlerta = EValorParametro.ALERT_APRBCION_INFRME_FSCLZCION.toString();
	        	String codTipoDetalleAlerta = EValorParametro.DEALE_PRA_CNCIMIENTO.toString();
	        	
	        	List<String> lNoUsuarios = new ArrayList<String>();
	        	
	        	lNoUsuarios = this.listarDestinatariosNotificacionAlerta(pgimInstanciaPaso);

	        	if(lNoUsuarios.size() > 0) {    	        		
	        		String deAlerta = ConstantesUtil.PARAM_ALERTA_APROBACION_INFORME_FISC_EMP_SUP;
	        		String deDetalleAlerta = ConstantesUtil.PARAM_DETALLE_ALERTA_APROBACION_INFORME_FISC_EMP_SUP;
		        	
		        	PgimAlertaDTO pgimAlertaDTO = new PgimAlertaDTO();
		        	pgimAlertaDTO.setIdInstanciaPaso(pgimInstanciaPaso.getInstanciaPasoPadre().getIdInstanciaPaso());
		        	pgimAlertaDTO.setIdTipoAlerta(this.valorParametroRepository.obtenerIdValorParametro(codTipoAlerta));
		        	pgimAlertaDTO.setDeAlerta(deAlerta);
		        	pgimAlertaDTO.setDescDeDetalleAlerta(deDetalleAlerta);
		        	pgimAlertaDTO.setDescIdTipoDetalleAlerta(this.valorParametroRepository.obtenerIdValorParametro(codTipoDetalleAlerta));
		        	
		        	this.registrarAlertaYDetalle(pgimInstanciaProces.getIdInstanciaProceso(), pgimAlertaDTO, lNoUsuarios, auditoriaDTO);
	        	}	        	        
	        }		      
        }
        
        @Override
        public List<String> listarDestinatariosNotificacionAlerta(PgimInstanciaPaso pgimInstanciaPaso) throws Exception {

	        Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
	        
	        List<String> lNoUsuarios = new ArrayList<String>();
	
	        // Alerta por aprobación de informe de fiscalización	        
	        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD)) {

	        	// Obtenemos la lista de fiscalizadores de la instancia proceso, de empresa supervisora
	        	List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = this.eqpInstanciaProService.obtenerPersonalXRolContrato(	        	
	        			pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso(), ConstantesUtil.PROCESO_ROL_SUPERVISOR);	        	
	        	
	        	if(lPgimEqpInstanciaProDTO.size() > 0) {
	        		//En general se maneja una sola cuenta de usuario fiscalizador por empresa supervisora, pero desde ya, se está cubriendo la posibilidad de ser múltiple
		        	lNoUsuarios = lPgimEqpInstanciaProDTO.stream()
		        			.map(pgimEqpInstanciaProDTO -> pgimEqpInstanciaProDTO.getDescNoUsuario())
		        			.distinct()
							.collect(Collectors.toList());		
		        	
	        	}	        	        
	        }	
	        
	        return lNoUsuarios; 
        }
        
}
