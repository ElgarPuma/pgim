package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PgimItemProgramaSupeDTOResultado extends PgimItemProgramaSupeDTO {

    /**
     @see pe.gob.osinergmin.pgim.models.repository.ItemProgramaSupeRepository#listarItemsProgramaPendientes()
     * @param idItemProgramaSupe
     * @param idLineaPrograma
     * @param idSubtipoSupervision
     * @param descSubtipoSupervision
     * @param idUnidadMinera
     * @param descCoUnidadMinera
     * @param descNoUnidadMinera
     * @param feMesEstimado
     * @param moCostoEstimadoSupervision
     * @param descIdAgenteSupervisado
     * @param descNoRazonSocial
     * @param descSituacionUm
     * @param descDivisionSupervision
     * @param descMetodoMinado
     */
    public PgimItemProgramaSupeDTOResultado(Long idItemProgramaSupe, Long idLineaPrograma,
            Long idSubtipoSupervision, String descSubtipoSupervision, Long idUnidadMinera, String descCoUnidadMinera,
            String descNoUnidadMinera, Date feMesEstimado, BigDecimal moCostoEstimadoSupervision,
            Long descIdAgenteSupervisado, String descNoRazonSocial, String descSituacionUm,
            String descDivisionSupervision, String descMetodoMinado, String descNoTipoactividad,
            String descNoEstadoUm) {
        super();

        this.setIdItemProgramaSupe(idItemProgramaSupe);
        this.setIdLineaPrograma(idLineaPrograma);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescSubtipoSupervision(descSubtipoSupervision);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescCoUnidadMinera(descCoUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setFeMesEstimado(feMesEstimado);
        this.setMoCostoEstimadoSupervision(moCostoEstimadoSupervision);
        this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescSituacionUm(descSituacionUm);
        this.setDescDivisionSupervision(descDivisionSupervision);
        this.setDescMetodoMinado(descMetodoMinado);
        this.setDescNoTipoactividad(descNoTipoactividad);
        this.setDescNoEstadoUm(descNoEstadoUm);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ItemProgramaSupeRepository#listarItemsProgramas()
     * 
     * @param idItemProgramaSupe
     * @param idProgramaSupervision
     * @param idLineaPrograma
     * @param idSubtipoSupervision
     * @param descSubtipoSupervision
     * @param idUnidadMinera
     * @param descCoUnidadMinera
     * @param descNoUnidadMinera
     * @param feMesEstimado
     * @param moCostoEstimadoSupervision
     * @param descIdAgenteSupervisado
     * @param descNoRazonSocial
     * @param descFlAsignado
     * @param idSupervision
     * @param descDivisionSupervision
     * @param descMetodoMinado
     * @param descSituacionUm
     * @param descNoTipoactividad
     * @param descNoEstadoUm
     * @param descFlRegistraRiesgos
     * @param descDeConfiguracionesRiesgo
     */
    public PgimItemProgramaSupeDTOResultado(Long idItemProgramaSupe, Long idProgramaSupervision, Long idLineaPrograma,
            Long idSubtipoSupervision, String descSubtipoSupervision, Long idUnidadMinera, String descCoUnidadMinera,
            String descNoUnidadMinera, Date feMesEstimado, BigDecimal moCostoEstimadoSupervision,
            Long descIdAgenteSupervisado, String descNoRazonSocial, String descFlAsignado, 
            Long idSupervision, String descDivisionSupervision, String descMetodoMinado,
            String descSituacionUm, String descNoTipoactividad, String descNoEstadoUm,
            String descFlRegistraRiesgos, String descDeConfiguracionesRiesgo) {
        super();

        this.setIdItemProgramaSupe(idItemProgramaSupe);
        this.setIdLineaPrograma(idLineaPrograma);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescSubtipoSupervision(descSubtipoSupervision);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescCoUnidadMinera(descCoUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setFeMesEstimado(feMesEstimado);
        this.setMoCostoEstimadoSupervision(moCostoEstimadoSupervision);
        this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescFlAsignado(descFlAsignado);
        this.setIdSupervision(idSupervision);
        this.setDescDivisionSupervision(descDivisionSupervision);
        this.setDescMetodoMinado(descMetodoMinado);
        this.setDescSituacionUm(descSituacionUm);
        this.setDescNoTipoactividad(descNoTipoactividad);
        this.setDescNoEstadoUm(descNoEstadoUm);
        
        this.setDescFlRegistraRiesgos(descFlRegistraRiesgos);
        this.setDescDeConfiguracionesRiesgo(descDeConfiguracionesRiesgo);
        
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("MM-yyyy");
        dateString = sdfr.format( feMesEstimado );
        this.setFeMesEstimadoDesc(dateString);
    }
    
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ItemProgramaSupeRepository#listarItemsPorLB()

     */
    public PgimItemProgramaSupeDTOResultado(Long idItemProgramaSupe, Long idLineaPrograma,
            Long idSubtipoSupervision, String descSubtipoSupervision, Long idUnidadMinera, String descCoUnidadMinera,
            String descNoUnidadMinera, Date feMesEstimado, BigDecimal moCostoEstimadoSupervision,
            Long descIdAgenteSupervisado, String descNoRazonSocial, String descFlAsignado,
            Long idSupervision,String deItemPrograma) {
        super();

        this.setIdItemProgramaSupe(idItemProgramaSupe);
        this.setIdLineaPrograma(idLineaPrograma);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescSubtipoSupervision(descSubtipoSupervision);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescCoUnidadMinera(descCoUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setFeMesEstimado(feMesEstimado);
        this.setMoCostoEstimadoSupervision(moCostoEstimadoSupervision);
        this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescFlAsignado(descFlAsignado);
        this.setIdSupervision(idSupervision);
        this.setDeItemPrograma(deItemPrograma);
    }

}
