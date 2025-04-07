package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRiesgo;

/**
 * 
 * @descripción: Lógica de negocio de la entidad Configuración de Riesgos
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 23/02/2020
 * @fecha_de_ultima_actualización: 23/02/2020 
 */
@Repository
public interface ConfiguraRiesgoRepository extends JpaRepository<PgimConfiguraRiesgo, Long> {
	
	/**
	 * Se utiliza para visualizar la lista seleccionable(Configuración de Riesgos) en el dialogo 
	 * Asignación de Ranking 
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
            + "cr.idConfiguraRiesgo,  cr.noConfiguracion, cr.pgimEspecialidad.idEspecialidad, "
            + "cr.tipoEstadoConfiguracion.idValorParametro, cr.tipoEstadoConfiguracion.noValorParametro, "
            + "cr.tipoPeriodo.idValorParametro, cr.tipoConfiguracionRiesgo.idValorParametro, cr.tipoPeriodo.noValorParametro, cr.tipoConfiguracionRiesgo.noValorParametro, " 
            + "cr.nuAnioPeriodo, cr.tipoConfiguracionRiesgo.coClaveTexto ) "
            + "FROM PgimConfiguraRiesgo cr "
            + "WHERE cr.esRegistro = '1' "
            + "AND (cr.tipoEstadoConfiguracion.coClaveTexto = :ESCFG_APRBDA "
            + "OR cr.tipoEstadoConfiguracion.coClaveTexto = :ESCFG_ARCHVDA) "
            + "ORDER BY cr.tipoConfiguracionRiesgo.nuOrden, cr.pgimEspecialidad.noEspecialidad")
	List<PgimConfiguraRiesgoDTO> obtenerConfiguracionesParaAsignacion(@Param("ESCFG_APRBDA") String ESCFG_APRBDA, @Param("ESCFG_ARCHVDA") String ESCFG_ARCHVDA);
	
	
	/**
	 * Se utiliza para obtener un regitro de la entidad "Configuración de Riesgos" 
	 * 
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
            + "cr.idConfiguraRiesgo, cr.noConfiguracion, cr.pgimEspecialidad.idEspecialidad, "
            + "cr.tipoEstadoConfiguracion.idValorParametro, cr.tipoEstadoConfiguracion.noValorParametro, cr.tipoConfiguracionRiesgo.idValorParametro) "
            + "FROM PgimConfiguraRiesgo cr "
            + "WHERE cr.esRegistro = '1' "
            + "AND cr.idConfiguraRiesgo = :idConfiguracion "
            )
	PgimConfiguraRiesgoDTO obtenerConfiguracionesPorId(@Param("idConfiguracion") Long idConfiguracion);
		
	/**
	 * Se utiliza para listar las configuraciones disponibles para el registro de riesgos de una supervisión
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
            + "cr.idConfiguraRiesgo, cr.noConfiguracion, cr.pgimEspecialidad.idEspecialidad, "
            + "cr.tipoEstadoConfiguracion.idValorParametro, cr.tipoEstadoConfiguracion.noValorParametro, cr.tipoConfiguracionRiesgo.idValorParametro) "
            + "FROM PgimConfiguraRiesgo cr "
            + "WHERE cr.esRegistro = '1' "
            + "AND cr.tipoEstadoConfiguracion.coClaveTexto = :ESCFG_APRBDA "
            + "AND cr.tipoConfiguracionRiesgo.coClaveTexto = :TIPO_CFG_RIESGO_FISCALIZACION "
            + "AND cr.pgimEspecialidad.idEspecialidad = :idEspecialidad "
            + "ORDER BY cr.feConfiguracion DESC ")
	List<PgimConfiguraRiesgoDTO> obtenerConfiguracionesParaSupervision(@Param("idEspecialidad") Long idEspecialidad,
    @Param("ESCFG_APRBDA") String ESCFG_APRBDA,
    @Param("TIPO_CFG_RIESGO_FISCALIZACION") String TIPO_CFG_RIESGO_FISCALIZACION);
	
	/**
	 * Se utiliza para listar las configuraciones de riesgo
	 * @param feConfiguracionAnio
	 * @param idEspecialidad
	 * @param idTipoEstadoConfiguracion
	 * @param textoBusqueda
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
			+ "cori.idConfiguraRiesgoAux, cori.idConfiguraRiesgoPadre, cori.idEspecialidad, "
            + "cori.noEspecialidad, cori.idTipoEstadoConfiguracion, cori.noValorParametro, "
            + "cori.noConfiguracion, cori.deConfiguracion, cori.feConfiguracion, "
            + "cori.feConfiguracionAnio, cori.noPersonaAsignada, cori.noFaseActual, " 
            + "cori.noPasoActual, cori.idTipoConfiguracionRiesgo, cori.coTipoConfiguracionRiesgo, " 
            + "cori.noTipoConfiguracionRiesgo, config.tipoPeriodo.idValorParametro, config.tipoPeriodo.noValorParametro, "
            + "config.nuAnioPeriodo, cori.noUsuarioAsignado, cori.flLeido, "
            + "cori.feLectura, cori.noPersonaOrigen, cori.noUsuarioOrigen, "
            + "cori.feInstanciaPaso, cori.deMensaje, cori.flPasoActivo, "
            + "cori.idInstanciaPaso "
            + ") "
            + "FROM PgimConfiguraRiesgoAux cori " 
            + "     INNER JOIN PgimConfiguraRiesgo config ON (cori.idConfiguraRiesgoAux = config.idConfiguraRiesgo) " 
            + "WHERE cori.esRegistro = '1' "            
            + "AND (:feConfiguracionAnio IS NULL OR LOWER(cori.feConfiguracionAnio) LIKE LOWER(CONCAT('%', :feConfiguracionAnio, '%')) ) "
            + "AND (:idEspecialidad IS NULL OR LOWER(cori.idEspecialidad) LIKE LOWER(CONCAT('%', :idEspecialidad, '%')) ) "
            + "AND (:idTipoEstadoConfiguracion IS NULL OR LOWER(cori.idTipoEstadoConfiguracion) LIKE LOWER(CONCAT('%', :idTipoEstadoConfiguracion, '%')) ) "
            + "AND (:idTipoConfiguracionRiesgo IS NULL OR cori.idTipoConfiguracionRiesgo = :idTipoConfiguracionRiesgo ) "
            + "AND (:idFaseProceso IS NULL OR cori.idFaseActual = :idFaseProceso ) "
            + "AND (:idPasoProceso IS NULL OR cori.idPasoActual = :idPasoProceso) "
            + "AND (:idTipoPeriodo IS NULL OR config.tipoPeriodo.idValorParametro = :idTipoPeriodo) "
            + "AND (:nuAnioPeriodo IS NULL OR config.nuAnioPeriodo = :nuAnioPeriodo) "            
			+ "AND ( "
             // Mis tareas (tareas)
			+ " (:flagMisAsignaciones = '1' AND cori.flPasoActivo = '1' AND LOWER(cori.noUsuarioAsignado) = LOWER(:usuarioAsignado) AND cori.idTipoRelacion NOT IN (291, 292) ) " 
			+ "OR "
             // Tareas enviadas // STORY: PGIM-5455 Consulta de objeto de trabajo enviado
            + " ("
            + "     :flagMisAsignaciones = '2' AND (LOWER(cori.noUsuarioOrigen) = LOWER(:usuarioOrigen) ) "
            + "     AND (:descPersonaDestino IS NULL OR LOWER(cori.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) ) " 
            + " ) "
            + "OR "
            // Todas las tareas
			+ " ("
            + "     :flagMisAsignaciones = '0' AND cori.flPasoActivo = '1' "
            + "      AND (:descPersonaDestino IS NULL OR LOWER(cori.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%'))) ) " 
			+ " ) "            
            + "AND ("
            + "     :textoBusqueda IS NULL OR LOWER(cori.noConfiguracion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "	    OR LOWER(pe.gob.osinergmin.pgim.utils.ConstantesUtil.PREFIJO_CFG_RIESGO || '-' || cori.idConfiguraRiesgoAux) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + " ) ")
    Page<PgimConfiguraRiesgoDTO> listarConfiguraRiesgo(
            @Param("feConfiguracionAnio") String feConfiguracionAnio,
            @Param("idEspecialidad") Long idEspecialidad,
            @Param("idTipoEstadoConfiguracion") Long idTipoEstadoConfiguracion,
            @Param("idFaseProceso") Long idFaseProceso, 
            @Param("idPasoProceso") Long idPasoProceso,
            @Param("flagMisAsignaciones") String flagMisAsignaciones, 
            @Param("usuarioOrigen") String usuarioOrigen,
            @Param("usuarioAsignado") String usuarioAsignado,
            @Param("descPersonaDestino") String descPersonaDestino,
            @Param("idTipoConfiguracionRiesgo") Long idTipoConfiguracionRiesgo,
            @Param("idTipoPeriodo") Long idTipoPeriodo,
            @Param("nuAnioPeriodo") Long nuAnioPeriodo,
            @Param("textoBusqueda") String textoBusqueda,
            Pageable paginador);

	/**
	 * Premite obtener la lista de configuraciones padres
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
            + "cr.idConfiguraRiesgo,  cr.noConfiguracion, cr.pgimEspecialidad.idEspecialidad, "
            + "cr.tipoEstadoConfiguracion.idValorParametro, cr.tipoEstadoConfiguracion.noValorParametro, cr.tipoConfiguracionRiesgo.idValorParametro) "
            + "FROM PgimConfiguraRiesgo cr "
            + "WHERE cr.esRegistro = '1' "
            + "AND cr.tipoEstadoConfiguracion.coClaveTexto = :ESCFG_APRBDA "
            + "AND cr.configuraRiesgoPadre is NULL ")
	List<PgimConfiguraRiesgoDTO> obtenerConfiguracionesParaAsignacionPadres(@Param("ESCFG_APRBDA") String ESCFG_APRBDA);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
			+ "cori.idConfiguraRiesgo, corp.idConfiguraRiesgo, cori.pgimEspecialidad.idEspecialidad, "
            + "cori.pgimEspecialidad.noEspecialidad, cori.tipoEstadoConfiguracion.idValorParametro, cori.tipoEstadoConfiguracion.noValorParametro, "
			+ "cori.noConfiguracion, cori.deConfiguracion, cori.feConfiguracion, " 
            + "cori.pgimInstanciaProces.idInstanciaProceso, cori.nuAnioPeriodo, cori.tipoPeriodo.idValorParametro, "
            + "cori.tipoConfiguracionRiesgo.idValorParametro, cori.tipoPeriodo.noValorParametro, cori.tipoConfiguracionRiesgo.noValorParametro, "
            + "corp.noConfiguracion "
            + ") "
            + "FROM PgimConfiguraRiesgo cori "  
            + "     LEFT OUTER JOIN cori.configuraRiesgoPadre corp "  
            + "WHERE cori.esRegistro = '1' "
            + "AND cori.idConfiguraRiesgo = :idConfiguraRiesgo ")
    PgimConfiguraRiesgoDTO obtenerConfiguraRiesgoPorId(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
			+ "cfri.idConfiguraRiesgo, cfri.noConfiguracion, cfri.pgimInstanciaProces.idInstanciaProceso ) " 
            + "FROM PgimSupervision supe "
            + "INNER JOIN PgimRankingSupervision rasu ON supe.idSupervision = rasu.pgimSupervision.idSupervision "
            + "INNER JOIN PgimConfiguraRiesgo cfri ON rasu.pgimConfiguraRiesgo.idConfiguraRiesgo = cfri.idConfiguraRiesgo "            
            + "WHERE supe.esRegistro = '1' AND rasu.esRegistro = '1' AND cfri.esRegistro = '1' "
            + "AND supe.idSupervision = :idSupervision ")
    PgimConfiguraRiesgoDTO obtenerConfiguraRiesgoPorIdSupervision(@Param("idSupervision") Long idSupervision);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
			+ "cori.idConfiguraRiesgo, corp.idConfiguraRiesgo, cori.pgimEspecialidad.idEspecialidad, "
            + "cori.pgimEspecialidad.noEspecialidad, cori.tipoEstadoConfiguracion.idValorParametro, cori.tipoEstadoConfiguracion.noValorParametro, "
			+ "cori.noConfiguracion, cori.deConfiguracion, cori.feConfiguracion, " 
            + "cori.pgimInstanciaProces.idInstanciaProceso, cori.nuAnioPeriodo, cori.tipoPeriodo.idValorParametro, "
            + "cori.tipoConfiguracionRiesgo.idValorParametro, cori.tipoPeriodo.noValorParametro, cori.tipoConfiguracionRiesgo.noValorParametro, "
            + "corp.noConfiguracion "
            + ") "
            + "FROM PgimConfiguraRiesgo cori "  
            + "     LEFT OUTER JOIN cori.configuraRiesgoPadre corp "  
            + "WHERE cori.esRegistro = '1' "
            + "AND cori.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso ")
    PgimConfiguraRiesgoDTO obtenerConfiguraRiesgoAuxByIdInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
			+ "cori.idConfiguraRiesgo, corp.idConfiguraRiesgo, cori.pgimEspecialidad.idEspecialidad, "
            + "cori.pgimEspecialidad.noEspecialidad, cori.tipoEstadoConfiguracion.idValorParametro, cori.tipoEstadoConfiguracion.noValorParametro, "
			+ "cori.noConfiguracion, cori.deConfiguracion, cori.feConfiguracion, " 
            + "cori.pgimInstanciaProces.idInstanciaProceso, cori.nuAnioPeriodo, cori.tipoPeriodo.idValorParametro, "
            + "cori.tipoConfiguracionRiesgo.idValorParametro, cori.tipoPeriodo.noValorParametro, cori.tipoConfiguracionRiesgo.noValorParametro, "
            + "corp.noConfiguracion "
            + ") "
            + "FROM PgimConfiguraRiesgo cori "  
            + "     INNER JOIN cori.configuraRiesgoPadre corp "  
            + "WHERE cori.esRegistro = '1' "
            + "AND corp.idConfiguraRiesgo = :idConfiguraRiesgo ")
    List<PgimConfiguraRiesgoDTO> obtenerConfiguraRiesgoPorIdConfiguraRiesgoPadre(@Param("idConfiguraRiesgo") Long idConfiguraRiesgo);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTOResultado( "
			+ "cfri.idConfiguraRiesgo, "
			+ "cfri.noConfiguracion "
			+ ")"
			+ "FROM PgimCfgFactorRiesgo cffr "
			+ "INNER JOIN cffr.pgimCfgGrupoRiesgo cfgr "
			+ "INNER JOIN cfgr.pgimConfiguraRiesgo cfri "
			+ "WHERE 1=1 "
			+ "AND cffr.pgimFactorRiesgo.idFactorRiesgo = :idFactorRiesgo "
			+ "AND cffr.esRegistro = '1' "
			+ "AND cfgr.esRegistro = '1' "
			+ "AND cfri.esRegistro = '1' "
			+ "ORDER BY cfri.idConfiguraRiesgo "
			)
	List<PgimConfiguraRiesgoDTO> listarConfiguraRiesgoPorIdFactor(
			@Param("idFactorRiesgo") Long idFactorRiesgo);
	
	/**
	 * Permite obtener la cantidad de configuraciones de riesgo pendientes de atención que tiene un usuario 
	 * 
	 * @param usuarioAsignado
	 * @return
	 */
	@Query("SELECT COUNT(cori.idConfiguraRiesgoAux) "
            + "FROM PgimConfiguraRiesgoAux cori " 
            + "WHERE cori.esRegistro = '1' "
			+ "AND cori.flPasoActivo = '1' AND LOWER(cori.noUsuarioAsignado) = LOWER(:usuarioAsignado) AND cori.idTipoRelacion NOT IN (291, 292) " 			          
            )
    Integer contarConfiguraRiesgoPendientes(
            @Param("usuarioAsignado") String usuarioAsignado
            );
	
}
