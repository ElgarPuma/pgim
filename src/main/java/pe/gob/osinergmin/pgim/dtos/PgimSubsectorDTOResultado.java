package pe.gob.osinergmin.pgim.dtos;

public class PgimSubsectorDTOResultado extends PgimSubsectorDTO {
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SubsectorRepository#listaSubsectores()
     * @param idSubsector
     * @param noSubsector
     * @param idSector
     */
    public PgimSubsectorDTOResultado(Long idSubsector, String noSubsector, Long idSector){
        super();
        this.setIdSubsector(idSubsector);
        this.setNoSubsector(noSubsector);
        this.setIdSector(idSector);
    }
}
