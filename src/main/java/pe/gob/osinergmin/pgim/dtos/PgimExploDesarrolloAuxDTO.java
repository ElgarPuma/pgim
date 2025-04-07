package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
* Clase Entidad para la tabla MV_OSIN_EXPLO_DESAR: 
* @descripción: Vista para obtener la lista de reporte de cumplimiento del programa de exploración y desarrollo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/

@Getter
@Setter
public class PgimExploDesarrolloAuxDTO {
    
    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_EXPLO_DESAR_AUX. Secuencia:
     * MV_OSIN_EXPLO_DESAR_AUX
     */
    private Long idCliente;

    /*
     * ANOPRO
     */
    private String anioPro;

    /*
     * MES
     */
    private String mes;

    /*
     * RUC
     */
    private String ruc;

    /*
     * TITULAR_MINERO
     */
    private String titularMinero;

    /*
     * ESTRATO
     */
    private String estrato;

    /*
     * CODIGO
     */
    private String codigo;

    /*
     * UNIDAD
     */
    private String unidad;

    /*
     * LABOR
     */
    private String labor;

    /*
     * EXPLORACION_CANTIDAD_EJECUTADA
     */
    private BigDecimal exploracionCantidadEjecutada;

    /*
     * EXPLORACION_NRO_LABORES
     */
    private BigDecimal exploracionNroLabores;

    /*
     * DESARROLLO_CANTIDAD_EJECUTADA
     */
    private BigDecimal desarrolloCantidadEjecutada;

    /*
     * DESARROLLO_NRO_LABORES
     */
    private BigDecimal desarrolloNroLabores;

    /*
     * PREPARACION_CANTIDAD_EJECUTADA
     */
    private BigDecimal preparacionCantidadEjecutada;

    /*
     * PREPARACION_NRO_LABORES
     */
    private BigDecimal preparacionNroLabores;

    /*
     * EXPLOTACION_CANTIDAD_EJECUTADA
     */
    private BigDecimal explotacionCantidadEjecutada;

    /*
     * EXPLOTACION_NRO_LABORES
     */
    private BigDecimal explotacionNroLabores;

    /**
     * Periodo inicial
     */
    private String descPeriodoInicial;

    /**
     * Periodo final
     */
    private String descPeriodoFinal;

    /*
     * Descripción titulo del reporte
     */
    private String deTituloReporte;

    /*
     * Nombre de archivo
     */
    private String descNoArchivo;

    /*
     * Tipo de extensión
     */
    private String descExtension;
    
    /*
    * Cantidad de registros
    */
    private Integer cantidadRegistros;

    /**
   * Nombre de unidad fiscalizable
   */
    private String descNoUnidadMinera;
}
