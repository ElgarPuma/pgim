package pe.gob.osinergmin.pgim.dtos;

public class PgimPlanSupervisionDTOResultado extends PgimPlanSupervisionDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PlanSupervisionRepository #listarPlanSupervision
     */
    public PgimPlanSupervisionDTOResultado(Long idPlanSupervision, Long nuAnio, String dePlanSupervision) {
        this.setIdPlanSupervision(idPlanSupervision);
        this.setNuAnio(nuAnio);
        this.setDePlanSupervision(dePlanSupervision);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PlanSupervisionRepository #filtrarPorAnio
     * @see pe.gob.osinergmin.pgim.models.repository.PlanSupervisionRepository #existeNuAnio
     */
    public PgimPlanSupervisionDTOResultado(Long idPlanSupervision, Long nuAnio) {
        this.setIdPlanSupervision(idPlanSupervision);
        this.setNuAnio(nuAnio);
    }
}
