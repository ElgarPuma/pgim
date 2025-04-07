package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;

import java.util.List;

/**
 * En ésta interface UnidadMineraRepository esta conformado pos sus metodos de
 * listar
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
public interface UnidadMineraRepository extends JpaRepository<PgimUnidadMinera, Long> {

        /**
         * Permite listar las unidades mineras de acuerdo con los criterios filtro
         * pre-establecidos.
         * 
         * @param coUnidadMinera
         * @param noUnidadMinera
         * @param coDocumentoIdentidad
         * @param noRazonSocial
         * @param idSituacion
         * @param idTipoUnidadMinera
         * @param idDivisionSupervisora
         * @param textoBusqueda
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, "
                        + "pers.coDocumentoIdentidad, pers.noRazonSocial, "
                        + "tact.noValorParametro, umin.divisionSupervisora.noValorParametro, "
                        + "sit.noValorParametro, tum.noValorParametro, "
                        + "met.noValorParametro, umin.estadoUm.noValorParametro) "
                        + "FROM PgimUnidadMinera umin, PgimAgenteSupervisado agsu, PgimPersona pers "
                        + "LEFT JOIN umin.tipoActividad tact "
                        + "LEFT JOIN umin.situacion sit "
                        + "LEFT JOIN umin.tipoUnidadMinera tum "
                        + "LEFT JOIN umin.metodoMinado met "
                        + "WHERE umin.esRegistro = '1' AND umin.pgimAgenteSupervisado = agsu AND agsu.pgimPersona = pers "
                        + "AND (:coUnidadMinera IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :coUnidadMinera, '%')) ) "
                        + "AND (:noUnidadMinera IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
                        + "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
                        + "AND (:coDocumentoIdentidad IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
                        + "AND (:noRazonSocial IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
                        + "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
                        + "AND (:idSituacion IS NULL OR sit.idValorParametro = :idSituacion) "
                        + "AND (:idTipoUnidadMinera IS NULL OR tum.idValorParametro = :idTipoUnidadMinera) "
                        + "AND (:idDivisionSupervisora IS NULL OR umin.divisionSupervisora.idValorParametro = :idDivisionSupervisora) "
                        + "AND (:idMetodoMinado IS NULL OR met.idValorParametro = :idMetodoMinado) "
                        + "AND (:idTipoActividad IS NULL OR tact.idValorParametro = :idTipoActividad) "
                        + "AND (:idEstadoUm IS NULL OR umin.estadoUm.idValorParametro = :idEstadoUm) "
                        + "AND (:textoBusqueda IS NULL OR ( "
                        + "LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(tact.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
                        + "OR LOWER(umin.divisionSupervisora.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
                        + "OR LOWER(sit.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
                        + "OR LOWER(tum.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
                        + "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) ))")
        Page<PgimUnidadMineraDTO> listar(@Param("coUnidadMinera") String coUnidadMinera,
                        @Param("noUnidadMinera") String noUnidadMinera,
                        @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
                        @Param("noRazonSocial") String noRazonSocial, @Param("idSituacion") Long idSituacion,
                        @Param("idTipoUnidadMinera") Long idTipoUnidadMinera,
                        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
                        @Param("idMetodoMinado") Long idMetodoMinado, 
                        @Param("idTipoActividad") Long idTipoActividad,
                        @Param("idEstadoUm") Long idEstadoUm,
                        @Param("textoBusqueda") String textoBusqueda,
                        Pageable paginador);

        /***
         * Permite obtener la lista de unidades mineras que coinciden con la palabra
         * clave.
         * 
         * @param palabraClave
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, "
                        + "umin.pgimAgenteSupervisado.pgimPersona.coDocumentoIdentidad, "
                        + "umin.pgimAgenteSupervisado.pgimPersona.noRazonSocial "
                        + ") "
                        + "FROM PgimUnidadMinera umin "
                        + "WHERE umin.esRegistro = '1' AND (:palabraClave IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                        + "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) ORDER BY umin.noUnidadMinera")
        List<PgimUnidadMineraDTO> listarPorPalabraClave(@Param("palabraClave") String palabraClave);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, "
                + "umin.pgimAgenteSupervisado.pgimPersona.coDocumentoIdentidad, "
                + "umin.pgimAgenteSupervisado.pgimPersona.noRazonSocial "
                + ") "
                + "FROM PgimUnidadMinera umin "
                + "WHERE 1 = 1 "
                + "AND umin.esRegistro = '1' "
                + "AND (:idAgenteSupervisado IS NULL OR (umin.pgimAgenteSupervisado.idAgenteSupervisado = :idAgenteSupervisado)) "
                + "AND (:palabraClave IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                + "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) "
                + "ORDER BY umin.noUnidadMinera")
        List<PgimUnidadMineraDTO> listarPorPalabraClaveYAs(@Param("palabraClave") String palabra,
                @Param("idAgenteSupervisado") Long idAgenteSupervisado);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera) "
                        + "FROM PgimUnidadMinera umin "
                        + "WHERE umin.divisionSupervisora.idValorParametro = :idDivisionSupervisora "
                        + "AND umin.esRegistro = '1' AND (:palabraClave IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                        + "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) ORDER BY umin.noUnidadMinera")
        List<PgimUnidadMineraDTO> listarPorPalabraClaveYDs(@Param("palabraClave") String palabra,
                        @Param("idDivisionSupervisora") Long idDivisionSupervisora);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera) "
                        + "FROM PgimUnidadMinera umin "
                        + "WHERE umin.tipoUnidadMinera.coClaveTexto = :UNMIN_CONCSION_BNFCIO "
                        + "AND umin.esRegistro = '1' AND (:palabraClave IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                        + "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) ORDER BY umin.noUnidadMinera")
        List<PgimUnidadMineraDTO> listarPlntasPorPalabraClave(@Param("palabraClave") String palabra, @Param("UNMIN_CONCSION_BNFCIO") String UNMIN_CONCSION_BNFCIO);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera) "
                        + "FROM PgimUnidadMinera umin "
                        + "WHERE umin.esRegistro = '1' AND (:idUnidadMinera IS NULL OR umin.idUnidadMinera != :idUnidadMinera)  "
                        + "AND LOWER(umin.coUnidadMinera) = LOWER(:coUnidadMinera)")
        List<PgimUnidadMineraDTO> existeUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera,
                        @Param("coUnidadMinera") String coUnidadMinera);

        /***
         * Permite recuperar la unidad minera que tiene el identificador pasado como
         * parámetro.
         * 
         * @param idUnidadMinera
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, "
                        + "umin.pgimAgenteSupervisado.idAgenteSupervisado, umin.pgimAgenteSupervisado.pgimPersona.coDocumentoIdentidad, " 
                        + "umin.pgimAgenteSupervisado.pgimPersona.noRazonSocial, umin.feInicioTitularidad, umin.tipoActividad.noValorParametro, "
                        + "umin.divisionSupervisora.noValorParametro, umin.situacion.noValorParametro, umin.tipoUnidadMinera.noValorParametro, "
                        + "umin.coAnonimizacion, umin.divisionSupervisora.idValorParametro, umin.situacion.idValorParametro, "
                        + "umin.tipoActividad.idValorParametro, umin.tipoUnidadMinera.idValorParametro, umin.nuCpcdadInstldaPlanta, "
                        + "met.idValorParametro, met.noValorParametro, meex.idMetodoExplotacion, umin.flCmraSubtrraneaGas, "
                        + "umin.nuProfundidad, umin.nuAlturaMinima, umin.nuAlturaMaxima, "
                        + "tiya.idValorParametro, umin.tipoSustancia.idValorParametro, umin.plntaBeneficioDestino.idUnidadMinera, "
                        + "plbd.noUnidadMinera, umin.deUbicacionAcceso, umin.esRegistro, "
                        + "umin.flRegistraRiesgos, sub.idSubactividad, est.idValorParametro, umin.flConPiques, " 
                        + "( "
                        + "SELECT count(1) FROM PgimComponenteMinero comp "
                                +"WHERE comp.pgimUnidadMinera.idUnidadMinera = umin.idUnidadMinera " 
                                + "AND comp.esRegistro = '1' " 
                                + "AND comp.tipoComponenteMinero.idValorParametro = 466 "
                                + "AND comp.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
                        + ") "
                        + ") "
                        + "FROM PgimUnidadMinera umin " 
                        + "LEFT OUTER JOIN umin.pgimMtdoExplotacion meex " 
                        + "LEFT OUTER JOIN umin.tipoYacimiento tiya "
                        + "LEFT OUTER JOIN umin.plntaBeneficioDestino plbd "
                        + "LEFT OUTER JOIN umin.metodoMinado met " 
                        + "LEFT OUTER JOIN umin.pgimSubactividad sub " 
                        + "LEFT OUTER JOIN umin.estadoUm est " 
                        + "WHERE umin.idUnidadMinera = :idUnidadMinera")
        PgimUnidadMineraDTO obtenerUnidadMineraPorId(@Param("idUnidadMinera") Long idUnidadMinera);

        /**
         * Permite listar las unidades mineras de uno o mas agentes supervisados de
         * acuerdo con los criterios filtro pre-establecidos.
         * 
         * @param idAgenteSupervisado
         * @param coUnidadMinera
         * @param noUnidadMinera
         * @param coDocumentoIdentidad
         * @param noRazonSocial
         * @param idSituacion
         * @param idTipoUnidadMinera
         * @param idDivisionSupervisora
         * @param textoBusqueda
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, "
                        + "pers.coDocumentoIdentidad, pers.noRazonSocial, "
                        + "tiac.noValorParametro, disu.noValorParametro, "
                        + "stc.noValorParametro, tium.noValorParametro, "
                        + "memi.noValorParametro, esum.noValorParametro) "
                        + "FROM PgimUnidadMinera umin "
                        + "INNER JOIN umin.pgimAgenteSupervisado agsu " 
                        + "INNER JOIN agsu.pgimPersona pers "
                        + "LEFT OUTER JOIN umin.tipoActividad tiac "
                        + "LEFT OUTER JOIN umin.divisionSupervisora disu "
                        + "LEFT OUTER JOIN umin.situacion stc "
                        + "LEFT OUTER JOIN umin.tipoUnidadMinera tium "
                        + "LEFT OUTER JOIN umin.metodoMinado memi "
                        + "LEFT OUTER JOIN umin.estadoUm esum "
                        + "WHERE umin.esRegistro = '1' AND umin.pgimAgenteSupervisado = agsu AND agsu.pgimPersona = pers "
                        + "AND agsu.idAgenteSupervisado = :idAgenteSupervisado "
                        + "AND (:coUnidadMinera IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :coUnidadMinera, '%')) ) "
                        + "AND (:noUnidadMinera IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
                        + "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
                        + "AND (:coDocumentoIdentidad IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
                        + "AND (:noRazonSocial IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
                        + "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
                        + "AND (:idSituacion IS NULL OR stc.idValorParametro = :idSituacion) "
                        + "AND (:idTipoUnidadMinera IS NULL OR tium.idValorParametro = :idTipoUnidadMinera) "
                        + "AND (:idDivisionSupervisora IS NULL OR disu.idValorParametro = :idDivisionSupervisora) "
                        + "AND (:textoBusqueda IS NULL OR ( "
                        + "LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(tiac.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
                        + "OR LOWER(disu.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
                        + "OR LOWER(stc.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
                        + "OR LOWER(tium.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
                        + "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) ))")
        Page<PgimUnidadMineraDTO> listarUnidadesMinerasAS(@Param("idAgenteSupervisado") Long idAgenteSupervisado,
                        @Param("coUnidadMinera") String coUnidadMinera, @Param("noUnidadMinera") String noUnidadMinera,
                        @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
                        @Param("noRazonSocial") String noRazonSocial, @Param("idSituacion") Long idSituacion,
                        @Param("idTipoUnidadMinera") Long idTipoUnidadMinera,
                        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
                        @Param("textoBusqueda") String textoBusqueda, Pageable paginador);

        /**
         * Permite obtener las unidades mineras que cumplen con al menos una regla de configuración dada o que aún cuando no las cumplan
         * se obtengan debido a que sí fueron registradas bajo la configuración de riesgo en cuestión.
         * @param idSituacion
         * @param idDivisionSupervisora
         * @param idMetodoMinado
         * @param idTipoActividad
         * @param idEstado
         * @param idConfiguraRiesgo
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera) "
                        + "FROM PgimUnidadMinera umin "
                        + "WHERE 1 = 1 "
                        + "AND NOT EXISTS ("
                        + "SELECT 1 "
                        + "FROM PgimRankingUm raum "
                        + "     INNER JOIN raum.pgimRankingRiesgo rari "
                        + "WHERE 1 = 1 "
                        + "AND raum.pgimUnidadMinera.idUnidadMinera = umin.idUnidadMinera "
                        + "AND raum.esRegistro = '1'  "
                        + "AND rari.idRankingRiesgo = :idRankingRiesgo  "
                        + ") "
                        + "AND ("
                        + "( "
                        + "umin.flRegistraRiesgos = '1' "
                        + "AND (:idDivisionSupervisora IS NULL OR umin.divisionSupervisora.idValorParametro = :idDivisionSupervisora) "
                        + "AND (:idSituacion IS NULL OR umin.situacion.idValorParametro = :idSituacion) "
                        + "AND (:idMetodoMinado IS NULL OR umin.metodoMinado.idValorParametro = :idMetodoMinado) "
                        + "AND (:idTipoActividad IS NULL OR umin.tipoActividad.idValorParametro = :idTipoActividad) "
                        + "AND (:idEstado IS NULL OR umin.estadoUm.idValorParametro = :idEstado) "
                        + ") "
                        + "OR EXISTS "
                        + "( "
                        + "SELECT 1 "
                        + "FROM PgimRankingSupervision rasu  "
                        + "     INNER JOIN rasu.pgimConfiguraRiesgo cfgr  "
                        + "     INNER JOIN rasu.pgimSupervision supe  "
                        + "     INNER JOIN supe.pgimUnidadMinera unmii  "
                        + "WHERE cfgr.idConfiguraRiesgo = :idConfiguraRiesgo  "
                        + "AND supe.flRegistraRiesgos = '1'  "
                        + "AND unmii.idUnidadMinera = umin.idUnidadMinera "
                        + ") "
                        + ") "
                        + "AND umin.esRegistro = '1' "
                        + "ORDER BY umin.noUnidadMinera")
        List<PgimUnidadMineraDTO> listarPorConfiguraRegla(
                        @Param("idSituacion") Long idSituacion,
                        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
                        @Param("idMetodoMinado") Long idMetodoMinado,
                        @Param("idTipoActividad") Long idTipoActividad,
                        @Param("idEstado") Long idEstado,
                        @Param("idConfiguraRiesgo") Long idConfiguraRiesgo,
                        @Param("idRankingRiesgo") Long idRankingRiesgo);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, "
                        + "umin.pgimAgenteSupervisado.pgimEstrato.noEstrato, disup.noValorParametro, "
                        + "tium.noValorParametro, situ.noValorParametro, pbede.noUnidadMinera, "
                        + "memi.noValorParametro, meex.noMetodoExplotacion, umin.flCmraSubtrraneaGas, tiya.noValorParametro, "
                        + "tisu.noValorParametro, umin.nuProfundidad, umin.nuAlturaMinima, umin.nuAlturaMaxima, "
                        + "umin.pgimAgenteSupervisado.idAgenteSupervisado, tium.idValorParametro, umin.deUbicacionAcceso "
                        + ") "
                        + "FROM PgimUnidadMinera umin "
                        + "LEFT JOIN PgimValorParametro disup ON disup = umin.divisionSupervisora "
                        + "LEFT JOIN PgimValorParametro tium ON tium = umin.tipoUnidadMinera "
                        + "LEFT JOIN PgimValorParametro situ ON situ = umin.situacion "
                        + "LEFT JOIN PgimUnidadMinera pbede ON umin.plntaBeneficioDestino = pbede "
                        + "LEFT JOIN PgimValorParametro memi ON umin.metodoMinado = memi "
                        + "LEFT JOIN PgimMtdoExplotacion meex ON umin.pgimMtdoExplotacion = meex "
                        + "LEFT JOIN PgimValorParametro tiya ON umin.tipoYacimiento = tiya "
                        + "LEFT JOIN PgimValorParametro tisu ON umin.tipoSustancia = tisu "
                        + "WHERE umin.idUnidadMinera = :idUnidadMinera")
        PgimUnidadMineraDTO obtenerFichaInformativaUM(@Param("idUnidadMinera") Long idUnidadMinera);

        /***
         * Permite obtener la lista de unidades mineras que coinciden con la palabra
         * clave.
         * 
         * @param palabraClave
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                        + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera) "
                        + "FROM PgimUnidadMinera umin "
                        + "WHERE (:palabraClave IS NULL OR LOWER(umin.coUnidadMinera) = LOWER(:palabraClave)) ")
        PgimUnidadMineraDTO obtenerUnidadMineraPorCod(@Param("palabraClave") String palabraClave);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado( "
                        + "umin.idUnidadMinera ) "
                        + "FROM PgimUnidadMinera umin "
                        + "WHERE umin.esRegistro = '1' "
                        + "AND umin.pgimAgenteSupervisado.idAgenteSupervisado = :idAgenteSupervisado ")
        List<PgimUnidadMineraDTO> listarUnidadMineraPorAgenteSupervisado(
                        @Param("idAgenteSupervisado") Long idAgenteSupervisado);

        List<PgimUnidadMinera> findByEsRegistro(String esRegistro);
        
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                + "umin.idUnidadMinera, umin.coUnidadMinera, umin.noUnidadMinera, "
                + "umin.pgimAgenteSupervisado.idAgenteSupervisado, umin.pgimAgenteSupervisado.pgimPersona.coDocumentoIdentidad, umin.pgimAgenteSupervisado.pgimPersona.noRazonSocial, umin.feInicioTitularidad, umin.tipoActividad.noValorParametro, "
                + "umin.divisionSupervisora.noValorParametro, umin.situacion.noValorParametro, umin.tipoUnidadMinera.noValorParametro, "
                + "umin.coAnonimizacion, umin.divisionSupervisora.idValorParametro, umin.situacion.idValorParametro, "
                + "umin.tipoActividad.idValorParametro, umin.tipoUnidadMinera.idValorParametro, umin.nuCpcdadInstldaPlanta, "
                + "met.idValorParametro, met.noValorParametro, meex.idMetodoExplotacion, umin.flCmraSubtrraneaGas, "
                + "umin.nuProfundidad, umin.nuAlturaMinima, umin.nuAlturaMaxima, "
                + "tiya.idValorParametro, umin.tipoSustancia.idValorParametro, umin.plntaBeneficioDestino.idUnidadMinera, "
                + "plbd.noUnidadMinera, umin.deUbicacionAcceso, umin.esRegistro, "
                + "umin.flRegistraRiesgos, sub.idSubactividad, est.idValorParametro, umin.flConPiques, " 
                + "( "
                + "SELECT count(1) FROM PgimComponenteMinero comp "
                        +"WHERE comp.pgimUnidadMinera.idUnidadMinera = umin.idUnidadMinera " 
                        + "AND comp.esRegistro = '1' " 
                        + "AND comp.tipoComponenteMinero.idValorParametro = 466L "
                + ") "
                + ") "
                + "FROM PgimUnidadMinera umin " 
                + "LEFT OUTER JOIN umin.pgimMtdoExplotacion meex " 
                + "LEFT OUTER JOIN umin.tipoYacimiento tiya "
                + "LEFT OUTER JOIN umin.plntaBeneficioDestino plbd "
                + "LEFT OUTER JOIN umin.metodoMinado met " 
                + "LEFT OUTER JOIN umin.pgimSubactividad sub " 
                + "LEFT OUTER JOIN umin.estadoUm est "
                + "WHERE umin.coUnidadMinera = :coUm")
        PgimUnidadMineraDTO obtenerUmPorCodigo(@Param("coUm") String coUm);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTOResultado("
                + "plb.idUnidadMinera, plb.coUnidadMinera, plb.noUnidadMinera, "
                + "pers.coDocumentoIdentidad, pers.noRazonSocial, "
                + "tact.noValorParametro, plb.divisionSupervisora.noValorParametro, "
                + "sit.noValorParametro, tum.noValorParametro, "
                + "met.noValorParametro, plb.estadoUm.noValorParametro) "
                + "FROM PgimUnidadMinera um, PgimAgenteSupervisado agsu, PgimPersona pers "
                + "LEFT JOIN PgimUnidadMinera plb ON plb.plntaBeneficioDestino.idUnidadMinera = um.idUnidadMinera "
                + "LEFT JOIN plb.tipoActividad tact "
                + "LEFT JOIN plb.situacion sit "
                + "LEFT JOIN plb.tipoUnidadMinera tum "
                + "LEFT JOIN plb.metodoMinado met "
                + "WHERE plb.esRegistro = '1' AND um.esRegistro = '1' " 
                + "AND plb.pgimAgenteSupervisado = agsu " 
                + "AND agsu.pgimPersona = pers "
                + "AND (:idPlntaBeneficioDestino IS NULL OR plb.plntaBeneficioDestino.idUnidadMinera = :idPlntaBeneficioDestino) "
                )
        List<PgimUnidadMineraDTO> listarUmAsociadas(@Param("idPlntaBeneficioDestino") Long idPlntaBeneficioDestino);
}
