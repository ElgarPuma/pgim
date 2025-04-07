package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimPrincipalProyectoInversionAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPrincipalProyectoInversionAux;

/**
 * @descripción: Lógica de negocio de la entidad reporte de principales proyectos
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
public interface PrincipalProyectoInversionAuxRepository extends JpaRepository<PgimPrincipalProyectoInversionAux, Long>{
    
     /**
     * Permite mostrar la lista de reporte de principales proyectos de inversión que está paginado y contiene filtros de busqueda mediante periodos
     * @param periodoInicial
     * @param periodoFinal
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrincipalProyectoInversionAuxDTOResultado("
            + "aux.anioPro, aux.mes, aux.idCliente, aux.ruc, aux.titularMinero, aux.estrato, aux.nombreProyecto, aux.fechaInicio, "
            + "aux.fechaTermino, aux.region, aux.etapaProyecto, aux.aniosVidaUtil, "
            + "aux.presupuestoGlobal, aux.presupuestoAnualEstimado, aux.inversionAcu2018, aux.inversionMesAnterior, "
            + "aux.inversionMes, aux.produccionAnual, aux.porcAvanceFisico, aux.proyectoCarteraMinem, "
            + "aux.mineralPrincipal, aux.otrosMinerales, aux.empleoOperacion, aux.empleoConstruccion, "
            + "aux.nombreInversionista, aux.concesionMinera "
            + ") "
            + "FROM PgimPrincipalProyectoInversionAux aux "
            + "WHERE 1 = 1 "
            + "AND (:periodoInicial IS NULL OR (TRIM(aux.anioPro) || '/' || TRIM(aux.mes) BETWEEN :periodoInicial AND :periodoFinal)) " 
            + "ORDER BY TRIM(aux.anioPro) DESC, TRIM(aux.mes) DESC "
            )
    Page<PgimPrincipalProyectoInversionAuxDTO> listarReportePrincipalesProyectosInvers(@Param("periodoInicial") String periodoInicial, @Param("periodoFinal") String periodoFinal, Pageable paginador);
}
