package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimRankingUmDTOResultado extends PgimRankingUmDTO {
    
    
    public PgimRankingUmDTOResultado (
        Long idRankingUm
        ,Long idRankingRiesgo
        ,Long idUnidadMinera
    ) {
        super();
        this.setIdRankingUm(idRankingUm);
        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdUnidadMinera(idUnidadMinera);
    }

    public PgimRankingUmDTOResultado (
        Long idRankingUm
        ,Long idRankingRiesgo
        ,Long idUnidadMinera
        ,BigDecimal moPuntaje

    ) {
        super();
        this.setIdRankingUm(idRankingUm);
        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setMoPuntaje(moPuntaje);
    }
}
