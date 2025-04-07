package pe.gob.osinergmin.pgim.dtos;

public class PgimEqpInstanciaProDTOResultado extends PgimEqpInstanciaProDTO {

    /**
     * Permite portar los datos necesarios del equipo de la instancia del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#listarPersEqpPorInstanProcYrol()
     * 
     * @param idEquipoInstanciaPro
     */
    public PgimEqpInstanciaProDTOResultado(Long idEquipoInstanciaPro) {
        super();

        this.setIdEquipoInstanciaPro(idEquipoInstanciaPro);
    }

    /**
     * Permite portar los datos necesarios del equipo de la instancia del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#obtenerPorId()
     * 
     * @param idEquipoInstanciaPro
     * @param idInstanciaProceso
     * @param idRolProceso
     * @param idPersonalContrato
     * @param idPersonalOsi
     */
    public PgimEqpInstanciaProDTOResultado(Long idEquipoInstanciaPro, Long idInstanciaProceso, Long idRolProceso,
            Long idPersonalContrato, Long idPersonalOsi) {
        super();

        this.setIdEquipoInstanciaPro(idEquipoInstanciaPro);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRolProceso(idRolProceso);
        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdPersonalOsi(idPersonalOsi);
    }

    /**
     * Permite portar los datos necesarios del equipo de supervisión.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#obtenerPersonaOsiEqpPorRol()
     * 
     * @param idEquipoInstanciaPro
     * @param idInstanciaProceso
     * @param idRolProceso
     * @param idPersonalOsi
     */
    public PgimEqpInstanciaProDTOResultado(Long idEquipoInstanciaPro, Long idInstanciaProceso, Long idRolProceso,
            Long idPersonalOsi, int osi) {
        super();

        this.setIdEquipoInstanciaPro(idEquipoInstanciaPro);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRolProceso(idRolProceso);
        this.setIdPersonalOsi(idPersonalOsi);
    }

    /**
     * Permite portar los datos necesarios del equipo de supervisión.
     * 
     * En el repositorio usa el método:
     * 
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#obtenerPersonaContraEqpPorRol()
     * 
     * @param idEquipoInstanciaPro
     * @param idInstanciaProceso
     * @param idRolProceso
     * @param idPersonalContrato
     */
    public PgimEqpInstanciaProDTOResultado(Long idEquipoInstanciaPro, Long idInstanciaProceso, Long idRolProceso,
            Long idPersonalContrato) {
        super();

        this.setIdEquipoInstanciaPro(idEquipoInstanciaPro);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRolProceso(idRolProceso);
        this.setIdPersonalContrato(idPersonalContrato);
    }

    /**
     * Permite portar los datos necesarios del equipo de la instancia del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#listarPersonalAsignableOsiConRol()
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#listarPersonalOsiResponsable()
     * 
     * @param idEquipoInstanciaPro
     * @param idInstanciaProceso
     * @param idPersonalOsi
     * @param descIdPersona
     * @param descNoUsuario
     * @param descIdTipoDocIdentidad
     * @param descNoTipoDocIdentidad
     * @param descCoDocumentoIdentidad
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     * @param descNoCompletoPersona
     * @param idRolProceso
     * @param descNoRolProceso
     */
    public PgimEqpInstanciaProDTOResultado(Long idEquipoInstanciaPro, Long idInstanciaProceso, Long idPersonalOsi,
            Long descIdPersona, String descNoUsuario, Long descIdTipoDocIdentidad, String descNoTipoDocIdentidad,
            String descCoDocumentoIdentidad, String descNoPersona, String descApPaterno, String descApMaterno,
            String descNoCompletoPersona, Long idRolProceso, String descNoRolProceso, int osi) {
        super();

        this.setIdEquipoInstanciaPro(idEquipoInstanciaPro);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdPersonalOsi(idPersonalOsi);
        this.setDescIdPersona(descIdPersona);
        this.setDescNoUsuario(descNoUsuario);
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setDescNoTipoDocIdentidad(descNoTipoDocIdentidad);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescNoCompletoPersona(descNoCompletoPersona);
        this.setIdRolProceso(idRolProceso);
        this.setDescNoRolProceso(descNoRolProceso);
    }

    /**
     * Permite portar los datos necesarios del equipo de la instancia del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#listarPersonalAsignableContraConRol()
     * 
     * @param idEquipoInstanciaPro
     * @param idInstanciaProceso
     * @param idPersonalContrato
     * @param descIdPersona
     * @param descNoUsuario
     * @param descIdTipoDocIdentidad
     * @param descNoTipoDocIdentidad
     * @param descCoDocumentoIdentidad
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     * @param descNoCompletoPersona
     * @param idRolProceso
     * @param descNoRolProceso
     */
    public PgimEqpInstanciaProDTOResultado(Long idEquipoInstanciaPro, Long idInstanciaProceso, Long idPersonalContrato,
            Long descIdPersona, String descNoUsuario, Long descIdTipoDocIdentidad, String descNoTipoDocIdentidad,
            String descCoDocumentoIdentidad, String descNoPersona, String descApPaterno, String descApMaterno,
            String descNoCompletoPersona, Long idRolProceso, String descNoRolProceso) {
        super();

        this.setIdEquipoInstanciaPro(idEquipoInstanciaPro);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdPersonalContrato(idPersonalContrato);
        this.setDescIdPersona(descIdPersona);
        this.setDescNoUsuario(descNoUsuario);
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setDescNoTipoDocIdentidad(descNoTipoDocIdentidad);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescNoCompletoPersona(descNoCompletoPersona);
        this.setIdRolProceso(idRolProceso);
        this.setDescNoRolProceso(descNoRolProceso);
    }

    /**
     * Permite portar los datos necesarios del equipo de la instancia del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#listarPersonalAsignableOsiSinRol()
     * 
     * @param idPersonalOsi
     * @param idPersonalContrato
     * @param descIdPersona
     * @param descNoUsuario
     * @param descIdTipoDocIdentidad
     * @param descNoTipoDocIdentidad
     * @param descCoDocumentoIdentidad
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     * @param descNoCompletoPersona
     */
    public PgimEqpInstanciaProDTOResultado(Long idPersonalOsi, Long descIdPersona,
            String descNoUsuario, Long descIdTipoDocIdentidad, String descNoTipoDocIdentidad,
            String descCoDocumentoIdentidad, String descNoPersona, String descApPaterno, String descApMaterno,
            String descNoCompletoPersona, int osi) {
        super();

        this.setIdPersonalOsi(idPersonalOsi);

        this.setDescIdPersona(descIdPersona);
        this.setDescNoUsuario(descNoUsuario);
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setDescNoTipoDocIdentidad(descNoTipoDocIdentidad);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescNoCompletoPersona(descNoCompletoPersona);
    }

    /**
     * Permite portar los datos necesarios del equipo de la instancia del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#listarPersonalAsignableContraSinRol()
     * 
     * @param idPersonalOsi
     * @param idPersonalContrato
     * @param descIdPersona
     * @param descNoUsuario
     * @param descIdTipoDocIdentidad
     * @param descNoTipoDocIdentidad
     * @param descCoDocumentoIdentidad
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     * @param descNoCompletoPersona
     */
    public PgimEqpInstanciaProDTOResultado(Long idPersonalContrato, Long descIdPersona, String descNoUsuario,
            Long descIdTipoDocIdentidad, String descNoTipoDocIdentidad, String descCoDocumentoIdentidad,
            String descNoPersona, String descApPaterno, String descApMaterno, String descNoCompletoPersona) {
        super();

        this.setIdPersonalContrato(idPersonalContrato);
        this.setDescIdPersona(descIdPersona);
        this.setDescNoUsuario(descNoUsuario);
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setDescNoTipoDocIdentidad(descNoTipoDocIdentidad);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescNoCompletoPersona(descNoCompletoPersona);
    }

    /**
     * Permite portar los datos necesarios del equipo de la instancia del proceso.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#obtenerParticipantesInstanciaPro()
     * 
     * @param idEquipoInstanciaPro
     * @param idInstanciaProceso
     * @param idRolProceso
     * @param noDescRolProceso
     * @param idPersonalContrato
     * @param idPersonalOsi
     * @param descNombreOsinergmin
     * @param descNombreContrato
     * @param descNoPersonaDoc
     * @param descDeOrigen
     * @param flEsResponsable
     * @param idPersona
     * @param descNoOriginalArchivo
     * @param descNoUsuario
     */
    public PgimEqpInstanciaProDTOResultado(Long idEquipoInstanciaPro, Long idInstanciaProceso, Long idRolProceso,
            String noDescRolProceso, Long idPersonalContrato, Long idPersonalOsi, String descNombreOsinergmin,
            String descNombreContrato, String descNoPersonaDoc, String descDeOrigen, String flEsResponsable, Long idPersona,
            String descNoOriginalArchivo, String descNoUsuario, String noCargoPersonaEquipo, String noPrefijoPersonaEquipo, String noPerfilPersonaEquipo, Long descIdPersona, String descTiSexo) {
        super();
        this.setIdEquipoInstanciaPro(idEquipoInstanciaPro);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRolProceso(idRolProceso);
        this.setDescNoRolProceso(noDescRolProceso);
        this.setIdPersonalContrato(idPersonalContrato);
        this.setIdPersonalOsi(idPersonalOsi);
        this.setDescNombreOsinergmin(descNombreOsinergmin);
        this.setDescNombreContrato(descNombreContrato);
        this.setDescNoPersonaDoc(descNoPersonaDoc);
        this.setDescDeOrigen(descDeOrigen);
        this.setFlEsResponsable(flEsResponsable);
        this.setDescIdPersona(idPersona);
        this.setDescNoOriginalArchivo(descNoOriginalArchivo);
        if (!descNombreOsinergmin.trim().equals("")) {
            this.setDescNoCompletoPersona(descNombreOsinergmin);
        } else {
            this.setDescNoCompletoPersona(descNombreContrato);
            this.setDescNoPersonaDoc(descNoPersonaDoc);
        }
        this.setDescNoUsuario(descNoUsuario);
        this.setNoCargoPersonaEquipo(noCargoPersonaEquipo);
        this.setNoPrefijoPersonaEquipo(noPrefijoPersonaEquipo);
        this.setNoPerfilPersonaEquipo(noPerfilPersonaEquipo);
        this.setDescIdPersona(descIdPersona);
        this.setDescTiSexo(descTiSexo);

    }

    /**
     * Permite portar los datos necesarios del equipo de la instancia del proceso de
     * un determinado rol.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#obtenerParticipantesInstanciaProXRol()
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#obtenerParticipanteInstanciaProXRol()
     * 
     * @param idEquipoInstanciaPro
     * @param idInstanciaProceso
     * @param idRolProceso
     * @param noRolProceso
     * @param idPersonalContrato
     * @param idPersonalOsi
     * @param nombreOsi
     * @param nombreContrato
     * @param descNoUsuario
     */
    public PgimEqpInstanciaProDTOResultado(Long idPersonal, Long idPersona, String coDocumentoIdentidad,
            String descNombreOsinergmin, String DescNoPersonaDocOsinergmin, String descNombreContrato,
            String DescNoPersonaDocContrato, String descDeOrigen, String noCargoPersonaEquipo, 
            String noPrefijoPersonaEquipo, String noPerfilPersonaEquipo, Long idRolProceso, String descNoTipoDocIdentidadContrato, String descNoTipoDocIdentidadOsinergmin,
            String descNoUsuario) {
        super();
        this.setDescIdPersonal(idPersonal);
        this.setDescIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setDescDeOrigen(descDeOrigen);
        if (descDeOrigen.trim().equals("Osinergmin")) {
            this.setDescNoPersonaDoc(DescNoPersonaDocOsinergmin);
            this.setDescNoCompletoPersona(descNombreOsinergmin);
        } else {
            this.setDescNoPersonaDoc(DescNoPersonaDocContrato);
            this.setDescNoCompletoPersona(descNombreContrato);
        }
        this.setNoCargoPersonaEquipo(noCargoPersonaEquipo);
        this.setNoPrefijoPersonaEquipo(noPrefijoPersonaEquipo);
        this.setNoPerfilPersonaEquipo(noPerfilPersonaEquipo);
        this.setIdRolProceso(idRolProceso);
        this.setDescNoTipoDocIdentidad(descNoTipoDocIdentidadContrato);
        this.setDescNoTipoDocIdentidadOsi(descNoTipoDocIdentidadOsinergmin);
        this.setDescNoUsuario(descNoUsuario);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository#obtenerPersonalXRolContrato()
     * 
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     * @param descCoDocumentoIdentidad
     * @param descNoRolProceso
     * @param noCargoPersonaEquipo
     * @param noPrefijoPersonaEquipo
     * @param noPerfilPersonaEquipo
     * @param descNoUsuario
     */
    public PgimEqpInstanciaProDTOResultado(String descNoPersona, String descApPaterno, String descApMaterno,
            String descCoDocumentoIdentidad, String descNoRolProceso, String noCargoPersonaEquipo, 
            String noPrefijoPersonaEquipo, String noPerfilPersonaEquipo, String descNoUsuario ) {
        super();
        this.setDescNoPersona(descNoPersona);
        this.setDescApPaterno(descApPaterno);
        this.setDescApMaterno(descApMaterno);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRolProceso(descNoRolProceso);
        this.setNoCargoPersonaEquipo(noCargoPersonaEquipo);
        this.setNoPrefijoPersonaEquipo(noPrefijoPersonaEquipo);
        this.setNoPerfilPersonaEquipo(noPerfilPersonaEquipo);
        this.setDescNoUsuario(descNoUsuario);
    }

}