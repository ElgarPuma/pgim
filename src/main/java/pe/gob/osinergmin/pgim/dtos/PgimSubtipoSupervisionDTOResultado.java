package pe.gob.osinergmin.pgim.dtos;

public class PgimSubtipoSupervisionDTOResultado extends PgimSubtipoSupervisionDTO {

    /**
     * Este constructor sirve para filtrar por palabra clave el tipo de supervision
     * @param idSubtipoSupervision
     * @param descTipoSupervision
     */
    public PgimSubtipoSupervisionDTOResultado( Long idSubtipoSupervision, Long idTipoSupervision, String descTipoSupervision) 
    {
        super();
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setIdTipoSupervision(idTipoSupervision);
        this.setDescTipoSupervision(descTipoSupervision);
    }

    /**
     * Permite obtener la lista de subtipo de supervision usado en el dialogo asignar supervision, depende del tipo de supervision
     * @param idSubtipoSupervision
     * @param descTipoSupervision
     * @param idTipoSupervision
     */
    public PgimSubtipoSupervisionDTOResultado(Long idSubtipoSupervision, String descTipoSupervision,
    		Long idTipoSupervision) {
        super();
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescTipoSupervision(descTipoSupervision);
        this.setIdTipoSupervision(idTipoSupervision);
    }
    
    /**
     * Permite obtener la lista de subtipo de supervision usado en el dialogo asignar supervision, depende del tipo de supervision
     * @param idSubtipoSupervision
     * @param descTipoSupervision
     * @param idTipoSupervision
     * @param idEspecialidad
     */
    public PgimSubtipoSupervisionDTOResultado(Long idSubtipoSupervision, String descTipoSupervision,
    		Long idTipoSupervision, Long idEspecialidad) {
        super();
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescTipoSupervision(descTipoSupervision);
        this.setIdTipoSupervision(idTipoSupervision);
        this.setIdEspecialidad(idEspecialidad);
    }
}