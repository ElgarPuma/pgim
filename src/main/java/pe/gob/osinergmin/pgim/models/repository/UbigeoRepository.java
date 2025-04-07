package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimUbigeo;

/**
 * En ésta interface UbigeoRepository esta conformado por sus metodos de listar por 
 * filtros por departamento,provincia o distrito.
 * 
 * @descripción: Lógica de negocio de la entidad Ubigeo
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 19/05/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface UbigeoRepository extends JpaRepository<PgimUbigeo, Long> {

    /***
     * Permite obtener la lista de ubigeos que coinciden con la palabra clave.
     * 
     * @param palabraClave
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTOResultado("
            + "ubdi.idUbigeo, ubde.noUbigeo || ', ' || ubpr.noUbigeo || ', ' || ubdi.noUbigeo) "
            + "FROM PgimUbigeo ubdi "
            + "   , PgimUbigeo ubpr "
            + "   , PgimUbigeo ubde "
            + "WHERE ubdi.esRegistro = '1' AND ubpr.esRegistro = '1' AND ubde.esRegistro = '1' "
            + "AND ubdi.coUbigeo NOT LIKE '%0000' AND ubdi.coUbigeo NOT LIKE '%00'"
            + "AND ubpr.coUbigeo LIKE '%00' "
            + "AND ubde.coUbigeo LIKE '%0000' "
            + "AND SUBSTR(ubdi.coUbigeo, 0, 4) || '00' = ubpr.coUbigeo "
            + "AND SUBSTR(ubdi.coUbigeo, 0, 2) || '0000' = ubde.coUbigeo "
            + "AND (:palabraClave IS NULL " 
            + "OR LOWER(ubde.noUbigeo) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + "OR LOWER(ubpr.noUbigeo) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + "OR LOWER(ubdi.noUbigeo) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) "            
            + "ORDER BY ubde.noUbigeo, ubpr.noUbigeo, ubdi.noUbigeo")
    List<PgimUbigeoDTO> listarPorPalabraClave(@Param("palabraClave") String palabraClave);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTOResultado( "
			+ " u.idUbigeo, "
			+ " (SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(u.coUbigeo,0,2)||'0000')||'-'||(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(u.coUbigeo,0,4)||'00')||'-'||TRIM(u.noUbigeo) )"
			+ " FROM PgimUbigeo u " 
			+ " WHERE u.coUbigeo NOT LIKE '%0000' "
			+ " AND u.coUbigeo NOT LIKE '%00' "
			+ " AND (:palabraClave is null OR LOWER( (SELECT TRIM(x.noUbigeo) from PgimUbigeo x where x.coUbigeo = substr(u.coUbigeo,0,2)||'0000')||'-'||(SELECT TRIM(y.noUbigeo) from PgimUbigeo y where y.coUbigeo = substr(u.coUbigeo,0,4)||'00')||'-'||TRIM(u.noUbigeo) )  LIKE LOWER(CONCAT('%',:palabraClave, '%')) ) ")
	public List<PgimUbigeoDTO> listarPorPalabraClaveAlternativo(@Param("palabraClave") String palabraClave);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTOResultado("
    		+ "ubdi.noUbigeo, ubpr.noUbigeo, ubde.noUbigeo "
            + ") "
            + "FROM PgimSupervision supe " 
            + "LEFT JOIN PgimDemarcacionUminera dum ON supe.pgimUnidadMinera.idUnidadMinera = dum.pgimUnidadMinera.idUnidadMinera "
            + "LEFT JOIN PgimUbigeo ubdi ON dum.pgimUbigeo.idUbigeo = ubdi.idUbigeo "
            + "LEFT JOIN PgimUbigeo ubpr ON SUBSTR(ubdi.coUbigeo, 0, 4) || '00' = ubpr.coUbigeo "
            + "LEFT JOIN PgimUbigeo ubde ON SUBSTR(ubdi.coUbigeo, 0, 2) || '0000' = ubde.coUbigeo "
            + "WHERE supe.esRegistro = '1' "
            + "AND supe.idSupervision = :idSupervision ")
    List<PgimUbigeoDTO> obtenerUbigeoPorIdSupervision(@Param("idSupervision") Long idSupervision);

}
