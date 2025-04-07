package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEstandarPrograma;

import java.util.List;

/**
 * En ésta interface PrgrmSupervisionRepository esta conformado pos sus metodos de listar
 * programa supervision, aplicacion de los filtros por nombres de programa supervision.
 * 
 * @descripción: Lógica de negocio de la entidad Programa supervision
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface EstandarProgramaRepository extends JpaRepository<PgimEstandarPrograma, Long> {
	
	/**
	 * Lista de datos estándar de un programa
	 * @param idLineaPrograma
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTOResultado( "
            + "est.idEstandarPrograma, est.pgimLineaPrograma.idLineaPrograma,"
            + "(SELECT sub.idSubtipoSupervision FROM PgimSubtipoSupervision sub " 
            + "WHERE sub.idSubtipoSupervision = est.pgimSubtipoSupervision.idSubtipoSupervision),"
            + "(SELECT sub.deSubtipoSupervision FROM PgimSubtipoSupervision sub " 
            + "WHERE sub.idSubtipoSupervision = est.pgimSubtipoSupervision.idSubtipoSupervision),"
            + "(SELECT mot.idMotivoSupervision FROM PgimMotivoSupervision mot " 
            + "WHERE mot.idMotivoSupervision = est.pgimMotivoSupervision.idMotivoSupervision),"
            + "(SELECT mot.deMotivoSupervision FROM PgimMotivoSupervision mot " 
            + "WHERE mot.idMotivoSupervision = est.pgimMotivoSupervision.idMotivoSupervision),"
            + "est.moPorSupervision, est.caDiasSupervision, est.caSupervisiones) "
            + "FROM PgimEstandarPrograma est "
            + "WHERE est.esRegistro = '1' "
            + "AND est.pgimLineaPrograma.idLineaPrograma = :idLineaPrograma")
	List<PgimEstandarProgramaDTO> listarEstandarPrograma(@Param("idLineaPrograma") Long idLineaPrograma);

	/**
	 * Lista de datos estándar de un programa
	 * @param idLineaPrograma
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTOResultado( "
            + " est.moPorSupervision, est.caDiasSupervision) "
            + " FROM PgimEstandarPrograma est "
            + " WHERE est.esRegistro = '1' "
            + " AND est.pgimLineaPrograma.idLineaPrograma = :idLineaPrograma"
            + " AND est.pgimSubtipoSupervision.idSubtipoSupervision = :idSubtipoSupervision")
	List<PgimEstandarProgramaDTO> listarCostosEstimados(@Param("idLineaPrograma") Long idLineaPrograma,
			@Param("idSubtipoSupervision") Long idSubtipoSupervision);
	
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTOResultado( "
            + "est.idEstandarPrograma, est.pgimLineaPrograma.idLineaPrograma,"
            + "(SELECT mot.idMotivoSupervision FROM PgimMotivoSupervision mot " 
            + "WHERE mot.idMotivoSupervision = est.pgimMotivoSupervision.idMotivoSupervision),"
            + "(SELECT mot.deMotivoSupervision FROM PgimMotivoSupervision mot " 
            + "WHERE mot.idMotivoSupervision = est.pgimMotivoSupervision.idMotivoSupervision),"
            + "est.moPorSupervision, est.caDiasSupervision, est.caSupervisiones, " 
            //+ "(est.moPorSupervision * est.caDiasSupervision * est.caSupervisiones)) "
            + "(est.moPorSupervision * est.caSupervisiones)) "
            + "FROM PgimEstandarPrograma est "
            + "WHERE est.esRegistro = '1' AND est.pgimMotivoSupervision.tipoSupervision.idValorParametro = 288 "
            + "AND est.pgimLineaPrograma.idLineaPrograma = :idLineaPrograma")
    List<PgimEstandarProgramaDTO> listarNoProgramada(@Param("idLineaPrograma") Long idLineaPrograma);
    
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTOResultado("
            + "est.idEstandarPrograma, est.pgimLineaPrograma.idLineaPrograma, "
            + "est.pgimSubtipoSupervision.idSubtipoSupervision, est.pgimMotivoSupervision.idMotivoSupervision, "
            + "est.moPorSupervision, est.caDiasSupervision "
            + ")"
            + "FROM PgimEstandarPrograma est "
            + "WHERE est.idEstandarPrograma = :idEstandarPrograma "
            + "AND est.esRegistro = '1' ")
	PgimEstandarProgramaDTO obtenerEstandarProgramaPorId(@Param("idEstandarPrograma") Long idEstandarPrograma);

}
