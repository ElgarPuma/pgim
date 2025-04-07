package pe.gob.osinergmin.pgim.dtos;

public class PgimSegAccidentadoDTOResultado extends PgimSegAccidentadoDTO {

    public PgimSegAccidentadoDTOResultado(Long idSeguroAccidentado, Long idAccidentado, Long idTipoSeguro) {
        super();

        this.setIdSeguroAccidentado(idSeguroAccidentado);
        this.setIdAccidentado(idAccidentado);
        this.setIdTipoSeguro(idTipoSeguro);
    }

}