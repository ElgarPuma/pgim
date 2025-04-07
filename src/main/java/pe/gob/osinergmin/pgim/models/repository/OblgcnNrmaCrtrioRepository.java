package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmaCrtrio;

import java.util.List;

/**
 * @descripción: Lógica de negocio de la entidad Obligación normativa por criterio de matriz de supervisión
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface OblgcnNrmaCrtrioRepository extends JpaRepository<PgimOblgcnNrmaCrtrio, Long> {

    // AVANZAR ESTA CLASE
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTOResultado( "
            + "obln.idOblgcnNrmaCrtrio, obln.pgimMatrizCriterio.idMatrizCriterio, "
            // NORMA SUPERIOR
            + "obln.pgimNormaObligacion.pgimNormaItem.pgimNorma.noCortoNorma, "
            // ITEMS DE NORMAS => vigente ###
            + "obln.pgimNormaObligacion.pgimNormaItem.coItem, obln.pgimNormaObligacion.pgimNormaItem.deContenidoT, "
            + "obln.pgimNormaObligacion.pgimNormaItem.flVigente, "
            // PARAFRASEO DE OBLIGACION DE SUPERVISION
            + "obln.pgimNormaObligacion.deNormaObligacionT, obln.pgimNormaObligacion.esVigente, "
            // TIPIFICACION DE ITEMS
            + "obln.pgimNormaObligacion.pgimItemTipificacion.deSancionPecuniariaUit, " 
            + "obln.pgimNormaObligacion.pgimItemTipificacion.coTipificacion,  "
            + "obln.pgimNormaObligacion.pgimItemTipificacion.noItemTipificacion,  "
            + "obln.pgimNormaObligacion.pgimItemTipificacion.esVigente  "
            + ") " 
            + "FROM PgimOblgcnNrmaCrtrio obln "
            + "WHERE obln.pgimMatrizCriterio.idMatrizCriterio = :idMatrizCriterio "
            )
    Page<PgimOblgcnNrmaCrtrioDTO> listarObligacionNrmaCriterio(@Param("idMatrizCriterio") Long idMatrizCriterio, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTOResultado( "    		
    		+ "obln.idOblgcnNrmaCrtrio, obln.pgimMatrizCriterio.idMatrizCriterio, "
            // NORMA SUPERIOR
            + "oblx.noCortoNorma,oblx.noTipoNorma, "
            // ITEMS DE NORMAS 
            + "oblx.coItem, "
            + "case when oblx.deNormaObligacionT is not null then oblx.deNormaObligacionT else oblx.deContenidoT end, "
            + "oblx.flVigenteItem,oblx.ubicacionItem, "            
            // PARAFRASEO DE OBLIGACION DE SUPERVISION
            + "oblx.deNormaObligacionT, oblx.esVigenteNormaObligacion, "
            // TIPIFICACION DE ITEMS
            + "oblx.deSancionPecuniariaUit, oblx.coTipificacion, oblx.noItemTipificacion, oblx.esVigenteTipificacion, oblx.coNormaTipificacion, "
            + "obln.esRegistro, "
            + "(SELECT count(*) FROM PgimOblgcnNrmtvaHchoc hc where hc.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio = obln.idOblgcnNrmaCrtrio and hc.esRegistro = '1' ), oblx.idNormaItemPadre, oblx.idNormaItem "
            + ") " 
            + "FROM PgimNormaObligacionAux oblx, PgimOblgcnNrmaCrtrio obln "
            //+ " "
            + "WHERE oblx.pgimNormaObligacion.idNormaObligacion=obln.pgimNormaObligacion.idNormaObligacion "
            + "and obln.esRegistro = '1' "
            + "and obln.pgimMatrizCriterio.idMatrizCriterio = :idMatrizCriterio "
            + "ORDER BY oblx.ubicacionItem ASC "
            )
    List<PgimOblgcnNrmaCrtrioDTO> listarObligacionNrmaCriterioAux(@Param("idMatrizCriterio") Long idMatrizCriterio);

	// private Long idNormaItemPadre;

	// private Long idNormaItem; 
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTOResultado( "
            + "obln.idOblgcnNrmaCrtrio, obln.pgimMatrizCriterio.idMatrizCriterio, "
            // NORMA SUPERIOR
            + "obln.pgimNormaObligacion.pgimNormaItem.pgimNorma.noCortoNorma, "
            // ITEMS DE NORMAS => vigente ###
            + "obln.pgimNormaObligacion.pgimNormaItem.coItem, obln.pgimNormaObligacion.pgimNormaItem.deContenidoT, "
            + "obln.pgimNormaObligacion.pgimNormaItem.flVigente,"
            // PARAFRASEO DE OBLIGACION DE SUPERVISION
            + "obln.pgimNormaObligacion.deNormaObligacionT, obln.pgimNormaObligacion.esVigente, "
            // TIPIFICACION DE ITEMS
            + "obln.pgimNormaObligacion.pgimItemTipificacion.deSancionPecuniariaUit, " 
            + "obln.pgimNormaObligacion.pgimItemTipificacion.coTipificacion,  "
            + "obln.pgimNormaObligacion.pgimItemTipificacion.noItemTipificacion,  "
            + "obln.pgimNormaObligacion.pgimItemTipificacion.esVigente  "
            + ") " 
            + "FROM PgimOblgcnNrmaCrtrio obln "
            + "WHERE obln.esRegistro = '1' "
            + "AND (:deContenidoItem IS NULL OR LOWER(obln.pgimNormaObligacion.pgimNormaItem.deContenidoT) LIKE LOWER(CONCAT('%', :deContenidoItem, '%'))) "
            + "AND (:descIdTipoNorma IS NULL OR LOWER(obln.pgimNormaObligacion.pgimNormaItem.pgimNorma.tipoNorma.idValorParametro) LIKE LOWER(CONCAT('%', :descIdTipoNorma, '%')) ) "
            + "AND (:descIdDivisionItem IS NULL OR LOWER(obln.pgimNormaObligacion.pgimNormaItem.divisionItem.idValorParametro) LIKE LOWER(CONCAT('%', :descIdDivisionItem, '%')) ) "
        //     + "AND (:descNoCortoNorma IS NULL OR LOWER(obln.pgimNormaObligacion.pgimNormaItem.pgimNorma.noCortoNorma) LIKE LOWER(CONCAT('%', :descNoCortoNorma, '%')) ) "
        //     + "AND (:descNoNorma IS NULL OR LOWER(obln.pgimNormaObligacion.pgimNormaItem.pgimNorma.noNorma) LIKE LOWER(CONCAT('%', :descNoNorma, '%')) ) "
        //     + "AND (:descNoCortoNormaTipificacion IS NULL OR LOWER(obln.pgimNormaObligacion.pgimItemTipificacion.pgimNorma.noCortoNorma) LIKE LOWER(CONCAT('%', :descNoCortoNormaTipificacion, '%')) ) "
        //     + "AND (:descNoNormaTipificacion IS NULL OR LOWER(obln.pgimNormaObligacion.pgimItemTipificacion.pgimNorma.noNorma) LIKE LOWER(CONCAT('%', :descNoNormaTipificacion, '%')) ) "
            )
    Page<PgimOblgcnNrmaCrtrioDTO> listarObligacionNrmaCriterioSeleccion_old(
                    @Param("deContenidoItem") String deContenidoItem, @Param("descIdTipoNorma") Long descIdTipoNorma,
                    @Param("descIdDivisionItem") Long descIdDivisionItem, 
                //     @Param("descNoCortoNorma") String descNoCortoNorma, 
                //     @Param("descNoNorma") String descNoNorma, 
                //     @Param("descNoCortoNormaTipificacion") String descNoCortoNormaTipificacion, 
                //     @Param("descNoNormaTipificacion") String descNoNormaTipificacion, 
                    Pageable paginador);
    
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTOResultado( "            
            // NORMA SUPERIOR
            + "obln.pgimNormaObligacion.idNormaObligacion ,obln.noCortoNorma,obln.noTipoNorma, obln.noNorma, "
            // ITEMS DE NORMAS => vigente ###
            + "obln.coItem, obln.deContenidoT, "
            + "obln.flVigenteItem, obln.ubicacionItem, "
            // PARAFRASEO DE OBLIGACION DE SUPERVISION
            + "obln.deNormaObligacionT, obln.esVigenteNormaObligacion, "
            // TIPIFICACION DE ITEMS
            + "obln.deSancionPecuniariaUit, " 
            + "obln.coTipificacion,  "
            + "obln.noItemTipificacion,  "
            + "obln.esVigenteTipificacion, obln.coNormaTipificacion, obln.idNormaItemPadre, obln.idNormaItem "
            + ") " 
            + "FROM PgimNormaObligacionAux obln "
            + "WHERE obln.pgimNormaObligacion.idNormaObligacion "
            + "NOT IN (SELECT onc.pgimNormaObligacion.idNormaObligacion FROM PgimOblgcnNrmaCrtrio onc WHERE onc.pgimMatrizCriterio.idMatrizCriterio = :idMatrizCriterio AND onc.esRegistro = '1' ) "            
            + "AND obln.pgimNormaObligacion.pgimItemTipificacion.pgimNorma.flVigente = '1' "
            + "AND obln.esVigenteNormaObligacion='1' AND obln.esVigenteTipificacion='1' AND obln.flVigenteItem='1'  "             
            + "AND (:deContenidoItem IS NULL OR LOWER(obln.deContenidoT) LIKE LOWER(CONCAT('%', :deContenidoItem, '%'))) "
            + "AND (:descIdTipoNorma IS NULL OR LOWER(obln.tipoNorma.idValorParametro) LIKE LOWER(CONCAT('%', :descIdTipoNorma, '%')) ) "
            + "AND (:descIdDivisionItem IS NULL OR LOWER(obln.divisionItem.idValorParametro) LIKE LOWER(CONCAT('%', :descIdDivisionItem, '%')) ) "
            + "AND (:descNoCortoNorma IS NULL OR LOWER(obln.noCortoNorma) LIKE LOWER(CONCAT('%', :descNoCortoNorma, '%')) ) "
            + "AND (:descNoNorma IS NULL OR LOWER(obln.noNorma) LIKE LOWER(CONCAT('%', :descNoNorma, '%')) ) "
            + "AND (:descCoTipificacion IS NULL OR LOWER(obln.coTipificacion) LIKE LOWER(CONCAT('%', :descCoTipificacion, '%')) ) "
            + "AND (:descNoItemTipificacion IS NULL OR LOWER(obln.noItemTipificacion) LIKE LOWER(CONCAT('%', :descNoItemTipificacion, '%')) ) "
            )
    Page<PgimOblgcnNrmaCrtrioDTO> listarObligacionNrmaCriterioSeleccion(
                    @Param("idMatrizCriterio") Long idMatrizCriterio,
                    @Param("deContenidoItem") String deContenidoItem, @Param("descIdTipoNorma") Long descIdTipoNorma,
                    @Param("descIdDivisionItem") Long descIdDivisionItem,
                    @Param("descNoCortoNorma") String descNoCortoNorma, 
                    @Param("descNoNorma") String descNoNorma, 
                    @Param("descCoTipificacion") String descCoTipificacion, 
                    @Param("descNoItemTipificacion") String descNoItemTipificacion, 
                    Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmaCrtrioDTOResultado( "
            + "obln.idOblgcnNrmaCrtrio, obln.pgimMatrizCriterio.idMatrizCriterio, obln.pgimNormaObligacion.idNormaObligacion , "
            // NORMA SUPERIOR
            + "obln.pgimNormaObligacion.pgimNormaItem.pgimNorma.noCortoNorma, "
            // ITEMS DE NORMAS => vigente ###
            + "obln.pgimNormaObligacion.pgimNormaItem.coItem, obln.pgimNormaObligacion.pgimNormaItem.deContenidoT, "
            + "obln.pgimNormaObligacion.pgimNormaItem.flVigente, oblx.ubicacionItem, "
            // PARAFRASEO DE OBLIGACION DE SUPERVISION
            + "obln.pgimNormaObligacion.deNormaObligacionT, obln.pgimNormaObligacion.esVigente, "
            // TIPIFICACION DE ITEMS
            + "obln.pgimNormaObligacion.pgimItemTipificacion.deSancionPecuniariaUit, " 
            + "obln.pgimNormaObligacion.pgimItemTipificacion.coTipificacion,  "
            + "obln.pgimNormaObligacion.pgimItemTipificacion.noItemTipificacion,  "
            + "obln.pgimNormaObligacion.pgimItemTipificacion.esVigente,  "
            + "obln.pgimNormaObligacion.pgimItemTipificacion.pgimNorma.noCortoNorma  "
            + ") " 
            + "FROM PgimOblgcnNrmaCrtrio obln, PgimNormaObligacionAux oblx "
            + "WHERE oblx.pgimNormaObligacion.idNormaObligacion=obln.pgimNormaObligacion.idNormaObligacion "
            + "AND obln.idOblgcnNrmaCrtrio = :idOblgcnNrmaCrtrio "
            )
    PgimOblgcnNrmaCrtrioDTO obtenerObligacionNrmaCriterioPorId(@Param("idOblgcnNrmaCrtrio") Long idOblgcnNrmaCrtrio);
}
