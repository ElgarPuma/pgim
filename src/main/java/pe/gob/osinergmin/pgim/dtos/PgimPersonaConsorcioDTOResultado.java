package pe.gob.osinergmin.pgim.dtos;

public class PgimPersonaConsorcioDTOResultado extends PgimPersonaConsorcioDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaConsorcioRepository#obtenerConsorcioPorId
     */
    public PgimPersonaConsorcioDTOResultado(Long idPersonaConsorcio, Long idPersonaJuridicaConsorcio, Long idPersona,
            String descCoDocumentoIdentidad, String descNoRazonSocial, String flEsPrincipal) {
        super();
        this.setIdPersonaConsorcio(idPersonaConsorcio);
        this.setIdPersonaJuridicaConsorcio(idPersonaJuridicaConsorcio);
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setFlEsPrincipal(flEsPrincipal);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaConsorcioRepository#listarPorPersonaJuridica
     */
    public PgimPersonaConsorcioDTOResultado(Long idPersona, String descCoDocumentoIdentidad) {
        super();
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaConsorcioRepository#listarConsorciosPorPersona
     */
    public PgimPersonaConsorcioDTOResultado(Long idPersonaConsorcio) {
        super();
        this.setIdPersonaConsorcio(idPersonaConsorcio);
    }
}
