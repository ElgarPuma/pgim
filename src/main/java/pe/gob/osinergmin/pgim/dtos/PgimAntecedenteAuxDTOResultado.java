package pe.gob.osinergmin.pgim.dtos;

public class PgimAntecedenteAuxDTOResultado extends PgimAntecedenteAuxDTO{
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AntecedenteSupervRepository#listarAntecedente()
	 * 
	 * @param idAntecedenteSupervAux
	 * @param idAntecedenteSuperv
	 * @param idSupervision
	 * @param idDocumento
	 * @param deAspectosRevisados
	 * @param idTipoAntecedente
	 * @param noTipoAntecedente
	 * @param noTipoOrigin
	 * @param idTipoOrigin
	 * @param idCategoriaDocumento
	 * @param noCategoriaDocumento
	 * @param noSubcatDocumento
	 * @param idSubcatDocumento
	 * @param coDocumentoSiged
	 * @param deAsuntoDocumento
	 * @param idInstanciaProceso
	 * @param nuExpedienteSiged
	 * @param cotablainstancia
	 * @param fecha
	 * @param codigo
	 * @param descripcion
	 * @param coDocumentoSigedPCopia
	 * 
	 * la propiedad de "textoBusqueda" se esta usando para obtenr el número de expediente desde el siged o desde lo registrado en el antecedente adjuntado "informe de fiscalización"
	 */
	public PgimAntecedenteAuxDTOResultado( 
			Long idAntecedenteSupervAux,
			Long idAntecedenteSuperv,
			Long idSupervision,
			Long idDocumento,
			String deAspectosRevisados,
			Long idTipoAntecedente, 
			String noTipoAntecedente,
			String noTipoOrigin,
			Long idTipoOrigin,
			Long idCategoriaDocumento,
			String noCategoriaDocumento,
			String noSubcatDocumento,
			Long idSubcatDocumento,
			Long coDocumentoSiged,
			String deAsuntoDocumento,
			Long idInstanciaProceso,
			String nuExpedienteSiged,
			Long cotablainstancia,
			String fecha,
			String codigo,
			String descripcion,
			Long coDocumentoSigedPCopia,
			String tipoSupervision) {
		super();
		
		this.setIdAntecedenteSupervAux(idAntecedenteSupervAux);
		this.setIdAntecedenteSuperv(idAntecedenteSuperv);
		this.setIdSupervision(idSupervision);
		this.setIdDocumento(idDocumento);
		this.setDeAspectosRevisados(deAspectosRevisados);
		this.setNoTipoAntecedente(noTipoAntecedente);
		this.setIdTipoAntecedente(idTipoAntecedente);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setNoTipoOrigin(noTipoOrigin);
		this.setIdTipoOrigin(idTipoOrigin);
		this.setIdCategoriaDocumento(idCategoriaDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento);
		this.setIdSubcatDocumento(idSubcatDocumento);
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setCoDocumentoSiged(coDocumentoSiged);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNuExpedienteSiged(nuExpedienteSiged);
		this.setCotablainstancia(cotablainstancia);
		this.setFecha(fecha);
		this.setCodigo(codigo);
		this.setDescripcion(descripcion);
		this.setCoDocumentoSigedPCopia(coDocumentoSigedPCopia);
		this.setTipoSupervision(tipoSupervision);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AntecedenteSupervRepository#obtenerAntecedenteInformeSuperv()
	 * 
	 * @param nuExpedienteSiged
	 * @param fecha
	 * @param tipoSupervision
	 * @param deAspectosRevisados
	 */
	public PgimAntecedenteAuxDTOResultado( 
			String nuExpedienteSiged, 
			String fecha, 
			String tipoSupervision, 
			String deAspectosRevisados) {
		super();
		
		this.setNuExpedienteSiged(nuExpedienteSiged);
		this.setFecha(fecha);
		this.setTipoSupervision(tipoSupervision); 
		this.setDeAspectosRevisados(deAspectosRevisados);
		
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AntecedenteSupervRepository#obtenerAntecedenteOtrosDocs() 
	 * 
	 * @param tipoAntecedente
	 * @param deAspectosRevisados
	 * @param coDocumentoSiged
	 */
	public PgimAntecedenteAuxDTOResultado( 
			String tipoAntecedente, 
			String deAspectosRevisados, 
			Long coDocumentoSiged) {
		super();
		
		this.setNoTipoAntecedente(tipoAntecedente); 
		this.setDeAspectosRevisados(deAspectosRevisados);
		this.setCoDocumentoSiged(coDocumentoSiged);
		
	}
	

}
