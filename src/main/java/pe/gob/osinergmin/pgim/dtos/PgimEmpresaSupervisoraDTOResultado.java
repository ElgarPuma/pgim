package pe.gob.osinergmin.pgim.dtos;

public class PgimEmpresaSupervisoraDTOResultado extends PgimEmpresaSupervisoraDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.EmpresaSupervisoraRepository#listar
     */
    public PgimEmpresaSupervisoraDTOResultado(Long idEmpresaSupervisora, String descCoDocumentoIdentidad,
            String descNoRazonSocial, String descDeTelefono, String descDeTelefono2, String descDeCorreo,
            String descDeCorreo2, Long descNuContratoVigente) {
        super();
        this.setIdEmpresaSupervisora(idEmpresaSupervisora);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescDeTelefono(descDeTelefono);
        this.setDescDeTelefono2(descDeTelefono2);
        this.setDescDeCorreo(descDeCorreo);
        this.setDescDeCorreo2(descDeCorreo2);
        this.setDescNuContratoVigente(descNuContratoVigente);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.EmpresaSupervisoraRepository#listarPorPalabraClave
     * @see pe.gob.osinergmin.pgim.models.repository.EmpresaSupervisoraRepository#obtenerPorId
     */
    public PgimEmpresaSupervisoraDTOResultado(Long idEmpresaSupervisora, Long idPersona,
            String descCoDocumentoIdentidad, String descNoRazonSocial, String descFlConsorcio) {
        super();
        this.setIdEmpresaSupervisora(idEmpresaSupervisora);
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescFlConsorcio(descFlConsorcio);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.EmpresaSupervisoraRepository#listarPorPersonaJuridica
     */
    public PgimEmpresaSupervisoraDTOResultado(Long idPersona, String descCoDocumentoIdentidad) {
        super();
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.EmpresaSupervisoraRepository#listarEmpresaSupervisoraPorPersona
     * @param idPersona
     */
    public PgimEmpresaSupervisoraDTOResultado(Long idPersona) {
        super();
        this.setIdPersona(idPersona);
    }
}