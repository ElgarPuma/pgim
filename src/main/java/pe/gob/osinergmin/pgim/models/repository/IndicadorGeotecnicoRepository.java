package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimIndicadorGeotecnicoAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIndicadorGeotecnicoAux;

public interface IndicadorGeotecnicoRepository extends JpaRepository<PgimIndicadorGeotecnicoAux, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorGeotecnicoAuxDTOResultado("
            + "ig.anioPro, ig.mes, ig.idCliente, ig.ruc, ig.titularMinero, ig.estrato, ig.codigo, ig.unidad, ig.grupo, "
            + "ig.indicador, ig.valor, ig.unidadMedida "
            + ") "
            + "FROM PgimIndicadorGeotecnicoAux ig "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ig.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ig.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(ig.anioPro) || '/' || TRIM(ig.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(ig.anioPro) DESC, TRIM(ig.mes) DESC ")
    Page<PgimIndicadorGeotecnicoAuxDTO> listarIndicadorGeotecnico(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorGeotecnicoAuxDTOResultado("
            + "ig.anioPro, ig.mes, ig.idCliente, ig.ruc, ig.titularMinero, ig.estrato, ig.codigo, ig.unidad, ig.grupo, "
            + "ig.indicador, ig.valor, ig.unidadMedida "
            + ") "
            + "FROM PgimIndicadorGeotecnicoAux ig "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ig.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ig.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:feInicio IS NULL OR (TRIM(ig.anioPro) || '/' || TRIM(ig.mes) BETWEEN :feInicio AND :feFin)) ")
    List<PgimIndicadorGeotecnicoAuxDTO> listarReporteIndicadorGeotecnico(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin);
}
