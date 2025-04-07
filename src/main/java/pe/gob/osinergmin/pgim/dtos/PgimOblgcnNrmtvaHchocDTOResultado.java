package pe.gob.osinergmin.pgim.dtos;

public class PgimOblgcnNrmtvaHchocDTOResultado extends PgimOblgcnNrmtvaHchocDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.OblgcnNrmtvaHchocRepository#obtenerOblgNormativaPorHechoDTO()
     * @see pe.gob.osinergmin.pgim.models.repository.OblgcnNrmtvaHchocRepository#listarOblgFiscalizadasPorCriterioSuperv()
     * @param idOblgcnNrmtvaHchoc
     * @param idHechoConstatado
     * @param idOblgcnNrmaCrtrio
     * @param esAplica
     */
    public PgimOblgcnNrmtvaHchocDTOResultado(Long idOblgcnNrmtvaHchoc, Long idHechoConstatado, Long idOblgcnNrmaCrtrio, String esAplica){
        super();
        this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
        this.setIdHechoConstatado(idHechoConstatado);
        this.setIdOblgcnNrmaCrtrio(idOblgcnNrmaCrtrio);
        this.setEsAplica(esAplica);

    }
    
}
