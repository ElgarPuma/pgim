package pe.gob.osinergmin.pgim.dtos;

public class PgimRelacionAccionDTOResultado extends PgimRelacionAccionDTO {

    /**
     * Sirve para obtener la relación de acciones dada. En el repositorio usa el
     * método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionAccionRepository#obtenerRelacionAccion()
     * 
     * @param idRelacionAccion
     * @param idRelacionPaso
     * @param idRelacionPasoParalela
     * @param deAccion
     * @param descNoPasoProceso
     * @param descNoRolPasoProceso
     * @param flActivarInstanciaPaso
     */
    public PgimRelacionAccionDTOResultado(Long idRelacionAccion, Long idRelacionPaso, Long idRelacionPasoParalela,
                                          String deAccion, String descNoPasoProceso, String descNoRolPasoProceso,
                                          String flActivarInstanciaPaso) {
        super();

        this.setIdRelacionAccion(idRelacionAccion);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdRelacionPasoParalela(idRelacionPasoParalela);
        this.setDeAccion(deAccion);
        this.setDescNoPasoProceso(descNoPasoProceso);
        this.setDescNoRolPasoProceso(descNoRolPasoProceso);
        this.setFlActivarInstanciaPaso(flActivarInstanciaPaso);
    }
}
