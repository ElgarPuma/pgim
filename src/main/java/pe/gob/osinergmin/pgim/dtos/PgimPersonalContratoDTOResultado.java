package pe.gob.osinergmin.pgim.dtos;

public class PgimPersonalContratoDTOResultado extends PgimPersonalContratoDTO {

    /**
     * Permite portar los datos necesarios del personal Osinergmin.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#obtenerPersonalContratoPorUsuario()
     * 
     * @param idPersonalContrato
     * @param idPersona
     * @param noUsuario
     * @param coUsuarioSiged
     */
    public PgimPersonalContratoDTOResultado(Long idPersonalContrato, Long idPersona, String noUsuario,
            Long coUsuarioSiged) {
        super();

        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdPersona(idPersona);
        this.setNoUsuario(noUsuario);
        this.setCoUsuarioSiged(coUsuarioSiged);
    }

    /**
     * Permite portar los datos necesarios del personal Osinergmin.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#listarPersonalContrato()
     *
     * @param idPersonalContrato
     * @param idContrato
     * @param idPersona
     * @param descPersonaCompleto
     * @param descCoDocumentoIdentidad
     * @param descTipoDocIdentidad
     * @param descFlEstado
     * @param noUsuario
     * @param coUsuarioSiged
     */
    public PgimPersonalContratoDTOResultado(Long idPersonalContrato, Long idContrato, Long idPersona,
            String descPersonaCompleto, String descCoDocumentoIdentidad, String descTipoDocIdentidad,
            String descFlEstado, String noUsuario, Long coUsuarioSiged, String noCargoEnContrato, 
            String noPerfilEnContrato, Long idRolProceso, String descNoRolProceso, String descDeValorParametro) {
        super();

        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdContrato(idContrato);
        this.setIdPersona(idPersona);
        this.setDescPersonaCompleto(descPersonaCompleto);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescTipoDocIdentidad(descTipoDocIdentidad);
        this.setDescFlEstado(descFlEstado);
        this.setNoUsuario(noUsuario);
        this.setCoUsuarioSiged(coUsuarioSiged);
        this.setNoCargoEnContrato(noCargoEnContrato);
        this.setNoPerfilEnContrato(noPerfilEnContrato);
        this.setIdRolProceso(idRolProceso);
        this.setDescNoRolProceso(descNoRolProceso);
        this.setDescDeValorParametro(descDeValorParametro);
    }

    /**
     * Permite portar los datos necesarios del personal Osinergmin.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#obtenerPersonalContrato()
     *
     * @param idPersonalContrato
     * @param idContrato
     * @param idPersona
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     * @param descCoDocumentoIdentidad
     * @param descIdTipoDocIdentidad
     * @param descTipoDocIdentidad
     * @param descIdUbigeo
     * @param descUbigeo
     * @param descFlEstado
     * @param noUsuario
     * @param coUsuarioSiged
     */
    public PgimPersonalContratoDTOResultado(Long idPersonalContrato, Long idContrato, Long idPersona,
            String descNoPersona, String descApPaterno, String descApMaterno, String descCoDocumentoIdentidad,
            Long descIdTipoDocIdentidad, String descTipoDocIdentidad, Long descIdUbigeo, String descUbigeo,
            String descFlEstado, String noUsuario, Long coUsuarioSiged, String noCargoEnContrato, 
            String noPerfilEnContrato, String descNoPrefijoPersona, Long idRolProceso, Long idDivisionSupervisora) {
        super();

        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdContrato(idContrato);
        this.setIdPersona(idPersona);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setDescTipoDocIdentidad(descTipoDocIdentidad);
        this.setDescIdUbigeo(descIdUbigeo);
        this.setDescUbigeo(descUbigeo);
        this.setDescFlEstado(descFlEstado);
        this.setNoUsuario(noUsuario);
        this.setCoUsuarioSiged(coUsuarioSiged);
        this.setNoCargoEnContrato(noCargoEnContrato);
        this.setNoPerfilEnContrato(noPerfilEnContrato);
        this.setDescNoPrefijoPersona(descNoPrefijoPersona);
        this.setIdRolProceso(idRolProceso);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
    }

    /**
     * Permite portar los datos necesarios del personal Osinergmin.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#existePersonalContrato()
     *
     * @param idPersonalContrato
     * @param idContrato
     * @param idPersona
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     * @param descCoDocumentoIdentidad
     * @param descIdTipoDocIdentidad
     * @param descTipoDocIdentidad
     */
    public PgimPersonalContratoDTOResultado(Long idPersonalContrato, Long idContrato, Long idPersona,
            String descNoPersona, String descApPaterno, String descApMaterno, String descCoDocumentoIdentidad,
            Long descIdTipoDocIdentidad, String descTipoDocIdentidad) {
        super();

        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdContrato(idContrato);
        this.setIdPersona(idPersona);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setDescTipoDocIdentidad(descTipoDocIdentidad);
    }

    /**
     * Permite portar los datos necesarios del personal Osinergmin.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#existePersonalContrato()
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#listarPorPersona()
     *
     * @param idPersonalContrato
     * @param idContrato
     * @param idPersona
     * @param descCoDocumentoIdentidad
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     */
    public PgimPersonalContratoDTOResultado(Long idPersonalContrato, Long idContrato, Long idPersona,
            String descCoDocumentoIdentidad, String descNoPersona, String descApPaterno, String descApMaterno) {
        super();

        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdContrato(idContrato);
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
    }

    /**
     * Permite portar los datos necesarios del personal Osinergmin.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#existePersonalContrato()
     **/
    public PgimPersonalContratoDTOResultado(Long idPersonalContrato, Long idContrato, Long idPersona,
            String descCoDocumentoIdentidad) {
        super();

        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdContrato(idContrato);
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }

    public PgimPersonalContratoDTOResultado(
            // Long idPersonalContrato
            // , Long idContrato
            Long idPersona, String descCoDocumentoIdentidad) {
        super();

        // this.setIdPersonalContrato(idPersonalContrato);
        // this.setIdContrato(idContrato);
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }

    public PgimPersonalContratoDTOResultado(Long idPersona) {
        super();
        this.setIdPersona(idPersona);
    }

    /**
     * Permite portar los datos necesarios del personal Contrato.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#existeNoUsuario(Long, String)
     * @param idPersona
     * @param noUsuario
     */
    public PgimPersonalContratoDTOResultado(Long idPersona, Long idPersonalContrato, String noUsuario) {
        super();

        this.setIdPersona(idPersona);
        this.setIdPersonalContrato(idPersonalContrato);
        this.setNoUsuario(noUsuario);
    }

    /**
     * Permite portar los datos necesarios del personal del contrato.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#listarPersonalContratoPorRoles()
     * 
     * @param idPersonalContrato
     * @param idPersona
     * @param noUsuario
     * @param coUsuarioSiged
     */
    public PgimPersonalContratoDTOResultado(Long idPersonalContrato, Long idPersona, Long idRolProceso, String noCargoEnContrato, 
        String noPerfilEnContrato, Long idDivisionSupervisora, String descDeValorParametro, String descPersonaCompleto) {
        super();
        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdPersona(idPersona);
        this.setIdRolProceso(idRolProceso);
        this.setNoCargoEnContrato(noCargoEnContrato);
        this.setNoPerfilEnContrato(noPerfilEnContrato);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setDescDeValorParametro(descDeValorParametro);
        this.setDescPersonaCompleto(descPersonaCompleto);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository#listarUsuarioPersonalContratoPorContrato()
     * 
     * @param idPersonalContrato
     * @param idContrato
     * @param idPersona
     * @param noUsuario
     * @param descPersonaCompleto
     * @param descCoDocumentoIdentidad
     */
    public PgimPersonalContratoDTOResultado(Long idPersonalContrato, Long idContrato, Long idPersona, String noUsuario, String descPersonaCompleto, String descCoDocumentoIdentidad) {
        super();
        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdContrato(idContrato);
        this.setIdPersona(idPersona);
        this.setNoUsuario(noUsuario);
        this.setDescPersonaCompleto(descPersonaCompleto);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }
}