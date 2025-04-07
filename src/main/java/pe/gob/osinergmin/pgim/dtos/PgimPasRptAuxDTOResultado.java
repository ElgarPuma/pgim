package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimPasRptAuxDTOResultado extends PgimPasRptAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasRptAuxRepository#listarPgimPasRptAuxPorUnidadMinera()
	 * @param idPasAux
	 * @param idPas
	 * @param idUnidadMinera
	 * @param idSupervision
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
	 * @param feCreacionPas
	 */
	  public PgimPasRptAuxDTOResultado(Long idPasAux, Long idPas, Long idUnidadMinera, Long idSupervision, 
			  String coSupervision, Long idTipoSupervision, String noTipoSupervision, Long idSubtipoSupervision, 
			  String deSubtipoSupervision, Date feInicioSupervision, Date feFinSupervision, Date feInicioSupervisionReal, 
			  Date feFinSupervisionReal, String etiquetaPasoActual, String nuExpedienteSiged, Date feCreacionPas) {
	        super();
	        this.setIdPasAux(idPasAux);
	        this.setIdPas(idPas);
	        this.setIdUnidadMinera(idUnidadMinera);
	        this.setIdSupervision(idSupervision);
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
	        this.setFeCreacionPas(feCreacionPas);
	    }
}