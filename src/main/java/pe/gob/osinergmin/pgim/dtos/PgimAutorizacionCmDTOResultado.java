package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimAutorizacionCmDTOResultado extends PgimAutorizacionCmDTO{
	
	
	/**
     * Este constructor sirve para obtener un listado de entidades AutorizacionCm a través de un IdAutorizacion.
     * 
     * En el repositorio se usa en el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.AutorizacionCmRepository#getListAutorizacionCmByIdAutorizacion()
     * 
     * 
     */		
	public PgimAutorizacionCmDTOResultado(Long idAutorizacionCminero, Long idAutorizacion, Long idComponenteMinero,			
			String esRegistro,String usCreacion, String ipCreacion, Date feCreacion
			, String usActualizacion, String ipActualizacion,Date feActualizacion) {

		super();

		this.setIdAutorizacionCminero(idAutorizacionCminero);
		this.setIdAutorizacion(idAutorizacion);
		this.setIdComponenteMinero(idComponenteMinero);				
		this.setEsRegistro(esRegistro);
		this.setUsCreacion(usCreacion);
		this.setIpCreacion(ipCreacion);
		this.setFeCreacion(feCreacion);
		this.setUsActualizacion(usActualizacion);
		this.setIpActualizacion(ipActualizacion);
		this.setFeActualizacion(feActualizacion);		
		
	}

}
