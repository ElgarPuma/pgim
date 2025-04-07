package pe.gob.osinergmin.pgim.dtos;

public class PgimInfracciontop15AuxDTOResultado extends PgimInfracciontop15AuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.Infracciontop15AuxRepository#listarReporteInfraccionesTop15()
	 * @param nroItemAux
	 * @param noCortoNorma
	 * @param coTipificacion
	 * @param noItemTipificacion
	 * @param nroInfracciones
	 */
	public PgimInfracciontop15AuxDTOResultado(Long nroItemAux, String noCortoNorma, String coTipificacion, String noItemTipificacion, Long nroInfracciones) {
		super();
		this.setNroItemAux(nroItemAux); 
		this.setNoCortoNorma(noCortoNorma); 
		this.setCoTipificacion(coTipificacion); 
		this.setNoItemTipificacion(noItemTipificacion); 
		this.setNroInfracciones(nroInfracciones); 
		
	}
}
