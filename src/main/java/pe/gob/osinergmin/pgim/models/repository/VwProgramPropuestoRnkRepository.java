package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimVwProgramPropuestoRnkDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimVwProgramPropuestoRnk;

/**
 * Ésta interface de PgimVwProgramPropuestoRnk.
 * 
 * @descripción: Lógica de negocio de la entidad PgimVwProgramPropuestoRnk
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 03/01/2024
 */
@Repository
public interface VwProgramPropuestoRnkRepository extends JpaRepository<PgimVwProgramPropuestoRnk, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimVwProgramPropuestoRnkDTOResultado("
      + "pp.idGenPrograma, pp.idProgramaSupervision, pp.flConforme, "
      + "pp.feGeneracion, pp.nuMaxFiscaAnualXuf, pp.nuMaxFiscaMensual, "
      + "pp.nuEnero, pp.nuFebrero, pp.nuMarzo, pp.nuAbril, pp.nuMayo, "
      + "pp.nuJuno, pp.nuJulio, pp.nuAgosto, pp.nuSeptiembre, "
      + "pp.nuOctubre, pp.nuNoviembre, pp.nuDiciembre, "
      + "pp.nuOrden, pp.idGenPrgRanking, pp.idRankingRiesgo, "
      + "pp.noRanking, pp.moPuntajeTotalRiesgo, pp.moPuntajeProporcion, "
      + "pp.moProporcionEntera, pp.caVisitas, pp.idRankingUm, "
      + "pp.idUnidadMinera, pp.noUnidadMinera, pp.enero, "
      + "pp.febrero, pp.marzo, pp.abril, pp.mayo, "
      + "pp.juno, pp.julio, pp.agosto, "
      + "pp.septiembre, pp.octubre, pp.noviembre, "
      + "pp.diciembre, pp.moPuntajeRiesgo, pp.deMensajePrgMes "
      + ") "
      + "FROM PgimVwProgramPropuestoRnk pp "
      + "WHERE pp.idProgramaSupervision = :idProgramaSupervision "
      + "AND pp.flConformePrgMes = '1' "
      + "AND pp.flConforme = '0' "
      + "ORDER BY pp.nuOrden ASC, pp.moPuntajeRiesgo DESC, pp.noUnidadMinera ASC "
      )
    Page<PgimVwProgramPropuestoRnkDTO> obtenerProgramaPropuesta(
      @Param("idProgramaSupervision") Long idProgramaSupervision, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimVwProgramPropuestoRnkDTOResultado("
      + "pp.idGenPrograma, pp.idProgramaSupervision, pp.flConforme, "
      + "pp.feGeneracion, pp.nuMaxFiscaAnualXuf, pp.nuMaxFiscaMensual, "
      + "pp.nuEnero, pp.nuFebrero, pp.nuMarzo, pp.nuAbril, pp.nuMayo, "
      + "pp.nuJuno, pp.nuJulio, pp.nuAgosto, pp.nuSeptiembre, "
      + "pp.nuOctubre, pp.nuNoviembre, pp.nuDiciembre, "
      + "pp.nuOrden, pp.idGenPrgRanking, pp.idRankingRiesgo, "
      + "pp.noRanking, pp.moPuntajeTotalRiesgo, pp.moPuntajeProporcion, "
      + "pp.moProporcionEntera, pp.caVisitas, pp.idRankingUm, "
      + "pp.idUnidadMinera, pp.noUnidadMinera, pp.enero, "
      + "pp.febrero, pp.marzo, pp.abril, pp.mayo, "
      + "pp.juno, pp.julio, pp.agosto, "
      + "pp.septiembre, pp.octubre, pp.noviembre, "
      + "pp.diciembre, pp.moPuntajeRiesgo, pp.deMensajePrgMes "
      + ") "
      + "FROM PgimVwProgramPropuestoRnk pp "
      + "WHERE pp.idProgramaSupervision = :idProgramaSupervision "
      + "AND pp.flConformePrgMes = '0' "
      + "AND pp.flConforme = '0' "
      + "ORDER BY pp.nuOrden ASC, pp.moPuntajeRiesgo DESC, pp.noUnidadMinera ASC "
      )
    List<PgimVwProgramPropuestoRnkDTO> obtenerProgramaPropuestaNoConforme(
      @Param("idProgramaSupervision") Long idProgramaSupervision);


    @Query("SELECT SUM(pp.caVisitas) " 
      + "FROM PgimVwProgramPropuestoRnk pp "
      + "WHERE pp.idProgramaSupervision = :idProgramaSupervision "
      + "AND pp.flConformePrgMes = '1' "
      + "AND pp.flConforme = '0' "
      )
    Long obtenerTotalVisitasProgramaPropuesta(
    @Param("idProgramaSupervision") Long idProgramaSupervision);

}
