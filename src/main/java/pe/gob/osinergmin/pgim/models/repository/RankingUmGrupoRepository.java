package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUmGrupo;

/**
 * Tiene como nombre Proceso administrativo sancionador.
 * 
 * @descripción: Logica de negocio de la entidad Ranking UM grupo
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface RankingUmGrupoRepository extends JpaRepository<PgimRankingUmGrupo, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "unmi.noUnidadMinera "
			+ ")"
			+ "FROM PgimRankingUmGrupo ragru "
			+ "INNER JOIN PgimRankingUm raum ON ragru.pgimRankingUm = raum "
			+ "INNER JOIN PgimUnidadMinera unmi ON raum.pgimUnidadMinera = unmi "
			+ "INNER JOIN PgimCfgGrupoRiesgo gruri ON ragru.pgimCfgGrupoRiesgo = gruri "
			+ "WHERE 1 = 1 "
			+ "AND ragru.esRegistro = '1' "
			+ "AND raum.esRegistro = '1' "
			+ "AND unmi.esRegistro = '1' "
			+ "AND gruri.esRegistro = '1' "
			+ "AND gruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
			+ "AND ragru.idRankingUmGrupo = :idRankingUmGrupo ")
	PgimRankingUmGrupoDTO obtenerUnidadMineraPorRankingGrupoUmAndGrupoRiesgo(
			@Param("idRankingUmGrupo") Long idRankingUmGrupo,
			@Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "ragru.idRankingUmGrupo, ragru.pgimRankingUm.idRankingUm, ragru.pgimRankingSupervision.idRankingSupervision, "
			+ "ragru.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, ragru.moPuntaje, ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "ra.idRankingRiesgo ) "
			+ "FROM PgimRankingUmGrupo ragru "
			+ "LEFT JOIN ragru.pgimRankingUm.pgimRankingRiesgo ra "
			+ "WHERE 1 = 1 "
			+ "AND ragru.flRegistrar = '1' "
			+ "AND ragru.esRegistro = '1' "
			+ "AND ragru.pgimRankingUm.idRankingUm = :idRankingUm ")
	List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorRankingUm(@Param("idRankingUm") Long idRankingUm);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "ragru.idRankingUmGrupo, ragru.pgimRankingUm.idRankingUm, ragru.pgimRankingSupervision.idRankingSupervision, "
			+ "ragru.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, ragru.moPuntaje, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.noGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.deGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.deGrupoRiesgo,"
			+ "ragru.pgimCfgGrupoRiesgo.pcFactorCorreccion,"
			+ "ragru.flRegistrar, "			
			+ "ragru.flMaximo,"			
			+ "ra.idRankingRiesgo ) "
			+ "FROM PgimRankingUmGrupo ragru "
			+ "LEFT JOIN ragru.pgimRankingUm.pgimRankingRiesgo ra "
			+ "WHERE 1 = 1 "
			+ "AND ragru.flRegistrar = '1' "
			+ "AND ragru.pgimRankingUm.idRankingUm IS NULL "
			+ "AND ragru.pgimRankingSupervision.idRankingSupervision = :idRankingSupervision "
			+ "AND ragru.esRegistro = '1' "
			)
	List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorIdRankingSuper(@Param("idRankingSupervision") Long idRankingSupervision);	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "ragru.idRankingUmGrupo, ragru.pgimRankingUm.idRankingUm, ragru.pgimRankingSupervision.idRankingSupervision, "
			+ "ragru.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, ragru.moPuntaje, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.noGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.deGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.deGrupoRiesgo,"
			+ "ragru.pgimCfgGrupoRiesgo.pcFactorCorreccion,"
			+ "ragru.flRegistrar, "			
			+ "ragru.flMaximo,"			
			+ "ra.idRankingRiesgo ) "
			+ "FROM PgimRankingUmGrupo ragru "
			+ "LEFT JOIN ragru.pgimRankingUm.pgimRankingRiesgo ra "
			+ "WHERE 1 = 1 "
			+ "AND ragru.pgimRankingUm.idRankingUm IS NULL "
			+ "AND ragru.pgimRankingSupervision.idRankingSupervision = :idRankingSupervision "
			+ "AND ragru.esRegistro = '1' "
			)
	List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorIdRankingSuperTodos(@Param("idRankingSupervision") Long idRankingSupervision);	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "ragru.idRankingUmGrupo, ragru.pgimRankingUm.idRankingUm, ragru.pgimRankingSupervision.idRankingSupervision, "
			+ "ragru.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, ragru.moPuntaje, ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "ra.idRankingRiesgo ) "
			+ "FROM PgimRankingUmGrupo ragru "
			+ "LEFT JOIN ragru.pgimRankingUm.pgimRankingRiesgo ra "
			+ "WHERE 1 = 1 "
			+ "AND ragru.esRegistro = '1' "
			+ "AND ragru.idRankingUmGrupo = :idRankingUmGrupo ")
	PgimRankingUmGrupoDTO obtenerRankingUmGrupoPorId(@Param("idRankingUmGrupo") Long idRankingUmGrupo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "ragru.idRankingUmGrupo, ragru.pgimRankingUm.idRankingUm, ragru.pgimRankingSupervision.idRankingSupervision, "
			+ "ragru.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, ragru.moPuntaje, ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.noGrupo, ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.deGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo,  ragru.pgimCfgGrupoRiesgo.deGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.pcFactorCorreccion, ragru.flRegistrar, ragru.flMaximo, "
			+ "ragru.pgimRankingSupervision.pgimConfiguraRiesgo.idConfiguraRiesgo, ragru.pgimRankingSupervision.pgimConfiguraRiesgo.noConfiguracion "
			+ ") "
			+ "FROM PgimRankingUmGrupo ragru "
			+ "WHERE 1 = 1 "			
			+ "AND ragru.pgimRankingUm.idRankingUm IS NULL "
			+ "AND ragru.pgimRankingSupervision.pgimSupervision.idSupervision = :idSupervision "
			+ "AND ragru.esRegistro = '1' "
			+ "ORDER BY ragru.flMaximo DESC, ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, ragru.flRegistrar DESC, ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo ASC"
			)
	List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorSupervision(@Param("idSupervision") Long idSupervision);	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "ragru.idRankingUmGrupo, ragru.pgimRankingUm.idRankingUm, ragru.pgimRankingSupervision.idRankingSupervision, "
			+ "ragru.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, ragru.moPuntaje, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.noGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.deGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.deGrupoRiesgo,"
			+ "ragru.pgimCfgGrupoRiesgo.pcFactorCorreccion,"
			+ "ragru.flRegistrar, "
			+ "ragru.flMaximo, "
			+ "ra.idRankingRiesgo ) "
			+ "FROM PgimRankingUmGrupo ragru "
			+ "LEFT JOIN ragru.pgimRankingUm.pgimRankingRiesgo ra "
			+ "WHERE 1 = 1 "
			+ "AND ragru.esRegistro = '1' "
			+ "AND ragru.pgimRankingUm.idRankingUm = :idRankingUm "
			+ "AND ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
			+ "ORDER BY ragru.flRegistrar DESC, ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo ASC"
	)
	List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorRankingUm(@Param("idRankingUm") Long idRankingUm, @Param("idGrupoRiesgo") Long idGrupoRiesgo);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "ragru.idRankingUmGrupo, ragru.pgimRankingUm.idRankingUm, ragru.pgimRankingSupervision.idRankingSupervision, "
			+ "ragru.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, ragru.moPuntaje, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.noGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.deGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.deGrupoRiesgo,"
			+ "ragru.pgimCfgGrupoRiesgo.pcFactorCorreccion,"
			+ "ragru.flRegistrar, "
			+ "ragru.flMaximo, "
			+ "ra.idRankingRiesgo ) "
			+ "FROM PgimRankingUmGrupo ragru "
			+ "LEFT JOIN ragru.pgimRankingUm.pgimRankingRiesgo ra "
			+ "WHERE 1 = 1 "
			+ "AND ragru.esRegistro = '1' "
			+ "AND ragru.pgimRankingSupervision.idRankingSupervision = :idRankingSupervision "
			+ "AND ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
			+ "ORDER BY ragru.flRegistrar DESC, ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo ASC"
	)	
	List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorRankingSupervision(@Param("idRankingSupervision") Long idRankingSupervision, @Param("idGrupoRiesgo") Long idGrupoRiesgo);


	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTOResultado( "
			+ "ragru.idRankingUmGrupo, ragru.pgimRankingUm.idRankingUm, ragru.pgimRankingSupervision.idRankingSupervision, "
			+ "ragru.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, ragru.moPuntaje, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.noGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.deGrupo, "
			+ "ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.deGrupoRiesgo, "
			+ "ragru.pgimCfgGrupoRiesgo.pcFactorCorreccion, "
			+ "ragru.flRegistrar,"
			+ "ragru.flMaximo, "
			+ "ra.idRankingRiesgo ) "
			+ "FROM PgimRankingUmGrupo ragru "
			+ "LEFT JOIN ragru.pgimRankingUm raum "
			+ "LEFT JOIN raum.pgimRankingRiesgo ra "
			+ "WHERE ragru.pgimCfgGrupoRiesgo.pgimGrupoRiesgo.idGrupoRiesgo = :idCfgGrupoRiesgo "
			+ "AND ragru.flRegistrar = '1' "
			+ "AND ragru.pgimRankingUm.idRankingUm IS NULL "
			+ "AND ragru.pgimRankingSupervision.idRankingSupervision IN "
			+ "( "
			+ "SELECT rs.idRankingSupervision "
			+ "FROM PgimRankingSupervision rs "
			+ "WHERE rs.pgimSupervision.idSupervision = :idSupervision "
			+ ") "
			+ "AND ragru.esRegistro = '1' "
			+ "ORDER BY ragru.flMaximo DESC, ragru.flRegistrar DESC, ragru.pgimCfgGrupoRiesgo.noGrupoRiesgo ASC"
			)
		List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorSupervisionYGrupo(@Param("idSupervision") Long idSupervision, @Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);	

}
