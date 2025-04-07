package pe.gob.osinergmin.pgim.dtos;

public class PgimInfraccionxumAuxDTOResultado extends PgimInfraccionxumAuxDTO{
	

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InfraccionAuxRepository#listarReporteInfraccionesUmAnioPaginado()
	 * @param coUnidadMinera
	 * @param noUnidadMinera
	 * @param noTipoUnidadMinera
	 * @param nroInfraccionP1
	 * @param nroInfraccionP2
	 * @param nroInfraccionP3
	 * @param nroInfraccionP4
	 * @param nroInfraccionP5
	 * @param nroInfraccionP6
	 * @param nroInfraccionTotal
	 */
	public PgimInfraccionxumAuxDTOResultado(
	      String coUnidadMinera
	      , String noUnidadMinera
	      , String noTipoUnidadMinera
	      , Long nroInfraccionP1
	      , Long nroInfraccionP2
	      , Long nroInfraccionP3
	      , Long nroInfraccionP4
	      , Long nroInfraccionP5
	      , Long nroInfraccionP6
	      , Long nroInfraccionTotal
	  ) {
		
		this.setCoUnidadMinera(coUnidadMinera);
		this.setNoTipoUnidadMinera(noTipoUnidadMinera);
		this.setNoUnidadMinera(noUnidadMinera);
	    this.setNroInfraccionP1(nroInfraccionP1);
	    this.setNroInfraccionP2(nroInfraccionP2);
	    this.setNroInfraccionP3(nroInfraccionP3);
	    this.setNroInfraccionP4(nroInfraccionP4);
	    this.setNroInfraccionP5(nroInfraccionP5);
	    this.setNroInfraccionP6(nroInfraccionP6);
	    this.setNroInfraccionTotal(nroInfraccionTotal);
	    
	  }

	
}
