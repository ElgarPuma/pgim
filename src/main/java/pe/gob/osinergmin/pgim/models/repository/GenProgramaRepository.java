package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimGenProgramaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimGenPrograma;

/** 
 * Ésta interface de GenProgramaRepository.
 * 
 * @descripción: Lógica de negocio de la entidad Generacion de programa - PgimGenPrograma
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 03/01/2024
*/
@Repository
public interface GenProgramaRepository extends JpaRepository<PgimGenPrograma, Long> {

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGenProgramaDTOResultado("
    + "genpr.idGenPrograma, genpr.pgimPrgrmSupervision.idProgramaSupervision, um.idUnidadMinera, "
    + " prm.nuMes, um.coUnidadMinera || ': ' || um.noUnidadMinera AS no_um "
    + ") "
    + "FROM PgimGenPrgMes prm "
    + "INNER JOIN prm.pgimGenPrgUfiscaliza pruf "
    + "INNER JOIN pruf.pgimRankingUm rkum "
    + "INNER JOIN rkum.pgimUnidadMinera um "
    + "INNER JOIN rkum.pgimRankingRiesgo rkri "
    + "INNER JOIN pruf.pgimGenPrgRanking prrk "
    + "INNER JOIN prrk.pgimGenPrograma genpr " 
    + "WHERE genpr.esRegistro = '1' "
    + "AND prm.flConforme = '1'"
    + "AND genpr.idGenPrograma = :idGenPrograma "
    + "ORDER BY prrk.nuOrden ASC, pruf.moPuntajeProporcion DESC, no_um ASC "
    // + "ORDER BY prrk.nuOrden ASC, pruf.moPuntajeProporcion DESC, prm.idGenPrgMes, um.noUnidadMinera ASC "
    )
  List<PgimGenProgramaDTO> obtenerProgramaPropuesta(@Param("idGenPrograma") Long idGenPrograma);


  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGenProgramaDTOResultado("
      + "genpr.idGenPrograma, genpr.pgimPrgrmSupervision.idProgramaSupervision, genpr.flConforme, "
      + "genpr.feGeneracion, genpr.nuMaxFiscaAnualXuf, "
      + "genpr.nuMaxFiscaMensual, genpr.nuEnero, genpr.nuFebrero, genpr.nuMarzo, "
      + "genpr.nuAbril, genpr.nuMayo, genpr.nuJuno, genpr.nuJulio, genpr.nuAgosto, "
      + "genpr.nuSeptiembre, genpr.nuOctubre, genpr.nuNoviembre, genpr.nuDiciembre "
      + ") "
      + "FROM PgimGenPrograma genpr "
      + "WHERE genpr.esRegistro = '1' "
      + "AND genpr.pgimPrgrmSupervision.idProgramaSupervision = :idProgramaSupervision "
      )
  List<PgimGenProgramaDTO> validadExistenciaProgramaPropuesta(@Param("idProgramaSupervision") Long idProgramaSupervision);


  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGenProgramaDTOResultado("
    + "genpr.idGenPrograma, genpr.pgimPrgrmSupervision.idProgramaSupervision, rkri.idRankingRiesgo, "
    + " 'RANK' || '-' || rkri.idRankingRiesgo || '. ' || rkri.noRanking "
    + ") "
    + "FROM PgimGenPrgRanking prrk "
    + "INNER JOIN prrk.pgimRankingRiesgo rkri "
    + "INNER JOIN prrk.pgimGenPrograma genpr " 
    + "WHERE genpr.esRegistro = '1' "
    + "AND genpr.flConforme = '1'"
    + "AND genpr.pgimPrgrmSupervision.idProgramaSupervision = :idProgramaSupervision "
    + "ORDER BY prrk.nuOrden ASC "
    )
  List<PgimGenProgramaDTO> obtenerRankingsInvolucrados(@Param("idProgramaSupervision") Long idProgramaSupervision);

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGenProgramaDTOResultado("
      + "genpr.idGenPrograma, genpr.pgimPrgrmSupervision.idProgramaSupervision, genpr.flConforme, "
      + "genpr.feGeneracion, genpr.nuMaxFiscaAnualXuf, "
      + "genpr.nuMaxFiscaMensual, genpr.nuEnero, genpr.nuFebrero, genpr.nuMarzo, "
      + "genpr.nuAbril, genpr.nuMayo, genpr.nuJuno, genpr.nuJulio, genpr.nuAgosto, "
      + "genpr.nuSeptiembre, genpr.nuOctubre, genpr.nuNoviembre, genpr.nuDiciembre "
      + ") "
      + "FROM PgimGenPrograma genpr "
      + "WHERE genpr.esRegistro = '1' "
      + "AND genpr.idGenPrograma = :idGenPrograma "
      )
  PgimGenProgramaDTO obtenerGenProgramaPorId(@Param("idGenPrograma") Long idGenPrograma);

}
