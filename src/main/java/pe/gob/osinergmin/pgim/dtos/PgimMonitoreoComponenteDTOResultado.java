package pe.gob.osinergmin.pgim.dtos;


public class PgimMonitoreoComponenteDTOResultado extends PgimMonitoreoComponenteDTO {

	public PgimMonitoreoComponenteDTOResultado(String feMonitoreo, Double distancia, Long nivelAceptacion, Double porcentajeAgua) {
		super();
		this.setFeMonitoreo(feMonitoreo);
		this.setDistancia(distancia);
		this.setNivelAceptacion(nivelAceptacion);
		this.setPorcentajeAgua(porcentajeAgua);
	}

	

	
	 
}
