package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimContratoSiafAuxDTOResultado extends PgimContratoSiafAuxDTO {

    public PgimContratoSiafAuxDTOResultado(Long idContratoSiafAux, Long idContratoSiaf, Long idContrato, String nuSiaf,
            Long nuAnio, BigDecimal moPresupuestoSiaf, BigDecimal moConsumoContrato, BigDecimal descSaldo) {
        super();
        this.setIdContratoSiafAux(idContratoSiafAux);
        this.setIdContratoSiaf(idContratoSiaf);
        this.setIdContrato(idContrato);
        this.setNuSiaf(nuSiaf);
        this.setNuAnio(nuAnio);
        this.setMoPresupuestoSiaf(moPresupuestoSiaf);
        this.setMoConsumoContrato(moConsumoContrato);
        this.setDescSaldo(descSaldo);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoSiafAuxRepository #listarReporteEjecucionPresupuestal()
     * @param idContratoSiafAux
     * @param idContratoSiaf
     * @param idContrato
     * @param nuSiaf
     * @param nuAnio
     * @param moPresupuestoSiaf
     * @param moConsumoContrato
     * @param descSaldo
     * @param descNuContrato
     * @param descNoRazonSocial
     */
    public PgimContratoSiafAuxDTOResultado(Long idContratoSiafAux, Long idContratoSiaf, Long idContrato, String nuSiaf,
            Long nuAnio, BigDecimal moPresupuestoSiaf, BigDecimal moConsumoContrato, BigDecimal descSaldo, 
            String descNuContrato, String descNoRazonSocial) {
        super();
        this.setIdContratoSiafAux(idContratoSiafAux);
        this.setIdContratoSiaf(idContratoSiaf);
        this.setIdContrato(idContrato);
        this.setNuSiaf(nuSiaf);
        this.setNuAnio(nuAnio);
        this.setMoPresupuestoSiaf(moPresupuestoSiaf);
        this.setMoConsumoContrato(moConsumoContrato);
        this.setDescSaldo(descSaldo);
        this.setDescNuContrato(descNuContrato);
        this.setDescNoRazonSocial(descNoRazonSocial);
    }
    
}
