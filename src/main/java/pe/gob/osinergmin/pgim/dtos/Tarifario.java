package pe.gob.osinergmin.pgim.dtos;

import java.util.List;

public class Tarifario {

	private PgimTarifarioContratoDTO pgimTarifarioContratoDTO;
	
	private List<PgimCostoUnitarioDTO> lPgimCostoUnitarioDTO;
	
	private List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO;

	public PgimTarifarioContratoDTO getPgimTarifarioContratoDTO() {
		return pgimTarifarioContratoDTO;
	}

	public void setPgimTarifarioContratoDTO(PgimTarifarioContratoDTO pgimTarifarioContratoDTO) {
		this.pgimTarifarioContratoDTO = pgimTarifarioContratoDTO;
	}

	public List<PgimCostoUnitarioDTO> getlPgimCostoUnitarioDTO() {
		return lPgimCostoUnitarioDTO;
	}

	public void setlPgimCostoUnitarioDTO(List<PgimCostoUnitarioDTO> lPgimCostoUnitarioDTO) {
		this.lPgimCostoUnitarioDTO = lPgimCostoUnitarioDTO;
	}

	public List<PgimTarifarioReglaDTO> getlPgimTarifarioReglaDTO() {
		return lPgimTarifarioReglaDTO;
	}

	public void setlPgimTarifarioReglaDTO(List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO) {
		this.lPgimTarifarioReglaDTO = lPgimTarifarioReglaDTO;
	}
	
	
}
