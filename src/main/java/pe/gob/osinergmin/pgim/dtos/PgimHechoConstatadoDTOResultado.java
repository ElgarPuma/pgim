package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

public class PgimHechoConstatadoDTOResultado extends PgimHechoConstatadoDTO {
	
	
	
	/**
     * Este constructor sirve para la lista hechos constatados por criterio de la matriz de supervisión. En el
     * repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository#listarHechosConstatadosPorCriterioMatriz()     
     * @see pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository#listarHechosConstatadosPorHechoConstatadoRmplznte()
     * @see pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository#listarHechosConstatadosPorSupervision()
     * @see pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository#listarHechosConstatadosPorCriterioMatrizHistPorFase()
     * @see pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository#obtenerHechosConstatadosPorTipoCumplimiento()
     *  
     *     
     */
	public PgimHechoConstatadoDTOResultado(Long idHechoConstatado,Long idCriterioSprvsion, Long idSupervision,
			Long idInstanciaPaso, Long idTipoCumplimiento,String descTipoCumplimiento,
            String deHechoConstatado, String deComplementoObservacion,String deSustentoSuper, Long idRolProceso
            ,Long descFaseProcesoSupervision, String descNoFaseDestino, String descNoPasoDestino,String esVigente,Long idHechoConstatadoRmplznte
            ,Long idHechoConstatadoOrigen, String flIncluidoActaSupervision) {            
        super();
        
        this.setIdHechoConstatado(idHechoConstatado);
        this.setIdCriterioSprvsion(idCriterioSprvsion);
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdTipoCumplimiento(idTipoCumplimiento);
        this.setDescTipoCumplimiento(descTipoCumplimiento);        
        this.setDeHechoConstatadoT(deHechoConstatado);        
        this.setDeComplementoObservacion(deComplementoObservacion);
        this.setDeSustentoT(deSustentoSuper);      
        this.setIdRolProceso(idRolProceso);
        this.setDescFaseProcesoSupervision(descFaseProcesoSupervision);
        this.setDescNoFaseDestino(descNoFaseDestino);
        this.setDescNoPasoDestino(descNoPasoDestino);
        
        this.setEsVigente(esVigente);
        this.setIdHechoConstatadoRmplznte(idHechoConstatadoRmplznte);
        this.setIdHechoConstatadoOrigen(idHechoConstatadoOrigen);
        this.setFlIncluidoActaSupervision(flIncluidoActaSupervision);
    }

    /**
     * Este constructor sirve para la lista hechos constatados por código de criterio de la matriz de supervisión. En el
     * repositorio usa el método:
     * @see pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository#listarHechosConstatadosConNoCumplimientos()
     *  
     *     
     */
	public PgimHechoConstatadoDTOResultado(Long idHechoConstatado,Long idCriterioSprvsion, String descCoMatrizCriterio, Long idSupervision,
			Long idInstanciaPaso, Long idTipoCumplimiento,String descTipoCumplimiento,
            String deHechoConstatado, String deComplementoObservacion,String deSustentoSuper, Long idRolProceso
            ,Long descFaseProcesoSupervision, String descNoFaseDestino, String descNoPasoDestino,String esVigente,Long idHechoConstatadoRmplznte
            ,Long idHechoConstatadoOrigen, String flIncluidoActaSupervision) {            
        super();
        
        this.setIdHechoConstatado(idHechoConstatado);
        this.setIdCriterioSprvsion(idCriterioSprvsion);
        this.setDescCoMatrizCriterio(descCoMatrizCriterio);
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdTipoCumplimiento(idTipoCumplimiento);
        this.setDescTipoCumplimiento(descTipoCumplimiento);        
        this.setDeHechoConstatadoT(deHechoConstatado);        
        this.setDeComplementoObservacion(deComplementoObservacion);
        this.setDeSustentoT(deSustentoSuper);      
        this.setIdRolProceso(idRolProceso);
        this.setDescFaseProcesoSupervision(descFaseProcesoSupervision);
        this.setDescNoFaseDestino(descNoFaseDestino);
        this.setDescNoPasoDestino(descNoPasoDestino);
        
        this.setEsVigente(esVigente);
        this.setIdHechoConstatadoRmplznte(idHechoConstatadoRmplznte);
        this.setIdHechoConstatadoOrigen(idHechoConstatadoOrigen);
        this.setFlIncluidoActaSupervision(flIncluidoActaSupervision);
    }
	
	
	
	/**
     * Este constructor sirve para obtener un registro de hecho constatado 
     * en el repositorio usa el método:
     * 
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository#obtenerHechoConstatadoPorId()
     *
     *     
     */
	public PgimHechoConstatadoDTOResultado(Long idHechoConstatado,Long idCriterioSprvsion, Long idSupervision,
			Long idInstanciaPaso, Long idTipoCumplimiento,String descTipoCumplimiento,
            String deHechoConstatado, String deComplementoObservacion,String deSustentoSuper, String deComentarioOsi
            , Long idRolProceso,Long descFaseProcesoSupervision, String noFaseProceso, String esVigente,Long idHechoConstatadoRmplznte
            ,Long idHechoConstatadoOrigen, String flIncluidoActaSupervision, String usCreacion
            ,Date feCreacion, String usActualizacion, Date feActualizacion, String descNoRolProceso) {            
        super();
        
        this.setIdHechoConstatado(idHechoConstatado);
        this.setIdCriterioSprvsion(idCriterioSprvsion);
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdTipoCumplimiento(idTipoCumplimiento);
        this.setDescTipoCumplimiento(descTipoCumplimiento);        
        this.setDeHechoConstatadoT(deHechoConstatado);        
        this.setDeComplementoObservacion(deComplementoObservacion);
        this.setDeSustentoT(deSustentoSuper);      
        this.setDeComentarioOsiT(deComentarioOsi);
        this.setIdRolProceso(idRolProceso);
        this.setDescFaseProcesoSupervision(descFaseProcesoSupervision);
        
        this.setEsVigente(esVigente);
        this.setIdHechoConstatadoRmplznte(idHechoConstatadoRmplznte);
        this.setIdHechoConstatadoOrigen(idHechoConstatadoOrigen);
        this.setFlIncluidoActaSupervision(flIncluidoActaSupervision);
        
        this.setUsCreacion(usCreacion);
        this.setFeCreacion(feCreacion);
        this.setUsActualizacion(usActualizacion);
        this.setFeActualizacion(feActualizacion);
        this.setDescNoRolProceso(descNoRolProceso);
        
        if(feActualizacion!=null)
        	this.setDescFeHistorico(feActualizacion);
        else
        	this.setDescFeHistorico(feCreacion);
        
        if(idRolProceso==ConstantesUtil.PROCESO_ROL_ESP_TECNICO || idRolProceso==ConstantesUtil.PROCESO_ROL_ESP_LEGAL) { 
        	this.setDescFlEspecialistaOsi(true);
        	this.setDescNoFaseProcesoAux(noFaseProceso.substring(2));
        }
        else {
        	this.setDescFlEspecialistaOsi(false);
        	this.setDescNoFaseProcesoAux("Supervisión o post-supervisión de Campo");
        }
    }
	
	
	
	/**
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository#obtenerHechoConstatadoDtoPorId()
     * 
     *     
     */
	public PgimHechoConstatadoDTOResultado(Long idHechoConstatado,Long idCriterioSprvsion, Long idSupervision,
			Long idInstanciaPaso, Long idTipoCumplimiento,String descTipoCumplimiento,
            String deHechoConstatado, String deComplementoObservacion,String deSustentoSuper, String deComentarioOsi
            , Long idRolProceso,Long descFaseProcesoSupervision, String noFaseProceso, String esVigente,Long idHechoConstatadoRmplznte
            ,Long idHechoConstatadoOrigen, String flIncluidoActaSupervision, String usCreacion
            ,Date feCreacion, String usActualizacion, Date feActualizacion, String descNoRolProceso
            ,String coMatrizCriterio,Long nuOrdenGrpoCrtrio,Long nuOrdenCriterio,Long coTipoCumplimiento, String descDeMatrizCriterio
			) {            
        super();
        
        this.setIdHechoConstatado(idHechoConstatado);
        this.setIdCriterioSprvsion(idCriterioSprvsion);
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdTipoCumplimiento(idTipoCumplimiento);
        this.setDescTipoCumplimiento(descTipoCumplimiento);        
        this.setDeHechoConstatadoT(deHechoConstatado);        
        this.setDeComplementoObservacion(deComplementoObservacion);
        this.setDeSustentoT(deSustentoSuper);      
        this.setDeComentarioOsiT(deComentarioOsi);
        this.setIdRolProceso(idRolProceso);
        this.setDescFaseProcesoSupervision(descFaseProcesoSupervision);
        
        this.setEsVigente(esVigente);
        this.setIdHechoConstatadoRmplznte(idHechoConstatadoRmplznte);
        this.setIdHechoConstatadoOrigen(idHechoConstatadoOrigen);
        this.setFlIncluidoActaSupervision(flIncluidoActaSupervision);
        
        this.setUsCreacion(usCreacion);
        this.setFeCreacion(feCreacion);
        this.setUsActualizacion(usActualizacion);
        this.setFeActualizacion(feActualizacion);
        this.setDescNoRolProceso(descNoRolProceso);
        
        this.setDescCoMatrizCriterio(coMatrizCriterio);
        this.setDescNuOrdenGrpoCrtrio(nuOrdenGrpoCrtrio);
        this.setDescNuOrdenCriterio(nuOrdenCriterio);
        this.setDescCoTipoCumplimiento(coTipoCumplimiento);
        
        if(feActualizacion!=null)
        	this.setDescFeHistorico(feActualizacion);
        else
        	this.setDescFeHistorico(feCreacion);
        
        if(idRolProceso==ConstantesUtil.PROCESO_ROL_ESP_TECNICO || idRolProceso==ConstantesUtil.PROCESO_ROL_ESP_LEGAL) { 
        	this.setDescFlEspecialistaOsi(true);
        	this.setDescNoFaseProcesoAux(noFaseProceso.substring(2));
        }
        else {
        	this.setDescFlEspecialistaOsi(false);
        	this.setDescNoFaseProcesoAux("Supervisión o post-supervisión de Campo");
        }
        
        this.setDescDeMatrizCriterio(descDeMatrizCriterio);
    }
	
	
	
}
