package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimSupervisionMotivoDTOResultado extends PgimSupervisionMotivoDTO {

    /**
     * @param idSupervisionMotivo
     * @param idSupervision
     * @param idObjetoMotivoInicio
     * @param descNoTipoEvento
     */
    public PgimSupervisionMotivoDTOResultado(Long idSupervisionMotivo, Long idSupervision, Long idObjetoMotivoInicio,
            String descCoEvento, String descNoTipoEvento, Date descFeEvento, String descDeEvento) {
        super();
        this.setIdSupervisionMotivo(idSupervisionMotivo);
        this.setIdSupervision(idSupervision);
        this.setIdObjetoMotivoInicio(idObjetoMotivoInicio);
        this.setDescCoEvento(descCoEvento);
        this.setDescNoTipoEvento(descNoTipoEvento);
        this.setDescFeEvento(descFeEvento);
        this.setDescDeEvento(descDeEvento);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionMotivoRepository#obtenerSupervisionMotivo
     * @param idSupervisionMotivo
     * @param idSupervision
     * @param idObjetoMotivoInicio
     * @param descNoTipoEvento
     */
    public PgimSupervisionMotivoDTOResultado(Long idSupervisionMotivo, Long idSupervision, Long idObjetoMotivoInicio
            ) {
        super();
        this.setIdSupervisionMotivo(idSupervisionMotivo);
        this.setIdSupervision(idSupervision);
        this.setIdObjetoMotivoInicio(idObjetoMotivoInicio);
    }

    /**
     * @param idSupervisionMotivo
     * @param idSupervision
     * @param idObjetoMotivoInicio
     * @param descNoTipoEvento
     */
    public PgimSupervisionMotivoDTOResultado(Long idObjetoMotivoInicio, String descCoEvento,
            String descNoTipoEvento, Date descFeEvento) {
        super();
        this.setIdObjetoMotivoInicio(idObjetoMotivoInicio);
        this.setDescCoEvento(descCoEvento);
        this.setDescNoTipoEvento(descNoTipoEvento);
        this.setDescFeEvento(descFeEvento);
    }
}
