package pe.gob.osinergmin.pgim.dtos;

public class PgimItemTipificacionDTOResultado extends PgimItemTipificacionDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ItemTipificacionRepository#listarItemTipificacion()
     * @param descNoCortoNorma
     * @param descNoNorma
     * @param descFePublicacion
     * @param descFlVigente
     */
    public PgimItemTipificacionDTOResultado(Long idItemTipificacion, Long idNorma, String coTipificacion,
            String noItemTipificacion, String deSancionPecuniariaUit, Long descNuObligaciones, String deBaseLegal, Long nuOrden) {
        super();
        this.setIdItemTipificacion(idItemTipificacion);
        this.setIdNorma(idNorma);
        this.setCoTipificacion(coTipificacion);
        this.setNoItemTipificacion(noItemTipificacion);
        this.setDeSancionPecuniariaUit(deSancionPecuniariaUit);
        this.setDescNuObligaciones(descNuObligaciones);
        this.setDeBaseLegal(deBaseLegal);
        this.setNuOrden(nuOrden);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ItemTipificacionRepository#obtenerItemTipificacionPorId()
     * @param descNoCortoNorma
     * @param descNoNorma
     * @param descFePublicacion
     * @param descFlVigente
     */
    public PgimItemTipificacionDTOResultado(Long idItemTipificacion, Long idNorma, String coTipificacion,
            String noItemTipificacion, String deSancionPecuniariaUit, String esVigente, String deBaseLegal, Long nuOrden) {
        super();
        this.setIdItemTipificacion(idItemTipificacion);
        this.setIdNorma(idNorma);
        this.setCoTipificacion(coTipificacion);
        this.setNoItemTipificacion(noItemTipificacion);
        this.setDeSancionPecuniariaUit(deSancionPecuniariaUit);
        this.setEsVigente(esVigente);
        this.setDeBaseLegal(deBaseLegal);
        this.setNuOrden(nuOrden);
    }
    
}
