package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimEstadisticoIncidenteAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEstadisticoIncidenteAux;

/**
 * @descripción: Lógica de negocio de la entidad reporte de estadístico de incidentes
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
public interface EstadisticoIncidenteAuxRepository extends JpaRepository<PgimEstadisticoIncidenteAux, Long>{
    
    /**
     * Permite mostrar la lista de reporte estadisticos de incidentes que está paginado y contiene filtros de busqueda mediante periodos
     * @param periodoInicial
     * @param periodoFinal
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEstadisticoIncidenteAuxDTOResultado("
            + "aux.idCliente, aux.anioPro, aux.mes, aux.idCorrelativo, aux.ruc, aux.estrato, aux.titularMinero, aux.codigo, aux.unidad, "
            + "aux.trabajadoresCia, aux.trabajadoresCm, aux.trabajadoresOtros, aux.trabajadoresTotal, "
            + "aux.incidentesMes, aux.incidentesAcumalada, aux.accidLevesMes, aux.accidLevesAcum, "
            + "aux.accidIncapacitMes, aux.accidIncapacitAcum, aux.accidMortalesMes, aux.accidMortalesAcum, "
            + "aux.diasperdidosMes, aux.diasperdidosAcum, aux.horhombresTrabMes, aux.horhombresTrabAcum, "
            + "aux.indiceFrecuenciaMes, aux.indiceFrecuenciaAcum, aux.indiceSeveridadMes, aux.indiceSeveridadAcum, "
            + "aux.indiceAccidentesMes, aux.indiceAccidentesAcum, aux.usuIngreso, aux.fecIngreso, "
            + "aux.ipIngreso, aux.usuModifica, aux.fecModifica, aux.ipModifica "
            + ") "
            + "FROM PgimEstadisticoIncidenteAux aux "
            + "WHERE 1 = 1 "
            + "AND (:noUnidadMinera IS NULL OR LOWER(aux.codigo) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(aux.unidad) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:periodoInicial IS NULL OR (TRIM(aux.anioPro) || '/' || TRIM(aux.mes) BETWEEN :periodoInicial AND :periodoFinal)) " 
            + "ORDER BY TRIM(aux.anioPro) DESC, TRIM(aux.mes) DESC ")
    Page<PgimEstadisticoIncidenteAuxDTO> listarEstadisticosIncidente(@Param("noUnidadMinera") String descNoUnidadMinera, @Param("periodoInicial") String periodoInicial, @Param("periodoFinal") String periodoFinal, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEstadisticoIncidenteAuxDTOResultado("
            + "aux.anioPro, aux.mes, aux.indiceFrecuenciaAcum, aux.indiceSeveridadAcum, aux.indiceAccidentesAcum"
            + ") "
            + "FROM PgimEstadisticoIncidenteAux aux "
            + "WHERE 1 = 1 "
            + "AND UPPER(aux.codigo) LIKE UPPER(:coUnidadMinera) "
            + "AND (TRIM(aux.anioPro) || '/' || TRIM(aux.mes) BETWEEN :feInicio AND :feFin) ORDER BY TRIM(aux.anioPro) DESC, TRIM(aux.mes) DESC "
            )
    List<PgimEstadisticoIncidenteAuxDTO> listaEstadisticosIncidenteUM(@Param("coUnidadMinera") String coUnidadMinera, @Param("feInicio") String feInicio, @Param("feFin") String feFin);
}
