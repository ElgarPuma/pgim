package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimCarboniferaDestinoAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCarboniferaDestinoAux;

/**
 * @descripción: Logica de negocio de la Vista para obtener la lista de reporte de destino de la producción carbonífera
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface CarboniferaDestinoAuxRepository extends JpaRepository<PgimCarboniferaDestinoAux, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCarboniferaDestinoAuxDTOResultado("
            + "po.idCliente, po.anioPro, po.mes, po.ruc, po.titularMinero, po.estrato, po.codigo, po.unidad, "
            + "po.recursoExtraido, po.destino, po.pais, po.idUnidadMedida, po.descripcion, po.cantidad, po.valor, po.idMoneda "
            + ") "
            + "FROM PgimCarboniferaDestinoAux po "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(po.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(po.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(po.anioPro) || '/' || TRIM(po.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(po.anioPro) DESC, TRIM(po.mes) DESC ")
    Page<PgimCarboniferaDestinoAuxDTO> listarCarboniferaDestino(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio,
            @Param("feFin") String feFin, Pageable paginador);

}
