package pe.gob.osinergmin.pgim.dtos;

public class PgimMtdoExplotacionDTOResultado extends PgimMtdoExplotacionDTO {

    public PgimMtdoExplotacionDTOResultado(Long idMetodoExplotacion, String noMetodoExplotacion, String deMetodoExplotacion,
            Long idMetodoMinado) {
        super();

        this.setIdMetodoExplotacion(idMetodoExplotacion);
        this.setNoMetodoExplotacion(noMetodoExplotacion);
        this.setDeMetodoExplotacion(deMetodoExplotacion);
        this.setIdMetodoMinado(idMetodoMinado);
    }

}