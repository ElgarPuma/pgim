package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimDemarcacionUmineraDTOResultado extends PgimDemarcacionUmineraDTO {

	public PgimDemarcacionUmineraDTOResultado(Long idDemarcacionUm, Long idUbigeo, Long idUnidadMinera,
			String flPrincipal, BigDecimal pcUbigeo, String esRegistro, String usCreacion, String ipCreacion,
			Date feCreacion, String usActualizacion, String ipActualizacion, Date feActualizacion,
			String descDepartamento, String descProvincia, String descDistrito) {
		super();
		this.setIdDemarcacionUm(idDemarcacionUm);
		this.setIdUbigeo(idUbigeo);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setFlPrincipal(flPrincipal);
		this.setPcUbigeo(pcUbigeo);
		this.setEsRegistro(esRegistro);
		this.setUsCreacion(usCreacion);
		this.setIpCreacion(ipCreacion);
		this.setFeCreacion(feCreacion);
		this.setUsActualizacion(usActualizacion);
		this.setIpActualizacion(ipActualizacion);
		this.setFeActualizacion(feActualizacion);
		this.setDescDepartamento(descDepartamento);
		this.setDescProvincia(descProvincia);
		this.setDescDistrito(descDistrito);
	}

	public PgimDemarcacionUmineraDTOResultado(Long idDemarcacionUm, Long idUbigeo, Long idUnidadMinera,
			String flPrincipal, BigDecimal pcUbigeo, String descDistrito, String descProvincia,
			String descDepartamento) {
		super();
		this.setIdDemarcacionUm(idDemarcacionUm);
		this.setIdUbigeo(idUbigeo);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setFlPrincipal(flPrincipal);
		this.setPcUbigeo(pcUbigeo);
		this.setDescDistrito(descDistrito);
		this.setDescProvincia(descProvincia);
		this.setDescDepartamento(descDepartamento);
	}
	
	public PgimDemarcacionUmineraDTOResultado(Long idDemarcacionUm, Long idUbigeo, Long idUnidadMinera,
			String descDepartamento,String descProvincia,String descDistrito) {
		super();
		this.setIdDemarcacionUm(idDemarcacionUm);
		this.setIdUbigeo(idUbigeo);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setDescDistrito(descDistrito);
		this.setDescProvincia(descProvincia);
		this.setDescDepartamento(descDepartamento);
	}

	public PgimDemarcacionUmineraDTOResultado(BigDecimal pcUbigeo) {
		super();
		this.setPcUbigeo(pcUbigeo);
	}
}