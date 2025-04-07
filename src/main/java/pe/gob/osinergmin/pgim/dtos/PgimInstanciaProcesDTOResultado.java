package pe.gob.osinergmin.pgim.dtos;

public class PgimInstanciaProcesDTOResultado extends PgimInstanciaProcesDTO {

	public PgimInstanciaProcesDTOResultado(Long idInstanciaProceso, Long idInstanciaProcesoPadre, Long idProceso,
			String noTablaInstancia, Long coTablaInstancia, String nuExpedienteSiged) {
		super();
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdInstanciaProcesoPadre(idInstanciaProcesoPadre);
		this.setIdProceso(idProceso);
		this.setNoTablaInstancia(noTablaInstancia);
		this.setCoTablaInstancia(coTablaInstancia);
		this.setNuExpedienteSiged(nuExpedienteSiged);
	}

	public PgimInstanciaProcesDTOResultado(Long idInstanciaProceso, Long idInstanciaProcesoPadre, Long idProceso,
			String noTablaInstancia, Long coTablaInstancia, String nuExpedienteSiged, String esRegistro) {
		super();
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdInstanciaProcesoPadre(idInstanciaProcesoPadre);
		this.setIdProceso(idProceso);
		this.setNoTablaInstancia(noTablaInstancia);
		this.setCoTablaInstancia(coTablaInstancia);
		this.setNuExpedienteSiged(nuExpedienteSiged);
		this.setEsRegistro(esRegistro);

	}

	public PgimInstanciaProcesDTOResultado(Long idInstanciaProceso, String noTablaInstancia, Long coTablaInstancia,
			String nuExpedienteSiged) {
		super();
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNoTablaInstancia(noTablaInstancia);
		this.setCoTablaInstancia(coTablaInstancia);
		this.setNuExpedienteSiged(nuExpedienteSiged);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository#obtenerInstanciaProceso()
	 * @see pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository#listarInstanciaProcesPorProceso()
	 * @param idInstanciaProceso
	 * @param idProceso
	 * @param noTablaInstancia
	 * @param coTablaInstancia
	 * @param nuExpedienteSiged
	 */
	public PgimInstanciaProcesDTOResultado(Long idInstanciaProceso, Long idProceso, String noTablaInstancia,
			Long coTablaInstancia, String nuExpedienteSiged) {
		super();

		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdProceso(idProceso);
		this.setNoTablaInstancia(noTablaInstancia);
		this.setCoTablaInstancia(coTablaInstancia);
		this.setNuExpedienteSiged(nuExpedienteSiged);
	}

	/**
	 * Sirve para listar por palabra clave "Numero de expediente Siged"
	 * 
	 * @param idInstanciaProceso
	 * @param nuExpedienteSiged
	 */
	public PgimInstanciaProcesDTOResultado(
			Long idInstanciaProceso, String nuExpedienteSiged) {
		super();
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setNuExpedienteSiged(nuExpedienteSiged);
	}
}
