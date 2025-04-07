package pe.gob.osinergmin.pgim.dtos;

public class PgimFaseProcesoDTOResultado extends PgimFaseProcesoDTO {

/**
     * Permite portar los datos necesarios de la fase del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository#filtrarPorNombreFaseProceso()
     * @see pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository#obtenerFasesPorIdProceso()
     * @see pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository#obtenerFasesInstanciaProceso()
     * 
     * @param idFaseProceso
     * @param noFaseProceso
     */
    public PgimFaseProcesoDTOResultado(Long idFaseProceso, Long idProceso, String noFaseProceso) {
        super();
        this.setIdFaseProceso(idFaseProceso);
        this.setIdProceso(idProceso);
        this.setNoFaseProceso(noFaseProceso);
    }
    
    /**
     * Permite portar los datos necesarios de la fase del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository#listarFasesProceso()
     * 
     * @param idFaseProceso
     * @param idProceso
     * @param noFaseProceso
     * @param deFaseProceso
     */
    public PgimFaseProcesoDTOResultado(Long idFaseProceso, Long idProceso, String noFaseProceso, String deFaseProceso) {
        super();
        this.setIdFaseProceso(idFaseProceso);
        this.setIdProceso(idProceso);
        this.setNoFaseProceso(noFaseProceso);
        this.setDeFaseProceso(deFaseProceso);
    }

}