package pe.gob.osinergmin.pgim.dtos;

public class PgimProcesoDTOResultado extends PgimProcesoDTO{
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ProcesoRepository#listarProcesos()
     */
    public PgimProcesoDTOResultado (Long idProceso, String noProceso) {
        super();
        this.setIdProceso(idProceso);
        this.setNoProceso(noProceso);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ProcesoRepository#listarProceso()
     */
    public PgimProcesoDTOResultado (Long idProceso, String noProceso, String deProceso, Long coProcesoSiged) {
        super();
        this.setIdProceso(idProceso);
        this.setNoProceso(noProceso);
        this.setDeProceso(deProceso);
        this.setCoProcesoSiged(coProcesoSiged);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ProcesoRepository#obtenerProcesoPorId()
     * @param idProceso
     * @param noProceso
     * @param deProceso
     * @param coProcesoSiged
     * @param idSector
     * @param idSubsector
     * @param flSeleccIndicador
     */
    public PgimProcesoDTOResultado (Long idProceso, String noProceso, String deProceso, Long coProcesoSiged,
        Long idSector, Long idSubsector, String flIndicador ) {
        super();
        this.setIdProceso(idProceso);
        this.setNoProceso(noProceso);
        this.setDeProceso(deProceso);
        this.setCoProcesoSiged(coProcesoSiged);
        this.setDescIdSector(idSector);
        this.setIdSubsector(idSubsector);
        this.setFlIndicador(flIndicador);
    }

}
