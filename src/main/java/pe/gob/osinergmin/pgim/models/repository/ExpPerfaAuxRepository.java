package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimExpPerfaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimExpPerfaAux;

import java.util.List;

/**
 * @descripción: Logica de negocio de la entidad expedientes pendientes de atender, por fase, según año
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface ExpPerfaAuxRepository extends JpaRepository<PgimExpPerfaAux, Long> {
	
	/**
	 * Permite obtener la lista preparada de expedientes con PAS por persona asignada, fase y año usado en reporte correspondiente de manera paginada
	 * @param anioSupervision
	 * @param paginador
	 * @return
	 */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExpPerfaAuxDTOResultado( "
            + "expp.idPersona, expp.deEntidadPersona, expp.etiquetaPersona, expp.noUsuario, SUM(expp.f1PreSupervision), SUM(expp.f2SupervisionDeCampo), "
            + "SUM(expp.f3PostSupervisionDeCampo), SUM(expp.f4RevInformeSupervision), SUM(expp.f5AprobacionResultados) "
            + ") " 
            + "FROM PgimExpPerfaAux expp "
    		+ "WHERE expp.anioSupervision = :anioSupervision "
    		+ "GROUP BY expp.idPersona, expp.etiquetaPersona, expp.deEntidadPersona, expp.noUsuario "
            )
    Page<PgimExpPerfaAuxDTO> listarReporteExpPasPerfaseAnioPaginado(@Param("anioSupervision") Long anioSupervision,
            Pageable paginador); 

    /**
     * Permite obtener la lista preparada de expedientes con PAS por persona asignada, fase y año usado en reporte correspondiente
     * @param anioSupervision
     * @return     		+ "ORDER BY exp.etiquetaPersona asc"
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExpPerfaAuxDTOResultado( "
            + "expp.idPersona, expp.deEntidadPersona, expp.etiquetaPersona, expp.noUsuario, SUM(expp.f1PreSupervision), SUM(expp.f2SupervisionDeCampo), "
            + "SUM(expp.f3PostSupervisionDeCampo), SUM(expp.f4RevInformeSupervision), SUM(expp.f5AprobacionResultados) "
            + ") " 
            + "FROM PgimExpPerfaAux expp "
    		+ "WHERE expp.anioSupervision = :anioSupervision "
    		+ "GROUP BY expp.idPersona, expp.etiquetaPersona, expp.deEntidadPersona, expp.noUsuario "
            )
    List<PgimExpPerfaAuxDTO> listarReporteExpPasPerfaseAnio(@Param("anioSupervision") Long anioSupervision,
    		Sort sort);

    
}
