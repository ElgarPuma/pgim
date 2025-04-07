package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimSuperRptAuxDTOResultado extends PgimSuperRptAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SuperRptAuxRepository#listarPgimSuperRptAuxPorUnidadMinera()
	 * @param idSupervisionAux
	 * @param idSupervision
	 * @param idUnidadMinera
	 * @param coSupervision
	 * @param idTipoSupervision
	 * @param noTipoSupervision
	 * @param idSubtipoSupervision
	 * @param deSubtipoSupervision
	 * @param feInicioSupervision
	 * @param feFinSupervision
	 * @param feInicioSupervisionReal
	 * @param feFinSupervisionReal
	 * @param etiquetaPasoActual
	 * @param nuExpedienteSiged
	 */
	public PgimSuperRptAuxDTOResultado(Long idSupervisionAux, Long idSupervision, Long idUnidadMinera, 
			String coSupervision, Long idTipoSupervision, String noTipoSupervision, Long idSubtipoSupervision, 
			String deSubtipoSupervision, Date feInicioSupervision, Date feFinSupervision, Date feInicioSupervisionReal, 
			Date feFinSupervisionReal, String etiquetaPasoActual, String nuExpedienteSiged) {
        super();
        this.setIdSupervisionAux(idSupervisionAux); 
        this.setIdSupervision(idSupervision); 
        this.setIdUnidadMinera(idUnidadMinera); 
        this.setCoSupervision(coSupervision); 
        this.setIdTipoSupervision(idTipoSupervision); 
        this.setNoTipoSupervision(noTipoSupervision); 
        this.setIdSubtipoSupervision(idSubtipoSupervision); 
        this.setDeSubtipoSupervision(deSubtipoSupervision); 
        this.setFeInicioSupervision(feInicioSupervision); 
        this.setFeFinSupervision(feFinSupervision); 
        this.setFeInicioSupervisionReal(feInicioSupervisionReal); 
        this.setFeFinSupervisionReal(feFinSupervisionReal); 
        this.setEtiquetaPasoActual(etiquetaPasoActual); 
        this.setNuExpedienteSiged(nuExpedienteSiged);
    }

}