package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimAccMortalAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAccMortalAux;

/** 
 * @descripción: Lógica de negocio de la entidad accidentes mortales
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/06/2020
*/
@Repository
public interface AccMortalAuxRepository extends JpaRepository<PgimAccMortalAux, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAccMortalAuxDTOResultado("
			+ "amaux.idEventoAux, eve.idEvento, amaux.idTipoEvento, amaux.noTipoEvento, amaux.coEvento, "
			+ "amaux.deEvento, amaux.fePresentacion, amaux.noEspecialidad, amaux.caAccidentados"
			+ ") " 
			+ "FROM PgimAccMortalAux amaux "
			+ "INNER JOIN PgimEvento eve ON eve = amaux.pgimEvento "
			+ "WHERE amaux.idUnidadMinera = :idUnidadMinera ")
	List<PgimAccMortalAuxDTO> listarAccMortalAuxPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
}
