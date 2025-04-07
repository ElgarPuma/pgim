package pe.gob.osinergmin.pgim.dtos;

public class PgimSectorDTOResultado extends PgimSectorDTO {
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SectorRepository#listaSectores()
     * @param idSector
     * @param noSector
     */
    public PgimSectorDTOResultado(Long idSector, String noSector){
        super();
        this.setIdSector(idSector);
        this.setNoSector(noSector);
    }
}
