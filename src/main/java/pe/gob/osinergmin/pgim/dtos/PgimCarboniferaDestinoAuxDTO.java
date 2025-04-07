package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_CARBO_DESTINO:
 * 
 * @descripción: Vista para obtener la lista de reporte de destino de la producción carbonífera
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Setter
@Getter
public class PgimCarboniferaDestinoAuxDTO {
    
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
     * RECURSO_EXTRAIDO
     */
    private String recursoExtraido;

    /*
     * DESTINO
     */
    private String destino;

    /*
     * PAIS
     */
    private String pais;

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

    /*
     * VALOR
     */
    private BigDecimal valor;

    /*
     * ID_MONEDA
     */
    private String idMoneda;

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
