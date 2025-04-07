package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimCfgMatrizParesDTOResultado extends PgimCfgMatrizParesDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgMatrizParesRepository#obtenerCfgMatrizParesPorIdCfgGrupoRiesgo()
	 * @param idCfgMatrizPares
	 * @param idCfgFactorRiesgoFila
	 * @param idCfgFactorRiesgoColu
	 * @param flEsEditable
	 * @param nuNumerador
	 * @param nuDenominador
	 * @param nuMcp
	 * @param nuMcpn
	 */
	public PgimCfgMatrizParesDTOResultado(Long idCfgMatrizPares, Long idCfgFactorRiesgoFila, Long idCfgFactorRiesgoColu,
			String flEsEditable, BigDecimal nuNumerador, BigDecimal nuDenominador, BigDecimal nuMcp, BigDecimal nuMcpn) {
        super();
        this.setIdCfgMatrizPares(idCfgMatrizPares); 
        this.setIdCfgFactorRiesgoFila(idCfgFactorRiesgoFila); 
        this.setIdCfgFactorRiesgoColu(idCfgFactorRiesgoColu);
        this.setFlEsEditable(flEsEditable);
        this.setNuNumerador(nuNumerador);
        this.setNuDenominador(nuDenominador);
        this.setNuMcp(nuMcp);
        this.setNuMcpn(nuMcpn);
    }

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.CfgMatrizParesRepository#obtenerCfgMatrizParesTCPorIdCfgGrupoRiesgo()
	 * @param idCfgMatrizPares
	 * @param descIdCfgFactorRiesgoFila
	 * @param descNoFactorFila
	 * @param descNuOrdenPrioridadFila
	 * @param descIdCfgFactorRiesgoColu
	 * @param descNoFactorColu
	 * @param descNuOrdenPrioridadColu
	 * @param flEsEditable
	 * @param nuNumerador
	 * @param nuDenominador
	 * @param nuMcp
	 * @param nuMcpn
	 */
    public PgimCfgMatrizParesDTOResultado(Long idCfgMatrizPares, 
    		Long idCfgFactorRiesgoFila, String descNoFactorFila, Long descNuOrdenPrioridadFila,
    		Long idCfgFactorRiesgoColu, String descNoFactorColu, Long descNuOrdenPrioridadColu, 
    		String flEsEditable, BigDecimal nuNumerador, BigDecimal nuDenominador, BigDecimal nuMcp, BigDecimal nuMcpn) {
        super();
        this.setIdCfgMatrizPares(idCfgMatrizPares); 
        this.setIdCfgFactorRiesgoFila(idCfgFactorRiesgoFila);
        this.setDescNoFactorFila(descNoFactorFila); 
        this.setDescNuOrdenPrioridadFila(descNuOrdenPrioridadFila);
        this.setIdCfgFactorRiesgoColu(idCfgFactorRiesgoColu); 
        this.setDescNoFactorColu(descNoFactorColu); 
        this.setDescNuOrdenPrioridadColu(descNuOrdenPrioridadColu);
        this.setFlEsEditable(flEsEditable);
        this.setNuNumerador(nuNumerador);
        this.setNuDenominador(nuDenominador);
        this.setNuMcp(nuMcp);
        this.setNuMcpn(nuMcpn);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.CfgMatrizParesRepository#obtenerCfgMatrizNormalzadaTC
     * @param idCfgMatrizPares
     * @param idCfgFactorRiesgoFila
     * @param descNoFactorFila
     * @param descNuOrdenPrioridadFila
     * @param idCfgFactorRiesgoColu
     * @param descNoFactorColu
     * @param descNuOrdenPrioridadColu
     * @param flEsEditable
     * @param nuNumerador
     * @param nuDenominador
     * @param nuMcp
     * @param nuMcpn
     * @param descNuVectorResultante
     */
    public PgimCfgMatrizParesDTOResultado(Long idCfgMatrizPares, 
    		Long idCfgFactorRiesgoFila, String descNoFactorFila, Long descNuOrdenPrioridadFila,
    		Long idCfgFactorRiesgoColu, String descNoFactorColu, Long descNuOrdenPrioridadColu, 
    		String flEsEditable, BigDecimal nuNumerador, BigDecimal nuDenominador, BigDecimal nuMcp, BigDecimal nuMcpn,
    		BigDecimal descNuVectorResultante) {
        super();
        this.setIdCfgMatrizPares(idCfgMatrizPares); 
        this.setIdCfgFactorRiesgoFila(idCfgFactorRiesgoFila);
        this.setDescNoFactorFila(descNoFactorFila); 
        this.setDescNuOrdenPrioridadFila(descNuOrdenPrioridadFila);
        this.setIdCfgFactorRiesgoColu(idCfgFactorRiesgoColu); 
        this.setDescNoFactorColu(descNoFactorColu); 
        this.setDescNuOrdenPrioridadColu(descNuOrdenPrioridadColu);
        this.setFlEsEditable(flEsEditable);
        this.setNuNumerador(nuNumerador);
        this.setNuDenominador(nuDenominador);
        this.setNuMcp(nuMcp);
        this.setNuMcpn(nuMcpn);
        this.setDescNuVectorResultante(descNuVectorResultante);
    }
}