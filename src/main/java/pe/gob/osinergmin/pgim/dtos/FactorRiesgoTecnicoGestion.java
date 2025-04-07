package pe.gob.osinergmin.pgim.dtos;

import java.util.List;

public class FactorRiesgoTecnicoGestion {

	private PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO;
	private List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgoDTO;
	
	public PgimCfgFactorRiesgoDTO getPgimCfgFactorRiesgoDTO() {
		return pgimCfgFactorRiesgoDTO;
	}
	public void setPgimCfgFactorRiesgoDTO(PgimCfgFactorRiesgoDTO pgimCfgFactorRiesgoDTO) {
		this.pgimCfgFactorRiesgoDTO = pgimCfgFactorRiesgoDTO;
	}
	public List<PgimCfgNivelRiesgoDTO> getlPgimCfgNivelRiesgoDTO() {
		return lPgimCfgNivelRiesgoDTO;
	}
	public void setlPgimCfgNivelRiesgoDTO(List<PgimCfgNivelRiesgoDTO> lPgimCfgNivelRiesgoDTO) {
		this.lPgimCfgNivelRiesgoDTO = lPgimCfgNivelRiesgoDTO;
	}
	
	
	
}
