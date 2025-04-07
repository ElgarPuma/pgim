package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimMineralRecibPlantaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMineralRecibPlantaAux;

/**
 * @descripción: Logica de negocio de la Vista para obtener la lista de reporte de mineral recibido en planta
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface MineralRecibPlantaAuxRepository extends JpaRepository<PgimMineralRecibPlantaAux, Long> {
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMineralRecibPlantaAuxDTOResultado("
            + "po.anioPro, po.mes, po.idCliente, po.ruc, po.titularMinero, po.estrato, po.codigo, po.unidad, po.proceso, "
            + "po.region, po.procedencia, po.obtenidoDe, po.unidadObtenido, po.rucProveedor, po.nombreProveedor, po.producto, po.idUnidadMedida, " 
            + "po.descripcion, po.cantidadRecibida, po.cantidadProcesada, po.pctCu, "
            + "po.pctPb, po.pctZn, po.agOzTc, po.auGrTm, po.pctFe, po.pctMo, po.pctSn, po.pctCd, po.pctWo3, po.pctSb, "
            + "po.pctAs, po.pctMn, po.pctBi, po.pcHg, po.pctIn, po.pctSe, po.pctTe, po.h2so4, "
            + "po.u, po.pctNi, po.pctMg "
            + ") "
            + "FROM PgimMineralRecibPlantaAux po "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(po.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(po.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(po.anioPro) || '/' || TRIM(po.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(po.anioPro) DESC, TRIM(po.mes) DESC ")
    Page<PgimMineralRecibPlantaAuxDTO> listarMineralRecibPlanta(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin, Pageable paginador);

}
