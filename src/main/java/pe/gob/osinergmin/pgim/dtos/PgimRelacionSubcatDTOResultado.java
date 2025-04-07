package pe.gob.osinergmin.pgim.dtos;

public class PgimRelacionSubcatDTOResultado extends PgimRelacionSubcatDTO {

    /**
     * Portador de la relación de subcategorías por relación de paso. En el
     * repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionSubcatRepository#listarSubCategoriasNoCargadas()
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionSubcatRepository#listarSubCategoriasAlmenosUnoCargadas()
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionSubcatRepository#listarSubCategoriasAlmenosUnoNoCargadas()
     * 
     * @param idRelacionSubcat
     * @param idRelacionPaso
     * @param idSubcatDocumento
     * @param flSubcatDocRequerido
     * @param descCoSubcatDocumento
     * @param descNoSubcatDocumento
     * @param descCoCategoriaDocumento
     * @param descNoCategoriaDocumento
     * @param descIdEspecialidad
     * @param flValidaReservaActiva
     */
    public PgimRelacionSubcatDTOResultado(Long idRelacionSubcat, Long idRelacionPaso, Long idSubcatDocumento,
            String flSubcatDocRequerido, String descCoSubcatDocumento, String descNoSubcatDocumento,
            String descCoCategoriaDocumento, String descNoCategoriaDocumento, Long descIdEspecialidad, String flValidaReservaActiva) {
        super();

        this.setIdRelacionSubcat(idRelacionSubcat);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setFlSubcatDocRequerido(flSubcatDocRequerido);

        this.setDescCoSubcatDocumento(descCoSubcatDocumento);
        this.setDescNoSubcatDocumento(descNoSubcatDocumento);
        this.setDescCoCategoriaDocumento(descCoCategoriaDocumento);
        this.setDescNoCategoriaDocumento(descNoCategoriaDocumento);
        this.setDescIdEspecialidad(descIdEspecialidad);
        this.setFlValidaReservaActiva(flValidaReservaActiva);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionSubcatRepository#obtenerConfigRelacionSubcat()
     * 
     * @param idRelacionSubcat
     * @param idRelacionPaso
     * @param idSubcatDocumento
     * @param flSubcatDocRequerido
     * @param flValidaReservaActiva
     * @param flRegistrarFechaEnvio
     * @param flActualizarFechaEnvio
     */
    public PgimRelacionSubcatDTOResultado(Long idRelacionSubcat, Long idRelacionPaso, Long idSubcatDocumento,
            String flSubcatDocRequerido, String flValidaReservaActiva, String flRegistrarFechaEnvio, String flActualizarFechaEnvio) {
        super();

        this.setIdRelacionSubcat(idRelacionSubcat);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setFlSubcatDocRequerido(flSubcatDocRequerido);
        this.setFlValidaReservaActiva(flValidaReservaActiva);
        this.setFlRegistrarFechaEnvio(flRegistrarFechaEnvio);
        this.setFlActualizarFechaEnvio(flActualizarFechaEnvio);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionSubcatRepository#listarConfigRelacionSubcatValidaNotifResponsables()
     * 
     * @param idRelacionSubcat
     * @param idRelacionPaso
     * @param idSubcatDocumento
     * @param flValidarNotifResponsable
     */
    public PgimRelacionSubcatDTOResultado(Long idRelacionSubcat, Long idRelacionPaso, Long idSubcatDocumento,
            String noSubcatDocumento, String flValidarNotifResponsable) {
        super();

        this.setIdRelacionSubcat(idRelacionSubcat);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setDescNoSubcatDocumento(noSubcatDocumento);
        this.setFlValidarNotifResponsable(flValidarNotifResponsable);
    }

}