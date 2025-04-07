package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimPresupuestoDsEspAuxDTOResultado extends PgimPresupuestoDsEspAuxDTO {

	public PgimPresupuestoDsEspAuxDTOResultado(String noDivisionSupervisora, String noEspecialidad, BigDecimal montoP1, BigDecimal montoP2, 
			BigDecimal montoP3, BigDecimal montoP4, BigDecimal montoP5, BigDecimal montoP6, BigDecimal totalMonto){
		super();
		
		this.setNoDivisionSupervisora(noDivisionSupervisora);
		this.setNoEspecialidad(noEspecialidad);
		this.setMontoP1(montoP1);
		this.setMontoP2(montoP2);
		this.setMontoP3(montoP3);
		this.setMontoP4(montoP4);
		this.setMontoP5(montoP5);
		this.setMontoP6(montoP6);
		this.setTotalMonto(totalMonto);
		
	}
}
