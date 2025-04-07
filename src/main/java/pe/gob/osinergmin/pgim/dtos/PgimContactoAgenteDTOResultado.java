package pe.gob.osinergmin.pgim.dtos;

public class PgimContactoAgenteDTOResultado extends PgimContactoAgenteDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ContactoAgenteRepository#listar
	 * @param idContactoAgente
	 * @param idAgenteSupervisado
	 * @param idPersona
	 * @param noCargo
	 * @param noPersona
	 * @param apPaterno
	 * @param apMaterno
	 * @param deTelefono
	 * @param deCorreo
	 * @param descNoRazonSocial
	 */
    public PgimContactoAgenteDTOResultado(Long idContactoAgente, Long idAgenteSupervisado, Long idPersona, String noCargo,
    		String descNoPersona, String descApPaterno, String descApMaterno, String descDeTelefono, String descDeCorreo,
    		String descNoRazonSocial) {
        super();

        this.setIdContactoAgente(idContactoAgente);
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setIdPersona(idPersona);
        this.setNoCargo(noCargo);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescDeTelefono(descDeTelefono);
        this.setDescDeCorreo(descDeCorreo);
        this.setDescNoRazonSocial(descNoRazonSocial);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ContactoAgenteRepository#obtenerContactoAgentePorId
     * @param idContactoAgente
     * @param idAgenteSupervisado
     * @param idPersona
     * @param noCargo
     */
    public PgimContactoAgenteDTOResultado(Long idContactoAgente, Long idAgenteSupervisado, Long idPersona, String noCargo) {
        super();

        this.setIdContactoAgente(idContactoAgente);
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setIdPersona(idPersona);
        this.setNoCargo(noCargo);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ContactoAgenteRepository#listarContactoAgentePorPersona
     * @param idContactoAgente
     */
    public PgimContactoAgenteDTOResultado(Long idContactoAgente) {
        super();
        this.setIdContactoAgente(idContactoAgente);
    }
    
}
