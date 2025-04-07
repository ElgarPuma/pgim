package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDenuncia;

public interface DenunciaService {

    /**
     * Permite listar las denuncias de la unidad minera.
     * 
     * @param idUnidadMinera
     * @param paginador
     * @return
     */
    Page<PgimDenunciaDTO> listarDenuncia(Long idUnidadMinera, Pageable paginador) throws Exception;

    /**
     * Permite listar las denuncias en general de la unidad minera.
     * @param filtroDenunciaDTO
     * @param paginador
     * @return
     */
    Page<PgimDenunciaDTO> listarDenunciaGeneral(PgimDenunciaDTO filtroDenunciaDTO, Pageable paginador) throws Exception;

    /**
     * Permite obtener la lista de denuncias de la unidad minera por el id.
     * 
     * @param idDenuncia
     * @return
     */
    PgimDenunciaDTO obtenerDenunciaPorId(Long idDenuncia);

    /**
     * Permirte crear una nueva denuncia
     *
     * @param pgimDenunciaDTO
     * @param auditoriaDTO
     * @return
     */
    PgimDenunciaDTO crearDenuncia(PgimDenunciaDTO pgimDenunciaDTO, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Permite modificar la denuncia.
     * 
     * @param pgimDenunciaDTO
     * @param auditoriaDTO
     * @return
     */
    PgimDenunciaDTO modificarDenuncia(@Valid PgimDenunciaDTO pgimDenunciaDTO, PgimDenuncia pgimDenuncia,
            AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Permiote eliminar una denuncia.
     * 
     * @param pgimDenunciaActual
     */
    void eliminarDenuncia(PgimDenuncia pgimDenunciaActual, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Permite obtener la denuncia de acuerdo con su identificador interno.
     * 
     * @param idDenuncia
     * @return
     */
    PgimDenuncia getByIdDenuncia(Long idDenuncia);

    /**
     * Permnite verificar si el denunciante de la UM existe o no.
     * 
     * @param idUnidadMinera
     * @param palabraClave
     * @return
     */
    List<PgimDenunciaDTO> listarPorPersona(Long idUnidadMinera, String palabraClave);
    List<PgimDenunciaDTO> listarPorPersonaGeneral(String palabraClave);

    PgimDenunciaDTO obtenerMaxCodigoDenuncia();

    PgimDenunciaDTO obtenerMaxCodigoDenunciaPorId(Long idDenuncia);
    
}
