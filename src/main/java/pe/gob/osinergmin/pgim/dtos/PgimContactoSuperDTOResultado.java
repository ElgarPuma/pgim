package pe.gob.osinergmin.pgim.dtos;

public class PgimContactoSuperDTOResultado extends PgimContactoSuperDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ContactoSuperRepository#listarContactosEmpresaSuperv
     */
    public PgimContactoSuperDTOResultado(Long idContactoSupervisora, Long idEmpresaSupervisora, Long idPersona,
            String noCargo, String descDeTelefono, String descDeCorreo, String descNoPersona, String descApPaterno,
            String descApMaterno, String descNoRazonSocial) {
        super();
        this.setIdContactoSupervisora(idContactoSupervisora);
        this.setIdEmpresaSupervisora(idEmpresaSupervisora);
        this.setIdPersona(idPersona);
        this.setNoCargo(noCargo);
        this.setDescDeTelefono(descDeTelefono);
        this.setDescDeCorreo(descDeCorreo);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescNoRazonSocial(descNoRazonSocial);
    }
}
