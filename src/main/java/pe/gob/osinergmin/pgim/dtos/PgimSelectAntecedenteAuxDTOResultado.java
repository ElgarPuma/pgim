package pe.gob.osinergmin.pgim.dtos;

public class PgimSelectAntecedenteAuxDTOResultado extends PgimSelectAntecedenteAuxDTO{
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AntecedenteSupervRepository#listarAntecedenteSeleccion()
	 * @param idDocumento
	 * @param fecha
	 * @param codigo
	 * @param descripcion
	 * @param idInstanciaProceso
	 * @param coTablaInstancia
	 * @param nuExpediente
	 * @param noTipoAntecedente
	 * @param idTipoAntecedente
	 */
	
	public PgimSelectAntecedenteAuxDTOResultado( Long idDocumento, String fecha, String codigo, String descripcion, Long idInstanciaProceso, 
			Long coTablaInstancia, String nuExpediente, String noTipoAntecedente, Long idTipoAntecedente, Long idEspecialidad ) {
		super();
		
		this.setIdDocumento(idDocumento);
		this.setFecha(fecha);
		this.setCodigo(codigo);
		this.setDescripcion(descripcion);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setCoTablaInstancia(coTablaInstancia);
		this.setNuExpedienteSiged(nuExpediente);
		this.setNoTipoAntecedente(noTipoAntecedente);
		this.setIdTipoAntecedente(idTipoAntecedente);
		this.setIdEspecialidad(idEspecialidad);
		
	}

}
