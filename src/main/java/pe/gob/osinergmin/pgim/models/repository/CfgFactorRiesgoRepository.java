package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgFactorRiesgo;

/*
* En ésta interface CfgFactorRiesgoRepository está conformado pos sus 
 * Métodos de listar riesgo por configuracion.
 * 
 * @descripción: Lógica de negocio de la entidad Configuracion factor riesgo
 * 
 * @author: lbarrantes
 * @version: 1.0
 * @fecha_de_creación: 25/05/2020
 * @fecha_de_ultima_actualización: 25/06/2020
*/
@Repository
public interface CfgFactorRiesgoRepository extends JpaRepository<PgimCfgFactorRiesgo, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTOResultado( "
			+ "cgfac.idCfgFactorRiesgo, "
			+ "cgfac.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, "
			+ "cgfac.pgimFactorRiesgo.idFactorRiesgo, "
			+ "cgfac.flAfectadoGestion "
			+ ")"
			+ "FROM PgimCfgFactorRiesgo cgfac "
			+ "WHERE cgfac.esRegistro = '1' "
			+ "AND cgfac.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = :idCfgGrupoRiesgo ")
	List<PgimCfgFactorRiesgoDTO> listarCfgFactorRiesgoPorConfiguracion(
			@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTOResultado( "
			+ "cgfac.idCfgFactorRiesgo, "
			+ "cgfac.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, "
			+ "cgfac.pgimFactorRiesgo.idFactorRiesgo, "
			+ "cgfac.flAfectadoGestion "
			+ ")"
			+ "FROM PgimCfgFactorRiesgo cgfac "
			+ "WHERE cgfac.esRegistro = '1' "
			+ "AND cgfac.flAfectadoGestion = '1' "
			+ "AND cgfac.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = :idCfgGrupoRiesgo ")
	List<PgimCfgFactorRiesgoDTO> listarCfgFactorRiesgoPorConfiguracionAfecGestion(
			@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTOResultado( "
            + "cfari.idCfgFactorRiesgo, cgruri.idCfgGrupoRiesgo, fari.idFactorRiesgo, "
            + "cfari.nuOrdenPrioridad, cfari.flAfectadoGestion, cfari.nuSumatoriaColumnaMcp, cfari.nuVectorResultante, "
            + "fari.noFactor, fari.tipoOrigenDatoRiesgo.noValorParametro ) "
            + "FROM PgimCfgFactorRiesgo cfari "
            + "     INNER JOIN PgimCfgGrupoRiesgo cgruri ON cfari.pgimCfgGrupoRiesgo = cgruri "
            + "     INNER JOIN PgimConfiguraRiesgo cori ON cgruri.pgimConfiguraRiesgo = cori "
            + "     INNER JOIN PgimFactorRiesgo fari ON cfari.pgimFactorRiesgo = fari "
            + "WHERE 1 = 1 "
            + "AND cgruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo "
            + "AND cgruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
            + "AND cfari.esRegistro = '1' "
            + "AND cgruri.esRegistro = '1' "
            + "AND cori.esRegistro = '1' "
            )
    List<PgimCfgFactorRiesgoDTO> listarCfgFactorTecnicoGestion(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo, 
    		@Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTOResultado( "
			+ "cfari.idCfgFactorRiesgo, cfari.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, fari.idFactorRiesgo, "
			+ "cfari.nuOrdenPrioridad, cfari.flAfectadoGestion, fari.noFactor, fari.deFactor, fari.tipoOrigenDatoRiesgo.idValorParametro "
            + ") "
            + "FROM PgimCfgFactorRiesgo cfari "
            + "INNER JOIN PgimCfgGrupoRiesgo cgruri ON cfari.pgimCfgGrupoRiesgo = cgruri "
            + "INNER JOIN PgimFactorRiesgo fari ON cfari.pgimFactorRiesgo = fari "
            + "WHERE 1 = 1 "
            + "AND cfari.esRegistro = '1' "
            + "AND cfari.idCfgFactorRiesgo = :idCfgFactorRiesgo "
            + "AND cgruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo ")
    PgimCfgFactorRiesgoDTO obtenerCfgFactorTecnicoGestion(@Param("idCfgFactorRiesgo") Long idCfgFactorRiesgo, 
    		@Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTOResultado( "
			+ "cfari.idCfgFactorRiesgo, cfari.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo, cfari.pgimFactorRiesgo.idFactorRiesgo, "
			+ "cfari.nuOrdenPrioridad, cfari.flAfectadoGestion, cfari.nuSumatoriaColumnaMcp, cfari.nuVectorResultante "
            + ") "
            + "FROM PgimCfgFactorRiesgo cfari "
            + "WHERE 1 = 1 "
            + "AND cfari.esRegistro = '1' "
            + "AND cfari.idCfgFactorRiesgo = :idCfgFactorRiesgo ")
    PgimCfgFactorRiesgoDTO obtenerCfgFactorRiesgoPorId(@Param("idCfgFactorRiesgo") Long idCfgFactorRiesgo);
	
	//PGIM 5009 - pjimenez
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgFactorRiesgoDTOResultado( "
            + "cfari.idCfgFactorRiesgo, cgruri.idCfgGrupoRiesgo, fari.idFactorRiesgo, "
            + "cfari.nuOrdenPrioridad, cfari.flAfectadoGestion, cfari.nuSumatoriaColumnaMcp, cfari.nuVectorResultante, "
            + "fari.noFactor, fari.tipoOrigenDatoRiesgo.noValorParametro ) "
            + "FROM PgimCfgFactorRiesgo cfari "
            + "INNER JOIN PgimCfgGrupoRiesgo cgruri ON cfari.pgimCfgGrupoRiesgo = cgruri "
            + "INNER JOIN PgimConfiguraRiesgo cori ON cgruri.pgimConfiguraRiesgo = cori "
            + "INNER JOIN PgimFactorRiesgo fari ON cfari.pgimFactorRiesgo = fari "
            + "WHERE 1 = 1 "
            + "AND cfari.esRegistro = '1' "
            + "AND cgruri.esRegistro = '1' "
            + "AND cori.esRegistro = '1' "
            + "AND cgruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo ")
    List<PgimCfgFactorRiesgoDTO> listarCfgFactorTecnicoGestion(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);
}
