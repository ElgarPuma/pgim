package pe.gob.osinergmin.pgim.models.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.ItemMedicionIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedicionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMedicion;

/**
 * @descripción: Logica de negocio de la entidad medicion.
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 23/10/2023
 */
@Repository
public interface MedicionRepository extends JpaRepository<PgimMedicion, Long> {

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedicionDTOResultado("
      + "med.idMedicion, indi.idIndicador, espe.idEspecialidad, "
      + "divsup.idValorParametro, med.coMedicion, med.noMedicion, "
      + "med.deMedicion, med.esPublicacion, med.feInicial, med.feFinal, "

      + "indi.pgimProceso.idProceso, indi.pgimProceso.noProceso, indi.tipoUnidadMedida.idValorParametro, "
      + "indi.tipoUnidadMedida.noValorParametro, "
      + "indi.relacionPasoOrigen.pasoProcesoOrigen.noPasoProceso || '  =>  ' || indi.relacionPasoOrigen.pasoProcesoDestino.noPasoProceso, "
      + "indi.relacionPasoDestino.pasoProcesoOrigen.noPasoProceso || '  =>  ' || indi.relacionPasoDestino.pasoProcesoDestino.noPasoProceso, "
      + "indi.tipoAgrupadoPor.idValorParametro, indi.tipoAgrupadoPor.noValorParametro, tact.idValorParametro, "
      + "tact.noValorParametro, tcar.idValorParametro, tcar.noValorParametro, indi.tipoAgrupadoPor.coClaveTexto, "
      + "med.usCreacion, indi.coIndicador, indi.noIndicador, "
      + "espe.noEspecialidad, divsup.noValorParametro "
      + ") "
      + "FROM PgimMedicion med "
      + "INNER JOIN med.pgimIndicador indi "
      + "LEFT JOIN med.pgimEspecialidad espe "
      + "LEFT JOIN med.divisionSupervisora divsup "
      + "LEFT JOIN indi.tipoCaracterFisc tcar "
      + "LEFT JOIN indi.tipoActorNegocio tact "
      + "WHERE med.esRegistro = '1' "
      + "AND med.idMedicion = :idMedicion")
  PgimMedicionDTO obtenerMedicionPorId(@Param("idMedicion") Long idMedicion);

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedicionDTOResultado("
      + "med.idMedicion, med.pgimIndicador.idIndicador, espe.idEspecialidad, "
      + "divsup.idValorParametro, med.coMedicion, med.noMedicion, "
      + "med.deMedicion, med.esPublicacion, med.feInicial, med.feFinal, "

      + "indi.pgimProceso.idProceso, indi.pgimProceso.noProceso, indi.tipoUnidadMedida.idValorParametro, "
      + "indi.tipoUnidadMedida.noValorParametro, "
	  + "indi.relacionPasoOrigen.pasoProcesoOrigen.noPasoProceso || '  =>  ' || indi.relacionPasoOrigen.pasoProcesoDestino.noPasoProceso, "
      + "indi.relacionPasoDestino.pasoProcesoOrigen.noPasoProceso || '  =>  ' || indi.relacionPasoDestino.pasoProcesoDestino.noPasoProceso, "
      + "indi.tipoAgrupadoPor.idValorParametro, indi.tipoAgrupadoPor.noValorParametro, tact.idValorParametro, "
      + "tact.noValorParametro, tcar.idValorParametro, tcar.noValorParametro, indi.tipoAgrupadoPor.coClaveTexto, "
      + "med.usCreacion, indi.coIndicador, indi.noIndicador, "
      + "espe.noEspecialidad, divsup.noValorParametro "
      + ") "
      + "FROM PgimMedicion med "
      + "INNER JOIN med.pgimIndicador indi "
      + "LEFT JOIN med.pgimEspecialidad espe "
      + "LEFT JOIN med.divisionSupervisora divsup "
      + "LEFT JOIN indi.tipoCaracterFisc tcar "
      + "LEFT JOIN indi.tipoActorNegocio tact "
      + "WHERE med.esRegistro = '1' "
      + "AND indi.esRegistro = '1' "
      + "AND (med.esPublicacion = '1' OR (med.esPublicacion = '0' AND LOWER(med.usCreacion) = LOWER(:usCreacion))) "
      + "AND (:textoBusqueda IS NULL OR ( "
      + "LOWER(indi.coIndicador) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
      + "OR LOWER(indi.noIndicador) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
      + "OR LOWER(med.deMedicion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
      + "OR LOWER(med.noMedicion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
      + "OR LOWER(med.coMedicion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
      + "))"
      )

  Page<PgimMedicionDTO> listarMediciones(@Param("textoBusqueda") String textoBusqueda, @Param("usCreacion") String usCreacion, Pageable paginador);

  @Query("SELECT max(med.coMedicion) "
      + "FROM PgimMedicion med "
      + "WHERE 1=1 "
      + "ORDER BY med.idMedicion desc")
	String obtenerUltimoCoCorrelativoMedicion();


  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedicionDTOResultado("
      + "med.idMedicion, indi.idIndicador, espe.idEspecialidad, "
      + "divsup.idValorParametro, med.coMedicion, med.noMedicion, "
      + "med.deMedicion, med.esPublicacion, med.feInicial, med.feFinal, "

	  + "indi.pgimProceso.idProceso, indi.pgimProceso.noProceso, indi.tipoUnidadMedida.idValorParametro, "
      + "indi.tipoUnidadMedida.noValorParametro, "
	  + "indi.relacionPasoOrigen.pasoProcesoOrigen.noPasoProceso || '  =>  ' || indi.relacionPasoOrigen.pasoProcesoDestino.noPasoProceso, "
      + "indi.relacionPasoDestino.pasoProcesoOrigen.noPasoProceso || '  =>  ' || indi.relacionPasoDestino.pasoProcesoDestino.noPasoProceso, "
      + "indi.tipoAgrupadoPor.idValorParametro, indi.tipoAgrupadoPor.noValorParametro, tact.idValorParametro, "
      + "tact.noValorParametro, tcar.idValorParametro, tcar.noValorParametro, indi.tipoAgrupadoPor.coClaveTexto, "
      + "med.usCreacion, indi.coIndicador, indi.noIndicador, "
      + "espe.noEspecialidad, divsup.noValorParametro "
      + ") "
      + "FROM PgimMedicion med "
      + "INNER JOIN med.pgimIndicador indi "
      + "LEFT JOIN med.pgimEspecialidad espe "
      + "LEFT JOIN med.divisionSupervisora divsup "
      + "LEFT JOIN indi.tipoCaracterFisc tcar "
      + "LEFT JOIN indi.tipoActorNegocio tact "
      + "WHERE med.esRegistro = '1' "
      + "AND indi.idIndicador = :idIndicador")
  List<PgimMedicionDTO> obtenerMedicionesPorIdIndicador(@Param("idIndicador") Long idIndicador);
  
  
// EJECUCIÓN DE MEDICIONES
  
  /**
   * Permite listar los items de una medición de indicadores de Fiscalización por actor de negocio "Agente fiscalizado"
   * 
   * @param idIndicador
   * @param feDesde
   * @param feHasta
   * @param idEspecialidad
   * @param idDivisionSupervisora
   * @return
   */
  @Query(value = "SELECT peaf.CO_DOCUMENTO_IDENTIDAD AS rucAgenteSupervisado, "
			+ "	NVL(fisc.NO_RAZON_SOCIAL_AFISCALIZADO, peaf.NO_RAZON_SOCIAL) AS noAgenteSupervisado, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 2 " // -- Fiscalizacion
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (inpr.ID_INSTANCIA_PROCESO = fisc.ID_INSTANCIA_PROCESO "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TP_VALOR_PARAMETRO disu ON (prog.ID_DIVISION_SUPERVISORA = disu.ID_VALOR_PARAMETRO) "
			+ "     INNER JOIN PGIM.PGIM_TM_ESPECIALIDAD espe ON (prog.ID_ESPECIALIDAD = espe.ID_ESPECIALIDAD) "
			+ "     INNER JOIN PGIM.PGIM_TM_UNIDAD_MINERA unfi ON (fisc.ID_UNIDAD_MINERA = unfi.ID_UNIDAD_MINERA "
			+ "                                               AND unfi.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_AGENTE_SUPERVISADO afis ON (NVL(fisc.ID_AGENTE_SUPERVISADO, unfi.ID_AGENTE_SUPERVISADO) = afis.ID_AGENTE_SUPERVISADO "
			+ "                                                    AND afis.ES_REGISTRO = '1')   "
			+ "     INNER JOIN PGIM.PGIM_TM_PERSONA peaf ON (afis.ID_PERSONA = peaf.ID_PERSONA "
			+ "                                         AND peaf.ES_REGISTRO = '1') "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:idEspecialidad = 0 OR espe.ID_ESPECIALIDAD = :idEspecialidad) "
          	+ "AND (:idDivisionSupervisora = 0 OR disu.ID_VALOR_PARAMETRO = :idDivisionSupervisora) "
			// Agrupado por agente fiscalizado
			+ "GROUP BY peaf.CO_DOCUMENTO_IDENTIDAD, "
			+ "         NVL(fisc.NO_RAZON_SOCIAL_AFISCALIZADO, peaf.NO_RAZON_SOCIAL) " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionFisPorActorNegocioAF(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora
			);
	
  /**
   * Permite listar los items de una medición de indicadores de Fiscalización por actor de negocio "Unidad fiscalizada"
   * 
   * @param idIndicador
   * @param feDesde
   * @param feHasta
   * @param idEspecialidad
   * @param idDivisionSupervisora
   * @return
   */
	@Query(value = "SELECT unfi.CO_UNIDAD_MINERA AS coUnidadMinera, "
			+ "	unfi.NO_UNIDAD_MINERA AS noUnidadMinera, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 2 " // -- Fiscalizacion
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (inpr.ID_INSTANCIA_PROCESO = fisc.ID_INSTANCIA_PROCESO "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TP_VALOR_PARAMETRO disu ON (prog.ID_DIVISION_SUPERVISORA = disu.ID_VALOR_PARAMETRO) "
			+ "     INNER JOIN PGIM.PGIM_TM_ESPECIALIDAD espe ON (prog.ID_ESPECIALIDAD = espe.ID_ESPECIALIDAD) "
			+ "     INNER JOIN PGIM.PGIM_TM_UNIDAD_MINERA unfi ON (fisc.ID_UNIDAD_MINERA = unfi.ID_UNIDAD_MINERA "
			+ "                                               AND unfi.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:idEspecialidad = 0 OR espe.ID_ESPECIALIDAD = :idEspecialidad) "
			+ "AND (:idDivisionSupervisora = 0 OR disu.ID_VALOR_PARAMETRO = :idDivisionSupervisora) "
			// Agrupado por unidad fiscalizada
			+ "GROUP BY unfi.CO_UNIDAD_MINERA, "
			+ "         unfi.NO_UNIDAD_MINERA " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionFisPorActorNegocioUM(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora
			);
	
	/**
    * Permite listar los items de una medición de indicadores de Fiscalización por característica de la fiscalización "Especialidad"
    * 
    * @param idIndicador
    * @param feDesde
    * @param feHasta
    * @return
    */
	@Query(value = "SELECT espe.ID_ESPECIALIDAD AS idEspecialidad, "
			+ "	espe.NO_ESPECIALIDAD AS noEspecialidad, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 2 " // -- Fiscalizacion
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (inpr.ID_INSTANCIA_PROCESO = fisc.ID_INSTANCIA_PROCESO "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TM_ESPECIALIDAD espe ON (prog.ID_ESPECIALIDAD = espe.ID_ESPECIALIDAD) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			// Agrupado por especialidad
			+ "GROUP BY espe.ID_ESPECIALIDAD, "
			+ "         espe.NO_ESPECIALIDAD " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionFisPorCaracteristicaEspecialidad(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta
			);	
	
	/**
    * Permite listar los items de una medición de indicadores de Fiscalización por característica de la fiscalización "División supervisora"
    * 
    * @param idIndicador
    * @param feDesde
    * @param feHasta
    * @return
    */
	@Query(value = "SELECT disu.ID_VALOR_PARAMETRO AS idDivisionSupervisora, "
			+ "	disu.NO_VALOR_PARAMETRO AS noDivisionSupervisora, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 2 " // -- Fiscalizacion
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (inpr.ID_INSTANCIA_PROCESO = fisc.ID_INSTANCIA_PROCESO "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TP_VALOR_PARAMETRO disu ON (prog.ID_DIVISION_SUPERVISORA = disu.ID_VALOR_PARAMETRO) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			// Agrupado por división supervisora
			+ "GROUP BY disu.ID_VALOR_PARAMETRO, "
			+ "         disu.NO_VALOR_PARAMETRO " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionFisPorCaracteristicaDS(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta
			);	
	
	/**
    * Permite listar los items de una medición de indicadores de Fiscalización por característica de la fiscalización "Especialidad y División supervisora"
    * 
    * @param idIndicador
    * @param feDesde
    * @param feHasta
    * @return
    */
	@Query(value = "SELECT espe.ID_ESPECIALIDAD AS idEspecialidad, "
			+ "	espe.NO_ESPECIALIDAD AS noEspecialidad, "
			+ " disu.ID_VALOR_PARAMETRO AS idDivisionSupervisora, "
			+ "	disu.NO_VALOR_PARAMETRO AS noDivisionSupervisora, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 2 " // -- Fiscalizacion
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (inpr.ID_INSTANCIA_PROCESO = fisc.ID_INSTANCIA_PROCESO "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TP_VALOR_PARAMETRO disu ON (prog.ID_DIVISION_SUPERVISORA = disu.ID_VALOR_PARAMETRO) "
			+ "     INNER JOIN PGIM.PGIM_TM_ESPECIALIDAD espe ON (prog.ID_ESPECIALIDAD = espe.ID_ESPECIALIDAD) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			// Agrupado por especialidad y división supervisora 
			+ "GROUP BY espe.ID_ESPECIALIDAD, "
			+ "	  		espe.NO_ESPECIALIDAD, "
			+ "			disu.ID_VALOR_PARAMETRO, "
			+ "         disu.NO_VALOR_PARAMETRO " 
			+ "ORDER BY 2 DESC, 4 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionFisPorCaracteristicaEspecYDS(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta
			);	
	
	/**
    * Permite listar los items de una medición de indicadores de Fiscalización con valores puntuales 
	* cuando no se ha seleccionado ninguna característica de la fiscalización.
    * 
    * @param idIndicador
    * @param feDesde
    * @param feHasta
    * @return
    */
	@Query(value = "SELECT COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 2 " // -- Fiscalizacion
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (inpr.ID_INSTANCIA_PROCESO = fisc.ID_INSTANCIA_PROCESO "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionFisPorCaracteristicaGeneral(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta
			);	
	
	/**
	 * Permite listar los items de una medición de indicadores de PAS por actor de negocio "Agente fiscalizado"
	 * 
	 * @param idIndicador
	 * @param feDesde
	 * @param feHasta
	 * @param idEspecialidad
	 * @param idDivisionSupervisora
	 * @return
	 */
	@Query(value = "SELECT peaf.CO_DOCUMENTO_IDENTIDAD AS rucAgenteSupervisado, "
			+ "	NVL(pas.NO_RAZON_SOCIAL_AFISCALIZADO, peaf.NO_RAZON_SOCIAL) AS noAgenteSupervisado, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 4 " // -- PAS
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_PAS pas ON (inpr.ID_INSTANCIA_PROCESO = pas.ID_INSTANCIA_PROCESO "
			+ "                                             AND pas.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (pas.ID_SUPERVISION = fisc.ID_SUPERVISION "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TP_VALOR_PARAMETRO disu ON (prog.ID_DIVISION_SUPERVISORA = disu.ID_VALOR_PARAMETRO) "
			+ "     INNER JOIN PGIM.PGIM_TM_ESPECIALIDAD espe ON (prog.ID_ESPECIALIDAD = espe.ID_ESPECIALIDAD) "
			+ "     INNER JOIN PGIM.PGIM_TM_UNIDAD_MINERA unfi ON (fisc.ID_UNIDAD_MINERA = unfi.ID_UNIDAD_MINERA "
			+ "                                               AND unfi.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_AGENTE_SUPERVISADO afis ON (NVL(fisc.ID_AGENTE_SUPERVISADO, unfi.ID_AGENTE_SUPERVISADO) = afis.ID_AGENTE_SUPERVISADO "
			+ "                                                    AND afis.ES_REGISTRO = '1')   "
			+ "     INNER JOIN PGIM.PGIM_TM_PERSONA peaf ON (afis.ID_PERSONA = peaf.ID_PERSONA "
			+ "                                         AND peaf.ES_REGISTRO = '1') "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:idEspecialidad = 0 OR espe.ID_ESPECIALIDAD = :idEspecialidad) "
			+ "AND (:idDivisionSupervisora = 0 OR disu.ID_VALOR_PARAMETRO = :idDivisionSupervisora) "
			// Agrupado por agente fiscalizado
			+ "GROUP BY peaf.CO_DOCUMENTO_IDENTIDAD, "
			+ "         NVL(pas.NO_RAZON_SOCIAL_AFISCALIZADO, peaf.NO_RAZON_SOCIAL) " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionPasPorActorNegocioAF(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora
			);

	/**
	 * Permite listar los items de una medición de indicadores de PAS por actor de negocio "Unidad fiscalizada"
	 * 
	 * @param idIndicador
	 * @param feDesde
	 * @param feHasta
	 * @param idEspecialidad
	 * @param idDivisionSupervisora
	 * @return
	 */
	@Query(value = "SELECT unfi.CO_UNIDAD_MINERA AS coUnidadMinera, "
			+ "	unfi.NO_UNIDAD_MINERA AS noUnidadMinera, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 4 " // -- PAS
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_PAS pas ON (inpr.ID_INSTANCIA_PROCESO = pas.ID_INSTANCIA_PROCESO "
			+ "                                             AND pas.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (pas.ID_SUPERVISION = fisc.ID_SUPERVISION "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TP_VALOR_PARAMETRO disu ON (prog.ID_DIVISION_SUPERVISORA = disu.ID_VALOR_PARAMETRO) "
			+ "     INNER JOIN PGIM.PGIM_TM_ESPECIALIDAD espe ON (prog.ID_ESPECIALIDAD = espe.ID_ESPECIALIDAD) "
			+ "     INNER JOIN PGIM.PGIM_TM_UNIDAD_MINERA unfi ON (fisc.ID_UNIDAD_MINERA = unfi.ID_UNIDAD_MINERA "
			+ "                                               AND unfi.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:idEspecialidad = 0 OR espe.ID_ESPECIALIDAD = :idEspecialidad) "
			+ "AND (:idDivisionSupervisora = 0 OR disu.ID_VALOR_PARAMETRO = :idDivisionSupervisora) "
			// Agrupado por unidad minera
			+ "GROUP BY unfi.CO_UNIDAD_MINERA, "
			+ "         unfi.NO_UNIDAD_MINERA " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionPasPorActorNegocioUM(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora
			);
	
	/**
	 * Permite listar los items de una medición de indicadores de PAS por característica de la fiscalización "Especialidad"
	 * 
	 * @param idIndicador
	 * @param feDesde
	 * @param feHasta
	 * @return
	 */
	@Query(value = "SELECT espe.ID_ESPECIALIDAD AS idEspecialidad, "
			+ "	espe.NO_ESPECIALIDAD AS noEspecialidad, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 4 " // -- PAS
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_PAS pas ON (inpr.ID_INSTANCIA_PROCESO = pas.ID_INSTANCIA_PROCESO "
			+ "                                             AND pas.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (pas.ID_SUPERVISION = fisc.ID_SUPERVISION "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TM_ESPECIALIDAD espe ON (prog.ID_ESPECIALIDAD = espe.ID_ESPECIALIDAD) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			// Agrupado por especialidad
			+ "GROUP BY espe.ID_ESPECIALIDAD, "
			+ "         espe.NO_ESPECIALIDAD " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionPasPorCaracteristicaEspecialidad(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta
			);
	
	/**
	 * Permite listar los items de una medición de indicadores de PAS por característica de la fiscalización "División supervisora"
	 * 
	 * @param idIndicador
	 * @param feDesde
	 * @param feHasta
	 * @return
	 */
	@Query(value = "SELECT disu.ID_VALOR_PARAMETRO AS idDivisionSupervisora, "
			+ "	disu.NO_VALOR_PARAMETRO AS noDivisionSupervisora, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 4 " // -- PAS
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_PAS pas ON (inpr.ID_INSTANCIA_PROCESO = pas.ID_INSTANCIA_PROCESO "
			+ "                                             AND pas.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (pas.ID_SUPERVISION = fisc.ID_SUPERVISION "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TP_VALOR_PARAMETRO disu ON (prog.ID_DIVISION_SUPERVISORA = disu.ID_VALOR_PARAMETRO) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			// Agrupado por división supervisora
			+ "GROUP BY disu.ID_VALOR_PARAMETRO, "
			+ "         disu.NO_VALOR_PARAMETRO " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionPasPorCaracteristicaDS(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta
			);
	
	/**
	 * Permite listar los items de una medición de indicadores de PAS por característica de la fiscalización "Especialidad y División supervisora"
	 * 
	 * @param idIndicador
	 * @param feDesde
	 * @param feHasta
	 * @return
	 */
	@Query(value = "SELECT espe.ID_ESPECIALIDAD AS idEspecialidad, "
			+ " espe.NO_ESPECIALIDAD AS noEspecialidad, "
			+ " disu.ID_VALOR_PARAMETRO AS idDivisionSupervisora, "
			+ "	disu.NO_VALOR_PARAMETRO AS noDivisionSupervisora, "
			+ "	COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 4 " // -- PAS
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_PAS pas ON (inpr.ID_INSTANCIA_PROCESO = pas.ID_INSTANCIA_PROCESO "
			+ "                                             AND pas.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_SUPERVISION fisc ON (pas.ID_SUPERVISION = fisc.ID_SUPERVISION "
			+ "                                             AND fisc.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "     INNER JOIN PGIM.PGIM_TC_PRGRM_SUPERVISION prog ON (fisc.ID_PROGRAMA_SUPERVISION = prog.ID_PROGRAMA_SUPERVISION) "
			+ "     INNER JOIN PGIM.PGIM_TP_VALOR_PARAMETRO disu ON (prog.ID_DIVISION_SUPERVISORA = disu.ID_VALOR_PARAMETRO) "
			+ "     INNER JOIN PGIM.PGIM_TM_ESPECIALIDAD espe ON (prog.ID_ESPECIALIDAD = espe.ID_ESPECIALIDAD) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			// Agrupado por especialidad y división supervisora
			+ "GROUP BY espe.ID_ESPECIALIDAD, "
			+ "			espe.NO_ESPECIALIDAD, "
			+ "			disu.ID_VALOR_PARAMETRO, "
			+ "         disu.NO_VALOR_PARAMETRO " 
			+ "ORDER BY 2 DESC "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionPasPorCaracteristicaEspecYDS(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta
			);
	
	/**
	 * Permite listar los items de una medición de indicadores de PAS con valores puntuales 
	 * cuando no se ha seleccionado ninguna característica de la fiscalización.
	 * 
	 * @param idIndicador
	 * @param feDesde
	 * @param feHasta
	 * @return
	 */
	@Query(value = "SELECT COUNT(1) AS caRegistros, "
			//	Promedios
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS avgDiasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS avgSemanasTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS avgMesesTranscurridos, "
			+ "	AVG((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS avgAniosTranscurridos, "
			//	Minimos
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS minDiasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS minSemanasTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS minMesesTranscurridos, "
			+ "	MIN((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS minAniosTranscurridos, "
			//	Máximos
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) ) AS maxDiasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 7.0 ) AS maxSemanasTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 30.0 ) AS maxMesesTranscurridos, "
			+ "	MAX((TRUNC(tfmr.FE_INSTANCIA_PASO) - TRUNC(tima.FE_INSTANCIA_PASO)) / 365.0) AS maxAniosTranscurridos "
			
			+ "FROM PGIM.PGIM_TC_INDICADOR indi "
			// Flujo de trabajo
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PROCES inpr ON (indi.ID_PROCESO = 4 " // -- PAS
			+ "                                                  AND indi.ID_PROCESO = inpr.ID_PROCESO "
			+ "                                                  AND indi.ES_REGISTRO = '1' "
			+ "                                                  AND inpr.ES_REGISTRO = '1' "
			+ "                                                  ) "
			// Instancia de paso inicial más antigua
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MIN(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 AND inpai.FL_ES_PASO_ACTIVO = '0' " // tarea ya completada
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tima ON (indi.ID_RELACION_PASO_ORIGEN = tima.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tima.ID_INSTANCIA_PROCESO) "
			// Instancia de paso final más reciente
			+ "     INNER JOIN (SELECT inpai.ID_INSTANCIA_PROCESO, "
			+ "                        inpai.ID_RELACION_PASO, " 
			+ "                        MAX(inpai.FE_INSTANCIA_PASO) AS FE_INSTANCIA_PASO "
			+ "                 FROM PGIM.PGIM_TC_INSTANCIA_PASO inpai "
			+ "                 WHERE 1 = 1 " 
			//                 -- Excluimos las reasignaciones en la transición final. 
			+ "                 AND NOT EXISTS (  "
			+ "                    SELECT 1 "
			+ "                    FROM PGIM.PGIM_TC_INSTANCIA_PASO inprr "
			+ "                    WHERE 1 = 1 "
			+ "                    AND inprr.ID_RELACION_PASO = inpai.ID_RELACION_PASO " // Tiene la misma relación entonces es una reasignación 
			+ "                    AND inprr.ID_INSTANCIA_PASO = inpai.ID_INSTANCIA_PASO_PADRE "
			+ "                    AND inprr.ES_REGISTRO = '1' "
			+ "                 ) "
			+ "                 AND inpai.ES_REGISTRO = '1' "
			+ "                 GROUP BY inpai.ID_INSTANCIA_PROCESO, "
			+ "                          inpai.ID_RELACION_PASO " 
			+ "                 ) tfmrc ON (indi.ID_RELACION_PASO_DESTINO = tfmrc.ID_RELACION_PASO "
			+ "                             AND inpr.ID_INSTANCIA_PROCESO = tfmrc.ID_INSTANCIA_PROCESO) "
			+ "     INNER JOIN PGIM.PGIM_TC_INSTANCIA_PASO tfmr ON (tfmrc.ID_INSTANCIA_PROCESO = tfmr.ID_INSTANCIA_PROCESO "
			+ "                                               AND tfmrc.ID_RELACION_PASO = tfmr.ID_RELACION_PASO "
			+ "                                               AND tfmrc.FE_INSTANCIA_PASO = tfmr.FE_INSTANCIA_PASO                                                 "
			+ "                                               AND tfmr.ES_REGISTRO = '1' "
			+ "                                               ) "
			+ "     INNER JOIN PGIM.PGIM_TM_RELACION_PASO retf ON (tfmr.ID_RELACION_PASO = retf.ID_RELACION_PASO) "
			+ "     INNER JOIN PGIM.PGIM_TM_PASO_PROCESO tafi1 ON (retf.ID_PASO_PROCESO_ORIGEN = tafi1.ID_PASO_PROCESO) "
			// Contexto "
			+ "     INNER JOIN PGIM.PGIM_TC_PAS pas ON (inpr.ID_INSTANCIA_PROCESO = pas.ID_INSTANCIA_PROCESO "
			+ "                                             AND pas.ES_REGISTRO = '1' "
			+ "                                             ) "
			+ "WHERE 1 = 1  "
			+ "AND indi.ID_INDICADOR = :idIndicador " 
			+ "AND indi.ES_INDICADOR = 'A' " // indicador activo	
			// No debe existir otra instancia de paso activa en el inicio
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inpri "
			+ "     WHERE inpri.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND (inpri.ID_RELACION_PASO = indi.ID_RELACION_PASO_ORIGEN) " // ...de la relación origen
			+ "     AND inpri.FL_ES_PASO_ACTIVO = '1' "
			+ "		AND inpri.ES_REGISTRO = '1' "
			+ ") "
			// Excluimos los retrocesos sobre la tarea origen de la transición final
			+ "AND NOT EXISTS ( "
			+ "     SELECT 1 "
			+ "     FROM PGIM.PGIM_TC_INSTANCIA_PASO inprb, "
			+ "          PGIM.PGIM_TM_RELACION_PASO retib,  "
			+ "          PGIM.PGIM_TM_PASO_PROCESO tainb "
			+ "     WHERE inprb.ID_RELACION_PASO = retib.ID_RELACION_PASO "
			+ "     AND retib.ID_PASO_PROCESO_DESTINO = tainb.ID_PASO_PROCESO "
			+ "     AND inprb.FL_ES_PASO_ACTIVO = '1' "
			+ "     AND inprb.ES_REGISTRO = '1' "
			+ "     AND inprb.ID_INSTANCIA_PROCESO = inpr.ID_INSTANCIA_PROCESO "
			+ "     AND tainb.ID_PASO_PROCESO = tafi1.ID_PASO_PROCESO " // Consideramos la tarea origen de la transición final
			+ ") "
			// Criterios filtro
			+ "AND (:feDesde <= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			+ "AND (:feHasta >= TRUNC(tfmr.FE_INSTANCIA_PASO)) "
			, nativeQuery = true) 
	List<ItemMedicionIndicadorDTO> listarItemsMedicionPasPorCaracteristicaGeneral(
			@Param("idIndicador") Long idIndicador,
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta
			);
  
}
