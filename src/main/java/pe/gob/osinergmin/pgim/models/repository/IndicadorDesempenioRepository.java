package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimIndicadorDesempenioAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIndicadorDesempenioAux;

/**
 * @descripción: Logica de negocio de la entidad MV_OSIN_IND_DESEMP_OPERAT
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 02/03/2023
 * @fecha_de_ultima_actualización: 
 */
public interface IndicadorDesempenioRepository extends JpaRepository<PgimIndicadorDesempenioAux, Long> {
    
    /**
     * Permite mostrar la lista de indicadores de desempeño operativo que está paginado y contiene filtros de busqueda mediante periodos
     * @param periodoInicial
     * @param periodoFinal
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorDesempenioAuxDTOResultado("
            + "aux.anioPro, aux.mes, aux.idCliente, aux.ruc, aux.titularMinero, aux.estrato, aux.codigo, aux.unidad, aux.coeficiente, "
            + "aux.unidadM, aux.descripcion, aux.cantidad, aux.explicacion "
            + ") "
            + "FROM PgimIndicadorDesempenioAux aux "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(aux.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(aux.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:periodoInicial IS NULL OR (TRIM(aux.anioPro) || '/' || TRIM(aux.mes) BETWEEN :periodoInicial AND :periodoFinal)) " 
            + "ORDER BY TRIM(aux.anioPro) DESC, TRIM(aux.mes) DESC ")
    Page<PgimIndicadorDesempenioAuxDTO> listarIndicadoresDesempenioOperativo(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("periodoInicial") String periodoInicial, @Param("periodoFinal") String periodoFinal, Pageable paginador);
}
