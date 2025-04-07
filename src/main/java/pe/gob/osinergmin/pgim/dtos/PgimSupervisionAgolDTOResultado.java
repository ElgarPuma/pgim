package pe.gob.osinergmin.pgim.dtos;

public class PgimSupervisionAgolDTOResultado extends PgimSupervisionAgolDTO {
	
	/**
     *  
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionAgolRepository#listarRegistrosPorSupervision()
     *    
     */
    //public PgimSupervisionAgolDTOResultado(Long idSupervisionAgol, Long idObject, Long idSupervision) {
	public PgimSupervisionAgolDTOResultado(Long idSupervisionAgol, Long idSupervision) {
        super();
        this.setIdSupervisionAgol(idSupervisionAgol);
        //this.setIdObject(idObject);
        this.setIdSupervision(idSupervision);        
    }

}
