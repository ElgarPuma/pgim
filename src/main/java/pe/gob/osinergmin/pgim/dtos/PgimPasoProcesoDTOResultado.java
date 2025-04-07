package pe.gob.osinergmin.pgim.dtos;

public class PgimPasoProcesoDTOResultado extends PgimPasoProcesoDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository#filtrarPorNombrePasoProceso()
     * @param idPasoProceso
     * @param noPasoProceso
     */
    public PgimPasoProcesoDTOResultado(Long idPasoProceso, String noPasoProceso) {
        super();
        this.setIdPasoProceso(idPasoProceso);
        this.setNoPasoProceso(noPasoProceso);
    }

    /**
     * Permite enviar las porpiedades al repositorio
     
     * @see pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository#obtenerPasosPorIdProceso()
     * @param idPasoProceso
     * @param noPasoProceso
     * @param idFaseProceso
     */
    public PgimPasoProcesoDTOResultado(Long idPasoProceso, String noPasoProceso, Long idFaseProceso) {
        super();
        this.setIdPasoProceso(idPasoProceso);
        this.setNoPasoProceso(noPasoProceso);
        this.setIdFaseProceso(idFaseProceso);
    }

    /**
     * Permite enviar las porpiedades al repositorio
     
     * @see pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository#listarPasosPorIdProceso()
     * @param idPasoProceso
     * @param noPasoProceso
     * @param idFaseProceso
     */
    public PgimPasoProcesoDTOResultado(Long idPasoProceso, String noPasoProceso, Long idFaseProceso, String coPasoProceso, String dePasoProceso) {
        super();
        this.setIdPasoProceso(idPasoProceso);
        this.setNoPasoProceso(noPasoProceso);
        this.setIdFaseProceso(idFaseProceso);
        this.setCoPasoProceso(coPasoProceso);
        this.setDePasoProceso(dePasoProceso);
    }

    /**@see pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository#obtenerpgimPasoProcesoDTOetiquetar()
     * 
     * @param idInstanciaPaso
     * @param idPasoProceso
     * @param noPasoProceso
     * @param idFaseProceso
     * @param flEtiquetarNotificacion
     */
    public PgimPasoProcesoDTOResultado(Long idInstanciaPaso, Long idPasoProceso, String noPasoProceso, Long idFaseProceso, String flEtiquetarNotificacion){
        super();

        this.setDescInstanciaPaso(idInstanciaPaso);
        this.setIdPasoProceso(idPasoProceso);
        this.setNoPasoProceso(noPasoProceso);
        this.setIdFaseProceso(idFaseProceso);
        this.setFlEtiquetarNotificacion(flEtiquetarNotificacion);        
    }
    
    /**
     * Permite enviar las porpiedades al repositorio
     
     * @see pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository#obtenerPasosPorFase()
     * @param idPasoProceso
     * @param noPasoProceso
     * @param idFaseProceso
     * @param noFaseProceso
     */
    public PgimPasoProcesoDTOResultado(Long idPasoProceso, String noPasoProceso, Long idFaseProceso, Long idProceso, String noFaseProceso) {
        super();
        this.setIdPasoProceso(idPasoProceso);
        this.setNoPasoProceso(noPasoProceso);
        this.setIdFaseProceso(idFaseProceso);
        this.setDescIdProceso(idProceso);
        this.setDescNoFase(noFaseProceso);
    }
}