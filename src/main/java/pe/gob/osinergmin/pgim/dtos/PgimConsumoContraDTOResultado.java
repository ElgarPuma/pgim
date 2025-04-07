package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimConsumoContraDTOResultado extends PgimConsumoContraDTO {
    
    public PgimConsumoContraDTOResultado(Long idConsumoContra, Long idSupervision, Long idContrato, BigDecimal moConsumoContrato) {
        super();

        this.setIdConsumoContra(idConsumoContra);
        this.setIdSupervision(idSupervision);
        this.setIdContrato(idContrato);
        this.setMoConsumoContrato(moConsumoContrato);
    }
    
    public PgimConsumoContraDTOResultado(Long idConsumoContra, BigDecimal moConsumoContrato) {
        super();

        this.setIdConsumoContra(idConsumoContra);
        this.setMoConsumoContrato(moConsumoContrato);
    }

}
