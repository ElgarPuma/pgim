package pe.gob.osinergmin.pgim.dtos;

public class PgimDestinatarioDocDTOResultado extends PgimDestinatarioDocDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DestinatarioDocRepository#listarDestinatarioDoc
     * @param idDestinatarioDoc
     * @param idDocumento
     * @param idDocumentoConstancia
     * @param idPersona
     */
    public PgimDestinatarioDocDTOResultado(Long idDestinatarioDoc, Long idDocumento, Long idDocumentoConstancia,
            Long idPersona, String descNoSubcatDocumento, String descNoSubcatDocumentoConstancia,
            String descNoRazonSocial) {
        super();
        this.setIdDestinatarioDoc(idDestinatarioDoc);
        this.setIdDocumento(idDocumento);
        this.setIdDocumentoConstancia(idDocumentoConstancia);
        this.setIdPersona(idPersona);
        this.setDescNoSubcatDocumento(descNoSubcatDocumento);
        this.setDescNoSubcatDocumentoConstancia(descNoSubcatDocumentoConstancia);
        this.setDescNoRazonSocial(descNoRazonSocial);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DestinatarioDocRepository#listarDestinatarioDocPorIdDocumento()
     * 
     * @param idDestinatarioDoc
     * @param idDocumento
     * @param idDocumentoConstancia
     * @param idPersona
     * @param descNoSubcatDocumento
     * @param descNoSubcatDocumentoConstancia
     * @param descNoRazonSocial
     * @param coDocumentoIdentidad
     */
    public PgimDestinatarioDocDTOResultado(Long idDestinatarioDoc, Long idDocumento, Long idDocumentoConstancia,
            Long idPersona, String descNoSubcatDocumento, String descNoSubcatDocumentoConstancia,
            String descNoRazonSocial, String coDocumentoIdentidad) {
        super();
        this.setIdDestinatarioDoc(idDestinatarioDoc);
        this.setIdDocumento(idDocumento);
        this.setIdDocumentoConstancia(idDocumentoConstancia);
        this.setIdPersona(idPersona);
        this.setDescNoSubcatDocumento(descNoSubcatDocumento);
        this.setDescNoSubcatDocumentoConstancia(descNoSubcatDocumentoConstancia);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescCoDocumentoIdentidad(coDocumentoIdentidad);
    }

    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DestinatarioDocRepository#obtenerDestinatarioDoc
     * @see pe.gob.osinergmin.pgim.models.repository.DestinatarioDocRepository#listaDestinatarioPorDoc
     * @see pe.gob.osinergmin.pgim.models.repository.DestinatarioDocRepository#obtenerDestinatarioPorIdDocYper
     * 
     * @param idDestinatarioDoc
     * @param idDocumento
     * @param idDocumentoConstancia
     * @param idPersona
     */
    public PgimDestinatarioDocDTOResultado(Long idDestinatarioDoc, Long idDocumento, Long idDocumentoConstancia,
            Long idPersona) {
        super();
        this.setIdDestinatarioDoc(idDestinatarioDoc);
        this.setIdDocumento(idDocumento);
        this.setIdDocumentoConstancia(idDocumentoConstancia);
        this.setIdPersona(idPersona);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DestinatarioDocRepository#listarDestinatarioPorIdDoc
     * 
     * @param idDestinatarioDoc
     * @param idDocumento
     * @param idDocumentoConstancia
     * @param idPersona
     * @param descNoRazonSocial
     * @param descCoDocumentoIdentidad
     */
    public PgimDestinatarioDocDTOResultado(Long idDestinatarioDoc, Long idDocumento, Long idDocumentoConstancia,
            Long idPersona, String descNoRazonSocial, String descCoDocumentoIdentidad) {
        super();
        this.setIdDestinatarioDoc(idDestinatarioDoc);
        this.setIdDocumento(idDocumento);
        this.setIdDocumentoConstancia(idDocumentoConstancia);
        this.setIdPersona(idPersona);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
    }
}
