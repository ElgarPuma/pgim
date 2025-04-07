package pe.gob.osinergmin.pgim.dtos;

public class PgimSubactividadDTOResultado extends PgimSubactividadDTO {
    
    public PgimSubactividadDTOResultado (Long idSubactividad, Long idActividad, String noSubactividad){
        super();
        this.setIdSubactividad(idSubactividad);
        this.setIdActividad(idActividad);
        this.setNoSubactividad(noSubactividad);
    }
}
