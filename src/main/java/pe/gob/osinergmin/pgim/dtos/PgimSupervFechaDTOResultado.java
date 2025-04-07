package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimSupervFechaDTOResultado extends PgimSupervFechaDTO{

	public PgimSupervFechaDTOResultado(Long idSupervFecha, Long idSupervision,
			Long idTipoFecha, Date feInicioSupervision, Date feFinSupervision, String deMotivoCambio) {
        super();
        this.setIdSupervFecha(idSupervFecha);
        this.setIdSupervision(idSupervision);
        this.setIdTipoFecha(idTipoFecha);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setDeMotivoCambio(deMotivoCambio);
    }

}