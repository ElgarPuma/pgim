package pe.gob.osinergmin.pgim.dtos;

import java.util.List;

public class Ranking {

	Long idRankingUmGrupo;
	
	public Long getIdRankingUmGrupo() {
		return idRankingUmGrupo;
	}

	public void setIdRankingUmGrupo(Long idRankingUmGrupo) {
		this.idRankingUmGrupo = idRankingUmGrupo;
	}

	private List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO;

	public List<PgimRankingUmFactorDTO> getlPgimRankingUmFactorDTO() {
		return lPgimRankingUmFactorDTO;
	}

	public void setlPgimRankingUmFactorDTO(List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO) {
		this.lPgimRankingUmFactorDTO = lPgimRankingUmFactorDTO;
	}

	
}
