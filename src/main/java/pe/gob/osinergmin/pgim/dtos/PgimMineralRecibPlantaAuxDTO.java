package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_MINERAL_RECIB_PLA:
 * 
 * @descripción: Vista para obtener la lista de reporte de mineral recibido en planta
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Getter
@Setter
public class PgimMineralRecibPlantaAuxDTO {
    
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
     * PROCESO
     */
    private String proceso;

    /*
     * REGION
     */
    private String region;

    /*
     * PROCEDENCIA
     */
    private String procedencia;

    /*
     * OBTENIDO_DE
     */
    private String obtenidoDe;

    /*
     * UNIDAD_OBTENIDO
     */
    private String unidadObtenido;

    /*
     * RUC_PROVEEDOR
     */
    private Long rucProveedor;

    /*
     * NOMBRE_PROVEEDOR
     */
    private String nombreProveedor;

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
     * CANTIDAD_RECIBIDA
     */
    private BigDecimal cantidadRecibida;

    /*
     * CANTIDAD_PROCESADA
     */
    private BigDecimal cantidadProcesada;

    /*
     * PCT_CU
     */
    private String pctCu;

    /*
     * PCT_PB
     */
    private String pctPb;

    /*
     * PCT_ZN
     */
    private String pctZn;

    /*
     * AG_OZ_TC
     */
    private String agOzTc;

    /*
     * AU_GR_TM
     */
    private String auGrTm;

    /*
     * PCT_FE
     */
    private String pctFe;

    /*
     * PCT_MO
     */
    private String pctMo;

    /*
     * PCT_SN
     */
    private String pctSn;

    /*
     * PCT_CD
     */
    private String pctCd;

    /*
     * PCT_WO3
     */
    private String pctWo3;

    /*
     * PCT_SB
     */
    private String pctSb;

    /*
     * PCT_AS
     */
    private String pctAs;

    /*
     * PCT_MN
     */
    private String pctMn;

    /*
     * PCT_BI
     */
    private String pctBi;

    /*
     * PCT_HG
     */
    private String pcHg;

    /*
     * PCT_IN
     */
    private String pctIn;

    /*
     * PCT_SE
     */
    private String pctSe;

    /*
     * PCT_TE
     */
    private String pctTe;

    /*
     * H2SO4
     */
    private String h2so4;
    
    /*
     * U
     */
    private String u;

    /*
     * PCT_NI
     */
    private String pctNi;
    
    /*
     * PCT_MG
     */
    private String pctMg;

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
