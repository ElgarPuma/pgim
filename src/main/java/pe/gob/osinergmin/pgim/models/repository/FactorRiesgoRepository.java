package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimFactorRiesgo;

/**
 * Éste interface EntregableLiquidacionAuxRepository
 * que aplica obtener y listar Factor riesgo.
 * 
 * @descripción: Logica de negocio de la entidad Factor riesgo.
 * 
 * @author: lbarrantes
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface FactorRiesgoRepository extends JpaRepository<PgimFactorRiesgo, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTOResultado( "
            + "fari.idFactorRiesgo, fari.pgimGrupoRiesgo.idGrupoRiesgo, fari.pgimEspecialidad.idEspecialidad, "
            + "fari.tipoOrigenDatoRiesgo.idValorParametro, fari.noFactor, fari.deFactor "
            + ") "
            + "FROM PgimFactorRiesgo fari " 
            + "WHERE fari.esRegistro = '1' "
            + "AND fari.idFactorRiesgo = :idFactorRiesgo ")
	PgimFactorRiesgoDTO obtenerFactorRiesgoPorId(@Param("idFactorRiesgo") Long idFactorRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTOResultado( "
            + "fari.idFactorRiesgo, fari.pgimGrupoRiesgo.idGrupoRiesgo, fari.pgimEspecialidad.idEspecialidad, "
            + "fari.tipoOrigenDatoRiesgo.idValorParametro, fari.noFactor, fari.deFactor "
            + ") "
            + "FROM PgimFactorRiesgo fari " 
            + "WHERE fari.esRegistro = '1' "
            + "AND fari.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
            + "AND fari.pgimEspecialidad.idEspecialidad = :idEspecialidad "
            + "AND fari.idFactorRiesgo NOT IN "
            + "( "
            + "SELECT cfari.pgimFactorRiesgo.idFactorRiesgo FROM PgimCfgFactorRiesgo cfari "
            + "INNER JOIN PgimCfgGrupoRiesgo cgruri ON cfari.pgimCfgGrupoRiesgo = cgruri "
            + "INNER JOIN PgimConfiguraRiesgo cori ON cgruri.pgimConfiguraRiesgo = cori "
            + "WHERE 1 = 1 "
            + "AND cfari.esRegistro = '1' "
            + "AND cgruri.esRegistro = '1' "
            + "AND cori.esRegistro = '1' "
            + "AND cgruri.idCfgGrupoRiesgo = :idCfgGrupoRiesgo "
            + "AND cgruri.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
            + ") "
            + "ORDER BY fari.noFactor "
			)
	List<PgimFactorRiesgoDTO> listarFactorRiesgoNotInCfgFactorRiesgo(@Param("idCfgGrupoRiesgo") Long idCfgGrupoRiesgo, 
			@Param("idGrupoRiesgo") Long idGrupoRiesgo, @Param("idEspecialidad") Long idEspecialidad);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTOResultado( "
            + "fari.idFactorRiesgo, fari.pgimGrupoRiesgo.idGrupoRiesgo, fari.pgimEspecialidad.idEspecialidad, "
            + "fari.tipoOrigenDatoRiesgo.idValorParametro, fari.noFactor, fari.deFactor "
            + ") "
            + "FROM PgimFactorRiesgo fari " 
            + "WHERE fari.esRegistro = '1' "
            + "AND fari.pgimGrupoRiesgo.idGrupoRiesgo = :idGrupoRiesgo "
            + "AND fari.pgimEspecialidad.idEspecialidad = :idEspecialidad "
			)
	List<PgimFactorRiesgoDTO> listarFactorRiesgo(@Param("idGrupoRiesgo") Long idGrupoRiesgo, 
			@Param("idEspecialidad") Long idEspecialidad);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTOResultado( "
			+ "fari.idFactorRiesgoAux, fari.idGrupoRiesgo, fari.idEspecialidad, fari.idTipoOrigenDatoRiesgo, fari.noFactor, fari.deFactor, "
			+ "fari.noGrupo, fari.noEspecialidad, fari.noValorParametro "
			+ ") "
            + "FROM PgimFactorRiesgoAux fari "  
            + "WHERE fari.esRegistro = '1' "
            
			+ "AND (:noFactor IS NULL OR LOWER(fari.noFactor) LIKE LOWER(CONCAT('%', :noFactor, '%')) ) "
			+ "AND (:idGrupoRiesgo IS NULL OR fari.idGrupoRiesgo = :idGrupoRiesgo ) "
            + "AND (:idEspecialidad IS NULL OR fari.idEspecialidad = :idEspecialidad ) "
            + "AND (:idTipoOrigenDatoRiesgo IS NULL OR fari.idTipoOrigenDatoRiesgo = :idTipoOrigenDatoRiesgo ) "
            
            + "AND (:textoBusqueda IS NULL OR ( "
            + "LOWER(fari.noFactor) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(fari.noGrupo) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(fari.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(fari.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + " ) ) ")
    Page<PgimFactorRiesgoDTO> listarFactorRiesgoBase(
    		@Param("noFactor") String noFactor,
    		@Param("idGrupoRiesgo") Long idGrupoRiesgo,
            @Param("idEspecialidad") Long idEspecialidad,
            @Param("idTipoOrigenDatoRiesgo") Long idTipoOrigenDatoRiesgo,
            @Param("textoBusqueda") String textoBusqueda,
            Pageable paginador);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTOResultado( "
			+ "fari.idFactorRiesgoAux, fari.idGrupoRiesgo, fari.idEspecialidad, fari.idTipoOrigenDatoRiesgo, fari.noFactor, fari.deFactor, "
			+ "fari.noGrupo, fari.noEspecialidad, fari.noValorParametro "
			+ ") "
            + "FROM PgimFactorRiesgoAux fari "  
            + "WHERE fari.esRegistro = '1' "
            + "AND fari.idFactorRiesgoAux = :idFactorRiesgo ")
    PgimFactorRiesgoDTO obtenerFactorRiesgoBasePorId(@Param("idFactorRiesgo") Long idFactorRiesgo);

}
