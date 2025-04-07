package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSupervisionAgolDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervisionAgol;

/**
 * 
 * 
 * @descripción: Lógica de negocio de la entidad supervisión Agol
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 * 
 */
@Repository
public interface SupervisionAgolRepository extends JpaRepository<PgimSupervisionAgol, Long> {


	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAgolDTOResultado("
            //+ "supe.idSupervisionAgol, supe.idObject, supe.pgimSupervision.idSupervision) " 
            + "supe.idSupervisionAgol, supe.pgimSupervision.idSupervision) "
			+ "FROM PgimSupervisionAgol supe "
            + "WHERE supe.esRegistro = '1' " 
			+ "AND supe.pgimSupervision.idSupervision = :idSupervision "
			+ "ORDER BY supe.feCreacion DESC"
            )
	List<PgimSupervisionAgolDTO> listarRegistrosPorSupervision(@Param("idSupervision") Long idSupervision);
	
	
}
