package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_INDICADOR_GEOTEC:
 * 
 * @descripción: Vista para obtener la lista de indicadores geotecnicos en el
 *               reporte Estamin
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 10/01/2023
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimIndicadorGeotecnicoAuxDTO {

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
     * GRUPO
     */
    private String grupo;

    /*
     * INDICADOR
     */
    private String indicador;

    /*
     * VALOR
     */
    private String valor;

    /*
     * UNIDAD_MEDIDA
     */
    private String unidadMedida;

    /*
     * Periodo inicial. Año y mes
     */
    private String descFeInicio;

    /*
     * Periodo final. Año y mes.
     */
    private String descFeFin;

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
