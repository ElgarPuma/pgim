package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimInstanciaPasoDocDTOResultado extends PgimInstanciaPasoDocDTO {

    /**
     * 
     * @param idInstanciaPasoDoc
     * @param idInstanciaPaso
     * @param idDocumento
     * @param feTransicionPasoDoc
     * @param deMensaje
     */
    public PgimInstanciaPasoDocDTOResultado(Long idInstanciaPasoDoc, Long idInstanciaPaso, Long idDocumento,
            Date feTransicionPasoDoc, String deMensaje) {
        super();

        this.setIdInstanciaPasoDoc(idInstanciaPasoDoc);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdDocumento(idDocumento);
        this.setFeTransicionPasoDoc(feTransicionPasoDoc);
        this.setDeMensaje(deMensaje);
    }

}
