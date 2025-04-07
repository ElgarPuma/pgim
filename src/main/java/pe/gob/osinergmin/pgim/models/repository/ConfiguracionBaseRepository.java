package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguracionBase;

/** 
 * @descripción: Lógica de negocio de la entidad configuracion base
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 04/04/2022
 */
public interface ConfiguracionBaseRepository extends JpaRepository<PgimConfiguracionBase, Long> {
	
	/**
	 * permite obtener un listado de forma pagina de las configuraciones base
	 * @param idTipoConfiguracionBase
	 * @param idEspecialidad
	 * @param noCofiguracionBase
	 * @param textoBusqueda
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto ) "
            + "FROM PgimConfiguracionBase cfg "
            + "WHERE cfg.esRegistro = '1' "
            + "AND (:idTipoConfiguracionBase IS NULL OR cfg.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase )"
            + "AND (:idEspecialidad IS NULL OR  cfg.pgimEspecialidad.idEspecialidad = :idEspecialidad )"
            + "AND (:noCofiguracionBase IS NULL OR  LOWER(cfg.noCofiguracionBase) LIKE LOWER(CONCAT('%', :noCofiguracionBase, '%')) )"
            + "AND (:textoBusqueda IS NULL OR ( "
            + "LOWER(cfg.tipoConfiguracionBase.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(cfg.noCofiguracionBase) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(cfg.deCofiguracionBase) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "))" )
    Page<PgimConfiguracionBaseDTO> listar(
    		@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase, 
    		@Param("idEspecialidad") Long idEspecialidad, 
    		@Param("noCofiguracionBase") String noCofiguracionBase, 
    		@Param("textoBusqueda") String textoBusqueda, 
    		Pageable paginador);
	
	/**
	 * permite obtener una configuración base por su identificador
	 * @param idConfiguracionBase
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND cfg.idConfiguracionBase = :idConfiguracionBase" )
	PgimConfiguracionBaseDTO obtenerCfgBasePorId(@Param("idConfiguracionBase") Long idConfiguracionBase);

	/**
	 * Permite obtener las configuraciones que tienen el mismo nombre para hacer la validación de ser únicos
	 * @param idConfiguracionBase
	 * @param noCofiguracionBase
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND (:idConfiguracionBase IS NULL OR cfg.idConfiguracionBase != :idConfiguracionBase ) "
			+ "AND TRIM(LOWER(cfg.noCofiguracionBase)) = TRIM(LOWER(:noCofiguracionBase)) "
			)
	List<PgimConfiguracionBaseDTO> existeCfg(@Param("idConfiguracionBase") Long idConfiguracionBase, @Param("noCofiguracionBase") String noCofiguracionBase );
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND cfg.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "
			+ "ORDER BY cfg.pgimEspecialidad.noEspecialidad, cfg.idConfiguracionBase DESC "
			)
	List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBase(@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND cfg.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "
			+ "AND NOT EXISTS (SELECT 1 " 
                    + "FROM PgimTdrPlantilla tdr " 
                    + "WHERE tdr.esRegistro = '1' " 
                    + "AND tdr.pgimConfiguracionBase = cfg) "
			+ "ORDER BY cfg.pgimEspecialidad.noEspecialidad, cfg.idConfiguracionBase DESC "
			)
	List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseTDR(@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND cfg.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "
			+ "AND NOT EXISTS (SELECT 1 " 
                    + "FROM PgimMatrizSupervision cverif " 
                    + "WHERE cverif.esRegistro = '1' " 
                    + "AND cverif.pgimConfiguracionBase = cfg) "
			+ "ORDER BY cfg.pgimEspecialidad.noEspecialidad, cfg.idConfiguracionBase DESC "
			)
	List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseCVERIF(@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND cfg.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "
			+ "AND NOT EXISTS (SELECT 1 " 
                    + "FROM PgimSolicitudBaseDoc solbd " 
                    + "WHERE solbd.esRegistro = '1' " 
                    + "AND solbd.pgimConfiguracionBase = cfg) "
			+ "ORDER BY cfg.pgimEspecialidad.noEspecialidad, cfg.idConfiguracionBase DESC "
			)
	List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseSOLBD(@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
	+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto ) "
	+ "FROM PgimConfiguracionBase cfg "
	+ "WHERE cfg.esRegistro = '1' "
	+ "AND cfg.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "
	+ "AND cfg.pgimEspecialidad.idEspecialidad = :idEspecialidad "
	+ "ORDER BY cfg.pgimEspecialidad.noEspecialidad, cfg.idConfiguracionBase DESC "
	)
    List<PgimConfiguracionBaseDTO> listaCfgBasePorTipoCfgBaseYEspecialidad(@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase,
	@Param("idEspecialidad") Long idEspecialidad);


	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, "
			+ "cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, "
			+ "cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto, 'TDRB-'|| tdr.idTdrPlantilla ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "INNER JOIN PgimTdrPlantilla tdr ON ( tdr.pgimConfiguracionBase = cfg AND tdr.esRegistro = '1' )"
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND cfg.idConfiguracionBase = :idConfiguracionBase "
			)
	List<PgimConfiguracionBaseDTO> existeCfgBEnTDR(@Param("idConfiguracionBase") Long idConfiguracionBase);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, "
			+ "cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, "
			+ "cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto, 'CVERIF-' || cverif.idMatrizSupervision || '. ' || cverif.deMatrizSupervision ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "INNER JOIN PgimMatrizSupervision cverif ON ( cverif.pgimConfiguracionBase = cfg AND cverif.esRegistro = '1' )"
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND cfg.idConfiguracionBase = :idConfiguracionBase "
			)
	List<PgimConfiguracionBaseDTO> existeCfgBEnCVERIF(@Param("idConfiguracionBase") Long idConfiguracionBase);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTOResultado( cfg.idConfiguracionBase, "
			+ "cfg.tipoConfiguracionBase.idValorParametro, cfg.noCofiguracionBase, cfg.deCofiguracionBase, "
			+ "cfg.tipoConfiguracionBase.noValorParametro, cfg.pgimEspecialidad.idEspecialidad, "
			+ "cfg.pgimEspecialidad.noEspecialidad, cfg.tipoConfiguracionBase.coClaveTexto, 'SOLBD-' || solbd.idSolicitudBaseDoc || '. ' || solbd.noSolicitudBaseDoc ) "
			+ "FROM PgimConfiguracionBase cfg "
			+ "INNER JOIN PgimSolicitudBaseDoc solbd ON ( solbd.pgimConfiguracionBase = cfg AND solbd.esRegistro = '1' )"
			+ "WHERE cfg.esRegistro = '1' "
			+ "AND cfg.idConfiguracionBase = :idConfiguracionBase "
			)
	List<PgimConfiguracionBaseDTO> existeCfgBEnSOLBD(@Param("idConfiguracionBase") Long idConfiguracionBase);
}
