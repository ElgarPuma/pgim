package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimPersonaDTOResultado extends PgimPersonaDTO {

    /**
     * Permite potar los datos de una persona natural.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#listarPorCoDocumentoIdentidad()
     * 
     * @param idPersona
     * @param coDocumentoIdentidad
     * @param noRazonSocial
     */
    public PgimPersonaDTOResultado(Long idPersona, String coDocumentoIdentidad, String noRazonSocial) {
        super();

        this.setIdPersona(idPersona);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoRazonSocial(noRazonSocial);
    }

    /**
     * Permite potar los datos de una persona natural.
     * 
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#listarPorNoRazonSocial
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#listarPersonalNaturalPorNoRazonSocial
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#listarPersonalNaturalPorNoRazonSocialSuper
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#listarPersonaNaturalPorPersonalOsi
     * @param idPersona
     * @param coDocumentoIdentidad
     * @param noRazonSocial
     */
    public PgimPersonaDTOResultado(Long idPersona, String coDocumentoIdentidad, String noRazonSocial, String noPersona,
            String apPaterno, String apMaterno) {
        super();

        this.setIdPersona(idPersona);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoRazonSocial(noRazonSocial);
        this.setNoPersona(noPersona);
        this.setApPaterno(apPaterno);
        this.setApMaterno(apMaterno);        
    }

    /**
     * Permite potar los datos de una persona natural.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#obtenerPersonaPorId()
     * 
     * @param idPersona
     * @param coDocumentoIdentidad
     * @param noPersona
     * @param apPaterno
     * @param apMaterno
     */
    public PgimPersonaDTOResultado(Long idPersona, String coDocumentoIdentidad, String noPersona, String apPaterno,
            String apMaterno) {
        super();

        this.setIdPersona(idPersona);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoPersona(noPersona);
        this.setApPaterno(apPaterno);
        this.setApMaterno(apMaterno);
    }

    /**
     * Permite potar los datos de una persona natural.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#listarPorPersona()
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#listarResponsablesXinstanciaProc()
     * 
     * @param idPersona
     * @param coDocumentoIdentidad
     * @param noPersona
     * @param apPaterno
     * @param apMaterno
     */
    public PgimPersonaDTOResultado(Long idPersona, String coDocumentoIdentidad, String noPersona,
            String noRazonSocial) {
        super();

        this.setIdPersona(idPersona);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoPersona(noPersona);
        this.setNoRazonSocial(noRazonSocial);
    }

    /**
     * Permite potar los datos de una persona natural o jurdica.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#listarPersonas()
     * 
     * @param idPersona
     * @param noRazonSocial
     * @param idTipoDocIdentidad
     * @param descIdTipoDocIdentidad
     * @param coDocumentoIdentidad
     * @param tiSexo
     */
    public PgimPersonaDTOResultado(Long idPersona, String noRazonSocial, Long idTipoDocIdentidad,
            String descIdTipoDocIdentidad, String coDocumentoIdentidad, String tiSexo, String noPersona,
            String apPaterno, String apMaterno, String flConsorcio) {
        super();

        this.setIdPersona(idPersona);
        this.setNoRazonSocial(noRazonSocial);
        this.setIdTipoDocIdentidad(idTipoDocIdentidad);
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setTiSexo(tiSexo);
        this.setNoPersona(noPersona);
        this.setApPaterno(apPaterno);
        this.setApMaterno(apMaterno);
        this.setFlConsorcio(flConsorcio);
    }

    /**
     * Permite potar los datos de una persona natural o jurdica.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#obtenerPersonalNatuJuriPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#obtenerPersonalNaturalPorId()
     */
    public PgimPersonaDTOResultado(Long idPersona, Long idTipoDocIdentidad, String descIdTipoDocIdentidad,
            String coDocumentoIdentidad, String noRazonSocial, String noCorto, String noPersona, String apPaterno,
            String apMaterno, String noPrefijoPersona, String tiSexo, Date feNacimiento, String deRestriccion, Long idFuentePersonaNatural,
            String deTelefono, String deTelefono2, String deCorreo, String deCorreo2, Long idUbigeo, String descUbigeo,
            String diPersona, String flAfiliadoNtfccionElctrnca, String deCorreoNtfccionElctrnca, Date feAfiliadoDesde,
            String cmNota, String flConsorcio) {
        super();
        // Identificación
        this.setIdPersona(idPersona); // N y J
        this.setIdTipoDocIdentidad(idTipoDocIdentidad); // N y J
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad); // N y J
        this.setCoDocumentoIdentidad(coDocumentoIdentidad); // N y J
        this.setNoRazonSocial(noRazonSocial); // J
        this.setNoCorto(noCorto);
        this.setNoPersona(noPersona); // N
        this.setApPaterno(apPaterno);// N
        this.setApMaterno(apMaterno);// N
        this.setNoPrefijoPersona(noPrefijoPersona);// N
        this.setTiSexo(tiSexo);// N
        this.setFeNacimiento(feNacimiento);
        this.setDeRestriccion(deRestriccion);
        this.setIdFuentePersonaNatural(idFuentePersonaNatural);
        // Contacto
        this.setDeTelefono(deTelefono);
        this.setDeTelefono2(deTelefono2);
        this.setDeCorreo(deCorreo);
        this.setDeCorreo2(deCorreo2);
        // Ubicación
        this.setIdUbigeo(idUbigeo);
        // this.setDescIdUbigeo(descIdUbigeo);
        this.setDescUbigeo(descUbigeo);
        this.setDiPersona(diPersona);
        // Notificaciones electrónicas
        this.setFlAfiliadoNtfccionElctrnca(flAfiliadoNtfccionElctrnca);
        this.setDeCorreoNtfccionElctrnca(deCorreoNtfccionElctrnca);
        this.setFeAfiliadoDesde(feAfiliadoDesde);
        // Otros
        this.setCmNota(cmNota);
        this.setFlConsorcio(flConsorcio);
    }

    /**
     * Permite potar los datos de una persona natural o jurdica.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#existePersona()
     * 
     */
    public PgimPersonaDTOResultado(Long idPersona, Long idTipoDocIdentidad, String descIdTipoDocIdentidad,
            String coDocumentoIdentidad) {
        super();
        this.setIdPersona(idPersona); // N y J
        this.setIdTipoDocIdentidad(idTipoDocIdentidad); // N y J
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad); // N y J
    }

    /**
     * Permite potar los datos de una persona natural o jurdica.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonaRepository#existePersonaJuridica()
     * 
     */
    public PgimPersonaDTOResultado(Long idPersona, String noRazonSocial) {
        super();
        this.setIdPersona(idPersona);
        this.setNoRazonSocial(noRazonSocial);
    }
}