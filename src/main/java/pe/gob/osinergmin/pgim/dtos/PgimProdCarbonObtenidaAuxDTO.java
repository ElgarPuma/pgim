package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_PRODUC_CARBON_OBT.:
 * 
 * @descripción: Vista para obtener la lista de reporte de producción
 *               carbonífera
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Getter
@Setter
public class PgimProdCarbonObtenidaAuxDTO {

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
     * CATEGORIA
     */
    private String categoria;

    /*
     * CODIGO
     */
    private String codigo;

    /*
     * UNIDAD
     */
    private String unidad;

    /*
     * CODIGO_INTEGRANTE_UEA
     */
    private String codigoIntegranteUea;

    /*
     * UNIDAD_INTEGRANTE
     */
    private String unidadIntegrante;

    /*
     * REGION
     */
    private String region;

    /*
     * PROVINCIA
     */
    private String provincia;

    /*
     * DISTRITO
     */
    private String distrito;

    /*
     * PRODUCTO
     */
    private String producto;

    /*
     * ID_UNIDAD_MEDIDA
     */
    private String idUnidadMedida;

    /*
     * DESCRIPCION
     */
    private String descripcion;

    /*
     * CANTIDAD
     */
    private BigDecimal cantidad;

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
