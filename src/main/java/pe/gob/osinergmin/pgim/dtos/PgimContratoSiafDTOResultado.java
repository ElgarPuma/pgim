package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimContratoSiafDTOResultado extends PgimContratoSiafDTO{
    
    public PgimContratoSiafDTOResultado (Long idContratoSiaf, Long idContrato, String nuSiaf, Long nuAnio, BigDecimal moPresupuestoSiaf) {
        super();
        this.setIdContratoSiaf(idContratoSiaf);
        this.setIdContrato(idContrato);
        this.setNuSiaf(nuSiaf);
        this.setNuAnio(nuAnio);
        this.setMoPresupuestoSiaf(moPresupuestoSiaf);
    }
}
