package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimProduccionNoMetalicaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimProduccionNoMetalicaAux;

public interface ProduccionNoMetalicaRepository extends JpaRepository<PgimProduccionNoMetalicaAux, Long>{
    
    /**
     * Permite mostrar la lista de pruducción no metálica que está paginado y contiene filtros de busqueda mediante periodos
     * @param periodoInicial
     * @param periodoFinal
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProduccionNoMetalicaAuxDTOResultado("
            + "aux.idCliente, aux.anioPro, aux.mes, aux.ruc, aux.titularMinero, aux.estrato, aux.categoria, aux.codigo, aux.unidad, "
            + "aux.codigoIntegranteUea, aux.unidadIntegrante, aux.region, aux.provincia, aux.distrito, aux.productoExtraido, aux.cantidadExtraido, " 
            + "aux.productoFinal, aux.cantidadProductoFinal, aux.idUnidadMedida, aux.descripcion "
            + ") "
            + "FROM PgimProduccionNoMetalicaAux aux "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(aux.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(aux.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:periodoInicial IS NULL OR (TRIM(aux.anioPro) || '/' || TRIM(aux.mes) BETWEEN :periodoInicial AND :periodoFinal)) " 
            + "ORDER BY TRIM(aux.anioPro) DESC, TRIM(aux.mes) DESC ")
    Page<PgimProduccionNoMetalicaAuxDTO> listarProduccionNoMetalica(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("periodoInicial") String periodoInicial, @Param("periodoFinal") String periodoFinal, Pageable paginador);
}
