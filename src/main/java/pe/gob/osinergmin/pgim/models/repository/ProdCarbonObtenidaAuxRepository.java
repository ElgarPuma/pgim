package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimProdCarbonObtenidaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimProdCarbonObtenidaAux;

/**
 * @descripción: Logica de negocio de la Vista para obtener la lista de reporte de producción carbonífera
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface ProdCarbonObtenidaAuxRepository extends JpaRepository<PgimProdCarbonObtenidaAux, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProdCarbonObtenidaAuxDTOResultado("
            + "po.idCliente, po.anioPro, po.mes, po.ruc, po.titularMinero, po.estrato, po.categoria, po.codigo, po.unidad, "
            + "po.codigoIntegranteUea, po.unidadIntegrante, po.region, po.provincia, po.distrito, po.producto, po.idUnidadMedida, " 
            + "po.descripcion, po.cantidad "
            + ") "
            + "FROM PgimProdCarbonObtenidaAux po "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(po.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(po.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(po.anioPro) || '/' || TRIM(po.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(po.anioPro) DESC, TRIM(po.mes) DESC ")
    Page<PgimProdCarbonObtenidaAuxDTO> listarProdCarbonObtenida(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio,
            @Param("feFin") String feFin, Pageable paginador);

}
