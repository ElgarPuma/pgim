package pe.gob.osinergmin.pgim.dtos;

public class PgimObligacionNormaAuxDTOResultado extends PgimObligacionNormaAuxDTO {

	/***
     * Este constructor sirve para la lista de obligaciones normativas por criterio de matriz de supervisión. En el
     * repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ObligacionNormaAuxRepository#listarObligacionesNormativasPorCriterioMatriz()
     * 
     * @param idObligacionNormaAux
     * @param idOblgcnNrmaCrtrio
     * @param idMatrizCriterio
     * @param idNormaObligacion
     * @param idNormaItem
     * @param deObligacionNormativa
     * @param deNormaItem
     * @param descCoTipificacion
     * @param descNoItemTipificacion
     * @param descNroNormaItemTipificacion
     */
    public PgimObligacionNormaAuxDTOResultado(Long idObligacionNormaAux,Long idOblgcnNrmaCrtrio, Long idMatrizCriterio,            
            Long idNormaObligacion, Long idNormaItem, String deObligacionNormativa, 
            String deNormaItem, String descCoTipificacion, String descNoItemTipificacion,
            String descNroNormaItemTipificacion) {
        super();
        
        this.setIdObligacionNormaAux(idObligacionNormaAux);
        this.setIdOblgcnNrmaCrtrio(idOblgcnNrmaCrtrio);
        this.setIdMatrizCriterio(idMatrizCriterio);
        this.setIdNormaObligacion(idNormaObligacion);
        this.setIdNormaItem(idNormaItem);
        this.setDeObligacionNormativa(deObligacionNormativa);
        this.setDeNormaItem(deNormaItem);
      
        //valores por defecto para lista de selección      	
      	this.setDescSeleccionado(false);    
        
        this.setDescCoTipificacion(descCoTipificacion);
        this.setDescNoItemTipificacion(descNoItemTipificacion);
        this.setDescNroNormaItemTipificacion(descNroNormaItemTipificacion);        
    }
    
    /**
     * Este constructor sirve para la lista de obligaciones normativas por hecho constatado. En el
     * repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ObligacionNormaAuxRepository#listarObligacionesNormativasPorHC()
     * @see pe.gob.osinergmin.pgim.models.repository.ObligacionNormaAuxRepository#lObligacionesNormativasPorHCSelec()
     * 
     * @param idObligacionNormaAux
     * @param idOblgcnNrmaCrtrio
     * @param idMatrizCriterio
     * @param idNormaObligacion
     * @param idNormaItem
     * @param deObligacionNormativa
     * @param deNormaItem
     * @param idOblgcnNrmtvaHchoc
     * @param esAplicaSuper
     * @param descCoTipificacion
     * @param descNoItemTipificacion
     * @param descNroNormaItemTipificacion
     */
    public PgimObligacionNormaAuxDTOResultado(Long idObligacionNormaAux,Long idOblgcnNrmaCrtrio, Long idMatrizCriterio,            
            Long idNormaObligacion, Long idNormaItem, String deObligacionNormativa,
            String deNormaItem, Long idOblgcnNrmtvaHchoc, String esAplicaSuper,
            String descCoTipificacion, String descNoItemTipificacion, String descNroNormaItemTipificacion) {
        super();
        
        this.setIdObligacionNormaAux(idObligacionNormaAux);
        this.setIdOblgcnNrmaCrtrio(idOblgcnNrmaCrtrio);
        this.setIdMatrizCriterio(idMatrizCriterio);
        this.setIdNormaObligacion(idNormaObligacion);
        this.setIdNormaItem(idNormaItem);
        this.setDeObligacionNormativa(deObligacionNormativa);
        this.setDeNormaItem(deNormaItem);
      
        //Campos Auxiliares
        this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
        
        if(esAplicaSuper.equals("1"))
        	this.setDescSeleccionado(true);
        else {
        	this.setDescSeleccionado(false);
        }

        this.setDescCoTipificacion(descCoTipificacion);
        this.setDescNoItemTipificacion(descNoItemTipificacion);
        this.setDescNroNormaItemTipificacion(descNroNormaItemTipificacion);        
    }

    /**
     * 
     * Este constructor sirve para la lista de obligaciones normativas por hecho constatado. En el
     * repositorio usa el método:
     * @see pe.gob.osinergmin.pgim.models.repository.ObligacionNormaAuxRepository#listarObligacionesNormativasPorCriterioMatrizAllSelecion()
     * 
     * @param idObligacionNormaAux
     * @param idOblgcnNrmaCrtrio
     * @param idMatrizCriterio
     * @param idNormaObligacion
     * @param idNormaItem
     * @param deObligacionNormativa
     * @param deNormaItem
     * @param descSeleccionado
     */
    public PgimObligacionNormaAuxDTOResultado(Long idObligacionNormaAux,Long idOblgcnNrmaCrtrio, Long idMatrizCriterio,            
        Long idNormaObligacion, Long idNormaItem, String deObligacionNormativa,String deNormaItem, Boolean descSeleccionado) {
        super();

        this.setIdObligacionNormaAux(idObligacionNormaAux);
        this.setIdOblgcnNrmaCrtrio(idOblgcnNrmaCrtrio);
        this.setIdMatrizCriterio(idMatrizCriterio);
        this.setIdNormaObligacion(idNormaObligacion);
        this.setIdNormaItem(idNormaItem);
        this.setDeObligacionNormativa(deObligacionNormativa);
        this.setDeNormaItem(deNormaItem);
        this.setDescSeleccionado(descSeleccionado);
    }
    
    
}
