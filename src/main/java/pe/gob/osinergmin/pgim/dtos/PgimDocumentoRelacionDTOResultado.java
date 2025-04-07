package pe.gob.osinergmin.pgim.dtos;

public class PgimDocumentoRelacionDTOResultado extends PgimDocumentoRelacionDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRelacionRepository#obtenerDocumentoRelacionPorId
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRelacionRepository#obtenerDocumentoRelacionPorIdDocumento
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRelacionRepository#obtenerListDocRelacionPorIdDocumentoPadre
	 * @param idDocumentoRelacion
	 * @param idDocumentoPadre
	 * @param idDocumento
	 * @param idTipoRelacionDocumento
	 */
	public PgimDocumentoRelacionDTOResultado(Long idDocumentoRelacion, Long idDocumentoPadre,
			Long idDocumento, Long idTipoRelacionDocumento) {
		super();
		this.setIdDocumentoRelacion(idDocumentoRelacion);
		this.setIdDocumentoPadre(idDocumentoPadre);
		this.setIdDocumento(idDocumento);
		this.setIdTipoRelacionDocumento(idTipoRelacionDocumento);
	}
}
