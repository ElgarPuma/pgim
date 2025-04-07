package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimNormaItemAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaItemDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaItem;

/**
 * @descripción: Lógica de negocio de la entidad ítems de las normas
 * 
 * @author: PresleyRomero
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface NormaItemRepository extends JpaRepository<PgimNormaItem, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaItemAuxDTOResultado(nitem.pgimNormaItem.idNormaItem, nitem.idNorma, nitem.normaItemPadre.idNormaItem, nitem.coItem, nitem.deContenidoT, nitem.flVigente, nitem.idDivisionItem, nitem.deDivisionItem, nitem.nuNivel) "    
            + "FROM PgimNormaItemAux nitem "     
            + "WHERE nitem.esRegistro = '1' "
            + "AND nitem.idNorma = :idNorma "
            + "AND (:idDivisionItem IS NULL OR nitem.idDivisionItem = :idDivisionItem) "
            + "AND (:deContenidoT IS NULL OR LOWER(nitem.deContenidoT) LIKE LOWER(CONCAT('%', :deContenidoT, '%')) ) "
            + "AND (:flVigente IS NULL OR nitem.flVigente = :flVigente) "            
            )
	Page<PgimNormaItemAuxDTO> listarNormaItem(
			@Param("idNorma") Long idNorma, 
			@Param("idDivisionItem") Long idDivisionItem,
    		@Param("deContenidoT") String deContenidoT,
    		@Param("flVigente") String flVigente,
			Pageable paginador);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaItemDTOResultado(nitem.idNormaItem, nitem.pgimNorma.idNorma, nitem.divisionItem.idValorParametro, nitem.normaItemPadre.idNormaItem, nitem.coItem, nitem.deContenidoT, nitem.flVigente) "
            + "FROM PgimNormaItem nitem "            
            + "WHERE nitem.idNormaItem = :idNormaItem "
            + "AND nitem.esRegistro = '1' "            
            )
	PgimNormaItemDTO obtenerItemPorId(@Param("idNormaItem") Long idNormaItem);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaItemDTOResultado(nitem.idNormaItem, nitem.pgimNorma.idNorma, nitem.normaItemPadre.idNormaItem) "
            + "FROM PgimNormaItem nitem "            
            + "WHERE nitem.normaItemPadre.idNormaItem = :idNormaItemPadre "
            + "AND nitem.esRegistro = '1' "            
            )
	List<PgimNormaItemDTO> obtenerItemsHijos(@Param("idNormaItemPadre") Long idNormaItemPadre);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaItemAuxDTOResultado(nitem.pgimNormaItem.idNormaItem, nitem.idNorma, nitem.normaItemPadre.idNormaItem, nitem.coItem, nitem.deContenidoT, nitem.flVigente, nitem.idDivisionItem, nitem.deDivisionItem, nitem.nuNivel) "    
            + "FROM PgimNormaItemAux nitem "     
            + "WHERE nitem.esRegistro = '1' "
            + "AND nitem.idNorma = :idNorma "
            + "AND (nitem.flVigente = '1') " 
            )
	List<PgimNormaItemAuxDTO> listarNormaItemParaObligacion(
			@Param("idNorma") Long idNorma);
	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaItemAuxDTOResultado(nitem.pgimNormaItem.idNormaItem, nitem.idNorma, nitem.normaItemPadre.idNormaItem, nitem.coItem, nitem.deContenidoT, nitem.flVigente, nitem.idDivisionItem, nitem.deDivisionItem, nitem.nuNivel, nitem.ubicacionItem, nitem.jerarquiaItem) "    
            + "FROM PgimNormaItemAux nitem "     
            + "WHERE nitem.esRegistro = '1' "
            + "AND (nitem.flVigente = '1') "
            + "AND (:ubicacionItem IS NULL OR LOWER(nitem.ubicacionItem) LIKE LOWER(CONCAT('%', :ubicacionItem, '%' )))"
            )
	List<PgimNormaItemAuxDTO> obtenerItemNormasHijosPorUbicacion(			
			@Param("ubicacionItem") String ubicacionItem);
	
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaItemDTOResultado( "    		
            + "oblg.idOblgcnNrmtvaItem, ponh.idOblgcnNrmtvaHchoc, "
            + "oblg.oblgcnNrmtvaItemPadre.idOblgcnNrmtvaItem, "
            + "nitem.pgimNormaItem.idNormaItem, nitem.ubicacionItem, nitem.coItem, nitem.deDivisionItem "
            + ") "
            + "FROM PgimNormaItemAux nitem " 
			+ "LEFT JOIN PgimOblgcnNrmtvaItem oblg ON (nitem.pgimNormaItem.idNormaItem = oblg.pgimNormaItem.idNormaItem AND oblg.esRegistro = '1') "
            + "LEFT JOIN PgimOblgcnNrmtvaHchoc ponh ON (oblg.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc = ponh.idOblgcnNrmtvaHchoc AND ponh.esRegistro = '1' AND ponh.idOblgcnNrmtvaHchoc = :idOblgcnNrmtvaHchoc ) "
            + "WHERE nitem.esRegistro = '1' "
            + "AND (nitem.flVigente = '1') "
            + "AND (:ubicacionItem IS NULL OR LOWER(nitem.ubicacionItem) LIKE LOWER(CONCAT('%', :ubicacionItem, '%' )))"
            )
            
	List<PgimOblgcnNrmtvaItemDTO> obtenerItemNormasHijosPorUbicacionPorObligacionNormativa(			
			@Param("ubicacionItem") String ubicacionItem,
			@Param("idOblgcnNrmtvaHchoc") Long idOblgcnNrmtvaHchoc);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaItemAuxDTOResultado(nitem.pgimNormaItem.idNormaItem, nitem.idNorma, nitem.normaItemPadre.idNormaItem, nitem.coItem, nitem.deContenidoT, nitem.flVigente, nitem.idDivisionItem, nitem.deDivisionItem, nitem.nuNivel, nitem.ubicacionItem, nitem.jerarquiaItem) "    
            + "FROM PgimNormaItemAux nitem "     
            + "WHERE nitem.esRegistro = '1' "
            + "AND (nitem.flVigente = '1') "
            + "AND (:idNorma IS NULL OR nitem.idNorma = :idNorma) "
            + "AND (:noNorma IS NULL OR LOWER(nitem.ubicacionItem) LIKE LOWER(CONCAT('%', :noNorma, '%' )))"
            + "AND (:articulo IS NULL OR LOWER(nitem.ubicacionItem) LIKE LOWER(CONCAT('%', :articulo, '%' )))"
            )
	Page<PgimNormaItemAuxDTO> obtenerIdArticuloParaObligacionFilter(
			@Param("idNorma") Long idNorma,
			@Param("noNorma") String noNorma,
			@Param("articulo") String articulo, 
			Pageable paginador);
	

}
