package pe.gob.osinergmin.pgim.dtos;

public class PgimUnidadOrganicaDTOResultado extends PgimUnidadOrganicaDTO {
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadOrganicaRepository#listarUnidadOrganica()
     * @param idUnidadOrganica
     * @param coUnidadOrganica
     * @param noUnidadOrganica
     * @param coUsuarioSigedNumerador
     */
    public PgimUnidadOrganicaDTOResultado(Long idUnidadOrganica, String coUnidadOrganica, String noUnidadOrganica, Long coUsuarioSigedNumerador, String noUsuarioSigedNumerador) {
        super();
        this.setIdUnidadOrganica(idUnidadOrganica);
        this.setCoUnidadOrganica(coUnidadOrganica);
        this.setNoUnidadOrganica(noUnidadOrganica);
        this.setCoUsuarioSigedNumerador(coUsuarioSigedNumerador);
        this.setNoUsuarioSigedNumerador(noUsuarioSigedNumerador);
    }
}
