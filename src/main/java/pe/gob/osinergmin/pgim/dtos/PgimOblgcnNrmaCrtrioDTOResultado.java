package pe.gob.osinergmin.pgim.dtos;

public class PgimOblgcnNrmaCrtrioDTOResultado extends PgimOblgcnNrmaCrtrioDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.OblgcnNrmaCrtrioRepository #listarObligacionNrmaCriterio()
     */
    public PgimOblgcnNrmaCrtrioDTOResultado(Long idOblgcnNrmaCrtrio, Long idMatrizCriterio, String descNoCortoNorma,
            String descCoItem, String descDeContenido, String descFlVigenteItem,
            String descDeNormaObligacion, String descEsVigenteNormaObligacion, String descDeSancionPecuniariaUit,
            String descCoTipificacion, String descNoItemTipificacion, String descEsVigenteTipificacion) {
        super();
        this.setIdOblgcnNrmaCrtrio(idOblgcnNrmaCrtrio);
        this.setIdMatrizCriterio(idMatrizCriterio);

        // NORMA SUPERIOR
        this.setDescNoCortoNorma(descNoCortoNorma);
        // this.setDescNoNorma(descNoNorma);

        // ITEMS DE NORMAS => vigente ###
        this.setDescCoItem(descCoItem);
        this.setDescDeContenido(descDeContenido);
        this.setDescFlVigenteItem(descFlVigenteItem);

        // PARAFRASEO DE OBLIGACION DE SUPERVISION
        this.setDescDeNormaObligacion(descDeNormaObligacion);
        this.setDescEsVigenteNormaObligacion(descEsVigenteNormaObligacion);

        // TIPIFICACION DE ITEMS
        this.setDescDeSancionPecuniariaUit(descDeSancionPecuniariaUit);
        this.setDescCoTipificacion(descCoTipificacion);
        this.setDescNoItemTipificacion(descNoItemTipificacion);
        this.setDescEsVigenteTipificacion(descEsVigenteTipificacion);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.OblgcnNrmaCrtrioRepository #listarObligacionNrmaCriterioAux()
     */
    public PgimOblgcnNrmaCrtrioDTOResultado(Long idOblgcnNrmaCrtrio, Long idMatrizCriterio, String descNoCortoNorma,String noTipoNorma,
            String descCoItem, String descDeContenido,
            String descFlVigenteItem, String ubicacionItem, String descDeNormaObligacion,
            String descEsVigenteNormaObligacion, String descDeSancionPecuniariaUit, String descCoTipificacion,
            String descNoItemTipificacion, String descEsVigenteTipificacion, String coNormaTipificacion, String esRegistro, Long descNuHijos, Long idNormaItemPadre, Long idNormaItem) {
        super();
        this.setIdOblgcnNrmaCrtrio(idOblgcnNrmaCrtrio);
        this.setIdMatrizCriterio(idMatrizCriterio);
        
        // NORMA SUPERIOR
        this.setDescNoCortoNorma(descNoCortoNorma);
        this.setDescNoTipoNorma(noTipoNorma);

        // ITEMS DE NORMAS
        this.setDescCoItem(descCoItem);
        this.setDescDeContenido(descDeContenido);
        this.setDescFlVigenteItem(descFlVigenteItem);
        this.setDescUbicacionItem(ubicacionItem);

        // PARAFRASEO DE OBLIGACION DE SUPERVISION
        this.setDescDeNormaObligacion(descDeNormaObligacion);
        this.setDescEsVigenteNormaObligacion(descEsVigenteNormaObligacion);

        // TIPIFICACION DE ITEMS
        this.setDescDeSancionPecuniariaUit(descDeSancionPecuniariaUit);
        this.setDescCoTipificacion(descCoTipificacion);
        this.setDescNoItemTipificacion(descNoItemTipificacion);
        this.setDescEsVigenteTipificacion(descEsVigenteTipificacion);
        this.setDescCoNormaTipificacion(coNormaTipificacion);
        this.setEsRegistro(esRegistro);
        this.setDescNuHijos(descNuHijos);
        this.setIdNormaItemPadre(idNormaItemPadre);
        this.setIdNormaItem(idNormaItem);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.OblgcnNrmaCrtrioRepository #obtenerObligacionNrmaCriterioPorId()
     */
    public PgimOblgcnNrmaCrtrioDTOResultado(Long idOblgcnNrmaCrtrio, Long idMatrizCriterio, Long idNormaObligacion,
            String descNoCortoNorma, String descCoItem, String descDeContenido, String descFlVigenteItem,
            String ubicacionItem, String descDeNormaObligacion, String descEsVigenteNormaObligacion, String descDeSancionPecuniariaUit,
            String descCoTipificacion, String descNoItemTipificacion, String descEsVigenteTipificacion, 
            String descCoNormaTipificacion) {
        super();
        this.setIdOblgcnNrmaCrtrio(idOblgcnNrmaCrtrio);
        this.setIdMatrizCriterio(idMatrizCriterio);
        this.setIdNormaObligacion(idNormaObligacion);
        // NORMA SUPERIOR
        this.setDescNoCortoNorma(descNoCortoNorma);

        // ITEMS DE NORMAS => vigente ###
        this.setDescCoItem(descCoItem);
        this.setDescDeContenido(descDeContenido);
        this.setDescFlVigenteItem(descFlVigenteItem);
        this.setDescUbicacionItem(ubicacionItem);        

        // PARAFRASEO DE OBLIGACION DE SUPERVISION
        this.setDescDeNormaObligacion(descDeNormaObligacion);
        this.setDescEsVigenteNormaObligacion(descEsVigenteNormaObligacion);

        // TIPIFICACION DE ITEMS
        this.setDescDeSancionPecuniariaUit(descDeSancionPecuniariaUit);
        this.setDescCoTipificacion(descCoTipificacion);
        this.setDescNoItemTipificacion(descNoItemTipificacion);
        this.setDescEsVigenteTipificacion(descEsVigenteTipificacion);
        this.setDescCoNormaTipificacion(descCoNormaTipificacion);
    }
    

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.OblgcnNrmaCrtrioRepository #listarObligacionNrmaCriterioSeleccion()
     */
    public PgimOblgcnNrmaCrtrioDTOResultado(Long idNormaObligacion,String descNoCortoNorma, String descNoNorma, String noTipoNorma,
            String descCoItem, String descDeContenido, String descFlVigenteItem,String ubicacionItem,
            String descDeNormaObligacion, String descEsVigenteNormaObligacion, String descDeSancionPecuniariaUit,
            String descCoTipificacion, String descNoItemTipificacion, String descEsVigenteTipificacion, String coNormaTipificacion,
            Long idNormaItemPadre, Long idNormaItem) {
        super();
        //this.setIdOblgcnNrmaCrtrio(idOblgcnNrmaCrtrio);
        //this.setIdMatrizCriterio(idMatrizCriterio);

        // NORMA SUPERIOR
        this.setIdNormaObligacion(idNormaObligacion);
        this.setDescNoCortoNorma(descNoCortoNorma);
        this.setDescNoNorma(descNoNorma);
        this.setDescNoTipoNorma(noTipoNorma);

        // ITEMS DE NORMAS => vigente ###
        this.setDescCoItem(descCoItem);
        this.setDescDeContenido(descDeContenido);
        this.setDescFlVigenteItem(descFlVigenteItem);
        this.setDescUbicacionItem(ubicacionItem);

        // PARAFRASEO DE OBLIGACION DE SUPERVISION
        this.setDescDeNormaObligacion(descDeNormaObligacion);
        this.setDescEsVigenteNormaObligacion(descEsVigenteNormaObligacion);

        // TIPIFICACION DE ITEMS
        this.setDescDeSancionPecuniariaUit(descDeSancionPecuniariaUit);
        this.setDescCoTipificacion(descCoTipificacion);
        this.setDescNoItemTipificacion(descNoItemTipificacion);
        this.setDescEsVigenteTipificacion(descEsVigenteTipificacion);
        this.setDescCoNormaTipificacion(coNormaTipificacion);
        this.setIdNormaItemPadre(idNormaItemPadre);
        this.setIdNormaItem(idNormaItem);
    }
    
}
