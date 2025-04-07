package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimIdentUnidMineraAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIdentUnidMineraAux;

/**
 * @descripción: Logica de negocio de la Vista para obtener la lista de reporte de identificación de concesiones y/o UEA
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface IdentUnidMineraAuxRepository extends JpaRepository<PgimIdentUnidMineraAux, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIdentUnidMineraAuxDTOResultado("
            + "ig.idCliente, ig.anioPro, ig.mes, ig.ruc, ig.titularMinero, ig.estrato, ig.codigo, ig.unidad, ig.idClaseSustancia, "
            + "ig.idSituacionup, ig.desSituacionup " 
            + ") "
            + "FROM PgimIdentUnidMineraAux ig "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ig.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ig.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(ig.anioPro) || '/' || TRIM(ig.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(ig.anioPro) DESC, TRIM(ig.mes) DESC ")
    Page<PgimIdentUnidMineraAuxDTO> listarIdentUnidMineras(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin, Pageable paginador);

}
