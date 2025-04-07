package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionCmDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAutorizacionCm;

/**
 * 
 *
 * @descripción: consultas de la entidad AutorizaciónCM
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 03/09/2020
 * @fecha_de_ultima_actualización: 03/09/2020
 */
@Repository
public interface AutorizacionCmRepository extends JpaRepository<PgimAutorizacionCm, Long> {
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAutorizacionCmDTOResultado( "
			+ "a.idAutorizacionCminero,a.pgimAutorizacion.idAutorizacion,a.pgimComponenteMinero.idComponenteMinero, "			
			+ "a.esRegistro,a.usCreacion,a.ipCreacion, "
			+ "a.feCreacion,a.usActualizacion,a.ipActualizacion,a.feActualizacion "			
			+ ")"
			+ "FROM PgimAutorizacionCm a "			
			+ "WHERE a.esRegistro = '1' "
			+ "AND a.pgimAutorizacion.idAutorizacion = :idAutorizacion ")
	List<PgimAutorizacionCmDTO> getListAutorizacionCmByIdAutorizacion(@Param("idAutorizacion") Long idAutorizacion);


}
