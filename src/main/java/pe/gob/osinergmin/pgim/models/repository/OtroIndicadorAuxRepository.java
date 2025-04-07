package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimOtroIndicadorAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimOtroIndicadorAux;

/** 
 * @descripción: Lógica de negocio de la entidad reporte de otros indicadores
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 09/10/2020
 * @fecha_de_ultima_actualización: 09/10/2020
*/
public interface OtroIndicadorAuxRepository extends JpaRepository<PgimOtroIndicadorAux, Long>{
    
    /**
     * Permite mostrar la lista de otros indicadores que está paginado y contiene filtros de busqueda mediante periodos
     * @param periodoInicial
     * @param periodoFinal
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOtroIndicadorAuxDTOResultado("
            + "aux.anioPro, aux.mes, aux.idCliente, aux.ruc, aux.titularMinero, aux.estrato, aux.codigo, aux.unidad, aux.proceso, "
            + "aux.concepto, aux.valor, aux.unidadMedida, aux.explicacion "
            + ") "
            + "FROM PgimOtroIndicadorAux aux "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(aux.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(aux.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:periodoInicial IS NULL OR (TRIM(aux.anioPro) || '/' || TRIM(aux.mes) BETWEEN :periodoInicial AND :periodoFinal)) " 
            + "ORDER BY TRIM(aux.anioPro) DESC, TRIM(aux.mes) DESC ")
    Page<PgimOtroIndicadorAuxDTO> listarOtrosIndicadores(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("periodoInicial") String periodoInicial, @Param("periodoFinal") String periodoFinal, Pageable paginador);
}
