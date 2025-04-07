package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgMatrizPares;

import java.util.List;

/**
 * @descripción: Lógica de negocio de la entidad configuración de la matriz de pares de riesgos
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
@Repository
public interface CfgMatrizParesRepository extends JpaRepository<PgimCfgMatrizPares, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo, mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "INNER JOIN mapa.cfgFactorRiesgoFila cfrif "
			+ "INNER JOIN mapa.cfgFactorRiesgoColu cfric "
			+ "WHERE mapa.esRegistro = '1' "
			+ "AND cfrif.esRegistro = '1' "
			+ "AND cfric.esRegistro = '1' "
			+ "AND mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo IN "
			+ "("
			+ "SELECT cfari.idCfgFactorRiesgo FROM PgimCfgFactorRiesgo cfari "
			+ "WHERE cfari.esRegistro = '1' "
			+ "AND cfari.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = :idCfgGrupoRiesgo "
			+ ")")
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesPorIdCfgGrupoRiesgo(
			@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);
   
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, "
			+ "frif.idCfgFactorRiesgo, cfrf.noFactor, frif.nuOrdenPrioridad, "
			+ "fric.idCfgFactorRiesgo, cfrc.noFactor, fric.nuOrdenPrioridad, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "INNER JOIN PgimCfgFactorRiesgo frif ON mapa.cfgFactorRiesgoFila = frif AND frif.esRegistro = '1' "
			+ "INNER JOIN PgimFactorRiesgo cfrf ON frif.pgimFactorRiesgo = cfrf AND cfrf.esRegistro = '1' "
			+ "INNER JOIN PgimCfgFactorRiesgo fric ON mapa.cfgFactorRiesgoColu = fric AND fric.esRegistro = '1' "
			+ "INNER JOIN PgimFactorRiesgo cfrc ON fric.pgimFactorRiesgo = cfrc AND cfrc.esRegistro = '1' "
			+ "AND frif.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = "
			+ "("
			+ "SELECT gruri.idCfgGrupoRiesgo FROM PgimCfgGrupoRiesgo gruri "
			+ "WHERE gruri.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo "
            + "AND gruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
            + "AND gruri.esRegistro = '1' "
			+ ")"
			+ "AND frif.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = fric.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo "
			+ "WHERE mapa.esRegistro = '1' "
			+ "ORDER BY frif.nuOrdenPrioridad, fric.nuOrdenPrioridad ")
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesTC(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo, 
    		@Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, "
			+ "frif.idCfgFactorRiesgo, cfrf.noFactor, frif.nuOrdenPrioridad, "
			+ "fric.idCfgFactorRiesgo, cfrc.noFactor, fric.nuOrdenPrioridad, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn, frif.nuVectorResultante "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "INNER JOIN PgimCfgFactorRiesgo frif ON mapa.cfgFactorRiesgoFila = frif AND frif.esRegistro = '1' "
			+ "INNER JOIN PgimFactorRiesgo cfrf ON frif.pgimFactorRiesgo = cfrf AND cfrf.esRegistro = '1' "
			+ "INNER JOIN PgimCfgFactorRiesgo fric ON mapa.cfgFactorRiesgoColu = fric AND fric.esRegistro = '1' "
			+ "INNER JOIN PgimFactorRiesgo cfrc ON fric.pgimFactorRiesgo = cfrc AND cfrc.esRegistro = '1' "
			+ "AND frif.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = "
			+ "("
			+ "SELECT gruri.idCfgGrupoRiesgo FROM PgimCfgGrupoRiesgo gruri "
			+ "WHERE gruri.pgimConfiguraRiesgo.idConfiguraRiesgo = :idConfiguraRiesgo "
            + "AND gruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
            + "AND gruri.esRegistro = '1' "
			+ ")"
			+ "AND frif.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = fric.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo "
			+ "WHERE mapa.esRegistro = '1' "
			+ "ORDER BY frif.nuOrdenPrioridad, fric.nuOrdenPrioridad ")
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizNormalizadaTC(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo, 
    		@Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo, mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "WHERE mapa.esRegistro = '1' "
			+ "AND mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo = :idCfgFactorRiesgo "
			+ "")
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesPorIdCfgFactorRiesgoFila(@Param("idCfgFactorRiesgo") Long idCfgFactorRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo, mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "WHERE mapa.esRegistro = '1' "
			+ "AND mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo = :idCfgFactorRiesgo "
			+ "")
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesPorIdCfgFactorRiesgoColu(@Param("idCfgFactorRiesgo") Long idCfgFactorRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo, mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "WHERE mapa.esRegistro = '1' "
			+ "AND mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo = :idCfgFactorRiesgoFila "
			+ "AND mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo = :idCfgFactorRiesgoColu "
			)
	PgimCfgMatrizParesDTO obtenerCfgMatrizParesPorFilaYColumna(@Param("idCfgFactorRiesgoFila") Long idCfgFactorRiesgoFila, 
			@Param("idCfgFactorRiesgoColu") Long idCfgFactorRiesgoColu);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo, mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "WHERE mapa.esRegistro = '1' "
			+ "AND mapa.idCfgMatrizPares = :idCfgMatrizPares "
			)
	PgimCfgMatrizParesDTO obtenerCfgMatrizParesPorId(@Param("idCfgMatrizPares") Long idCfgMatrizPares);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo, mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "INNER JOIN mapa.cfgFactorRiesgoFila cfrif "
			+ "INNER JOIN mapa.cfgFactorRiesgoColu cfric "
			+ "WHERE mapa.esRegistro = '1' "
			+ "AND cfrif.esRegistro = '1' "
			+ "AND cfric.esRegistro = '1' "
			+ "AND mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo IN "
			+ "("
			+ "SELECT cfari.idCfgFactorRiesgo "
			+ "FROM PgimCfgFactorRiesgo cfari "
			+ "		INNER JOIN PgimCfgGrupoRiesgo cgruri ON cfari.pgimCfgGrupoRiesgo = cgruri "
            + "		INNER JOIN PgimConfiguraRiesgo cori ON cgruri.pgimConfiguraRiesgo = cori "
			+ "WHERE cgruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo "
			+ "AND cgruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
			+ "AND cfari.esRegistro = '1' "
			+ "AND cgruri.esRegistro = '1' "
            + "AND cori.esRegistro = '1' "
			+ ") "
			+ "AND ("
			+ "mapa.nuNumerador IS NULL OR mapa.nuDenominador IS NULL OR mapa.nuMcp IS NULL"
			+ ")")
	List<PgimCfgMatrizParesDTO> validarCfgMatrizParesIncompleto(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo, 
    		@Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo, mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "INNER JOIN mapa.cfgFactorRiesgoFila cfrif "
			+ "INNER JOIN mapa.cfgFactorRiesgoColu cfric "
			+ "WHERE mapa.esRegistro = '1' "
			+ "AND cfrif.esRegistro = '1' "
			+ "AND cfric.esRegistro = '1' "
			+ "AND mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo = :idCfgFactorRiesgoColu "
			)
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesPorColumna(
			@Param("idCfgFactorRiesgoColu") Long idCfgFactorRiesgoColu);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo, mapa.cfgFactorRiesgoColu.idCfgFactorRiesgo, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "INNER JOIN mapa.cfgFactorRiesgoFila cfrif "
			+ "INNER JOIN mapa.cfgFactorRiesgoColu cfric "
			+ "WHERE mapa.esRegistro = '1' "
			+ "AND cfrif.esRegistro = '1' "
			+ "AND cfric.esRegistro = '1' "
			+ "AND mapa.cfgFactorRiesgoFila.idCfgFactorRiesgo = :idCfgFactorRiesgoFila "
			)
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesPorFila(
			@Param("idCfgFactorRiesgoFila") Long idCfgFactorRiesgoFila);
	
	//PGIM-5009 - pjimenez
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, "
			+ "frif.idCfgFactorRiesgo, cfrf.noFactor, frif.nuOrdenPrioridad, "
			+ "fric.idCfgFactorRiesgo, cfrc.noFactor, fric.nuOrdenPrioridad, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "INNER JOIN PgimCfgFactorRiesgo frif ON mapa.cfgFactorRiesgoFila = frif AND frif.esRegistro = '1' "
			+ "INNER JOIN PgimFactorRiesgo cfrf ON frif.pgimFactorRiesgo = cfrf " 
			+ "INNER JOIN PgimCfgFactorRiesgo fric ON mapa.cfgFactorRiesgoColu = fric AND fric.esRegistro = '1' "
			+ "INNER JOIN PgimFactorRiesgo cfrc ON fric.pgimFactorRiesgo = cfrc " 
			+ "AND frif.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = "
			+ "("
			+ "SELECT gruri.idCfgGrupoRiesgo FROM PgimCfgGrupoRiesgo gruri "
			+ "WHERE gruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo "
            + "AND gruri.esRegistro = '1' "
			+ ")"
			+ "AND frif.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = fric.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo "
			+ "WHERE mapa.esRegistro = '1' "
			+ "ORDER BY frif.nuOrdenPrioridad, fric.nuOrdenPrioridad ")
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizParesTCPorCfgGrupo(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);
	
	//PGIM-5009 - pjimenez
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgMatrizParesDTOResultado( "
			+ "mapa.idCfgMatrizPares, "
			+ "frif.idCfgFactorRiesgo, cfrf.noFactor, frif.nuOrdenPrioridad, "
			+ "fric.idCfgFactorRiesgo, cfrc.noFactor, fric.nuOrdenPrioridad, "
			+ "mapa.flEsEditable, mapa.nuNumerador, mapa.nuDenominador, mapa.nuMcp, mapa.nuMcpn, frif.nuVectorResultante "
			+ ")"
			+ "FROM PgimCfgMatrizPares mapa "
			+ "INNER JOIN PgimCfgFactorRiesgo frif ON mapa.cfgFactorRiesgoFila = frif AND frif.esRegistro = '1' "
			+ "INNER JOIN PgimFactorRiesgo cfrf ON frif.pgimFactorRiesgo = cfrf "
			+ "INNER JOIN PgimCfgFactorRiesgo fric ON mapa.cfgFactorRiesgoColu = fric AND fric.esRegistro = '1' "
			+ "INNER JOIN PgimFactorRiesgo cfrc ON fric.pgimFactorRiesgo = cfrc "
			+ "AND frif.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = "
			+ "("
			+ "SELECT gruri.idCfgGrupoRiesgo FROM PgimCfgGrupoRiesgo gruri "
			+ "WHERE gruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo "
            + "AND gruri.esRegistro = '1' "
			+ ")"
			+ "AND frif.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo = fric.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo "
			+ "WHERE mapa.esRegistro = '1' "
			+ "ORDER BY frif.nuOrdenPrioridad, fric.nuOrdenPrioridad ")
	List<PgimCfgMatrizParesDTO> obtenerCfgMatrizNormalizadaTCPorCfgGrupo(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo);
}
