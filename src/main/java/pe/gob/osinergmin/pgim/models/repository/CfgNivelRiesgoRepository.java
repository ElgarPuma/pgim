package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgNivelRiesgo;

/*
* En ésta interface CfgNivelRiesgoRepository está conformado pos sus 
 * Métodos de listar nivel riesgo por grupo riesgo.
 * 
 * @descripción: Lógica de negocio de la entidad Configuracion nivel riesgo
 * 
 * @author: lbarrantes
 * @version: 1.0
 * @fecha_de_creación: 25/05/2020
 * @fecha_de_ultima_actualización: 25/06/2020
*/
@Repository
public interface CfgNivelRiesgoRepository extends JpaRepository<PgimCfgNivelRiesgo, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTOResultado( "
			+ "cniri.idCfgNivelRiesgo, cfari.idCfgFactorRiesgo, cniri.pgimNivelRiesgo.idNivelRiesgo, cniri.deEspecificacion, "
			+ "niri.nuOrden, niri.noNivelRiesgo, niri.nuValor"
			+ ")"
			+ "FROM PgimCfgNivelRiesgo cniri "
			+ "INNER JOIN cniri.pgimNivelRiesgo niri "
			+ "INNER JOIN cniri.pgimCfgFactorRiesgo cfari "
			+ "INNER JOIN cfari.pgimCfgGrupoRiesgo cgruri "
			+ "WHERE 1 = 1 "
			+ "AND cgruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo "
			+ "AND cniri.esRegistro = '1' "
			+ "AND niri.esRegistro = '1' "
			+ "AND cfari.esRegistro = '1' "
			+ "AND cgruri.esRegistro = '1' "
			+ "ORDER BY cfari.idCfgFactorRiesgo, niri.nuOrden ASC ")
	List<PgimCfgNivelRiesgoDTO> listarCfgNivelRiesgoPorGrupoRiesgo(
			@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTOResultado( "
			+ "cniri.idCfgNivelRiesgo, cniri.pgimCfgFactorRiesgo.idCfgFactorRiesgo, cniri.pgimNivelRiesgo.idNivelRiesgo, cniri.deEspecificacion "
			+ ")"
			+ "FROM PgimCfgNivelRiesgo cniri "
			+ "WHERE 1 = 1 "
			+ "AND cniri.esRegistro = '1' "
			+ "and cniri.pgimCfgFactorRiesgo.idCfgFactorRiesgo = :idCfgFactorRiesgo ")
	List<PgimCfgNivelRiesgoDTO> listarCfgNivelRiesgoPorFactorRiesgo(
			@Param("idCfgFactorRiesgo") Long idCfgFactorRiesgo);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTOResultado( "
			+ "cniri.idCfgNivelRiesgo, cniri.pgimCfgFactorRiesgo.idCfgFactorRiesgo, cniri.pgimNivelRiesgo.idNivelRiesgo, "
			+ "cniri.pgimNivelRiesgo.noNivelRiesgo, cniri.pgimNivelRiesgo.nuOrden, cniri.deEspecificacion "
			+ ")"
			+ "FROM PgimCfgNivelRiesgo cniri "
			+ "WHERE 1 = 1 "
			+ "AND cniri.esRegistro = '1' "
			+ "and cniri.pgimCfgFactorRiesgo.idCfgFactorRiesgo = :idCfgFactorRiesgo ")
	List<PgimCfgNivelRiesgoDTO> listarCfgNivelRiesgoPorIdCfgFactorRiesgo(
			@Param("idCfgFactorRiesgo") Long idCfgFactorRiesgo);

	@Query("SELECT COUNT(cniri.idCfgNivelRiesgo) "
			+ "FROM PgimCfgNivelRiesgo cniri "
			+ "INNER JOIN cniri.pgimNivelRiesgo niri "
			+ "INNER JOIN cniri.pgimCfgFactorRiesgo cfari "
			+ "INNER JOIN cfari.pgimCfgGrupoRiesgo cgruri "
			+ "INNER JOIN cgruri.pgimConfiguraRiesgo cfri "
			+ "WHERE 1 = 1 "
			+ "AND cfri.idConfiguraRiesgo = :idConfiguraRiesgo "
			+ "AND cniri.deEspecificacion IS NULL "
			+ "AND cniri.esRegistro = '1' "
			+ "AND niri.esRegistro = '1' "
			+ "AND cfari.esRegistro = '1' "
			+ "AND cgruri.esRegistro = '1' "
			+ "AND cfri.esRegistro = '1' "
			)
		Integer obtenerCantidadCfgNivelRiesgoEspecNulos(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);

}
