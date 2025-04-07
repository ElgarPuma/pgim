package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContratoSiaf;

public interface ContratoSiafService {

    PgimContratoSiafDTO obtenerContratoSiafPorId(Long idContrato);

    /**
     * Permirte crear un contrato siaf
     *
     * @param pgimContratoSiafDTO
     * @param auditoriaDTO
     * @return
     */
    PgimContratoSiafDTO crearContratoSiaf(PgimContratoSiafDTO pgimContratoSiafDTO, AuditoriaDTO auditoriaDTO)
            throws Exception;

    /**
     * Permite listar el contrato SIAF
     */
    List<PgimContratoSiafAuxDTO> listarContratoSiaf(Long idContrato);

    /**
     * Permite modificar los datos de un contrato SIAF.
     * 
     * @param pgimContratoSiafDTO
     * @param auditoriaDTO
     * @return
     */
    PgimContratoSiafDTO modificarContratoSiaf(@Valid PgimContratoSiafDTO pgimContratoSiafDTO,
            AuditoriaDTO auditoriaDTO);

    /**
     * Permiote eliminar un contrato SIAF
     * 
     * @param pgimContratoSiafActual
     */
    void eliminarContratoSiaf(PgimContratoSiaf pgimContratoSiafActual, AuditoriaDTO auditoriaDTO);

    /**
     * Permite obtener un contrato SIAF de acuerdo con su identificador
     * interno.
     * 
     * @param idContratoSiaf
     * @return
     */
    PgimContratoSiaf getByIdContratoSiaf(Long idContratoSiaf);
}
