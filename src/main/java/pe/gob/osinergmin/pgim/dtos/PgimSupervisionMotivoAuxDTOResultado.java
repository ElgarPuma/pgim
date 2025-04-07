package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimSupervisionMotivoAuxDTOResultado extends PgimSupervisionMotivoAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SupervisionMotivoAuxRepository#filtrarSeleccionMotivosPorUM
	 * 
	 * @param idMotivo
	 * @param idUnidadMinera
	 * @param idTipoMotivo
	 * @param deTipoMotivo
	 * @param deMotivo
	 * @param feMotivo
	 * @param coMotivo
	 * @param deSubtipoMotivo
	 * @param idSubtipoMotivo
	 * @param idInstanciaProcesoMotivo
	 */
	public PgimSupervisionMotivoAuxDTOResultado(Long idMotivo, Long idUnidadMinera, Long idTipoMotivo, 
			String deTipoMotivo, String deMotivo, Date feMotivo, String coMotivo, 
			String deSubtipoMotivo, Long idSubtipoMotivo, Long idInstanciaProcesoMotivo 
			) {
        super();

        this.setIdMotivo(idMotivo);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setIdTipoMotivo(idTipoMotivo);
        this.setDeTipoMotivo(deTipoMotivo);
        this.setDeMotivo(deMotivo);
        this.setFeMotivo(feMotivo);
        this.setCoMotivo(coMotivo);
        this.setDeSubtipoMotivo(deSubtipoMotivo);
        this.setIdSubtipoMotivo(idSubtipoMotivo);
        this.setIdInstanciaProcesoMotivo(idInstanciaProcesoMotivo);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SupervisionMotivoAuxRepository#listarSupervisionMotivoAuxPorSupervision
	 * 
	 * @param idMotivo
	 * @param idUnidadMinera
	 * @param idTipoMotivo
	 * @param deTipoMotivo
	 * @param deMotivo
	 * @param feMotivo
	 * @param coMotivo
	 * @param deSubtipoMotivo
	 * @param idSubtipoMotivo
	 * @param idInstanciaProcesoMotivo
	 * @param idSupervisionMotivo
	 * @param idSupervision
	 * @param idObjetoMotivoInicio
	 * @param idTipoMotivoInicio
	 * @param descIdInstanciaPasoMa
	 */
	public PgimSupervisionMotivoAuxDTOResultado(Long idMotivo, Long idUnidadMinera, Long idTipoMotivo, 
			String deTipoMotivo, String deMotivo, Date feMotivo, String coMotivo, 
			String deSubtipoMotivo, Long idSubtipoMotivo, Long idInstanciaProcesoMotivo, 
			Long idSupervisionMotivo, Long idSupervision, Long idObjetoMotivoInicio, 
			Long idTipoMotivoInicio, Long descIdInstanciaPasoMa						
			) {
        super();

        this.setIdMotivo(idMotivo);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setIdTipoMotivo(idTipoMotivo);
        this.setDeTipoMotivo(deTipoMotivo);
        this.setDeMotivo(deMotivo);
        this.setFeMotivo(feMotivo);
        this.setCoMotivo(coMotivo);
        this.setDeSubtipoMotivo(deSubtipoMotivo);
        this.setIdSubtipoMotivo(idSubtipoMotivo);
        this.setIdInstanciaProcesoMotivo(idInstanciaProcesoMotivo);
        this.setIdSupervisionMotivo(idSupervisionMotivo);
        this.setIdSupervision(idSupervision);
        this.setIdObjetoMotivoInicio(idObjetoMotivoInicio);
        this.setIdTipoMotivoInicio(idTipoMotivoInicio);
        
        this.setDescIdInstanciaPasoMa(descIdInstanciaPasoMa);

    }

}
