package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_PROGRAMA_INVERS:
 * 
 * @descripción: Vista para obtener la lista de reporte de programa de inversiones
 *               
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 07/03/2023
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimProgramaInversionAuxDTO {
    
    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_PROGRAMA_INVERS_AUX. Secuencia:
     * MV_OSIN_PROGRAMA_INVERS_AUX
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
     * TIPO_INVERSION
     */
    private String tipoInversion;

    /*
     * NIVEL_DETALLE
     */
    private Integer nivelDetalle;

    /*
     * ORDEN
     */
    private Integer orden;

    /*
     * OTROS_DESCRIPCION
     */
    private String otrosDescripcion;

    /*
     * CANTIDAD_PROGRAMADA
     */
    private Integer cantidadProgramada;
    
    /*
    * CANTIDAD_EJECUTADA
    */
    private Integer cantidadEjecutada;
    
    /*
    * CAPEX_SOSTENIMIENTO
    */
    private Integer capexSostenimiento;
    
    /*
    * CAPEX_CRECIMIENTO
    */
    private Integer capexCrecimiento;

    /**
     * Número de registros que contiene la vista del reporte
     */
    private Integer cantidadRegistros;

    /**
     * Periodo inicial
     */
    private String descPeriodoInicial;

    /**
     * Periodo final
     */
    private String descPeriodoFinal;

    /*
     * Titulo del reporte
     */
    private String deTituloReporte;

    /*
     * Nombre del archivo
     */
    private String descNoArchivo;

    /**
     * Nombre de la extensión del archivo
     */
    private String descExtension;
    
    /**
   * Nombre de unidad fiscalizable
   */
  private String descNoUnidadMinera;
}
