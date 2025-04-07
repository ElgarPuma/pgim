package pe.gob.osinergmin.pgim.dtos;

public class PgimSolicitudBaseDocDTOResultado extends PgimSolicitudBaseDocDTO{
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SolicitudBaseDocRepository#listarSolicitudBaseDocPage()
	 * @see pe.gob.osinergmin.pgim.models.repository.SolicitudBaseDocRepository#obtenerSolicitudbaseDocByIdConfiguracionBase()
	 * @see pe.gob.osinergmin.pgim.models.repository.SolicitudBaseDocRepository#obtenerSolicitudBaseDocPorId()
	 * 
	 * @param idSolicitudBaseDoc
	 * @param idConfiguracionBase
	 * @param descNoCofiguracionBase
	 * @param noSolicitudBaseDoc
	 * @param deSolicitudBaseDoc
	 * @param desNoEspecialidad
	 * @param desIdEspecialidad
	 */
	public PgimSolicitudBaseDocDTOResultado(Long idSolicitudBaseDoc, Long idConfiguracionBase, String descNoCofiguracionBase, 
			String noSolicitudBaseDoc, String deSolicitudBaseDoc,  String desNoEspecialidad,  Long desIdEspecialidad) {
		
		super();
		
		this.setIdSolicitudBaseDoc(idSolicitudBaseDoc);
		this.setNoSolicitudBaseDoc(noSolicitudBaseDoc);
		this.setDeSolicitudBaseDoc(deSolicitudBaseDoc);
		this.setIdConfiguracionBase(idConfiguracionBase);
		this.setDescNoConfiguracionBase(descNoCofiguracionBase);
		this.setDescNoEspecialidad(desNoEspecialidad); 
		this.setDescIdEspecialidad(desIdEspecialidad);
		
	}

}
