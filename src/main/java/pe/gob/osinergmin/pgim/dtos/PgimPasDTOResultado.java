package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;
public class PgimPasDTOResultado extends PgimPasDTO {

    /**
     * Permite portar los datos de PAS.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#obtenerPasPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#obtenerPasByidInstanciaPaso()
     * @param idPas
     * @param idSupervision
     * @param idInstanciaProceso
	 * @param coPas
	 * @param descCoSupervision
	 * @param descNoUnidadMinera
	 * @param descNuExpedienteSiged
	 * @param feCreacionPas
	 * @param descNoEspecialidad
	 * @param descNoAgenteSupervisado
	 * @param descFeCreacionPasAnio
	 * @param nuAlertas
	 * @param nuIntervaloAlertas
	 */
    public PgimPasDTOResultado(Long idPas, Long idSupervision, Long idInstanciaProceso, String coPas, String descCoSupervision,
    String descNoUnidadMinera, String descNuExpedienteSiged, Date feCreacionPas,
    String descNoEspecialidad, String descNoAgenteSupervisado, String descFeCreacionPasAnio,
    Long nuAlertas, Long nuIntervaloAlertas) {
        super();
        
        this.setIdPas(idPas);
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setCoPas(coPas);
        this.setDescCoSupervision(descCoSupervision);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setFeCreacionPas(feCreacionPas);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescNoAgenteSupervisado(descNoAgenteSupervisado);
        this.setDescFeCreacionPasAnio(descFeCreacionPasAnio);
        this.setNuAlertas(nuAlertas);
        this.setNuIntervaloAlertas(nuIntervaloAlertas);
    }

    /**
     * Permite potar los datos de PAS.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#listarPorNuExpedienteSiged()
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#obtenerPasPorIdSupervision()
     * 
     * @param idPas
     * @param idInstanciaProceso
     * @param descNuExpedienteSiged
     */
    public PgimPasDTOResultado(Long idPas, Long idInstanciaProceso, 
    String descNuExpedienteSiged) {
        super();
        this.setIdPas(idPas);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
    }

    /**
     * Permite potar los datos de PAS.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#listarPorUnidadMinera()
     * 
     * @param idPas
     * @param descNoUnidadMinera
     */
    public PgimPasDTOResultado(Long idPas, String descNoUnidadMinera) {
        super();
        this.setIdPas(idPas);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
    }

    /**
     * Permite potar los datos de PAS.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#
     * 
     * @param idPas
     * @param idSupervision
     * @param idInstanciaProceso
     * @param feCreacionPas
     */
    public PgimPasDTOResultado(Long idPas, Long idSupervision, Long idInstanciaProceso, Date feCreacionPas) {
        super();
        this.setIdPas(idPas);
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setFeCreacionPas(feCreacionPas);
    }

    /**
     * Permite potar los datos de PAS.
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#listarPorCoSupervision
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#obtenerPasPorIdPas
     * @see pe.gob.osinergmin.pgim.models.repository.PasRepository#obtenerPasByidInstanciaProceso
     * 
     * @param idPas
     * @param descCoSupervision
     * @param descNoUnidadMinera
     */
    public PgimPasDTOResultado(Long idPas, Long idSupervision, String descCoSupervision, String descNoUnidadMinera) {
        super();
        this.setIdPas(idPas);
        this.setIdSupervision(idSupervision);
        this.setDescCoSupervision(descCoSupervision);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
    }
}
