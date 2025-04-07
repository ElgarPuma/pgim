package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimCorrelativoSupervDTOResultado extends PgimCorrelativoSupervDTO {
	
	public PgimCorrelativoSupervDTOResultado(Long idCorrelativoSuperv, Long nuAnioCorrelativo, Long seUltimoCorrelativo
			, String esRegistro, String usCreacion, String ipCreacion,
			Date feCreacion, String usActualizacion, String ipActualizacion, Date feActualizacion) {
		super();
		this.setIdCorrelativoSuperv(idCorrelativoSuperv);
		this.setNuAnioCorrelativo(nuAnioCorrelativo);		
		this.setSeUltimoCorrelativo(seUltimoCorrelativo);		
		this.setEsRegistro(esRegistro);
		this.setUsCreacion(usCreacion);
		this.setIpCreacion(ipCreacion);
		this.setFeCreacion(feCreacion);
		this.setUsActualizacion(usActualizacion);
		this.setIpActualizacion(ipActualizacion);
		this.setFeActualizacion(feActualizacion);		
	}

}
