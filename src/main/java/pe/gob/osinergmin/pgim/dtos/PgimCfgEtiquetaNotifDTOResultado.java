package pe.gob.osinergmin.pgim.dtos;

public class PgimCfgEtiquetaNotifDTOResultado extends PgimCfgEtiquetaNotifDTO{

    public PgimCfgEtiquetaNotifDTOResultado(Long idCfgEtiquetaNotif, Long idPasoProceso, Long idRelacionPaso ){
        super();

        this.setIdCfgEtiquetaNotif(idCfgEtiquetaNotif);
        this.setIdPasoProceso(idPasoProceso);
        this.setIdRelacionPaso(idRelacionPaso);

    }
    
}


