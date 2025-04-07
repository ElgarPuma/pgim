package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimInfraccionAuxDTOResultado extends PgimInfraccionAuxDTO {
	
	
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InfraccionAuxRepository#obtenerInfraccionAuxPorId
	 * 
	 * @param idInfraccion
	 * @param idSupervision
	 * @param rucAs
	 * @param noRazonSocialAs
	 * @param coUnidadMinera
	 * @param noUnidadMinera
	 */
	public PgimInfraccionAuxDTOResultado(Long idInfraccion, Long idOblgcnNrmtvaHchoc, Long idSupervision, 
			String flIncluirEnPas, String deSustento, Date feComisionDeteccion, 
			BigDecimal moMultaUit, Long idInstanciaPaso, Long idPas, 
			String rucAs, String noRazonSocialAs, String coUnidadMinera, String noUnidadMinera) {
        super();

        this.setIdInfraccionAux(idInfraccion);       
        this.setIdInfraccion(idInfraccion);       
        this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
        this.setIdSupervision(idSupervision);
        this.setFlIncluirEnPas(flIncluirEnPas);
        this.setDeSustento(deSustento);
        this.setFeComisionDeteccion(feComisionDeteccion);
        this.setMoMultaUit(moMultaUit);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdPas(idPas);
        this.setRucAs(rucAs);
        this.setNoRazonSocialAs(noRazonSocialAs);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
    }

    /**
     * Permite portar estas propiedades a la lista de infracciones Método del
     * repository:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InfraccionRepository#listarInfraccionPorIdSupervision()
     * @see pe.gob.osinergmin.pgim.models.repository.InfraccionRepository#listarInfraccionByIdSupervision()
     * @see pe.gob.osinergmin.pgim.models.repository.InfraccionRepository#listarInfraccionPorIdPas()
     * 
     * @param idInfraccion
     * @param coTipificacion
     * @param noItemTipificacion
     * @param deBaseLegal
     * @param flIncluirEnPas
     * @param deSancionPecuniariaUit
     * @param deSustento
     * @param descCoMatrizCriterio
     * @param descDeMatrizCriterio
     * @param descDeBaseLegal
     * @param descDeHechoConstatado
     * @param descDeComplementoObservacion
     * @param descDeSustento
     * @param descTipoCumplimiento
     * @param descNoCortoNorma
     * @param moMultaUit
     * @param feComisionDeteccion
     */
    public PgimInfraccionAuxDTOResultado(Long idInfraccion, String coTipificacion, String noItemTipificacion,
            String deBaseLegal, String flIncluirEnPas, String deSancionPecuniariaUit, String deSustento,
            String descCoMatrizCriterio, String descDeMatrizCriterio, String descDeBaseLegal,
            String descDeHechoConstatado, String descDeComplementoObservacion, String descDeSustento,
            String descTipoCumplimiento, String descNoCortoNorma, BigDecimal moMultaUit, 
            Date feComisionDeteccion, Long idPasoProceso, Long idFaseProceso) {
        super();

        this.setIdInfraccionAux(idInfraccion);
        this.setCoTipificacion(coTipificacion);
        this.setNoItemTipificacion(noItemTipificacion);
        this.setDeBaseLegal(deBaseLegal);
        this.setDeSancionPecuniariaUit(deSancionPecuniariaUit);
        this.setFlIncluirEnPas(flIncluirEnPas);
        this.setDeSustento(deSustento);

        this.setDescCoMatrizCriterio(descCoMatrizCriterio);
        this.setDescDeMatrizCriterio(descDeMatrizCriterio);
        this.setDescDeBaseLegal(descDeBaseLegal);

        this.setDescDeHechoConstatado(descDeHechoConstatado);
        this.setDescDeComplementoObservacion(descDeComplementoObservacion);
        this.setDescDeSustento(descDeSustento);
        this.setDescTipoCumplimiento(descTipoCumplimiento);
        this.setDescNoCortoNorma(descNoCortoNorma);

        this.setMoMultaUit(moMultaUit);
        this.setFeComisionDeteccion(feComisionDeteccion);
        this.setIdPasoProceso(idPasoProceso);
        this.setIdFaseProceso(idFaseProceso);
    }

    /**
     * Permite portar estas propiedades a la lista de infracciones Método del
     * repository:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InfraccionRepository#listarInfraccionPorIdSupervision()
     * 
     * @param idInfraccionAux
     * @param idInfraccion
     * @param idSupervision
     * @param idPas
     * @param idOblgcnNrmtvaHchoc
     * @param idInstanciaPaso
     * @param idInfraccionOrigen
     * @param coTipificacion
     * @param noItemTipificacion
     * @param deBaseLegal
     * @param flIncluirEnPas
     * @param deSancionPecuniariaUit
     * @param deSustento
     * @param feAnioInfraccion
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param rucAs
     * @param noRazonSocialAs
     * @param noCortoAs
     * @param feComisionDeteccion
     * @param moMultaUit
     */
    public PgimInfraccionAuxDTOResultado(Long idInfraccionAux, Long idInfraccion, Long idSupervision, Long idPas,
            Long idOblgcnNrmtvaHchoc, Long idInstanciaPaso, Long idInfraccionOrigen, String coTipificacion,
            String noItemTipificacion, String deBaseLegal, String flIncluirEnPas, String deSancionPecuniariaUit,
            String deSustento, Long feAnioInfraccion, String coUnidadMinera, String noUnidadMinera, String rucAs,
            String noRazonSocialAs, String noCortoAs, Date feComisionDeteccion, BigDecimal moMultaUit) {
        super();

        this.setIdInfraccionAux(idInfraccionAux);
        this.setIdInfraccion(idInfraccion);
        this.setIdSupervision(idSupervision);
        this.setIdPas(idPas);
        this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdInfraccionOrigen(idInfraccionOrigen);
        this.setCoTipificacion(coTipificacion);
        this.setNoItemTipificacion(noItemTipificacion);
        this.setDeBaseLegal(deBaseLegal);
        this.setFlIncluirEnPas(flIncluirEnPas);
        this.setDeSancionPecuniariaUit(deSancionPecuniariaUit);
        this.setDeSustento(deSustento);
        this.setFeAnioInfraccion(feAnioInfraccion);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setRucAs(rucAs);
        this.setNoRazonSocialAs(noRazonSocialAs);
        this.setNoCortoAs(noCortoAs);
        this.setFeComisionDeteccion(feComisionDeteccion);
        this.setMoMultaUit(moMultaUit);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.InfraccionAuxRepository#listaInfraccionPorUM()
     * @param coSupervision
     * @param coPas
     * @param coTipificacion
     * @param noItemTipificacion
     * @param deSancionPecuniariaUit
     * @param deNormaObligacionT
     */
    public PgimInfraccionAuxDTOResultado(String coSupervision, String coPas, String coTipificacion, String noItemTipificacion, 
        String deSancionPecuniariaUit, String deObligacionNormativa ) {
        super();
        this.setDescCoSupervision(coSupervision);
        this.setDescCoPas(coPas);
        this.setCoTipificacion(coTipificacion);
        this.setNoItemTipificacion(noItemTipificacion);
        this.setDeSancionPecuniariaUit(deSancionPecuniariaUit);
        this.setDescDeObligacionNormativa(deObligacionNormativa);
    }
}