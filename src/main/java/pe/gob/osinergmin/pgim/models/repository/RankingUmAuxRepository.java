package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUmAux;

/**
 * Éste interface ItemSolicitudDocRepository
 * que aplica obtener y listar Ranking de unidades mineras.
 * 
 * @descripción: Logica de negocio de la entidad Ranking de unidades mineras.
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface RankingUmAuxRepository extends JpaRepository<PgimRankingUmAux, Long> {
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTOResultado( "
            + "prgaux.idRankingUmAux, prgaux.pgimRankingUm.idRankingUm, prgaux.idRankingRiesgo, " 
            + " prgaux.idUnidadMinera, prgaux.coUnidadMinera, prgaux.noUnidadMinera, " 
            + "prgaux.coAnonimizacion, prgaux.puntajeTecnico, prgaux.puntajeGestion, prgaux.puntajeGeneral, " 
            + "prgaux.nroTecnicoPendiente, prgaux.nroGestionPendiente, "
            + "CASE "
            + " WHEN prgaux.nroTecnicoPendiente > 0 AND prgaux.nroGestionPendiente > 0 THEN "
            + "         'No' "
            + " ELSE 'Sí' "
            + "END, "
            + "prgaux.coTipoInclusionRanking, prgaux.noTipoInclusionRanking, "
            + "prgaux.nuCalificacionTecnico, prgaux.nuCalificacionGestion, "
            + "prgaux.flActivo " 
            + ") "
            + "FROM PgimRankingUmAux prgaux " 
            + "WHERE prgaux.idRankingRiesgo = :idRankingRiesgo ")
    Page<PgimRankingUmAuxDTO> listarRankingUm(@Param("idRankingRiesgo") Long idRankingRiesgo, Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTOResultado( "
            + "prgaux.idRankingUmAux, prgaux.pgimRankingUm.idRankingUm, prgaux.idRankingRiesgo, " 
            + "prgaux.idUnidadMinera, prgaux.coUnidadMinera, prgaux.noUnidadMinera, " 
            + "prgaux.coAnonimizacion, prgaux.puntajeTecnico, prgaux.puntajeGestion, "
            + "prgaux.puntajeGeneral, prgaux.nroTecnicoPendiente, prgaux.nroGestionPendiente, "
            + "CASE "
            + " WHEN prgaux.nroTecnicoPendiente > 0 AND prgaux.nroGestionPendiente > 0 THEN "
            + "         'No' "
            + " ELSE 'Sí' "
            + "END, "
            + "prgaux.coTipoInclusionRanking, prgaux.noTipoInclusionRanking, "
            + "prgaux.nuCalificacionTecnico, prgaux.nuCalificacionGestion, "
            + "prgaux.flActivo " 
            + ") "
            + "FROM PgimRankingUmAux prgaux "
            + "LEFT JOIN PgimUnidadMineraAux umin ON umin.idUnidadMinera = prgaux.idUnidadMinera "
            + "WHERE prgaux.idRankingRiesgo = :idRankingRiesgo "
            + "AND prgaux.flActivo = :flActivo "
			+ "AND (:coUnidadMinera IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :coUnidadMinera, '%')) ) "
			+ "AND (:noUnidadMinera IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
			+ "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
			+ "AND (:coDocumentoIdentidad IS NULL OR LOWER(umin.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
			+ "AND (:noRazonSocial IS NULL OR LOWER(umin.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
			+ "OR LOWER(umin.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
			+ "AND (:idSituacion IS NULL OR umin.idSituacion = :idSituacion) "
			+ "AND (:idTipoUnidadMinera IS NULL OR umin.idTipoUnidadMinera = :idTipoUnidadMinera) "
			+ "AND (:idDivisionSupervisora IS NULL OR umin.idDivisionSupervisora = :idDivisionSupervisora) "
			+ "AND (:idMetodoMinado IS NULL OR umin.idMetodoMinado = :idMetodoMinado) "
			+ "AND (:idTipoActividad IS NULL OR umin.idTipoActividad = :idTipoActividad) "
			+ "AND (:idEstadoUm IS NULL OR umin.idEstadoUm = :idEstadoUm) "
			+ "AND (:textoBusqueda IS NULL OR ( "
			+ "LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(umin.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
			+ "OR LOWER(umin.noTipoActividad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(umin.noDivisonSupervisora) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(umin.noTipoSituacion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(umin.noTipoUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(umin.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) )) "
            )
    Page<PgimRankingUmAuxDTO> listarRankingUmPorFiltroUM(@Param("coUnidadMinera") String coUnidadMinera,
            @Param("noUnidadMinera") String noUnidadMinera,
            @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
            @Param("noRazonSocial") String noRazonSocial, 
            @Param("idSituacion") Long idSituacion,
            @Param("idTipoUnidadMinera") Long idTipoUnidadMinera,
            @Param("idDivisionSupervisora") Long idDivisionSupervisora,
            @Param("idMetodoMinado") Long idMetodoMinado, 
            @Param("idTipoActividad") Long idTipoActividad,
            @Param("idEstadoUm") Long idEstadoUm,
            @Param("idRankingRiesgo") Long idRankingRiesgo,
            @Param("textoBusqueda") String textoBusqueda,
            @Param("flActivo") String flActivo,
            Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTOResultado( "
            + "prgaux.pgimRankingUm.idRankingUm, prgaux.idRankingRiesgo, " 
            + " prgaux.idUnidadMinera, prgaux.coUnidadMinera, prgaux.noUnidadMinera, " 
            + "prgaux.coAnonimizacion, prgaux.puntajeTecnico, prgaux.puntajeGestion, prgaux.puntajeGeneral, " 
            + "prgaux.nroTecnicoPendiente, prgaux.nroGestionPendiente, "
            + "CASE "
            + " WHEN prgaux.nroTecnicoPendiente > 0 AND prgaux.nroGestionPendiente > 0 THEN "
            + "         'No'"
            + " ELSE 'Sí' "
            + "END, "
            + "prgaux.nuCalificacionTecnico, prgaux.nuCalificacionGestion "
            + ") "
            + "FROM PgimRankingUmAux prgaux " 
            + "WHERE prgaux.idRankingRiesgo = :idRankingRiesgo "
            + "AND prgaux.flActivo = '1' "
            + "ORDER BY prgaux.noUnidadMinera ASC")
    List<PgimRankingUmAuxDTO> listarRankingUmByIdRanking(@Param("idRankingRiesgo") Long idRankingRiesgo);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTOResultado( "
            + "prgaux.pgimRankingUm.idRankingUm, prgaux.idRankingRiesgo, " 
            + " prgaux.idUnidadMinera, prgaux.coUnidadMinera, prgaux.coAnonimizacion, " 
            + "prgaux.coAnonimizacion, prgaux.puntajeTecnico, prgaux.puntajeGestion, prgaux.puntajeGeneral, " 
            + "prgaux.nroTecnicoPendiente, prgaux.nroGestionPendiente, "
            + "CASE "
            + " WHEN prgaux.nroTecnicoPendiente > 0 AND prgaux.nroGestionPendiente > 0 THEN "
            + "         'No' "
            + " ELSE "
            + "         'Sí' "
            + " END, "
            + "prgaux.nuCalificacionTecnico, prgaux.nuCalificacionGestion "
            + ") "
            + "FROM PgimRankingUmAux prgaux " 
            + "WHERE prgaux.idRankingRiesgo = :idRankingRiesgo "
            + "AND prgaux.flActivo = '1' "
            + "ORDER BY prgaux.noUnidadMinera ASC")
    List<PgimRankingUmAuxDTO> listarRankingUmPorAnonimizacion(@Param("idRankingRiesgo") Long idRankingRiesgo);
    
}
