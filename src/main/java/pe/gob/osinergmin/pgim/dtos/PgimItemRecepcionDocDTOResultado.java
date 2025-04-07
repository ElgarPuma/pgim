package pe.gob.osinergmin.pgim.dtos;

public class PgimItemRecepcionDocDTOResultado extends PgimItemRecepcionDocDTO{

	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemRecepcionDocRepository#obtenerItemRecepcionDocPorId()
	 * @param idItemRecepcionDoc
	 * @param idItemSolicitudDoc
	 * @param idDocumento
	 */
	public PgimItemRecepcionDocDTOResultado(Long idItemRecepcionDoc, Long idItemSolicitudDoc,
	        Long idDocumento) {
	    super();
	
	    this.setIdItemRecepcionDoc(idItemRecepcionDoc);
	    this.setIdItemSolicitudDoc(idItemSolicitudDoc);
	    this.setIdDocumento(idDocumento);
	}
}