package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimIncPeligrosoAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIncPeligrosoAux;

/**
 * @descripción: Logica de negocio de la entidad incidentes peligrosos
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
@Repository
public interface IncPeligrosoAuxRepository extends JpaRepository<PgimIncPeligrosoAux, Long> {
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIncPeligrosoAuxDTOResultado("
			+ "ipaux.idEventoAux, eve.idEvento, ipaux.idTipoEvento, ipaux.noTipoEvento, ipaux.coEvento, "
			+ "ipaux.deEvento, ipaux.fePresentacion, ipaux.noEspecialidad "
			+ ") " 
			+ "FROM PgimIncPeligrosoAux ipaux "
			+ "INNER JOIN PgimEvento eve ON eve = ipaux.pgimEvento " 
			+ "WHERE ipaux.idUnidadMinera = :idUnidadMinera ")
	List<PgimIncPeligrosoAuxDTO> listarPgimIncPeligrosoAuxPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
}
