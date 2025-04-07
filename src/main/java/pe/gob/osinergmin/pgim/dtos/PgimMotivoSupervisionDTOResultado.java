package pe.gob.osinergmin.pgim.dtos;

public class PgimMotivoSupervisionDTOResultado extends PgimMotivoSupervisionDTO{

	public PgimMotivoSupervisionDTOResultado(Long idMotivoSupervision, String deMotivoSupervision,
			Long idTipoSupervision) {
        super();
        this.setIdMotivoSupervision(idMotivoSupervision); 
        this.setDeMotivoSupervision(deMotivoSupervision);
        this.setIdTipoSupervision(idTipoSupervision);
    }
}
