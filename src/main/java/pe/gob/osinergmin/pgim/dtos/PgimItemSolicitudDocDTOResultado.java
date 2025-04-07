package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimItemSolicitudDocDTOResultado extends PgimItemSolicitudDocDTO{

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemSolicitudDocRepository#obtenerItemSolicitudDocPorId()
	 * @param idItemSolicitudDoc
	 * @param idSolicitudDoc
	 * @param feSolicitudDocumentacion
	 * @param deSolicitudObservacion
	 * @param feRecepcionDocumentacion
	 * @param deRecepcionObservacion
	 * @param esRecibido
	 */
	public PgimItemSolicitudDocDTOResultado(Long idItemSolicitudDoc, Long idSolicitudDoc,
	        Date feSolicitudDocumentacion, String deSolicitudObservacion, Date feRecepcionDocumentacion,
	        String deRecepcionObservacion, String esRecibido, Long nuOrden) {
	    super();
	
	    this.setIdItemSolicitudDoc(idItemSolicitudDoc);
	    this.setIdSolicitudDoc(idSolicitudDoc);
	    this.setFeSolicitudDocumentacion(feSolicitudDocumentacion);
	    this.setDeSolicitudObservacion(deSolicitudObservacion);
	    this.setFeRecepcionDocumentacion(feRecepcionDocumentacion);
	    this.setDeRecepcionObservacion(deRecepcionObservacion);
	    this.setEsRecibido(esRecibido);
		this.setNuOrden(nuOrden);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemSolicitudDocRepository#modificarOrden()
	 * @param idItemSolicitudDoc
	 * @param nuOrden
	 */
	public PgimItemSolicitudDocDTOResultado(Long idItemSolicitudDoc, Long nuOrden) {
	    super();
	
	    this.setIdItemSolicitudDoc(idItemSolicitudDoc);
		this.setNuOrden(nuOrden);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ItemSolicitudDocRepository#listarItemSolicitudDocPorIdSolicitudDoc()
	 * @param idItemSolicitudDoc
	 * @param idSolicitudDoc
	 * @param feSolicitudDocumentacion
	 * @param deSolicitudObservacion
	 * @param feRecepcionDocumentacion
	 * @param deRecepcionObservacion
	 * @param esRecibido
	 * @param descNoEspecialidad
	 */
	public PgimItemSolicitudDocDTOResultado(Long idItemSolicitudDoc, Long idSolicitudDoc,
	        Date feSolicitudDocumentacion, String deSolicitudObservacion, Date feRecepcionDocumentacion,
	        String deRecepcionObservacion, String esRecibido, String descNoEspecialidad, Long nuOrden, Long descIdSupervision) {
	    super();
	
	    this.setIdItemSolicitudDoc(idItemSolicitudDoc);
	    this.setIdSolicitudDoc(idSolicitudDoc);
	    this.setFeSolicitudDocumentacion(feSolicitudDocumentacion);
	    this.setDeSolicitudObservacion(deSolicitudObservacion);
	    this.setFeRecepcionDocumentacion(feRecepcionDocumentacion);
	    this.setDeRecepcionObservacion(deRecepcionObservacion);
	    this.setEsRecibido(esRecibido);
	    this.setDescNoEspecialidad(descNoEspecialidad);
		this.setNuOrden(nuOrden);
		this.setDescIdSupervision(descIdSupervision);
	}
}