package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContratoSiaf;

import java.util.List;

/**
 * @descripción: Logica de negocio de la entidad contrato siaf.
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/07/2020
 * @fecha_de_ultima_actualización: 17/07/2020
 */
@Repository
public interface ContratoSiafRepository extends JpaRepository<PgimContratoSiaf, Long> {
    
    /**
     * Permite obtener la lista de contratos SIAF
     * @param idContrato
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoSiafDTOResultado("
        + "pcs.idContratoSiaf, pcs.pgimContrato.idContrato, pcs.nuSiaf, pcs.nuAnio, pcs.moPresupuestoSiaf ) "
        + "FROM PgimContratoSiaf pcs "
        + "WHERE pcs.esRegistro = '1' "
        + "AND pcs.pgimContrato.idContrato = :idContrato "
    )
    PgimContratoSiafDTO obtenerContratoSiafPorId(@Param("idContrato") Long idContrato);

    /**
     * Permite listar los contratos SIAF
     * @param idContrato
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoSiafDTOResultado("
        + "pcs.idContratoSiaf, pcs.pgimContrato.idContrato, pcs.nuSiaf, pcs.nuAnio, pcs.moPresupuestoSiaf "
        + " ) "
        + "FROM PgimContratoSiaf pcs "
        + "WHERE pcs.esRegistro = '1' "
        + "AND pcs.pgimContrato.idContrato = :idContrato "
        + "ORDER BY pcs.moPresupuestoSiaf DESC "
    )
    List<PgimContratoSiafDTO> listarContratoSiaf(@Param("idContrato") Long idContrato);
}
