package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRankingUmDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUm;

/**
 * Tiene como nombre RankingUmRepository.
 * 
 * @descripción: Logica de negocio de la entidad Ranking UM
 * 
 * @author: lbarrantes
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface RankingUmRepository extends JpaRepository<PgimRankingUm, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmDTOResultado( "
    + "rkum.idRankingUm, rkum.pgimRankingRiesgo.idRankingRiesgo, "
    + "rkum.pgimUnidadMinera.idUnidadMinera)" 
    + "FROM PgimRankingUm rkum " 
    + "WHERE rkum.esRegistro = '1' "
    + "AND rkum.idRankingUm = :idRankingUm ")
    PgimRankingUmDTO obtenerRankingUmPorId(@Param("idRankingUm") Long idRankingUm);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmDTOResultado( "
    + "rkum.idRankingUm, rkum.pgimRankingRiesgo.idRankingRiesgo, "
    + "rkum.pgimUnidadMinera.idUnidadMinera, NVL(rkum.moPuntaje, 0) AS puntaje ) " 
    + "FROM PgimRankingUm rkum " 
    + "WHERE rkum.esRegistro = '1' "
    + "AND rkum.flActivo = '1' " 
    + "AND rkum.pgimRankingRiesgo.idRankingRiesgo = :idRankingRiesgo "
    + "ORDER BY puntaje DESC, rkum.pgimUnidadMinera.coUnidadMinera, rkum.pgimUnidadMinera.noUnidadMinera ASC "
    )
    List<PgimRankingUmDTO> listarRankingUmByIdRanking(@Param("idRankingRiesgo") Long idRankingRiesgo);

	
}
