package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimMedAdmRptAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMedAdmRptAux;

/**
 * @descripción: Logica de negocio de la entidad PAS
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
@Repository
public interface MedAdmRptAuxRepository extends JpaRepository<PgimMedAdmRptAux, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedAdmRptAuxDTOResultado("
			+ "maux.idMedidaAdministrativaAux, maux.pgimMedidaAdm.idMedidaAdministrativa, "
			+ "maux.idUnidadMinera, maux.idTipoMedidaAdministrativa, maux.deTipoMedidaAdministrativa, "
			+ "maux.deMedidaAdministrativa, maux.feMedidaAdministrativa, maux.nuExpedienteSiged, "
			+ "maux.pgimMedidaAdm.coMedidaAdministrativa "
			+ ") " 
			+ "FROM PgimMedAdmRptAux maux " 
			+ "WHERE maux.idUnidadMinera = :idUnidadMinera ")
	List<PgimMedAdmRptAuxDTO> listarPgimMedAdmRptAuxPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
}
