package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimDetaInfraccionesAuxDTOResultado extends PgimDetaInfraccionesAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfracciones()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesASPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesAS()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesUMPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesUM()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesDSPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesDS()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesEspecPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository#listarReporteInfraccionesEspec()
	 * @param anioSupervision
	 * @param feInicioSupervision
	 * @param feFinSupervision
	 * @param feInicioSupervisionReal
	 * @param feFinSupervisionReal
	 * @param expSigedPas
	 * @param etiquetaAgenteSupervisado
	 * @param etiquetaUnidadMinera
	 * @param noEspecialidad
	 * @param fechaEmisionResolucion
	 * @param etiquetaInfraccion
	 * @param noDivisionSupervisora
	 * @param flPropia
	 * @param expSigedSupervision
	 */
	public PgimDetaInfraccionesAuxDTOResultado(Long anioSupervision, Date feInicioSupervision, Date feFinSupervision,  
			Date feInicioSupervisionReal, Date feFinSupervisionReal,  
			String expSigedPas, String etiquetaAgenteSupervisado, String etiquetaUnidadMinera, String noEspecialidad, 
			Date fechaEmisionResolucion, String etiquetaInfraccion, String noDivisionSupervisora, String flPropia, String expSigedSupervision) {
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
		this.setFechaEmisionResolucion(fechaEmisionResolucion);
		this.setEtiquetaInfraccion(etiquetaInfraccion);	
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setFlPropia(flPropia);
		this.setExpSigedSupervision(expSigedSupervision);
	}
	
}
