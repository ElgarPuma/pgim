package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUmFactor;

/**
 * Éste interface ItemSolicitudDocRepository
 * que aplica obtener y listar Ranking de unidades mineras por factores.
 * 
 * @descripción: Logica de negocio de la entidad Ranking de unidades mineras por factores.
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface RankingUmFactorRepository extends JpaRepository<PgimRankingUmFactor, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTOResultado( "
			+ "rafa.idRankingUmFactor, ragru.idRankingUmGrupo, fari.idFactorRiesgo, "
			+ "cfgnr.idCfgNivelRiesgo, rafa.moCalificacion, rafa.moPuntaje, rafa.cmCalificacion, "
			+ "cfari.nuOrdenPrioridad, fari.noFactor, 'Fiscalización', cfari.idCfgFactorRiesgo, cfari.nuVectorResultante  "
			+ ")"
			+ "FROM PgimRankingUmFactor rafa "
			+ "INNER JOIN rafa.pgimRankingUmGrupo ragru "			
			+ "INNER JOIN ragru.pgimCfgGrupoRiesgo gruri "
			+ "INNER JOIN rafa.pgimFactorRiesgo fari "
			+ "INNER JOIN PgimCfgFactorRiesgo cfari ON (fari.idFactorRiesgo = cfari.pgimFactorRiesgo.idFactorRiesgo "
			+ "											AND gruri.idCfgGrupoRiesgo = cfari.pgimCfgGrupoRiesgo.idCfgGrupoRiesgo) "  
			+ "LEFT OUTER JOIN rafa.pgimCfgNivelRiesgo cfgnr "
			+ "WHERE 1 = 1 "
			+ "AND ragru.idRankingUmGrupo = :idRankingUmGrupo "
			+ "AND gruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
			+ "AND rafa.esRegistro = '1' "
			+ "AND ragru.esRegistro = '1' "
			+ "AND gruri.esRegistro = '1' "			
			+ "AND cfari.esRegistro = '1' "
			+ "ORDER BY cfari.nuOrdenPrioridad ASC ")
	List<PgimRankingUmFactorDTO> listarRankingUmFactorPorRankingGrupoUmAndGrupoRiesgo(
			@Param("idRankingUmGrupo") Long idRankingUmGrupo,
			@Param("idGrupoRiesgo") Long idGrupoRiesgo);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTOResultado( "
			+ "rafa.idRankingUmFactor, rafa.pgimRankingUmGrupo.idRankingUmGrupo, rafa.pgimFactorRiesgo.idFactorRiesgo"
			+ ")"
			+ "FROM PgimRankingUmFactor rafa "
			+ "WHERE 1 = 1 "
			+ "AND rafa.esRegistro = '1' "
			+ "AND rafa.idRankingUmFactor = :idRankingUmFactor ")
	PgimRankingUmFactorDTO obtenerRankingUMFactorPorId(@Param("idRankingUmFactor") Long idRankingUmFactor);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTOResultado( "
			+ "rumf.idRankingUmFactor, rumf.pgimRankingUmGrupo.idRankingUmGrupo, rumf.pgimFactorRiesgo.idFactorRiesgo, "
			+ "rumf.pgimCfgNivelRiesgo.idCfgNivelRiesgo, rumf.moCalificacion, rumf.moPuntaje, rumf.cmCalificacion, "
			+ "rumf.pgimFactorRiesgo.noFactor, rumf.pgimFactorRiesgo.deFactor, "
			+ "rumf.pgimCfgNivelRiesgo.deEspecificacion) "
			+ "FROM PgimRankingUmFactor rumf "
			+ "WHERE 1 = 1 "
			+ "AND rumf.esRegistro = '1' "
			+ "AND rumf.pgimRankingUmGrupo.idRankingUmGrupo = :idRankingUmGrupo ")
	List<PgimRankingUmFactorDTO> listarRankingUMFactorPorRankingUmGrupo(@Param("idRankingUmGrupo") Long idRankingUmGrupo);
		
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTOResultado( "
			+ "rumf.idRankingUmFactor, rumf.pgimRankingUmGrupo.idRankingUmGrupo, rumf.pgimFactorRiesgo.idFactorRiesgo, "
			+ "cnr.idCfgNivelRiesgo, rumf.moCalificacion, rumf.moPuntaje, rumf.cmCalificacion, "
			+ "rumf.pgimFactorRiesgo.noFactor, rumf.pgimFactorRiesgo.deFactor, "
			+ "cnr.deEspecificacion) "
			+ "FROM PgimRankingUmFactor rumf "
			+ "LEFT JOIN PgimCfgNivelRiesgo cnr ON (rumf.pgimCfgNivelRiesgo.idCfgNivelRiesgo = cnr.idCfgNivelRiesgo AND cnr.esRegistro = '1') "
			+ "WHERE 1 = 1 "
			+ "AND rumf.esRegistro = '1' "
			+ "AND rumf.pgimRankingUmGrupo.esRegistro = '1' "
			+ "AND rumf.pgimRankingUmGrupo.idRankingUmGrupo = :idRankingUmGrupo ")
	List<PgimRankingUmFactorDTO> listarFactoresIncluidoPendientesPorIdGrupo(@Param("idRankingUmGrupo") Long idRankingUmGrupo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTOResultado( "
			+ "rumf.idRankingUmFactor, rumf.pgimRankingUmGrupo.idRankingUmGrupo, rumf.pgimFactorRiesgo.idFactorRiesgo, "
			+ "rumf.pgimCfgNivelRiesgo.idCfgNivelRiesgo, rumf.moCalificacion, rumf.moPuntaje, rumf.cmCalificacion, "
			+ "rumf.pgimFactorRiesgo.noFactor, rumf.pgimFactorRiesgo.deFactor, "
			+ "rumf.pgimCfgNivelRiesgo.deEspecificacion) "
			+ "FROM PgimRankingUmFactor rumf "
			+ "INNER JOIN rumf.pgimRankingUmGrupo rumgru "
			+ "WHERE 1 = 1 "
			+ "AND rumf.esRegistro = '1' "
			+ "AND rumgru.pgimRankingUm.idRankingUm = :idRankingUm ")
	List<PgimRankingUmFactorDTO> listarRankingUMFactorPorRankingUm(@Param("idRankingUm") Long idRankingUm);

	/**
	 * Permite conocer la lista de factores requeridos para ser registrados en la fiscalización.
	 * @param idRankingUmGrupo
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTOResultado( "
			+ "rumf.idRankingUmFactor, rumf.pgimRankingUmGrupo.idRankingUmGrupo, rumf.pgimFactorRiesgo.idFactorRiesgo, "
			+ "cnr.idCfgNivelRiesgo, rumf.moCalificacion, rumf.moPuntaje, rumf.cmCalificacion, "
			+ "rumf.pgimFactorRiesgo.noFactor, rumf.pgimFactorRiesgo.deFactor, "
			+ "cnr.deEspecificacion) "
			+ "FROM PgimRankingUmFactor rumf "
			+ "LEFT OUTER JOIN PgimCfgNivelRiesgo cnr ON rumf.pgimCfgNivelRiesgo.idCfgNivelRiesgo = cnr.idCfgNivelRiesgo "
			+ "WHERE 1 = 1 "
			+ "AND rumf.pgimFactorRiesgo.tipoOrigenDatoRiesgo.coClaveTexto = :ORDAR_FSCLZCION "
			+ "AND rumf.esRegistro = '1' "
			+ "AND rumf.pgimRankingUmGrupo.idRankingUmGrupo = :idRankingUmGrupo ")
	List<PgimRankingUmFactorDTO> listarFactoresFiscalizacionPorIdGrupo(@Param("idRankingUmGrupo") Long idRankingUmGrupo, @Param("ORDAR_FSCLZCION") String ORDAR_FSCLZCION);
	
	
	
}