package pe.gob.osinergmin.pgim.dtos;

public class PgimPersonaosiAuxDTOResultado extends PgimPersonaosiAuxDTO {

    /**
     * Permite portar los datos necesarios del personal Osinergmin.
     * 
     * En el repositorio usa el método:
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalOsiAuxRepository#listarPersonalOsi()
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalOsiAuxRepository#obtenerPersonalOsiNombreUsuarioWindows()
     * 
     * @param idPersonalOsiAux
     * @param idPersonalOsi
     * @param idPersona
     * @param noUsuario
     * @param idTipoDocIdentidad
     * @param noTipoDocIdentidad
     * @param coDocumentoIdentidad
     * @param noPersona
     * @param apPaterno
     * @param apMaterno
     */
    public PgimPersonaosiAuxDTOResultado(Long idPersonalOsiAux, Long idPersonalOsi, Long idPersona, String noUsuario,
            Long idTipoDocIdentidad, String noTipoDocIdentidad, String coDocumentoIdentidad, String noPersona,
            String apPaterno, String apMaterno, String noCompletoPersona) {
        super();

        this.setIdPersonalOsiAux(idPersonalOsiAux);
        this.setIdPersonalOsi(idPersonalOsi);
        this.setIdPersona(idPersona);
        this.setNoUsuario(noUsuario);
        this.setIdTipoDocIdentidad(idTipoDocIdentidad);
        this.setNoTipoDocIdentidad(noTipoDocIdentidad);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoPersona(noPersona);
        this.setApPaterno(apPaterno);
        this.setApMaterno(apMaterno);
        this.setNoCompletoPersona(noCompletoPersona);
    }

}