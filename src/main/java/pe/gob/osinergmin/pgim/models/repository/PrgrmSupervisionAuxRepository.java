package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSupervisionAux;

/**
 * En ésta interface PrgrmSupervisionAuxRepository esta conformado pos sus metodos de listar
 * programa supervision (auxiliar), aplicacion de los filtros por nombres de programa supervision.
 * 
 * @descripción: Lógica de negocio de la entidad Programa supervision auxiliar
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 01/12/2020
 * @fecha_de_ultima_actualización: 01/12/2020 
 */
@Repository
public interface PrgrmSupervisionAuxRepository extends JpaRepository<PgimPrgrmSupervisionAux, Long> {

	
	/**
     * Permite listar los programas de acuerdo con los criterios filtro
     * especificados.
     *
     * @param idDivisionSupervisora
     * @param nuAnio
     * @param idEspecialidad
     * @param textoBusqueda
     * @param paginador               
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionAuxDTOResultado("
            + "pps.idProgramaSupervisionAux, ppro.idProgramaSupervision,  esp.idEspecialidad, "
            + "pps.noEspecialidad, div.idValorParametro, pps.deDivisionSupervisora, "
            + "pps.noProgramaSupervision, pps.nuAnio, NVL(pps.moPartida, 0), "
            + "NVL(pps.moCostoTotal, 0) , pps.personaAsignada, pps.usuarioAsignado, "
            + "fase.idFaseProceso, pps.noFaseActual, paso.idPasoProceso, "
            + "pps.noPasoActual, pins.idInstanciaProceso, rela.idRelacionPaso, "
            + "tire.idValorParametro, pps.noTipoRelacion, plin.idLineaPrograma, "
            + "pps.feLineaPrograma, plip.idValorParametro, pps.estadoLineaBase, "
            + "pps.flLeido, pps.feLectura, pps.feInstanciaPaso, "
            + "pps.noPersonaOrigen, pps.flPasoActivo, pps.deMensaje, "
            + "pps.idInstanciaPaso "
            + ") "
            + "FROM PgimPrgrmSupervisionAux pps "
            + "INNER JOIN pps.pgimPrgrmSupervision ppro "
            + "INNER JOIN pps.pgimEspecialidad esp "
            + "INNER JOIN pps.divisionSupervisora div "
            + "INNER JOIN pps.faseActual fase "
            + "INNER JOIN pps.pasoActual paso "
            + "INNER JOIN pps.pgimInstanciaProces pins "
            + "INNER JOIN pps.pgimRelacionPaso rela "
            + "INNER JOIN pps.tipoRelacion tire  "
            + "INNER JOIN pps.pgimLineaPrograma plin  "
            + "INNER JOIN pps.lineaProgramaEstado plip "
            + "WHERE (:idEspecialidad IS NULL OR esp.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR div.idValorParametro = :idDivisionSupervisora) "
            + "AND (:nuAnio IS NULL OR pps.nuAnio = :nuAnio) "
            + "AND "
            + "( "
            // Mis tareas
			+ "     (:flagMisAsignaciones = '1' "
            + "      AND pps.flPasoActivo = '1' "
            + "      AND LOWER(pps.usuarioAsignado) = LOWER(:usuarioAsignado) "
            + "      AND tire.idValorParametro NOT IN (291, 292) "
            + "      ) "  
			+ "     OR "
             // Tareas enviadas
            + "     (:flagMisAsignaciones = '2' "
            + "      AND LOWER(pps.noUsuarioOrigen) = LOWER(:noUsuarioOrigen) "
            + "      AND ("
            + "             :descPersonaDestino IS NULL "
            + "             OR LOWER(pps.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
            + "           ) " 
            + "      ) "
            + "     OR "
            // Todas las tareas
			+ "     (:flagMisAsignaciones = '0' "
            + "       AND pps.flPasoActivo = '1' "
            + "       AND ("
            + "             :descPersonaDestino IS NULL "
            + "             AND pps.flPasoActivo = '1' "
            + "             OR LOWER(pps.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
            + "            )"
            + "      ) "
			+ " ) "
            + "AND (:textoBusqueda IS NULL OR "
            + "LOWER(pps.divisionSupervisora.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(pps.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
            + "OR LOWER(pe.gob.osinergmin.pgim.utils.ConstantesUtil.PREFIJO_PROGRAMA_SUP || '-' || pps.idProgramaSupervisionAux || '. ' || pps.nuAnio) "
            + "			LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + ") ")
    Page<PgimPrgrmSupervisionAuxDTO> listarProgramasAux(
            @Param("idDivisionSupervisora") Long idDivisionSupervisora, 
            @Param("nuAnio") Long nuAnio,
            @Param("idEspecialidad") Long idEspecialidad,
            @Param("flagMisAsignaciones") String flagMisAsignaciones,    
            @Param("noUsuarioOrigen") String noUsuarioOrigen,            
            @Param("usuarioAsignado") String usuarioAsignado,            
            @Param("descPersonaDestino") String descPersonaDestino,            
            @Param("textoBusqueda") String textoBusqueda,
            Pageable paginador);
	
	
	/**
     * Permite obtener un regitro de programa por ID      
     *
     * @param idProgramaSupervision
     *                
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionAuxDTOResultado("
            + "pps.idProgramaSupervisionAux, pps.pgimPrgrmSupervision.idProgramaSupervision,  pps.pgimEspecialidad.idEspecialidad, "
            + "pps.noEspecialidad, pps.divisionSupervisora.idValorParametro, pps.deDivisionSupervisora, "
            + "pps.noProgramaSupervision, pps.nuAnio, NVL(pps.moPartida,0), "
            + "NVL(pps.moCostoTotal,0) , pps.personaAsignada, pps.usuarioAsignado, "
            + "pps.faseActual.idFaseProceso, pps.noFaseActual, pps.pasoActual.idPasoProceso, "
            + "pps.noPasoActual, pps.pgimInstanciaProces.idInstanciaProceso, pps.pgimRelacionPaso.idRelacionPaso, "
            + "pps.tipoRelacion.idValorParametro, pps.noTipoRelacion, pps.pgimLineaPrograma.idLineaPrograma, "
            + "pps.feLineaPrograma, pps.lineaProgramaEstado.idValorParametro, pps.estadoLineaBase, "
            + "pps.flLeido, pps.feLectura, pps.feInstanciaPaso, "
            + "pps.noPersonaOrigen, pps.flPasoActivo, pps.deMensaje, "
            + "pps.idInstanciaPaso "
            + ") "
            + "FROM PgimPrgrmSupervisionAux pps "
            + "WHERE pps.pgimPrgrmSupervision.idProgramaSupervision = :idProgramaSupervision "            
            + "AND  pps.flPasoActivo = '1' "            
            )
    PgimPrgrmSupervisionAuxDTO obtenerProgramaAuxById(@Param("idProgramaSupervision") Long idProgramaSupervision);
	
	
	/**
     * Permite obtener un regitro de programa por IdInstanciaProceso      
     *
     * @param idInstanciaProceso
     *                
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionAuxDTOResultado("
            + "pps.idProgramaSupervisionAux, pps.pgimPrgrmSupervision.idProgramaSupervision, pps.pgimEspecialidad.idEspecialidad, "
            + "pps.noEspecialidad, pps.divisionSupervisora.idValorParametro, pps.deDivisionSupervisora, "
            + "pps.noProgramaSupervision, pps.nuAnio, NVL(pps.moPartida,0), "
            + "NVL(pps.moCostoTotal,0), pps.personaAsignada, pps.usuarioAsignado, "
            + "pps.faseActual.idFaseProceso, pps.noFaseActual, pps.pasoActual.idPasoProceso, "
            + "pps.noPasoActual, pps.pgimInstanciaProces.idInstanciaProceso, pps.pgimRelacionPaso.idRelacionPaso, "
            + "pps.tipoRelacion.idValorParametro, pps.noTipoRelacion, pps.pgimLineaPrograma.idLineaPrograma, "
            + "pps.feLineaPrograma, pps.lineaProgramaEstado.idValorParametro, pps.estadoLineaBase, "
            + "pps.flLeido, pps.feLectura, pps.feInstanciaPaso, "
            + "pps.noPersonaOrigen, pps.flPasoActivo, pps.deMensaje, "
            + "pps.idInstanciaPaso "
            + ") "
            + "FROM PgimPrgrmSupervisionAux pps "
            + "WHERE pps.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "            
            + "AND pps.flPasoActivo = '1' "            
            )
    PgimPrgrmSupervisionAuxDTO obtenerProgramaAuxByIdInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);
	
	
	/**
	 * Permite obtener la cantidad de programaciones pendientes de atención que tiene un usuario
	 * 
	 * @param usuarioAsignado
	 * @return
	 */
	@Query("SELECT COUNT(pps.idProgramaSupervisionAux)"
            + "FROM PgimPrgrmSupervisionAux pps "
            + "WHERE "            
            + "pps.flPasoActivo = '1' "
            + "AND LOWER(pps.usuarioAsignado) = LOWER(:usuarioAsignado) "
            + "AND pps.tipoRelacion.idValorParametro NOT IN (291, 292) "
            + "AND pps.pasoActual.idPasoProceso <> 37 " //omite paso "Realizar seguimiento"
            )
    Integer contarProgramasPendientes(
            @Param("usuarioAsignado") String usuarioAsignado            
            );

}
