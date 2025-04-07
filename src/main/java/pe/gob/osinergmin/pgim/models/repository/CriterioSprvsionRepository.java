package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCriterioSprvsion;

import java.util.List;

/**
 *  
 * @descripción: Logica de negocio de la entidad Configuración de Criterios de la Matriz de Supervisión, para una supervisión dada 
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
@Repository
public interface CriterioSprvsionRepository extends JpaRepository<PgimCriterioSprvsion, Long> {
			
	
	/**
     * Permite obtener la pre-configuración de los criterios de la matriz de supervisión para una especialidad
     * 
     * @param idEspecialidad
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTOResultado("
                    + " cr.idMatrizCriterio,cr.idMatrizCriterio "  
                    + " ,cr.coMatrizCriterio,cr.deMatrizCriterio,cr.esVigente,cr.deBaseLegal "  
                    + " ,gr.idMatrizGrpoCrtrio,gr.pgimMatrizSupervision.idMatrizSupervision,gr.coMatrizGrpoCrtrio,gr.noMatrizGrpoCrtrio "
                    + " ,gr.nuOrden,gr.esVisible,cr.nuOrden ) " 
                    + "FROM PgimMatrizCriterio cr, PgimMatrizGrpoCrtrio gr, PgimMatrizSupervision ms "                    
                    + "WHERE cr.pgimMatrizGrpoCrtrio.idMatrizGrpoCrtrio=gr.idMatrizGrpoCrtrio and gr.pgimMatrizSupervision.idMatrizSupervision=ms.idMatrizSupervision "
                    + "and cr.esRegistro = '1' and gr.esRegistro = '1' and ms.esRegistro = '1' " 
                    + "and ms.pgimEspecialidad.idEspecialidad = :idEspecialidad "
                    )
    List<PgimCriterioSprvsionDTO> generarConfiguracionCriteriosSupervision(@Param("idEspecialidad") Long idEspecialidad);    
    
    /**
     * Permite obtener un registro de la entidad PgimCriterioSprvsion a partir de un identificador de la tabla (ID)
     * 
     * @param idCriterioSprvsion
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTOResultado("
            + "cs.idCriterioSprvsion, cs.pgimSupervision.idSupervision, cs.pgimMatrizCriterio.idMatrizCriterio, "
            + "cs.coMatrizCriterio, cs.deMatrizCriterio, cs.esVigenteMatrizCriterio, cs.deBaseLegal, "
            + "cs.idMatrizGrpoCrtrio, cs.idMatrizSupervision, cs.coMatrizGrpoCrtrio,cs.noMatrizGrpoCrtrio, "
            + "cs.esVisibleGrpoCrtrio ) "
            + "FROM PgimCriterioSprvsion cs " 
            + "WHERE cs.esRegistro = '1' "
            + "AND cs.idCriterioSprvsion = :idCriterioSprvsion ")
    PgimCriterioSprvsionDTO obtenerCriterioSprvsionById(@Param("idCriterioSprvsion") Long idCriterioSprvsion);
    
    /**
     * Permite obtener un registro de la entidad PgimCriterioSprvsion a partir de un identificador del matriz criterio y supervisión
     * 
     * @param idMatrizCriterio
     * @param idSupervision
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTOResultado("
            + "cs.idCriterioSprvsion, cs.pgimSupervision.idSupervision, cs.pgimMatrizCriterio.idMatrizCriterio, "
            + "cs.coMatrizCriterio, cs.deMatrizCriterio, cs.esVigenteMatrizCriterio, cs.deBaseLegal, "
            + "cs.idMatrizGrpoCrtrio, cs.idMatrizSupervision, cs.coMatrizGrpoCrtrio,cs.noMatrizGrpoCrtrio, "
            + "cs.esVisibleGrpoCrtrio ) "
            + "FROM PgimCriterioSprvsion cs " 
            + "WHERE cs.esRegistro = '1' "
            + "AND cs.pgimMatrizCriterio.idMatrizCriterio = :idMatrizCriterio "
            + "AND cs.pgimSupervision.idSupervision =  :idSupervision "
            )
    PgimCriterioSprvsionDTO obtenerCriterioSprvsionByIdMatrizCriterioYIdSupervision(@Param("idMatrizCriterio") Long idMatrizCriterio, 
    		@Param("idSupervision") Long idSupervision);
    
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTOResultado("
            + "crsupe.idCriterioSprvsion, crsupe.coMatrizGrpoCrtrio, crsupe.noMatrizGrpoCrtrio, crsupe.coMatrizCriterio, crsupe.deMatrizCriterio, crsupe.deBaseLegal, "
            + "hecons.deHechoConstatadoT, hecons.deComplementoObservacion, hecons.deSustentoT, hecons.tipoCumplimiento.idValorParametro, "
            + "crsupe.idMatrizGrpoCrtrio, crsupe.pgimMatrizCriterio.idMatrizCriterio, crsupe.nuOrdenGrpoCrtrio, crsupe.nuOrdenCriterio) "
            + "FROM PgimCriterioSprvsion crsupe "
            + "LEFT JOIN PgimHechoConstatado hecons ON crsupe.idCriterioSprvsion = hecons.pgimCriterioSprvsion.idCriterioSprvsion "
            + "AND hecons.esRegistro = '1' "
            + "AND hecons.esVigente = 'V' "
            + "AND hecons.pgimRolProceso.idRolProceso = 4L "
            + "WHERE crsupe.esRegistro = '1' "
            + "AND crsupe.pgimSupervision.idSupervision = :idSupervision "
            + "ORDER BY crsupe.nuOrdenGrpoCrtrio, crsupe.nuOrdenCriterio ASC")
    List<PgimCriterioSprvsionDTO> obtenerMatrizSupervision(@Param("idSupervision") Long idSupervision);
    
    /**
     * Permite obtener la pre-configuración del criterio de la matriz de supervisión para una especialidad y matriz de criterio dados
     * 
     * @param idEspecialidad
     * @param idMatrizCriterio
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTOResultado("
                    + " cr.idMatrizCriterio,cr.idMatrizCriterio "  
                    + " ,cr.coMatrizCriterio,cr.deMatrizCriterio,cr.esVigente,cr.deBaseLegal "  
                    + " ,gr.idMatrizGrpoCrtrio,gr.pgimMatrizSupervision.idMatrizSupervision,gr.coMatrizGrpoCrtrio,gr.noMatrizGrpoCrtrio "
                    + " ,gr.nuOrden,gr.esVisible,cr.nuOrden ) " 
                    + "FROM PgimMatrizCriterio cr, PgimMatrizGrpoCrtrio gr, PgimMatrizSupervision ms "                    
                    + "WHERE cr.pgimMatrizGrpoCrtrio.idMatrizGrpoCrtrio=gr.idMatrizGrpoCrtrio and gr.pgimMatrizSupervision.idMatrizSupervision=ms.idMatrizSupervision "
                    + "and cr.esRegistro = '1' and gr.esRegistro = '1' and ms.esRegistro = '1' " 
                    + "and ms.pgimEspecialidad.idEspecialidad = :idEspecialidad "
                    + "and cr.idMatrizCriterio = :idMatrizCriterio "
                    )
    PgimCriterioSprvsionDTO obtenerConfiguracionCriterioSupervision(@Param("idEspecialidad") Long idEspecialidad, @Param("idMatrizCriterio") Long idMatrizCriterio);
    
    /**
     * Permite obtener la pre-configuración de los criterios de la matriz de supervisión para una configuracion base
     * 
     * @param idConfiguracionBase
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTOResultado("
                    + " cr.idMatrizCriterio,cr.idMatrizCriterio "  
                    + " ,cr.coMatrizCriterio,cr.deMatrizCriterio,cr.esVigente,cr.deBaseLegal "  
                    + " ,gr.idMatrizGrpoCrtrio,gr.pgimMatrizSupervision.idMatrizSupervision,gr.coMatrizGrpoCrtrio,gr.noMatrizGrpoCrtrio "
                    + " ,gr.nuOrden,gr.esVisible,cr.nuOrden ) " 
                    + "FROM PgimMatrizCriterio cr, PgimMatrizGrpoCrtrio gr, PgimMatrizSupervision ms "                    
                    + "WHERE cr.pgimMatrizGrpoCrtrio.idMatrizGrpoCrtrio=gr.idMatrizGrpoCrtrio and gr.pgimMatrizSupervision.idMatrizSupervision=ms.idMatrizSupervision "
                    + "and cr.esRegistro = '1' and gr.esRegistro = '1' and ms.esRegistro = '1' " 
                    + "and ms.pgimConfiguracionBase.idConfiguracionBase = :idConfiguracionBase "
                    )
    List<PgimCriterioSprvsionDTO> obtenerConfiguracionBaseCriteriosSupervision(@Param("idConfiguracionBase") Long idConfiguracionBase);

    /**
     * Permite obtener la pre-configuración de los criterios de la matriz de supervisión de un cuadro de verificación dado.
     * 
     * @param idMatrizSupervision
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTOResultado("
                    + "macr.idMatrizCriterio, macr.idMatrizCriterio, macr.coMatrizCriterio, "
                    + "macr.deMatrizCriterio, macr.esVigente, macr.deBaseLegal, "
                    + "grcr.idMatrizGrpoCrtrio, masu.idMatrizSupervision, grcr.coMatrizGrpoCrtrio, "
                    + "grcr.noMatrizGrpoCrtrio, grcr.nuOrden, grcr.esVisible, "
                    + "macr.nuOrden" 
                    + ")" 
                    + "FROM PgimMatrizCriterio macr "
                    + "INNER JOIN macr.pgimMatrizGrpoCrtrio grcr "
                    + "INNER JOIN grcr.pgimMatrizSupervision masu "                    
                    + "WHERE 1 = 1 "
                    + "AND masu.idMatrizSupervision = :idMatrizSupervision "
                    + "AND macr.esRegistro = '1' "
                    + "AND grcr.esRegistro = '1' "
                    + "AND masu.esRegistro = '1' "
                    )
    List<PgimCriterioSprvsionDTO> obtenerCritiosPorMatrizSupervision(@Param("idMatrizSupervision") Long idMatrizSupervision);

}
