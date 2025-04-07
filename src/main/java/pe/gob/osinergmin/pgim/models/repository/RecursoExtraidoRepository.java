package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import pe.gob.osinergmin.pgim.dtos.PgimRecursoExtraidoAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRecursoExtraidoAux;

/**
 * @descripción: Demarcacion unidad minera
 *
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 21/12/2022
 */
@Repository
public interface RecursoExtraidoRepository extends JpaRepository<PgimRecursoExtraidoAux, String>{
    

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRecursoExtraidoAuxDTOResultado( "
    		+ "TRIM(re.anioPro), TRIM(re.mes), re.idCliente, re.rucCliente, "
            + "re.titularMinero, re.estrato, re.codigo, re.noUnidadMinera, re.codIntegranteUEA, re.noUnidadIntegrante, "
            + "re.noRegion, re.noProvincia, re.noDistrito, re.noRecursoExtraido, re.procedencia, re.noVendedor, re.idUnidadMedida, "
            + "re.descripcion, re.cantidad, re.pct_CU, re.pct_PB, re.pct_ZN, re.ag_OZ_TC, re.au_GR_TM, re.pct_FE, re.pct_MO, "
            + "re.pct_SN, re.pct_CD, re.pct_WO3, re.pct_SB, re.pct_AS, re.pct_MN, re.pct_BI, re.pct_HG, re.pct_IN, 	"
            + "re.pct_SE, re.pct_TE, re.h2SO4, re.pct_U, re.pct_NI, re.pct_MG "
    		+ ") "
    		+ "FROM PgimRecursoExtraidoAux re "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(re.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(re.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:descFeInicial IS NULL "
            + "OR(TRIM(re.anioPro)||TRIM(re.mes) BETWEEN :descFeInicial and :descFeFinal)) "
            + "ORDER BY TRIM(re.anioPro) desc, TRIM(re.mes) desc"
            )
    Page<PgimRecursoExtraidoAuxDTO> listarRecursosExtraidosPaginado(
            @Param("noUnidadMinera") String descNoUnidadMinera, 
            @Param("descFeInicial") String descFeInicial,
            @Param("descFeFinal") String descFeFinal,
            Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRecursoExtraidoAuxDTOResultado( "
    		+ "TRIM(re.anioPro), TRIM(re.mes), re.idCliente, re.rucCliente, "
            + "re.titularMinero, re.estrato, re.codigo, re.noUnidadMinera, re.codIntegranteUEA, re.noUnidadIntegrante, "
            + "re.noRegion, re.noProvincia, re.noDistrito, re.noRecursoExtraido, re.procedencia, re.noVendedor, re.idUnidadMedida, "
            + "re.descripcion, re.cantidad, re.pct_CU, re.pct_PB, re.pct_ZN, re.ag_OZ_TC, re.au_GR_TM, re.pct_FE, re.pct_MO, "
            + "re.pct_SN, re.pct_CD, re.pct_WO3, re.pct_SB, re.pct_AS, re.pct_MN, re.pct_BI, re.pct_HG, re.pct_IN, 	"
            + "re.pct_SE, re.pct_TE, re.h2SO4, re.pct_U, re.pct_NI, re.pct_MG "
    		+ ") "
    		+ "FROM PgimRecursoExtraidoAux re "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(re.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(re.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:descFeInicial IS NULL " 
            + "OR(TRIM(re.anioPro)||TRIM(re.mes) BETWEEN :descFeInicial and :descFeFinal)) "
            + "ORDER BY TRIM(re.anioPro) desc, TRIM(re.mes) desc"
            )
    List<PgimRecursoExtraidoAuxDTO> listarReporteRecursosExtraidos(
            @Param("noUnidadMinera") String descNoUnidadMinera, 
            @Param("descFeInicial") String descFeInicial,
            @Param("descFeFinal") String descFeFinal,
            Sort sort);
}
