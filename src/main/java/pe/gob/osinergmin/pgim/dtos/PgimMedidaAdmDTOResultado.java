package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimMedidaAdmDTOResultado extends PgimMedidaAdmDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#listarMedidaAdministrativaSupervPas
     */
    public PgimMedidaAdmDTOResultado(Long idMedidaAdministrativa, Long idTipoMedidaAdministrativa, String descNuExpedienteSiged, 
            String descNoTipoMedidaAdministrativa, String coMedidaAdministrativa, String deMedidaAdministrativa, 
            Date feMedidaAdministrativa, String descNoTipoObjeto, String descNoUnidadMinera, 
            String descCoSupervision, String descCoSupervisionPas) {
        super();
        
        this.setIdMedidaAdministrativa(idMedidaAdministrativa);
        this.setIdTipoMedidaAdministrativa(idTipoMedidaAdministrativa);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setDescNoTipoMedidaAdministrativa(descNoTipoMedidaAdministrativa);
        this.setCoMedidaAdministrativa(coMedidaAdministrativa);
        this.setDeMedidaAdministrativa(deMedidaAdministrativa);
        this.setFeMedidaAdministrativa(feMedidaAdministrativa);

        this.setDescNoTipoObjeto(descNoTipoObjeto);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescCoSupervision(descCoSupervision);
        this.setDescCoSupervisionPas(descCoSupervisionPas);
    }

    /**
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#listarMedidaAdministrativa
     * 
     * @param idMedidaAdministrativa
     * @param idTipoMedidaAdministrativa
     * @param descNuExpedienteSiged
     * @param descNoTipoMedidaAdministrativa
     * @param coMedidaAdministrativa
     * @param deMedidaAdministrativa
     * @param feMedidaAdministrativa
     * @param descNoTipoObjeto
     * @param descNoUnidadMinera
     * @param descCoSupervision
     * @param descCoSupervisionPas
     * @param descNoPersonaAsignada
     * @param descUsuarioAsignado
     * @param descIdInstanciaPaso
     * @param descFeInstanciaPaso
     * @param descDeMensaje
     * @param descFlPasoActivo
     */
    public PgimMedidaAdmDTOResultado(Long idMedidaAdministrativa, Long idTipoMedidaAdministrativa, String descNuExpedienteSiged, 
            String descNoTipoMedidaAdministrativa, String coMedidaAdministrativa, String deMedidaAdministrativa, 
            Date feMedidaAdministrativa, String descNoTipoObjeto, String descNoUnidadMinera, 
            String descCoSupervision, String descCoSupervisionPas, String descNoPersonaAsignada, 
            String descUsuarioAsignado, Long descIdInstanciaPaso, Date descFeInstanciaPaso, 
            String descDeMensaje, String descFlPasoActivo) {
        super();
        this.setIdMedidaAdministrativa(idMedidaAdministrativa);
        this.setIdTipoMedidaAdministrativa(idTipoMedidaAdministrativa);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setDescNoTipoMedidaAdministrativa(descNoTipoMedidaAdministrativa);
        this.setCoMedidaAdministrativa(coMedidaAdministrativa);
        this.setDeMedidaAdministrativa(deMedidaAdministrativa);
        this.setFeMedidaAdministrativa(feMedidaAdministrativa);

        this.setDescNoTipoObjeto(descNoTipoObjeto);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescCoSupervision(descCoSupervision);
        this.setDescCoSupervisionPas(descCoSupervisionPas);
        this.setDescNoPersonaAsignada(descNoPersonaAsignada);
        this.setDescUsuarioAsignado(descUsuarioAsignado);

        this.setDescIdInstanciaPaso(descIdInstanciaPaso);
        this.setDescFeInstanciaPaso(descFeInstanciaPaso);
        this.setDescDeMensaje(descDeMensaje);
        this.setDescFlPasoActivo(descFlPasoActivo);        
    }

    /**
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#listarMedidaAdministrativaSupervision
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#listarMedidaAdministrativaPas
     * 
     * @param idMedidaAdministrativa
     * @param idTipoMedidaAdministrativa
     * @param descNuExpedienteSiged
     * @param descNoTipoMedidaAdministrativa
     * @param coMedidaAdministrativa
     * @param deMedidaAdministrativa
     * @param feMedidaAdministrativa
     * @param descNoTipoObjeto
     * @param descCoSupervision
     * @param descNoPersonaAsignada
     * @param descIdInstanciaPaso
     */
    public PgimMedidaAdmDTOResultado(Long idMedidaAdministrativa, Long idTipoMedidaAdministrativa, String descNuExpedienteSiged,
            String descNoTipoMedidaAdministrativa, String coMedidaAdministrativa, String deMedidaAdministrativa, 
            Date feMedidaAdministrativa, String descNoTipoObjeto, String descCoSupervision, 
            String descNoPersonaAsignada, Long descIdInstanciaPaso) {
        super();
        this.setIdMedidaAdministrativa(idMedidaAdministrativa);
        this.setIdTipoMedidaAdministrativa(idTipoMedidaAdministrativa);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setDescNoTipoMedidaAdministrativa(descNoTipoMedidaAdministrativa);
        this.setCoMedidaAdministrativa(coMedidaAdministrativa);
        this.setDeMedidaAdministrativa(deMedidaAdministrativa);
        this.setFeMedidaAdministrativa(feMedidaAdministrativa);

        this.setDescNoTipoObjeto(descNoTipoObjeto);
        this.setDescCoSupervision(descCoSupervision);
        this.setDescNoPersonaAsignada(descNoPersonaAsignada);

        this.setDescIdInstanciaPaso(descIdInstanciaPaso);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#listarMedidaAdministrativaUm
     */
    public PgimMedidaAdmDTOResultado(Long idMedidaAdministrativa, Long idTipoMedidaAdministrativa,
            String descNoTipoMedidaAdministrativa, String coMedidaAdministrativa, String deMedidaAdministrativa,
            Date feMedidaAdministrativa, String descNoTipoObjeto, String descNoUnidadMinera, String descNoPersonaAsignada,  
            Long descIdInstanciaPaso) {
        super();
        this.setIdMedidaAdministrativa(idMedidaAdministrativa);
        this.setIdTipoMedidaAdministrativa(idTipoMedidaAdministrativa);
        this.setDescNoTipoMedidaAdministrativa(descNoTipoMedidaAdministrativa);
        this.setCoMedidaAdministrativa(coMedidaAdministrativa);
        this.setDeMedidaAdministrativa(deMedidaAdministrativa);
        this.setFeMedidaAdministrativa(feMedidaAdministrativa);

        this.setDescNoTipoObjeto(descNoTipoObjeto);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescNoPersonaAsignada(descNoPersonaAsignada);
        
        this.setDescIdInstanciaPaso(descIdInstanciaPaso);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#listarPorCoMedidaAdministrativa
     */
    public PgimMedidaAdmDTOResultado(Long idMedidaAdministrativa, String coMedidaAdministrativa) {
        super();
        this.setIdMedidaAdministrativa(idMedidaAdministrativa);
        this.setCoMedidaAdministrativa(coMedidaAdministrativa);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#listarPorNumeroExpediente
     */
    public PgimMedidaAdmDTOResultado(Long idMedidaAdministrativa, Long idInstanciaProceso,
            String descNuExpedienteSiged) {
        super();
        this.setIdMedidaAdministrativa(idMedidaAdministrativa);
        this.setIdInstanciaProceso(idInstanciaProceso);
        ;
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#obtenerMedidaAdministrativaPorId
     */
    public PgimMedidaAdmDTOResultado(Long idMedidaAdministrativa, Long idTipoMedidaAdministrativa, Long idInstanciaProceso, String descNuExpedienteSiged, String descNoTipoMedidaAdministrativa,
            String coMedidaAdministrativa, String deMedidaAdministrativa, Date feMedidaAdministrativa,
            Long idUnidadMinera, String descNoUnidadMinera, Long idTipoObjeto, Long idSupervision,
            String descCoSupervision, Long idPas, String descCoSupervisionPas) {
        super();
        this.setIdMedidaAdministrativa(idMedidaAdministrativa);
        this.setIdTipoMedidaAdministrativa(idTipoMedidaAdministrativa);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setDescNoTipoMedidaAdministrativa(descNoTipoMedidaAdministrativa);
        this.setCoMedidaAdministrativa(coMedidaAdministrativa);
        this.setDeMedidaAdministrativa(deMedidaAdministrativa);
        this.setFeMedidaAdministrativa(feMedidaAdministrativa);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setIdTipoObjeto(idTipoObjeto);
        this.setIdSupervision(idSupervision);
        this.setDescCoSupervision(descCoSupervision);
        this.setIdPas(idPas);
        this.setDescCoSupervisionPas(descCoSupervisionPas);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository#obtenerMedidaAdministrativaPorIdPas
     */
    public PgimMedidaAdmDTOResultado(Long idMedidaAdministrativa, Long idPas, String descCoSupervisionPas, String fin) {
        super();
        this.setIdMedidaAdministrativa(idMedidaAdministrativa);
        this.setIdPas(idPas);
        this.setDescCoSupervisionPas(descCoSupervisionPas);
    }
}
