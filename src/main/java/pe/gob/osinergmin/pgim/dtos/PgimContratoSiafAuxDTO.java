package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_CONTRATO_SIAF_AUX: 
* @descripción: Vista para obtener la lista de SIAF en el contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 08/04/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimContratoSiafAuxDTO {

    /*
     * Identificador interno del detalle del contrato SIAF. Secuencia:
     * PGIM_SEQ_CONTRATO_SIAF
     */
    private Long idContratoSiafAux;

    /*
     * Identificador interno del detalle del contrato SIAF. Secuencia:
     * PGIM_SEQ_CONTRATO_SIAF
     */
    private Long idContratoSiaf;

    /*
     * Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
     */
    private Long idContrato;

    /*
     * Número del SIAF
     */
    private String nuSiaf;

    /*
     * Año al cual pertenece el código SIAF
     */
    private Long nuAnio;

    /*
     * Monto del presupuesto SIAF
     */
    private BigDecimal moPresupuestoSiaf;

    /*
     * Monto del consumo de contrato
     */
    private BigDecimal moConsumoContrato;

    /*
     * campo de ayuda
     */
    private BigDecimal descSaldo;
    
    /*
     * Numero del contrato
     */
    private String descNuContrato;
    
    /*
     * Razon social de la empresa supervisora
     */
    private String descNoRazonSocial;
    
	/*
     * Nombre del archivo a exportar
     */
	private String descNoArchivo;

    /*
     * Extensión del archivo a exportar
     */
	private String descExtension;

    /**
     * Nombre del titulo de reporte
     */
    private String deTituloReporte;
}
