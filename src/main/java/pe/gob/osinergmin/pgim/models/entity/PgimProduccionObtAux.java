package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla MV_OSIN_PRODUCCION_OBTEN: 
* @descripción: Vista para obtener la lista de producción obtenida en el reporte Estamin
*
* @author: jvalerio
* @version 1.0
* @fecha_de_creación: 23/12/2022
*/
@Entity
@Table(name = "MV_OSIN_PRODUCCION_OBTEN", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimProduccionObtAux implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_PRODUCCION_OBTEN_AUX. Secuencia:
     * MV_OSIN_PRODUCCION_OBTEN_AUX
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_PRODUCCION_OBTEN_AUX")
    @SequenceGenerator(name = "MV_OSIN_PRODUCCION_OBTEN_AUX", sequenceName = "MV_OSIN_PRODUCCION_OBTEN_AUX", allocationSize = 1)
    @Column(name = "ID_CLIENTE", nullable = true)
    private Long idCliente;

    /*
     * ANOPRO
     */
    @Column(name = "ANOPRO", nullable = true)
    private String anioPro;

    /*
     * MES
     */
    @Column(name = "MES", nullable = true)
    private String mes;

    /*
     * RUC
     */
    @Column(name = "RUC", nullable = true)
    private String ruc;

    /*
     * TITULAR_MINERO
     */
    @Column(name = "TITULAR_MINERO", nullable = true)
    private String titularMinero;

    /*
     * ESTRATO
     */
    @Column(name = "ESTRATO", nullable = true)
    private String estrato;

    /*
     * CODIGO
     */
    @Column(name = "CODIGO", nullable = true)
    private String codigo;

    /*
     * UNIDAD
     */
    @Column(name = "UNIDAD", nullable = true)
    private String unidad;

    /*
     * PROCESO
     */
    @Column(name = "PROCESO", nullable = true)
    private String proceso;

    /*
     * REGION
     */
    @Column(name = "REGION", nullable = true)
    private String region;

    /*
     * PROCEDENCIA
     */
    @Column(name = "PROCEDENCIA", nullable = true)
    private String procedencia;

    /*
     * NOMBRE_VENDEDOR
     */
    @Column(name = "NOMBRE_VENDEDOR", nullable = true)
    private String nombreVendedor;

    /*
     * PRODUCTO
     */
    @Column(name = "PRODUCTO", nullable = true)
    private String producto;

    /*
     * RATIO
     */
    @Column(name = "RATIO", nullable = true)
    private BigDecimal ratio;

    /*
     * ID_UNIDAD_MEDIDA
     */
    @Column(name = "ID_UNIDAD_MEDIDA", nullable = true)
    private String idUnidadMedida;

    /*
     * DESCRIPCION
     */
    @Column(name = "DESCRIPCION", nullable = true)
    private String descripcion;

    /*
     * CANTIDAD
     */
    @Column(name = "CANTIDAD", nullable = true)
    private BigDecimal cantidad;

    /*
     * PCT_CU
     */
    @Column(name = "PCT_CU", nullable = true)
    private String pctCu;

    /*
     * PCT_PB
     */
    @Column(name = "PCT_PB", nullable = true)
    private String pctPb;

    /*
     * PCT_ZN
     */
    @Column(name = "PCT_ZN", nullable = true)
    private String pctZn;

    /*
     * AG_OZ_TC
     */
    @Column(name = "AG_OZ_TC", nullable = true)
    private String agOzTc;

    /*
     * AU_GR_TM
     */
    @Column(name = "AU_GR_TM", nullable = true)
    private String auGrTm;

    /*
     * PCT_FE
     */
    @Column(name = "PCT_FE", nullable = true)
    private String pctFe;

    /*
     * PCT_MO
     */
    @Column(name = "PCT_MO", nullable = true)
    private String pctMo;

    /*
     * PCT_SN
     */
    @Column(name = "PCT_SN", nullable = true)
    private String pctSn;

    /*
     * PCT_CD
     */
    @Column(name = "PCT_CD", nullable = true)
    private String pctCd;

    /*
     * PCT_WO3
     */
    @Column(name = "PCT_WO3", nullable = true)
    private String pctWo3;

    /*
     * PCT_SB
     */
    @Column(name = "PCT_SB", nullable = true)
    private String pctSb;

    /*
     * PCT_AS
     */
    @Column(name = "PCT_AS", nullable = true)
    private String pctAs;

    /*
     * PCT_MN
     */
    @Column(name = "PCT_MN", nullable = true)
    private String pctMn;

    /*
     * PCT_BI
     */
    @Column(name = "PCT_BI", nullable = true)
    private String pctBi;

    /*
     * PCT_HG
     */
    @Column(name = "PCT_HG", nullable = true)
    private String pcHg;

    /*
     * PCT_IN
     */
    @Column(name = "PCT_IN", nullable = true)
    private String pctIn;

    /*
     * PCT_SE
     */
    @Column(name = "PCT_SE", nullable = true)
    private String pctSe;

    /*
     * PCT_TE
     */
    @Column(name = "PCT_TE", nullable = true)
    private String pctTe;

    /*
     * H2SO4
     */
    @Column(name = "H2SO4", nullable = true)
    private String h2so4;

    /*
     * PCT_U
     */
    @Column(name = "PCT_U", nullable = true)
    private String pctu;

    /*
     * PCT_NI
     */
    @Column(name = "PCT_NI", nullable = true)
    private String pctNi;
    
    /*
     * PCT_MG
     */
    @Column(name = "PCT_MG", nullable = true)
    private String pctMg;
}
