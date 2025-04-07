package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaItemDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmtvaItem;

/** 
 * @descripción: Lógica de negocio de la entidad de ítem de normas que pertenece una obligación normativa
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 24/04/2023
*/
@Repository

public interface OblgcnNrmtvaItemRepository  extends JpaRepository<PgimOblgcnNrmtvaItem, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaItemDTOResultado( "
            + "oblg.idOblgcnNrmtvaItem, oblg.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc, "
            + "oblg.pgimNormaItem.idNormaItem, oblg.oblgcnNrmtvaItemPadre.idOblgcnNrmtvaItem) " 
            + "FROM PgimOblgcnNrmtvaItem oblg "
            + "WHERE oblg.esRegistro = '1' and "
            + "oblg.pgimOblgcnNrmtvaHchoc.esRegistro = '1' and "
            + "oblg.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc = :idOblgcnNrmtvaHchoc "
            )
    public List<PgimOblgcnNrmtvaItemDTO> listarItemsNormasXobligacionNorma(@Param("idOblgcnNrmtvaHchoc") Long idOblgcnNrmtvaHchoc);
    
    @Query("SELECT oblg.idOblgcnNrmtvaItem " 
            + "FROM PgimOblgcnNrmtvaItem oblg "                    
            + "WHERE oblg.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc = :idOblgcnNrmtvaHchoc and "
            + "oblg.pgimOblgcnNrmtvaHchoc.esRegistro = '1' "
            )
    public List<Long> listarIdItemsNormasXobligacionNorma(@Param("idOblgcnNrmtvaHchoc") Long idOblgcnNrmtvaHchoc);
    
}
