package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimLiquidacionAuxDTOResultado extends PgimLiquidacionAuxDTO {

    /**
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionAuxRepository#listarLiquidaciones()
     * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionAuxRepository#obtenerLiquidacionAuxPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionAuxRepository#listarLiquidacionesContrato()
     * 
     * @param idLiquidacionAux
     * @param idLiquidacion
     * @param nuLiquidacion
     * @param idTipoEntregableLiquidacion
     * @param tipoEntregableLiquidacion
     * @param moItemConsumo
     * @param moItemPenalidad
     * @param idContrato
     * @param nuContrato
     * @param deContrato
     * @param idEspecialidad
     * @param noEspecialidad
     * @param idEmpresaSupervisora
     * @param noRazonSocialSupervisora
     * @param nuExpedienteSiged
     * @param personaAsignada
     * @param usuarioAsignado
     * @param idFaseActual
     * @param noFaseActual
     * @param idPasoActual
     * @param noPasoActual
     * @param idInstanciaProceso
     * @param idRelacionPaso
     * @param idTipoRelacion
     * @param tipoRelacionPaso
     * @param feCreacion
     * @param flLeido
     * @param feLectura
     * @param feInstanciaPaso
     * @param noPersonaOrigen
     * @param flPasoActivo
     * @param deMensaje
     * @param idInstanciaPaso
     */
    public PgimLiquidacionAuxDTOResultado(Long idLiquidacionAux, Long idLiquidacion, String nuLiquidacion,
            Long idTipoEntregableLiquidacion, String tipoEntregableLiquidacion, BigDecimal moItemConsumo,
            BigDecimal moItemPenalidad, Long idContrato, String nuContrato, 
            String deContrato, Long idEspecialidad, String noEspecialidad, 
            Long idEmpresaSupervisora, String noRazonSocialSupervisora, String nuExpedienteSiged,
            String personaAsignada, String usuarioAsignado, Long idFaseActual, 
            String noFaseActual, Long idPasoActual, String noPasoActual, 
            Long idInstanciaProceso, Long idRelacionPaso, Long idTipoRelacion,
            String tipoRelacionPaso, Date feCreacion, String flLeido, 
            Date feLectura, Date feInstanciaPaso, String noPersonaOrigen,
            String flPasoActivo, String deMensaje, Long idInstanciaPaso
            ) {
        super();

        this.setIdLiquidacionAux(idLiquidacionAux);
        this.setIdLiquidacion(idLiquidacion);
        this.setNuLiquidacion(nuLiquidacion);
        this.setIdTipoEntregableLiquidacion(idTipoEntregableLiquidacion);
        this.setTipoEntregableLiquidacion(tipoEntregableLiquidacion);
        this.setMoItemConsumo(moItemConsumo);
        this.setMoItemPenalidad(moItemPenalidad);
        this.setIdContrato(idContrato);
        this.setNuContrato(nuContrato);
        this.setDeContrato(deContrato);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setIdEmpresaSupervisora(idEmpresaSupervisora);
        this.setNoRazonSocialSupervisora(noRazonSocialSupervisora);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setPersonaAsignada(personaAsignada);
        this.setUsuarioAsignado(usuarioAsignado);
        this.setIdFaseActual(idFaseActual);
        this.setNoFaseActual(noFaseActual);
        this.setIdPasoActual(idPasoActual);
        this.setNoPasoActual(noPasoActual);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdTipoRelacion(idTipoRelacion);
        this.setTipoRelacionPaso(tipoRelacionPaso);
        this.setFeCreacion(feCreacion);
        this.setFlLeido(flLeido);
        this.setFeLectura(feLectura);

        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setFlPasoActivo(flPasoActivo);
        this.setDeMensaje(deMensaje);
        
        this.setIdInstanciaPaso(idInstanciaPaso);
    }
    
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.LiquidacionAuxRepository#obtenerLiquidacionAuxPorInstanciaProceso()
     *    
     */
    public PgimLiquidacionAuxDTOResultado(Long idLiquidacionAux, Long idLiquidacion, String nuLiquidacion,
            Long idTipoEntregableLiquidacion, String tipoEntregableLiquidacion, BigDecimal moItemConsumo,
            BigDecimal moItemPenalidad, Long idContrato, String nuContrato, String deContrato, Long idEspecialidad,
            String noEspecialidad, Long idEmpresaSupervisora, String noRazonSocialSupervisora, String nuExpedienteSiged,
            String personaAsignada, String usuarioAsignado, Long idFaseActual, String noFaseActual, Long idPasoActual,
            String noPasoActual, Long idInstanciaProceso, Long idRelacionPaso, Long idTipoRelacion,
            String tipoRelacionPaso, Date feCreacion, Long idDivisionSupervisora, String deDivisionSupervisora,
            Long idTipoSupervision, String noTipoSupervision, Long idSubtipoSupervision, String deSubtipoSupervision,
            Long idMotivoSupervision, String deMotivoSupervision, String flPenalidadReemplazoPersona, BigDecimal moPenalidadReemplazoPersona
            ) {
        super();

        this.setIdLiquidacionAux(idLiquidacionAux);
        this.setIdLiquidacion(idLiquidacion);
        this.setNuLiquidacion(nuLiquidacion);
        this.setIdTipoEntregableLiquidacion(idTipoEntregableLiquidacion);
        this.setTipoEntregableLiquidacion(tipoEntregableLiquidacion);
        this.setMoItemConsumo(moItemConsumo);
        this.setMoItemPenalidad(moItemPenalidad);
        this.setIdContrato(idContrato);
        this.setNuContrato(nuContrato);
        this.setDeContrato(deContrato);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setIdEmpresaSupervisora(idEmpresaSupervisora);
        this.setNoRazonSocialSupervisora(noRazonSocialSupervisora);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setPersonaAsignada(personaAsignada);
        this.setUsuarioAsignado(usuarioAsignado);
        this.setIdFaseActual(idFaseActual);
        this.setNoFaseActual(noFaseActual);
        this.setIdPasoActual(idPasoActual);
        this.setNoPasoActual(noPasoActual);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdTipoRelacion(idTipoRelacion);
        this.setTipoRelacionPaso(tipoRelacionPaso);
        this.setFeCreacion(feCreacion);
        
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setDeDivisionSupervisora(deDivisionSupervisora);
        this.setIdTipoSupervision(idTipoSupervision);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDeSubtipoSupervision(deSubtipoSupervision);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setDeMotivoSupervision(deMotivoSupervision);
        this.setDescFlPenalidadReemplazoPersona(flPenalidadReemplazoPersona);
        this.setDescMoPenalidadReemplazoPersona(moPenalidadReemplazoPersona);
    }

}
