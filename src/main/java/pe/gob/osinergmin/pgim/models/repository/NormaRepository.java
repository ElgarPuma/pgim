package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimNormaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimNorma;

import java.util.Date;
import java.util.List;

/**
 * @descripción: Lógica de negocio de la entidad Norma Legal
 * 
 * @author: PresleyRomero
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface NormaRepository extends JpaRepository<PgimNorma, Long> {
	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaDTOResultado(pnorma.idNorma, pnorma.tipoNorma.idValorParametro, pnorma.noCortoNorma, pnorma.noNorma, pnorma.fePublicacion, pnorma.flVigente) "
            + "FROM PgimNorma pnorma "
            + "WHERE pnorma.esRegistro = '1' "
            + "AND pnorma.idNorma = :idNorma ")
    PgimNormaDTO obtenerNormaPorId(@Param("idNorma") Long idNorma);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaAuxDTOResultado(pnorma.pgimNorma.idNorma, pnorma.noCortoNorma, pnorma.noNorma, pnorma.flVigente, pnorma.fePublicacion, pnorma.idTipoNorma, pnorma.noTipoNorma) "
            + "FROM PgimNormaAux pnorma "
            + "WHERE pnorma.pgimNorma.esRegistro = '1' "
            + "AND pnorma.pgimNorma.idNorma = :idNorma ")
    PgimNormaAuxDTO obtenerNormaAuxPorId(@Param("idNorma") Long idNorma);
	
	
	/**
	 * 
	 * @param palabraClave
	 * @return
	 */	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaDTOResultado(pnorma.idNorma, pnorma.noCortoNorma, pnorma.noNorma) "
            + "FROM PgimNorma pnorma "
			+ "WHERE pnorma.esRegistro = '1' "
            + "AND LOWER(pnorma.noCortoNorma) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<PgimNormaDTO> listarPorPalabraClave(String palabraClave);
    
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaDTOResultado(pnorma.idNorma, pnorma.noCortoNorma, pnorma.noNorma) "
            + "FROM PgimNorma pnorma "
			+ "WHERE pnorma.esRegistro = '1' "
			+ "AND (:noCortoNorma IS NULL OR LOWER(pnorma.noCortoNorma) LIKE LOWER(CONCAT('%', :noCortoNorma, '%')) ) "
			+ "AND (:noNorma IS NULL OR LOWER(pnorma.noNorma) LIKE LOWER(CONCAT('%', :noNorma, '%')) ) "
			+ "AND (:coTipoNorma IS NULL OR ((:coTipoNorma = '0' AND pnorma.tipoNorma.coClaveTexto <> :coTipoCuadroverificacion ) OR (:coTipoNorma <> '0' AND pnorma.tipoNorma.coClaveTexto = :coTipoNorma))) ")
    List<PgimNormaDTO> listarNormaPorNoCortoONoNormaYTipoNorma(@Param("noCortoNorma") String noCortoNorma,
    		@Param("noNorma") String noNorma,
    		@Param("coTipoNorma") String coTipoNorma,
    		@Param("coTipoCuadroverificacion") String coTipoCuadroverificacion);
   	
	/**
	 * Permite listar las normas de acuerdo con los criterios filtro pre-establecidos 
	 * 
	 * @param noCortoNorma
	 * @param noNorma
	 * @param idTipoNorma
	 * @param textoBusqueda
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaAuxDTOResultado(pnorma.pgimNorma.idNorma, pnorma.noCortoNorma, pnorma.noNorma, pnorma.flVigente, pnorma.fePublicacion, pnorma.idTipoNorma, pnorma.noTipoNorma ) "
    		+ "FROM PgimNormaAux pnorma "
    		+ "WHERE pnorma.pgimNorma.esRegistro = '1' "
    		+ "AND (:noCortoNorma IS NULL OR LOWER(pnorma.noCortoNorma) LIKE LOWER(CONCAT('%', :noCortoNorma, '%')) ) "
    		+ "AND (:noNorma IS NULL OR LOWER(pnorma.noNorma) LIKE LOWER(CONCAT('%', :noNorma, '%')) ) "
    		+ "AND (:idTipoNorma IS NULL OR pnorma.idTipoNorma = :idTipoNorma) "
    		+ "AND (:textoBusqueda IS NULL "
    		+ "OR (LOWER(pnorma.noCortoNorma) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))) "
    		+ "OR (LOWER(pnorma.noNorma) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))))")
    Page<PgimNormaAuxDTO> listar(    		
    		@Param("noCortoNorma") String noCortoNorma,
    		@Param("noNorma") String noNorma,
    		@Param("idTipoNorma") Long idTipoNorma,
    		@Param("textoBusqueda") String textoBusqueda, 
            Pageable paginador);


	/**
	 * Me permite listar los items de tipificación
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaDTOResultado( " 
            + "pno.idNorma, " 
            + "pno.noCortoNorma, pno.noNorma, pno.fePublicacion, "
            + "pno.flVigente "
            + " ) "
            + "FROM PgimNorma pno " 
            + "WHERE pno.esRegistro = '1' "
			+ "AND pno.tipoNorma.idValorParametro = 384 "
			+ "AND (:noCortoNorma IS NULL OR LOWER(pno.noCortoNorma) LIKE LOWER(CONCAT('%', :noCortoNorma, '%')) ) "
			+ "AND (:fePublicacion IS NULL OR TRUNC(pno.fePublicacion) = :fePublicacion ) "
			+ "AND (:flVigente IS NULL OR LOWER(pno.flVigente) LIKE LOWER(CONCAT('%', :flVigente, '%')) ) "
            )
    Page<PgimNormaDTO> listarTipificacion(@Param("noCortoNorma") String noCortoNorma,
										  @Param("fePublicacion") Date fePublicacion,
										  @Param("flVigente") String flVigente,
										  Pageable paginador);

	/**
	 * Me permite obtener la lista de item de tipificación
	 * @param idNorma
	 * @return
	 */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaDTOResultado(" 
            + "pno.idNorma, " 
            + "pno.noCortoNorma, pno.noNorma, pno.fePublicacion, "
            + "pno.flVigente "

            + ") "

            + "FROM PgimNorma pno " 
            + "WHERE pno.esRegistro = '1' "
            + "AND pno.idNorma = :idNorma "
            )
	PgimNormaDTO obtenerTipificacionPorId(@Param("idNorma") Long idNorma);
    
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaDTOResultado(pnorma.idNorma, pnorma.noCortoNorma, pnorma.noNorma) "
            + "FROM PgimNorma pnorma "
			+ "WHERE pnorma.esRegistro = '1' "
			+ "AND pnorma.tipoNorma.idValorParametro = :idTipoNorma "
			+ "ORDER BY pnorma.fePublicacion DESC NULLS LAST" )
	List<PgimNormaDTO> listarCuadroTipificacion(@Param("idTipoNorma") Long idTipoNorma);
	
	/**
	 * Me permite obtener la lista de normas vinculados a un cuadro de verificción
	 * @param idMatrizSupervision
	 * @param coTipoCuadroverificacion
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaAuxDTOResultado(norm.idNorma, "
			+ "CASE "
			+ "     WHEN tino.coClaveTexto = :coTipoCuadroverificacion THEN "
			+ "        'Cuadro de tipificación' "
			+ "     ELSE "
			+ "         'Obligación normativa (no tipificación)' "
			+ "END, "
			
			+ "norm.noCortoNorma, norm.noNorma, "
			+ "tino.idValorParametro, tino.deValorParametro, norm.fePublicacion, "
			+ "norm.flVigente ) "
			+ "FROM PgimNorma norm "
			+ "INNER JOIN norm.tipoNorma tino "
			+ "WHERE 1 = 1 "
			+ "AND EXISTS (SELECT 1 "
				+ "FROM PgimNormaObligacion noob "
				+ "INNER JOIN PgimOblgcnNrmaCrtrio nocr ON noob.idNormaObligacion = nocr.pgimNormaObligacion.idNormaObligacion "
				+ "INNER JOIN PgimMatrizCriterio macr ON nocr.pgimMatrizCriterio.idMatrizCriterio = macr.idMatrizCriterio "
				+ "INNER JOIN PgimMatrizGrpoCrtrio magr ON macr.pgimMatrizGrpoCrtrio.idMatrizGrpoCrtrio = magr.idMatrizGrpoCrtrio "
				+ "INNER JOIN PgimMatrizSupervision masu ON magr.pgimMatrizSupervision.idMatrizSupervision = masu.idMatrizSupervision "
				+ "WHERE 1 = 1 "
				+ "AND masu.idMatrizSupervision = :idMatrizSupervision "
				+ "AND noob.esRegistro = '1' "
				+ "AND nocr.esRegistro = '1' "
				+ "AND macr.esRegistro = '1' "
				+ "AND magr.esRegistro = '1' "
				+ "AND masu.esRegistro = '1' "
				+ "AND ("
					// -- Para normas relacionados a los cuadros de tipificación
					+ "EXISTS (SELECT 1 "
					+ "FROM PgimItemTipificacion itti "
					+ "WHERE 1 = 1 "
					+ "AND itti.idItemTipificacion = noob.pgimItemTipificacion.idItemTipificacion " // Relación con los ítems de tipificación
					+ "AND itti.pgimNorma.idNorma = norm.idNorma " // Relación con la norma
					+ "AND itti.esVigente = '1' "
					+ "AND itti.esRegistro = '1' "
					+ ") "

					//-- Para normas relacionados a los otros ítems de norma que no son del cuadro de tipificación
					+ "OR EXISTS (SELECT 1 "
					+ "FROM PgimNormaItem noit "
					+ "WHERE 1 = 1 "
					+ "AND noit.idNormaItem = noob.pgimNormaItem.idNormaItem " // Relación con los ítems de norma
					+ "AND noit.pgimNorma.idNorma = norm.idNorma " // Relación con la norma
					+ "AND noit.flVigente = '1' "
					+ "AND noit.esRegistro = '1' "
					+ ") "
				+ ") "
			+ ") "
			+ "AND norm.esRegistro = '1' "
			+ "ORDER BY norm.fePublicacion DESC NULLS LAST, "
			+ "norm.flVigente DESC, tino.noValorParametro "
			)
	Page<PgimNormaAuxDTO> listarNormasDeCuadroVerificacion(@Param("idMatrizSupervision") Long idMatrizSupervision, 
			@Param("coTipoCuadroverificacion") String coTipoCuadroverificacion, 
			Pageable paginador);
}
