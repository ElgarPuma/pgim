package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimAccMortalAuxDTOResultado extends PgimAccMortalAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AccMortalAuxRepository#listarAccMortalAuxPorUnidadMinera()
	 * @param idEventoAux
	 * @param idEvento
	 * @param idTipoEvento
	 * @param noTipoEvento
	 * @param coEvento
	 * @param deEvento
	 * @param fePresentacion
	 * @param noEspecialidad
	 * @param caAccidentados
	 */
	public PgimAccMortalAuxDTOResultado(Long idEventoAux, Long idEvento, Long idTipoEvento,
            String noTipoEvento, String coEvento, String deEvento, Date fePresentacion, String noEspecialidad, Long caAccidentados) {
        super();
        this.setIdEventoAux(idEventoAux); 
        this.setIdEvento(idEvento);
        this.setNoTipoEvento(noTipoEvento); 
        this.setCoEvento(coEvento); 
        this.setDeEvento(deEvento); 
        this.setFePresentacion(fePresentacion); 
        this.setNoEspecialidad(noEspecialidad); 
        this.setCaAccidentados(caAccidentados);
    }


}