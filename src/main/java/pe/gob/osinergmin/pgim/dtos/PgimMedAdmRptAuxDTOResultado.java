package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimMedAdmRptAuxDTOResultado extends PgimMedAdmRptAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.MedAdmRptAuxRepository#listarPgimMedAdmRptAuxPorUnidadMinera()
	 * @param idMedidaAdministrativaAux
	 * @param idMedidaAdministrativa
	 * @param idUnidadMinera
	 * @param idTipoMedidaAdministrativa
	 * @param deTipoMedidaAdministrativa
	 * @param deMedidaAdministrativa
	 * @param feMedidaAdministrativa
	 * @param nuExpedienteSiged
	 * @param descCoMedidaAdministrativa
	 */
	public PgimMedAdmRptAuxDTOResultado(Long idMedidaAdministrativaAux, Long idMedidaAdministrativa, Long idUnidadMinera,
			  Long idTipoMedidaAdministrativa, String deTipoMedidaAdministrativa, String deMedidaAdministrativa,
			  Date feMedidaAdministrativa, String nuExpedienteSiged, String descCoMedidaAdministrativa) {
        super();
        this.setIdMedidaAdministrativaAux(idMedidaAdministrativaAux); 
        this.setIdMedidaAdministrativa(idMedidaAdministrativa); 
        this.setIdUnidadMinera(idUnidadMinera);
        this.setIdTipoMedidaAdministrativa(idTipoMedidaAdministrativa); 
        this.setDeTipoMedidaAdministrativa(deTipoMedidaAdministrativa); 
        this.setDeMedidaAdministrativa(deMedidaAdministrativa);
        this.setFeMedidaAdministrativa(feMedidaAdministrativa); 
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setDescCoMedidaAdministrativa(descCoMedidaAdministrativa);
    }
}