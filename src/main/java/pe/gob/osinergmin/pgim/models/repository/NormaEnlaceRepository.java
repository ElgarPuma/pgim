package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimNormaEnlaceDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaEnlace;

/**
 * @descripción: Lógica de negocio de la entidad Enlace de norma legal
 * 
 * @author: PresleyRomero
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface NormaEnlaceRepository extends JpaRepository<PgimNormaEnlace, Long> {
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaEnlaceDTOResultado(nenl.idNormaEnlace, nenl.pgimNorma.idNorma, nenl.deTitulo, nenl.deEnlace) "            
            + "FROM PgimNormaEnlace nenl "     
            + "WHERE nenl.pgimNorma.idNorma = :idNorma "
            + "AND nenl.esRegistro = '1' "
            + "ORDER BY nenl.idNormaEnlace desc "
            )
	List<PgimNormaEnlaceDTO> listarNormaEnlace(@Param("idNorma") Long idNorma);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaEnlaceDTOResultado(nenl.idNormaEnlace, nenl.pgimNorma.idNorma, nenl.deTitulo, nenl.deEnlace) "
            + "FROM PgimNormaEnlace nenl "            
            + "WHERE nenl.idNormaEnlace = :idNormaEnlace "
            + "AND nenl.esRegistro = '1' "            
            )
	PgimNormaEnlaceDTO obtenerEnlacePorId(@Param("idNormaEnlace") Long idNormaEnlace);

}
