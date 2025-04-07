package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimProduccionObtAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimProduccionObtAux;

/**
 * Ésta interface cumple con los metodos de listar el reporte de producción obtenida y su exportación en excel.
 * 
 * @descripción: Lógica de negocio de la entidad o vista reporte de producción obtenida
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 20/12/2022
 */
public interface ProduccionObtRepository extends JpaRepository<PgimProduccionObtAux, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProduccionObtAuxDTOResultado("
            + "po.anioPro, po.mes, po.idCliente, po.ruc, po.titularMinero, po.estrato, po.codigo, po.unidad, po.proceso, "
            + "po.region, po.procedencia, po.nombreVendedor, po.producto, po.ratio, po.idUnidadMedida, po.descripcion, po.cantidad, po.pctCu, "
            + "po.pctPb, po.pctZn, po.agOzTc, po.auGrTm, po.pctFe, po.pctMo, po.pctSn, po.pctCd, po.pctWo3, po.pctSb, "
            + "po.pctAs, po.pctMn, po.pctBi, po.pcHg, po.pctIn, po.pctSe, po.pctTe, po.h2so4, "
            + "po.pctu, po.pctNi, po.pctMg "
            + ") "
            + "FROM PgimProduccionObtAux po "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(po.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(po.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(po.anioPro) || '/' || TRIM(po.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(po.anioPro) DESC, TRIM(po.mes) DESC ")
    Page<PgimProduccionObtAuxDTO> ListarProduccionObtenida(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProduccionObtAuxDTOResultado("
            + "po.anioPro, po.mes, po.idCliente, po.ruc, po.titularMinero, po.estrato, po.codigo, po.unidad, po.proceso, "
            + "po.region, po.procedencia, po.nombreVendedor, po.producto, po.ratio, po.idUnidadMedida, po.descripcion, po.cantidad, po.pctCu, "
            + "po.pctPb, po.pctZn, po.agOzTc, po.auGrTm, po.pctFe, po.pctMo, po.pctSn, po.pctCd, po.pctWo3, po.pctSb, "
            + "po.pctAs, po.pctMn, po.pctBi, po.pcHg, po.pctIn, po.pctSe, po.pctTe, po.h2so4, "
            + "po.pctu, po.pctNi, po.pctMg "
            + ") "
            + "FROM PgimProduccionObtAux po "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(po.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(po.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(po.anioPro) || '/' || TRIM(po.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(po.anioPro) DESC, TRIM(po.mes) DESC ")
    List<PgimProduccionObtAuxDTO> listarReporteProduccionObtenida(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProduccionObtAuxDTOResultado("
            + "po.producto, po.anioPro, po.mes, po.proceso, po.cantidad, po.descripcion "
            + ") "
            + "FROM PgimProduccionObtAux po "
            + "WHERE 1 = 1 "
            + "AND UPPER(po.codigo) LIKE UPPER(:coUnidadMinera) "
            + "AND (TRIM(po.anioPro) || '/' || TRIM(po.mes) BETWEEN :feInicio AND :feFin) ORDER BY TRIM(po.producto) ASC, TRIM(po.anioPro) DESC, TRIM(po.mes) DESC ")
    List<PgimProduccionObtAuxDTO> listarProduccionObtenidaUM(@Param("coUnidadMinera") String coUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin);
}
