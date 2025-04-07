package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimUnidadOrganicaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadOrganica;

/**
 * Tiene como nombre Unidad orgánica.
 * 
 * @descripción: Logica de negocio de la entidad Unidad orgánica. 
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 20/01/2023
 * @fecha_de_ultima_actualización: 20/01/2023
 */
public interface UnidadOrganicaRepository extends JpaRepository<PgimUnidadOrganica, Long> {
    
    /**
     * Me permite listar las unidades orgánicas
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadOrganicaDTOResultado("
            + "uo.idUnidadOrganica, uo.coUnidadOrganica, uo.noUnidadOrganica, uo.coUsuarioSigedNumerador, uo.noUsuarioSigedNumerador "
            + ") "
            + "FROM PgimUnidadOrganica uo "
            + "WHERE uo.esRegistro = '1' "
            + "ORDER BY uo.idUnidadOrganica ASC "
            )
    List<PgimUnidadOrganicaDTO> listarUnidadOrganica();

    /**
     * Me permite obtener la unidad organica mediante su codigo
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimUnidadOrganicaDTOResultado("
            + "uo.idUnidadOrganica, uo.coUnidadOrganica, uo.noUnidadOrganica, uo.coUsuarioSigedNumerador, uo.noUsuarioSigedNumerador "
            + ") "
            + "FROM PgimUnidadOrganica uo "
            + "WHERE uo.esRegistro = '1' "
            + "AND uo.coUnidadOrganica = :coUnidadOrganica "
            )
    PgimUnidadOrganicaDTO obtenerUnidadOrganicaPorCod(@Param("coUnidadOrganica") String coUnidadOrganica);
}
