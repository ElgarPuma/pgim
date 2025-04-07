package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PgimNormaObligacionDTOResultado extends PgimNormaObligacionDTO{
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.
	 * @param idNormaItemObligacion
	 * @param idNorma
	 * @param idItemTipificacion
	 * @param deNormaObligacion
	 * @param flVigente
	 */
	public PgimNormaObligacionDTOResultado(Long idNormaItemObligacion, Long idNorma, Long idItemTipificacion, String deNormaObligacion, String flVigente) {
        super();
        this.setIdNormaObligacion(idNormaItemObligacion);
        this.setIdNormaItem(idNorma);
        this.setIdItemTipificacion(idItemTipificacion);
        this.setDeNormaObligacionT(deNormaObligacion);
        this.setEsVigente(flVigente);
        
    }
	


}
