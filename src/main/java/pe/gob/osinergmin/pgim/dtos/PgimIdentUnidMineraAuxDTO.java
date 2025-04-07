package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_IDENT_UNID_MINERA:
 * 
 * @descripción: Vista para obtener la lista de reporte de identificación de concesiones y/o UEA
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Getter
@Setter
public class PgimIdentUnidMineraAuxDTO {

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
     * ID_CLASE_SUSTANCIA
     */
    private String idClaseSustancia;

    /*
     * ID_SITUACIONUP
     */
    private String idSituacionup;

    /*
     * DES_SITUACIONUP
     */
    private String desSituacionup;

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
