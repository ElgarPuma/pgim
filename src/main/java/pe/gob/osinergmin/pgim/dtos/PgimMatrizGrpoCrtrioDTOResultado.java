package pe.gob.osinergmin.pgim.dtos;

public class PgimMatrizGrpoCrtrioDTOResultado extends PgimMatrizGrpoCrtrioDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.MatrizGrpoCrtrioRepository#listarMatrizGrpoCrtrio()
	 * 
	 */
	public PgimMatrizGrpoCrtrioDTOResultado(Long IdMatrizGrpoCrtrio, String coMatrizGrpoCrtrio
			,String noMatrizGrpoCrtrio, Long nuOrden, String esVisible, Long descCantidadCriterios ) {
		super();

		this.setIdMatrizGrpoCrtrio(IdMatrizGrpoCrtrio);
		this.setCoMatrizGrpoCrtrio(coMatrizGrpoCrtrio);
		this.setNoMatrizGrpoCrtrio(noMatrizGrpoCrtrio);		
		this.setNuOrden(nuOrden);
		this.setEsVisible(esVisible);
		this.setDescCantidadCriterios(descCantidadCriterios);
	}
	
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.MatrizGrpoCrtrioRepository#obtenerGrupoCriterioPorId()
	 * 
	 */
	public PgimMatrizGrpoCrtrioDTOResultado(Long IdMatrizGrpoCrtrio,Long idMatrizSupervision, String coMatrizGrpoCrtrio
			,String noMatrizGrpoCrtrio, Long nuOrden, String esVisible
			,String descEspecialidad
			) {
		super();

		this.setIdMatrizGrpoCrtrio(IdMatrizGrpoCrtrio);
		this.setIdMatrizSupervision(idMatrizSupervision);
		this.setCoMatrizGrpoCrtrio(coMatrizGrpoCrtrio);
		this.setNoMatrizGrpoCrtrio(noMatrizGrpoCrtrio);		
		this.setNuOrden(nuOrden);
		this.setEsVisible(esVisible);
		this.setDescEspecialidad(descEspecialidad);		
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.MatrizGrpoCrtrioRepository#filtrarPorGrupoCriterio
	 * 
	 */
	public PgimMatrizGrpoCrtrioDTOResultado(Long IdMatrizGrpoCrtrio, String coMatrizGrpoCrtrio
			,String noMatrizGrpoCrtrio) {
		super();
		this.setIdMatrizGrpoCrtrio(IdMatrizGrpoCrtrio);
		this.setCoMatrizGrpoCrtrio(coMatrizGrpoCrtrio);
		this.setNoMatrizGrpoCrtrio(noMatrizGrpoCrtrio);		
	}
	
}
