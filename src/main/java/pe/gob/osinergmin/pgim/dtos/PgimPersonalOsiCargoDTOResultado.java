package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimPersonalOsiCargoDTOResultado extends PgimPersonalOsiCargoDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalOsiCargoRepository#listarPersonalOsiCargo()
     * @param idPersonalOsiCargo
     * @param idPersonalOsi
     * @param idUnidadOrganica
     * @param noCargo
     * @param feInicio
     * @param feFin
     */
    public PgimPersonalOsiCargoDTOResultado(Long idPersonalOsiCargo, Long idPersonalOsi, Long idUnidadOrganica,
            String noCargo, Date feInicio, Date feFin, String flPrincipal, String descCoUnidadOrganica, String descNoUnidadOrganica, String nuExpedienteSiged, Long coTipoDocumentoSiged, String nuDocumentoSiged) {
        super();
        this.setIdPersonalOsiCargo(idPersonalOsiCargo);
        this.setIdPersonalOsi(idPersonalOsi);
        this.setIdUnidadOrganica(idUnidadOrganica);
        this.setNoCargo(noCargo);
        this.setFeInicio(feInicio);
        this.setFeFin(feFin);
        this.setFlPrincipal(flPrincipal);
        this.setDescCoUnidadOrganica(descCoUnidadOrganica);
        this.setDescNoUnidadOrganica(descNoUnidadOrganica);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
        this.setNuDocumentoSiged(nuDocumentoSiged);
    }
}
