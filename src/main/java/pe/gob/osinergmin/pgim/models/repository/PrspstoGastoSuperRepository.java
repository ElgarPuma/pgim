package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimPrspstoGastoSuperDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPrspstoGastoSuper;

/**
 * @descripción: Logica de negocio de la entidad reporte ad-hoc de presupuesto y gasto de supervisión
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface PrspstoGastoSuperRepository extends JpaRepository<PgimPrspstoGastoSuper, Long> {

	  
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrspstoGastoSuperDTOResultado("
            + "pgasu.idContratoAux, pgasu.pgimContrato.idContrato, pgasu.nuContrato, pgasu.feInicioContrato, "
            + "pgasu.pgimEmpresaSupervisora.idEmpresaSupervisora, pgasu.pgimPersona.idPersona, "
            + "pgasu.nuRucEmpresaSupervisora || ' - ' || pgasu.deEmpresaSupervisora, pgasu.moImporteContrato, pgasu.moConsumoContrato, pgasu.moSaldo, pgasu.moActaSupervision, "
            + "pgasu.nuActaSupervision, pgasu.moInformeSupervision, pgasu.nuInformeSupervision, pgasu.moSupervisionFallida, "
            + "pgasu.nuSupervisionFallida, pgasu.moInformeGestion, pgasu.nuInformeGestion "
            + ") " 
            + "FROM PgimPrspstoGastoSuper pgasu "
            + "WHERE 1 = 1 "
            + "AND (:descNoRazonSocial IS NULL OR LOWER(pgasu.deEmpresaSupervisora) LIKE LOWER(CONCAT('%', :descNoRazonSocial, '%')) ) "
            + "AND (:nuAnio IS NULL OR TO_CHAR(pgasu.feInicioContrato,'YYYY') LIKE CONCAT('%', :nuAnio, '%') ) "
            + "ORDER BY pgasu.feInicioContrato DESC "
            )
    Page<PgimPrspstoGastoSuperDTO> listarPrspstoGastoSuper(@Param("descNoRazonSocial") String descNoRazonSocial, 
    		@Param("nuAnio") Long nuAnio, Pageable paginador);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrspstoGastoSuperDTOResultado("
            + "pgasu.idContratoAux, pgasu.pgimContrato.idContrato, pgasu.nuContrato, pgasu.feInicioContrato, "
            + "pgasu.pgimEmpresaSupervisora.idEmpresaSupervisora, pgasu.pgimPersona.idPersona, "
            + "pgasu.nuRucEmpresaSupervisora || ' - ' || pgasu.deEmpresaSupervisora, pgasu.moImporteContrato, pgasu.moConsumoContrato, pgasu.moSaldo, pgasu.moActaSupervision, "
            + "pgasu.nuActaSupervision, pgasu.moInformeSupervision, pgasu.nuInformeSupervision, pgasu.moSupervisionFallida, "
            + "pgasu.nuSupervisionFallida, pgasu.moInformeGestion, pgasu.nuInformeGestion "
            + ") " 
            + "FROM PgimPrspstoGastoSuper pgasu "
            + "WHERE 1 = 1 "
            + "AND (:descNoRazonSocial IS NULL OR LOWER(pgasu.deEmpresaSupervisora) LIKE LOWER(CONCAT('%', :descNoRazonSocial, '%')) ) "
            + "AND (:nuAnio IS NULL OR TO_CHAR(pgasu.feInicioContrato,'YYYY') LIKE CONCAT('%', :nuAnio, '%') ) "
            + "ORDER BY pgasu.feInicioContrato DESC "
            )
    List<PgimPrspstoGastoSuperDTO> listarReporteAdHocPreGasSupervision(
    		@Param("descNoRazonSocial") String descNoRazonSocial, @Param("nuAnio") Long nuAnio);
}
