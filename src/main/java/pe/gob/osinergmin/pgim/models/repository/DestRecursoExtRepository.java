package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimDestRecursoExtAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDestRecursoExtAux;

/**
 * Ésta interface cumple con los metodos de listar el reporte de destno de recursos extraidos y su exportación en excel.
 * 
 * @descripción: Lógica de negocio de la entidad o vista reporte de destino de recursos extraidos
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 20/12/2022
 */
public interface DestRecursoExtRepository extends JpaRepository<PgimDestRecursoExtAux, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestRecursoExtAuxDTOResultado("
            + "dmm.anioPro, dmm.mes, dmm.idCliente, dmm.ruc, dmm.titularMinero, dmm.estrato, dmm.codigo, dmm.unidad, dmm.recursoExtraido, "
            + "dmm.destino, dmm.codigoPlanta, dmm.nombrePlanta, dmm.pais, dmm.idUnidadMedida, dmm.descripcion, dmm.cantidadTm, dmm.pctCu, "
            + "dmm.pctPb, dmm.pctZn, dmm.agOzTc, dmm.auGrTm, dmm.pctFe, dmm.pctMo, dmm.pctSn, dmm.pctCd, dmm.pctWo3, dmm.pctSb, "
            + "dmm.pctAs, dmm.pctMn, dmm.pctBi, dmm.pcHg, dmm.pctIn, dmm.pctSe, dmm.pctTe, dmm.h2so4, "
            + "dmm.pctu, dmm.pctNi, dmm.pctMg "
            + ") "
            + "FROM PgimDestRecursoExtAux dmm "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(dmm.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(dmm.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(dmm.anioPro) || '/' || TRIM(dmm.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(dmm.anioPro) DESC, TRIM(dmm.mes) DESC")
    Page<PgimDestRecursoExtAuxDTO> ListarDestinoMineralesMetalicos(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestRecursoExtAuxDTOResultado("
            + "dmm.anioPro, dmm.mes, dmm.idCliente, dmm.ruc, dmm.titularMinero, dmm.estrato, dmm.codigo, dmm.unidad, dmm.recursoExtraido, "
            + "dmm.destino, dmm.codigoPlanta, dmm.nombrePlanta, dmm.pais, dmm.idUnidadMedida, dmm.descripcion, dmm.cantidadTm, dmm.pctCu, "
            + "dmm.pctPb, dmm.pctZn, dmm.agOzTc, dmm.auGrTm, dmm.pctFe, dmm.pctMo, dmm.pctSn, dmm.pctCd, dmm.pctWo3, dmm.pctSb, "
            + "dmm.pctAs, dmm.pctMn, dmm.pctBi, dmm.pcHg, dmm.pctIn, dmm.pctSe, dmm.pctTe, dmm.h2so4, "
            + "dmm.pctu, dmm.pctNi, dmm.pctMg "
            + ") "
            + "FROM PgimDestRecursoExtAux dmm "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(dmm.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(dmm.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(dmm.anioPro) || '/' || TRIM(dmm.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(dmm.anioPro) DESC, TRIM(dmm.mes) DESC")
    List<PgimDestRecursoExtAuxDTO> listarReporteDestinoMineralesMetalicos(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin);
}
