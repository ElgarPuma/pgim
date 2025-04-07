package pe.gob.osinergmin.pgim.dtos;

public class PgimSustanciaDTOResultado extends PgimSustanciaDTO {

    public PgimSustanciaDTOResultado(Long idSustancia, String noSustancia, Long idTipoSustancia) {
        super();

        this.setIdSustancia(idSustancia);
        this.setNoSustancia(noSustancia);
        this.setIdTipoSustancia(idTipoSustancia);
    }
    
}