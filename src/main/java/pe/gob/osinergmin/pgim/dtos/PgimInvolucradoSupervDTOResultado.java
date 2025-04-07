package pe.gob.osinergmin.pgim.dtos;

public class PgimInvolucradoSupervDTOResultado extends PgimInvolucradoSupervDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InvolucradoSupervRepository#obtenerRepresentantesAgenteSupervisado()
	 * @see pe.gob.osinergmin.pgim.models.repository.InvolucradoSupervRepository#obtenerRepresentantesTrabajadores()
	 * @param descNoPersona
	 * @param descApPaterno
	 * @param descApMaterno
	 * @param descCoDocumentoIdentidad
	 * @param deCargo
	 */
	public PgimInvolucradoSupervDTOResultado(String descNoPersona, String descApPaterno, String descApMaterno,
			String descCoDocumentoIdentidad, String deCargo, String descIdTipoPrefijoInvolucrado) {
		super();
		this.setDescNoPersona(descNoPersona);
		this.setDescApPaterno(descApPaterno);
		this.setDescApMaterno(descApMaterno);
		this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
		this.setDeCargo(deCargo);
		this.setDescIdTipoPrefijoInvolucrado(descIdTipoPrefijoInvolucrado);
	}

    
    /**
     * Permite portar los datos necesarios a la Persona involucrada.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InvolucradoSupervRepository#listarInvolucradoSupervision()
     * 
     * @param idInvolucradoSuperv
     * @param idPersona
     * @param idTipoActaInvolucrado
     * @param idTipoInvolucrado
     * @param idTipoPrefijoInvolucrado
     * @param deCargo
     * @param descIdTipoInvolucrado
     * @param descCoDocumentoIdentidad
     * @param descTipoDocIdentidad
     * @param descPersonaCompleto
     * @param descIdTipoPrefijoInvolucrado
     */
    public PgimInvolucradoSupervDTOResultado(
        Long idInvolucradoSuperv, Long idSupervision, Long idPersona, Long idTipoActaInvolucrado, 
        Long idTipoInvolucrado, Long idTipoPrefijoInvolucrado, String deCargo,
        String descIdTipoInvolucrado, String descCoDocumentoIdentidad, 
        String descTipoDocIdentidad, String descPersonaCompleto, String descIdTipoPrefijoInvolucrado
        ) {
        super();
        this.setIdInvolucradoSuperv(idInvolucradoSuperv);
        this.setIdSupervision(idSupervision);
        this.setIdPersona(idPersona);
        this.setIdTipoActaInvolucrado(idTipoActaInvolucrado);
        this.setIdTipoInvolucrado(idTipoInvolucrado);
        this.setIdTipoPrefijoInvolucrado(idTipoPrefijoInvolucrado);
        this.setDeCargo(deCargo);
        this.setDescIdTipoInvolucrado(descIdTipoInvolucrado);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescTipoDocIdentidad(descTipoDocIdentidad);
        this.setDescPersonaCompleto(descPersonaCompleto);
        this.setDescIdTipoPrefijoInvolucrado(descIdTipoPrefijoInvolucrado);
    }

    /**
     * Permite portar los datos necesarios a la Persona involucrada.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InvolucradoSupervRepository#obtenerInvolucradoSuperv()
     * 
     * @param idInvolucradoSuperv
     * @param idPersona
     * @param idTipoActaInvolucrado
     * @param idTipoInvolucrado
     * @param idTipoPrefijoInvolucrado
     * @param deCargo
     * @param descIdTipoInvolucrado
     * @param descCoDocumentoIdentidad
     * @param descTipoDocIdentidad
     * @param descIdTipoPrefijoInvolucrado
     */
    public PgimInvolucradoSupervDTOResultado(
        Long idInvolucradoSuperv, Long idSupervision, Long idPersona, Long idTipoActaInvolucrado, 
        Long idTipoInvolucrado, Long idTipoPrefijoInvolucrado, String deCargo,
        String descIdTipoInvolucrado, String descCoDocumentoIdentidad, 
        String descTipoDocIdentidad, String descIdTipoPrefijoInvolucrado
        ) {
        super();
        this.setIdInvolucradoSuperv(idInvolucradoSuperv);
        this.setIdSupervision(idSupervision);
        this.setIdPersona(idPersona);
        this.setIdTipoActaInvolucrado(idTipoActaInvolucrado);
        this.setIdTipoInvolucrado(idTipoInvolucrado);
        this.setIdTipoPrefijoInvolucrado(idTipoPrefijoInvolucrado);
        this.setDeCargo(deCargo);

        this.setDescIdTipoInvolucrado(descIdTipoInvolucrado);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescTipoDocIdentidad(descTipoDocIdentidad);
        this.setDescIdTipoPrefijoInvolucrado(descIdTipoPrefijoInvolucrado);
    }

    /**
     * Permite portar los datos necesarios a la Persona involucrada.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InvolucradoSupervRepository#listarPorInvolucrado()
     * @param idPersona
     * @param descCoDocumentoIdentidad
     */
    public PgimInvolucradoSupervDTOResultado(Long idPersona, 
     String descCoDocumentoIdentidad) {
        super();
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }

    /**
     * Permite portar los datos necesarios a la Persona involucrada.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InvolucradoSupervRepository#existeRepresentanteAi()
     *
     * @param idInvolucradoSuperv
     * @param idSupervision
     * @param idPersona
     * @param idTipoActaInvolucrado
     * @param descCoDocumentoIdentidad
     */
    public PgimInvolucradoSupervDTOResultado(Long idInvolucradoSuperv, Long idSupervision, Long idPersona, Long idTipoActaInvolucrado,
     String descCoDocumentoIdentidad) {
        super();
        this.setIdInvolucradoSuperv(idInvolucradoSuperv);
        this.setIdSupervision(idSupervision);
        this.setIdPersona(idPersona);
        this.setIdTipoActaInvolucrado(idTipoActaInvolucrado);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }
}
