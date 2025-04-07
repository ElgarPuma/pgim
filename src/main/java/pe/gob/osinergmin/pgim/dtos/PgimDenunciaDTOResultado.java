package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimDenunciaDTOResultado extends PgimDenunciaDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DenunciaRepository#listarDenuncia()
     * @see pe.gob.osinergmin.pgim.models.repository.DenunciaRepository#listarDenunciaGeneral()
     */
    public PgimDenunciaDTOResultado(Long idDenuncia, Long idUnidadMinera, String descNoUnidadMinera, String coDenuncia,
            String deDenuncia, Date fePresentacion, Long descIdTipoDocIdentidad, String descNoPersonaDenunciante,
            String descMedioDenuncia) {
        super();
        this.setIdDenuncia(idDenuncia);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setCoDenuncia(coDenuncia);
        this.setDeDenuncia(deDenuncia);
        this.setFePresentacion(fePresentacion);
        this.setDescIdTipoDocIdentidad(descIdTipoDocIdentidad);
        this.setDescNoPersonaDenunciante(descNoPersonaDenunciante);
        this.setDescMedioDenuncia(descMedioDenuncia);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DenunciaRepository#obtenerDenunciaPorId()
     */
    public PgimDenunciaDTOResultado(Long idDenuncia, Long idUnidadMinera, Long idMedioDenuncia,
            Long idPersonaDenunciante, String descMedioDenuncia, String descNoPersonaDenunciante, String coDenuncia,
            Date fePresentacion, String deDenuncia, String descNoUnidadMinera, Long idInstanciaProceso, String descNuExpedienteSiged) {
        super();
        this.setIdDenuncia(idDenuncia);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setIdMedioDenuncia(idMedioDenuncia);
        this.setIdPersonaDenunciante(idPersonaDenunciante);
        this.setDescMedioDenuncia(descMedioDenuncia);
        this.setDescNoPersonaDenunciante(descNoPersonaDenunciante);
        this.setCoDenuncia(coDenuncia);
        this.setFePresentacion(fePresentacion);
        this.setDeDenuncia(deDenuncia);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DenunciaRepository#listarPorPersona
     */
    public PgimDenunciaDTOResultado(Long idPersonaDenunciante, String descNoPersonaDenunciante) {
        super();
        this.setIdPersonaDenunciante(idPersonaDenunciante);
        this.setDescNoPersonaDenunciante(descNoPersonaDenunciante);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DenunciaRepository#obtenerMaxCodigoDenuncia()
     */
    public PgimDenunciaDTOResultado(String descMaxCoDenuncia) {
        super();
        this.setDescMaxCoDenuncia(descMaxCoDenuncia);
    }
}
