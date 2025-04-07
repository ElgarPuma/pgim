package pe.gob.osinergmin.pgim.dtos;

public class PgimPersonaconAuxDTOResultado extends PgimPersonaconAuxDTO {
	
	/**
     * Permite portar los datos necesarios del personal de la supervisora.
     * 
     * En el repositorio usa el método:
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalConAuxRepository#listarPersonalCon()
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalConAuxRepository#listarPersonalContratoXusuario()
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalConAuxRepository#listarPersonalContratoSinDuplicadosXrol()
     * 
     * 
     * @param idPersonalConAux
     * @param idPersonalContrato
     * @param idPersona
     * @param noUsuario
     * @param idTipoDocIdentidad
     * @param noTipoDocIdentidad
     * @param coDocumentoIdentidad
     * @param noPersona
     * @param apPaterno
     * @param apMaterno
     */
    public PgimPersonaconAuxDTOResultado(Long idPersonalConAux, Long idPersonalContrato, Long idPersona, String noUsuario,
            Long idTipoDocIdentidad, String noTipoDocIdentidad, String coDocumentoIdentidad, String noPersona,
            String apPaterno, String apMaterno, String noCompletoPersona,Long idContrato) {
        super();

        this.setIdPersonalConAux(idPersonalConAux);        
        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdPersona(idPersona);
        this.setNoUsuario(noUsuario);
        this.setIdTipoDocIdentidad(idTipoDocIdentidad);
        this.setNoTipoDocIdentidad(noTipoDocIdentidad);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoPersona(noPersona);
        this.setApPaterno(apPaterno);
        this.setApMaterno(apMaterno);
        this.setNoCompletoPersona(noCompletoPersona);
        this.setIdContrato(idContrato);
    }

}
