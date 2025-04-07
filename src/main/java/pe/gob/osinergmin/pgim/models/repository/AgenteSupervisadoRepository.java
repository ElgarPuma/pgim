package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;

import java.util.List;

/**
 * Ésta interface AgenteSupervisadoRepository incluye los metodos de obtener,filtrar, paginar
 * listar agentes supervisados.
 * 
 * @descripción: Lógica de negocio de la entidad Agente supervisado
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/06/2020
 */
@Repository
public interface AgenteSupervisadoRepository extends JpaRepository<PgimAgenteSupervisado, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado("
            + "agsu.idAgenteSupervisado, agsu.pgimPersona.coDocumentoIdentidad, agsu.pgimPersona.noRazonSocial, "
            + "(SELECT pe.noEstrato FROM PgimEstrato pe where pe.idEstrato = agsu.pgimEstrato.idEstrato), "
            + "(SELECT NVL(SUM(pum.nuCpcdadInstldaPlanta),0) FROM PgimUnidadMinera pum where pum.tipoUnidadMinera.idValorParametro = :idTipoUnidadMinera AND pum.pgimAgenteSupervisado.idAgenteSupervisado = agsu.idAgenteSupervisado) ) "
            + "FROM PgimAgenteSupervisado agsu " + "WHERE agsu.idAgenteSupervisado = :idAgenteSupervisado")
    PgimAgenteSupervisadoDTO obtenerAgenteSupervisado(@Param("idAgenteSupervisado") Long idAgenteSupervisado,
            @Param("idTipoUnidadMinera") Long idTipoUnidadMinera);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado("
            + "agsu.idAgenteSupervisado, pers.coDocumentoIdentidad, pers.noRazonSocial, pers.idPersona) "
            + "FROM PgimAgenteSupervisado agsu, PgimPersona pers WHERE agsu.esRegistro = '1' AND agsu.pgimPersona = pers "
            + "AND (?1 IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', ?1, '%'))  "
            + "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', ?1, '%')) ) ")
    List<PgimAgenteSupervisadoDTO> listarPorPalabraClave(String palabraClave);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado("
            + "pas.idAgenteSupervisado, pas.pgimPersona.idPersona, pp.coDocumentoIdentidad, "
            + "pp.noRazonSocial, pas.pgimEstrato.idEstrato, pas.tamanioEmpresa.idValorParametro, "
            + "pp.deTelefono, pp.deTelefono2, pp.deCorreo, pp.deCorreo2, pp.diPersona, " + "pp.pgimUbigeo.idUbigeo, "
            + "(SELECT NVL(SUM(pum.nuCpcdadInstldaPlanta),0) FROM PgimUnidadMinera pum where pum.tipoUnidadMinera.idValorParametro = :idTipoUnidadMinera AND pum.pgimAgenteSupervisado.idAgenteSupervisado = pas.idAgenteSupervisado), "
            + " (SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(ubi.coUbigeo,0,2)||'0000')||', '||(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(ubi.coUbigeo,0,4)||'00')||', '||TRIM(ubi.noUbigeo), "
            + "pp.flAfiliadoNtfccionElctrnca,pp.feAfiliadoDesde,pp.deCorreoNtfccionElctrnca,pp.noCorto,pas.dePrincipalActividadEcnmca )"
            + "FROM PgimAgenteSupervisado pas "
            + "INNER JOIN PgimPersona pp on pas.pgimPersona.idPersona = pp.idPersona "
            + "INNER JOIN PgimUbigeo ubi on pp.pgimUbigeo.idUbigeo = ubi.idUbigeo " + "WHERE pas.esRegistro = '1' "
            + "AND pas.idAgenteSupervisado = :idAgenteSupervisado ")
    PgimAgenteSupervisadoDTO obtenerAgenteSupervisadoPorId(@Param("idAgenteSupervisado") Long idAgenteSupervisado,
            @Param("idTipoUnidadMinera") Long idTipoUnidadMinera);

    /**
     * Permite listar los agentes supervisados de acuerdo con los criterios filtro
     * pre-establecidos.
     * 
     * @param coDocumentoIdentidad
     * @param noRazonSocial
     * @param textoBusqueda
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado( "
            + "agsu.idAgenteSupervisado, agsu.pgimPersona.noRazonSocial, agsu.pgimPersona.coDocumentoIdentidad, "
            + "agsu.pgimEstrato.noEstrato, agsu.tamanioEmpresa.noValorParametro, agsu.pgimPersona.deTelefono, "
            + "agsu.pgimPersona.deTelefono2, agsu.pgimPersona.deCorreo, agsu.pgimPersona.deCorreo2, agsu_aux.nuCantidadUniMinera, agsu_aux.nuCapacidadTotalInstalada )  "
            + "FROM PgimAgenteSupervisadoAux agsu_aux INNER JOIN agsu_aux.pgimAgenteSupervisado agsu  "
            + "WHERE agsu.esRegistro = '1' "
            + "AND (:coDocumentoIdentidad IS NULL OR LOWER(agsu.pgimPersona.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
            + "AND (:noRazonSocial IS NULL OR LOWER(agsu.pgimPersona.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
            + "OR LOWER(agsu.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
            + "AND (:idEstrato IS NULL OR agsu.pgimEstrato.idEstrato = :idEstrato) "
            + "AND (:textoBusqueda IS NULL OR ( "
            + "LOWER(agsu.pgimPersona.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(agsu.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(agsu.pgimEstrato.noEstrato) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) ) )  ")
    Page<PgimAgenteSupervisadoDTO> listar(@Param("coDocumentoIdentidad") String coDocumentoIdentidad,
            @Param("noRazonSocial") String noRazonSocial, @Param("idEstrato") Long idEstrato,
            @Param("textoBusqueda") String textoBusqueda, Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado("
            + "pas.idAgenteSupervisado, pas.pgimPersona.coDocumentoIdentidad, pas.pgimPersona.noRazonSocial, pas.pgimPersona.idPersona ) "
            + "FROM PgimAgenteSupervisado pas "
            + "WHERE pas.esRegistro = '1' "
            + "AND (:idAgenteSupervisado IS NULL OR pas.idAgenteSupervisado != :idAgenteSupervisado) "
            + "AND LOWER(pas.pgimPersona.coDocumentoIdentidad) = LOWER(:coDocumentoIdentidad)")
    List<PgimAgenteSupervisadoDTO> existeAgenteSupervisado(@Param("idAgenteSupervisado") Long idAgenteSupervisado, 
    		@Param("coDocumentoIdentidad") String coDocumentoIdentidad);
    
    
    /**
     * Permite obtener la lista preparada de agentes supervisadas usado en reporte correspondiente
     * Se utiliza el metodo listar para el paginado
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado( "
    		+ "agsu.idAgenteSupervisado, agsu.pgimPersona.coDocumentoIdentidad, agsu.pgimPersona.noRazonSocial, agsu.tamanioEmpresa.noValorParametro ) "
    		+ "FROM PgimAgenteSupervisadoAux agsu_aux INNER JOIN agsu_aux.pgimAgenteSupervisado agsu "
    		+ "WHERE agsu.esRegistro = '1' " )
    List<PgimAgenteSupervisadoDTO> listarReporteAS(Sort sort);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado("
            + "agsu.idAgenteSupervisado, pers.coDocumentoIdentidad, pers.noRazonSocial, pers.idPersona ) "
            + "FROM PgimAgenteSupervisado agsu, PgimPersona pers WHERE agsu.pgimPersona = pers "
            + "AND (?1 IS NULL OR LOWER(pers.coDocumentoIdentidad) = LOWER(?1)) " )
    PgimAgenteSupervisadoDTO obtenerAgenteSupervisadoPorRuc(String palabraClave);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado( "
    		+ "agsu.idAgenteSupervisado) "
    		+ "FROM PgimAgenteSupervisado agsu "
    		+ "WHERE agsu.esRegistro = '1' "
    		+ "AND agsu.pgimPersona.idPersona = :idPersona " )
    List<PgimAgenteSupervisadoDTO> listarAgenteSupervisadoPorPersona(@Param("idPersona") Long idPersona);
    
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado("
            + "agsu.idAgenteSupervisado, pers.coDocumentoIdentidad, pers.noRazonSocial, pers.idPersona "
            + ") "
            + "FROM PgimAgenteSupervisado agsu "
            + "INNER JOIN agsu.pgimPersona pers "
            + "WHERE 1=1 "
            + "AND agsu.idAgenteSupervisado = :idAgenteSupervisado" )
    PgimAgenteSupervisadoDTO obtenerAgenteSupervisadoPorIdAs(@Param("idAgenteSupervisado") Long idAgenteSupervisado);
    
    /**
     * Permite obtener el agente fiscalizado de determinada unidad minera.
     * 
     * @param idUnidadMinera
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTOResultado("
            + "agsu.idAgenteSupervisado, pers.coDocumentoIdentidad, pers.noRazonSocial, pers.idPersona "
            + ") "
            + "FROM PgimUnidadMinera um "
            + "INNER JOIN um.pgimAgenteSupervisado agsu "
            + "INNER JOIN agsu.pgimPersona pers "
            + "WHERE 1=1 "
            + "AND um.idUnidadMinera = :idUnidadMinera")
    PgimAgenteSupervisadoDTO obtenerAgenteSupervisadoPorIdUm(@Param("idUnidadMinera") Long idUnidadMinera);

}