package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimEntregableLiquidaAuxDTOResultado extends PgimEntregableLiquidaAuxDTO {

    /**
     * 
     * @param idEntregableLiquidaAux
     * @param idItemConsumo
     * @param idTipoEntregable
     * @param deTipoEntregable
     * @param idSupervision
     * @param coSupervision
     * @param deTipoSupervision
     * @param idSubtipoSupervision
     * @param deSubtipoSupervision
     * @param idMotivoSupervision
     * @param deMotivoSupervision
     * @param feInicioSupervisionReal
     * @param feFinSupervisionReal
     * @param idAgenteSupervisado
     * @param coDocumentoIdentidad
     * @param noRazonSocial
     * @param moConsumoContrato
     * @param moItemConsumo
     * @param idDocumento
     * @param deAsuntoDocumento
     * @param idSubcatDocumento
     * @param coSubcatDocumento
     * @param noSubcatDocumento
     * @param deMensaje
     * @param feOrigenDocumento
     * @param feInstanciaPaso
     * @param idContrato
     * @param nuContrato
     * @param idDivisionSupervisora
     * @param deDivisionSupervisora
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param nuExpedienteSiged
	 * @param idTipoEstadioConsumo
	 * @param deTipoEstadioConsumo
	 * @param fePresentaEntregable
	 */
    public PgimEntregableLiquidaAuxDTOResultado(Long idEntregableLiquidaAux, Long idItemConsumo, Long idTipoEntregable,
            String deTipoEntregable, Long idSupervision, String coSupervision, String deTipoSupervision,
            Long idSubtipoSupervision, String deSubtipoSupervision, Long idMotivoSupervision,
            String deMotivoSupervision, Date feInicioSupervisionReal, Date feFinSupervisionReal,
            Long idAgenteSupervisado, String coDocumentoIdentidad, String noRazonSocial, BigDecimal moConsumoContrato,
            BigDecimal moItemConsumo, Long idDocumento, String deAsuntoDocumento, Long idSubcatDocumento,
            String coSubcatDocumento, String noSubcatDocumento, String deMensaje, Date feOrigenDocumento,
            Date feInstanciaPaso, Long idContrato, String nuContrato, Long idDivisionSupervisora,
            String deDivisionSupervisora, String coUnidadMinera, String noUnidadMinera, String nuExpedienteSiged,
            Long idTipoEstadioConsumo, String deTipoEstadioConsumo, Date fePresentaEntregable) {
        super();

        this.setIdEntregableLiquidaAux(idEntregableLiquidaAux);
        this.setIdItemConsumo(idItemConsumo);
        this.setIdTipoEntregable(idTipoEntregable);
        this.setDeTipoEntregable(deTipoEntregable);
        this.setIdSupervision(idSupervision);
        this.setCoSupervision(coSupervision);
        this.setDeTipoSupervision(deTipoSupervision);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDeSubtipoSupervision(deSubtipoSupervision);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setDeMotivoSupervision(deMotivoSupervision);
        this.setFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setFeFinSupervisionReal(feFinSupervisionReal);
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoRazonSocial(noRazonSocial);
        this.setMoConsumoContrato(moConsumoContrato);
        this.setMoItemConsumo(moItemConsumo);
        this.setIdDocumento(idDocumento);
        this.setDeAsuntoDocumento(deAsuntoDocumento);
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setCoSubcatDocumento(coSubcatDocumento);
        this.setNoSubcatDocumento(noSubcatDocumento);
        this.setDeMensaje(deMensaje);
        this.setFeOrigenDocumento(feOrigenDocumento);
        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setIdContrato(idContrato);
        this.setNuContrato(nuContrato);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setDeDivisionSupervisora(deDivisionSupervisora);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdTipoEstadioConsumo(idTipoEstadioConsumo);
        this.setDeTipoEstadioConsumo(deTipoEstadioConsumo);
        this.setFePresentaEntregable(fePresentaEntregable);
    }

}
