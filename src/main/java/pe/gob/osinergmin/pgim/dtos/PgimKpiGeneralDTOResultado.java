package pe.gob.osinergmin.pgim.dtos;


public class PgimKpiGeneralDTOResultado extends PgimKpiGeneralDTO{

	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerKpiAprobacionInforme()
	 * 
	 */
	public PgimKpiGeneralDTOResultado(Long nuTotalRegistros, Long nuMaximo,
			Long nuMinimo,Double nuPromedio) {
	    super();
	
	    this.setNuTotalRegistros(nuTotalRegistros);
	    this.setNuMaximo(nuMaximo);
	    this.setNuMinimo(nuMinimo);
	    this.setNuPromedio(nuPromedio);
	}
}