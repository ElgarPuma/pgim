package pe.gob.osinergmin.pgim.dtos;

public class PgimMatrizCriterioDTOResultado extends PgimMatrizCriterioDTO {
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizCriterioRepository #obtenerMatrizCriterioPorId()
     *     
     */
    public PgimMatrizCriterioDTOResultado(Long idMatrizCriterio, Long descIdMatrizSupervision,
            String descCoMatrizGrpoCrtrio, String descNoMatrizGrpoCrtrio, String coMatrizCriterio,
            String deMatrizCriterio, String deBaseLegal, Long nuOrden, String esVigente, Long descNuObligaciones,
            Long idMatrizGrpoCrtrio, String flCopiaPorDefecto) {
        super();
        this.setIdMatrizCriterio(idMatrizCriterio);
        this.setDescIdMatrizSupervision(descIdMatrizSupervision);
        this.setDescCoMatrizGrpoCrtrio(descCoMatrizGrpoCrtrio);
        this.setDescNoMatrizGrpoCrtrio(descNoMatrizGrpoCrtrio);
        this.setCoMatrizCriterio(coMatrizCriterio);
        this.setDeMatrizCriterio(deMatrizCriterio);
        this.setDeBaseLegal(deBaseLegal);
        this.setNuOrden(nuOrden);
        this.setEsVigente(esVigente);
        this.setDescNuObligaciones(descNuObligaciones);
        this.setIdMatrizGrpoCrtrio(idMatrizGrpoCrtrio);
        this.setFlCopiaPorDefecto(flCopiaPorDefecto);
    }
    
}
