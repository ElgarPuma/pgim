package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_HECHOS_IMPORTAN.:
 * 
 * @descripción: Vista para obtener la lista de reporte de información de hechos de importancia
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Getter
@Setter
public class PgimInfoHechosImportAuxDTO {
    
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
     * HECHOS_AFECTAN_DECLARACION
     */
    private String hechosAfectanDeclaracion;

    /*
     * INFO_GESTION_SOCIAL
     */
    private String infoGestionSocial;
    
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
}
