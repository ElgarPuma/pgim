package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimReglaBase;

/** 
 * @descripción: Lógica de negocio de la entidad regla de una configuracion base
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 04/04/2022
 */
public interface ReglaBaseRepository extends JpaRepository<PgimReglaBase, Long>{
	
	/**
	 * Permite obtener un listado de reglas de una determinada configuración base
	 * @param idConfiguracionBase
	 * @return
	 */
  	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTOResultado( regla.idReglaBase, cfg.idConfiguracionBase, cfg.noCofiguracionBase, "
		+ "ds.idValorParametro, ds.noValorParametro, mt.idMotivoSupervision, mt.deMotivoSupervision, "
		+ "stsup.idSubtipoSupervision, stsup.deSubtipoSupervision, mm.idValorParametro, mm.noValorParametro, "
		+ "regla.flPropia, regla.flConPiques, tsup.noValorParametro, tsup.idValorParametro, "
		+ "esp.noEspecialidad, esp.idEspecialidad, cfg.tipoConfiguracionBase.idValorParametro ) "
		+ "FROM PgimReglaBase regla "
  		+ "INNER JOIN regla.pgimConfiguracionBase cfg  "
  		+ "INNER JOIN regla.divisionSupervisora ds "
  		+ "INNER JOIN regla.pgimMotivoSupervision mt "
  		+ "INNER JOIN regla.metodoMinado mm "
		+ "INNER JOIN regla.pgimSubtipoSupervision stsup "
		+ "INNER JOIN regla.pgimSubtipoSupervision.tipoSupervision tsup "
		+ "INNER JOIN regla.pgimSubtipoSupervision.pgimEspecialidad esp "
		+ "WHERE regla.esRegistro = '1' "
		+ "AND cfg.idConfiguracionBase = :idConfiguracionBase" )
	List<PgimReglaBaseDTO> listarReglasPorCfgBase(@Param("idConfiguracionBase") Long idConfiguracionBase);

  	/**
  	 * Permite obtener una regla según su identificador
  	 * @param idReglaBase
  	 * @return
  	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTOResultado( regla.idReglaBase, regla.pgimConfiguracionBase.idConfiguracionBase, regla.pgimConfiguracionBase.noCofiguracionBase, "
			+ "regla.divisionSupervisora.idValorParametro, regla.divisionSupervisora.noValorParametro, regla.pgimMotivoSupervision.idMotivoSupervision, regla.pgimMotivoSupervision.deMotivoSupervision, "
			+ "regla.pgimSubtipoSupervision.idSubtipoSupervision, regla.pgimSubtipoSupervision.deSubtipoSupervision, regla.metodoMinado.idValorParametro, regla.metodoMinado.noValorParametro, regla.flPropia, regla.flConPiques) "
            + "FROM PgimReglaBase regla "
            + "WHERE regla.esRegistro = '1' " 
            + "AND regla.idReglaBase = :idReglaBase" )
	PgimReglaBaseDTO obtenerReglaBasePorId(@Param("idReglaBase") Long idReglaBase);
	
	/**
	 * Permite obtener las reglas que se traslapen
	 * @param idDivisionSupervisora
	 * @param idMotivoSupervision
	 * @param idSubtipoSupervision
	 * @param idMetodoMinado
	 * @param flPropia
	 * @param flConPiques
	 * @param idRegla
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTOResultado( regla.idReglaBase, regla.pgimConfiguracionBase.idConfiguracionBase, regla.pgimConfiguracionBase.noCofiguracionBase, "
			+ "regla.divisionSupervisora.idValorParametro, regla.divisionSupervisora.noValorParametro, regla.pgimMotivoSupervision.idMotivoSupervision, regla.pgimMotivoSupervision.deMotivoSupervision, "
			+ "regla.pgimSubtipoSupervision.idSubtipoSupervision, regla.pgimSubtipoSupervision.deSubtipoSupervision, regla.metodoMinado.idValorParametro, regla.metodoMinado.noValorParametro, regla.flPropia, regla.flConPiques) "
            + "FROM PgimReglaBase regla "
            + "WHERE regla.esRegistro = '1' "
            + "AND ( :idRegla IS NULL OR regla.idReglaBase <> :idRegla) " 
            + "AND regla.divisionSupervisora.idValorParametro = :idDivisionSupervisora "
            + "AND regla.pgimMotivoSupervision.idMotivoSupervision = :idMotivoSupervision "
            + "AND regla.pgimSubtipoSupervision.idSubtipoSupervision = :idSubtipoSupervision "
            + "AND regla.metodoMinado.idValorParametro = :idMetodoMinado "
            + "AND regla.flPropia = :flPropia "
            + "AND regla.flConPiques = :flConPiques "
            + "AND regla.pgimConfiguracionBase.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "
			+ "AND regla.pgimConfiguracionBase.esRegistro = '1' "
			)
	List<PgimReglaBaseDTO> listarReglasTraslapadas(
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idMotivoSupervision") Long idMotivoSupervision,
			@Param("idSubtipoSupervision") Long idSubtipoSupervision, 
			@Param("idMetodoMinado") Long idMetodoMinado,
			@Param("flPropia") String flPropia,
			@Param("flConPiques") String flConPiques,
			@Param("idRegla") Long idRegla,
			@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase );

	/**
	 *  Permite obtener las reglas que se traslapen para el cuadro de verificación
	 * 
	 * @param idDivisionSupervisora
	 * @param idMotivoSupervision
	 * @param idSubtipoSupervision
	 * @param idMetodoMinado
	 * @param flPropia
	 * @param flConPiques
	 * @param idRegla
	 * @param idTipoConfiguracionBase
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTOResultado( regla.idReglaBase, regla.pgimConfiguracionBase.idConfiguracionBase, regla.pgimConfiguracionBase.noCofiguracionBase, "
		+ "regla.divisionSupervisora.idValorParametro, regla.divisionSupervisora.noValorParametro, regla.pgimMotivoSupervision.idMotivoSupervision, regla.pgimMotivoSupervision.deMotivoSupervision, "
		+ "regla.pgimSubtipoSupervision.idSubtipoSupervision, regla.pgimSubtipoSupervision.deSubtipoSupervision, regla.metodoMinado.idValorParametro, regla.metodoMinado.noValorParametro, regla.flPropia, regla.flConPiques) "
		+ "FROM PgimReglaBase regla "
		+ "WHERE regla.esRegistro = '1' "
		+ "AND ( :idRegla IS NULL OR regla.idReglaBase <> :idRegla) " 
		+ "AND regla.divisionSupervisora.idValorParametro = :idDivisionSupervisora "
		+ "AND regla.pgimMotivoSupervision.idMotivoSupervision = :idMotivoSupervision "
		+ "AND regla.pgimSubtipoSupervision.idSubtipoSupervision = :idSubtipoSupervision "
		+ "AND regla.metodoMinado.idValorParametro = :idMetodoMinado "
		+ "AND regla.flPropia = :flPropia "
		+ "AND regla.flConPiques = :flConPiques "
		+ "AND regla.pgimConfiguracionBase.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "

		+ "AND EXISTS (SELECT 1 "
		+ "  FROM PgimMatrizSupervision mtz "
		+ "  WHERE mtz.pgimConfiguracionBase = regla.pgimConfiguracionBase "
		+ "  AND mtz.esRegistro = '1' "
		+ "  AND mtz.flActivo = '1' )"

		+ "AND regla.pgimConfiguracionBase.esRegistro = '1' "
		)
	List<PgimReglaBaseDTO> listarReglasTraslapadasCuadroVerificacion(
		@Param("idDivisionSupervisora") Long idDivisionSupervisora,
		@Param("idMotivoSupervision") Long idMotivoSupervision,
		@Param("idSubtipoSupervision") Long idSubtipoSupervision, 
		@Param("idMetodoMinado") Long idMetodoMinado,
		@Param("flPropia") String flPropia,
		@Param("flConPiques") String flConPiques,
		@Param("idRegla") Long idRegla,
		@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase );
	
	/**
	 * Permite obtener la regla que es aplicable para una fiscalización
	 * @param idDivisionSupervisora
	 * @param idMotivoSupervision
	 * @param idSubtipoSupervision
	 * @param idMetodoMinado
	 * @param flPropia
	 * @param flConPiques
	 * @param idTipoConfiguracionBase
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTOResultado("
	        + "regla.idReglaBase, regla.pgimConfiguracionBase.idConfiguracionBase, regla.pgimConfiguracionBase.noCofiguracionBase, "
			+ "regla.divisionSupervisora.idValorParametro, regla.divisionSupervisora.noValorParametro, regla.pgimMotivoSupervision.idMotivoSupervision, "
			+ "regla.pgimMotivoSupervision.deMotivoSupervision, regla.pgimSubtipoSupervision.idSubtipoSupervision, regla.pgimSubtipoSupervision.deSubtipoSupervision, "
			+ "regla.metodoMinado.idValorParametro, regla.metodoMinado.noValorParametro, regla.flPropia, "
			+ "regla.flConPiques "
			+") "
            + "FROM PgimReglaBase regla "
            + "INNER JOIN regla.pgimConfiguracionBase cfg "
            + "WHERE regla.esRegistro = '1' AND cfg.esRegistro = '1' "
            + "AND regla.divisionSupervisora.idValorParametro = :idDivisionSupervisora "
            + "AND regla.pgimMotivoSupervision.idMotivoSupervision = :idMotivoSupervision "
            + "AND regla.pgimSubtipoSupervision.idSubtipoSupervision = :idSubtipoSupervision "
            + "AND regla.metodoMinado.idValorParametro = :idMetodoMinado "
            + "AND regla.flPropia = :flPropia "
            + "AND regla.flConPiques = :flConPiques "
            + "AND regla.pgimConfiguracionBase.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "
			)
	PgimReglaBaseDTO obtenerReglaPorSupervision(
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idMotivoSupervision") Long idMotivoSupervision,
			@Param("idSubtipoSupervision") Long idSubtipoSupervision, 
			@Param("idMetodoMinado") Long idMetodoMinado,
			@Param("flPropia") String flPropia,
			@Param("flConPiques") String flConPiques,
			@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTOResultado("
	        + "regla.idReglaBase, regla.pgimConfiguracionBase.idConfiguracionBase, regla.pgimConfiguracionBase.noCofiguracionBase, "
			+ "regla.divisionSupervisora.idValorParametro, regla.divisionSupervisora.noValorParametro, regla.pgimMotivoSupervision.idMotivoSupervision, "
			+ "regla.pgimMotivoSupervision.deMotivoSupervision, regla.pgimSubtipoSupervision.idSubtipoSupervision, regla.pgimSubtipoSupervision.deSubtipoSupervision, "
			+ "regla.metodoMinado.idValorParametro, regla.metodoMinado.noValorParametro, regla.flPropia, "
			+ "regla.flConPiques "
			+") "
            + "FROM PgimReglaBase regla "
            + "INNER JOIN regla.pgimConfiguracionBase cfg "
            + "WHERE regla.esRegistro = '1' AND cfg.esRegistro = '1' "
            + "AND regla.divisionSupervisora.idValorParametro = :idDivisionSupervisora "
            + "AND regla.pgimMotivoSupervision.idMotivoSupervision = :idMotivoSupervision "
            + "AND regla.pgimSubtipoSupervision.idSubtipoSupervision = :idSubtipoSupervision "
            + "AND regla.metodoMinado.idValorParametro = :idMetodoMinado "
            + "AND regla.flPropia = :flPropia "
            + "AND regla.flConPiques = :flConPiques "
            + "AND regla.pgimConfiguracionBase.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "
			)
	List<PgimReglaBaseDTO> obtenerListaReglaPorSupervision(
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idMotivoSupervision") Long idMotivoSupervision,
			@Param("idSubtipoSupervision") Long idSubtipoSupervision, 
			@Param("idMetodoMinado") Long idMetodoMinado,
			@Param("flPropia") String flPropia,
			@Param("flConPiques") String flConPiques,
			@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase);

	/**
	 * Permite obtener la regla de cuadro de verifiación que es aplicable para una fiscalización 
	 * @param idDivisionSupervisora
	 * @param idMotivoSupervision
	 * @param idSubtipoSupervision
	 * @param idMetodoMinado
	 * @param flPropia
	 * @param flConPiques
	 * @param idTipoConfiguracionBase
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTOResultado( regla.idReglaBase, regla.pgimConfiguracionBase.idConfiguracionBase, regla.pgimConfiguracionBase.noCofiguracionBase, "
			+ "regla.divisionSupervisora.idValorParametro, regla.divisionSupervisora.noValorParametro, regla.pgimMotivoSupervision.idMotivoSupervision, regla.pgimMotivoSupervision.deMotivoSupervision, "
			+ "regla.pgimSubtipoSupervision.idSubtipoSupervision, regla.pgimSubtipoSupervision.deSubtipoSupervision, regla.metodoMinado.idValorParametro, regla.metodoMinado.noValorParametro, regla.flPropia, regla.flConPiques) "
            + "FROM PgimReglaBase regla "
            + "INNER JOIN regla.pgimConfiguracionBase cfg "
            + "WHERE regla.esRegistro = '1' AND cfg.esRegistro = '1' "
            + "AND regla.divisionSupervisora.idValorParametro = :idDivisionSupervisora "
            + "AND regla.pgimMotivoSupervision.idMotivoSupervision = :idMotivoSupervision "
            + "AND regla.pgimSubtipoSupervision.idSubtipoSupervision = :idSubtipoSupervision "
            + "AND regla.metodoMinado.idValorParametro = :idMetodoMinado "
            + "AND regla.flPropia = :flPropia "
            + "AND regla.flConPiques = :flConPiques "
            + "AND regla.pgimConfiguracionBase.tipoConfiguracionBase.idValorParametro = :idTipoConfiguracionBase "

						+ "AND EXISTS (SELECT 1 "
						+ "  FROM PgimMatrizSupervision mtz "
						+ "  WHERE mtz.pgimConfiguracionBase = regla.pgimConfiguracionBase "
						+ "  AND mtz.esRegistro = '1' "
						+ "  AND mtz.flActivo = '1' )"
			)
	PgimReglaBaseDTO obtenerReglaPorSupervisionCuadroVerificacion(
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idMotivoSupervision") Long idMotivoSupervision,
			@Param("idSubtipoSupervision") Long idSubtipoSupervision, 
			@Param("idMetodoMinado") Long idMetodoMinado,
			@Param("flPropia") String flPropia,
			@Param("flConPiques") String flConPiques,
			@Param("idTipoConfiguracionBase") Long idTipoConfiguracionBase);
	
}
