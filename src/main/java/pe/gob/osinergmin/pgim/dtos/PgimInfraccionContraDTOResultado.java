package pe.gob.osinergmin.pgim.dtos;

public class PgimInfraccionContraDTOResultado extends PgimInfraccionContraDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InfraccionContraRepository#obtenerContratistaByIdContratistaOrigen
	 * 
	 * @param idInfraccionContra
	 * @param idInfraccion
	 * @param idInfraccionContraOrigen
	 * @param idPersona
	 * @param flVigente
	 */
	public PgimInfraccionContraDTOResultado(Long idInfraccionContra, Long idInfraccion, Long idInfraccionContraOrigen,
			Long idPersona, String flVigente) {
        super();
        this.setIdInfraccionContra(idInfraccionContra);
        this.setIdInfraccion(idInfraccion);
        this.setIdInfraccionContraOrigen(idInfraccionContraOrigen);
        this.setIdPersona(idPersona);
        this.setFlVigente(flVigente);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InfraccionContraRepository#listarContratistaByIdInfraccion
	 * 
	 * @param idInfraccionContra
	 * @param idInfraccion
	 * @param idInfraccionContraOrigen
	 * @param idPersona
	 * @param flVigente
	 * @param descNoRazonSocial
	 * @param descCoDocumentoIdentidad
	 */
	public PgimInfraccionContraDTOResultado(Long idInfraccionContra, Long idInfraccion, Long idInfraccionContraOrigen,
			Long idPersona, String flVigente, String descNoRazonSocial, String descCoDocumentoIdentidad) {
        super();
        this.setIdInfraccionContra(idInfraccionContra);
        this.setIdInfraccion(idInfraccion);
        this.setIdInfraccionContraOrigen(idInfraccionContraOrigen);
        this.setIdPersona(idPersona);
        this.setFlVigente(flVigente);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InfraccionContraRepository#listarContratistaByIdInstanciaProceso
	 * 
	 * @param idPersona
	 * @param descNoRazonSocial
	 * @param descCoDocumentoIdentidad
	 */
	public PgimInfraccionContraDTOResultado(Long idPersona, String descNoRazonSocial, String descCoDocumentoIdentidad) {
        super();
        this.setIdPersona(idPersona);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }


}
