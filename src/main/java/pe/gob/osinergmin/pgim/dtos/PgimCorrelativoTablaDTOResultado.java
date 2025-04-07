package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimCorrelativoTablaDTOResultado extends PgimCorrelativoTablaDTO {

    public PgimCorrelativoTablaDTOResultado(Long idCorrelativoTabla, String noTabla, Long nuAnioCorrelativo, Long seUltimoCorrelativo,
			String esRegistro, String usCreacion, String ipCreacion,
			Date feCreacion, String usActualizacion, String ipActualizacion, Date feActualizacion) {
		super();
		this.setIdCorrelativoTabla(idCorrelativoTabla);
        this.setNoTabla(noTabla);
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
