package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimInstanciaPasoDTOResultado extends PgimInstanciaPasoDTO {

    /***
     * Usado para obtener el último coSupervision.
     * En el repositorio usa el método:
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository#obtenerInstanciaPasosActuales()
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository#obtenerInstanciaPaso()
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository#obtenerInstanciaPasoPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository#obtenerInstanciaPasosXrelacion()
     * 
     * @param idInstanciaPaso
     * @param idInstanciaProceso
     * @param idRelacionPaso
     * @param idPersonaEqpOrigen
     * @param idPersonaEqpDestino
     * @param feInstanciaPaso
     * @param deMensaje
     * @param idPasoProcesoOrigen
     * @param idTipoSubflujo
     */
    public PgimInstanciaPasoDTOResultado(Long idInstanciaPaso, Long idInstanciaProceso, Long idRelacionPaso,
            Long idPersonaEqpOrigen, Long idPersonaEqpDestino, Date feInstanciaPaso, String deMensaje, 
            Long idPasoProcesoOrigen, Long idPasoProcesoDestino, Long idTipoSubflujo) {
        super();

        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdPersonaEqpOrigen(idPersonaEqpOrigen);
        this.setIdPersonaEqpDestino(idPersonaEqpDestino);
        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setDeMensaje(deMensaje);
        this.setIdPasoProcesoOrigen(idPasoProcesoOrigen);
        this.setIdPasoProcesoDestino(idPasoProcesoDestino);
        this.setIdTipoSubflujo(idTipoSubflujo);
    }

}