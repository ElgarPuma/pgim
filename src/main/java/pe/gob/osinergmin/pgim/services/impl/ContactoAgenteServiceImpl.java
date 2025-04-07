package pe.gob.osinergmin.pgim.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContactoAgenteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimContactoAgente;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository;
import pe.gob.osinergmin.pgim.models.repository.ContactoAgenteRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonaRepository;
import pe.gob.osinergmin.pgim.services.ContactoAgenteService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Contacto agente
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020
 */

@Service
@Transactional(readOnly = true)
public class ContactoAgenteServiceImpl implements ContactoAgenteService {

    @Autowired
    private ContactoAgenteRepository contactoAgenteRepository;

    @Autowired
    private AgenteSupervisadoRepository agenteSupervisadoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Page<PgimContactoAgenteDTO> filtrar(Long idAgenteSupervisado, Pageable paginador) {

        Page<PgimContactoAgenteDTO> lPgimContactoAgenteDTO = this.contactoAgenteRepository.listar(
                idAgenteSupervisado, paginador);

        return lPgimContactoAgenteDTO;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimContactoAgenteDTO crearContactoAgente(PgimContactoAgenteDTO pgimContactoAgenteDTO,
            AuditoriaDTO auditoriaDTO) {
        PgimContactoAgente pgimContactoAgente = new PgimContactoAgente();

        pgimContactoAgente.setNoCargo(pgimContactoAgenteDTO.getNoCargo());

        PgimAgenteSupervisado pgimAgenteSupervisado = agenteSupervisadoRepository
                .findById(pgimContactoAgenteDTO.getIdAgenteSupervisado()).orElse(null);
        pgimContactoAgente.setPgimAgenteSupervisado(pgimAgenteSupervisado);

        PgimPersona pgimPersona = personaRepository.findById(pgimContactoAgenteDTO.getIdPersona()).orElse(null);
        pgimContactoAgente.setPgimPersona(pgimPersona);

        pgimContactoAgente.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimContactoAgente.setFeCreacion(auditoriaDTO.getFecha());
        pgimContactoAgente.setUsCreacion(auditoriaDTO.getUsername());
        pgimContactoAgente.setIpCreacion(auditoriaDTO.getTerminal());
        PgimContactoAgente pgimContactoAgenteCreado = contactoAgenteRepository.save(pgimContactoAgente);

        PgimContactoAgenteDTO pgimContactoAgenteDTOCreado = this
                .obtenerContactoAgentePorId(pgimContactoAgenteCreado.getIdContactoAgente());

        return pgimContactoAgenteDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimContactoAgenteDTO modificarContactoAgente(PgimContactoAgenteDTO pgimContactoAgenteDTO,
            PgimPersona pgimPersona, PgimAgenteSupervisado pgimAgenteSupervisado, AuditoriaDTO auditoriaDTO) {

        PgimContactoAgente pgimContactoAgente = contactoAgenteRepository
                .findById(pgimContactoAgenteDTO.getIdContactoAgente()).orElse(null);

        pgimContactoAgente.setIdContactoAgente(pgimContactoAgenteDTO.getIdContactoAgente());
        pgimContactoAgente.setNoCargo(pgimContactoAgenteDTO.getNoCargo());
        pgimContactoAgente.setPgimAgenteSupervisado(pgimAgenteSupervisado);
        pgimContactoAgente.setPgimPersona(pgimPersona);

        pgimAgenteSupervisado.setFeActualizacion(auditoriaDTO.getFecha());
        pgimAgenteSupervisado.setUsActualizacion(auditoriaDTO.getUsername());
        pgimAgenteSupervisado.setIpActualizacion(auditoriaDTO.getTerminal());
        PgimContactoAgente pgimContactoAgenteModificado = contactoAgenteRepository.save(pgimContactoAgente);

        PgimContactoAgenteDTO pgimContactoAgenteDTOModificado = this
                .obtenerContactoAgentePorId(pgimContactoAgenteModificado.getIdContactoAgente());

        return pgimContactoAgenteDTOModificado;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarContactoAgente(PgimContactoAgente pgimContactoAgente, AuditoriaDTO auditoriaDTO) {

        pgimContactoAgente.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimContactoAgente.setFeActualizacion(auditoriaDTO.getFecha());
        pgimContactoAgente.setUsActualizacion(auditoriaDTO.getUsername());
        pgimContactoAgente.setIpActualizacion(auditoriaDTO.getTerminal());

        this.contactoAgenteRepository.save(pgimContactoAgente);
    }

    @Override
    public PgimContactoAgenteDTO obtenerContactoAgentePorId(Long idContactoAgente) {
        return this.contactoAgenteRepository.obtenerContactoAgentePorId(idContactoAgente);
    }

    @Override
    public PgimContactoAgente getByIdContactoAgente(Long idContactoAgente) {
        return this.contactoAgenteRepository.findById(idContactoAgente).orElse(null);
    }
}
