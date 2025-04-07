package pe.gob.osinergmin.pgim.dtos;

public class PgimCmineroSprvsionDTOResultado extends PgimCmineroSprvsionDTO{
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.CmineroSprvsionRepository#listarComponenteMineroSupervision(Long)
     * @see pe.gob.osinergmin.pgim.models.repository.CmineroSprvsionRepository#obtenerComponenteMineroSupervisionId(Long)
     * @param idCmineroSprvsion
     * @param idSupervision
     * @param descCoSupervision
     * @param idComponenteMinero
     * @param descCoComponente
     * @param descNoComponente
     */
    public PgimCmineroSprvsionDTOResultado (Long idCmineroSprvsion, Long idSupervision, String descCoSupervision,
            Long idComponenteMinero, String descNoTipoComponente, String descCoComponente, String descNoComponente, boolean descSelected) {
        super();

        this.setIdCmineroSprvsion(idCmineroSprvsion);
        this.setIdSupervision(idSupervision);
        this.setDescCoSupervision(descCoSupervision);
        this.setIdComponenteMinero(idComponenteMinero);
        this.setDescNoTipoComponente(descNoTipoComponente);
        this.setDescCoComponente(descCoComponente);
        this.setDescNoComponente(descNoComponente);
        this.setDescSelected(descSelected);
    }
}
