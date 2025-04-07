package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimInstanPasoAuxDTOResultado extends PgimInstanPasoAuxDTO {

    /**
     * Permite portar los datos necesarios del paso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository#obtenerInstanciaPasoAuxPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository#obtenerInstanciaPasoAuxPorInstanciaProceso()
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository#obtenerInstanciaPasoAuxPorInstanciaProcesoList()
     * 
     * @param idInstanciaPasoAux
     * @param idInstanciaPaso
     * @param idInstanciaProceso
     * @param idRelacionPaso
     * @param idPersonaEqpOrigen
     * @param idPersonaEqpDestino
     * @param feInstanciaPaso
     * @param deMensaje
     * @param flIniciaProceso
     * @param flRequiereDestinatario
     * @param flRequiereAprobacion
     * @param idTipoRelacion
     * @param desNoTipoRelacion
     * @param idPasoProcesoOrigen
     * @param noPasoProcesoOrigen
     * @param dePasoProcesoOrigen
     * @param idPasoProcesoDestino
     * @param noPasoProcesoDestino
     * @param dePasoProcesoDestino
     * @param idFaseProcesoOrigen
     * @param noFaseProcesoOrigen
     * @param deFaseProcesoOrigen
     * @param idFaseProcesoDestino
     * @param noFaseProcesoDestino
     * @param deFaseProcesoDestino
     * @param idPersonaOrigen
     * @param noUsuarioOrigen
     * @param coUsuarioSigedOrigen
     * @param noPersonaOrigen
     * @param apPaternoOrigen
     * @param apMaternoOrigen
     * @param idPersonaDestino
     * @param noUsuarioDestino
     * @param coUsuarioSigedDestino
     * @param noPersonaDestino
     * @param apPaternoDestino
     * @param apMaternoDestino
     * @param idRolProcesoOrigen
     * @param noRolProcesoOrigen
     * @param deRolProcesoOrigen
     * @param idRolProcesoDestino
     * @param noRolProcesoDestino
     * @param deRolProcesoDestino
     * @param deEntidadPersonaOrigen
     * @param deEntidadPersonaDestino
     * @param descNoCompletoPersonaOrigen
     * @param descNoCompletoPersonaDestino
     * @param idTipoAccionSiged
     * @param deTipoAccionSiged
     * @param flNotificableE
     * @param noProceso
     * @param noEtiquetaOtrabajo
     * @param flEsPasoActivo
     * @param idTipoSubflujo
     */
    public PgimInstanPasoAuxDTOResultado(Long idInstanciaPasoAux, Long idInstanciaPaso, Long idInstanciaProceso,
            Long idRelacionPaso, Long idPersonaEqpOrigen, Long idPersonaEqpDestino, 
            Date feInstanciaPaso, String deMensaje, String flIniciaProceso, 
            String flRequiereDestinatario, String flRequiereAprobacion, Long idTipoRelacion, 
            String desNoTipoRelacion, Long idPasoProcesoOrigen, String noPasoProcesoOrigen, 
            String dePasoProcesoOrigen, Long idPasoProcesoDestino, String noPasoProcesoDestino, 
            String dePasoProcesoDestino, Long idFaseProcesoOrigen, String noFaseProcesoOrigen, 
            String deFaseProcesoOrigen, Long idFaseProcesoDestino, String noFaseProcesoDestino, 
            String deFaseProcesoDestino, Long idPersonaOrigen, String noUsuarioOrigen, 
            Long coUsuarioSigedOrigen, String noPersonaOrigen, String apPaternoOrigen, 
            String apMaternoOrigen, Long idPersonaDestino, String noUsuarioDestino, 
            Long coUsuarioSigedDestino, String noPersonaDestino, String apPaternoDestino, 
            String apMaternoDestino, Long idRolProcesoOrigen, String noRolProcesoOrigen, 
            String deRolProcesoOrigen, Long idRolProcesoDestino, String noRolProcesoDestino, 
            String deRolProcesoDestino, String deEntidadPersonaOrigen, String deEntidadPersonaDestino,
            String descNoCompletoPersonaOrigen, String descNoCompletoPersonaDestino, Long  idTipoAccionSiged, 
            String deTipoAccionSiged, String flNotificableE, String noProceso, 
            String noEtiquetaOtrabajo, String flEsPasoActivo, Long idTipoSubflujo) {
        super();

        this.setIdInstanciaPasoAux(idInstanciaPasoAux);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdPersonaEqpOrigen(idPersonaEqpOrigen);
        this.setIdPersonaEqpDestino(idPersonaEqpDestino);
        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setDeMensaje(deMensaje);
        this.setFlIniciaProceso(flIniciaProceso);
        this.setFlRequiereDestinatario(flRequiereDestinatario);
        this.setFlRequiereAprobacion(flRequiereAprobacion);
        this.setIdTipoRelacion(idTipoRelacion);
        this.setDesNoTipoRelacion(desNoTipoRelacion);
        this.setIdPasoProcesoOrigen(idPasoProcesoOrigen);
        this.setNoPasoProcesoOrigen(noPasoProcesoOrigen);
        this.setDePasoProcesoOrigen(dePasoProcesoOrigen);
        this.setIdPasoProcesoDestino(idPasoProcesoDestino);
        this.setNoPasoProcesoDestino(noPasoProcesoDestino);
        this.setDePasoProcesoDestino(dePasoProcesoDestino);
        this.setIdFaseProcesoOrigen(idFaseProcesoOrigen);
        this.setNoFaseProcesoOrigen(noFaseProcesoOrigen);
        this.setDeFaseProcesoOrigen(deFaseProcesoOrigen);
        this.setIdFaseProcesoDestino(idFaseProcesoDestino);
        this.setNoFaseProcesoDestino(noFaseProcesoDestino);
        this.setDeFaseProcesoDestino(deFaseProcesoDestino);
        this.setIdPersonaOrigen(idPersonaOrigen);
        this.setNoUsuarioOrigen(noUsuarioOrigen);
        this.setCoUsuarioSigedOrigen(coUsuarioSigedOrigen);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setApPaternoOrigen(apPaternoOrigen);
        this.setApMaternoOrigen(apMaternoOrigen);
        this.setIdPersonaDestino(idPersonaDestino);
        this.setNoUsuarioDestino(noUsuarioDestino);
        this.setCoUsuarioSigedDestino(coUsuarioSigedDestino);
        this.setNoPersonaDestino(noPersonaDestino);
        this.setApPaternoDestino(apPaternoDestino);
        this.setApMaternoDestino(apMaternoDestino);
        this.setIdRolProcesoOrigen(idRolProcesoOrigen);
        this.setNoRolProcesoOrigen(noRolProcesoOrigen);
        this.setDeRolProcesoOrigen(deRolProcesoOrigen);
        this.setIdRolProcesoDestino(idRolProcesoDestino);
        this.setNoRolProcesoDestino(noRolProcesoDestino);
        this.setDeRolProcesoDestino(deRolProcesoDestino);
        this.setDeEntidadPersonaOrigen(deEntidadPersonaOrigen);
        this.setDeEntidadPersonaDestino(deEntidadPersonaDestino);
        this.setDescNoCompletoPersonaOrigen(descNoCompletoPersonaOrigen);
        this.setDescNoCompletoPersonaDestino(descNoCompletoPersonaDestino);        
        this.setIdTipoAccionSiged(idTipoAccionSiged);
        this.setDeTipoAccionSiged(deTipoAccionSiged);
        this.setFlNotificableE(flNotificableE);
        this.setNoProceso(noProceso);
        this.setNoEtiquetaOtrabajo(noEtiquetaOtrabajo);

        this.setFlEsPasoActivo(flEsPasoActivo);
        this.setIdTipoSubflujo(idTipoSubflujo);
    }

    /**
     * Permite portar los datos necesarios de la persona destino y responsable uso
     * el metodo:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository#listarPorPersonaDestino()
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository#listarPorPersonaResponsable()
     * @param idPersonaDestino
     * @param descNoCompletoPersonaDestino
     */
    public PgimInstanPasoAuxDTOResultado(Long idPersonaDestino, String descNoCompletoPersonaDestino) {
        super();

        this.setIdPersonaDestino(idPersonaDestino);
        this.setDescNoCompletoPersonaDestino(descNoCompletoPersonaDestino);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository#listarPorPersonaOsiDestino()
     * @param idPersonaDestino
     * @param descIdPersonalOsi
     * @param descNoCompletoPersonaDestino
     */
    public PgimInstanPasoAuxDTOResultado(Long idPersonaDestino, Long descIdPersonalOsi, String descNoCompletoPersonaDestino) {
        super();

        this.setIdPersonaDestino(idPersonaDestino);
        this.setDescIdPersonalOsi(descIdPersonalOsi);
        this.setDescNoCompletoPersonaDestino(descNoCompletoPersonaDestino);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository#listarPorPersonaContratoDestino()
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository#listarPersonaDestinoPorContrato()
     * @param idPersonaDestino
     * @param descIdPersonalContrato
     * @param descNuContrato
     * @param descNoCompletoPersonaDestino
     */
    public PgimInstanPasoAuxDTOResultado(Long idPersonaDestino, Long descIdPersonalContrato, String descNuContrato, String descNoCompletoPersonaDestino) {
        super();

        this.setIdPersonaDestino(idPersonaDestino);
        this.setDescIdPersonalContrato(descIdPersonalContrato);
        this.setDescNuContrato(descNuContrato);
        this.setDescNoCompletoPersonaDestino(descNoCompletoPersonaDestino);
    }

}
