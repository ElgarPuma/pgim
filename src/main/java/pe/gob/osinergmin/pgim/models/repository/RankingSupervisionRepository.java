package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingSupervision;

/**
 * Tiene como nombre Ranking de supervisión.
 * 
 * @descripción: Logica de negocio de la entidad Ranking de supervisión
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface RankingSupervisionRepository extends JpaRepository<PgimRankingSupervision, Long> {
			

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTOResultado( "
			+ "rasp.idRankingSupervision, rasp.pgimSupervision.idSupervision, "
			+ "rasp.pgimConfiguraRiesgo.idConfiguraRiesgo,rasp.moPuntaje, "
			+ "rasp.pgimConfiguraRiesgo.pgimEspecialidad.idEspecialidad, "
			+ "rasp.pgimConfiguraRiesgo.pgimEspecialidad.noEspecialidad, "
			+ "rasp.pgimSupervision.pgimUnidadMinera.idUnidadMinera, "
			+ "rasp.pgimSupervision.pgimUnidadMinera.noUnidadMinera "
			+ ") "
			+ "FROM PgimRankingSupervision rasp "
			+ "WHERE 1 = 1 "
			+ "AND rasp.idRankingSupervision = :idRankingSupervision "
			+ "AND rasp.esRegistro = '1' "
			)
	PgimRankingSupervisionDTO obtenerRankingSupervisionPorId(@Param("idRankingSupervision") Long idRankingSupervision);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTOResultado( "
			+ "rasp.idRankingSupervision, rasp.pgimSupervision.idSupervision, "
			+ "rasp.pgimConfiguraRiesgo.idConfiguraRiesgo,rasp.moPuntaje, "
			+ "rasp.pgimConfiguraRiesgo.pgimEspecialidad.idEspecialidad, "
			+ "rasp.pgimConfiguraRiesgo.pgimEspecialidad.noEspecialidad, "
			+ "rasp.pgimSupervision.pgimUnidadMinera.idUnidadMinera, "
			+ "rasp.pgimSupervision.pgimUnidadMinera.noUnidadMinera "
			+ ") "
			+ "FROM PgimRankingSupervision rasp "
			+ "WHERE 1 = 1 "
			+ "AND rasp.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguracionRiesgo "
			+ "AND rasp.esRegistro = '1' "
			)
	List<PgimRankingSupervisionDTO> obtenerRankingsSupervisionPorConfiguracionRiesgo(@Param("idConfiguracionRiesgo") Long idConfiguracionRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTOResultado( "
			+ "rasp.idRankingSupervision, rasp.pgimSupervision.idSupervision, "
			+ "rasp.pgimConfiguraRiesgo.idConfiguraRiesgo,rasp.moPuntaje, "
			+ "rasp.pgimConfiguraRiesgo.pgimEspecialidad.idEspecialidad, "
			+ "rasp.pgimConfiguraRiesgo.pgimEspecialidad.noEspecialidad, "
			+ "rasp.pgimSupervision.pgimUnidadMinera.idUnidadMinera, "
			+ "rasp.pgimSupervision.pgimUnidadMinera.noUnidadMinera "
			+ ") "
			+ "FROM PgimRankingSupervision rasp "
			+ "WHERE 1 = 1 "
			+ "AND rasp.pgimSupervision.flRegistraRiesgos = '1' "
			+ "AND rasp.pgimSupervision.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
			+ "AND rasp.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo "
			+ "AND rasp.pgimSupervision.feFinSupervisionReal IS NOT NULL "
			+ "AND rasp.pgimSupervision.idSupervision IN ( "
			+ "SELECT aux.pgimSupervision.idSupervision "
			+ "FROM PgimSupervisionAux aux "
			+ "WHERE aux.idFaseActual IN (pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SUPERVISION_REV_INFO_SUPERVISION, pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SUPERVISION_APROB_RESULTADOS) "
			+ ") "
			+ "AND rasp.esRegistro = '1' "
			+ "ORDER BY rasp.pgimSupervision.feFinSupervisionReal DESC "
			)
	List<PgimRankingSupervisionDTO> listarRankingSupervisionPorUmConfig(@Param("idUnidadMinera") Long idUnidadMinera,@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTOResultado( "
			+ "rasp.idRankingSupervision, rasp.pgimSupervision.idSupervision, "
			+ "rasp.pgimConfiguraRiesgo.idConfiguraRiesgo,rasp.moPuntaje, "
			+ "rasp.pgimConfiguraRiesgo.pgimEspecialidad.idEspecialidad, "
			+ "rasp.pgimConfiguraRiesgo.pgimEspecialidad.noEspecialidad, "
			+ "rasp.pgimSupervision.pgimUnidadMinera.idUnidadMinera, "
			+ "rasp.pgimSupervision.pgimUnidadMinera.noUnidadMinera "
			+ ") "
			+ "FROM PgimRankingSupervision rasp "
			+ "WHERE 1 = 1 "
			+ "AND rasp.pgimSupervision.idSupervision = :idSupervision "
			+ "AND rasp.esRegistro = '1' "
			)
	PgimRankingSupervisionDTO obtenerRankingSupervisionPorIdSupervision(@Param("idSupervision") Long idSupervision);
}
