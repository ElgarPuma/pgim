package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimContratoSegumntoAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContratoSegumntoAux;

/** 
 * @descripción: Lógica de negocio de la entidad seguimiento de contratos
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/06/2020
*/
@Repository
public interface ContratoSegumntoAuxRepository extends JpaRepository<PgimContratoSegumntoAux, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoSegumntoAuxDTOResultado("
			+ "caux.idContratoSegumntoAux, caux.pgimContrato.idContrato, caux.nuContrato, caux.pgimEmpresaSupervisora.idEmpresaSupervisora, "
			+ "caux.pgimPersona.idPersona, caux.coDocumentoIdentidad || ' - ' || caux.noRazonSocial, caux.pgimEspecialidad.idEspecialidad, caux.noEspecialidad, caux.moImporteContrato, "
			+ "caux.saldoContrato, caux.preComprometido, caux.comprometido, caux.porLiquidar, caux.liquidado, caux.facturado, "
			+ "caux.totalConsumoContrato "
            + ") " 
            + "FROM PgimContratoSegumntoAux caux " 
            + "WHERE 1 = 1 "
            + "AND (:nuContrato IS NULL OR LOWER(caux.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noRazonSocial IS NULL OR LOWER(caux.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
            + "AND (:idEspecialidad IS NULL OR caux.pgimEspecialidad.idEspecialidad = :idEspecialidad) ")
	Page<PgimContratoSegumntoAuxDTO> listarContratoSeguimientoAux(
	            @Param("nuContrato") String nuContrato, @Param("noRazonSocial") String noRazonSocial,
	            @Param("idEspecialidad") Long idEspecialidad, Pageable paginador);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoSegumntoAuxDTOResultado("
			+ "caux.idContratoSegumntoAux, caux.pgimContrato.idContrato, caux.nuContrato, caux.pgimEmpresaSupervisora.idEmpresaSupervisora, "
			+ "caux.pgimPersona.idPersona, caux.coDocumentoIdentidad || ' - ' || caux.noRazonSocial, caux.pgimEspecialidad.idEspecialidad, caux.noEspecialidad, caux.moImporteContrato, "
			+ "caux.saldoContrato, caux.preComprometido, caux.comprometido, caux.porLiquidar, caux.liquidado, caux.facturado, "
			+ "caux.totalConsumoContrato "
            + ") " 
            + "FROM PgimContratoSegumntoAux caux " 
            + "WHERE 1 = 1 "
            + "AND (:nuContrato IS NULL OR LOWER(caux.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noRazonSocial IS NULL OR LOWER(caux.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
            + "AND (:idEspecialidad IS NULL OR caux.pgimEspecialidad.idEspecialidad = :idEspecialidad) "
            + "ORDER BY caux.nuContrato DESC ")
	List<PgimContratoSegumntoAuxDTO> listarReporteConSalContratoSupervisora(
	            @Param("nuContrato") String nuContrato, @Param("noRazonSocial") String noRazonSocial,
	            @Param("idEspecialidad") Long idEspecialidad);
}
