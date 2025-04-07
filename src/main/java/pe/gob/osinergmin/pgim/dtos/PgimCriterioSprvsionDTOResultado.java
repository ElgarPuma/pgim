package pe.gob.osinergmin.pgim.dtos;

public class PgimCriterioSprvsionDTOResultado extends PgimCriterioSprvsionDTO{
		
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CriterioSprvsionRepository#generarConfiguracionCriteriosSupervision()
	 * @param idCriterioSprvsion
	 * @param idMatrizCriterio
	 * @param coMatrizCriterio
	 * @param deMatrizCriterio
	 * @param esVigenteMatrizCriterio
	 * @param deBaseLegal
	 * @param idMatrizGrpoCrtrio
	 * @param idMatrizSupervision
	 * @param coMatrizGrpoCrtrio
	 * @param noMatrizGrpoCrtrio
	 * @param nuOrdenGrpoCrtrio
	 * @param esVisibleGrpoCrtrio
	 * @param nuOrdenCriterio
	 */
	public PgimCriterioSprvsionDTOResultado(Long idCriterioSprvsion,Long idMatrizCriterio
			,String coMatrizCriterio,String deMatrizCriterio,String esVigenteMatrizCriterio,String deBaseLegal
			,Long idMatrizGrpoCrtrio,Long idMatrizSupervision,String coMatrizGrpoCrtrio,String noMatrizGrpoCrtrio
			,Long nuOrdenGrpoCrtrio,String esVisibleGrpoCrtrio,Long nuOrdenCriterio) {
		super();
		this.setIdCriterioSprvsion(idCriterioSprvsion);
		this.setIdMatrizCriterio(idMatrizCriterio);		
		this.setCoMatrizCriterio(coMatrizCriterio);
		this.setDeMatrizCriterio(deMatrizCriterio);
		this.setEsVigenteMatrizCriterio(esVigenteMatrizCriterio);
		this.setDeBaseLegal(deBaseLegal);
		this.setIdMatrizGrpoCrtrio(idMatrizGrpoCrtrio);
		this.setIdMatrizSupervision(idMatrizSupervision);
		this.setCoMatrizGrpoCrtrio(coMatrizGrpoCrtrio);
		this.setNoMatrizGrpoCrtrio(noMatrizGrpoCrtrio);
		this.setNuOrdenGrpoCrtrio(nuOrdenGrpoCrtrio);
		this.setEsVisibleGrpoCrtrio(esVisibleGrpoCrtrio);
		this.setNuOrdenCriterio(nuOrdenCriterio);
	}
	
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CriterioSprvsionRepository#obtenerCriterioSprvsionById()
	 * @param idCriterioSprvsion
	 * 
	 */	
	public PgimCriterioSprvsionDTOResultado(Long idCriterioSprvsion,Long idSupervision,Long idMatrizCriterio
			,String coMatrizCriterio,String deMatrizCriterio,String esVigenteMatrizCriterio,String deBaseLegal
			,Long idMatrizGrpoCrtrio,Long idMatrizSupervision,String coMatrizGrpoCrtrio,String noMatrizGrpoCrtrio,String esVisibleGrpoCrtrio) {
		super();
		this.setIdCriterioSprvsion(idCriterioSprvsion);		
		this.setIdSupervision(idSupervision);
		this.setIdMatrizCriterio(idMatrizCriterio);		
		this.setCoMatrizCriterio(coMatrizCriterio);
		this.setDeMatrizCriterio(deMatrizCriterio);
		this.setEsVigenteMatrizCriterio(esVigenteMatrizCriterio);
		this.setDeBaseLegal(deBaseLegal);
		this.setIdMatrizGrpoCrtrio(idMatrizGrpoCrtrio);
		this.setIdMatrizSupervision(idMatrizSupervision);
		this.setCoMatrizGrpoCrtrio(coMatrizGrpoCrtrio);
		this.setNoMatrizGrpoCrtrio(noMatrizGrpoCrtrio);
		this.setEsVisibleGrpoCrtrio(esVisibleGrpoCrtrio);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CriterioSprvsionRepository#obtenerMatrizSupervision()
	 * @param coMatrizGrpoCrtrio
	 * @param noMatrizGrpoCrtrio
	 * @param coMatrizCriterio
	 * @param deMatrizCriterio
	 * @param deBaseLegal
	 * @param descDeHechoConstatado
	 * @param descDeComplementoObservacion
	 * @param descDeSustento
	 * @param descIdtipoCumplimiento
	 * @param idMatrizGrpoCrtrio
	 * @param Long idMatrizCriterio
	 * @param Long nuOrdenGrpoCrtrio
	 * @param Long nuOrdenCriterio
	 */
	public PgimCriterioSprvsionDTOResultado(Long idCriterioSprvsion, String coMatrizGrpoCrtrio, String noMatrizGrpoCrtrio, String coMatrizCriterio,
			String deMatrizCriterio, String deBaseLegal, String descDeHechoConstatado, String descDeComplementoObservacion,
			String descDeSustento, Long descIdtipoCumplimiento, Long idMatrizGrpoCrtrio, Long idMatrizCriterio, Long nuOrdenGrpoCrtrio,
			Long nuOrdenCriterio) {
		super();
		this.setIdCriterioSprvsion(idCriterioSprvsion);
		this.setCoMatrizGrpoCrtrio(coMatrizGrpoCrtrio);
		this.setNoMatrizGrpoCrtrio(noMatrizGrpoCrtrio); 
		this.setCoMatrizCriterio(coMatrizCriterio); 
		this.setDeMatrizCriterio(deMatrizCriterio); 
		this.setDeBaseLegal(deBaseLegal);
		this.setDescDeHechoConstatado(descDeHechoConstatado);
		this.setDescDeComplementoObservacion(descDeComplementoObservacion); 
		this.setDescDeSustento(descDeSustento); 
		this.setDescIdtipoCumplimiento(descIdtipoCumplimiento);
		this.setIdMatrizGrpoCrtrio(idMatrizGrpoCrtrio);
		this.setIdMatrizCriterio(idMatrizCriterio);
		this.setNuOrdenGrpoCrtrio(nuOrdenGrpoCrtrio);
		this.setNuOrdenCriterio(nuOrdenCriterio);
	}

}
