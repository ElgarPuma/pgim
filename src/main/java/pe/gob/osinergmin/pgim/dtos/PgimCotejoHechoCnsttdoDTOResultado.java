package pe.gob.osinergmin.pgim.dtos;

public class PgimCotejoHechoCnsttdoDTOResultado extends PgimCotejoHechoCnsttdoDTO  {
	
	/**
     * Este constructor sirve para la lista de cotejo de hechos constatados por supervisión. 
     * El repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.CotejoHechoCnsttdoRepository#listarCotejoHechosConstatadosPaginado()
     * @see pe.gob.osinergmin.pgim.models.repository.CotejoHechoCnsttdoRepository#listarCotejoHCpendientesConformidad() 
     * @see pe.gob.osinergmin.pgim.models.repository.CotejoHechoCnsttdoRepository#listarCotejoHechosConstatadosPorFasePaginado()
     *     
     */
	public PgimCotejoHechoCnsttdoDTOResultado(Long idHechoConstatado
			,String deHechoConstatado, String deComplementoObservacion,String deSustento
			,Long idTipoCumplimiento, String deTipoCumplimiento,Long idSupervision
			,Long idCriterioSprvsion,String coMatrizCriterio, Long nuOrdenGrpoCrtrio			            
            ,Long idHechoConstatadoOsi,String deHechoConstatadoOsi,String deComplementoObservacionOsi,String deSustentoOsi
            ,Long idTipoCumplimientoOsi, String deTipoCumplimientoOsi, String deComentarioOsi, String descCoMatrizCriterioOsi) {            
        super();
        
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
        
        if(idHechoConstatado != null) 
        	this.setDescComentarioHechoConstatadoOsi(deComentarioOsi);        
        else
        	this.setDescComentarioHechoConstatadoOsi(deHechoConstatadoOsi);
        
        this.setDescCoMatrizCriterioOsi(descCoMatrizCriterioOsi);
        
    }
	
	
	/**
     * 
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.CotejoHechoCnsttdoRepository#listarCotejoHechosConstatados()
     * 
     *     
     */
	public PgimCotejoHechoCnsttdoDTOResultado(Long idHechoConstatado
			,String deHechoConstatado, String deComplementoObservacion,String deSustento
			,Long idTipoCumplimiento, String deTipoCumplimiento,Long idSupervision
			,Long idCriterioSprvsion,String coMatrizCriterio, Long nuOrdenGrpoCrtrio
			,Long idFaseProceso
            ,Long idHechoConstatadoOsi,String deHechoConstatadoOsi,String deComplementoObservacionOsi,String deSustentoOsi
            ,Long idTipoCumplimientoOsi, String deTipoCumplimientoOsi, String deComentarioOsi
            ,Long idFaseProcesoOsi
			) {            
        super();
        
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
        
        this.setIdFaseProceso(idFaseProceso);
        
        this.setIdHechoConstatadoOsi(idHechoConstatadoOsi);
        this.setDeHechoConstatadoOsi(deHechoConstatadoOsi);        
        this.setDeComplementoObservacionOsi(deComplementoObservacionOsi);
        this.setDeSustentoOsi(deSustentoOsi);
        this.setIdTipoCumplimientoOsi(idTipoCumplimientoOsi);
        this.setDeTipoCumplimientoOsi(deTipoCumplimientoOsi);
        this.setDeComentarioOsi(deComentarioOsi);
        
        this.setIdFaseProcesoOsi(idFaseProcesoOsi);
        
        if(idHechoConstatado != null) 
        	this.setDescComentarioHechoConstatadoOsi(deComentarioOsi);        
        else
        	this.setDescComentarioHechoConstatadoOsi(deHechoConstatadoOsi);
        
    }
	
	
	

}
