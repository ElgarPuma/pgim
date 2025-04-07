package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_TITULAR:
 * 
 * @descripción: Vista para obtener la lista de reporte de identificación y ubicación del titular
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Setter
@Getter
public class PgimTitularAuxDTO {
    
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
     * ID_REPRESENTANTE
     */
    private Long idRepresentante;

    /*
     * NOMBRE_REPRESENTANTE_LEGAL
     */
    private String nombreRepresentanteLegal;

    /*
     * EMAIL
     */
    private String email;

    /*
     * CARGO_REPRESENTANTE
     */
    private String cargoRepresentante;

    /*
     * NOMBRERESPONSABLE
     */
    private String nombreresponsable;

    /*
     * CARGO_RESPONSABLE
     */
    private Long cargoResponsable;

    /*
     * DESCRIPCION
     */
    private String descripcion;

    /*
     * RESP_NO_DOCUMENTO
     */
    private String respNoDocumento;

    /*
     * RESP_EMAIL
     */
    private String respEmail;

    /*
     * RESP_TELEFONO
     */
    private String respTelefono;
 
    /*
     * RESP_TELEFONO_MOVIL
     */
    private String respTelefonoMovil;
 
    /*
     * ID_CARGO
     */
    private Long idCargo;

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
