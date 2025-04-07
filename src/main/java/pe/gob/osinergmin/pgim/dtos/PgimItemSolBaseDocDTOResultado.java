package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimItemSolBaseDocDTOResultado extends PgimItemSolBaseDocDTO {
	
	public PgimItemSolBaseDocDTOResultado(Long idItemSolBaseDoc, Long idSolicitudBaseDoc, String deSolicitudObservacion, BigDecimal nuOrden) {
		super();
		
		this.setIdItemSolBaseDoc(idItemSolBaseDoc);
		this.setIdSolicitudBaseDoc(idSolicitudBaseDoc);
		this.setDeSolicitudObservacion(deSolicitudObservacion);
		this.setNuOrden(nuOrden);

	}

}
