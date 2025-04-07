package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimExppasPendientesAuxDTOResultado extends PgimExppasPendientesAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ExppasPendientesAuxRepository#listarReporteExpPersonaAsignadaPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.ExppasPendientesAuxRepository#listarReporteExpPersonaAsignada()
	 * @param anioSupervision
	 * @param feInicioSupervision
	 * @param feFinSupervision
	 * @param feInicioSupervisionReal
	 * @param feFinSupervisionReal
	 * @param expSigedPas
	 * @param etiquetaAgenteSupervisado
	 * @param etiquetaUnidadMinera
	 * @param noEspecialidad
	 * @param noDivisionSupervisora
	 * @param flPropia
	 * @param etiquetaPasoActual
	 * @param fechaPasoActual
	 * @param diasTranscurridos
	 */
	public PgimExppasPendientesAuxDTOResultado(Long anioSupervision, Date feInicioSupervision, Date feFinSupervision, Date feInicioSupervisionReal, Date feFinSupervisionReal, 
			String expSigedPas,	String etiquetaAgenteSupervisado, String etiquetaUnidadMinera, String noEspecialidad, String noDivisionSupervisora, String flPropia, 
			String etiquetaPasoActual, Date fechaPasoActual, Long diasTranscurridos) {
		super();
		this.setAnioSupervision(anioSupervision);
		this.setFeInicioSupervision(feInicioSupervision);
		this.setFeFinSupervision(feFinSupervision);
		this.setFeInicioSupervisionReal(feInicioSupervisionReal);
		this.setFeFinSupervisionReal(feFinSupervisionReal);
		this.setExpSigedPas(expSigedPas);
		this.setEtiquetaAgenteSupervisado(etiquetaAgenteSupervisado);
		this.setEtiquetaUnidadMinera(etiquetaUnidadMinera);
		this.setNoEspecialidad(noEspecialidad);
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setFlPropia(flPropia);
		this.setEtiquetaPasoActual(etiquetaPasoActual);
		this.setFechaPasoActual(fechaPasoActual);
		this.setDiasTranscurridos(diasTranscurridos);
		
	}
	
	/**
	 * 
	 * @param etiquetaUnidadMinera
	 */
	public PgimExppasPendientesAuxDTOResultado(String etiquetaUnidadMinera) {
		super();
		this.setEtiquetaUnidadMinera(etiquetaUnidadMinera);
	}

}
