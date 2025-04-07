package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* Clase Entidad para la tabla MV_OSIN_PRODUCCION_OBTEN: 
* @descripción: Vista para obtener la lista de producción obtenida en el reporte Estamin
*
* @author: jvalerio
* @version 1.0
* @fecha_de_creación: 23/12/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimProduccionObtAuxDTO {

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
     * NOMBRE_VENDEDOR
     */
    private String nombreVendedor;

    /*
     * PRODUCTO
     */
    private String producto;

    /*
     * RATIO
     */
    private BigDecimal ratio;

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
     * PCT_U
     */
    private String pctu;

    /*
     * PCT_NI
     */
    private String pctNi;

    /*
     * PCT_MG
     */
    private String pctMg;

    /*
     * Nombre del archivo a exportar
     */
    private String descNoArchivo;

    /*
     * Extensión del archivo a exportar
     */
    private String descExtension;

    /*
     * Título del reporte
     */
    private String deTituloReporte;

    /**
     * Fecha de inicio
     */
    private String descFeInicio;

    /**
     * Fecha fin
     */
    private String descFeFin;
    
    /**
   * Nombre de unidad fiscalizable
   */
  private String descNoUnidadMinera;
}
