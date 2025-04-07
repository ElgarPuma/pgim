package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSubsectorDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSubsector;

/**  
 * @descripción: Lógica de negocio de la entidad PgimSubector 
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 19/12/2023
 * @fecha_de_ultima_actualización: 19/12/2023
*/
@Repository
public interface SubsectorRepository extends JpaRepository<PgimSubsector, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubsectorDTOResultado(ssct.idSubsector, ssct.noSubsector, sct.idSector) "
            + "FROM PgimSubsector ssct " 
            + "INNER JOIN ssct.pgimSector sct "
            + "WHERE 1=1 "
            + "AND ssct.esRegistro = '1' "
            + "AND sct.esRegistro = '1' "
            )
    List<PgimSubsectorDTO> listaSubsectores();
    
}