package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCopiaDetalleUmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMtdoExplotacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubactividadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSustanciaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSustanciaUmineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimItemProgramaSupe;
import pe.gob.osinergmin.pgim.models.entity.PgimLineaPrograma;
import pe.gob.osinergmin.pgim.models.entity.PgimMtdoExplotacion;
import pe.gob.osinergmin.pgim.models.entity.PgimSubactividad;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimSustancia;
import pe.gob.osinergmin.pgim.models.entity.PgimSustanciaUminera;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.ConfiguraReglaRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemProgramaSupeRepository;
import pe.gob.osinergmin.pgim.models.repository.LineaProgramaRepository;
import pe.gob.osinergmin.pgim.models.repository.MetodoExplotacionRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.SubactividadRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.SustanciaRepository;
import pe.gob.osinergmin.pgim.models.repository.SustanciaUnidadMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.UnidadMineraService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Unidad minera
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class UnidadMineraServiceImpl implements UnidadMineraService {

    @Autowired
    private UnidadMineraRepository unidadMineraRepository;

    @Autowired
    private UnidadMineraAuxRepository unidadMineraAuxRepository;

    @Autowired
    private MetodoExplotacionRepository metodoExplotacionRepository;

    @Autowired
    private SustanciaRepository sustanciaRepository;

    @Autowired
    private SustanciaUnidadMineraRepository sustanciaUnidadMineraRepository;

    @Autowired
    private ItemProgramaSupeRepository itemProgramaSupeRepository;

    @Autowired
    private LineaProgramaRepository lineaProgramaRepository;
    
    @Autowired
    private SubactividadRepository subActividadRepository;
        
    @Autowired
	private ValorParametroRepository valorParametroRepository;

    @Autowired
    private RankingRiesgoRepository rankingRiesgoRepository;

    @Autowired
    private ConfiguraReglaRepository configuraReglaRepository;

    @Autowired
	private SupervisionRepository supervisionRepository;
    
    @Override
    public Page<PgimUnidadMineraDTO> filtrar(PgimUnidadMineraDTO filtroUnidadMinera, Pageable paginador) {

        Page<PgimUnidadMineraDTO> pPgimUnidadMineraDTO = this.unidadMineraRepository.listar(
                filtroUnidadMinera.getCoUnidadMinera(), filtroUnidadMinera.getNoUnidadMinera(),
                filtroUnidadMinera.getDescCoDocumentoIdentidad(), filtroUnidadMinera.getDescNoRazonSocial(),
                filtroUnidadMinera.getIdSituacion(), filtroUnidadMinera.getIdTipoUnidadMinera(),
                filtroUnidadMinera.getIdDivisionSupervisora(), filtroUnidadMinera.getIdMetodoMinado(), 
                filtroUnidadMinera.getIdTipoActividad(), filtroUnidadMinera.getIdEstadoUm(),
                filtroUnidadMinera.getTextoBusqueda(), paginador);

        return pPgimUnidadMineraDTO;
    }

    @Override
    public Page<PgimUnidadMineraAuxDTO> filtrarSeleccionablesParaPrograma(PgimUnidadMineraAuxDTO filtroUnidadMinera,
            Pageable paginador) {

        if (filtroUnidadMinera.getDescIdLineaPrograma() == null) {
            throw new PgimException("error", "No se ha enviado la línea base del programa");
        }

        PgimLineaPrograma pgimLineaPrograma = this.lineaProgramaRepository
                .findById(filtroUnidadMinera.getDescIdLineaPrograma()).orElse(null);
        Long idDivisionSupervisora = pgimLineaPrograma.getPgimPrgrmSupervision().getDivisionSupervisora()
                .getIdValorParametro();

        if (filtroUnidadMinera.getNoUnidadMinera() != null) {
            if (filtroUnidadMinera.getNoUnidadMinera().equals("")) {
                filtroUnidadMinera.setNoUnidadMinera(null);
            }
        }        

        if (filtroUnidadMinera.getNoRazonSocial() != null) {
            if (filtroUnidadMinera.getNoRazonSocial().equals("")) {
                filtroUnidadMinera.setNoRazonSocial(null);
            }
        }        

        if (filtroUnidadMinera.getNoUbigeo() != null) {
            if (filtroUnidadMinera.getNoUbigeo().equals("")) {
                filtroUnidadMinera.setNoUbigeo(null);
            }
        }        

        Page<PgimUnidadMineraAuxDTO> pPgimUnidadMineraDTO = this.unidadMineraAuxRepository.listar(
                filtroUnidadMinera.getNoUnidadMinera(), filtroUnidadMinera.getNoRazonSocial(),
                filtroUnidadMinera.getIdSituacion(), filtroUnidadMinera.getNoUbigeo(), filtroUnidadMinera.getDescMoPuntaje(), idDivisionSupervisora,
                paginador);

        return pPgimUnidadMineraDTO;
    }

    @Override
    public List<PgimUnidadMineraDTO> listarPorPalabraClave(String palabra) {
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = this.unidadMineraRepository.listarPorPalabraClave(palabra);

        return lPgimUnidadMineraDTO;
    }
    
    @Override
    public List<PgimUnidadMineraDTO> listarPorPalabraClaveYAs(String palabra, Long idAgenteSupervisado) {
    	idAgenteSupervisado = (idAgenteSupervisado == 0L) ? null : idAgenteSupervisado;
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = this.unidadMineraRepository.listarPorPalabraClaveYAs(palabra,
                idAgenteSupervisado);

        return lPgimUnidadMineraDTO;
    }

    @Override
    public List<PgimUnidadMineraDTO> listarPorPalabraClaveYDs(String palabra, Long idDivisionSupervisora) {
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = this.unidadMineraRepository.listarPorPalabraClaveYDs(palabra,
                idDivisionSupervisora);

        return lPgimUnidadMineraDTO;
    }

    @Override
    public List<PgimUnidadMineraDTO> listarPlntasPorPalabraClave(String palabra) {
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = this.unidadMineraRepository
        		.listarPlntasPorPalabraClave(palabra, EValorParametro.UNMIN_CONCSION_BNFCIO.toString());

        return lPgimUnidadMineraDTO;
    }

    @Override
    public List<PgimMtdoExplotacionDTO> listarMetodosExplotacion() {
        return this.metodoExplotacionRepository.listar();
    }

    @Override
    public List<PgimSustanciaDTO> listarSustancias() {
        return this.sustanciaRepository.listar();
    }

    @Override
    public List<PgimSustanciaUmineraDTO> listarSustanciasUM(Long idUnidadMinera) {
        return this.sustanciaUnidadMineraRepository.listar(idUnidadMinera);
    }

    @Override
    public PgimUnidadMineraDTO obtenerUnidadMinera(Long idUnidadMinera) {
        return this.unidadMineraRepository.obtenerUnidadMineraPorId(idUnidadMinera);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimUnidadMineraDTO crearUnidadMinera(PgimUnidadMineraDTO pgimUnidadMineraDTO, AuditoriaDTO auditoriaDTO) {
        PgimUnidadMinera pgimUnidadMinera = new PgimUnidadMinera();

        pgimUnidadMinera = this.configurarValoresUM(pgimUnidadMineraDTO, pgimUnidadMinera);

        pgimUnidadMinera.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimUnidadMinera.setFeCreacion(auditoriaDTO.getFecha());
        pgimUnidadMinera.setUsCreacion(auditoriaDTO.getUsername());
        pgimUnidadMinera.setIpCreacion(auditoriaDTO.getTerminal());

        PgimUnidadMinera pgimUnidadMineraCreada = this.unidadMineraRepository.save(pgimUnidadMinera);
        pgimUnidadMineraCreada.setFeInicioTitularidad(pgimUnidadMineraDTO.getFeInicioTitularidad());

        List<PgimSustanciaUminera> lPgimSustanciaUminera2Create = new ArrayList<>();
        List<PgimSustanciaUminera> lPgimSustanciaUminera2Delete = new ArrayList<>();

        this.identificarCambiosSustanciasUM(pgimUnidadMineraDTO, pgimUnidadMinera, lPgimSustanciaUminera2Create,
                lPgimSustanciaUminera2Delete, auditoriaDTO);

        for (PgimSustanciaUminera pgimSustanciaUminera2Create : lPgimSustanciaUminera2Create) {
            this.sustanciaUnidadMineraRepository.save(pgimSustanciaUminera2Create);
        }

        for (PgimSustanciaUminera pgimSustanciaUminera2Delete : lPgimSustanciaUminera2Delete) {
            this.sustanciaUnidadMineraRepository.save(pgimSustanciaUminera2Delete);
        }

        PgimUnidadMineraDTO pgimUnidadMineraDTOCreada = this
                .obtenerUnidadMinera(pgimUnidadMineraCreada.getIdUnidadMinera());

        return pgimUnidadMineraDTOCreada;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimItemProgramaSupeDTO crearUnidadMineraPrograma(PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO,
            AuditoriaDTO auditoriaDTO) {
        PgimItemProgramaSupe pgimItemProgramaSupe = new PgimItemProgramaSupe();

        pgimItemProgramaSupe.setPgimSubtipoSupervision(new PgimSubtipoSupervision());
        pgimItemProgramaSupe.getPgimSubtipoSupervision()
                .setIdSubtipoSupervision(pgimItemProgramaSupeDTO.getIdSubtipoSupervision());

        pgimItemProgramaSupe.setPgimUnidadMinera(new PgimUnidadMinera());
        pgimItemProgramaSupe.getPgimUnidadMinera().setIdUnidadMinera(pgimItemProgramaSupeDTO.getIdUnidadMinera());

        pgimItemProgramaSupe.setPgimLineaPrograma(new PgimLineaPrograma());
        pgimItemProgramaSupe.getPgimLineaPrograma().setIdLineaPrograma(pgimItemProgramaSupeDTO.getIdLineaPrograma());

        Calendar feMesEstimado = Calendar.getInstance();
        feMesEstimado.setTime(pgimItemProgramaSupeDTO.getFeMesEstimado());
        feMesEstimado.set(Calendar.DAY_OF_MONTH, 1);

        pgimItemProgramaSupe.setFeMesEstimado(feMesEstimado.getTime());
        pgimItemProgramaSupe.setMoCostoEstimadoSupervision(pgimItemProgramaSupeDTO.getMoCostoEstimadoSupervision());

        pgimItemProgramaSupe.setEsRegistro("1");
        pgimItemProgramaSupe.setFeCreacion(auditoriaDTO.getFecha());
        pgimItemProgramaSupe.setUsCreacion(auditoriaDTO.getUsername());
        pgimItemProgramaSupe.setIpCreacion(auditoriaDTO.getTerminal());
        PgimItemProgramaSupe pgimItemProgramaSupeCreada = this.itemProgramaSupeRepository.save(pgimItemProgramaSupe);

        PgimItemProgramaSupeDTO pgimItemProgramaSupeDTOCreada = new PgimItemProgramaSupeDTO();

        pgimItemProgramaSupeDTOCreada.setIdItemProgramaSupe(pgimItemProgramaSupeCreada.getIdItemProgramaSupe());
        return pgimItemProgramaSupeDTOCreada;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimUnidadMineraDTO modificarUnidadMinera(PgimUnidadMineraDTO pgimUnidadMineraDTO,
            PgimUnidadMinera pgimUnidadMinera, AuditoriaDTO auditoriaDTO) {
        pgimUnidadMinera = this.configurarValoresUM(pgimUnidadMineraDTO, pgimUnidadMinera);
        pgimUnidadMinera.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        /*
         * pgimUnidadMinera.setFeActualizacion(new Date());
         * pgimUnidadMinera.setUsActualizacion("admin");
         * pgimUnidadMinera.setIpActualizacion("127.0.0.1");
         */
        pgimUnidadMinera.setFeActualizacion(auditoriaDTO.getFecha());
        pgimUnidadMinera.setUsActualizacion(auditoriaDTO.getUsername());
        pgimUnidadMinera.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimUnidadMinera pgimUnidadMineraModificada = this.unidadMineraRepository.save(pgimUnidadMinera);
        pgimUnidadMineraModificada.setFeInicioTitularidad(pgimUnidadMineraDTO.getFeInicioTitularidad());

        List<PgimSustanciaUminera> lPgimSustanciaUminera2Create = new ArrayList<>();
        List<PgimSustanciaUminera> lPgimSustanciaUminera2Delete = new ArrayList<>();

        this.identificarCambiosSustanciasUM(pgimUnidadMineraDTO, pgimUnidadMinera, lPgimSustanciaUminera2Create,
                lPgimSustanciaUminera2Delete, auditoriaDTO);

        for (PgimSustanciaUminera pgimSustanciaUminera2Create : lPgimSustanciaUminera2Create) {
            this.sustanciaUnidadMineraRepository.save(pgimSustanciaUminera2Create);
        }

        for (PgimSustanciaUminera pgimSustanciaUminera2Delete : lPgimSustanciaUminera2Delete) {
            this.sustanciaUnidadMineraRepository.save(pgimSustanciaUminera2Delete);
        }

        PgimUnidadMineraDTO pgimUnidadMineraDTOResultado = this
                .obtenerUnidadMinera(pgimUnidadMineraModificada.getIdUnidadMinera());

        return pgimUnidadMineraDTOResultado;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarUnidadMinera(PgimUnidadMinera pgimUnidadMinerActual, AuditoriaDTO auditoriaDTO) {

        pgimUnidadMinerActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimUnidadMinerActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimUnidadMinerActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimUnidadMinerActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.unidadMineraRepository.save(pgimUnidadMinerActual);
    }

    private PgimUnidadMinera configurarValoresUM(PgimUnidadMineraDTO pgimUnidadMineraDTO,
            PgimUnidadMinera pgimUnidadMinera) {

        pgimUnidadMinera.setCoUnidadMinera(pgimUnidadMineraDTO.getCoUnidadMinera());
        pgimUnidadMinera.setNoUnidadMinera(pgimUnidadMineraDTO.getNoUnidadMinera());

        pgimUnidadMinera.setDivisionSupervisora(new PgimValorParametro());
        pgimUnidadMinera.getDivisionSupervisora().setIdValorParametro(pgimUnidadMineraDTO.getIdDivisionSupervisora());

        pgimUnidadMinera.setSituacion(new PgimValorParametro());
        pgimUnidadMinera.getSituacion().setIdValorParametro(pgimUnidadMineraDTO.getIdSituacion());

        pgimUnidadMinera.setPgimAgenteSupervisado(new PgimAgenteSupervisado());
        pgimUnidadMinera.getPgimAgenteSupervisado()
                .setIdAgenteSupervisado(pgimUnidadMineraDTO.getIdAgenteSupervisado());

        pgimUnidadMinera.setFeInicioTitularidad(pgimUnidadMineraDTO.getFeInicioTitularidad());

        pgimUnidadMinera.setTipoActividad(new PgimValorParametro());
        pgimUnidadMinera.getTipoActividad().setIdValorParametro(pgimUnidadMineraDTO.getIdTipoActividad());

        pgimUnidadMinera.setTipoUnidadMinera(new PgimValorParametro());
        pgimUnidadMinera.getTipoUnidadMinera().setIdValorParametro(pgimUnidadMineraDTO.getIdTipoUnidadMinera());

        if (pgimUnidadMineraDTO.getIdTipoUnidadMinera().equals(this.valorParametroRepository
				.obtenerIdValorParametro(EValorParametro.UNMIN_CONCSION_BNFCIO.toString()))) {
            pgimUnidadMinera.setNuCpcdadInstldaPlanta(pgimUnidadMineraDTO.getNuCpcdadInstldaPlanta());
        } else {
            pgimUnidadMinera.setNuCpcdadInstldaPlanta(null);
        }

        pgimUnidadMinera.setMetodoMinado(new PgimValorParametro());
        pgimUnidadMinera.getMetodoMinado().setIdValorParametro(pgimUnidadMineraDTO.getIdMetodoMinado());

        if (pgimUnidadMineraDTO.getIdMetodoExplotacion() != null) {
            pgimUnidadMinera.setPgimMtdoExplotacion(new PgimMtdoExplotacion());
            pgimUnidadMinera.getPgimMtdoExplotacion()
                    .setIdMetodoExplotacion(pgimUnidadMineraDTO.getIdMetodoExplotacion());
        } else {
            pgimUnidadMinera.setPgimMtdoExplotacion(null);
        }

        pgimUnidadMinera.setFlCmraSubtrraneaGas(pgimUnidadMineraDTO.getFlCmraSubtrraneaGas());

        pgimUnidadMinera.setNuProfundidad(pgimUnidadMineraDTO.getNuProfundidad());
        pgimUnidadMinera.setNuAlturaMinima(pgimUnidadMineraDTO.getNuAlturaMinima());
        pgimUnidadMinera.setNuAlturaMaxima(pgimUnidadMineraDTO.getNuAlturaMaxima());

        if (pgimUnidadMineraDTO.getIdTipoYacimiento() != null) {
            pgimUnidadMinera.setTipoYacimiento(new PgimValorParametro());
            pgimUnidadMinera.getTipoYacimiento().setIdValorParametro(pgimUnidadMineraDTO.getIdTipoYacimiento());
        } else {
            pgimUnidadMinera.setTipoYacimiento(null);
        }

        pgimUnidadMinera.setTipoSustancia(new PgimValorParametro());
        pgimUnidadMinera.getTipoSustancia().setIdValorParametro(pgimUnidadMineraDTO.getIdTipoSustancia());

        if (pgimUnidadMineraDTO.getIdPlntaBeneficioDestino() != null) {
            pgimUnidadMinera.setPlntaBeneficioDestino(new PgimUnidadMinera());
            pgimUnidadMinera.getPlntaBeneficioDestino()
                    .setIdUnidadMinera(pgimUnidadMineraDTO.getIdPlntaBeneficioDestino());
        } else {
            pgimUnidadMinera.setPlntaBeneficioDestino(null);
        }

        pgimUnidadMinera.setFlRegistraRiesgos(pgimUnidadMineraDTO.getFlRegistraRiesgos());

        if (pgimUnidadMineraDTO.getIdSubactividad() != null) {
            pgimUnidadMinera.setPgimSubactividad(new PgimSubactividad());
            pgimUnidadMinera.getPgimSubactividad().setIdSubactividad(pgimUnidadMineraDTO.getIdSubactividad());
        } else {
            pgimUnidadMinera.setPgimSubactividad(null);
        }

        if (pgimUnidadMineraDTO.getIdEstadoUm() != null) {
            pgimUnidadMinera.setEstadoUm(new PgimValorParametro());
            pgimUnidadMinera.getEstadoUm().setIdValorParametro(pgimUnidadMineraDTO.getIdEstadoUm());
        } else {
            pgimUnidadMinera.setEstadoUm(null);
        }

        pgimUnidadMinera.setFlConPiques(pgimUnidadMineraDTO.getFlConPiques());

        return pgimUnidadMinera;
    }

    /***
     * Permite identificar los cambios ocurridos para la unidad minera en lo que a
     * sustancias se refiere.
     * 
     * @param pgimUnidadMineraDTO
     * @param pgimUnidadMinera
     * @param lPgimSustanciaUminera2Create
     * @param lPgimSustanciaUminera2Delete
     */
    private void identificarCambiosSustanciasUM(PgimUnidadMineraDTO pgimUnidadMineraDTO,
            PgimUnidadMinera pgimUnidadMinera, List<PgimSustanciaUminera> lPgimSustanciaUminera2Create,
            List<PgimSustanciaUminera> lPgimSustanciaUminera2Delete, AuditoriaDTO auditoriaDTO) {

        List<PgimSustanciaUmineraDTO> lPgimSustanciaUmineraDTO = this
                .listarSustanciasUM(pgimUnidadMineraDTO.getIdUnidadMinera());

        // Procesamos las creaciones
        PgimSustanciaUminera pgimSustanciaUminera2Create = null;
        boolean sustanciaUmExiste = false;

        try {
            for (PgimSustanciaDTO pgimSustanciaDTO : pgimUnidadMineraDTO.getAuxSustanciasUm()) {

                for (PgimSustanciaUmineraDTO pgimSustanciaUmineraDTO : lPgimSustanciaUmineraDTO) {
                    if (pgimSustanciaDTO.getIdSustancia().equals(pgimSustanciaUmineraDTO.getIdSustancia())) {
                        sustanciaUmExiste = true;

                    }
                }

                if (!sustanciaUmExiste) {
                    pgimSustanciaUminera2Create = new PgimSustanciaUminera();
                    pgimSustanciaUminera2Create.setPgimUnidadMinera(pgimUnidadMinera);
                    pgimSustanciaUminera2Create.setPgimSustancia(new PgimSustancia());
                    pgimSustanciaUminera2Create.getPgimSustancia().setIdSustancia(pgimSustanciaDTO.getIdSustancia());

                    pgimSustanciaUminera2Create.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                    /*
                     * pgimSustanciaUminera2Create.setFeCreacion(new Date());
                     * pgimSustanciaUminera2Create.setUsCreacion("admin");
                     * pgimSustanciaUminera2Create.setIpCreacion("127.0.0.1");
                     */
                    pgimSustanciaUminera2Create.setFeCreacion(auditoriaDTO.getFecha());
                    pgimSustanciaUminera2Create.setUsCreacion(auditoriaDTO.getUsername());
                    pgimSustanciaUminera2Create.setIpCreacion(auditoriaDTO.getTerminal());

                    lPgimSustanciaUminera2Create.add(pgimSustanciaUminera2Create);
                }
                sustanciaUmExiste = false;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        // - Procesamos las eliminaciones
        PgimSustanciaUminera pgimSustanciaUminera2Delete = null;
        boolean sustanciaExiste = false;

        for (PgimSustanciaUmineraDTO pgimSustanciaUmineraDTO : lPgimSustanciaUmineraDTO) {

            for (PgimSustanciaDTO pgimSustanciaDTO : pgimUnidadMineraDTO.getAuxSustanciasUm()) {
                if (pgimSustanciaUmineraDTO.getIdSustancia().equals(pgimSustanciaDTO.getIdSustancia())) {
                    sustanciaExiste = true;
                    break;
                }
            }

            if (!sustanciaExiste) {
                pgimSustanciaUminera2Delete = this.sustanciaUnidadMineraRepository
                        .findById(pgimSustanciaUmineraDTO.getIdSustanciaUminera()).orElse(null);

                pgimSustanciaUminera2Delete.setEsRegistro(ConstantesUtil.IND_INACTIVO);
                pgimSustanciaUminera2Delete.setFeActualizacion(auditoriaDTO.getFecha());
                pgimSustanciaUminera2Delete.setUsActualizacion(auditoriaDTO.getUsername());
                pgimSustanciaUminera2Delete.setIpActualizacion(auditoriaDTO.getTerminal());

                lPgimSustanciaUminera2Delete.add(pgimSustanciaUminera2Delete);
            }

            sustanciaExiste = false;
        }
    }

    @Override
    public PgimUnidadMinera getByIdUnidadMinera(Long idUnidadMinera) {

        return this.unidadMineraRepository.findById(idUnidadMinera).orElse(null);
    }

    @Override
    public List<PgimUnidadMineraDTO> existeUnidadMinera(Long idUnidadMinera, String coUnidadMinera) {
        List<PgimUnidadMineraDTO> pPgimUnidadMineraDTO = this.unidadMineraRepository.existeUnidadMinera(idUnidadMinera,
                coUnidadMinera);

        return pPgimUnidadMineraDTO;
    }

    @Override
    public Page<PgimUnidadMineraDTO> filtrarUnidadMineraAS(Long idAgenteSupervisado,
            PgimUnidadMineraDTO filtroUnidadMinera, Pageable paginador) {
        Page<PgimUnidadMineraDTO> pPgimUnidadMineraDTO = this.unidadMineraRepository.listarUnidadesMinerasAS(
                idAgenteSupervisado, filtroUnidadMinera.getCoUnidadMinera(), filtroUnidadMinera.getNoUnidadMinera(),
                filtroUnidadMinera.getDescCoDocumentoIdentidad(), filtroUnidadMinera.getDescNoRazonSocial(),
                filtroUnidadMinera.getIdSituacion(), filtroUnidadMinera.getIdTipoUnidadMinera(),
                filtroUnidadMinera.getIdDivisionSupervisora(), filtroUnidadMinera.getTextoBusqueda(), paginador);

        return pPgimUnidadMineraDTO;
    }

    @Override
    public Page<PgimUnidadMineraAuxDTO> listarReporteUMPaginado(PgimUnidadMineraAuxDTO filtroUnidadMinera,
            Pageable paginador) {

        Page<PgimUnidadMineraAuxDTO> pPgimUnidadMineraDTO = this.unidadMineraAuxRepository
                .listarReporteUMPaginado(paginador);

        return pPgimUnidadMineraDTO;
    }

    @Transactional(readOnly = false)
    @Override
    public void actualizarAliasUM(AuditoriaDTO auditoriaDTO) {

        List<PgimUnidadMinera> lPgimUnidadMinera = this.unidadMineraRepository.findByEsRegistro("1");

        int cantidadUM = lPgimUnidadMinera.size();

        if (cantidadUM == 0) {
            return;
        }

        List<Integer> lNumerosAleatorios = this.obtenerArregloAnonimizado(cantidadUM - 1);

        Integer contador = 0;
        Integer numeroAleatorio;
        String codigoAnonimizacion;

        for (PgimUnidadMinera pgimUnidadMinera : lPgimUnidadMinera) {

            numeroAleatorio = lNumerosAleatorios.get(contador);
            codigoAnonimizacion = String.format("UMA-%04d", numeroAleatorio);

            pgimUnidadMinera.setCoAnonimizacion(codigoAnonimizacion);

            pgimUnidadMinera.setFeActualizacion(auditoriaDTO.getFecha());
            pgimUnidadMinera.setUsActualizacion(auditoriaDTO.getUsername());
            pgimUnidadMinera.setIpActualizacion(auditoriaDTO.getTerminal());

            this.unidadMineraRepository.save(pgimUnidadMinera);

            contador++;
        }
    }

    private List<Integer> obtenerArregloAnonimizado(Integer tamanio) {
        Integer[] arreglo = new Integer[1000];

        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = i;
        }

        List<Integer> lNumerosAleatorios = Arrays.asList(arreglo);

        Collections.shuffle(lNumerosAleatorios);

        return lNumerosAleatorios;
    }

    @Override
    public Page<PgimCopiaDetalleUmDTO> listarHistoricoUMPaginado(PgimCopiaDetalleUmDTO filtroUnidadMinera,
            Pageable paginador) {

        Page<PgimCopiaDetalleUmDTO> pPgimCopiaDetalleUmDTO = this.unidadMineraAuxRepository
                .listarHistoricoUMPaginado(filtroUnidadMinera.getIdUnidadMinera(), paginador); 

        return pPgimCopiaDetalleUmDTO;
    }
    
    @Override
    public PgimUnidadMineraDTO obtenerUmPorCodigo(String coUm) {
        return this.unidadMineraRepository.obtenerUmPorCodigo(coUm);
    }

    @Override
	public List<PgimSubactividadDTO> listarSubActividad() {
		return this.subActividadRepository.listarSubActividad();
	}
    
    @Override
    public Page<PgimUnidadMineraAuxDTO> listarUmAuxDisponiblePorRankingRPaginado(PgimUnidadMineraAuxDTO filtroUnidadMinera,
            Pageable paginador) {
    	
        Page<PgimUnidadMineraAuxDTO> pPgimUnidadMineraAuxDTO = this.unidadMineraAuxRepository.listarUmAuxDisponiblePorRankingRPaginado(
                		filtroUnidadMinera.getCoUnidadMinera(), filtroUnidadMinera.getNoUnidadMinera(),
                        filtroUnidadMinera.getCoDocumentoIdentidad(), filtroUnidadMinera.getNoRazonSocial(),
                        filtroUnidadMinera.getIdSituacion(), filtroUnidadMinera.getIdTipoUnidadMinera(),
                        filtroUnidadMinera.getIdDivisionSupervisora(), filtroUnidadMinera.getIdMetodoMinado(), 
                        filtroUnidadMinera.getIdTipoActividad(), filtroUnidadMinera.getIdEstadoUm(), filtroUnidadMinera.getDescIdRankingRiesgo(), 
                        filtroUnidadMinera.getTextoBusqueda(), paginador);

        Long idConfigRiesgo = this.rankingRiesgoRepository.findById(filtroUnidadMinera.getDescIdRankingRiesgo()).orElse(null).getPgimConfiguraRiesgo().getIdConfiguraRiesgo();
        List<PgimConfiguraReglaDTO> lPgimConfiguraReglaDTO = this.configuraReglaRepository.obtenerReglasPorConfiguracion(idConfigRiesgo);

        if(filtroUnidadMinera.getDescCoTipoConfiguracionRiesgo().equals(EValorParametro.TIPO_CFG_RIESGO_FISCALIZACION.toString())){
            List<PgimUnidadMineraAuxDTO> lPgimUnidadMineraDTONew = new ArrayList<PgimUnidadMineraAuxDTO>();            
            for (PgimUnidadMineraAuxDTO pgimUnidadMineraDTO : pPgimUnidadMineraAuxDTO.getContent()){
                Integer cumpleRegla = 0;
                
                for (PgimConfiguraReglaDTO pgimConfiguraReglaDTO : lPgimConfiguraReglaDTO){        
                    if(pgimConfiguraReglaDTO.getIdSituacion().equals(pgimUnidadMineraDTO.getIdSituacion()) &&
                    pgimConfiguraReglaDTO.getIdMetodoMinado().equals(pgimUnidadMineraDTO.getIdMetodoMinado()) && 
                    pgimConfiguraReglaDTO.getIdTipoActividad().equals(pgimUnidadMineraDTO.getIdTipoActividad()) && 
                    pgimConfiguraReglaDTO.getIdEstado().equals(pgimUnidadMineraDTO.getIdEstadoUm()) && 
                    pgimConfiguraReglaDTO.getIdDivisionSupervisora().equals(pgimUnidadMineraDTO.getIdDivisionSupervisora()) ){                    
                        cumpleRegla = 1;
                    }
                    if(cumpleRegla == 1){
                        break;
                    }
                }       
                
                if(cumpleRegla == 1){
                    pgimUnidadMineraDTO.setDescFlCumpleRegla(ConstantesUtil.FL_IND_SI);
                }else{
                    pgimUnidadMineraDTO.setDescFlCumpleRegla(ConstantesUtil.FL_IND_NO);
                }

                lPgimUnidadMineraDTONew.add(pgimUnidadMineraDTO);        
            }               
            
            Page<PgimUnidadMineraAuxDTO> newlPgimUnidadMineraDTO = new PageImpl<>(lPgimUnidadMineraDTONew, pPgimUnidadMineraAuxDTO.getPageable(), pPgimUnidadMineraAuxDTO.getTotalElements());
            pPgimUnidadMineraAuxDTO = newlPgimUnidadMineraDTO;
        }

        return pPgimUnidadMineraAuxDTO;
    }

    @Override
    public List<PgimUnidadMineraDTO> listarUmAsociadas(Long idPlntaBeneficioDestino) {
        List<PgimUnidadMineraDTO> listar = this.unidadMineraRepository.listarUmAsociadas(idPlntaBeneficioDestino);
        return listar;
    }

}
