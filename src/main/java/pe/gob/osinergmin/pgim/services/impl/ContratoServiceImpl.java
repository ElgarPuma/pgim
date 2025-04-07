package pe.gob.osinergmin.pgim.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConsumoContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSegumntoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPenalidadAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrspstoGastoSuperDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;
import pe.gob.osinergmin.pgim.models.entity.PgimEspecialidad;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.ContratoSegumntoAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.ContratoSiafAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.CostoUnitarioRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemConsumoContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.PenalidadAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.ProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.PrspstoGastoSuperRepository;
import pe.gob.osinergmin.pgim.services.ContratoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.PersonalContratoService;
import pe.gob.osinergmin.pgim.services.TarifarioContratoService;
import pe.gob.osinergmin.pgim.services.TipoParametroMedicionPorContratoService;
import pe.gob.osinergmin.pgim.siged.BuscarExpedienteOut;
import pe.gob.osinergmin.pgim.siged.wssoap.SigedSoapService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Contrato
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020
 */
@Service
@Transactional(readOnly = true)
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private TarifarioContratoService tarifarioContratoService;

    @Autowired
    private InstanciaProcesRepository instanciaProcesRepository;

    @Autowired
    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private CostoUnitarioRepository costoUnitarioRepository;

    @Autowired
    private ItemConsumoContratoRepository itemConsumoContratoRepository;

    @Autowired
    private SigedSoapService sigedSoapService;

    @Autowired
    private ProcesoRepository procesoRepository;

    @Autowired
    private ContratoSegumntoAuxRepository contratoSegumntoAuxRepository;

    @Autowired
    private ContratoSiafAuxRepository contratoSiafAuxRepository;

    @Autowired
    private PenalidadAuxRepository penalidadAuxRepository;

    @Autowired
    private PrspstoGastoSuperRepository prspstoGastoSuperRepository;

    @Autowired
    private TipoParametroMedicionPorContratoService tipoParametroMedicionPorContratoService;

    @Autowired
    private PersonalContratoRepository personalContratoRepository;
    
    @Autowired
    private PersonalContratoService personalContratoService;

    @Override
    public List<PgimContratoDTO> obtenerContratos() {
        List<PgimContratoDTO> lPgimContratoDTO = contratoRepository.obtenerContratos();
        return lPgimContratoDTO;
    }

    @Override
    public List<PgimContratoDTO> obtenerContratos(AuditoriaDTO obtenerAuditoria) {
        String userName = obtenerAuditoria.getUsername();
        List<PgimContratoDTO> lPgimContratoDTO = contratoRepository.obtenerContratos(userName);
        return lPgimContratoDTO;
    }

    @Override
    public Page<PgimContratoDTO> listarContratos(PgimContratoDTO filtroContrato, Pageable paginador) {
        Page<PgimContratoDTO> pPgimContratoDTO = this.contratoRepository.listarContratos(
                filtroContrato.getNuContrato(), filtroContrato.getDescNoRazonSocial(),
                filtroContrato.getDescNuExpedienteSiged(), filtroContrato.getIdEspecialidad(),
                filtroContrato.getTextoBusqueda(), paginador);

        return pPgimContratoDTO;
    }

    @Override
    public List<PgimContratoDTO> listarPorCodigoContrato(String palabra) {
        List<PgimContratoDTO> lPgimContratoDTO = this.contratoRepository.listarPorCodigoContrato(palabra);

        return lPgimContratoDTO;
    }

    @Override
    public List<PgimContratoDTO> listarPorNumeroExpediente(String palabra) {
        List<PgimContratoDTO> lPgimContratoDTO = this.contratoRepository.listarPorNumeroExpediente(palabra);

        return lPgimContratoDTO;
    }

    @Override
    public PgimContratoDTO obtenerContratoPorId(Long idContrato) {
        return this.contratoRepository.obtenerContratoPorId(idContrato);

    }

    @Override
    public PgimContratoDTO obtenerContrato(Long idContrato) {
        return this.contratoRepository.obtenerContrato(idContrato);
    }

    /**
     * Permite obtener contrato para seguimiento
     *
     * @param pgimContratoDTO
     * @param idContrato
     * @return
     */
    @Override
    public PgimContratoDTO obtenerContratoSeguimientoPorId(Long idContrato) {

        // Registro del contrato
        PgimContratoDTO pgimContratoDTO = this.contratoRepository.obtenerContratoPorId(idContrato);

        // Resumen del consumo del contrato S/
        PgimContratoDTO pgimContratoDTOresumen = this.contratoRepository.obtenerMontoConsumoContrato(idContrato);
        if (pgimContratoDTOresumen != null) {
            pgimContratoDTO.setDescResumenConsumoContrato(pgimContratoDTOresumen.getDescResumenConsumoContrato());
        } else {
            pgimContratoDTO.setDescResumenConsumoContrato(BigDecimal.ZERO);
        }

        // Saldo del contrato S/
        if (pgimContratoDTOresumen != null)
            pgimContratoDTO.setDescSaldoContrato(pgimContratoDTO.getMoImporteContrato()
                    .subtract(pgimContratoDTOresumen.getDescResumenConsumoContrato()));
        else
            pgimContratoDTO.setDescSaldoContrato(pgimContratoDTO.getMoImporteContrato());

        // Pre-comprometido
        PgimContratoDTO pgimContratoDTOpreComp = this.contratoRepository.obtenerMontoConsumoContratoPorEstadio(
                idContrato, ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_PRECOMPROMETIDO);
        if (pgimContratoDTOpreComp != null)
            pgimContratoDTO.setDescMontoTotalPreComprometido(pgimContratoDTOpreComp.getDescResumenConsumoContrato());

        // Comprometido
        PgimContratoDTO pgimContratoDTOComp = this.contratoRepository.obtenerMontoConsumoContratoPorEstadio(idContrato,
                ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_COMPROMETIDO);
        if (pgimContratoDTOComp != null)
            pgimContratoDTO.setDescMontoTotalComprometido(pgimContratoDTOComp.getDescResumenConsumoContrato());

        // Por Liquidar
        PgimContratoDTO pgimContratoDTOporLiquidar = this.contratoRepository.obtenerMontoConsumoContratoPorEstadio(
                idContrato, ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_PORLIQUIDAR);
        if (pgimContratoDTOporLiquidar != null)
            pgimContratoDTO.setDescMontoTotalPorLiquidar(pgimContratoDTOporLiquidar.getDescResumenConsumoContrato());

        // Liquidado
        PgimContratoDTO pgimContratoDTOLiquidado = this.contratoRepository
                .obtenerMontoConsumoContratoPorEstadio(idContrato, ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_LIQUIDADO);
        if (pgimContratoDTOLiquidado != null)
            pgimContratoDTO.setDescMontoTotalLiquidado(pgimContratoDTOLiquidado.getDescResumenConsumoContrato());

        // Facturado
        PgimContratoDTO pgimContratoDTOFacturado = this.contratoRepository
                .obtenerMontoConsumoContratoPorEstadio(idContrato, ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_FACTURADO);
        if (pgimContratoDTOFacturado != null)
            pgimContratoDTO.setDescMontoTotalFacturado(pgimContratoDTOFacturado.getDescResumenConsumoContrato());

        return pgimContratoDTO;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimContratoDTO crearContrato(@Valid PgimContratoDTO pgimContratoDTO, AuditoriaDTO auditoriaDTO)
            throws NullPointerException {

        String flEst = "1";

        PgimContrato pgimContrato = new PgimContrato();

        this.configurarValores(pgimContratoDTO, pgimContrato);

        pgimContrato.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimContrato.setFeCreacion(auditoriaDTO.getFecha());
        pgimContrato.setUsCreacion(auditoriaDTO.getUsername());
        pgimContrato.setIpCreacion(auditoriaDTO.getTerminal());
        pgimContrato.setFlEstado(flEst);

        PgimEmpresaSupervisora pgimEmpresaSupervisora = new PgimEmpresaSupervisora();
        pgimEmpresaSupervisora.setIdEmpresaSupervisora(pgimContratoDTO.getIdEmpresaSupervisora());
        pgimContrato.setPgimEmpresaSupervisora(pgimEmpresaSupervisora);

        PgimContrato pgimContratoCreado = this.contratoRepository.save(pgimContrato);

        // Se asegura la instancia del proceso
        List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService.asegurarInstanciasProceso(ConstantesUtil.PARAM_PROCESO_CONTRATO, pgimContratoCreado.getIdContrato(), auditoriaDTO);

        PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

        PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository.findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

        pgimInstanciaProcesActual.setNuExpedienteSiged(pgimContratoDTO.getDescNuExpedienteSiged());
        pgimContrato.setPgimInstanciaProces(pgimInstanciaProcesActual);

        // Se actualiza la instancia del proceso en el registro del contrato
        this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);

        PgimContratoDTO pgimContratoDTOCreado = this.obtenerContratoPorId(pgimContratoCreado.getIdContrato());

        return pgimContratoDTOCreado;
    }

    @Transactional(readOnly = false)
    private PgimContrato configurarValores(PgimContratoDTO pgimContratoDTO, PgimContrato pgimContrato) {

        pgimContrato.setNuContrato(pgimContratoDTO.getNuContrato());
        pgimContrato.setFeInicioContrato(pgimContratoDTO.getFeInicioContrato());
        pgimContrato.setFeFinContrato(pgimContratoDTO.getFeFinContrato());

        pgimContrato.setDeContrato(pgimContratoDTO.getDeContrato());

        pgimContrato.setPgimEspecialidad(new PgimEspecialidad());
        pgimContrato.getPgimEspecialidad().setIdEspecialidad(pgimContratoDTO.getIdEspecialidad());

        pgimContrato.setPcEntregableActa(pgimContratoDTO.getPcEntregableActa());
        pgimContrato.setPcEntregableInforme(pgimContratoDTO.getPcEntregableInforme());
        pgimContrato.setPcEntregableFinal(pgimContratoDTO.getPcEntregableFinal());

        pgimContrato.setMoImporteContrato(pgimContratoDTO.getMoImporteContrato());

        pgimContrato.setNuDiasEntregaInforme(pgimContratoDTO.getNuDiasEntregaInforme());
        pgimContrato.setNuDiasAbsolucionInforme(pgimContratoDTO.getNuDiasAbsolucionInforme());
        pgimContrato.setNuDiasRevisionInforme(pgimContratoDTO.getNuDiasRevisionInforme());
        pgimContrato.setNoUsuarioXDefecto(pgimContratoDTO.getNoUsuarioXDefecto());

        return pgimContrato;

    }

    @Override
    public PgimContrato getByIdContrato(Long idContrato) {
        return this.contratoRepository.findById(idContrato).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimContratoDTO modificarContrato(PgimContratoDTO pgimContratoDTO, PgimContrato pgimContrato,
            AuditoriaDTO auditoriaDTO) {

        pgimContrato = this.configurarValores(pgimContratoDTO, pgimContrato);

        pgimContrato.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimContrato.setFeActualizacion(auditoriaDTO.getFecha());
        pgimContrato.setUsActualizacion(auditoriaDTO.getUsername());
        pgimContrato.setIpActualizacion(auditoriaDTO.getTerminal());
        pgimContrato.setFlEstado(pgimContratoDTO.getFlEstado());
        PgimEmpresaSupervisora pgimEmpresaSupervisora = new PgimEmpresaSupervisora();
        pgimEmpresaSupervisora.setIdEmpresaSupervisora(pgimContratoDTO.getIdEmpresaSupervisora());
        pgimContrato.setPgimEmpresaSupervisora(pgimEmpresaSupervisora);

        PgimContrato pgimContratoModificado = this.contratoRepository.save(pgimContrato);

        // Se asegura la instancia del proceso
        List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService.asegurarInstanciasProceso(ConstantesUtil.PARAM_PROCESO_CONTRATO, pgimContratoModificado.getIdContrato(), auditoriaDTO);

        PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

        PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository.findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

        pgimInstanciaProcesActual.setNuExpedienteSiged(pgimContratoDTO.getDescNuExpedienteSiged());
        pgimContrato.setPgimInstanciaProces(pgimInstanciaProcesActual);

        /* Procedemos a actualizar el usuario por defecto a todos los personales del contrato */
        this.actualizarUsuarioPorDefectoPersonalContrato(pgimContratoDTO);

        // Se actualiza la instancia del proceso en el registro del contrato
        this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);
        
        PgimContratoDTO pgimContratoDTOResultado = this.obtenerContratoPorId(pgimContratoModificado.getIdContrato());

        return pgimContratoDTOResultado;
    }

    /**
    * ----------------------------------------------------------------------------------------------------------------------------------------
    * Story: https://relazta.atlassian.net/browse/PGIM-9273 : «Nombre usuario por defecto» al crear persona de contrato de supervisión
    * ----------------------------------------------------------------------------------------------------------------------------------------
    **/
    @Transactional(readOnly = false)
    public void actualizarUsuarioPorDefectoPersonalContrato(PgimContratoDTO pgimContratoDTO) {

        /* Obtenemos la lista de personales del contrato por el id del contrato */
        List<PgimPersonalContratoDTO> lPgimPersonalContratoDTO = this.personalContratoRepository.listarUsuarioPersonalContratoPorContrato(pgimContratoDTO.getIdContrato());

        /* En caso no haya personales durante la actualización del contrato, se procede a omitir */
        if (lPgimPersonalContratoDTO.size() > 0) {

            /* Actualizamos en este bucle de personas del contrato, su usuario que actualizara por defecto obtenida del contrato */
            for (PgimPersonalContratoDTO pgimPersonalContratoDTO : lPgimPersonalContratoDTO) {

                pgimPersonalContratoDTO.setNoUsuario(pgimContratoDTO.getNoUsuarioXDefecto());

                PgimPersonalContrato pgimPersonalContrato = this.personalContratoService.getByIdPersonalContrato(pgimPersonalContratoDTO.getIdPersonalContrato());

                pgimPersonalContrato.setNoUsuario(pgimPersonalContratoDTO.getNoUsuario());

                this.personalContratoRepository.save(pgimPersonalContrato);
            }
        }
    }


    @Transactional(readOnly = false)
    @Override
    public void eliminarContrato(PgimContrato pgimContratoActual, AuditoriaDTO auditoriaDTO) {
        pgimContratoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimContratoActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimContratoActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimContratoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.contratoRepository.save(pgimContratoActual);

    }

    @Override
    public List<PgimContratoDTO> existeContrato(Long idContrato, String nuContrato) {
        List<PgimContratoDTO> pPgimContratoDTO = this.contratoRepository.existeContrato(idContrato, nuContrato);

        return pPgimContratoDTO;
    }

    @Override
    public List<PgimContratoDTO> existeNuExpediente(Long idInstanciaProceso, String nuExpedienteSiged) {
        List<PgimContratoDTO> pPgimContratoDTO = this.contratoRepository.existeNuExpediente(idInstanciaProceso,
                nuExpedienteSiged);

        return pPgimContratoDTO;
    }

    @Override
    public PgimCostoUnitarioDTO obtenerCostoUnitario(PgimSupervisionDTO pgimSupervisionDTO, boolean flagFechaReal,
            Long cantidadDiasEntre) {
        List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO = this.tarifarioContratoService.listarTarifariosCumplenRegla(
                pgimSupervisionDTO.getDescIdContrato(), pgimSupervisionDTO.getIdSubtipoSupervision(),
                pgimSupervisionDTO.getIdMotivoSupervision());

        PgimTarifarioReglaDTO pgimTarifarioReglaDTO = null;

        if (lPgimTarifarioReglaDTO.size() == 1) {
            pgimTarifarioReglaDTO = lPgimTarifarioReglaDTO.get(0);
        } else if (lPgimTarifarioReglaDTO.size() == 0) {
            throw new PgimException(TipoResultado.WARNING, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                    "No se ha encontrado ninguna regla válida para determinar el tarifario del contrato seleccionado, por favor coordine con su Especialista Administrativo/a");
        } else {
            throw new PgimException(
                    TipoResultado.WARNING, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                    "Se ha encontrado más de una regla válida para determinar el tarifario del contrato seleccionado, por favor coordine con su Especialista Administrativo/a");
        }

        // Si se llega hasta acá entonces se debe determinar el costo unitario del
        // tarifario en cuestión.
        List<PgimCostoUnitarioDTO> lPgimCostoUnitarioDTO = this.costoUnitarioRepository
                .obtenerCUnitarioPorIdTarifarioYDiaCosto(pgimTarifarioReglaDTO.getIdTarifarioContrato(),
                        cantidadDiasEntre.intValue());

        PgimCostoUnitarioDTO pgimCostoUnitarioDTO = null;

        if (lPgimCostoUnitarioDTO.size() == 1) {
            pgimCostoUnitarioDTO = lPgimCostoUnitarioDTO.get(0);
        } else if (lPgimCostoUnitarioDTO.size() == 0) {
            throw new PgimException(TipoResultado.WARNING, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                    "No se ha encontrado ningún costo unitario definido para las condiciones establecidas");
        } else {
            throw new PgimException(
                    TipoResultado.WARNING, // STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                    "Se ha encontrado más de una definición de costo unitario para las condiciones establecidas");
        }

        return pgimCostoUnitarioDTO;
    }

    @Override
    public PgimConsumoContraDTO calcularCostoSupervision(PgimSupervisionDTO pgimSupervisionDTO, boolean flagFechaReal) {

        Long cantidadDiasEntre = this.diasCalendarioSupervision(pgimSupervisionDTO, flagFechaReal);
        PgimCostoUnitarioDTO pgimCostoUnitarioDTO = this.obtenerCostoUnitario(pgimSupervisionDTO, flagFechaReal,
                cantidadDiasEntre);

        BigDecimal moConsumoContrato = pgimCostoUnitarioDTO.getMoCostoUnitario();

        PgimTarifarioContratoDTO pgimTarifarioContratoDTO = this.tarifarioContratoService
                .obtenerTarifarioContratoPorId(pgimCostoUnitarioDTO.getIdTarifarioContrato());

        PgimConsumoContraDTO pgimConsumoContraDTO = new PgimConsumoContraDTO();
        pgimConsumoContraDTO.setIdContrato(pgimSupervisionDTO.getDescIdContrato());
        pgimConsumoContraDTO.setDescNoTarifario(pgimTarifarioContratoDTO.getNoTarifario());
        pgimConsumoContraDTO.setDescMoSupervisionFallida(pgimTarifarioContratoDTO.getMoSupervisionFallida());
        pgimConsumoContraDTO.setDescDiasCalculadosInicioFin(cantidadDiasEntre);
        pgimConsumoContraDTO.setDescMoCostoUnitario(pgimCostoUnitarioDTO.getMoCostoUnitario());
        pgimConsumoContraDTO.setMoConsumoContrato(moConsumoContrato);

        return pgimConsumoContraDTO;
    }

    @Override
    public PgimContratoDTO calcularSaldoContrato(PgimContratoDTO pgimContratoDTO) {
        // Obtener contrato
        PgimContratoDTO pgimContratoDTOResultado = this.obtenerContratoPorId(pgimContratoDTO.getIdContrato());

        // Obtener consumo
        BigDecimal consumoTotal = this.obtenerMontoConsumoTotal(pgimContratoDTO.getIdContrato());
        pgimContratoDTOResultado.setDescMontoTotalComprometido(consumoTotal);

        // Preparar resultado
        pgimContratoDTOResultado.setDescSaldoContrato(pgimContratoDTOResultado.getMoImporteContrato()
                .subtract(pgimContratoDTOResultado.getDescMontoTotalComprometido()));

        if (pgimContratoDTOResultado.getDescSaldoContrato().signum() != 1) {
            pgimContratoDTOResultado
                    .setDescMensajeValidacionSaldoContrato("Saldo insuficiente para ejecutar la fiscalización.");
        }

        return pgimContratoDTOResultado;
    }

    @Override
    public BigDecimal obtenerMontoConsumoTotal(Long idContrato) {
        BigDecimal consumoTotalizado = this.itemConsumoContratoRepository.obtenerConsumoContratoTotalizado(idContrato);

        if (consumoTotalizado != null)
            return consumoTotalizado;
        else
            return BigDecimal.ZERO;
    }

    /**
     * Permite validar el saldo del contrato para una supervisión.
     * 
     * @param pgimSupervisionDTO
     * @param pgimContratoDTO
     * @return
     */
    @Override
    public PgimConsumoContraDTO validarSaldoContratoXsupervision(PgimContratoDTO pgimContratoDTO,
            PgimSupervisionDTO pgimSupervisionDTO) {

        PgimContratoDTO pgimContratoSaldo = this.calcularSaldoContrato(pgimContratoDTO);

        boolean flagSuperReal = false;
        if (pgimSupervisionDTO.getFeInicioSupervisionReal() != null)
            flagSuperReal = true;

        PgimConsumoContraDTO pgimConsumoContra = this.calcularCostoSupervision(pgimSupervisionDTO, flagSuperReal);
        pgimConsumoContra.setDescSaldoContrato(pgimContratoSaldo.getDescSaldoContrato());
        pgimConsumoContra.setDescMontoFaltante(
                pgimConsumoContra.getMoConsumoContrato().subtract(pgimContratoSaldo.getDescSaldoContrato()));

        return pgimConsumoContra;
    }

    @Override
    public String validarExpedienteSiged(String nuExpedienteSiged, AuditoriaDTO auditoriaDTO) {
        String salida = "";
        BuscarExpedienteOut buscarExpedienteOut = sigedSoapService.obtenerExpedienteSiged(nuExpedienteSiged,
                auditoriaDTO);
        if (buscarExpedienteOut.getResultCode() != null && buscarExpedienteOut.getResultCode().intValue() == 1) {
            PgimProceso pgimProceso = procesoRepository.findById(ConstantesUtil.PARAM_PROCESO_CONTRATO).orElse(null);

            if (buscarExpedienteOut.getIdProceso().equals(pgimProceso.getCoProcesoSiged().toString())) {
                salida = "OK";
            } else {
                salida = "ERROR";
            }
        } else if (buscarExpedienteOut.getErrorCode().toString().equals(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS)) {
            salida = "ERROR";
        }

        return salida;
    }

    @Override
    public Page<PgimContratoSegumntoAuxDTO> listarContratoSeguimientoAux(
            PgimContratoSegumntoAuxDTO filtroContratoSegumntoAux, Pageable paginador) {
        Page<PgimContratoSegumntoAuxDTO> pPgimContratoSegumntoAuxDTO = this.contratoSegumntoAuxRepository
                .listarContratoSeguimientoAux(filtroContratoSegumntoAux.getNuContrato(),
                        filtroContratoSegumntoAux.getNoRazonSocial(), filtroContratoSegumntoAux.getIdEspecialidad(),
                        paginador);

        return pPgimContratoSegumntoAuxDTO;
    }

    @Override
    public Page<PgimContratoSiafAuxDTO> listarReporteEjecucionPresupuestal(
            PgimContratoSiafAuxDTO filtroContratoSiafAuxDTO, Pageable paginador) {
        Page<PgimContratoSiafAuxDTO> pPgimContratoSegumntoAuxDTO = this.contratoSiafAuxRepository
                .listarPgimContratoSiafAux(filtroContratoSiafAuxDTO.getNuAnio(),
                        filtroContratoSiafAuxDTO.getDescNuContrato(), paginador);

        return pPgimContratoSegumntoAuxDTO;
    }

    @Override
    public Page<PgimPenalidadAuxDTO> listarReportePenalidadesPeriodoContratoSupervisora(
            PgimPenalidadAuxDTO filtroPenalidadAuxDTO, Pageable paginador) {
        Page<PgimPenalidadAuxDTO> pPgimPenalidadAuxDTO = this.penalidadAuxRepository
                .listarReportePenalidadesPeriodoContratoSupervisora(
                        filtroPenalidadAuxDTO.getNuContrato(), filtroPenalidadAuxDTO.getDescNoRazonSocial(),
                        filtroPenalidadAuxDTO.getDescFeInicio(), filtroPenalidadAuxDTO.getDescFeFin(), paginador);

        return pPgimPenalidadAuxDTO;
    }

    @Override
    public Page<PgimPrspstoGastoSuperDTO> listarReportePresupuestoGastoSupervision(
            PgimPrspstoGastoSuperDTO filtroPgimPrspstoGastoSuperDTO, Pageable paginador) {
        Page<PgimPrspstoGastoSuperDTO> pPgimPrspstoGastoSuperDTO = this.prspstoGastoSuperRepository
                .listarPrspstoGastoSuper(filtroPgimPrspstoGastoSuperDTO.getDeEmpresaSupervisora(),
                        filtroPgimPrspstoGastoSuperDTO.getDescNuAnio(), paginador);

        return pPgimPrspstoGastoSuperDTO;
    }

    @Override
    public Long diasCalendarioSupervision(PgimSupervisionDTO pgimSupervisionDTO, boolean flagFechaReal) {

        TimeZone timeZona = TimeZone.getTimeZone("America/Lima");

        Calendar calendarioIni = Calendar.getInstance(timeZona);
        if (flagFechaReal) {
            calendarioIni.setTime(pgimSupervisionDTO.getFeInicioSupervisionReal());
        } else {
            calendarioIni.setTime(pgimSupervisionDTO.getFeInicioSupervision());
        }

        Calendar calendarioFin = Calendar.getInstance(timeZona);
        if (flagFechaReal) {
            calendarioFin.setTime(pgimSupervisionDTO.getFeFinSupervisionReal());
        } else {
            calendarioFin.setTime(pgimSupervisionDTO.getFeFinSupervision());
        }

        LocalDate fechaLocalInicial = LocalDate.of(calendarioIni.get(Calendar.YEAR),
                calendarioIni.get(Calendar.MONTH) + 1, calendarioIni.get(Calendar.DAY_OF_MONTH));

        LocalDate fechaLocalFinal = LocalDate.of(calendarioFin.get(Calendar.YEAR),
                calendarioFin.get(Calendar.MONTH) + 1, calendarioFin.get(Calendar.DAY_OF_MONTH));

        Long cantidadDiasEntre = ChronoUnit.DAYS.between(fechaLocalInicial, fechaLocalFinal);

        if (cantidadDiasEntre >= 0) {
            cantidadDiasEntre++;
        } else {
            throw new PgimException(TipoResultado.WARNING, String.format("La cantidad de días para la fiscalización no es coherente, por favor revise")); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        }

        return cantidadDiasEntre;
    }

    @Transactional(readOnly = false)
    @Override
    public void seleccionarTParametrosXTTipoInstrumento(Long idContrato,
            List<PgimTprmtroXTinstrmntoDTO> lPgimTprmtroXTinstrmntoDTO, AuditoriaDTO auditoriaDTO) {

        this.tipoParametroMedicionPorContratoService.seleccionarTParametrosXTTipoInstrumento(idContrato,
                lPgimTprmtroXTinstrmntoDTO, auditoriaDTO);
    }

}
