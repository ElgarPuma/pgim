package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimReporteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimReporte;

import java.util.List;

/**
 * @descripción: Logica de negocio de la entidad reporte.
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface ReporteRepository extends JpaRepository<PgimReporte, Long> {

    /**
     * Permite listar los reportes.
     * 
     * @param tipoMateria
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReporteDTOResultado("
            + "repo.idReporte, repo.deTitulo, repo.deReporte, repo.tipoReporte.idValorParametro, repo.tipoReporte.noValorParametro, repo.deObjetivo, " 
            + "repo.materia.idValorParametro, repo.materia.deValorParametro, repo.grupoReporte.idValorParametro, repo.grupoReporte.noValorParametro, "
            + "repo.deUrlRelativo, repo.coReporte "  
            + ") " 
            + "FROM PgimReporte repo "
            + "WHERE repo.esRegistro = '1'"
            + "AND (:tipoMateria IS NULL OR repo.materia.idValorParametro = :tipoMateria) "
            + "AND (:tipoReporte IS NULL OR repo.tipoReporte.idValorParametro = :tipoReporte) "
            + "AND (:grupo IS NULL OR repo.grupoReporte.idValorParametro = :grupo) "
            + "AND (:textoBusqueda IS NULL OR (LOWER(repo.deTitulo) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))) " 
            + "OR LOWER (repo.deReporte) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER (repo.deObjetivo) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
            + ") " 
            )
    Page<PgimReporteDTO> listarReporte(@Param("tipoMateria") Long tipoMateria, 
                                        @Param("tipoReporte") Long tipoReporte, 
                                        @Param("grupo") Long grupo, 
                                       @Param("textoBusqueda") String textoBusqueda, Pageable paginador);

    /**
     * Permite obtener la lista de reportes por el id.
     * 
     * @param idReporte
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReporteDTOResultado("
            + "repo.idReporte, repo.deTitulo, repo.deReporte, repo.tipoReporte.idValorParametro, repo.tipoReporte.noValorParametro, repo.deObjetivo, " 
            + "repo.materia.idValorParametro, repo.materia.deValorParametro, repo.grupoReporte.idValorParametro, repo.grupoReporte.noValorParametro, " 
            + "repo.deUrlRelativo " 
            + ") " 
            + "FROM PgimReporte repo " 
            + "WHERE repo.idReporte = :idReporte "
            )
    PgimReporteDTO obtenerReportePorId(@Param("idReporte") Long idReporte);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReporteDTOResultado("
            + "repo.idReporte, repo.deTitulo, repo.deReporte, repo.tipoReporte.idValorParametro, repo.tipoReporte.noValorParametro, repo.deObjetivo, " 
            + "repo.materia.idValorParametro, repo.materia.deValorParametro, repo.grupoReporte.idValorParametro, repo.grupoReporte.noValorParametro, " 
            + "repo.deUrlRelativo, repo.coReporte " 
            + ") " 
            + "FROM PgimReporte repo "
            + "WHERE repo.esRegistro = '1' "
            + "AND (:parametro IS NULL OR repo.grupoReporte.idValorParametro = :parametro)"
            )
    List<PgimReporteDTO> listarReporteGeneral(@Param("parametro") Long parametro);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReporteDTOResultado("
            + "repo.idReporte, repo.deTitulo, repo.deReporte, repo.tipoReporte.idValorParametro, repo.tipoReporte.noValorParametro, repo.deObjetivo, " 
            + "repo.materia.idValorParametro, repo.materia.deValorParametro, repo.grupoReporte.idValorParametro, repo.grupoReporte.noValorParametro, " 
            + "repo.deUrlRelativo, repo.coReporte " 
            + ") " 
            + "FROM PgimReporte repo "
            + "WHERE repo.esRegistro = '1' "
            + "AND (:parametro IS NULL OR repo.grupoReporte.coClaveTexto = :parametro)"
            + "ORDER BY repo.idReporte DESC "
            )
    List<PgimReporteDTO> listarReporteGeneralByCoClaveTexto(@Param("parametro") String parametro);
}
