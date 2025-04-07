package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoParametroMed;

/**
 * PgimTipoParametroMedRepository incluye el método para listar en el maestro 
 * los parametros de medición para el tipo de instrumntos
 * 
 * @descripción: Lógica de negocio de la entidad PgimTipoParametroMed
 * 
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 19/08/2022
 */
@Repository
public interface PgimTipoParametroMedRepository extends JpaRepository<PgimTipoParametroMed, Long> {

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTOResultado(" 
	    + "tipo.idTipoParametroMed, tipo.coTipoParametro, tipo.noTipoParametro, "
        + "tipo.deTipoParametro, COUNT(pxi.idTprmtroXTinstrmnto) as numUsos ) "
        + "FROM PgimTipoParametroMed tipo  "
        + "LEFT JOIN PgimTprmtroXTinstrmnto pxi ON ( tipo.idTipoParametroMed = pxi.pgimTipoParametroMed.idTipoParametroMed AND pxi.esRegistro = '1' ) "
        + "WHERE tipo.esRegistro = '1'  "
        + "AND (:textoBusqueda IS NULL OR ( LOWER(tipo.coTipoParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
        + "OR LOWER(tipo.noTipoParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
        + ")) "
        + "GROUP BY (tipo.idTipoParametroMed, tipo.coTipoParametro, tipo.noTipoParametro, tipo.deTipoParametro) "
        )
    Page<PgimTipoParametroMedDTO> listarParamMedicion(
        @Param("textoBusqueda") String textoBusqueda,
        Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTOResultado(" 
	    + "tipo.idTipoParametroMed, tipo.coTipoParametro, tipo.noTipoParametro, "
        + "tipo.deTipoParametro ) " 
        + "FROM PgimTipoParametroMed tipo "
        + "WHERE tipo.esRegistro = '1'  "
        + "AND tipo.idTipoParametroMed = :idTipoParametroMed"
        )
    PgimTipoParametroMedDTO obtenerIdParamMedicion(
        @Param("idTipoParametroMed") Long idTipoParametroMed);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTOResultado(" 
	    + "tipo.idTipoParametroMed, tipo.coTipoParametro, tipo.noTipoParametro, "
        + "tipo.deTipoParametro ) " 
        + "FROM PgimTipoParametroMed tipo "
        + "WHERE tipo.esRegistro = '1'  "
        + "AND LOWER(tipo.noTipoParametro) LIKE LOWER(:noTipoParametro)"
        )
    PgimTipoParametroMedDTO obtenerNombreParamMedicion(
        @Param("noTipoParametro") String noTipoParametro);

    @Query("SELECT max(tipo.coTipoParametro) "
        + "FROM PgimTipoParametroMed tipo "
        + "WHERE tipo.esRegistro = '1'  "
        + "ORDER BY tipo.idTipoParametroMed desc"
        )
    String obtenerUtimoCoorelativoTP();

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTOResultado(" 
	    + "tipo.idTipoParametroMed, tipo.coTipoParametro, tipo.noTipoParametro, "
        + "tipo.deTipoParametro ) " 
        + "FROM PgimTipoParametroMed tipo "
        + "WHERE tipo.esRegistro = '0'  "
        + "AND tipo.idTipoParametroMed = :idTipoParametroMed"
        )
    PgimTipoParametroMedDTO obtenerIdParamMedicionElimado(
        @Param("idTipoParametroMed") Long idTipoParametroMed);
    
    
}
