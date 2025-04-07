package pe.gob.osinergmin.pgim.dtos;

public class PgimSupernpDsEspAuxDTOResultado extends PgimSupernpDsEspAuxDTO {
	
	public PgimSupernpDsEspAuxDTOResultado(String noDivisionSupervisora, String noEspecialidad, Long supA1, Long supA2, 
			Long supA3, Long supA4, Long supA5, Long supA6, Long totalSupervision){
		super();
		
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setNoEspecialidad(noEspecialidad);
		this.setSupA1(supA1);
		this.setSupA2(supA2);
		this.setSupA3(supA3);
		this.setSupA4(supA4);
		this.setSupA5(supA5);
		this.setSupA6(supA6);
		this.setTotalSupervision(totalSupervision);
		
	}
}
