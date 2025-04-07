package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimSolicitudDocDTOResultado extends PgimSolicitudDocDTO{

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SolicitudDocRepository#obtenerSolicitudDocPorId()
	 * @see pe.gob.osinergmin.pgim.models.repository.SolicitudDocRepository#obtenerSolicitudDocxIdSupervision()
	 * @param idSolicitudDoc
	 * @param idSupervision
	 * @param feSolicitudDocumentacion
	 * @param deSolicitudObservacion
	 * @param feRecepcionDocumentacion
	 * @param deRecepcionObservacion
	 */
	public PgimSolicitudDocDTOResultado(Long idSolicitudDoc, Long idSupervision, Date feSolicitudDocumentacion,
			String deSolicitudObservacion,Date feRecepcionDocumentacion, String deRecepcionObservacion) {
        super();

        this.setIdSolicitudDoc(idSolicitudDoc);
        this.setIdSupervision(idSupervision); 
        this.setFeSolicitudDocumentacion(feSolicitudDocumentacion);
        this.setDeSolicitudObservacion(deSolicitudObservacion);
        this.setFeRecepcionDocumentacion(feRecepcionDocumentacion); 
        this.setDeRecepcionObservacion(deRecepcionObservacion);
	}

	
	public PgimSolicitudDocDTOResultado(Long idSolicitudDoc, Long idSupervision, Date feSolicitudDocumentacion,
			String deSolicitudObservacion,Date feRecepcionDocumentacion, String deRecepcionObservacion,
			Date  feInicioSupervisionReal, Date  feFinSupervisionReal) {
        super();

        this.setIdSolicitudDoc(idSolicitudDoc);
        this.setIdSupervision(idSupervision); 
        this.setFeSolicitudDocumentacion(feSolicitudDocumentacion);
        this.setDeSolicitudObservacion(deSolicitudObservacion);
        this.setFeRecepcionDocumentacion(feRecepcionDocumentacion); 
        this.setDeRecepcionObservacion(deRecepcionObservacion);
        this.setDescFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setDescFeFinSupervisionReal(feFinSupervisionReal);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SolicitudDocRepository#listarSolicitudDocRequerido()
	 * 
	 * @param idSolicitudDoc
	 * @param idSupervision
	 * @param descTipoSupervision
	 * @param feSolicitudDocumentacion
	 * @param deSolicitudObservacion
	 * @param feRecepcionDocumentacion
	 * @param deRecepcionObservacion
	 */
	public PgimSolicitudDocDTOResultado(Long idSolicitudDoc, Long idSupervision, String descTipoSupervision, Date feSolicitudDocumentacion,
			String deSolicitudObservacion,Date feRecepcionDocumentacion, String deRecepcionObservacion, String descNoEspecialidad) {
        super();

        this.setIdSolicitudDoc(idSolicitudDoc);
        this.setIdSupervision(idSupervision); 
        this.setDescTipoSupervision(descTipoSupervision); 
        this.setFeSolicitudDocumentacion(feSolicitudDocumentacion);
        this.setDeSolicitudObservacion(deSolicitudObservacion);
        this.setFeRecepcionDocumentacion(feRecepcionDocumentacion); 
        this.setDeRecepcionObservacion(deRecepcionObservacion);
        this.setDescNoEspecialidad(descNoEspecialidad);
    }

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SolicitudDocRepository#obtenerSolicitudAndItemSolicitudPorId()
	 * @param idSolicitudDoc
	 * @param idSupervision
	 * @param feSolicitudDocumentacion
	 * @param deSolicitudObservacion
	 * @param feRecepcionDocumentacion
	 * @param deRecepcionObservacion
	 * @param descIdItemSolicitudDoc
	 * @param descFeSolicitudDocumentacion
	 * @param descDeSolicitudObservacion
	 * @param descFeRecepcionDocumentacion
	 * @param descDeRecepcionObservacion
	 * @param descEsRecibido
	 */
	public PgimSolicitudDocDTOResultado(Long idSolicitudDoc, Long idSupervision, Date feSolicitudDocumentacion,
			String deSolicitudObservacion,Date feRecepcionDocumentacion, String deRecepcionObservacion,
			Long descIdItemSolicitudDoc, Date descFeSolicitudDocumentacion, String descDeSolicitudObservacion,
			Date descFeRecepcionDocumentacion, String descDeRecepcionObservacion, String descEsRecibido) {
        super();

        this.setIdSolicitudDoc(idSolicitudDoc);
        this.setIdSupervision(idSupervision); 
        this.setFeSolicitudDocumentacion(feSolicitudDocumentacion);
        this.setDeSolicitudObservacion(deSolicitudObservacion);
        this.setFeRecepcionDocumentacion(feRecepcionDocumentacion); 
        this.setDeRecepcionObservacion(deRecepcionObservacion);
        this.setDescIdItemSolicitudDoc(descIdItemSolicitudDoc);
        this.setDescFeSolicitudDocumentacion(descFeSolicitudDocumentacion); 
        this.setDescDeSolicitudObservacion(descDeSolicitudObservacion); 
        this.setDescFeRecepcionDocumentacion(descFeRecepcionDocumentacion);
        this.setDescDeRecepcionObservacion(descDeRecepcionObservacion); 
        this.setDescEsRecibido(descEsRecibido);
    }

}