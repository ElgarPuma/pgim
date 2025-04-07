package pe.gob.osinergmin.pgim.dtos;

public class PgimTipoParametroMedDTOResultado extends PgimTipoParametroMedDTO{

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PgimTipoParametroMedRepository#listarParamMedicion()
     * @param idTipoParametroMed
     * @param coTipoParametro
     * @param noTipoParametro
     * @param deTipoParametro
     */
    public PgimTipoParametroMedDTOResultado(Long idTipoParametroMed, String coTipoParametro, 
        String noTipoParametro, String deTipoParametro, Long descNumUsos){
        super();
        this.setIdTipoParametroMed(idTipoParametroMed);
        this.setCoTipoParametro(coTipoParametro);
        this.setNoTipoParametro(noTipoParametro);
        this.setDeTipoParametro(deTipoParametro);
        this.setDescNumUsos(descNumUsos);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PgimTipoParametroMedRepository#obtenerIdParamMedicion()
     * @param idTipoParametroMed
     * @param coTipoParametro
     * @param noTipoParametro
     * @param deTipoParametro
     */
    public PgimTipoParametroMedDTOResultado(Long idTipoParametroMed, String coTipoParametro, 
        String noTipoParametro, String deTipoParametro){
        super();
        this.setIdTipoParametroMed(idTipoParametroMed);
        this.setCoTipoParametro(coTipoParametro);
        this.setNoTipoParametro(noTipoParametro);
        this.setDeTipoParametro(deTipoParametro);
    }
    
}
