package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgGrupoRiesgo;

import java.util.List;

/*
* En ésta interface CfgGrupoRiesgoRepository está conformado pos sus 
 * Métodos de listar grupo riesgo por configuracion.
 * 
 * @descripción: Lógica de negocio de la entidad Configuracion grupo riesgo
 * 
 * @author: lbarrantes
 * @version: 1.0
 * @fecha_de_creación: 25/05/2020
 * @fecha_de_ultima_actualización: 25/06/2020
*/
@Repository
public interface CfgGrupoRiesgoRepository extends JpaRepository<PgimCfgGrupoRiesgo, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTOResultado( "
			+ "cgruri.idCfgGrupoRiesgo, cgruri.pgimConfiguraRiesgo.idConfiguraRiesgo, cgruri.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "cgruri.noGrupoRiesgo, cgruri.deGrupoRiesgo, cgruri.pcFactorCorreccion, "
			+ "cgruri.nuRatioConsistencia"
			+ ")"
			+ "FROM PgimCfgGrupoRiesgo cgruri "
			+ "WHERE cgruri.esRegistro = '1' "
			+ "AND cgruri.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo ")
	List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgoPorConfiguracion(
			@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTOResultado( "
			+ "cgruri.idCfgGrupoRiesgo, cfgri.idConfiguraRiesgo, grri.idGrupoRiesgo, "
			+ "cgruri.noGrupoRiesgo, cgruri.deGrupoRiesgo, cgruri.pcFactorCorreccion, "
			+ "cgruri.nuRatioConsistencia"
			+ ")"
			+ "FROM PgimCfgGrupoRiesgo cgruri "
			+ "	    INNER JOIN cgruri.pgimConfiguraRiesgo cfgri "
			+ "	    INNER JOIN cgruri.pgimGrupoRiesgo grri "
			+ "WHERE cgruri.esRegistro = '1' "
			+ "AND EXISTS "
			+ "( "
			+ " SELECT  1 "
			+ " FROM PgimRankingUmGrupo raumg "
			+ "      INNER JOIN raumg.pgimCfgGrupoRiesgo cgrurii "
			+ "      INNER JOIN raumg.pgimRankingUm raumi "
			+ "      INNER JOIN raumi.pgimRankingRiesgo rarie "
			+ " 	 INNER JOIN rarie.pgimConfiguraRiesgo cfgrii "
			+ " WHERE raumg.flRegistrar = '1' "
			+ " AND cgrurii.idCfgGrupoRiesgo = cgruri.idCfgGrupoRiesgo "
			+ " AND raumi.idRankingUm = :idRankingUm "
			+ " AND cfgrii.idConfiguraRiesgo = cfgri.idConfiguraRiesgo "
			+ " AND raumg.esRegistro = '1' "
			+ " AND cgrurii.esRegistro = '1' "
			+ " AND raumi.esRegistro = '1' "
			+ " AND rarie.esRegistro = '1' "			
			+ " AND cfgrii.esRegistro = '1' "			
			+ ")")
	List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgoPorIdRankingUm(
			@Param("idRankingUm") Long idRankingUm);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTOResultado( "
			+ "cgruri.idCfgGrupoRiesgo, cfgri.idConfiguraRiesgo, grri.idGrupoRiesgo, "
			+ "cgruri.noGrupoRiesgo, cgruri.deGrupoRiesgo, cgruri.pcFactorCorreccion, "
			+ "cgruri.nuRatioConsistencia"
			+ ")"
			+ "FROM PgimCfgGrupoRiesgo cgruri "
			+ "	    INNER JOIN cgruri.pgimConfiguraRiesgo cfgri "
			+ "	    INNER JOIN cgruri.pgimGrupoRiesgo grri "
			+ "WHERE cgruri.esRegistro = '1' "
			+ "AND EXISTS "
			+ "( "
			+ " SELECT  1 "
			+ " FROM PgimRankingUmGrupo raumg "
			+ "      INNER JOIN raumg.pgimCfgGrupoRiesgo cgrurii "
			+ "      INNER JOIN raumg.pgimRankingSupervision rasup "
			+ " 	 INNER JOIN rasup.pgimConfiguraRiesgo cfgrii "
			+ " WHERE raumg.flRegistrar = '1' "
			+ " AND cgrurii.idCfgGrupoRiesgo = cgruri.idCfgGrupoRiesgo "
			+ " AND rasup.idRankingSupervision = :idRankingSupervision "
			+ " AND cfgrii.idConfiguraRiesgo = cfgri.idConfiguraRiesgo "
			+ " AND raumg.esRegistro = '1' "
			+ " AND cgrurii.esRegistro = '1' "
			+ " AND rasup.esRegistro = '1' "
			+ " AND cfgrii.esRegistro = '1' "			
			+ ")")
	List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgoPorIdRankingSupervision(
			@Param("idRankingSupervision") Long idRankingSupervision);

	/**
	 * Permite obtener la lista de grupos de riesgo de un determinado ranking de riesgo y tipo de grupo de riesgo.
	 * @param idUnidadMinera
	 * @param idGrupoRiesgo
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTOResultado( "
			+ "cfri.idCfgGrupoRiesgo, cori.idConfiguraRiesgo, cfri.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "cfri.noGrupoRiesgo, cfri.deGrupoRiesgo, cfri.pcFactorCorreccion, "
			+ "cfri.nuRatioConsistencia"
			+ ")"
			+ "FROM PgimRankingUmGrupo raug "
			+ "     INNER JOIN raug.pgimCfgGrupoRiesgo cfri "
			+ "     INNER JOIN cfri.pgimConfiguraRiesgo cori "
			+ "		INNER JOIN cfri.pgimGrupoRiesgo grri "
			+ "		INNER JOIN raug.pgimRankingUm raum "
			+ "WHERE raum.idRankingUm = :idRankingUm "
			+ "AND grri.idGrupoRiesgo = :idGrupoRiesgo "
			+ "AND raug.esRegistro = '1' "
			+ "AND cfri.esRegistro = '1' "
			+ "AND cori.esRegistro = '1' "
			+ "AND grri.esRegistro = '1' ")
	List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgo(@Param("idRankingUm") Long idUnidadMinera, @Param("idGrupoRiesgo") Long idGrupoRiesgo);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTOResultado( "
			+ "cgruri.idCfgGrupoRiesgo, cgruri.pgimConfiguraRiesgo.idConfiguraRiesgo, cgruri.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "cgruri.nuCalificacionGrupo, cgruri.nuIndiceConsistencia, cgruri.nuRatioConsistencia, cgruri.nuIndConsistenciaAleatoria, "
			+ "cgruri.nuNmax)"
			+ "FROM PgimCfgGrupoRiesgo cgruri "
			+ "WHERE cgruri.esRegistro = '1' "
			+ "AND cgruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo "
            + "AND cgruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo ")
	PgimCfgGrupoRiesgoDTO obtenerCalculoRatioConsistencia(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo, 
    		@Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	//PGIM-5007 - pjimenez
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTOResultado( "
			+ "cgruri.idCfgGrupoRiesgo, "
			+ "cgruri.pgimConfiguraRiesgo.idConfiguraRiesgo, "
			+ "cgruri.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "cgruri.nuCalificacionGrupo, "
			+ "cgruri.nuIndiceConsistencia, "
			+ "cgruri.nuRatioConsistencia, "
			+ "cgruri.nuIndConsistenciaAleatoria, "
			+ "cgruri.nuNmax, cgruri.noGrupoRiesgo, "
			+ "cgruri.deGrupoRiesgo, "
			+ "cgruri.pcFactorCorreccion "
			+ ")"
			+ "FROM PgimCfgGrupoRiesgo cgruri "
			+ "WHERE cgruri.esRegistro = '1' "
			+ "AND cgruri.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo "
			+ "AND cgruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo ")
	List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgoPorConfiguracionyGrupo(
			@Param("idConfiguraRiesgo") Long idConfiguraRiesgo, @Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	//PGIM-5008 - pjimenez
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTOResultado( "
			+ "cgruri.idCfgGrupoRiesgo, "
			+ "cgruri.pgimConfiguraRiesgo.idConfiguraRiesgo, "
			+ "cgruri.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "cgruri.nuCalificacionGrupo, "
			+ "cgruri.nuIndiceConsistencia, "
			+ "cgruri.nuRatioConsistencia, "
			+ "cgruri.nuIndConsistenciaAleatoria, "
			+ "cgruri.nuNmax, "
			+ "cgruri.noGrupoRiesgo, "
			+ "cgruri.deGrupoRiesgo, "
			+ "cgruri.pcFactorCorreccion "
            + ") "
            + "FROM PgimCfgGrupoRiesgo cgruri "
            + "WHERE 1 = 1 "
            + "AND cgruri.esRegistro = '1' "
            + "AND cgruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo ")
    PgimCfgGrupoRiesgoDTO obtenerCfgGrupoRiesgoPorId(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);
	
	//PGIM-5009 - pjimenez
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTOResultado( "
			+ "cgruri.idCfgGrupoRiesgo, cgruri.pgimConfiguraRiesgo.idConfiguraRiesgo, cgruri.pgimGrupoRiesgo.idGrupoRiesgo, "
			+ "cgruri.nuCalificacionGrupo, cgruri.nuIndiceConsistencia, cgruri.nuRatioConsistencia, cgruri.nuIndConsistenciaAleatoria, "
			+ "cgruri.nuNmax)"
			+ "FROM PgimCfgGrupoRiesgo cgruri "
			+ "WHERE cgruri.esRegistro = '1' "
			+ "AND cgruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo ")
	PgimCfgGrupoRiesgoDTO obtenerCalculoRatioConsistenciaPorCfgGrupo(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);	

	@Query("SELECT COUNT(cfari.idCfgFactorRiesgo) "
		+ "FROM PgimCfgFactorRiesgo cfari "
		+ "INNER JOIN cfari.pgimCfgGrupoRiesgo cgruri "
		+ "INNER JOIN cgruri.pgimConfiguraRiesgo cfri "
		+ "INNER JOIN cgruri.pgimGrupoRiesgo gri "
		+ "WHERE cfari.esRegistro = '1' "
		+ "AND cgruri.esRegistro = '1' "
		+ "AND cfri.esRegistro = '1' "
		+ "AND gri.esRegistro = '1' "
		+ "AND cfri.idConfiguraRiesgo = :idConfiguraRiesgo "
		+ "AND gri.idGrupoRiesgo = :idGrupoRiesgo "
		+ "AND cfari.flAfectadoGestion = '1' "
		)
	Integer obtenerCantidadFactTecFlAfectadoGestion( @Param("idConfiguraRiesgo") Long idConfiguraRiesgo, @Param("idGrupoRiesgo") Long idGrupoRiesgo);

}
