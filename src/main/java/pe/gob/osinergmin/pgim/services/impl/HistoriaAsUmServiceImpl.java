package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHistoriaAsUmDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimHistoriaAsUm;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.repository.HistoriaAsUmRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.services.HistoriaAsUmService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Historial de Agente Supervisado de la Unidad Minera
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 30/06/2020 
 */
@Service
@Transactional(readOnly = true)
public class HistoriaAsUmServiceImpl implements HistoriaAsUmService {

    @Autowired
    private HistoriaAsUmRepository historiaAsUmRepository;

    @Autowired
    private UnidadMineraRepository unidadMineraRepository;

    @Override
    public List<PgimHistoriaAsUmDTO> listarHistoriasAS(Long idUnidadMinera) {
        List<PgimHistoriaAsUmDTO> lista = new ArrayList<>();
        lista = historiaAsUmRepository.listarHistoriasAS(idUnidadMinera);
        return lista;
    }

    /**
     * Permite configuar los valores del objetivo entidad con los valores del objeto
     * de transferencia de datos.
     * 
     * @param pgimHistoriaAsUmDTO
     * @param pgimHistoriaAsUm
     * @return
     */
    private PgimHistoriaAsUm configurarValoresASUM(PgimHistoriaAsUmDTO pgimHistoriaAsUmDTO,
            PgimHistoriaAsUm pgimHistoriaAsUm, PgimUnidadMinera pgimUnidadMinera) {

        pgimHistoriaAsUm.setPgimUnidadMinera(new PgimUnidadMinera());
        pgimHistoriaAsUm.getPgimUnidadMinera().setIdUnidadMinera(pgimHistoriaAsUmDTO.getIdUnidadMinera());

        pgimHistoriaAsUm.setAgenteSupervisadoPrevio(new PgimAgenteSupervisado());
        pgimHistoriaAsUm.getAgenteSupervisadoPrevio()
                .setIdAgenteSupervisado(pgimUnidadMinera.getPgimAgenteSupervisado().getIdAgenteSupervisado());
        pgimHistoriaAsUm.setFeInicioTitularidad(pgimUnidadMinera.getFeInicioTitularidad());

        return pgimHistoriaAsUm;
    }

    /** Permite cambiar de titularidad de un agente fiscalizado de la unidad minera*/
    @Transactional(readOnly = false)
    @Override
    public PgimHistoriaAsUmDTO crearHistoriaAsUm(PgimHistoriaAsUmDTO pgimHistoriaAsUmDTO, AuditoriaDTO auditoriaDTO) {
        PgimUnidadMinera pgimUnidadMinera = this.unidadMineraRepository
                .findById(pgimHistoriaAsUmDTO.getIdUnidadMinera()).orElse(null);

        // Guarando el historial del cambio de titularidad
        PgimHistoriaAsUm pgimHistoriaAsUm = new PgimHistoriaAsUm();

        pgimHistoriaAsUm = this.configurarValoresASUM(pgimHistoriaAsUmDTO, pgimHistoriaAsUm, pgimUnidadMinera);
        pgimHistoriaAsUm.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        /*pgimHistoriaAsUm.setFeCreacion(new Date());
        pgimHistoriaAsUm.setUsCreacion("admin");
        pgimHistoriaAsUm.setIpCreacion("127.0.0.1");*/
        pgimHistoriaAsUm.setFeCreacion(auditoriaDTO.getFecha());
        pgimHistoriaAsUm.setUsCreacion(auditoriaDTO.getUsername());
        pgimHistoriaAsUm.setIpCreacion(auditoriaDTO.getTerminal());

        PgimHistoriaAsUm pgimHistoriaAsUmCreada = this.historiaAsUmRepository.save(pgimHistoriaAsUm);

        // Actualizando el nuevo titular e inicio de la titularidad para la Unidad
        // Minera.
        pgimUnidadMinera.setPgimAgenteSupervisado(new PgimAgenteSupervisado());
        pgimUnidadMinera.getPgimAgenteSupervisado()
                .setIdAgenteSupervisado(pgimHistoriaAsUmDTO.getIdAgenteSupervisadoNuevo());
        pgimUnidadMinera.setFeInicioTitularidad(pgimHistoriaAsUmDTO.getFeInicioTitularidad());

        this.unidadMineraRepository.save(pgimUnidadMinera);

        PgimHistoriaAsUmDTO pgimHistoriaAsUmDTOCreada = this
                .obtenerHistoriaAsUm(pgimHistoriaAsUmCreada.getIdHistoriaAsUm());

        return pgimHistoriaAsUmDTOCreada;
    }

    /***Permite obtener la historia de titularidad de agente fiscalizado de la unidad minera */
    @Override
    public PgimHistoriaAsUmDTO obtenerHistoriaAsUm(Long idHistoriaAsUm) {
        return this.historiaAsUmRepository.obtenerHistoriaAsUmPorId(idHistoriaAsUm);
    }

    /*** Esta metodo permite validar la fecha de inicio del titular que sea posterior al 
	 * anterior titular de agente fiscalizado de una unidad minera */
    @Override
    public PgimHistoriaAsUmDTO validarFeInicioTitularidad(Long idHistoriaAsUm) {
        PgimHistoriaAsUmDTO HistoriaAsUmDTO = this.historiaAsUmRepository.validarFeInicioTitularidad(idHistoriaAsUm);
        return HistoriaAsUmDTO;
    }

}
