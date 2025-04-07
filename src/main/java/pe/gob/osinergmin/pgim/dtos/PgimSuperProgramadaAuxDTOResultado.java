package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimSuperProgramadaAuxDTOResultado extends PgimSuperProgramadaAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository#listarReporteSuperProgramadaPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository#listarReporteSuperProgramada()
	 * @param noDivisionSupervisora
	 * @param noEspecialidad
	 * @param nroSupPrgP1
	 * @param nroSupPrgP2
	 * @param nroSupPrgP3
	 * @param nroSupPrgP4
	 * @param nroSupPrgP5
	 * @param nroSupPrgP6
	 * @param totalNroSupPrg
	 */
	public PgimSuperProgramadaAuxDTOResultado(String noDivisionSupervisora, String noEspecialidad, Long nroSupPrgP1, Long nroSupPrgP2, 
			Long nroSupPrgP3, Long nroSupPrgP4, Long nroSupPrgP5, Long nroSupPrgP6, Long totalNroSupPrg){
		super();
		
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setNoEspecialidad(noEspecialidad);
		this.setNroSupPrgP1(nroSupPrgP1);
		this.setNroSupPrgP2(nroSupPrgP2);
		this.setNroSupPrgP3(nroSupPrgP3);
		this.setNroSupPrgP4(nroSupPrgP4);
		this.setNroSupPrgP5(nroSupPrgP5);
		this.setNroSupPrgP6(nroSupPrgP6);
		this.setTotalNroSupPrg(totalNroSupPrg);
		
	}
	

}
