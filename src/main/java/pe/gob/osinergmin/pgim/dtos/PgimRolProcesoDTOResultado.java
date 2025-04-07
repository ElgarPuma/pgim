package pe.gob.osinergmin.pgim.dtos;

public class PgimRolProcesoDTOResultado extends PgimRolProcesoDTO {

    /**
     * Sirve para portar los datos del rol de proceso. En el repositorio usa el
     * método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository#obtenerRolInteresadoPorInstanciaProceso()
     * 
     * @param idRolProceso
     */
    public PgimRolProcesoDTOResultado(Long idRolProceso) {
        super();

        this.setIdRolProceso(idRolProceso);
    }

    /**
     * Sirve para portar los datos del rol de proceso. En el repositorio usa el
     * método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository#obtenerRolesProceso()
     * @see pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository#obtenerRolesProcesoPorAmbito()
     * @see pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository#listarRolesPersonalContrato()
     * @see pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository#obtenerRolCoordinadorESParaReprogramacion()
     * 
     * @param
     */
    public PgimRolProcesoDTOResultado(Long idRolProceso, String noRolProceso, String coRolProceso, Long idProceso,
            String flSoloOsinergmin, String deRolProceso) {
        super();

        this.setIdRolProceso(idRolProceso);
        this.setNoRolProceso(noRolProceso);
        this.setCoRolProceso(coRolProceso);
        this.setIdProceso(idProceso);
        this.setFlSoloOsinergmin(flSoloOsinergmin);
        this.setDeRolProceso(deRolProceso);

    }

    /**
     * Sirve para portar los datos del rol de proceso. En el repositorio usa el
     * método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository#obtenerRolesProceso()
     * @param
     */
    public PgimRolProcesoDTOResultado(Long idRolProceso, String noRolProceso, String coRolProceso, Long idProceso,
            String flSoloOsinergmin, String deRolProceso, Long idUnidadOrganica, String descNoUnidadOrganica) {
        super();

        this.setIdRolProceso(idRolProceso);
        this.setNoRolProceso(noRolProceso);
        this.setCoRolProceso(coRolProceso);
        this.setIdProceso(idProceso);
        this.setFlSoloOsinergmin(flSoloOsinergmin);
        this.setDeRolProceso(deRolProceso);
        this.setIdUnidadOrganica(idUnidadOrganica);
        this.setDescNoUnidadOrganica(descNoUnidadOrganica);
    }

    public PgimRolProcesoDTOResultado(Long idRolProceso, String noRolProceso, String coRolProceso, Long idProceso,
            String flSoloOsinergmin, Long idUnidadOrganica, String descNoUnidadOrganica) {
        super();

        this.setIdRolProceso(idRolProceso);
        this.setNoRolProceso(noRolProceso);
        this.setCoRolProceso(coRolProceso);
        this.setIdProceso(idProceso);
        this.setFlSoloOsinergmin(flSoloOsinergmin);
        this.setIdUnidadOrganica(idUnidadOrganica);
        this.setDescNoUnidadOrganica(descNoUnidadOrganica);
    }

}