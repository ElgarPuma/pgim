package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContactoSuperDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContactoSuper;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;

public interface ContactoSuperService {

    /**
     * Permite obtener la lista de contactos de la empresa supervisora
     * 
     * @param idEmpresaSupervisora
     * @param paginador
     * @return
     */
    Page<PgimContactoSuperDTO> listarContactosEmpresaSuperv(Long idEmpresaSupervisora, Pageable paginador);

    /**
     * Permite obtener el registro DTO del contacto de la empresa supervisora por id
     * 
     * @param idContactoSupervisora
     * @return
     */
    PgimContactoSuperDTO obtenerContactoSuperPorId(Long idContactoSupervisora);

    public PgimContactoSuperDTO crearContactoSuper(PgimContactoSuperDTO pgimContactoSuperDTO,
            AuditoriaDTO auditoriaDTO) throws Exception;

    public PgimContactoSuperDTO modificarContactoSuper(PgimContactoSuperDTO pgimContactoSuperDTO,
            PgimPersona pgimPersona, PgimEmpresaSupervisora pgimEmpresaSupervisora, AuditoriaDTO auditoriaDTO);

    public void eliminarContactoSuper(PgimContactoSuper pgimContactoSuper, AuditoriaDTO auditoriaDTO);

    public PgimContactoSuper getByIdContactoSuper(Long idContactoSupervisora);
}
