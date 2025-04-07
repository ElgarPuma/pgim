package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;

public interface EmpresaSupervisoraService {

    /**
     * Permite listar los agentes supervisados.
     */
    Page<PgimEmpresaSupervisoraDTO> filtrar(PgimEmpresaSupervisoraDTO filtroEmpresaSupervisora, Pageable paginador);

    /**
     * Permite listar los agentes supervisados por palabra clave.
     * 
     * @param palabra Palabra clave utilizada para buscar en la lista de agentes
     *                supervisados.
     * @return
     */
    List<PgimEmpresaSupervisoraDTO> listarPorPalabraClave(String palabra);

    /**
     * @param idEmpresaSupervisora
     * @return
     */
    PgimEmpresaSupervisoraDTO obtenerPorId(Long idEmpresaSupervisora);

    /**
     * Permirte crear una nueva empresa supervisora
     *
     * @param pgimEmpresaSupervisoraDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    PgimEmpresaSupervisoraDTO crearEmpresaSupervisora(PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO,
            AuditoriaDTO auditoriaDTO) throws Exception;

    /***
     * Permite obtener un objeto entidad de la empresa supervisora con el valor
     * idEmpresaSupervisora.
     * 
     * @param idEmpresaSupervisora
     * @return
     */
    PgimEmpresaSupervisora getByIdEmpresaSupervisora(Long idEmpresaSupervisora);

    /**
     * Permite modificar una empresa supervisora.
     * 
     * @param pgimEmpresaSupervisoraDTO
     * @param pgimEmpresaSupervisora
     * @param auditoriaDTO
     * @return
     */
    PgimEmpresaSupervisoraDTO modificarEmpresaSupervisora(PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO,
            PgimEmpresaSupervisora pgimEmpresaSupervisora, AuditoriaDTO auditoriaDTO);

    /**
     * Permite eliminar una empresa supervisora.
     * 
     * @param pgimEmpresaSupervisoraActual
     * @param auditoriaDTO
     */
    void eliminarEmpresaSupervisora(PgimEmpresaSupervisora pgimEmpresaSupervisoraActual, AuditoriaDTO auditoriaDTO);

    /**
     * Permnite listar por persona juridica y validar si ya existe o no
     * 
     * @param palabraClave
     * @return
     */
    List<PgimEmpresaSupervisoraDTO> listarPorPersonaJuridica(String palabraClave);
}
