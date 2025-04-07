package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimGenPrgMesDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimGenPrgMes;

/** 
 * Ésta interface de GenPrgMesRepository.
 * 
 * @descripción: Lógica de negocio de la entidad PgimGenPrgMes
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 04/01/2024
*/
@Repository
public interface GenPrgMesRepository extends JpaRepository<PgimGenPrgMes, Long> {

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGenPrgMesDTOResultado("
      + "prm.idGenPrgMes, pruf.idGenPrgUfiscaliza, prm.flConforme, prm.nuMes, prm.deMensaje "
      + ") "
      + "FROM PgimGenPrgMes prm "
      + "INNER JOIN prm.pgimGenPrgUfiscaliza pruf "
      + "WHERE prm.esRegistro = '1' "
      + "AND prm.flConforme = '1' "
      + "AND pruf.idGenPrgUfiscaliza = :idGenPrgUfiscaliza "
      + "ORDER BY prm.idGenPrgMes DESC"
      )
  List<PgimGenPrgMesDTO> obtenerGenPrgMes(@Param("idGenPrgUfiscaliza") Long idGenPrgUfiscaliza);

}
