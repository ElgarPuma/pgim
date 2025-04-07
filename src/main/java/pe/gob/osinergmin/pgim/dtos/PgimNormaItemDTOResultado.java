package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimNormaItemDTOResultado extends PgimNormaItemDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaItemRepository#obtenerItemPorId
	 * @param idNormaItem
	 * @param idNorma
	 * @param idDivisionItem
	 * @param idNormaItemPadre
	 * @param coItem
	 * @param deContenidoT
	 * @param flVigente
	 */
	public PgimNormaItemDTOResultado(Long idNormaItem, Long idNorma, Long idDivisionItem, Long idNormaItemPadre, String coItem, String deContenidoT, String flVigente) {
        super();
        this.setIdNormaItem(idNormaItem);
        this.setIdNorma(idNorma);
        this.setIdDivisionItem(idDivisionItem);
        this.setIdNormaItemPadre(idNormaItemPadre);
        this.setCoItem(coItem);
        this.setDeContenidoT(deContenidoT);
        this.setFlVigente(flVigente);
        
    }
	
	
	public PgimNormaItemDTOResultado(Long idNormaItem, Long idNorma, Long idNormaItemPadre) {
		super();
		this.setIdNormaItem(idNormaItem);
        this.setIdNorma(idNorma);
        this.setIdNormaItemPadre(idNormaItemPadre);
	}
	
	public PgimNormaItemDTOResultado(Long idNormaItem) {
		super();
		this.setIdNormaItem(idNormaItem);
	}

}
