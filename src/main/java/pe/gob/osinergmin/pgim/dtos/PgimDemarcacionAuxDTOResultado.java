package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimDemarcacionAuxDTOResultado extends PgimDemarcacionAuxDTO{
	
	public PgimDemarcacionAuxDTOResultado (
			Long idDemarcacionUmAux,
			Long idDemarcacionUm,
			Long idAgenteSupervisado,
			String coDocumentoIdentidadAf,
			String noRazonSocialAf,
			Long idUnidadMinera,
			String coUnidadMinera,
			String noUnidadMinera,
			Long idTipoUnidadMinera,
			String noTipoUnidadMinera,
			Long idDivisionSupervisora,
			String noDivisionSupervisora,
			Long idDepartamentoDemarcacion,
			Long idProvinciaDemarcacion,
			Long idDistritoDemarcacion,
			String noDepartamentoDemarcacion,
			String noProvinciaDemarcacion,
			String noDistritoDemarcacion,
			String flPrincipalDemarcacion,
			String principalDemarcacion,
			BigDecimal pcDemarcacion,
			String descRazonSocial,
			String descUnidadMinera,
			String descUbigeo) {
		
		super();
		
		this.setIdDemarcacionUmAux(idDemarcacionUmAux);
		this.setIdDemarcacionUm(idDemarcacionUm);
		this.setIdAgenteSupervisado(idAgenteSupervisado);
		this.setCoDocumentoIdentidadAf(coDocumentoIdentidadAf);
		this.setNoRazonSocialAf(noRazonSocialAf);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setCoUnidadMinera(coUnidadMinera);
		this.setNoUnidadMinera(noUnidadMinera);
		this.setIdTipoUnidadMinera(idTipoUnidadMinera);
		this.setNoTipoUnidadMinera(noTipoUnidadMinera);
		this.setIdDivisionSupervisora(idDivisionSupervisora);
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setIdDepartamentoDemarcacion(idDepartamentoDemarcacion);
		this.setIdProvinciaDemarcacion(idProvinciaDemarcacion);
		this.setIdDistritoDemarcacion(idDistritoDemarcacion);
		this.setNoDepartamentoDemarcacion(noDepartamentoDemarcacion);
		this.setNoProvinciaDemarcacion(noProvinciaDemarcacion);
		this.setNoDistritoDemarcacion(noDistritoDemarcacion);
		this.setFlPrincipalDemarcacion(flPrincipalDemarcacion);
		this.setPrincipalDemarcacion(principalDemarcacion);
		this.setPcDemarcacion(pcDemarcacion);
		this.setDescRazonSocial(descRazonSocial);
		this.setDescUnidadMinera(descUnidadMinera);
		this.setDescUbigeo(descUbigeo);
	}
	
}
