package pe.gob.osinergmin.pgim.dtos;

public class PgimHechoCnsttdoFaseDTOResultado extends PgimHechoCnsttdoFaseDTO {
	
	/**
     *
     * @see pe.gob.osinergmin.pgim.models.repository.CotejoHechoCnsttdoRepository#listarHechoCnsttdoFase()
     * @see pe.gob.osinergmin.pgim.models.repository.CotejoHechoCnsttdoRepository#listarHechoCnsttdoFaseParaEliminar()
     *     
     */
	public PgimHechoCnsttdoFaseDTOResultado(Long idHechoConstatadoHist,Long idHechoConstatado
			,String deHechoConstatado, String deComplementoObservacion,String deSustento
			,Long idTipoCumplimiento, String deTipoCumplimiento,Long idSupervision
			,Long idCriterioSprvsion,String coMatrizCriterio, Long nuOrdenGrpoCrtrio			            
            ,Long idHechoConstatadoOsi,String deHechoConstatadoOsi,String deComplementoObservacionOsi,String deSustentoOsi
            ,Long idTipoCumplimientoOsi, String deTipoCumplimientoOsi, String deComentarioOsi) {            
        super();
        
        this.setIdHechoConstatadoHist(idHechoConstatadoHist);
        this.setIdHechoConstatado(idHechoConstatado);
        this.setDeHechoConstatado(deHechoConstatado);        
        this.setDeComplementoObservacion(deComplementoObservacion);
        this.setDeSustento(deSustento);      
        this.setIdTipoCumplimiento(idTipoCumplimiento);
        this.setDeTipoCumplimiento(deTipoCumplimiento);
        this.setIdSupervision(idSupervision);        
        this.setIdCriterioSprvsion(idCriterioSprvsion);
        this.setCoMatrizCriterio(coMatrizCriterio);        
        this.setNuOrdenGrpoCrtrio(nuOrdenGrpoCrtrio);
        this.setIdHechoConstatadoOsi(idHechoConstatadoOsi);
        this.setDeHechoConstatadoOsi(deHechoConstatadoOsi);        
        this.setDeComplementoObservacionOsi(deComplementoObservacionOsi);
        this.setDeSustentoOsi(deSustentoOsi);
        this.setIdTipoCumplimientoOsi(idTipoCumplimientoOsi);
        this.setDeTipoCumplimientoOsi(deTipoCumplimientoOsi);
        this.setDeComentarioOsi(deComentarioOsi);
        /*
        if(idHechoConstatado != null) 
        	this.setDescComentarioHechoConstatadoOsi(deComentarioOsi);        
        else
        	this.setDescComentarioHechoConstatadoOsi(deHechoConstatadoOsi);
        */
    }

}
