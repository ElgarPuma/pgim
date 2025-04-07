package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimIncPeligrosoAuxDTOResultado extends PgimIncPeligrosoAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.IncPeligrosoAuxRepository#listarPgimIncPeligrosoAuxPorUnidadMinera()
	 * @param idEventoAux
	 * @param idEvento
	 * @param idTipoEvento
	 * @param noTipoEvento
	 * @param coEvento
	 * @param deEvento
	 * @param fePresentacion
	 * @param noEspecialidad
	 */
	public PgimIncPeligrosoAuxDTOResultado(Long idEventoAux, Long idEvento, Long idTipoEvento,
            String noTipoEvento, String coEvento, String deEvento, Date fePresentacion, String noEspecialidad) {
        super();
        this.setIdEventoAux(idEventoAux); 
        this.setIdEvento(idEvento);
        this.setIdTipoEvento(idTipoEvento);
        this.setNoTipoEvento(noTipoEvento);
        this.setCoEvento(coEvento);
        this.setDeEvento(deEvento);
        this.setFePresentacion(fePresentacion);
        this.setNoEspecialidad(noEspecialidad);
    }
}