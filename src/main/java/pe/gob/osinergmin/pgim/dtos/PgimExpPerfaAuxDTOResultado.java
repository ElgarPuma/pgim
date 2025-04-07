package pe.gob.osinergmin.pgim.dtos;

public class PgimExpPerfaAuxDTOResultado extends PgimExpPerfaAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ExpPerfaAuxRepository#listarReporteExpPasPerfaseAnioPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.ExpPerfaAuxRepository#listarReporteExpPasPerfaseAnio()
	 * 
	 * @param deEntidadPersona
	 * @param etiquetaPersona
	 * @param noUsuario
	 * @param f1PreSupervision
	 * @param f2SupervisionDeCampo
	 * @param f3PostSupervisionDeCampo
	 * @param f4RevInformeSupervision
	 * @param f5AprobacionResultados
	 */
	public PgimExpPerfaAuxDTOResultado(Long idPersona, String deEntidadPersona, String etiquetaPersona, String noUsuario, Long f1PreSupervision, Long f2SupervisionDeCampo, 
			Long f3PostSupervisionDeCampo, Long f4RevInformeSupervision, Long f5AprobacionResultados) {
		super();
		this.setIdPersona(idPersona);
		this.setDeEntidadPersona(deEntidadPersona);
		this.setEtiquetaPersona(etiquetaPersona);
		this.setNoUsuario(noUsuario);
		this.setF1PreSupervision(f1PreSupervision);
		this.setF2SupervisionDeCampo(f2SupervisionDeCampo);
		this.setF3PostSupervisionDeCampo(f3PostSupervisionDeCampo);
		this.setF4RevInformeSupervision(f4RevInformeSupervision);
		this.setF5AprobacionResultados(f5AprobacionResultados);
//		this.setAnioSupervision(anioSupervision);
	}

}
