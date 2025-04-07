package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.*;
import pe.gob.osinergmin.pgim.models.entity.PgimPasAux;

import java.util.Date;
import java.util.List;

/**
 * Tiene como nombre Proceso administrativo sancionador.
 * 
 * @descripción: Logica de negocio de la entidad Pas auxiliar.
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 10/02/2021
 */
@Repository
public interface PasAuxRepository extends JpaRepository<PgimPasAux, Long> {

        /**
         * Permite obtener la lista de PAS auxiliares para mostrarlos como parte del
         * listado oficial en la pantalla principal de fiscalización.
         * @param idInstanciaPaso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
                        + "paau.idPasAux, paau.pgimPas.idPas, paau.pgimPas.coPas, paau.feCreacionPas, "
						+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
						+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
						+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
						+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
						+ "paau.nuExpedienteSigedSup, paau.flPropia, paau.noPersonaAsignada, "
                        + "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
						+ "paau.idPasoActual, paau.noPasoActual, paau.idRelacionPaso, "
						+ "paau.idTipoRelacion, paau.noDivisionSupervisora, paau.flLeido, "
						+ "paau.feLectura, paau.feInstanciaPaso, paau.noPersonaOrigen, "
						+ "paau.flPasoActivo, paau.deMensaje, paau.nuAlertas, "
						+ "paau.nuIntervaloAlertas, paau.idInstanciaProcesoPas, paau.idInstanciaPaso "
						+ ") " 
                        + "FROM PgimPasAux paau "
                        + "WHERE paau.idInstanciaPaso = :idInstanciaPaso "
//                        + "AND paau.flPasoActivo = '1' " //No considerar flPasoActivo para no afectar a la consulta de tareas enviadas (pestaña General).
						)
        PgimPasAuxDTO obtenerPasPorIdInstanciaPaso(@Param("idInstanciaPaso") Long idInstanciaPaso);
        
                        /**
         * Permite obtener la lista de PAS auxiliares para mostrarlos como parte del
         * listado oficial en la pantalla principal de fiscalización.
         * @param coSupervision
         * @param noUnidadMinera
         * @param asNoRazonSocial
         * @param feCreacionPasAnio
         * @param idEspecialidad
         * @param idFaseActual
         * @param idPasoActual
         * @param nuExpedienteSigedPas
         * @param usuarioInteres
         * @param flagMisAsignaciones
         * @param textoBusqueda
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
                        + "paau.idPasAux, paau.pgimPas.idPas, paau.pgimPas.coPas, paau.feCreacionPas, "
						+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
						+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
						+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
						+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
						+ "paau.nuExpedienteSigedSup, paau.flPropia, paau.noPersonaAsignada, "
                        + "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
						+ "paau.idPasoActual, paau.noPasoActual, paau.idRelacionPaso, "
						+ "paau.idTipoRelacion, paau.noDivisionSupervisora, paau.flLeido, "
						+ "paau.feLectura, paau.feInstanciaPaso, paau.noPersonaOrigen, "
						+ "paau.flPasoActivo, paau.deMensaje, paau.nuAlertas, "
						+ "paau.nuIntervaloAlertas, paau.idInstanciaProcesoPas, paau.idInstanciaPaso "
						+ ") " 
                        + "FROM PgimPasAux paau "
                        + "WHERE 1 = 1 "

                        // Parámetros específicos
                        + "AND (:coSupervision IS NULL OR LOWER(paau.coSupervision) LIKE LOWER(CONCAT('%', :coSupervision, '%'))) "
                        + "AND (:noUnidadMinera IS NULL OR LOWER(paau.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))) "
                        + "AND (:asNoRazonSocial IS NULL OR LOWER(paau.asNoRazonSocial) LIKE LOWER(CONCAT('%', :asNoRazonSocial, '%'))) "
                        + "AND (:feCreacionPasAnio IS NULL OR LOWER(paau.feCreacionPasAnio) LIKE LOWER(CONCAT('%', :feCreacionPasAnio, '%'))) "
                        + "AND (:idEspecialidad IS NULL OR paau.idEspecialidad = :idEspecialidad) "
                        + "AND (:idFaseActual IS NULL OR paau.idFaseActual = :idFaseActual ) "
                        + "AND (:idPasoActual IS NULL OR paau.idPasoActual = :idPasoActual) "
                        + "AND (:nuExpedienteSigedPas IS NULL OR paau.nuExpedienteSigedPas = :nuExpedienteSigedPas) "

                        // Parámetros de flujo
                        + "AND (:usuarioInteres IS NULL "
                        + "OR EXISTS (SELECT 1 "
                        + "           FROM PgimEqpInstanciaPro eipy "
                        + "                INNER JOIN eipy.pgimRolProceso ropr "
                        + "                INNER JOIN eipy.pgimPersonalOsi peos "
                        + "           WHERE eipy.pgimInstanciaProces.idInstanciaProceso = paau.idInstanciaProcesoPas "
                        + "           AND ropr.idRolProceso = 27 "
                        + "           AND eipy.esRegistro = '1' "
                        + "           AND peos.noUsuario = :usuarioInteres "
                        + "           ) "
                        + ") " 
                        + "AND ("
						// Mis tareas
						+ "		(:flagMisAsignaciones = '1' "
						+ "		  AND paau.flPasoActivo = '1' "
						+ "		  AND LOWER(paau.noUsuarioAsignado) = LOWER(:descUsuarioAsignado) "
						+ "		  AND paau.idTipoRelacion NOT IN (291, 292) " 
						+ "		 ) " 
						+ "		 OR " 
						// Tareas enviadas
						+ "		(:flagMisAsignaciones = '2' "
						+ "		  AND LOWER(paau.noUsuarioOrigen) = LOWER(:noUsuarioOrigen) "
						+ "       AND ("
						+ "             :descPersonaDestino IS NULL "
						+ "             OR LOWER(paau.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
						+ "           ) " 						
						+ "		 ) " 
						+ "		 OR " 	
						// Todas las tareas
						+ "     (:flagMisAsignaciones = '0' "
						+ "		  AND paau.flPasoActivo = '1' "
						+ "       AND ("
						+ "             :descPersonaDestino IS NULL "
						+ "             OR LOWER(paau.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
						+ "            )"
						+ "      ) "
                        + " ) "
                        // Búsqueda general
                        + "AND ("
                        + ":textoBusqueda IS NULL "
                        + "OR LOWER(paau.coSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(paau.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
                        + "OR LOWER(paau.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(paau.asNoRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR paau.nuExpedienteSigedPas LIKE CONCAT('%', :textoBusqueda, '%') "
                        + " ) ")
        Page<PgimPasAuxDTO> filtrar(@Param("coSupervision") String coSupervision,
                        @Param("noUnidadMinera") String noUnidadMinera,
                        @Param("asNoRazonSocial") String asNoRazonSocial,
                        @Param("feCreacionPasAnio") String feCreacionPasAnio,
                        @Param("idEspecialidad") Long idEspecialidad, 
                        @Param("idFaseActual") Long idFaseActual,
                        @Param("idPasoActual") Long idPasoActual,
                        @Param("nuExpedienteSigedPas") String nuExpedienteSigedPas,
                        @Param("noUsuarioOrigen") String noUsuarioOrigen,
                        @Param("descUsuarioAsignado") String descUsuarioAsignado,
                        @Param("descPersonaDestino") String descPersonaDestino,
                        @Param("usuarioInteres") String usuarioInteres,
                        @Param("flagMisAsignaciones") String flagMisAsignaciones,
                        @Param("textoBusqueda") String textoBusqueda, 
						Pageable paginador);
        
        @Query("SELECT distinct(paau.feCreacionPasAnio) " 
                + "FROM PgimPasAux paau ")
        List<String> obtenerAniosPasAux();
        
        
        /**
         * Permite obtener la lista preparada de expedientes detallados con PAS usado en reporte correspondiente de manera paginada
         * @param feCreacionPasAnio
         * @param paginador
         * @return
         */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
		        + "paau.idPasAux, paau.pgimPas.idPas, paau.pgimPas.coPas, paau.feCreacionPas, "
				+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
				+ "paau.nuExpedienteSigedSup, paau.flPropia, paau.noPersonaAsignada, "
		        + "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
				+ "paau.idPasoActual, paau.noFaseActual || ' - ' || paau.noPasoActual, paau.idRelacionPaso, "
				+ "paau.idTipoRelacion, paau.noDivisionSupervisora, paau.flLeido, "
				+ "paau.feLectura, paau.feInstanciaPaso, paau.noPersonaOrigen, "
				+ "paau.flPasoActivo, paau.deMensaje, paau.nuAlertas, "
				+ "paau.nuIntervaloAlertas, paau.idInstanciaProcesoPas, paau.idInstanciaPaso "
				+ ") " 
		        + "FROM PgimPasAux paau "
		        + "WHERE 1 = 1 "
				+ "AND paau.flPasoActivo = '1' "
		        + "AND paau.idTipoRelacion NOT IN (:REPAS_CNCLAR_FLJO, :REPAS_FNLZAR_FLJO) "
		        + "AND (:feCreacionPasAnio IS NULL OR LOWER(paau.feCreacionPasAnio) LIKE LOWER(CONCAT('%', :feCreacionPasAnio, '%'))) "
		       )
		Page<PgimPasAuxDTO> listarReporteExpPasPaginado(
				@Param("feCreacionPasAnio") String feCreacionPasAnio, 
				Pageable paginador, @Param("REPAS_CNCLAR_FLJO") Long REPAS_CNCLAR_FLJO, @Param("REPAS_FNLZAR_FLJO") Long REPAS_FNLZAR_FLJO);
		
		/**
		* Permite obtener la lista preparada de expedientes detallados con PAS usado en reporte correspondiente
		* @param feCreacionPasAnio
		* @return
		*/
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
		        + "paau.idPasAux, paau.pgimPas.idPas, paau.pgimPas.coPas, paau.feCreacionPas, "
				+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
				+ "paau.nuExpedienteSigedSup, paau.flPropia, paau.noPersonaAsignada, "
		        + "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
				+ "paau.idPasoActual, paau.noFaseActual || ' - ' || paau.noPasoActual, paau.idRelacionPaso, "
				+ "paau.idTipoRelacion, paau.noDivisionSupervisora, paau.flLeido, "
				+ "paau.feLectura, paau.feInstanciaPaso, paau.noPersonaOrigen, "
				+ "paau.flPasoActivo, paau.deMensaje, paau.nuAlertas, "
				+ "paau.nuIntervaloAlertas, paau.idInstanciaProcesoPas, paau.idInstanciaPaso "
				+ ") " 
		        + "FROM PgimPasAux paau "
		        + "WHERE 1 = 1 "
				+ "AND paau.flPasoActivo = '1' "
		        + "AND paau.idTipoRelacion NOT IN (:REPAS_CNCLAR_FLJO, :REPAS_FNLZAR_FLJO) "
		        + "AND (:feCreacionPasAnio IS NULL OR LOWER(paau.feCreacionPasAnio) LIKE LOWER(CONCAT('%', :feCreacionPasAnio, '%'))) "
		       )
		List<PgimPasAuxDTO> listarReporteExpPas(@Param("feCreacionPasAnio") String feCreacionPasAnio,
				Sort sort, @Param("REPAS_CNCLAR_FLJO") Long REPAS_CNCLAR_FLJO, @Param("REPAS_FNLZAR_FLJO") Long REPAS_FNLZAR_FLJO );
		
		
		/**
		 * Permite obtener la lista preparada de expedientes con PAS por año usado en reporte correspondiente
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasAnioAuxDTOResultado( "
	            + "expp.idFaseProceso, expp.noFaseProceso, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.totalFase "
	            + ") " 
	            + "FROM PgimExppasAnioAux expp "
				)
	    List<PgimExppasAnioAuxDTO> listarReporteExppasAnio();

		/**
		 * Permite obtener la lista preparada de expedientes con PAS por año usado en reporte correspondiente
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasAnioAuxDTOResultado( "
				+ "expp.idFaseProceso, expp.noFaseProceso, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.totalFase "
				+ ") " 
				+ "FROM PgimExppasAnioAux expp "
				)
		List<PgimExppasAnioAuxDTO> listarReporteExppasAnioExportar(Sort sort);
	    
	    
		/**
		 * Permite obtener la lista preparada de expedientes con PAS por año usado en reporte correspondiente de manera paginada
		 * @param paginador
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasAnioAuxDTOResultado( "
	            + "expp.idFaseProceso, expp.noFaseProceso, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.totalFase "
	            + ") " 
	            + "FROM PgimExppasAnioAux expp "
				)
	    Page<PgimExppasAnioAuxDTO> listarReporteExppasAnioPaginado(
	    		Pageable paginador); 
		
		
        /**
		* Permite obtener el listado de expedientes por persona asignada para su respectivo reporte
		* @param noPersonaAsignada
		* @param paginador
		* @return
		*/
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
		        + "paau.idPasAux, paau.pgimPas.idPas, paau.pgimPas.coPas, paau.feCreacionPas, "
				+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
				+ "paau.nuExpedienteSigedSup, paau.flPropia, paau.noPersonaAsignada, "
		        + "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
				+ "paau.idPasoActual, paau.noPasoActual, paau.idRelacionPaso, "
				+ "paau.idTipoRelacion, paau.noDivisionSupervisora, paau.flLeido, "
				+ "paau.feLectura, paau.feInstanciaPaso, paau.noPersonaOrigen, "
				+ "paau.flPasoActivo, paau.deMensaje, paau.nuAlertas, "
				+ "paau.nuIntervaloAlertas, paau.idInstanciaProcesoPas, paau.idInstanciaPaso "
				+ ") " 
		        + "FROM PgimPasAux paau "
		        + "WHERE 1 = 1 "
				+ "AND paau.flPasoActivo = '1' "
		        + "AND (:noPersonaAsignada IS NULL "
				+ "OR LOWER(paau.noPersonaAsignada) LIKE LOWER(CONCAT('%', :noPersonaAsignada, '%'))) "
		       )
		Page<PgimPasAuxDTO> filtrarExpedientePorPersonaAsignada(
				@Param("noPersonaAsignada") String noPersonaAsignada, 
				Pageable paginador);
		
		
		/**
		 * Permite obtener el reporte de expedientes por persona asignada
		 * @param evaluador
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
				+ "paau.idPasAux, paau.pgimPas.idPas, paau.pgimPas.coPas, paau.feCreacionPas, "
				+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
				+ "paau.nuExpedienteSigedSup, paau.flPropia, paau.noPersonaAsignada, "
				+ "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
				+ "paau.idPasoActual, paau.noPasoActual, paau.idRelacionPaso, "
				+ "paau.idTipoRelacion, paau.noDivisionSupervisora, paau.flLeido, "
				+ "paau.feLectura, paau.feInstanciaPaso, paau.noPersonaOrigen, "
				+ "paau.flPasoActivo, paau.deMensaje, paau.nuAlertas, "
				+ "paau.nuIntervaloAlertas, paau.idInstanciaProcesoPas, paau.idInstanciaPaso "
				+ ") "
				+ "FROM PgimPasAux paau "
				+ "WHERE 1 = 1 "
				+ "AND paau.flPasoActivo = '1' "
				+ "AND (:evaluador IS NULL OR LOWER(paau.noPersonaAsignada) LIKE LOWER(CONCAT('%', :evaluador, '%'))) "
				)
		List<PgimPasAuxDTO> obtenerPasAuxEvaluador(@Param("evaluador") String evaluador);
		
		
		/**
         * Permite obtener la lista preparada de expedientes con PAS por división supervisora y año usado en reporte correspondiente
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasdsuAnioAuxDTOResultado( "
                + "expp.noDivisionSupervisora, expp.noFaseProceso, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.totalFase "
                + ") " 
                + "FROM PgimExppasdsuAnioAux expp "
                + "ORDER BY expp.noDivisionSupervisora asc, expp.noFaseProceso asc "
                )
        List<PgimExppasdsuAnioAuxDTO> listarReporteExppasdsuAnio();

        /**
         * Permite obtener la lista preparada de expedientes con PAS por división supervisora y año usado en reporte correspondiente
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasdsuAnioAuxDTOResultado( "
        		+ "expp.noDivisionSupervisora, expp.noFaseProceso, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.totalFase "
        		+ ") " 
        		+ "FROM PgimExppasdsuAnioAux expp "
        		+ "ORDER BY expp.noDivisionSupervisora asc"
        		)
        List<PgimExppasdsuAnioAuxDTO> listarReporteExppasdsuAnioExportar(Sort sort);
        
        
        /**
         * Permite obtener la lista preparada de expedientes con PAS por división supervisora y año usado en reporte correspondiente de manera paginada
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasdsuAnioAuxDTOResultado( "
                + "expp.noDivisionSupervisora, expp.noFaseProceso, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.totalFase "
                + ") " 
                + "FROM PgimExppasdsuAnioAux expp "
                + "ORDER BY expp.noDivisionSupervisora asc "
                )
        Page<PgimExppasdsuAnioAuxDTO> listarReporteExppasdsuAnioPaginado(
                Pageable paginador); 
        
        
        /**
         * Permite obtener la lista preparada de expedientes con PAS por especialidad y mes usado en reporte correspondiente
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasespeMesAuxDTOResultado( "
                + "expp.idEspecialidad, expp.noEspecialidad, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.nroExpPasP7, "
				+ "expp.nroExpPasP8, expp.nroExpPasP9, expp.nroExpPasP10, "
				+ "expp.nroExpPasP11, expp.nroExpPasP12, expp.totalEspecialidad "
                + ") " 
                + "FROM PgimExppasespeMesAux expp "
                )
        List<PgimExppasespeMesAuxDTO> listarReporteExppasespeMes();

        /**
         * Permite obtener la lista preparada de expedientes con PAS por especialidad y mes usado en reporte correspondiente
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasespeMesAuxDTOResultado( "
        		+ "expp.idEspecialidad, expp.noEspecialidad, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.nroExpPasP7, "
				+ "expp.nroExpPasP8, expp.nroExpPasP9, expp.nroExpPasP10, "
				+ "expp.nroExpPasP11, expp.nroExpPasP12, expp.totalEspecialidad "
        		+ ") " 
        		+ "FROM PgimExppasespeMesAux expp "
        		)
        List<PgimExppasespeMesAuxDTO> listarReporteExppasespeMesExportar(Sort sort);
        
        
        /**
         * Permite obtener la lista preparada de expedientes con PAS por especialidad y mes usado en reporte correspondiente de manera paginada
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasespeMesAuxDTOResultado( "
                + "expp.idEspecialidad, expp.noEspecialidad, expp.nroExpPasP1, "
				+ "expp.nroExpPasP2, expp.nroExpPasP3, expp.nroExpPasP4, "
				+ "expp.nroExpPasP5, expp.nroExpPasP6, expp.nroExpPasP7, "
				+ "expp.nroExpPasP8, expp.nroExpPasP9, expp.nroExpPasP10, "
				+ "expp.nroExpPasP11, expp.nroExpPasP12, expp.totalEspecialidad "
                + ") " 
                + "FROM PgimExppasespeMesAux expp "
                )
        Page<PgimExppasespeMesAuxDTO> listarReporteExppasespeMesPaginado(
                Pageable paginador); 
        
        
        /**
		* Permite obtener la lista de PAS auxiliares para mostrarlos como parte del
		* listado oficial en la pantalla principal de fiscalización (exportar).
		* @param coSupervision
		* @param noUnidadMinera
		* @param asNoRazonSocial
		* @param feCreacionPasAnio
		* @param idEspecialidad
		* @param idFaseActual
		* @param idPasoActual
		* @param nuExpedienteSigedPas
		* @param usuarioInteres
		* @param flagMisAsignaciones
		* @param textoBusqueda
		* @param sort
		* @return
		*/
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
				+ "paau.idPasAux, paau.pgimPas.idPas, paau.pgimPas.coPas, paau.feCreacionPas, "
				+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
				+ "paau.nuExpedienteSigedSup, paau.flPropia, paau.noPersonaAsignada, "
				+ "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
				+ "paau.idPasoActual, paau.noPasoActual, paau.idRelacionPaso, "
				+ "paau.idTipoRelacion, paau.noDivisionSupervisora, paau.flLeido, "
				+ "paau.feLectura, paau.feInstanciaPaso, paau.noPersonaOrigen, "
				+ "paau.flPasoActivo, paau.deMensaje, paau.nuAlertas, "
				+ "paau.nuIntervaloAlertas, paau.idInstanciaProcesoPas, paau.idInstanciaPaso "
				+ ") " 
				+ "FROM PgimPasAux paau "
				+ "WHERE 1 = 1 "

				// Parámetros específicos
				+ "AND (:coSupervision IS NULL OR LOWER(paau.coSupervision) LIKE LOWER(CONCAT('%', :coSupervision, '%'))) "
				+ "AND (:noUnidadMinera IS NULL OR LOWER(paau.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))) "
				+ "AND (:asNoRazonSocial IS NULL OR LOWER(paau.asNoRazonSocial) LIKE LOWER(CONCAT('%', :asNoRazonSocial, '%'))) "
				+ "AND (:feCreacionPasAnio IS NULL OR LOWER(paau.feCreacionPasAnio) LIKE LOWER(CONCAT('%', :feCreacionPasAnio, '%'))) "
				+ "AND (:idEspecialidad IS NULL OR paau.idEspecialidad = :idEspecialidad) "
				+ "AND (:idFaseActual IS NULL OR paau.idFaseActual = :idFaseActual ) "
				+ "AND (:idPasoActual IS NULL OR paau.idPasoActual = :idPasoActual) "
				+ "AND (:nuExpedienteSigedPas IS NULL OR paau.nuExpedienteSigedPas = :nuExpedienteSigedPas) "

				// Parámetros de flujo
				+ "AND (:usuarioInteres IS NULL "
				+ "OR EXISTS (SELECT 1 "
				+ "           FROM PgimEqpInstanciaPro eipy "
				+ "                INNER JOIN eipy.pgimRolProceso ropr "
				+ "                INNER JOIN eipy.pgimPersonalOsi peos "
				+ "           WHERE eipy.pgimInstanciaProces.idInstanciaProceso = paau.idInstanciaProcesoPas "
				+ "           AND ropr.idRolProceso = 27 "
				+ "           AND eipy.esRegistro = '1' "
				+ "           AND peos.noUsuario = :usuarioInteres "
				+ "           ) "
				+ ") " 
				+ "AND ("
				// Mis tareas
				+ "		(:flagMisAsignaciones = '1' "
				+ "		  AND paau.flPasoActivo = '1' "
				+ "		  AND LOWER(paau.noUsuarioAsignado) = LOWER(:descUsuarioAsignado) "
				+ "		  AND paau.idTipoRelacion NOT IN (291, 292) " 
				+ "		 ) " 
				+ "		 OR " 
				// Tareas enviadas
				+ "		(:flagMisAsignaciones = '2' "
				+ "		  AND LOWER(paau.noUsuarioOrigen) = LOWER(:noUsuarioOrigen) "
				+ "       AND ("
				+ "             :descPersonaDestino IS NULL "
				+ "             OR LOWER(paau.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
				+ "           ) " 						
				+ "		 ) " 
				+ "		 OR " 	
				// Todas las tareas
				+ "     (:flagMisAsignaciones = '0' "
				+ "		  AND paau.flPasoActivo = '1' "
				+ "       AND ("
				+ "             :descPersonaDestino IS NULL "
				+ "             OR LOWER(paau.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
				+ "            )"
				+ "      ) "
				+ " ) "
				// Búsqueda general
				+ "AND ("
		        + ":textoBusqueda IS NULL "
		        + "OR LOWER(paau.coSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		        + "OR LOWER(paau.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
		        + "OR LOWER(paau.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		        + "OR LOWER(paau.asNoRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		        + "OR paau.nuExpedienteSigedPas LIKE CONCAT('%', :textoBusqueda, '%') "
		        + " ) ")
		List<PgimPasAuxDTO> filtrarReporte(@Param("coSupervision") String coSupervision,
		        @Param("noUnidadMinera") String noUnidadMinera,
		        @Param("asNoRazonSocial") String asNoRazonSocial,
		        @Param("feCreacionPasAnio") String feCreacionPasAnio,
		        @Param("idEspecialidad") Long idEspecialidad, 
		        @Param("idFaseActual") Long idFaseActual,
		        @Param("idPasoActual") Long idPasoActual,
		        @Param("nuExpedienteSigedPas") String nuExpedienteSigedPas,
				@Param("noUsuarioOrigen") String noUsuarioOrigen,
		        @Param("descUsuarioAsignado") String descUsuarioAsignado,
				@Param("descPersonaDestino") String descPersonaDestino,
		        @Param("usuarioInteres") String usuarioInteres,
		        @Param("flagMisAsignaciones") String flagMisAsignaciones,
		        @Param("textoBusqueda") String textoBusqueda, 
		        Sort sort);
		
		/**
		* Permite obtener la lista de PAS auxiliares para mostrarlos como parte del
		* listado oficial en la pantalla principal de fiscalización (exportar).
		* @param idUnidadMinera
		* @param asNoRazonSocial
		* @param descCoPas
		* @param idEspecialidad
		* @param idFaseActual
		* @param idPasoActual
		* @param descPrograma
		* @return
		*/
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
				+ "paau.idPasAux, paau.pgimPas.idPas, paau.idInstanciaPaso, "
				+ "paau.idSupervision, paau.coSupervision, "
				+ "paau.coPas, paau.feOrigenDocumento, paau.incumplimientos, "
				+ "paau.infracciones, paau.idProgramaSupervision, paau.descPrograma, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.idDivisionSupervisora, "
				+ "paau.noDivisionSupervisora, paau.nuExpedienteSigedPas, paau.noPersonaAsignada, "
				+ "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
				+ "paau.idPasoActual, paau.noPasoActual, paau.idInstanciaProcesoPas, "
				+ "paau.noPersonaOrigen, paau.noUsuarioOrigen, paau.flPasoActivo, "
				+ "paau.idMotivoSupervision, paau.deMotivoSupervision "
				+ ") " 
				+ "FROM PgimPasUnidadFiscAux paau "
				+ "WHERE 1 = 1 "
				+ "AND paau.flPasoActivo = '1' "
				+ "AND paau.idUnidadMinera = :idUnidadMinera "
				+ "AND (:descCoPas IS NULL OR LOWER(paau.coPas) LIKE LOWER(CONCAT('%', :descCoPas, '%')) ) "
				+ "AND (:idEspecialidad IS NULL OR paau.idEspecialidad = :idEspecialidad) "
                + "AND (:feOrigenDocumento IS NULL OR paau.feOrigenDocumento = :feOrigenDocumento) "
                + "AND (:incumplimientos IS NULL OR paau.incumplimientos = :incumplimientos) "
                + "AND (:infracciones IS NULL OR paau.infracciones = :infracciones) "
				+ "AND (:noPersonaAsignada IS NULL OR LOWER(paau.noPersonaAsignada) LIKE LOWER(CONCAT('%', :noPersonaAsignada, '%')) ) "
				+ "AND (:idFaseActual IS NULL OR paau.idFaseActual = :idFaseActual ) "
                + "AND (:idPasoActual IS NULL OR paau.idPasoActual = :idPasoActual) "
				+ "AND (:descIdMotivoSupervision IS NULL OR paau.idMotivoSupervision = :descIdMotivoSupervision) "
				+ "AND (:descPrograma IS NULL OR LOWER(paau.descPrograma) LIKE LOWER(CONCAT('%', :descPrograma, '%')) ) "
				+ "AND (:asNoRazonSocial IS NULL OR paau.asNoRazonSocial = :asNoRazonSocial) "
				)
		List<PgimPasAuxDTO> listarPasEnGeneral(@Param("idUnidadMinera") Long idUnidadMinera,
												@Param("descCoPas") String descCoPas,										
												@Param("idEspecialidad") Long idEspecialidad,
												@Param("feOrigenDocumento") Date feOrigenDocumento,
												@Param("incumplimientos") Long cantidadIncumplimientosF,
												@Param("infracciones") Long infracciones,
												@Param("noPersonaAsignada") String noPersonaAsignada,
												@Param("idFaseActual") Long idFaseActual,
												@Param("idPasoActual") Long idPasoActual,
												@Param("descIdMotivoSupervision") Long descIdMotivoSupervision,
												@Param("descPrograma") String descPrograma,
												@Param("asNoRazonSocial") String asNoRazonSocial
		);

		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
				+ "paau.idPasAux, paau.pgimPas.idPas, paau.idInstanciaPaso, "
				+ "paau.idSupervision, paau.coSupervision, "
				+ "paau.coPas, paau.feOrigenDocumento, paau.incumplimientos, "
				+ "paau.infracciones, paau.idProgramaSupervision, paau.descPrograma, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.idDivisionSupervisora, "
				+ "paau.noDivisionSupervisora, paau.nuExpedienteSigedPas, paau.noPersonaAsignada, "
				+ "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
				+ "paau.idPasoActual, paau.noPasoActual, paau.idInstanciaProcesoPas, "
				+ "paau.noPersonaOrigen, paau.noUsuarioOrigen, paau.flPasoActivo, "
				+ "paau.idMotivoSupervision, paau.deMotivoSupervision "
				+ ") " 
				+ "FROM PgimPasUnidadFiscAux paau "
				+ "WHERE 1 = 1 "
				+ "AND paau.flPasoActivo = '1' "
				+ "AND paau.idUnidadMinera = :idUnidadMinera "
				+ "AND (:descCoPas IS NULL OR LOWER(paau.coPas) LIKE LOWER(CONCAT('%', :descCoPas, '%')) ) "
				+ "AND (:idEspecialidad IS NULL OR paau.idEspecialidad = :idEspecialidad) "
                + "AND (:feOrigenDocumento IS NULL OR paau.feOrigenDocumento = :feOrigenDocumento) "
                + "AND (:incumplimientos IS NULL OR paau.incumplimientos = :incumplimientos) "
                + "AND (:infracciones IS NULL OR paau.infracciones = :infracciones) "
				+ "AND (:noPersonaAsignada IS NULL OR LOWER(paau.noPersonaAsignada) LIKE LOWER(CONCAT('%', :noPersonaAsignada, '%')) ) "
				+ "AND (:idFaseActual IS NULL OR paau.idFaseActual = :idFaseActual ) "
                + "AND (:idPasoActual IS NULL OR paau.idPasoActual = :idPasoActual) "
				+ "AND (:descIdMotivoSupervision IS NULL OR paau.idMotivoSupervision = :descIdMotivoSupervision) "
				+ "AND (:descPrograma IS NULL OR LOWER(paau.descPrograma) LIKE LOWER(CONCAT('%', :descPrograma, '%')) ) "
				+ "AND (:asNoRazonSocial IS NULL OR paau.asNoRazonSocial = :asNoRazonSocial) "
				)
		Page<PgimPasAuxDTO> listarPasEnGeneralPaginado(@Param("idUnidadMinera") Long idUnidadMinera, 												
														@Param("descCoPas") String descCoPas, 
														@Param("idEspecialidad") Long idEspecialidad,
														@Param("feOrigenDocumento") Date feOrigenDocumento,
														@Param("incumplimientos") Long incumplimientos,
														@Param("infracciones") Long infracciones,
														@Param("noPersonaAsignada") String noPersonaAsignada,
														@Param("idFaseActual") Long idFaseActual,
														@Param("idPasoActual") Long idPasoActual,
														@Param("descIdMotivoSupervision") Long descIdMotivoSupervision,
														@Param("descPrograma") String descPrograma,
														@Param("asNoRazonSocial") String asNoRazonSocial,
														Pageable paginador);
		
		/**
		 * Permite obtener la lista preparada de expedientes con PAS por tipo de sustancia y año usado en reporte correspondiente de manera paginada
		 * @param paginador
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppastipsustAnioAuxDTOResultado( "
                + "expp.noTipoSustancia, expp.nroExpPasP1, expp.nroExpPasP2, "
				+ "expp.nroExpPasP3, expp.nroExpPasP4, expp.nroExpPasP5, "
				+ "expp.nroExpPasP6, expp.totalNroExp "
                + ") " 
                + "FROM PgimExppastipsustAnioAux expp "
                )
        Page<PgimExppastipsustAnioAuxDTO> listarReporteExppastipsustAnioPaginado(
        		Pageable paginador);
		
		
		/**
		 * Permite obtener la lista preparada de expedientes con PAS por tipo de sustancia y año usado en reporte correspondiente
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppastipsustAnioAuxDTOResultado( "
                + "expp.noTipoSustancia, expp.nroExpPasP1, expp.nroExpPasP2, "
				+ "expp.nroExpPasP3, expp.nroExpPasP4, expp.nroExpPasP5, "
				+ "expp.nroExpPasP6, expp.totalNroExp "
                + ") " 
                + "FROM PgimExppastipsustAnioAux expp "
                )
        List<PgimExppastipsustAnioAuxDTO> listarReporteExppastipsustAnio(Sort sort);
		
		/**
		 * Permite obtener la lista preparada de expedientes con PAS por especialidad y año usado en reporte correspondiente de manera paginado
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasEspAnioAuxDTOResultado( "
	            + "expp.noEspecialidad, expp.nroExpPasP1, expp.nroExpPasP2, "
				+ "expp.nroExpPasP3, expp.nroExpPasP4, expp.nroExpPasP5, "
				+ "expp.nroExpPasP6, expp.totalNroExp "
	            + ") " 
	            + "FROM PgimExppasEspAnioAux expp "
				)
	    Page<PgimExppasEspAnioAuxDTO> listarReporteExpPasEspecAnioPaginado(Pageable paginador);

		/**
		 * Permite obtener la lista preparada de expedientes con PAS por especialidad y año usado en reporte correspondiente
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasEspAnioAuxDTOResultado( "
				+ "expp.noEspecialidad, expp.nroExpPasP1, expp.nroExpPasP2, "
				+ "expp.nroExpPasP3, expp.nroExpPasP4, expp.nroExpPasP5, "
				+ "expp.nroExpPasP6, expp.totalNroExp "
				+ ") " 
				+ "FROM PgimExppasEspAnioAux expp "
				)
		List<PgimExppasEspAnioAuxDTO> listarReporteExpPasEspecAnio(Sort sort);
		
		
		/**
		 * Permite obtener la lista preparada de expedientes con PAS asignado a evaluador por DS y especialidad usado en reporte correspondiente de manera paginado
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasEvaluadorAuxDTOResultado( "
				+ "expp.noDivisionSupervisora, expp.noEspecialidad, expp.noEvaluador, "
				+ "expp.nroExpPasP1, expp.nroExpPasP2, expp.nroExpPasP3, "
				+ "expp.nroExpPasP4, expp.nroExpPasP5, expp.nroExpPasP6, "
				+ "expp.nroExpPasTotal "
				+ ") " 
				+ "FROM PgimExppasEvaluadorAux expp "
				+ "WHERE 1 = 1 "
				+ "AND (:noEvaluador IS NULL OR LOWER(expp.noEvaluador) = LOWER(:noEvaluador)) "
		        + "AND (:idEspecialidad IS NULL OR expp.pgimEspecialidad.idEspecialidad = :idEspecialidad) "
		        + "AND (:idDivisionSupervisora IS NULL OR expp.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
		        + "ORDER BY expp.noDivisionSupervisora desc"
				)
		Page<PgimExppasEvaluadorAuxDTO> listarReporteExpPasPerDsEspecPaginado(
		        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
		        @Param("idEspecialidad") Long idEspecialidad,
		        @Param("noEvaluador") String noEvaluador,
				Pageable paginador);
		
		/**
		 * Permite obtener la lista preparada de expedientes con PAS asignado a evaluador por DS y especialidad usado en reporte correspondiente
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasEvaluadorAuxDTOResultado( "
				+ "expp.noDivisionSupervisora, expp.noEspecialidad, expp.noEvaluador, "
				+ "expp.nroExpPasP1, expp.nroExpPasP2, expp.nroExpPasP3, "
				+ "expp.nroExpPasP4, expp.nroExpPasP5, expp.nroExpPasP6, "
				+ "expp.nroExpPasTotal "
				+ ") " 
				+ "FROM PgimExppasEvaluadorAux expp "
				+ "WHERE 1 = 1 "
				+ "AND (:noEvaluador IS NULL OR LOWER(expp.noEvaluador) LIKE LOWER(CONCAT('%', :noEvaluador, '%'))) "
		        + "AND (:idEspecialidad IS NULL OR expp.pgimEspecialidad.idEspecialidad = :idEspecialidad) "
		        + "AND (:idDivisionSupervisora IS NULL OR expp.divisionSupervisora.idValorParametro = :idDivisionSupervisora ) "
		        + "ORDER BY expp.noDivisionSupervisora desc"
				)
		List<PgimExppasEvaluadorAuxDTO> listarReporteExpPasPerDsEspec(
		        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
		        @Param("idEspecialidad") Long idEspecialidad,
		        @Param("noEvaluador") String noEvaluador,
		        Sort sort);
		
		
        /**
         * Permite obtener la lista preparada de expedientes detallados con PAS usado en reporte correspondiente de manera paginada
         * @param idFaseActual
         * @param paginador
         * @return
         */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
		        + "paau.idPasAux, paau.pgimPas.idPas, paau.feCreacionPas, "
				+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
				+ "paau.nuExpedienteSigedSup, paau.noPersonaAsignada, paau.noUsuarioAsignado, "
				+ "paau.idFaseActual, paau.noFaseActual, paau.idPasoActual, "
				+ "paau.noPasoActual, paau.idRelacionPaso, paau.idTipoRelacion, "
				+ "paau.feInstanciaPaso, paau.diasTranscurridos, "
				+ "paau.noDivisionSupervisora "
				+ ") " 
		        + "FROM PgimPasAux paau "
		        + "WHERE paau.flPasoActivo = '1' "
		        + "AND paau.idTipoRelacion NOT IN (:REPAS_CNCLAR_FLJO, :REPAS_FNLZAR_FLJO) "
		        + "AND (:idFaseActual IS NULL OR paau.idFaseActual = :idFaseActual) "
		        + "AND (:idPasoActual IS NULL OR paau.idPasoActual = :idPasoActual) "
		       )
		Page<PgimPasAuxDTO> listarReporteExpPasProcesoPaginado(
				@Param("idFaseActual") Long idFaseActual, 
				@Param("idPasoActual") Long idPasoActual, 
				Pageable paginador, @Param("REPAS_CNCLAR_FLJO") Long REPAS_CNCLAR_FLJO, @Param("REPAS_FNLZAR_FLJO") Long REPAS_FNLZAR_FLJO);
		
		/**
		 * Permite obtener la lista preparada de expedientes detallados con PAS usado en reporte correspondiente de manera paginada
		 * @param idFaseActual
		 * @param sort
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
				+ "paau.idPasAux, paau.pgimPas.idPas, paau.feCreacionPas, "
				+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
				+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
				+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
				+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
				+ "paau.nuExpedienteSigedSup, paau.noPersonaAsignada, paau.noUsuarioAsignado, "
				+ "paau.idFaseActual, paau.noFaseActual, paau.idPasoActual, "
				+ "paau.noPasoActual, paau.idRelacionPaso, paau.idTipoRelacion, "
				+ "paau.feInstanciaPaso, paau.diasTranscurridos, paau.noDivisionSupervisora "
				+ ") " 
				+ "FROM PgimPasAux paau "
				+ "WHERE 1 = 1 "				
				+ "AND paau.flPasoActivo = '1' "
		        + "AND (:idFaseActual IS NULL OR paau.idFaseActual = :idFaseActual) "
		        + "AND (:idPasoActual IS NULL OR paau.idPasoActual = :idPasoActual) "
		        + "AND paau.idTipoRelacion NOT IN (:REPAS_CNCLAR_FLJO,"
		        + "								   :REPAS_FNLZAR_FLJO) "
				)
		List<PgimPasAuxDTO> listarReporteExpPasProceso(
				@Param("idFaseActual") Long idFaseActual, 
				@Param("idPasoActual") Long idPasoActual,
				@Param("REPAS_CNCLAR_FLJO") Long REPAS_CNCLAR_FLJO, @Param("REPAS_FNLZAR_FLJO") Long REPAS_FNLZAR_FLJO,
				Sort sort);

		/**
		 * Permite obtener la lista preparada de expedientes con PAS por tipo de actividad y año usado en reporte correspondiente de manera paginada
		 * @param paginador
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppastipactiAnioAuxDTOResultado( "
				+ "expp.noTipoActividad, expp.nroExpPasP1, expp.nroExpPasP2, "
				+ "expp.nroExpPasP3, expp.nroExpPasP4, expp.nroExpPasP5, "
				+ "expp.nroExpPasP6, expp.totalNroExp "
				+ ") "
				+ "FROM PgimExppastipactiAnioAux expp ")
		Page<PgimExppastipactiAnioAuxDTO> listarReporteExppastipactiAnioPaginado(
				Pageable paginador);
		
		/**
		 * Permite obtener la lista preparada de expedientes con PAS por tipo de actividad y año usado en reporte correspondiente
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppastipactiAnioAuxDTOResultado( "
				+ "expp.noTipoActividad, expp.nroExpPasP1, expp.nroExpPasP2, "
				+ "expp.nroExpPasP3, expp.nroExpPasP4, expp.nroExpPasP5, "
				+ "expp.nroExpPasP6, expp.totalNroExp  "
				+ ") "
				+ "FROM PgimExppastipactiAnioAux expp ")
		List<PgimExppastipactiAnioAuxDTO> listarReporteExppastipactiAnio(Sort sort);
		
		
		
		/**
			 * Permite obtener la lista preparada de expedientes con PAS por estado de resolución, ds y especialidad usado en reporte correspondiente de manera paginada
			 * @param paginador
			 * @return
			 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasEstResolAuxDTOResultado( "
				+ "expp.divisionSupervisora.idValorParametro, expp.deDivisionSupervisora, expp.noDivisionSupervisora, expp.coEstado, expp.noEstado, expp.anioSuper, expp.nuGeomecania, expp.nuGeotecania, expp.nuPlantaBene, expp.nuTransporte, expp.nuVentilacion, expp.nuTotal "
				+ ") "
				+ "FROM PgimExppasEstResolAux expp "
				+ "WHERE (expp.anioSuper = :anioSuper) "
				+ "ORDER BY expp.deDivisionSupervisora asc ")
		Page<PgimExppasEstResolAuxDTO> listarReporteExppasEstResolPaginado(
				@Param("anioSuper") String anioSuper,
				Pageable paginador);
		
		/**
		 * Permite obtener la lista preparada de expedientes con PAS por estado de resolución, ds y especialidad usado en reporte correspondiente 
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExppasEstResolAuxDTOResultado( "
				+ "expp.divisionSupervisora.idValorParametro, expp.deDivisionSupervisora, expp.noDivisionSupervisora, "
				+ "expp.coEstado, expp.noEstado, expp.anioSuper, "
				+ "expp.nuGeomecania, expp.nuGeotecania, expp.nuPlantaBene, "
				+ "expp.nuTransporte, expp.nuVentilacion, expp.nuTotal "
				+ ") "
				+ "FROM PgimExppasEstResolAux expp "
				+ "WHERE (expp.anioSuper = :anioSuper) "
				+ "ORDER BY expp.deDivisionSupervisora asc ")
		List<PgimExppasEstResolAuxDTO> listarReporteExppasEstResol(
				@Param("anioSuper") String anioSuper,
				Sort sort);


		/**
	     * Permite obtener la lista preparada de documentos por expediente usado en reporte correspondiente de manera paginada
	     * @param paginador
	     * @return
	     */
	    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoAuxDTOResultado( "
                    + "doc.nuExpedienteSiged, doc.noFaseProceso, doc.coCategoriaDocumento, "
					+ "doc.noCategoriaDocumento, doc.coSubcatDocumento, doc.noSubcatDocumento, "
					+ "doc.deAsuntoDocumento, doc.noTipoOrigenDocumento, doc.noUsuario, "
					+ "doc.feCreacion "
                    + ") " 
                    + "FROM PgimDocumentoAux doc "
                    + "WHERE (doc.nuExpedienteSiged = :nuExpedienteSiged) "
                    )
	    Page<PgimDocumentoAuxDTO> listarReporteExpDocsPaginado(
            @Param("nuExpedienteSiged") String nuExpedienteSiged, 
                Pageable paginador);

	    
	    /**
	     * Permite obtener la lista preparada de documentos por expediente usado en reporte correspondiente de manera paginada
	     * @param sort
	     * @return
	     */
	    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoAuxDTOResultado( "
                    + "doc.nuExpedienteSiged, doc.noFaseProceso, doc.coCategoriaDocumento, "
					+ "doc.coCategoriaDocumento || ' - ' || doc.noCategoriaDocumento, doc.coSubcatDocumento, doc.coSubcatDocumento || ' - ' || doc.noSubcatDocumento, "
					+ "doc.deAsuntoDocumento, doc.noTipoOrigenDocumento, doc.noUsuario, "
					+ "doc.feCreacion "
                    + ") " 
                    + "FROM PgimDocumentoAux doc "
                    + "WHERE (doc.nuExpedienteSiged = :nuExpedienteSiged) "
                    )
	    List<PgimDocumentoAuxDTO> listarReporteExpDocs(
            @Param("nuExpedienteSiged") String nuExpedienteSiged, 
            Sort sort);
	    
	    	    
	    
	    /**
         * Permite obtener datos de cabecera de un PAS buscado por numero de expediente
         * 
         * @param nuExpedienteSiged
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
                + "aux.pgimPas.idPas, aux.nuExpedienteSigedPas, aux.idUnidadMinera, "
				+ "aux.noUnidadMinera, aux.asNoRazonSocial, aux.asCoDocumentoIdentidad "
				+ ")"
                + "FROM PgimPasAux aux "
                + "	    INNER JOIN aux.pgimPas pas "
                + "WHERE pas.esRegistro = '1' " 
				+ "AND aux.flPasoActivo = '1' "
                + "AND aux.nuExpedienteSigedPas = :nuExpedienteSiged ")
        PgimPasAuxDTO obtenerCabecPasPorExpediente(@Param("nuExpedienteSiged") String nuExpedienteSiged);
        
        /**
         * Permite listar las tareas activas de un PAS por su IdPas, 
         * y si se desea, de una relacion de paso dada
         * 
         * @param idPas				Id del PAS a consultar
         * @param idRelacionPaso	Id de la relación de paso que se desea filtrar. 
         * 							Si es nulo retornará todas las tareas activas del PAS
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTOResultado("
                        + "paau.idPasAux, paau.pgimPas.idPas, paau.pgimPas.coPas, paau.feCreacionPas, "
						+ "paau.feCreacionPasAnio, paau.idSupervision, paau.coSupervision, "
						+ "paau.idEspecialidad, paau.noEspecialidad, paau.idUnidadMinera, "
						+ "paau.coUnidadMinera, paau.noUnidadMinera, paau.idAgenteSupervisado, "
						+ "paau.asCoDocumentoIdentidad, paau.asNoRazonSocial, paau.nuExpedienteSigedPas, "
						+ "paau.nuExpedienteSigedSup, paau.flPropia, paau.noPersonaAsignada, "
                        + "paau.noUsuarioAsignado, paau.idFaseActual, paau.noFaseActual, "
						+ "paau.idPasoActual, paau.noPasoActual, paau.idRelacionPaso, "
						+ "paau.idTipoRelacion, paau.noDivisionSupervisora, paau.flLeido, "
						+ "paau.feLectura, paau.feInstanciaPaso, paau.noPersonaOrigen, "
						+ "paau.flPasoActivo, paau.deMensaje, paau.nuAlertas, "
						+ "paau.nuIntervaloAlertas, paau.idInstanciaProcesoPas, paau.idInstanciaPaso "
						+ ") " 
                        + "FROM PgimPasAux paau "
                        + "WHERE 1 = 1 "
                        + "AND paau.pgimPas.idPas = :idPas "
                        + "AND paau.flPasoActivo = '1' "
                        + "AND (:idRelacionPaso IS NULL OR paau.idRelacionPaso = :idRelacionPaso) "
                        + "ORDER BY paau.idInstanciaPaso DESC "
						)
        List<PgimPasAuxDTO> listarTareasPasActivasPorId(@Param("idPas") Long idPas, @Param("idRelacionPaso") Long idRelacionPaso);
        
        
        /**
         * Permite obtener la cantidad de PAS pendientes de atención que tiene un usuario
         * 
         * @param descUsuarioAsignado
         * @return
         */
        @Query("SELECT COUNT(paau.idPasAux) " 
                + "FROM PgimPasAux paau "
                + "WHERE "               
				+ "paau.flPasoActivo = '1' "
				+ "AND LOWER(paau.noUsuarioAsignado) = LOWER(:descUsuarioAsignado) "
				+ "AND paau.idTipoRelacion NOT IN (291, 292) " 
                )
        Integer contarPasPendientes(
                @Param("descUsuarioAsignado") String descUsuarioAsignado
                );
}