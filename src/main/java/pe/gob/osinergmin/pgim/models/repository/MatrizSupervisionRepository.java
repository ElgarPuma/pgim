package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMatrizSupervision;

/**
 * 
 * @descripción: Logica de negocio de la entidad Matriz de Supervisión
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 13/05/2021
 * 
 */
@Repository
public interface MatrizSupervisionRepository extends JpaRepository<PgimMatrizSupervision, Long> {

        /**
         * Permite mostrar la lista de matriz de supervision
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTOResultado( "
                        + "mtz.idMatrizSupervision, mtz.pgimEspecialidad.noEspecialidad, mtz.deMatrizSupervision, "
                        + "mtz.feCreacion, "
                        + "cfba.idConfiguracionBase, "
                        + "cfba.noCofiguracionBase, " 
                        + "mtz.pgimEspecialidad.idEspecialidad, "
                        + "mtz.flActivo "
                        + ") " 
                        + "FROM PgimMatrizSupervision mtz " 
                        + "LEFT OUTER JOIN mtz.pgimConfiguracionBase cfba " 
                        + "WHERE 1=1 "
                        + "AND ((:descNoCortoNorma IS NULL AND :descNoNorma IS NULL AND :descCoTipoNorma IS NULL AND :descNoGrupoNorma IS NULL ) "
                        + "OR EXISTS (SELECT 1 "
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
	            				+ "AND mtz.idMatrizSupervision = masu.idMatrizSupervision "
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
	            			+ "AND (:descNoCortoNorma IS NULL OR LOWER(norm.noCortoNorma) LIKE LOWER(CONCAT('%', :descNoCortoNorma, '%')) ) "
	            			+ "AND (:descNoNorma IS NULL OR LOWER(norm.noNorma) LIKE LOWER(CONCAT('%', :descNoNorma, '%')) ) "
	            			+ "AND (:descCoTipoNorma IS NULL OR tino.coClaveTexto = :descCoTipoNorma ) "
	            			+ "AND (:descNoGrupoNorma IS NULL OR ( (:descNoGrupoNorma = :coTipoCuadroverificacion AND tino.coClaveTexto = :coTipoCuadroverificacion ) " 
	            			+ "	OR (:descNoGrupoNorma <> :coTipoCuadroverificacion AND tino.coClaveTexto <> :coTipoCuadroverificacion)) ) "
                        +") )"
                        + "AND mtz.esRegistro = '1'"
                        + "AND (:idEspecialidad IS NULL OR mtz.pgimEspecialidad.idEspecialidad = :idEspecialidad ) "
                        + "AND (:descripcion IS NULL OR LOWER(mtz.deMatrizSupervision) LIKE LOWER(CONCAT('%', :descripcion, '%')) ) "
                        + "AND (:flActivo IS NULL OR LOWER(mtz.flActivo) LIKE LOWER(CONCAT('%', :flActivo, '%')) ) "
                        + "ORDER BY mtz.feCreacion DESC, mtz.pgimEspecialidad.noEspecialidad")
        List<PgimMatrizSupervisionDTO> listarMatrizSupervision(@Param("idEspecialidad") Long noCortoNorma,
                                                                @Param("descripcion") String descripcion,
                                                                @Param("flActivo") String flActivo,
                                                                @Param("descNoCortoNorma") String descNoCortoNorma,
                                                                @Param("descNoNorma") String descNoNorma,
                                                                @Param("descNoGrupoNorma") String descNoGrupoNorma,
                                                                @Param("descCoTipoNorma") String descCoTipoNorma,
                                                                @Param("coTipoCuadroverificacion") String coTipoCuadroverificacion);

        /**
         * Obtener las propiedades de la lista de matriz de supervisión
         * @param idMatrizSupervision identificador de matriz de supervisión
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTOResultado("
                        + "mtz.idMatrizSupervision, mtz.pgimEspecialidad.idEspecialidad, mtz.pgimEspecialidad.noEspecialidad, "
                        + "mtz.deMatrizSupervision, mtz.feCreacion, cfba.idConfiguracionBase, "
                        + "cfba.noCofiguracionBase, cfba.tipoConfiguracionBase.idValorParametro, cfba.deCofiguracionBase, "
                        + "mtz.flActivo ) "
                        + "FROM PgimMatrizSupervision mtz " 
                        + "LEFT OUTER JOIN mtz.pgimConfiguracionBase cfba " 
                        + "WHERE mtz.esRegistro = '1' "
                        + "AND mtz.idMatrizSupervision = :idMatrizSupervision ")
        PgimMatrizSupervisionDTO obtenerMatrizSupervisionPorId(@Param("idMatrizSupervision") Long idMatrizSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTOResultado(" 
                        + "mtz.idMatrizSupervision, " 
                        + "TRIM(UPPER(mtz.deMatrizSupervision)) " 
                        + " ) " 
                        + "FROM PgimMatrizSupervision mtz "
                        + "WHERE mtz.esRegistro = '1' " 
                        + "AND (:idMatrizSupervision IS NULL OR mtz.idMatrizSupervision != :idMatrizSupervision) "
                        + "AND TRIM(UPPER(mtz.deMatrizSupervision)) = :deMatrizSupervision "
                        + " ")
        List<PgimMatrizSupervisionDTO> validarExisteCuadroVerificacion(@Param("idMatrizSupervision") Long idMatrizSupervision,
        @Param("deMatrizSupervision") String deMatrizSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTOResultado(" 
        + "masu.idMatrizSupervision, masu.deMatrizSupervision, coba.idConfiguracionBase, masu.feCreacion " 
        + " ) " 
        + "FROM PgimMatrizSupervision masu "
        + "LEFT OUTER JOIN masu.pgimConfiguracionBase coba "
        + "WHERE 1 = 1 " 
        + "AND (coba.idConfiguracionBase IS NULL OR (coba.esRegistro = '1' AND masu.flActivo = '1' ) ) "
        + "AND (:idEspecialidad IS NULL OR masu.pgimEspecialidad.idEspecialidad = :idEspecialidad) "
        + "AND EXISTS ( "
        + "SELECT 1 "
        + "FROM PgimMatrizCriterio macr "
        + "     INNER JOIN macr.pgimMatrizGrpoCrtrio magc "
        + "WHERE magc.pgimMatrizSupervision.idMatrizSupervision = masu.idMatrizSupervision  "
        + "AND magc.esRegistro = '1' "
        + "AND macr.esRegistro = '1' "
        + "AND macr.esRegistro = '1' "
        + ") "
        + "AND masu.esRegistro = '1' "
        + " ")
	Page<PgimMatrizSupervisionDTO> listarMatrizSupervision(@Param("idEspecialidad") Long idEspecialidad, Pageable paginador);

}
