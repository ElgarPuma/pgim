package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.*;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSupervision;

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
public interface PrgrmSupervisionRepository extends JpaRepository<PgimPrgrmSupervision, Long> {

	/**
	 * Se utiliza para visualizar la lista seleccionable(Programa) en el dialogo 
	 * Asignación de supervisión [Año]-[División supervisora]: [Especialidad]
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTOResultado("
            + "pps.idProgramaSupervision, "
            + "plsu.nuAnio || ' - ' || pps.divisionSupervisora.noValorParametro || ': ' || pps.pgimEspecialidad.noEspecialidad, "
            + "pps.pgimEspecialidad.idEspecialidad, pps.divisionSupervisora.idValorParametro) "
            + "FROM PgimPrgrmSupervision pps "
						+ "INNER JOIN pps.pgimPlanSupervision plsu "
            + "WHERE EXISTS (SELECT 1 "
            + "FROM PgimLineaPrograma liba "
						+ "INNER JOIN liba.lineaProgramaEstado esta "
            + "WHERE esta.coClaveTexto = :PREST_APRBDA "
            + "AND liba.pgimPrgrmSupervision = pps "
            + "AND liba.esRegistro = '1' "
            + "AND esta.esRegistro = '1') "
            + "AND pps.esRegistro = '1' "
            + "AND plsu.esRegistro = '1' "
			+ "ORDER BY plsu.nuAnio DESC, pps.divisionSupervisora.noValorParametro ASC, pps.pgimEspecialidad.noEspecialidad ASC "
            )
	List<PgimPrgrmSupervisionDTO> obtenerPrgrmSupervision(@Param("PREST_APRBDA") String PREST_APRBDA);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTOResultado( "
            + "progsp.idProgramaSupervision, progsp.pgimEspecialidad.noEspecialidad ) "
            + "FROM PgimPrgrmSupervision progsp WHERE progsp.esRegistro = '1' ")
    List<PgimPrgrmSupervisionDTO> filtrarPorNombreProgramaSupervision(@Param("nombre") String nombre);
	
    /**
     * Permite listar los programas de acuerdo con los criterios filtro
     * especificados.
     * @param idDivisionSupervisora
     * @param descNuAnio
     * @param idEspecialidad
     * @param textoBusqueda
     * @param paginador
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTOResultado("
            + "pps.idProgramaSupervision,  "
            + "pps.pgimEspecialidad.idEspecialidad, "
            + "pps.pgimEspecialidad.deEspecialidad, "
            + "pps.divisionSupervisora.idValorParametro, "
            + "pps.divisionSupervisora.noValorParametro, "
            + "pps.pgimPlanSupervision.nuAnio || ' - ' || pps.divisionSupervisora.noValorParametro || ': ' || pps.pgimEspecialidad.noEspecialidad, "
            + "pps.pgimPlanSupervision.nuAnio, "
            + "NVL(pps.moPartida,0), "
            + "NVL(pps.moPartida,0) ) "
            + "FROM PgimPrgrmSupervision pps "
            + "WHERE pps.esRegistro = '1' "
            + "AND (:idEspecialidad IS NULL OR pps.pgimEspecialidad.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora) "
            + "AND (:descNuAnio IS NULL OR pps.pgimPlanSupervision.nuAnio = :descNuAnio) "
            + "AND (:textoBusqueda IS NULL OR ( "
            + "LOWER(pps.divisionSupervisora.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(pps.pgimEspecialidad.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " + " )) ")
    Page<PgimPrgrmSupervisionDTO> listarProgramas(
            @Param("idDivisionSupervisora") Long idDivisionSupervisora, @Param("descNuAnio") Long descNuAnio,
            @Param("idEspecialidad") Long idEspecialidad,@Param("textoBusqueda") String textoBusqueda,
            Pageable paginador);
	
    /**
     * Permite obtener un contrato 
     *
     * @param idProgramaSupervision               
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTOResultado("
            + "pps.idProgramaSupervision,  "
            + "pps.pgimEspecialidad.idEspecialidad, "
            + "pps.pgimEspecialidad.deEspecialidad, "
            + "pps.divisionSupervisora.idValorParametro, "
            + "pps.divisionSupervisora.noValorParametro, "
            + "pps.pgimPlanSupervision.nuAnio || ' - ' || pps.divisionSupervisora.noValorParametro || ': ' || pps.pgimEspecialidad.noEspecialidad, "
            + "pps.pgimPlanSupervision.nuAnio, "
            + "NVL(pps.moPartida,0), "
            + "pps.pgimInstanciaProces.idInstanciaProceso) "
            + "FROM PgimPrgrmSupervision pps "
            + "WHERE pps.esRegistro = '1' "
            + "AND pps.idProgramaSupervision = :idProgramaSupervision")
	PgimPrgrmSupervisionDTO obtenerPrograma(@Param("idProgramaSupervision") Long idProgramaSupervision);
	
	
	/**
	 * Se utiliza para visualizar la lista seleccionable(Programa) en el dialogo 
	 * Asignación de programa [Año]-[Estrato]: [Especialidad]
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTOResultado("
            + "pps.idProgramaSupervision,  "
            + "pps.pgimPlanSupervision.nuAnio || ' - ' || pps.divisionSupervisora.noValorParametro || ': ' || pps.pgimEspecialidad.noEspecialidad, "
            + "pps.pgimEspecialidad.idEspecialidad, pps.divisionSupervisora.idValorParametro) "
            + "FROM PgimPrgrmSupervision pps "
            + "WHERE pps.esRegistro = '1' "
            + "AND pps.pgimPlanSupervision.esRegistro = '1' "
            + "AND pps.pgimInstanciaProces.idInstanciaProceso is null "
            + "ORDER BY pps.pgimPlanSupervision.nuAnio DESC")
	List<PgimPrgrmSupervisionDTO> obtenerProgramaParaAsignacion();

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTOResultado("
            + "prgrm.idProgramaSupervision,  "
            + "('PRGS-' || '' || prgrm.idProgramaSupervision || '. ' || prgrm.pgimPlanSupervision.nuAnio || ' - ' || prgrm.divisionSupervisora.noValorParametro || ': ' || prgrm.pgimEspecialidad.noEspecialidad), "
            + "prgrm.pgimEspecialidad.idEspecialidad, prgrm.divisionSupervisora.idValorParametro) "
            + "FROM PgimPrgrmSupervision prgrm "
			+ "     LEFT OUTER JOIN prgrm.pgimPlanSupervision plan "
			+ "     LEFT OUTER JOIN prgrm.pgimEspecialidad esp "
            + "     LEFT OUTER JOIN prgrm.divisionSupervisora div "
            + "WHERE prgrm.esRegistro = '1' "
            + "AND esp.esRegistro = '1' "
            + "AND div.esRegistro = '1' "
			+ "AND (:descPrograma IS NULL OR LOWER( 'PRGS-' || '' || prgrm.idProgramaSupervision || '. ' || plan.nuAnio || ' - ' || div.noValorParametro || ': ' || esp.noEspecialidad) LIKE LOWER(CONCAT('%', :descPrograma, '%')) )"
            + "ORDER BY plan.nuAnio DESC")
	List<PgimPrgrmSupervisionDTO> obtenerProgramaAutocompletado(@Param("descPrograma") String descPrograma);
	
     @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTOResultado(" 
                    + "pps_aux.idProgramaSupervisionAux,  "
                    + "pps_aux.idPlanSupervision,  "
                    + "pps_aux.idLineaPrograma, "
                    + "pps_aux.noDivisionSupervision, " 
                    + "pps_aux.noEspecialidad, " 
                    + "pps_aux.estado, "
                    + "pps_aux.moPartida, " 
                    + "pps_aux.moCostoTotal "
                    + ") "
                    + "FROM PgimPrgrmSupervPlanAux pps_aux " 
                    + "WHERE pps_aux.idPlanSupervision = :idPlanSupervision "
                    + "ORDER BY  pps_aux.noEspecialidad, pps_aux.noDivisionSupervision DESC")
    List<PgimPrgrmSupervisionDTO> listarProgramasSupervPlan(@Param("idPlanSupervision") Long idPlanSupervision);
     
     
	/**
	 * Permite obtener la lista preparada de supervisiones programadas por estrato-especialidad de manera paginada
	 * @return
	 */
 	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSuperProgramadaAuxDTOResultado( "
			+ "pps.noDivisionSupervisora, pps.noEspecialidad, pps.nroSupPrgP1, pps.nroSupPrgP2, pps.nroSupPrgP3, pps.nroSupPrgP4, pps.nroSupPrgP5, pps.nroSupPrgP6, pps.totalNroSupPrg "
			+ ") " 
			+ "FROM PgimSuperProgramadaAux pps "
			+ "WHERE 1 = 1 "
	        + "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
	        + "ORDER BY pps.noDivisionSupervisora asc"
			)
	Page<PgimSuperProgramadaAuxDTO> listarReporteSuperProgramadaPaginado(
	        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
			 Pageable paginador
	       );
 	
 	/**
 	 * Permite obtener la lista preparada de supervisiones programadas por división supervisora-especialidad
 	 * @return
 	 */
 	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSuperProgramadaAuxDTOResultado( "
 			+ "pps.noDivisionSupervisora, pps.noEspecialidad, pps.nroSupPrgP1, pps.nroSupPrgP2, pps.nroSupPrgP3, pps.nroSupPrgP4, pps.nroSupPrgP5, pps.nroSupPrgP6, pps.totalNroSupPrg "
 			+ ") " 
 			+ "FROM PgimSuperProgramadaAux pps "
 			+ "WHERE 1 = 1 "
 			+ "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
 			+ "ORDER BY pps.noDivisionSupervisora asc"
 			)
 	List<PgimSuperProgramadaAuxDTO> listarReporteSuperProgramada(
 			@Param("idDivisionSupervisora") Long idDivisionSupervisora, Sort sort);
 	

	/**
	 * Permite obtener la lista preparada de supervisiones no programadas por division supervisora-especialidad de manera paginada
	 * @return
	 */
 	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupernpDsEspAuxDTOResultado( "
			+ "pps.noDivisionSupervisora, pps.noEspecialidad, pps.supA1, pps.supA2, pps.supA3, pps.supA4, pps.supA5, pps.supA6, pps.totalSupervision "
			+ ") " 
			+ "FROM PgimSupernpDsEspAux pps "
			+ "WHERE 1 = 1 "
	        + "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
	        + "ORDER BY pps.noDivisionSupervisora asc"
			)
	Page<PgimSupernpDsEspAuxDTO> listarReporteSuperNoProgramadaPaginado(
	        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
			 Pageable paginador
	       );
 	
 	/**
 	 * Permite obtener la lista preparada de supervisiones no programadas por division supervisora-especialidad
 	 * @return
 	 */
 	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupernpDsEspAuxDTOResultado( "
 			+ "pps.noDivisionSupervisora, pps.noEspecialidad, pps.supA1, pps.supA2, pps.supA3, pps.supA4, pps.supA5, pps.supA6, pps.totalSupervision "
 			+ ") " 
 			+ "FROM PgimSupernpDsEspAux pps "
 			+ "WHERE 1 = 1 "
 			+ "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
 			+ "ORDER BY pps.noDivisionSupervisora asc"
 			)
 	List<PgimSupernpDsEspAuxDTO> listarReporteSuperNoProgramada(
 			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
 			Sort sort
 			);
 	
	/**
	 * Permite obtener la lista preparada de supervisiones por división supervisora y unidad minera (programadas y no programadas) de manera paginada
	 * @return
	 */
 	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSuperDsUmAuxDTOResultado( "
			+ "pps.noDivisionSupervisora, pps.nuRuc, pps.noRazonSocial, pps.coUnidadMinera, pps.noUnidadMinera, pps.supA1, pps.supA2, pps.supA3, pps.supA4, pps.supA5, pps.supA6, pps.totalSupervision "
			+ ") " 
			+ "FROM PgimSuperDsUmAux pps "
			+ "WHERE 1 = 1 "
	        + "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
	        + "AND (:noRazonSocial IS NULL OR LOWER(pps.noRazonSocial) = LOWER(:noRazonSocial)) "
	        + "ORDER BY pps.noDivisionSupervisora asc"
			)
	Page<PgimSuperDsUmAuxDTO> listarReporteSuperDsUmPaginado(
	        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
 			@Param("noRazonSocial") String noRazonSocial,
			 Pageable paginador
	       );
 	
 	/**
 	 * Permite obtener la lista preparada de supervisiones por división supervisora y unidad minera (programadas y no programadas) 
 	 * @return
 	 */
 	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSuperDsUmAuxDTOResultado( "
 			+ "pps.noDivisionSupervisora, pps.nuRuc, pps.noRazonSocial, pps.coUnidadMinera, pps.noUnidadMinera, pps.supA1, pps.supA2, pps.supA3, pps.supA4, pps.supA5, pps.supA6, pps.totalSupervision "
 			+ ") " 
 			+ "FROM PgimSuperDsUmAux pps "
 			+ "WHERE 1 = 1 "
 			+ "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
 			+ "AND (:noRazonSocial IS NULL OR LOWER(pps.noRazonSocial) = LOWER(:noRazonSocial)) "
 			+ "ORDER BY pps.noDivisionSupervisora asc"
 			)
 	List<PgimSuperDsUmAuxDTO> listarReporteSuperDsUm(
 			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
 			@Param("noRazonSocial") String noRazonSocial,
 			Sort sort
 			);
 	
	/**
	 * Permite obtener la lista preparada de presupuesto consolidado por división supervisora y especialidad de manera paginada
	 * @return
	 */
 	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPresupuestoDsEspAuxDTOResultado( "
			+ "pps.noDivisionSupervisora, pps.noEspecialidad, pps.montoP1, pps.montoP2, pps.montoP3, pps.montoP4, pps.montoP5, pps.montoP6, pps.totalMonto "
			+ ") " 
			+ "FROM PgimPresupuestoDsEspAux pps "
			+ "WHERE 1 = 1 "
	        + "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
	        + "ORDER BY pps.noDivisionSupervisora asc"
			)
	Page<PgimPresupuestoDsEspAuxDTO> listarReportePresupDsEspecPaginado    (
	        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
			 Pageable paginador
	       );
 	
 	/**
 	 * Permite obtener la lista preparada de presupuesto consolidado por división supervisora y especialidad
 	 * @return
 	 */
 	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPresupuestoDsEspAuxDTOResultado( "
 			+ "pps.noDivisionSupervisora, pps.noEspecialidad, pps.montoP1, pps.montoP2, pps.montoP3, pps.montoP4, pps.montoP5, pps.montoP6, pps.totalMonto "
 			+ ") " 
 			+ "FROM PgimPresupuestoDsEspAux pps "
 			+ "WHERE 1 = 1 "
 			+ "AND (:idDivisionSupervisora IS NULL OR pps.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
 			+ "ORDER BY pps.noDivisionSupervisora asc"
 			)
 	List<PgimPresupuestoDsEspAuxDTO> listarReportePresupDsEspec   (
 			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
 			Sort sort
 			);
 	
}
