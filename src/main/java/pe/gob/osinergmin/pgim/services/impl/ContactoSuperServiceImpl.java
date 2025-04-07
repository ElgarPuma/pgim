package pe.gob.osinergmin.pgim.services.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContactoSuperDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContactoSuper;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.repository.ContactoSuperRepository;
import pe.gob.osinergmin.pgim.services.ContactoSuperService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Contacto de empresa supervisora
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020
 */
@Service
@Transactional(readOnly = true)
public class ContactoSuperServiceImpl implements ContactoSuperService {

    @Autowired
    private ContactoSuperRepository contactoSuperRepository;

    @Override
    public Page<PgimContactoSuperDTO> listarContactosEmpresaSuperv(Long idEmpresaSupervisora, Pageable paginador) {
        Page<PgimContactoSuperDTO> lPgimContactoSuperDTO = this.contactoSuperRepository.listarContactosEmpresaSuperv(
                idEmpresaSupervisora, paginador);

        return lPgimContactoSuperDTO;
    }

    @Override
    public PgimContactoSuperDTO obtenerContactoSuperPorId(Long idContactoSupervisora) {
        return this.contactoSuperRepository.obtenerContactoSuperPorId(idContactoSupervisora);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimContactoSuperDTO crearContactoSuper(@Valid PgimContactoSuperDTO pgimContactoSuperDTO,
            AuditoriaDTO auditoriaDTO) throws Exception {

        PgimContactoSuper pgimContactoSuper = new PgimContactoSuper();

        pgimContactoSuper.setNoCargo(pgimContactoSuperDTO.getNoCargo());

        // PgimEmpresaSupervisora pgimEmpresaSupervisora = empresaSupervisoraRepository
        //         .findById(pgimContactoSuperDTO.getIdEmpresaSupervisora()).orElse(null);
        // pgimContactoSuper.setPgimEmpresaSupervisora(pgimEmpresaSupervisora);

        pgimContactoSuper.setPgimEmpresaSupervisora(new PgimEmpresaSupervisora());
        pgimContactoSuper.getPgimEmpresaSupervisora().setIdEmpresaSupervisora(pgimContactoSuperDTO.getIdEmpresaSupervisora());
        
        // PgimPersona pgimPersona = personaRepository.findById(pgimContactoSuperDTO.getIdPersona()).orElse(null);
        // pgimContactoSuper.setPgimPersona(pgimPersona);

        pgimContactoSuper.setPgimPersona(new PgimPersona());
        pgimContactoSuper.getPgimPersona().setIdPersona(pgimContactoSuperDTO.getIdPersona());
        
        pgimContactoSuper.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimContactoSuper.setFeCreacion(auditoriaDTO.getFecha());
        pgimContactoSuper.setUsCreacion(auditoriaDTO.getUsername());
        pgimContactoSuper.setIpCreacion(auditoriaDTO.getTerminal());
        
        PgimContactoSuper pgimContactoSuperCreado = this.contactoSuperRepository.save(pgimContactoSuper);

        PgimContactoSuperDTO pgimContactoSuperDTOCreado = this
                .obtenerContactoSuperPorId(pgimContactoSuperCreado.getIdContactoSupervisora());

        return pgimContactoSuperDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimContactoSuperDTO modificarContactoSuper(PgimContactoSuperDTO pgimContactoSuperDTO,
            PgimPersona pgimPersona, PgimEmpresaSupervisora pgimEmpresaSupervisora, AuditoriaDTO auditoriaDTO) {
        PgimContactoSuper pgimContactoSuper = contactoSuperRepository
                .findById(pgimContactoSuperDTO.getIdContactoSupervisora()).orElse(null);

        pgimContactoSuper.setIdContactoSupervisora(pgimContactoSuperDTO.getIdContactoSupervisora());
        pgimContactoSuper.setNoCargo(pgimContactoSuperDTO.getNoCargo());
        pgimContactoSuper.setPgimEmpresaSupervisora(pgimEmpresaSupervisora);
        pgimContactoSuper.setPgimPersona(pgimPersona);

        pgimEmpresaSupervisora.setFeActualizacion(auditoriaDTO.getFecha());
        pgimEmpresaSupervisora.setUsActualizacion(auditoriaDTO.getUsername());
        pgimEmpresaSupervisora.setIpActualizacion(auditoriaDTO.getTerminal());
        PgimContactoSuper pgimContactoSuperModificado = contactoSuperRepository.save(pgimContactoSuper);

        PgimContactoSuperDTO pgimContactoSuperDTOModificado = this
                .obtenerContactoSuperPorId(pgimContactoSuperModificado.getIdContactoSupervisora());

        return pgimContactoSuperDTOModificado;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarContactoSuper(PgimContactoSuper pgimContactoSuper, AuditoriaDTO auditoriaDTO) {
        pgimContactoSuper.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimContactoSuper.setFeActualizacion(auditoriaDTO.getFecha());
        pgimContactoSuper.setUsActualizacion(auditoriaDTO.getUsername());
        pgimContactoSuper.setIpActualizacion(auditoriaDTO.getTerminal());

        this.contactoSuperRepository.save(pgimContactoSuper);

    }

    @Override
    public PgimContactoSuper getByIdContactoSuper(Long idContactoSupervisora) {
        return this.contactoSuperRepository.findById(idContactoSupervisora).orElse(null);
    }

}
