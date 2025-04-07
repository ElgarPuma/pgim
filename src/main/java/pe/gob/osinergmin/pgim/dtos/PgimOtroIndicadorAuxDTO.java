package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_OTROS_INDICADORES:
 * 
 * @descripción: Vista para obtener la lista de otros indicadores
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 07/03/2023
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimOtroIndicadorAuxDTO {

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_OTROS_INDICADORES_AUX. Secuencia:
     * MV_OSIN_OTROS_INDICADORES_AUX
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
     * PROCESO
     */
    private String proceso;

    /*
     * CONCEPTO
     */
    private String concepto;

    /*
     * VALOR
     */
    private String valor;

    /*
     * UNIDAD_MEDIDA
     */
    private String unidadMedida;

    /*
     * EXPLICACION
     */
    private String explicacion;

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
