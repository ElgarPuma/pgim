package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimFichaRevision;

/**
 * Ésta interface FichaRevisionRepository incluye los metodos de obtener
 * la ficha de revisión
 * 
 * @descripción: Lógica de negocio de la entidad Fiacha revision
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 */
public interface FichaRevisionRepository extends JpaRepository<PgimFichaRevision, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTOResultado("
			+ "fire.idFichaRevision, fire.pgimDocumento.idDocumento, fire.tipoConformidad.idValorParametro, "
			+ "fire.feRevisionFicha, fire.caDiasParaPresentacion, fire.feDesdeParaPresentacion, "
			+ "fire.fePresentacion, fire.caDiasDemoraCalculados, fire.flAplicaPenalidad, "
			+ "fire.caDiasDemoraEstablecidos, fire.cmPenalidad, fire.flObservacionEpp, "
			+ "fire.cmObservacionEpp, fire.caDiasPlazoPresentacion, fire.feHastaParaPresentacion, "
			+ "fire.feFirmaActaFiscaliza, fire.pgimDocumento.feEnvioDocumento, "
			+ "fire.flEppAfiscalizado, fire.cmEppFiscalizado, fire.flEquiposDefectuosos, "			
			+ "fire.cmEquiposDefectuosos, fire.flEqpMedicionDefectuosos, fire.cmEqpMedicionDefectuosos, "			
			+ "fire.flSinEquipos, fire.cmSinEquipos, fire.flEqpCalibracionNvigente, fire.cmEqpCalibracionNvigente, "			
			+ "fire.flSinInstrumentos, fire.cmSinInstrumentos, fire.flInsCalibracionNvigente, fire.cmInsCalibracionNvigente, "			
			+ "fire.flAlterarFormatos, fire.cmAlterarFormatos, fire.flFrustrarFiscalizacion, fire.cmFrustrarFiscalizacion "		
			+ ") "
			+ "FROM PgimFichaRevision fire "
			+ "WHERE fire.esRegistro = '1' "
			+ "AND fire.idFichaRevision = :idFichaRevision ")
	PgimFichaRevisionDTO obtenerFichaRevisionPorId(@Param("idFichaRevision") Long idFichaRevision);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTOResultado("
			+ "fire.idFichaRevision, fire.pgimDocumento.idDocumento, fire.tipoConformidad.idValorParametro, "
			+ "fire.feRevisionFicha, fire.caDiasParaPresentacion, fire.feDesdeParaPresentacion, "
			+ "fire.fePresentacion, fire.caDiasDemoraCalculados, fire.flAplicaPenalidad, "
			+ "fire.caDiasDemoraEstablecidos, fire.cmPenalidad, fire.flObservacionEpp, "
			+ "fire.cmObservacionEpp, fire.caDiasPlazoPresentacion, fire.feHastaParaPresentacion, "
			+ "fire.feFirmaActaFiscaliza, fire.pgimDocumento.feEnvioDocumento, "			
			+ "fire.flEppAfiscalizado, fire.cmEppFiscalizado, fire.flEquiposDefectuosos, "			
			+ "fire.cmEquiposDefectuosos, fire.flEqpMedicionDefectuosos, fire.cmEqpMedicionDefectuosos, "			
			+ "fire.flSinEquipos, fire.cmSinEquipos, fire.flEqpCalibracionNvigente, fire.cmEqpCalibracionNvigente, "			
			+ "fire.flSinInstrumentos, fire.cmSinInstrumentos, fire.flInsCalibracionNvigente, fire.cmInsCalibracionNvigente, "			
			+ "fire.flAlterarFormatos, fire.cmAlterarFormatos, fire.flFrustrarFiscalizacion, fire.cmFrustrarFiscalizacion, "		
			+ "CASE " 
			+ "		WHEN fire.flObservacionEpp = '1' OR fire.flEppAfiscalizado = '1' OR fire.flEquiposDefectuosos = '1' OR fire.flEqpMedicionDefectuosos = '1' OR fire.flSinEquipos = '1' " 
			+ "		OR fire.flEqpCalibracionNvigente = '1' OR fire.flSinInstrumentos = '1' OR fire.flInsCalibracionNvigente = '1' OR fire.flAlterarFormatos = '1' OR fire.flFrustrarFiscalizacion = '1' THEN '1' "
			+ "		ELSE '0' " 
			+ "END  "			
			+ ") "
			+ "FROM PgimFichaRevision fire "
			+ "WHERE fire.esRegistro = '1' "
			+ "AND fire.pgimDocumento.idDocumento = :idDocumento ")
	PgimFichaRevisionDTO obtenerFichaRevisionPorIdDocumento(@Param("idDocumento") Long idDocumento);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTOResultado("
			+ "fire.idFichaRevision, fire.pgimDocumento.idDocumento, fire.tipoConformidad.idValorParametro, "
			+ "fire.feRevisionFicha, fire.caDiasParaPresentacion, fire.feDesdeParaPresentacion, "
			+ "fire.fePresentacion, fire.caDiasDemoraCalculados, fire.flAplicaPenalidad, "
			+ "fire.caDiasDemoraEstablecidos, fire.cmPenalidad, fire.flObservacionEpp, "
			+ "fire.cmObservacionEpp, fire.caDiasPlazoPresentacion, fire.feHastaParaPresentacion, "
			+ "fire.feFirmaActaFiscaliza, fire.pgimDocumento.feEnvioDocumento, "
			+ "fire.flEppAfiscalizado, fire.cmEppFiscalizado, fire.flEquiposDefectuosos, "			
			+ "fire.cmEquiposDefectuosos, fire.flEqpMedicionDefectuosos, fire.cmEqpMedicionDefectuosos, "			
			+ "fire.flSinEquipos, fire.cmSinEquipos, fire.flEqpCalibracionNvigente, fire.cmEqpCalibracionNvigente, "			
			+ "fire.flSinInstrumentos, fire.cmSinInstrumentos, fire.flInsCalibracionNvigente, fire.cmInsCalibracionNvigente, "			
			+ "fire.flAlterarFormatos, fire.cmAlterarFormatos, fire.flFrustrarFiscalizacion, fire.cmFrustrarFiscalizacion, "		
			+ "CASE " 
			+ "		WHEN fire.flObservacionEpp = '1' OR fire.flEppAfiscalizado = '1' OR fire.flEquiposDefectuosos = '1' OR fire.flEqpMedicionDefectuosos = '1' OR fire.flSinEquipos = '1' " 
			+ "		OR fire.flEqpCalibracionNvigente = '1' OR fire.flSinInstrumentos = '1' OR fire.flInsCalibracionNvigente = '1' THEN '1' "
			+ "		ELSE '0' " 
			+ "END  "
			+ ") "
			+ "FROM PgimFichaRevision fire "
			+ "WHERE fire.esRegistro = '1' "
			+ "AND fire.tipoConformidad.coClaveTexto = :OBFIC_RVSION "// 337 ==> Revisión de la observación
			+ "AND fire.pgimDocumento.idDocumento in "
			+ "(SELECT doc.idDocumento FROM PgimDocumento doc "
			+ "WHERE doc.pgimInstanciaProces.idInstanciaProceso=:idInstanciaProceso "
			+ "AND doc.esRegistro = '1' and doc.pgimSubcategoriaDoc.idSubcatDocumento = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SUBCAT_DOC_CIS) " // 34 ==> Ficha de conformidad de informe de supervisión 
			+ "ORDER BY  fire.idFichaRevision DESC"
			)
	List<PgimFichaRevisionDTO> obtenerListFichaConformidadPorInstanciaProc(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("OBFIC_RVSION") String OBFIC_RVSION);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTOResultado("
			+ "fire.idFichaRevision, fire.pgimDocumento.idDocumento, fire.tipoConformidad.idValorParametro, "
			+ "fire.feRevisionFicha, fire.caDiasParaPresentacion, fire.feDesdeParaPresentacion, "
			+ "fire.fePresentacion, fire.caDiasDemoraCalculados, fire.flAplicaPenalidad, "
			+ "fire.caDiasDemoraEstablecidos, fire.cmPenalidad, fire.flObservacionEpp, "
			+ "fire.cmObservacionEpp, fire.caDiasPlazoPresentacion, fire.feHastaParaPresentacion, "
			+ "fire.feFirmaActaFiscaliza, fire.pgimDocumento.feEnvioDocumento, "
			+ "fire.flEppAfiscalizado, fire.cmEppFiscalizado, fire.flEquiposDefectuosos, "			
			+ "fire.cmEquiposDefectuosos, fire.flEqpMedicionDefectuosos, fire.cmEqpMedicionDefectuosos, "			
			+ "fire.flSinEquipos, fire.cmSinEquipos, fire.flEqpCalibracionNvigente, fire.cmEqpCalibracionNvigente, "			
			+ "fire.flSinInstrumentos, fire.cmSinInstrumentos, fire.flInsCalibracionNvigente, fire.cmInsCalibracionNvigente, "			
			+ "fire.flAlterarFormatos, fire.cmAlterarFormatos, fire.flFrustrarFiscalizacion, fire.cmFrustrarFiscalizacion  "		
			+ ") "
			+ "FROM PgimFichaRevision fire "
			+ "WHERE fire.esRegistro = '1' "
			+ "AND fire.tipoConformidad.coClaveTexto = :FIREV_FCHA_OBSRVCIONES "// 335 ==> Ficha de observaciones
			+ "AND fire.pgimDocumento.idDocumento in "
			+ "(select doc.idDocumento from PgimDocumento doc "
			+ "where doc.pgimInstanciaProces.idInstanciaProceso=:idInstanciaProceso "
			+ "and doc.esRegistro='1' and doc.pgimSubcategoriaDoc.idSubcatDocumento = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SUBCAT_DOC_OIS) " // 33 ==> Ficha de observaciones al informe de supervisión 
			+ "and exists ("
			+ "select 1 from PgimFichaObservacion fiob where fiob.pgimFichaRevision.idFichaRevision=fire.idFichaRevision "
			+ "AND fiob.flSubsanada='1' AND fiob.esRegistro='1') "
			+ "ORDER BY fire.idFichaRevision DESC"
			)
	List<PgimFichaRevisionDTO> obtenerListFichaObservacionNoSubsanadasPorInstanciaProc(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("FIREV_FCHA_OBSRVCIONES") String FIREV_FCHA_OBSRVCIONES);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTOResultado("
			+ "fire.idFichaRevision, fire.pgimDocumento.idDocumento, fire.tipoConformidad.idValorParametro, "
			+ "fire.feRevisionFicha, fire.caDiasParaPresentacion, fire.feDesdeParaPresentacion, "
			+ "fire.fePresentacion, fire.caDiasDemoraCalculados, fire.flAplicaPenalidad, "
			+ "fire.caDiasDemoraEstablecidos, fire.cmPenalidad, fire.flObservacionEpp, "
			+ "fire.cmObservacionEpp, fire.caDiasPlazoPresentacion, fire.feHastaParaPresentacion, "
			+ "fire.feFirmaActaFiscaliza, fire.pgimDocumento.feEnvioDocumento, "
			+ "fire.flEppAfiscalizado, fire.cmEppFiscalizado, fire.flEquiposDefectuosos, "			
			+ "fire.cmEquiposDefectuosos, fire.flEqpMedicionDefectuosos, fire.cmEqpMedicionDefectuosos, "			
			+ "fire.flSinEquipos, fire.cmSinEquipos, fire.flEqpCalibracionNvigente, fire.cmEqpCalibracionNvigente, "			
			+ "fire.flSinInstrumentos, fire.cmSinInstrumentos, fire.flInsCalibracionNvigente, fire.cmInsCalibracionNvigente, "			
			+ "fire.flAlterarFormatos, fire.cmAlterarFormatos, fire.flFrustrarFiscalizacion, fire.cmFrustrarFiscalizacion  "		
			+ ") "
			+ "FROM PgimFichaRevision fire "
			+ "INNER JOIN fire.pgimDocumento docu "
			+ "INNER JOIN docu.pgimSubcategoriaDoc sudo "
			+ "INNER JOIN docu.pgimInstanciaProces inpr "
			+ "WHERE 1 = 1 "
			+ "AND inpr.idInstanciaProceso = :idInstanciaProceso  "
			+ "AND sudo.idSubcatDocumento = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SUBCAT_DOC_CIS "
			+ "AND fire.esRegistro = '1' "
			+ "AND docu.esRegistro = '1' "
			+ "AND sudo.esRegistro = '1' "
			+ "AND inpr.esRegistro = '1' "
			)
	List<PgimFichaRevisionDTO> obtenerFichaRevisionPorInstanciaProcess(@Param("idInstanciaProceso") Long idInstanciaProceso);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTOResultado("
			+ "fire.idFichaRevision, fire.pgimDocumento.idDocumento, fire.tipoConformidad.idValorParametro, "
			+ "fire.feRevisionFicha, fire.caDiasParaPresentacion, fire.feDesdeParaPresentacion, "
			+ "fire.fePresentacion, fire.caDiasDemoraCalculados, fire.flAplicaPenalidad, "
			+ "fire.caDiasDemoraEstablecidos, fire.cmPenalidad, fire.flObservacionEpp, "
			+ "fire.cmObservacionEpp, fire.caDiasPlazoPresentacion, fire.feHastaParaPresentacion, "
			+ "fire.feFirmaActaFiscaliza, fire.pgimDocumento.feEnvioDocumento, "
			+ "fire.flEppAfiscalizado, fire.cmEppFiscalizado, fire.flEquiposDefectuosos, "			
			+ "fire.cmEquiposDefectuosos, fire.flEqpMedicionDefectuosos, fire.cmEqpMedicionDefectuosos, "			
			+ "fire.flSinEquipos, fire.cmSinEquipos, fire.flEqpCalibracionNvigente, fire.cmEqpCalibracionNvigente, "			
			+ "fire.flSinInstrumentos, fire.cmSinInstrumentos, fire.flInsCalibracionNvigente, fire.cmInsCalibracionNvigente, "			
			+ "fire.flAlterarFormatos, fire.cmAlterarFormatos, fire.flFrustrarFiscalizacion, fire.cmFrustrarFiscalizacion  "		
			+ ") "
			+ "FROM PgimFichaRevision fire "
			+ "WHERE 1 = 1 "
			+ "AND fire.esRegistro = '1' "
			+ "AND EXISTS ( "
			+ "SELECT 1 "
			+ "FROM PgimDocumentoRelacion dore "
			+ "		INNER JOIN dore.pgimDocumento docu "
			+ "		INNER JOIN docu.pgimInstanciaProces inpr "
			+ "WHERE dore.tipoRelacionDocumento.coClaveTexto = :REDOC_OBSRVCION "
			+ "AND docu = fire.pgimDocumento "			
			+ "AND inpr.idInstanciaProceso = :idInstanciaProceso "
			+ "AND docu.esRegistro = '1' "
			+ "AND inpr.esRegistro = '1' "
			+ ") "
			+ "ORDER BY fire.feRevisionFicha, fire.idFichaRevision ASC"
			)
	List<PgimFichaRevisionDTO> obtenerFichasRevisionObsDescendentes(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("REDOC_OBSRVCION") String REDOC_OBSRVCION);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTOResultado("
			+ "fire.idFichaRevision, fire.pgimDocumento.idDocumento, fire.tipoConformidad.idValorParametro, "
			+ "fire.feRevisionFicha, fire.caDiasParaPresentacion, fire.feDesdeParaPresentacion, "
			+ "fire.fePresentacion, fire.caDiasDemoraCalculados, fire.flAplicaPenalidad, "
			+ "fire.caDiasDemoraEstablecidos, fire.cmPenalidad, fire.flObservacionEpp, "
			+ "fire.cmObservacionEpp, fire.caDiasPlazoPresentacion, fire.feHastaParaPresentacion, "
			+ "fire.feFirmaActaFiscaliza, fire.pgimDocumento.feEnvioDocumento, "
			+ "fire.flEppAfiscalizado, fire.cmEppFiscalizado, fire.flEquiposDefectuosos, "			
			+ "fire.cmEquiposDefectuosos, fire.flEqpMedicionDefectuosos, fire.cmEqpMedicionDefectuosos, "			
			+ "fire.flSinEquipos, fire.cmSinEquipos, fire.flEqpCalibracionNvigente, fire.cmEqpCalibracionNvigente, "			
			+ "fire.flSinInstrumentos, fire.cmSinInstrumentos, fire.flInsCalibracionNvigente, fire.cmInsCalibracionNvigente, "			
			+ "fire.flAlterarFormatos, fire.cmAlterarFormatos, fire.flFrustrarFiscalizacion, fire.cmFrustrarFiscalizacion  "		
			+ ") "
			+ "FROM PgimFichaRevision fire "
			+ "INNER JOIN fire.pgimDocumento dofr  "
			+ "INNER JOIN dofr.pgimInstanciaProces inpr  "
			+ "INNER JOIN PgimDocumento docu ON (inpr.idInstanciaProceso = docu.pgimInstanciaProces.idInstanciaProceso)  "
			+ "WHERE docu.idDocumento = :idDocumentoObservado "
			+ "AND dofr.feOrigenDocumento < docu.feOrigenDocumento "
			+ "AND fire.esRegistro = '1' "
			+ "AND dofr.esRegistro = '1' "
			+ "AND docu.esRegistro = '1' "
			+ "ORDER BY fire.feRevisionFicha DESC, fire.idFichaRevision ASC "
			)	
	List<PgimFichaRevisionDTO> obtenerFichasRevisionAnteriores(@Param("idDocumentoObservado") Long idDocumentoObservado);

}
