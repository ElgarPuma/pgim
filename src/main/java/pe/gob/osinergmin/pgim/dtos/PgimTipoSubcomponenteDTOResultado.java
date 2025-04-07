package pe.gob.osinergmin.pgim.dtos;

public class PgimTipoSubcomponenteDTOResultado extends PgimTipoSubcomponenteDTO {

    public PgimTipoSubcomponenteDTOResultado(Long idTipoSubcomponente, Long idTipoComponenteMinero,
            String coTipoSubcomponente,
            String noTipoSubcomponente) {
        super();
        this.setIdTipoSubcomponente(idTipoSubcomponente);
        this.setIdTipoComponenteMinero(idTipoComponenteMinero);
        this.setCoTipoSubcomponente(coTipoSubcomponente);
        this.setNoTipoSubcomponente(noTipoSubcomponente);
    }
}
