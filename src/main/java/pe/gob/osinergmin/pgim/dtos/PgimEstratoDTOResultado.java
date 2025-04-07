package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimEstratoDTOResultado extends PgimEstratoDTO {

	public PgimEstratoDTOResultado(Long idEstrato, String noEstrato, BigDecimal nuCapacidadProductiva,
			BigDecimal nuCapacidadBeneficio, String esRegistro) {
		super();
		this.setIdEstrato(idEstrato);
		this.setNoEstrato(noEstrato);
		this.setNuCapacidadProductiva(nuCapacidadProductiva);
		this.setNuCapacidadBeneficio(nuCapacidadBeneficio);
		this.setEsRegistro(esRegistro);
	}

	public PgimEstratoDTOResultado(Long idEstrato, String noEstrato) {
        super();
        this.setIdEstrato(idEstrato);
        this.setNoEstrato(noEstrato);
    }

}