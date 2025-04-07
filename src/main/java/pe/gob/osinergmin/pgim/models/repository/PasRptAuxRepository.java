package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimPasRptAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPasRptAux;

/**
 * Tiene como nombre Proceso administrativo sancionador.
 * 
 * @descripción: Logica de negocio de la entidad PAS para reporte
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface PasRptAuxRepository extends JpaRepository<PgimPasRptAux, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasRptAuxDTOResultado("
			+ "paux.idPasAux, paux.pgimPas.idPas, paux.idUnidadMinera, paux.idSupervision, paux.coSupervision, paux.idTipoSupervision, "
			+ "paux.noTipoSupervision, paux.idSubtipoSupervision, paux.deSubtipoSupervision, paux.feInicioSupervision, "
			+ "paux.feFinSupervision, paux.feInicioSupervisionReal, paux.feFinSupervisionReal, paux.etiquetaPasoActual, "
			+ "paux.nuExpedienteSiged, paux.feCreacionPas "
			+ ") " 
			+ "FROM PgimPasRptAux paux " 
			+ "WHERE paux.idUnidadMinera = :idUnidadMinera ")
	List<PgimPasRptAuxDTO> listarPgimPasRptAuxPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
}
