package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSectorDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSector;

/** 
 * 
 * @descripción: Lógica de negocio de la entidad PgimSector
 * 
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 19/12/2023
 * @fecha_de_ultima_actualización: 19/12/2023
*/
@Repository
public interface SectorRepository extends JpaRepository<PgimSector, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSectorDTOResultado(sct.idSector, sct.noSector) "
            + "FROM PgimSector sct " 
            + "WHERE sct.esRegistro = '1' "
            )
    List<PgimSectorDTO> listaSectores();
    
}