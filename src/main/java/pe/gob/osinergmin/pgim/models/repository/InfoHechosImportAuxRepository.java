package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimInfoHechosImportAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInfoHechosImportAux;

/**
 * @descripción: Logica de negocio de la Vista para obtener la lista de reporte de información de hechos de importancia
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface InfoHechosImportAuxRepository extends JpaRepository<PgimInfoHechosImportAux, Long> {
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfoHechosImportAuxDTOResultado("
            + "po.idCliente, po.anioPro, po.mes, po.ruc, po.titularMinero, po.estrato, po.hechosAfectanDeclaracion, po.infoGestionSocial "
            + ") "
            + "FROM PgimInfoHechosImportAux po "
            + "WHERE 1 = 1 "
            + "AND (:feInicio IS NULL OR (TRIM(po.anioPro) || '/' || TRIM(po.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(po.anioPro) DESC, TRIM(po.mes) DESC ")
    Page<PgimInfoHechosImportAuxDTO> listarInfoHechosImport(@Param("feInicio") String feInicio, @Param("feFin") String feFin, Pageable paginador);
}
