package pe.gob.osinergmin.pgim.dtos;

public class PgimSustanciaUmineraDTOResultado extends PgimSustanciaUmineraDTO {

    public PgimSustanciaUmineraDTOResultado(Long idSustanciaUminera, Long idSustancia, Long idUnidadMinera) {
        super();

        this.setIdSustanciaUminera(idSustanciaUminera);
        this.setIdSustancia(idSustancia);
        this.setIdUnidadMinera(idUnidadMinera);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SustanciaUnidadMineraRepository#listarSustanciaPorUnidadMinera
     * @param idSustanciaUminera
     * @param idSustancia
     * @param idUnidadMinera
     * @param descNoSustancia
     */
    public PgimSustanciaUmineraDTOResultado(Long idSustanciaUminera, Long idSustancia, Long idUnidadMinera, String descNoSustancia) {
        super();

        this.setIdSustanciaUminera(idSustanciaUminera);
        this.setIdSustancia(idSustancia);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoSustancia(descNoSustancia);
    }

}