package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_IND_DESEMP_OPERAT:
 * 
 * @descripción: Vista para obtener la lista de indicadores de desempeños
 *               operativo
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 07/03/2023
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimIndicadorDesempenioAuxDTO {

    /*
     * ID_CLIENTE
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
     * COEFICIENTE
     */
    private String coeficiente;

    /*
     * UNIDAD_M
     */
    private String unidadM;

    /*
     * DESCRIPCION
     */
    private String descripcion;

    /*
     * CANTIDAD
     */
    private String cantidad;

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
