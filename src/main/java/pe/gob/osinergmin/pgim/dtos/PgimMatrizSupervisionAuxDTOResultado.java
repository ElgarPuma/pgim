package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimMatrizSupervisionAuxDTOResultado extends PgimMatrizSupervisionAuxDTO {
	
	
	/**
     * Este constructor sirve para la lista de la matriz de supervisión. En el
     * repositorio usa el método:
     *
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionAuxRepository #listarMatrizSupervision()
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionAuxRepository #listarMatrizSupervisionAddConformidad()
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionAuxRepository #listarMatrizSupervisionFaseHist()
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionAuxRepository #listarMatrizSupervisionDescarga()
     *     
     */	
    public PgimMatrizSupervisionAuxDTOResultado(Long idMatrizSupervisionAux,Long idCriterioSprvsion, Long idMatrizCriterio,
            String coMatrizCriterio, String deMatrizCriterio, String deBaseLegal,
            Long idMatrizGrpoCrtrio, String coMatrizGrpoCrtrio, String noMatrizGrpoCrtrio,
            Long idSupervision, BigDecimal cantidadHc,Long codigoCumple, String descripcionCumple, String deNorma, String deTipificacion,
            Long idInstanciaPasoCriterio, Long idPasoProcesoAgregoCs, Long idMatrizSupervision) {
        super();
        
        this.setIdMatrizSupervisionAux(idMatrizSupervisionAux);
        this.setIdCriterioSprvsion(idCriterioSprvsion);
        this.setIdMatrizCriterio(idMatrizCriterio);
        this.setCoMatrizCriterio(coMatrizCriterio);
        this.setDeMatrizCriterio(deMatrizCriterio);
        this.setDeBaseLegal(deBaseLegal);
        this.setIdMatrizGrpoCrtrio(idMatrizGrpoCrtrio);
        this.setCoMatrizGrpoCrtrio(coMatrizGrpoCrtrio);
        this.setNoMatrizGrpoCrtrio(noMatrizGrpoCrtrio);
        this.setIdSupervision(idSupervision);        
        this.setCantidadHc(cantidadHc);
        this.setCodigoCumple(codigoCumple);
        this.setDescripcionCumple(descripcionCumple);
        this.setDeNorma(deNorma);
        this.setDeTipificacion(deTipificacion);
        this.setIdInstanciaPasoCriterio(idInstanciaPasoCriterio);
        this.setIdPasoProcesoAgregoCs(idPasoProcesoAgregoCs);
        this.setIdMatrizSupervision(idMatrizSupervision);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionAuxRepository #listarSupervisionesPorCuadroVerificacion()
     */
    public PgimMatrizSupervisionAuxDTOResultado(Long idSupervision) {
        super();
        
        this.setIdSupervision(idSupervision);        
        
    }

}
