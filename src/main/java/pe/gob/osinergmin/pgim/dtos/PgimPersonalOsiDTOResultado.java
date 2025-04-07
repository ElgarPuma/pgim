package pe.gob.osinergmin.pgim.dtos;

public class PgimPersonalOsiDTOResultado extends PgimPersonalOsiDTO {

    /**
     * Permite portar los datos necesarios del personal Osinergmin.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository#obtenerPersonalOsiPorUsuario()
     *      * @see
     *      pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository#obtenerPersonalOsiPorId()
     * 
     * @param idPersonalOsi
     * @param idPersona
     * @param noUsuario
     * @param coUsuarioSiged
     */
    public PgimPersonalOsiDTOResultado(Long idPersonalOsi, Long idPersona, String noUsuario, Long coUsuarioSiged, String descTiSexo) {
        super();

        this.setIdPersonalOsi(idPersonalOsi);
        this.setIdPersona(idPersona);
        this.setNoUsuario(noUsuario);
        this.setCoUsuarioSiged(coUsuarioSiged);
        this.setDescTiSexo(descTiSexo);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository#listarPersonalOsi
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository#obtenerPersonalOsigPorId
     */
    public PgimPersonalOsiDTOResultado(Long idPersonalOsi, Long idPersona, String noUsuario, Long coUsuarioSiged,
            String descPersonaCompleto, String descNoPersona, String descApPaterno, String descApMaterno,
            String descCoDocumentoIdentidad, String descTipoDocumentoIdentidad, String descDeCorreo,
            String descDeTelefono, String flActivo, String descNoPrefijoPersona) {
        super();

        this.setIdPersonalOsi(idPersonalOsi);
        this.setIdPersona(idPersona);
        this.setNoUsuario(noUsuario);
        this.setCoUsuarioSiged(coUsuarioSiged);
        this.setDescPersonaCompleto(descPersonaCompleto);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescTipoDocumentoIdentidad(descTipoDocumentoIdentidad);
        this.setDescDeCorreo(descDeCorreo);
        this.setDescDeTelefono(descDeTelefono);
        this.setFlActivo(flActivo);
        this.setDescNoPrefijoPersona(descNoPrefijoPersona);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository#existeUsuario()
     */
    public PgimPersonalOsiDTOResultado(Long idPersonalOsi, String noUsuario) {
        super();
        this.setIdPersonalOsi(idPersonalOsi);
        this.setNoUsuario(noUsuario);
    }
}