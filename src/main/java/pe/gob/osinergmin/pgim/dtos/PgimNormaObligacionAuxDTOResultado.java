package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimNormaObligacionAuxDTOResultado extends PgimNormaObligacionAuxDTO {

	/**
	@see pe.gob.osinergmin.pgim.models.repository.NormaObligacionAuxRepository#listar()	
	*/
	
	public PgimNormaObligacionAuxDTOResultado(Long idNormaItemObligacion, String noCortoNorma, String coNormaItem, Long idDivisionNormaItem, String deContenidoT, String flVigenteItem, 
			String ubicacionItem, String deNormaObligacionT, String esVigenteNormaObligacion, String coTipificacion, String esVigenteTipificacion, String coNormaTipificacion, String noItemTipificacion, Long idItemTipificacion, String noTipoNorma) {
        super();
        this.setIdNormaObligacionAux(idNormaItemObligacion);
        this.setNoCortoNorma(noCortoNorma);
		this.setCoItem(coNormaItem);
		this.setIdDivisionItem(idDivisionNormaItem);
		this.setDeContenidoT(deContenidoT);
		this.setFlVigenteItem(flVigenteItem);
		this.setUbicacionItem(ubicacionItem);
		this.setDeNormaObligacionT(deNormaObligacionT);
		this.setEsVigenteNormaObligacion(esVigenteNormaObligacion);
		this.setCoTipificacion(coTipificacion);
		this.setEsVigenteTipificacion(esVigenteTipificacion);
		this.setCoNormaTipificacion(coNormaTipificacion);
		this.setNoItemTipificacion(noItemTipificacion);
		this.setIdItemTipificacion(idItemTipificacion);
		this.setNoTipoNorma(noTipoNorma);
        
    }
	
	
	/**
	@see pe.gob.osinergmin.pgim.models.repository.NormaObligacionAuxRepository#obtenerNormaObligacionAuxPorId()	
	*/
	
	public PgimNormaObligacionAuxDTOResultado(Long idNormaItemObligacion, String noCortoNorma, String coNormaItem, Long idDivisionNormaItem, String deContenidoT, String flVigenteItem, 
			String ubicacionItem, String deNormaObligacionT, String esVigenteNormaObligacion, String coTipificacion, String esVigenteTipificacion, String coNormaTipificacion, String noItemTipificacion, Long idItemTipificacion, String noTipoNorma
			,String descNoDivisionItem) {
        super();
        this.setIdNormaObligacionAux(idNormaItemObligacion);
        this.setNoCortoNorma(noCortoNorma);
		this.setCoItem(coNormaItem);
		this.setIdDivisionItem(idDivisionNormaItem);
		this.setDeContenidoT(deContenidoT);
		this.setFlVigenteItem(flVigenteItem);
		this.setUbicacionItem(ubicacionItem);
		this.setDeNormaObligacionT(deNormaObligacionT);
		this.setEsVigenteNormaObligacion(esVigenteNormaObligacion);
		this.setCoTipificacion(coTipificacion);
		this.setEsVigenteTipificacion(esVigenteTipificacion);
		this.setCoNormaTipificacion(coNormaTipificacion);
		this.setNoItemTipificacion(noItemTipificacion);
		this.setIdItemTipificacion(idItemTipificacion);
		this.setNoTipoNorma(noTipoNorma);
		
		this.setDescNoDivisionItem(descNoDivisionItem);
        
    }
	
	
	
}
