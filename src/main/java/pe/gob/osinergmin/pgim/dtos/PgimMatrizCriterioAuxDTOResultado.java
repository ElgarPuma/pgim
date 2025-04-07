package pe.gob.osinergmin.pgim.dtos;

public class PgimMatrizCriterioAuxDTOResultado extends PgimMatrizCriterioAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizCriterioAuxRepository#listarMatrizCriterio()
     * @param idMatrizCriterioAux
     * @param idMatrizCriterio
     * @param idMatrizSupervision
     * @param coMatrizGrpoCrtrio
     * @param noMatrizGrpoCrtrio
     * @param coMatrizCriterio
     * @param deMatrizCriterio
     * @param nuObligaciones
     * @param nuOrden
     * @param esVigente
     */
    public PgimMatrizCriterioAuxDTOResultado(Long idMatrizCriterioAux, Long idMatrizCriterio, Long idMatrizSupervision,
            Long idMatrizGrpoCrtrio, String coMatrizGrpoCrtrio, String noMatrizGrpoCrtrio, Long nuOrdenMatrizGrpoCrtrio,
            String coMatrizCriterio, String deMatrizCriterio, Long nuOrdenMatrizCriterio, Long nuObligaciones,
            String esVigente, String deBaseLegal, String norma, String tipificacion) {
        super();
        this.setIdMatrizCriterioAux(idMatrizCriterioAux);
        this.setIdMatrizCriterio(idMatrizCriterio);
        this.setIdMatrizSupervision(idMatrizSupervision);
        this.setIdMatrizGrpoCrtrio(idMatrizGrpoCrtrio);
        this.setCoMatrizGrpoCrtrio(coMatrizGrpoCrtrio);
        this.setNoMatrizGrpoCrtrio(noMatrizGrpoCrtrio);
        this.setNuOrdenMatrizGrpoCrtrio(nuOrdenMatrizGrpoCrtrio);
        this.setCoMatrizCriterio(coMatrizCriterio);
        this.setDeMatrizCriterio(deMatrizCriterio);
        this.setNuOrdenMatrizCriterio(nuOrdenMatrizCriterio);
        this.setNuObligaciones(nuObligaciones);
        this.setEsVigente(esVigente);
        this.setDeBaseLegal(deBaseLegal);
        this.setNorma(norma);
        this.setTipificacion(tipificacion);
    }

    public PgimMatrizCriterioAuxDTOResultado(Long idMatrizCriterioAux, Long idMatrizCriterio, Long idMatrizSupervision,
            Long idMatrizGrpoCrtrio, Long nuObligaciones) {
        super();
        this.setIdMatrizCriterioAux(idMatrizCriterioAux);
        this.setIdMatrizCriterio(idMatrizCriterio);
        this.setIdMatrizSupervision(idMatrizSupervision);
        this.setIdMatrizGrpoCrtrio(idMatrizGrpoCrtrio);
        this.setNuObligaciones(nuObligaciones);
    }
}
