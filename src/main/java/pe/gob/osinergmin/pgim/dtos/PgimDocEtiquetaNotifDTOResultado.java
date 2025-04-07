package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimDocEtiquetaNotifDTOResultado extends PgimDocEtiquetaNotifDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DocEtiquetaNotifRepository#pgimDocEtiquetaNotifById()
     * @see pe.gob.osinergmin.pgim.models.repository.DocEtiquetaNotifRepository#listarPgimDocEtiquetaNotifByDocAndPaso()
     * @see pe.gob.osinergmin.pgim.models.repository.DocEtiquetaNotifRepository#listarPgimDocEtiquetadosByInstaPaso()
     */
    public PgimDocEtiquetaNotifDTOResultado(Long idDocEtiquetaNotif, Long idDocumento, Long idInstanciaPaso, String flEtiquetaNotifActiva){
        super();
        this.setIdDocEtiquetaNotif(idDocEtiquetaNotif);
        this.setIdDocumentoEtiquetado(idDocumento);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setFlEtiquetaNotifActiva(flEtiquetaNotifActiva);
    }
    
}
