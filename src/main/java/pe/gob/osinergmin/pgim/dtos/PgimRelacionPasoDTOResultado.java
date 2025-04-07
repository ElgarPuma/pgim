package pe.gob.osinergmin.pgim.dtos;

public class PgimRelacionPasoDTOResultado extends PgimRelacionPasoDTO {

    /**
     * Sirve para obtener la relación de una relación dada. En el repositorio usa el
     * método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository#obtenerRelacionPasoInicial()
     * 
     * @param idRelacionPaso
     * @param idPasoProcesoOrigen
     * @param idPasoProcesoDestino
     * @param flRequiereDestinatario
     * @param flRequiereAprobacion
     * @param descNoPasoProcesoOrigen
     * @param descDePasoProcesoOrigen
     * @param descNoPasoProcesoDestino
     * @param descDePasoProcesoDestino
     * @param descIdRolProcesoOrigen
     * @param descNoRolProcesoOrigen
     * @param descIdRolProcesoDestino
     * @param descNoRolProcesoDestino
     * @param idTipoRelacion
     * @param descNoTipoRelacion
     * @param descTareaFlAgrupadoraDestino
     */
    public PgimRelacionPasoDTOResultado(Long idRelacionPaso, Long idPasoProcesoOrigen, Long idPasoProcesoDestino,
            String flRequiereDestinatario, String flRequiereAprobacion, String descNoPasoProcesoOrigen,
            String descDePasoProcesoOrigen, String descNoPasoProcesoDestino, String descDePasoProcesoDestino,
            Long descIdRolProcesoOrigen, String descNoRolProcesoOrigen, Long descIdRolProcesoDestino,
            String descNoRolProcesoDestino, Long idTipoRelacion, String descNoTipoRelacion) {
        super();

        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdPasoProcesoOrigen(idPasoProcesoOrigen);
        this.setIdPasoProcesoDestino(idPasoProcesoDestino);
        this.setFlRequiereDestinatario(flRequiereDestinatario);
        this.setFlRequiereAprobacion(flRequiereAprobacion);

        this.setDescNoPasoProcesoOrigen(descNoPasoProcesoOrigen);
        this.setDescDePasoProcesoOrigen(descDePasoProcesoOrigen);

        this.setDescNoPasoProcesoDestino(descNoPasoProcesoDestino);
        this.setDescDePasoProcesoDestino(descDePasoProcesoDestino);

        this.setDescIdRolProcesoOrigen(descIdRolProcesoOrigen);
        this.setDescNoRolProcesoOrigen(descNoRolProcesoOrigen);

        this.setDescIdRolProcesoDestino(descIdRolProcesoDestino);
        this.setDescNoRolProcesoDestino(descNoRolProcesoDestino);

        this.setIdTipoRelacion(idTipoRelacion);
        this.setDescNoTipoRelacion(descNoTipoRelacion);
    }
    
    
    /**
     * Sirve para obtener la relación de una relación dada. En el repositorio usa el
     * método:
     *  
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository#listarRelacionPasoPorIdProceso() 
     * 
     * @param idRelacionPaso
     * @param idPasoProcesoOrigen
     * @param idPasoProcesoDestino
     * @param flRequiereDestinatario
     * @param flRequiereAprobacion
     * @param descNoPasoProcesoOrigen
     * @param descDePasoProcesoOrigen
     * @param descNoPasoProcesoDestino
     * @param descDePasoProcesoDestino
     * @param descIdRolProcesoOrigen
     * @param descNoRolProcesoOrigen
     * @param descIdRolProcesoDestino
     * @param descNoRolProcesoDestino
     * @param idTipoRelacion
     * @param descNoTipoRelacion
     * @param idTipoAccionSiged
     * @param descTipoAccionSiged
     * @param descTareaFlAgrupadoraDestino
     */
    public PgimRelacionPasoDTOResultado(Long idRelacionPaso, Long idPasoProcesoOrigen, Long idPasoProcesoDestino,
            String flRequiereDestinatario, String flRequiereAprobacion, String descNoPasoProcesoOrigen,
            String descDePasoProcesoOrigen, String descNoPasoProcesoDestino, String descDePasoProcesoDestino,
            Long descIdRolProcesoOrigen, String descNoRolProcesoOrigen, Long descIdRolProcesoDestino,
            String descNoRolProcesoDestino, Long idTipoRelacion, String descNoTipoRelacion,
            Long idTipoAccionSiged, String descTipoAccionSiged, String descTareaFlAgrupadoraDestino
            ) {
        super();

        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdPasoProcesoOrigen(idPasoProcesoOrigen);
        this.setIdPasoProcesoDestino(idPasoProcesoDestino);
        this.setFlRequiereDestinatario(flRequiereDestinatario);
        this.setFlRequiereAprobacion(flRequiereAprobacion);

        this.setDescNoPasoProcesoOrigen(descNoPasoProcesoOrigen);
        this.setDescDePasoProcesoOrigen(descDePasoProcesoOrigen);

        this.setDescNoPasoProcesoDestino(descNoPasoProcesoDestino);
        this.setDescDePasoProcesoDestino(descDePasoProcesoDestino);

        this.setDescIdRolProcesoOrigen(descIdRolProcesoOrigen);
        this.setDescNoRolProcesoOrigen(descNoRolProcesoOrigen);

        this.setDescIdRolProcesoDestino(descIdRolProcesoDestino);
        this.setDescNoRolProcesoDestino(descNoRolProcesoDestino);

        this.setIdTipoRelacion(idTipoRelacion);
        this.setDescNoTipoRelacion(descNoTipoRelacion);
        
        this.setIdTipoAccionSiged(idTipoAccionSiged);
        this.setDescTipoAccionSiged(descTipoAccionSiged);

        this.setDescTareaFlAgrupadoraDestino(descTareaFlAgrupadoraDestino);
    }

    /**
     * Sirve para obtener la relación de una relación dada. En el repositorio usa el
     * método:
     *  
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository#listarRelacionPasoPorIdProceso() 
     */
    public PgimRelacionPasoDTOResultado(Long idRelacionPaso, Long idPasoProcesoOrigen, Long idPasoProcesoDestino,
            String flRequiereDestinatario, String flRequiereAprobacion, String descNoPasoProcesoOrigen,
            String descDePasoProcesoOrigen, String descNoPasoProcesoDestino, String descDePasoProcesoDestino,
            Long descIdRolProcesoOrigen, String descNoRolProcesoOrigen, Long descIdRolProcesoDestino,
            String descNoRolProcesoDestino, Long idTipoRelacion, String descNoTipoRelacion,
            Long idTipoAccionSiged, String descTipoAccionSiged, String descTareaFlAgrupadoraDestino, String descNoFaseProcesoDestino
            ) {
        super();

        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdPasoProcesoOrigen(idPasoProcesoOrigen);
        this.setIdPasoProcesoDestino(idPasoProcesoDestino);
        this.setFlRequiereDestinatario(flRequiereDestinatario);
        this.setFlRequiereAprobacion(flRequiereAprobacion);

        this.setDescNoPasoProcesoOrigen(descNoPasoProcesoOrigen);
        this.setDescDePasoProcesoOrigen(descDePasoProcesoOrigen);

        this.setDescNoPasoProcesoDestino(descNoPasoProcesoDestino);
        this.setDescDePasoProcesoDestino(descDePasoProcesoDestino);

        this.setDescIdRolProcesoOrigen(descIdRolProcesoOrigen);
        this.setDescNoRolProcesoOrigen(descNoRolProcesoOrigen);

        this.setDescIdRolProcesoDestino(descIdRolProcesoDestino);
        this.setDescNoRolProcesoDestino(descNoRolProcesoDestino);

        this.setIdTipoRelacion(idTipoRelacion);
        this.setDescNoTipoRelacion(descNoTipoRelacion);
        
        this.setIdTipoAccionSiged(idTipoAccionSiged);
        this.setDescTipoAccionSiged(descTipoAccionSiged);

        this.setDescTareaFlAgrupadoraDestino(descTareaFlAgrupadoraDestino);
        this.setDescNoFaseProcesoDestino(descNoFaseProcesoDestino);
    }

    public PgimRelacionPasoDTOResultado(Long idRelacionPaso, Long idPasoProcesoOrigen, Long idPasoProcesoDestino,
            String flRequiereDestinatario, String flRequiereAprobacion, String descNoPasoProcesoOrigen,
            String descDePasoProcesoOrigen, String descNoPasoProcesoDestino, String descDePasoProcesoDestino,
            Long descIdRolProcesoOrigen, String descNoRolProcesoOrigen, Long descIdRolProcesoDestino,
            String descNoRolProcesoDestino, Long idTipoRelacion, String descNoTipoRelacion,
            Long idTipoAccionSiged, String descTipoAccionSiged, String descTareaFlAgrupadoraDestino,
            Long descIdProcesoOrigen, Long descIdFaseProcesoOrigen, String descNoFaseProcesoOrigen, 
            Long descIdProcesoDestino, Long descIdFaseProcesoDestino, String descNoFaseProcesoDestino
            ) {
        super();

        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdPasoProcesoOrigen(idPasoProcesoOrigen);
        this.setIdPasoProcesoDestino(idPasoProcesoDestino);
        this.setFlRequiereDestinatario(flRequiereDestinatario);
        this.setFlRequiereAprobacion(flRequiereAprobacion);

        this.setDescNoPasoProcesoOrigen(descNoPasoProcesoOrigen);
        this.setDescDePasoProcesoOrigen(descDePasoProcesoOrigen);

        this.setDescNoPasoProcesoDestino(descNoPasoProcesoDestino);
        this.setDescDePasoProcesoDestino(descDePasoProcesoDestino);

        this.setDescIdRolProcesoOrigen(descIdRolProcesoOrigen);
        this.setDescNoRolProcesoOrigen(descNoRolProcesoOrigen);

        this.setDescIdRolProcesoDestino(descIdRolProcesoDestino);
        this.setDescNoRolProcesoDestino(descNoRolProcesoDestino);

        this.setIdTipoRelacion(idTipoRelacion);
        this.setDescNoTipoRelacion(descNoTipoRelacion);
        
        this.setIdTipoAccionSiged(idTipoAccionSiged);
        this.setDescTipoAccionSiged(descTipoAccionSiged);

        this.setDescTareaFlAgrupadoraDestino(descTareaFlAgrupadoraDestino);

        this.setDescIdProcesoOrigen(descIdProcesoOrigen);
        this.setDescIdFaseProcesoOrigen(descIdFaseProcesoOrigen);
        this.setDescNoFaseProcesoOrigen(descNoFaseProcesoOrigen);
        this.setDescIdProcesoDestino(descIdProcesoDestino);
        this.setDescIdFaseProcesoDestino(descIdFaseProcesoDestino);
        this.setDescNoFaseProcesoDestino(descNoFaseProcesoDestino);

    }

}