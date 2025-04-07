package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRegla;

import java.util.List;

/**
 * 
 * @descripción: Lógica de negocio de la entidad Configuración de Riesgos
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 23/02/2020
 * @fecha_de_ultima_actualización: 23/02/2020
 */
@Repository
public interface ConfiguraReglaRepository extends JpaRepository<PgimConfiguraRegla, Long> {

	/**
	 * Se utiliza para visualizar la lista de reglas
	 * 
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTOResultado( "
			+ "reg.idConfiguraRegla,  "
			+ "reg.pgimConfiguraRiesgo.idConfiguraRiesgo, "
			+ "reg.divisionSupervisora.idValorParametro, "
			+ "reg.metodoMinado.idValorParametro, "
			+ "reg.situacion.idValorParametro, "
			+ "reg.tipoActividad.idValorParametro, "
			+ "reg.estado.idValorParametro "
			+ ") "
			+ "FROM PgimConfiguraRegla reg "
			+ "WHERE reg.esRegistro = '1' "
			+ "AND reg.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo ")
	List<PgimConfiguraReglaDTO> obtenerReglasPorConfiguracion(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);

	/**
	 * Se utiliza para obtener un regitro de la entidad "Configuración de Riesgos"
	 * 
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTOResultado( "
			+ "reg.idConfiguraRegla,  "
			+ "reg.pgimConfiguraRiesgo.idConfiguraRiesgo,"
			+ "reg.divisionSupervisora.idValorParametro, "
			+ "reg.metodoMinado.idValorParametro, "
			+ "reg.situacion.idValorParametro, "
			+ "reg.tipoActividad.idValorParametro, "
			+ "reg.estado.idValorParametro "
			+ ") "
			+ "FROM PgimConfiguraRegla reg "
			+ "WHERE reg.esRegistro = '1' "
			+ "AND reg.idConfiguraRegla = :idConfiguraRegla ")
	PgimConfiguraReglaDTO obtenerReglaPorId(@Param("idConfiguraRegla") Long idConfiguraRegla);

	/**
	 * Se utiliza para visualizar la lista de reglas de riesgo por configuración de
	 * riesgo
	 * 
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTOResultado("
			+ "ROWNUM, "
			+ "core.idConfiguraRegla, "
			+ "core.pgimConfiguraRiesgo.idConfiguraRiesgo, "
			+ "core.divisionSupervisora.noValorParametro, "
			+ "core.metodoMinado.noValorParametro, "
			+ "core.situacion.noValorParametro, "
			+ "core.divisionSupervisora.idValorParametro, "
			+ "core.metodoMinado.idValorParametro, "
			+ "core.situacion.idValorParametro, "
			+ "tiac.idValorParametro, "
			+ "tiac.noValorParametro, "
			+ "esta.idValorParametro, "
			+ "esta.noValorParametro"
			+ ") "
			+ "FROM PgimConfiguraRegla core "
			+ "     LEFT OUTER JOIN core.estado esta "
			+ "     LEFT OUTER JOIN core.tipoActividad tiac "
			+ "WHERE core.esRegistro = '1' "
			+ "AND core.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo ")
	List<PgimConfiguraReglaDTO> listarReglasRiesgoPorConfiguracion(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);

	@Query("SELECT COUNT(reg.idConfiguraRegla) "
			+ "FROM PgimConfiguraRegla reg "
			+ "WHERE 1 = 1 "
			+ "AND reg.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo "
			+ "AND (reg.idConfiguraRegla <> :idConfiguraRegla OR :idConfiguraRegla IS NULL) "
			+ "AND reg.divisionSupervisora.idValorParametro = :idDivisionSupervisora "
			+ "AND reg.metodoMinado.idValorParametro = :idMetodoMinado "
			+ "AND reg.situacion.idValorParametro = :idSituacion "
			+ "AND reg.tipoActividad.idValorParametro = :idTipoActividad "
			+ "AND reg.estado.idValorParametro = :idEstado "
			+ "AND reg.esRegistro = '1'")
	Integer contarCantidadReglasRiesgo(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo,
			@Param("idConfiguraRegla") Long idConfiguraRegla,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idMetodoMinado") Long idMetodoMinado,
			@Param("idSituacion") Long idSituacion,
			@Param("idTipoActividad") Long idTipoActividad,
			@Param("idEstado") Long idEstado);

	@Query("SELECT COUNT(reg.idConfiguraRegla) "
			+ "FROM PgimConfiguraRegla reg "
			+ "WHERE reg.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo "
			+ "AND reg.esRegistro = '1'")
	Integer obtenerCantidadReglas(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);

	/**
	 * Permite contar la cantidad de reglas que se traslapan con algunas de las
	 * reglas de la actual configuración de riesgo
	 * 
	 * @param idConfiguraRiesgo
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraReglaDTOResultado( "
			+ "otras.pgimConfiguraRiesgo.idConfiguraRiesgo, "
			+ "otras.pgimConfiguraRiesgo.noConfiguracion, "
			+ "otras.idConfiguraRegla, "
			+ "otras.divisionSupervisora.idValorParametro, "			
			+ "otras.divisionSupervisora.noValorParametro, "
			+ "otras.metodoMinado.idValorParametro, "
			+ "otras.metodoMinado.noValorParametro, "
			+ "otras.situacion.idValorParametro, "
			+ "otras.situacion.noValorParametro, "
			+ "otras.estado.idValorParametro, "
			+ "otras.estado.noValorParametro, "
			+ "otras.tipoActividad.idValorParametro, "
			+ "otras.tipoActividad.noValorParametro "
	        +") "
			+ "FROM PgimConfiguraRegla reglas,  "
			+ "		PgimConfiguraRegla otras  "
			+ "WHERE 1 = 1  "
			+ "AND reglas.pgimConfiguraRiesgo.tipoConfiguracionRiesgo.coClaveTexto = :TIPO_CFG_RIESGO_FISCALIZACION "
			+ "AND otras.pgimConfiguraRiesgo.tipoConfiguracionRiesgo.coClaveTexto = :TIPO_CFG_RIESGO_FISCALIZACION "
			+ "AND otras.pgimConfiguraRiesgo.tipoEstadoConfiguracion.coClaveTexto = :ESCFG_APRBDA "
			+ "AND reglas.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo "
			+ "AND otras.pgimConfiguraRiesgo.idConfiguraRiesgo != :idConfiguraRiesgo "
			+ "AND ("
			+ "		reglas.pgimConfiguraRiesgo.pgimEspecialidad.idEspecialidad = otras.pgimConfiguraRiesgo.pgimEspecialidad.idEspecialidad "			
			+ "		AND reglas.divisionSupervisora.idValorParametro = otras.divisionSupervisora.idValorParametro "
			+ "		AND reglas.metodoMinado.idValorParametro = otras.metodoMinado.idValorParametro "
			+ "		AND reglas.situacion.idValorParametro = otras.situacion.idValorParametro "
			+ "		AND reglas.tipoActividad.idValorParametro = otras.tipoActividad.idValorParametro "
			+ "		AND reglas.estado.idValorParametro = otras.estado.idValorParametro "
			+ "    )"
			+ "AND reglas.esRegistro = '1' "
			+ "AND otras.esRegistro = '1' "
			+ "AND reglas.pgimConfiguraRiesgo.esRegistro = '1' "
			+ "AND otras.pgimConfiguraRiesgo.esRegistro = '1' "
			+ "ORDER BY otras.pgimConfiguraRiesgo.noConfiguracion "
			+ "       , otras.divisionSupervisora.noValorParametro "
			+ "       , otras.metodoMinado.noValorParametro "
			+ "       , otras.situacion.noValorParametro "
			)
	List<PgimConfiguraReglaDTO> obtenerTraslapesOtrasConfiguraciones(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo,
	@Param("ESCFG_APRBDA") String ESCFG_APRBDA, 
	@Param("TIPO_CFG_RIESGO_FISCALIZACION") String TIPO_CFG_RIESGO_FISCALIZACION);

}
