package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeguimientoProgramaDTOResultado extends SeguimientoProgramaDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSeguimientoAuxRepository#obtenerCostosRealesPrograma()
	 *  
	 */
    public SeguimientoProgramaDTOResultado(BigDecimal realCostoTotal,BigDecimal realCostoNoProgramadas,BigDecimal realCostoProgramadas) {
        super();

        this.setRealCostoTotal(realCostoTotal);
        this.setRealCostoNoProgramadas(realCostoNoProgramadas);
        this.setRealCostoProgramadas(realCostoProgramadas);

    }

}
