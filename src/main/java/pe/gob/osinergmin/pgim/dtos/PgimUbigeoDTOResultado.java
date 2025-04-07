package pe.gob.osinergmin.pgim.dtos;

public class PgimUbigeoDTOResultado extends PgimUbigeoDTO {

    public PgimUbigeoDTOResultado(Long idUbigeo, String noUbigeo) {
		super();
		this.setIdUbigeo(idUbigeo);		
		this.setNoUbigeo(noUbigeo);
	}

    public PgimUbigeoDTOResultado(String descDistrito, String descProvincia, String descDepartamento) {
		super();
		this.setDescDistrito(descDistrito);
		this.setDescProvincia(descProvincia);
		this.setDescDepartamento(descDepartamento);
	}
}