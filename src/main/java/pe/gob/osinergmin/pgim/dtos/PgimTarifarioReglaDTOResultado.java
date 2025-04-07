package pe.gob.osinergmin.pgim.dtos;

public class PgimTarifarioReglaDTOResultado extends PgimTarifarioReglaDTO{

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.TarifarioReglaRepository#obtenerTarifarioReglaPorId
	 * @see pe.gob.osinergmin.pgim.models.repository.TarifarioReglaRepository#obtenerTarifarioReglaPorIdTarifarioContrato
	 * @param idTarifarioRegla
	 * @param idTarifarioContrato
	 * @param idSubtipoSupervision
	 * @param idMotivoSupervision
	 * @param esRegistro
	 */
	public PgimTarifarioReglaDTOResultado(Long idTarifarioRegla, Long idTarifarioContrato, 
			Long idSubtipoSupervision, Long idMotivoSupervision, String esRegistro) {
		super();
		this.setIdTarifarioRegla(idTarifarioRegla);
		this.setIdTarifarioContrato(idTarifarioContrato);
		this.setIdSubtipoSupervision(idSubtipoSupervision);
		this.setIdMotivoSupervision(idMotivoSupervision);
		this.setEsRegistro(esRegistro);
	}
	
	/**
	 * * @see pe.gob.osinergmin.pgim.models.repository.TarifarioReglaRepository#listarTarifariosCumplenRegla
	 * @param idTarifarioRegla
	 * @param idTarifarioContrato
	 * @param idSubtipoSupervision
	 * @param idMotivoSupervision
	 */
	public PgimTarifarioReglaDTOResultado(Long idTarifarioRegla, Long idTarifarioContrato, 
			Long idSubtipoSupervision, Long idMotivoSupervision) {
		super();
		
		this.setIdTarifarioRegla(idTarifarioRegla);
		this.setIdTarifarioContrato(idTarifarioContrato);
		this.setIdSubtipoSupervision(idSubtipoSupervision);
		this.setIdMotivoSupervision(idMotivoSupervision);
	}
}
