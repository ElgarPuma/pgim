package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimNormaItemAuxDTOResultado extends PgimNormaItemAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaItemRepository#listarNormaItem
	 * @param idNormaItem
	 * @param idNorma
	 * @param idNormaItemPadre
	 * @param coItem
	 * @param deContenidoT
	 * @param flVigente
	 * @param idDivisionItem
	 * @param deDivisionItem
	 * @param nuNivel
	 */
	public PgimNormaItemAuxDTOResultado(Long idNormaItem, Long idNorma, Long idNormaItemPadre, String coItem,
			String deContenidoT, String flVigente, Long idDivisionItem, String deDivisionItem, Long nuNivel) {
		super();
		this.setIdNormaItem(idNormaItem);
		this.setIdNorma(idNorma);
		this.setIdNormaItemPadre(idNormaItemPadre);
		this.setCoItem(coItem);
		this.setDeContenidoT(deContenidoT);
		this.setFlVigente(flVigente);
		this.setIdDivisionItem(idDivisionItem);
		this.setDeDivisionItem(deDivisionItem);
		this.setNuNivel(nuNivel);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaItemRepository#obtenerIdArticuloParaObligacionFilter
	 * @param idNormaItem
	 * @param idNorma
	 * @param idNormaItemPadre
	 * @param coItem
	 * @param deContenidoT
	 * @param flVigente
	 * @param idDivisionItem
	 * @param deDivisionItem
	 * @param nuNivel
	 * @param ubicacionItem
	 */
	public PgimNormaItemAuxDTOResultado(Long idNormaItem, Long idNorma, Long idNormaItemPadre, String coItem,
			String deContenidoT, String flVigente, Long idDivisionItem, String deDivisionItem, Long nuNivel, String ubicacionItem, String jerarquiaItem) {
		super();
		this.setIdNormaItem(idNormaItem);
		this.setIdNorma(idNorma);
		this.setIdNormaItemPadre(idNormaItemPadre);
		this.setCoItem(coItem);
		this.setDeContenidoT(deContenidoT);
		this.setFlVigente(flVigente);
		this.setIdDivisionItem(idDivisionItem);
		this.setDeDivisionItem(deDivisionItem);
		this.setNuNivel(nuNivel);
		this.setUbicacionItem(ubicacionItem);
		this.setJerarquiaItem(jerarquiaItem);
	}
	
}
