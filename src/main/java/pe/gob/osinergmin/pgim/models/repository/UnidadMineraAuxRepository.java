package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimCopiaDetalleUmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMineraAux;

import java.util.List;

/**
 * En ésta interface UnidadMineraRepository esta conformado pos sus metodos de listar
 * las unidades mineras, aplicacion de los filtros por nombres de UM, 
 * obtener sus propiedades y la Existencia de UM.
 * 
 * @descripción: Lógica de negocio de la entidad Unidad minera
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface UnidadMineraAuxRepository extends JpaRepository<PgimUnidadMineraAux, Long> {
	
    /**
     * Permite listar las unidades mineras de acuerdo con los criterios filtro pre-establecidos.
     * @param noUnidadMinera
     * @param noRazonSocial
     * @param idSituacion
     * @param noUbigeo
     * @param descMoPuntaje
     * @param idDivisionSupervisora
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTOResultado("
                    + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, "
                    + "umin.coDocumentoIdentidad, umin.noRazonSocial, "
                    + "umin.noTipoActividad, umin.noDivisonSupervisora, "
                    + "umin.noTipoSituacion, umin.noTipoUnidadMinera, umin.nuCpcdadInstldaPlanta, "
                    + "umin.noUbigeo, "
                    + "umin.moPuntaje, umin.noRanking, umin.feGeneracionRanking, umin.idRankingRiesgo, "
                    + "umin.idInstanciaPasoRanking ) "
                    + "FROM PgimUnidadMineraAux umin "
                    + "WHERE 1 = 1 "
                    + "AND umin.idDivisionSupervisora = :idDivisionSupervisora "
                    + "AND (:noUnidadMinera IS NULL OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) )  "
                    + "AND (:noRazonSocial IS NULL OR LOWER(umin.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
                    + "AND (:idSituacion IS NULL OR umin.idSituacion = :idSituacion) "
                    + "AND (:noUbigeo IS NULL OR umin.noUbigeo = :noUbigeo) " 
                    + "AND (:descMoPuntaje IS NULL OR (CASE " 
                    + "WHEN umin.moPuntaje is null THEN 'No' " 
                    + "WHEN umin.moPuntaje > 0 THEN 'Sí' "
                    + "END = :descMoPuntaje)) "
                    + "")
    Page<PgimUnidadMineraAuxDTO> listar(@Param("noUnidadMinera") String noUnidadMinera
                                      , @Param("noRazonSocial") String noRazonSocial
                                      , @Param("idSituacion") Long idSituacion
                                      , @Param("noUbigeo") String noUbigeo
                                      , @Param("descMoPuntaje") String descMoPuntaje
                                      , @Param("idDivisionSupervisora") Long idDivisionSupervisora
                                      , Pageable paginador);
    
    /**
     * Permite listar las unidades mineras de acuerdo con los criterios filtro pre-establecidos;
     * entre otros, las que aún no pertenecen a un rankig de riesgo determinado
     * 
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param coDocumentoIdentidad
     * @param noRazonSocial
     * @param idSituacion
     * @param idTipoUnidadMinera
     * @param idDivisionSupervisora
     * @param idMetodoMinado
     * @param idTipoActividad
     * @param idEstadoUm
     * @param idRankingRiesgo
     * @param textoBusqueda
     * @param paginador
     * @return
     */
 
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTOResultado("
    		+ "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, umin.coDocumentoIdentidad, umin.noRazonSocial, " 
            + "umin.noTipoSituacion, umin.noTipoUnidadMinera, umin.noDivisonSupervisora, umin.noTipoActividad, "
            + "umin.coPlantaBeneficioDestino, umin.noPlantaBeneficioDestino, umin.noMetodoMinado, umin.noMetodoExplotacion, " 
            + "umin.indicioCamaraSubterranea, umin.nuProfundidad, umin.nuAlturaMaxima, umin.nuAlturaMinima, "
            + "umin.noTipoYacimiento, umin.mineralesSustancias, umin.requiereDatosRiesgo, umin.noSubactividad, umin.noEstadoUm, umin.idRankingRiesgo, "
            + "umin.idDivisionSupervisora, umin.idTipoActividad, umin.idSituacion, umin.idMetodoMinado, umin.idEstadoUm "
    		+ ") "
            + "FROM PgimUnidadMineraAux umin "
            + "WHERE "
            + "(:coUnidadMinera IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :coUnidadMinera, '%')) ) "
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
            + "AND umin.idUnidadMinera NOT IN (SELECT idUnidadMinera FROM PgimRankingUmAux WHERE idRankingRiesgo = :idRankingRiesgo) "
            )
    Page<PgimUnidadMineraAuxDTO> listarUmAuxDisponiblePorRankingRPaginado(@Param("coUnidadMinera") String coUnidadMinera,
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
            Pageable paginador);

    /**
     * Permite obtener la lista preparada de unidades mineras usado en reporte correspondiente de manera paginada
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTOResultado("
    		+ "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, umin.coDocumentoIdentidad, umin.noRazonSocial, " 
            + "umin.noTipoSituacion, umin.noTipoUnidadMinera, umin.noDivisonSupervisora, umin.noTipoActividad, "
            + "umin.coPlantaBeneficioDestino, umin.noPlantaBeneficioDestino, umin.noMetodoMinado, umin.noMetodoExplotacion, " 
            + "umin.indicioCamaraSubterranea, umin.nuProfundidad, umin.nuAlturaMaxima, umin.nuAlturaMinima, "
            + " umin.noTipoYacimiento, umin.mineralesSustancias, umin.requiereDatosRiesgo, umin.noSubactividad, umin.noEstadoUm, umin.idRankingRiesgo, "
            + "umin.idDivisionSupervisora, umin.idTipoActividad, umin.idSituacion, umin.idMetodoMinado, umin.idEstadoUm "
    		+ ") "
    		+ "FROM PgimUnidadMineraAux umin "
    		+ "WHERE 1 = 1 " )
    Page<PgimUnidadMineraAuxDTO> listarReporteUMPaginado(Pageable paginador);
    
    /**
     * Permite obtener la lista preparada de unidades mineras usado en reporte correspondiente
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTOResultado("
    		+ "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, umin.coDocumentoIdentidad, umin.noRazonSocial, " 
            + "umin.noTipoSituacion, umin.noTipoUnidadMinera, umin.noDivisonSupervisora, umin.noTipoActividad, "
    		+ "umin.coPlantaBeneficioDestino, umin.noPlantaBeneficioDestino, umin.noMetodoMinado, umin.noMetodoExplotacion, " 
            + "umin.indicioCamaraSubterranea, umin.nuProfundidad, umin.nuAlturaMaxima, umin.nuAlturaMinima, "
            + " umin.noTipoYacimiento, umin.mineralesSustancias, umin.requiereDatosRiesgo, umin.noSubactividad, umin.noEstadoUm, umin.idRankingRiesgo, "
            + "umin.idDivisionSupervisora, umin.idTipoActividad, umin.idSituacion, umin.idMetodoMinado, umin.idEstadoUm "
            + ") "
    		+ "FROM PgimUnidadMineraAux umin "
    		+ "WHERE 1 = 1 "
    		)
    List<PgimUnidadMineraAuxDTO> listarReporteUM(Sort sort);

    /**
     * Permite obtener la lista preparada el historial de cambios de la unidades mineras usado en reporte correspondiente
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCopiaDetalleUmDTOResultado("
    		+"um.idCopiaDetalleUm, um.pgimTrabajoCopiaUm.idTrabajoCopiaUm, um.pgimUnidadMinera.idUnidadMinera, um.pgimAgenteSupervisado.idAgenteSupervisado, "
    		+"um.divisionSupervisora.idValorParametro, um.situacion.idValorParametro, um.tipoUnidadMinera.idValorParametro, um.metodoMinado.idValorParametro, "
    		+"um.tipoSustancia.idValorParametro, um.tipoActividad.idValorParametro, um.rucAgenteSupervisado, um.razonSocialAs, "
    		+"um.coUnidadMinera, um.noUnidadMinera, um.coAnonimizacion, um.nuCpcdadInstldaPlanta, "
    		+"um.divisionSupervisora.noValorParametro, um.situacion.noValorParametro, um.tipoUnidadMinera.noValorParametro, "
    		+"um.metodoMinado.noValorParametro, um.tipoSustancia.noValorParametro, um.tipoActividad.noValorParametro, "
    		+ "um.coUnidadMineraPlanta, um.noUnidadMineraPlanta, um.feInicioTitularidad, "
    		+ "CASE month(um.pgimTrabajoCopiaUm.feCopiaUm) "
            + 	"WHEN 1 THEN CONCAT('Enero ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 2 THEN CONCAT('Febrero ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 3 THEN CONCAT('Marzo ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 4 THEN CONCAT('Abril ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 5 THEN CONCAT('Mayo ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 6 THEN CONCAT('Junio ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 7 THEN CONCAT('Julio ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 8 THEN CONCAT('Agosto ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 9 THEN CONCAT('Septiembre ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 10 THEN CONCAT('Octubre ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 11 THEN CONCAT('Noviembre ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"WHEN 12 THEN CONCAT('Diciembre ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
            + 	"ELSE ' ' "
            + "END "
    		+ ") "
    		+ "FROM PgimCopiaDetalleUm um "
    		+ "WHERE um.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
    		+ "ORDER BY um.pgimTrabajoCopiaUm.feCopiaUm desc " 
    		)
    Page<PgimCopiaDetalleUmDTO> listarHistoricoUMPaginado( @Param("idUnidadMinera") Long idUnidadMinera,
    		Pageable paginador );

    /**
     * Permite obtener la lista preparada el historial de cambios de la unidades mineras usado en reporte correspondiente
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCopiaDetalleUmDTOResultado("
    		+"um.idCopiaDetalleUm, um.pgimTrabajoCopiaUm.idTrabajoCopiaUm, um.pgimUnidadMinera.idUnidadMinera, um.pgimAgenteSupervisado.idAgenteSupervisado, "
    		+"um.divisionSupervisora.idValorParametro, um.situacion.idValorParametro, um.tipoUnidadMinera.idValorParametro, um.metodoMinado.idValorParametro, "
    		+"um.tipoSustancia.idValorParametro, um.tipoActividad.idValorParametro, um.rucAgenteSupervisado, um.razonSocialAs, "
    		+"um.coUnidadMinera, um.noUnidadMinera, um.coAnonimizacion, um.nuCpcdadInstldaPlanta, "
    		+"um.divisionSupervisora.noValorParametro, um.situacion.noValorParametro, um.tipoUnidadMinera.noValorParametro, "
    		+"um.metodoMinado.noValorParametro, um.tipoSustancia.noValorParametro, um.tipoActividad.noValorParametro, "
    		+ "um.coUnidadMineraPlanta, um.noUnidadMineraPlanta, um.feInicioTitularidad, "
    		+ "CASE month(um.pgimTrabajoCopiaUm.feCopiaUm) "
    		+ 	"WHEN 1 THEN CONCAT('Enero ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 2 THEN CONCAT('Febrero ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 3 THEN CONCAT('Marzo ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 4 THEN CONCAT('Abril ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 5 THEN CONCAT('Mayo ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 6 THEN CONCAT('Junio ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 7 THEN CONCAT('Julio ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 8 THEN CONCAT('Agosto ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 9 THEN CONCAT('Septiembre ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 10 THEN CONCAT('Octubre ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 11 THEN CONCAT('Noviembre ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"WHEN 12 THEN CONCAT('Diciembre ', year(um.pgimTrabajoCopiaUm.feCopiaUm)) "
    		+ 	"ELSE ' ' "
    		+ "END "
    		+ ") "
    		+ "FROM PgimCopiaDetalleUm um "
    		+ "WHERE um.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
    		+ "ORDER BY um.pgimTrabajoCopiaUm.feCopiaUm desc " 
    		)
    List<PgimCopiaDetalleUmDTO> listarHistoricoUMReporte( @Param("idUnidadMinera") Long idUnidadMinera);
    

}
