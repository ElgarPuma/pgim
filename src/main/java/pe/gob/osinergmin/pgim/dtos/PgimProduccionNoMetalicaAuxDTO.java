package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * Clase Entidad para la tabla MV_OSIN_PRODUC_NO_METALIC:
 * 
 * @descripción: Vista para obtener la lista de producciones no metálicas
 *               
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 07/03/2023
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimProduccionNoMetalicaAuxDTO {
    
    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_PRODUC_NO_METALIC_AUX. Secuencia:
     * MV_OSIN_PRODUC_NO_METALIC_AUX
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
    * PRODUCTO_EXTRAIDO
    */
    private String productoExtraido;
    
    /*
    * CANTIDAD_EXTRAIDO
    */
    private BigDecimal cantidadExtraido;
    
    /*
    * PRODUCTO_FINAL
    */
    private String productoFinal;
    
    /*
    * CANTIDAD_PRODUCTO_FINAL
    */
    private BigDecimal cantidadProductoFinal;
    
    /*
    * ID_UNIDAD_MEDIDA
    */
    private String idUnidadMedida;
    
    /*
     * DESCRIPCION
     */
    private String descripcion;

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
