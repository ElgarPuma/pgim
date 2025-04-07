package pe.gob.osinergmin.pgim.dtos;

public class PgimOblgcnNrmtvaItemDTOResultado extends PgimOblgcnNrmtvaItemDTO{

	public PgimOblgcnNrmtvaItemDTOResultado(Long idOblgcnNrmtvaItem, Long idOblgcnNrmtvaHchoc, Long idNormaItem, Long idOblgcnNrmtvaHchocPadre) {
		super();
		this.setIdOblgcnNrmtvaItem(idOblgcnNrmtvaItem);
		this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
		this.setIdOblgcnNrmtvaItemPadre(idOblgcnNrmtvaHchocPadre);
		this.setIdNormaItem(idNormaItem);
		      
	}
	
	public PgimOblgcnNrmtvaItemDTOResultado(Long idOblgcnNrmtvaItem, Long idOblgcnNrmtvaHchoc, Long idOblgcnNrmtvaHchocPadre, Long idNormaItem, String descUbicacionNormaItem, String descCoItem, String descDeDivisionItem) {
		super();
		this.setIdOblgcnNrmtvaItem(idOblgcnNrmtvaItem);
		this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
		this.setIdOblgcnNrmtvaItemPadre(idOblgcnNrmtvaHchocPadre);
		this.setIdNormaItem(idNormaItem);
		this.setDescUbicacionNormaItem(descUbicacionNormaItem);
		this.setDescCoItem(descCoItem);
		this.setDescDeDivisionItem(descDeDivisionItem);
		      
	}
	
}
            