package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado;
import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDemarcacionUminera;

/**
 * Ésta interface DemarcacionUMineraRepository que permitira listar, 
 * obtener y validar la demarcacion de la unidad minera y el ubigeo
 * 
 * @descripción: Logica de negocio de la entidad Demarcación
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface DemarcacionUMineraRepository extends JpaRepository<PgimDemarcacionUminera, Long>{

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTOResultado( "
			+ " u.idUbigeo, "
			+ " (SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(u.coUbigeo,0,2)||'0000')||', '||(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(u.coUbigeo,0,4)||'00')||', '||TRIM(u.noUbigeo) )"
			+ " FROM PgimUbigeo u " 
			+ " WHERE u.coUbigeo NOT LIKE '%0000' "
			+ " AND u.coUbigeo NOT LIKE '%00' "
			+ " AND (:noUbigeo is null OR LOWER( (SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(u.coUbigeo,0,2)||'0000')||', '||(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(u.coUbigeo,0,4)||'00')||', '||TRIM(u.noUbigeo) )  LIKE LOWER(CONCAT('%',:noUbigeo, '%')) ) ")
	public List<PgimUbigeoDTO> getUbigeoPorNombre(@Param("noUbigeo") String noUbigeo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTOResultado( "
			+ " u.idUbigeo, "
			+ " (SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(u.coUbigeo,0,2)||'0000')||', '||(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(u.coUbigeo,0,4)||'00')||', '||TRIM(u.noUbigeo) )"
			+ " FROM PgimUbigeo u " 
			+ " WHERE u.coUbigeo NOT LIKE '%0000' "
			+ " AND u.coUbigeo NOT LIKE '%00' ")
	public List<PgimUbigeoDTO> getUbigeo();
	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado(" + 
			"	dem.idDemarcacionUm, dem.pgimUbigeo.idUbigeo," + 
			"	dem.pgimUnidadMinera.idUnidadMinera, dem.flPrincipal, dem.pcUbigeo, dem.esRegistro," + 
			"	dem.usCreacion,dem.ipCreacion,dem.feCreacion,dem.usActualizacion," + 
			"	dem.ipActualizacion,dem.feActualizacion," + 
			"	(SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(ubi.coUbigeo,0,2)||'0000'), " + 
			"	(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(ubi.coUbigeo,0,4)||'00'), " + 
			"	TRIM(ubi.noUbigeo)) " + 
			"	FROM PgimDemarcacionUminera dem" + 
			"	INNER JOIN PgimUbigeo ubi on dem.pgimUbigeo.idUbigeo = ubi.idUbigeo" + 
			"	WHERE 1 = 1" + 
			" 	AND (:idDemarcacionUm IS NULL OR ( dem.idDemarcacionUm = :idDemarcacionUm )   )")
	public PgimDemarcacionUmineraDTO getDemarcacionById(@Param("idDemarcacionUm") Long idDemarcacionUm);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado( "
			+ "e.idDemarcacionUm,e.pgimUbigeo.idUbigeo, e.pgimUnidadMinera.idUnidadMinera"
			+ ", e.flPrincipal, e.pcUbigeo"
			+ ", TRIM(e.pgimUbigeo.noUbigeo),"
			+ "(select TRIM(noUbigeo) from PgimUbigeo where coUbigeo = "
			+ "(select substr(coUbigeo,1,4)||'00'from PgimUbigeo where idUbigeo=e.pgimUbigeo.idUbigeo))"
			+ ",(select TRIM(noUbigeo) from PgimUbigeo where coUbigeo = "
			+ "(select substr(coUbigeo,1,2)||'0000'from PgimUbigeo where idUbigeo=e.pgimUbigeo.idUbigeo))"
			+ ") FROM PgimDemarcacionUminera e "
			+ "WHERE e.pgimUnidadMinera.idUnidadMinera = ?1 "
			+ "AND e.esRegistro = '1' ")			
	public List<PgimDemarcacionUmineraDTOResultado> findByUm(Long id);
	
	@Query("SELECT e FROM PgimDemarcacionUminera e "
			+ "WHERE e.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
			+ "AND e.esRegistro = '1' ")
	public List<PgimDemarcacionUminera> getDemarcacionByidUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado("
	        + " dem.idDemarcacionUm, dem.pgimUbigeo.idUbigeo, dem.pgimUnidadMinera.idUnidadMinera, "
	        + "	(SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(ubi.coUbigeo,0,2)||'0000'), "
			+ "	(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(ubi.coUbigeo,0,4)||'00'), "
			+ "	TRIM(ubi.noUbigeo)) " 
	        + " FROM PgimDemarcacionUminera dem "
	        + "	INNER JOIN PgimUbigeo ubi on dem.pgimUbigeo.idUbigeo = ubi.idUbigeo" 
	        + " WHERE dem.esRegistro = '1' "
	        + " AND (:idUnidadMinera IS NULL OR dem.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera) "
	        + " AND (:idDemarcacionUm IS NULL OR dem.idDemarcacionUm <> :idDemarcacionUm) "
			/*+ " AND (:noUbigeo is null OR LOWER( (SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(ubi.coUbigeo,0,2)||'0000')||','||(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(ubi.coUbigeo,0,4)||'00')||','||TRIM(ubi.noUbigeo) )  LIKE LOWER(:noUbigeo) ) ")*/
			+ " AND (:idUbigeo IS NULL OR dem.pgimUbigeo.idUbigeo = :idUbigeo ) ")
	public List<PgimDemarcacionUmineraDTO> existeDemarcacionUMinera(@Param("idUnidadMinera") Long idUnidadMinera, @Param("idUbigeo") Long idUbigeo, @Param("idDemarcacionUm") Long idDemarcacionUm);

	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado("
	        + " SUM(dem.pcUbigeo) ) "
	        + " FROM PgimDemarcacionUminera dem "
	        + " WHERE dem.esRegistro = '1' "
	        + " AND (:idUnidadMinera IS NULL OR dem.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera) "
			+ " AND (:idDemarcacionUm IS NULL OR dem.idDemarcacionUm <> :idDemarcacionUm) ")
	public PgimDemarcacionUmineraDTO excedeLimiteDemarcacionUMinera(@Param("idUnidadMinera") Long idUnidadMinera, @Param("idDemarcacionUm") Long idDemarcacionUm);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado(" + 
			"	dem.idDemarcacionUm, dem.pgimUbigeo.idUbigeo," + 
			"	dem.pgimUnidadMinera.idUnidadMinera, dem.flPrincipal, dem.pcUbigeo, dem.esRegistro," + 
			"	dem.usCreacion,dem.ipCreacion,dem.feCreacion,dem.usActualizacion," + 
			"	dem.ipActualizacion,dem.feActualizacion," + 
			"	(SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(ubi.coUbigeo,0,2)||'0000'), " + 
			"	(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(ubi.coUbigeo,0,4)||'00'), " + 
			"	TRIM(ubi.noUbigeo)) " + 
			"	FROM PgimDemarcacionUminera dem" + 
			"	INNER JOIN PgimUbigeo ubi on dem.pgimUbigeo.idUbigeo = ubi.idUbigeo" + 
			"	WHERE 1 = 1" + 
			" 	AND dem.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera ")
	public List<PgimDemarcacionUmineraDTO> getDemarcacionUMByIdUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
	
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDemarcacionAuxDTOResultado( "
    		+ "dem.idDemarcacionUmAux, dem.idDemarcacionUm, dem.idAgenteSupervisado, dem.coDocumentoIdentidadAf, dem.noRazonSocialAf, dem.idUnidadMinera, "
    		+ "dem.coUnidadMinera, dem.noUnidadMinera, dem.idTipoUnidadMinera, dem.noTipoUnidadMinera, dem.idDivisionSupervisora, dem.noDivisionSupervisora, "
    		+ "dem.idDepartamentoDemarcacion, dem.idProvinciaDemarcacion, dem.idDistritoDemarcacion, dem.noDepartamentoDemarcacion, dem.noProvinciaDemarcacion, "
    		+ "dem.noDistritoDemarcacion, dem.flPrincipalDemarcacion, dem.principalDemarcacion, dem.pcDemarcacion, "
    		+ "dem.coDocumentoIdentidadAf || ' - ' || dem.noRazonSocialAf, dem.coUnidadMinera || ' - ' || dem.noUnidadMinera, "
			+ "CASE "
			+ "     WHEN dem.idDemarcacionUm IS NOT NULL THEN "
			+ "         (dem.noDepartamentoDemarcacion || ', ' || dem.noProvinciaDemarcacion || ', ' || dem.noDistritoDemarcacion) "
			+ "     ELSE "
			+ "         '' "
			+ "END "
    		+ ") "
    		+ "FROM PgimDemarcacionAux dem "
    		+ "WHERE "
    		+ "(:coUnidadMinera IS NULL OR LOWER(dem.coUnidadMinera) LIKE LOWER(CONCAT('%', :coUnidadMinera, '%')) ) "
    		+ "AND (:noUnidadMinera IS NULL OR LOWER(dem.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
    		+ "OR LOWER(dem.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
    		+ "AND (:coDocumentoIdentidad IS NULL OR LOWER(dem.coDocumentoIdentidadAf) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
    		+ "AND (:noRazonSocial IS NULL OR LOWER(dem.coDocumentoIdentidadAf) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
    		+ "OR LOWER(dem.noRazonSocialAf) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
    		+ "AND (:idTipoUnidadMinera IS NULL OR dem.idTipoUnidadMinera = :idTipoUnidadMinera) "
    		+ "AND (:idDivisionSupervisora IS NULL OR dem.idDemarcacionUm = :idDivisionSupervisora) "
            + "AND (:descFlDemarcacion IS NULL OR (dem.idDemarcacionUm IS NOT NULL AND :descFlDemarcacion = '1' ) OR (dem.idDemarcacionUm IS NULL AND :descFlDemarcacion = '0')) "
            + "AND (:flPrincipalDemarcacion IS NULL OR dem.flPrincipalDemarcacion = :flPrincipalDemarcacion) "
            + "AND (:descUbigeo is null OR LOWER( dem.noDepartamentoDemarcacion || ', ' || dem.noProvinciaDemarcacion || ', ' || dem.noDistritoDemarcacion )  LIKE LOWER(CONCAT('%',:descUbigeo, '%')))"
            + "AND (:idUbigeo IS NULL OR dem.idDistritoDemarcacion = :idUbigeo) "
            + "")
    Page<PgimDemarcacionAuxDTO> listarReporteDemarcacionesUMineraPaginado(@Param("coUnidadMinera") String coUnidadMinera,
            @Param("noUnidadMinera") String noUnidadMinera,
            @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
            @Param("noRazonSocial") String noRazonSocial,
            @Param("idTipoUnidadMinera") Long idTipoUnidadMinera,
            @Param("idDivisionSupervisora") Long idDivisionSupervisora,
            @Param("flPrincipalDemarcacion") String flPrincipalDemarcacion,
            @Param("descFlDemarcacion") String descFlDemarcacion,
            @Param("descUbigeo") String descUbigeo,
            @Param("idUbigeo") Long idUbigeo,
            Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDemarcacionAuxDTOResultado( "
    		+ "dem.idDemarcacionUmAux, dem.idDemarcacionUm, dem.idAgenteSupervisado, dem.coDocumentoIdentidadAf, dem.noRazonSocialAf, dem.idUnidadMinera, "
    		+ "dem.coUnidadMinera, dem.noUnidadMinera, dem.idTipoUnidadMinera, dem.noTipoUnidadMinera, dem.idDivisionSupervisora, dem.noDivisionSupervisora, "
    		+ "dem.idDepartamentoDemarcacion, dem.idProvinciaDemarcacion, dem.idDistritoDemarcacion, dem.noDepartamentoDemarcacion, dem.noProvinciaDemarcacion, "
    		+ "dem.noDistritoDemarcacion, dem.flPrincipalDemarcacion, dem.principalDemarcacion, dem.pcDemarcacion, "
    		+ "dem.coDocumentoIdentidadAf || ' - ' || dem.noRazonSocialAf, dem.coUnidadMinera || ' - ' || dem.noUnidadMinera, "
			+ "CASE "
			+ "     WHEN dem.idDemarcacionUm IS NOT NULL THEN "
			+ "         (dem.noDepartamentoDemarcacion || ', ' || dem.noProvinciaDemarcacion || ', ' || dem.noDistritoDemarcacion) "
			+ "     ELSE "
			+ "         '' "
			+ "END "
    		+ ") "
    		+ "FROM PgimDemarcacionAux dem "
    		+ "WHERE "
    		+ "(:coUnidadMinera IS NULL OR LOWER(dem.coUnidadMinera) LIKE LOWER(CONCAT('%', :coUnidadMinera, '%')) ) "
    		+ "AND (:noUnidadMinera IS NULL OR LOWER(dem.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
    		+ "OR LOWER(dem.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
    		+ "AND (:coDocumentoIdentidad IS NULL OR LOWER(dem.coDocumentoIdentidadAf) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
    		+ "AND (:noRazonSocial IS NULL OR LOWER(dem.coDocumentoIdentidadAf) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
    		+ "OR LOWER(dem.noRazonSocialAf) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
    		+ "AND (:idTipoUnidadMinera IS NULL OR dem.idTipoUnidadMinera = :idTipoUnidadMinera) "
    		+ "AND (:idDivisionSupervisora IS NULL OR dem.idDemarcacionUm = :idDivisionSupervisora) "
            + "AND (:descFlDemarcacion IS NULL OR (dem.idDemarcacionUm IS NOT NULL AND :descFlDemarcacion = '1' ) OR (dem.idDemarcacionUm IS NULL AND :descFlDemarcacion = '0')) "
            + "AND (:flPrincipalDemarcacion IS NULL OR dem.flPrincipalDemarcacion = :flPrincipalDemarcacion) "
            + "AND (:descUbigeo is null OR LOWER( dem.noDepartamentoDemarcacion || ', ' || dem.noProvinciaDemarcacion || ', ' || dem.noDistritoDemarcacion )  LIKE LOWER(CONCAT('%',:descUbigeo, '%')))"
            + "AND (:idUbigeo IS NULL OR dem.idDistritoDemarcacion = :idUbigeo) "
    		+ "")
    List<PgimDemarcacionAuxDTO> listarReporteDemarcacionesUMinera(@Param("coUnidadMinera") String coUnidadMinera,
    		@Param("noUnidadMinera") String noUnidadMinera,
    		@Param("coDocumentoIdentidad") String coDocumentoIdentidad,
    		@Param("noRazonSocial") String noRazonSocial,
    		@Param("idTipoUnidadMinera") Long idTipoUnidadMinera,
    		@Param("idDivisionSupervisora") Long idDivisionSupervisora,
            @Param("flPrincipalDemarcacion") String flPrincipalDemarcacion,
            @Param("descFlDemarcacion") String descFlDemarcacion,
            @Param("descUbigeo") String descUbigeo,
            @Param("idUbigeo") Long idUbigeo
    		);
    


}
