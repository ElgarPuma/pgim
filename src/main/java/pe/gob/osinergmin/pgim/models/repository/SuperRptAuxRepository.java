package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimSuperRptAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSuperRptAux;

/**
 * @descripción: Logica de negocio de la entidad supervisiones para reporte
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface SuperRptAuxRepository extends JpaRepository<PgimSuperRptAux, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSuperRptAuxDTOResultado("
			+ "saux.idSupervisionAux, saux.pgimSupervision.idSupervision, saux.idUnidadMinera, saux.coSupervision, saux.idTipoSupervision, "
			+ "saux.noTipoSupervision, saux.idSubtipoSupervision, saux.deSubtipoSupervision, saux.feInicioSupervision, "
			+ "saux.feFinSupervision, saux.feInicioSupervisionReal, saux.feFinSupervisionReal, saux.etiquetaPasoActual, "
			+ "saux.nuExpedienteSiged "
			+ ") " 
			+ "FROM PgimSuperRptAux saux " 
			+ "WHERE saux.idUnidadMinera = :idUnidadMinera ")
	List<PgimSuperRptAuxDTO> listarPgimSuperRptAuxPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
}
