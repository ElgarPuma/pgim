package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContratoSiafAux;

/**
 * Tiene como nombre contrato Siaf.
 * 
 * @descripción: Logica de negocio de la entidad contrato Siaf auxiliar.
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 10/02/2021
 */
@Repository
public interface ContratoSiafAuxRepository extends JpaRepository<PgimContratoSiafAux, Long> {
    
    /**
     * Permite listar los contratos SIAF
     * @param idContrato
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTOResultado("
        + "pcs_aux.idContratoSiafAux, pcs_aux.pgimContratoSiaf.idContratoSiaf, pcs_aux.idContrato, " 
        + "pcs_aux.nuSiaf, pcs_aux.nuAnio, pcs_aux.moPresupuestoSiaf, pcs_aux.moConsumoContrato, (pcs_aux.moPresupuestoSiaf - pcs_aux.moConsumoContrato) "
        + " ) "
        + "FROM PgimContratoSiafAux pcs_aux "
        + "WHERE pcs_aux.idContrato = :idContrato "
        + "ORDER BY pcs_aux.moPresupuestoSiaf DESC "
    )
    List<PgimContratoSiafAuxDTO> listarContratoSiaf(@Param("idContrato") Long idContrato);
    
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTOResultado("
            + "csaux.idContratoSiafAux, csaux.pgimContratoSiaf.idContratoSiaf, csaux.idContrato, " 
            + "csaux.nuSiaf, csaux.nuAnio, csaux.moPresupuestoSiaf, csaux.moConsumoContrato, "
            + "(csaux.moPresupuestoSiaf - csaux.moConsumoContrato), con.nuContrato, emsu.pgimPersona.coDocumentoIdentidad || ' - ' || emsu.pgimPersona.noRazonSocial ) "
            + "FROM PgimContratoSiafAux csaux "
            + "INNER JOIN PgimContrato con ON csaux.idContrato = con.idContrato "
            + "LEFT JOIN PgimEmpresaSupervisora emsu ON con.pgimEmpresaSupervisora.idEmpresaSupervisora = emsu.idEmpresaSupervisora "
            + "WHERE 1 = 1 "
            + "AND (:nuAnio IS NULL OR LOWER(csaux.nuAnio) LIKE LOWER(CONCAT('%', :nuAnio, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(con.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "ORDER BY csaux.nuAnio DESC "
            )
    Page<PgimContratoSiafAuxDTO> listarPgimContratoSiafAux(
    		@Param("nuAnio") Long nuAnio, @Param("nuContrato") String nuContrato, Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTOResultado("
            + "csaux.idContratoSiafAux, csaux.pgimContratoSiaf.idContratoSiaf, csaux.idContrato, " 
            + "csaux.nuSiaf, csaux.nuAnio, csaux.moPresupuestoSiaf, csaux.moConsumoContrato, "
            + "(csaux.moPresupuestoSiaf - csaux.moConsumoContrato), con.nuContrato, emsu.pgimPersona.coDocumentoIdentidad || ' - ' || emsu.pgimPersona.noRazonSocial ) "
            + "FROM PgimContratoSiafAux csaux "
            + "INNER JOIN PgimContrato con ON csaux.idContrato = con.idContrato "
            + "LEFT JOIN PgimEmpresaSupervisora emsu ON con.pgimEmpresaSupervisora.idEmpresaSupervisora = emsu.idEmpresaSupervisora "
            + "WHERE 1 = 1 "
            + "AND (:nuAnio IS NULL OR LOWER(csaux.nuAnio) LIKE LOWER(CONCAT('%', :nuAnio, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(con.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "ORDER BY csaux.pgimContratoSiaf.pgimContrato.nuContrato, csaux.nuAnio DESC "
            )
    List<PgimContratoSiafAuxDTO> listarReporteEjecucionPresupuestal(
    		@Param("nuAnio") Long nuAnio, @Param("nuContrato") String nuContrato);
}
